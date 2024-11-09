package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Requirement;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.ResearchCategory;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.ResearchCategoryType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Research;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager.class */
public class ResearchManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2438a = TLog.forClass(ResearchManager.class);
    public static final int MAP_SIZE = 8192;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private float m;
    private int n;
    private int o;

    /* renamed from: b, reason: collision with root package name */
    private final Array<Research> f2439b = new Array<>(true, ResearchType.values.length, Research.class);
    private final ObjectMap<ResearchCategoryType, ResearchCategory> c = new ObjectMap<>();
    private final Array<Research.ResearchLink> d = new Array<>();
    private final Array<PolygonConfig> e = new Array<>(PolygonConfig.class);
    private boolean l = false;
    private final StartResearchingException p = new StartResearchingException();
    private final DelayedRemovalArray<ResearchManagerListener> q = new DelayedRemovalArray<>(false, 1);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$StartResearchFailReason.class */
    public enum StartResearchFailReason {
        OTHER_RESEARCH_IN_PROGRESS,
        NOT_VISIBLE,
        MAX_LEVEL,
        REQUIRES_PREVIOUS_RESEARCHES,
        NOT_ENOUGH_MONEY,
        NOT_ENOUGH_RESOURCES,
        NOT_ENOUGH_STARS,
        NOT_ENOUGH_ITEMS,
        REQUIREMENT_NOT_SATISFIED;

        public static final StartResearchFailReason[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$StartResearchingException.class */
    public static final class StartResearchingException extends RuntimeException {
        public final Array<StartResearchFailReason> reasons = new Array<>();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ResearchManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ResearchManager read() {
            return Game.i.researchManager;
        }
    }

    public int getInstalledLevel(ResearchType researchType) {
        return ProgressPrefs.i().research.getInstalledLevel(researchType);
    }

    public boolean isVisible(Research research) {
        TowerType relatedToTowerType;
        if (research.endlessOnly && !DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
            return false;
        }
        if (Config.isModdingMode() || (relatedToTowerType = research.getRelatedToTowerType()) == null || Game.i.towerManager.getFactory(relatedToTowerType).isAvailable(Game.i.gameValueManager.getSnapshot())) {
            return true;
        }
        return research.isUnlocksTower() && research.linksToParents.first().parent.getInstalledLevel() > 0;
    }

    private void b() {
        if (!this.l) {
            throw new IllegalStateException("manager is not set up yet");
        }
    }

    public Research getInstance(ResearchType researchType) {
        b();
        return this.f2439b.items[researchType.ordinal()];
    }

    public Research getInstancePreSetup(ResearchType researchType) {
        return this.f2439b.items[researchType.ordinal()];
    }

    public Array<Research> getInstances() {
        b();
        return this.f2439b;
    }

    public Array<Research.ResearchLink> getLinks() {
        b();
        return this.d;
    }

    public Array<PolygonConfig> getPolygonSprites() {
        b();
        return this.e;
    }

    public void resetResearchForAccelerators(Research research) {
        int resetForAcceleratorsState = research.resetForAcceleratorsState();
        if (resetForAcceleratorsState != 0) {
            f2438a.e("can not be reset now " + research.type + " state " + resetForAcceleratorsState, new Object[0]);
            return;
        }
        int resetPrice = research.getResetPrice();
        if (resetPrice <= 0) {
            f2438a.e("reset price is " + resetPrice, new Object[0]);
            return;
        }
        Array<ItemStack> cumulativePrice = research.getCumulativePrice(0, research.getInstalledLevel(), true);
        if (Game.i.progressManager.removeAccelerators(resetPrice)) {
            research.setInstalledLevel(0);
            if (research.levels[0].price.size == 0) {
                research.setInstalledLevel(1);
            }
            int i = 0;
            while (true) {
                if (i >= cumulativePrice.size) {
                    break;
                }
                if (cumulativePrice.get(i).getItem().getType() != ItemType.ACCELERATOR) {
                    i++;
                } else {
                    cumulativePrice.removeIndex(i);
                    break;
                }
            }
            ProgressPrefs.i().research.resetLevelsInstalledForTokens(research.type);
            ProgressPrefs.i().requireSave();
            Game.i.progressManager.addItemArray(cumulativePrice, "research_reset_refund");
            f2438a.i("reset " + research.type + " for accelerators", new Object[0]);
            g();
            return;
        }
        Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_accelerators"));
    }

    private void c() {
        b();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.f2439b.size; i4++) {
            Research research = this.f2439b.items[i4];
            if (research.getInstalledLevel() > 0) {
                i += research.getInstalledLevel();
                i2++;
                if (research.priceInStars == 0) {
                    boolean z = false;
                    if (research.type == ResearchType.DECRYPTING_QUEUE_SIZE) {
                        z = true;
                    } else {
                        Research.ResearchLevel researchLevel = research.levels[0];
                        int i5 = 0;
                        while (true) {
                            if (i5 >= researchLevel.price.size) {
                                break;
                            }
                            if (researchLevel.price.items[i5].getItem().getType() != ItemType.PRESTIGE_TOKEN) {
                                i5++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (!z && !research.endlessOnly) {
                        i3 += Math.min(research.getInstalledLevel(), research.levels.length);
                    }
                }
            }
        }
        if (Game.i.statisticsManager.getAllTime(StatisticsType.RC) < i2) {
            Game.i.statisticsManager.registerValue(StatisticsType.RC, i2);
        }
        if (Game.i.statisticsManager.getAllTime(StatisticsType.RCL) < i) {
            Game.i.statisticsManager.registerValue(StatisticsType.RCL, i);
        }
        Game.i.achievementManager.setProgress(AchievementType.FIVE_HUNDRED_RESEARCH, i2);
        Game.i.achievementManager.setProgress(AchievementType.FULL_REGULAR_RESEARCH, i3);
    }

    public boolean isSetUp() {
        return this.l;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (!Config.isHeadless()) {
            Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.ResearchManager.1
                @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
                public void reloaded() {
                    ResearchManager.this.reloadNew();
                    ResearchManager.this.checkResearchesStatus(false);
                }
            });
        }
        reloadNew();
        if (!Config.isHeadless()) {
            addListener(new ResearchManagerListener.ResearchManagerListenerAdapter(this) { // from class: com.prineside.tdi2.managers.ResearchManager.2
                @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
                public void researchCompleted(Research research) {
                    Game.i.analyticsManager.logCustomEvent("research_completed", new String[]{"type", research.type.name(), "level", String.valueOf(research.getInstalledLevel())}, new String[0]);
                }
            });
        }
        this.l = true;
        if (!Config.isHeadless()) {
            Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
                checkResearchesStatus(false);
                updateAndValidateStarBranch();
            });
        }
    }

    public void installAllResearches() {
        b();
        Array.ArrayIterator<Research> it = this.f2439b.iterator();
        while (it.hasNext()) {
            Research next = it.next();
            if (!next.endlessOnly && next.getInstalledLevel() < next.levels.length) {
                next.setInstalledLevel(next.levels.length);
            }
        }
        g();
        c();
    }

    public void installAllEndlessResearches() {
        b();
        Array.ArrayIterator<Research> it = this.f2439b.iterator();
        while (it.hasNext()) {
            Research next = it.next();
            if (next.getInstalledLevel() < next.maxEndlessLevel) {
                next.setInstalledLevel(next.maxEndlessLevel);
            }
        }
        g();
        c();
    }

    public void updateAfforableResearchesCount() {
        b();
        this.n = 0;
        for (int i = 0; i < this.f2439b.size; i++) {
            if (canStartResearching(this.f2439b.items[i], false)) {
                this.n++;
            }
        }
    }

    public int getAfforableResearchesCount() {
        return this.n;
    }

    public boolean isLinkedToRoot(Research research, ObjectSet<ResearchType> objectSet) {
        if (research.type == ResearchType.ROOT) {
            return true;
        }
        objectSet.add(research.type);
        if (research.getInstalledLevel() == 0) {
            return false;
        }
        for (int i = 0; i < research.linksToParents.size; i++) {
            Research research2 = research.linksToParents.items[i].parent;
            if (!objectSet.contains(research2.type) && isLinkedToRoot(research2, objectSet)) {
                return true;
            }
        }
        for (int i2 = 0; i2 < research.linksToChildren.size; i2++) {
            Research research3 = research.linksToChildren.items[i2].child;
            if (!objectSet.contains(research3.type) && isLinkedToRoot(research3, objectSet)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLinkedToRoot(Research research, IntIntMap intIntMap, ObjectSet<ResearchType> objectSet) {
        if (research.type == ResearchType.ROOT) {
            return true;
        }
        objectSet.add(research.type);
        if (intIntMap.get(research.type.ordinal(), 0) == 0) {
            return false;
        }
        for (int i = 0; i < research.linksToParents.size; i++) {
            Research research2 = research.linksToParents.items[i].parent;
            if (!objectSet.contains(research2.type) && isLinkedToRoot(research2, intIntMap, objectSet)) {
                return true;
            }
        }
        for (int i2 = 0; i2 < research.linksToChildren.size; i2++) {
            Research research3 = research.linksToChildren.items[i2].child;
            if (!objectSet.contains(research3.type) && isLinkedToRoot(research3, intIntMap, objectSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean d() {
        boolean z = false;
        ObjectSet<ResearchType> objectSet = new ObjectSet<>();
        boolean z2 = true;
        while (z2) {
            z2 = false;
            int i = 0;
            while (true) {
                if (i < this.f2439b.size) {
                    Research research = this.f2439b.items[i];
                    if (research.priceInStars > 0 && research.getInstalledLevel() > 0) {
                        objectSet.clear();
                        if (!isLinkedToRoot(research, objectSet)) {
                            f2438a.e("reverting " + research.type + " - not linked to ROOT", new Object[0]);
                            research.setInstalledLevel(0);
                            z = true;
                            z2 = true;
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean e() {
        boolean z = false;
        int gainedStars = Game.i.basicLevelManager.getGainedStars();
        int i = 0;
        for (int i2 = 0; i2 < this.f2439b.size; i2++) {
            if (this.f2439b.items[i2].priceInStars > 0 && this.f2439b.items[i2].getInstalledLevel() > 0) {
                i += this.f2439b.items[i2].priceInStars;
            }
        }
        this.o = gainedStars - i;
        if (this.o < 0) {
            f2438a.i("unused stars count: " + this.o, new Object[0]);
            Array array = new Array(Research.class);
            for (int i3 = 0; i3 < this.f2439b.size; i3++) {
                if (this.f2439b.items[i3].priceInStars > 0 && this.f2439b.items[i3].getInstalledLevel() != 0) {
                    array.add(this.f2439b.items[i3]);
                }
            }
            array.sort((research, research2) -> {
                return Float.compare(research.distanceToCenter, research2.distanceToCenter);
            });
            for (int i4 = 0; i4 < array.size; i4++) {
                Research research3 = ((Research[]) array.items)[i4];
                research3.setInstalledLevel(0);
                z = true;
                f2438a.e("reverting " + research3.type.name() + " - exceeds amount of stars", new Object[0]);
                this.o += research3.priceInStars;
                if (this.o >= 0) {
                    break;
                }
            }
        }
        return z;
    }

    public void updateAndValidateStarBranch() {
        b();
        boolean z = true;
        boolean z2 = false;
        while (z) {
            z = false;
            if (d()) {
                z = true;
            }
            if (e()) {
                z = true;
            }
            if (z) {
                z2 = true;
            }
        }
        if (z2) {
            g();
            c();
        }
    }

    public void installRecursiveFree(int i) {
        b();
        Game.i.statisticsManager.registerDelta(StatisticsType.TDD, 1.0E8d);
        for (TowerType towerType : TowerType.values) {
            Game.i.statisticsManager.registerDelta(Game.i.towerManager.getDamageDealtStatisticType(towerType), 1.0E8d);
        }
        a(getInstance(ResearchType.ROOT), i, new int[]{0}, new int[]{0, 0, 0, 0, 0});
        g();
        c();
    }

    private void a(Research research, int i, int[] iArr, int[] iArr2) {
        if (research.priceInStars <= 0 && research.type != ResearchType.PRESTIGE) {
            Array.ArrayIterator<Research.ResearchLink> it = research.linksToChildren.iterator();
            while (it.hasNext()) {
                Research research2 = it.next().child;
                for (Research.ResearchLevel researchLevel : research2.levels) {
                    boolean z = true;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= researchLevel.price.size) {
                            break;
                        }
                        ItemStack itemStack = researchLevel.price.items[i2];
                        if (itemStack.getItem().getType() != ItemType.RESOURCE || ((ResourceItem) itemStack.getItem()).resourceType.ordinal() <= i) {
                            i2++;
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (z && Game.i.researchManager.canStartResearching(research2, true)) {
                        for (int i3 = 0; i3 < researchLevel.price.size; i3++) {
                            ItemStack itemStack2 = researchLevel.price.items[i3];
                            if (itemStack2.getItem().getType() == ItemType.GREEN_PAPER) {
                                iArr[0] = iArr[0] + itemStack2.getCount();
                            } else if (itemStack2.getItem().getType() == ItemType.RESOURCE) {
                                int ordinal = ((ResourceItem) itemStack2.getItem()).resourceType.ordinal();
                                iArr2[ordinal] = iArr2[ordinal] + itemStack2.getCount();
                            }
                        }
                        research2.setInstalledLevel(researchLevel.number);
                    }
                }
                a(research2, i, iArr, iArr2);
            }
        }
    }

    public int getLoadedConfigHash() {
        int i = ((((((((((31 + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k;
        for (int i2 = 0; i2 < this.d.size; i2++) {
            Research.ResearchLink researchLink = this.d.get(i2);
            i = (((((((((((((((i * 31) + researchLink.pivotX) * 31) + researchLink.pivotY) * 31) + researchLink.parent.type.ordinal()) * 31) + researchLink.child.type.ordinal()) * 31) + researchLink.requiredLevels) * 31) + researchLink.requiredLevelsLabelX) * 31) + researchLink.requiredLevelsLabelY) * 31) + ((int) (researchLink.requiredLevelsLabelPos * 100.0f));
        }
        for (int i3 = 0; i3 < this.f2439b.size; i3++) {
            Research research = this.f2439b.get(i3);
            i = (((((((((((((((((((((((((((i * 31) + research.type.ordinal()) * 31) + research.category.alias.ordinal()) * 31) + research.priceInStars) * 31) + research.x) * 31) + research.y) * 31) + research.maxEndlessLevel) * 31) + research.endlessPriceLevel) * 31) + ((int) research.distanceToCenter)) * 31) + (research.cantBeIgnoredOnUserMaps ? 1 : 0)) * 31) + (research.unlocksTower ? 1 : 0)) * 31) + ((int) (research.endlessPriceExp * 100.0f))) * 31) + (research.endlessOnly ? 1 : 0)) * 31) + (research.small ? 1 : 0)) * 31) + (research.relatedToTowerType == null ? -1 : research.relatedToTowerType.ordinal());
            for (int i4 = 0; i4 < research.linksToChildren.size; i4++) {
                Research.ResearchLink researchLink2 = research.linksToChildren.get(i4);
                i = (((i * 31) + researchLink2.parent.type.ordinal()) * 31) + researchLink2.child.type.ordinal();
            }
            for (int i5 = 0; i5 < research.linksToParents.size; i5++) {
                Research.ResearchLink researchLink3 = research.linksToParents.get(i5);
                i = (((i * 31) + researchLink3.parent.type.ordinal()) * 31) + researchLink3.child.type.ordinal();
            }
            if (research.endlessLevel != null) {
                i = (((((((((i * 31) + research.endlessLevel.prestigeTokens) * 31) + research.endlessLevel.priceBase) * 31) + research.endlessLevel.randomSeed) * 31) + research.endlessLevel.effects.length) * 31) + (research.endlessLevel.blueprintType == null ? -1 : research.endlessLevel.blueprintType.ordinal());
            }
            for (Research.ResearchLevel researchLevel : research.levels) {
                i = (((i * 31) + researchLevel.number) * 31) + researchLevel.researchDuration;
                for (GameValueManager.GameValueEffect gameValueEffect : researchLevel.effects) {
                    i = (((i * 31) + gameValueEffect.type.ordinal()) * 31) + ((int) (gameValueEffect.delta * 100.0d));
                }
                for (Requirement requirement : researchLevel.requirements) {
                    i = (((((((((((((((((((i * 31) + requirement.type.ordinal()) * 31) + requirement.researchLevel) * 31) + (requirement.researchType == null ? -1 : requirement.researchType.ordinal())) * 31) + (requirement.statisticsType == null ? -1 : requirement.statisticsType.ordinal())) * 31) + requirement.levelStars) * 31) + requirement.stageStars) * 31) + ((int) (requirement.statisticsValue * 100.0d))) * 31) + (requirement.levelName == null ? -1 : requirement.levelName.length())) * 31) + (requirement.stageName == null ? -1 : requirement.stageName.length())) * 31) + (requirement.openedLevelName == null ? -1 : requirement.openedLevelName.length());
                }
            }
        }
        for (ResearchCategoryType researchCategoryType : ResearchCategoryType.values) {
            ResearchCategory researchCategory = this.c.get(researchCategoryType);
            i = (((((((i * 31) + researchCategory.alias.ordinal()) * 31) + researchCategory.titleAlias.length()) * 31) + researchCategory.descriptionAlias.length()) * 31) + researchCategory.getIconString().length();
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x00db. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01f8 A[Catch: IOException -> 0x028d, TryCatch #0 {IOException -> 0x028d, blocks: (B:3:0x0040, B:4:0x005c, B:6:0x0066, B:7:0x0074, B:8:0x00a0, B:11:0x00af, B:14:0x00be, B:17:0x00cd, B:21:0x00db, B:42:0x00f8, B:43:0x00fd, B:45:0x0107, B:48:0x011c, B:49:0x0121, B:51:0x012b, B:69:0x0145, B:70:0x0164, B:53:0x0165, B:55:0x017f, B:56:0x0187, B:58:0x0192, B:59:0x019a, B:61:0x01a5, B:62:0x01ad, B:64:0x01b8, B:74:0x01c3, B:75:0x01c8, B:77:0x01d2, B:22:0x01f8, B:29:0x01fe, B:30:0x0203, B:32:0x020d, B:34:0x0216, B:25:0x0221, B:80:0x022e, B:82:0x0255, B:84:0x027a, B:86:0x0280), top: B:2:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x011c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x022e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void reloadNew() {
        /*
            Method dump skipped, instructions count: 663
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ResearchManager.reloadNew():void");
    }

    public ResearchCategory getCategory(ResearchCategoryType researchCategoryType) {
        return this.c.get(researchCategoryType);
    }

    public void reload() {
        this.d.clear();
        this.f2439b.clear();
        this.c.clear();
        this.e.clear();
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MIN_VALUE;
        this.h = Integer.MAX_VALUE;
        this.i = Integer.MIN_VALUE;
        JsonValue parse = new JsonReader().parse(Gdx.files.internal("res/researches.json"));
        Iterator<JsonValue> iterator2 = parse.get("categories").iterator2();
        while (iterator2.hasNext()) {
            JsonValue next = iterator2.next();
            String string = next.getString("alias");
            String str = "research_title_" + string;
            String str2 = "research_description_" + string;
            ResearchCategoryType valueOf = ResearchCategoryType.valueOf(string);
            this.c.put(valueOf, new ResearchCategory(valueOf, str, str2, next.getString("icon")));
        }
        this.f2439b.setSize(ResearchType.values.length);
        int i = 0;
        Iterator<JsonValue> iterator22 = parse.get("researches").iterator2();
        while (iterator22.hasNext()) {
            JsonValue next2 = iterator22.next();
            ResearchType valueOf2 = ResearchType.valueOf(next2.getString(Attribute.NAME_ATTR));
            if (next2.has("category")) {
                ResearchCategory researchCategory = this.c.get(ResearchCategoryType.valueOf(next2.getString("category")));
                int i2 = next2.getInt("maxEndlessModeLevels");
                if (this.f2439b.items[valueOf2.ordinal()] != null) {
                    throw new RuntimeException("Research " + valueOf2.name() + " already exists");
                }
                Array array = new Array(Research.ResearchLevel.class);
                int i3 = 1;
                Iterator<JsonValue> iterator23 = next2.get("levels").iterator2();
                while (iterator23.hasNext()) {
                    JsonValue next3 = iterator23.next();
                    Array array2 = new Array(GameValueManager.GameValueEffect.class);
                    Iterator<JsonValue> iterator24 = next3.get("effects").iterator2();
                    while (iterator24.hasNext()) {
                        JsonValue next4 = iterator24.next();
                        try {
                            array2.add(new GameValueManager.GameValueEffect(GameValueType.valueOf(next4.name), next4.asDouble()));
                        } catch (Exception e) {
                            f2438a.e("failed loading gv " + next4.name + " for " + valueOf2.name() + " in research " + valueOf2.name(), e);
                        }
                    }
                    int i4 = next3.getInt("researchDuration", 0);
                    Array array3 = new Array(Requirement.class);
                    JsonValue jsonValue = next3.get("requirements");
                    if (jsonValue != null) {
                        Iterator<JsonValue> iterator25 = jsonValue.iterator2();
                        while (iterator25.hasNext()) {
                            array3.add(Requirement.fromJson(iterator25.next()));
                        }
                    }
                    try {
                        Research.ResearchLevel researchLevel = new Research.ResearchLevel();
                        researchLevel.number = i3;
                        researchLevel.researchDuration = i4;
                        researchLevel.effects = (GameValueManager.GameValueEffect[]) array2.toArray();
                        Iterator<JsonValue> iterator26 = next3.get("price").iterator2();
                        while (iterator26.hasNext()) {
                            JsonValue next5 = iterator26.next();
                            if (next5.name.equals("money")) {
                                researchLevel.price.add(new ItemStack(Item.D.GREEN_PAPER, next5.asInt()));
                            } else if (next5.name.startsWith("bp_")) {
                                researchLevel.price.add(new ItemStack(Item.D.F_BLUEPRINT.create(BlueprintType.valueOf(next5.name.substring(3))), next5.asInt()));
                            } else if (next5.name.equals("PRESTIGE_TOKEN")) {
                                researchLevel.price.add(new ItemStack(Item.D.PRESTIGE_TOKEN, next5.asInt()));
                            } else if (next5.name.equals("ACCELERATOR")) {
                                researchLevel.price.add(new ItemStack(Item.D.ACCELERATOR, next5.asInt()));
                            } else {
                                researchLevel.price.add(new ItemStack(Item.D.F_RESOURCE.create(ResourceType.valueOf(next5.name)), next5.asInt()));
                            }
                        }
                        researchLevel.requirements = (Requirement[]) array3.toArray();
                        array.add(researchLevel);
                        i3++;
                    } catch (Exception e2) {
                        throw new RuntimeException("Failed to add research level for " + valueOf2.name(), e2);
                    }
                }
                Research research = new Research(valueOf2, researchCategory, (Research.ResearchLevel[]) array.toArray(), i2);
                research.endlessOnly = next2.getBoolean("endlessOnly", false);
                research.endlessPriceExp = next2.getFloat("epe", 1.0f);
                int i5 = next2.getInt("epl", -1);
                int i6 = i5;
                if (i5 != -1) {
                    if (i6 > research.maxEndlessLevel) {
                        f2438a.e("endless mode price level is " + i6 + " while max is " + research.maxEndlessLevel + " for " + valueOf2.name(), new Object[0]);
                        i6 = research.maxEndlessLevel;
                    }
                    research.endlessPriceLevel = i6;
                }
                research.x = next2.getInt("x");
                research.y = next2.getInt("y");
                research.cantBeIgnoredOnUserMaps = next2.getBoolean("cantBeIgnoredOnUserMaps", false);
                research.small = next2.getBoolean("small", false);
                research.priceInStars = next2.getInt("starsPrice", 0);
                this.f2439b.set(research.type.ordinal(), research);
                if (research.x < this.f) {
                    this.f = research.x;
                }
                if (research.x > this.g) {
                    this.g = research.x;
                }
                if (research.y < this.h) {
                    this.h = research.y;
                }
                if (research.y > this.i) {
                    this.i = research.y;
                }
                research.distanceToCenter = 1.0f - ((PMath.getDistanceBetweenPoints(research.x, research.y, 4096.0f, 4096.0f) / 8192.0f) * 1.4142f);
                i++;
            } else {
                throw new IllegalStateException("Research " + valueOf2.name() + " has no category");
            }
        }
        if (i != ResearchType.values.length) {
            throw new RuntimeException("Number of upgrade types (" + ResearchType.values.length + ") doesn't match the number in JSON file (" + i + ")");
        }
        Iterator<JsonValue> iterator27 = parse.get("links").iterator2();
        while (iterator27.hasNext()) {
            JsonValue next6 = iterator27.next();
            Research research2 = this.f2439b.items[ResearchType.valueOf(next6.getString("parent")).ordinal()];
            Research research3 = this.f2439b.items[ResearchType.valueOf(next6.getString("child")).ordinal()];
            Research.ResearchLink researchLink = new Research.ResearchLink(research2, research3, next6.getInt("requiredLevels"), next6.getInt("pivotX"), next6.getInt("pivotY"), next6.getFloat("requiredLevelsLabelPos"));
            research2.linksToChildren.add(researchLink);
            research3.linksToParents.add(researchLink);
            this.d.add(researchLink);
        }
        if (!Config.isHeadless() && parse.has("polygons")) {
            ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
            EarClippingTriangulator earClippingTriangulator = new EarClippingTriangulator();
            Color color = new Color();
            Iterator<JsonValue> iterator28 = parse.get("polygons").iterator2();
            while (iterator28.hasNext()) {
                JsonValue next7 = iterator28.next();
                try {
                    String string2 = next7.getString("color");
                    String string3 = next7.getString("visibleWith", "");
                    JsonValue jsonValue2 = next7.get("points");
                    FloatArray floatArray = new FloatArray();
                    int i7 = Integer.MAX_VALUE;
                    int i8 = Integer.MAX_VALUE;
                    int i9 = Integer.MIN_VALUE;
                    int i10 = Integer.MIN_VALUE;
                    int regionHeight = blankWhiteTextureRegion.getRegionHeight();
                    Iterator<JsonValue> iterator29 = jsonValue2.iterator2();
                    while (iterator29.hasNext()) {
                        JsonValue next8 = iterator29.next();
                        int i11 = next8.getInt(0);
                        int i12 = next8.getInt(1);
                        if (i11 < i7) {
                            i7 = i11;
                        }
                        if (i11 > i9) {
                            i9 = i11;
                        }
                        if (i12 < i8) {
                            i8 = i12;
                        }
                        if (i12 > i10) {
                            i10 = i12;
                        }
                        floatArray.add(i11, i12);
                    }
                    float f = i9 - i7;
                    float f2 = i10 - i8;
                    for (int i13 = 0; i13 < floatArray.size; i13 += 2) {
                        float[] fArr = floatArray.items;
                        fArr[i13] = ((fArr[i13] - i7) / f) * regionHeight;
                        floatArray.items[i13 + 1] = ((floatArray.items[i13 + 1] - i8) / f2) * regionHeight;
                    }
                    PolygonSprite polygonSprite = new PolygonSprite(new PolygonRegion(blankWhiteTextureRegion, floatArray.toArray(), earClippingTriangulator.computeTriangles(floatArray).toArray()));
                    polygonSprite.setBounds(i7 - this.f, i8 - this.h, i9 - i7, i10 - i8);
                    Color.rgba8888ToColor(color, PMath.parseUnsignedInt(string2.substring(1), 16));
                    polygonSprite.setColor(color);
                    PolygonConfig polygonConfig = new PolygonConfig();
                    polygonConfig.sprite = polygonSprite;
                    if (string3.length() > 0) {
                        polygonConfig.visibleWith = this.f2439b.items[ResearchType.valueOf(string3).ordinal()];
                    }
                    this.e.add(polygonConfig);
                } catch (Exception e3) {
                    f2438a.e("failed to load polygon for researches", e3);
                }
            }
        }
        this.j = this.g - this.f;
        this.k = this.i - this.h;
        if (this.l && !Config.isHeadless()) {
            g();
            c();
            updateAndValidateStarBranch();
        }
    }

    public Model create3dGraphModel() {
        b();
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        new Material();
        return modelBuilder.end();
    }

    public boolean hasInstalledParents(Research research) {
        b();
        if (research.linksToParents.size == 0) {
            return true;
        }
        Array.ArrayIterator<Research.ResearchLink> it = research.linksToParents.iterator();
        while (it.hasNext()) {
            if (it.next().parent.getInstalledLevel() != 0) {
                return true;
            }
        }
        if (research.priceInStars > 0) {
            Array.ArrayIterator<Research.ResearchLink> it2 = research.linksToChildren.iterator();
            while (it2.hasNext()) {
                if (it2.next().child.getInstalledLevel() != 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void checkResearchesStatus(boolean z) {
        int i;
        b();
        for (int i2 = 0; i2 < this.f2439b.size; i2++) {
            Research research = this.f2439b.items[i2];
            if (research.priceInStars <= 0) {
                for (int installedLevel = research.getInstalledLevel(); installedLevel < research.levels.length; installedLevel++) {
                    Research.ResearchLevel researchLevel = research.levels[installedLevel];
                    if (researchLevel.price.size == 0) {
                        Requirement[] requirementArr = researchLevel.requirements;
                        int length = requirementArr.length;
                        while (true) {
                            if (i < length) {
                                i = requirementArr[i].isSatisfied() ? i + 1 : 0;
                            } else if (hasInstalledParents(research)) {
                                setInstalledLevel(research.type, installedLevel + 1, true);
                                if (z) {
                                    a(research);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(Research research) {
        if (Game.i.uiManager == null || !this.l) {
            return;
        }
        Notifications.i().add(Game.i.localeManager.i18n.get("research_completed") + ": " + ((Object) research.getTitle()), Game.i.assetManager.getDrawable("icon-research"), null, StaticSoundType.RESEARCH);
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        if (this.l) {
            this.m += f;
            if (this.m > 1.0f) {
                f();
                this.m = 0.0f;
            }
        }
    }

    private void f() {
        b();
        PP_Research pP_Research = ProgressPrefs.i().research;
        if (pP_Research.getCurrentlyResearching() != null) {
            if (getInstance(pP_Research.getCurrentlyResearching()).isMaxEndlessLevel()) {
                f2438a.e("current research can't have higher level, aborting", new Object[0]);
                pP_Research.setCurrentlyResearching(null);
                ProgressPrefs.i().requireSave();
            } else if (pP_Research.getCurrentResearchEndTime() <= TimeUtils.millis()) {
                setInstalledLevel(pP_Research.getCurrentlyResearching(), getInstance(pP_Research.getCurrentlyResearching()).getInstalledLevel() + 1, true);
                a(getInstance(pP_Research.getCurrentlyResearching()));
                pP_Research.setCurrentlyResearching(null);
                ProgressPrefs.i().requireSave();
                checkResearchesStatus(true);
            }
        }
    }

    public Research getCurrentResearching() {
        b();
        PP_Research pP_Research = ProgressPrefs.i().research;
        if (pP_Research.getCurrentlyResearching() == null || pP_Research.getCurrentResearchEndTime() <= TimeUtils.millis()) {
            return null;
        }
        return getInstance(pP_Research.getCurrentlyResearching());
    }

    public void finishCurrentResearch() {
        b();
        if (getCurrentResearching() != null) {
            ProgressPrefs.i().research.setCurrentResearchEndTime(TimeUtils.millis() - 1);
            ProgressPrefs.i().requireSave();
            f();
        }
    }

    public long getMillisToResearchingEnd() {
        b();
        if (ProgressPrefs.i().research.getCurrentlyResearching() == null) {
            return 0L;
        }
        long currentResearchEndTime = ProgressPrefs.i().research.getCurrentResearchEndTime() - TimeUtils.millis();
        if (currentResearchEndTime < 0) {
            return 0L;
        }
        return currentResearchEndTime;
    }

    public boolean canResearchForToken(Research research, boolean z) {
        if (research.priceInStars > 0) {
            return false;
        }
        if (!z && !canStartResearching(research, true)) {
            return false;
        }
        for (int i = 0; i < research.levels.length; i++) {
            Research.ResearchLevel researchLevel = research.levels[i];
            for (int i2 = 0; i2 < researchLevel.price.size; i2++) {
                ItemType type = researchLevel.price.items[i2].getItem().getType();
                if (type == ItemType.PRESTIGE_TOKEN || type == ItemType.ACCELERATOR) {
                    return false;
                }
            }
        }
        if (research.type == ResearchType.DECRYPTING_QUEUE_SIZE || research.type == ResearchType.DEVELOPER_MODE) {
            return false;
        }
        return true;
    }

    public boolean researchForToken(Research research) {
        if (canResearchForToken(research, false) && Game.i.progressManager.removeItems(Item.D.RESEARCH_TOKEN, 1)) {
            Game.i.analyticsManager.logCurrencySpent("research_" + research.type, "research_token", 1);
            this.q.begin();
            for (int i = 0; i < this.q.size; i++) {
                this.q.get(i).researchStarted(research, 0L);
            }
            this.q.end();
            setInstalledLevel(research.type, research.getInstalledLevel() + 1, true);
            ProgressPrefs.i().research.addLevelInstalledForTokens(research.type, research.getInstalledLevel());
            a(research);
            ProgressPrefs.i().research.setCurrentlyResearching(null);
            ProgressPrefs.i().requireSave();
            checkResearchesStatus(true);
            return true;
        }
        return false;
    }

    public boolean startResearching(Research research, boolean z) {
        Array<ItemStack> price;
        b();
        try {
            tryStartResearching(research, false, null);
            if (z) {
                if (research.getInstalledLevel() < research.levels.length) {
                    price = research.levels[research.getInstalledLevel()].price;
                } else {
                    if (research.endlessLevel == null) {
                        throw new IllegalArgumentException("research.endlessLevel is null");
                    }
                    price = research.endlessLevel.getPrice(research.getInstalledLevel() + 1);
                }
                for (int i = 0; i < price.size; i++) {
                    ItemStack itemStack = price.items[i];
                    Game.i.progressManager.removeItems(itemStack.getItem(), itemStack.getCount());
                }
            }
            ProgressPrefs.i().research.setCurrentlyResearching(research.type);
            long researchingDuration = getResearchingDuration(research);
            ProgressPrefs.i().research.setCurrentResearchEndTime(TimeUtils.millis() + researchingDuration);
            ProgressPrefs.i().requireSave();
            this.q.begin();
            for (int i2 = 0; i2 < this.q.size; i2++) {
                this.q.get(i2).researchStarted(research, researchingDuration);
            }
            this.q.end();
            f();
            return true;
        } catch (StartResearchingException e) {
            f2438a.e("unable to start researching " + research.type.name(), e);
            for (int i3 = 0; i3 < e.reasons.size; i3++) {
                f2438a.e("reason: " + e.reasons.get(i3), new Object[0]);
            }
            throw e;
        }
    }

    public void tryStartResearching(Research research, boolean z, StartResearchingException startResearchingException) {
        boolean isMaxNormalLevel;
        b();
        if (startResearchingException == null) {
            startResearchingException = new StartResearchingException();
        }
        startResearchingException.reasons.clear();
        if (getCurrentResearching() != null) {
            startResearchingException.reasons.add(StartResearchFailReason.OTHER_RESEARCH_IN_PROGRESS);
        }
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.EASY || Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL) {
            isMaxNormalLevel = research.isMaxNormalLevel();
        } else {
            isMaxNormalLevel = research.isMaxEndlessLevel();
        }
        if (research.getInstalledLevel() >= research.levels.length && research.endlessLevel == null) {
            isMaxNormalLevel = true;
        }
        if (isMaxNormalLevel) {
            startResearchingException.reasons.add(StartResearchFailReason.MAX_LEVEL);
        }
        if (research.priceInStars > 0) {
            boolean z2 = false;
            int i = 0;
            while (true) {
                if (i >= research.linksToParents.size) {
                    break;
                }
                if (research.linksToParents.get(i).parent.getInstalledLevel() <= 0) {
                    i++;
                } else {
                    z2 = true;
                    break;
                }
            }
            int i2 = 0;
            while (true) {
                if (i2 >= research.linksToChildren.size) {
                    break;
                }
                if (research.linksToChildren.get(i2).child.getInstalledLevel() <= 0) {
                    i2++;
                } else {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                startResearchingException.reasons.add(StartResearchFailReason.REQUIRES_PREVIOUS_RESEARCHES);
            }
            if (Game.i.researchManager.getUnusedStarsCount() < research.priceInStars) {
                startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_STARS);
            }
        } else {
            int i3 = 0;
            while (true) {
                if (i3 >= research.linksToParents.size) {
                    break;
                }
                Research.ResearchLink researchLink = research.linksToParents.get(i3);
                if (researchLink.requiredLevels <= researchLink.parent.getInstalledLevel()) {
                    i3++;
                } else {
                    startResearchingException.reasons.add(StartResearchFailReason.REQUIRES_PREVIOUS_RESEARCHES);
                    break;
                }
            }
        }
        if (!isMaxNormalLevel) {
            if (research.levels.length > research.getInstalledLevel()) {
                Research.ResearchLevel researchLevel = research.levels[research.getInstalledLevel()];
                if (!z) {
                    for (int i4 = 0; i4 < researchLevel.price.size; i4++) {
                        ItemStack itemStack = researchLevel.price.items[i4];
                        if (itemStack.getItem().getType() == ItemType.GREEN_PAPER) {
                            if (itemStack.getCount() > Game.i.progressManager.getGreenPapers()) {
                                startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_MONEY);
                            }
                        } else if (itemStack.getItem().getType() != ItemType.RESOURCE) {
                            if (itemStack.getCount() > Game.i.progressManager.getItemsCount(itemStack.getItem()) && !startResearchingException.reasons.contains(StartResearchFailReason.NOT_ENOUGH_ITEMS, true)) {
                                startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_ITEMS);
                            }
                        } else {
                            if (itemStack.getCount() > Game.i.progressManager.getResources(((ResourceItem) itemStack.getItem()).resourceType) && !startResearchingException.reasons.contains(StartResearchFailReason.NOT_ENOUGH_RESOURCES, true)) {
                                startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_RESOURCES);
                            }
                        }
                    }
                }
                Requirement[] requirementArr = researchLevel.requirements;
                int length = requirementArr.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        break;
                    }
                    if (requirementArr[i5].isSatisfied()) {
                        i5++;
                    } else {
                        startResearchingException.reasons.add(StartResearchFailReason.REQUIREMENT_NOT_SATISFIED);
                        break;
                    }
                }
            } else if (!z) {
                Array<ItemStack> price = research.endlessLevel.getPrice(research.getInstalledLevel() + 1);
                for (int i6 = 0; i6 < price.size; i6++) {
                    ItemStack itemStack2 = price.items[i6];
                    if (itemStack2.getItem().getType() == ItemType.GREEN_PAPER) {
                        if (itemStack2.getCount() > Game.i.progressManager.getGreenPapers()) {
                            startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_MONEY);
                        }
                    } else if (itemStack2.getItem().getType() != ItemType.RESOURCE) {
                        if (itemStack2.getCount() > Game.i.progressManager.getItemsCount(itemStack2.getItem()) && !startResearchingException.reasons.contains(StartResearchFailReason.NOT_ENOUGH_ITEMS, true)) {
                            startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_ITEMS);
                        }
                    } else {
                        if (itemStack2.getCount() > Game.i.progressManager.getResources(((ResourceItem) itemStack2.getItem()).resourceType) && !startResearchingException.reasons.contains(StartResearchFailReason.NOT_ENOUGH_RESOURCES, true)) {
                            startResearchingException.reasons.add(StartResearchFailReason.NOT_ENOUGH_RESOURCES);
                        }
                    }
                }
            }
        }
        if (startResearchingException.reasons.size != 0) {
            throw startResearchingException;
        }
    }

    public void resetStarResearches() {
        b();
        if (Game.i.researchManager.getResetStarResearchesAcceleratorPrice() > 0) {
            int resetStarResearchesAcceleratorPrice = Game.i.researchManager.getResetStarResearchesAcceleratorPrice();
            if (Game.i.progressManager.removeAccelerators(resetStarResearchesAcceleratorPrice)) {
                Game.i.analyticsManager.logCurrencySpent("reset_star_research", "accelerator", resetStarResearchesAcceleratorPrice);
                for (int i = 0; i < this.f2439b.size; i++) {
                    if (this.f2439b.items[i].priceInStars > 0 && this.f2439b.items[i].getInstalledLevel() > 0) {
                        this.f2439b.items[i].setInstalledLevel(0);
                    }
                }
                g();
                return;
            }
            Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_accelerators"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
        }
    }

    public boolean canStartResearching(Research research, boolean z) {
        b();
        try {
            tryStartResearching(research, z, this.p);
            return true;
        } catch (StartResearchingException unused) {
            return false;
        }
    }

    public long getResearchingDuration(Research research) {
        b();
        if (research.isMaxNormalLevel()) {
            return 0L;
        }
        return research.levels[research.getInstalledLevel()].researchDuration * 1000;
    }

    private void g() {
        updateAndValidateStarBranch();
        Game.i.authManager.notifyNeedCloudSave(true);
        this.q.begin();
        int i = this.q.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.q.get(i2).researchesUpdated();
        }
        this.q.end();
    }

    public void addListener(ResearchManagerListener researchManagerListener) {
        if (researchManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.q.contains(researchManagerListener, true)) {
            this.q.add(researchManagerListener);
        }
    }

    public void removeListener(ResearchManagerListener researchManagerListener) {
        if (researchManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.q.removeValue(researchManagerListener, true);
    }

    public int getMapMinX() {
        return this.f;
    }

    public int getMapMaxX() {
        return this.g;
    }

    public int getMapMinY() {
        return this.h;
    }

    public int getMapMaxY() {
        return this.i;
    }

    public int getMapWidth() {
        return this.j;
    }

    public int getMapHeight() {
        return this.k;
    }

    public void setInstalledLevel(ResearchType researchType, int i, boolean z) {
        b();
        Research researchInstance = getResearchInstance(researchType);
        if (researchInstance.maxEndlessLevel < i) {
            throw new IllegalArgumentException("Level for " + researchType.name() + " is too high (" + i + "), max research level is " + researchInstance.maxEndlessLevel);
        }
        researchInstance.setInstalledLevel(i);
        if (z) {
            this.q.begin();
            int i2 = this.q.size;
            for (int i3 = 0; i3 < i2; i3++) {
                this.q.get(i3).researchCompleted(researchInstance);
            }
            this.q.end();
        }
        g();
        c();
    }

    public Research getResearchInstance(ResearchType researchType) {
        b();
        return this.f2439b.items[researchType.ordinal()];
    }

    public int getUnusedStarsCount() {
        return this.o;
    }

    public int getResetStarResearchesAcceleratorPrice() {
        b();
        int i = 0;
        for (int i2 = 0; i2 < this.f2439b.size; i2++) {
            if (this.f2439b.items[i2].priceInStars > 0 && this.f2439b.items[i2].getInstalledLevel() > 0) {
                i++;
            }
        }
        return i;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (int i = 0; i < this.f2439b.size; i++) {
            Research research = this.f2439b.get(i);
            try {
                research.getDescription();
                research.getTitle();
                research.category.getDescription();
                research.category.getTitle();
            } catch (Exception e) {
                f2438a.e("Test: failed for research type " + research.type.name(), new Object[0]);
                throw e;
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$ResearchManagerListener.class */
    public interface ResearchManagerListener {
        void researchesUpdated();

        void researchStarted(Research research, long j);

        void researchCompleted(Research research);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$ResearchManagerListener$ResearchManagerListenerAdapter.class */
        public static abstract class ResearchManagerListenerAdapter implements ResearchManagerListener {
            @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
            public void researchesUpdated() {
            }

            @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
            public void researchStarted(Research research, long j) {
            }

            @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
            public void researchCompleted(Research research) {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ResearchManager$PolygonConfig.class */
    public static class PolygonConfig {

        /* renamed from: a, reason: collision with root package name */
        private static final EarClippingTriangulator f2441a = new EarClippingTriangulator();

        /* renamed from: b, reason: collision with root package name */
        private static final Color f2442b = new Color();
        public PolygonSprite sprite;
        public Research visibleWith;

        /* JADX WARN: Removed duplicated region for block: B:17:0x00bc A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00c4 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0120 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00b4 A[SYNTHETIC] */
        @com.badlogic.gdx.utils.Null
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.prineside.tdi2.managers.ResearchManager.PolygonConfig fromJson(com.a.a.b.l r7) {
            /*
                Method dump skipped, instructions count: 589
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ResearchManager.PolygonConfig.fromJson(com.a.a.b.l):com.prineside.tdi2.managers.ResearchManager$PolygonConfig");
        }
    }
}

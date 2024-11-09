package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.items.BlueprintItem;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Research;
import com.prineside.tdi2.utils.CRC;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;
import java.nio.charset.StandardCharsets;
import net.bytebuddy.utility.JavaConstant;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Research.class */
public final class Research {
    public final ResearchType type;
    public static final int RESET_RESEARCH_STATE_AVAILABLE = 0;
    public static final int RESET_RESEARCH_STATE_HAS_CHILD = 1;
    public static final int RESET_RESEARCH_STATE_NOT_INSTALLED = 2;
    public static final int RESET_RESEARCH_STATE_NOT_SUITABLE = 3;
    public static final int RESET_RESEARCH_STATE_STAR_BRANCH = 4;
    public static final int RESET_RESEARCH_STATE_NOT_ENOUGH_ACCELERATORS = 5;
    public final ResearchCategory category;
    public final ResearchLevel[] levels;

    @Null
    public EndlessResearchLevel endlessLevel;
    public int maxEndlessLevel;
    public int endlessPriceLevel;
    public boolean endlessOnly;
    public float endlessPriceExp;
    public int x;
    public int y;
    public float distanceToCenter;
    public TowerType relatedToTowerType;
    public boolean unlocksTower;

    /* renamed from: b, reason: collision with root package name */
    private final String f1751b;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1750a = TLog.forClass(Research.class);
    private static final StringBuilder c = new StringBuilder();
    private static final Array<ItemStack> d = new Array<>();
    private static final Array<ItemStack> e = new Array<>(ItemStack.class);
    public final Array<ResearchLink> linksToParents = new Array<>(ResearchLink.class);
    public final Array<ResearchLink> linksToChildren = new Array<>(ResearchLink.class);
    public boolean cantBeIgnoredOnUserMaps = false;
    public boolean small = false;
    public int priceInStars = 0;

    public Research(ResearchType researchType, ResearchCategory researchCategory, ResearchLevel[] researchLevelArr, int i) {
        if (researchLevelArr.length == 0) {
            throw new IllegalArgumentException("upgrade " + researchType.name() + " must have at least one level");
        }
        if (i < researchLevelArr.length) {
            throw new IllegalArgumentException("upgrade " + researchType.name() + " must have maxEndlessLevel >= " + researchLevelArr.length);
        }
        this.type = researchType;
        this.maxEndlessLevel = i;
        this.endlessPriceLevel = i;
        this.category = researchCategory;
        this.levels = researchLevelArr;
        if (i != researchLevelArr.length) {
            int i2 = 0;
            int i3 = 1;
            int i4 = 0;
            BlueprintType blueprintType = null;
            for (ResearchLevel researchLevel : researchLevelArr) {
                int i5 = 0;
                for (int i6 = 0; i6 < researchLevel.price.size; i6++) {
                    ItemStack itemStack = researchLevel.price.items[i6];
                    if (itemStack.getItem().getType() == ItemType.GREEN_PAPER) {
                        i5 += (int) (itemStack.getCount() * 0.65f);
                    } else if (itemStack.getItem().getType() != ItemType.RESOURCE) {
                        if (blueprintType != null || itemStack.getItem().getType() != ItemType.BLUEPRINT) {
                            if (itemStack.getItem().getType() == ItemType.PRESTIGE_TOKEN) {
                                i4 = itemStack.getCount();
                            }
                        } else {
                            BlueprintItem blueprintItem = (BlueprintItem) itemStack.getItem();
                            if (blueprintItem.getRarity() == RarityType.COMMON) {
                                blueprintType = blueprintItem.getBlueprintType();
                            }
                        }
                    } else {
                        i5 += (3 + ((ResourceItem) itemStack.getItem()).resourceType.ordinal()) * 3 * itemStack.getCount();
                    }
                }
                i3 = (i3 * 31) + i5;
                i2 += i5;
            }
            int length = i2 / researchLevelArr.length;
            GameValueManager.GameValueEffect[] gameValueEffectArr = new GameValueManager.GameValueEffect[researchLevelArr[researchLevelArr.length - 1].effects.length];
            for (int i7 = 0; i7 < researchLevelArr[researchLevelArr.length - 1].effects.length; i7++) {
                gameValueEffectArr[i7] = new GameValueManager.GameValueEffect(researchLevelArr[researchLevelArr.length - 1].effects[i7].type, researchLevelArr[researchLevelArr.length - 1].effects[i7].delta);
            }
            this.endlessLevel = new EndlessResearchLevel(length, i3, blueprintType, i4, gameValueEffectArr);
        }
        this.f1751b = Long.toHexString(CRC.calculateCRC(CRC.Parameters.CRC32, researchType.name().getBytes(StandardCharsets.UTF_8)));
        if (researchType.name().startsWith("TOWER_")) {
            String[] split = researchType.name().split(JavaConstant.Dynamic.DEFAULT_NAME);
            try {
                if (split[1].equals("TYPE")) {
                    this.relatedToTowerType = TowerType.valueOf(split[2]);
                    this.unlocksTower = true;
                } else {
                    this.relatedToTowerType = TowerType.valueOf(split[1]);
                }
            } catch (Exception unused) {
                f1750a.e("Unknown tower type for " + researchType.name(), new Object[0]);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x01bc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01d7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01f9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0207 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0215 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0223 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x022d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0258 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01b4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.Research fromJson(com.a.a.b.l r7) {
        /*
            Method dump skipped, instructions count: 793
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.Research.fromJson(com.a.a.b.l):com.prineside.tdi2.Research");
    }

    public final int getMaxLevel() {
        return Math.max(this.levels.length, this.maxEndlessLevel);
    }

    public final int getMaxRegularLevel() {
        return this.levels.length;
    }

    public final int getInstalledLevel() {
        return ProgressPrefs.i().research.getInstalledLevel(this.type);
    }

    public final void setInstalledLevel(int i) {
        PP_Research pP_Research = ProgressPrefs.i().research;
        short s = (short) i;
        if (i > 32767) {
            s = Short.MAX_VALUE;
        } else if (i < 0) {
            s = 0;
        }
        if (pP_Research.getInstalledLevel(this.type) != s) {
            pP_Research.setInstalledLevel(this.type, s);
            ProgressPrefs.i().requireSave();
        }
    }

    public final String getShortName() {
        return this.f1751b;
    }

    public final StringBuilder getTitle() {
        if (!isUnlocksTower() && getRelatedToTowerType() != null) {
            c.setLength(0);
            c.append(this.category.getTitle()).append(" (").append(Game.i.towerManager.getTitle(getRelatedToTowerType())).append(")");
            return c;
        }
        c.setLength(0);
        c.append(this.category.getTitle());
        return c;
    }

    public final CharSequence getDescription() {
        return this.category.getDescription();
    }

    public final int resetForAcceleratorsState() {
        int resetPrice = getResetPrice();
        if (resetPrice == 0) {
            return 3;
        }
        if (this.priceInStars > 0) {
            return 4;
        }
        Array.ArrayIterator<ResearchLink> it = this.linksToChildren.iterator();
        while (it.hasNext()) {
            if (it.next().child.getInstalledLevel() > 0) {
                return 1;
            }
        }
        if (getInstalledLevel() == 0) {
            return 2;
        }
        return Game.i.progressManager.getAccelerators() < resetPrice ? 5 : 0;
    }

    public final int getResetPrice() {
        double d2 = 0.0d;
        Array.ArrayIterator<ItemStack> it = getCumulativePrice(0, getInstalledLevel(), false).iterator();
        while (it.hasNext()) {
            ItemStack next = it.next();
            d2 += next.getItem().getPriceInAcceleratorsForResearchReset(next.getCount());
        }
        return MathUtils.clamp(MathUtils.round((float) Math.pow(d2, 0.9d)), 0, 400);
    }

    public final Array<ItemStack> getCumulativePrice(int i, int i2, boolean z) {
        Array<ItemStack> price;
        int max = Math.max(i, 0);
        int min = Math.min(i2, this.maxEndlessLevel);
        d.clear();
        PP_Research pP_Research = ProgressPrefs.i().research;
        for (int i3 = max + 1; i3 <= min; i3++) {
            if (z && pP_Research.isLevelInstalledForToken(this.type, i3)) {
                ProgressManager.addItemToStacksArray(d, Item.D.RESEARCH_TOKEN_USED, 1);
            } else {
                if (i3 - 1 < this.levels.length) {
                    price = this.levels[i3 - 1].price;
                } else {
                    price = this.endlessLevel.getPrice(i3);
                }
                for (int i4 = 0; i4 < price.size; i4++) {
                    ItemStack itemStack = price.items[i4];
                    ProgressManager.addItemToStacksArray(d, itemStack.getItem(), itemStack.getCount());
                }
            }
        }
        return d;
    }

    public final Array<GameValueManager.GameValueEffect> getEffects(int i) {
        if (i <= 0 || i > this.maxEndlessLevel) {
            throw new IllegalArgumentException("Invalid research level '" + i + "' for " + this.type.name() + ", max: " + this.maxEndlessLevel);
        }
        Array<GameValueManager.GameValueEffect> array = new Array<>(GameValueManager.GameValueEffect.class);
        int i2 = i;
        if (i >= this.levels.length) {
            i2 = this.levels.length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < this.levels[i3].effects.length; i4++) {
                GameValueManager.GameValueEffect gameValueEffect = this.levels[i3].effects[i4];
                boolean z = false;
                Array.ArrayIterator<GameValueManager.GameValueEffect> it = array.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    GameValueManager.GameValueEffect next = it.next();
                    if (next.type == gameValueEffect.type) {
                        next.delta += gameValueEffect.delta;
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    array.add(new GameValueManager.GameValueEffect(gameValueEffect.type, gameValueEffect.delta));
                }
            }
        }
        if (i2 != i) {
            int length = i - this.levels.length;
            for (int i5 = 0; i5 < this.endlessLevel.effects.length; i5++) {
                GameValueManager.GameValueEffect gameValueEffect2 = this.endlessLevel.effects[i5];
                boolean z2 = false;
                Array.ArrayIterator<GameValueManager.GameValueEffect> it2 = array.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    GameValueManager.GameValueEffect next2 = it2.next();
                    if (next2.type == gameValueEffect2.type) {
                        next2.delta += gameValueEffect2.delta * length;
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    array.add(new GameValueManager.GameValueEffect(gameValueEffect2.type, gameValueEffect2.delta * length));
                }
            }
        }
        return array;
    }

    public final boolean isMaxNormalLevel() {
        return this.endlessOnly || getInstalledLevel() >= this.levels.length;
    }

    public final boolean isMaxEndlessLevel() {
        return getInstalledLevel() >= this.maxEndlessLevel;
    }

    @Null
    public final TowerType getRelatedToTowerType() {
        return this.relatedToTowerType;
    }

    public final boolean isUnlocksTower() {
        return this.unlocksTower;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Research$ResearchLink.class */
    public static final class ResearchLink {
        public final Research parent;
        public final Research child;
        public final int requiredLevels;
        public int pivotX;
        public int pivotY;
        public final float requiredLevelsLabelPos;
        public final int requiredLevelsLabelX;
        public final int requiredLevelsLabelY;

        public ResearchLink(Research research, Research research2, int i, int i2, int i3, float f) {
            this.parent = research;
            this.child = research2;
            this.requiredLevels = i;
            this.pivotX = i2;
            this.pivotY = i3;
            this.requiredLevelsLabelPos = f;
            float distanceBetweenPoints = PMath.getDistanceBetweenPoints(research2.x, research2.y, i2, i3);
            float distanceBetweenPoints2 = PMath.getDistanceBetweenPoints(research.x, research.y, i2, i3);
            float f2 = f * (distanceBetweenPoints + distanceBetweenPoints2);
            if (f2 < distanceBetweenPoints) {
                float f3 = f2 / distanceBetweenPoints;
                this.requiredLevelsLabelX = (int) (research2.x + ((i2 - research2.x) * f3));
                this.requiredLevelsLabelY = (int) (research2.y + ((i3 - research2.y) * f3));
            } else {
                float f4 = (f2 - distanceBetweenPoints) / distanceBetweenPoints2;
                this.requiredLevelsLabelX = (int) (i2 + ((research.x - i2) * f4));
                this.requiredLevelsLabelY = (int) (i3 + ((research.y - i3) * f4));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x00fc A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0110 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0119 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0127 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0132 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x013d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00e8 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.prineside.tdi2.Research.ResearchLink fromJson(com.a.a.b.l r9) {
            /*
                Method dump skipped, instructions count: 402
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.Research.ResearchLink.fromJson(com.a.a.b.l):com.prineside.tdi2.Research$ResearchLink");
        }

        public final boolean isVisible() {
            return this.requiredLevels <= this.parent.getInstalledLevel();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Research$ResearchLevel.class */
    public static class ResearchLevel {
        public int number;
        public int researchDuration;
        public GameValueManager.GameValueEffect[] effects;
        public Array<ItemStack> price = new Array<>(ItemStack.class);
        public Requirement[] requirements;

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x01ab, code lost:            switch(r12) {            case 0: goto L83;            case 1: goto L79;            case 2: goto L80;            case 3: goto L81;            default: goto L82;        };     */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x01de, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.PRESTIGE_TOKEN, r0));     */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x01f4, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.ACCELERATOR, r0));     */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x020a, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.BIT_DUST, r0));     */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0227, code lost:            if (r0.startsWith("bp_") == false) goto L84;     */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x0250, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.F_RESOURCE.create(com.prineside.tdi2.enums.ResourceType.valueOf(r0)), r0));     */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x022a, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.F_BLUEPRINT.create(com.prineside.tdi2.enums.BlueprintType.valueOf(r0.substring(3))), r0));     */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x01c8, code lost:            r0.price.add(new com.prineside.tdi2.ItemStack(com.prineside.tdi2.Item.D.GREEN_PAPER, r0));     */
        /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0037. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0129  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.prineside.tdi2.Research.ResearchLevel fromJson(com.a.a.b.l r7) {
            /*
                Method dump skipped, instructions count: 684
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.Research.ResearchLevel.fromJson(com.a.a.b.l):com.prineside.tdi2.Research$ResearchLevel");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(int i) {
        if (i < 10) {
            return i;
        }
        if (i < 100) {
            return (i / 5) * 5;
        }
        if (i < 1000) {
            return (i / 10) * 10;
        }
        return (i / 50) * 50;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Research$EndlessResearchLevel.class */
    public class EndlessResearchLevel {
        public final GameValueManager.GameValueEffect[] effects;
        public final int priceBase;
        public final int randomSeed;
        public final int prestigeTokens;
        public BlueprintType blueprintType;

        public EndlessResearchLevel(int i, int i2, BlueprintType blueprintType, int i3, GameValueManager.GameValueEffect[] gameValueEffectArr) {
            this.prestigeTokens = i3;
            this.priceBase = i;
            this.randomSeed = i2;
            this.effects = gameValueEffectArr;
            this.blueprintType = blueprintType;
        }

        public Array<ItemStack> getPrice(int i) {
            BlueprintType blueprintType;
            int length = Research.this.levels.length;
            if (i > length) {
                Research.e.clear();
                int min = Math.min(Research.this.endlessPriceLevel, i);
                FastRandom.random.setSeed((this.randomSeed * 31) + i);
                if (this.prestigeTokens > 0) {
                    int ceil = this.prestigeTokens + MathUtils.ceil((float) (this.prestigeTokens * 0.1d * (min - 1)));
                    if (i > Research.this.endlessPriceLevel) {
                        ceil += (int) (ceil * 0.025f * (i - Research.this.endlessPriceLevel));
                    }
                    Research.e.add(new ItemStack(Item.D.PRESTIGE_TOKEN, ceil));
                } else {
                    int nextInt = FastRandom.random.nextInt(2);
                    int i2 = min - length;
                    int pow = (int) StrictMath.pow(StrictMath.pow(this.priceBase * (1.0f + ((i2 / length) * 0.2f)), 1.0f + (i2 * (0.0085f + (FastRandom.random.nextFloat() * 0.0015f)))) * 0.6d, Research.this.endlessPriceExp);
                    if (i > Research.this.endlessPriceLevel) {
                        pow += (int) (pow * 0.075f * (i - Research.this.endlessPriceLevel));
                    }
                    Research.e.add(new ItemStack(Item.D.GREEN_PAPER, Research.b((int) ((nextInt == 0 ? pow * 1.3f : pow) * 1.4f))));
                    ResourceType resourceType = ResourceType.values[FastRandom.random.nextInt(ResourceType.values.length)];
                    if (FastRandom.random.nextFloat() < 0.25f) {
                        resourceType = ResourceType.INFIAR;
                    }
                    double pow2 = StrictMath.pow((nextInt == 1 ? pow * 1.3f : pow) * 0.5f, 0.99f + (FastRandom.random.nextFloat() * 0.02f)) * 0.25d;
                    switch (resourceType) {
                        case SCALAR:
                            Research.e.add(new ItemStack(Item.D.RESOURCE_SCALAR, Research.b((int) (pow2 * 1.5d))));
                            break;
                        case VECTOR:
                            Research.e.add(new ItemStack(Item.D.RESOURCE_VECTOR, Research.b((int) (pow2 * 1.25d))));
                            break;
                        case MATRIX:
                            Research.e.add(new ItemStack(Item.D.RESOURCE_MATRIX, Research.b((int) pow2)));
                            break;
                        case TENSOR:
                            Research.e.add(new ItemStack(Item.D.RESOURCE_TENSOR, Research.b((int) (pow2 * 0.9d))));
                            break;
                        case INFIAR:
                            Research.e.add(new ItemStack(Item.D.RESOURCE_INFIAR, Research.b((int) (pow2 * 0.8d))));
                            break;
                    }
                    int nextInt2 = 6500 + FastRandom.random.nextInt(1200);
                    if (pow > nextInt2) {
                        int nextInt3 = (pow - nextInt2) / (User32.WM_MDITILE + FastRandom.random.nextInt(150));
                        int i3 = nextInt3;
                        if (nextInt3 <= 0) {
                            i3 = 1;
                        }
                        Research.e.add(new ItemStack(Item.D.BIT_DUST, Research.b(i3)));
                    }
                    if (this.blueprintType != null) {
                        Research.e.add(new ItemStack(Item.D.F_BLUEPRINT.create(this.blueprintType), i + ((int) (pow * 5.0E-4d))));
                    }
                    int i4 = 0;
                    for (int i5 = 0; i5 < 54 && pow > 7000 + (i5 * (2000 + (i5 * 500))); i5++) {
                        i4++;
                    }
                    if (i4 >= 27) {
                        blueprintType = BlueprintType.SPECIAL_IV;
                        i4 /= 27;
                    } else if (i4 >= 9) {
                        blueprintType = BlueprintType.SPECIAL_III;
                        i4 /= 9;
                    } else if (i4 >= 3) {
                        blueprintType = BlueprintType.SPECIAL_II;
                        i4 /= 3;
                    } else {
                        blueprintType = BlueprintType.SPECIAL_I;
                    }
                    if (i4 != 0) {
                        Research.e.add(new ItemStack(Item.D.F_BLUEPRINT.create(blueprintType), i4));
                    }
                }
                FastRandom.random.setSeed(FastRandom.getInt(CGL.kCGLBadAttribute));
                return Research.e;
            }
            throw new IllegalArgumentException("level must be > " + length);
        }
    }
}

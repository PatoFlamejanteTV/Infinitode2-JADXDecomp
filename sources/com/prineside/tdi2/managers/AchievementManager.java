package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AchievementManager.class */
public class AchievementManager extends Manager.ManagerAdapter {
    public AchievementConfig[] configs = new AchievementConfig[AchievementType.values.length];

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2272a = TLog.forClass(AchievementManager.class);

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f2273b = {"0.1", "0.2", "0.3", "0.4"};
    private static final String[] c = {"1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8"};
    private static final String[] d = {"2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8"};
    private static final String[] e = {"3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8"};
    private static final String[] f = {"4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8"};
    private static final String[] g = {"5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8"};

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AchievementManager$AchievementConfig.class */
    public static class AchievementConfig {
        public Array<ItemStack> rewards = new Array<>(ItemStack.class);
        public int required;
        public boolean hidden;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AchievementManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<AchievementManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AchievementManager read() {
            return Game.i.achievementManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        Iterator<JsonValue> iterator2 = new JsonReader().parse(Gdx.files.internal("res/achievements.json")).iterator2();
        while (iterator2.hasNext()) {
            JsonValue next = iterator2.next();
            AchievementType valueOf = AchievementType.valueOf(next.name);
            AchievementConfig achievementConfig = new AchievementConfig();
            achievementConfig.required = next.getInt("required", 1);
            achievementConfig.hidden = next.getBoolean("hidden", false);
            Iterator<JsonValue> iterator22 = next.get("rewards").iterator2();
            while (iterator22.hasNext()) {
                achievementConfig.rewards.add(ItemStack.fromJson(iterator22.next()));
            }
            this.configs[valueOf.ordinal()] = achievementConfig;
        }
    }

    public boolean isRedeemed(AchievementType achievementType) {
        return ProgressPrefs.i().achievement.isRedeemed(achievementType);
    }

    public CharSequence getName(AchievementType achievementType) {
        return Game.i.localeManager.i18n.get("achievement_name_" + achievementType.name());
    }

    public CharSequence getDescription(AchievementType achievementType) {
        return Game.i.localeManager.i18n.format("achievement_description_" + achievementType.name(), Integer.valueOf(this.configs[achievementType.ordinal()].required));
    }

    public void setProgress(AchievementType achievementType, int i) {
        if (Config.isHeadless() || Config.isModdingMode()) {
            return;
        }
        if (Game.i.isInMainThread()) {
            ProgressPrefs i2 = ProgressPrefs.i();
            if (i2.achievement.getProgress(achievementType) < i) {
                int i3 = this.configs[achievementType.ordinal()].required;
                if (i2.achievement.getProgress(achievementType) < i3 && i >= i3 && Game.isLoaded()) {
                    Notifications.i().add(Game.i.localeManager.i18n.format("achievement_complete", getName(achievementType)), Game.i.assetManager.getDrawable("icon-trophy"), MaterialColor.LIGHT_GREEN.P800, StaticSoundType.SUCCESS);
                    Game.i.actionResolver.unlockAchievement(achievementType);
                }
                i2.achievement.setProgress(achievementType, i);
                i2.requireSave();
                return;
            }
            return;
        }
        f2272a.e("skipped setProgress for " + achievementType + " - not on main thread", new Object[0]);
    }

    public void syncAchievementsWithPlatform() {
        for (AchievementType achievementType : AchievementType.values) {
            if (isRequirementMet(achievementType)) {
                Game.i.actionResolver.unlockAchievement(achievementType);
            }
        }
    }

    public void redeem(AchievementType achievementType) {
        ProgressPrefs i = ProgressPrefs.i();
        if (!isRequirementMet(achievementType) || i.achievement.isRedeemed(achievementType)) {
            return;
        }
        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.ACHIEVEMENT, Game.getTimestampSeconds());
        Array<? extends ItemStack> array = new Array<>(ItemStack.class);
        array.addAll(this.configs[achievementType.ordinal()].rewards);
        issuedItems.items.addAll(array);
        issuedItems.achievementType = achievementType;
        Game.i.progressManager.addIssuedPrizes(issuedItems, true);
        Game.i.progressManager.addItemArray(issuedItems.items, "achievement_redeem");
        Game.i.progressManager.showNewlyIssuedPrizesPopup();
        i.achievement.setRedeemed(achievementType, true);
        i.requireSave();
    }

    public int countAchievementsToRedeem() {
        int i = 0;
        ProgressPrefs i2 = ProgressPrefs.i();
        for (AchievementType achievementType : AchievementType.values) {
            if (Game.i.achievementManager.isRequirementMet(achievementType) && !i2.achievement.isRedeemed(achievementType)) {
                i++;
            }
        }
        return i;
    }

    public boolean isRequirementMet(AchievementType achievementType) {
        return ProgressPrefs.i().achievement.getProgress(achievementType) >= this.configs[achievementType.ordinal()].required;
    }

    public int getCurrentProgress(AchievementType achievementType) {
        return ProgressPrefs.i().achievement.getProgress(achievementType);
    }

    private void a(AchievementType achievementType, String[] strArr) {
        int i = 0;
        for (String str : strArr) {
            BasicLevel level = Game.i.basicLevelManager.getLevel(str);
            if (level != null) {
                if (Game.i.basicLevelManager.getGainedStarsOnLevel(level) >= 3) {
                    i++;
                }
            } else {
                throw new IllegalStateException("handleCompletedLevels - level " + str + " not found");
            }
        }
        setProgress(achievementType, i);
    }

    public void updateGlobal() {
        if (Config.isModdingMode()) {
            return;
        }
        a(AchievementType.TUTORIALS_COMPLETE, f2273b);
        a(AchievementType.STAGE_1_COMPLETE, c);
        a(AchievementType.STAGE_2_COMPLETE, d);
        a(AchievementType.STAGE_3_COMPLETE, e);
        a(AchievementType.STAGE_4_COMPLETE, f);
        a(AchievementType.STAGE_5_COMPLETE, g);
        setProgress(AchievementType.UNLOCK_ALL_TROPHIES, Game.i.progressManager.getItemsByType(ItemType.TROPHY).size);
        setProgress(AchievementType.MILLION_PAPERS, (int) Game.i.statisticsManager.getAllTime(StatisticsType.GPG));
        setProgress(AchievementType.KILL_MILLION_ENEMIES, (int) Game.i.statisticsManager.getAllTime(StatisticsType.EK));
        setProgress(AchievementType.KILL_TEN_MILLION_ENEMIES, (int) Game.i.statisticsManager.getAllTime(StatisticsType.EK));
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}

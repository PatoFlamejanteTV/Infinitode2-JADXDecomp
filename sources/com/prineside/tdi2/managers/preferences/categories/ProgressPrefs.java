package com.prineside.tdi2.managers.preferences.categories;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.preferences.PrefCategory;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Achievement;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Auth;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_BasicLevel;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Custom;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_DailyQuest;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Enemy;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Inventory;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Message;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Progress;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Purchase;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Research;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_SecretCode;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Statistics;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_UserMaps;
import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/ProgressPrefs.class */
public final class ProgressPrefs extends PrefCategory {
    public final PP_Achievement achievement = new PP_Achievement();
    public final PP_Auth auth = new PP_Auth();
    public final PP_BasicLevel basicLevel = new PP_BasicLevel();
    public final PP_DailyQuest dailyQuest = new PP_DailyQuest();
    public final PP_Enemy enemy = new PP_Enemy();
    public final PP_Message message = new PP_Message();
    public final PP_Progress progress = new PP_Progress();
    public final PP_Inventory inventory = new PP_Inventory();
    public final PP_Purchase purchase = new PP_Purchase();
    public final PP_Research research = new PP_Research();
    public final PP_SecretCode secretCode = new PP_SecretCode();
    public final PP_Statistics statistics = new PP_Statistics();
    public final PP_UserMaps userMaps = new PP_UserMaps();
    public final PP_Custom custom = new PP_Custom();
    public final PrefSubcategory[] all = {this.achievement, this.auth, this.basicLevel, this.dailyQuest, this.enemy, this.message, this.progress, this.inventory, this.purchase, this.research, this.secretCode, this.statistics, this.userMaps, this.custom};

    public static ProgressPrefs i() {
        return Game.i.preferencesManager.getProgressPrefs();
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefCategory
    public final void load(PrefMap prefMap) {
        for (PrefSubcategory prefSubcategory : this.all) {
            prefSubcategory.load(prefMap);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefCategory
    public final void save(PrefMap prefMap) {
        for (PrefSubcategory prefSubcategory : this.all) {
            prefSubcategory.save(prefMap);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefCategory
    public final void saveAsync(PrefMap prefMap, Runnable runnable, ObjectConsumer<Exception> objectConsumer) {
        Threads.i().asyncConcurrentLoop(this.all, 0, this.all.length, (i, prefSubcategory) -> {
            prefSubcategory.save(prefMap);
        }, runnable, objectConsumer);
    }
}

package com.prineside.tdi2.managers.preferences.categories;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.preferences.PrefCategory;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Auth;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Enemy;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Locale;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Music;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Rating;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Replay;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Settings;
import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/SettingsPrefs.class */
public final class SettingsPrefs extends PrefCategory {
    public final SP_Auth auth = new SP_Auth();
    public final SP_Enemy enemy = new SP_Enemy();
    public final SP_Locale locale = new SP_Locale();
    public final SP_Music music = new SP_Music();
    public final SP_Rating rating = new SP_Rating();
    public final SP_Replay replay = new SP_Replay();
    public final SP_Settings settings = new SP_Settings();
    public final PrefSubcategory[] all = {this.auth, this.enemy, this.locale, this.music, this.rating, this.replay, this.settings};

    public static SettingsPrefs i() {
        return Game.i.preferencesManager.getSettingsPrefs();
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

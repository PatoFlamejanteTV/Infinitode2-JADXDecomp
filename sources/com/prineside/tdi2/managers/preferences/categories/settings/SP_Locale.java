package com.prineside.tdi2.managers.preferences.categories.settings;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Locale.class */
public final class SP_Locale implements PrefSubcategory {

    @Null
    public String localeName;

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.localeName = prefMap.get("locale", null);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("locale", this.localeName);
    }
}

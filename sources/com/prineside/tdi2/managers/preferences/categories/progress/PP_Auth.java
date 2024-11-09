package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Auth.class */
public final class PP_Auth implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private String f2518a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private String f2519b;

    @Null
    public final String getProgressOwnerId() {
        return this.f2518a;
    }

    public final synchronized void setProgressOwnerId(@Null String str) {
        this.f2518a = str;
    }

    @Null
    public final String getProgressOwnerNickname() {
        return this.f2519b;
    }

    public final synchronized void setProgressOwnerNickname(@Null String str) {
        this.f2519b = str;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2518a = prefMap.get("authProgressOwnerId", null);
        this.f2519b = prefMap.get("authProgressOwnerNickname", null);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("authProgressOwnerId", this.f2518a);
        prefMap.set("authProgressOwnerNickname", this.f2519b);
    }
}

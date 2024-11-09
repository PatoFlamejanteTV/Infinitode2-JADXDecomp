package com.prineside.tdi2.managers.preferences;

import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/PrefCategory.class */
public abstract class PrefCategory {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2513a;

    public abstract void load(PrefMap prefMap);

    public abstract void save(PrefMap prefMap);

    public abstract void saveAsync(PrefMap prefMap, Runnable runnable, ObjectConsumer<Exception> objectConsumer);

    public void requireSave() {
        this.f2513a = true;
    }

    public void setSaveRequired(boolean z) {
        this.f2513a = z;
    }

    public boolean isSaveRequired() {
        return this.f2513a;
    }
}

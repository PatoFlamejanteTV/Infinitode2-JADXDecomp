package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Event;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/CountdownEventAction.class */
public class CountdownEventAction<T extends Event> extends EventAction<T> {
    private int f;
    private int g;

    public CountdownEventAction(Class<? extends T> cls, int i) {
        super(cls);
        this.f = i;
    }

    @Override // com.prineside.tdi2.scene2d.actions.EventAction
    public boolean handle(T t) {
        this.g++;
        return this.g >= this.f;
    }
}

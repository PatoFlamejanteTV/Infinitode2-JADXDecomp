package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/Selection.class */
public class Selection<T> implements Disableable, Iterable<T> {

    @Null
    private Actor e;

    /* renamed from: b, reason: collision with root package name */
    boolean f2734b;
    private boolean g;
    boolean c;
    boolean d;

    @Null
    private T i;

    /* renamed from: a, reason: collision with root package name */
    final OrderedSet<T> f2733a = new OrderedSet<>();
    private final OrderedSet<T> f = new OrderedSet<>();
    private boolean h = true;

    public void setActor(@Null Actor actor) {
        this.e = actor;
    }

    public void choose(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.f2734b) {
            return;
        }
        b();
        try {
            if ((!this.g && !UIUtils.ctrl()) || !this.f2733a.contains(t)) {
                boolean z = false;
                if (!this.c || (!this.g && !UIUtils.ctrl())) {
                    if (this.f2733a.size == 1 && this.f2733a.contains(t)) {
                        return;
                    }
                    z = this.f2733a.size > 0;
                    this.f2733a.clear(8);
                }
                if (!this.f2733a.add(t) && !z) {
                    return;
                } else {
                    this.i = t;
                }
            } else {
                if (this.d && this.f2733a.size == 1) {
                    return;
                }
                this.f2733a.remove(t);
                this.i = null;
            }
            if (fireChangeEvent()) {
                c();
            } else {
                a();
            }
        } finally {
            d();
        }
    }

    @Deprecated
    public boolean hasItems() {
        return this.f2733a.size > 0;
    }

    public boolean notEmpty() {
        return this.f2733a.size > 0;
    }

    public boolean isEmpty() {
        return this.f2733a.size == 0;
    }

    public int size() {
        return this.f2733a.size;
    }

    public OrderedSet<T> items() {
        return this.f2733a;
    }

    @Null
    public T first() {
        if (this.f2733a.size == 0) {
            return null;
        }
        return this.f2733a.first();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.f.clear(this.f2733a.size);
        this.f.addAll((OrderedSet) this.f2733a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        this.f2733a.clear(this.f.size);
        this.f2733a.addAll((OrderedSet) this.f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d() {
        this.f.clear(32);
    }

    public void set(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.f2733a.size == 1 && this.f2733a.first() == t) {
            return;
        }
        b();
        this.f2733a.clear(8);
        this.f2733a.add(t);
        if (this.h && fireChangeEvent()) {
            c();
        } else {
            this.i = t;
            a();
        }
        d();
    }

    public void setAll(Array<T> array) {
        boolean z = false;
        b();
        this.i = null;
        this.f2733a.clear(array.size);
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = array.get(i2);
            if (t == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.f2733a.add(t)) {
                z = true;
            }
        }
        if (z) {
            if (this.h && fireChangeEvent()) {
                c();
            } else if (array.size > 0) {
                this.i = array.peek();
                a();
            }
        }
        d();
    }

    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.f2733a.add(t)) {
            if (this.h && fireChangeEvent()) {
                this.f2733a.remove(t);
            } else {
                this.i = t;
                a();
            }
        }
    }

    public void addAll(Array<T> array) {
        boolean z = false;
        b();
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = array.get(i2);
            if (t == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.f2733a.add(t)) {
                z = true;
            }
        }
        if (z) {
            if (this.h && fireChangeEvent()) {
                c();
            } else {
                this.i = array.peek();
                a();
            }
        }
        d();
    }

    public void remove(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.f2733a.remove(t)) {
            if (this.h && fireChangeEvent()) {
                this.f2733a.add(t);
            } else {
                this.i = null;
                a();
            }
        }
    }

    public void removeAll(Array<T> array) {
        boolean z = false;
        b();
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = array.get(i2);
            if (t == null) {
                throw new IllegalArgumentException("item cannot be null.");
            }
            if (this.f2733a.remove(t)) {
                z = true;
            }
        }
        if (z) {
            if (this.h && fireChangeEvent()) {
                c();
            } else {
                this.i = null;
                a();
            }
        }
        d();
    }

    public void clear() {
        if (this.f2733a.size == 0) {
            this.i = null;
            return;
        }
        b();
        this.f2733a.clear(8);
        if (this.h && fireChangeEvent()) {
            c();
        } else {
            this.i = null;
            a();
        }
        d();
    }

    protected void a() {
    }

    public boolean fireChangeEvent() {
        if (this.e == null) {
            return false;
        }
        ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
        try {
            return this.e.fire(changeEvent);
        } finally {
            Pools.free(changeEvent);
        }
    }

    public boolean contains(@Null T t) {
        if (t == null) {
            return false;
        }
        return this.f2733a.contains(t);
    }

    @Null
    public T getLastSelected() {
        if (this.i != null) {
            return this.i;
        }
        if (this.f2733a.size > 0) {
            return this.f2733a.first();
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.f2733a.iterator();
    }

    public Array<T> toArray() {
        return this.f2733a.iterator().toArray();
    }

    public Array<T> toArray(Array<T> array) {
        return this.f2733a.iterator().toArray(array);
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.f2734b = z;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.f2734b;
    }

    public boolean getToggle() {
        return this.g;
    }

    public void setToggle(boolean z) {
        this.g = z;
    }

    public boolean getMultiple() {
        return this.c;
    }

    public void setMultiple(boolean z) {
        this.c = z;
    }

    public boolean getRequired() {
        return this.d;
    }

    public void setRequired(boolean z) {
        this.d = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.h = z;
    }

    public String toString() {
        return this.f2733a.toString();
    }
}

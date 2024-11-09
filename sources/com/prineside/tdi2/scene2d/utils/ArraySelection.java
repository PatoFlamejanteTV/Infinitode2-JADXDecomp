package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ArraySelection.class */
public class ArraySelection<T> extends Selection<T> {
    private Array<T> e;
    private boolean f = true;
    private T g;

    public ArraySelection(Array<T> array) {
        this.e = array;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Selection
    public void choose(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.f2734b) {
            return;
        }
        if (!this.f || !this.c) {
            super.choose(t);
            return;
        }
        if (this.f2733a.size > 0 && UIUtils.shift()) {
            int indexOf = this.g == null ? -1 : this.e.indexOf(this.g, false);
            int i = indexOf;
            if (indexOf != -1) {
                T t2 = this.g;
                b();
                int i2 = i;
                int indexOf2 = this.e.indexOf(t, false);
                if (i > indexOf2) {
                    indexOf2 = i;
                    i2 = indexOf2;
                }
                if (!UIUtils.ctrl()) {
                    this.f2733a.clear(8);
                }
                for (int i3 = i2; i3 <= indexOf2; i3++) {
                    this.f2733a.add(this.e.get(i3));
                }
                if (fireChangeEvent()) {
                    c();
                } else {
                    a();
                }
                this.g = t2;
                d();
                return;
            }
        }
        super.choose(t);
        this.g = t;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Selection
    protected final void a() {
        this.g = null;
    }

    public boolean getRangeSelect() {
        return this.f;
    }

    public void setRangeSelect(boolean z) {
        this.f = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void validate() {
        Array<T> array = this.e;
        if (array.size == 0) {
            clear();
            return;
        }
        boolean z = false;
        OrderedSet.OrderedSetIterator<T> it = items().iterator();
        while (it.hasNext()) {
            if (!array.contains(it.next(), false)) {
                it.remove();
                z = true;
            }
        }
        if (this.d && this.f2733a.size == 0) {
            set(array.first());
        } else if (z) {
            a();
        }
    }
}

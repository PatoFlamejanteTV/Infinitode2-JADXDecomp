package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedSet;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/ArraySelection.class */
public class ArraySelection<T> extends Selection<T> {
    private Array<T> array;
    private boolean rangeSelect = true;
    private T rangeStart;

    public ArraySelection(Array<T> array) {
        this.array = array;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
    public void choose(T t) {
        if (t == null) {
            throw new IllegalArgumentException("item cannot be null.");
        }
        if (this.isDisabled) {
            return;
        }
        if (!this.rangeSelect || !this.multiple) {
            super.choose(t);
            return;
        }
        if (this.selected.size > 0 && UIUtils.shift()) {
            int indexOf = this.rangeStart == null ? -1 : this.array.indexOf(this.rangeStart, false);
            int i = indexOf;
            if (indexOf != -1) {
                T t2 = this.rangeStart;
                snapshot();
                int i2 = i;
                int indexOf2 = this.array.indexOf(t, false);
                if (i > indexOf2) {
                    indexOf2 = i;
                    i2 = indexOf2;
                }
                if (!UIUtils.ctrl()) {
                    this.selected.clear(8);
                }
                for (int i3 = i2; i3 <= indexOf2; i3++) {
                    this.selected.add(this.array.get(i3));
                }
                if (fireChangeEvent()) {
                    revert();
                } else {
                    changed();
                }
                this.rangeStart = t2;
                cleanup();
                return;
            }
        }
        super.choose(t);
        this.rangeStart = t;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
    protected void changed() {
        this.rangeStart = null;
    }

    public boolean getRangeSelect() {
        return this.rangeSelect;
    }

    public void setRangeSelect(boolean z) {
        this.rangeSelect = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void validate() {
        Array<T> array = this.array;
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
        if (this.required && this.selected.size == 0) {
            set(array.first());
        } else if (z) {
            changed();
        }
    }
}

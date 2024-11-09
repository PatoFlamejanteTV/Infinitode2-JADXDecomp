package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.ui.Button;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ButtonGroup.class */
public class ButtonGroup<T extends Button> {

    /* renamed from: a, reason: collision with root package name */
    private final Array<T> f2654a;

    /* renamed from: b, reason: collision with root package name */
    private Array<T> f2655b;
    private int c;
    private int d;
    private boolean e;
    private T f;

    public ButtonGroup() {
        this.f2654a = new Array<>();
        this.f2655b = new Array<>(1);
        this.d = 1;
        this.e = true;
        this.c = 1;
    }

    public ButtonGroup(T... tArr) {
        this.f2654a = new Array<>();
        this.f2655b = new Array<>(1);
        this.d = 1;
        this.e = true;
        this.c = 0;
        add(tArr);
        this.c = 1;
    }

    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        t.l = null;
        boolean z = t.isChecked() || this.f2654a.size < this.c;
        t.setChecked(false);
        t.l = this;
        this.f2654a.add(t);
        t.setChecked(z);
    }

    public void add(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("buttons cannot be null.");
        }
        for (T t : tArr) {
            add((ButtonGroup<T>) t);
        }
    }

    public void remove(T t) {
        if (t == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        t.l = null;
        this.f2654a.removeValue(t, true);
        this.f2655b.removeValue(t, true);
    }

    public void remove(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("buttons cannot be null.");
        }
        for (T t : tArr) {
            remove((ButtonGroup<T>) t);
        }
    }

    public void clear() {
        this.f2654a.clear();
        this.f2655b.clear();
    }

    public void setChecked(String str) {
        if (str == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        int i = this.f2654a.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = this.f2654a.get(i2);
            if ((t instanceof TextButton) && str.contentEquals(((TextButton) t).getText())) {
                t.setChecked(true);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(T t, boolean z) {
        int i;
        if (t.k == z) {
            return false;
        }
        if (!z) {
            if (this.f2655b.size <= this.c) {
                return false;
            }
            this.f2655b.removeValue(t, true);
            return true;
        }
        if (this.d != -1 && this.f2655b.size >= this.d) {
            if (!this.e) {
                return false;
            }
            int i2 = 0;
            do {
                int i3 = this.c;
                this.c = 0;
                this.f.setChecked(false);
                this.c = i3;
                if (t.k == z) {
                    return false;
                }
                if (this.f2655b.size >= this.d) {
                    i = i2;
                    i2++;
                }
            } while (i <= 10);
            return false;
        }
        this.f2655b.add(t);
        this.f = t;
        return true;
    }

    public void uncheckAll() {
        int i = this.c;
        this.c = 0;
        int i2 = this.f2654a.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f2654a.get(i3).setChecked(false);
        }
        this.c = i;
    }

    @Null
    public T getChecked() {
        if (this.f2655b.size > 0) {
            return this.f2655b.get(0);
        }
        return null;
    }

    public int getCheckedIndex() {
        if (this.f2655b.size > 0) {
            return this.f2654a.indexOf(this.f2655b.get(0), true);
        }
        return -1;
    }

    public Array<T> getAllChecked() {
        return this.f2655b;
    }

    public Array<T> getButtons() {
        return this.f2654a;
    }

    public void setMinCheckCount(int i) {
        this.c = i;
    }

    public void setMaxCheckCount(int i) {
        if (i == 0) {
            i = -1;
        }
        this.d = i;
    }

    public void setUncheckLast(boolean z) {
        this.e = z;
    }
}

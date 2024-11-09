package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ButtonGroup.class */
public class ButtonGroup<T extends Button> {
    private final Array<T> buttons;
    private Array<T> checkedButtons;
    private int minCheckCount;
    private int maxCheckCount;
    private boolean uncheckLast;
    private T lastChecked;

    public ButtonGroup() {
        this.buttons = new Array<>();
        this.checkedButtons = new Array<>(1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 1;
    }

    public ButtonGroup(T... tArr) {
        this.buttons = new Array<>();
        this.checkedButtons = new Array<>(1);
        this.maxCheckCount = 1;
        this.uncheckLast = true;
        this.minCheckCount = 0;
        add(tArr);
        this.minCheckCount = 1;
    }

    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("button cannot be null.");
        }
        t.buttonGroup = null;
        boolean z = t.isChecked() || this.buttons.size < this.minCheckCount;
        t.setChecked(false);
        t.buttonGroup = this;
        this.buttons.add(t);
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
        t.buttonGroup = null;
        this.buttons.removeValue(t, true);
        this.checkedButtons.removeValue(t, true);
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
        this.buttons.clear();
        this.checkedButtons.clear();
    }

    public void setChecked(String str) {
        if (str == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }
        int i = this.buttons.size;
        for (int i2 = 0; i2 < i; i2++) {
            T t = this.buttons.get(i2);
            if ((t instanceof TextButton) && str.contentEquals(((TextButton) t).getText())) {
                t.setChecked(true);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canCheck(T t, boolean z) {
        int i;
        if (t.isChecked == z) {
            return false;
        }
        if (!z) {
            if (this.checkedButtons.size <= this.minCheckCount) {
                return false;
            }
            this.checkedButtons.removeValue(t, true);
            return true;
        }
        if (this.maxCheckCount != -1 && this.checkedButtons.size >= this.maxCheckCount) {
            if (!this.uncheckLast) {
                return false;
            }
            int i2 = 0;
            do {
                int i3 = this.minCheckCount;
                this.minCheckCount = 0;
                this.lastChecked.setChecked(false);
                this.minCheckCount = i3;
                if (t.isChecked == z) {
                    return false;
                }
                if (this.checkedButtons.size >= this.maxCheckCount) {
                    i = i2;
                    i2++;
                }
            } while (i <= 10);
            return false;
        }
        this.checkedButtons.add(t);
        this.lastChecked = t;
        return true;
    }

    public void uncheckAll() {
        int i = this.minCheckCount;
        this.minCheckCount = 0;
        int i2 = this.buttons.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.buttons.get(i3).setChecked(false);
        }
        this.minCheckCount = i;
    }

    @Null
    public T getChecked() {
        if (this.checkedButtons.size > 0) {
            return this.checkedButtons.get(0);
        }
        return null;
    }

    public int getCheckedIndex() {
        if (this.checkedButtons.size > 0) {
            return this.buttons.indexOf(this.checkedButtons.get(0), true);
        }
        return -1;
    }

    public Array<T> getAllChecked() {
        return this.checkedButtons;
    }

    public Array<T> getButtons() {
        return this.buttons;
    }

    public void setMinCheckCount(int i) {
        this.minCheckCount = i;
    }

    public void setMaxCheckCount(int i) {
        if (i == 0) {
            i = -1;
        }
        this.maxCheckCount = i;
    }

    public void setUncheckLast(boolean z) {
        this.uncheckLast = z;
    }
}

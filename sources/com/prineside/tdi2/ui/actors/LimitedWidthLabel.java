package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/LimitedWidthLabel.class */
public class LimitedWidthLabel extends Label {
    private float j;
    private int[] k;
    private static final IntArray l = new IntArray();

    public LimitedWidthLabel(CharSequence charSequence, int i, int i2, float f) {
        super(charSequence, Game.i.assetManager.getLabelStyle(i));
        this.j = f;
        this.k = a(i2, i);
        setText(charSequence);
    }

    private static int[] a(int i, int i2) {
        l.clear();
        if (i != i2) {
            l.add(i);
            if (i < 18 && i2 > 18) {
                l.add(18);
            }
            if (i < 21 && i2 > 21) {
                l.add(21);
            }
            if (i < 24 && i2 > 24) {
                l.add(24);
            }
            if (i < 30 && i2 > 30) {
                l.add(30);
            }
            if (i < 36 && i2 > 36) {
                l.add(36);
            }
            if (i < 60 && i2 > 60) {
                l.add(60);
            }
        }
        l.add(i2);
        return l.toArray();
    }

    private static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence.length() == charSequence2.length()) {
            return charSequence.length() >= 3 && charSequence.charAt(charSequence.length() - 3) == '.' && charSequence.charAt(charSequence.length() - 2) == '.' && charSequence.charAt(charSequence.length() - 1) == '.';
        }
        return true;
    }

    @Override // com.prineside.tdi2.ui.actors.Label
    public void setText(@Null CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        Label.LabelStyle style = getStyle();
        CharSequence charSequence2 = charSequence;
        for (int length = this.k.length - 1; length >= 0; length--) {
            style = Game.i.assetManager.getLabelStyle(this.k[length]);
            CharSequence fitToWidth = StringFormatter.fitToWidth(charSequence, this.j, style.font, "...");
            charSequence2 = fitToWidth;
            if (!a(fitToWidth, charSequence)) {
                break;
            }
        }
        if (style != getStyle()) {
            setStyle(style);
        }
        super.setText(charSequence2);
    }
}

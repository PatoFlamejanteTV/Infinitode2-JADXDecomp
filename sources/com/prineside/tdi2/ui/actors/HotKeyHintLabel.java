package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/HotKeyHintLabel.class */
public class HotKeyHintLabel extends Group {
    private int[] l;
    private final Array<int[]> m;
    private Label q;
    private Label r;
    public boolean debug;
    private int s;
    private static final TLog k = TLog.forClass(HotKeyHintLabel.class);
    private static final StringBuilder n = new StringBuilder();
    private static final StringBuilder o = new StringBuilder();
    private static final Vector2 p = new Vector2();

    private HotKeyHintLabel(float f, float f2) {
        this.l = new int[0];
        this.m = new Array<>(true, 8, int[].class);
        this.debug = false;
        this.s = -1;
        this.q = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.q.setAlignment(1);
        this.q.setSize(1.0f, 1.0f);
        this.q.setColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.q.setPosition(1.5f, -1.5f);
        addActor(this.q);
        this.r = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.r.setAlignment(1);
        this.r.setSize(1.0f, 1.0f);
        this.r.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        addActor(this.r);
        setTransform(false);
        setSize(1.0f, 1.0f);
        setPosition(f, f2 + 8.0f);
    }

    public HotKeyHintLabel(int[] iArr, float f, float f2) {
        this(f, f2);
        this.l = iArr;
        d();
    }

    public HotKeyHintLabel(int[] iArr, float f, float f2, int i) {
        this(f, f2);
        this.q.setAlignment(i);
        this.r.setAlignment(i);
        this.l = iArr;
        d();
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        super.draw(batch, f);
        d();
        float screenHeight = Game.i.uiManager.getScreenHeight();
        p.set(Vector2.Zero);
        localToStageCoordinates(p);
        float scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() / screenHeight;
        float x = Gdx.input.getX() * scaledViewportHeight;
        float y = (screenHeight - Gdx.input.getY()) * scaledViewportHeight;
        float distanceBetweenPoints = PMath.getDistanceBetweenPoints(p.x, p.y, x, y);
        float clamp = MathUtils.clamp(1.0f - (distanceBetweenPoints / 480.0f), 0.0f, 1.0f);
        this.q.setColor(0.0f, 0.0f, 0.0f, 0.09f + (0.35f * clamp));
        this.r.setColor(1.0f, 1.0f, 1.0f, 0.16f + (0.65f * clamp));
        if (this.debug) {
            k.i(((int) distanceBetweenPoints) + " / " + ((int) p.x) + ":" + ((int) p.y) + " / " + ((int) x) + ":" + ((int) y), new Object[0]);
        }
    }

    public void addVariant(int[] iArr) {
        this.m.add(iArr);
        this.s = -1;
        d();
    }

    private void d() {
        int i = 1;
        if (this.m.size != 0) {
            IntArray holdingHotKeys = Game.i.settingsManager.getHoldingHotKeys();
            int[] iArr = null;
            Array.ArrayIterator<int[]> it = this.m.iterator();
            while (it.hasNext()) {
                int[] next = it.next();
                int length = next.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (!holdingHotKeys.contains(next[i2])) {
                        i2++;
                    } else {
                        iArr = next;
                        break;
                    }
                }
            }
            if (iArr != null) {
                for (int i3 : iArr) {
                    i = (i * 31) + i3;
                }
            } else {
                i = 9032;
            }
        }
        if (this.s != i) {
            this.s = i;
            if (this.m.size != 0) {
                IntArray holdingHotKeys2 = Game.i.settingsManager.getHoldingHotKeys();
                int[] iArr2 = null;
                Array.ArrayIterator<int[]> it2 = this.m.iterator();
                while (it2.hasNext()) {
                    int[] next2 = it2.next();
                    int length2 = next2.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            break;
                        }
                        if (!holdingHotKeys2.contains(next2[i4])) {
                            i4++;
                        } else {
                            iArr2 = next2;
                            break;
                        }
                    }
                }
                if (iArr2 != null) {
                    this.r.setText(generateHotKeysLabelText(iArr2));
                    this.q.setText(this.r.getText());
                    return;
                }
                boolean z = false;
                Array.ArrayIterator<int[]> it3 = this.m.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        if (it3.next().length == 1) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z) {
                    o.setLength(0);
                    o.append(generateHotKeysLabelText(this.l));
                    Array.ArrayIterator<int[]> it4 = this.m.iterator();
                    while (it4.hasNext()) {
                        int[] next3 = it4.next();
                        if (next3.length == 1) {
                            o.append("[#888888] / []");
                            o.append(generateHotKeysLabelText(next3));
                        }
                    }
                    this.r.setText(o);
                    this.q.setText(this.r.getText());
                    return;
                }
            }
            this.r.setText(generateHotKeysLabelText(this.l));
            this.q.setText(this.r.getText());
        }
    }

    public static boolean isAvailable() {
        return Gdx.app.getType() == Application.ApplicationType.Desktop;
    }

    public static boolean isEnabled() {
        return Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_HOT_KEY_HINTS) != 0.0d && isAvailable();
    }

    public static CharSequence generateHotKeysLabelText(int[] iArr) {
        n.setLength(0);
        for (int i : iArr) {
            if (n.length != 0) {
                n.append("[#AAAAAA] + []");
            }
            if (i == 71) {
                n.append("[[");
            } else if (i == 129) {
                n.append("Ctrl");
            } else if (i == 59) {
                n.append("Shift");
            } else if (i == 57) {
                n.append("Alt");
            } else if (i == 144) {
                n.append("Nm 0");
            } else if (i == 145) {
                n.append("Nm 1");
            } else if (i == 146) {
                n.append("Nm 2");
            } else if (i == 147) {
                n.append("Nm 3");
            } else if (i == 148) {
                n.append("Nm 4");
            } else if (i == 149) {
                n.append("Nm 5");
            } else if (i == 150) {
                n.append("Nm 6");
            } else if (i == 151) {
                n.append("Nm 7");
            } else if (i == 152) {
                n.append("Nm 8");
            } else if (i == 153) {
                n.append("Nm 9");
            } else {
                n.append(Input.Keys.toString(i).toUpperCase(Locale.US));
            }
        }
        return n;
    }
}

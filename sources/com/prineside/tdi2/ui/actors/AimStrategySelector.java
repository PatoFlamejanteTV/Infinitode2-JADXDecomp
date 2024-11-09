package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AimStrategySelector.class */
public class AimStrategySelector extends Group {
    private final PaddedImageButton k;
    private final PaddedImageButton l;
    private Tower.AimStrategy n;
    private float o;
    private static final Comparator<StrategyIcon> s = (strategyIcon, strategyIcon2) -> {
        if (strategyIcon.k == strategyIcon2.k) {
            return 0;
        }
        return StrictMath.abs(strategyIcon.k) > StrictMath.abs(strategyIcon2.k) ? -1 : 1;
    };
    private float m = 4.0f;
    private final StrategyIcon[] p = new StrategyIcon[Tower.AimStrategy.values.length];
    private final Array<StrategyIcon> q = new Array<>(StrategyIcon.class);
    private final DelayedRemovalArray<AimStrategySelectorListener> r = new DelayedRemovalArray<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AimStrategySelector$AimStrategySelectorListener.class */
    public interface AimStrategySelectorListener {
        void strategyChanged(Tower.AimStrategy aimStrategy);
    }

    public AimStrategySelector() {
        setTransform(false);
        setSize(310.0f, 84.0f);
        Group group = new Group();
        group.setSize(310.0f, 84.0f);
        group.setTransform(false);
        addActor(group);
        for (Tower.AimStrategy aimStrategy : Tower.AimStrategy.values) {
            StrategyIcon strategyIcon = new StrategyIcon(this, Game.i.towerManager.getAimStrategyIconAlias(aimStrategy), Game.i.towerManager.getAimStrategyColor(aimStrategy));
            this.p[aimStrategy.ordinal()] = strategyIcon;
            group.addActor(strategyIcon);
            this.q.add(strategyIcon);
        }
        this.k = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-left"), () -> {
            d();
        }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P900);
        this.k.setSize(155.0f, 84.0f);
        this.k.setIconSize(40.0f, 40.0f).setIconPosition(6.0f, 23.0f);
        addActor(this.k);
        this.l = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right"), () -> {
            e();
        }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P900);
        this.l.setSize(155.0f, 84.0f);
        this.l.setPosition(155.0f, 0.0f);
        this.l.setIconSize(40.0f, 40.0f).setIconPosition(110.0f, 23.0f);
        addActor(this.l);
        if (HotKeyHintLabel.isEnabled()) {
            addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.AIM_MODE_SWITCH_PREVIOUS), 26.0f, 6.0f));
            addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.AIM_MODE_SWITCH_NEXT), 285.0f, 6.0f));
        }
        setStrategy(Tower.AimStrategy.values[0], false, false);
        f();
    }

    public void addListener(AimStrategySelectorListener aimStrategySelectorListener) {
        if (aimStrategySelectorListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.r.contains(aimStrategySelectorListener, true)) {
            this.r.add(aimStrategySelectorListener);
        }
    }

    public void removeListener(AimStrategySelectorListener aimStrategySelectorListener) {
        if (aimStrategySelectorListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.r.removeValue(aimStrategySelectorListener, true);
    }

    private void d() {
        int ordinal = this.n.ordinal() - 1;
        int i = ordinal;
        if (ordinal == -1) {
            i = Tower.AimStrategy.values.length - 1;
        }
        setStrategy(Tower.AimStrategy.values[i], true, true);
    }

    private void e() {
        int ordinal = this.n.ordinal() + 1;
        int i = ordinal;
        if (ordinal == Tower.AimStrategy.values.length) {
            i = 0;
        }
        setStrategy(Tower.AimStrategy.values[i], true, true);
    }

    public void setStrategy(Tower.AimStrategy aimStrategy, boolean z, boolean z2) {
        if (this.n != aimStrategy) {
            this.n = aimStrategy;
            if (!z) {
                this.o = aimStrategy.ordinal();
                f();
            } else {
                this.m = StrictMath.abs(PMath.loopedDistance(this.o, aimStrategy.ordinal(), Tower.AimStrategy.values.length)) * 4.0f;
                if (this.m < 4.0f) {
                    this.m = 4.0f;
                }
            }
            if (z2) {
                this.r.begin();
                int i = this.r.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.r.get(i2).strategyChanged(aimStrategy);
                }
                this.r.end();
            }
        }
    }

    private void f() {
        for (int i = 0; i < Tower.AimStrategy.values.length; i++) {
            this.p[i].a(PMath.loopedDistance(i, this.o, Tower.AimStrategy.values.length));
        }
        this.q.sort(s);
        for (int i2 = 0; i2 < this.q.size; i2++) {
            this.q.items[i2].setZIndex(99);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        if (this.o != this.n.ordinal()) {
            float f2 = f * this.m;
            if (!Game.i.settingsManager.isUiAnimationsEnabled()) {
                f2 = 9001.0f;
            }
            float loopedDistance = PMath.loopedDistance(this.o, this.n.ordinal(), Tower.AimStrategy.values.length);
            float f3 = loopedDistance;
            if (loopedDistance > f2) {
                f3 = f2;
            } else if (f3 < (-f2)) {
                f3 = -f2;
            }
            this.o -= f3;
            this.o = (this.o + this.p.length) % this.p.length;
            f();
        }
        super.act(f);
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void sizeChanged() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AimStrategySelector$StrategyIcon.class */
    public class StrategyIcon extends Group {
        float k;
        private Image l;
        private Image m;
        private final Color n = new Color();

        StrategyIcon(AimStrategySelector aimStrategySelector, String str, Color color) {
            this.n.set(color);
            this.l = new Image(Game.i.assetManager.getDrawable("ui-aim-strategy-switch-item-background"));
            addActor(this.l);
            this.m = new Image(Game.i.assetManager.getDrawable(str));
            addActor(this.m);
            setTransform(false);
            setSize(94.0f, 84.0f);
        }

        final void a(float f) {
            this.k = f;
            float abs = (2.0f - StrictMath.abs(f)) / 2.0f;
            if (abs < 0.0f || abs > 1.0f) {
                setVisible(false);
                return;
            }
            setVisible(true);
            float f2 = 1.0f - ((1.0f - abs) * 0.33f);
            float f3 = 94.0f * f2;
            float f4 = 84.0f * f2;
            setPosition((155.0f + (70.0f * f)) - (f3 * 0.5f), 42.0f - (f4 * 0.5f));
            setSize(f3, f4);
            this.n.f889a = abs;
            this.l.setColor(this.n);
            this.l.setSize(f3, f4);
            float f5 = 64.0f * f2;
            this.m.setColor(1.0f, 1.0f, 1.0f, abs);
            this.m.setSize(f5, f5);
            this.m.setPosition(15.0f * f2, 12.0f * f2);
        }
    }
}

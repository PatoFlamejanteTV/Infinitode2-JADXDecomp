package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.actions.Actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TooltipManager.class */
public class TooltipManager {
    private static TooltipManager e;
    private static Files f;
    Tooltip d;
    public float initialTime = 2.0f;
    public float subsequentTime = 0.0f;
    public float resetTime = 1.5f;
    public boolean enabled = true;
    public boolean animations = true;
    public float maxWidth = 2.1474836E9f;
    public float offsetX = 15.0f;
    public float offsetY = 19.0f;
    public float edgeDistance = 7.0f;

    /* renamed from: a, reason: collision with root package name */
    final Array<Tooltip> f2685a = new Array<>();

    /* renamed from: b, reason: collision with root package name */
    float f2686b = this.initialTime;
    final Timer.Task c = new Timer.Task() { // from class: com.prineside.tdi2.scene2d.ui.TooltipManager.1
        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            TooltipManager.this.f2686b = TooltipManager.this.initialTime;
        }
    };
    private Timer.Task g = new Timer.Task() { // from class: com.prineside.tdi2.scene2d.ui.TooltipManager.2
        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            Stage stage;
            if (TooltipManager.this.d == null || TooltipManager.this.d.d == null || (stage = TooltipManager.this.d.d.getStage()) == null) {
                return;
            }
            stage.addActor(TooltipManager.this.d.f2683a);
            TooltipManager.this.d.f2683a.toFront();
            TooltipManager.this.f2685a.add(TooltipManager.this.d);
            TooltipManager.this.d.f2683a.clearActions();
            TooltipManager.this.a(TooltipManager.this.d);
            if (!TooltipManager.this.d.f2684b) {
                TooltipManager.this.f2686b = TooltipManager.this.subsequentTime;
                TooltipManager.this.c.cancel();
            }
        }
    };

    public void touchDown(Tooltip tooltip) {
        this.g.cancel();
        if (tooltip.f2683a.remove()) {
            this.c.cancel();
        }
        this.c.run();
        if (this.enabled || tooltip.c) {
            this.d = tooltip;
            Timer.schedule(this.g, this.f2686b);
        }
    }

    public void enter(Tooltip tooltip) {
        this.d = tooltip;
        this.g.cancel();
        if (this.enabled || tooltip.c) {
            if (this.f2686b == 0.0f || tooltip.f2684b) {
                this.g.run();
            } else {
                Timer.schedule(this.g, this.f2686b);
            }
        }
    }

    public void hide(Tooltip tooltip) {
        this.d = null;
        this.g.cancel();
        if (tooltip.f2683a.hasParent()) {
            this.f2685a.removeValue(tooltip, true);
            b(tooltip);
            this.c.cancel();
            Timer.schedule(this.c, this.resetTime);
        }
    }

    protected final void a(Tooltip tooltip) {
        float f2 = this.animations ? this.f2686b > 0.0f ? 0.5f : 0.15f : 0.1f;
        tooltip.f2683a.setTransform(true);
        tooltip.f2683a.getColor().f889a = 0.2f;
        tooltip.f2683a.setScale(0.05f);
        tooltip.f2683a.addAction(Actions.parallel(Actions.fadeIn(f2, Interpolation.fade), Actions.scaleTo(1.0f, 1.0f, f2, Interpolation.fade)));
    }

    private static void b(Tooltip tooltip) {
        tooltip.f2683a.addAction(Actions.sequence(Actions.parallel(Actions.alpha(0.2f, 0.2f, Interpolation.fade), Actions.scaleTo(0.05f, 0.05f, 0.2f, Interpolation.fade)), Actions.removeActor()));
    }

    public void hideAll() {
        this.c.cancel();
        this.g.cancel();
        this.f2686b = this.initialTime;
        this.d = null;
        Array.ArrayIterator<Tooltip> it = this.f2685a.iterator();
        while (it.hasNext()) {
            it.next().hide();
        }
        this.f2685a.clear();
    }

    public void instant() {
        this.f2686b = 0.0f;
        this.g.run();
        this.g.cancel();
    }

    public static TooltipManager getInstance() {
        if (f == null || f != Gdx.files) {
            f = Gdx.files;
            e = new TooltipManager();
        }
        return e;
    }
}

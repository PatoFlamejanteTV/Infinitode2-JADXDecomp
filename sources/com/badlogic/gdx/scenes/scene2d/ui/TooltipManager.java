package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TooltipManager.class */
public class TooltipManager {
    private static TooltipManager instance;
    private static Files files;
    Tooltip showTooltip;
    public float initialTime = 2.0f;
    public float subsequentTime = 0.0f;
    public float resetTime = 1.5f;
    public boolean enabled = true;
    public boolean animations = true;
    public float maxWidth = 2.1474836E9f;
    public float offsetX = 15.0f;
    public float offsetY = 19.0f;
    public float edgeDistance = 7.0f;
    final Array<Tooltip> shown = new Array<>();
    float time = this.initialTime;
    final Timer.Task resetTask = new Timer.Task() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TooltipManager.1
        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            TooltipManager.this.time = TooltipManager.this.initialTime;
        }
    };
    final Timer.Task showTask = new Timer.Task() { // from class: com.badlogic.gdx.scenes.scene2d.ui.TooltipManager.2
        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
        public void run() {
            Stage stage;
            if (TooltipManager.this.showTooltip == null || TooltipManager.this.showTooltip.targetActor == null || (stage = TooltipManager.this.showTooltip.targetActor.getStage()) == null) {
                return;
            }
            stage.addActor(TooltipManager.this.showTooltip.container);
            TooltipManager.this.showTooltip.container.toFront();
            TooltipManager.this.shown.add(TooltipManager.this.showTooltip);
            TooltipManager.this.showTooltip.container.clearActions();
            TooltipManager.this.showAction(TooltipManager.this.showTooltip);
            if (!TooltipManager.this.showTooltip.instant) {
                TooltipManager.this.time = TooltipManager.this.subsequentTime;
                TooltipManager.this.resetTask.cancel();
            }
        }
    };

    public void touchDown(Tooltip tooltip) {
        this.showTask.cancel();
        if (tooltip.container.remove()) {
            this.resetTask.cancel();
        }
        this.resetTask.run();
        if (this.enabled || tooltip.always) {
            this.showTooltip = tooltip;
            Timer.schedule(this.showTask, this.time);
        }
    }

    public void enter(Tooltip tooltip) {
        this.showTooltip = tooltip;
        this.showTask.cancel();
        if (this.enabled || tooltip.always) {
            if (this.time == 0.0f || tooltip.instant) {
                this.showTask.run();
            } else {
                Timer.schedule(this.showTask, this.time);
            }
        }
    }

    public void hide(Tooltip tooltip) {
        this.showTooltip = null;
        this.showTask.cancel();
        if (tooltip.container.hasParent()) {
            this.shown.removeValue(tooltip, true);
            hideAction(tooltip);
            this.resetTask.cancel();
            Timer.schedule(this.resetTask, this.resetTime);
        }
    }

    protected void showAction(Tooltip tooltip) {
        float f = this.animations ? this.time > 0.0f ? 0.5f : 0.15f : 0.1f;
        tooltip.container.setTransform(true);
        tooltip.container.getColor().f889a = 0.2f;
        tooltip.container.setScale(0.05f);
        tooltip.container.addAction(Actions.parallel(Actions.fadeIn(f, Interpolation.fade), Actions.scaleTo(1.0f, 1.0f, f, Interpolation.fade)));
    }

    protected void hideAction(Tooltip tooltip) {
        tooltip.container.addAction(Actions.sequence(Actions.parallel(Actions.alpha(0.2f, 0.2f, Interpolation.fade), Actions.scaleTo(0.05f, 0.05f, 0.2f, Interpolation.fade)), Actions.removeActor()));
    }

    public void hideAll() {
        this.resetTask.cancel();
        this.showTask.cancel();
        this.time = this.initialTime;
        this.showTooltip = null;
        Array.ArrayIterator<Tooltip> it = this.shown.iterator();
        while (it.hasNext()) {
            it.next().hide();
        }
        this.shown.clear();
    }

    public void instant() {
        this.time = 0.0f;
        this.showTask.run();
        this.showTask.cancel();
    }

    public static TooltipManager getInstance() {
        if (files == null || files != Gdx.files) {
            files = Gdx.files;
            instance = new TooltipManager();
        }
        return instance;
    }
}

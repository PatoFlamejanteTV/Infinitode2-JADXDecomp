package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/VisibleDisplayFrameDebugFeature.class */
public class VisibleDisplayFrameDebugFeature extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3761a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.OVERLAY, 70001, "VisibleDisplayFrameDebugFeature", true);

    /* renamed from: b, reason: collision with root package name */
    private Image f3762b;
    private Cell<?> c;

    static {
        TLog.forClass(VisibleDisplayFrameDebugFeature.class);
    }

    public static VisibleDisplayFrameDebugFeature i() {
        return (VisibleDisplayFrameDebugFeature) Game.i.uiManager.getComponent(VisibleDisplayFrameDebugFeature.class);
    }

    public VisibleDisplayFrameDebugFeature() {
        this.f3761a.ignoreVisibleFrame = true;
        this.f3762b = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.f3762b.setTouchable(Touchable.enabled);
        this.f3762b.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.shared.VisibleDisplayFrameDebugFeature.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                float stageY = inputEvent.getStageY();
                float f3 = stageY;
                if (stageY < 0.0f) {
                    f3 = 0.0f;
                } else if (f3 > inputEvent.getStage().getHeight() - 4.0f) {
                    f3 = inputEvent.getStage().getHeight() - 4.0f;
                }
                VisibleDisplayFrameDebugFeature.this.c.padBottom(f3);
                VisibleDisplayFrameDebugFeature.this.f3761a.getTable().invalidateHierarchy();
                VisibleDisplayFrameDebugFeature.this.a();
            }
        });
        this.f3761a.getTable().add().width(1.0f).growY().row();
        this.c = this.f3761a.getTable().add((Table) this.f3762b).growX().height(4.0f).bottom().padBottom(600.0f);
        this.f3761a.getTable().setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Game.i.notifyVisibleDisplayFrameChanged(0, 0, 0, Gdx.graphics.getHeight() - ((int) this.f3762b.localToScreenCoordinates(new Vector2()).y));
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        this.f3761a.getTable().setVisible(false);
        Game.i.notifyVisibleDisplayFrameChanged(0, 0, 0, 0);
    }

    public void show() {
        this.f3761a.getTable().setVisible(true);
        a();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }
}

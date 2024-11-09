package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.BaseDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ScreenBorderGradient.class */
public class ScreenBorderGradient implements Disposable {
    private NinePatch c;
    private Image d;
    private Image e;
    private Color f;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3233a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 100, "ScreenBorderGradient", true);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3234b = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 101, "ScreenBorderGradient - full screen", true);
    private Color g = new Color(0.0f, 0.0f, 0.0f, 0.0f);

    public ScreenBorderGradient() {
        AssetManager assetManager = Game.i.assetManager;
        this.f = assetManager.getColor("screen_border_gradient_normal");
        this.c = new NinePatch(assetManager.getTextureRegion("ui-screen-border-0"), assetManager.getTextureRegion("ui-screen-border-1"), assetManager.getTextureRegion("ui-screen-border-2"), assetManager.getTextureRegion("ui-screen-border-3"), null, assetManager.getTextureRegion("ui-screen-border-5"), assetManager.getTextureRegion("ui-screen-border-6"), assetManager.getTextureRegion("ui-screen-border-7"), assetManager.getTextureRegion("ui-screen-border-8"));
        this.d = new Image(new BaseDrawable() { // from class: com.prineside.tdi2.ui.actors.ScreenBorderGradient.1
            @Override // com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
            public void draw(Batch batch, float f, float f2, float f3, float f4) {
                ScreenBorderGradient.this.c.setColor(ScreenBorderGradient.this.d.getColor());
                ScreenBorderGradient.this.c.draw(batch, f, f2, 0.0f, 0.0f, f3, f4, 1.0f, 1.0f, 0.0f);
            }
        });
        this.d.setColor(this.f);
        this.f3233a.getTable().add((Table) this.d).expand().fill();
        this.f3233a.getTable().setTouchable(Touchable.disabled);
        this.e = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME)) { // from class: com.prineside.tdi2.ui.actors.ScreenBorderGradient.2
            @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f) {
                if (ScreenBorderGradient.this.e.getColor().f889a > 0.0f) {
                    batch.flush();
                    batch.setBlendFunction(770, 1);
                    super.draw(batch, f);
                    batch.flush();
                    batch.setBlendFunction(770, 771);
                }
            }
        };
        this.e.setColor(0.0f, 0.0f, 0.0f, 0.0f);
        this.f3234b.getTable().add((Table) this.e).expand().fill();
        this.f3234b.getTable().setTouchable(Touchable.disabled);
    }

    public void flash(Color color, float f) {
        this.d.clearActions();
        this.d.addAction(Actions.sequence(Actions.color(color, f * 0.1f), Actions.color(this.f, f * 0.9f)));
    }

    public void fullscreenFlash(Color color, float f, Interpolation interpolation) {
        this.e.clearActions();
        this.e.addAction(Actions.sequence(Actions.color(color, f * 0.05f), Actions.color(this.g, f * 0.95f, interpolation)));
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3233a);
        Game.i.uiManager.removeLayer(this.f3234b);
    }
}

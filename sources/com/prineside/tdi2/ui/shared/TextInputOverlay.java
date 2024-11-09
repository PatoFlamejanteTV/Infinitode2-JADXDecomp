package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/TextInputOverlay.class */
public class TextInputOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3749a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 20001, "TextInputOverlay main");

    /* renamed from: b, reason: collision with root package name */
    private final Label f3750b;
    private final TextFieldXPlatform c;
    private Input.TextInputListener d;

    public static TextInputOverlay i() {
        return (TextInputOverlay) Game.i.uiManager.getComponent(TextInputOverlay.class);
    }

    public TextInputOverlay() {
        Table table = this.f3749a.getTable();
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.enabled);
        group.addListener(new InputVoid());
        table.add((Table) group).size(800.0f, 200.0f);
        QuadActor quadActor = new QuadActor(new Color(33), new float[]{10.0f, 0.0f, 0.0f, 200.0f, 800.0f, 190.0f, 790.0f, 12.0f});
        quadActor.setSize(800.0f, 200.0f);
        quadActor.setPosition(12.0f, -12.0f);
        group.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(724249599), new float[]{10.0f, 0.0f, 0.0f, 200.0f, 800.0f, 190.0f, 790.0f, 12.0f});
        group.setSize(800.0f, 200.0f);
        group.addActor(quadActor2);
        this.f3750b = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.f3750b.setPosition(40.0f, 150.0f);
        group.addActor(this.f3750b);
        this.c = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        this.c.setSize(720.0f, 64.0f);
        this.c.setPosition(40.0f, 50.0f);
        group.addActor(this.c);
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
            Input.TextInputListener textInputListener = this.d;
            hide();
            if (textInputListener != null) {
                textInputListener.canceled();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }
        });
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 26.0f, 18.0f, 48.0f, 48.0f);
        complexButton.setIconLabelColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
        complexButton.setSize(100.0f, 80.0f);
        complexButton.setPosition(750.0f, 146.0f);
        group.addActor(complexButton);
        ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
            Input.TextInputListener textInputListener = this.d;
            hide();
            if (textInputListener != null) {
                textInputListener.input(this.c.getText());
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }
        });
        complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{2.0f, 3.0f, 0.0f, 77.0f, 120.0f, 80.0f, 118.0f, 0.0f})), 0.0f, 0.0f, 120.0f, 80.0f);
        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-check"), 36.0f, 18.0f, 48.0f, 48.0f);
        complexButton2.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.GRAY);
        complexButton2.setSize(120.0f, 80.0f);
        complexButton2.setPosition(640.0f, -50.0f);
        group.addActor(complexButton2);
        this.f3749a.getTable().setVisible(false);
    }

    private void a() {
        DarkOverlay.i().addCallerOverlayLayer("TextInputOverlay", this.f3749a.zIndex - 1, () -> {
            return false;
        });
        this.f3749a.getTable().setVisible(true);
        Game.i.uiManager.stage.setKeyboardFocus(this.c);
    }

    public void show(Input.TextInputListener textInputListener, String str, String str2, String str3) {
        this.d = textInputListener;
        this.c.setText(str2);
        this.c.setMessageText(str3);
        this.f3750b.setText(str);
        a();
    }

    public boolean isVisible() {
        return this.f3749a.getTable().isVisible();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public void postRender(float f) {
        if (isVisible()) {
            if (Gdx.input.isKeyJustPressed(4) || Gdx.input.isKeyJustPressed(111)) {
                this.d.canceled();
                hide();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            } else if (Gdx.input.isKeyJustPressed(66) || Gdx.input.isKeyJustPressed(160)) {
                Input.TextInputListener textInputListener = this.d;
                hide();
                if (textInputListener != null) {
                    textInputListener.input(this.c.getText());
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        this.f3749a.getTable().setVisible(false);
        DarkOverlay.i().removeCaller("TextInputOverlay");
        this.d = null;
    }
}

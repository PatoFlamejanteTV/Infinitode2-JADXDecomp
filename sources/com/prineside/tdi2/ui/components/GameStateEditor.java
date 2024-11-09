package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GameStateEditor.class */
public class GameStateEditor implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3300a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 104, "GameStateEditor");

    /* renamed from: b, reason: collision with root package name */
    private Label f3301b;
    private TextFieldXPlatform c;
    private GameSystemProvider d;
    private static final StringBuilder e = new StringBuilder();

    public GameStateEditor(GameSystemProvider gameSystemProvider) {
        this.d = gameSystemProvider;
        Group group = new Group();
        group.setTransform(false);
        this.f3300a.getTable().add((Table) group).size(600.0f, 200.0f).padBottom(40.0f).bottom().expandY();
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        image.setSize(600.0f, 200.0f);
        group.addActor(image);
        this.f3301b = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.f3301b.setPosition(20.0f, 160.0f);
        this.f3301b.setSize(100.0f, 20.0f);
        group.addActor(this.f3301b);
        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-pause"), () -> {
            gameSystemProvider.gameState.setGameSpeed(0.0f);
        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
        paddedImageButton.setSize(48.0f, 48.0f);
        paddedImageButton.setIconPosition(8.0f, 8.0f);
        paddedImageButton.setIconSize(32.0f, 32.0f);
        paddedImageButton.setPosition(20.0f, 100.0f);
        group.addActor(paddedImageButton);
        PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right"), () -> {
            GameScreen a2 = a();
            a2.updateSystems();
            a2.updateDraw(gameSystemProvider.gameValue.getTickRateDeltaTime(), gameSystemProvider.gameValue.getTickRateDeltaTime());
        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
        paddedImageButton2.setSize(48.0f, 48.0f);
        paddedImageButton2.setIconPosition(8.0f, 8.0f);
        paddedImageButton2.setIconSize(32.0f, 32.0f);
        paddedImageButton2.setPosition(84.0f, 100.0f);
        group.addActor(paddedImageButton2);
        PaddedImageButton paddedImageButton3 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-double-triangle-right"), () -> {
            GameScreen a2 = a();
            int i = 10;
            try {
                i = Integer.parseInt(this.c.getText());
            } catch (Exception unused) {
            }
            for (int i2 = 0; i2 < i; i2++) {
                a2.updateSystems();
                a2.updateDraw(gameSystemProvider.gameValue.getTickRateDeltaTime(), gameSystemProvider.gameValue.getTickRateDeltaTime());
            }
        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
        paddedImageButton3.setSize(48.0f, 48.0f);
        paddedImageButton3.setIconPosition(8.0f, 8.0f);
        paddedImageButton3.setIconSize(32.0f, 32.0f);
        paddedImageButton3.setPosition(148.0f, 100.0f);
        group.addActor(paddedImageButton3);
        this.c = new TextFieldXPlatform("10", Game.i.assetManager.getTextFieldStyle(24));
        this.c.setSize(96.0f, 48.0f);
        this.c.setPosition(212.0f, 100.0f);
        group.addActor(this.c);
        this.f3300a.getTable().setVisible(false);
    }

    private static GameScreen a() {
        return (GameScreen) Game.i.screenManager.getCurrentScreen();
    }

    public void draw(float f) {
        if (Gdx.input.isKeyJustPressed(140)) {
            this.f3300a.getTable().setVisible(!this.f3300a.getTable().isVisible());
        }
        if (this.f3300a.getTable().isVisible()) {
            e.setLength(0);
            e.append("F: ").append(StringFormatter.commaSeparatedNumber(this.d.gameState.updateNumber)).append("| S: ").append(StringFormatter.compactNumberWithPrecisionTrimZeros(this.d.gameState.getGameSpeed(), 2, true));
            this.f3301b.setText(e);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3300a);
    }
}

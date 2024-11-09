package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.PreRender;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextArea;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/FullScreenTextEditor.class */
public final class FullScreenTextEditor extends UiManager.UiComponent.Adapter {

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3538b;
    private Image e;
    private Label f;
    private TextArea g;
    private ObjectConsumer<String> h;
    private int c = -1;
    private final Listener<PreRender> d = preRender -> {
        a();
    };

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3537a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.OVERLAY, 9002, "FullScreenTextEditor background", true);

    static {
        TLog.forClass(FullScreenTextEditor.class);
    }

    public static FullScreenTextEditor i() {
        return (FullScreenTextEditor) Game.i.uiManager.getComponent(FullScreenTextEditor.class);
    }

    public FullScreenTextEditor() {
        this.f3537a.ignoreVisibleFrame = true;
        this.f3538b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 9003, "FullScreenTextEditor main");
        this.f3538b.followVisibleFrame = true;
        this.f3538b.ignoreVisibleFrame = true;
        Game.i.uiManager.rebuildLayers();
        this.f3537a.getTable().setBackground(new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(572662527)));
        Table table = new Table();
        this.f3538b.getTable().add(table).grow().row();
        Table table2 = new Table();
        table.add(table2).growX().height(56.0f).row();
        FancyButton fancyButton = new FancyButton("regularRedButton.a", this::hide);
        fancyButton.add((FancyButton) new Image(Game.i.assetManager.getDrawable("icon-times"))).size(20.0f).padRight(4.0f);
        fancyButton.add((FancyButton) new Label("Cancel", Game.i.assetManager.getLabelStyle(21)));
        fancyButton.setBackgroundDrawable(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        fancyButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P700, MaterialColor.RED.P900, Color.WHITE);
        table2.add(fancyButton).size(128.0f, 56.0f);
        table2.add().growX();
        FancyButton fancyButton2 = new FancyButton("regularGreenButton.b", () -> {
            this.h.accept(this.g.getText());
            hide();
        });
        fancyButton2.add((FancyButton) new Image(Game.i.assetManager.getDrawable("icon-check"))).size(20.0f).padRight(4.0f);
        fancyButton2.add((FancyButton) new Label("Confirm", Game.i.assetManager.getLabelStyle(21)));
        fancyButton2.setBackgroundDrawable(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        fancyButton2.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900, Color.WHITE);
        table2.add(fancyButton2).size(128.0f, 56.0f);
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(Game.i.assetManager.getDebugFont(true), Color.WHITE, new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()), new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(MaterialColor.BLUE.P800), new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(0.0f, 0.0f, 0.0f, 0.4f)));
        textFieldStyle.cursor.setMinWidth(2.0f);
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth() + 16.0f);
        textFieldStyle.background.setRightWidth(textFieldStyle.background.getRightWidth() + 8.0f);
        textFieldStyle.background.setBottomHeight(textFieldStyle.background.getBottomHeight() + 8.0f);
        textFieldStyle.background.setTopHeight(textFieldStyle.background.getTopHeight() + 8.0f);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.vScroll = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        scrollPaneStyle.vScrollKnob = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(572662527));
        scrollPaneStyle.vScroll.setMinWidth(128.0f);
        scrollPaneStyle.vScrollKnob.setMinWidth(128.0f);
        Table table3 = new Table();
        ScrollPane scrollPane = new ScrollPane(table3, scrollPaneStyle);
        scrollPane.setOverscroll(false, false);
        scrollPane.setFadeScrollBars(false);
        table.add((Table) scrollPane).grow().row();
        this.e = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.14f);
        table3.addActor(this.e);
        this.f = new Label("", Game.i.assetManager.getDebugLabelStyle());
        this.f.setAlignment(16);
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table3.add((Table) this.f).top().width(118.0f).padTop(textFieldStyle.background.getTopHeight()).padRight(10.0f);
        this.g = new TextArea("", textFieldStyle);
        this.g.prefSizeDependsOnContents = true;
        this.g.setFocusTraversal(false);
        this.g.replaceTabsWithSpaces = true;
        this.g.addListener(new InputListener(this) { // from class: com.prineside.tdi2.ui.shared.FullScreenTextEditor.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                inputEvent.stop();
                inputEvent.cancel();
                return true;
            }
        });
        table3.add((Table) this.g).grow();
    }

    private void a() {
        int lines = this.g.getLines();
        if (this.c != lines) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lines; i++) {
                sb.append(i + 1).append(SequenceUtils.EOL);
            }
            this.f.setText(sb);
            this.c = lines;
        }
        Rectangle rectangle = new Rectangle();
        this.g.getSelectionBoundingBox(rectangle);
        this.e.setX(0.0f);
        this.e.setY(rectangle.y - 1.0f);
        this.e.setHeight(rectangle.height);
        this.e.setWidth(Game.i.uiManager.stage.getWidth());
    }

    public final void show(String str, ObjectConsumer<String> objectConsumer) {
        Preconditions.checkNotNull(objectConsumer, "onConfirm can not be null");
        this.h = objectConsumer;
        this.f3537a.getTable().setVisible(true);
        this.f3538b.getTable().setVisible(true);
        this.g.setText(str);
        Game.EVENTS.getListeners(PreRender.class).add(this.d);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        this.f3537a.getTable().setVisible(false);
        this.f3538b.getTable().setVisible(false);
        Game.EVENTS.getListeners(PreRender.class).remove(this.d);
    }
}

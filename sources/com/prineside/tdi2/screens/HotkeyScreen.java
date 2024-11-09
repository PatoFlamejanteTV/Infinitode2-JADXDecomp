package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.ForwardButton;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.UiUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/HotkeyScreen.class */
public class HotkeyScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2756a;
    private Table f;
    private Table g;
    private Label h;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2757b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "HotkeyScreen main");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "HotkeyScreen gradient");
    private final UiManager.UiLayer d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "HotkeyScreen hotkey tint");
    private final UiManager.UiLayer e = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 103, "HotkeyScreen hotkey");
    private IntArray i = new IntArray();
    private SettingsManager.HotkeyAction j = null;

    static {
        int[] iArr = {129, 130, 59, 60, 57, 58};
        f2756a = iArr;
        Arrays.sort(iArr);
    }

    public HotkeyScreen() {
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-wrench")).setText(Game.i.localeManager.i18n.get("hotkey_editor_title")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(this::a);
        this.f = new Table();
        ScrollPane scrollPane = new ScrollPane(this.f, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setScrollingDisabled(true, false);
        this.f2757b.getTable().add((Table) scrollPane).expand().fill();
        Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image.setColor(Config.BACKGROUND_COLOR);
        this.c.getTable().setTouchable(Touchable.disabled);
        this.c.getTable().add((Table) image).expandX().fillX().height(128.0f).row();
        this.c.getTable().add().expand().fill().row();
        this.f.add().height(128.0f).fillX().expandX().row();
        this.g = new Table();
        this.f.add(this.g).expandX().fillX();
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(Config.BACKGROUND_COLOR);
        image2.getColor().f889a = 0.78f;
        this.d.getTable().add((Table) image2).expand().fill();
        this.d.getTable().setTouchable(Touchable.enabled);
        this.d.getTable().addListener(new InputVoid());
        this.d.getTable().setVisible(false);
        this.h = new Label("ABC", Game.i.assetManager.getLabelStyle(36));
        this.e.getTable().add((Table) this.h);
        this.e.getTable().setVisible(false);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IntArray intArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < intArray.size; i++) {
            if (Arrays.binarySearch(f2756a, intArray.items[i]) >= 0) {
                if (stringBuilder.length != 0) {
                    stringBuilder.append(" + ");
                }
                String keys = Input.Keys.toString(intArray.items[i]);
                String str = keys;
                if (keys.startsWith("L-")) {
                    str = str.substring(2);
                }
                stringBuilder.append(str);
            }
        }
        for (int i2 = 0; i2 < intArray.size; i2++) {
            if (Arrays.binarySearch(f2756a, intArray.items[i2]) < 0) {
                if (stringBuilder.length != 0) {
                    stringBuilder.append(" + ");
                }
                stringBuilder.append(Input.Keys.toString(intArray.items[i2]));
            }
        }
        this.h.setText(stringBuilder);
    }

    private void a() {
        if (this.j == null) {
            Game.i.screenManager.goToSettingsScreen();
        } else {
            a(false);
        }
    }

    private void a(SettingsManager.HotkeyAction hotkeyAction) {
        this.i.clear();
        a(new IntArray(Game.i.settingsManager.getHotKey(hotkeyAction)));
        this.d.getTable().setVisible(true);
        this.e.getTable().setVisible(true);
        this.j = hotkeyAction;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new InputAdapter() { // from class: com.prineside.tdi2.screens.HotkeyScreen.1

            /* renamed from: a, reason: collision with root package name */
            private IntArray f2758a = new IntArray();

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean keyDown(int i) {
                if (i == 4 || i == 111) {
                    return false;
                }
                if (!this.f2758a.contains(i)) {
                    this.f2758a.add(i);
                }
                HotkeyScreen.this.i.clear();
                HotkeyScreen.this.i.addAll(this.f2758a);
                HotkeyScreen.this.a(this.f2758a);
                return true;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean keyUp(int i) {
                this.f2758a.removeValue(i);
                HotkeyScreen.this.a(HotkeyScreen.this.i);
                return false;
            }
        });
        inputMultiplexer.addProcessor(Game.i.uiManager.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        ForwardButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            a(true);
        });
    }

    private void a(boolean z) {
        if (z && this.i.size != 0) {
            Game.i.settingsManager.setHotKey(this.j, this.i.toArray());
        }
        this.d.getTable().setVisible(false);
        this.e.getTable().setVisible(false);
        this.j = null;
        Game.i.uiManager.setAsInputHandler();
        ForwardButton.i().setVisible(false);
        b();
    }

    private void b() {
        this.g.clear();
        int i = 0;
        for (SettingsManager.HotkeyAction hotkeyAction : SettingsManager.HotkeyAction.values) {
            String hotkeyGroupTitle = Game.i.settingsManager.getHotkeyGroupTitle(hotkeyAction);
            if (hotkeyGroupTitle != null) {
                Label label = new Label(hotkeyGroupTitle, Game.i.assetManager.getLabelStyle(30));
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.g.add((Table) label).height(64.0f).padTop(16.0f).row();
            }
            Group group = new Group();
            group.setTransform(false);
            group.setSize(800.0f, 48.0f);
            this.g.add((Table) group).padBottom(4.0f).row();
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setSize(800.0f, 48.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.21f);
            group.addActor(image);
            Label label2 = new Label(Game.i.settingsManager.getHotKeyName(hotkeyAction), Game.i.assetManager.getLabelStyle(24));
            label2.setPosition(10.0f, 0.0f);
            label2.setSize(200.0f, 48.0f);
            group.addActor(label2);
            StringBuilder stringBuilder = new StringBuilder();
            int[] hotKey = Game.i.settingsManager.getHotKey(hotkeyAction);
            for (int i2 = 0; i2 < hotKey.length; i2++) {
                if (i2 != 0) {
                    stringBuilder.append(" + ");
                }
                String keys = Input.Keys.toString(hotKey[i2]);
                String str = keys;
                if (keys.startsWith("L-")) {
                    str = str.substring(2);
                }
                stringBuilder.append(str);
            }
            FancyButton withLabel = new FancyButton(i % 2 == 0 ? "regularButton.a" : "regularButton.b", () -> {
                a(hotkeyAction);
            }).withLabel(24, stringBuilder);
            withLabel.setSize(192.0f, 48.0f);
            withLabel.setPosition(412.0f, 0.0f);
            group.addActor(withLabel);
            FancyButton withLabel2 = new FancyButton(i % 2 == 0 ? "regularButton.b" : "regularButton.a", () -> {
                Game.i.settingsManager.setHotKey(hotkeyAction, Game.i.settingsManager.getDefaultHotKey(hotkeyAction));
                b();
            }).withLabel(24, Game.i.localeManager.i18n.get("reset"));
            withLabel2.setSize(192.0f, 48.0f);
            withLabel2.setPosition(608.0f, 0.0f);
            group.addActor(withLabel2);
            if (Game.i.settingsManager.isDefaultHotKey(hotkeyAction)) {
                withLabel2.setEnabled(false);
            }
            i++;
        }
        this.g.add().height(128.0f).width(1.0f).row();
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            a();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f2757b);
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.e);
        Game.i.uiManager.removeLayer(this.d);
    }
}

package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/AboutScreen.class */
public class AboutScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f2739a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "AboutScreen main");

    public AboutScreen() {
        long realTickCount = Game.getRealTickCount();
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            Game.i.screenManager.goToMainMenu();
        });
        Table table = this.f2739a.getTable();
        Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(30);
        Label.LabelStyle labelStyle2 = new Label.LabelStyle(Game.i.assetManager.getLabelStyle(24));
        labelStyle2.fontColor = MaterialColor.AMBER.P500;
        table.add((Table) new Image(Game.i.assetManager.getDrawable("infinitode-2-logo"))).size(128.0f).padBottom(20.0f);
        table.row();
        table.add((Table) new Label(Game.i.localeManager.i18n.get("game_name"), labelStyle));
        table.row();
        table.add((Table) new Label(Game.i.localeManager.i18n.get("about_version"), labelStyle2)).height(40.0f).padTop(16.0f);
        table.row();
        table.add((Table) new Label("R.1.9.2 (build 208)", labelStyle)).height(40.0f);
        table.row();
        Table table2 = new Table();
        table2.add(new FancyButton("regularButton.a", () -> {
            Game.i.actionResolver.openSupportPage();
        }).withLabel(30, Game.i.localeManager.i18n.get("get_support"))).size(320.0f, 64.0f);
        table2.add(new FancyButton("regularButton.b", () -> {
            Gdx.f881net.openURI("https://tracker.prineside.com/set_project.php?project_id=1");
        }).withLabel(30, Game.i.localeManager.i18n.get("report_bug"))).size(320.0f, 64.0f).padLeft(16.0f);
        table2.add(new FancyButton("regularButton.a", () -> {
            Gdx.f881net.openURI("https://tracker.prineside.com/set_project.php?project_id=2");
        }).withLabel(30, Game.i.localeManager.i18n.get("i_have_idea"))).size(320.0f, 64.0f).padLeft(16.0f);
        table.add(table2).padTop(16.0f).row();
        Table table3 = new Table();
        table.add(table3).padTop(16.0f).row();
        Table table4 = new Table();
        table3.add(table4).width(500.0f);
        table4.add((Table) new Label(Game.i.localeManager.i18n.get("about_developer"), labelStyle2)).height(40.0f).padTop(16.0f);
        table4.row();
        table4.add((Table) new Label("Vadym Sakhno (therainycat)", labelStyle)).height(40.0f);
        table4.row();
        Label label = new Label("web.prineside@gmail.com", Game.i.assetManager.getLabelStyle(30));
        label.setColor(MaterialColor.LIGHT_BLUE.P500);
        label.setTouchable(Touchable.enabled);
        label.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AboutScreen.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.app.getClipboard().setContents("web.prineside@gmail.com");
                Notifications.i().add(Game.i.localeManager.i18n.get("copied_to_clipboard"), Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, StaticSoundType.NOTIFICATION);
            }
        });
        table4.add((Table) label).height(40.0f);
        Table table5 = new Table();
        table3.add(table5).width(500.0f).row();
        table5.add((Table) new Label(Game.i.localeManager.i18n.get("about_3d_artist"), labelStyle2)).height(40.0f).padTop(16.0f);
        table5.row();
        table5.add((Table) new Label("Ihor Pronoza (SlyCheD)", labelStyle)).height(40.0f);
        table5.row();
        Label label2 = new Label("ihor.pronoza@gmail.com", Game.i.assetManager.getLabelStyle(30));
        label2.setColor(MaterialColor.LIGHT_BLUE.P500);
        label2.setTouchable(Touchable.enabled);
        label2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AboutScreen.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.app.getClipboard().setContents("ihor.pronoza@gmail.com");
                Notifications.i().add(Game.i.localeManager.i18n.get("copied_to_clipboard"), Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, StaticSoundType.NOTIFICATION);
            }
        });
        table5.add((Table) label2).height(40.0f);
        Table table6 = new Table();
        table3.add(table6).colspan(2).row();
        table6.add((Table) new Label(Game.i.localeManager.i18n.get("about_community_manager"), labelStyle2)).height(40.0f).padTop(16.0f);
        table6.row();
        table6.add((Table) new Label("Alexander Susla (MarshallUA)", labelStyle)).height(40.0f);
        table6.row();
        table6.add((Table) new Label(Game.i.localeManager.i18n.get("about_special_thanks"), labelStyle2)).height(40.0f).padTop(16.0f);
        table6.row();
        table6.add((Table) new Label("Steven McManus (Zeraco)", labelStyle)).height(40.0f);
        table6.row();
        Label label3 = new Label("infinitode.prineside.com", Game.i.assetManager.getLabelStyle(30));
        label3.setColor(MaterialColor.LIGHT_BLUE.P500);
        label3.setTouchable(Touchable.enabled);
        label3.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AboutScreen.3
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.f881net.openURI("https://infinitode.prineside.com");
            }
        });
        table.add((Table) label3).height(40.0f).padTop(16.0f);
        table.row();
        LabelButton labelButton = new LabelButton(Game.i.localeManager.i18n.get("privacy_policy"), Game.i.assetManager.getLabelStyle(30), () -> {
            Gdx.f881net.openURI(Config.PRIVACY_POLICY_URL);
        });
        labelButton.setAlignment(1);
        table.add((Table) labelButton).padTop(12.0f).padBottom(8.0f).row();
        LabelButton labelButton2 = new LabelButton(Game.i.localeManager.i18n.get("terms_and_conditions"), Game.i.assetManager.getLabelStyle(30), () -> {
            Gdx.f881net.openURI(Config.TERMS_AND_CONDITIONS_URL);
        });
        labelButton2.setAlignment(1);
        table.add((Table) labelButton2).padTop(8.0f).padBottom(12.0f).row();
        Table table7 = new Table();
        table.add(table7).padTop(15.0f);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-colored-reddit"));
        image.setTouchable(Touchable.enabled);
        image.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AboutScreen.4
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.f881net.openURI("https://www.reddit.com/r/infinitode/");
            }
        });
        table7.add((Table) image).size(64.0f).padRight(32.0f);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-colored-discord"));
        image2.setTouchable(Touchable.enabled);
        image2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AboutScreen.5
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.f881net.openURI("https://discord.gg/4kZ2TJ4");
            }
        });
        table7.add((Table) image2).size(64.0f);
        if (Game.i.debugManager != null) {
            Game.i.debugManager.registerFrameJob("ConstructScreen-" + getClass().getSimpleName(), Game.getRealTickCount() - realTickCount);
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        this.f2739a.getTable().setVisible(true);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void hide() {
        this.f2739a.getTable().setVisible(false);
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f2739a);
    }
}

package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.LocaleManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LanguageSelectScreen.class */
public class LanguageSelectScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2760a = TLog.forClass(LanguageSelectScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2761b;
    private final UiManager.UiLayer c;

    public LanguageSelectScreen() {
        this(false);
    }

    public LanguageSelectScreen(boolean z) {
        this.f2761b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "LanguageSelectScreen main");
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 103, "MainMenuScreen launchFade");
        a();
        this.c.getTable().setTouchable(Touchable.disabled);
        if (z) {
            this.c.getTable().setVisible(true);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(Config.BACKGROUND_COLOR);
            this.c.getTable().add((Table) image).grow();
            this.c.getTable().addAction(Actions.fadeOut(0.8f));
            return;
        }
        this.c.getTable().setVisible(false);
    }

    private void a() {
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setText("Language").setIcon(Game.i.assetManager.getDrawable("icon-locale")).setVisible(true);
        Table table = this.f2761b.getTable();
        table.clear();
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE);
        Label.LabelStyle labelStyle2 = new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE);
        labelStyle2.background = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_GREEN.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.07f));
        int i = 0;
        int i2 = 1;
        int i3 = Game.i.localeManager.getAvailableLocales().size;
        if (i3 > 10) {
            i2 = 3;
        } else if (i3 > 5) {
            i2 = 2;
        }
        Table table2 = new Table();
        table.add(table2).colspan(i2).padBottom(32.0f).row();
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        table.add((Table) image).height(1.0f).fillX().colspan(i2).row();
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(1.0f, 1.0f, 1.0f, 0.14f);
        table.add((Table) image2).height(1.0f).fillX().colspan(i2).padBottom(32.0f).row();
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-note"));
        image3.setColor(MaterialColor.LIGHT_BLUE.P400);
        table2.add((Table) image3).size(40.0f).padRight(12.0f);
        table2.add(new LabelToggleButton("Music", Game.i.settingsManager.isMusicEnabled(), 30, 32.0f, bool -> {
            Game.i.settingsManager.setMusicVolume(bool.booleanValue() ? 0.699999988079071d : 0.0d);
        })).height(32.0f);
        Image image4 = new Image(Game.i.assetManager.getDrawable("icon-speaker"));
        image4.setColor(MaterialColor.LIGHT_BLUE.P400);
        table2.add((Table) image4).size(40.0f).padRight(12.0f).padLeft(92.0f);
        table2.add(new LabelToggleButton("Sounds", Game.i.settingsManager.isSoundEnabled(), 30, 32.0f, bool2 -> {
            Game.i.settingsManager.setSoundVoulme(bool2.booleanValue() ? 0.699999988079071d : 0.0d);
        })).height(32.0f);
        Image image5 = new Image(Game.i.assetManager.getDrawable("icon-large-fonts"));
        image5.setColor(MaterialColor.LIGHT_BLUE.P400);
        table2.add((Table) image5).size(40.0f).padRight(12.0f).padLeft(92.0f);
        table2.add(new LabelToggleButton("Large fonts", Game.i.settingsManager.isLargeFontsEnabled(), 30, 32.0f, bool3 -> {
            Game.i.settingsManager.setLargeFontsEnabled(bool3.booleanValue());
            a();
        })).height(32.0f);
        String str = null;
        try {
            f2760a.i("device locale " + Game.i.actionResolver.getDefaultLocale(), new Object[0]);
            String[] split = Game.i.actionResolver.getDefaultLocale().split(JavaConstant.Dynamic.DEFAULT_NAME);
            if (split.length == 1 && split[0].length() == 2) {
                str = split[0].toLowerCase(Locale.US);
            } else if (split.length > 1) {
                if (split[0].length() == 2) {
                    str = split[0].toLowerCase(Locale.US);
                } else if (split[1].length() == 2) {
                    str = split[1].toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
            f2760a.i("failed to get default language", e);
        }
        f2760a.i("highlighting locale '" + str + "'", new Object[0]);
        Array.ArrayIterator<LocaleManager.Locale> it = Game.i.localeManager.getAvailableLocales().iterator();
        while (it.hasNext()) {
            final LocaleManager.Locale next = it.next();
            final Label label = new Label(next.name, labelStyle);
            label.setAlignment(1);
            table.add((Table) label).size(460.0f, 120.0f);
            if (str != null && next.alias.startsWith(str)) {
                label.setStyle(labelStyle2);
            }
            label.addListener(new InputListener(this) { // from class: com.prineside.tdi2.screens.LanguageSelectScreen.1
                @Override // com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i4, Actor actor) {
                    label.setColor(MaterialColor.LIGHT_BLUE.P500);
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i4, Actor actor) {
                    label.setColor(Color.WHITE);
                }
            });
            label.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.LanguageSelectScreen.2
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    Game.i.localeManager.setLocale(next.alias, true);
                    LanguageSelectScreen.this.f2761b.getTable().setTouchable(Touchable.disabled);
                    LanguageSelectScreen.this.c.getTable().clear();
                    LanguageSelectScreen.this.c.getTable().setVisible(true);
                    Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image6.setColor(Config.BACKGROUND_COLOR);
                    LanguageSelectScreen.this.c.getTable().add((Table) image6).grow();
                    LanguageSelectScreen.this.c.getTable().getColor().f889a = 0.0f;
                    LanguageSelectScreen.this.c.getTable().addAction(Actions.sequence(Actions.fadeIn(0.5f), Actions.run(() -> {
                        Game.i.screenManager.goToMainMenuJustLaunched(true);
                    })));
                }
            });
            i++;
            if (i % i2 == 0) {
                table.row();
            }
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f2761b);
        Game.i.uiManager.removeLayer(this.c);
    }
}

package com.prineside.tdi2.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.ActionResolver;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.ibxm.IBXM;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.HttpManager;
import com.prineside.tdi2.managers.LocaleManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.music.LiveMusicManager;
import com.prineside.tdi2.managers.preferences.LegacyPreferences;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.screens.account.AccountScreen;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.HorizontalSlider;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.DeveloperConsole;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.MainMenuUiScene;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.ui.shared.WebBrowser;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.FileIntegrityChecker;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.nio.IntBuffer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.JavaConstant;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen.class */
public class SettingsScreen extends Screen {
    private final UiManager.UiLayer c;
    private final UiManager.UiLayer d;
    private final UiManager.UiLayer e;
    private Table g;
    private int h;
    private int i;
    private ScrollPane j;
    private LabelToggleButton k;
    private LabelToggleButton l;
    private LabelToggleButton m;
    private SelectBox<String> n;
    private HorizontalSlider o;
    private HorizontalSlider p;
    private HorizontalSlider q;
    private HorizontalSlider r;
    private SelectBox<String> s;
    private Label t;
    private FancyButton u;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2820a = TLog.forClass(SettingsScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private static boolean f2821b = false;
    private static final int[] f = {0, 24, 30, 45, 48, 60, 72, 90, 100, 120, 128, 140, 144, User32.VK_OEM_ATTN};

    public SettingsScreen() {
        this(0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreen(float f2) {
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "SettingsScreen main");
        this.d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "SettingsScreen gradient");
        this.e = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 102, "SettingsScreen safezone hint", true);
        final ActionResolver.InitConfigManager initConfigManager = Game.i.actionResolver.getInitConfigManager();
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-wrench")).setText(Game.i.localeManager.i18n.get("settings_title")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            Game.i.screenManager.goToMainMenu();
        });
        this.g = new Table();
        this.j = new ScrollPane(this.g, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(this.j);
        this.j.setScrollingDisabled(true, false);
        this.c.getTable().add((Table) this.j).expand().fill();
        Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image.setColor(Config.BACKGROUND_COLOR);
        this.d.getTable().setTouchable(Touchable.disabled);
        this.d.getTable().add((Table) image).expandX().fillX().height(128.0f).row();
        this.d.getTable().add().expand().fill().row();
        this.g.add().height(128.0f).fillX().expandX().row();
        Table a2 = a(Game.i.localeManager.i18n.get("settings_language"), "icon-locale");
        Table table = new Table();
        a2.add(table).expandX().fillX().row();
        int i = 0;
        Array.ArrayIterator<LocaleManager.Locale> it = Game.i.localeManager.getAvailableLocales().iterator();
        while (it.hasNext()) {
            LocaleManager.Locale next = it.next();
            LocaleButton localeButton = new LocaleButton(next.name, () -> {
                a(next.alias);
            });
            localeButton.setSelected(next.alias.equals(Game.i.localeManager.i18n.getLocale().getLanguage() + JavaConstant.Dynamic.DEFAULT_NAME + Game.i.localeManager.i18n.getLocale().getCountry()));
            Cell width = table.add(localeButton.f2829b).height(64.0f).width(340.0f);
            i++;
            if (i == 4) {
                width.row();
                i = 0;
            }
        }
        final Table table2 = new Table();
        Cell padTop = a2.add(table2).padLeft(64.0f).padRight(-64.0f).expandX().fillX().padTop(16.0f);
        a2.row();
        if (!Game.i.localeManager.getLocale().equals("en_US")) {
            padTop.minHeight(160.0f);
            Label label = new Label(Game.i.localeManager.i18n.get("loading..."), Game.i.assetManager.getLabelStyle(24));
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table2.add((Table) label);
            Game.i.httpManager.post(Config.GET_COMMUNITY_TRANSLATORS_URL).param("locale", Game.i.localeManager.getLocale()).listener(new HttpManager.RequestListener(this) { // from class: com.prineside.tdi2.screens.SettingsScreen.1
                @Override // com.prineside.tdi2.managers.HttpManager.RequestListener
                public void onFinish(boolean z, Net.HttpResponse httpResponse, boolean z2, Throwable th) {
                    String resultAsString = httpResponse.getResultAsString();
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    Threads i2 = Threads.i();
                    Table table3 = table2;
                    i2.runOnMainThread(() -> {
                        try {
                            Table table4 = new Table();
                            int i3 = 0;
                            Iterator<JsonValue> iterator2 = parse.get("users").iterator2();
                            while (iterator2.hasNext()) {
                                JsonValue next2 = iterator2.next();
                                final String string = next2.getString("nickname");
                                if ((!string.toLowerCase(Locale.US).equals("therainycat") && !string.toLowerCase(Locale.US).equals("marshallua")) || Game.i.localeManager.getLocale().equals("uk_UA") || Game.i.localeManager.getLocale().equals("ru_RU")) {
                                    Table table5 = new Table();
                                    table4.add(table5).width(340.0f);
                                    i3++;
                                    if (i3 == 4) {
                                        table4.row();
                                        i3 = 0;
                                    }
                                    Image image2 = new Image(Game.i.assetManager.getTextureRegion("icon-user"));
                                    if (next2.getInt("has_avatar") == 1) {
                                        image2.setDrawable(new TextureRegionDrawable(Game.i.assetManager.loadWebTexture(Game.i.authManager.getAvatarWebUrl(next2.getString(Attribute.ID_ATTR), 32))));
                                    }
                                    table5.add((Table) image2).size(24.0f).padRight(8.0f);
                                    final LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(string, 24, 18, 308.0f);
                                    limitedWidthLabel.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                    table5.add((Table) limitedWidthLabel).width(308.0f);
                                    table5.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.SettingsScreen.1.1
                                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                        public void clicked(InputEvent inputEvent, float f3, float f4) {
                                            WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_PLAYER_PROFILE_BY_NICKNAME_URL + string, null);
                                            WebBrowser.i().setVisible(true);
                                        }

                                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                                        public void enter(InputEvent inputEvent, float f3, float f4, int i4, @Null Actor actor) {
                                            limitedWidthLabel.setColor(Color.WHITE);
                                        }

                                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                                        public void exit(InputEvent inputEvent, float f3, float f4, int i4, @Null Actor actor) {
                                            limitedWidthLabel.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                        }
                                    });
                                }
                            }
                            table3.clear();
                            Label label2 = new Label(Game.i.localeManager.i18n.get("settings_community_translators_title"), Game.i.assetManager.getLabelStyle(24));
                            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                            label2.setWrap(true);
                            table3.add((Table) label2).growX().row();
                            table3.add(table4).growX().row();
                        } catch (Throwable th2) {
                            SettingsScreen.f2820a.e("Failed to list translators, server response: %s", resultAsString, th2);
                        }
                    });
                }
            }).send();
        }
        LabelButton labelButton = new LabelButton(Game.i.localeManager.i18n.get("settings_fix_translation") + SequenceUtils.SPACE + ((Object) Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-link-out>")), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.I18N_SUGGEST_FIX_URL);
        });
        labelButton.setColors(MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P300);
        a2.add((Table) labelButton).left().height(64.0f).padTop(16.0f).padLeft(64.0f).row();
        Table a3 = a(Game.i.localeManager.i18n.get("settings_audio"), "icon-note");
        a3.add((Table) new Label(Game.i.localeManager.i18n.get("settings_sound"), Game.i.assetManager.getLabelStyle(30))).padLeft(64.0f).size(616.0f, 64.0f);
        a3.add((Table) new Label(Game.i.localeManager.i18n.get("settings_music"), Game.i.assetManager.getLabelStyle(30))).padLeft(64.0f).size(616.0f, 64.0f).row();
        Table table3 = new Table();
        a3.add(table3).padLeft(64.0f).padBottom(16.0f).size(616.0f, 48.0f);
        Label label2 = new Label(StrictMath.round(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SOUND_VOLUME) * 100.0d) + "%", Game.i.assetManager.getLabelStyle(24));
        HorizontalSlider horizontalSlider = new HorizontalSlider(400.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SOUND_VOLUME), 0.0d, 1.0d, d -> {
            Game.i.settingsManager.setSoundVoulme(d.doubleValue());
            label2.setText(StrictMath.round(d.doubleValue() * 100.0d) + "%");
        });
        horizontalSlider.setNotifyOnDrag(true);
        table3.add((Table) horizontalSlider).size(400.0f, 48.0f);
        table3.add((Table) label2).padLeft(16.0f);
        table3.add().height(1.0f).expandX().fillX();
        Table table4 = new Table();
        a3.add(table4).padLeft(64.0f).padBottom(16.0f).size(616.0f, 48.0f).row();
        Label label3 = new Label(StrictMath.round(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME) * 100.0d) + "%", Game.i.assetManager.getLabelStyle(24));
        HorizontalSlider horizontalSlider2 = new HorizontalSlider(400.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME), 0.0d, 1.0d, d2 -> {
            double customValue = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME);
            Game.i.settingsManager.setMusicVolume(d2.doubleValue());
            if (customValue > 0.0d && d2.doubleValue() <= 0.0d) {
                Game.i.musicManager.stopMusic();
            } else if (customValue <= 0.0d && d2.doubleValue() > 0.0d) {
                Game.i.musicManager.continuePlayingMenuMusicTrack();
            }
            label3.setText(StrictMath.round(d2.doubleValue() * 100.0d) + "%");
        });
        horizontalSlider2.setNotifyOnDrag(true);
        table4.add((Table) horizontalSlider2).size(400.0f, 48.0f);
        table4.add((Table) label3).padLeft(16.0f);
        table4.add().height(1.0f).expandX().fillX();
        a3.add((Table) new Label(Game.i.localeManager.i18n.get("settings_shooting_sound"), Game.i.assetManager.getLabelStyle(30))).padLeft(64.0f).size(616.0f, 64.0f).row();
        Table table5 = new Table();
        a3.add(table5).padLeft(64.0f).padBottom(16.0f).size(616.0f, 48.0f).row();
        Label label4 = new Label(StrictMath.round(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME) * 100.0d) + "%", Game.i.assetManager.getLabelStyle(24));
        HorizontalSlider horizontalSlider3 = new HorizontalSlider(400.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME), 0.0d, 1.0d, d3 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME, d3.doubleValue());
            label4.setText(StrictMath.round(d3.doubleValue() * 100.0d) + "%");
        });
        horizontalSlider3.setNotifyOnDrag(true);
        table5.add((Table) horizontalSlider3).size(400.0f, 48.0f);
        table5.add((Table) label4).padLeft(16.0f);
        table5.add().height(1.0f).expandX().fillX();
        a3.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_smooth_music"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SMOOTH_MUSIC) != 0.0d, 30, 40.0f, true, bool -> {
            IBXM ibxm;
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.SMOOTH_MUSIC, bool.booleanValue() ? 1.0d : 0.0d);
            if ((Game.i.musicManager instanceof LiveMusicManager) && (ibxm = ((LiveMusicManager) Game.i.musicManager).ibxm) != null) {
                ibxm.setInterpolation(Game.i.musicManager.getInterpolation());
            }
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        if (Game.i.settingsManager.isSecretCodesEnabled()) {
            Table a4 = a(Game.i.localeManager.i18n.get("settings_special"), "icon-star");
            a3 = a4;
            a4.add((Table) new LabelButton(Game.i.localeManager.i18n.get("settings_secret_code"), Game.i.assetManager.getLabelStyle(30), () -> {
                Game.i.uiManager.getTextInput(new Input.TextInputListener(this) { // from class: com.prineside.tdi2.screens.SettingsScreen.2
                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void input(String str) {
                        Threads.i().runOnMainThread(() -> {
                            Game.i.secretCodeManager.applyCode(str);
                        });
                    }

                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void canceled() {
                    }
                }, Game.i.localeManager.i18n.get("secret_code"), "", "");
            })).height(64.0f).padLeft(64.0f).width(616.0f);
        }
        a3.add((Table) new LabelButton(Game.i.localeManager.i18n.get("settings_restore_purchases"), Game.i.assetManager.getLabelStyle(30), () -> {
            if (Game.i.purchaseManager.isPurchasesEnabled()) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("settings_restore_purchases") + TypeDescription.Generic.OfWildcardType.SYMBOL, () -> {
                    Game.i.purchaseManager.purchaseManager.purchaseRestore();
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("settings_purchases_restored"));
                });
            }
        })).height(64.0f).padLeft(64.0f).width(616.0f).row();
        if (Game.i.progressManager.hasPermanentDoubleGain() || Game.i.progressManager.hasTemporaryDoubleGain()) {
            Table table6 = new Table();
            a3.add(table6).padLeft(64.0f).width(616.0f);
            table6.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_enable_double_gain"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DOUBLE_GAIN_DISABLED_MANUALLY) == 0.0d, 30, 40.0f, true, bool2 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DOUBLE_GAIN_DISABLED_MANUALLY, bool2.booleanValue() ? 0.0d : 1.0d);
            })).height(64.0f).growX().row();
            Label label5 = new Label(Game.i.localeManager.i18n.get("settings_enable_double_gain_hint"), Game.i.assetManager.getLabelStyle(24));
            label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label5.setWrap(true);
            table6.add((Table) label5).padTop(8.0f).padBottom(16.0f).fillX().row();
        }
        if (Game.i.authManager.isProfileStatusActive(Config.PROFILE_STATUS_PREMIUM)) {
            Table table7 = new Table();
            a3.add(table7).padLeft(64.0f).width(616.0f);
            table7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_enable_premium_status"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PREMIUM_STATUS_DISABLED_MANUALLY) == 0.0d, 30, 40.0f, true, bool3 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.PREMIUM_STATUS_DISABLED_MANUALLY, bool3.booleanValue() ? 0.0d : 1.0d);
            })).height(64.0f).growX().row();
            Label label6 = new Label(Game.i.localeManager.i18n.get("settings_enable_premium_status_hint"), Game.i.assetManager.getLabelStyle(24));
            label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label6.setWrap(true);
            table7.add((Table) label6).padTop(8.0f).padBottom(16.0f).fillX().row();
        }
        a3.row();
        if (Game.i.authManager.isProfileStatusActive(Config.PROFILE_STATUS_PREMIUM) || Game.i.progressManager.hasPermanentDoubleGain() || Game.i.progressManager.hasTemporaryDoubleGain()) {
            Label label7 = new Label(Game.i.localeManager.i18n.get("settings_enable_account_status_disclaimer"), Game.i.assetManager.getLabelStyle(24));
            label7.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label7.setWrap(true);
            a3.add((Table) label7).padLeft(64.0f).colspan(2).fillX();
        }
        a3.row();
        if (Game.i.actionResolver.personalizedAdsSupported()) {
            a3.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_personalized_ads"), Game.i.actionResolver.personalizedAdsEnabled(), 30, 40.0f, true, bool4 -> {
                Game.i.actionResolver.setPersonalizedAds(bool4.booleanValue());
            })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        }
        Table a5 = a(Game.i.localeManager.i18n.get("settings_gameplay"), "icon-joystick");
        if (Game.getTimestampSeconds() < 1723485232 && !f2821b && Game.i.preferencesManager.getLegacyPreferences().has1dot8prefs()) {
            Table table8 = new Table();
            table8.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.PURPLE.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.07f)));
            a5.add(table8).padBottom(4.0f).colspan(2).fillX().row();
            Label label8 = new Label(Game.i.localeManager.i18n.get("old_1_dot_8_prefs_found_manual_migration_hint"), Game.i.assetManager.getLabelStyle(21));
            label8.setWrap(true);
            label8.setColor(MaterialColor.PURPLE.P300);
            label8.setAlignment(1);
            table8.add((Table) label8).growX().padLeft(20.0f).padRight(20.0f).padTop(20.0f).row();
            FancyButton fancyButton = new FancyButton("regularGreenButton.a", () -> {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("1_dot_8_manual_migration_confirm"), () -> {
                    try {
                        f2820a.i("manual progress migration triggered", new Object[0]);
                        Array<ObjectPair<String, HashMap<String, String>>> locallyStoredPrefs = Game.i.preferencesManager.getLegacyPreferences().getLocallyStoredPrefs();
                        f2820a.i("found " + locallyStoredPrefs.size + " legacy preference categories in local storage, migrating them...", new Object[0]);
                        Game.i.preferencesManager.fromLegacy(locallyStoredPrefs, true);
                        f2820a.i("successfully migrated legacy local preferences, adding a migration tag", new Object[0]);
                        LegacyPreferences.SafePreferences propertiesInstance = Game.i.preferencesManager.getLegacyPreferences().getPropertiesInstance(Config.LEGACY_PREFERENCES_NAME_PROGRESS);
                        propertiesInstance.set("_migrated_1_9_0", "true");
                        propertiesInstance.flush();
                        Notifications.i().addSuccess("Progress has been migrated from 1.8.* to 1.9.0!");
                        f2821b = true;
                        Game.i.screenManager.goToMainMenu();
                    } catch (Exception e) {
                        f2820a.e("failed to migrate legacy preferences", e);
                    }
                });
            });
            fancyButton.add((FancyButton) new Label(Game.i.localeManager.i18n.get("migrate_manually"), Game.i.assetManager.getLabelStyle(24)));
            table8.add(fancyButton).width(400.0f).height(48.0f).padTop(16.0f).padBottom(8.0f).row();
            StringBuilder stringBuilder = new StringBuilder();
            FileHandle local = Gdx.files.local(PreferencesManager.getSettingsPrefsFilePath());
            FileHandle local2 = Gdx.files.local(PreferencesManager.getProgressPrefsFilePath());
            if (local.exists() || local2.exists()) {
                stringBuilder.append("1.9 saves found /");
            } else {
                stringBuilder.append("1.9 saves not found /");
            }
            stringBuilder.append(" migration flag ").append(Game.i.preferencesManager.getLegacyPreferences().has1dot9migrationFlag() ? "found" : "not found");
            Label label9 = new Label(stringBuilder, Game.i.assetManager.getLabelStyle(21));
            label9.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            table8.add((Table) label9).padTop(8.0f).padBottom(20.0f).row();
        }
        a5.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_instant_auto_wave_calls"), Game.i.settingsManager.isInstantAutoWaveCallEnabled(), 30, 40.0f, true, bool5 -> {
            Game.i.settingsManager.setInstantAutoWaveCallEnabled(bool5.booleanValue());
        })).height(64.0f).padLeft(64.0f).width(616.0f);
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            a5.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_rmb_equals_hold_button"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) != 0.0d, 30, 40.0f, true, bool6 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB, bool6.booleanValue() ? 1.0d : 0.0d);
            })).height(64.0f).padLeft(64.0f).width(616.0f).row();
        }
        Group group = new Group();
        group.setTransform(false);
        Label label10 = new Label(Game.i.localeManager.i18n.get("settings_auto_save_interval"), Game.i.assetManager.getLabelStyle(30));
        label10.setSize(100.0f, 64.0f);
        label10.setPosition(0.0f, 64.0f);
        group.addActor(label10);
        Label label11 = new Label("", Game.i.assetManager.getLabelStyle(24));
        label11.setPosition(420.0f, 0.0f);
        label11.setSize(180.0f, 64.0f);
        group.addActor(label11);
        int customValue = (int) (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.STATE_AUTO_SAVE_INTERVAL) * 2.0d);
        this.r = new HorizontalSlider(400.0f, 0.0d, 0.0d, 30.0d, d4 -> {
            int round = MathUtils.round(d4.floatValue());
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.STATE_AUTO_SAVE_INTERVAL, round * 0.5f);
            if (round == 0) {
                label11.setText(Game.i.localeManager.i18n.get("never"));
            } else if (round % 2 == 0) {
                label11.setText((round / 2) + Game.i.localeManager.i18n.get("TIME_CHAR_MINUTE"));
            } else {
                label11.setText((round / 2) + Game.i.localeManager.i18n.get("TIME_CHAR_MINUTE") + " 30" + Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
            }
        });
        this.r.setNotifyOnDrag(true);
        this.r.setSize(400.0f, 64.0f);
        this.r.setValue(customValue, true);
        group.addActor(this.r);
        a5.row();
        a5.add((Table) group).padLeft(64.0f).width(616.0f).height(128.0f);
        LabelButton labelButton2 = new LabelButton(Game.i.localeManager.i18n.get("settings_reset_progress"), Game.i.assetManager.getLabelStyle(30), () -> {
            boolean isDeveloperModeEnabled = Game.i.progressManager.isDeveloperModeEnabled();
            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("settings_reset_progress_question"), () -> {
                if (isDeveloperModeEnabled) {
                    Threads.i().runOnMainThread(() -> {
                        Dialog.i().showAlertWithConfirmCallback(Game.i.localeManager.i18n.get("restart_the_game"), () -> {
                            if (DeveloperConsole.i() != null) {
                                DeveloperConsole.i().dispose();
                            }
                            Game.i.preferencesManager.resetProgress();
                            Game.exit();
                        });
                    });
                } else {
                    Game.i.preferencesManager.resetProgress();
                    Game.i.screenManager.goToMainMenu();
                }
            });
            Dialog.i().makeConfirmButtonDisabled(2);
        });
        labelButton2.setColors(MaterialColor.RED.P600, MaterialColor.ORANGE.P400);
        a5.add((Table) labelButton2).bottom().left().padLeft(64.0f).width(616.0f).height(64.0f).row();
        Label label12 = new Label(Game.i.localeManager.i18n.get("settings_auto_save_hint"), Game.i.assetManager.getLabelStyle(24));
        label12.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label12.setWrap(true);
        a5.add((Table) label12).top().left().padLeft(64.0f).width(616.0f).padTop(8.0f).padBottom(16.0f).row();
        if (Game.i.actionResolver.hasNotifications()) {
            a5.row();
            a5.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_notifications"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SEND_NOTIFICATIONS) != 0.0d, 30, 40.0f, true, bool7 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.SEND_NOTIFICATIONS, bool7.booleanValue() ? 1.0d : 0.0d);
            })).padLeft(64.0f).width(616.0f).height(64.0f);
        }
        a5.row();
        a5.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_show_bonus_selection_immediately"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOW_BONUS_SELECTION_IMMEDIATELY) != 0.0d, 30, 40.0f, true, bool8 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.SHOW_BONUS_SELECTION_IMMEDIATELY, bool8.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a5.row();
        a5.add().height(1.0f).expandX().fillX().row();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            a5.add((Table) new LabelButton(Game.i.localeManager.i18n.get("settings_edit_hotkeys"), Game.i.assetManager.getLabelStyle(30), () -> {
                Game.i.screenManager.goToHotkeyEditorScreen();
            })).padLeft(64.0f).height(64.0f).width(616.0f).row();
        }
        if (Gdx.graphics.supportsDisplayModeChange() || initConfigManager.isAvailable(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS)) {
            Table a6 = a(Game.i.localeManager.i18n.get("settings_screen"), "icon-screen");
            Table table9 = new Table();
            a6.add(table9).colspan(2).width(1360.0f).row();
            if (Gdx.graphics.supportsDisplayModeChange()) {
                this.k = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_toggle_graphics_full_screen"), initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) != 0, 30, 40.0f, true, bool9 -> {
                    this.u.setEnabled(true);
                });
                table9.add(this.k).width(616.0f).padLeft(64.0f).height(64.0f).top().left();
                this.m = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_toggle_graphics_vertical_synchronization"), initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_VSYNC) != 0, 30, 40.0f, true, bool10 -> {
                    this.u.setEnabled(true);
                });
                table9.add(this.m).width(616.0f).padLeft(64.0f).height(64.0f).top().left().row();
                if (initConfigManager.isAvailable(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS)) {
                    this.l = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_windowed_borderless_fullscreen"), initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS) != 0, 30, 40.0f, true, bool11 -> {
                        this.u.setEnabled(true);
                    });
                    table9.add(this.l).width(616.0f).padLeft(64.0f).height(64.0f).top().left().row();
                }
                Graphics.DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
                int i2 = 0;
                for (Graphics.DisplayMode displayMode : displayModes) {
                    if (i2 < displayMode.bitsPerPixel) {
                        i2 = displayMode.bitsPerPixel;
                    }
                }
                Array array = new Array(IntPair.class);
                for (Graphics.DisplayMode displayMode2 : displayModes) {
                    if (displayMode2.bitsPerPixel >= i2 && displayMode2.height >= 540.0f) {
                        boolean z = false;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= array.size) {
                                break;
                            }
                            if (((IntPair[]) array.items)[i3].f3850a != displayMode2.width || ((IntPair[]) array.items)[i3].f3851b != displayMode2.height) {
                                i3++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            array.add(new IntPair(displayMode2.width, displayMode2.height));
                        }
                    }
                }
                array.sort((intPair, intPair2) -> {
                    return Integer.compare(intPair.f3850a, intPair2.f3850a);
                });
                Array<String> array2 = new Array<>(String.class);
                for (int i4 = 0; i4 < array.size; i4++) {
                    IntPair intPair3 = ((IntPair[]) array.items)[i4];
                    array2.add(intPair3.f3850a + "x" + intPair3.f3851b);
                }
                Graphics.DisplayMode bestFullscreenMode = SettingsManager.getBestFullscreenMode(initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_WIDTH), initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_HEIGHT));
                this.n = new SelectBox<>(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), true));
                this.n.setItems(array2);
                this.n.setSelected(bestFullscreenMode.width + "x" + bestFullscreenMode.height);
                this.n.addListener(new ChangeListener() { // from class: com.prineside.tdi2.screens.SettingsScreen.3
                    @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                    public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                        SettingsScreen.this.u.setEnabled(true);
                    }
                });
                table9.add((Table) this.n).width(516.0f).padLeft(64.0f).height(56.0f).padTop(8.0f).padRight(100.0f).top().left();
                Group group2 = new Group();
                group2.setTransform(false);
                table9.add((Table) group2).width(616.0f).padLeft(64.0f).height(64.0f).top().left().row();
                Label label13 = new Label("", Game.i.assetManager.getLabelStyle(24));
                label13.setPosition(420.0f, 0.0f);
                label13.setSize(180.0f, 64.0f);
                group2.addActor(label13);
                int i5 = initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FPS_LIMIT);
                this.o = new HorizontalSlider(400.0f, i5, 0.0d, 1.0d, d5 -> {
                    int round = MathUtils.round(((float) this.o.getValue()) * (f.length - 1));
                    if (this.u != null) {
                        this.u.setEnabled(true);
                    }
                    int i6 = f[round];
                    if (i6 == 0) {
                        label13.setText(Game.i.localeManager.i18n.get("settings_label_no_fps_limit"));
                    } else {
                        label13.setText("Max " + i6 + " FPS");
                    }
                });
                this.o.setNotifyOnDrag(true);
                this.o.setSize(400.0f, 64.0f);
                int i6 = 0;
                int i7 = 9001;
                for (int i8 = 0; i8 < f.length; i8++) {
                    int abs = Math.abs(f[i8] - i5);
                    if (abs < i7) {
                        i7 = abs;
                        i6 = i8;
                    }
                }
                this.o.setValue(i6 / (f.length - 1), true);
                group2.addActor(this.o);
            }
            if (initConfigManager.isAvailable(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS)) {
                table9.add((Table) new Label(Game.i.localeManager.i18n.get("settings_label_anti_aliasing"), Game.i.assetManager.getLabelStyle(30))).top().left().padTop(24.0f).padLeft(64.0f).row();
                this.s = new SelectBox<>(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), false));
                Array<String> array3 = new Array<>();
                array3.add("None");
                int i9 = 4;
                try {
                    IntBuffer newIntBuffer = BufferUtils.newIntBuffer(4);
                    Gdx.gl20.glGetIntegerv(36183, newIntBuffer);
                    i9 = newIntBuffer.get();
                    f2820a.i("received max samples: " + i9, new Object[0]);
                } catch (Exception unused) {
                    f2820a.e("Failed to get max samples, falling back to " + i9, new Object[0]);
                }
                if (i9 >= 2) {
                    array3.add("2x MSAA");
                }
                if (i9 >= 4) {
                    array3.add("4x MSAA");
                }
                if (i9 >= 8) {
                    array3.add("8x MSAA");
                }
                if (i9 >= 16) {
                    array3.add("16x MSAA");
                }
                this.s.setItems(new String[0]);
                this.s.setItems(array3);
                int i10 = initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS);
                f2820a.i("current MSAA setting: " + i10, new Object[0]);
                try {
                    IntBuffer newIntBuffer2 = BufferUtils.newIntBuffer(4);
                    Gdx.gl20.glGetIntegerv(32937, newIntBuffer2);
                    f2820a.i("received current samples: " + newIntBuffer2.get(), new Object[0]);
                } catch (Exception unused2) {
                    f2820a.e("Failed to get current samples", new Object[0]);
                }
                switch (i10) {
                    case 0:
                    case 1:
                        this.s.setSelected("None");
                        break;
                    case 2:
                        this.s.setSelected("2x MSAA");
                        break;
                    case 4:
                        this.s.setSelected("4x MSAA");
                        break;
                    case 8:
                        this.s.setSelected("8x MSAA");
                        break;
                    case 16:
                        this.s.setSelected("16x MSAA");
                        break;
                }
                this.s.addListener(new ChangeListener() { // from class: com.prineside.tdi2.screens.SettingsScreen.4
                    @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                    public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                        String str = (String) SettingsScreen.this.s.getSelected();
                        int i11 = 1;
                        boolean z2 = -1;
                        switch (str.hashCode()) {
                            case -1921989598:
                                if (str.equals("4x MSAA")) {
                                    z2 = 2;
                                    break;
                                }
                                break;
                            case -648621741:
                                if (str.equals("16x MSAA")) {
                                    z2 = 4;
                                    break;
                                }
                                break;
                            case 2433880:
                                if (str.equals("None")) {
                                    z2 = false;
                                    break;
                                }
                                break;
                            case 597970336:
                                if (str.equals("2x MSAA")) {
                                    z2 = true;
                                    break;
                                }
                                break;
                            case 1628025126:
                                if (str.equals("8x MSAA")) {
                                    z2 = 3;
                                    break;
                                }
                                break;
                        }
                        switch (z2) {
                            case false:
                                i11 = 1;
                                break;
                            case true:
                                i11 = 2;
                                break;
                            case true:
                                i11 = 4;
                                break;
                            case true:
                                i11 = 8;
                                break;
                            case true:
                                i11 = 16;
                                break;
                        }
                        SettingsScreen.this.u.setEnabled(true);
                        if (i11 != initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS)) {
                            SettingsScreen.this.t.setVisible(true);
                        }
                    }
                });
                table9.add((Table) this.s).width(516.0f).padLeft(64.0f).height(56.0f).padTop(8.0f).top().left().row();
            }
            this.u = new FancyButton("regularButton.a", () -> {
                if (Gdx.graphics.supportsDisplayModeChange()) {
                    Gdx.graphics.setVSync(this.m.isEnabled());
                    if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_VSYNC) != (this.m.isEnabled() ? 1 : 0)) {
                        initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_VSYNC, this.m.isEnabled() ? 1 : 0);
                    }
                    int i11 = f[MathUtils.round(((float) this.o.getValue()) * (f.length - 1))];
                    if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FPS_LIMIT) != i11) {
                        initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FPS_LIMIT, i11);
                    }
                    Game.i.actionResolver.setFpsLimit(i11);
                    if (this.n.getSelected() != null) {
                        String[] split = this.n.getSelected().split("x");
                        if (split.length >= 2) {
                            try {
                                int parseInt = Integer.parseInt(split[0]);
                                int parseInt2 = Integer.parseInt(split[1]);
                                initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_WIDTH, parseInt);
                                initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_HEIGHT, parseInt2);
                            } catch (Exception e) {
                                f2820a.e(e.getMessage(), new Object[0]);
                            }
                        }
                    }
                    boolean isEnabled = this.l.isEnabled();
                    if (isEnabled) {
                        if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS) == 0) {
                            initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS, 1);
                        }
                    } else if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS) != 0) {
                        initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS, 0);
                    }
                    if (this.k.isEnabled()) {
                        Graphics.DisplayMode bestFullscreenMode2 = SettingsManager.getBestFullscreenMode(initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_WIDTH), initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_HEIGHT));
                        if (bestFullscreenMode2 != null) {
                            if (isEnabled) {
                                Gdx.graphics.setUndecorated(true);
                                Gdx.graphics.setWindowedMode(bestFullscreenMode2.width, bestFullscreenMode2.height);
                            } else {
                                Gdx.graphics.setUndecorated(false);
                                Gdx.graphics.setFullscreenMode(bestFullscreenMode2);
                            }
                            if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) == 0) {
                                initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED, 1);
                            }
                        }
                    } else {
                        Gdx.graphics.setUndecorated(false);
                        Gdx.graphics.setWindowedMode(Config.DISPLAY_WIDTH, 900);
                        if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) != 0) {
                            initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED, 0);
                        }
                    }
                }
                if (initConfigManager.isAvailable(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS)) {
                    int i12 = 1;
                    String selected = this.s.getSelected();
                    boolean z2 = -1;
                    switch (selected.hashCode()) {
                        case -1921989598:
                            if (selected.equals("4x MSAA")) {
                                z2 = 2;
                                break;
                            }
                            break;
                        case -648621741:
                            if (selected.equals("16x MSAA")) {
                                z2 = 4;
                                break;
                            }
                            break;
                        case 2433880:
                            if (selected.equals("None")) {
                                z2 = false;
                                break;
                            }
                            break;
                        case 597970336:
                            if (selected.equals("2x MSAA")) {
                                z2 = true;
                                break;
                            }
                            break;
                        case 1628025126:
                            if (selected.equals("8x MSAA")) {
                                z2 = 3;
                                break;
                            }
                            break;
                    }
                    switch (z2) {
                        case false:
                            i12 = 1;
                            break;
                        case true:
                            i12 = 2;
                            break;
                        case true:
                            i12 = 4;
                            break;
                        case true:
                            i12 = 8;
                            break;
                        case true:
                            i12 = 16;
                            break;
                    }
                    if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS) != i12) {
                        initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS, i12);
                    }
                }
                this.u.setEnabled(false);
                Game.i.actionResolver.getInitConfigManager().saveIfRequired();
            }).withLabel(30, Game.i.localeManager.i18n.get("settings_button_apply"));
            this.u.setEnabled(false);
            table9.add(this.u).size(220.0f, 64.0f).padLeft(64.0f).left().padTop(32.0f).padBottom(16.0f).row();
            this.t = new Label(Game.i.localeManager.i18n.get("settings_note_restart_required"), Game.i.assetManager.getLabelStyle(24));
            this.t.setColor(MaterialColor.AMBER.P500);
            this.t.setVisible(false);
            table9.add((Table) this.t).padLeft(64.0f).top().left().fillX().row();
            table9.add().height(1.0f).expandX().fillX();
        }
        Table a7 = a(Game.i.localeManager.i18n.get("settings_graphics"), "icon-easel");
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_large_fonts"), Game.i.settingsManager.isLargeFontsEnabled(), 30, 40.0f, true, bool12 -> {
            Game.i.settingsManager.setLargeFontsEnabled(bool12.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_particles"), Game.i.settingsManager.isParticlesDrawing(), 30, 40.0f, true, bool13 -> {
            Game.i.settingsManager.setParticlesDrawing(bool13.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_explosions"), Game.i.settingsManager.isExplosionsDrawing(), 30, 40.0f, true, bool14 -> {
            Game.i.settingsManager.setExplosionsDrawing(bool14.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_projectiles"), Game.i.settingsManager.isProjectilesDrawing(), 30, 40.0f, true, bool15 -> {
            Game.i.settingsManager.setProjectilesDrawing(bool15.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_projectile_trails"), Game.i.settingsManager.isProjectileTrailsDrawing(), 30, 40.0f, true, bool16 -> {
            Game.i.settingsManager.setProjectileTrailsDrawing(bool16.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_flying_coins"), Game.i.settingsManager.isFlyingCoinsEnabled(), 30, 40.0f, true, bool17 -> {
            Game.i.settingsManager.setFlyingCoinsEnabled(bool17.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_ui_animations"), Game.i.settingsManager.isUiAnimationsEnabled(), 30, 40.0f, true, bool18 -> {
            Game.i.settingsManager.setUiAnimationsEnabled(bool18.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_stains"), Game.i.settingsManager.isStainsEnabled(), 30, 40.0f, true, bool19 -> {
            Game.i.settingsManager.setStainsEnabled(bool19.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_colorblindness"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.COLORBLIND_MODE) == 1.0d, 30, 40.0f, true, bool20 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.COLORBLIND_MODE, bool20.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_slow_motion_pause"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SLOW_MOTION_PAUSE) == 1.0d, 30, 40.0f, true, bool21 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.SLOW_MOTION_PAUSE, bool21.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_tower_target"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET) == 1.0d, 30, 40.0f, true, bool22 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET, bool22.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_live_leaderboards"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LIVE_LEADERBOARDS) > 0.0d, 30, 40.0f, true, bool23 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.LIVE_LEADERBOARDS, bool23.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a7.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_statistics_chart"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.STATISTICS_CHART_ENABLED) > 0.0d, 30, 40.0f, true, bool24 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.STATISTICS_CHART_ENABLED, bool24.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        LabelToggleButton labelToggleButton = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_3d_models"), Game.i.settingsManager.isThreeDeeModelsEnabled(), 30, 40.0f, true, null);
        labelToggleButton.onToggle = bool25 -> {
            if (Game.i.settingsManager.setThreeDeeModelsEnabled(bool25.booleanValue())) {
                MainMenuUiScene.i().recreate();
                MainMenuUiScene.i().rebuildIfNeeded();
            } else {
                labelToggleButton.setEnabled(Game.i.settingsManager.isThreeDeeModelsEnabled());
            }
        };
        a7.add(labelToggleButton).padLeft(64.0f).width(616.0f).height(64.0f).row();
        LabelToggleButton labelToggleButton2 = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_loot_icons"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED) != 0.0d, 30, 40.0f, true, null);
        labelToggleButton2.onToggle = bool26 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED, bool26.booleanValue() ? 1.0d : 0.0d);
        };
        a7.add(labelToggleButton2).padLeft(64.0f).width(616.0f).height(64.0f);
        boolean z2 = false;
        if (HotKeyHintLabel.isAvailable()) {
            LabelToggleButton labelToggleButton3 = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_hot_key_hints"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_HOT_KEY_HINTS) != 0.0d, 30, 40.0f, true, null);
            labelToggleButton3.onToggle = bool27 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_HOT_KEY_HINTS, bool27.booleanValue() ? 1.0d : 0.0d);
            };
            z2 = true;
            a7.add(labelToggleButton3).padLeft(64.0f).width(616.0f).height(64.0f).row();
        }
        if (Game.i.actionResolver.rewardAdsAvailable()) {
            LabelToggleButton labelToggleButton4 = new LabelToggleButton();
            labelToggleButton4.setup(Game.i.localeManager.i18n.get("settings_pause_ads_icon"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.ENABLE_PAUSE_AD_ICON) != 0.0d, 30, 40.0f, true, bool28 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.ENABLE_PAUSE_AD_ICON, bool28.booleanValue() ? 1.0d : 0.0d);
            });
            Cell height = a7.add(labelToggleButton4).padLeft(64.0f).width(616.0f).height(64.0f);
            boolean z3 = !z2;
            z2 = z3;
            if (z3) {
                height.row();
            }
        }
        LabelToggleButton labelToggleButton5 = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_damage_particles"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_ENABLED) != 0.0d, 30, 40.0f, true, null);
        labelToggleButton5.onToggle = bool29 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_ENABLED, bool29.booleanValue() ? 1.0d : 0.0d);
        };
        Cell height2 = a7.add(labelToggleButton5).padLeft(64.0f).width(616.0f).height(64.0f);
        boolean z4 = !z2;
        boolean z5 = z4;
        if (z4) {
            height2.row();
        }
        LabelToggleButton labelToggleButton6 = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_damage_particles_more"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_MORE) != 0.0d, 30, 40.0f, true, null);
        labelToggleButton6.onToggle = bool30 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DAMAGE_PARTICLES_MORE, bool30.booleanValue() ? 1.0d : 0.0d);
        };
        Cell height3 = a7.add(labelToggleButton6).padLeft(64.0f).width(616.0f).height(64.0f);
        boolean z6 = !z5;
        boolean z7 = z6;
        if (z6) {
            height3.row();
        }
        LabelToggleButton labelToggleButton7 = new LabelToggleButton(Game.i.localeManager.i18n.get("settings_music_spectrum"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_SPECTRUM_ENABLED) != 0.0d, 30, 40.0f, true, null);
        labelToggleButton7.onToggle = bool31 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.MUSIC_SPECTRUM_ENABLED, bool31.booleanValue() ? 1.0d : 0.0d);
        };
        Cell height4 = a7.add(labelToggleButton7).padLeft(64.0f).width(616.0f).height(64.0f);
        if (!z7) {
            height4.row();
        }
        Group group3 = new Group();
        group3.setTransform(false);
        Label label14 = new Label(Game.i.localeManager.i18n.get("settings_ui_safe_zone"), Game.i.assetManager.getLabelStyle(30));
        label14.setSize(100.0f, 64.0f);
        label14.setPosition(0.0f, 64.0f);
        group3.addActor(label14);
        Label label15 = new Label("", Game.i.assetManager.getLabelStyle(24));
        label15.setPosition(420.0f, 0.0f);
        label15.setSize(180.0f, 64.0f);
        group3.addActor(label15);
        int i11 = initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_SAFE_AREA);
        this.p = new HorizontalSlider(400.0f, i11, 0.0d, 160.0d, d6 -> {
            int round = MathUtils.round(d6.floatValue());
            initConfigManager.set(SettingsManager.InitConfig.GRAPHICS_SAFE_AREA, round);
            this.e.getTable().setTouchable(Touchable.disabled);
            this.e.getTable().clear();
            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image2.setColor(MaterialColor.LIGHT_BLUE.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
            this.e.getTable().add((Table) image2).expandY().fillY().width(round);
            this.e.getTable().add().fill().expand();
            Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image3.setColor(MaterialColor.LIGHT_BLUE.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
            this.e.getTable().add((Table) image3).expandY().fillY().width(round);
            image2.addAction(Actions.sequence(Actions.delay(0.2f), Actions.fadeOut(1.0f)));
            image3.addAction(Actions.sequence(Actions.delay(0.2f), Actions.fadeOut(1.0f)));
            if (round == 0) {
                label15.setText(Game.i.localeManager.i18n.get("settings_label_full_screen"));
            } else {
                label15.setText(round + "px");
            }
        });
        this.p.setNotifyOnDrag(true);
        this.p.setSize(400.0f, 64.0f);
        this.p.setValue(i11, true);
        group3.addActor(this.p);
        a7.row();
        a7.add((Table) group3).padLeft(64.0f).width(616.0f).height(128.0f);
        Group group4 = new Group();
        group4.setTransform(false);
        Label label16 = new Label(Game.i.localeManager.i18n.get("settings_ui_scale"), Game.i.assetManager.getLabelStyle(30));
        label16.setSize(100.0f, 64.0f);
        label16.setPosition(0.0f, 64.0f);
        group4.addActor(label16);
        Label label17 = new Label("", Game.i.assetManager.getLabelStyle(24));
        label17.setPosition(420.0f, 0.0f);
        label17.setSize(180.0f, 64.0f);
        group4.addActor(label17);
        double customValue2 = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_SCALE);
        this.q = new HorizontalSlider(400.0f, customValue2, 0.7d, 1.0d, d7 -> {
            double doubleValue = d7.doubleValue();
            f2820a.i("setting custom value UI_SCALE " + doubleValue, new Object[0]);
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_SCALE, doubleValue);
            label17.setText(MathUtils.round((float) (doubleValue * 100.0d)) + "%");
        });
        this.q.setNotifyOnDrag(true);
        this.q.setSize(400.0f, 64.0f);
        this.q.setValue(customValue2, false);
        label17.setText(MathUtils.round((float) (customValue2 * 100.0d)) + "%");
        group4.addActor(this.q);
        a7.add((Table) group4).padLeft(64.0f).width(616.0f).height(128.0f);
        a7.row();
        Table a8 = a(Game.i.localeManager.i18n.get("settings_experimental"), "icon-exclamation-triangle");
        a8.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_multithreaded_rendering"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) != 0.0d, 30, 40.0f, true, bool32 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.MULTITHREADING, bool32.booleanValue() ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a8.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_postprocessing"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) != 0.0d, 30, 40.0f, true, bool33 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.POSTPROCESSING, bool33.booleanValue() ? 1.0d : 0.0d);
            Game.i.screenManager.goToSettingsScreenAndScroll(this.j.getScrollY());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a8.row();
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) != 0.0d) {
            Table a9 = a(((Game.i.localeManager.i18n.get("settings_postprocessing") + " [#FF9900](") + Game.i.localeManager.i18n.get("settings_experimental")) + ")[]", "icon-easel");
            Table table10 = new Table();
            a9.add(table10).padLeft(64.0f).padTop(32.0f).padBottom(24.0f).size(616.0f, 48.0f);
            table10.add((Table) new Label(Game.i.localeManager.i18n.get("settings_pp_graphics_scale"), Game.i.assetManager.getLabelStyle(30))).padBottom(16.0f).top().left().row();
            Label label18 = new Label(StrictMath.round(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_GRAPHICS_SCALE) * 100.0d) + "%", Game.i.assetManager.getLabelStyle(24));
            HorizontalSlider horizontalSlider4 = new HorizontalSlider(400.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_GRAPHICS_SCALE), 0.5d, 1.0d, d8 -> {
                double round = Math.round(d8.doubleValue() * 20.0d) / 20.0d;
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.PP_GRAPHICS_SCALE, round);
                label18.setText(StrictMath.round(round * 100.0d) + "%");
            });
            horizontalSlider4.setNotifyOnDrag(true);
            table10.add((Table) horizontalSlider4).size(400.0f, 48.0f);
            table10.add((Table) label18).padLeft(16.0f);
            table10.add().height(1.0f).expandX().fillX();
            a9.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_pp_clean_detailed_mode"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_CLEAN_DETAILED_MODE) != 0.0d, 30, 40.0f, true, bool34 -> {
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.PP_CLEAN_DETAILED_MODE, bool34.booleanValue() ? 1.0d : 0.0d);
            })).padLeft(64.0f).width(616.0f).height(64.0f).row();
            a9.row();
            Table table11 = new Table();
            a9.add(table11).padLeft(64.0f).padTop(32.0f).padBottom(24.0f).size(616.0f, 48.0f);
            table11.add((Table) new Label(Game.i.localeManager.i18n.get("settings_pp_effects_scale"), Game.i.assetManager.getLabelStyle(30))).padBottom(16.0f).top().left().row();
            Label label19 = new Label(StrictMath.round(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_EFFECTS_SCALE) * 100.0d) + "%", Game.i.assetManager.getLabelStyle(24));
            HorizontalSlider horizontalSlider5 = new HorizontalSlider(400.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_EFFECTS_SCALE), 0.5d, 1.0d, d9 -> {
                double round = Math.round(d9.doubleValue() * 20.0d) / 20.0d;
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.PP_EFFECTS_SCALE, round);
                label19.setText(StrictMath.round(round * 100.0d) + "%");
            });
            horizontalSlider5.setNotifyOnDrag(true);
            table11.add((Table) horizontalSlider5).size(400.0f, 48.0f);
            table11.add((Table) label19).padLeft(16.0f);
            table11.add().height(1.0f).expandX().fillX();
            a9.row();
        }
        Table a10 = a(Game.i.localeManager.i18n.get("settings_development"), "icon-tools");
        a10.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_debug_mode"), Game.i.settingsManager.isInDebugMode(), 30, 40.0f, true, bool35 -> {
            Game.i.settingsManager.setDebugMode(bool35.booleanValue());
            this.h++;
            if (this.h <= 3) {
                Game.i.soundManager.playRarity(RarityType.COMMON);
            } else if (this.h == 4) {
                Game.i.soundManager.playRarity(RarityType.RARE);
            } else if (this.h == 5) {
                Game.i.soundManager.playRarity(RarityType.VERY_RARE);
            } else if (this.h == 6) {
                Game.i.soundManager.playRarity(RarityType.EPIC);
            } else if (this.h == 7) {
                Game.i.soundManager.playRarity(RarityType.LEGENDARY);
            }
            if (this.h == 8) {
                Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel("zecred"), null);
            }
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a10.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_debug_detailed_mode"), Game.i.settingsManager.isInDebugDetailedMode(), 30, 40.0f, true, bool36 -> {
            Game.i.settingsManager.setDebugDetailedMode(bool36.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a10.add(new LabelToggleButton("FPS", Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SHOW_FPS) != 0.0d, 30, 40.0f, true, bool37 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DBG_SHOW_FPS, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SHOW_FPS) == 0.0d ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a10.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_toggle_desync_check"), Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION) != 0.0d, 30, 40.0f, true, bool38 -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SYNC_VALIDATION) == 0.0d ? 1.0d : 0.0d);
        })).padLeft(64.0f).width(616.0f).height(64.0f).row();
        a10.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_debug_bug_reports"), Game.i.settingsManager.isBugReportsEnabled(), 30, 40.0f, true, bool39 -> {
            Game.i.settingsManager.setBugReportsEnabled(bool39.booleanValue());
        })).padLeft(64.0f).width(616.0f).height(64.0f);
        a10.add((Table) new LabelButton(Game.i.localeManager.i18n.get("settings_button_send_logs"), Game.i.assetManager.getLabelStyle(30), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener(this) { // from class: com.prineside.tdi2.screens.SettingsScreen.8
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Threads.i().runOnMainThread(() -> {
                        FastRandom.random.setSeed(new Random().nextLong());
                        String distinguishableString = FastRandom.getDistinguishableString(6, FastRandom.random);
                        CrashHandler.report("Manual " + distinguishableString + ": " + (str.length() > 256 ? str.substring(0, 256) : str));
                        Gdx.app.getClipboard().setContents(distinguishableString);
                        Notifications.i().add("Logs sent, ID: [#FFFF00]" + distinguishableString + "[] (copied to clipboard)", null, null, null);
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, Game.i.localeManager.i18n.get("settings_send_logs_dialog_question"), "", Game.i.localeManager.i18n.get("settings_send_logs_field_placeholder"));
        })).padLeft(64.0f).height(64.0f).width(616.0f).row();
        this.g.add().height(144.0f).fillX().expandX().row();
        a10.row();
        Label label20 = new Label("", Game.i.assetManager.getLabelStyle(24));
        label20.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        a10.add((Table) label20).padLeft(64.0f).fillX().padTop(16.0f).row();
        try {
            Array<String> runTheTest = FileIntegrityChecker.runTheTest();
            if (runTheTest.size == 0) {
                label20.setColor(MaterialColor.LIGHT_GREEN.P500);
                label20.setText("File integrity check passed!");
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("File integrity check failed with ").append(String.valueOf(runTheTest.size)).append(" error(s):");
                for (int i12 = 0; i12 < runTheTest.size; i12++) {
                    stringBuilder2.append("- ").append(runTheTest.get(i12)).append(SequenceUtils.EOL);
                }
                label20.setColor(MaterialColor.ORANGE.P500);
                label20.setText(stringBuilder2);
            }
        } catch (Exception e) {
            f2820a.e("File integrity check failed", e);
            label20.setColor(Color.RED);
            label20.setText("File integrity check failed: " + e.getMessage());
        }
        if (f2 != 0.0f) {
            f2820a.i("scrolling to " + f2, new Object[0]);
            this.c.getTable().invalidate();
            this.c.getTable().layout();
            this.j.setScrollY(f2);
            this.j.updateVisualScroll();
        }
    }

    /* renamed from: com.prineside.tdi2.screens.SettingsScreen$5, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen$5.class */
    class AnonymousClass5 implements Input.TextInputListener {
        @Override // com.badlogic.gdx.Input.TextInputListener
        public void input(String str) {
            Threads.i().runOnMainThread(() -> {
                Game.i.authManager.loadStateFromServer(str, null);
            });
        }

        @Override // com.badlogic.gdx.Input.TextInputListener
        public void canceled() {
        }
    }

    /* renamed from: com.prineside.tdi2.screens.SettingsScreen$6, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen$6.class */
    class AnonymousClass6 implements Input.TextInputListener {
        @Override // com.badlogic.gdx.Input.TextInputListener
        public void input(String str) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.SITE_URL + "/?m=api&a=loadGameById&v=208");
            HashMap hashMap = new HashMap();
            hashMap.put(Attribute.ID_ATTR, str);
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Game.i.authManager.queueRequest(new AuthManager.HttpQueuedRequest("loadSavedGameFromServer", httpRequest, (z, str2) -> {
                if (!z) {
                    SettingsScreen.f2820a.e("Failed to load saved game from server", new Object[0]);
                    return;
                }
                try {
                    JsonValue parse = new JsonReader().parse(str2);
                    if (!parse.getString("status").equals("success")) {
                        SettingsScreen.f2820a.e("can't load game: " + str2, new Object[0]);
                        return;
                    }
                    if (parse.get("savedGame").getInt("gameBuild") > 208) {
                        Notifications.i().add(Game.i.localeManager.i18n.get("cant_load_from_cloud_need_update"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                        return;
                    }
                    SettingsPrefs.i().requireSave();
                    String sessionId = Game.i.authManager.getSessionId();
                    Game.i.preferencesManager.fromBase64(parse.get("savedGame").get("data").asString());
                    Game.i.preferencesManager.reapplyAllPreferences();
                    Game.i.authManager.loadStateFromServer(sessionId, null);
                    AccountScreen.goToScreen();
                    if (SettingsPrefs.i().auth.isHasUnsavedProgressForCloud()) {
                        SettingsPrefs.i().auth.setHasUnsavedProgressForCloud(false);
                        SettingsPrefs.i().requireSave();
                    }
                    Notifications.i().add(Game.i.localeManager.i18n.get("game_loaded_from_cloud"), Game.i.assetManager.getDrawable("icon-info"), null, StaticSoundType.NOTIFICATION);
                } catch (Exception e) {
                    SettingsScreen.f2820a.e("Failed to parse response", e);
                }
            }));
        }

        @Override // com.badlogic.gdx.Input.TextInputListener
        public void canceled() {
        }
    }

    /* renamed from: com.prineside.tdi2.screens.SettingsScreen$7, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen$7.class */
    class AnonymousClass7 implements Comparator<ObjectMap<String, Integer>> {
        @Override // java.util.Comparator
        public int compare(ObjectMap<String, Integer> objectMap, ObjectMap<String, Integer> objectMap2) {
            return Integer.compare(objectMap.get("real_time").intValue(), objectMap2.get("real_time").intValue());
        }
    }

    /* renamed from: com.prineside.tdi2.screens.SettingsScreen$9, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen$9.class */
    class AnonymousClass9 implements Input.TextInputListener {
        @Override // com.badlogic.gdx.Input.TextInputListener
        public void input(String str) {
            Threads.i().runOnMainThread(() -> {
                ReplayManager.ReplayRecord record = Game.i.replayManager.getRecord(str);
                if (record == null || record.getStateBytes() == null) {
                    SettingsScreen.f2820a.i("requesting server for replay", new Object[0]);
                    Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                    httpRequest.setUrl(Config.SITE_URL + "/?m=api&a=getReplay&v=208");
                    HashMap hashMap = new HashMap();
                    hashMap.put("replayid", str);
                    if (Game.i.authManager.getSessionId() != null) {
                        hashMap.put("sessionid", Game.i.authManager.getSessionId());
                    }
                    httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.screens.SettingsScreen.9.1
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            String resultAsString = httpResponse.getResultAsString();
                            SettingsScreen.f2820a.i(resultAsString, new Object[0]);
                            try {
                                JsonValue parse = new JsonReader().parse(resultAsString);
                                if (parse.getString("status").equals("success")) {
                                    Threads.i().runOnMainThread(() -> {
                                        try {
                                            GameStateSystem.startReplay(ReplayManager.ReplayRecord.fromCompactString(parse.getString("replay")));
                                        } catch (Exception e) {
                                            SettingsScreen.f2820a.e(e.getMessage(), e);
                                            SettingsScreen.f2820a.e(resultAsString, new Object[0]);
                                            Notifications.i().add("Failed to load the replay: " + e.getMessage(), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                                        }
                                    });
                                    return;
                                }
                                SettingsScreen.f2820a.e("Status is not success: " + parse.getString("status"), new Object[0]);
                                SettingsScreen.f2820a.i(resultAsString, new Object[0]);
                                Threads.i().runOnMainThread(() -> {
                                    Notifications.i().add(parse.getString("status") + ": " + parse.getString("message"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                                });
                            } catch (Exception unused) {
                                SettingsScreen.f2820a.e("Invalid response: " + resultAsString, new Object[0]);
                                Threads.i().runOnMainThread(() -> {
                                    Notifications.i().add("Invalid response: " + resultAsString, Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                                });
                            }
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            SettingsScreen.f2820a.e("Failed", th);
                            Threads.i().runOnMainThread(() -> {
                                Notifications.i().add("Failed to load the replay", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                            });
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                            SettingsScreen.f2820a.e(Transaction.REVERSAL_TEXT_CANCELLED, new Object[0]);
                        }
                    });
                    return;
                }
                try {
                    GameStateSystem.startReplay(record);
                } catch (Exception e) {
                    SettingsScreen.f2820a.e("parsing failed", e);
                    Threads.i().runOnMainThread(() -> {
                        Notifications.i().add("Failed to load the replay", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                    });
                }
            });
        }

        @Override // com.badlogic.gdx.Input.TextInputListener
        public void canceled() {
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        super.show();
        Game.i.uiManager.stage.setScrollFocus(this.j);
    }

    private Table a(String str, String str2) {
        Table table = new Table();
        this.g.add(table).fillX().expandX().row();
        if (this.i % 2 == 0) {
            table.add((Table) new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f}, new Color(0.0f, 0.0f, 0.0f, 0.28f))).expandX().fillX().height(24.0f).padTop(8.0f).row();
        }
        Table table2 = new Table();
        if (this.i % 2 == 0) {
            table2.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
        }
        table.add(table2).expandX().fillX().row();
        Table table3 = new Table();
        table2.add(table3).width(1360.0f).padTop(32.0f).row();
        table3.add((Table) new Image(Game.i.assetManager.getDrawable(str2))).padRight(16.0f).size(48.0f).left();
        table3.add((Table) new Label(str, Game.i.assetManager.getLabelStyle(36))).expandX().fillX();
        Table table4 = new Table();
        table2.add(table4).padBottom(16.0f).padTop(16.0f).width(1360.0f).row();
        if (this.i % 2 == 0) {
            table.add((Table) new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f}, new Color(0.0f, 0.0f, 0.0f, 0.28f))).expandX().fillX().height(24.0f).padBottom(8.0f).row();
        }
        this.i++;
        return table4;
    }

    private static void a(String str) {
        Game.i.localeManager.setLocale(str, true);
        Game.i.screenManager.goToSettingsScreen();
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f2) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.d);
        Game.i.uiManager.removeLayer(this.e);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SettingsScreen$LocaleButton.class */
    public static class LocaleButton {
        private Image c;
        public Label label;

        /* renamed from: a, reason: collision with root package name */
        boolean f2828a;
        private boolean d;

        /* renamed from: b, reason: collision with root package name */
        Table f2829b;
        private Runnable e;

        public LocaleButton(String str, Runnable runnable) {
            Preconditions.checkNotNull(runnable);
            this.e = runnable;
            this.f2829b = new Table();
            this.c = new Image(Game.i.assetManager.getDrawable("icon-check"));
            this.c.setColor(MaterialColor.LIGHT_GREEN.P500);
            this.label = new Label(str, new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
            this.label.setColor(MaterialColor.LIGHT_BLUE.P300);
            this.f2829b.add((Table) this.c).padRight(16.0f).size(48.0f, 48.0f);
            this.f2829b.add((Table) this.label).expandX().fillX();
            this.f2829b.setTouchable(Touchable.enabled);
            this.f2829b.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.SettingsScreen.LocaleButton.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    LocaleButton.this.onClick();
                }
            });
            this.f2829b.addListener(new InputListener() { // from class: com.prineside.tdi2.screens.SettingsScreen.LocaleButton.2
                @Override // com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                    LocaleButton.this.f2828a = true;
                    LocaleButton.this.a();
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                    LocaleButton.this.f2828a = false;
                    LocaleButton.this.a();
                }
            });
            setSelected(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.d) {
                this.label.setColor(MaterialColor.LIGHT_GREEN.P300);
            } else if (this.f2828a) {
                this.label.setColor(Color.WHITE);
            } else {
                this.label.setColor(MaterialColor.LIGHT_BLUE.P300);
            }
        }

        public void setSelected(boolean z) {
            this.d = z;
            if (z) {
                this.c.setVisible(true);
            } else {
                this.c.setVisible(false);
            }
            a();
        }

        public void onClick() {
            this.e.run();
        }
    }
}

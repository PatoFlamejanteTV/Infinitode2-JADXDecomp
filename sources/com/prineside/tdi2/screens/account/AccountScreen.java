package com.prineside.tdi2.screens.account;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.ui.shared.TextInputOverlay;
import com.prineside.tdi2.ui.shared.WebBrowser;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountScreen.class */
public class AccountScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2877a = TLog.forClass(AccountScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2878b;
    private final UiManager.UiLayer c;
    private final UiManager.UiLayer d;
    private final Table e;
    private final Label f;
    private final Image g;
    private final Label h;
    private final Label i;
    private final Label j;
    private Table k;
    private final LabelToggleButton l;
    private final Table m;
    private final Array<FancyButton> n;
    private final _AuthManagerListener o;

    public static RectButton createGoogleSignInButton(Runnable runnable, Runnable runnable2, boolean z) {
        Preconditions.checkNotNull(runnable, "disableButtonsRunnable can not be null");
        Preconditions.checkNotNull(runnable2, "enableButtonsRunnable can not be null");
        RectButton rectButton = new RectButton("Sign " + (z ? "up" : "in") + " with Google", new Label.LabelStyle(Game.i.defaultSmallFuturaFont, Color.WHITE), () -> {
            runnable.run();
            Game.i.actionResolver.requestGoogleAuth(str -> {
                if (str != null) {
                    Game.i.authManager.signInWithGoogle(str, googleSignInResult -> {
                        switch (googleSignInResult) {
                            case OTHER_ERROR:
                                Notifications.i().addFailure(Game.i.localeManager.i18n.get("unknown_error"));
                                return;
                            case SUCCESS:
                                goToScreen();
                                return;
                            case OTP_REQUIRED:
                                return;
                            case SIGN_UP_REQUIRED:
                                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("google_sign_in_no_user_sign_up_dialog"), () -> {
                                    Game.i.screenManager.setScreen(new AccountSignUpGoogleScreen());
                                });
                                return;
                            default:
                                return;
                        }
                    });
                } else {
                    Notifications.i().add(Game.i.localeManager.i18n.get("unknown_error"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                    runnable2.run();
                }
            });
        });
        rectButton.setIconPositioned(Game.i.assetManager.getDrawable("google-g-icon"), 16.0f, 8.0f, 48.0f, 48.0f);
        Color color = Color.WHITE;
        Color color2 = MaterialColor.GREY.P100;
        Color color3 = Color.WHITE;
        rectButton.setBackgroundColors(color, color2, color3, color3);
        Color color4 = Color.WHITE;
        Color color5 = Color.WHITE;
        rectButton.setIconColors(color4, color4, color5, color5);
        rectButton.setLabelColors(new Color(1886417151), new Color(1886417151), new Color(1886417151), Color.BLACK);
        rectButton.setLabel(72.0f, 0.0f, 240.0f, 64.0f, 1);
        rectButton.setSize(320.0f, 64.0f);
        return rectButton;
    }

    public static RectButton createSteamSignInButton(Runnable runnable, Runnable runnable2, boolean z) {
        Preconditions.checkNotNull(runnable, "disableButtonsRunnable can not be null");
        Preconditions.checkNotNull(runnable2, "enableButtonsRunnable can not be null");
        RectButton rectButton = new RectButton("Sign " + (z ? "up" : "in") + " with Steam", new Label.LabelStyle(Game.i.defaultSmallFuturaFont, Color.WHITE), () -> {
            runnable.run();
            Game.i.authManager.signInWithSteam(signInResponse -> {
                if (signInResponse.result == AuthManager.SignInResult.USER_NOT_FOUND) {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("steam_sign_in_no_user_sign_up_dialog"), () -> {
                        Game.i.screenManager.setScreen(new AccountSignUpSteamScreen());
                    });
                } else if (signInResponse.result == AuthManager.SignInResult.SUCCESS) {
                    Game.i.screenManager.setScreen(new AccountScreen());
                } else {
                    Notifications.i().add(Game.i.localeManager.i18n.get("unknown_error"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                }
                runnable2.run();
            });
        });
        rectButton.setIconPositioned(Game.i.assetManager.getDrawable("steam-icon"), 16.0f, 8.0f, 48.0f, 48.0f);
        Color color = Color.WHITE;
        Color color2 = MaterialColor.GREY.P100;
        Color color3 = Color.WHITE;
        rectButton.setBackgroundColors(color, color2, color3, color3);
        Color color4 = Color.WHITE;
        Color color5 = Color.WHITE;
        rectButton.setIconColors(color4, color4, color5, color5);
        rectButton.setLabelColors(new Color(1886417151), new Color(1886417151), new Color(1886417151), Color.BLACK);
        rectButton.setLabel(72.0f, 0.0f, 240.0f, 64.0f, 1);
        rectButton.setSize(320.0f, 64.0f);
        return rectButton;
    }

    public static void goToScreen() {
        if (Game.i.authManager.isSignedIn()) {
            Game.i.screenManager.setScreen(new AccountScreen());
        } else {
            Game.i.screenManager.setScreen(new AccountSignInScreen());
        }
    }

    public AccountScreen() {
        super(null, () -> {
            Game.i.screenManager.goToMainMenu();
        });
        String str;
        this.f2878b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "AccountScreen status");
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "AccountScreen preferencesRestoreOverlay");
        this.d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 103, "AccountScreen backupRestoreOverlay");
        this.n = new Array<>();
        this.o = new _AuthManagerListener(this, (byte) 0);
        Game.i.authManager.addListener(this.o);
        this.c.getTable().setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.78f)));
        this.c.getTable().setTouchable(Touchable.enabled);
        this.c.getTable().addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
            }
        });
        this.c.getTable().setVisible(false);
        this.d.getTable().setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.78f)));
        this.d.getTable().setTouchable(Touchable.enabled);
        this.d.getTable().addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
            }
        });
        this.d.getTable().setVisible(false);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        Table table2 = new Table();
        table.add(table2).width(600.0f);
        this.f = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.f.setColor(MaterialColor.GREEN.P500);
        table2.add((Table) this.f).row();
        Table table3 = new Table();
        table2.add(table3).padTop(20.0f).row();
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.enabled);
        group.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.3
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Gdx.f881net.openURI(Config.SITE_URL + "/?m=edit_profile&ts=" + Game.i.authManager.getSessionId());
            }
        });
        table3.add((Table) group).size(64.0f, 64.0f);
        this.g = new Image();
        this.g.setSize(64.0f, 64.0f);
        this.g.setColor(0.56f, 0.56f, 0.56f, 1.0f);
        group.addActor(this.g);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-edit"));
        image.setSize(28.0f, 28.0f);
        image.setPosition(2.0f, 2.0f);
        group.addActor(image);
        Table table4 = new Table();
        table3.add(table4).padLeft(16.0f);
        this.h = new Label("", Game.i.assetManager.getLabelStyle(36));
        table4.add((Table) this.h);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-edit"));
        image2.setColor(MaterialColor.LIGHT_BLUE.P500);
        image2.setTouchable(Touchable.enabled);
        image2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.4
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Game.i.uiManager.getTextInput(new Input.TextInputListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.4.1
                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void input(String str2) {
                        Threads.i().runOnMainThread(() -> {
                            if (!Pattern.compile("^[a-zA-Z0-9_]+$").matcher(str2).matches()) {
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                            } else {
                                Game.i.authManager.requestNicknameChange(str2, bool -> {
                                    AccountScreen.goToScreen();
                                });
                            }
                        });
                    }

                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void canceled() {
                    }
                }, Game.i.localeManager.i18n.get("nickname"), Game.i.authManager.getNickname(), "");
            }
        });
        table4.add((Table) image2).size(32.0f).padLeft(16.0f);
        this.i = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.i.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table2.add((Table) this.i).padTop(6.0f).row();
        this.j = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.j.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table2.add((Table) this.j).padTop(6.0f).row();
        table2.add(new FancyButton("regularButton.a", () -> {
            WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_PLAYER_PROFILE_URL + Game.i.authManager.getPlayerId(), null);
            WebBrowser.i().setVisible(true);
        }).withLabel(24, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-user> " + Game.i.localeManager.i18n.get("view_profile")))).size(296.0f, 56.0f).padTop(32.0f).row();
        table2.add(new FancyButton("regularButton.b", () -> {
            Game.i.screenManager.goToAccountSettingsScreen();
        }).withLabel(24, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-tools> " + Game.i.localeManager.i18n.get("settings")))).size(296.0f, 56.0f).padTop(16.0f).row();
        if (Game.i.authManager.getInviteCode() != null) {
            Label label = new Label(Game.i.localeManager.i18n.get("invite_code"), Game.i.assetManager.getLabelStyle(24));
            label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            table2.add((Table) label).padTop(32.0f).row();
            Label label2 = new Label(Game.i.authManager.getInviteCode(), Game.i.assetManager.getLabelStyle(36));
            label2.setColor(MaterialColor.LIGHT_GREEN.P500);
            table2.add((Table) label2).row();
            label2.setTouchable(Touchable.enabled);
            label2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.5
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    Gdx.app.getClipboard().setContents(Game.i.authManager.getInviteCode());
                    Notifications.i().add(Game.i.localeManager.i18n.get("copied_to_clipboard"), null, null, null);
                }
            });
            Label label3 = new Label(Game.i.localeManager.i18n.get("invite_code_hint"), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label3.setWrap(true);
            label3.setAlignment(1);
            table2.add((Table) label3).width(500.0f).row();
        }
        if (!Game.i.authManager.isPasswordSet()) {
            this.k = new Table();
            this.k.setTouchable(Touchable.enabled);
            this.k.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.6
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    Game.i.screenManager.setScreen(new AccountSetPasswordScreen());
                }
            });
            this.k.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.PURPLE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f)));
            Label label4 = new Label(Game.i.localeManager.i18n.get("account_password_not_set_hint"), Game.i.assetManager.getLabelStyle(24));
            label4.setWrap(true);
            label4.setAlignment(1);
            this.k.add((Table) label4).width(460.0f).padTop(16.0f).padBottom(16.0f);
            table2.add(this.k).width(500.0f).padBottom(24.0f).padTop(16.0f).row();
        }
        FancyButton fancyButton = new FancyButton("regularButton.a", () -> {
            Game.i.authManager.signOut();
        });
        fancyButton.withLabel(24, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-exit> " + Game.i.localeManager.i18n.get("sign_out")));
        table2.add(fancyButton).size(296.0f, 56.0f).padTop(32.0f).row();
        Table table5 = new Table();
        table.add(table5).width(800.0f);
        Label label5 = new Label(Game.i.localeManager.i18n.get("cloud_saves"), Game.i.assetManager.getLabelStyle(30));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table5.add((Table) label5).padBottom(8.0f).row();
        this.m = new Table();
        this.m.setWidth(800.0f);
        ScrollPane scrollPane = new ScrollPane(this.m, Game.i.assetManager.getScrollPaneStyle(0.0f));
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);
        table5.add((Table) scrollPane).width(800.0f).height(700.0f).row();
        Label label6 = new Label(Game.i.localeManager.i18n.get("auto_saves_hint"), Game.i.assetManager.getLabelStyle(24));
        label6.setWrap(true);
        label6.setAlignment(1);
        table5.add((Table) label6).width(500.0f).padTop(16.0f).row();
        this.l = new LabelToggleButton(Game.i.localeManager.i18n.get("auto_saves"), Game.i.authManager.isAutoSavesEnabled(), null);
        this.l.onToggle = bool -> {
            if (bool.booleanValue()) {
                if (Game.i.authManager.getCloudSaveSlotId() == -1) {
                    b();
                    Game.i.authManager.getCloudSavedGamesList(jsonValue -> {
                        int i = 0;
                        if (jsonValue != null) {
                            Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                            while (iterator2.hasNext()) {
                                iterator2.next();
                                i++;
                            }
                        }
                        if (i >= Game.i.authManager.getMaxCloudSaveSlots()) {
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("save_game_to_any_slot_first"));
                            Game.i.authManager.setAutoSavesEnabled(false);
                            this.l.setEnabled(false);
                        } else {
                            Notifications.i().add(Game.i.localeManager.i18n.get("game_will_be_auto_saved_to_new_slot"), Game.i.assetManager.getDrawable("icon-info"), null, StaticSoundType.NOTIFICATION);
                            Game.i.authManager.setAutoSavesEnabled(true);
                        }
                        c();
                    });
                    return;
                } else {
                    Game.i.authManager.setAutoSavesEnabled(true);
                    return;
                }
            }
            Game.i.authManager.setAutoSavesEnabled(false);
        };
        table5.add(this.l).height(96.0f).row();
        if (Game.i.authManager.isSignedIn()) {
            LabelButton labelButton = new LabelButton(Game.i.localeManager.i18n.get("lost_progress?"), Game.i.assetManager.getLabelStyle(24), () -> {
                if (Game.i.progressManager.isDeveloperModeEnabled()) {
                    Notifications.i().add("Could not load game while being in Developer mode, please reset your progress first", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                    return;
                }
                try {
                    Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                    httpRequest.setUrl(Config.GET_BACKUPS_TO_RESTORE_PREFERENCES_URL);
                    HashMap hashMap = new HashMap();
                    hashMap.put("sessionid", Game.i.authManager.getSessionId());
                    httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                    this.d.getTable().clearChildren();
                    Image image3 = new Image(Game.i.assetManager.getDrawable("loading-icon"));
                    image3.setOrigin(32.0f, 32.0f);
                    image3.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
                    this.d.getTable().add((Table) image3).size(64.0f, 64.0f);
                    this.d.getTable().setVisible(true);
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.screens.account.AccountScreen.7
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            try {
                                String resultAsString = httpResponse.getResultAsString();
                                AccountScreen.f2877a.i(resultAsString, new Object[0]);
                                JsonValue parse = new JsonReader().parse(resultAsString);
                                if (!parse.getString("status").equals("success")) {
                                    AccountScreen.f2877a.e(resultAsString, new Object[0]);
                                } else {
                                    Threads.i().runOnMainThread(() -> {
                                        AccountScreen.this.d.getTable().clearChildren();
                                        Label label7 = new Label(Game.i.localeManager.i18n.get("select_date_to_restore_game_state"), Game.i.assetManager.getLabelStyle(24));
                                        label7.setWrap(true);
                                        label7.setAlignment(1);
                                        AccountScreen.this.d.getTable().add((Table) label7).width(600.0f).padBottom(15.0f).row();
                                        boolean z = false;
                                        Iterator<JsonValue> iterator2 = parse.get("data").iterator2();
                                        while (iterator2.hasNext()) {
                                            JsonValue next = iterator2.next();
                                            int i = next.getInt("modified");
                                            String string = next.getString("url");
                                            z = true;
                                            LabelButton labelButton2 = new LabelButton(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).format(new Date(i * 1000)), Game.i.assetManager.getLabelStyle(30), () -> {
                                                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("restore_progress_to_date_confirm"), () -> {
                                                    String sessionId = Game.i.authManager.getSessionId();
                                                    Game.i.preferencesManager.loadFromUrl(string, bool2 -> {
                                                        if (!bool2.booleanValue()) {
                                                            AccountScreen.f2877a.e("Failed to load backup", new Object[0]);
                                                            return;
                                                        }
                                                        Game.i.preferencesManager.reapplyAllPreferences();
                                                        AccountScreen.this.d.getTable().setVisible(false);
                                                        Game.i.authManager.loadStateFromServer(sessionId, () -> {
                                                            AccountScreen.goToScreen();
                                                            AccountScreen.f2877a.i("new session: " + Game.i.authManager.getSessionId(), new Object[0]);
                                                            Notifications.i().add(Game.i.localeManager.i18n.get("game_loaded_from_cloud"), Game.i.assetManager.getDrawable("icon-info"), null, StaticSoundType.NOTIFICATION);
                                                        });
                                                    });
                                                });
                                                Dialog.i().makeConfirmButtonDisabled(2);
                                            });
                                            labelButton2.setAlignment(1);
                                            AccountScreen.this.d.getTable().add((Table) labelButton2).size(600.0f, 64.0f).row();
                                        }
                                        if (!z) {
                                            Label label8 = new Label(Game.i.localeManager.i18n.get("no_replays_found_cant_restore"), Game.i.assetManager.getLabelStyle(30));
                                            label8.setColor(MaterialColor.AMBER.P500);
                                            label8.setAlignment(1);
                                            AccountScreen.this.d.getTable().add((Table) label8).size(600.0f, 64.0f).row();
                                            AccountScreen.f2877a.i("no replays", new Object[0]);
                                        }
                                        AccountScreen.this.d.getTable().setVisible(true);
                                        FancyButton withLabel = new FancyButton("regularButton.a", () -> {
                                            AccountScreen.this.d.getTable().clearChildren();
                                            AccountScreen.this.d.getTable().setVisible(false);
                                        }).withLabel(30, Game.i.localeManager.i18n.get("cancel"));
                                        AccountScreen.this.d.getTable().row();
                                        AccountScreen.this.d.getTable().add(withLabel).padTop(15.0f).size(200.0f, 56.0f);
                                    });
                                }
                            } catch (Exception e) {
                                AccountScreen.f2877a.e("Failed to parse response", e);
                            }
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            AccountScreen.f2877a.e("Error", th);
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                            AccountScreen.f2877a.e("Error", new Object[0]);
                        }
                    });
                } catch (Exception e) {
                    f2877a.e("Error", e);
                }
            });
            labelButton.setAlignment(16);
            table5.add((Table) labelButton).padTop(8.0f).right().height(64.0f).width(300.0f);
        }
        this.e = new Table();
        this.f2878b.getTable().add(this.e).expand().bottom().padBottom(40.0f);
        if (Game.i.authManager.getSignInStatus() != AuthManager.SignInStatus.SIGNED_IN) {
            Game.i.authManager.loadStateFromServer(null, null);
        }
        if (Game.i.authManager.isSignedIn()) {
            ScreenTitle.i().setText(Game.i.localeManager.i18n.get("account_screen_title"));
            this.g.setDrawable(new TextureRegionDrawable(Game.i.authManager.getAvatar(64)));
            this.h.setText(Game.i.authManager.getNickname());
            this.i.setText(Game.i.authManager.getPlayerId());
            this.j.setText(Game.i.authManager.getEmailHint());
            Label label7 = this.f;
            if (Game.i.authManager.getSignInStatus() == AuthManager.SignInStatus.SIGNED_IN) {
                str = Game.i.localeManager.i18n.get("signed_in_online_as");
            } else {
                str = Game.i.localeManager.i18n.get("signed_in_offline_as");
            }
            label7.setText(str);
            if (this.k != null) {
                if (Game.i.authManager.isPasswordSet()) {
                    this.k.setVisible(false);
                } else {
                    this.k.setVisible(true);
                }
            }
            this.m.clearChildren();
            Label label8 = new Label(Game.i.localeManager.i18n.get("loading..."), Game.i.assetManager.getLabelStyle(30));
            label8.setColor(MaterialColor.AMBER.P500);
            this.m.add((Table) label8);
            Game.i.authManager.getCloudSavedGamesList(jsonValue -> {
                if (jsonValue == null) {
                    this.m.clearChildren();
                    Label label9 = new Label(Game.i.localeManager.i18n.get("failed_to_load_saved_games"), Game.i.assetManager.getLabelStyle(30));
                    label9.setColor(MaterialColor.RED.P500);
                    this.m.add((Table) label9);
                } else {
                    this.m.clearChildren();
                    this.n.clear();
                    int i = 0;
                    Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                    while (iterator2.hasNext()) {
                        JsonValue next = iterator2.next();
                        i++;
                        final int i2 = next.getInt("slotId");
                        Group group2 = new Group();
                        group2.setTransform(false);
                        this.m.add((Table) group2).size(800.0f, 160.0f).padBottom(4.0f).row();
                        Actor image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        if (Game.i.authManager.getCloudSaveSlotId() == i2) {
                            image3.setColor(new Color(61469747));
                        } else {
                            image3.setColor(1.0f, 1.0f, 1.0f, 0.07f);
                        }
                        image3.setSize(800.0f, 160.0f);
                        group2.addActor(image3);
                        Actor label10 = new Label("#" + i2 + "-" + next.getString("gameStartHash") + " (" + StringFormatter.timePassed(next.getInt("timeInGame"), false, false) + ")", Game.i.assetManager.getLabelStyle(30));
                        label10.setPosition(20.0f, 108.0f);
                        group2.addActor(label10);
                        String format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US).format(new Date(next.getInt("slotTimestamp") * 1000));
                        boolean z = Game.i.authManager.getCloudSaveSlotTimestamp() < next.getInt("slotTimestamp");
                        boolean z2 = z;
                        if (z) {
                            format = format + " - [#4CAF50]" + Game.i.localeManager.i18n.get("newer") + "[]";
                        }
                        Actor label11 = new Label(format, Game.i.assetManager.getLabelStyle(24));
                        label11.setPosition(20.0f, 70.0f);
                        label11.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        group2.addActor(label11);
                        FancyButton withLabel = new FancyButton("regularButton.a", () -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("load_game_from_cloud_confirm"), () -> {
                                Game.i.authManager.loadSavedGameFromServer(i2);
                            });
                            Dialog.i().makeConfirmButtonDisabled(3);
                        }).withLabel(36, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-cloud-download>"));
                        this.n.add(withLabel);
                        withLabel.setPosition(676.0f, 20.0f);
                        withLabel.setSize(104.0f, 64.0f);
                        if (z2) {
                            withLabel.setFlavor("regularGreenButton.a");
                        }
                        group2.addActor(withLabel);
                        FancyButton withLabel2 = new FancyButton("regularButton.b", () -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("overwrite_cloud_save_confirm"), () -> {
                                Game.i.authManager.saveGameToServer(i2, saveGameResult -> {
                                    goToScreen();
                                });
                            });
                            Dialog.i().makeConfirmButtonDisabled(3);
                        }).withLabel(36, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-floppy>"));
                        this.n.add(withLabel2);
                        if (Game.i.authManager.hasUnsavedProgressForCloud() && i2 == Game.i.authManager.getCloudSaveSlotId()) {
                            Image image4 = new Image(Game.i.assetManager.getQuad("ui.regularGreenButton.b.hover"));
                            image4.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.0f, 0.33f), Actions.alpha(1.0f, 0.33f))));
                            image4.setFillParent(true);
                            withLabel2.addActorAt(1, image4);
                        }
                        withLabel2.setPosition(560.0f, 20.0f);
                        withLabel2.setSize(104.0f, 64.0f);
                        group2.addActor(withLabel2);
                        FancyButton withLabel3 = new FancyButton("regularRedButton.a", () -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("delete_cloud_save_confirm"), () -> {
                                Game.i.authManager.deleteGameFromServer(i2, bool2 -> {
                                    goToScreen();
                                });
                            });
                            Dialog.i().makeConfirmButtonDisabled(2);
                        }).withLabel(36, Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-times>"));
                        this.n.add(withLabel3);
                        withLabel3.setPosition(468.0f, 20.0f);
                        withLabel3.setSize(80.0f, 64.0f);
                        group2.addActor(withLabel3);
                        if (Game.i.authManager.getCloudSaveSlotId() == i2) {
                            Label label12 = new Label(Game.i.localeManager.i18n.get("current").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
                            label12.setColor(MaterialColor.AMBER.P500);
                            label12.setAlignment(16);
                            label12.setSize(80.0f, 20.0f);
                            label12.setPosition(700.0f, 124.0f);
                            group2.addActor(label12);
                            if (Game.i.authManager.hasUnsavedProgressForCloud()) {
                                Label label13 = new Label(Game.i.localeManager.i18n.get("cloud_save_has_unsaved_progress"), Game.i.assetManager.getLabelStyle(21));
                                label13.setColor(MaterialColor.LIGHT_GREEN.P300);
                                label13.setAlignment(16);
                                label13.setSize(80.0f, 24.0f);
                                label13.setPosition(700.0f, 95.0f);
                                group2.addActor(label13);
                            }
                        }
                        int i3 = next.getInt("gameBuild");
                        Actor label14 = new Label(Game.i.localeManager.i18n.get("about_version") + ": B" + i3, Game.i.assetManager.getLabelStyle(21));
                        label14.setPosition(20.0f, 44.0f);
                        if (208 < i3) {
                            label14.setColor(MaterialColor.ORANGE.P500);
                        } else {
                            label14.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        }
                        group2.addActor(label14);
                        final String string = next.getString("note", null);
                        Label label15 = new Label(string == null ? Game.i.localeManager.i18n.get("click_here_to_add_note") : string, Game.i.assetManager.getLabelStyle(21));
                        if (string == null) {
                            label15.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        } else {
                            label15.setColor(MaterialColor.LIGHT_GREEN.P400);
                        }
                        label15.setPosition(20.0f, 15.0f);
                        label15.setSize(420.0f, 58.0f);
                        label15.setWrap(true);
                        label15.setAlignment(12);
                        group2.addActor(label15);
                        label15.setTouchable(Touchable.enabled);
                        label15.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.8
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                TextInputOverlay.i().show(new Input.TextInputListener() { // from class: com.prineside.tdi2.screens.account.AccountScreen.8.1
                                    @Override // com.badlogic.gdx.Input.TextInputListener
                                    public void input(String str2) {
                                        Threads i4 = Threads.i();
                                        int i5 = i2;
                                        i4.runOnMainThread(() -> {
                                            String sessionId = Game.i.authManager.getSessionId();
                                            if (sessionId != null) {
                                                Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                                                httpRequest.setUrl(Config.SET_CLOUD_SAVE_NOTE);
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("sessionid", sessionId);
                                                hashMap.put("slot", new StringBuilder().append(i5).toString());
                                                hashMap.put("note", str2 == null ? "" : str2);
                                                httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                                                Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.screens.account.AccountScreen.8.1.1
                                                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                                                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                                                        AccountScreen.f2877a.i(httpResponse.getResultAsString(), new Object[0]);
                                                        Threads.i().runOnMainThread(AccountScreen::goToScreen);
                                                    }

                                                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                                                    public void failed(Throwable th) {
                                                        AccountScreen.f2877a.e("SET_CLOUD_SAVE_HINT request failed", th);
                                                    }

                                                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                                                    public void cancelled() {
                                                    }
                                                });
                                            }
                                        });
                                    }

                                    @Override // com.badlogic.gdx.Input.TextInputListener
                                    public void canceled() {
                                    }
                                }, Game.i.localeManager.i18n.get("cloud_save_note_title"), string, null);
                            }
                        });
                    }
                    this.m.row();
                    this.m.add().growY().width(1.0f).row();
                    if (i < Game.i.authManager.getMaxCloudSaveSlots()) {
                        FancyButton withLabel4 = new FancyButton("regularButton.a", () -> {
                            Game.i.authManager.saveGameToServer(-1, saveGameResult -> {
                                goToScreen();
                            });
                        }).withLabel(24, Game.i.localeManager.i18n.get("save_game_to_new_slot"));
                        this.n.add(withLabel4);
                        this.m.add(withLabel4).size(400.0f, 80.0f).row();
                    }
                }
                f2877a.i(jsonValue == null ? "null" : jsonValue.toJson(JsonWriter.OutputType.json), new Object[0]);
            });
        }
        AuthManager.SignInStatus signInStatus = Game.i.authManager.getSignInStatus();
        this.e.clear();
        this.e.add((Table) new Label(Game.i.localeManager.i18n.get("status") + ": [#FFC107]" + signInStatus.name() + "[] " + (Game.i.actionResolver.isSignedInWithGoogle() ? "G" : ""), Game.i.assetManager.getLabelStyle(24))).padTop(64.0f).row();
        this.e.add((Table) new Label(Game.i.localeManager.i18n.get("nickname") + ": [#FFC107]" + Game.i.authManager.getNickname() + "[]", Game.i.assetManager.getLabelStyle(24))).row();
        this.e.add((Table) new Label("Playerid: [#FFC107]" + Game.i.authManager.getPlayerId() + "[]", Game.i.assetManager.getLabelStyle(24))).row();
    }

    private void b() {
        this.l.setEnabled(false);
        for (int i = 0; i < this.n.size; i++) {
            this.n.get(i).setEnabled(false);
        }
    }

    private void c() {
        this.l.setEnabled(true);
        for (int i = 0; i < this.n.size; i++) {
            this.n.get(i).setEnabled(true);
        }
    }

    @Override // com.prineside.tdi2.screens.account.GenericAccountScreen, com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        super.dispose();
        Game.i.uiManager.removeLayer(this.f2878b);
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.d);
        Game.i.authManager.removeListener(this.o);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountScreen$_AuthManagerListener.class */
    private class _AuthManagerListener extends AuthManager.AuthManagerListener.AuthManagerListenerAdapter {
        private _AuthManagerListener() {
        }

        /* synthetic */ _AuthManagerListener(AccountScreen accountScreen, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
        public void signInStatusUpdated() {
            if (Game.i.screenManager.getCurrentScreen() instanceof AccountScreen) {
                AccountScreen.goToScreen();
            }
        }

        @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
        public void autoSaveModeChanged() {
            AccountScreen.this.l.setEnabled(Game.i.authManager.isAutoSavesEnabled());
        }
    }
}

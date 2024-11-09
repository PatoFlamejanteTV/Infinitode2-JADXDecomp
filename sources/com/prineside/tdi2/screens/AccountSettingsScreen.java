package com.prineside.tdi2.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Purchase;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.screens.account.AccountScreen;
import com.prineside.tdi2.screens.account.AccountSetPasswordScreen;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/AccountSettingsScreen.class */
public class AccountSettingsScreen extends Screen {

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2741b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "AccountSettingsScreen");
    private boolean c;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2740a = TLog.forClass(AccountSettingsScreen.class);
    private static final DateFormat d = DateFormat.getDateInstance(2, Locale.US);
    private static final DateFormat e = DateFormat.getTimeInstance(2, Locale.US);

    public AccountSettingsScreen() {
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-user")).setText(Game.i.localeManager.i18n.get("account_settings_screen_title")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(AccountScreen::goToScreen);
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
    }

    public void refresh() {
        this.f2741b.getTable().clear();
        if (Game.i.authManager.getSignInStatus() != AuthManager.SignInStatus.SIGNED_IN) {
            Label label = new Label(Game.i.localeManager.i18n.get("not_signed_in"), Game.i.assetManager.getLabelStyle(30));
            label.setAlignment(1);
            this.f2741b.getTable().add((Table) label).growX().row();
            FancyButton withLabel = new FancyButton("regularButton.a", null).withLabel(30, Game.i.localeManager.i18n.get("sign_in"));
            withLabel.setClickHandler(() -> {
                withLabel.setEnabled(false);
                Game.i.authManager.loadStateFromServer(null, () -> {
                    if (Game.i.authManager.getSignInStatus() == AuthManager.SignInStatus.SIGNED_IN) {
                        refresh();
                    } else {
                        Notifications.i().add(Game.i.localeManager.i18n.get("load_state_from_server_failed") + SequenceUtils.EOL + Game.i.authManager.getSignInStatus(), null, MaterialColor.RED.P800, StaticSoundType.FAIL);
                        withLabel.setEnabled(true);
                    }
                });
            });
            this.f2741b.getTable().add(withLabel).size(192.0f, 48.0f).padTop(15.0f).row();
            return;
        }
        Image image = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        image.setOrigin(32.0f, 32.0f);
        image.addAction(Actions.forever(Actions.rotateBy(180.0f, 1.0f)));
        this.f2741b.getTable().add((Table) image).size(64.0f);
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.GET_ACCOUNT_SETTINGS_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        hashMap.put("locale", Game.i.localeManager.getLocale());
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        double percentValueAsMultiplier = 1.0f + ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.SHOP_PURCHASE_BONUS));
        int papersHourBasePrice = Game.i.purchaseManager.getPapersHourBasePrice();
        json.writeObjectStart("shopStats");
        json.writeValue("purchaseMultiplier", String.valueOf(percentValueAsMultiplier));
        json.writeValue("papersPerHour", String.valueOf(papersHourBasePrice));
        json.writeObjectEnd();
        json.writeArrayStart("transactions");
        PP_Purchase pP_Purchase = ProgressPrefs.i().purchase;
        for (int i = 0; i < pP_Purchase.getTransactions().size; i++) {
            Transaction transaction = pP_Purchase.getTransactions().get(i);
            json.writeObjectStart();
            json.writeValue("identifier", transaction.getIdentifier());
            json.writeValue("purchaseCost", Integer.valueOf(transaction.getPurchaseCost()));
            json.writeValue("storeName", transaction.getStoreName());
            json.writeValue("orderId", transaction.getOrderId());
            json.writeValue("requestId", transaction.getRequestId());
            json.writeValue("userId", transaction.getUserId());
            json.writeValue("purchaseTime", transaction.getPurchaseTime() == null ? null : Integer.valueOf((int) (transaction.getPurchaseTime().getTime() / 1000)));
            json.writeValue("purchaseText", transaction.getPurchaseText());
            json.writeValue("purchaseCostCurrency", transaction.getPurchaseCostCurrency());
            json.writeValue("reversalTime", transaction.getReversalTime() == null ? null : Integer.valueOf((int) (transaction.getReversalTime().getTime() / 1000)));
            json.writeValue("reversalText", transaction.getReversalText());
            json.writeValue("transactionData", transaction.getTransactionData());
            json.writeValue("transactionDataSignature", transaction.getTransactionDataSignature());
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
        hashMap.put("data", stringWriter.toString());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.screens.AccountSettingsScreen.1
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                AccountSettingsScreen.f2740a.i("getAccountSettings server: " + resultAsString, new Object[0]);
                Threads.i().runOnMainThread(() -> {
                    try {
                        AccountSettingsScreen.this.a(new JsonReader().parse(resultAsString));
                    } catch (Exception e2) {
                        AccountSettingsScreen.f2740a.e("failed to parse response from server: " + resultAsString, e2);
                    }
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                AccountSettingsScreen.f2740a.e("failed to get account settings", th);
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JsonValue jsonValue) {
        Label label;
        String str;
        this.f2741b.getTable().clear();
        Table table = new Table();
        ScrollPane scrollPane = new ScrollPane(table, Game.i.assetManager.getScrollPaneStyle(16.0f));
        scrollPane.setScrollingDisabled(true, false);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        this.f2741b.getTable().add((Table) scrollPane).expand().fill();
        table.add().width(1.0f).height(120.0f).row();
        Table table2 = new Table();
        table.add(table2).width(960.0f).padBottom(15.0f).row();
        Label label2 = new Label(Game.i.authManager.getNickname(), Game.i.assetManager.getLabelStyle(36));
        label2.setColor(MaterialColor.CYAN.P500);
        table2.add((Table) label2).growX().row();
        Label label3 = new Label(Game.i.authManager.getPlayerId(), Game.i.assetManager.getLabelStyle(24));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label3).growX().row();
        Label label4 = new Label(Game.i.authManager.getEmailHint() + ((Object) (jsonValue.getBoolean("email_confirmed") ? Game.i.assetManager.replaceRegionAliasesWithChars(" <@icon-check>") : "")), Game.i.assetManager.getLabelStyle(24));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label4).growX().row();
        if (jsonValue.getBoolean("email_set") && !jsonValue.getBoolean("email_confirmed")) {
            Table table3 = new Table();
            table3.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f)));
            Label label5 = new Label(Game.i.localeManager.i18n.get("email_not_confirmed"), Game.i.assetManager.getLabelStyle(24));
            label5.setWrap(true);
            table3.add((Table) label5).pad(15.0f).padLeft(40.0f).width(570.0f);
            FancyButton withLabel = new FancyButton("regularButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("confirm_email_button"));
            withLabel.setClickHandler(() -> {
                withLabel.setEnabled(false);
                Game.i.authManager.confirmEmail(confirmEmailResult -> {
                    switch (confirmEmailResult) {
                        case SUCCESS:
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("email_confirm_message_sent"));
                            return;
                        case ALREADY_CONFIRMED:
                            return;
                        case TOO_MANY_ATTEMPTS:
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("too_many_attempts"));
                            return;
                        case OTHER_ERROR:
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("unknown_error"));
                            return;
                        default:
                            return;
                    }
                });
            });
            table3.add(withLabel).left().height(48.0f).pad(15.0f).growX().row();
            table.add(table3).width(960.0f).padBottom(15.0f).row();
        }
        if (!jsonValue.getBoolean("password_set")) {
            Table table4 = new Table();
            table4.setTouchable(Touchable.enabled);
            table4.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AccountSettingsScreen.2
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    Game.i.screenManager.setScreen(new AccountSetPasswordScreen());
                }
            });
            table4.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.PURPLE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f)));
            Label label6 = new Label(Game.i.localeManager.i18n.get("account_password_not_set_hint"), Game.i.assetManager.getLabelStyle(24));
            label6.setWrap(true);
            table4.add((Table) label6).pad(15.0f).padLeft(40.0f).padRight(40.0f).growX();
            table.add(table4).width(960.0f).padBottom(15.0f).row();
        }
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if (!jsonValue.getBoolean("steam_linked")) {
                Table table5 = new Table();
                table5.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f)));
                Table table6 = new Table();
                table5.add(table6).pad(15.0f).padLeft(40.0f).width(570.0f);
                Label label7 = new Label(Game.i.localeManager.i18n.get("steam_account_not_linked"), Game.i.assetManager.getLabelStyle(24));
                label7.setWrap(true);
                table6.add((Table) label7).growX().row();
                Label label8 = new Label(Game.i.localeManager.i18n.get("steam_account_link_benefits"), Game.i.assetManager.getLabelStyle(18));
                label8.setWrap(true);
                table6.add((Table) label8).growX().padTop(5.0f).row();
                FancyButton withLabel2 = new FancyButton("regularButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("link_steam_button"));
                withLabel2.setClickHandler(() -> {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("link_steam_button_confirm"), () -> {
                        withLabel2.setEnabled(false);
                        Game.i.authManager.linkSteamAccount(bool -> {
                            Game.i.screenManager.goToAccountSettingsScreen();
                        });
                    });
                });
                table5.add(withLabel2).left().height(48.0f).pad(15.0f).growX().row();
                table.add(table5).width(960.0f).padBottom(15.0f).row();
            } else {
                Table table7 = new Table();
                table7.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f)));
                Label label9 = new Label(Game.i.localeManager.i18n.get("steam_account_linked"), Game.i.assetManager.getLabelStyle(24));
                label9.setWrap(true);
                label9.setColor(MaterialColor.LIGHT_GREEN.P300);
                table7.add((Table) label9).padBottom(10.0f).padTop(10.0f).padLeft(40.0f).padRight(40.0f).growX().row();
                table.add(table7).width(960.0f).padBottom(15.0f).row();
            }
        }
        table.add((Table) new Label(Game.i.localeManager.i18n.get("account_statuses"), Game.i.assetManager.getLabelStyle(36))).width(960.0f).padTop(40.0f).row();
        Label label10 = new Label(Game.i.localeManager.i18n.get("account_statuses_hint"), Game.i.assetManager.getLabelStyle(21));
        label10.setWrap(true);
        label10.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label10).width(960.0f).padBottom(15.0f).row();
        if (jsonValue.get("profile_statuses").size > 0) {
            Iterator<JsonValue> iterator2 = jsonValue.get("profile_statuses").iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                JsonValue jsonValue2 = next.get("current_status");
                Table table8 = new Table();
                if (jsonValue2 == null) {
                    table8.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
                } else {
                    table8.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.14f)));
                }
                table.add(table8).width(960.0f).padBottom(4.0f).row();
                Table table9 = new Table();
                table8.add(table9).padLeft(40.0f).padRight(40.0f).padTop(20.0f).growX().row();
                Label label11 = new Label(next.getString(Attribute.TITLE_ATTR), Game.i.assetManager.getLabelStyle(30));
                if (jsonValue2 != null) {
                    label11.setColor(MaterialColor.LIGHT_GREEN.P500);
                }
                table9.add((Table) label11).growX();
                if (jsonValue2 == null) {
                    Label label12 = new Label(Game.i.localeManager.i18n.get("account_status_not_linked"), Game.i.assetManager.getLabelStyle(24));
                    label = label12;
                    label12.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                } else {
                    Label label13 = new Label(Game.i.localeManager.i18n.get("account_status_linked"), Game.i.assetManager.getLabelStyle(24));
                    label = label13;
                    label13.setColor(MaterialColor.LIGHT_GREEN.P500);
                }
                table9.add((Table) label);
                Label label14 = new Label(next.getString("description"), Game.i.assetManager.getLabelStyle(21));
                label14.setColor(1.0f, 1.0f, 1.0f, 0.78f);
                label14.setWrap(true);
                table8.add((Table) label14).padLeft(40.0f).padRight(40.0f).padBottom(15.0f).growX().row();
                if (jsonValue2 != null) {
                    Label label15 = new Label(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-check> " + jsonValue2.getString("reason")), Game.i.assetManager.getLabelStyle(18));
                    label15.setColor(MaterialColor.LIGHT_GREEN.P500);
                    table8.add((Table) label15).padLeft(40.0f).padRight(40.0f).growX().row();
                    int i = jsonValue2.getInt("received_at");
                    int i2 = jsonValue2.get("removed_at").isNull() ? -1 : jsonValue2.getInt("removed_at");
                    String str2 = (d.format(new Date(i * 1000)) + SequenceUtils.SPACE + e.format(new Date(i * 1000))) + " - ";
                    if (i2 == -1) {
                        str = str2 + "Permanently";
                    } else {
                        str = str2 + d.format(new Date(i2 * 1000)) + SequenceUtils.SPACE + e.format(new Date(i2 * 1000));
                    }
                    Label label16 = new Label(str, Game.i.assetManager.getLabelStyle(18));
                    label16.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table8.add((Table) label16).padLeft(40.0f).padRight(40.0f).padBottom(15.0f).growX().row();
                }
                if (jsonValue2 != null) {
                    if (Config.PROFILE_STATUS_DOUBLE_GAIN.equals(next.getString(Attribute.ID_ATTR)) && !Game.i.progressManager.hasPermanentDoubleGain()) {
                        FancyButton withLabel3 = new FancyButton("regularGreenButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("enable_account_status_button"));
                        withLabel3.setClickHandler(() -> {
                            Game.i.progressManager.enableDoubleGainPermanently();
                            Game.i.screenManager.goToAccountSettingsScreen();
                        });
                        table8.add(withLabel3).left().padLeft(40.0f).size(240.0f, 48.0f).padBottom(15.0f).row();
                    }
                } else if (Config.PROFILE_STATUS_DOUBLE_GAIN.equals(next.getString(Attribute.ID_ATTR))) {
                    Iterator<JsonValue> iterator22 = jsonValue.get("transactions").iterator2();
                    while (iterator22.hasNext()) {
                        JsonValue next2 = iterator22.next();
                        if (next2.getBoolean("valid") && (next2.getString("identifier").equals("double_gain_infinitode2") || next2.getString("identifier").equals(Config.PROFILE_STATUS_DOUBLE_GAIN))) {
                            FancyButton withLabel4 = new FancyButton("regularGreenButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("account_status_enable_with_purchase") + " (" + next2.getString("store_name") + ")");
                            withLabel4.setClickHandler(() -> {
                                withLabel4.setEnabled(false);
                                Json json = new Json(JsonWriter.OutputType.json);
                                StringWriter stringWriter = new StringWriter();
                                json.setWriter(stringWriter);
                                json.writeObjectStart();
                                json.writeValue("type", "transaction");
                                json.writeValue("store_name", next2.getString("store_name"));
                                json.writeValue("order_id", next2.getString("order_id"));
                                json.writeObjectEnd();
                                Game.i.authManager.linkAccountStatus(stringWriter.toString(), str3 -> {
                                    if (str3 == null) {
                                        Notifications.i().add(Game.i.localeManager.i18n.get("account_status_link_success"), null, MaterialColor.LIGHT_GREEN.P800, StaticSoundType.SUCCESS);
                                    } else {
                                        Notifications.i().add(Game.i.localeManager.i18n.get("account_status_link_error") + SequenceUtils.EOL + str3, null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                                    }
                                    Game.i.screenManager.goToAccountSettingsScreen();
                                });
                            });
                            table8.add(withLabel4).left().padLeft(40.0f).size(400.0f, 48.0f).padBottom(15.0f).row();
                        }
                    }
                    if (Game.i.actionResolver.doubleGainEnabledBySteamGamePurchase()) {
                        Label label17 = new Label(Game.i.localeManager.i18n.get("steam_game_purchase_found_can_link_dg"), Game.i.assetManager.getLabelStyle(21));
                        label17.setWrap(true);
                        table8.add((Table) label17).fillX().padLeft(40.0f).padRight(40.0f).padTop(15.0f).padBottom(15.0f).row();
                        FancyButton withLabel5 = new FancyButton("regularGreenButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("account_status_enable_with_steam_link"));
                        withLabel5.setClickHandler(() -> {
                            withLabel5.setEnabled(false);
                            Game.i.authManager.linkSteamAccount(bool -> {
                                Game.i.screenManager.goToAccountSettingsScreen();
                            });
                        });
                        table8.add(withLabel5).left().padLeft(40.0f).size(400.0f, 48.0f).padBottom(15.0f).row();
                    }
                }
            }
        } else {
            Label label18 = new Label(Game.i.localeManager.i18n.get("account_has_no_statuses"), Game.i.assetManager.getLabelStyle(24));
            label18.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label18).width(960.0f).padBottom(15.0f).row();
        }
        String playerId = Game.i.authManager.getPlayerId();
        Table table10 = new Table();
        table10.add((Table) new Label(Game.i.localeManager.i18n.get("transactions"), Game.i.assetManager.getLabelStyle(36)));
        table10.add().height(1.0f).growX();
        if (Game.i.purchaseManager.isPurchasesEnabled()) {
            FancyButton withLabel6 = new FancyButton("regularButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("settings_restore_purchases"));
            withLabel6.setClickHandler(() -> {
                Game.i.purchaseManager.purchaseManager.purchaseRestore();
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("settings_purchases_restored"));
                refresh();
            });
            table10.add(withLabel6).height(48.0f).width(220.0f).pad(15.0f);
        }
        table10.row();
        table.add(table10).width(960.0f).padTop(40.0f).padBottom(15.0f).row();
        if (jsonValue.get("transactions").size > 0) {
            Table table11 = new Table();
            table.add(table11).pad(15.0f).width(930.0f).row();
            Label label19 = new Label("Store", Game.i.assetManager.getLabelStyle(24));
            label19.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table11.add((Table) label19).width(150.0f);
            Label label20 = new Label("Order ID", Game.i.assetManager.getLabelStyle(24));
            label20.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table11.add((Table) label20).growX();
            Label label21 = new Label("Date", Game.i.assetManager.getLabelStyle(24));
            label21.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table11.add((Table) label21).width(150.0f);
            table11.add().height(1.0f).width(64.0f);
            table11.add().height(1.0f).width(64.0f);
            Array array = new Array();
            Iterator<JsonValue> iterator23 = jsonValue.get("transactions").iterator2();
            while (iterator23.hasNext()) {
                array.add(iterator23.next());
            }
            array.sort((jsonValue3, jsonValue4) -> {
                return Integer.compare(jsonValue4.getInt("purchase_time"), jsonValue3.getInt("purchase_time"));
            });
            int i3 = 0;
            while (true) {
                if (i3 < array.size) {
                    if (!this.c && i3 == 10) {
                        FancyButton withLabel7 = new FancyButton("regularButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("show_more_button"));
                        withLabel7.setClickHandler(() -> {
                            this.c = true;
                            refresh();
                        });
                        table.add(withLabel7).height(48.0f).width(220.0f).pad(15.0f).row();
                        break;
                    }
                    JsonValue jsonValue5 = (JsonValue) array.get(i3);
                    Table table12 = new Table();
                    table12.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
                    if (!jsonValue5.getBoolean("valid") || !jsonValue5.getString("playerid").equals(playerId)) {
                        table12.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.14f)));
                    }
                    table.add(table12).width(960.0f).padBottom(4.0f).row();
                    Table table13 = new Table();
                    table12.add(table13).pad(15.0f).width(930.0f);
                    table13.add((Table) new Label(jsonValue5.getString("store_name"), Game.i.assetManager.getLabelStyle(24))).width(150.0f);
                    Table table14 = new Table();
                    table14.add((Table) new Label(jsonValue5.getString("order_id"), Game.i.assetManager.getLabelStyle(24))).growX().row();
                    Label label22 = new Label(jsonValue5.getString("identifier"), Game.i.assetManager.getLabelStyle(21));
                    label22.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table14.add((Table) label22).growX().row();
                    table13.add(table14).growX();
                    table13.add((Table) new Label(d.format(new Date(jsonValue5.getInt("purchase_time") * 1000)), Game.i.assetManager.getLabelStyle(24))).width(150.0f);
                    if (!jsonValue5.getString("playerid").equals(playerId)) {
                        final String string = jsonValue5.getString("playerid");
                        Image image = new Image(Game.i.assetManager.getDrawable("icon-user"));
                        image.setColor(MaterialColor.ORANGE.P500);
                        image.setTouchable(Touchable.enabled);
                        image.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AccountSettingsScreen.3
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("transaction_warning_wrong_owner") + SequenceUtils.SPACE + string);
                            }
                        });
                        table13.add((Table) image).height(32.0f).width(32.0f).padLeft(16.0f).padRight(16.0f);
                    } else {
                        table13.add().height(1.0f).width(64.0f);
                    }
                    if (jsonValue5.getBoolean("valid")) {
                        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-check"));
                        image2.setColor(MaterialColor.LIGHT_GREEN.P500);
                        image2.setTouchable(Touchable.enabled);
                        image2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AccountSettingsScreen.4
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("transaction_info_valid"));
                            }
                        });
                        table13.add((Table) image2).height(32.0f).width(32.0f).padLeft(16.0f).padRight(16.0f);
                    } else {
                        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-times"));
                        image3.setColor(MaterialColor.ORANGE.P500);
                        image3.setTouchable(Touchable.enabled);
                        image3.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.AccountSettingsScreen.5
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("transaction_info_invalid"));
                            }
                        });
                        table13.add((Table) image3).height(32.0f).width(32.0f).padLeft(16.0f).padRight(16.0f);
                    }
                    i3++;
                } else {
                    break;
                }
            }
        } else {
            Label label23 = new Label(Game.i.localeManager.i18n.get("account_has_no_transactions"), Game.i.assetManager.getLabelStyle(24));
            label23.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label23).width(960.0f).padBottom(15.0f).row();
        }
        table.add().growY().row();
        table.add().width(1.0f).height(120.0f).row();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        refresh();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f2741b);
    }
}

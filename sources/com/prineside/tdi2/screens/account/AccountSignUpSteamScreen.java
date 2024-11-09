package com.prineside.tdi2.screens.account;

import com.badlogic.gdx.Gdx;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountSignUpSteamScreen.class */
public class AccountSignUpSteamScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2892a;

    public AccountSignUpSteamScreen() {
        super(Game.i.localeManager.i18n.get("sign_up") + " / Steam", AccountScreen::goToScreen);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        String str = " (" + Game.i.localeManager.i18n.get("optional").toLowerCase(Locale.US) + ")";
        Label label = new Label(Game.i.localeManager.i18n.get("nickname"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform).size(400.0f, 56.0f).padBottom(16.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("email") + str, Game.i.assetManager.getLabelStyle(30));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label2).padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        table.add((Table) textFieldXPlatform2).size(400.0f, 56.0f).padBottom(8.0f).row();
        Label label3 = new Label(Game.i.localeManager.i18n.get("email_filed_pro_tip"), Game.i.assetManager.getLabelStyle(21));
        label3.setWrap(true);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label3.setAlignment(1);
        table.add((Table) label3).minWidth(1200.0f).growX().padBottom(8.0f).row();
        table.add((Table) new LabelButton(Game.i.localeManager.i18n.get("why_account_link"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.WHY_ACCOUNT_URL);
        })).height(48.0f).padBottom(16.0f).row();
        Label label4 = new Label(Game.i.localeManager.i18n.get("invite_code") + str, Game.i.assetManager.getLabelStyle(30));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label4).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform3).size(400.0f, 56.0f).padBottom(8.0f).row();
        Label label5 = new Label(Game.i.localeManager.i18n.get("invite_code_sign_up_hint"), Game.i.assetManager.getLabelStyle(21));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label5.setAlignment(1);
        label5.setWrap(true);
        table.add((Table) label5).fillX().row();
        this.f2892a = new FancyButton("regularButton.b", () -> {
            String text = textFieldXPlatform.getText();
            String trim = textFieldXPlatform2.getText().trim();
            String trim2 = textFieldXPlatform3.getText().trim();
            if (text.length() < 3) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_too_short"));
                return;
            }
            if (!Pattern.compile("^[a-zA-Z0-9_]+$").matcher(text).matches()) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                return;
            }
            if (trim.length() != 0 && !StringFormatter.VALID_EMAIL_ADDRESS_REGEX.matcher(trim).matches()) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("email_is_invalid"));
                return;
            }
            this.f2892a.label.setText(Game.i.localeManager.i18n.get("signing_up..."));
            a();
            Game.i.authManager.signUpWithSteam(text, trim, trim2, signUpResult -> {
                switch (signUpResult) {
                    case INVALID_EMAIL:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("email_is_invalid"));
                        break;
                    case INVALID_LOGIN:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                        break;
                    case INVALID_PASSWORD:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("password_is_too_short"));
                        break;
                    case TOO_MANY_ATTEMPTS:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("too_many_sign_ups"));
                        break;
                    case EMAIL_ALREADY_EXISTS:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("email_already_exists"));
                        break;
                    case NICKNAME_ALREADY_EXISTS:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("nickname_already_exists"));
                        break;
                    case OTHER_ERROR:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("unknown_error"));
                        break;
                    case SUCCESS:
                        Notifications.i().addSuccess(Game.i.localeManager.i18n.get("signed_in_online_as") + SequenceUtils.SPACE + Game.i.authManager.getNickname());
                        AccountScreen.goToScreen();
                        break;
                }
                b();
            });
        }).withLabel(30, Game.i.localeManager.i18n.get("sign_up"));
        table.add(this.f2892a).size(400.0f, 64.0f).padTop(16.0f).row();
        table.add((Table) new LabelButton(Game.i.localeManager.i18n.get("privacy_policy"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.PRIVACY_POLICY_URL);
        })).height(32.0f).padTop(32.0f).padBottom(16.0f).row();
        table.add((Table) new LabelButton(Game.i.localeManager.i18n.get("terms_and_conditions"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.TERMS_AND_CONDITIONS_URL);
        })).height(32.0f).padBottom(40.0f);
    }

    private void a() {
        this.f2892a.setEnabled(false);
    }

    private void b() {
        this.f2892a.label.setText(Game.i.localeManager.i18n.get("sign_up"));
        this.f2892a.setEnabled(true);
    }
}

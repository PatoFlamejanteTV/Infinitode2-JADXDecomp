package com.prineside.tdi2.screens.account;

import com.badlogic.gdx.Application;
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

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountSignUpScreen.class */
public class AccountSignUpScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2890a;

    public AccountSignUpScreen() {
        super(Game.i.localeManager.i18n.get("sign_up"), AccountScreen::goToScreen);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        Table table2 = new Table();
        table.add(table2).colspan(2).height(64.0f).minWidth(1.0f).padTop(16.0f).padBottom(32.0f).row();
        if (Game.i.actionResolver.hasGoogleAuth()) {
            table2.add((Table) AccountScreen.createGoogleSignInButton(this::a, this::b, true)).size(320.0f, 64.0f);
        }
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if (Game.i.actionResolver.hasGoogleAuth()) {
                table2.add().height(1.0f).width(16.0f);
            }
            table2.add((Table) AccountScreen.createSteamSignInButton(this::a, this::b, true)).size(320.0f, 64.0f);
        }
        String str = " (" + Game.i.localeManager.i18n.get("optional").toLowerCase(Locale.US) + ")";
        Label label = new Label(Game.i.localeManager.i18n.get("nickname"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).size(320.0f, 48.0f).colspan(2).padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform).size(320.0f, 56.0f).colspan(2).padBottom(16.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("email") + str, Game.i.assetManager.getLabelStyle(30));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label2).size(320.0f, 48.0f).colspan(2).padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        table.add((Table) textFieldXPlatform2).size(320.0f, 56.0f).colspan(2).padBottom(16.0f).row();
        Label label3 = new Label(Game.i.localeManager.i18n.get("email_filed_pro_tip"), Game.i.assetManager.getLabelStyle(21));
        label3.setWrap(true);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label3.setAlignment(1);
        table.add((Table) label3).growX().minWidth(1200.0f).colspan(2).padBottom(8.0f).row();
        Label label4 = new Label(Game.i.localeManager.i18n.get("password"), Game.i.assetManager.getLabelStyle(30));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label4.setAlignment(1);
        table.add((Table) label4).size(320.0f, 48.0f).right().padRight(24.0f).padBottom(8.0f);
        Label label5 = new Label(Game.i.localeManager.i18n.get("repeat_password"), Game.i.assetManager.getLabelStyle(30));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label5.setAlignment(1);
        table.add((Table) label5).size(320.0f, 48.0f).left().padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        textFieldXPlatform3.setPasswordMode(true);
        textFieldXPlatform3.setPasswordCharacter('*');
        table.add((Table) textFieldXPlatform3).size(320.0f, 56.0f).padBottom(16.0f).padRight(24.0f).right();
        TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        textFieldXPlatform4.setPasswordMode(true);
        textFieldXPlatform4.setPasswordCharacter('*');
        table.add((Table) textFieldXPlatform4).size(320.0f, 56.0f).padBottom(16.0f).left().row();
        Label label6 = new Label(Game.i.localeManager.i18n.get("invite_code") + " (" + Game.i.localeManager.i18n.get("optional").toLowerCase(Locale.ENGLISH) + ")", Game.i.assetManager.getLabelStyle(30));
        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label6).height(64.0f).colspan(2).row();
        TextFieldXPlatform textFieldXPlatform5 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform5).size(320.0f, 56.0f).padBottom(8.0f).colspan(2).row();
        Label label7 = new Label(Game.i.localeManager.i18n.get("invite_code_sign_up_hint"), Game.i.assetManager.getLabelStyle(21));
        label7.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label7.setAlignment(1);
        label7.setWrap(true);
        label7.setWidth(600.0f);
        table.add((Table) label7).width(600.0f).colspan(2).row();
        this.f2890a = new FancyButton("regularButton.a", () -> {
            String text = textFieldXPlatform.getText();
            String text2 = textFieldXPlatform3.getText();
            String text3 = textFieldXPlatform4.getText();
            String trim = textFieldXPlatform2.getText().trim();
            String trim2 = textFieldXPlatform5.getText().trim();
            if (text.length() < 3) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_too_short"));
                return;
            }
            if (!Pattern.compile("^[a-zA-Z0-9_]+$").matcher(text).matches()) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                return;
            }
            if (text2.length() < 6) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("password_is_too_short"));
                return;
            }
            if (!text2.equals(text3)) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("passwords_do_not_match"));
                return;
            }
            if (trim.length() != 0 && !StringFormatter.VALID_EMAIL_ADDRESS_REGEX.matcher(trim).matches()) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("email_is_invalid"));
                return;
            }
            this.f2890a.label.setText(Game.i.localeManager.i18n.get("signing_up..."));
            a();
            Game.i.authManager.signUp(text, text2, trim, trim2, signUpResult -> {
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
        table.add(this.f2890a).size(320.0f, 64.0f).padTop(16.0f).colspan(2).row();
        table.add((Table) new LabelButton(Game.i.localeManager.i18n.get("why_account_link"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.WHY_ACCOUNT_URL);
        })).height(40.0f).padTop(32.0f).padBottom(8.0f).colspan(2).row();
        LabelButton labelButton = new LabelButton(Game.i.localeManager.i18n.get("privacy_policy"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.PRIVACY_POLICY_URL);
        });
        labelButton.setAlignment(1);
        table.add((Table) labelButton).height(40.0f).padTop(8.0f).width(320.0f).right().padRight(20.0f).padBottom(16.0f);
        LabelButton labelButton2 = new LabelButton(Game.i.localeManager.i18n.get("terms_and_conditions"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.TERMS_AND_CONDITIONS_URL);
        });
        labelButton2.setAlignment(1);
        table.add((Table) labelButton2).height(40.0f).padTop(8.0f).width(320.0f).padLeft(20.0f).left().padBottom(16.0f);
    }

    private void a() {
        this.f2890a.setEnabled(false);
    }

    private void b() {
        this.f2890a.setEnabled(true);
        this.f2890a.label.setText(Game.i.localeManager.i18n.get("sign_up"));
    }
}

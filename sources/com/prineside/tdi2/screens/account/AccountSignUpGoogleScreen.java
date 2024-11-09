package com.prineside.tdi2.screens.account;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountSignUpGoogleScreen.class */
public class AccountSignUpGoogleScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2888a;

    public AccountSignUpGoogleScreen() {
        super(Game.i.localeManager.i18n.get("sign_up") + " / Google", AccountScreen::goToScreen);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        String str = " (" + Game.i.localeManager.i18n.get("optional").toLowerCase(Locale.US) + ")";
        Label label = new Label(Game.i.localeManager.i18n.get("nickname"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).padBottom(8.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform).size(400.0f, 56.0f).padBottom(16.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("invite_code") + str, Game.i.assetManager.getLabelStyle(30));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label2).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        table.add((Table) textFieldXPlatform2).size(400.0f, 56.0f).padBottom(8.0f).row();
        Label label3 = new Label(Game.i.localeManager.i18n.get("invite_code_sign_up_hint"), Game.i.assetManager.getLabelStyle(21));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label3.setAlignment(1);
        label3.setWrap(true);
        table.add((Table) label3).fillX().row();
        this.f2888a = new FancyButton("regularButton.a", () -> {
            String trim = textFieldXPlatform.getText().trim();
            String trim2 = textFieldXPlatform2.getText().trim();
            if (trim.length() < 3) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_too_short"));
            } else {
                if (!Pattern.compile("^[a-zA-Z0-9_]+$").matcher(trim).matches()) {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                    return;
                }
                this.f2888a.label.setText(Game.i.localeManager.i18n.get("signing_up..."));
                a();
                Game.i.authManager.signUpWithGoogle(trim, trim2, signUpResult -> {
                    Threads.i().runOnMainThread(() -> {
                        switch (signUpResult) {
                            case INVALID_LOGIN:
                                Notifications.i().addFailure(Game.i.localeManager.i18n.get("nickname_is_invalid"));
                                return;
                            case TOO_MANY_ATTEMPTS:
                                Notifications.i().addFailure(Game.i.localeManager.i18n.get("too_many_sign_ups"));
                                return;
                            case NICKNAME_ALREADY_EXISTS:
                                Notifications.i().addFailure(Game.i.localeManager.i18n.get("nickname_already_exists"));
                                return;
                            case SUCCESS:
                                AccountScreen.goToScreen();
                                return;
                            default:
                                Notifications.i().addFailure(Game.i.localeManager.i18n.get("unknown_error"));
                                return;
                        }
                    });
                });
            }
        }).withLabel(30, Game.i.localeManager.i18n.get("sign_up"));
        table.add(this.f2888a).size(320.0f, 64.0f).padTop(32.0f).padBottom(64.0f).row();
    }

    private void a() {
        this.f2888a.setEnabled(false);
    }
}

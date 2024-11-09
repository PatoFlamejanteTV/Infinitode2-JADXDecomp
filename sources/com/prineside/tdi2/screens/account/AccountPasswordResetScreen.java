package com.prineside.tdi2.screens.account;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountPasswordResetScreen.class */
public class AccountPasswordResetScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2875a;

    public AccountPasswordResetScreen() {
        super(Game.i.localeManager.i18n.get("reset_password"), AccountScreen::goToScreen);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        Label label = new Label(Game.i.localeManager.i18n.get("email_or_nickname"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        table.add((Table) textFieldXPlatform).size(400.0f, 56.0f).row();
        this.f2875a = new FancyButton("regularButton.b", () -> {
            String trim = textFieldXPlatform.getText().trim();
            if (!StringFormatter.VALID_EMAIL_ADDRESS_REGEX.matcher(trim).matches() && !Pattern.compile("^[a-zA-Z0-9_]+$").matcher(trim).matches()) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("email_or_nickname_is_incorrect"));
                return;
            }
            Notifications.i().add(Game.i.localeManager.i18n.get("requesting..."), Game.i.assetManager.getDrawable("icon-user"), MaterialColor.LIGHT_BLUE.P800, null);
            a();
            Game.i.authManager.resetPassword(trim, passwordResetResult -> {
                switch (passwordResetResult) {
                    case SUCCESS:
                        AccountScreen.goToScreen();
                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("check_mail_for_password_reset"));
                        break;
                    case USER_NOT_FOUND:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("user_not_found"));
                        break;
                    case TOO_MANY_ATTEMPTS:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("too_many_attempts"));
                        break;
                    case OTHER_ERROR:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("error"));
                        break;
                }
                b();
            });
        }).withLabel(30, Game.i.localeManager.i18n.get("reset_password"));
        table.add(this.f2875a).size(400.0f, 64.0f).padTop(16.0f).row();
    }

    private void a() {
        this.f2875a.setEnabled(false);
    }

    private void b() {
        this.f2875a.setEnabled(true);
    }
}

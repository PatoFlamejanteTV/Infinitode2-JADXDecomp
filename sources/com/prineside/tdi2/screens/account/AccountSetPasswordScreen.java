package com.prineside.tdi2.screens.account;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.Notifications;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountSetPasswordScreen.class */
public class AccountSetPasswordScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2885a;

    public AccountSetPasswordScreen() {
        super(Game.i.localeManager.i18n.get("account_screen_title_set_password"), AccountScreen::goToScreen);
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        Label label = new Label(Game.i.localeManager.i18n.get("password"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        textFieldXPlatform.setPasswordMode(true);
        textFieldXPlatform.setPasswordCharacter('*');
        table.add((Table) textFieldXPlatform).size(320.0f, 56.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("repeat_password"), Game.i.assetManager.getLabelStyle(30));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label2).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        textFieldXPlatform2.setPasswordMode(true);
        textFieldXPlatform2.setPasswordCharacter('*');
        table.add((Table) textFieldXPlatform2).size(320.0f, 56.0f).row();
        this.f2885a = new FancyButton("regularButton.a", () -> {
            String trim = textFieldXPlatform.getText().trim();
            if (!trim.equals(textFieldXPlatform2.getText().trim())) {
                Notifications.i().addFailure(Game.i.localeManager.i18n.get("passwords_do_not_match"));
            } else {
                a();
                Game.i.authManager.setPassword(trim, bool -> {
                    if (bool.booleanValue()) {
                        Notifications.i().addSuccess(Game.i.localeManager.i18n.get("password_successfully_set_notification"));
                        AccountScreen.goToScreen();
                    } else {
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("unknown_error"));
                        b();
                    }
                });
            }
        }).withLabel(30, Game.i.localeManager.i18n.get("account_screen_title_set_password"));
        table.add(this.f2885a).size(320.0f, 64.0f).padTop(16.0f).row();
    }

    private void a() {
        this.f2885a.setEnabled(false);
    }

    private void b() {
        this.f2885a.setEnabled(true);
    }
}

package com.prineside.tdi2.screens.account;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/AccountSignInScreen.class */
public class AccountSignInScreen extends GenericAccountScreen {

    /* renamed from: a, reason: collision with root package name */
    private FancyButton f2886a;

    public AccountSignInScreen() {
        super(null, () -> {
            Game.i.screenManager.goToMainMenu();
        });
        Table table = new Table();
        this.uiLayer.getTable().add(table).row();
        table.add().height(120.0f).width(1.0f).row();
        Label label = new Label(Game.i.localeManager.i18n.get("login"), Game.i.assetManager.getLabelStyle(30));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(30));
        String progressOwnerPlayerId = Game.i.authManager.getProgressOwnerPlayerId();
        if (progressOwnerPlayerId != null) {
            textFieldXPlatform.setText(Game.i.authManager.getProgressOwnerPlayerNickname());
            TooltipsOverlay.i().showText("AccountScreen.progressOwnerForcedNickname", textFieldXPlatform, Game.i.localeManager.i18n.format("sign_in_login_forced_by_progress_owner", progressOwnerPlayerId), this.uiLayer.mainUiLayer, this.uiLayer.zIndex + 1, 16);
        }
        table.add((Table) textFieldXPlatform).size(320.0f, 56.0f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("password"), Game.i.assetManager.getLabelStyle(30));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table.add((Table) label2).height(64.0f).row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(30, false));
        textFieldXPlatform2.setPasswordMode(true);
        textFieldXPlatform2.setPasswordCharacter('*');
        table.add((Table) textFieldXPlatform2).size(320.0f, 56.0f).row();
        this.f2886a = new FancyButton("regularButton.a", () -> {
            if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
                Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                return;
            }
            this.f2886a.label.setText(Game.i.localeManager.i18n.get("signing_in..."));
            a();
            Game.i.authManager.signIn(textFieldXPlatform.getText(), textFieldXPlatform2.getText(), signInResponse -> {
                switch (signInResponse.result) {
                    case OTHER_ERROR:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("unknown_error"));
                        break;
                    case USER_NOT_FOUND:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("user_not_found"));
                        break;
                    case WRONG_PASSWORD:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("wrong_password"));
                        break;
                    case PASSWORD_NOT_SET:
                        Notifications.i().addFailure(Game.i.localeManager.i18n.get("password_not_set_use_other_method"));
                        break;
                    case SUCCESS:
                        AccountScreen.goToScreen();
                        break;
                }
                b();
            });
        }).withLabel(30, Game.i.localeManager.i18n.get("sign_in"));
        table.add(this.f2886a).size(320.0f, 64.0f).padTop(32.0f).row();
        Table table2 = new Table();
        table.add(table2).height(64.0f).padTop(64.0f).padBottom(16.0f).row();
        boolean z = false;
        if (Game.i.actionResolver.hasGoogleAuth()) {
            table2.add((Table) AccountScreen.createGoogleSignInButton(this::a, this::b, false)).size(320.0f, 64.0f);
            z = true;
        }
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            Cell size = table2.add((Table) AccountScreen.createSteamSignInButton(this::a, this::b, false)).size(320.0f, 64.0f);
            if (z) {
                size.padLeft(16.0f);
            }
        }
        LabelButton labelButton = new LabelButton(Game.i.localeManager.i18n.get("why_account_link"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.WHY_ACCOUNT_URL);
        });
        labelButton.setAlignment(1);
        table.add((Table) labelButton).size(320.0f, 32.0f).padTop(32.0f).padBottom(8.0f).row();
        LabelButton labelButton2 = new LabelButton(Game.i.localeManager.i18n.get("privacy_policy"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.PRIVACY_POLICY_URL);
        });
        labelButton2.setAlignment(1);
        table.add((Table) labelButton2).size(320.0f, 32.0f).padTop(8.0f).padBottom(8.0f).row();
        LabelButton labelButton3 = new LabelButton(Game.i.localeManager.i18n.get("terms_and_conditions"), Game.i.assetManager.getLabelStyle(24), () -> {
            Gdx.f881net.openURI(Config.TERMS_AND_CONDITIONS_URL);
        });
        labelButton3.setAlignment(1);
        table.add((Table) labelButton3).size(320.0f, 32.0f).padTop(8.0f).padBottom(32.0f).row();
        Table table3 = new Table();
        table.add(table3).padTop(16.0f).padBottom(160.0f).row();
        Label label3 = new Label(Game.i.localeManager.i18n.get("dont_have_account?"), Game.i.assetManager.getLabelStyle(24));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table3.add((Table) label3);
        Label label4 = new Label(Game.i.localeManager.i18n.get("forgot_password?"), Game.i.assetManager.getLabelStyle(24));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table3.add((Table) label4).padLeft(64.0f).row();
        table3.add(new FancyButton("regularButton.a", () -> {
            Game.i.screenManager.setScreen(new AccountSignUpScreen());
        }).withLabel(30, Game.i.localeManager.i18n.get("sign_up"))).size(320.0f, 64.0f).padTop(16.0f);
        table3.add(new FancyButton("regularButton.b", () -> {
            Game.i.screenManager.setScreen(new AccountPasswordResetScreen());
        }).withLabel(30, Game.i.localeManager.i18n.get("reset_password"))).size(320.0f, 64.0f).padTop(16.0f).padLeft(64.0f).row();
    }

    private void a() {
        this.f2886a.setEnabled(false);
    }

    private void b() {
        this.f2886a.label.setText(Game.i.localeManager.i18n.get("sign_in"));
        this.f2886a.setEnabled(true);
    }
}

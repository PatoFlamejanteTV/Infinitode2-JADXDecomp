package com.prineside.tdi2.screens.account;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.ScreenTitle;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/account/GenericAccountScreen.class */
public abstract class GenericAccountScreen extends Screen {
    public final UiManager.UiLayer uiLayer = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "AccountScreen-" + getClass().getSimpleName());

    public GenericAccountScreen(@Null CharSequence charSequence, Runnable runnable) {
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-user")).setText(Game.i.localeManager.i18n.get("account_screen_title") + (charSequence == null ? "" : " - " + ((Object) charSequence))).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(runnable);
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.uiLayer);
    }
}

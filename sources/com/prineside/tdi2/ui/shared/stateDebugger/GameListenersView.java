package com.prineside.tdi2.ui.shared.stateDebugger;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.screens.GameScreen;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/GameListenersView.class */
public class GameListenersView extends ListenersView {
    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getId() {
        return "GAME_LISTENERS";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getName() {
        return "Game listeners";
    }

    @Override // com.prineside.tdi2.ui.shared.stateDebugger.ListenersView, com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onShow() {
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            setDispatcher(((GameScreen) Game.i.screenManager.getCurrentScreen()).S.events);
        }
        super.onShow();
    }
}

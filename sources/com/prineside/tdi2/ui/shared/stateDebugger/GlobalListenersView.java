package com.prineside.tdi2.ui.shared.stateDebugger;

import com.prineside.tdi2.Game;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/GlobalListenersView.class */
public class GlobalListenersView extends ListenersView {
    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getId() {
        return "GLOBAL_LISTENERS";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getName() {
        return "Global listeners";
    }

    @Override // com.prineside.tdi2.ui.shared.stateDebugger.ListenersView, com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onShow() {
        setDispatcher(Game.EVENTS);
        super.onShow();
    }
}

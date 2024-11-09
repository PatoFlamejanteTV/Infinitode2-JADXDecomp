package com.prineside.tdi2.ui.shared.stateDebugger;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.StateDebugger;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/UtilitiesView.class */
public class UtilitiesView implements StateDebugger.View {
    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getId() {
        return "UTILITIES";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getName() {
        return "Utils";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void rebuildWindow() {
        TableButton tableButton = new TableButton(() -> {
            GameSystemProvider currentSystemProvider = StateDebugger.i().getCurrentSystemProvider();
            if (currentSystemProvider != null) {
                CameraController cameraController = currentSystemProvider._input.getCameraController();
                cameraController.hardZoomLimits = true;
                cameraController.hardMinZoom = 0.1d;
                cameraController.hardMaxZoom = 16.0d;
                cameraController.updateMinMaxZoom();
                Notifications.i().addSuccess("Extra zoom enabled");
            }
        });
        tableButton.add((TableButton) new Label("Extra zoom", Game.i.assetManager.getLabelStyle(18)));
        tableButton.setContentColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700, Color.WHITE);
        StateDebugger.i().contentTable.add(tableButton).height(32.0f).growX().row();
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void init() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void postInit() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onShow() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onHide() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onRender() {
    }
}

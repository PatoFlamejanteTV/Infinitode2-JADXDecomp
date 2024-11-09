package com.badlogic.gdx.controllers.desktop.support;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.ControllerMapping;
import com.badlogic.gdx.controllers.ControllerPowerLevel;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.b;
import com.studiohartman.jamepad.c;
import com.studiohartman.jamepad.e;
import java.util.UUID;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/JamepadController.class */
public class JamepadController implements Controller {
    private static final IntMap<c> CODE_TO_BUTTON = new IntMap<>(c.values().length);
    private static final IntMap<b> CODE_TO_AXIS = new IntMap<>(b.values().length);
    private static final Logger logger = new Logger(JamepadController.class.getSimpleName());
    private final ControllerIndex controllerIndex;
    private long vibrationEndMs;
    private final CompositeControllerListener compositeControllerListener = new CompositeControllerListener();
    private final IntMap<Boolean> buttonState = new IntMap<>();
    private final IntMap<Float> axisState = new IntMap<>();
    private boolean connected = true;
    private Boolean canVibrate = null;
    private int axisCount = -1;
    private int maxButtonIndex = -1;
    private final String uuid = UUID.randomUUID().toString();

    static {
        for (c cVar : c.values()) {
            CODE_TO_BUTTON.put(cVar.ordinal(), cVar);
        }
        for (b bVar : b.values()) {
            CODE_TO_AXIS.put(bVar.ordinal(), bVar);
        }
    }

    public JamepadController(ControllerIndex controllerIndex) {
        this.controllerIndex = controllerIndex;
        initializeState();
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean getButton(int i) {
        try {
            c button = toButton(i);
            if (button != null) {
                return this.controllerIndex.a(button);
            }
            return false;
        } catch (e unused) {
            setDisconnected();
            return false;
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public float getAxis(int i) {
        try {
            b axis = toAxis(i);
            if (axis != null) {
                return this.controllerIndex.a(axis);
            }
            return 0.0f;
        } catch (e unused) {
            setDisconnected();
            return 0.0f;
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public String getName() {
        try {
            return this.controllerIndex.f();
        } catch (e unused) {
            setDisconnected();
            return "Unknown";
        }
    }

    private void setDisconnected() {
        if (this.connected) {
            this.connected = false;
            logger.info("Failed querying controller at index: " + this.controllerIndex.d());
            this.compositeControllerListener.disconnected(this);
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void addListener(ControllerListener controllerListener) {
        this.compositeControllerListener.addListener(controllerListener);
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void removeListener(ControllerListener controllerListener) {
        this.compositeControllerListener.removeListener(controllerListener);
    }

    public boolean update() {
        updateButtonsState();
        updateAxisState();
        return this.connected;
    }

    private c toButton(int i) {
        return CODE_TO_BUTTON.get(i);
    }

    private b toAxis(int i) {
        return CODE_TO_AXIS.get(i);
    }

    private void updateAxisState() {
        for (b bVar : b.values()) {
            int ordinal = bVar.ordinal();
            float axis = getAxis(ordinal);
            if (axis != this.axisState.get(ordinal).floatValue()) {
                if (logger.getLevel() == 3) {
                    logger.debug("Axis [" + ordinal + " - " + toAxis(ordinal) + "] moved [" + axis + "]");
                }
                this.compositeControllerListener.axisMoved(this, ordinal, axis);
            }
            this.axisState.put(ordinal, Float.valueOf(axis));
        }
    }

    private void updateButtonsState() {
        for (c cVar : c.values()) {
            int ordinal = cVar.ordinal();
            boolean button = getButton(ordinal);
            if (button != this.buttonState.get(ordinal).booleanValue()) {
                if (button) {
                    this.compositeControllerListener.buttonDown(this, ordinal);
                } else {
                    this.compositeControllerListener.buttonUp(this, ordinal);
                }
                if (logger.getLevel() == 3) {
                    logger.debug("Button [" + ordinal + " - " + toButton(ordinal) + "] is " + (button ? "pressed" : "released"));
                }
            }
            this.buttonState.put(ordinal, Boolean.valueOf(button));
        }
    }

    private void initializeState() {
        for (b bVar : b.values()) {
            this.axisState.put(bVar.ordinal(), Float.valueOf(0.0f));
        }
        for (c cVar : c.values()) {
            this.buttonState.put(cVar.ordinal(), Boolean.FALSE);
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean canVibrate() {
        if (this.canVibrate == null) {
            try {
                this.canVibrate = Boolean.valueOf(this.controllerIndex.e());
            } catch (e unused) {
                setDisconnected();
            }
        }
        return this.canVibrate.booleanValue();
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean isVibrating() {
        return canVibrate() && TimeUtils.millis() < this.vibrationEndMs;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void startVibration(int i, float f) {
        try {
            if (this.controllerIndex.a(f, f, i)) {
                this.vibrationEndMs = TimeUtils.millis() + i;
                this.canVibrate = Boolean.TRUE;
            }
        } catch (e unused) {
            setDisconnected();
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void cancelVibration() {
        if (isVibrating()) {
            startVibration(0, 0.0f);
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public String getUniqueId() {
        return this.uuid;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean supportsPlayerIndex() {
        return true;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public int getPlayerIndex() {
        try {
            return this.controllerIndex.g();
        } catch (e unused) {
            setDisconnected();
            return -1;
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void setPlayerIndex(int i) {
        try {
            this.controllerIndex.a(i);
        } catch (e unused) {
            setDisconnected();
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public int getMinButtonIndex() {
        return 0;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public int getMaxButtonIndex() {
        if (this.maxButtonIndex >= 0) {
            return this.maxButtonIndex;
        }
        this.maxButtonIndex = CODE_TO_BUTTON.size - 1;
        while (this.maxButtonIndex > 0 && !this.controllerIndex.b(CODE_TO_BUTTON.get(this.maxButtonIndex))) {
            try {
                this.maxButtonIndex--;
            } catch (e unused) {
                setDisconnected();
            }
        }
        return this.maxButtonIndex;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public int getAxisCount() {
        if (this.axisCount >= 0) {
            return this.axisCount;
        }
        this.axisCount = CODE_TO_AXIS.size;
        while (this.axisCount > 0 && !this.controllerIndex.b(CODE_TO_AXIS.get(this.axisCount - 1))) {
            try {
                this.axisCount--;
            } catch (e unused) {
                setDisconnected();
            }
        }
        return this.axisCount;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean isConnected() {
        return this.connected && this.controllerIndex.c();
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public ControllerMapping getMapping() {
        return JamepadMapping.getInstance();
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public ControllerPowerLevel getPowerLevel() {
        try {
            switch (this.controllerIndex.h()) {
                case POWER_MAX:
                case POWER_FULL:
                    return ControllerPowerLevel.POWER_FULL;
                case POWER_MEDIUM:
                    return ControllerPowerLevel.POWER_MEDIUM;
                case POWER_LOW:
                    return ControllerPowerLevel.POWER_LOW;
                case POWER_EMPTY:
                    return ControllerPowerLevel.POWER_EMPTY;
                case POWER_WIRED:
                    return ControllerPowerLevel.POWER_WIRED;
                default:
                    return ControllerPowerLevel.POWER_UNKNOWN;
            }
        } catch (Throwable unused) {
            return ControllerPowerLevel.POWER_UNKNOWN;
        }
    }
}

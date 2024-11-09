package com.badlogic.gdx.controllers;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/Controller.class */
public interface Controller {
    public static final int PLAYER_IDX_UNSET = -1;

    boolean getButton(int i);

    float getAxis(int i);

    String getName();

    String getUniqueId();

    int getMinButtonIndex();

    int getMaxButtonIndex();

    int getAxisCount();

    boolean isConnected();

    boolean canVibrate();

    boolean isVibrating();

    void startVibration(int i, float f);

    void cancelVibration();

    boolean supportsPlayerIndex();

    int getPlayerIndex();

    void setPlayerIndex(int i);

    ControllerMapping getMapping();

    ControllerPowerLevel getPowerLevel();

    void addListener(ControllerListener controllerListener);

    void removeListener(ControllerListener controllerListener);
}

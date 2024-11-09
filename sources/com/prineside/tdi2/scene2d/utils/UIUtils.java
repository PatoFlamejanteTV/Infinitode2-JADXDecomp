package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.prineside.tdi2.Game;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/UIUtils.class */
public final class UIUtils {
    public static boolean isAndroid = SharedLibraryLoader.isAndroid;
    public static boolean isMac = SharedLibraryLoader.isMac;
    public static boolean isWindows = SharedLibraryLoader.isWindows;
    public static boolean isLinux = SharedLibraryLoader.isLinux;
    public static boolean isIos = SharedLibraryLoader.isIos;

    private UIUtils() {
    }

    public static boolean hasClipboard() {
        try {
            return Gdx.app.getClipboard().hasContents();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean left() {
        return Gdx.input.isButtonPressed(0);
    }

    public static boolean left(int i) {
        return i == 0;
    }

    public static boolean right() {
        return Gdx.input.isButtonPressed(1);
    }

    public static boolean right(int i) {
        return i == 1;
    }

    public static boolean middle() {
        return Gdx.input.isButtonPressed(2);
    }

    public static boolean middle(int i) {
        return i == 2;
    }

    public static boolean shift() {
        return Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60) || Game.i.uiManager.isStageKeyPressed(59) || Game.i.uiManager.isStageKeyPressed(60);
    }

    public static boolean shift(int i) {
        return i == 59 || i == 60;
    }

    public static boolean ctrl() {
        if (isMac) {
            return Gdx.input.isKeyPressed(63);
        }
        return Gdx.input.isKeyPressed(129) || Gdx.input.isKeyPressed(130) || Game.i.uiManager.isStageKeyPressed(129) || Game.i.uiManager.isStageKeyPressed(130) || Game.i.uiManager.isStageKeyPressed(113);
    }

    public static boolean ctrl(int i) {
        return isMac ? i == 63 : i == 129 || i == 130 || i == 113;
    }

    public static boolean alt() {
        return Gdx.input.isKeyPressed(57) || Gdx.input.isKeyPressed(58) || Game.i.uiManager.isStageKeyPressed(57) || Game.i.uiManager.isStageKeyPressed(58);
    }

    public static boolean alt(int i) {
        return i == 57 || i == 58;
    }
}

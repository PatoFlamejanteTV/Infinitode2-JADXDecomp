package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Os;
import com.badlogic.gdx.utils.SharedLibraryLoader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/UIUtils.class */
public final class UIUtils {
    public static boolean isAndroid;
    public static boolean isMac;
    public static boolean isWindows;
    public static boolean isLinux;
    public static boolean isIos;

    private UIUtils() {
    }

    static {
        isAndroid = SharedLibraryLoader.os == Os.Android;
        isMac = SharedLibraryLoader.os == Os.MacOsX;
        isWindows = SharedLibraryLoader.os == Os.Windows;
        isLinux = SharedLibraryLoader.os == Os.Linux;
        isIos = SharedLibraryLoader.os == Os.IOS;
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
        return Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
    }

    public static boolean shift(int i) {
        return i == 59 || i == 60;
    }

    public static boolean ctrl() {
        if (isMac) {
            return Gdx.input.isKeyPressed(63);
        }
        return Gdx.input.isKeyPressed(129) || Gdx.input.isKeyPressed(130);
    }

    public static boolean ctrl(int i) {
        return isMac ? i == 63 : i == 129 || i == 130;
    }

    public static boolean alt() {
        return Gdx.input.isKeyPressed(57) || Gdx.input.isKeyPressed(58);
    }

    public static boolean alt(int i) {
        return i == 57 || i == 58;
    }
}

package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.Color;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3WindowConfiguration.class */
public class Lwjgl3WindowConfiguration {
    Lwjgl3Graphics.Lwjgl3Monitor maximizedMonitor;
    Files.FileType windowIconFileType;
    String[] windowIconPaths;
    Lwjgl3WindowListener windowListener;
    Lwjgl3Graphics.Lwjgl3DisplayMode fullscreenMode;
    String title;
    int windowX = -1;
    int windowY = -1;
    int windowWidth = 640;
    int windowHeight = 480;
    int windowMinWidth = -1;
    int windowMinHeight = -1;
    int windowMaxWidth = -1;
    int windowMaxHeight = -1;
    boolean windowResizable = true;
    boolean windowDecorated = true;
    boolean windowMaximized = false;
    boolean autoIconify = true;
    Color initialBackgroundColor = Color.BLACK;
    boolean initialVisible = true;
    boolean vSyncEnabled = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWindowConfiguration(Lwjgl3WindowConfiguration lwjgl3WindowConfiguration) {
        this.windowX = lwjgl3WindowConfiguration.windowX;
        this.windowY = lwjgl3WindowConfiguration.windowY;
        this.windowWidth = lwjgl3WindowConfiguration.windowWidth;
        this.windowHeight = lwjgl3WindowConfiguration.windowHeight;
        this.windowMinWidth = lwjgl3WindowConfiguration.windowMinWidth;
        this.windowMinHeight = lwjgl3WindowConfiguration.windowMinHeight;
        this.windowMaxWidth = lwjgl3WindowConfiguration.windowMaxWidth;
        this.windowMaxHeight = lwjgl3WindowConfiguration.windowMaxHeight;
        this.windowResizable = lwjgl3WindowConfiguration.windowResizable;
        this.windowDecorated = lwjgl3WindowConfiguration.windowDecorated;
        this.windowMaximized = lwjgl3WindowConfiguration.windowMaximized;
        this.maximizedMonitor = lwjgl3WindowConfiguration.maximizedMonitor;
        this.autoIconify = lwjgl3WindowConfiguration.autoIconify;
        this.windowIconFileType = lwjgl3WindowConfiguration.windowIconFileType;
        if (lwjgl3WindowConfiguration.windowIconPaths != null) {
            this.windowIconPaths = (String[]) Arrays.copyOf(lwjgl3WindowConfiguration.windowIconPaths, lwjgl3WindowConfiguration.windowIconPaths.length);
        }
        this.windowListener = lwjgl3WindowConfiguration.windowListener;
        this.fullscreenMode = lwjgl3WindowConfiguration.fullscreenMode;
        this.title = lwjgl3WindowConfiguration.title;
        this.initialBackgroundColor = lwjgl3WindowConfiguration.initialBackgroundColor;
        this.initialVisible = lwjgl3WindowConfiguration.initialVisible;
        this.vSyncEnabled = lwjgl3WindowConfiguration.vSyncEnabled;
    }

    public void setInitialVisible(boolean z) {
        this.initialVisible = z;
    }

    public void setWindowedMode(int i, int i2) {
        this.windowWidth = i;
        this.windowHeight = i2;
    }

    public void setResizable(boolean z) {
        this.windowResizable = z;
    }

    public void setDecorated(boolean z) {
        this.windowDecorated = z;
    }

    public void setMaximized(boolean z) {
        this.windowMaximized = z;
    }

    public void setMaximizedMonitor(Graphics.Monitor monitor) {
        this.maximizedMonitor = (Lwjgl3Graphics.Lwjgl3Monitor) monitor;
    }

    public void setAutoIconify(boolean z) {
        this.autoIconify = z;
    }

    public void setWindowPosition(int i, int i2) {
        this.windowX = i;
        this.windowY = i2;
    }

    public void setWindowSizeLimits(int i, int i2, int i3, int i4) {
        this.windowMinWidth = i;
        this.windowMinHeight = i2;
        this.windowMaxWidth = i3;
        this.windowMaxHeight = i4;
    }

    public void setWindowIcon(String... strArr) {
        setWindowIcon(Files.FileType.Internal, strArr);
    }

    public void setWindowIcon(Files.FileType fileType, String... strArr) {
        this.windowIconFileType = fileType;
        this.windowIconPaths = strArr;
    }

    public void setWindowListener(Lwjgl3WindowListener lwjgl3WindowListener) {
        this.windowListener = lwjgl3WindowListener;
    }

    public void setFullscreenMode(Graphics.DisplayMode displayMode) {
        this.fullscreenMode = (Lwjgl3Graphics.Lwjgl3DisplayMode) displayMode;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setInitialBackgroundColor(Color color) {
        this.initialBackgroundColor = color;
    }

    public void useVsync(boolean z) {
        this.vSyncEnabled = z;
    }
}

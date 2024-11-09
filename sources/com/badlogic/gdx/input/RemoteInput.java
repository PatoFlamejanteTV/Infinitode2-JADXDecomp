package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.net.InetAddress;
import java.net.ServerSocket;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteInput.class */
public class RemoteInput implements Input, Runnable {
    private static final int MAX_TOUCHES = 20;
    public static int DEFAULT_PORT = 8190;
    private ServerSocket serverSocket;
    private float[] accel;
    private float[] gyrate;
    private float[] compass;
    private boolean multiTouch;
    private float remoteWidth;
    private float remoteHeight;
    private boolean connected;
    private RemoteInputListener listener;
    int keyCount;
    boolean[] keys;
    boolean keyJustPressed;
    boolean[] justPressedKeys;
    int[] deltaX;
    int[] deltaY;
    int[] touchX;
    int[] touchY;
    boolean[] isTouched;
    boolean justTouched;
    InputProcessor processor;
    private final int port;
    public final String[] ips;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteInput$RemoteInputListener.class */
    public interface RemoteInputListener {
        void onConnected();

        void onDisconnected();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteInput$KeyEvent.class */
    class KeyEvent {
        static final int KEY_DOWN = 0;
        static final int KEY_UP = 1;
        static final int KEY_TYPED = 2;
        long timeStamp;
        int type;
        int keyCode;
        char keyChar;

        KeyEvent() {
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteInput$TouchEvent.class */
    class TouchEvent {
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_UP = 1;
        static final int TOUCH_DRAGGED = 2;
        long timeStamp;
        int type;
        int x;
        int y;
        int pointer;

        TouchEvent() {
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteInput$EventTrigger.class */
    class EventTrigger implements Runnable {
        TouchEvent touchEvent;
        KeyEvent keyEvent;

        public EventTrigger(TouchEvent touchEvent, KeyEvent keyEvent) {
            this.touchEvent = touchEvent;
            this.keyEvent = keyEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            RemoteInput.this.justTouched = false;
            if (RemoteInput.this.keyJustPressed) {
                RemoteInput.this.keyJustPressed = false;
                for (int i = 0; i < RemoteInput.this.justPressedKeys.length; i++) {
                    RemoteInput.this.justPressedKeys[i] = false;
                }
            }
            if (RemoteInput.this.processor != null) {
                if (this.touchEvent != null) {
                    switch (this.touchEvent.type) {
                        case 0:
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.processor.touchDown(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer, 0);
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = true;
                            RemoteInput.this.justTouched = true;
                            break;
                        case 1:
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                            RemoteInput.this.processor.touchUp(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer, 0);
                            RemoteInput.this.isTouched[this.touchEvent.pointer] = false;
                            break;
                        case 2:
                            RemoteInput.this.deltaX[this.touchEvent.pointer] = this.touchEvent.x - RemoteInput.this.touchX[this.touchEvent.pointer];
                            RemoteInput.this.deltaY[this.touchEvent.pointer] = this.touchEvent.y - RemoteInput.this.touchY[this.touchEvent.pointer];
                            RemoteInput.this.processor.touchDragged(this.touchEvent.x, this.touchEvent.y, this.touchEvent.pointer);
                            break;
                    }
                    RemoteInput.this.touchX[this.touchEvent.pointer] = this.touchEvent.x;
                    RemoteInput.this.touchY[this.touchEvent.pointer] = this.touchEvent.y;
                }
                if (this.keyEvent != null) {
                    switch (this.keyEvent.type) {
                        case 0:
                            RemoteInput.this.processor.keyDown(this.keyEvent.keyCode);
                            if (!RemoteInput.this.keys[this.keyEvent.keyCode]) {
                                RemoteInput.this.keyCount++;
                                RemoteInput.this.keys[this.keyEvent.keyCode] = true;
                            }
                            RemoteInput.this.keyJustPressed = true;
                            RemoteInput.this.justPressedKeys[this.keyEvent.keyCode] = true;
                            return;
                        case 1:
                            RemoteInput.this.processor.keyUp(this.keyEvent.keyCode);
                            if (RemoteInput.this.keys[this.keyEvent.keyCode]) {
                                RemoteInput.this.keyCount--;
                                RemoteInput.this.keys[this.keyEvent.keyCode] = false;
                                return;
                            }
                            return;
                        case 2:
                            RemoteInput.this.processor.keyTyped(this.keyEvent.keyChar);
                            return;
                        default:
                            return;
                    }
                }
                return;
            }
            if (this.touchEvent != null) {
                switch (this.touchEvent.type) {
                    case 0:
                        RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                        RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                        RemoteInput.this.isTouched[this.touchEvent.pointer] = true;
                        RemoteInput.this.justTouched = true;
                        break;
                    case 1:
                        RemoteInput.this.deltaX[this.touchEvent.pointer] = 0;
                        RemoteInput.this.deltaY[this.touchEvent.pointer] = 0;
                        RemoteInput.this.isTouched[this.touchEvent.pointer] = false;
                        break;
                    case 2:
                        RemoteInput.this.deltaX[this.touchEvent.pointer] = this.touchEvent.x - RemoteInput.this.touchX[this.touchEvent.pointer];
                        RemoteInput.this.deltaY[this.touchEvent.pointer] = this.touchEvent.y - RemoteInput.this.touchY[this.touchEvent.pointer];
                        break;
                }
                RemoteInput.this.touchX[this.touchEvent.pointer] = this.touchEvent.x;
                RemoteInput.this.touchY[this.touchEvent.pointer] = this.touchEvent.y;
            }
            if (this.keyEvent != null) {
                if (this.keyEvent.type == 0) {
                    if (!RemoteInput.this.keys[this.keyEvent.keyCode]) {
                        RemoteInput.this.keyCount++;
                        RemoteInput.this.keys[this.keyEvent.keyCode] = true;
                    }
                    RemoteInput.this.keyJustPressed = true;
                    RemoteInput.this.justPressedKeys[this.keyEvent.keyCode] = true;
                }
                if (this.keyEvent.type == 1 && RemoteInput.this.keys[this.keyEvent.keyCode]) {
                    RemoteInput.this.keyCount--;
                    RemoteInput.this.keys[this.keyEvent.keyCode] = false;
                }
            }
        }
    }

    public RemoteInput() {
        this(DEFAULT_PORT);
    }

    public RemoteInput(RemoteInputListener remoteInputListener) {
        this(DEFAULT_PORT, remoteInputListener);
    }

    public RemoteInput(int i) {
        this(i, null);
    }

    public RemoteInput(int i, RemoteInputListener remoteInputListener) {
        this.accel = new float[3];
        this.gyrate = new float[3];
        this.compass = new float[3];
        this.multiTouch = false;
        this.remoteWidth = 0.0f;
        this.remoteHeight = 0.0f;
        this.connected = false;
        this.keyCount = 0;
        this.keys = new boolean[256];
        this.keyJustPressed = false;
        this.justPressedKeys = new boolean[256];
        this.deltaX = new int[20];
        this.deltaY = new int[20];
        this.touchX = new int[20];
        this.touchY = new int[20];
        this.isTouched = new boolean[20];
        this.justTouched = false;
        this.processor = null;
        this.listener = remoteInputListener;
        try {
            this.port = i;
            this.serverSocket = new ServerSocket(i);
            Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
            InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
            this.ips = new String[allByName.length];
            for (int i2 = 0; i2 < allByName.length; i2++) {
                this.ips[i2] = allByName[i2].getHostAddress();
            }
        } catch (Exception e) {
            throw new GdxRuntimeException("Couldn't open listening socket at port '" + i + "'", e);
        }
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.BlockNode.getSuccessors()" because "block" is null
        	at jadx.core.dex.nodes.MethodNode.isPreExitBlock(MethodNode.java:398)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:908)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX INFO: Infinite loop detected, blocks: 36, insns: 0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v52, types: [com.badlogic.gdx.Application] */
    @Override // java.lang.Runnable
    public void run() {
        /*
            Method dump skipped, instructions count: 629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.input.RemoteInput.run():void");
    }

    public boolean isConnected() {
        return this.connected;
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerX() {
        return this.accel[0];
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerY() {
        return this.accel[1];
    }

    @Override // com.badlogic.gdx.Input
    public float getAccelerometerZ() {
        return this.accel[2];
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeX() {
        return this.gyrate[0];
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeY() {
        return this.gyrate[1];
    }

    @Override // com.badlogic.gdx.Input
    public float getGyroscopeZ() {
        return this.gyrate[2];
    }

    @Override // com.badlogic.gdx.Input
    public int getMaxPointers() {
        return 20;
    }

    @Override // com.badlogic.gdx.Input
    public int getX() {
        return this.touchX[0];
    }

    @Override // com.badlogic.gdx.Input
    public int getX(int i) {
        return this.touchX[i];
    }

    @Override // com.badlogic.gdx.Input
    public int getY() {
        return this.touchY[0];
    }

    @Override // com.badlogic.gdx.Input
    public int getY(int i) {
        return this.touchY[i];
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched() {
        return this.isTouched[0];
    }

    @Override // com.badlogic.gdx.Input
    public boolean justTouched() {
        return this.justTouched;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isTouched(int i) {
        return this.isTouched[i];
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure() {
        return getPressure(0);
    }

    @Override // com.badlogic.gdx.Input
    public float getPressure(int i) {
        return isTouched(i) ? 1.0f : 0.0f;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonPressed(int i) {
        if (i != 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.isTouched.length; i2++) {
            if (this.isTouched[i2]) {
                return true;
            }
        }
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isButtonJustPressed(int i) {
        return i == 0 && this.justTouched;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isKeyPressed(int i) {
        if (i == -1) {
            return this.keyCount > 0;
        }
        if (i < 0 || i > 255) {
            return false;
        }
        return this.keys[i];
    }

    @Override // com.badlogic.gdx.Input
    public boolean isKeyJustPressed(int i) {
        if (i == -1) {
            return this.keyJustPressed;
        }
        if (i < 0 || i > 255) {
            return false;
        }
        return this.justPressedKeys[i];
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
        Gdx.app.getInput().getTextInput(textInputListener, str, str2, str3);
    }

    @Override // com.badlogic.gdx.Input
    public void getTextInput(Input.TextInputListener textInputListener, String str, String str2, String str3, Input.OnscreenKeyboardType onscreenKeyboardType) {
        Gdx.app.getInput().getTextInput(textInputListener, str, str2, str3, onscreenKeyboardType);
    }

    @Override // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void setOnscreenKeyboardVisible(boolean z, Input.OnscreenKeyboardType onscreenKeyboardType) {
    }

    @Override // com.badlogic.gdx.Input
    public void openTextInputField(NativeInputConfiguration nativeInputConfiguration) {
    }

    @Override // com.badlogic.gdx.Input
    public void closeTextInputField(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void setKeyboardHeightObserver(Input.KeyboardHeightObserver keyboardHeightObserver) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(int i, int i2, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public void vibrate(Input.VibrationType vibrationType) {
    }

    @Override // com.badlogic.gdx.Input
    public float getAzimuth() {
        return this.compass[0];
    }

    @Override // com.badlogic.gdx.Input
    public float getPitch() {
        return this.compass[1];
    }

    @Override // com.badlogic.gdx.Input
    public float getRoll() {
        return this.compass[2];
    }

    @Override // com.badlogic.gdx.Input
    public void setCatchKey(int i, boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public boolean isCatchKey(int i) {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public void setInputProcessor(InputProcessor inputProcessor) {
        this.processor = inputProcessor;
    }

    @Override // com.badlogic.gdx.Input
    public InputProcessor getInputProcessor() {
        return this.processor;
    }

    public String[] getIPs() {
        return this.ips;
    }

    @Override // com.badlogic.gdx.Input
    public boolean isPeripheralAvailable(Input.Peripheral peripheral) {
        if (peripheral == Input.Peripheral.Accelerometer || peripheral == Input.Peripheral.Compass) {
            return true;
        }
        if (peripheral == Input.Peripheral.MultitouchScreen) {
            return this.multiTouch;
        }
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public int getRotation() {
        return 0;
    }

    @Override // com.badlogic.gdx.Input
    public Input.Orientation getNativeOrientation() {
        return Input.Orientation.Landscape;
    }

    @Override // com.badlogic.gdx.Input
    public void setCursorCatched(boolean z) {
    }

    @Override // com.badlogic.gdx.Input
    public boolean isCursorCatched() {
        return false;
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX() {
        return this.deltaX[0];
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaX(int i) {
        return this.deltaX[i];
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY() {
        return this.deltaY[0];
    }

    @Override // com.badlogic.gdx.Input
    public int getDeltaY(int i) {
        return this.deltaY[i];
    }

    @Override // com.badlogic.gdx.Input
    public void setCursorPosition(int i, int i2) {
    }

    @Override // com.badlogic.gdx.Input
    public long getCurrentEventTime() {
        return 0L;
    }

    @Override // com.badlogic.gdx.Input
    public void getRotationMatrix(float[] fArr) {
    }
}

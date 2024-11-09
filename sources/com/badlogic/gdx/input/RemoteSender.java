package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.prineside.tdi2.events.EventListeners;
import java.io.DataOutputStream;
import java.net.Socket;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/input/RemoteSender.class */
public class RemoteSender implements InputProcessor {
    private DataOutputStream out;
    private boolean connected;
    public static final int KEY_DOWN = 0;
    public static final int KEY_UP = 1;
    public static final int KEY_TYPED = 2;
    public static final int TOUCH_DOWN = 3;
    public static final int TOUCH_UP = 4;
    public static final int TOUCH_DRAGGED = 5;
    public static final int ACCEL = 6;
    public static final int COMPASS = 7;
    public static final int SIZE = 8;
    public static final int GYRO = 9;

    public RemoteSender(String str, int i) {
        this.connected = false;
        try {
            Socket socket = new Socket(str, i);
            socket.setTcpNoDelay(true);
            socket.setSoTimeout(EventListeners.PRIORITY_HIGHEST);
            this.out = new DataOutputStream(socket.getOutputStream());
            this.out.writeBoolean(Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen));
            this.connected = true;
            Gdx.input.setInputProcessor(this);
        } catch (Exception unused) {
            Gdx.app.log("RemoteSender", "couldn't connect to " + str + ":" + i);
        }
    }

    public void sendUpdate() {
        synchronized (this) {
            if (this.connected) {
                try {
                    this.out.writeInt(6);
                    this.out.writeFloat(Gdx.input.getAccelerometerX());
                    this.out.writeFloat(Gdx.input.getAccelerometerY());
                    this.out.writeFloat(Gdx.input.getAccelerometerZ());
                    this.out.writeInt(7);
                    this.out.writeFloat(Gdx.input.getAzimuth());
                    this.out.writeFloat(Gdx.input.getPitch());
                    this.out.writeFloat(Gdx.input.getRoll());
                    this.out.writeInt(8);
                    this.out.writeFloat(Gdx.graphics.getWidth());
                    this.out.writeFloat(Gdx.graphics.getHeight());
                    this.out.writeInt(9);
                    this.out.writeFloat(Gdx.input.getGyroscopeX());
                    this.out.writeFloat(Gdx.input.getGyroscopeY());
                    this.out.writeFloat(Gdx.input.getGyroscopeZ());
                } catch (Throwable unused) {
                    this.out = null;
                    this.connected = false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int i) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(0);
                this.out.writeInt(i);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int i) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(1);
                this.out.writeInt(i);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(2);
                this.out.writeChar(c);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int i, int i2, int i3, int i4) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(3);
                this.out.writeInt(i);
                this.out.writeInt(i2);
                this.out.writeInt(i3);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int i, int i2, int i3, int i4) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(4);
                this.out.writeInt(i);
                this.out.writeInt(i2);
                this.out.writeInt(i3);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchCancelled(int i, int i2, int i3, int i4) {
        return touchUp(i, i2, i3, i4);
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        synchronized (this) {
            if (!this.connected) {
                return false;
            }
            try {
                this.out.writeInt(5);
                this.out.writeInt(i);
                this.out.writeInt(i2);
                this.out.writeInt(i3);
                return false;
            } catch (Throwable unused) {
                synchronized (this) {
                    this.connected = false;
                    return false;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f2) {
        return false;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.connected;
        }
        return z;
    }
}

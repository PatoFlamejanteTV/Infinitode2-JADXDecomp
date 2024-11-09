package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/CameraInputController.class */
public class CameraInputController extends GestureDetector {
    public int rotateButton;
    public float rotateAngle;
    public int translateButton;
    public float translateUnits;
    public int forwardButton;
    public int activateKey;
    protected boolean activatePressed;
    public boolean alwaysScroll;
    public float scrollFactor;
    public float pinchZoomFactor;
    public boolean autoUpdate;
    public Vector3 target;
    public boolean translateTarget;
    public boolean forwardTarget;
    public boolean scrollTarget;
    public int forwardKey;
    protected boolean forwardPressed;
    public int backwardKey;
    protected boolean backwardPressed;
    public int rotateRightKey;
    protected boolean rotateRightPressed;
    public int rotateLeftKey;
    protected boolean rotateLeftPressed;
    protected boolean controlsInverted;
    public Camera camera;
    protected int button;
    private float startX;
    private float startY;
    private final Vector3 tmpV1;
    private final Vector3 tmpV2;
    protected final CameraGestureListener gestureListener;
    private int touched;
    private boolean multiTouch;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/CameraInputController$CameraGestureListener.class */
    protected static class CameraGestureListener extends GestureDetector.GestureAdapter {
        public CameraInputController controller;
        private float previousZoom;

        protected CameraGestureListener() {
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean touchDown(float f, float f2, int i, int i2) {
            this.previousZoom = 0.0f;
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean tap(float f, float f2, int i, int i2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean longPress(float f, float f2) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean fling(float f, float f2, int i) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean pan(float f, float f2, float f3, float f4) {
            return false;
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean zoom(float f, float f2) {
            float f3 = f2 - f;
            float f4 = f3 - this.previousZoom;
            this.previousZoom = f3;
            float width = Gdx.graphics.getWidth();
            float height = Gdx.graphics.getHeight();
            return this.controller.pinchZoom(f4 / (width > height ? height : width));
        }

        @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
        public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
            return false;
        }
    }

    protected CameraInputController(CameraGestureListener cameraGestureListener, Camera camera) {
        super(cameraGestureListener);
        this.rotateButton = 0;
        this.rotateAngle = 360.0f;
        this.translateButton = 1;
        this.translateUnits = 10.0f;
        this.forwardButton = 2;
        this.activateKey = 0;
        this.alwaysScroll = true;
        this.scrollFactor = -0.1f;
        this.pinchZoomFactor = 10.0f;
        this.autoUpdate = true;
        this.target = new Vector3();
        this.translateTarget = true;
        this.forwardTarget = true;
        this.scrollTarget = false;
        this.forwardKey = 51;
        this.backwardKey = 47;
        this.rotateRightKey = 29;
        this.rotateLeftKey = 32;
        this.button = -1;
        this.tmpV1 = new Vector3();
        this.tmpV2 = new Vector3();
        this.gestureListener = cameraGestureListener;
        this.gestureListener.controller = this;
        this.camera = camera;
    }

    public CameraInputController(Camera camera) {
        this(new CameraGestureListener(), camera);
    }

    public void update() {
        if (this.rotateRightPressed || this.rotateLeftPressed || this.forwardPressed || this.backwardPressed) {
            float deltaTime = Gdx.graphics.getDeltaTime();
            if (this.rotateRightPressed) {
                this.camera.rotate(this.camera.up, (-deltaTime) * this.rotateAngle);
            }
            if (this.rotateLeftPressed) {
                this.camera.rotate(this.camera.up, deltaTime * this.rotateAngle);
            }
            if (this.forwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl(deltaTime * this.translateUnits));
                if (this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if (this.backwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl((-deltaTime) * this.translateUnits));
                if (this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if (this.autoUpdate) {
                this.camera.update();
            }
        }
    }

    @Override // com.badlogic.gdx.input.GestureDetector, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDown(int i, int i2, int i3, int i4) {
        this.touched |= 1 << i3;
        this.multiTouch = !MathUtils.isPowerOfTwo(this.touched);
        if (this.multiTouch) {
            this.button = -1;
        } else if (this.button < 0 && (this.activateKey == 0 || this.activatePressed)) {
            this.startX = i;
            this.startY = i2;
            this.button = i4;
        }
        return super.touchDown(i, i2, i3, i4) || this.activateKey == 0 || this.activatePressed;
    }

    @Override // com.badlogic.gdx.input.GestureDetector, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchUp(int i, int i2, int i3, int i4) {
        this.touched &= (-1) ^ (1 << i3);
        this.multiTouch = !MathUtils.isPowerOfTwo(this.touched);
        if (i4 == this.button) {
            this.button = -1;
        }
        return super.touchUp(i, i2, i3, i4) || this.activatePressed;
    }

    public void setInvertedControls(boolean z) {
        if (this.controlsInverted != z) {
            this.rotateAngle = -this.rotateAngle;
        }
        this.controlsInverted = z;
    }

    protected boolean process(float f, float f2, int i) {
        if (i == this.rotateButton) {
            this.tmpV1.set(this.camera.direction).crs(this.camera.up).y = 0.0f;
            this.camera.rotateAround(this.target, this.tmpV1.nor(), f2 * this.rotateAngle);
            this.camera.rotateAround(this.target, Vector3.Y, f * (-this.rotateAngle));
        } else if (i == this.translateButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).crs(this.camera.up).nor().scl((-f) * this.translateUnits));
            this.camera.translate(this.tmpV2.set(this.camera.up).scl((-f2) * this.translateUnits));
            if (this.translateTarget) {
                this.target.add(this.tmpV1).add(this.tmpV2);
            }
        } else if (i == this.forwardButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).scl(f2 * this.translateUnits));
            if (this.forwardTarget) {
                this.target.add(this.tmpV1);
            }
        }
        if (this.autoUpdate) {
            this.camera.update();
            return true;
        }
        return true;
    }

    @Override // com.badlogic.gdx.input.GestureDetector, com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        boolean z = super.touchDragged(i, i2, i3);
        if (z || this.button < 0) {
            return z;
        }
        float width = (i - this.startX) / Gdx.graphics.getWidth();
        float height = (this.startY - i2) / Gdx.graphics.getHeight();
        this.startX = i;
        this.startY = i2;
        return process(width, height, this.button);
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f2) {
        return zoom(f2 * this.scrollFactor * this.translateUnits);
    }

    public boolean zoom(float f) {
        if (!this.alwaysScroll && this.activateKey != 0 && !this.activatePressed) {
            return false;
        }
        this.camera.translate(this.tmpV1.set(this.camera.direction).scl(f));
        if (this.scrollTarget) {
            this.target.add(this.tmpV1);
        }
        if (this.autoUpdate) {
            this.camera.update();
            return true;
        }
        return true;
    }

    protected boolean pinchZoom(float f) {
        return zoom(this.pinchZoomFactor * f);
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyDown(int i) {
        if (i == this.activateKey) {
            this.activatePressed = true;
        }
        if (i == this.forwardKey) {
            this.forwardPressed = true;
            return false;
        }
        if (i == this.backwardKey) {
            this.backwardPressed = true;
            return false;
        }
        if (i == this.rotateRightKey) {
            this.rotateRightPressed = true;
            return false;
        }
        if (i == this.rotateLeftKey) {
            this.rotateLeftPressed = true;
            return false;
        }
        return false;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyUp(int i) {
        if (i == this.activateKey) {
            this.activatePressed = false;
            this.button = -1;
        }
        if (i == this.forwardKey) {
            this.forwardPressed = false;
            return false;
        }
        if (i == this.backwardKey) {
            this.backwardPressed = false;
            return false;
        }
        if (i == this.rotateRightKey) {
            this.rotateRightPressed = false;
            return false;
        }
        if (i == this.rotateLeftKey) {
            this.rotateLeftPressed = false;
            return false;
        }
        return false;
    }
}

package com.prineside.tdi2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.ui.shared.CameraTools;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.PMath;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController.class */
public final class CameraController {
    public static final float DEFAULT_MIN_ZOOM_TILE_SIZE = 1.524f;
    public static final float DEFAULT_MAX_ZOOM_MAP_SCALE = 1.25f;
    public OrthographicCamera camera;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private int k;
    private CameraControllerAnimation l;
    public CameraTools.Scenario currentScenario;
    public float scenarioTime;
    public boolean scenarioLooped;
    private Map o;
    private static final Rectangle p = new Rectangle();
    private static final Vector2 q = new Vector2();
    private static final Vector2 r = new Vector2();
    private static final Rectangle u = new Rectangle();
    public double zoom = 1.0d;

    /* renamed from: a, reason: collision with root package name */
    private final Rectangle f1672a = new Rectangle();

    /* renamed from: b, reason: collision with root package name */
    private final Rectangle f1673b = new Rectangle();
    private double c = 0.5d;
    private double d = 4.0d;
    public boolean hardZoomLimits = false;
    public double hardMinZoom = 0.5d;
    public double hardMaxZoom = 4.0d;
    public boolean scrollZoomRequiresAlt = false;
    public boolean outmapCheckEnabled = true;
    public IntArray dragButtonIndices = new IntArray(new int[]{0});
    private float m = 1.524f;
    private float n = 1.25f;
    private final DelayedRemovalArray<CameraControllerListener> s = new DelayedRemovalArray<>(CameraControllerListener.class);
    private final _InputProcessor t = new _InputProcessor(this, 0);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$CameraControllerAnimation.class */
    public interface CameraControllerAnimation {
        void start(CameraController cameraController);

        void update(float f);

        void end();

        boolean isDone();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$CameraControllerListener.class */
    public interface CameraControllerListener {
        void onViewportUpdated(CameraController cameraController);
    }

    public CameraController(OrthographicCamera orthographicCamera, int i, int i2) {
        Preconditions.checkNotNull(orthographicCamera, "camera can not be null");
        this.camera = orthographicCamera;
        this.e = i;
        this.f = i2;
        setScreenSize(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
        setZoomBoundaries(1.524f, 1.25f);
        updateCamera();
    }

    public final double getRevZoomPercent() {
        return (1.0d / this.zoom) * 100.0d;
    }

    public final void setRevZoomPercent(double d) {
        if (d != getRevZoomPercent()) {
            setZoom(1.0d / (d / 100.0d));
        }
    }

    public final double getMinZoom() {
        return this.c;
    }

    public final double getMaxZoom() {
        return this.d;
    }

    public final void setMap(Map map) {
        this.o = map;
        this.i = true;
    }

    public final int getMapWidth() {
        return this.e;
    }

    public final int getMapHeight() {
        return this.f;
    }

    public final InputProcessor getInputProcessor() {
        return this.t;
    }

    public final void playScenario(CameraTools.Scenario scenario, float f, boolean z) {
        this.currentScenario = scenario;
        this.scenarioTime = f;
        this.scenarioLooped = z;
    }

    public final void stopScenario() {
        this.currentScenario = null;
    }

    public final void animate(CameraControllerAnimation cameraControllerAnimation) {
        stopAnimations();
        this.l = cameraControllerAnimation;
        cameraControllerAnimation.start(this);
    }

    public final void stopAnimations() {
        if (this.l != null) {
            this.l.end();
            this.l = null;
        }
    }

    public final void setScreenSize(int i, int i2) {
        if (i == 0 || i2 == 0) {
            i = 1600;
            i2 = 900;
        }
        this.g = i;
        this.h = i2;
        updateMinMaxZoom();
        updateCamera();
    }

    public final void setMapSize(int i, int i2) {
        this.e = i;
        this.f = i2;
        updateMinMaxZoom();
        updateCamera();
    }

    public final boolean isRectVisible(Rectangle rectangle) {
        return this.f1672a.overlaps(rectangle);
    }

    public final boolean isIntRectVisible(IntRectangle intRectangle) {
        return this.f1672a.overlaps(u.set(intRectangle.minX, intRectangle.minY, intRectangle.maxX - intRectangle.minX, intRectangle.maxY - intRectangle.minY));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final boolean isPointVisible(Vector2 vector2, float f) {
        return isRectVisible(u.set(vector2.x - f, vector2.y - f, f, f));
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final boolean isPointVisible(float f, float f2, float f3) {
        return isRectVisible(u.set(f - f3, f2 - f3, f3, f3));
    }

    public final boolean isRectVisibleMarginSmall(Rectangle rectangle) {
        return this.f1673b.overlaps(rectangle);
    }

    private void c() {
        this.f1672a.set(this.camera.position.x - (this.camera.viewportWidth / 2.0f), this.camera.position.y - (this.camera.viewportHeight / 2.0f), this.camera.viewportWidth, this.camera.viewportHeight);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_VIEWPORT_CULLING) != 0.0d) {
            this.f1672a.width -= this.f1672a.width * 0.5f;
        }
        this.f1673b.set(this.f1672a.x - 64.0f, this.f1672a.y - 64.0f, this.f1672a.width + 128.0f, this.f1672a.height + 128.0f);
        this.s.begin();
        for (int i = 0; i < this.s.size; i++) {
            this.s.items[i].onViewportUpdated(this);
        }
        this.s.end();
    }

    public final void addListener(CameraControllerListener cameraControllerListener) {
        if (cameraControllerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.s.contains(cameraControllerListener, true)) {
            this.s.add(cameraControllerListener);
        }
    }

    public final void removeListener(CameraControllerListener cameraControllerListener) {
        if (cameraControllerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.s.removeValue(cameraControllerListener, true);
    }

    public final void updateMinMaxZoom() {
        if (this.hardZoomLimits) {
            this.c = this.hardMinZoom;
            this.d = this.hardMaxZoom;
            return;
        }
        this.c = (((128.0f / Gdx.graphics.getDensity()) / 160.0f) * 2.54f) / this.m;
        if (this.c < 0.25d) {
            this.c = 0.25d;
        }
        if (this.c > 1.0d) {
            this.c = 1.0d;
        }
        if (this.e / this.g < this.f / this.h) {
            this.d = (this.f / this.h) * this.n;
        } else {
            this.d = (this.e / this.g) * this.n;
        }
        if (this.d > 16.0d) {
            this.d = 16.0d;
        }
        if (this.d < 2.0d) {
            this.d = 2.0d;
        }
    }

    public final void setZoomBoundaries(float f, float f2) {
        this.m = f;
        this.n = f2;
        updateMinMaxZoom();
    }

    public final void setZoom(double d) {
        this.zoom = d;
        if (this.zoom < this.c) {
            this.zoom = this.c;
        } else if (this.zoom > this.d) {
            this.zoom = this.d;
        }
        if (Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Zoom").append(((float) (this.zoom * 100.0d)) / 100.0f);
        }
        updateCamera();
    }

    public final void lookAt(float f, float f2) {
        this.camera.position.set(f, f2, this.camera.position.z);
        updateCamera();
    }

    public final Vector3 getLookPos() {
        return this.camera.position;
    }

    public final void lookAtAlignToViewport(float f, float f2, float f3, float f4) {
        float f5 = this.camera.viewportWidth * f3;
        float f6 = this.camera.viewportHeight * f4;
        lookAt(f + ((this.camera.viewportWidth / 2.0f) - f5), f2 + ((this.camera.viewportHeight / 2.0f) - f6));
    }

    public final void screenToViewport(Vector2 vector2) {
        vector2.x = (vector2.x / this.g) * this.camera.viewportWidth;
        vector2.y = (1.0f - (vector2.y / this.h)) * this.camera.viewportHeight;
    }

    public final void viewportToScreen(Vector2 vector2) {
        vector2.x = (vector2.x / this.camera.viewportWidth) * this.g;
        vector2.y = (1.0f - (vector2.y / this.camera.viewportHeight)) * this.h;
    }

    public final void stageToScreen(Vector2 vector2) {
        float scaledViewportHeight = this.h / Game.i.settingsManager.getScaledViewportHeight();
        vector2.x *= scaledViewportHeight;
        vector2.y = this.h - (vector2.y * scaledViewportHeight);
    }

    public final void screenToStage(Vector2 vector2) {
        float scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() / this.h;
        vector2.x *= scaledViewportHeight;
        vector2.y = Game.i.settingsManager.getScaledViewportHeight() - (vector2.y * scaledViewportHeight);
    }

    public final void screenToMap(Vector2 vector2) {
        screenToViewport(vector2);
        vector2.x = (this.camera.position.x - (this.camera.viewportWidth / 2.0f)) + vector2.x;
        vector2.y = (this.camera.position.y - (this.camera.viewportHeight / 2.0f)) + vector2.y;
    }

    public final void stageToMap(Vector2 vector2) {
        stageToScreen(vector2);
        screenToMap(vector2);
    }

    public final void mapToViewport(Vector2 vector2) {
        vector2.x = (vector2.x - this.camera.position.x) + (this.camera.viewportWidth / 2.0f);
        vector2.y = (vector2.y - this.camera.position.y) + (this.camera.viewportHeight / 2.0f);
    }

    public final void mapToScreen(Vector2 vector2) {
        mapToViewport(vector2);
        viewportToScreen(vector2);
    }

    public final void mapToStage(Vector2 vector2) {
        mapToScreen(vector2);
        screenToStage(vector2);
    }

    private void a(double d, Rectangle rectangle) {
        rectangle.width = (int) (this.g * d);
        rectangle.height = (int) (this.h * d);
    }

    public final void fitMapToScreen(float f) {
        stopAnimations();
        this.currentScenario = null;
        lookAt(this.e / 2, this.f / 2);
        if (this.e / (this.g - (f * 2.0f)) < this.f / (this.h - (f * 2.0f))) {
            setZoom(this.f / r0);
        } else {
            setZoom(this.e / r0);
        }
    }

    public final void realUpdate(float f) {
        if (this.currentScenario != null) {
            this.scenarioTime += f;
            boolean z = false;
            if (this.scenarioTime > this.currentScenario.length / this.currentScenario.fps) {
                if (this.scenarioLooped) {
                    this.scenarioTime %= this.currentScenario.length / this.currentScenario.fps;
                    this.scenarioTime += this.currentScenario.startFrame / this.currentScenario.fps;
                } else {
                    this.scenarioTime = this.currentScenario.length / this.currentScenario.fps;
                    z = true;
                }
            }
            Vector3 calculate = this.currentScenario.calculate(this.scenarioTime);
            this.camera.position.set(Float.isNaN(calculate.x) ? this.camera.position.x : calculate.x, Float.isNaN(calculate.y) ? this.camera.position.y : calculate.y, this.camera.position.z);
            this.zoom = Float.isNaN(calculate.z) ? this.zoom : calculate.z;
            updateCamera();
            if (z) {
                this.currentScenario = null;
            }
        } else if (this.l != null) {
            this.l.update(f);
            if (this.l.isDone()) {
                stopAnimations();
            }
        }
        if (this.o != null && this.i) {
            this.i = false;
            DelayedRemovalArray<Tile> allTiles = this.o.getAllTiles();
            int i = allTiles.size;
            for (int i2 = 0; i2 < i; i2++) {
                allTiles.items[i2].visibleOnScreen = isPointVisible((r0.f1770a << 7) + 64, (r0.f1771b << 7) + 64, 128.0f);
            }
        }
    }

    public final void updateCamera() {
        float scaledViewportHeight = (1.0f / Game.i.settingsManager.getScaledViewportHeight()) * this.h;
        float f = 1200.0f * scaledViewportHeight;
        float f2 = 1200.0f * scaledViewportHeight;
        float scaledViewportHeight2 = Game.i.settingsManager.getScaledViewportHeight() * 0.5f * scaledViewportHeight;
        float scaledViewportHeight3 = Game.i.settingsManager.getScaledViewportHeight() * 0.5f * scaledViewportHeight;
        Rectangle rectangle = p;
        a(this.zoom, rectangle);
        this.camera.viewportWidth = rectangle.width;
        this.camera.viewportHeight = rectangle.height;
        if (this.outmapCheckEnabled) {
            float f3 = (float) (f2 * this.zoom);
            float f4 = (float) (f * this.zoom);
            if (this.e + f3 + f4 <= this.camera.viewportWidth) {
                this.camera.position.x = this.e / 2;
            } else {
                float f5 = (-f3) + (this.camera.viewportWidth / 2.0f);
                float f6 = (this.e + f4) - (this.camera.viewportWidth / 2.0f);
                if (this.camera.position.x < f5) {
                    this.camera.position.x = f5;
                } else if (this.camera.position.x > f6) {
                    this.camera.position.x = f6;
                }
            }
            float f7 = (float) (scaledViewportHeight2 * this.zoom);
            float f8 = (float) (scaledViewportHeight3 * this.zoom);
            if (this.f + f7 + f8 <= this.camera.viewportHeight) {
                this.camera.position.y = this.f / 2;
            } else {
                float f9 = (-f8) + (this.camera.viewportHeight / 2.0f);
                float f10 = (this.f + f7) - (this.camera.viewportHeight / 2.0f);
                if (this.camera.position.y < f9) {
                    this.camera.position.y = f9;
                } else if (this.camera.position.y > f10) {
                    this.camera.position.y = f10;
                }
            }
        }
        this.camera.update();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$_InputProcessor.class */
    public class _InputProcessor implements InputProcessor {

        /* renamed from: a, reason: collision with root package name */
        private int f1680a;

        /* renamed from: b, reason: collision with root package name */
        private int f1681b;
        private long c;
        private boolean d;
        private final IntMap<CameraControllerTouch> e;

        private _InputProcessor() {
            this.e = new IntMap<>();
        }

        /* synthetic */ _InputProcessor(CameraController cameraController, byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean keyDown(int i) {
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean keyUp(int i) {
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean keyTyped(char c) {
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean touchDown(int i, int i2, int i3, int i4) {
            if (!CameraController.this.dragButtonIndices.contains(i4)) {
                return false;
            }
            CameraController.this.stopAnimations();
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.TOUCHES_STOP_CAMERA_SCENARIOS) != 0.0d) {
                CameraController.this.currentScenario = null;
            }
            if (this.e.size > 2) {
                return false;
            }
            CameraControllerTouch cameraControllerTouch = new CameraControllerTouch();
            cameraControllerTouch.setup(i, i2, CameraController.this.camera.position.x, CameraController.this.camera.position.y, CameraController.this.zoom);
            if (this.e.size == 1) {
                CameraControllerTouch cameraControllerTouch2 = null;
                Iterator<CameraControllerTouch> it = this.e.values().iterator();
                if (it.hasNext()) {
                    cameraControllerTouch2 = it.next();
                }
                if (cameraControllerTouch2 != null) {
                    CameraControllerTouch cameraControllerTouch3 = cameraControllerTouch2;
                    cameraControllerTouch3.c = cameraControllerTouch3.x;
                    CameraControllerTouch cameraControllerTouch4 = cameraControllerTouch2;
                    cameraControllerTouch4.d = cameraControllerTouch4.y;
                }
            } else if (this.e.size == 0 && Math.abs(this.f1680a - i) < 32 && Math.abs(this.f1681b - i2) < 32 && Game.getTimestampMillis() - this.c < 300 && Gdx.app.getType() != Application.ApplicationType.Desktop) {
                this.d = true;
            }
            this.e.put(i3, cameraControllerTouch);
            this.f1680a = i;
            this.f1681b = i2;
            this.c = Game.getTimestampMillis();
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean touchUp(int i, int i2, int i3, int i4) {
            this.d = false;
            if (this.e.get(i3) != null) {
                CameraControllerTouch cameraControllerTouch = this.e.get(i3);
                boolean z = false;
                if (cameraControllerTouch.i || cameraControllerTouch.j) {
                    z = true;
                }
                this.e.remove(i3);
                return z;
            }
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean touchCancelled(int i, int i2, int i3, int i4) {
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean touchDragged(int i, int i2, int i3) {
            if (this.e.size != 0) {
                if (this.e.size == 1) {
                    if (this.d) {
                        CameraControllerTouch cameraControllerTouch = null;
                        Iterator<CameraControllerTouch> it = this.e.values().iterator();
                        if (it.hasNext()) {
                            cameraControllerTouch = it.next();
                        }
                        if (cameraControllerTouch == null) {
                            this.d = false;
                            return false;
                        }
                        CameraController.this.setZoom(CameraController.this.zoom + (((((this.f1680a - i) + (this.f1681b - i2)) * 1080.0f) / Game.i.uiManager.getScreenHeight()) * 0.002f));
                        this.f1680a = i;
                        this.f1681b = i2;
                    } else {
                        CameraControllerTouch cameraControllerTouch2 = this.e.get(i3);
                        if (cameraControllerTouch2 != null && !cameraControllerTouch2.i) {
                            int i4 = i - cameraControllerTouch2.f1676a;
                            int i5 = i2 - cameraControllerTouch2.f1677b;
                            CameraController.this.camera.position.set(cameraControllerTouch2.e + ((-i4) * ((float) CameraController.this.zoom)), cameraControllerTouch2.f + (i5 * ((float) CameraController.this.zoom)), CameraController.this.camera.position.z);
                            CameraController.this.updateCamera();
                            if (StrictMath.abs(i4) > 20 || StrictMath.abs(i5) > 20) {
                                cameraControllerTouch2.j = true;
                            }
                        }
                    }
                } else if (this.e.size == 2) {
                    CameraControllerTouch cameraControllerTouch3 = null;
                    CameraControllerTouch cameraControllerTouch4 = null;
                    Iterator<CameraControllerTouch> it2 = this.e.values().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        CameraControllerTouch next = it2.next();
                        if (cameraControllerTouch3 == null) {
                            cameraControllerTouch3 = next;
                        } else {
                            cameraControllerTouch4 = next;
                            break;
                        }
                    }
                    double distanceBetweenPoints = PMath.getDistanceBetweenPoints(cameraControllerTouch3.x, cameraControllerTouch3.y, cameraControllerTouch4.x, cameraControllerTouch4.y) / PMath.getDistanceBetweenPoints(cameraControllerTouch3.c, cameraControllerTouch3.d, cameraControllerTouch4.c, cameraControllerTouch4.d);
                    if (!cameraControllerTouch3.i) {
                        float f = (cameraControllerTouch3.x + cameraControllerTouch4.x) / 2;
                        float f2 = (cameraControllerTouch3.y + cameraControllerTouch4.y) / 2;
                        Vector2 vector2 = new Vector2();
                        vector2.set(f, f2);
                        CameraController.this.screenToMap(vector2);
                        cameraControllerTouch3.h = vector2;
                        cameraControllerTouch4.h = vector2;
                    } else {
                        double d = cameraControllerTouch3.g;
                        CameraController.q.set((cameraControllerTouch3.x + cameraControllerTouch4.x) / 2, (cameraControllerTouch3.y + cameraControllerTouch4.y) / 2);
                        CameraController.this.screenToViewport(CameraController.q);
                        float f3 = CameraController.q.x / CameraController.this.camera.viewportWidth;
                        float f4 = CameraController.q.y / CameraController.this.camera.viewportHeight;
                        CameraController.this.setZoom(cameraControllerTouch3.g / distanceBetweenPoints);
                        if (CameraController.this.zoom != d && cameraControllerTouch3.h != null) {
                            CameraController.this.lookAtAlignToViewport((int) cameraControllerTouch3.h.x, (int) cameraControllerTouch3.h.y, f3, f4);
                        }
                    }
                    cameraControllerTouch3.i = true;
                    cameraControllerTouch4.i = true;
                }
            }
            CameraControllerTouch cameraControllerTouch5 = this.e.get(i3);
            if (cameraControllerTouch5 != null) {
                cameraControllerTouch5.x = i;
                cameraControllerTouch5.y = i2;
                return false;
            }
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean mouseMoved(int i, int i2) {
            CameraController.this.j = i;
            CameraController.this.k = i2;
            return false;
        }

        @Override // com.badlogic.gdx.InputProcessor
        public boolean scrolled(float f, float f2) {
            double d;
            if (CameraController.this.scrollZoomRequiresAlt && !Gdx.input.isKeyPressed(57)) {
                return false;
            }
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.TOUCHES_STOP_CAMERA_SCENARIOS) != 0.0d) {
                CameraController.this.currentScenario = null;
            }
            CameraController.this.stopAnimations();
            Vector2 vector2 = CameraController.q;
            Vector2 vector22 = CameraController.r;
            vector2.set(CameraController.this.j, CameraController.this.k);
            CameraController.this.screenToMap(vector2);
            vector22.set(CameraController.this.j, CameraController.this.k);
            CameraController.this.screenToViewport(vector22);
            float f3 = vector22.x / CameraController.this.camera.viewportWidth;
            float f4 = vector22.y / CameraController.this.camera.viewportHeight;
            double d2 = CameraController.this.zoom;
            double revZoomPercent = CameraController.this.getRevZoomPercent();
            if (f2 > 0.0f) {
                d = revZoomPercent * 0.9d;
            } else {
                d = revZoomPercent * 1.1111111111111112d;
            }
            CameraController.this.setRevZoomPercent(d);
            if (CameraController.this.zoom != d2) {
                CameraController.this.lookAtAlignToViewport((int) vector2.x, (int) vector2.y, f3, f4);
                return false;
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$CameraControllerTouch.class */
    private static class CameraControllerTouch {
        public int x;
        public int y;

        /* renamed from: a, reason: collision with root package name */
        int f1676a;

        /* renamed from: b, reason: collision with root package name */
        int f1677b;
        int c;
        int d;
        float e;
        float f;
        double g;
        Vector2 h;
        boolean i = false;
        boolean j = false;

        CameraControllerTouch() {
        }

        public void setup(int i, int i2, float f, float f2, double d) {
            this.x = i;
            this.y = i2;
            this.f1676a = i;
            this.f1677b = i2;
            this.c = i;
            this.d = i2;
            this.e = f;
            this.f = f2;
            this.g = d;
        }

        public String toString() {
            return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.f1676a + ", " + this.f1677b + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$BasicAnimation.class */
    public static class BasicAnimation implements CameraControllerAnimation {

        /* renamed from: a, reason: collision with root package name */
        private float f1674a;

        /* renamed from: b, reason: collision with root package name */
        private float f1675b;
        private double c;
        private float d;
        private CameraController e;
        private float f;
        private float g;
        private double h;
        private float i;
        private Interpolation j;

        public BasicAnimation(float f, float f2, double d, float f3, Interpolation interpolation) {
            this.f = f;
            this.g = f2;
            this.h = d;
            this.i = f3;
            this.j = interpolation;
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void start(CameraController cameraController) {
            this.f1674a = cameraController.camera.position.x;
            this.f1675b = cameraController.camera.position.y;
            this.c = cameraController.zoom;
            this.d = 0.0f;
            this.e = cameraController;
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void update(float f) {
            this.d += f;
            if (this.d >= this.i) {
                this.d = this.i;
            }
            float apply = this.j.apply(this.d / this.i);
            this.e.camera.position.set(this.f1674a + ((this.f - this.f1674a) * apply), this.f1675b + ((this.g - this.f1675b) * apply), this.e.camera.position.z);
            this.e.zoom = this.c + ((this.h - this.c) * apply);
            this.e.updateCamera();
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void end() {
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public boolean isDone() {
            return this.d == this.i;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CameraController$ShakeAnimation.class */
    public static class ShakeAnimation implements CameraControllerAnimation {

        /* renamed from: a, reason: collision with root package name */
        private float f1678a;

        /* renamed from: b, reason: collision with root package name */
        private float f1679b;
        private float c;
        private CameraController d;
        private float e;
        private float f;

        public ShakeAnimation(float f, float f2) {
            this.e = f;
            this.f = f2;
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void start(CameraController cameraController) {
            this.f1678a = cameraController.camera.position.x;
            this.f1679b = cameraController.camera.position.y;
            this.c = 0.0f;
            this.d = cameraController;
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void update(float f) {
            this.c += f;
            if (this.c >= this.f) {
                this.c = this.f;
            }
            float apply = this.e * (1.0f - Interpolation.exp5Out.apply(this.c / this.f));
            this.d.camera.position.set(this.f1678a + (((FastRandom.getFloat() * 2.0f) - 1.0f) * apply), this.f1679b + (((FastRandom.getFloat() * 2.0f) - 1.0f) * apply), this.d.camera.position.z);
            this.d.updateCamera();
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public void end() {
            this.d.camera.position.set(this.f1678a, this.f1679b, this.d.camera.position.z);
            this.d.updateCamera();
        }

        @Override // com.prineside.tdi2.CameraController.CameraControllerAnimation
        public boolean isDone() {
            return this.c == this.f;
        }
    }
}

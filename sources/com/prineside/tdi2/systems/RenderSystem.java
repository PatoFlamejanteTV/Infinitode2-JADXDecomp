package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.game.GameStateTick;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RenderSystem.class */
public final class RenderSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3052a = TLog.forClass(RenderSystem.class);
    public float gameOverGameSpeed;
    public float gameOverInterpolatedTime;
    private float d;
    public float currentInGameDeltaTime;
    public float currentInterpolatedDeltaTime;
    public float currentRealDeltaTime;
    private boolean e;
    private FrameBuffer f;

    /* renamed from: b, reason: collision with root package name */
    private final Array<Layer> f3053b = new Array<>(true, 1, Layer.class);
    public long gameOverTimestamp = -1;
    private OrthographicCamera c = new OrthographicCamera(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RenderSystem$LayerRenderer.class */
    public interface LayerRenderer {
        void render(Batch batch, float f, float f2, float f3);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(GameStateTick.class).add(gameStateTick -> {
            this.d = 0.0f;
        });
        this.S._render.addLayer(new Layer(GameRenderingOrder.EFFECTS_FBO_START, false, (batch, f, f2, f3) -> {
            this.e = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) != 0.0d;
            double customValue = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_EFFECTS_SCALE);
            if (this.e && ((this.e && (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_CLEAN_DETAILED_MODE) > 0.0d ? 1 : (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_CLEAN_DETAILED_MODE) == 0.0d ? 0 : -1)) != 0) || customValue < 1.0d)) {
                try {
                    double clamp = MathUtils.clamp(customValue, 0.5d, 1.0d);
                    int screenWidth = Game.i.uiManager.getScreenWidth();
                    int screenHeight = Game.i.uiManager.getScreenHeight();
                    int round = MathUtils.round(((float) clamp) * screenWidth);
                    int round2 = MathUtils.round(((float) clamp) * screenHeight);
                    if (round2 != 0 && (this.f == null || this.f.getWidth() != round)) {
                        f3052a.i("recreating effects FBO with size " + round + "x" + round2, new Object[0]);
                        if (this.f != null) {
                            this.f.dispose();
                            this.f = null;
                        }
                        this.f = new FrameBuffer(Pixmap.Format.RGB888, round, round2, false);
                    }
                    if (this.f != null) {
                        batch.flush();
                        Game.i.renderingManager.startFBO(this.f, "RenderSystemEffects");
                        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                        Gdx.gl.glClear(16640);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    throw new IllegalStateException("failed to recreate / begin drawing to effects FBO", e);
                }
            }
            if (this.f != null) {
                this.f.dispose();
                this.f = null;
            }
        }).setName("Render-effectsFboStart"));
        this.S._render.addLayer(new Layer(GameRenderingOrder.EFFECTS_FBO_END, false, (batch2, f4, f5, f6) -> {
            if (this.f != null) {
                batch2.flush();
                Game.i.renderingManager.endFBO("RenderSystemEffects");
                Matrix4 projectionMatrix = batch2.getProjectionMatrix();
                projectionMatrix.setToOrtho2D(0.0f, 0.0f, Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
                batch2.setProjectionMatrix(projectionMatrix);
                batch2.setBlendFunction(770, 1);
                if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PP_CLEAN_DETAILED_MODE) != 0.0d && this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
                    batch2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                } else {
                    batch2.setColor(Color.WHITE);
                }
                Texture colorBufferTexture = this.f.getColorBufferTexture();
                Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
                colorBufferTexture.setFilter(textureFilter, textureFilter);
                batch2.draw(colorBufferTexture, 0.0f, 0.0f, Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight(), 0, 0, this.f.getWidth(), this.f.getHeight(), false, true);
                batch2.flush();
                batch2.setColor(Color.WHITE);
                batch2.setProjectionMatrix(this.c.combined);
            }
        }).setName("Render-effectsFboEnd"));
        this.S.events.getListeners(Render.class).addWithPriority(this::calculateDeltaTimes, 2000).setDescription("RenderSystem - calculates and updates frame time deltas, sets the interpolated delta time");
        this.S.events.getListeners(Render.class).addWithPriority(render -> {
            drawLayers(Game.i.renderingManager.batch);
        }, EventListeners.PRIORITY_VERY_LOW).setDescription("RenderSystem - draws layers");
    }

    public final OrthographicCamera getCamera() {
        return this.c;
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        if (this.f != null) {
            this.f.dispose();
            this.f = null;
        }
    }

    @Null
    public final Layer getLayer(int i) {
        for (int i2 = 0; i2 < this.f3053b.size; i2++) {
            Layer layer = this.f3053b.items[i2];
            if (layer.f3054a == i) {
                return layer;
            }
            if (layer.f3054a > i) {
                return null;
            }
        }
        return null;
    }

    @Null
    public final Layer removeLayerByZIndex(int i) {
        for (int i2 = 0; i2 < this.f3053b.size; i2++) {
            Layer layer = this.f3053b.items[i2];
            if (layer.f3054a == i) {
                return this.f3053b.removeIndex(i2);
            }
            if (layer.f3054a > i) {
                return null;
            }
        }
        return null;
    }

    @Null
    public final Layer removeLayer(Layer layer) {
        for (int i = 0; i < this.f3053b.size; i++) {
            if (layer == this.f3053b.items[i]) {
                return this.f3053b.removeIndex(i);
            }
        }
        return null;
    }

    public final void addLayer(Layer layer) {
        removeLayerByZIndex(layer.f3054a);
        this.f3053b.add(layer);
        a();
    }

    public final void addLayerAfter(Layer layer) {
        int i = layer.f3054a;
        while (getLayer(i) != null) {
            i++;
        }
        layer.f3054a = i;
        addLayer(layer);
    }

    public final void addLayerBefore(Layer layer) {
        int i = layer.f3054a;
        while (getLayer(i) != null) {
            i--;
        }
        layer.f3054a = i;
        addLayer(layer);
    }

    private void a() {
        this.f3053b.sort((layer, layer2) -> {
            return Integer.compare(layer.f3054a, layer2.f3054a);
        });
    }

    public final void calculateDeltaTimes(Render render) {
        float inGameDeltaTime = render.getInGameDeltaTime();
        float realDeltaTime = render.getRealDeltaTime();
        if (inGameDeltaTime < 0.0f || Float.isInfinite(inGameDeltaTime) || Float.isNaN(inGameDeltaTime)) {
            inGameDeltaTime = 0.0f;
        } else if (inGameDeltaTime > 10.0f) {
            inGameDeltaTime = 10.0f;
        }
        if (realDeltaTime < 0.0f) {
            realDeltaTime = 0.0f;
        }
        float f = this.d;
        if (this.S.gameValue != null && f > this.S.gameValue.getTickRateDeltaTime()) {
            f = this.S.gameValue.getTickRateDeltaTime();
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.GRAPHICS_INTERPOLATION_ENABLED) == 0.0d) {
            f = 0.0f;
        }
        if (this.S.gameState != null && this.S.gameState.isGameOver()) {
            if (this.gameOverTimestamp == -1) {
                this.gameOverTimestamp = Game.getRealTickCount();
            }
            float realTickCount = 1.0f - (((float) (Game.getRealTickCount() - this.gameOverTimestamp)) / 2500000.0f);
            float f2 = realTickCount;
            if (realTickCount < 0.0f) {
                f2 = 0.0f;
            }
            inGameDeltaTime = realDeltaTime * (realDeltaTime / this.S.gameValue.getTickRateDeltaTime()) * this.gameOverGameSpeed * f2;
            this.gameOverInterpolatedTime += inGameDeltaTime;
            realDeltaTime *= f2;
            f = this.gameOverInterpolatedTime;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.currentInGameDeltaTime = inGameDeltaTime;
        this.currentInterpolatedDeltaTime = f;
        this.currentRealDeltaTime = realDeltaTime;
        this.d += inGameDeltaTime;
        render.setInGameDeltaTime(inGameDeltaTime);
        render.setRealDeltaTime(realDeltaTime);
        render.setInterpolatedTime(f);
    }

    public final void drawLayers(Batch batch) {
        for (int i = 0; i < this.f3053b.size; i++) {
            Layer layer = this.f3053b.items[i];
            long realTickCount = Game.getRealTickCount();
            prepareBatch(batch, layer.isAdditive());
            layer.renderer.render(batch, this.currentRealDeltaTime, this.currentInGameDeltaTime, this.currentInterpolatedDeltaTime);
            Game.i.debugManager.registerFrameJob(layer.name, Game.getRealTickCount() - realTickCount);
        }
        batch.flush();
    }

    public final void prepareBatch(Batch batch, boolean z) {
        RenderingManager.prepareBatch(batch, z);
        batch.setProjectionMatrix(this.c.combined);
        batch.setTransformMatrix(new Matrix4());
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Render";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/RenderSystem$Layer.class */
    public static final class Layer {

        /* renamed from: a, reason: collision with root package name */
        private int f3054a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3055b;
        public LayerRenderer renderer;
        public String name = "unnamed";

        public Layer(int i, boolean z, LayerRenderer layerRenderer) {
            this.f3054a = i;
            this.f3055b = z;
            setRenderer(layerRenderer);
        }

        public final boolean isAdditive() {
            return this.f3055b;
        }

        public final Layer setAdditive(boolean z) {
            this.f3055b = z;
            return this;
        }

        public final String getName() {
            return this.name;
        }

        public final Layer setName(String str) {
            Preconditions.checkNotNull(str);
            this.name = str;
            return this;
        }

        public final int getZ() {
            return this.f3054a;
        }

        public final LayerRenderer getRenderer() {
            return this.renderer;
        }

        public final Layer setRenderer(LayerRenderer layerRenderer) {
            Preconditions.checkNotNull(layerRenderer);
            this.renderer = layerRenderer;
            return this;
        }
    }
}

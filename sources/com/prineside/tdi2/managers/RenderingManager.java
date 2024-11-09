package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/RenderingManager.class */
public class RenderingManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2425a = TLog.forClass(RenderingManager.class);
    private final Array<ObjectPair<String, FrameBuffer>> d = new Array<>(true, 1, ObjectPair.class);
    private final DelayedRemovalArray<DebugPoint> e = new DelayedRemovalArray<>(true, 1, DebugPoint.class);
    public final ShapeRenderer shapeRenderer = new ShapeRenderer(5000, createDefaultShapeRendererShader());

    /* renamed from: b, reason: collision with root package name */
    private final Matrix4 f2426b = new Matrix4(this.shapeRenderer.getTransformMatrix());
    public final ModelBatch modelBatch = new ModelBatch();
    public final SpriteBatch batch = new SpriteBatch(8191, createDefaultSpriteBatchShader());
    private final Matrix4 c = new Matrix4(this.batch.getTransformMatrix());

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/RenderingManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<RenderingManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public RenderingManager read() {
            return Game.i.renderingManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
    }

    public int getMSAASampleCount() {
        return Game.i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS);
    }

    public int getCurrentRenderTargetWidth() {
        ObjectPair<String, FrameBuffer> currentFBO = getCurrentFBO();
        if (currentFBO != null) {
            return currentFBO.second.getWidth();
        }
        return Game.i.uiManager.getScreenWidth();
    }

    public int getCurrentRenderTargetHeight() {
        ObjectPair<String, FrameBuffer> currentFBO = getCurrentFBO();
        if (currentFBO != null) {
            return currentFBO.second.getHeight();
        }
        return Game.i.uiManager.getScreenHeight();
    }

    @Null
    public ObjectPair<String, FrameBuffer> getCurrentFBO() {
        if (this.d.size == 0) {
            return null;
        }
        return this.d.get(this.d.size - 1);
    }

    public void startFBO(FrameBuffer frameBuffer, String str) {
        Preconditions.checkNotNull(frameBuffer, "buffer can not be null");
        Preconditions.checkNotNull(str, "name can not be null");
        ObjectPair<String, FrameBuffer> currentFBO = getCurrentFBO();
        if (currentFBO != null && currentFBO.second == frameBuffer) {
            throw new IllegalArgumentException("Can't start currently active FBO");
        }
        if (currentFBO != null) {
            currentFBO.second.end();
        }
        this.d.add(new ObjectPair<>(str, frameBuffer));
        frameBuffer.begin();
    }

    public void endFBO(String str) {
        Preconditions.checkNotNull(str, "name can not be null");
        ObjectPair<String, FrameBuffer> currentFBO = getCurrentFBO();
        if (currentFBO == null) {
            throw new IllegalArgumentException("No FBO currently active");
        }
        if (!currentFBO.first.equals(str)) {
            throw new IllegalArgumentException("Incorrect active FBO provided (" + str + ") - some other FBO is currently active (" + currentFBO.first + ")");
        }
        currentFBO.second.end();
        this.d.pop();
        if (this.d.size != 0) {
            this.d.get(this.d.size - 1).second.begin();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        resetTransformState();
    }

    public void addDebugPoint(float f, float f2, float f3, Color color) {
        try {
            DebugPoint debugPoint = new DebugPoint((byte) 0);
            debugPoint.x = f;
            debugPoint.y = Gdx.graphics.getHeight() - f2;
            debugPoint.size = f3;
            debugPoint.color = color;
            debugPoint.duration = 1.0f;
            debugPoint.existsTime = 0.0f;
            this.e.add(debugPoint);
        } catch (Exception unused) {
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void postRender(float f) {
        if (this.e.size != 0) {
            this.e.begin();
            for (int i = 0; i < this.e.size; i++) {
                DebugPoint debugPoint = this.e.get(i);
                debugPoint.existsTime += f;
                if (debugPoint.existsTime >= debugPoint.duration) {
                    this.e.removeIndex(i);
                }
            }
            this.e.end();
            if (this.e.size != 0) {
                prepareBatch(this.batch, false);
                Matrix4 projectionMatrix = this.batch.getProjectionMatrix();
                projectionMatrix.setToOrtho2D(0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                this.batch.setProjectionMatrix(projectionMatrix);
                for (int i2 = 0; i2 < this.e.size; i2++) {
                    DebugPoint debugPoint2 = this.e.get(i2);
                    this.batch.setColor(debugPoint2.color);
                    this.batch.setColor(this.batch.getColor().mul(1.0f, 1.0f, 1.0f, (debugPoint2.duration - debugPoint2.existsTime) / debugPoint2.duration));
                    this.batch.draw(AssetManager.TextureRegions.i().smallCircle, debugPoint2.x - (debugPoint2.size * 0.5f), debugPoint2.y - (debugPoint2.size * 0.5f), debugPoint2.size, debugPoint2.size);
                }
                this.batch.end();
            }
        }
    }

    public void resetTransformState() {
        this.batch.setTransformMatrix(this.c);
        this.shapeRenderer.setTransformMatrix(this.f2426b);
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.shapeRenderer.dispose();
        this.batch.dispose();
    }

    public static void setBatchAdditiveBlending(Batch batch, boolean z) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DISABLE_ADDITIVE_BLENDING) != 0.0d) {
            z = false;
        }
        if (z) {
            if (batch.getBlendSrcFunc() != 770 || batch.getBlendDstFunc() != 1) {
                if (batch.isDrawing()) {
                    batch.flush();
                }
                batch.setBlendFunction(770, 1);
                return;
            }
            return;
        }
        if (batch.getBlendSrcFunc() != 770 || batch.getBlendDstFunc() != 771) {
            if (batch.isDrawing()) {
                batch.flush();
            }
            batch.setBlendFunction(770, 771);
        }
    }

    public static boolean isAdditiveBatch(Batch batch) {
        return batch.getBlendSrcFunc() == 770 && batch.getBlendDstFunc() == 1;
    }

    public static <T extends Batch> T prepareBatch(T t, boolean z) {
        t.flush();
        setBatchAdditiveBlending(t, z);
        if (!t.isDrawing()) {
            t.begin();
        }
        t.setColor(Color.WHITE);
        return t;
    }

    public void stopAnyBatchDrawing() {
        if (this.batch.isDrawing()) {
            this.batch.end();
        }
        if (this.shapeRenderer.isDrawing()) {
            this.shapeRenderer.end();
        }
    }

    public static ShaderProgram createShader(String str) {
        f2425a.d("createShader %s", str);
        String readString = AssetManager.localOrInternalFile("shaders/" + str + ".vert.glsl").readString("UTF-8");
        String readString2 = AssetManager.localOrInternalFile("shaders/" + str + ".frag.glsl").readString("UTF-8");
        try {
            ShaderProgram shaderProgram = new ShaderProgram(readString, readString2);
            if (!shaderProgram.isCompiled()) {
                throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());
            }
            f2425a.i("successfully compiled the original shader", new Object[0]);
            return shaderProgram;
        } catch (Throwable unused) {
            f2425a.w("failed to compile original shader, adding #version", new Object[0]);
            String str2 = "#version 330\n" + readString;
            String str3 = "#version 330\n" + readString2;
            ShaderProgram shaderProgram2 = new ShaderProgram(str2, str3);
            if (!shaderProgram2.isCompiled()) {
                throw new IllegalArgumentException("Error compiling shader: " + shaderProgram2.getLog() + "\nVertex:\n" + str2 + "\nFragment:\n" + str3);
            }
            f2425a.i("successfully compiled the shader with #version", new Object[0]);
            return shaderProgram2;
        }
    }

    public static ShaderProgram createDefaultSpriteBatchShader() {
        return createShader("SpriteBatch");
    }

    public static ShaderProgram createDefaultShapeRendererShader() {
        return createShader("ShapeRenderer");
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/RenderingManager$DebugPoint.class */
    private static final class DebugPoint {
        public float x;
        public float y;
        public float size;
        public Color color;
        public float duration;
        public float existsTime;

        private DebugPoint() {
        }

        /* synthetic */ DebugPoint(byte b2) {
            this();
        }
    }
}

package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.items.TrophyItem;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.nio.Buffer;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TrophyManager.class */
public class TrophyManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2486a = TLog.forClass(TrophyManager.class);

    /* renamed from: b, reason: collision with root package name */
    private final TrophyConfig[] f2487b = new TrophyConfig[TrophyType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TrophyManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<TrophyManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TrophyManager read() {
            return Game.i.trophyManager;
        }
    }

    public TrophyManager() {
        for (int i = 0; i < TrophyType.values.length; i++) {
            this.f2487b[i] = new TrophyConfig(this);
        }
        int i2 = 0;
        Iterator<JsonValue> iterator2 = new JsonReader().parse(Gdx.files.internal("res/trophies.json")).iterator2();
        while (iterator2.hasNext()) {
            JsonValue next = iterator2.next();
            try {
                TrophyType valueOf = TrophyType.valueOf(next.name);
                TrophyConfig config = getConfig(valueOf);
                TrophyConfig.a(config, false);
                Array array = new Array(GameValueManager.GameValueEffect.class);
                Iterator<JsonValue> iterator22 = next.get("gameValues").iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next2 = iterator22.next();
                    array.add(new GameValueManager.GameValueEffect(GameValueType.valueOf(next2.name), next2.asDouble()));
                }
                config.a(valueOf, (Array<GameValueManager.GameValueEffect>) array);
                i2++;
            } catch (Exception e) {
                f2486a.e("failed to load game value config '" + next.name + "'", e);
            }
        }
        if (i2 != this.f2487b.length) {
            throw new RuntimeException("Loaded only " + i2 + " out of " + this.f2487b.length + " trophy configs");
        }
    }

    public String getHowToObtainHint(TrophyType trophyType) {
        switch (trophyType) {
            case SPECIAL_STORYLINE:
                return Game.i.localeManager.i18n.get("trophy_obtain_hint_SPECIAL_STORYLINE");
            case SPECIAL_DEVELOPER:
                return Game.i.localeManager.i18n.get("trophy_obtain_hint_SPECIAL_DEVELOPER");
            case SPECIAL_MILLION:
                return Game.i.localeManager.i18n.get("trophy_obtain_hint_SPECIAL_MILLION");
            case SPECIAL_MASTER:
                return Game.i.localeManager.i18n.get("trophy_obtain_hint_SPECIAL_MASTER");
            default:
                Array<BasicLevel> array = Game.i.basicLevelManager.levelsOrdered;
                for (int i = 0; i < array.size; i++) {
                    BasicLevel basicLevel = array.get(i);
                    for (int i2 = 0; i2 < basicLevel.waveQuests.size; i2++) {
                        BasicLevel.WaveQuest waveQuest = basicLevel.waveQuests.get(i2);
                        for (int i3 = 0; i3 < waveQuest.prizes.size; i3++) {
                            ItemStack itemStack = waveQuest.prizes.get(i3);
                            if ((itemStack.getItem() instanceof TrophyItem) && ((TrophyItem) itemStack.getItem()).trophyType == trophyType) {
                                return Game.i.localeManager.i18n.format("trophy_obtain_hint_beat_wave", Integer.valueOf(waveQuest.waves), basicLevel.name);
                            }
                        }
                    }
                }
                return "I don't know how obtain it";
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
    }

    public TrophyConfig getConfig(TrophyType trophyType) {
        return this.f2487b[trophyType.ordinal()];
    }

    public TrophyConfig[] getConfigs() {
        return this.f2487b;
    }

    public void renderPreviews(String str, int i, float f) {
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, i, i, true);
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(67.0f, i, i);
        perspectiveCamera.position.set(0.32f, 0.4f, 0.55f);
        perspectiveCamera.lookAt(0.0f, 0.0f, 0.0f);
        perspectiveCamera.near = 0.01f;
        perspectiveCamera.far = 300.0f;
        perspectiveCamera.update();
        Environment environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.6f, 0.6f, 0.6f, 1.0f));
        environment.add(new PointLight().set(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f));
        Model sceneModelIfLoaded = Game.i.assetManager.getSceneModelIfLoaded();
        if (sceneModelIfLoaded != null) {
            for (TrophyType trophyType : TrophyType.values) {
                String str2 = str + trophyType.name() + ".png";
                String str3 = "t-" + trophyType.name();
                ModelInstance modelInstance = new ModelInstance(sceneModelIfLoaded, str3, true, true, true);
                Node node = modelInstance.getNode(str3);
                node.translation.set(0.0f, 0.0f, 0.0f);
                node.rotation.setFromAxis(Vector3.X, -90.0f);
                modelInstance.calculateTransforms();
                modelInstance.transform.setToTranslationAndScaling(new Vector3(0.0f, 0.0f, 0.0f), new Vector3(f, f, f));
                Game.i.renderingManager.startFBO(frameBuffer, "TrophyManagerPreview");
                Gdx.gl.glClearColor(Config.BACKGROUND_COLOR.r, Config.BACKGROUND_COLOR.g, Config.BACKGROUND_COLOR.f888b, 0.0f);
                Gdx.gl.glClear(16640);
                Game.i.renderingManager.modelBatch.begin(perspectiveCamera);
                Game.i.renderingManager.modelBatch.render(modelInstance, environment);
                Game.i.renderingManager.modelBatch.end();
                byte[] frameBufferPixels = ScreenUtils.getFrameBufferPixels(0, 0, frameBuffer.getWidth(), frameBuffer.getHeight(), true);
                Pixmap pixmap = new Pixmap(frameBuffer.getWidth(), frameBuffer.getHeight(), Pixmap.Format.RGBA8888);
                BufferUtils.copy(frameBufferPixels, 0, (Buffer) pixmap.getPixels(), frameBufferPixels.length);
                PixmapIO.writePNG(Gdx.files.local(str2), pixmap);
                pixmap.dispose();
                Game.i.renderingManager.endFBO("TrophyManagerPreview");
            }
        }
        frameBuffer.dispose();
        f2486a.i("trophies rendered", new Object[0]);
    }

    public Array<TrophyType> getReceivedTrophies() {
        Array<TrophyType> array = new Array<>(true, 1, TrophyType.class);
        DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TROPHY);
        for (int i = 0; i < itemsByType.size; i++) {
            array.add(((TrophyItem) itemsByType.get(i).getItem()).trophyType);
        }
        return array;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (TrophyConfig trophyConfig : this.f2487b) {
            trophyConfig.getTitle();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TrophyManager$TrophyConfig.class */
    public class TrophyConfig {
        public TrophyType type;
        public Array<GameValueManager.GameValueEffect> gameValues;

        /* renamed from: a, reason: collision with root package name */
        private String f2489a;

        /* renamed from: b, reason: collision with root package name */
        private String f2490b;
        private String c;
        private boolean d;

        public TrophyConfig(TrophyManager trophyManager) {
        }

        static /* synthetic */ boolean a(TrophyConfig trophyConfig, boolean z) {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(TrophyType trophyType, Array<GameValueManager.GameValueEffect> array) {
            this.type = trophyType;
            this.gameValues = array;
            this.f2489a = "tr_title_" + trophyType.name();
            this.f2490b = "trophy-" + trophyType.name() + "-white";
            this.c = "trophy-" + trophyType.name();
            this.d = true;
        }

        public boolean isReceived() {
            if (!this.d) {
                throw new IllegalStateException("Config is not setup");
            }
            DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TROPHY);
            for (int i = 0; i < itemsByType.size; i++) {
                if (((TrophyItem) itemsByType.get(i).getItem()).trophyType == this.type) {
                    return true;
                }
            }
            return false;
        }

        public String getTitle() {
            return Game.i.localeManager.i18n.get(this.f2489a);
        }

        public TextureRegion getIconTexture() {
            return Game.i.assetManager.getTextureRegion(this.c);
        }

        public TextureRegion getWhiteTexture() {
            return Game.i.assetManager.getTextureRegion(this.f2490b);
        }
    }
}

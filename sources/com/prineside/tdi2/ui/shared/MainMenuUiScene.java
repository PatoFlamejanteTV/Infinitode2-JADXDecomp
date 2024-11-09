package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.TrophyManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ActorGestureListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.ModelView;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/MainMenuUiScene.class */
public final class MainMenuUiScene extends UiManager.UiComponent.Adapter {

    /* renamed from: b, reason: collision with root package name */
    private ModelView f3703b;
    private Table c = new Table();
    private final AtomicBoolean d = new AtomicBoolean(false);
    private int e = -1;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3702a = TLog.forClass(MainMenuUiScene.class);
    private static final float[] f = new float[9];
    private static final Color g = new Color();

    public static MainMenuUiScene i() {
        return (MainMenuUiScene) Game.i.uiManager.getComponent(MainMenuUiScene.class);
    }

    public MainMenuUiScene() {
        recreate();
    }

    public final void recreate() {
        long timestampMillis = Game.getTimestampMillis();
        this.e = -1;
        if (!Game.i.settingsManager.isThreeDeeModelsEnabled()) {
            if (this.f3703b != null) {
                this.f3703b.dispose();
                this.f3703b = null;
            }
            this.c.clearListeners();
            this.c.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.MainMenuUiScene.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f2, float f3) {
                    TrophiesListOverlay.i().show();
                }
            });
            this.c.setTouchable(Touchable.enabled);
        } else if (this.f3703b == null) {
            Environment environment = new Environment();
            environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.65f, 0.65f, 0.65f, 1.0f));
            final PointLight pointLight = new PointLight();
            environment.add(pointLight);
            PointLight pointLight2 = new PointLight();
            pointLight2.set(MaterialColor.CYAN.P200, 0.0f, 0.5f, 0.0f, 0.3f);
            environment.add(pointLight2);
            final boolean[] zArr = {true};
            final float[] fArr = {0.0f};
            final float[] fArr2 = {0.45f};
            final float[] fArr3 = {0.0f};
            this.f3703b = new ModelView(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight(), new ModelView.Transformer(this) { // from class: com.prineside.tdi2.ui.shared.MainMenuUiScene.2

                /* renamed from: a, reason: collision with root package name */
                private float f3704a = 20.0f;

                /* renamed from: b, reason: collision with root package name */
                private float f3705b = 360.0f;
                private float c = 25.0f;

                @Override // com.prineside.tdi2.ui.actors.ModelView.Transformer
                public void transform(ModelView modelView, float f2, float f3) {
                    if (zArr[0]) {
                        float f4 = this.c * f2;
                        if (fArr3[0] < (-this.f3705b)) {
                            fArr3[0] = -this.f3705b;
                        } else if (fArr3[0] > this.f3705b) {
                            fArr3[0] = this.f3705b;
                        }
                        if ((fArr3[0] < 0.0f && this.f3704a > 0.0f) || (fArr3[0] > 0.0f && this.f3704a < 0.0f)) {
                            this.f3704a = -this.f3704a;
                        }
                        if (fArr3[0] < this.f3704a) {
                            float[] fArr4 = fArr3;
                            fArr4[0] = fArr4[0] + f4;
                            if (fArr3[0] > this.f3704a) {
                                fArr3[0] = this.f3704a;
                            }
                        } else if (fArr3[0] > this.f3704a) {
                            float[] fArr5 = fArr3;
                            fArr5[0] = fArr5[0] - f4;
                            if (fArr3[0] < this.f3704a) {
                                fArr3[0] = this.f3704a;
                            }
                        }
                        float[] fArr6 = fArr;
                        fArr6[0] = fArr6[0] + (fArr3[0] * f2);
                    }
                    fArr[0] = fArr[0] % 360.0f;
                    float apply = Interpolation.circleOut.apply(1.0f - ((fArr2[0] / 1.15f) * 0.99f));
                    Vector2 vector2 = new Vector2();
                    PMath.getPointByAngleFromPoint(0.0f, 0.0f, fArr[0], 1.1f * apply, vector2);
                    if (modelView == null) {
                        throw new IllegalStateException("modelView is null");
                    }
                    if (modelView.camera == null) {
                        throw new IllegalStateException("modelView.camera is null");
                    }
                    if (modelView.camera.position == null) {
                        throw new IllegalStateException("modelView.camera.position is null");
                    }
                    modelView.camera.position.set(vector2.x, fArr2[0], vector2.y);
                    modelView.camera.up.set(0.0f, 1.0f, 0.0f);
                    modelView.camera.lookAt(0.0f, 0.35f * apply, 0.0f);
                    modelView.camera.update();
                    Vector2 vector22 = new Vector2();
                    PMath.getPointByAngleFromPoint(0.0f, 0.0f, fArr[0] - 45.0f, 3.0f, vector22);
                    PMath.getPointByAngleFromPoint(vector2.x, vector2.y, fArr[0] - 90.0f, 1.0f, vector22);
                    pointLight.set(0.55f, 0.55f, 0.55f, vector22.x, fArr2[0] + 1.0f, vector22.y, 2.5f);
                    modelView.requireRedraw();
                }
            }, environment, true);
            this.f3703b.addListener(new ActorGestureListener(this) { // from class: com.prineside.tdi2.ui.shared.MainMenuUiScene.3
                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                    zArr[0] = false;
                    fArr3[0] = 0.0f;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void touchUp(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                    zArr[0] = true;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void fling(InputEvent inputEvent, float f2, float f3, int i) {
                    fArr3[0] = f2 * 0.05f;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void pan(InputEvent inputEvent, float f2, float f3, float f4, float f5) {
                    fArr[0] = fArr[0] + (f4 * 0.1f);
                    float[] fArr4 = fArr2;
                    fArr4[0] = fArr4[0] - (f5 * 0.001f);
                    fArr2[0] = MathUtils.clamp(fArr2[0], 0.3f, 1.15f);
                }

                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void zoom(InputEvent inputEvent, float f2, float f3) {
                }

                @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
                public void tap(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                    TrophiesListOverlay.i().show();
                }
            });
        }
        f3702a.d("recreate took " + (Game.getTimestampMillis() - timestampMillis) + "ms", new Object[0]);
    }

    public final Actor getContents() {
        return this.f3703b == null ? this.c : this.f3703b;
    }

    public final void rebuildIfNeeded() {
        long timestampMillis = Game.getTimestampMillis();
        int screenWidth = (Game.i.uiManager.getScreenWidth() * 31) + Game.i.uiManager.getScreenHeight();
        final Array<Research> instances = Game.i.researchManager.getInstances();
        final Array<Research.ResearchLink> links = Game.i.researchManager.getLinks();
        for (int i = 0; i < instances.size; i++) {
            screenWidth = (screenWidth * 29) + instances.get(i).getInstalledLevel();
        }
        for (TrophyType trophyType : TrophyType.values) {
            if (Game.i.trophyManager.getConfig(trophyType).isReceived()) {
                screenWidth = (screenWidth * 29) + 1;
            }
        }
        if (this.e != screenWidth) {
            try {
                this.e = screenWidth;
                if (this.f3703b == null) {
                    this.c.clearChildren();
                    Table table = new Table();
                    this.c.add(table).expand().fill().padRight(340.0f);
                    final ResourcePack.AtlasTextureRegion textureRegion = Game.i.assetManager.getTextureRegion("small-circle");
                    final ResourcePack.AtlasTextureRegion textureRegion2 = Game.i.assetManager.getTextureRegion(AssetManager.BLANK_REGION_NAME);
                    final Research researchInstance = Game.i.researchManager.getResearchInstance(ResearchType.ROOT);
                    final float[] fArr = {10.0f};
                    table.add((Table) new Actor(this) { // from class: com.prineside.tdi2.ui.shared.MainMenuUiScene.4
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // com.prineside.tdi2.scene2d.Actor
                        public void draw(Batch batch, float f2) {
                            float f3;
                            float f4;
                            float[] fArr2 = fArr;
                            fArr2[0] = fArr2[0] + Gdx.graphics.getDeltaTime();
                            int mapMinX = Game.i.researchManager.getMapMinX();
                            int mapMinY = Game.i.researchManager.getMapMinY();
                            float min = StrictMath.min(640.0f / Game.i.researchManager.getMapWidth(), 640.0f / Game.i.researchManager.getMapHeight());
                            float x = getX();
                            float y = getY();
                            Array.ArrayIterator it = links.iterator();
                            while (it.hasNext()) {
                                Research.ResearchLink researchLink = (Research.ResearchLink) it.next();
                                Color color = Color.BLACK;
                                if (researchLink.parent.getInstalledLevel() > 0 && researchLink.child.getInstalledLevel() > 0) {
                                    color = MaterialColor.CYAN.P900;
                                }
                                MainMenuUiScene.f[0] = ((researchLink.parent.x - mapMinX) * min) + x;
                                MainMenuUiScene.f[1] = ((researchLink.parent.y - mapMinY) * min) + y;
                                MainMenuUiScene.f[2] = color.toFloatBits();
                                MainMenuUiScene.f[3] = ((researchLink.pivotX - mapMinX) * min) + x;
                                MainMenuUiScene.f[4] = ((researchLink.pivotY - mapMinY) * min) + y;
                                MainMenuUiScene.f[5] = MainMenuUiScene.f[2];
                                MainMenuUiScene.f[6] = ((researchLink.child.x - mapMinX) * min) + x;
                                MainMenuUiScene.f[7] = ((researchLink.child.y - mapMinY) * min) + y;
                                MainMenuUiScene.f[8] = MainMenuUiScene.f[2];
                                DrawUtils.texturedMultiLine((SpriteBatch) batch, 2.0f, textureRegion2, MainMenuUiScene.f);
                            }
                            int i2 = instances.size;
                            for (int i3 = 0; i3 < i2; i3++) {
                                float f5 = ((r0.x - mapMinX) * min) + x;
                                float f6 = ((r0.y - mapMinY) * min) + y;
                                if (((Research) instances.get(i3)).getInstalledLevel() > 0) {
                                    float squareDistanceBetweenPoints = 1.0f + ((((-fArr[0]) * 0.4f) + (PMath.getSquareDistanceBetweenPoints(r0.x, r0.y, researchInstance.x, researchInstance.y) * 1.0E-7f)) % 1.0f);
                                    if (squareDistanceBetweenPoints < 0.5f) {
                                        f3 = 0.0f;
                                    } else {
                                        f3 = (squareDistanceBetweenPoints - 0.5f) * 2.0f;
                                    }
                                    if (f3 < 0.85f) {
                                        f4 = f3 / 0.85f;
                                    } else {
                                        f4 = 1.0f - ((f3 - 0.85f) / 0.15f);
                                    }
                                    MainMenuUiScene.g.set(MaterialColor.CYAN.P900);
                                    MainMenuUiScene.g.lerp(MaterialColor.CYAN.P400, f4);
                                    batch.setColor(MainMenuUiScene.g);
                                    batch.draw(textureRegion, f5 - 4.0f, f6 - 4.0f, 8.0f, 8.0f);
                                } else {
                                    batch.setColor(Color.BLACK);
                                    batch.draw(textureRegion, f5 - 4.0f, f6 - 4.0f, 8.0f, 8.0f);
                                }
                            }
                        }
                    }).size(640.0f, 640.0f).row();
                    Table table2 = new Table();
                    int i2 = 0;
                    for (TrophyType trophyType2 : TrophyType.values) {
                        TrophyManager.TrophyConfig config = Game.i.trophyManager.getConfig(trophyType2);
                        Image image = new Image();
                        if (config.isReceived()) {
                            image.setDrawable(new TextureRegionDrawable(config.getIconTexture()));
                        } else {
                            image.setDrawable(new TextureRegionDrawable(config.getWhiteTexture()));
                            image.setColor(Color.BLACK);
                        }
                        table2.add((Table) image).size(48.0f).pad(4.0f);
                        i2++;
                        if (i2 % 12 == 0) {
                            table2.row();
                        }
                    }
                    table.add(table2).padTop(32.0f).padBottom(32.0f);
                } else {
                    int screenWidth2 = Game.i.uiManager.getScreenWidth();
                    int screenHeight = Game.i.uiManager.getScreenHeight();
                    if (this.f3703b.width != screenWidth2 || this.f3703b.height != screenHeight) {
                        this.f3703b.setSize(screenWidth2, screenHeight);
                    }
                    if (!this.d.get()) {
                        this.d.set(true);
                        Game.i.assetManager.getSceneModel(model -> {
                            Node child;
                            ModelInstance modelInstance = new ModelInstance(model);
                            Node node = modelInstance.getNode("researches");
                            if (node != null) {
                                for (int i3 = 0; i3 < links.size; i3++) {
                                    Research.ResearchLink researchLink = (Research.ResearchLink) links.get(i3);
                                    if ((researchLink.child.getInstalledLevel() == 0 || researchLink.parent.getInstalledLevel() == 0) && (child = node.getChild(researchLink.parent.getShortName() + "-" + researchLink.child.getShortName(), false, false)) != null) {
                                        child.detach();
                                    }
                                }
                                for (int i4 = 0; i4 < instances.size; i4++) {
                                    Research research = (Research) instances.get(i4);
                                    Node child2 = node.getChild(research.type.name(), false, false);
                                    if (research.getInstalledLevel() == 0) {
                                        if (child2 == null) {
                                            f3702a.e("3d scene node not exists for research " + research.type.name(), new Object[0]);
                                        } else {
                                            child2.detach();
                                        }
                                    }
                                }
                            }
                            Node node2 = modelInstance.getNode("trophies");
                            if (node2 != null) {
                                for (TrophyType trophyType3 : TrophyType.values) {
                                    if (!Game.i.trophyManager.getConfig(trophyType3).isReceived()) {
                                        node2.getChild("t-" + trophyType3.name(), false, false).detach();
                                        Node child3 = node2.getChild("t-" + trophyType3.name() + "-beam", false, false);
                                        if (child3 != null) {
                                            child3.detach();
                                        }
                                    }
                                }
                            }
                            Threads.i().runOnMainThread(() -> {
                                try {
                                    this.f3703b.setModel(modelInstance, 0.18f);
                                    this.f3703b.clearActions();
                                    this.f3703b.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.5f)));
                                } catch (Throwable th) {
                                    f3702a.e("failed to set model", th);
                                }
                                this.d.set(false);
                            });
                        });
                    } else {
                        f3702a.i("skipped - already rebuilding", new Object[0]);
                    }
                }
            } catch (Exception e) {
                this.e = screenWidth;
                f3702a.e("Failed to build 3d scene", e);
            }
        }
        f3702a.d("rebuildIfNeeded took " + (Game.getTimestampMillis() - timestampMillis) + "ms", new Object[0]);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
    }
}

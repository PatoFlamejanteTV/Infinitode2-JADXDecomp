package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ResearchManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.components.ResearchMenu;
import com.prineside.tdi2.ui.components.ResearchScreenInventory;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ProfileSummary;
import com.prineside.tdi2.ui.shared.ResourcesAndMoney;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/ResearchesScreen.class */
public class ResearchesScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2813a = TLog.forClass(ResearchesScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2814b;
    private final UiManager.UiLayer c;
    private final InputMultiplexer d;
    private OrthographicCamera e;
    public CameraController cameraController;
    private static final Color f;
    private static final Color g;
    private static final Color h;
    private static final Color i;
    private static final Color j;
    private static final Color k;
    private static final Color l;
    private static final Color m;
    private static final Color n;
    private static final Color o;
    private TextureRegion p;
    private TextureRegion q;
    private TextureRegion r;
    private TextureRegion s;
    private TextureRegion t;
    private TextureRegion u;
    private TextureRegion v;
    private TextureRegion w;
    private TextureRegion x;
    private TextureRegion y;
    private BitmapFont z;
    private BitmapFont A;
    private SideMenu B;
    private ResearchMenu C;
    private boolean D;
    private ResearchScreenInventory E;
    private Group F;
    private Label G;
    private ParticleEffect H;
    private ParticleEffectPool I;
    private DelayedRemovalArray<ParticleEffectPool.PooledEffect> J;
    private final DelayedRemovalArray<ItemStack> K;
    public Research hoveredResearch;
    public Research selectedResearch;
    private float L;
    private static final float[] M;
    private static final StringBuilder N;
    private PolygonSpriteBatch O;
    private final _ResearchManagerListener P;
    private final DelayedRemovalArray<ResearchesScreenListener> Q;
    private static final IntRectangle R;
    private static final Color S;
    private float T;

    static /* synthetic */ boolean a(ResearchesScreen researchesScreen, Research research, Vector2 vector2) {
        return a(research, vector2);
    }

    static /* synthetic */ boolean a(ResearchesScreen researchesScreen, boolean z) {
        researchesScreen.D = true;
        return true;
    }

    static {
        Color color = MaterialColor.LIGHT_GREEN.P500;
        f = MaterialColor.TEAL.P500;
        g = new Color(1.0f, 1.0f, 1.0f, 0.21f);
        h = MaterialColor.RED.P800;
        Color color2 = MaterialColor.LIGHT_GREEN.P500;
        i = MaterialColor.TEAL.P500;
        j = MaterialColor.AMBER.P500;
        k = MaterialColor.GREY.P700;
        l = MaterialColor.PURPLE.P300;
        m = MaterialColor.PURPLE.P600;
        n = new Color(1.0f, 1.0f, 1.0f, 0.14f);
        o = MaterialColor.LIGHT_BLUE.P500;
        M = new float[9];
        N = new StringBuilder();
        R = new IntRectangle();
        S = new Color();
    }

    private static void b() {
        Array<Research> instances = Game.i.researchManager.getInstances();
        boolean z = Game.i.progressManager.getDifficultyMode() != DifficultyMode.NORMAL;
        for (int i2 = 0; i2 < instances.size; i2++) {
            Research research = instances.items[i2];
            if (research.type == ResearchType.ROOT || research.unlocksTower) {
                research.setInstalledLevel(1);
            } else if (research.type.name().endsWith("MAX_EXP_LEVEL") || research.type.name().endsWith("MAX_UPGRADE_LEVEL") || research.type.name().startsWith("MINER_TYPE_") || research.type.name().startsWith("MODIFIER_TYPE_") || research.type.name().startsWith("STORYLINE_")) {
                research.setInstalledLevel(z ? research.maxEndlessLevel : research.levels.length);
            } else {
                research.setInstalledLevel(0);
            }
        }
    }

    private static void c() {
        Array<Research> instances = Game.i.researchManager.getInstances();
        boolean z = Game.i.progressManager.getDifficultyMode() != DifficultyMode.NORMAL;
        for (int i2 = 0; i2 < instances.size; i2++) {
            Research research = instances.items[i2];
            int length = (z ? research.maxEndlessLevel : research.levels.length) / 2;
            int i3 = length;
            if (length <= 0) {
                i3 = 1;
            }
            if (research.getInstalledLevel() < i3) {
                research.setInstalledLevel(i3);
            }
        }
    }

    private static void d() {
        Array<Research> instances = Game.i.researchManager.getInstances();
        boolean z = Game.i.progressManager.getDifficultyMode() != DifficultyMode.NORMAL;
        for (int i2 = 0; i2 < instances.size; i2++) {
            Research research = instances.items[i2];
            research.setInstalledLevel(z ? research.maxEndlessLevel : research.levels.length);
        }
    }

    public ResearchesScreen() {
        this(null);
    }

    public ResearchesScreen(ResearchType researchType) {
        this.f2814b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 104, "ResearchesScreen inventory");
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 106, "ResearchesScreen");
        this.d = new InputMultiplexer();
        this.D = true;
        this.J = new DelayedRemovalArray<>(false, 1, ParticleEffectPool.PooledEffect.class);
        this.K = new DelayedRemovalArray<>(true, 1, ItemStack.class);
        this.hoveredResearch = null;
        this.selectedResearch = null;
        this.L = 0.0f;
        this.P = new _ResearchManagerListener(this, (byte) 0);
        this.Q = new DelayedRemovalArray<>(false, 1);
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        ScreenTitle.i().setVisible(true).setText(Game.i.localeManager.i18n.get("researches")).setIcon(Game.i.assetManager.getDrawable("icon-research"));
        ResourcesAndMoney.i().setVisible(false);
        ProfileSummary.i().setVisible(false);
        BackButton.i().setClickHandler(() -> {
            Game.i.screenManager.goToMainMenu();
        }).setText(null).setVisible(true);
        InventoryOverlay.i().hideWithToggleButton(true);
        Game.i.researchManager.updateAndValidateStarBranch();
        this.p = Game.i.assetManager.getTextureRegion("global-upgrades-icon-background");
        this.q = Game.i.assetManager.getTextureRegion("global-upgrades-icon-background-small");
        this.r = Game.i.assetManager.getTextureRegion("global-upgrades-icon-background-invisible");
        this.s = Game.i.assetManager.getTextureRegion("icon-infinitode-1-logo");
        this.t = Game.i.assetManager.getTextureRegion("global-upgrades-icon-selection");
        this.u = Game.i.assetManager.getTextureRegion("global-upgrades-icon-selection-small");
        this.v = Game.i.assetManager.getTextureRegion(AssetManager.BLANK_REGION_NAME);
        this.w = Game.i.assetManager.getTextureRegion("particle-triangle");
        this.y = Game.i.assetManager.getTextureRegion("icon-star");
        this.x = Game.i.assetManager.getTextureRegion("global-upgrades-icon-level-overlay");
        this.z = Game.i.assetManager.getFont(21);
        this.A = Game.i.assetManager.getFont(24);
        this.e = new OrthographicCamera(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
        this.cameraController = new CameraController(this.e, Game.i.researchManager.getMapWidth(), Game.i.researchManager.getMapHeight());
        this.cameraController.setScreenSize(Game.i.uiManager.getScreenWidth(), Game.i.uiManager.getScreenHeight());
        this.cameraController.setZoomBoundaries(2.0f, 1.75f);
        this.cameraController.zoom = 4.0d;
        this.cameraController.lookAt(Game.i.researchManager.getMapWidth() / 2, Game.i.researchManager.getMapHeight() / 2);
        this.e.update();
        this.d.addProcessor(Game.i.uiManager.stage);
        this.d.addProcessor(this.cameraController.getInputProcessor());
        this.d.addProcessor(new InputAdapter() { // from class: com.prineside.tdi2.screens.ResearchesScreen.1

            /* renamed from: a, reason: collision with root package name */
            private final Vector2 f2815a = new Vector2();

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean mouseMoved(int i2, int i3) {
                this.f2815a.set(i2, i3);
                ResearchesScreen.this.cameraController.screenToMap(this.f2815a);
                this.f2815a.x += Game.i.researchManager.getMapMinX();
                this.f2815a.y += Game.i.researchManager.getMapMinY();
                Array.ArrayIterator<Research> it = Game.i.researchManager.getInstances().iterator();
                while (it.hasNext()) {
                    Research next = it.next();
                    if (ResearchesScreen.a(ResearchesScreen.this, next, this.f2815a) && Game.i.researchManager.isVisible(next)) {
                        ResearchesScreen.this.a(next);
                        return false;
                    }
                }
                ResearchesScreen.this.a((Research) null);
                return false;
            }

            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchUp(int i2, int i3, int i4, int i5) {
                this.f2815a.set(i2, i3);
                ResearchesScreen.this.cameraController.screenToMap(this.f2815a);
                this.f2815a.x += Game.i.researchManager.getMapMinX();
                this.f2815a.y += Game.i.researchManager.getMapMinY();
                Array.ArrayIterator<Research> it = Game.i.researchManager.getInstances().iterator();
                while (it.hasNext()) {
                    Research next = it.next();
                    if (ResearchesScreen.a(ResearchesScreen.this, next, this.f2815a) && Game.i.researchManager.isVisible(next)) {
                        ResearchesScreen.this.b(next);
                        return false;
                    }
                }
                ResearchesScreen.this.b((Research) null);
                return false;
            }
        });
        this.B = new SideMenu(600.0f);
        this.B.sideShadow.setVisible(false);
        Image image = new Image(Game.i.assetManager.getDrawable("ui-research-menu-top"));
        image.setSize(600.0f, 15.0f);
        image.setColor(new Color(724249599));
        image.setPosition(0.0f, 978.0f);
        image.setTouchable(Touchable.disabled);
        this.B.getBackgroundContainer().addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setSize(600.0f, 978.0f);
        image2.setColor(new Color(724249599));
        image2.setTouchable(Touchable.disabled);
        this.B.getBackgroundContainer().addActor(image2);
        this.C = new ResearchMenu(this.B, this);
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.enabled);
        group.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.ResearchesScreen.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("research_tip_stars"));
            }
        });
        this.c.getTable().add((Table) group).size(200.0f, 32.0f).expand().top().left().padTop(109.0f).padLeft(136.0f);
        if (Config.isModdingMode()) {
            PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-restart"), () -> {
                Game.i.researchManager.reloadNew();
            }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
            paddedImageButton.setPosition(0.0f, -64.0f);
            paddedImageButton.setSize(64.0f, 64.0f);
            paddedImageButton.setIconSize(48.0f, 48.0f);
            paddedImageButton.setIconPosition(8.0f, 8.0f);
            group.addActor(paddedImageButton);
            PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-density-low"), () -> {
                b();
                Game.i.researchManager.checkResearchesStatus(false);
                Notifications.i().add("Minimal research enabled", null, null, StaticSoundType.SUCCESS);
            }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
            paddedImageButton2.setPosition(64.0f, -64.0f);
            paddedImageButton2.setSize(64.0f, 64.0f);
            paddedImageButton2.setIconSize(48.0f, 48.0f);
            paddedImageButton2.setIconPosition(8.0f, 8.0f);
            group.addActor(paddedImageButton2);
            PaddedImageButton paddedImageButton3 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-density-medium"), () -> {
                b();
                c();
                Game.i.researchManager.checkResearchesStatus(false);
                Notifications.i().add("Average research enabled", null, null, StaticSoundType.SUCCESS);
            }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
            paddedImageButton3.setPosition(128.0f, -64.0f);
            paddedImageButton3.setSize(64.0f, 64.0f);
            paddedImageButton3.setIconSize(48.0f, 48.0f);
            paddedImageButton3.setIconPosition(8.0f, 8.0f);
            group.addActor(paddedImageButton3);
            PaddedImageButton paddedImageButton4 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-density-high"), () -> {
                d();
                Game.i.researchManager.checkResearchesStatus(false);
                Notifications.i().add("Full research enabled", null, null, StaticSoundType.SUCCESS);
            }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
            paddedImageButton4.setPosition(192.0f, -64.0f);
            paddedImageButton4.setSize(64.0f, 64.0f);
            paddedImageButton4.setIconSize(48.0f, 48.0f);
            paddedImageButton4.setIconPosition(8.0f, 8.0f);
            group.addActor(paddedImageButton4);
            PaddedImageButton paddedImageButton5 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-backpack-arrow-down"), () -> {
                String json = GameValueManager.createSnapshot(null, Game.i.progressManager.getDifficultyMode(), false, Game.i.basicLevelManager.getLevel("1.1"), false, false, Game.i.progressManager.createProgressSnapshotForState()).toJson();
                f2813a.i(json, new Object[0]);
                Gdx.app.getClipboard().setContents(json);
                Notifications.i().add("Game value snapshot saved to clipboard", null, null, StaticSoundType.SUCCESS);
            }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700);
            paddedImageButton5.setPosition(256.0f, -64.0f);
            paddedImageButton5.setSize(64.0f, 64.0f);
            paddedImageButton5.setIconSize(48.0f, 48.0f);
            paddedImageButton5.setIconPosition(8.0f, 8.0f);
            group.addActor(paddedImageButton5);
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-star"));
        image3.setPosition(0.0f, 0.0f);
        image3.setSize(32.0f, 32.0f);
        image3.setColor(MaterialColor.AMBER.P500);
        group.addActor(image3);
        this.G = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.G.setPosition(42.0f, 0.0f);
        this.G.setSize(100.0f, 32.0f);
        this.G.setColor(MaterialColor.AMBER.P500);
        group.addActor(this.G);
        updateStarsCount();
        this.F = new Group();
        this.F.setTransform(false);
        this.F.setSize(292.0f, 117.0f);
        this.F.setTouchable(Touchable.childrenOnly);
        Image image4 = new Image(Game.i.assetManager.getQuad("ui.researchScreenInventory.toggleButton"));
        image4.setSize(292.0f, 117.0f);
        this.F.addActor(image4);
        final Image image5 = new Image(Game.i.assetManager.getDrawable("icon-backpack"));
        image5.setSize(64.0f, 64.0f);
        image5.setPosition(123.0f, 27.0f);
        this.F.addActor(image5);
        Actor actor = new Actor();
        actor.setTouchable(Touchable.enabled);
        actor.setSize(128.0f, 117.0f);
        actor.setPosition(92.0f, 0.0f);
        actor.addListener(new InputListener() { // from class: com.prineside.tdi2.screens.ResearchesScreen.3

            /* renamed from: a, reason: collision with root package name */
            private boolean f2817a;

            private void a() {
                if (this.f2817a) {
                    image5.setColor(MaterialColor.LIGHT_BLUE.P300);
                } else {
                    image5.setColor(Color.WHITE);
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f2, float f3, int i2, @Null Actor actor2) {
                this.f2817a = true;
                a();
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i2, @Null Actor actor2) {
                this.f2817a = false;
                a();
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                ResearchesScreen.a(ResearchesScreen.this, true);
                return true;
            }
        });
        this.F.addActor(actor);
        this.f2814b.getTable().addActor(this.F);
        this.E = new ResearchScreenInventory();
        this.f2814b.getTable().addActor(this.E);
        PaddedImageButton paddedImageButton6 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right-hollow"), () -> {
            this.D = false;
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500);
        paddedImageButton6.setIconPosition(79.0f, 25.0f);
        paddedImageButton6.setIconSize(48.0f, 48.0f);
        paddedImageButton6.setSize(205.0f, 96.0f);
        this.E.addActor(paddedImageButton6);
        this.H = new ParticleEffect();
        this.H.load(Gdx.files.internal("particles/research.prt"), Game.i.assetManager.getTextureRegion("icon-research").getAtlas());
        this.H.setEmittersCleanUpBlendFunction(false);
        this.I = Game.i.assetManager.getParticleEffectPool("research-completed.prt");
        Game.i.researchManager.addListener(this.P);
        if (researchType != null) {
            Research researchInstance = Game.i.researchManager.getResearchInstance(researchType);
            this.cameraController.setZoom(this.cameraController.getMinZoom());
            this.cameraController.lookAt(researchInstance.x - Game.i.researchManager.getMapMinX(), researchInstance.y - Game.i.researchManager.getMapMinY());
            b(researchInstance);
        }
        this.O = new PolygonSpriteBatch();
    }

    public void updateStarsCount() {
        Game.i.researchManager.updateAndValidateStarBranch();
        this.G.setTextFromInt(Game.i.researchManager.getUnusedStarsCount());
    }

    public void addListener(ResearchesScreenListener researchesScreenListener) {
        if (researchesScreenListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.Q.contains(researchesScreenListener, true)) {
            this.Q.add(researchesScreenListener);
        }
    }

    public void removeListener(ResearchesScreenListener researchesScreenListener) {
        if (researchesScreenListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.Q.removeValue(researchesScreenListener, true);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        Gdx.input.setInputProcessor(this.d);
        Game.i.uiManager.stage.setScrollFocus(null);
    }

    private static boolean a(Research research, Vector2 vector2) {
        float f2 = (research.small ? 64 : 110) * 0.5f;
        return vector2.x > ((float) research.x) - f2 && vector2.x < ((float) research.x) + f2 && vector2.y > ((float) research.y) - f2 && vector2.y < ((float) research.y) + f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Research research) {
        if (research == this.hoveredResearch) {
            return;
        }
        this.hoveredResearch = research;
        this.Q.begin();
        int i2 = this.Q.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.Q.get(i3).hoveredResearchChanged();
        }
        this.Q.end();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Research research) {
        if (research == this.selectedResearch) {
            return;
        }
        this.selectedResearch = research;
        this.Q.begin();
        int i2 = this.Q.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.Q.get(i3).selectedResearchChanged();
        }
        this.Q.end();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.prineside.tdi2.Threads] */
    public void startSelectedResearch() {
        if (Game.i.researchManager.getCurrentResearching() == this.selectedResearch) {
            if (!Game.i.progressManager.removeAccelerators(Game.i.progressManager.countAcceleratorsNeeded((int) (Game.i.researchManager.getMillisToResearchingEnd() / 1000)))) {
                Threads.i().postRunnable(() -> {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_accelerators"));
                });
                return;
            } else {
                Game.i.researchManager.finishCurrentResearch();
                return;
            }
        }
        if (this.selectedResearch.isMaxNormalLevel() && this.selectedResearch.type == ResearchType.DEVELOPER_MODE) {
            Threads.i().postRunnable(() -> {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("disable_developer_mode_hint"));
            });
            return;
        }
        if (this.selectedResearch.isMaxEndlessLevel()) {
            return;
        }
        Runnable runnable = () -> {
            ?? r0;
            try {
                if (this.selectedResearch != null) {
                    Game.i.researchManager.tryStartResearching(this.selectedResearch, false, null);
                    if (this.selectedResearch.type == ResearchType.DEVELOPER_MODE) {
                        Threads.i().postRunnable(() -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("enable_developer_mode_confirm"), () -> {
                                ?? startResearching;
                                try {
                                    Game.i.researchManager.tryStartResearching(this.selectedResearch, false, null);
                                    startResearching = Game.i.researchManager.startResearching(this.selectedResearch, true);
                                } catch (ResearchManager.StartResearchingException e) {
                                    startResearching.printStackTrace();
                                }
                            });
                            Table table = new Table();
                            Dialog.i().getHintTable().add(table).grow();
                            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            image.setColor(0.0f, 0.0f, 0.0f, 0.78f);
                            image.setFillParent(true);
                            table.addActor(image);
                            Label label = new Label(Game.i.localeManager.i18n.get("enable_developer_mode_disclaimer"), Game.i.assetManager.getLabelStyle(30));
                            label.setWrap(true);
                            table.add((Table) label).pad(20.0f).grow();
                        });
                    } else {
                        Game.i.researchManager.startResearching(this.selectedResearch, true);
                    }
                }
                r0 = this.C;
                r0.update();
            } catch (ResearchManager.StartResearchingException e) {
                a((ResearchManager.StartResearchingException) r0);
            }
        };
        ResearchManager.StartResearchingException savedGameExists = GameStateSystem.savedGameExists();
        if (savedGameExists != 0) {
            try {
                if (this.selectedResearch != null) {
                    Game.i.researchManager.tryStartResearching(this.selectedResearch, false, null);
                    savedGameExists = Threads.i();
                    savedGameExists.postRunnable(() -> {
                        Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                            GameStateSystem.deleteSavedGame();
                            runnable.run();
                        });
                    });
                    return;
                }
                return;
            } catch (ResearchManager.StartResearchingException e) {
                a(savedGameExists);
                return;
            }
        }
        runnable.run();
    }

    private static void a(ResearchManager.StartResearchingException startResearchingException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Game.i.localeManager.i18n.get("cant_start_research")).append(":\n");
        for (int i2 = 0; i2 < startResearchingException.reasons.size && i2 != 2; i2++) {
            stringBuilder.append(Game.i.localeManager.i18n.get("start_research_fail_reason_" + startResearchingException.reasons.get(i2).name())).append(SequenceUtils.EOL);
        }
        Threads.i().postRunnable(() -> {
            Dialog.i().showAlert(stringBuilder);
        });
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f2) {
        Color color;
        boolean isMaxNormalLevel;
        float f3;
        Color color2;
        float f4;
        float f5;
        float angleBetweenPoints;
        Color color3 = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color3.r, color3.g, color3.f888b, color3.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
            return;
        }
        if (Gdx.input.isKeyJustPressed(66) && this.selectedResearch != null && !Dialog.i().isVisible()) {
            startSelectedResearch();
            this.T = 0.5f;
        }
        if (Gdx.input.isKeyPressed(66)) {
            this.T -= f2;
            if (this.T <= 0.0f) {
                if (this.selectedResearch != null && !Dialog.i().isVisible()) {
                    startSelectedResearch();
                    this.T = 0.05f;
                } else {
                    this.T = 1000.0f;
                }
            }
        }
        if (this.D) {
            this.E.preRender(f2);
            if (this.B.isOffscreen() || !this.B.isVisible()) {
                this.E.setPosition(this.c.getTable().getWidth() - 227.0f, 204.0f);
            } else {
                this.E.setPosition(((this.c.getTable().getWidth() - 227.0f) - 600.0f) + this.B.getWrapper().getX(), 204.0f);
            }
            this.F.setVisible(false);
            this.E.setVisible(true);
        } else {
            this.F.setVisible(true);
            this.E.setVisible(false);
            if (this.B.isOffscreen() || !this.B.isVisible()) {
                this.F.setPosition(this.c.getTable().getWidth() - 227.0f, 204.0f);
            } else {
                this.F.setPosition(((this.c.getTable().getWidth() - 227.0f) - 600.0f) + this.B.getWrapper().getX(), 204.0f);
            }
        }
        SpriteBatch spriteBatch = Game.i.renderingManager.batch;
        boolean z = this.cameraController.zoom < 2.0d;
        this.e.update();
        this.cameraController.realUpdate(f2);
        int mapMinX = Game.i.researchManager.getMapMinX();
        int mapMinY = Game.i.researchManager.getMapMinY();
        this.O.begin();
        this.O.setProjectionMatrix(this.e.combined);
        this.O.enableBlending();
        Array<ResearchManager.PolygonConfig> polygonSprites = Game.i.researchManager.getPolygonSprites();
        Array<Research.ResearchLink> links = Game.i.researchManager.getLinks();
        Array<Research> instances = Game.i.researchManager.getInstances();
        for (int i2 = 0; i2 < polygonSprites.size; i2++) {
            if (polygonSprites.items[i2].visibleWith == null || polygonSprites.items[i2].visibleWith.getInstalledLevel() > 0) {
                polygonSprites.items[i2].sprite.draw(this.O);
            }
        }
        this.O.end();
        spriteBatch.setProjectionMatrix(this.e.combined);
        spriteBatch.begin();
        spriteBatch.setBlendFunction(770, 771);
        Color color4 = f;
        Color color5 = i;
        this.L = (this.L + (f2 * 0.25f)) % 1.0f;
        Array.ArrayIterator<Research.ResearchLink> it = links.iterator();
        while (it.hasNext()) {
            Research.ResearchLink next = it.next();
            if (Game.i.researchManager.isVisible(next.parent) && Game.i.researchManager.isVisible(next.child)) {
                R.minX = StrictMath.min(next.child.x - mapMinX, next.parent.x - mapMinX);
                R.minY = StrictMath.min(next.child.y - mapMinY, next.parent.y - mapMinY);
                R.maxX = StrictMath.max(next.child.x - mapMinX, next.parent.x - mapMinX);
                R.maxY = StrictMath.max(next.child.y - mapMinY, next.parent.y - mapMinY);
                if (this.cameraController.isIntRectVisible(R)) {
                    if ((next.parent.priceInStars > 0 && next.child.getInstalledLevel() > 0) || (next.child.priceInStars > 0 && next.parent.getInstalledLevel() > 0)) {
                        color2 = color4;
                    } else if (next.isVisible()) {
                        color2 = color4;
                    } else if (next.parent.getInstalledLevel() != 0) {
                        color2 = h;
                    } else {
                        color2 = g;
                    }
                    M[0] = next.parent.x - mapMinX;
                    M[1] = next.parent.y - mapMinY;
                    M[2] = color2.toFloatBits();
                    M[3] = next.pivotX - mapMinX;
                    M[4] = next.pivotY - mapMinY;
                    M[5] = M[2];
                    M[6] = next.child.x - mapMinX;
                    M[7] = next.child.y - mapMinY;
                    M[8] = M[2];
                    DrawUtils.texturedMultiLine(spriteBatch, 6.0f, this.v, M);
                    if (z && next.child.priceInStars == 0) {
                        for (int i3 = 0; i3 < 3; i3++) {
                            float f6 = (this.L + (0.333f * i3)) % 1.0f;
                            if (M[0] == M[3] && M[1] == M[4]) {
                                f4 = ((M[6] - M[3]) * f6) + M[3];
                                f5 = ((M[7] - M[4]) * f6) + M[4];
                                angleBetweenPoints = PMath.getAngleBetweenPoints(M[3], M[4], M[6], M[7]);
                            } else if (M[6] == M[3] && M[7] == M[4]) {
                                f4 = ((M[3] - M[0]) * f6) + M[0];
                                f5 = ((M[4] - M[1]) * f6) + M[1];
                                angleBetweenPoints = PMath.getAngleBetweenPoints(M[0], M[1], M[3], M[4]);
                            } else if (f6 < 0.5f) {
                                f4 = ((M[3] - M[0]) * f6 * 2.0f) + M[0];
                                f5 = ((M[4] - M[1]) * f6 * 2.0f) + M[1];
                                angleBetweenPoints = PMath.getAngleBetweenPoints(M[0], M[1], M[3], M[4]);
                            } else {
                                f4 = ((M[6] - M[3]) * (f6 - 0.5f) * 2.0f) + M[3];
                                f5 = ((M[7] - M[4]) * (f6 - 0.5f) * 2.0f) + M[4];
                                angleBetweenPoints = PMath.getAngleBetweenPoints(M[3], M[4], M[6], M[7]);
                            }
                            float f7 = angleBetweenPoints;
                            Vector2 vector2 = new Vector2();
                            PMath.getPointByAngleFromPoint(f4, f5, f7 - 180.0f, 2.0f, vector2);
                            spriteBatch.setColor(Config.BACKGROUND_COLOR);
                            spriteBatch.draw(this.w, vector2.x - 8.0f, vector2.y - 8.0f, 8.0f, 8.0f, 16.0f, 16.0f, 1.0f, 1.0f, f7);
                            PMath.getPointByAngleFromPoint(f4, f5, f7, 3.0f, vector2);
                            spriteBatch.draw(this.w, vector2.x - 8.0f, vector2.y - 8.0f, 8.0f, 8.0f, 16.0f, 16.0f, 1.0f, 1.0f, f7);
                            spriteBatch.setColor(color2);
                            spriteBatch.draw(this.w, f4 - 8.0f, f5 - 8.0f, 8.0f, 8.0f, 16.0f, 16.0f, 1.0f, 1.0f, f7);
                        }
                        spriteBatch.setColor(Color.WHITE);
                    }
                    if (z && !next.isVisible() && next.requiredLevels > 1) {
                        float f8 = (next.requiredLevelsLabelX - 130.0f) - mapMinX;
                        float f9 = (next.requiredLevelsLabelY - 20.0f) - mapMinY;
                        N.setLength(0);
                        N.append(next.parent.getInstalledLevel());
                        N.append("/");
                        N.append(next.requiredLevels);
                        this.z.setColor(Color.BLACK);
                        this.z.draw(spriteBatch, N, f8 + 2.0f, (f9 + 28.0f) - 2.0f, 260.0f, 1, false);
                        this.z.setColor(Color.WHITE);
                        this.z.draw(spriteBatch, N, f8, f9 + 28.0f, 260.0f, 1, false);
                    }
                }
            }
        }
        int i4 = instances.size;
        for (int i5 = 0; i5 < i4; i5++) {
            Research research = instances.get(i5);
            float f10 = research.x - mapMinX;
            float f11 = research.y - mapMinY;
            int i6 = research.small ? 64 : 110;
            float f12 = f10 - (i6 * 0.5f);
            float f13 = f11 - (i6 * 0.5f);
            R.minX = (int) f12;
            R.minY = (int) f13;
            IntRectangle intRectangle = R;
            intRectangle.maxX = intRectangle.minX + i6;
            IntRectangle intRectangle2 = R;
            intRectangle2.maxY = intRectangle2.minY + i6;
            if (this.cameraController.isIntRectVisible(R)) {
                float f14 = research.small ? 80.0f : 120.0f;
                TextureRegion textureRegion = research.small ? this.u : this.t;
                if (this.selectedResearch != research) {
                    if (this.hoveredResearch == research) {
                        spriteBatch.setColor(n);
                        spriteBatch.draw(textureRegion, f10 - (f14 * 0.5f), f11 - (f14 * 0.5f), f14, f14);
                    }
                } else {
                    spriteBatch.setColor(o);
                    spriteBatch.draw(textureRegion, f10 - (f14 * 0.5f), f11 - (f14 * 0.5f), f14, f14);
                }
                if (Game.i.researchManager.isVisible(research)) {
                    boolean canStartResearching = Game.i.researchManager.canStartResearching(research, false);
                    if (canStartResearching) {
                        float timestampMillis = ((float) (Game.getTimestampMillis() % 1000)) * 0.001f;
                        if (timestampMillis < 0.5f) {
                            f3 = timestampMillis * 2.0f;
                        } else {
                            f3 = 1.0f - ((timestampMillis - 0.5f) * 2.0f);
                        }
                        S.set(l).lerp(m, f3);
                        color = S;
                    } else if (research.getInstalledLevel() == 0) {
                        color = k;
                    } else if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.EASY || Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL) {
                        if (research.isMaxNormalLevel()) {
                            color = j;
                        } else {
                            color = color5;
                        }
                    } else if (research.isMaxEndlessLevel()) {
                        color = j;
                    } else {
                        color = color5;
                    }
                    spriteBatch.setColor(color);
                    spriteBatch.draw(research.small ? this.q : this.p, f12, f13, i6, i6);
                    if (research.getInstalledLevel() == 0) {
                        spriteBatch.setColor(k);
                    } else {
                        spriteBatch.setColor(Color.WHITE);
                    }
                    float f15 = research.small ? 32.0f : 80.0f;
                    research.category.getIcon().draw(spriteBatch, f10 - (f15 * 0.5f), f11 - (f15 * 0.5f), f15, f15);
                    if (z) {
                        int length = research.levels.length;
                        if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                            length = research.maxEndlessLevel;
                        }
                        if (length > 1 && research.getInstalledLevel() != 0) {
                            spriteBatch.setColor(color);
                            spriteBatch.draw(this.x, f12 + 44.0f, f13 - 13.0f, 61.0f, 33.0f);
                            N.setLength(0);
                            N.append('L');
                            if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                                N.append(research.getInstalledLevel());
                            } else if (research.getInstalledLevel() > research.levels.length) {
                                N.append(research.levels.length);
                            } else {
                                N.append(research.getInstalledLevel());
                            }
                            if (canStartResearching) {
                                this.z.setColor(Color.BLACK);
                                this.z.draw(spriteBatch, N, f12 + 48.0f + 2.0f + 2.0f, ((f13 + 32.0f) - 2.0f) - 20.0f, 48.0f, 16, false);
                                this.z.setColor(Color.WHITE);
                                this.z.draw(spriteBatch, N, f12 + 48.0f + 2.0f, (f13 + 32.0f) - 20.0f, 48.0f, 16, false);
                            } else {
                                this.z.setColor(Color.BLACK);
                                this.z.draw(spriteBatch, N, f12 + 48.0f + 2.0f, (f13 + 32.0f) - 20.0f, 48.0f, 16, false);
                                this.z.setColor(Color.WHITE);
                            }
                        }
                    }
                    if (z && research.priceInStars > 0) {
                        N.setLength(0);
                        N.append(research.priceInStars);
                        float f16 = f10 + (i6 * 0.35f);
                        float f17 = f11 + (i6 * 0.1f);
                        spriteBatch.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                        spriteBatch.draw(this.y, f16 - 5.0f, f17 - 5.0f, 34.0f, 34.0f);
                        spriteBatch.setColor(MaterialColor.AMBER.P400);
                        spriteBatch.draw(this.y, f16, f17, 24.0f, 24.0f);
                        this.A.setColor(MaterialColor.AMBER.P400);
                        this.A.draw(spriteBatch, N, f16 + 32.0f, f17 + 21.0f, 100.0f, 8, false);
                    }
                    spriteBatch.setColor(Color.WHITE);
                    if (this.cameraController.zoom <= 1.5d && this.selectedResearch != research) {
                        if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                            isMaxNormalLevel = research.isMaxEndlessLevel();
                        } else {
                            isMaxNormalLevel = research.isMaxNormalLevel();
                        }
                        if (!isMaxNormalLevel) {
                            this.K.clear();
                            if (research.getInstalledLevel() >= research.levels.length) {
                                this.K.addAll(research.endlessLevel.getPrice(research.getInstalledLevel() + 1));
                            } else {
                                this.K.addAll(research.levels[research.getInstalledLevel()].price);
                            }
                            this.K.begin();
                            for (int i7 = 0; i7 < this.K.size; i7++) {
                                ItemStack itemStack = this.K.items[i7];
                                if (itemStack.getCount() <= Game.i.progressManager.getItemsCount(itemStack.getItem()) || itemStack.getItem().getIconDrawable() == null) {
                                    this.K.removeIndex(i7);
                                }
                            }
                            this.K.end();
                            if (this.K.size != 0) {
                                float f18 = (this.K.size * 20.0f) + ((this.K.size - 1) * 4.0f);
                                float f19 = f12 - 20.0f;
                                float f20 = ((f13 + i6) - 10.0f) - 20.0f;
                                spriteBatch.setColor(color);
                                spriteBatch.getColor().f889a = 0.14f;
                                spriteBatch.setColor(spriteBatch.getColor());
                                spriteBatch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), f19 - 4.0f, ((f20 - f18) + 20.0f) - 5.0f, 30.0f, f18 + 10.0f);
                                spriteBatch.setColor(Color.WHITE);
                                for (int i8 = 0; i8 < this.K.size && i8 != 4; i8++) {
                                    ItemStack itemStack2 = this.K.get(i8);
                                    float f21 = f20 - (i8 * 24.0f);
                                    spriteBatch.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                                    itemStack2.getItem().getIconDrawable().draw(spriteBatch, f19 + 2.0f, f21 - 2.0f, 20.0f, 20.0f);
                                    spriteBatch.setColor(Color.WHITE);
                                    itemStack2.getItem().getIconDrawable().draw(spriteBatch, f19, f21, 20.0f, 20.0f);
                                }
                            }
                        }
                    }
                } else {
                    spriteBatch.setColor(1.0f, 1.0f, 1.0f, 0.07f);
                    spriteBatch.draw(this.r, f12, f13, i6, i6);
                    if (research.endlessOnly) {
                        if (!research.small) {
                            spriteBatch.draw(this.s, f10 - 28.0f, (f11 - 28.0f) + 12.0f, 56.0f, 56.0f);
                            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(18);
                            font.setColor(1.0f, 1.0f, 1.0f, 0.07f);
                            font.draw(spriteBatch, Game.i.localeManager.i18n.get("difficulty_mode_ENDLESS_I"), f10, f11 - 10.0f, 1.0f, 1, false);
                            font.setColor(Color.WHITE);
                        } else {
                            spriteBatch.draw(this.s, f10 - 20.0f, f11 - 20.0f, 40.0f, 40.0f);
                        }
                    }
                }
            }
        }
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.end();
        if (Game.i.researchManager.getCurrentResearching() != null) {
            spriteBatch.begin();
            this.H.setPosition(r0.x - mapMinX, r0.y - mapMinY);
            this.H.draw(spriteBatch, f2);
            spriteBatch.end();
        }
        if (this.J.size != 0) {
            spriteBatch.begin();
            spriteBatch.setBlendFunction(770, 1);
            this.J.begin();
            for (int i9 = 0; i9 < this.J.size; i9++) {
                ParticleEffectPool.PooledEffect pooledEffect = this.J.items[i9];
                pooledEffect.draw(spriteBatch, f2);
                if (pooledEffect.isComplete()) {
                    this.J.removeIndex(i9);
                }
            }
            this.J.end();
            spriteBatch.end();
        }
        spriteBatch.setColor(Color.WHITE);
        spriteBatch.setBlendFunction(770, 771);
        this.C.draw(f2);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i2, int i3) {
        if (i2 > 0 && i3 > 0) {
            this.cameraController.setScreenSize(i2, i3);
        }
        super.resize(i2, i3);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.researchManager.removeListener(this.P);
        this.B.dispose();
        this.O.dispose();
        this.C.dispose();
        this.E.dispose();
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.f2814b);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/ResearchesScreen$_ResearchManagerListener.class */
    private class _ResearchManagerListener extends ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter {
        private _ResearchManagerListener() {
        }

        /* synthetic */ _ResearchManagerListener(ResearchesScreen researchesScreen, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchStarted(Research research, long j) {
            ResearchesScreen.f2813a.i("research started: " + research.type.name() + ", ends in " + j + "ms", new Object[0]);
            ResearchesScreen.this.updateStarsCount();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchCompleted(Research research) {
            ResearchesScreen.this.updateStarsCount();
            int mapMinX = Game.i.researchManager.getMapMinX();
            int mapMinY = Game.i.researchManager.getMapMinY();
            float f = research.x - mapMinX;
            float f2 = research.y - mapMinY;
            ParticleEffectPool.PooledEffect obtain = ResearchesScreen.this.I.obtain();
            obtain.setPosition(f, f2);
            obtain.start();
            ResearchesScreen.this.J.add(obtain);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/ResearchesScreen$ResearchesScreenListener.class */
    public interface ResearchesScreenListener {
        void selectedResearchChanged();

        void hoveredResearchChanged();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/ResearchesScreen$ResearchesScreenListener$ResearchesScreenListenerAdapter.class */
        public static abstract class ResearchesScreenListenerAdapter implements ResearchesScreenListener {
            @Override // com.prineside.tdi2.screens.ResearchesScreen.ResearchesScreenListener
            public void selectedResearchChanged() {
            }

            @Override // com.prineside.tdi2.screens.ResearchesScreen.ResearchesScreenListener
            public void hoveredResearchChanged() {
            }
        }
    }
}

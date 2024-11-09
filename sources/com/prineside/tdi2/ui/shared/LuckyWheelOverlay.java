package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.TrailMultilineActor;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LuckyWheelOverlay.class */
public final class LuckyWheelOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3694a = TLog.forClass(LuckyWheelOverlay.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Interpolation f3695b = Interpolation.sine;
    private static final Color c;
    private static final Color d;
    private static final Color e;
    private static final Color f;
    private Color[] i;
    private Color[] j;
    private Color[] k;
    private Color[] l;
    private final Group m;
    private final Group n;
    private final ParticlesCanvas o;
    private final Group p;
    private final Group q;
    private final PieChartActor r;
    private final PieChartActor s;
    private final Group t;
    private final Image u;
    private final Table v;
    private final Label w;
    private boolean y;
    private float A;
    private boolean B;
    private boolean C;
    private float D;
    private float E;
    private float F;
    private float G;
    private float H;
    private float I;
    private boolean J;
    private boolean K;
    private float L;
    private final Group N;
    private final ParticleEffectPool R;
    private static final Vector2 S;
    private float T;
    private boolean U;
    private final UiManager.UiLayer g = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 90, "LuckyWheelOverlay tint");
    private final UiManager.UiLayer h = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 91, "LuckyWheelOverlay main");
    private int x = -1;
    private final Array<WheelOptionConfig> z = new Array<>(WheelOptionConfig.class);
    private float M = 0.0f;
    private final TrailMultilineActor[] O = new TrailMultilineActor[5];
    private final Image[] P = new Image[5];
    private float Q = 0.0f;

    static /* synthetic */ boolean a(LuckyWheelOverlay luckyWheelOverlay, boolean z) {
        luckyWheelOverlay.B = false;
        return false;
    }

    static {
        Color color = MaterialColor.YELLOW.P600;
        c = color;
        d = color.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f);
        Color color2 = MaterialColor.YELLOW.P300;
        e = color2;
        f = color2.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f);
        S = new Vector2();
    }

    public static LuckyWheelOverlay i() {
        return (LuckyWheelOverlay) Game.i.uiManager.getComponent(LuckyWheelOverlay.class);
    }

    public LuckyWheelOverlay() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.g.getTable().add((Table) image).expand().fill();
        this.g.getTable().setTouchable(Touchable.enabled);
        this.g.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.LuckyWheelOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                if (!LuckyWheelOverlay.this.B && !LuckyWheelOverlay.this.J) {
                    LuckyWheelOverlay.this.setVisible(false);
                }
            }
        });
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(240.0f, 240.0f);
        this.h.getTable().add((Table) group).size(480.0f, 480.0f);
        this.m = new Group();
        this.m.setTransform(true);
        this.m.setOrigin(240.0f, 240.0f);
        this.m.setSize(480.0f, 480.0f);
        group.addActor(this.m);
        this.o = new ParticlesCanvas();
        this.o.setSize(480.0f, 480.0f);
        this.m.addActor(this.o);
        this.n = new Group();
        this.n.setOrigin(240.0f, 240.0f);
        this.n.setSize(480.0f, 480.0f);
        this.m.addActor(this.n);
        this.R = Game.i.assetManager.getParticleEffectPool("lucky-wheel-hit.prt");
        this.r = new PieChartActor();
        this.r.setSegmentCount(360);
        this.r.chart.setShadowSegments(-1);
        this.r.chart.setFadeToOut(true);
        this.r.setSize(640.0f, 640.0f);
        this.r.setPosition(-80.0f, -80.0f);
        this.n.addActor(this.r);
        Image image2 = new Image(Game.i.assetManager.getDrawable("circle"));
        image2.setSize(292.0f, 292.0f);
        image2.setPosition(94.0f, 94.0f);
        image2.setColor(Config.BACKGROUND_COLOR);
        this.n.addActor(image2);
        this.s = new PieChartActor();
        this.s.setSegmentCount(360);
        this.s.setSize(280.0f, 280.0f);
        this.s.setPosition(100.0f, 100.0f);
        this.s.chart.setShadowSegments(-1);
        this.n.addActor(this.s);
        final Circle circle = (Circle) Game.i.shapeManager.getFactory(ShapeType.CIRCLE).obtain();
        circle.setup(240.0f, 240.0f, 0.0f, 128.0f, 360, Config.BACKGROUND_COLOR.toFloatBits(), Config.BACKGROUND_COLOR.toFloatBits());
        Actor actor = new Actor(this) { // from class: com.prineside.tdi2.ui.shared.LuckyWheelOverlay.2
            @Override // com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f2) {
                circle.draw(batch);
            }
        };
        actor.setSize(256.0f, 256.0f);
        this.n.addActor(actor);
        this.p = new Group();
        this.p.setTransform(false);
        this.p.setSize(480.0f, 480.0f);
        this.n.addActor(this.p);
        Image image3 = new Image(Game.i.assetManager.getQuad("towers.GAUSS.base"));
        image3.setSize(128.0f, 128.0f);
        image3.setPosition(176.0f, 176.0f);
        this.m.addActor(image3);
        for (int i = 0; i < this.O.length; i++) {
            this.O[i] = new TrailMultilineActor();
            this.O[i].setup(MaterialColor.ORANGE.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.0f), 8.0f, 0.4f, 0.0f);
            this.O[i].setPosition(240.0f, 267.0f + (i * 12.0f));
            this.m.addActor(this.O[i]);
        }
        this.N = new Group();
        this.N.setTransform(true);
        this.N.setSize(28.0f, 105.0f);
        this.N.setOrigin(14.0f, 21.0f);
        this.N.setPosition(226.0f, 219.0f);
        this.m.addActor(this.N);
        Image image4 = new Image(Game.i.assetManager.getDrawable("tower-gauss-weapon"));
        image4.setSize(28.0f, 105.0f);
        this.N.addActor(image4);
        for (int i2 = 0; i2 < this.P.length; i2++) {
            this.P[i2] = new Image(Game.i.assetManager.getDrawable("tower-gauss-weapon-glow"));
            this.P[i2].setPosition(-4.0f, 36.0f + (i2 * 12.0f));
            this.P[i2].setColor(1.0f, 1.0f, 1.0f, 0.0f);
            this.N.addActor(this.P[i2]);
        }
        Group group2 = new Group();
        group2.setTouchable(Touchable.enabled);
        group2.setTransform(false);
        group2.setSize(100.0f, 480.0f);
        group2.setPosition(580.0f, 0.0f);
        this.m.addActor(group2);
        QuadActor quadActor = new QuadActor(Color.WHITE, new float[]{2.0f, 4.0f, 0.0f, 442.0f, 22.0f, 448.0f, 24.0f, 0.0f});
        quadActor.setSize(24.0f, 448.0f);
        quadActor.setPosition(38.0f, 16.0f);
        quadActor.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group2.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(Color.WHITE, new float[]{2.0f, 4.0f, 0.0f, 442.0f, 10.0f, 446.0f, 9.0f, 3.0f});
        quadActor2.setSize(10.0f, 446.0f);
        quadActor2.setPosition(38.0f, 16.0f);
        quadActor2.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group2.addActor(quadActor2);
        this.t = new Group();
        this.t.setTransform(false);
        this.t.setSize(100.0f, 64.0f);
        this.t.setPosition(0.0f, 416.0f);
        this.t.setPosition(0.0f, 416.0f);
        this.t.setTouchable(Touchable.enabled);
        this.t.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.shared.LuckyWheelOverlay.3

            /* renamed from: a, reason: collision with root package name */
            private float f3697a;

            /* renamed from: b, reason: collision with root package name */
            private int f3698b = 0;

            private float a(float f2) {
                float f3 = this.f3697a - f2;
                float f4 = f3;
                if (f3 < 0.0f) {
                    f4 = 0.0f;
                } else if (f4 > 416.0f) {
                    f4 = 416.0f;
                }
                return f4;
            }

            private void b(float f2) {
                LuckyWheelOverlay.this.t.setPosition(0.0f, 416.0f - a(f2));
                float f3 = this.f3697a - f2;
                float f4 = f3;
                if (f3 < 0.0f) {
                    f4 = 0.0f;
                } else if (f4 > 416.0f) {
                    f4 = 416.0f;
                }
                float f5 = f4 / 416.0f;
                FastRandom.getFairInt(2);
                StaticSoundType staticSoundType = StaticSoundType.SPRING_TENSION_2;
                if (f5 > 0.96d && this.f3698b < 7) {
                    this.f3698b = 7;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.68f, 1.165f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_1, 0.3f, 1.16f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    return;
                }
                if (f5 > 0.86d && this.f3698b < 6) {
                    this.f3698b = 6;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.65f, 1.125f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_1, 0.25f, 1.12f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    return;
                }
                if (f5 > 0.75d && this.f3698b < 5) {
                    this.f3698b = 5;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.62f, 1.09f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_1, 0.2f, 1.09f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    return;
                }
                if (f5 > 0.63d && this.f3698b < 4) {
                    this.f3698b = 4;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.59f, 1.06f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_1, 0.15f, 1.06f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    return;
                }
                if (f5 > 0.5d && this.f3698b < 3) {
                    this.f3698b = 3;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.56f, 1.035f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                    Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_1, 0.1f, 1.03f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                } else if (f5 > 0.36d && this.f3698b < 2) {
                    this.f3698b = 2;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.53f, 1.015f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                } else if (f5 > 0.205d && this.f3698b <= 0) {
                    this.f3698b = 1;
                    Game.i.soundManager.playStaticParameterized(staticSoundType, 0.5f, 1.0f + (FastRandom.getFloat() * 0.05f), 1.0f, false);
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i3, int i4) {
                if (LuckyWheelOverlay.this.B || !Game.i.progressManager.isLuckyWheelSpinAvailable()) {
                    return false;
                }
                inputEvent.cancel();
                this.f3698b = 0;
                LuckyWheelOverlay.a(LuckyWheelOverlay.this, false);
                this.f3697a = inputEvent.getStageY();
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i3, int i4) {
                if (LuckyWheelOverlay.this.U) {
                    float a2 = a(inputEvent.getStageY()) / 416.0f;
                    if (a2 > 0.2f) {
                        LuckyWheelOverlay.this.a(a2);
                    }
                    LuckyWheelOverlay.this.t.clearActions();
                    LuckyWheelOverlay.this.t.addAction(Actions.parallel(Actions.moveTo(0.0f, 416.0f, 0.2f, Interpolation.pow3In), Actions.sequence(Actions.delay(0.1f), Actions.run(() -> {
                        Game.i.soundManager.playStaticParameterized(StaticSoundType.SPRING_TENSION_RELEASE, Math.min(a2 * 2.0f, 0.8f), 1.0f, 1.0f, false);
                    }))));
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f2, float f3, int i3) {
                b(inputEvent.getStageY());
            }
        });
        group2.addActor(this.t);
        Actor actor2 = new Actor();
        actor2.setSize(164.0f, 128.0f);
        actor2.setPosition(-32.0f, -32.0f);
        actor2.setTouchable(Touchable.enabled);
        this.t.addActor(actor2);
        this.u = new Image(Game.i.assetManager.getDrawable("ui-lucky-wheel-handle"));
        this.u.setColor(MaterialColor.LIGHT_BLUE.P500);
        this.u.setSize(100.0f, 64.0f);
        this.u.setTouchable(Touchable.disabled);
        this.t.addActor(this.u);
        this.q = new Group();
        this.q.setTransform(false);
        this.q.setSize(640.0f, 128.0f);
        this.q.setPosition(-80.0f, -224.0f);
        this.m.addActor(this.q);
        this.v = new Table();
        this.v.setSize(640.0f, 96.0f);
        this.v.setPosition(-80.0f, 192.0f);
        this.v.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.76f)));
        this.m.addActor(this.v);
        Label label = new Label(Game.i.localeManager.i18n.get("lucky_shot_disabled_after_cloud_load"), Game.i.assetManager.getLabelStyle(21));
        label.setWrap(true);
        label.setColor(MaterialColor.AMBER.P300);
        label.setAlignment(1);
        this.v.add((Table) label).width(600.0f).row();
        this.w = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.v.add((Table) this.w).padTop(8.0f);
        this.g.getTable().setVisible(false);
        this.h.getTable().setVisible(false);
        h();
    }

    private float b() {
        return this.B ? this.A : ProgressPrefs.i().progress.getLuckyWheelLastRotation();
    }

    private float c() {
        return this.B ? this.M : ProgressPrefs.i().progress.getLuckyWheelLastWeaponAngle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2) {
        if (!Game.i.progressManager.isLuckyWheelSpinAvailable()) {
            f3694a.e("spin not available", new Object[0]);
            return;
        }
        if (Game.i.progressManager.getSecondsTillLuckyWheelSpinAvailable() > 0) {
            f3694a.e("spin is not yet available", new Object[0]);
            return;
        }
        f3694a.i("spin", new Object[0]);
        ProgressPrefs.i().progress.setLuckyWheelSpinInProgress(true);
        ProgressPrefs.i().progress.setLuckyWheelSpinAvailable(false);
        this.B = true;
        this.E = ProgressPrefs.i().progress.getLuckyWheelLastRotation();
        this.D = this.E + ((((int) (f2 * 5.0f)) + 3) * 360.0f) + (ProgressPrefs.i().progress.getLuckyWheelSpinRandom().nextFloat() * 360.0f);
        this.G = ProgressPrefs.i().progress.getLuckyWheelLastWeaponAngle();
        this.F = (this.G - ((((int) (f2 * 3.0f)) + 1) * 360.0f)) - (ProgressPrefs.i().progress.getLuckyWheelSpinRandom().nextFloat() * 360.0f);
        this.I = 0.0f;
        this.H = 2.5f + (((float) StrictMath.random()) * 0.5f) + (3.0f * f2);
        ProgressPrefs.i().progress.setLuckyWheelLastRotation(this.D);
        ProgressPrefs.i().progress.setLuckyWheelLastWeaponAngle(this.F);
        ProgressPrefs.i().requireSave();
        f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        f3694a.i("prepareRespin", new Object[0]);
        ProgressPrefs.i().progress.setLuckyWheelSpinAvailable(true);
        ProgressPrefs.i().requireSave();
        Array array = new Array(WheelOptionConfig.class);
        for (int i = 0; i < this.z.size; i++) {
            if (this.z.items[i].wasHit) {
                array.add(this.z.items[i]);
            }
        }
        if (array.size == 0) {
            rebuild();
            return;
        }
        for (int i2 = 0; i2 < this.z.size; i2++) {
            WheelOptionConfig wheelOptionConfig = this.z.items[i2];
            wheelOptionConfig.e.clearActions();
            if (!array.contains(wheelOptionConfig, true)) {
                wheelOptionConfig.respinPrepareFromAngle = wheelOptionConfig.d;
                wheelOptionConfig.e.addAction(Actions.parallel(Actions.alpha(1.0f, 0.5f, f3695b), Actions.scaleTo(1.0f, 1.0f, 0.5f, f3695b)));
            } else {
                wheelOptionConfig.e.addAction(Actions.scaleTo(0.0f, 0.0f, 0.5f, f3695b));
            }
        }
        FloatArray floatArray = new FloatArray();
        for (int i3 = 0; i3 < array.size; i3++) {
            floatArray.add(((WheelOptionConfig[]) array.items)[i3].f3700a.chance);
            ((WheelOptionConfig[]) array.items)[i3].f3700a.chance = 0.0f;
        }
        g();
        for (int i4 = 0; i4 < array.size; i4++) {
            ((WheelOptionConfig[]) array.items)[i4].f3700a.chance = floatArray.items[i4];
        }
        this.J = true;
        this.L = 0.0f;
        f();
    }

    private void e() {
        f3694a.i("prepareNextWheel", new Object[0]);
        this.K = true;
        f();
        this.n.clearActions();
        this.n.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f, 0.3f, Interpolation.pow2In), Actions.run(() -> {
            Game.i.progressManager.generateNewLuckyWheel();
            ProgressPrefs.i().progress.setLuckyWheelSpinAvailable(true);
            ProgressPrefs.i().requireSave();
            this.o.clearParticles();
            rebuild();
        }), Actions.scaleTo(1.0f, 1.0f, 0.3f, Interpolation.pow2Out), Actions.run(() -> {
            this.K = false;
            f();
        })));
    }

    private void a(WheelOptionConfig wheelOptionConfig) {
        f3694a.i("onSpinFinished", new Object[0]);
        ProgressPrefs.i().progress.setLuckyWheelSpinInProgress(false);
        ProgressPrefs.i().requireSave();
        Array<WheelOption> luckyWheelOptions = Game.i.progressManager.getLuckyWheelOptions();
        if (!luckyWheelOptions.removeValue(wheelOptionConfig.f3700a, true)) {
            throw new IllegalStateException("can't remove last hit option from manager");
        }
        if (luckyWheelOptions.size < 5) {
            for (int i = luckyWheelOptions.size - 1; i >= 0; i--) {
                if (luckyWheelOptions.items[i].wheelMultiplier != 0) {
                    WheelOption removeIndex = luckyWheelOptions.removeIndex(i);
                    for (int i2 = 0; i2 < this.z.size; i2++) {
                        if (this.z.items[i2].f3700a == removeIndex) {
                            this.z.items[i2].wasHit = true;
                        }
                    }
                }
            }
        }
        if (wheelOptionConfig.f3700a.wheelMultiplier == 0) {
            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.LUCKY_SHOT, Game.getTimestampSeconds());
            issuedItems.items.add(wheelOptionConfig.f3700a.item);
            Game.i.progressManager.addIssuedPrizes(issuedItems, false);
            Game.i.progressManager.addItemStack(wheelOptionConfig.f3700a.item, "lucky_wheel");
        } else {
            for (int i3 = 0; i3 < luckyWheelOptions.size; i3++) {
                if (luckyWheelOptions.items[i3].item != null && luckyWheelOptions.items[i3].item.getItem().affectedByLuckyWheelMultiplier()) {
                    luckyWheelOptions.items[i3].item.setCount(PMath.multiplyWithoutOverflow(luckyWheelOptions.items[i3].item.getCount(), wheelOptionConfig.f3700a.wheelMultiplier));
                }
            }
            for (int i4 = 0; i4 < this.z.size; i4++) {
                if (this.z.items[i4].f3700a.item != null && this.z.items[i4].g != null) {
                    this.z.items[i4].g.setText("x" + this.z.items[i4].f3700a.item.getCount());
                    this.z.items[i4].g.clearActions();
                    this.z.items[i4].g.addAction(Actions.sequence(Actions.scaleTo(1.5f, 1.5f, 0.3f), Actions.scaleTo(1.0f, 1.0f, 0.3f)));
                }
            }
            ProgressPrefs.i().progress.setLuckyWheelCurrentMultiplier(ProgressPrefs.i().progress.getLuckyWheelCurrentMultiplier() * wheelOptionConfig.f3700a.wheelMultiplier);
            ProgressPrefs.i().requireSave();
            d();
        }
        f();
    }

    private void f() {
        this.q.clear();
        this.y = false;
        if (this.B || this.J || this.K) {
            this.y = false;
            return;
        }
        if (Game.i.progressManager.isLuckyWheelSpinAvailable()) {
            this.y = true;
            return;
        }
        this.y = false;
        int luckyWheelRespinPriceTokens = Game.i.progressManager.getLuckyWheelRespinPriceTokens();
        int luckyWheelRespinPriceAccelerators = Game.i.progressManager.getLuckyWheelRespinPriceAccelerators();
        if (luckyWheelRespinPriceTokens > 0 || luckyWheelRespinPriceAccelerators > 0) {
            Table table = new Table();
            table.setSize(luckyWheelRespinPriceTokens > 0 ? 392.0f : 192.0f, 42.0f);
            table.setPosition(luckyWheelRespinPriceTokens > 0 ? 248.0f : 448.0f, 86.0f);
            this.q.addActor(table);
            Image image = new Image(Game.i.assetManager.getDrawable("icon-restart"));
            image.setColor(MaterialColor.GREEN.P300);
            table.add((Table) image).size(32.0f).padRight(6.0f);
            Label label = new Label(Game.i.localeManager.i18n.get("lucky_shot_respin_title"), Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.GREEN.P300);
            table.add((Table) label);
            if (luckyWheelRespinPriceTokens > 0) {
                ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    int luckyWheelRespinPriceTokens2 = Game.i.progressManager.getLuckyWheelRespinPriceTokens();
                    if (Game.i.progressManager.removeItems(Item.D.LUCKY_SHOT_TOKEN, luckyWheelRespinPriceTokens2)) {
                        Game.i.analyticsManager.logCurrencySpent("lucky_wheel_respin", "lucky_shot_token", luckyWheelRespinPriceTokens2);
                        d();
                    } else {
                        Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_tokens"), null, null, null);
                    }
                });
                Table table2 = new Table();
                table2.setSize(192.0f, 80.0f);
                complexButton.addActor(table2);
                table2.add((Table) new Image(Game.i.assetManager.getDrawable("lucky-shot-token"))).size(48.0f).padRight(6.0f);
                table2.add((Table) new Label("x" + luckyWheelRespinPriceTokens, Game.i.assetManager.getLabelStyle(30)));
                complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 3.0f, 80.0f, 189.0f, 77.0f, 192.0f, 3.0f})), 0.0f, 0.0f, 192.0f, 80.0f);
                complexButton.setBackgroundColors(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.GREEN.P900.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.GREEN.P700.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.RED.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
                complexButton.setSize(192.0f, 80.0f);
                complexButton.setPosition(248.0f, 0.0f);
                this.q.addActor(complexButton);
            }
            if (luckyWheelRespinPriceAccelerators > 0) {
                ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    int luckyWheelRespinPriceAccelerators2 = Game.i.progressManager.getLuckyWheelRespinPriceAccelerators();
                    if (Game.i.progressManager.removeItems(Item.D.ACCELERATOR, luckyWheelRespinPriceAccelerators2)) {
                        Game.i.analyticsManager.logCurrencySpent("lucky_wheel_respin", "accelerator", luckyWheelRespinPriceAccelerators2);
                        d();
                    } else {
                        Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_accelerators"), null, null, null);
                    }
                });
                Table table3 = new Table();
                table3.setSize(192.0f, 80.0f);
                complexButton2.addActor(table3);
                table3.add((Table) new Image(Game.i.assetManager.getDrawable("time-accelerator"))).size(48.0f).padRight(6.0f);
                table3.add((Table) new Label("x" + luckyWheelRespinPriceAccelerators, Game.i.assetManager.getLabelStyle(30)));
                complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{3.0f, 3.0f, 0.0f, 77.0f, 192.0f, 80.0f, 189.0f, 0.0f})), 0.0f, 0.0f, 192.0f, 80.0f);
                complexButton2.setBackgroundColors(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.GREEN.P900.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.GREEN.P700.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.RED.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
                complexButton2.setSize(192.0f, 80.0f);
                complexButton2.setPosition(448.0f, 0.0f);
                this.q.addActor(complexButton2);
            }
        }
        Table table4 = new Table();
        table4.setSize(192.0f, 42.0f);
        table4.setPosition(0.0f, 86.0f);
        this.q.addActor(table4);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-lucky-wheel-plus"));
        image2.setColor(MaterialColor.LIGHT_BLUE.P300);
        table4.add((Table) image2).size(32.0f).padRight(6.0f);
        Label label2 = new Label(Game.i.localeManager.i18n.get("lucky_shot_new_wheel_title"), Game.i.assetManager.getLabelStyle(24));
        label2.setColor(MaterialColor.LIGHT_BLUE.P300);
        table4.add((Table) label2);
        ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            if (Game.i.progressManager.removeItems(Item.D.LUCKY_SHOT_TOKEN, 1)) {
                Game.i.analyticsManager.logCurrencySpent("lucky_wheel_spin", "lucky_shot_token", 1);
                e();
            } else {
                Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_tokens"), null, null, null);
            }
        });
        Table table5 = new Table();
        table5.setSize(192.0f, 80.0f);
        complexButton3.addActor(table5);
        table5.add((Table) new Image(Game.i.assetManager.getDrawable("lucky-shot-token"))).size(48.0f).padRight(6.0f);
        table5.add((Table) new Label("x1", Game.i.assetManager.getLabelStyle(30)));
        complexButton3.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{3.0f, 3.0f, 0.0f, 77.0f, 192.0f, 80.0f, 189.0f, 0.0f})), 0.0f, 0.0f, 192.0f, 80.0f);
        complexButton3.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.LIGHT_BLUE.P900.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.LIGHT_BLUE.P700.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.RED.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
        complexButton3.setSize(192.0f, 80.0f);
        complexButton3.setPosition(0.0f, 0.0f);
        this.q.addActor(complexButton3);
        Table table6 = new Table();
        table6.setTouchable(Touchable.disabled);
        table6.setPosition(0.0f, 140.0f);
        table6.setSize(this.q.getWidth(), 48.0f);
        this.q.addActor(table6);
        table6.add((Table) new Image(Game.i.assetManager.getDrawable("lucky-shot-token"))).size(48.0f).padRight(10.0f);
        Label label3 = new Label(new StringBuilder().append(Game.i.progressManager.getItemsCount(Item.D.LUCKY_SHOT_TOKEN)).toString(), Game.i.assetManager.getLabelStyle(30));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.78f);
        table6.add((Table) label3);
    }

    private void g() {
        if (this.z.size == 0) {
            return;
        }
        float f2 = 0.0f;
        for (int i = 0; i < this.z.size; i++) {
            f2 += this.z.items[i].f3700a.chance;
        }
        float f3 = 360.0f / f2;
        float f4 = 0.0f;
        for (int i2 = 0; i2 < this.z.size; i2++) {
            WheelOptionConfig wheelOptionConfig = this.z.items[i2];
            float f5 = f4;
            f4 += wheelOptionConfig.f3700a.chance * f3;
            wheelOptionConfig.f3701b = f5;
            wheelOptionConfig.c = f4;
            wheelOptionConfig.d = (f5 + f4) * 0.5f;
        }
        this.z.items[this.z.size - 1].c = 360.0f;
    }

    private void h() {
        this.i = Game.i.progressManager.getRarityColors();
        this.j = Game.i.progressManager.getRarityBrightColors();
        this.k = new Color[this.i.length];
        this.l = new Color[this.i.length];
        for (int i = 0; i < RarityType.values.length; i++) {
            this.k[i] = this.i[i].cpy().mul(1.0f, 1.0f, 1.0f, 0.28f);
            this.l[i] = this.j[i].cpy().mul(1.0f, 1.0f, 1.0f, 0.56f);
        }
    }

    public final void rebuild() {
        Color color;
        Color color2;
        Array<WheelOption> luckyWheelOptions = Game.i.progressManager.getLuckyWheelOptions();
        this.z.clear();
        for (int i = 0; i < luckyWheelOptions.size; i++) {
            WheelOptionConfig wheelOptionConfig = new WheelOptionConfig();
            wheelOptionConfig.f3700a = luckyWheelOptions.items[i];
            this.z.add(wheelOptionConfig);
        }
        h();
        g();
        float f2 = 0.0f;
        Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
        for (int i2 = 0; i2 < this.z.size; i2++) {
            if (this.z.items[i2].f3700a.wheelMultiplier != 0) {
                color2 = d;
            } else {
                color2 = this.k[this.z.items[i2].f3700a.item.getItem().getRarity().ordinal()];
            }
            PieChart.ChartEntryConfig chartEntryConfig = new PieChart.ChartEntryConfig(color2, this.z.items[i2].f3700a.chance, 0.0f);
            chartEntryConfig.userObject = this.z.items[i2];
            array.add(chartEntryConfig);
            this.z.items[i2].i = chartEntryConfig;
            f2 += this.z.items[i2].f3700a.chance;
        }
        this.r.setConfigs(array);
        this.r.chart.requestVerticesRebuild();
        Array<PieChart.ChartEntryConfig> array2 = new Array<>(PieChart.ChartEntryConfig.class);
        for (int i3 = 0; i3 < this.z.size; i3++) {
            if (this.z.items[i3].f3700a.wheelMultiplier != 0) {
                color = c;
            } else {
                color = this.i[this.z.items[i3].f3700a.item.getItem().getRarity().ordinal()];
            }
            PieChart.ChartEntryConfig chartEntryConfig2 = new PieChart.ChartEntryConfig(color, this.z.items[i3].f3700a.chance, 0.0f);
            chartEntryConfig2.userObject = this.z.items[i3];
            array2.add(chartEntryConfig2);
            this.z.items[i3].h = chartEntryConfig2;
        }
        this.s.setConfigs(array2);
        this.s.chart.requestVerticesRebuild();
        this.p.clear();
        for (int i4 = 0; i4 < this.z.size; i4++) {
            final WheelOptionConfig wheelOptionConfig2 = this.z.items[i4];
            float f3 = wheelOptionConfig2.d - 90.0f;
            Group group = new Group();
            group.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.LuckyWheelOverlay.4
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f4, float f5) {
                    if (wheelOptionConfig2.f3700a.item == null) {
                        return;
                    }
                    ItemDescriptionDialog.i().showWithCount(wheelOptionConfig2.f3700a.item.getItem(), wheelOptionConfig2.f3700a.item.getCount());
                }
            });
            group.setTransform(true);
            group.setSize(80.0f, 80.0f);
            group.setOrigin(40.0f, 40.0f);
            this.p.addActor(group);
            wheelOptionConfig2.e = group;
            if (wheelOptionConfig2.f3700a.wheelMultiplier == 0) {
                group.addActor(wheelOptionConfig2.f3700a.item.getItem().generateIcon(80.0f, true));
                wheelOptionConfig2.g = new Label("x" + wheelOptionConfig2.f3700a.item.getCount(), Game.i.assetManager.getLabelStyle(24));
                wheelOptionConfig2.g.setAlignment(1);
                wheelOptionConfig2.g.setSize(80.0f, 32.0f);
                wheelOptionConfig2.g.setPosition(0.0f, -32.0f);
                group.addActor(wheelOptionConfig2.g);
            } else if (wheelOptionConfig2.f3700a.wheelMultiplier == 2) {
                Image image = new Image(Game.i.assetManager.getDrawable("lucky-wheel-x2"));
                image.setSize(80.0f, 80.0f);
                group.addActor(image);
            } else {
                Image image2 = new Image(Game.i.assetManager.getDrawable("lucky-wheel-x3"));
                image2.setSize(80.0f, 80.0f);
                group.addActor(image2);
            }
            Label label = new Label(StrictMath.round((wheelOptionConfig2.f3700a.chance / f2) * 100.0f) + "%", Game.i.assetManager.getLabelStyle(21));
            label.setAlignment(1);
            label.setSize(80.0f, 24.0f);
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label.setPosition(0.0f, -94.0f);
            group.addActor(label);
            if (wheelOptionConfig2.f3700a.wheelMultiplier == 0) {
                label.setColor(Game.i.progressManager.getRarityBrightColor(wheelOptionConfig2.f3700a.item.getItem().getRarity()));
            } else {
                label.setColor(e);
            }
            wheelOptionConfig2.f = label;
            label.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.alpha(1.0f, 0.2f), Actions.delay(1.5f), Actions.alpha(0.0f, 1.0f)));
            wheelOptionConfig2.setItemContainerAngle(f3);
        }
        this.x = -1;
        f();
    }

    public final void setVisible(boolean z) {
        if (!z && (this.B || this.J || this.K)) {
            f3694a.e("can't hide while spinning / preparing", new Object[0]);
            return;
        }
        if (z) {
            rebuild();
            UiUtils.bouncyShowOverlayWithCallback(this.g.getTable(), this.h.getTable(), this.m, () -> {
                this.m.setTransform(true);
            });
            if (!this.U && ProgressPrefs.i().progress.isLuckyWheelSpinInProgress()) {
                f3694a.e("showing lucky wheel while spin in progress", new Object[0]);
                Game.i.progressManager.removeItems(Item.D.LUCKY_SHOT_TOKEN, 1);
                ProgressPrefs.i().progress.setLuckyWheelSpinAvailable(true);
                ProgressPrefs.i().requireSave();
                a(0.3f + (((float) Math.random()) * 0.6f));
            }
        } else {
            UiUtils.bouncyHideOverlay(this.g.getTable(), this.h.getTable(), this.m);
        }
        this.U = z;
    }

    private int a(float f2, float f3) {
        int i = -1;
        float f4 = ((((f2 - f3) + 90.0f) % 360.0f) + 360.0f) % 360.0f;
        int i2 = 0;
        while (true) {
            if (i2 >= this.z.size) {
                break;
            }
            WheelOptionConfig wheelOptionConfig = this.z.items[i2];
            float min = StrictMath.min(wheelOptionConfig.f3701b, wheelOptionConfig.c);
            float max = StrictMath.max(wheelOptionConfig.f3701b, wheelOptionConfig.c);
            if (f4 < min || f4 > max) {
                i2++;
            } else {
                i = i2;
                break;
            }
        }
        if (i == -1) {
            throw new IllegalStateException("Invalid weapon rotation " + f2 + ", wheelOptions.size " + this.z.size);
        }
        return i;
    }

    private void j() {
        Color add;
        Color add2;
        if (this.z.size == 0) {
            f3694a.e("wheelOptions is empty, rebuilding", new Object[0]);
            rebuild();
            return;
        }
        if (this.J) {
            this.L += 0.03332f;
            if (this.L >= 1.0f) {
                this.J = false;
                rebuild();
            } else {
                float apply = f3695b.apply(this.L);
                for (int i = 0; i < this.z.size; i++) {
                    WheelOptionConfig wheelOptionConfig = this.z.items[i];
                    if (wheelOptionConfig.wasHit) {
                        float f2 = wheelOptionConfig.f3700a.chance * (1.0f - apply);
                        wheelOptionConfig.h.setValue(f2);
                        wheelOptionConfig.i.setValue(f2);
                    }
                }
                this.s.chart.requestVerticesRebuild();
                this.r.chart.requestVerticesRebuild();
                for (int i2 = 0; i2 < this.z.size; i2++) {
                    WheelOptionConfig wheelOptionConfig2 = this.z.items[i2];
                    if (!wheelOptionConfig2.wasHit) {
                        Vector3 posRotForAngle = WheelOptionConfig.getPosRotForAngle((wheelOptionConfig2.respinPrepareFromAngle + (PMath.getDistanceBetweenAngles(wheelOptionConfig2.respinPrepareFromAngle, wheelOptionConfig2.d) * apply)) - 90.0f);
                        wheelOptionConfig2.e.setPosition(posRotForAngle.x, posRotForAngle.y);
                        wheelOptionConfig2.e.setRotation(posRotForAngle.z);
                    }
                }
            }
        }
        if (this.B) {
            this.I += 0.01666f;
            int a2 = a(this.M, this.A);
            if (a2 != this.x) {
                Game.i.soundManager.playStatic(StaticSoundType.TICK);
                if (this.x != -1) {
                    WheelOptionConfig wheelOptionConfig3 = this.z.items[this.x];
                    this.s.getConfigs().items[this.x].color = wheelOptionConfig3.f3700a.wheelMultiplier == 0 ? this.i[wheelOptionConfig3.f3700a.item.getItem().getRarity().ordinal()] : c;
                    this.r.getConfigs().items[this.x].color = wheelOptionConfig3.f3700a.wheelMultiplier == 0 ? this.k[wheelOptionConfig3.f3700a.item.getItem().getRarity().ordinal()] : d;
                }
                this.x = a2;
                WheelOptionConfig wheelOptionConfig4 = this.z.items[this.x];
                this.s.getConfigs().items[a2].color = wheelOptionConfig4.f3700a.wheelMultiplier == 0 ? this.j[wheelOptionConfig4.f3700a.item.getItem().getRarity().ordinal()] : e;
                this.r.getConfigs().items[a2].color = wheelOptionConfig4.f3700a.wheelMultiplier == 0 ? this.l[wheelOptionConfig4.f3700a.item.getItem().getRarity().ordinal()] : f;
                this.s.chart.requestVerticesRebuild();
                this.r.chart.requestVerticesRebuild();
            }
            float f3 = this.I / this.H;
            if (this.H - 1.3f < this.I && !this.C) {
                Game.i.soundManager.playStaticParameterized(StaticSoundType.SHOT_GAUSS_CHARGE, 0.7f, 0.75f, 0.0f, false);
                this.C = true;
            }
            if (f3 >= 1.0f) {
                this.Q = 1.0f;
                this.B = false;
                this.C = false;
                BulletSmokeMultiLine bulletSmokeMultiLine = (BulletSmokeMultiLine) Game.i.shapeManager.getFactory(ShapeType.BULLET_SMOKE_MULTI_LINE).obtain();
                bulletSmokeMultiLine.setTexture(Game.i.assetManager.getTextureRegion("bullet-trace-smoke"), false, FastRandom.getFloat() < 0.5f);
                bulletSmokeMultiLine.maxSegmentWidth = 25.6f;
                bulletSmokeMultiLine.nodesDisperseTime = 3.0f;
                bulletSmokeMultiLine.maxAlpha = 0.56f;
                Vector2 vector2 = new Vector2();
                PMath.getPointByAngleFromPoint(240.0f, 240.0f, this.M, 24.0f, vector2);
                float f4 = vector2.x;
                float f5 = vector2.y;
                PMath.getPointByAngleFromPoint(240.0f, 240.0f, this.M, 960.0f, vector2);
                bulletSmokeMultiLine.setup(f4, f5, vector2.x, vector2.y);
                Actor actor = new Actor(this, bulletSmokeMultiLine, 0.01666f) { // from class: com.prineside.tdi2.ui.shared.LuckyWheelOverlay.5
                    private /* synthetic */ BulletSmokeMultiLine j;
                    private /* synthetic */ float k = 0.01666f;

                    @Override // com.prineside.tdi2.scene2d.Actor
                    public void draw(Batch batch, float f6) {
                        this.j.update(this.k);
                        if (this.j.isFinished()) {
                            this.j.free();
                            remove();
                            return;
                        }
                        batch.end();
                        batch.setBlendFunction(770, 1);
                        batch.begin();
                        this.j.draw(batch);
                        batch.end();
                        batch.setBlendFunction(770, 771);
                        batch.begin();
                    }
                };
                actor.setSize(1.0f, 1.0f);
                this.m.addActor(actor);
                WheelOptionConfig wheelOptionConfig5 = this.z.items[this.x];
                if (wheelOptionConfig5.f3700a.wheelMultiplier != 0) {
                    add = e.cpy().add(0.14f, 0.14f, 0.14f, 1.0f);
                    add2 = f.cpy().add(0.0f, 0.0f, 0.0f, 1.0f);
                } else {
                    add = this.j[wheelOptionConfig5.f3700a.item.getItem().getRarity().ordinal()].cpy().add(0.14f, 0.14f, 0.14f, 1.0f);
                    add2 = this.l[wheelOptionConfig5.f3700a.item.getItem().getRarity().ordinal()].cpy().add(0.0f, 0.0f, 0.0f, 1.0f);
                }
                this.s.getConfigs().items[this.x].color = add;
                this.r.getConfigs().items[this.x].color = add2;
                this.s.chart.requestVerticesRebuild();
                this.r.chart.requestVerticesRebuild();
                Game.i.soundManager.playStaticParameterized(StaticSoundType.SHOT_GAUSS, 0.5f, 1.0f, 0.0f, false);
                if (wheelOptionConfig5.f3700a.wheelMultiplier != 0) {
                    Game.i.soundManager.playStatic(StaticSoundType.SUCCESS);
                } else {
                    Game.i.soundManager.playRarity(wheelOptionConfig5.f3700a.item.getItem().getRarity());
                }
                for (int i3 = 0; i3 < this.z.size; i3++) {
                    WheelOptionConfig wheelOptionConfig6 = this.z.items[i3];
                    wheelOptionConfig6.e.clearActions();
                    wheelOptionConfig6.f.clearActions();
                    wheelOptionConfig6.f.addAction(Actions.alpha(0.0f, 0.2f));
                    if (wheelOptionConfig5 == wheelOptionConfig6) {
                        wheelOptionConfig6.e.addAction(Actions.parallel(Actions.scaleTo(1.1f, 1.1f, 0.2f)));
                    } else {
                        wheelOptionConfig6.e.addAction(Actions.parallel(Actions.scaleTo(0.9f, 0.9f, 0.2f), Actions.alpha(0.56f, 0.2f)));
                    }
                }
                Color rarityBrightColor = wheelOptionConfig5.f3700a.wheelMultiplier == 0 ? Game.i.progressManager.getRarityBrightColor(wheelOptionConfig5.f3700a.item.getItem().getRarity()) : e;
                ParticleEffectPool.PooledEffect obtain = this.R.obtain();
                obtain.getEmitters().first().getTint().setColors(new float[]{rarityBrightColor.r, rarityBrightColor.g, rarityBrightColor.f888b});
                obtain.getEmitters().first().getAngle().setHighMin(wheelOptionConfig5.f3701b + this.n.getRotation());
                obtain.getEmitters().first().getAngle().setHighMax(wheelOptionConfig5.c + this.n.getRotation());
                obtain.getEmitters().first().getEmission().setHigh((StrictMath.abs(PMath.getDistanceBetweenAngles(wheelOptionConfig5.c, wheelOptionConfig5.f3701b)) / 30.0f) * 300.0f);
                this.o.addParticle(obtain, 240.0f, 240.0f);
                this.N.clearActions();
                this.N.setTransform(true);
                Vector2 vector22 = new Vector2();
                PMath.getPointByAngleFromPoint(0.0f, 0.0f, this.N.getRotation() + 180.0f, 15.0f, vector22);
                this.N.addAction(Actions.sequence(Actions.moveTo(226.0f + vector22.x, 219.0f + vector22.y), Actions.moveTo(226.0f, 219.0f, 0.3f, Interpolation.exp5In)));
                wheelOptionConfig5.wasHit = true;
                a(wheelOptionConfig5);
            } else {
                this.A = this.E + (Interpolation.exp10Out.apply(f3) * (this.D - this.E));
                this.Q = f3 * 1.25f;
                this.M = this.G + (Interpolation.smoother.apply(f3) * (this.F - this.G));
            }
        } else {
            this.Q *= 0.98334f;
        }
        if (this.Q > 1.0f) {
            this.Q = 1.0f;
        }
        this.A = ((this.A % 360.0f) + 360.0f) % 360.0f;
        this.n.setRotation(b());
        this.N.setRotation(c());
        float f6 = (this.Q - 0.25f) / 0.75f;
        for (int i4 = 0; i4 < this.O.length; i4++) {
            float f7 = 0.0f;
            float length = (1.0f / this.O.length) * i4;
            if (f6 > length) {
                float length2 = (f6 - length) * this.O.length;
                f7 = length2;
                if (length2 < 0.0f) {
                    f7 = 0.0f;
                } else if (f7 > 1.0f) {
                    f7 = 1.0f;
                }
            }
            S.set(0.0f, 1.0f).rotateDeg(c()).scl(27.0f + (i4 * 12.0f));
            this.O[i4].trail.getColor().f889a = f7;
            this.O[i4].setPosition(240.0f + S.x, 240.0f + S.y);
            this.P[i4].setColor(1.0f, 1.0f, 1.0f, f7);
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f2) {
        if (this.U) {
            int secondsTillLuckyWheelSpinAvailable = Game.i.progressManager.getSecondsTillLuckyWheelSpinAvailable();
            if (secondsTillLuckyWheelSpinAvailable > 0) {
                this.w.setText(StringFormatter.timePassed(secondsTillLuckyWheelSpinAvailable, false, false));
                this.v.setVisible(true);
            } else {
                this.v.setVisible(false);
            }
            if (this.y && secondsTillLuckyWheelSpinAvailable <= 0) {
                this.t.setTouchable(Touchable.enabled);
                this.u.setColor(MaterialColor.LIGHT_BLUE.P500);
            } else {
                this.t.setTouchable(Touchable.disabled);
                this.u.setColor(MaterialColor.GREY.P600);
            }
            this.T += f2;
            while (this.T > 0.01666f) {
                j();
                this.T -= 0.01666f;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LuckyWheelOverlay$WheelOptionConfig.class */
    public static class WheelOptionConfig {

        /* renamed from: a, reason: collision with root package name */
        private WheelOption f3700a;

        /* renamed from: b, reason: collision with root package name */
        private float f3701b;
        private float c;
        private float d;
        private Group e;
        private Label f;
        private Label g;
        private PieChart.ChartEntryConfig h;
        private PieChart.ChartEntryConfig i;
        public boolean wasHit;
        public float respinPrepareFromAngle;
        public static Vector3 helperVector3 = new Vector3();

        public static Vector3 getPosRotForAngle(float f) {
            LuckyWheelOverlay.S.set(0.0f, 1.0f).rotateDeg(f).scl(230.0f);
            float f2 = 240.0f + LuckyWheelOverlay.S.x;
            float f3 = 240.0f + LuckyWheelOverlay.S.y;
            helperVector3.x = f2 - 40.0f;
            helperVector3.y = f3 - 40.0f;
            helperVector3.z = f;
            return helperVector3;
        }

        public void setItemContainerAngle(float f) {
            Vector3 posRotForAngle = getPosRotForAngle(f);
            this.e.setPosition(posRotForAngle.x, posRotForAngle.y);
            this.e.setRotation(posRotForAngle.z);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LuckyWheelOverlay$WheelOption.class */
    public static class WheelOption {
        public ItemStack item;
        public float chance;
        public int wheelMultiplier;

        public WheelOption(ItemStack itemStack, float f) {
            this.item = itemStack;
            this.chance = f;
        }

        public WheelOption(ItemStack itemStack, float f, int i) {
            this.item = itemStack;
            this.chance = f;
            this.wheelMultiplier = i;
        }

        public void toJson(Json json) {
            if (this.item != null) {
                json.writeObjectStart("item");
                this.item.toJson(json);
                json.writeObjectEnd();
            }
            json.writeValue("chance", Float.valueOf(this.chance));
            if (this.wheelMultiplier != 0) {
                json.writeValue("wheelMultiplier", Integer.valueOf(this.wheelMultiplier));
            }
        }

        public static WheelOption fromJson(JsonValue jsonValue) {
            ItemStack itemStack = null;
            if (jsonValue.get("item") != null) {
                itemStack = ItemStack.fromJson(jsonValue.get("item"));
            }
            return new WheelOption(itemStack, jsonValue.getFloat("chance"), jsonValue.getInt("wheelMultiplier", 0));
        }

        public String toString() {
            return "(chance: " + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(this.chance, 2, false)) + ", wheelMultiplier: " + this.wheelMultiplier + ", item: " + this.item + ")";
        }
    }
}

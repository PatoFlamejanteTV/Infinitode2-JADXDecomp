package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.towers.AirTower;
import com.prineside.tdi2.towers.BasicTower;
import com.prineside.tdi2.towers.BlastTower;
import com.prineside.tdi2.towers.CannonTower;
import com.prineside.tdi2.towers.CrusherTower;
import com.prineside.tdi2.towers.FlamethrowerTower;
import com.prineside.tdi2.towers.FreezingTower;
import com.prineside.tdi2.towers.GaussTower;
import com.prineside.tdi2.towers.LaserTower;
import com.prineside.tdi2.towers.MinigunTower;
import com.prineside.tdi2.towers.MissileTower;
import com.prineside.tdi2.towers.MultishotTower;
import com.prineside.tdi2.towers.SniperTower;
import com.prineside.tdi2.towers.SplashTower;
import com.prineside.tdi2.towers.TeslaTower;
import com.prineside.tdi2.towers.VenomTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerManager.class */
public final class TowerManager extends Manager.ManagerAdapter {
    public static final int STAT_ROUNDING_NONE = 0;
    public static final int STAT_ROUNDING_FLOOR = 1;
    public static final int STAT_ROUNDING_MIDDLE = 2;
    public static final int STAT_ROUNDING_CEIL = 3;

    /* renamed from: a, reason: collision with root package name */
    private final Tower.Factory[] f2473a = new Tower.Factory[TowerType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private final String[] f2474b = new String[Tower.AimStrategy.values.length];
    private final String[] c = new String[Tower.AimStrategy.values.length];
    public final ObjectMap<TowerType, String> SHORT_TOWER_ALIASES = new ObjectMap<>();
    public final boolean[][] canTowerAttackEnemy;
    public final float[][] towerEnemyDamageMultiplier;
    private final TowerStatsConfig[] d;
    private final StatisticsType[] e;
    private final StatisticsType[] f;
    private final StatisticsType[] g;
    private final StatisticsType[] h;
    private final StatisticsType[] i;
    private final StatisticsType[] j;
    private final String[] k;
    private final String[] l;
    private final String[] m;
    private final GameValueType[] n;
    private final GameValueType[] o;
    private final GameValueType[] p;
    private final GameValueType[] q;
    private final GameValueType[] r;
    private final GameValueType[] s;
    private final GameValueType[] t;
    private final GameValueType[] u;
    private final GameValueType[] v;
    private final GameValueType[] w;
    private final GameValueType[] x;
    private final GameValueType[] y;
    public final Factories F;
    public ParticleEffectPool abilityAvailableParticleEffectPool;
    public ParticleEffectPool[] highlightParticles;
    public ParticleEffectPool upgradeParticles;
    public ParticleEffectPool lvlUpParticles;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerManager$Factories.class */
    public static class Factories {
        public BasicTower.BasicTowerFactory BASIC;
        public AirTower.AirTowerFactory AIR;
        public BlastTower.BlastTowerFactory BLAST;
        public CannonTower.CannonTowerFactory CANNON;
        public FreezingTower.FreezingTowerFactory FREEZING;
        public MinigunTower.MinigunTowerFactory MINIGUN;
        public MissileTower.MissileTowerFactory MISSILE;
        public MultishotTower.MultishotTowerFactory MULTISHOT;
        public SniperTower.SniperTowerFactory SNIPER;
        public SplashTower.SplashTowerFactory SPLASH;
        public TeslaTower.TeslaTowerFactory TESLA;
        public VenomTower.VenomTowerFactory VENOM;
        public FlamethrowerTower.FlamethrowerTowerFactory FLAMETHROWER;
        public LaserTower.LaserTowerFactory LASER;
        public GaussTower.GaussTowerFactory GAUSS;
        public CrusherTower.CrusherTowerFactory CRUSHER;
    }

    static {
        TLog.forClass(TowerManager.class);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<TowerManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TowerManager read() {
            return Game.i.towerManager;
        }
    }

    public TowerManager() {
        this.SHORT_TOWER_ALIASES.put(TowerType.BASIC, "B");
        this.SHORT_TOWER_ALIASES.put(TowerType.SNIPER, "S");
        this.SHORT_TOWER_ALIASES.put(TowerType.CANNON, "C");
        this.SHORT_TOWER_ALIASES.put(TowerType.FREEZING, "F");
        this.SHORT_TOWER_ALIASES.put(TowerType.VENOM, "V");
        this.SHORT_TOWER_ALIASES.put(TowerType.SPLASH, "SP");
        this.SHORT_TOWER_ALIASES.put(TowerType.BLAST, "BL");
        this.SHORT_TOWER_ALIASES.put(TowerType.MULTISHOT, "M");
        this.SHORT_TOWER_ALIASES.put(TowerType.MINIGUN, "MI");
        this.SHORT_TOWER_ALIASES.put(TowerType.AIR, "A");
        this.SHORT_TOWER_ALIASES.put(TowerType.TESLA, "T");
        this.SHORT_TOWER_ALIASES.put(TowerType.MISSILE, "MS");
        this.SHORT_TOWER_ALIASES.put(TowerType.FLAMETHROWER, "FL");
        this.SHORT_TOWER_ALIASES.put(TowerType.LASER, "L");
        this.SHORT_TOWER_ALIASES.put(TowerType.GAUSS, "G");
        this.SHORT_TOWER_ALIASES.put(TowerType.CRUSHER, "CR");
        this.canTowerAttackEnemy = new boolean[EnemyType.values.length][TowerType.values.length];
        this.towerEnemyDamageMultiplier = new float[EnemyType.values.length][TowerType.values.length];
        this.d = new TowerStatsConfig[TowerType.values.length];
        this.e = new StatisticsType[TowerType.values.length];
        this.f = new StatisticsType[TowerType.values.length];
        this.g = new StatisticsType[TowerType.values.length];
        this.h = new StatisticsType[TowerType.values.length];
        this.i = new StatisticsType[TowerType.values.length];
        this.j = new StatisticsType[TowerType.values.length];
        this.k = new String[TowerType.values.length];
        this.l = new String[TowerType.values.length];
        this.m = new String[TowerType.values.length];
        this.n = new GameValueType[TowerType.values.length];
        this.o = new GameValueType[TowerType.values.length];
        this.p = new GameValueType[TowerType.values.length];
        this.q = new GameValueType[TowerType.values.length];
        this.r = new GameValueType[TowerType.values.length];
        this.s = new GameValueType[TowerType.values.length];
        this.t = new GameValueType[TowerType.values.length];
        this.u = new GameValueType[TowerType.values.length];
        this.v = new GameValueType[TowerType.values.length];
        this.w = new GameValueType[TowerType.values.length];
        this.x = new GameValueType[TowerType.values.length];
        this.y = new GameValueType[TowerType.values.length];
        this.F = new Factories();
        this.highlightParticles = new ParticleEffectPool[TowerType.values.length];
        for (Tower.AimStrategy aimStrategy : Tower.AimStrategy.values) {
            this.f2474b[aimStrategy.ordinal()] = "aim_strategy_" + aimStrategy.name();
        }
        this.c[Tower.AimStrategy.FIRST.ordinal()] = "icon-target-first";
        this.c[Tower.AimStrategy.LAST.ordinal()] = "icon-target-last";
        this.c[Tower.AimStrategy.STRONGEST.ordinal()] = "icon-target-strong";
        this.c[Tower.AimStrategy.WEAKEST.ordinal()] = "icon-target-weak";
        this.c[Tower.AimStrategy.NEAREST.ordinal()] = "icon-target-near";
        this.c[Tower.AimStrategy.RANDOM.ordinal()] = "icon-target-random";
        for (TowerType towerType : TowerType.values) {
            String str = this.SHORT_TOWER_ALIASES.get(towerType);
            this.e[towerType.ordinal()] = StatisticsType.valueOf("TMS_" + str);
            this.f[towerType.ordinal()] = StatisticsType.valueOf("TDD_" + str);
            this.g[towerType.ordinal()] = StatisticsType.valueOf("TB_" + str);
            this.h[towerType.ordinal()] = StatisticsType.valueOf("TS_" + str);
            this.i[towerType.ordinal()] = StatisticsType.valueOf("TU_" + str);
            this.j[towerType.ordinal()] = StatisticsType.valueOf("TEK_" + str);
            this.k[towerType.ordinal()] = "tower_name_" + towerType.name();
            this.l[towerType.ordinal()] = "tower_description_" + towerType.name();
            this.m[towerType.ordinal()] = "tower_unique_stat_description_" + towerType.name();
            this.n[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_UPGRADE_PRICE");
            this.o[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_PRICE");
            this.p[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_MAX_EXP_LEVEL");
            this.q[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_MAX_UPGRADE_LEVEL");
            this.r[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_EXPERIENCE_GENERATION");
            this.u[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_EXPERIENCE_MULTIPLIER");
            this.s[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_PPL_TILL_10");
            this.t[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_PPL_AFTER_10");
            this.v[towerType.ordinal()] = GameValueType.valueOf("TOWER_TYPE_" + towerType.name());
            this.w[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_STARTING_LEVEL");
            this.x[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_STARTING_PWR");
            this.y[towerType.ordinal()] = GameValueType.valueOf("TOWER_" + towerType.name() + "_A_POWERFUL_PWR");
        }
        Tower.Factory[] factoryArr = this.f2473a;
        int ordinal = TowerType.BASIC.ordinal();
        Factories factories = this.F;
        BasicTower.BasicTowerFactory basicTowerFactory = new BasicTower.BasicTowerFactory();
        factories.BASIC = basicTowerFactory;
        factoryArr[ordinal] = basicTowerFactory;
        Tower.Factory[] factoryArr2 = this.f2473a;
        int ordinal2 = TowerType.AIR.ordinal();
        Factories factories2 = this.F;
        AirTower.AirTowerFactory airTowerFactory = new AirTower.AirTowerFactory();
        factories2.AIR = airTowerFactory;
        factoryArr2[ordinal2] = airTowerFactory;
        Tower.Factory[] factoryArr3 = this.f2473a;
        int ordinal3 = TowerType.BLAST.ordinal();
        Factories factories3 = this.F;
        BlastTower.BlastTowerFactory blastTowerFactory = new BlastTower.BlastTowerFactory();
        factories3.BLAST = blastTowerFactory;
        factoryArr3[ordinal3] = blastTowerFactory;
        Tower.Factory[] factoryArr4 = this.f2473a;
        int ordinal4 = TowerType.CANNON.ordinal();
        Factories factories4 = this.F;
        CannonTower.CannonTowerFactory cannonTowerFactory = new CannonTower.CannonTowerFactory();
        factories4.CANNON = cannonTowerFactory;
        factoryArr4[ordinal4] = cannonTowerFactory;
        Tower.Factory[] factoryArr5 = this.f2473a;
        int ordinal5 = TowerType.FREEZING.ordinal();
        Factories factories5 = this.F;
        FreezingTower.FreezingTowerFactory freezingTowerFactory = new FreezingTower.FreezingTowerFactory();
        factories5.FREEZING = freezingTowerFactory;
        factoryArr5[ordinal5] = freezingTowerFactory;
        Tower.Factory[] factoryArr6 = this.f2473a;
        int ordinal6 = TowerType.MINIGUN.ordinal();
        Factories factories6 = this.F;
        MinigunTower.MinigunTowerFactory minigunTowerFactory = new MinigunTower.MinigunTowerFactory();
        factories6.MINIGUN = minigunTowerFactory;
        factoryArr6[ordinal6] = minigunTowerFactory;
        Tower.Factory[] factoryArr7 = this.f2473a;
        int ordinal7 = TowerType.MISSILE.ordinal();
        Factories factories7 = this.F;
        MissileTower.MissileTowerFactory missileTowerFactory = new MissileTower.MissileTowerFactory();
        factories7.MISSILE = missileTowerFactory;
        factoryArr7[ordinal7] = missileTowerFactory;
        Tower.Factory[] factoryArr8 = this.f2473a;
        int ordinal8 = TowerType.MULTISHOT.ordinal();
        Factories factories8 = this.F;
        MultishotTower.MultishotTowerFactory multishotTowerFactory = new MultishotTower.MultishotTowerFactory();
        factories8.MULTISHOT = multishotTowerFactory;
        factoryArr8[ordinal8] = multishotTowerFactory;
        Tower.Factory[] factoryArr9 = this.f2473a;
        int ordinal9 = TowerType.SNIPER.ordinal();
        Factories factories9 = this.F;
        SniperTower.SniperTowerFactory sniperTowerFactory = new SniperTower.SniperTowerFactory();
        factories9.SNIPER = sniperTowerFactory;
        factoryArr9[ordinal9] = sniperTowerFactory;
        Tower.Factory[] factoryArr10 = this.f2473a;
        int ordinal10 = TowerType.SPLASH.ordinal();
        Factories factories10 = this.F;
        SplashTower.SplashTowerFactory splashTowerFactory = new SplashTower.SplashTowerFactory();
        factories10.SPLASH = splashTowerFactory;
        factoryArr10[ordinal10] = splashTowerFactory;
        Tower.Factory[] factoryArr11 = this.f2473a;
        int ordinal11 = TowerType.TESLA.ordinal();
        Factories factories11 = this.F;
        TeslaTower.TeslaTowerFactory teslaTowerFactory = new TeslaTower.TeslaTowerFactory();
        factories11.TESLA = teslaTowerFactory;
        factoryArr11[ordinal11] = teslaTowerFactory;
        Tower.Factory[] factoryArr12 = this.f2473a;
        int ordinal12 = TowerType.VENOM.ordinal();
        Factories factories12 = this.F;
        VenomTower.VenomTowerFactory venomTowerFactory = new VenomTower.VenomTowerFactory();
        factories12.VENOM = venomTowerFactory;
        factoryArr12[ordinal12] = venomTowerFactory;
        Tower.Factory[] factoryArr13 = this.f2473a;
        int ordinal13 = TowerType.FLAMETHROWER.ordinal();
        Factories factories13 = this.F;
        FlamethrowerTower.FlamethrowerTowerFactory flamethrowerTowerFactory = new FlamethrowerTower.FlamethrowerTowerFactory();
        factories13.FLAMETHROWER = flamethrowerTowerFactory;
        factoryArr13[ordinal13] = flamethrowerTowerFactory;
        Tower.Factory[] factoryArr14 = this.f2473a;
        int ordinal14 = TowerType.LASER.ordinal();
        Factories factories14 = this.F;
        LaserTower.LaserTowerFactory laserTowerFactory = new LaserTower.LaserTowerFactory();
        factories14.LASER = laserTowerFactory;
        factoryArr14[ordinal14] = laserTowerFactory;
        Tower.Factory[] factoryArr15 = this.f2473a;
        int ordinal15 = TowerType.GAUSS.ordinal();
        Factories factories15 = this.F;
        GaussTower.GaussTowerFactory gaussTowerFactory = new GaussTower.GaussTowerFactory();
        factories15.GAUSS = gaussTowerFactory;
        factoryArr15[ordinal15] = gaussTowerFactory;
        Tower.Factory[] factoryArr16 = this.f2473a;
        int ordinal16 = TowerType.CRUSHER.ordinal();
        Factories factories16 = this.F;
        CrusherTower.CrusherTowerFactory crusherTowerFactory = new CrusherTower.CrusherTowerFactory();
        factories16.CRUSHER = crusherTowerFactory;
        factoryArr16[ordinal16] = crusherTowerFactory;
        for (TowerType towerType2 : TowerType.values) {
            if (this.f2473a[towerType2.ordinal()] == null) {
                throw new RuntimeException("Not all tower factories were created");
            }
        }
        JsonValue parse = new JsonReader().parse(Gdx.files.internal("res/tower-enemy-attack-matrix.json"));
        Array array = new Array();
        Iterator<JsonValue> iterator2 = parse.get("columns").iterator2();
        while (iterator2.hasNext()) {
            array.add(TowerType.valueOf(iterator2.next().asString()));
        }
        Iterator<JsonValue> iterator22 = parse.get("values").iterator2();
        while (iterator22.hasNext()) {
            JsonValue next = iterator22.next();
            EnemyType valueOf = EnemyType.valueOf(next.name);
            int i = 0;
            Iterator<JsonValue> iterator23 = next.iterator2();
            while (iterator23.hasNext()) {
                this.canTowerAttackEnemy[valueOf.ordinal()][((TowerType) array.get(i)).ordinal()] = iterator23.next().asBoolean();
                i++;
            }
        }
        JsonValue parse2 = new JsonReader().parse(Gdx.files.internal("res/tower-enemy-damage-matrix.json"));
        Array array2 = new Array();
        Iterator<JsonValue> iterator24 = parse2.get("columns").iterator2();
        while (iterator24.hasNext()) {
            array2.add(TowerType.valueOf(iterator24.next().asString()));
        }
        Iterator<JsonValue> iterator25 = parse2.get("values").iterator2();
        while (iterator25.hasNext()) {
            JsonValue next2 = iterator25.next();
            EnemyType valueOf2 = EnemyType.valueOf(next2.name);
            int i2 = 0;
            Iterator<JsonValue> iterator26 = next2.iterator2();
            while (iterator26.hasNext()) {
                this.towerEnemyDamageMultiplier[valueOf2.ordinal()][((TowerType) array2.get(i2)).ordinal()] = iterator26.next().asFloat();
                i2++;
            }
        }
    }

    public final float getStatBarCoeff(TowerStatType towerStatType, float f, float f2) {
        float f3;
        if (towerStatType != TowerStatType.DAMAGE) {
            f3 = f / f2;
        } else if (f2 < 1000.0f) {
            f3 = Interpolation.pow3Out.apply(f / f2);
        } else if (f2 < 3000.0f) {
            f3 = Interpolation.pow4Out.apply(f / f2);
        } else if (f2 < 10000.0f) {
            f3 = Interpolation.pow5Out.apply(f / f2);
        } else {
            f3 = Interpolation.pow5Out.apply(f / 10000.0f);
        }
        return MathUtils.clamp(f3, 0.0f, 1.0f);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x01c7, code lost:            switch(r19) {            case 0: goto L31;            case 1: goto L32;            case 2: goto L33;            default: goto L34;        };     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x01e0, code lost:            r0.rounding = 2;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x01e9, code lost:            r0.rounding = 3;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01f2, code lost:            r0.rounding = 1;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void reloadTowerStats() {
        /*
            Method dump skipped, instructions count: 868
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.TowerManager.reloadTowerStats():void");
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void setup() {
        reloadTowerStats();
        for (Tower.Factory factory : this.f2473a) {
            factory.setup();
        }
        if (Game.i.assetManager != null) {
            this.abilityAvailableParticleEffectPool = Game.i.assetManager.getParticleEffectPool("ability-available-mark.prt");
            this.upgradeParticles = Game.i.assetManager.getParticleEffectPool("upgrade.prt");
            this.lvlUpParticles = Game.i.assetManager.getParticleEffectPool("lvl-up.prt");
            for (TowerType towerType : TowerType.values) {
                ParticleEffect particleEffect = new ParticleEffect();
                particleEffect.load(Gdx.files.internal("particles/building-highlight.prt"), Game.i.assetManager.getTextureRegion("tower-basic-base").getAtlas());
                particleEffect.setEmittersCleanUpBlendFunction(false);
                Array<Sprite> array = new Array<>(Sprite.class);
                array.add(new Sprite(Game.i.assetManager.getTextureRegion("tower-" + towerType.name().toLowerCase(Locale.US) + "-shape")));
                particleEffect.getEmitters().first().setSprites(array);
                Color color = getFactory(towerType).getColor();
                float[] colors = particleEffect.getEmitters().first().getTint().getColors();
                for (int i = 0; i < colors.length; i += 3) {
                    colors[i] = color.r;
                    colors[i + 1] = color.g;
                    colors[i + 2] = color.f888b;
                }
                this.highlightParticles[towerType.ordinal()] = Game.i.assetManager.getParticleEffectPoolWithTemplate("building-highlight.prt@towerType:" + towerType.name(), particleEffect);
            }
        }
    }

    public final Tower.Factory<? extends Tower> getFactory(TowerType towerType) {
        return this.f2473a[towerType.ordinal()];
    }

    public final float getStatFromConfig(TowerType towerType, TowerStatType towerStatType, int i, int i2, GameValueProvider gameValueProvider) {
        float f;
        if (gameValueProvider == null) {
            throw new IllegalArgumentException("gvp is null");
        }
        if (i > 10) {
            throw new IllegalArgumentException("Upgrade level is " + i + ", max 10");
        }
        if (i2 > 999) {
            throw new IllegalArgumentException("Experience level is " + i2 + ", max 20");
        }
        TowerStatConfig towerStatConfig = this.d[towerType.ordinal()].c.get(towerStatType.ordinal());
        if (towerStatConfig == null) {
            throw new IllegalArgumentException(towerType.name() + " has no " + towerStatType.name());
        }
        if (towerStatConfig.unique) {
            f = towerStatConfig.values[i2 - 1];
        } else {
            f = towerStatConfig.values[i];
        }
        if (towerStatConfig.gameValueMultipliers != null) {
            f = (float) (f * gameValueProvider.getPercentValueAsMultiplierSumAll(towerStatConfig.gameValueMultipliers));
        }
        switch (towerStatConfig.rounding) {
            case 1:
                f = MathUtils.floor(f);
                break;
            case 2:
                f = MathUtils.round(f);
                break;
            case 3:
                f = MathUtils.ceil(f);
                break;
        }
        return f;
    }

    public final TowerStatType[] getStatTypes(TowerType towerType) {
        return this.d[towerType.ordinal()].d;
    }

    public final TowerStatConfig getStatConfig(TowerType towerType, TowerStatType towerStatType) {
        return this.d[towerType.ordinal()].c.get(towerStatType.ordinal());
    }

    public final boolean hasStat(TowerType towerType, TowerStatType towerStatType) {
        return this.d[towerType.ordinal()].c.containsKey(towerStatType.ordinal());
    }

    public final float clampStat(TowerType towerType, TowerStatType towerStatType, float f) {
        TowerStatConfig towerStatConfig = this.d[towerType.ordinal()].c.get(towerStatType.ordinal());
        if (towerStatConfig.minValue != Float.MIN_VALUE && f < towerStatConfig.minValue) {
            f = towerStatConfig.minValue;
        }
        if (towerStatConfig.maxValue != Float.MAX_VALUE && f > towerStatConfig.maxValue) {
            f = towerStatConfig.maxValue;
        }
        switch (towerStatConfig.rounding) {
            case 1:
                f = MathUtils.floor(f);
                break;
            case 2:
                f = MathUtils.round(f);
                break;
            case 3:
                f = MathUtils.ceil(f);
                break;
        }
        return f;
    }

    public final int getUpgradePrice(TowerType towerType, int i, GameValueProvider gameValueProvider) {
        return (int) (this.d[towerType.ordinal()].f2477a[i - 1] * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.TOWERS_UPGRADE_PRICE, getUpgradePriceGameValueType(towerType)));
    }

    public final float getUpgradePriceMultiplier(TowerType towerType) {
        return this.d[towerType.ordinal()].f2478b;
    }

    public final Tower fromJson(JsonValue jsonValue) {
        Tower create = getFactory(TowerType.valueOf(jsonValue.getString("type"))).create();
        create.loadFromJson(jsonValue);
        return create;
    }

    public final String getAimStrategyName(Tower.AimStrategy aimStrategy) {
        return Game.i.localeManager.i18n.get(this.f2474b[aimStrategy.ordinal()]);
    }

    public final String getAimStrategyIconAlias(Tower.AimStrategy aimStrategy) {
        return this.c[aimStrategy.ordinal()];
    }

    public final TextureRegion getAimStrategyIcon(Tower.AimStrategy aimStrategy) {
        return Game.i.assetManager.getTextureRegion(getAimStrategyIconAlias(aimStrategy));
    }

    public final Color getAimStrategyColor(Tower.AimStrategy aimStrategy) {
        switch (aimStrategy) {
            case FIRST:
                return MaterialColor.CYAN.P800;
            case LAST:
                return MaterialColor.BLUE.P800;
            case STRONGEST:
                return MaterialColor.DEEP_ORANGE.P800;
            case WEAKEST:
                return MaterialColor.GREEN.P800;
            case NEAREST:
                return MaterialColor.YELLOW.P900;
            case RANDOM:
                return MaterialColor.PURPLE.P700;
            default:
                return null;
        }
    }

    public final String getTitle(TowerType towerType) {
        return Game.i.localeManager.i18n.get(this.k[towerType.ordinal()]);
    }

    public final String getDescription(TowerType towerType) {
        return Game.i.localeManager.i18n.get(this.l[towerType.ordinal()]);
    }

    public final String getUniqueStatDescription(TowerType towerType) {
        return Game.i.localeManager.i18n.get(this.m[towerType.ordinal()]);
    }

    public final GameValueType getUpgradePriceGameValueType(TowerType towerType) {
        return this.n[towerType.ordinal()];
    }

    public final GameValueType getPriceGameValueType(TowerType towerType) {
        return this.o[towerType.ordinal()];
    }

    public final GameValueType getMaxExpLevelGameValueType(TowerType towerType) {
        return this.p[towerType.ordinal()];
    }

    public final GameValueType getMaxUpgradeLevelGameValueType(TowerType towerType) {
        return this.q[towerType.ordinal()];
    }

    public final GameValueType getExperienceGenerationGameValueType(TowerType towerType) {
        return this.r[towerType.ordinal()];
    }

    public final GameValueType getPplTill10GameValueType(TowerType towerType) {
        return this.s[towerType.ordinal()];
    }

    public final GameValueType getPplAfter10GameValueType(TowerType towerType) {
        return this.t[towerType.ordinal()];
    }

    public final GameValueType getExperienceMultiplierGameValueType(TowerType towerType) {
        return this.u[towerType.ordinal()];
    }

    public final GameValueType getTowerGameValueType(TowerType towerType) {
        return this.v[towerType.ordinal()];
    }

    public final GameValueType getStartingLevelGameValueType(TowerType towerType) {
        return this.w[towerType.ordinal()];
    }

    public final GameValueType getStartingPwrGameValueType(TowerType towerType) {
        return this.x[towerType.ordinal()];
    }

    public final GameValueType getPowerfulAbilityGameValueType(TowerType towerType) {
        return this.y[towerType.ordinal()];
    }

    public final StatisticsType getMoneySpentStatisticType(TowerType towerType) {
        return this.e[towerType.ordinal()];
    }

    public final StatisticsType getUpgradedStatisticType(TowerType towerType) {
        return this.i[towerType.ordinal()];
    }

    public final StatisticsType getDamageDealtStatisticType(TowerType towerType) {
        return this.f[towerType.ordinal()];
    }

    public final StatisticsType getEnemiesKilledStatisticsType(TowerType towerType) {
        return this.j[towerType.ordinal()];
    }

    public final StatisticsType getBuiltStatisticType(TowerType towerType) {
        return this.g[towerType.ordinal()];
    }

    public final StatisticsType getSoldStatisticType(TowerType towerType) {
        return this.h[towerType.ordinal()];
    }

    public final CharSequence getGeneralizedTowerStatName(GeneralizedTowerStatType generalizedTowerStatType) {
        switch (generalizedTowerStatType) {
            case RANGE:
                return Game.i.localeManager.i18n.get("generalized_tower_stat_RANGE");
            case ATTACK_SPEED:
                return Game.i.localeManager.i18n.get("generalized_tower_stat_ATTACK_SPEED");
            case DAMAGE:
                return Game.i.localeManager.i18n.get("generalized_tower_stat_DAMAGE");
            case CROWD_DAMAGE:
                return Game.i.localeManager.i18n.get("generalized_tower_stat_CROWD_DAMAGE");
            case AGILITY:
                return Game.i.localeManager.i18n.get("generalized_tower_stat_AGILITY");
            default:
                return "";
        }
    }

    public final Color getGeneralizedTowerStatColor(GeneralizedTowerStatType generalizedTowerStatType) {
        switch (generalizedTowerStatType) {
            case RANGE:
                return MaterialColor.GREEN.P500;
            case ATTACK_SPEED:
                return MaterialColor.ORANGE.P500;
            case DAMAGE:
                return MaterialColor.RED.P500;
            case CROWD_DAMAGE:
                return MaterialColor.PINK.P500;
            case AGILITY:
                return MaterialColor.PURPLE.P500;
            default:
                return Color.WHITE;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerManager$TowerStatsConfig.class */
    public static class TowerStatsConfig {

        /* renamed from: a, reason: collision with root package name */
        int[] f2477a;

        /* renamed from: b, reason: collision with root package name */
        float f2478b;
        IntMap<TowerStatConfig> c;
        TowerStatType[] d;

        private TowerStatsConfig() {
            this.f2477a = new int[10];
            this.c = new IntMap<>();
        }

        /* synthetic */ TowerStatsConfig(byte b2) {
            this();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TowerStatsConfig { ");
            stringBuilder.append("prices: ").append(Arrays.toString(this.f2477a)).append(", ");
            stringBuilder.append("upgradePriceMultiplier: ").append(this.f2478b).append(", ");
            stringBuilder.append("configs: ").append(this.c).append(", ");
            stringBuilder.append("statTypes: ").append(Arrays.toString(this.d)).append(", ");
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerManager$TowerStatConfig.class */
    public static class TowerStatConfig {
        public GameValueType[] gameValueMultipliers;
        public boolean unique;
        public float pwrFactor;
        public float[] values;
        public float minValue;
        public float maxValue;
        public int rounding = 0;
        public float pwrPowerFactor = 1.0f;

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TowerStatConfig { ");
            stringBuilder.append("gameValueMultipliers: ").append(this.gameValueMultipliers == null ? "null" : Arrays.toString(this.gameValueMultipliers)).append(", ");
            stringBuilder.append("unique: ").append(this.unique).append(", ");
            stringBuilder.append("rounding: ").append(this.rounding).append(", ");
            stringBuilder.append("pwrFactor: ").append(this.pwrFactor).append(", ");
            stringBuilder.append("pwrPowerFactor: ").append(this.pwrPowerFactor).append(", ");
            stringBuilder.append("values: ").append(Arrays.toString(this.values)).append(", ");
            stringBuilder.append("minValue: ").append(this.minValue).append(", ");
            stringBuilder.append("maxValue: ").append(this.maxValue);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }
}

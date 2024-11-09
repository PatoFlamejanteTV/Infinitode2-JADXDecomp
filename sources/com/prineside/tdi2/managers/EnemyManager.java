package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enemies.ArmoredEnemy;
import com.prineside.tdi2.enemies.BossEnemy;
import com.prineside.tdi2.enemies.FastEnemy;
import com.prineside.tdi2.enemies.FighterEnemy;
import com.prineside.tdi2.enemies.GenericEnemy;
import com.prineside.tdi2.enemies.HealerEnemy;
import com.prineside.tdi2.enemies.HeliEnemy;
import com.prineside.tdi2.enemies.IcyEnemy;
import com.prineside.tdi2.enemies.JetEnemy;
import com.prineside.tdi2.enemies.LightEnemy;
import com.prineside.tdi2.enemies.RegularEnemy;
import com.prineside.tdi2.enemies.StrongEnemy;
import com.prineside.tdi2.enemies.ToxicEnemy;
import com.prineside.tdi2.enemies.bosses.BrootEnemy;
import com.prineside.tdi2.enemies.bosses.ConstructorBossEnemy;
import com.prineside.tdi2.enemies.bosses.MetaphorBossCreepEnemy;
import com.prineside.tdi2.enemies.bosses.MetaphorBossEnemy;
import com.prineside.tdi2.enemies.bosses.MobchainBossBodyEnemy;
import com.prineside.tdi2.enemies.bosses.MobchainBossCreepEnemy;
import com.prineside.tdi2.enemies.bosses.MobchainBossHeadEnemy;
import com.prineside.tdi2.enemies.bosses.SnakeBossBodyEnemy;
import com.prineside.tdi2.enemies.bosses.SnakeBossHeadEnemy;
import com.prineside.tdi2.enemies.bosses.SnakeBossTailEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/EnemyManager.class */
public class EnemyManager extends Manager.ManagerAdapter {
    public final Factories F = new Factories();

    /* renamed from: a, reason: collision with root package name */
    private final Enemy.Factory[] f2332a = new Enemy.Factory[EnemyType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private final TextureRegion[] f2333b = new TextureRegion[2];
    private final TextureRegion[] c = new TextureRegion[DamageType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/EnemyManager$Factories.class */
    public static class Factories {
        public HeliEnemy.HeliEnemyFactory HELI;
        public ArmoredEnemy.ArmoredEnemyFactory ARMORED;
        public BossEnemy.BossEnemyFactory BOSS;
        public FastEnemy.FastEnemyFactory FAST;
        public FighterEnemy.FighterEnemyFactory FIGHTER;
        public IcyEnemy.IcyEnemyFactory ICY;
        public JetEnemy.JetEnemyFactory JET;
        public LightEnemy.LightEnemyFactory LIGHT;
        public RegularEnemy.RegularEnemyFactory REGULAR;
        public StrongEnemy.StrongEnemyFactory STRONG;
        public ToxicEnemy.ToxicEnemyFactory TOXIC;
        public HealerEnemy.HealerEnemyFactory HEALER;
        public GenericEnemy.GenericEnemyFactory GENERIC;
        public SnakeBossHeadEnemy.SnakeBossHeadEnemyFactory SNAKE_BOSS_HEAD;
        public SnakeBossBodyEnemy.SnakeBossBodyEnemyFactory SNAKE_BOSS_BODY;
        public SnakeBossTailEnemy.SnakeBossTailEnemyFactory SNAKE_BOSS_TAIL;
        public BrootEnemy.BrootEnemyFactory BROOT_BOSS;
        public ConstructorBossEnemy.ConstructorBossBodyEnemyFactory CONSTRUCTOR_BOSS;
        public MobchainBossBodyEnemy.MobchainBossBodyEnemyFactory MOBCHAIN_BOSS_BODY;
        public MobchainBossHeadEnemy.MobchainBossHeadEnemyFactory MOBCHAIN_BOSS_HEAD;
        public MobchainBossCreepEnemy.MobchainBossCreepEnemyFactory MOBCHAIN_BOSS_CREEP;
        public MetaphorBossEnemy.MetaphorBossEnemyFactory METAPHOR_BOSS;
        public MetaphorBossCreepEnemy.MetaphorBossCreepEnemyFactory METAPHOR_BOSS_CREEP;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/EnemyManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<EnemyManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public EnemyManager read() {
            return Game.i.enemyManager;
        }
    }

    public EnemyManager() {
        Enemy.Factory[] factoryArr = this.f2332a;
        int ordinal = EnemyType.HELI.ordinal();
        Factories factories = this.F;
        HeliEnemy.HeliEnemyFactory heliEnemyFactory = new HeliEnemy.HeliEnemyFactory();
        factories.HELI = heliEnemyFactory;
        factoryArr[ordinal] = heliEnemyFactory;
        Enemy.Factory[] factoryArr2 = this.f2332a;
        int ordinal2 = EnemyType.ARMORED.ordinal();
        Factories factories2 = this.F;
        ArmoredEnemy.ArmoredEnemyFactory armoredEnemyFactory = new ArmoredEnemy.ArmoredEnemyFactory();
        factories2.ARMORED = armoredEnemyFactory;
        factoryArr2[ordinal2] = armoredEnemyFactory;
        Enemy.Factory[] factoryArr3 = this.f2332a;
        int ordinal3 = EnemyType.BOSS.ordinal();
        Factories factories3 = this.F;
        BossEnemy.BossEnemyFactory bossEnemyFactory = new BossEnemy.BossEnemyFactory();
        factories3.BOSS = bossEnemyFactory;
        factoryArr3[ordinal3] = bossEnemyFactory;
        Enemy.Factory[] factoryArr4 = this.f2332a;
        int ordinal4 = EnemyType.FAST.ordinal();
        Factories factories4 = this.F;
        FastEnemy.FastEnemyFactory fastEnemyFactory = new FastEnemy.FastEnemyFactory();
        factories4.FAST = fastEnemyFactory;
        factoryArr4[ordinal4] = fastEnemyFactory;
        Enemy.Factory[] factoryArr5 = this.f2332a;
        int ordinal5 = EnemyType.FIGHTER.ordinal();
        Factories factories5 = this.F;
        FighterEnemy.FighterEnemyFactory fighterEnemyFactory = new FighterEnemy.FighterEnemyFactory();
        factories5.FIGHTER = fighterEnemyFactory;
        factoryArr5[ordinal5] = fighterEnemyFactory;
        Enemy.Factory[] factoryArr6 = this.f2332a;
        int ordinal6 = EnemyType.ICY.ordinal();
        Factories factories6 = this.F;
        IcyEnemy.IcyEnemyFactory icyEnemyFactory = new IcyEnemy.IcyEnemyFactory();
        factories6.ICY = icyEnemyFactory;
        factoryArr6[ordinal6] = icyEnemyFactory;
        Enemy.Factory[] factoryArr7 = this.f2332a;
        int ordinal7 = EnemyType.JET.ordinal();
        Factories factories7 = this.F;
        JetEnemy.JetEnemyFactory jetEnemyFactory = new JetEnemy.JetEnemyFactory();
        factories7.JET = jetEnemyFactory;
        factoryArr7[ordinal7] = jetEnemyFactory;
        Enemy.Factory[] factoryArr8 = this.f2332a;
        int ordinal8 = EnemyType.LIGHT.ordinal();
        Factories factories8 = this.F;
        LightEnemy.LightEnemyFactory lightEnemyFactory = new LightEnemy.LightEnemyFactory();
        factories8.LIGHT = lightEnemyFactory;
        factoryArr8[ordinal8] = lightEnemyFactory;
        Enemy.Factory[] factoryArr9 = this.f2332a;
        int ordinal9 = EnemyType.REGULAR.ordinal();
        Factories factories9 = this.F;
        RegularEnemy.RegularEnemyFactory regularEnemyFactory = new RegularEnemy.RegularEnemyFactory();
        factories9.REGULAR = regularEnemyFactory;
        factoryArr9[ordinal9] = regularEnemyFactory;
        Enemy.Factory[] factoryArr10 = this.f2332a;
        int ordinal10 = EnemyType.STRONG.ordinal();
        Factories factories10 = this.F;
        StrongEnemy.StrongEnemyFactory strongEnemyFactory = new StrongEnemy.StrongEnemyFactory();
        factories10.STRONG = strongEnemyFactory;
        factoryArr10[ordinal10] = strongEnemyFactory;
        Enemy.Factory[] factoryArr11 = this.f2332a;
        int ordinal11 = EnemyType.TOXIC.ordinal();
        Factories factories11 = this.F;
        ToxicEnemy.ToxicEnemyFactory toxicEnemyFactory = new ToxicEnemy.ToxicEnemyFactory();
        factories11.TOXIC = toxicEnemyFactory;
        factoryArr11[ordinal11] = toxicEnemyFactory;
        Enemy.Factory[] factoryArr12 = this.f2332a;
        int ordinal12 = EnemyType.HEALER.ordinal();
        Factories factories12 = this.F;
        HealerEnemy.HealerEnemyFactory healerEnemyFactory = new HealerEnemy.HealerEnemyFactory();
        factories12.HEALER = healerEnemyFactory;
        factoryArr12[ordinal12] = healerEnemyFactory;
        Enemy.Factory[] factoryArr13 = this.f2332a;
        int ordinal13 = EnemyType.GENERIC.ordinal();
        Factories factories13 = this.F;
        GenericEnemy.GenericEnemyFactory genericEnemyFactory = new GenericEnemy.GenericEnemyFactory();
        factories13.GENERIC = genericEnemyFactory;
        factoryArr13[ordinal13] = genericEnemyFactory;
        Enemy.Factory[] factoryArr14 = this.f2332a;
        int ordinal14 = EnemyType.SNAKE_BOSS_HEAD.ordinal();
        Factories factories14 = this.F;
        SnakeBossHeadEnemy.SnakeBossHeadEnemyFactory snakeBossHeadEnemyFactory = new SnakeBossHeadEnemy.SnakeBossHeadEnemyFactory();
        factories14.SNAKE_BOSS_HEAD = snakeBossHeadEnemyFactory;
        factoryArr14[ordinal14] = snakeBossHeadEnemyFactory;
        Enemy.Factory[] factoryArr15 = this.f2332a;
        int ordinal15 = EnemyType.SNAKE_BOSS_BODY.ordinal();
        Factories factories15 = this.F;
        SnakeBossBodyEnemy.SnakeBossBodyEnemyFactory snakeBossBodyEnemyFactory = new SnakeBossBodyEnemy.SnakeBossBodyEnemyFactory();
        factories15.SNAKE_BOSS_BODY = snakeBossBodyEnemyFactory;
        factoryArr15[ordinal15] = snakeBossBodyEnemyFactory;
        Enemy.Factory[] factoryArr16 = this.f2332a;
        int ordinal16 = EnemyType.SNAKE_BOSS_TAIL.ordinal();
        Factories factories16 = this.F;
        SnakeBossTailEnemy.SnakeBossTailEnemyFactory snakeBossTailEnemyFactory = new SnakeBossTailEnemy.SnakeBossTailEnemyFactory();
        factories16.SNAKE_BOSS_TAIL = snakeBossTailEnemyFactory;
        factoryArr16[ordinal16] = snakeBossTailEnemyFactory;
        Enemy.Factory[] factoryArr17 = this.f2332a;
        int ordinal17 = EnemyType.BROOT_BOSS.ordinal();
        Factories factories17 = this.F;
        BrootEnemy.BrootEnemyFactory brootEnemyFactory = new BrootEnemy.BrootEnemyFactory();
        factories17.BROOT_BOSS = brootEnemyFactory;
        factoryArr17[ordinal17] = brootEnemyFactory;
        Enemy.Factory[] factoryArr18 = this.f2332a;
        int ordinal18 = EnemyType.CONSTRUCTOR_BOSS.ordinal();
        Factories factories18 = this.F;
        ConstructorBossEnemy.ConstructorBossBodyEnemyFactory constructorBossBodyEnemyFactory = new ConstructorBossEnemy.ConstructorBossBodyEnemyFactory();
        factories18.CONSTRUCTOR_BOSS = constructorBossBodyEnemyFactory;
        factoryArr18[ordinal18] = constructorBossBodyEnemyFactory;
        Enemy.Factory[] factoryArr19 = this.f2332a;
        int ordinal19 = EnemyType.MOBCHAIN_BOSS_BODY.ordinal();
        Factories factories19 = this.F;
        MobchainBossBodyEnemy.MobchainBossBodyEnemyFactory mobchainBossBodyEnemyFactory = new MobchainBossBodyEnemy.MobchainBossBodyEnemyFactory();
        factories19.MOBCHAIN_BOSS_BODY = mobchainBossBodyEnemyFactory;
        factoryArr19[ordinal19] = mobchainBossBodyEnemyFactory;
        Enemy.Factory[] factoryArr20 = this.f2332a;
        int ordinal20 = EnemyType.MOBCHAIN_BOSS_HEAD.ordinal();
        Factories factories20 = this.F;
        MobchainBossHeadEnemy.MobchainBossHeadEnemyFactory mobchainBossHeadEnemyFactory = new MobchainBossHeadEnemy.MobchainBossHeadEnemyFactory();
        factories20.MOBCHAIN_BOSS_HEAD = mobchainBossHeadEnemyFactory;
        factoryArr20[ordinal20] = mobchainBossHeadEnemyFactory;
        Enemy.Factory[] factoryArr21 = this.f2332a;
        int ordinal21 = EnemyType.MOBCHAIN_BOSS_CREEP.ordinal();
        Factories factories21 = this.F;
        MobchainBossCreepEnemy.MobchainBossCreepEnemyFactory mobchainBossCreepEnemyFactory = new MobchainBossCreepEnemy.MobchainBossCreepEnemyFactory();
        factories21.MOBCHAIN_BOSS_CREEP = mobchainBossCreepEnemyFactory;
        factoryArr21[ordinal21] = mobchainBossCreepEnemyFactory;
        Enemy.Factory[] factoryArr22 = this.f2332a;
        int ordinal22 = EnemyType.METAPHOR_BOSS.ordinal();
        Factories factories22 = this.F;
        MetaphorBossEnemy.MetaphorBossEnemyFactory metaphorBossEnemyFactory = new MetaphorBossEnemy.MetaphorBossEnemyFactory();
        factories22.METAPHOR_BOSS = metaphorBossEnemyFactory;
        factoryArr22[ordinal22] = metaphorBossEnemyFactory;
        Enemy.Factory[] factoryArr23 = this.f2332a;
        int ordinal23 = EnemyType.METAPHOR_BOSS_CREEP.ordinal();
        Factories factories23 = this.F;
        MetaphorBossCreepEnemy.MetaphorBossCreepEnemyFactory metaphorBossCreepEnemyFactory = new MetaphorBossCreepEnemy.MetaphorBossCreepEnemyFactory();
        factories23.METAPHOR_BOSS_CREEP = metaphorBossCreepEnemyFactory;
        factoryArr23[ordinal23] = metaphorBossCreepEnemyFactory;
        for (EnemyType enemyType : EnemyType.values) {
            if (this.f2332a[enemyType.ordinal()] == null) {
                throw new RuntimeException("Not all enemy factories were created");
            }
        }
    }

    public EnemyType getBossEnemyType(BossType bossType) {
        switch (bossType) {
            case BROOT:
                return EnemyType.BROOT_BOSS;
            case SNAKE:
                return EnemyType.SNAKE_BOSS_HEAD;
            case METAPHOR:
                return EnemyType.METAPHOR_BOSS;
            case MOBCHAIN:
                return EnemyType.MOBCHAIN_BOSS_HEAD;
            case CONSTRUCTOR:
                return EnemyType.CONSTRUCTOR_BOSS;
            default:
                throw new IllegalArgumentException("Not implemented for " + bossType.name());
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Enemy.Factory factory : this.f2332a) {
            factory.setup();
        }
        if (Game.i.assetManager != null) {
            this.f2333b[0] = Game.i.assetManager.getTextureRegion("enemy-ice-overlay-1");
            this.f2333b[1] = Game.i.assetManager.getTextureRegion("enemy-ice-overlay-2");
            this.c[DamageType.BULLET.ordinal()] = Game.i.assetManager.getTextureRegion("icon-bullet");
            this.c[DamageType.ELECTRICITY.ordinal()] = Game.i.assetManager.getTextureRegion("icon-lightning-bolt");
            this.c[DamageType.EXPLOSION.ordinal()] = Game.i.assetManager.getTextureRegion("icon-explosion-range");
            this.c[DamageType.FIRE.ordinal()] = Game.i.assetManager.getTextureRegion("icon-flame");
            this.c[DamageType.LASER.ordinal()] = Game.i.assetManager.getTextureRegion("icon-laser");
            this.c[DamageType.POISON.ordinal()] = Game.i.assetManager.getTextureRegion("icon-poison");
        }
    }

    public TextureRegion getIceOverlayTexture(int i) {
        return this.f2333b[i];
    }

    public TextureRegion getDamageTypeIcon(DamageType damageType) {
        return this.c[damageType.ordinal()];
    }

    public Enemy.Factory<? extends Enemy> getFactory(EnemyType enemyType) {
        return this.f2332a[enemyType.ordinal()];
    }

    public boolean isEnemyTypeNewForPlayer(EnemyType enemyType, boolean z) {
        if (Config.isModdingMode()) {
            return false;
        }
        return ((z && SettingsPrefs.i().enemy.enemiesMetWith.contains(enemyType)) || ProgressPrefs.i().enemy.isEnemyMetWith(enemyType)) ? false : true;
    }

    public void markEnemyTypeAsNotNewForPlayer(EnemyType enemyType) {
        if (!ProgressPrefs.i().enemy.isEnemyMetWith(enemyType)) {
            ProgressPrefs.i().enemy.setEnemyMetWith(enemyType, true);
            ProgressPrefs.i().requireSave();
        }
        ObjectSet<EnemyType> objectSet = SettingsPrefs.i().enemy.enemiesMetWith;
        if (!objectSet.contains(enemyType)) {
            objectSet.add(enemyType);
            SettingsPrefs.i().requireSave();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}

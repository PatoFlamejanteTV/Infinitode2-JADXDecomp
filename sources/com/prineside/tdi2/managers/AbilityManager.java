package com.prineside.tdi2.managers;

import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.abilities.BallLightningAbility;
import com.prineside.tdi2.abilities.BlizzardAbility;
import com.prineside.tdi2.abilities.BulletWallAbility;
import com.prineside.tdi2.abilities.FireballAbility;
import com.prineside.tdi2.abilities.FirestormAbility;
import com.prineside.tdi2.abilities.LoicAbility;
import com.prineside.tdi2.abilities.LoopAbility;
import com.prineside.tdi2.abilities.MagnetAbility;
import com.prineside.tdi2.abilities.NukeAbility;
import com.prineside.tdi2.abilities.OverloadAbility;
import com.prineside.tdi2.abilities.SmokeBombAbility;
import com.prineside.tdi2.abilities.ThunderAbility;
import com.prineside.tdi2.abilities.WindstormAbility;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AbilityManager.class */
public class AbilityManager extends Manager.ManagerAdapter {
    public final Factories F = new Factories();

    /* renamed from: a, reason: collision with root package name */
    private final Ability.Factory[] f2270a = new Ability.Factory[AbilityType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private final GameValueType[] f2271b = new GameValueType[AbilityType.values.length];
    private final GameValueType[] c = new GameValueType[AbilityType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AbilityManager$Factories.class */
    public static class Factories {
        public FireballAbility.FireballAbilityFactory FIREBALL;
        public BlizzardAbility.BlizzardAbilityFactory BLIZZARD;
        public WindstormAbility.WindstormAbilityFactory WINDSTORM;
        public ThunderAbility.ThunderAbilityFactory THUNDER;
        public SmokeBombAbility.SmokeBombAbilityFactory SMOKE_BOMB;
        public FirestormAbility.FirestormAbilityFactory FIRESTORM;
        public MagnetAbility.MagnetAbilityFactory MAGNET;
        public BulletWallAbility.BulletWallAbilityFactory BULLET_WALL;
        public BallLightningAbility.BallLightningAbilityFactory BALL_LIGHTNING;
        public LoicAbility.LoicAbilityFactory LOIC;
        public NukeAbility.NukeAbilityFactory NUKE;
        public OverloadAbility.OverloadAbilityFactory OVERLOAD;
        public LoopAbility.RepeatAbilityFactory REPEAT;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AbilityManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<AbilityManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AbilityManager read() {
            return Game.i.abilityManager;
        }
    }

    public AbilityManager() {
        for (AbilityType abilityType : AbilityType.values) {
            this.f2271b[abilityType.ordinal()] = GameValueType.valueOf("ABILITY_" + abilityType.name() + "_MAX_PER_GAME");
            this.c[abilityType.ordinal()] = GameValueType.valueOf("ABILITY_" + abilityType.name() + "_ENERGY_COST");
        }
        Ability.Factory[] factoryArr = this.f2270a;
        int ordinal = AbilityType.FIREBALL.ordinal();
        Factories factories = this.F;
        FireballAbility.FireballAbilityFactory fireballAbilityFactory = new FireballAbility.FireballAbilityFactory(AbilityType.FIREBALL);
        factories.FIREBALL = fireballAbilityFactory;
        factoryArr[ordinal] = fireballAbilityFactory;
        Ability.Factory[] factoryArr2 = this.f2270a;
        int ordinal2 = AbilityType.BLIZZARD.ordinal();
        Factories factories2 = this.F;
        BlizzardAbility.BlizzardAbilityFactory blizzardAbilityFactory = new BlizzardAbility.BlizzardAbilityFactory(AbilityType.BLIZZARD);
        factories2.BLIZZARD = blizzardAbilityFactory;
        factoryArr2[ordinal2] = blizzardAbilityFactory;
        Ability.Factory[] factoryArr3 = this.f2270a;
        int ordinal3 = AbilityType.WINDSTORM.ordinal();
        Factories factories3 = this.F;
        WindstormAbility.WindstormAbilityFactory windstormAbilityFactory = new WindstormAbility.WindstormAbilityFactory(AbilityType.WINDSTORM);
        factories3.WINDSTORM = windstormAbilityFactory;
        factoryArr3[ordinal3] = windstormAbilityFactory;
        Ability.Factory[] factoryArr4 = this.f2270a;
        int ordinal4 = AbilityType.THUNDER.ordinal();
        Factories factories4 = this.F;
        ThunderAbility.ThunderAbilityFactory thunderAbilityFactory = new ThunderAbility.ThunderAbilityFactory(AbilityType.THUNDER);
        factories4.THUNDER = thunderAbilityFactory;
        factoryArr4[ordinal4] = thunderAbilityFactory;
        Ability.Factory[] factoryArr5 = this.f2270a;
        int ordinal5 = AbilityType.SMOKE_BOMB.ordinal();
        Factories factories5 = this.F;
        SmokeBombAbility.SmokeBombAbilityFactory smokeBombAbilityFactory = new SmokeBombAbility.SmokeBombAbilityFactory(AbilityType.SMOKE_BOMB);
        factories5.SMOKE_BOMB = smokeBombAbilityFactory;
        factoryArr5[ordinal5] = smokeBombAbilityFactory;
        Ability.Factory[] factoryArr6 = this.f2270a;
        int ordinal6 = AbilityType.FIRESTORM.ordinal();
        Factories factories6 = this.F;
        FirestormAbility.FirestormAbilityFactory firestormAbilityFactory = new FirestormAbility.FirestormAbilityFactory(AbilityType.FIRESTORM);
        factories6.FIRESTORM = firestormAbilityFactory;
        factoryArr6[ordinal6] = firestormAbilityFactory;
        Ability.Factory[] factoryArr7 = this.f2270a;
        int ordinal7 = AbilityType.MAGNET.ordinal();
        Factories factories7 = this.F;
        MagnetAbility.MagnetAbilityFactory magnetAbilityFactory = new MagnetAbility.MagnetAbilityFactory(AbilityType.MAGNET);
        factories7.MAGNET = magnetAbilityFactory;
        factoryArr7[ordinal7] = magnetAbilityFactory;
        Ability.Factory[] factoryArr8 = this.f2270a;
        int ordinal8 = AbilityType.BULLET_WALL.ordinal();
        Factories factories8 = this.F;
        BulletWallAbility.BulletWallAbilityFactory bulletWallAbilityFactory = new BulletWallAbility.BulletWallAbilityFactory(AbilityType.BULLET_WALL);
        factories8.BULLET_WALL = bulletWallAbilityFactory;
        factoryArr8[ordinal8] = bulletWallAbilityFactory;
        Ability.Factory[] factoryArr9 = this.f2270a;
        int ordinal9 = AbilityType.BALL_LIGHTNING.ordinal();
        Factories factories9 = this.F;
        BallLightningAbility.BallLightningAbilityFactory ballLightningAbilityFactory = new BallLightningAbility.BallLightningAbilityFactory(AbilityType.BALL_LIGHTNING);
        factories9.BALL_LIGHTNING = ballLightningAbilityFactory;
        factoryArr9[ordinal9] = ballLightningAbilityFactory;
        Ability.Factory[] factoryArr10 = this.f2270a;
        int ordinal10 = AbilityType.LOIC.ordinal();
        Factories factories10 = this.F;
        LoicAbility.LoicAbilityFactory loicAbilityFactory = new LoicAbility.LoicAbilityFactory(AbilityType.LOIC);
        factories10.LOIC = loicAbilityFactory;
        factoryArr10[ordinal10] = loicAbilityFactory;
        Ability.Factory[] factoryArr11 = this.f2270a;
        int ordinal11 = AbilityType.NUKE.ordinal();
        Factories factories11 = this.F;
        NukeAbility.NukeAbilityFactory nukeAbilityFactory = new NukeAbility.NukeAbilityFactory(AbilityType.NUKE);
        factories11.NUKE = nukeAbilityFactory;
        factoryArr11[ordinal11] = nukeAbilityFactory;
        Ability.Factory[] factoryArr12 = this.f2270a;
        int ordinal12 = AbilityType.OVERLOAD.ordinal();
        Factories factories12 = this.F;
        OverloadAbility.OverloadAbilityFactory overloadAbilityFactory = new OverloadAbility.OverloadAbilityFactory(AbilityType.OVERLOAD);
        factories12.OVERLOAD = overloadAbilityFactory;
        factoryArr12[ordinal12] = overloadAbilityFactory;
        Ability.Factory[] factoryArr13 = this.f2270a;
        int ordinal13 = AbilityType.LOOP.ordinal();
        Factories factories13 = this.F;
        LoopAbility.RepeatAbilityFactory repeatAbilityFactory = new LoopAbility.RepeatAbilityFactory(AbilityType.LOOP);
        factories13.REPEAT = repeatAbilityFactory;
        factoryArr13[ordinal13] = repeatAbilityFactory;
        for (AbilityType abilityType2 : AbilityType.values) {
            if (this.f2270a[abilityType2.ordinal()] == null) {
                throw new RuntimeException("Not all ability factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Ability.Factory factory : this.f2270a) {
            factory.setup();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (AbilityType abilityType : AbilityType.values) {
            Ability.Factory<? extends Ability> factory = getFactory(abilityType);
            if (factory.abilityType != abilityType) {
                throw new IllegalStateException(abilityType.name() + " factory.abilityType wrong ability type: " + String.valueOf(factory.abilityType));
            }
            if (factory.create() == null) {
                throw new IllegalStateException(abilityType.name() + " factory.create() is null");
            }
            if (factory.getColor() == null) {
                throw new IllegalStateException(abilityType.name() + " factory.getColor() is null");
            }
            if (factory.getDarkerColor() == null) {
                throw new IllegalStateException(abilityType.name() + " factory.getDarkerColor() is null");
            }
            if (factory.getTitle() == null || factory.getTitle().length() == 0) {
                throw new IllegalStateException(abilityType.name() + " factory.getTitle() is null or empty");
            }
            if (factory.getDescription(Game.i.gameValueManager.getSnapshot()) == null || factory.getDescription(Game.i.gameValueManager.getSnapshot()).length() == 0) {
                throw new IllegalStateException(abilityType.name() + " factory.getDescription() is null or empty");
            }
            if (factory.getIconDrawable() == null) {
                throw new IllegalStateException(abilityType.name() + " factory.getIconDrawable() is null");
            }
            for (int i = 0; i < 20; i++) {
                factory.getPriceInGreenPapers(i);
                for (ResourceType resourceType : ResourceType.values) {
                    factory.getPriceInResources(resourceType, i);
                }
            }
        }
    }

    public boolean isAnyAbilityOpened() {
        for (AbilityType abilityType : AbilityType.values) {
            if (Game.i.gameValueManager.getSnapshot().getIntValue(getMaxPerGameGameValueType(abilityType)) != 0) {
                return true;
            }
        }
        return false;
    }

    public Ability.Factory<? extends Ability> getFactory(AbilityType abilityType) {
        return this.f2270a[abilityType.ordinal()];
    }

    public GameValueType getMaxPerGameGameValueType(AbilityType abilityType) {
        return this.f2271b[abilityType.ordinal()];
    }

    public GameValueType getEnergyCostGameValueType(AbilityType abilityType) {
        return this.c[abilityType.ordinal()];
    }
}

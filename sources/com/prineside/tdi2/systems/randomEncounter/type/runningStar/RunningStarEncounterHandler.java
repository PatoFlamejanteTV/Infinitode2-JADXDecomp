package com.prineside.tdi2.systems.randomEncounter.type.runningStar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enemies.GenericEnemy;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.SystemsStateRestore;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.IntObjectPair;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/runningStar/RunningStarEncounterHandler.class */
public class RunningStarEncounterHandler implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3081a = TLog.forClass(RunningStarEncounterHandler.class);
    public GameSystemProvider S;
    public TowerType vulnerableToTower;

    @Null
    public GenericEnemy enemy;
    public StateRestoreListener stateRestoreListener = new StateRestoreListener(this, 0);
    public EnemyDespawnListener enemyDespawnListener = new EnemyDespawnListener(this, 0);

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private RenderSystem.Layer f3082b;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.S);
        kryo.writeObject(output, this.vulnerableToTower);
        kryo.writeClassAndObject(output, this.enemy);
        kryo.writeObject(output, this.stateRestoreListener);
        kryo.writeObject(output, this.enemyDespawnListener);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.S = (GameSystemProvider) kryo.readObject(input, GameSystemProvider.class);
        this.vulnerableToTower = (TowerType) kryo.readObject(input, TowerType.class);
        this.enemy = (GenericEnemy) kryo.readClassAndObject(input);
        this.stateRestoreListener = (StateRestoreListener) kryo.readObject(input, StateRestoreListener.class);
        this.enemyDespawnListener = (EnemyDespawnListener) kryo.readObject(input, EnemyDespawnListener.class);
    }

    private RunningStarEncounterHandler() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RunningStarEncounterHandler(GameSystemProvider gameSystemProvider) {
        Preconditions.checkNotNull(gameSystemProvider);
        this.S = gameSystemProvider;
        float towersMaxDps = (float) (gameSystemProvider.damage.getTowersMaxDps() * 0.8999999761581421d);
        double statistic = gameSystemProvider.statistics.getStatistic(StatisticsType.PT);
        float f = statistic < 1800.0d ? 1.0f + ((float) (1.0d - (statistic / 1800.0d))) : 1.0f;
        float f2 = towersMaxDps * f;
        if (gameSystemProvider.wave.wave != null) {
            float f3 = 1.0f - (gameSystemProvider.wave.wave.waveNumber * 0.005f);
            f2 *= f3 < 0.65f ? 0.65f : f3;
        }
        f3081a.d("hp: %s, hpMul: %s", Float.valueOf(f2), Float.valueOf(f));
        GenericEnemy create = Game.i.enemyManager.F.GENERIC.create();
        create.setMaxHealth(f2);
        create.setHealth(f2);
        create.canNotBeDisoriented = true;
        create.setSpeed(0.75f);
        create.baseDamage = 0;
        create.setPathfindingEnemyTypeOverride(EnemyType.REGULAR);
        this.enemy = create;
        if (Game.i.assetManager != null) {
            create.texture = Game.i.assetManager.getTextureRegion("enemy-type-boss");
        }
        Array tilesByType = gameSystemProvider.map.getMap().getTilesByType(SpawnTile.class);
        gameSystemProvider.enemy.addEnemy(create, (SpawnTile) tilesByType.get(gameSystemProvider.gameState.randomInt(tilesByType.size)), 5, null, 0.0f);
        create.setSpecialDamageVulnerability(SpecialDamageType.KILLSHOT, false);
        create.setSpecialDamageVulnerability(SpecialDamageType.INSTAKILL, false);
        ObjectPair[] objectPairArr = {new ObjectPair(TowerType.BASIC, Float.valueOf(0.6f)), new ObjectPair(TowerType.SNIPER, Float.valueOf(0.3f)), new ObjectPair(TowerType.CANNON, Float.valueOf(0.85f)), new ObjectPair(TowerType.SPLASH, Float.valueOf(1.5f)), new ObjectPair(TowerType.BLAST, Float.valueOf(2.0f)), new ObjectPair(TowerType.MULTISHOT, Float.valueOf(0.6f)), new ObjectPair(TowerType.MINIGUN, Float.valueOf(0.25f)), new ObjectPair(TowerType.VENOM, Float.valueOf(0.85f)), new ObjectPair(TowerType.TESLA, Float.valueOf(0.75f)), new ObjectPair(TowerType.MISSILE, Float.valueOf(0.5f)), new ObjectPair(TowerType.FLAMETHROWER, Float.valueOf(1.8f)), new ObjectPair(TowerType.LASER, Float.valueOf(1.2f))};
        int[] iArr = new int[TowerType.values.length];
        for (int i = 0; i < gameSystemProvider.tower.towers.size; i++) {
            Tower tower = gameSystemProvider.tower.towers.get(i);
            int ordinal = tower.type.ordinal();
            iArr[ordinal] = iArr[ordinal] + tower.moneySpentOn;
        }
        Array array = new Array(true, 1, IntObjectPair.class);
        for (int i2 = 0; i2 < 12; i2++) {
            ObjectPair objectPair = objectPairArr[i2];
            if (Game.i.towerManager.getFactory((TowerType) objectPair.first).isAvailable(gameSystemProvider.gameValue)) {
                array.add(new IntObjectPair(iArr[((TowerType) objectPair.first).ordinal()], (TowerType) objectPair.first));
            }
        }
        Threads.sortGdxArray(array, (intObjectPair, intObjectPair2) -> {
            return Integer.compare(intObjectPair.f3849a, intObjectPair2.f3849a);
        });
        if (array.size > 7) {
            array.setSize(7);
        }
        ObjectPair objectPair2 = null;
        if (array.size == 0) {
            objectPair2 = objectPairArr[gameSystemProvider.gameState.randomInt(12)];
            this.vulnerableToTower = (TowerType) objectPair2.first;
        } else {
            this.vulnerableToTower = (TowerType) ((IntObjectPair) array.get(gameSystemProvider.gameState.randomInt(array.size))).t;
            int i3 = 0;
            while (true) {
                if (i3 >= 12) {
                    break;
                }
                ObjectPair objectPair3 = objectPairArr[i3];
                if (objectPair3.first != this.vulnerableToTower) {
                    i3++;
                } else {
                    objectPair2 = objectPair3;
                    break;
                }
            }
            if (objectPair2 == null) {
                f3081a.d("impossible case: %s", this.vulnerableToTower);
                objectPair2 = new ObjectPair(this.vulnerableToTower, Float.valueOf(0.3f));
            }
        }
        create.towerDamageMultiplier = new float[TowerType.values.length];
        for (int i4 = 0; i4 < TowerType.values.length; i4++) {
            create.towerDamageMultiplier[i4] = 0.03f;
        }
        create.towerDamageMultiplier[this.vulnerableToTower.ordinal()] = ((Float) objectPair2.second).floatValue();
        create.towerDamageMultiplier[TowerType.FREEZING.ordinal()] = 1.0f;
        create.towerDamageMultiplier[TowerType.CRUSHER.ordinal()] = 0.0f;
        create.abilityVulnerability = new float[AbilityType.values.length];
        for (int i5 = 0; i5 < AbilityType.values.length; i5++) {
            create.abilityVulnerability[i5] = 0.015f;
        }
        a();
        gameSystemProvider.events.getListeners(SystemsStateRestore.class).addStateAffecting(this.stateRestoreListener).setDescription("Restores rendering layer");
        gameSystemProvider.events.getListeners(EnemyDespawn.class).addStateAffecting(this.enemyDespawnListener).setDescription("Stops the encounter when the star despawns");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.S._render != null) {
            this.f3082b = new RenderSystem.Layer(GameRenderingOrder.ENEMY_DRAW, false, (batch, f, f2, f3) -> {
                if (this.enemy != null) {
                    batch.setColor(Color.BLACK);
                    Game.i.assetManager.getQuad("randomEncounter.bubbleTowerType").draw(batch, this.enemy.drawPosition.x - 24.0f, this.enemy.drawPosition.y + 78.0f, 48.0f, 48.0f);
                    batch.setColor(Color.WHITE);
                    batch.draw(Game.i.towerManager.getFactory(this.vulnerableToTower).getIconTexture(), this.enemy.drawPosition.x - 20.0f, this.enemy.drawPosition.y + 82.0f, 40.0f, 40.0f);
                }
            });
            this.S._render.addLayerAfter(this.f3082b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(EnemyDespawn enemyDespawn) {
        if (enemyDespawn.getEnemy() == this.enemy) {
            if (this.enemy.getHealth() < 0.01f) {
                this.S.randomEncounter.selectRandomReward().giveReward(this.S, this.enemy.getPosition().x, this.enemy.getPosition().y);
                if (this.S._sound != null) {
                    this.S._sound.playStatic(StaticSoundType.SUCCESS);
                }
            } else if (this.S._sound != null) {
                this.S._sound.playStatic(StaticSoundType.FAIL);
            }
            this.S.events.getListeners(SystemsStateRestore.class).remove(this.stateRestoreListener);
            this.S.events.getListeners(EnemyDespawn.class).remove(this.enemyDespawnListener);
            if (this.f3082b != null) {
                this.S._render.removeLayer(this.f3082b);
            }
            this.enemy = null;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/runningStar/RunningStarEncounterHandler$StateRestoreListener.class */
    public static final class StateRestoreListener extends SerializableListener<RunningStarEncounterHandler> implements Listener<SystemsStateRestore> {
        /* synthetic */ StateRestoreListener(RunningStarEncounterHandler runningStarEncounterHandler, byte b2) {
            this(runningStarEncounterHandler);
        }

        private StateRestoreListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private StateRestoreListener(RunningStarEncounterHandler runningStarEncounterHandler) {
            this.f1759a = runningStarEncounterHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(SystemsStateRestore systemsStateRestore) {
            if (((RunningStarEncounterHandler) this.f1759a).enemy != null) {
                ((RunningStarEncounterHandler) this.f1759a).enemy.texture = Game.i.assetManager.getTextureRegion("enemy-type-boss");
                ((RunningStarEncounterHandler) this.f1759a).a();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/runningStar/RunningStarEncounterHandler$EnemyDespawnListener.class */
    public static final class EnemyDespawnListener extends SerializableListener<RunningStarEncounterHandler> implements Listener<EnemyDespawn> {
        /* synthetic */ EnemyDespawnListener(RunningStarEncounterHandler runningStarEncounterHandler, byte b2) {
            this(runningStarEncounterHandler);
        }

        private EnemyDespawnListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private EnemyDespawnListener(RunningStarEncounterHandler runningStarEncounterHandler) {
            this.f1759a = runningStarEncounterHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDespawn enemyDespawn) {
            ((RunningStarEncounterHandler) this.f1759a).a(enemyDespawn);
        }
    }
}

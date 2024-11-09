package com.prineside.tdi2.systems.randomEncounter.type.starfall;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enemies.GenericEnemy;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.GameStateTick;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.events.game.SystemsStateRestore;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.ui.components.BossHpBar;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/starfall/StarfallEncounterHandler.class */
public class StarfallEncounterHandler implements KryoSerializable, Listener<GameStateTick> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3083a = TLog.forClass(StarfallEncounterHandler.class);
    public GameSystemProvider S;
    public int totalStarCount;
    public int passedStarCount;

    @NAGS
    @Null
    public BossHpBar bossHpBar;
    public DelayedRemovalArray<Star> starsToSpawn = new DelayedRemovalArray<>(true, 1, Star.class);
    public DelayedRemovalArray<Star> stars = new DelayedRemovalArray<>(true, 1, Star.class);
    public RenderListener renderListener = new RenderListener(this, 0);
    public StateRestoreListener stateRestoreListener = new StateRestoreListener(this, 0);
    public EnemyDespawnListener enemyDespawnListener = new EnemyDespawnListener(this, 0);
    public Vector2 lastDespawnedStarPosition = new Vector2();

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.S);
        kryo.writeObject(output, this.starsToSpawn);
        kryo.writeObject(output, this.stars);
        output.writeVarInt(this.totalStarCount, true);
        output.writeVarInt(this.passedStarCount, true);
        kryo.writeObject(output, this.renderListener);
        kryo.writeObject(output, this.stateRestoreListener);
        kryo.writeObject(output, this.enemyDespawnListener);
        kryo.writeObject(output, this.lastDespawnedStarPosition);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.S = (GameSystemProvider) kryo.readObject(input, GameSystemProvider.class);
        this.starsToSpawn = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.stars = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.totalStarCount = input.readVarInt(true);
        this.passedStarCount = input.readVarInt(true);
        this.renderListener = (RenderListener) kryo.readObject(input, RenderListener.class);
        this.stateRestoreListener = (StateRestoreListener) kryo.readObject(input, StateRestoreListener.class);
        this.enemyDespawnListener = (EnemyDespawnListener) kryo.readObject(input, EnemyDespawnListener.class);
        this.lastDespawnedStarPosition = (Vector2) kryo.readObject(input, Vector2.class);
    }

    private StarfallEncounterHandler() {
    }

    public StarfallEncounterHandler(GameSystemProvider gameSystemProvider) {
        Preconditions.checkNotNull(gameSystemProvider);
        this.S = gameSystemProvider;
        Map map = gameSystemProvider.map.getMap();
        float height = (map.getHeight() << 7) + (map.getWidth() << 7);
        int i = gameSystemProvider.wave.wave != null ? 12 + (gameSystemProvider.wave.wave.waveNumber / 5) : 12;
        i = i > 50 ? 50 : i;
        this.totalStarCount = i;
        this.passedStarCount = 0;
        float towersMaxDps = ((float) (gameSystemProvider.damage.getTowersMaxDps() * (1.0f / ((float) Math.pow(i, 1.2d))) * 8.5f)) * 0.25f;
        Array array = new Array(true, 1, Vector2.class);
        for (int i2 = 0; i2 < gameSystemProvider.tower.towers.size; i2++) {
            Tower tower = gameSystemProvider.tower.towers.get(i2);
            array.add(new Vector2(tower.getTile().center).add((gameSystemProvider.gameState.randomFloat() * 128.0f) - 64.0f, 0.0f));
            array.add(new Vector2(tower.getTile().center).add((gameSystemProvider.gameState.randomFloat() * 128.0f) - 64.0f, 0.0f));
        }
        while (array.size < i) {
            array.add(new Vector2((map.getWidth() * 0.5f * 128.0f) + (map.getWidth() * 0.4f * 128.0f * ((gameSystemProvider.gameState.randomFloat() * 2.0f) - 1.0f)), map.getHeight() * 0.5f * 128.0f));
        }
        for (int i3 = 0; i3 < array.size; i3++) {
            array.swap(i3, gameSystemProvider.gameState.randomInt(array.size));
        }
        Vector2 rotateDeg = new Vector2(1.0f, 0.0f).rotateDeg(210.0f + (gameSystemProvider.gameState.randomFloat() * 120.0f));
        for (int i4 = 0; i4 < i; i4++) {
            GenericEnemy create = Game.i.enemyManager.F.GENERIC.create();
            create.setMaxHealth(towersMaxDps);
            create.setHealth(towersMaxDps);
            create.velocity = new Vector2();
            create.doesNotDisableTowers = true;
            create.canNotBeDisoriented = true;
            Star star = new Star(create);
            star.flyVector = rotateDeg;
            star.enemy.setSpeed(0.75f + (gameSystemProvider.gameState.randomFloat() * 0.1f));
            create.setUserData("RandomEncounterStarfallStar", star);
            create.setUserData("RandomEncounterInstance", this);
            Vector2 vector2 = (Vector2) array.get(i4);
            star.flyMaxDistance = height + (height * 0.1f * ((gameSystemProvider.gameState.randomFloat() * 2.0f) - 1.0f));
            star.spawnPos.set(rotateDeg).scl(-1.0f).scl(star.flyMaxDistance * 0.5f).add(vector2);
            this.starsToSpawn.add(star);
        }
        a();
        gameSystemProvider.events.getListeners(GameStateTick.class).addStateAffecting(this).setDescription("Updates stars");
        gameSystemProvider.events.getListeners(Render.class).addStateAffecting(this.renderListener).setDescription("Updates boss HP bar");
        gameSystemProvider.events.getListeners(SystemsStateRestore.class).addStateAffecting(this.stateRestoreListener).setDescription("Sets enemy textures");
        gameSystemProvider.events.getListeners(EnemyDespawn.class).addStateAffecting(this.enemyDespawnListener).setDescription("Remembers the last position of the killed stars");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (Game.i.assetManager != null) {
            ResourcePack.AtlasTextureRegion textureRegion = Game.i.assetManager.getTextureRegion("enemy-type-boss");
            for (int i = 0; i < this.starsToSpawn.size; i++) {
                this.starsToSpawn.get(i).enemy.texture = textureRegion;
            }
            for (int i2 = 0; i2 < this.stars.size; i2++) {
                this.stars.get(i2).enemy.texture = textureRegion;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.S.CFG.headless) {
            return;
        }
        if (this.bossHpBar == null && this.S._gameUi != null) {
            this.bossHpBar = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("random_encounter_starfall_boss_hp_line_name")).setMainBarColor(new Color(-176220161).mul(0.3f, 0.3f, 0.3f, 1.0f), new Color(-36162049)).setLabelsColor(new Color(-36162049)).setIcon(Game.i.assetManager.getDrawable("enemy-type-boss"));
            this.S._gameUi.mainUi.addBossHpBar(this.bossHpBar);
        }
        if (this.bossHpBar != null) {
            if (this.passedStarCount != 0) {
                this.bossHpBar.setMainBarColor(MaterialColor.RED.P900, MaterialColor.RED.P500);
            }
            this.bossHpBar.setMainHP(this.stars.size + this.passedStarCount, this.totalStarCount);
        }
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(GameStateTick gameStateTick) {
        if (this.starsToSpawn.size != 0) {
            Star removeIndex = this.starsToSpawn.removeIndex(0);
            GenericEnemy genericEnemy = removeIndex.enemy;
            this.S.enemy.addStaticEnemy(genericEnemy, removeIndex.spawnPos.x, removeIndex.spawnPos.y);
            genericEnemy.towerDamageMultiplier = new float[TowerType.values.length];
            for (int i = 0; i < TowerType.values.length; i++) {
                genericEnemy.towerDamageMultiplier[i] = 0.25f;
            }
            genericEnemy.abilityVulnerability = new float[AbilityType.values.length];
            for (int i2 = 0; i2 < AbilityType.values.length; i2++) {
                genericEnemy.abilityVulnerability[i2] = 0.1f;
            }
            genericEnemy.setBuffVulnerability(BuffType.BURN, 0.25f);
            genericEnemy.setBuffVulnerability(BuffType.POISON, 0.25f);
            this.stars.add(removeIndex);
        }
        this.stars.begin();
        for (int i3 = 0; i3 < this.stars.size; i3++) {
            Star star = this.stars.get(i3);
            if (star.enemy.isRegistered()) {
                star.enemy.velocity.set(star.flyVector.x, star.flyVector.y);
                if (star.spawnPos.dst2(star.enemy.getPosition()) > star.flyMaxDistance * star.flyMaxDistance) {
                    if (this.S._particle != null) {
                        this.S._particle.addShatterParticle(Game.i.assetManager.getTextureRegion("enemy-type-boss"), star.enemy.drawPosition.x, star.enemy.drawPosition.y, star.enemy.getSize(), star.enemy.angle, 1.0f, Color.WHITE, null, false);
                    }
                    this.S.map.despawnEnemy(star.enemy);
                    this.stars.removeIndex(i3);
                    this.passedStarCount++;
                } else {
                    if (this.S._projectileTrail != null && star.trail == null) {
                        star.trail = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
                        star.trail.setup(MaterialColor.YELLOW.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), 30.0f, 1.5f, 0.5f);
                        star.trail.setStartPoint(star.enemy.getPosition().x, star.enemy.getPosition().y);
                        this.S._projectileTrail.addTrail(star.trail);
                    }
                    if (star.trail != null) {
                        star.trail.setHeadPosition(star.enemy.getPosition().x, star.enemy.getPosition().y);
                    }
                }
            } else {
                this.stars.removeIndex(i3);
            }
        }
        this.stars.end();
        if (this.stars.size == 0) {
            f3083a.d("end, passed stars: %s", Integer.valueOf(this.passedStarCount));
            if (this.passedStarCount == 0) {
                this.S.randomEncounter.selectRandomReward().giveReward(this.S, this.lastDespawnedStarPosition.x, this.lastDespawnedStarPosition.y);
                if (this.S._sound != null) {
                    this.S._sound.playStatic(StaticSoundType.SUCCESS);
                }
            } else if (this.S._sound != null) {
                this.S._sound.playStatic(StaticSoundType.FAIL);
            }
            this.S.events.getListeners(GameStateTick.class).remove(this);
            this.S.events.getListeners(Render.class).remove(this.renderListener);
            this.S.events.getListeners(SystemsStateRestore.class).remove(this.stateRestoreListener);
            this.S.events.getListeners(EnemyDespawn.class).remove(this.enemyDespawnListener);
            if (this.bossHpBar != null) {
                this.S._gameUi.mainUi.removeBossHpBar(this.bossHpBar);
                this.bossHpBar = null;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/starfall/StarfallEncounterHandler$Star.class */
    public static class Star implements KryoSerializable {
        public GenericEnemy enemy;
        public Vector2 spawnPos = new Vector2();
        public Vector2 flyVector;
        public float flyMaxDistance;

        @NAGS
        public TrailMultiLine trail;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.enemy);
            kryo.writeObject(output, this.spawnPos);
            output.writeFloat(this.flyMaxDistance);
            kryo.writeObject(output, this.flyVector);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.enemy = (GenericEnemy) kryo.readObject(input, GenericEnemy.class);
            this.spawnPos = (Vector2) kryo.readObject(input, Vector2.class);
            this.flyMaxDistance = input.readFloat();
            this.flyVector = (Vector2) kryo.readObject(input, Vector2.class);
        }

        private Star() {
        }

        public Star(GenericEnemy genericEnemy) {
            Preconditions.checkNotNull(genericEnemy);
            this.enemy = genericEnemy;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/starfall/StarfallEncounterHandler$RenderListener.class */
    public static final class RenderListener extends SerializableListener<StarfallEncounterHandler> implements Listener<Render> {
        /* synthetic */ RenderListener(StarfallEncounterHandler starfallEncounterHandler, byte b2) {
            this(starfallEncounterHandler);
        }

        private RenderListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private RenderListener(StarfallEncounterHandler starfallEncounterHandler) {
            this.f1759a = starfallEncounterHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(Render render) {
            ((StarfallEncounterHandler) this.f1759a).b();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/starfall/StarfallEncounterHandler$StateRestoreListener.class */
    public static final class StateRestoreListener extends SerializableListener<StarfallEncounterHandler> implements Listener<SystemsStateRestore> {
        /* synthetic */ StateRestoreListener(StarfallEncounterHandler starfallEncounterHandler, byte b2) {
            this(starfallEncounterHandler);
        }

        private StateRestoreListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private StateRestoreListener(StarfallEncounterHandler starfallEncounterHandler) {
            this.f1759a = starfallEncounterHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(SystemsStateRestore systemsStateRestore) {
            ((StarfallEncounterHandler) this.f1759a).a();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/starfall/StarfallEncounterHandler$EnemyDespawnListener.class */
    public static final class EnemyDespawnListener extends SerializableListener<StarfallEncounterHandler> implements Listener<EnemyDespawn> {
        /* synthetic */ EnemyDespawnListener(StarfallEncounterHandler starfallEncounterHandler, byte b2) {
            this(starfallEncounterHandler);
        }

        private EnemyDespawnListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private EnemyDespawnListener(StarfallEncounterHandler starfallEncounterHandler) {
            this.f1759a = starfallEncounterHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDespawn enemyDespawn) {
            Enemy enemy = enemyDespawn.getEnemy();
            Object userData = enemy.getUserData("RandomEncounterInstance");
            if ((userData instanceof StarfallEncounterHandler) && ((StarfallEncounterHandler) userData) == this.f1759a) {
                ((StarfallEncounterHandler) this.f1759a).lastDespawnedStarPosition.set(enemy.getPosition());
            }
        }
    }
}

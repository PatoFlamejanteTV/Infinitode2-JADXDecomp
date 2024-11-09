package com.prineside.tdi2.waves.processors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.enemies.bosses.ConstructorBossEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.ui.components.BossHpBar;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/ConstructorBossWaveProcessor.class */
public class ConstructorBossWaveProcessor implements KryoSerializable, WaveProcessor, Listener<EnemySpawn> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3981a = TLog.forClass(ConstructorBossWaveProcessor.class);

    /* renamed from: b, reason: collision with root package name */
    private Wave f3982b;
    private Enemy.EnemyReference c;
    private boolean d;
    private Array<EnemyType> e;
    private Array<EnemyType> f;
    private GameSystemProvider g;

    @NAGS
    private BossHpBar h;

    @NAGS
    private Drawable i;

    /* synthetic */ ConstructorBossWaveProcessor(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f3982b, Wave.class);
        kryo.writeObjectOrNull(output, this.c, Enemy.EnemyReference.class);
        output.writeBoolean(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f3982b = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.c = (Enemy.EnemyReference) kryo.readObjectOrNull(input, Enemy.EnemyReference.class);
        this.d = input.readBoolean();
        this.e = (Array) kryo.readObject(input, Array.class);
        this.f = (Array) kryo.readObject(input, Array.class);
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    private ConstructorBossWaveProcessor() {
        this.d = false;
        this.e = new Array<>(EnemyType.class);
        this.f = new Array<>(EnemyType.class);
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Array<EnemyGroup> generateEnemyGroups(int i, int i2) {
        float calculateDefaultBossWaveCoinsSum = Wave.calculateDefaultBossWaveCoinsSum(i);
        float calculateDefaultBossWaveExpSum = Wave.calculateDefaultBossWaveExpSum(i);
        float calculateDefaultBossWaveScoreSum = Wave.calculateDefaultBossWaveScoreSum(i);
        Array<EnemyGroup> array = new Array<>();
        array.add(new EnemyGroup(EnemyType.CONSTRUCTOR_BOSS, 0.3003f, (100.0f + (((float) StrictMath.pow(WaveSystem.getWaveValue(i, i2) * 10.0d, 1.275d)) * 1.35f)) * 3.3f, 1, 0.0f, 0.0f, calculateDefaultBossWaveCoinsSum * 0.75f, calculateDefaultBossWaveExpSum * 0.75f, (int) (calculateDefaultBossWaveScoreSum * 0.75f)));
        return array;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Wave setup(GameSystemProvider gameSystemProvider, int i, int i2) {
        this.g = gameSystemProvider;
        this.f3982b = new Wave(i, i2, generateEnemyGroups(i, i2));
        this.f3982b.enemiesCanBeSplitBetweenSpawns = false;
        this.f3982b.enemiesCanHaveRandomSideShifts = false;
        this.f3982b.waveMessage = Config.isHeadless() ? null : Game.i.localeManager.i18n.get("enemy_name_CONSTRUCTOR_BOSS");
        this.f3982b.waveProcessor = this;
        gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Handles boss spawn");
        return this.f3982b;
    }

    private void a(ConstructorBossEnemy constructorBossEnemy) {
        this.c = this.g.enemy.getReference(constructorBossEnemy);
        constructorBossEnemy.processor = this;
        this.e.clear();
        Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = constructorBossEnemy.spawnTile.getAllowedEnemies();
        for (int i = 0; i < allowedEnemies.size; i++) {
            this.e.add(allowedEnemies.items[i].enemyType);
        }
        for (int i2 = this.e.size - 1; i2 >= 0; i2--) {
            EnemyType enemyType = this.e.items[i2];
            if (enemyType == EnemyType.ARMORED || enemyType == EnemyType.HEALER || EnemyType.isBoss(enemyType)) {
                this.e.removeIndex(i2);
            }
        }
        if (this.e.size == 0) {
            f3981a.i("no normal enemy types allowed, fallback", new Object[0]);
            this.e.add(EnemyType.REGULAR);
            this.e.add(EnemyType.FAST);
            this.e.add(EnemyType.STRONG);
        }
        if (constructorBossEnemy.spawnTile.getAllowedEnemiesSet().contains(EnemyType.HEALER)) {
            this.f.add(EnemyType.HEALER);
        }
        if (constructorBossEnemy.spawnTile.getAllowedEnemiesSet().contains(EnemyType.ARMORED)) {
            this.f.add(EnemyType.ARMORED);
        }
        if (this.f.size == 0) {
            f3981a.i("no aura enemy types allowed, fallback", new Object[0]);
            this.f.addAll(this.e);
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public float getNextWaveDelayMultiplier() {
        return 7.0f;
    }

    private Drawable a() {
        if (this.i == null) {
            this.i = Game.i.assetManager.getDrawable("buff-health-bar-icon-armor");
        }
        return this.i;
    }

    private void b() {
        if (this.c == null) {
            return;
        }
        if (this.h == null && this.g._gameUi != null) {
            this.h = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("enemy_name_CONSTRUCTOR_BOSS")).setMainBarColor(new Color(321401855), new Color(1618840575)).setSmallBarsCount(2).setFirstSmallBarColor(new Color(590230527), new Color(1618840575)).setSecondSmallBarColor(new Color(806110975), new Color(1780194047)).setLabelsColor(new Color(-1868255489)).addMark(0.75f).addMark(0.5f).addMark(0.25f).setIcon(Game.i.assetManager.getDrawable("boss-wave-icon-CONSTRUCTOR"));
            this.g._gameUi.mainUi.addBossHpBar(this.h);
        }
        if (this.h != null) {
            ConstructorBossEnemy constructorBossEnemy = (ConstructorBossEnemy) this.c.enemy;
            this.h.setMainHP(constructorBossEnemy.getHealth(), constructorBossEnemy.maxHealth);
            float invulnerabilityProgress = getInvulnerabilityProgress();
            if (invulnerabilityProgress > 0.0f) {
                if (!this.h.isEffectIconExists(a())) {
                    this.h.addEffectIcon(a());
                }
                this.h.setSmallBarOneProgress(invulnerabilityProgress);
                this.h.setSmallBarTwoProgress(0.0d);
            } else {
                this.h.clearEffectIcons();
                this.h.setSmallBarOneProgress(0.0d);
                this.h.setSmallBarTwoProgress(constructorBossEnemy.timeSinceCreepSpawn / 10.0f);
            }
            int i = 0;
            if (!constructorBossEnemy.groupSpawned75hp) {
                i = 0 + 1;
            }
            if (!constructorBossEnemy.groupSpawned50hp) {
                i++;
            }
            if (!constructorBossEnemy.groupSpawned25hp) {
                i++;
            }
            if (this.h.marksGroup.getChildren().size != i) {
                this.h.clearMarks();
                if (!constructorBossEnemy.groupSpawned75hp) {
                    this.h.addMark(0.75f);
                }
                if (!constructorBossEnemy.groupSpawned50hp) {
                    this.h.addMark(0.5f);
                }
                if (!constructorBossEnemy.groupSpawned25hp) {
                    this.h.addMark(0.25f);
                }
            }
        }
    }

    public float getInvulnerabilityProgress() {
        ConstructorBossEnemy constructorBossEnemy = (ConstructorBossEnemy) this.c.enemy;
        if (constructorBossEnemy == null || !constructorBossEnemy.invulnerable) {
            return 0.0f;
        }
        return 1.0f - ((((constructorBossEnemy.spawnDelayBeforeTime + constructorBossEnemy.spawnDelayAfterTime) + ((constructorBossEnemy.enemiesToSpawnStartCount - constructorBossEnemy.enemiesToSpawn.size) * 0.75f)) + constructorBossEnemy.spawningTime) / (1.2f + (0.75f * constructorBossEnemy.enemiesToSpawnStartCount)));
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void draw(Batch batch, float f) {
        b();
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void update(float f) {
        if (this.d || this.c == null) {
            return;
        }
        if (this.c.enemy == null) {
            this.d = true;
            c();
            return;
        }
        ConstructorBossEnemy constructorBossEnemy = (ConstructorBossEnemy) this.c.enemy;
        float health = constructorBossEnemy.getHealth() / constructorBossEnemy.maxHealth;
        if (health <= 0.75f && !constructorBossEnemy.groupSpawned75hp) {
            for (int i = 0; i < 3; i++) {
                Enemy obtain = Game.i.enemyManager.getFactory(this.e.items[this.g.gameState.randomInt(this.e.size)]).obtain();
                obtain.setSpeed(0.6f);
                obtain.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f;
                obtain.setHealth(obtain.maxHealth);
                obtain.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain);
            }
            constructorBossEnemy.groupSpawned75hp = true;
        }
        if (health <= 0.5f && !constructorBossEnemy.groupSpawned50hp) {
            for (int i2 = 0; i2 < 4; i2++) {
                Enemy obtain2 = Game.i.enemyManager.getFactory(this.e.items[this.g.gameState.randomInt(this.e.size)]).obtain();
                obtain2.setSpeed(0.6f + (i2 * 0.05f));
                obtain2.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f;
                obtain2.setHealth(obtain2.maxHealth);
                obtain2.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain2.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain2.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain2);
            }
            for (int i3 = 0; i3 <= 0; i3++) {
                Enemy obtain3 = Game.i.enemyManager.getFactory(this.f.items[this.g.gameState.randomInt(this.f.size)]).obtain();
                obtain3.setSpeed(0.65f);
                obtain3.maxHealth = constructorBossEnemy.maxHealth * 0.021000002f;
                obtain3.setHealth(obtain3.maxHealth);
                obtain3.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain3.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain3.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain3);
            }
            constructorBossEnemy.groupSpawned50hp = true;
        }
        if (health <= 0.25f && !constructorBossEnemy.groupSpawned25hp) {
            for (int i4 = 0; i4 < 5; i4++) {
                Enemy obtain4 = Game.i.enemyManager.getFactory(this.e.items[this.g.gameState.randomInt(this.e.size)]).obtain();
                obtain4.setSpeed(0.6f + (i4 * 0.05f));
                obtain4.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f;
                obtain4.setHealth(obtain4.maxHealth);
                obtain4.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain4.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain4.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain4);
            }
            for (int i5 = 0; i5 < 2; i5++) {
                Enemy obtain5 = Game.i.enemyManager.getFactory(this.f.items[this.g.gameState.randomInt(this.f.size)]).obtain();
                obtain5.setSpeed(0.65f);
                obtain5.maxHealth = constructorBossEnemy.maxHealth * 0.021000002f;
                obtain5.setHealth(obtain5.maxHealth);
                obtain5.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain5.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain5.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain5);
            }
            constructorBossEnemy.groupSpawned25hp = true;
        }
        if (constructorBossEnemy.enemiesToSpawn.size != 0) {
            constructorBossEnemy.enemiesToSpawnStartCount = Math.max(constructorBossEnemy.enemiesToSpawn.size, constructorBossEnemy.enemiesToSpawnStartCount);
            constructorBossEnemy.spawnDelayAfterTime = 0.0f;
            constructorBossEnemy.invulnerable = true;
            constructorBossEnemy.changeSpeedTo(0.0f, f);
            if (constructorBossEnemy.spawnDelayBeforeTime < 0.2f) {
                constructorBossEnemy.spawnDelayBeforeTime += f;
                return;
            }
            constructorBossEnemy.spawningTime += f;
            if (constructorBossEnemy.spawningTime >= 0.75f) {
                Enemy pop = constructorBossEnemy.enemiesToSpawn.pop();
                pop.ignorePathfinding = true;
                this.g.enemy.addEnemyWithPath(pop, constructorBossEnemy.spawnTile, constructorBossEnemy.graphPath, 5, constructorBossEnemy.wave, constructorBossEnemy.passedTiles);
                constructorBossEnemy.timeSinceCreepSpawn = 0.0f;
                constructorBossEnemy.spawningTime = 0.0f;
                return;
            }
            return;
        }
        if (constructorBossEnemy.spawnDelayAfterTime < 1.0f) {
            constructorBossEnemy.spawnDelayAfterTime += f;
            return;
        }
        constructorBossEnemy.spawnDelayBeforeTime = 0.0f;
        constructorBossEnemy.enemiesToSpawnStartCount = 0;
        constructorBossEnemy.invulnerable = false;
        constructorBossEnemy.changeSpeedTo(0.3003f, f);
        if (constructorBossEnemy.passedTiles < constructorBossEnemy.graphPath.getLengthInTiles() * 0.5f) {
            constructorBossEnemy.timeSinceCreepSpawn += f;
            if (constructorBossEnemy.timeSinceCreepSpawn >= 10.0f) {
                Enemy obtain6 = Game.i.enemyManager.getFactory(EnemyType.REGULAR).obtain();
                obtain6.setSpeed(0.8f);
                obtain6.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f;
                obtain6.setHealth(obtain6.maxHealth);
                obtain6.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain6.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain6.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain6);
                Enemy obtain7 = Game.i.enemyManager.getFactory(EnemyType.FAST).obtain();
                obtain7.setSpeed(0.92f);
                obtain7.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f * 0.85f;
                obtain7.setHealth(obtain7.maxHealth);
                obtain7.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain7.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain7.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain7);
                Enemy obtain8 = Game.i.enemyManager.getFactory(EnemyType.STRONG).obtain();
                obtain8.setSpeed(0.64000005f);
                obtain8.maxHealth = constructorBossEnemy.maxHealth * 0.015000001f * 1.15f;
                obtain8.setHealth(obtain8.maxHealth);
                obtain8.killScore = StrictMath.round(constructorBossEnemy.killScore * 0.027777778f);
                obtain8.bounty = StrictMath.round(constructorBossEnemy.bounty * 0.027777778f);
                obtain8.setKillExp(constructorBossEnemy.getKillExp() * 0.027777778f);
                constructorBossEnemy.enemiesToSpawn.add(obtain8);
                constructorBossEnemy.spawningTime = 0.0f;
            }
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public boolean isDone() {
        return this.d;
    }

    private void c() {
        this.g.events.getListeners(EnemySpawn.class).remove(this);
        if (this.h != null) {
            this.g._gameUi.mainUi.removeBossHpBar(this.h);
            this.h = null;
        }
        this.g = null;
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (enemy.wave == this.f3982b && enemy.type == EnemyType.CONSTRUCTOR_BOSS && this.c == null) {
            a((ConstructorBossEnemy) enemy);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/ConstructorBossWaveProcessor$ConstructorBossWaveProcessorFactory.class */
    public static class ConstructorBossWaveProcessorFactory extends WaveProcessor.WaveProcessorFactory<ConstructorBossWaveProcessor> {
        public ConstructorBossWaveProcessorFactory() {
            super(BossType.CONSTRUCTOR);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.WaveProcessor.WaveProcessorFactory
        public ConstructorBossWaveProcessor create() {
            return new ConstructorBossWaveProcessor((byte) 0);
        }
    }
}

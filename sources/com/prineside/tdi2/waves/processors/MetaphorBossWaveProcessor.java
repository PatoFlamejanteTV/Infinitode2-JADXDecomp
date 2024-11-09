package com.prineside.tdi2.waves.processors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.enemies.bosses.MetaphorBossCreepEnemy;
import com.prineside.tdi2.enemies.bosses.MetaphorBossEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.components.BossHpBar;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MetaphorBossWaveProcessor.class */
public class MetaphorBossWaveProcessor implements KryoSerializable, WaveProcessor {

    /* renamed from: a, reason: collision with root package name */
    private Wave f3983a;

    /* renamed from: b, reason: collision with root package name */
    private Enemy.EnemyReference f3984b;
    private Array<Enemy.EnemyReference> c;
    private boolean d;
    private Array<Tower> e;
    private Array<Tower> f;
    private GameSystemProvider g;
    private int h;

    @NAGS
    private BossHpBar i;
    private OnEnemySpawn j;
    private OnEnemyDespawn k;
    private OnEnemyDie l;

    /* synthetic */ MetaphorBossWaveProcessor(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f3983a, Wave.class);
        kryo.writeObjectOrNull(output, this.f3984b, Enemy.EnemyReference.class);
        kryo.writeObject(output, this.c);
        output.writeBoolean(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
        output.writeVarInt(this.h, true);
        kryo.writeObjectOrNull(output, this.j, OnEnemySpawn.class);
        kryo.writeObjectOrNull(output, this.k, OnEnemyDespawn.class);
        kryo.writeObjectOrNull(output, this.l, OnEnemyDie.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f3983a = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.f3984b = (Enemy.EnemyReference) kryo.readObjectOrNull(input, Enemy.EnemyReference.class);
        this.c = (Array) kryo.readObject(input, Array.class);
        this.d = input.readBoolean();
        this.e = (Array) kryo.readObject(input, Array.class);
        this.f = (Array) kryo.readObject(input, Array.class);
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.h = input.readVarInt(true);
        this.j = (OnEnemySpawn) kryo.readObjectOrNull(input, OnEnemySpawn.class);
        this.k = (OnEnemyDespawn) kryo.readObjectOrNull(input, OnEnemyDespawn.class);
        this.l = (OnEnemyDie) kryo.readObjectOrNull(input, OnEnemyDie.class);
    }

    private MetaphorBossWaveProcessor() {
        this.c = new Array<>(true, 8, Enemy.EnemyReference.class);
        this.d = false;
        this.e = new Array<>(Tower.class);
        this.f = new Array<>(Tower.class);
        this.h = 0;
        this.j = new OnEnemySpawn(this, (byte) 0);
        this.k = new OnEnemyDespawn(this, (byte) 0);
        this.l = new OnEnemyDie(this);
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Array<EnemyGroup> generateEnemyGroups(int i, int i2) {
        float calculateDefaultBossWaveCoinsSum = Wave.calculateDefaultBossWaveCoinsSum(i);
        float calculateDefaultBossWaveExpSum = Wave.calculateDefaultBossWaveExpSum(i);
        float calculateDefaultBossWaveScoreSum = Wave.calculateDefaultBossWaveScoreSum(i);
        Array<EnemyGroup> array = new Array<>();
        float pow = (100.0f + (((float) StrictMath.pow(WaveSystem.getWaveValue(i, i2) * 10.0d, 1.275d)) * 2.0f)) * 0.3f;
        array.add(new EnemyGroup(EnemyType.METAPHOR_BOSS, 0.27f, pow * 1.73f, 1, 1.0f, 0.0f, calculateDefaultBossWaveCoinsSum * 0.5f, calculateDefaultBossWaveExpSum * 0.5f, (int) (calculateDefaultBossWaveScoreSum * 0.041666668f)));
        int length = MetaphorBossCreepEnemy.Kind.values.length << 1;
        array.add(new EnemyGroup(EnemyType.METAPHOR_BOSS_CREEP, 0.27f, pow * 0.22f, length, 0.0f, 0.25f, calculateDefaultBossWaveCoinsSum * (0.5f / length), calculateDefaultBossWaveExpSum * (0.5f / length), (int) (calculateDefaultBossWaveScoreSum * (0.5f / length))));
        return array;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Wave setup(GameSystemProvider gameSystemProvider, int i, int i2) {
        this.g = gameSystemProvider;
        Array<EnemyGroup> generateEnemyGroups = generateEnemyGroups(i, i2);
        this.h = 0;
        for (int i3 = 0; i3 < generateEnemyGroups.size; i3++) {
            EnemyGroup enemyGroup = generateEnemyGroups.get(i3);
            if (enemyGroup.getEnemyType() == EnemyType.METAPHOR_BOSS_CREEP) {
                this.h += enemyGroup.count;
            }
        }
        this.f3983a = new Wave(i, i2, generateEnemyGroups);
        this.f3983a.enemiesCanBeSplitBetweenSpawns = false;
        this.f3983a.enemiesCanHaveRandomSideShifts = true;
        this.f3983a.waveMessage = Config.isHeadless() ? null : Game.i.localeManager.i18n.get("enemy_name_METAPHOR_BOSS");
        this.f3983a.waveProcessor = this;
        gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this.j).setDescription("MetaphorBossWaveProcessor - stores creep and boss references, configures creeps");
        gameSystemProvider.events.getListeners(EnemyDespawn.class).addStateAffecting(this.k).setDescription("MetaphorBossWaveProcessor - removes enemies from its list of creeps");
        gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(this.l).setDescription("MetaphorBossWaveProcessor - kills creeps when head is dead");
        return this.f3983a;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public float getNextWaveDelayMultiplier() {
        return 7.0f;
    }

    public String getTowerOutOfOrderReasonNearby() {
        return "DisabledByMetaphorNearbyWave" + this.f3983a.waveNumber;
    }

    public String getTowerOutOfOrderReasonPower() {
        return "DisabledByMetaphorPowerWave" + this.f3983a.waveNumber;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void update(float f) {
        if (this.d || this.f3984b == null) {
            return;
        }
        if (this.f3984b.enemy == null) {
            this.d = true;
            b();
            return;
        }
        String towerOutOfOrderReasonNearby = getTowerOutOfOrderReasonNearby();
        String towerOutOfOrderReasonPower = getTowerOutOfOrderReasonPower();
        MetaphorBossEnemy metaphorBossEnemy = (MetaphorBossEnemy) this.f3984b.enemy;
        for (int i = 0; i < this.e.size; i++) {
            this.e.items[i].outOfOrder.removeReason(towerOutOfOrderReasonNearby);
        }
        this.e.clear();
        Tile currentTile = metaphorBossEnemy.getCurrentTile();
        if (currentTile != null) {
            this.g.map.traverseNeighborTilesIncludingTile(currentTile, tile -> {
                if (tile instanceof PlatformTile) {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                        Tower tower = (Tower) platformTile.building;
                        tower.outOfOrder.addReason(towerOutOfOrderReasonNearby);
                        this.e.add(tower);
                        return true;
                    }
                    return true;
                }
                return true;
            });
        }
        int i2 = 0;
        float health = metaphorBossEnemy.getHealth() / metaphorBossEnemy.maxHealth;
        if (health < 0.25d) {
            i2 = 3;
        } else if (health < 0.5d) {
            i2 = 2;
        } else if (health < 0.75d) {
            i2 = 1;
        }
        if (i2 != 0) {
            for (int i3 = this.f.size - 1; i3 >= 0; i3--) {
                if (this.e.contains(this.f.items[i3], true)) {
                    this.f.items[i3].outOfOrder.removeReason(towerOutOfOrderReasonPower);
                    this.f.removeIndex(i3);
                }
            }
            if (this.f.size < i2) {
                Tower tower = null;
                for (int i4 = 0; i4 < this.g.tower.towers.size; i4++) {
                    Tower tower2 = this.g.tower.towers.items[i4];
                    if (!tower2.outOfOrder.hasReason(towerOutOfOrderReasonPower) && !tower2.outOfOrder.hasReason(towerOutOfOrderReasonNearby) && (tower == null || tower.moneySpentOn < tower2.moneySpentOn)) {
                        tower = tower2;
                    }
                }
                if (tower != null) {
                    tower.outOfOrder.addReason(towerOutOfOrderReasonPower);
                    this.f.add(tower);
                }
            }
        } else if (this.f.size != 0) {
            for (int i5 = 0; i5 < this.f.size; i5++) {
                this.f.items[i5].outOfOrder.removeReason(towerOutOfOrderReasonPower);
            }
            this.f.clear();
        }
        EntityUtils.removeNullRefs(this.c);
        for (int i6 = 0; i6 < this.c.size; i6++) {
            MetaphorBossCreepEnemy metaphorBossCreepEnemy = (MetaphorBossCreepEnemy) this.c.items[i6].enemy;
            if (metaphorBossCreepEnemy.getKind() == MetaphorBossCreepEnemy.Kind.FRONT) {
                if (metaphorBossCreepEnemy.passedTiles < metaphorBossEnemy.passedTiles + 0.5f) {
                    float speed = metaphorBossCreepEnemy.getSpeed() + (0.4f * f);
                    float f2 = speed;
                    if (speed > 1.2f) {
                        f2 = 1.2f;
                    }
                    metaphorBossCreepEnemy.setSpeed(f2);
                } else if (metaphorBossCreepEnemy.passedTiles > metaphorBossEnemy.passedTiles + 2.0f) {
                    float speed2 = metaphorBossCreepEnemy.getSpeed() - (0.8f * f);
                    float f3 = speed2;
                    if (speed2 < 0.2f) {
                        f3 = 0.2f;
                    } else if (f3 > 1.2f) {
                        f3 = 1.2f;
                    }
                    metaphorBossCreepEnemy.setSpeed(f3);
                }
            } else if (metaphorBossCreepEnemy.getKind() == MetaphorBossCreepEnemy.Kind.REAR) {
                if (metaphorBossCreepEnemy.passedTiles < metaphorBossEnemy.passedTiles - 2.0f) {
                    metaphorBossCreepEnemy.setSpeed(metaphorBossCreepEnemy.getSpeed() + (0.4f * f));
                } else if (metaphorBossCreepEnemy.passedTiles > metaphorBossEnemy.passedTiles - 0.5f) {
                    float speed3 = metaphorBossCreepEnemy.getSpeed() - (0.8f * f);
                    float f4 = speed3;
                    if (speed3 < 0.2f) {
                        f4 = 0.2f;
                    } else if (f4 > 1.2f) {
                        f4 = 1.2f;
                    }
                    metaphorBossCreepEnemy.setSpeed(f4);
                }
            } else if (metaphorBossCreepEnemy.getKind() == MetaphorBossCreepEnemy.Kind.RANDOM_SPEED) {
                metaphorBossCreepEnemy.setSpeed(0.27f + (PMath.sin(metaphorBossCreepEnemy.existsTime) * 0.2f));
            }
        }
        metaphorBossEnemy.creepCount = this.c.size;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public boolean isDone() {
        return this.d;
    }

    private void a() {
        if (this.f3984b == null) {
            return;
        }
        if (this.i == null && this.g._gameUi != null) {
            this.i = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("enemy_name_METAPHOR_BOSS")).setMainBarColor(new Color(856888319), new Color(-1340663809)).setSmallBarsCount(1).setFirstSmallBarColor(new Color(590230527), new Color(1618840575)).setSmallBarOneProgress(0.3d).setLabelsColor(new Color(-448050177)).addMark(0.25f).addMark(0.5f).addMark(0.75f).setIcon(Game.i.assetManager.getDrawable("boss-wave-icon-METAPHOR"));
            this.g._gameUi.mainUi.addBossHpBar(this.i);
        }
        if (this.i != null) {
            MetaphorBossEnemy metaphorBossEnemy = (MetaphorBossEnemy) this.f3984b.enemy;
            this.i.setMainHP(metaphorBossEnemy.getHealth(), metaphorBossEnemy.maxHealth);
            float f = 0.0f;
            for (int i = 0; i < this.c.size; i++) {
                MetaphorBossCreepEnemy metaphorBossCreepEnemy = (MetaphorBossCreepEnemy) this.c.get(i).enemy;
                if (metaphorBossCreepEnemy != null) {
                    f += metaphorBossCreepEnemy.getHealth() / metaphorBossCreepEnemy.maxHealth;
                }
            }
            this.i.setSmallBarOneProgress(f / this.h);
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void draw(Batch batch, float f) {
        a();
    }

    private void b() {
        this.g.events.getListeners(EnemySpawn.class).remove(this.j);
        this.g.events.getListeners(EnemyDespawn.class).remove(this.k);
        this.g.events.getListeners(EnemyDie.class).remove(this.l);
        this.c.clear();
        String towerOutOfOrderReasonNearby = getTowerOutOfOrderReasonNearby();
        String towerOutOfOrderReasonPower = getTowerOutOfOrderReasonPower();
        if (this.f.size != 0) {
            for (int i = 0; i < this.f.size; i++) {
                this.f.items[i].outOfOrder.removeReason(towerOutOfOrderReasonPower);
            }
            this.f.clear();
        }
        if (this.e.size != 0) {
            for (int i2 = 0; i2 < this.e.size; i2++) {
                this.e.items[i2].outOfOrder.removeReason(towerOutOfOrderReasonNearby);
            }
            this.e.clear();
        }
        if (this.i != null) {
            this.g._gameUi.mainUi.removeBossHpBar(this.i);
            this.i = null;
        }
        this.g = null;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MetaphorBossWaveProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<MetaphorBossWaveProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(MetaphorBossWaveProcessor metaphorBossWaveProcessor) {
            this.f1759a = metaphorBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Enemy enemy = lastDamage.getEnemy();
            if (((MetaphorBossWaveProcessor) this.f1759a).f3984b != null && enemy == ((MetaphorBossWaveProcessor) this.f1759a).f3984b.enemy) {
                for (int i = ((MetaphorBossWaveProcessor) this.f1759a).c.size - 1; i >= 0; i--) {
                    if (((Enemy.EnemyReference[]) ((MetaphorBossWaveProcessor) this.f1759a).c.items)[i].enemy != null) {
                        ((MetaphorBossWaveProcessor) this.f1759a).g.damage.queueEnemyKill(lastDamage.copyFor(((Enemy.EnemyReference[]) ((MetaphorBossWaveProcessor) this.f1759a).c.items)[i].enemy, ((MetaphorBossWaveProcessor) this.f1759a).g.damage.obtainRecord()));
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MetaphorBossWaveProcessor$OnEnemyDespawn.class */
    public static class OnEnemyDespawn extends SerializableListener<MetaphorBossWaveProcessor> implements Listener<EnemyDespawn> {
        /* synthetic */ OnEnemyDespawn(MetaphorBossWaveProcessor metaphorBossWaveProcessor, byte b2) {
            this(metaphorBossWaveProcessor);
        }

        private OnEnemyDespawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemyDespawn(MetaphorBossWaveProcessor metaphorBossWaveProcessor) {
            this.f1759a = metaphorBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(EnemyDespawn enemyDespawn) {
            Enemy enemy = enemyDespawn.getEnemy();
            if (enemy.type == EnemyType.METAPHOR_BOSS_CREEP) {
                EntityUtils.removeByValue(((MetaphorBossWaveProcessor) this.f1759a).c, enemy);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MetaphorBossWaveProcessor$OnEnemySpawn.class */
    public static class OnEnemySpawn extends SerializableListener<MetaphorBossWaveProcessor> implements Listener<EnemySpawn> {
        /* synthetic */ OnEnemySpawn(MetaphorBossWaveProcessor metaphorBossWaveProcessor, byte b2) {
            this(metaphorBossWaveProcessor);
        }

        private OnEnemySpawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemySpawn(MetaphorBossWaveProcessor metaphorBossWaveProcessor) {
            this.f1759a = metaphorBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(EnemySpawn enemySpawn) {
            Enemy enemy = enemySpawn.getEnemy();
            if (enemy.wave != ((MetaphorBossWaveProcessor) this.f1759a).f3983a || enemy.type != EnemyType.METAPHOR_BOSS) {
                if (enemy.wave == ((MetaphorBossWaveProcessor) this.f1759a).f3983a && enemy.type == EnemyType.METAPHOR_BOSS_CREEP) {
                    MetaphorBossCreepEnemy metaphorBossCreepEnemy = (MetaphorBossCreepEnemy) enemy;
                    ((MetaphorBossWaveProcessor) this.f1759a).c.add(((MetaphorBossWaveProcessor) this.f1759a).g.enemy.getReference(metaphorBossCreepEnemy));
                    MetaphorBossCreepEnemy.Kind kind = MetaphorBossCreepEnemy.Kind.values[(((MetaphorBossWaveProcessor) this.f1759a).c.size - 1) % MetaphorBossCreepEnemy.Kind.values.length];
                    metaphorBossCreepEnemy.setKind(kind);
                    if (kind == MetaphorBossCreepEnemy.Kind.HIGH_HP) {
                        float f = metaphorBossCreepEnemy.maxHealth * 3.0f;
                        metaphorBossCreepEnemy.setMaxHealth(f);
                        metaphorBossCreepEnemy.setHealth(f);
                        return;
                    } else {
                        if (kind == MetaphorBossCreepEnemy.Kind.LOW_HP) {
                            float f2 = metaphorBossCreepEnemy.maxHealth * 0.333f;
                            metaphorBossCreepEnemy.setMaxHealth(f2);
                            metaphorBossCreepEnemy.setHealth(f2);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            ((MetaphorBossWaveProcessor) this.f1759a).f3984b = ((MetaphorBossWaveProcessor) this.f1759a).g.enemy.getReference(enemy);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MetaphorBossWaveProcessor$MetaphorBossWaveProcessorFactory.class */
    public static class MetaphorBossWaveProcessorFactory extends WaveProcessor.WaveProcessorFactory<MetaphorBossWaveProcessor> {
        public MetaphorBossWaveProcessorFactory() {
            super(BossType.METAPHOR);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.WaveProcessor.WaveProcessorFactory
        public MetaphorBossWaveProcessor create() {
            return new MetaphorBossWaveProcessor((byte) 0);
        }
    }
}

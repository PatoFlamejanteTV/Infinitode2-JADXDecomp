package com.prineside.tdi2.waves.processors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
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
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.enemies.bosses.SnakeBossHeadEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.ui.components.BossHpBar;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/SnakeBossWaveProcessor.class */
public class SnakeBossWaveProcessor implements KryoSerializable, WaveProcessor, Listener<EnemySpawn> {

    /* renamed from: a, reason: collision with root package name */
    private Wave f3987a;

    /* renamed from: b, reason: collision with root package name */
    private Enemy.EnemyReference f3988b;
    private Array<Enemy.EnemyReference> c;
    private int d;
    private int e;
    private int f;
    private boolean g;

    @NAGS
    private BossHpBar h;

    @NAGS
    private Drawable i;
    private GameSystemProvider j;
    private OnEnemyDespawn k;
    private OnEnemyDie l;

    static /* synthetic */ int b(SnakeBossWaveProcessor snakeBossWaveProcessor) {
        int i = snakeBossWaveProcessor.f;
        snakeBossWaveProcessor.f = i + 1;
        return i;
    }

    /* synthetic */ SnakeBossWaveProcessor(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f3987a, Wave.class);
        kryo.writeObject(output, this.f3988b);
        kryo.writeObject(output, this.c);
        output.writeVarInt(this.d, true);
        output.writeVarInt(this.e, true);
        output.writeVarInt(this.f, true);
        output.writeBoolean(this.g);
        kryo.writeObjectOrNull(output, this.j, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.k, OnEnemyDespawn.class);
        kryo.writeObjectOrNull(output, this.l, OnEnemyDie.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f3987a = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.f3988b = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.c = (Array) kryo.readObject(input, Array.class);
        this.d = input.readVarInt(true);
        this.e = input.readVarInt(true);
        this.f = input.readVarInt(true);
        this.g = input.readBoolean();
        this.j = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.k = (OnEnemyDespawn) kryo.readObjectOrNull(input, OnEnemyDespawn.class);
        this.l = (OnEnemyDie) kryo.readObjectOrNull(input, OnEnemyDie.class);
    }

    private SnakeBossWaveProcessor() {
        this.f3988b = Enemy.EnemyReference.NULL;
        this.c = new Array<>(true, 8, Enemy.EnemyReference.class);
        this.f = 0;
        this.g = false;
        this.k = new OnEnemyDespawn(this, (byte) 0);
        this.l = new OnEnemyDie(this);
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Array<EnemyGroup> generateEnemyGroups(int i, int i2) {
        Array<EnemyGroup> array = new Array<>();
        float waveValue = WaveSystem.getWaveValue(i, i2);
        int floor = MathUtils.floor(10.0f + (((float) StrictMath.pow(i, 0.85d)) / 6.0f));
        int i3 = floor;
        if (floor > 30) {
            i3 = 30;
        }
        float calculateDefaultBossWaveCoinsSum = Wave.calculateDefaultBossWaveCoinsSum(i);
        float calculateDefaultBossWaveExpSum = Wave.calculateDefaultBossWaveExpSum(i);
        float calculateDefaultBossWaveScoreSum = Wave.calculateDefaultBossWaveScoreSum(i);
        float f = calculateDefaultBossWaveCoinsSum * 0.5f;
        float f2 = calculateDefaultBossWaveExpSum * 0.3f;
        int i4 = (int) (calculateDefaultBossWaveScoreSum * 0.3f);
        float f3 = (calculateDefaultBossWaveCoinsSum * 0.5f) / i3;
        float f4 = (calculateDefaultBossWaveExpSum * 0.7f) / i3;
        int round = StrictMath.round((calculateDefaultBossWaveScoreSum * 0.7f) / i3);
        float pow = (8.0f + (((float) StrictMath.pow(waveValue * 10.0d, 1.275d)) * 0.1f)) * 3.5f;
        float f5 = (pow / 3.0f) * (20.0f / i3);
        array.add(new EnemyGroup(EnemyType.SNAKE_BOSS_HEAD, 0.3f, pow * 2.0f, 1, 0.0f, 0.0f, f, f2, i4));
        if (i3 > 1) {
            array.add(new EnemyGroup(EnemyType.SNAKE_BOSS_BODY, 0.3f, f5, i3 - 1, 0.37f, 0.37f, f3, f4, round));
        }
        array.add(new EnemyGroup(EnemyType.SNAKE_BOSS_TAIL, 0.3f, f5 * 2.0f * 1.5f, 1, 0.37f * i3, 0.0f, f3, f4, round));
        return array;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Wave setup(GameSystemProvider gameSystemProvider, int i, int i2) {
        this.j = gameSystemProvider;
        Array<EnemyGroup> generateEnemyGroups = generateEnemyGroups(i, i2);
        this.e = 0;
        for (int i3 = 0; i3 < generateEnemyGroups.size; i3++) {
            this.e += generateEnemyGroups.get(i3).count;
        }
        this.e--;
        this.f3987a = new Wave(i, i2, generateEnemyGroups);
        this.f3987a.enemiesCanBeSplitBetweenSpawns = false;
        this.f3987a.enemiesCanHaveRandomSideShifts = false;
        this.f3987a.waveMessage = Config.isHeadless() ? null : Game.i.localeManager.i18n.get("enemy_name_SNAKE_BOSS_HEAD");
        this.f3987a.waveProcessor = this;
        gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Stores references for creeps");
        gameSystemProvider.events.getListeners(EnemyDespawn.class).addStateAffecting(this.k);
        gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(this.l);
        return this.f3987a;
    }

    private Drawable a() {
        if (this.i == null) {
            this.i = Game.i.assetManager.getDrawable("buff-health-bar-icon-armor");
        }
        return this.i;
    }

    private void b() {
        SnakeBossHeadEnemy snakeBossHeadEnemy;
        if (this.f3988b == null) {
            return;
        }
        if (this.h == null && this.j._gameUi != null) {
            this.h = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("enemy_name_SNAKE_BOSS_HEAD")).setMainBarColor(new Color(607327231), new Color(-1950135553)).setSmallBarsCount(2).setFirstSmallBarColor(new Color(590230527), new Color(1618840575)).setSecondSmallBarColor(new Color(607327231), new Color(-1950135553)).setLabelsColor(new Color(-1361739265)).setIcon(Game.i.assetManager.getDrawable("boss-wave-icon-SNAKE"));
            this.j._gameUi.mainUi.addBossHpBar(this.h);
        }
        if (this.h == null || (snakeBossHeadEnemy = (SnakeBossHeadEnemy) this.f3988b.enemy) == null) {
            return;
        }
        this.h.setMainHP(snakeBossHeadEnemy.getHealth(), snakeBossHeadEnemy.maxHealth);
        if (this.d < this.e) {
            if (!this.h.isEffectIconExists(a())) {
                this.h.addEffectIcon(a());
            }
        } else {
            this.h.clearEffectIcons();
        }
        float f = 0.0f;
        for (int i = 0; i < this.c.size; i++) {
            Enemy enemy = this.c.get(i).enemy;
            if (enemy != null && enemy.type != EnemyType.SNAKE_BOSS_HEAD) {
                f += enemy.getHealth() / enemy.maxHealth;
            }
        }
        this.h.setSmallBarOneProgress(snakeBossHeadEnemy.damageResistance);
        this.h.setSmallBarTwoProgress(f / this.e);
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void draw(Batch batch, float f) {
        b();
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void update(float f) {
        if (this.g) {
            return;
        }
        EntityUtils.removeNullRefs(this.c);
        if (this.c.size == 0 && this.f3987a.isFullySpawned()) {
            this.g = true;
            c();
            return;
        }
        if (this.f3988b.enemy == null) {
            for (int i = 0; i < this.c.size; i++) {
                this.c.items[i].enemy.setSpeed(1.0f);
            }
            return;
        }
        SnakeBossHeadEnemy snakeBossHeadEnemy = (SnakeBossHeadEnemy) this.f3988b.enemy;
        float f2 = snakeBossHeadEnemy.defaultMinSpeed + ((this.f / this.e) * (snakeBossHeadEnemy.defaultMaxSpeed - snakeBossHeadEnemy.defaultMinSpeed));
        if (this.d < this.e) {
            snakeBossHeadEnemy.damageResistance = 1.0f;
        } else {
            snakeBossHeadEnemy.damageResistance = 1.0f - (this.f / this.e);
        }
        float f3 = 0.0f;
        for (int i2 = 1; i2 < this.c.size; i2++) {
            float f4 = (this.c.items[i2 - 1].enemy.passedTiles - this.c.items[i2].enemy.passedTiles) - 0.37f;
            if (f4 > 0.0f) {
                f3 += f4;
            }
        }
        float f5 = f2 - f3;
        float f6 = f5;
        if (f5 < snakeBossHeadEnemy.defaultMinSpeed * 0.5f) {
            f6 = snakeBossHeadEnemy.defaultMinSpeed * 0.5f;
        }
        snakeBossHeadEnemy.setSpeed(f6);
        for (int i3 = 1; i3 < this.c.size; i3++) {
            Enemy enemy = this.c.items[i3].enemy;
            Enemy enemy2 = this.c.items[i3 - 1].enemy;
            enemy.setSpeed(f2);
            if (enemy2.passedTiles - enemy.passedTiles < 0.37f) {
                enemy.passedTiles = enemy2.passedTiles - 0.37f;
                if (enemy.passedTiles < 0.0f) {
                    enemy.passedTiles = 0.0f;
                }
            }
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public boolean isDone() {
        return this.g;
    }

    private void c() {
        if (this.h != null) {
            this.j._gameUi.mainUi.removeBossHpBar(this.h);
            this.h = null;
        }
        this.j.events.getListeners(EnemySpawn.class).remove(this);
        this.j.events.getListeners(EnemyDespawn.class).remove(this.k);
        this.j.events.getListeners(EnemyDie.class).remove(this.l);
        this.j = null;
        this.f3988b = Enemy.EnemyReference.NULL;
        this.c.clear();
        this.f3987a = null;
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (enemy.wave == this.f3987a) {
            Enemy.EnemyReference reference = this.j.enemy.getReference(enemy);
            this.c.add(reference);
            this.d++;
            if (enemy.type == EnemyType.SNAKE_BOSS_HEAD) {
                this.f3988b = reference;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/SnakeBossWaveProcessor$OnEnemyDespawn.class */
    public static class OnEnemyDespawn extends SerializableListener<SnakeBossWaveProcessor> implements Listener<EnemyDespawn> {
        /* synthetic */ OnEnemyDespawn(SnakeBossWaveProcessor snakeBossWaveProcessor, byte b2) {
            this(snakeBossWaveProcessor);
        }

        private OnEnemyDespawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemyDespawn(SnakeBossWaveProcessor snakeBossWaveProcessor) {
            this.f1759a = snakeBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(EnemyDespawn enemyDespawn) {
            Enemy enemy = enemyDespawn.getEnemy();
            if (enemy.wave == ((SnakeBossWaveProcessor) this.f1759a).f3987a) {
                SnakeBossWaveProcessor.b((SnakeBossWaveProcessor) this.f1759a);
                if (enemy == ((SnakeBossWaveProcessor) this.f1759a).f3988b.enemy) {
                    ((SnakeBossWaveProcessor) this.f1759a).f3988b = Enemy.EnemyReference.NULL;
                }
                for (int i = 0; i < ((SnakeBossWaveProcessor) this.f1759a).c.size; i++) {
                    if (((Enemy.EnemyReference[]) ((SnakeBossWaveProcessor) this.f1759a).c.items)[i].enemy == enemy) {
                        ((SnakeBossWaveProcessor) this.f1759a).c.removeIndex(i);
                        return;
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/SnakeBossWaveProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<SnakeBossWaveProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(SnakeBossWaveProcessor snakeBossWaveProcessor) {
            this.f1759a = snakeBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Enemy enemy = lastDamage.getEnemy();
            if (enemy.wave == ((SnakeBossWaveProcessor) this.f1759a).f3987a && enemy.type == EnemyType.SNAKE_BOSS_HEAD) {
                ((SnakeBossWaveProcessor) this.f1759a).j.wave.stopSpawningCurrentWave(((SnakeBossWaveProcessor) this.f1759a).f3987a, lastDamage.getTower(), lastDamage.getDamageType());
                for (int i = ((SnakeBossWaveProcessor) this.f1759a).c.size - 1; i >= 0; i--) {
                    Enemy enemy2 = ((Enemy.EnemyReference[]) ((SnakeBossWaveProcessor) this.f1759a).c.items)[i].enemy;
                    if (enemy2 != null && enemy2 != enemy) {
                        ((SnakeBossWaveProcessor) this.f1759a).j.damage.queueEnemyKill(lastDamage.copyFor(enemy2, ((SnakeBossWaveProcessor) this.f1759a).j.damage.obtainRecord()));
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/SnakeBossWaveProcessor$SnakeBossWaveProcessorFactory.class */
    public static class SnakeBossWaveProcessorFactory extends WaveProcessor.WaveProcessorFactory<SnakeBossWaveProcessor> {
        public SnakeBossWaveProcessorFactory() {
            super(BossType.SNAKE);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.WaveProcessor.WaveProcessorFactory
        public SnakeBossWaveProcessor create() {
            return new SnakeBossWaveProcessor((byte) 0);
        }
    }
}

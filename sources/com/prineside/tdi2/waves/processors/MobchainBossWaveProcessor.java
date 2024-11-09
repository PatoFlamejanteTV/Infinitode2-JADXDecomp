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
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.buffs.InvulnerabilityBuff;
import com.prineside.tdi2.buffs.RegenerationBuff;
import com.prineside.tdi2.buffs.SlippingBuff;
import com.prineside.tdi2.enemies.bosses.MobchainBossCreepEnemy;
import com.prineside.tdi2.enemies.bosses.MobchainBossHeadEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MobchainBossWaveProcessor.class */
public class MobchainBossWaveProcessor implements KryoSerializable, WaveProcessor, Listener<EnemySpawn> {

    /* renamed from: a, reason: collision with root package name */
    private Wave f3985a;

    /* renamed from: b, reason: collision with root package name */
    private Enemy.EnemyReference f3986b;
    private Array<Enemy.EnemyReference> c;
    private int d;
    private int e;
    private Array<Enemy.EnemyReference> f;
    private int g;
    private boolean h;
    private GameSystemProvider i;

    @NAGS
    private BossHpBar j;

    @NAGS
    private Drawable k;

    @NAGS
    private Drawable l;
    private OnEnemyDespawn m;
    private OnEnemyDie n;

    /* synthetic */ MobchainBossWaveProcessor(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f3985a, Wave.class);
        kryo.writeObject(output, this.f3986b);
        kryo.writeObject(output, this.c);
        output.writeVarInt(this.d, true);
        output.writeVarInt(this.e, true);
        kryo.writeObject(output, this.f);
        output.writeVarInt(this.g, true);
        output.writeBoolean(this.h);
        kryo.writeObjectOrNull(output, this.i, GameSystemProvider.class);
        kryo.writeObjectOrNull(output, this.m, OnEnemyDespawn.class);
        kryo.writeObjectOrNull(output, this.n, OnEnemyDie.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f3985a = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.f3986b = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.c = (Array) kryo.readObject(input, Array.class);
        this.d = input.readVarInt(true);
        this.e = input.readVarInt(true);
        this.f = (Array) kryo.readObject(input, Array.class);
        this.g = input.readVarInt(true);
        this.h = input.readBoolean();
        this.i = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.m = (OnEnemyDespawn) kryo.readObjectOrNull(input, OnEnemyDespawn.class);
        this.n = (OnEnemyDie) kryo.readObjectOrNull(input, OnEnemyDie.class);
    }

    private MobchainBossWaveProcessor() {
        this.f3986b = Enemy.EnemyReference.NULL;
        this.c = new Array<>(true, 8, Enemy.EnemyReference.class);
        this.f = new Array<>(true, 8, Enemy.EnemyReference.class);
        this.g = 1;
        this.h = false;
        this.m = new OnEnemyDespawn(this, (byte) 0);
        this.n = new OnEnemyDie(this);
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public float getNextWaveDelayMultiplier() {
        return 7.0f;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Array<EnemyGroup> generateEnemyGroups(int i, int i2) {
        Array<EnemyGroup> array = new Array<>(EnemyGroup.class);
        float waveValue = WaveSystem.getWaveValue(i, i2);
        int floor = MathUtils.floor(4.0f + (((float) StrictMath.pow(i, 0.85d)) / 12.0f));
        int i3 = floor;
        if (floor > 8) {
            i3 = 8;
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
        float pow = 30.0f + (((float) StrictMath.pow(waveValue * 10.0d, 1.275d)) * 0.5f);
        array.add(new EnemyGroup(EnemyType.MOBCHAIN_BOSS_HEAD, 0.32f, pow * 3.3f, 1, 0.0f, 0.0f, f, f2, i4));
        array.add(new EnemyGroup(EnemyType.MOBCHAIN_BOSS_BODY, 0.32f, ((pow * (1.0f + (i3 * 0.2f))) / i3) * 0.7f * 2.2f, i3 - 1, 0.55f, 0.55f, f3, f4, round));
        return array;
    }

    private Drawable a() {
        if (this.k == null) {
            this.k = Game.i.assetManager.getDrawable("buff-health-bar-icon-armor");
        }
        return this.k;
    }

    private Drawable b() {
        if (this.l == null) {
            this.l = Game.i.assetManager.getDrawable("buff-health-bar-icon-regeneration");
        }
        return this.l;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public Wave setup(GameSystemProvider gameSystemProvider, int i, int i2) {
        this.i = gameSystemProvider;
        Array<EnemyGroup> generateEnemyGroups = generateEnemyGroups(i, i2);
        for (int i3 = 0; i3 < generateEnemyGroups.size; i3++) {
            this.d += generateEnemyGroups.items[i3].count;
        }
        this.e = this.d - 1;
        this.f3985a = new Wave(i, i2, generateEnemyGroups);
        this.f3985a.enemiesCanBeSplitBetweenSpawns = false;
        this.f3985a.enemiesCanHaveRandomSideShifts = false;
        this.f3985a.waveMessage = Config.isHeadless() ? null : Game.i.localeManager.i18n.get("enemy_name_MOBCHAIN_BOSS_HEAD");
        this.f3985a.waveProcessor = this;
        gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(this.n);
        gameSystemProvider.events.getListeners(EnemyDespawn.class).addStateAffecting(this.m);
        gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Stores spawned boss parts to be handled by this processor");
        return this.f3985a;
    }

    private void c() {
        MobchainBossHeadEnemy mobchainBossHeadEnemy;
        if (this.f3986b == null) {
            return;
        }
        if (this.j == null && this.i._gameUi != null) {
            this.j = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("enemy_name_MOBCHAIN_BOSS_HEAD")).setMainBarColor(new Color(521352191), new Color(1731901439)).setSmallBarsCount(1).setFirstSmallBarColor(new Color(590230527), new Color(1618840575)).setLabelsColor(new Color(-1787441665)).setIcon(Game.i.assetManager.getDrawable("boss-wave-icon-MOBCHAIN"));
            this.i._gameUi.mainUi.addBossHpBar(this.j);
        }
        if (this.j == null || (mobchainBossHeadEnemy = (MobchainBossHeadEnemy) this.f3986b.enemy) == null) {
            return;
        }
        this.j.setMainHP(mobchainBossHeadEnemy.getHealth(), mobchainBossHeadEnemy.maxHealth);
        float f = 0.0f;
        for (int i = 0; i < this.c.size; i++) {
            Enemy enemy = this.c.get(i).enemy;
            if (enemy != null && enemy.type != EnemyType.MOBCHAIN_BOSS_HEAD) {
                f += enemy.getHealth() / enemy.maxHealth;
            }
        }
        if (f == 0.0f) {
            if (this.j.isEffectIconExists(a())) {
                this.j.clearEffectIcons();
            }
            float f2 = 0.0f;
            for (int i2 = 0; i2 < this.f.size; i2++) {
                Enemy enemy2 = this.f.get(i2).enemy;
                if (enemy2 != null) {
                    f2 += enemy2.getHealth() / enemy2.maxHealth;
                }
            }
            this.j.setSmallBarOneProgress(f2 / this.g);
            if (mobchainBossHeadEnemy.hasBuffsByType(BuffType.REGENERATION)) {
                if (!this.j.isEffectIconExists(b())) {
                    this.j.addEffectIcon(b());
                    return;
                }
                return;
            }
            this.j.clearEffectIcons();
            return;
        }
        this.j.setSmallBarOneProgress(f / this.e);
        if (!this.j.isEffectIconExists(a())) {
            this.j.addEffectIcon(a());
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void draw(Batch batch, float f) {
        c();
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public void update(float f) {
        if (this.h) {
            return;
        }
        EntityUtils.removeNullRefs(this.c);
        EntityUtils.removeNullRefs(this.f);
        if (this.c.size == 0 && this.f.size == 0 && this.f3985a.isFullySpawned()) {
            this.h = true;
            d();
            return;
        }
        if (this.f3986b.enemy == null) {
            for (int i = 0; i < this.c.size; i++) {
                this.c.items[i].enemy.setSpeed(0.64f);
            }
            return;
        }
        Enemy enemy = this.f3986b.enemy;
        float f2 = 0.0f;
        for (int i2 = 1; i2 < this.c.size; i2++) {
            float f3 = (this.c.items[i2 - 1].enemy.passedTiles - this.c.items[i2].enemy.passedTiles) - 0.55f;
            if (f3 > 0.0f) {
                f2 += f3;
            }
        }
        float f4 = 0.32f - f2;
        if (this.c.size <= 1) {
            f4 = 0.2f;
        }
        if (f4 < 0.2f) {
            f4 = 0.2f;
        }
        enemy.setSpeed(f4);
        for (int i3 = 1; i3 < this.c.size; i3++) {
            Enemy enemy2 = this.c.items[i3].enemy;
            Enemy enemy3 = this.c.items[i3 - 1].enemy;
            enemy2.setSpeed(0.32f);
            if (enemy3.passedTiles - enemy2.passedTiles < 0.55f) {
                enemy2.passedTiles = enemy3.passedTiles - 0.55f;
                if (enemy2.passedTiles < 0.0f) {
                    enemy2.passedTiles = 0.0f;
                }
            }
        }
        if (enemy.getHealth() < enemy.maxHealth * 0.5f) {
            if ((enemy.buffsByType == null || enemy.buffsByType[BuffType.REGENERATION.ordinal()].size == 0) && this.f.size != 0) {
                MobchainBossCreepEnemy mobchainBossCreepEnemy = (MobchainBossCreepEnemy) this.f.first().enemy;
                RegenerationBuff regenerationBuff = new RegenerationBuff();
                regenerationBuff.setup(2.5f, 25.0f, mobchainBossCreepEnemy.getHealth() * 0.6f, this.i.enemy.getReference(mobchainBossCreepEnemy));
                InvulnerabilityBuff invulnerabilityBuff = new InvulnerabilityBuff();
                invulnerabilityBuff.setup(2.5f, 4.0f);
                SlippingBuff slippingBuff = new SlippingBuff();
                slippingBuff.setup(3.0f, 6.0f);
                slippingBuff.speedMultiplier = 0.4f;
                this.i.buff.P_INVULNERABILITY.removeAllBuffs(enemy, BuffType.INVULNERABILITY);
                this.i.buff.P_REGENERATION.addBuffStackSameSourceRemoveOthers(enemy, regenerationBuff, true);
                this.i.buff.P_SLIPPING.addBuff(enemy, slippingBuff);
                this.i.buff.P_INVULNERABILITY.addBuff(enemy, invulnerabilityBuff);
                this.i.damage.queueEnemyKill(this.i.damage.obtainRecord().setup(mobchainBossCreepEnemy, 1.0f, DamageType.BULLET));
            }
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public boolean isDone() {
        return this.h;
    }

    private void d() {
        this.i.events.getListeners(EnemyDie.class).remove(this.n);
        this.i.events.getListeners(EnemySpawn.class).remove(this);
        this.i.events.getListeners(EnemyDespawn.class).remove(this.m);
        if (this.j != null) {
            this.i._gameUi.mainUi.removeBossHpBar(this.j);
            this.j = null;
        }
        this.i = null;
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        Enemy enemy = enemySpawn.getEnemy();
        if (enemy.wave == this.f3985a) {
            if (enemy.type == EnemyType.MOBCHAIN_BOSS_BODY || enemy.type == EnemyType.MOBCHAIN_BOSS_HEAD) {
                this.d--;
                this.c.add(this.i.enemy.getReference(enemy));
                if (enemy.type == EnemyType.MOBCHAIN_BOSS_HEAD) {
                    this.f3986b = this.i.enemy.getReference(enemy);
                    return;
                }
                return;
            }
            if (enemy.type == EnemyType.MOBCHAIN_BOSS_CREEP) {
                this.f.add(this.i.enemy.getReference(enemy));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MobchainBossWaveProcessor$OnEnemyDespawn.class */
    public static class OnEnemyDespawn extends SerializableListener<MobchainBossWaveProcessor> implements Listener<EnemyDespawn> {
        /* synthetic */ OnEnemyDespawn(MobchainBossWaveProcessor mobchainBossWaveProcessor, byte b2) {
            this(mobchainBossWaveProcessor);
        }

        private OnEnemyDespawn() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnEnemyDespawn(MobchainBossWaveProcessor mobchainBossWaveProcessor) {
            this.f1759a = mobchainBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(EnemyDespawn enemyDespawn) {
            Enemy enemy = enemyDespawn.getEnemy();
            if (enemy.wave == ((MobchainBossWaveProcessor) this.f1759a).f3985a) {
                if (enemy.type != EnemyType.MOBCHAIN_BOSS_BODY && enemy.type != EnemyType.MOBCHAIN_BOSS_HEAD) {
                    if (enemy.type == EnemyType.MOBCHAIN_BOSS_CREEP) {
                        EntityUtils.removeByValue(((MobchainBossWaveProcessor) this.f1759a).f, enemy);
                        return;
                    }
                    return;
                }
                if (enemy == ((MobchainBossWaveProcessor) this.f1759a).f3986b.enemy) {
                    ((MobchainBossWaveProcessor) this.f1759a).f3986b = Enemy.EnemyReference.NULL;
                    for (int i = ((MobchainBossWaveProcessor) this.f1759a).f.size - 1; i >= 0; i--) {
                        Enemy enemy2 = ((Enemy.EnemyReference) ((MobchainBossWaveProcessor) this.f1759a).f.get(i)).enemy;
                        if (enemy2 != null) {
                            ((MobchainBossWaveProcessor) this.f1759a).i.damage.queueEnemyKill(((MobchainBossWaveProcessor) this.f1759a).i.damage.obtainRecord().setup(enemy2, 1.0f, DamageType.BULLET));
                        }
                    }
                }
                EntityUtils.removeByValue(((MobchainBossWaveProcessor) this.f1759a).c, enemy);
                MobchainBossHeadEnemy mobchainBossHeadEnemy = (MobchainBossHeadEnemy) ((MobchainBossWaveProcessor) this.f1759a).f3986b.enemy;
                if (((MobchainBossWaveProcessor) this.f1759a).c.size == 1 && ((MobchainBossWaveProcessor) this.f1759a).d == 0 && mobchainBossHeadEnemy != null) {
                    mobchainBossHeadEnemy.vulnerable = true;
                    Array array = new Array(TowerType.class);
                    for (int i2 = 0; i2 < ((MobchainBossWaveProcessor) this.f1759a).i.tower.towers.size; i2++) {
                        Tower tower = ((MobchainBossWaveProcessor) this.f1759a).i.tower.towers.items[i2];
                        if (tower.type != TowerType.AIR && tower.type != TowerType.FREEZING && !array.contains(tower.type, true)) {
                            array.add(tower.type);
                        }
                    }
                    if (array.size < 4) {
                        if (!array.contains(TowerType.BASIC, true)) {
                            array.add(TowerType.BASIC);
                        }
                        if (!array.contains(TowerType.CANNON, true)) {
                            array.add(TowerType.CANNON);
                        }
                        if (!array.contains(TowerType.SNIPER, true)) {
                            array.add(TowerType.SNIPER);
                        }
                        if (!array.contains(TowerType.MULTISHOT, true)) {
                            array.add(TowerType.MULTISHOT);
                        }
                    }
                    int floor = MathUtils.floor(2.0f + (((float) StrictMath.pow(((MobchainBossWaveProcessor) this.f1759a).f3985a.waveNumber, 0.85d)) / 12.0f));
                    int i3 = floor;
                    if (floor > 5) {
                        i3 = 5;
                    }
                    ((MobchainBossWaveProcessor) this.f1759a).g = i3;
                    for (int i4 = 0; i4 < i3; i4++) {
                        TowerType towerType = ((TowerType[]) array.items)[((MobchainBossWaveProcessor) this.f1759a).i.gameState.randomInt(array.size)];
                        MobchainBossCreepEnemy mobchainBossCreepEnemy = (MobchainBossCreepEnemy) Game.i.enemyManager.getFactory(EnemyType.MOBCHAIN_BOSS_CREEP).obtain();
                        mobchainBossCreepEnemy.setSpeed(mobchainBossHeadEnemy.getSpeed());
                        mobchainBossCreepEnemy.maxHealth = mobchainBossHeadEnemy.maxHealth * 0.23f;
                        mobchainBossCreepEnemy.setHealth(mobchainBossCreepEnemy.maxHealth);
                        mobchainBossCreepEnemy.killScore = StrictMath.round(mobchainBossHeadEnemy.killScore * 0.1f);
                        mobchainBossCreepEnemy.bounty = 0.0f;
                        mobchainBossCreepEnemy.setKillExp(mobchainBossHeadEnemy.getKillExp() * 0.1f);
                        mobchainBossCreepEnemy.vulnerableTo = towerType;
                        mobchainBossCreepEnemy.color = Game.i.towerManager.getFactory(towerType).getColor();
                        float f = (mobchainBossHeadEnemy.passedTiles - 0.5f) - (i4 * 0.5f);
                        float f2 = f;
                        if (f < 0.0f) {
                            f2 = 0.0f;
                        }
                        ((MobchainBossWaveProcessor) this.f1759a).i.enemy.addEnemyWithPath(mobchainBossCreepEnemy, mobchainBossHeadEnemy.spawnTile, mobchainBossHeadEnemy.graphPath, -1, mobchainBossHeadEnemy.wave, f2);
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MobchainBossWaveProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<MobchainBossWaveProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(MobchainBossWaveProcessor mobchainBossWaveProcessor) {
            this.f1759a = mobchainBossWaveProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Enemy enemy = lastDamage.getEnemy();
            if (enemy.wave == ((MobchainBossWaveProcessor) this.f1759a).f3985a && enemy.type == EnemyType.MOBCHAIN_BOSS_HEAD) {
                ((MobchainBossWaveProcessor) this.f1759a).i.wave.stopSpawningCurrentWave(((MobchainBossWaveProcessor) this.f1759a).f3985a, lastDamage.getTower(), lastDamage.getDamageType());
                for (int i = ((MobchainBossWaveProcessor) this.f1759a).c.size - 1; i >= 0; i--) {
                    Enemy enemy2 = ((Enemy.EnemyReference[]) ((MobchainBossWaveProcessor) this.f1759a).c.items)[i].enemy;
                    if (enemy2 != null && enemy2 != enemy) {
                        ((MobchainBossWaveProcessor) this.f1759a).i.damage.queueEnemyKill(lastDamage.copyFor(enemy2, ((MobchainBossWaveProcessor) this.f1759a).i.damage.obtainRecord()));
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/MobchainBossWaveProcessor$MobchainBossWaveProcessorFactory.class */
    public static class MobchainBossWaveProcessorFactory extends WaveProcessor.WaveProcessorFactory<MobchainBossWaveProcessor> {
        public MobchainBossWaveProcessorFactory() {
            super(BossType.MOBCHAIN);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.WaveProcessor.WaveProcessorFactory
        public MobchainBossWaveProcessor create() {
            return new MobchainBossWaveProcessor((byte) 0);
        }
    }
}

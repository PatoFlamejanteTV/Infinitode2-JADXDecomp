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
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.enemies.bosses.BrootEnemy;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.events.game.GiveDamageToEnemy;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.ui.components.BossHpBar;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/BrootBossWaveProcessor.class */
public final class BrootBossWaveProcessor implements KryoSerializable, WaveProcessor, Listener<EnemySpawn> {
    public static final float NEXT_WAVE_DELAY_MULT = 7.0f;
    public static final float HEALTH_MULT = 1.75f;
    public static final float DEFAULT_SPEED = 0.2f;
    public static final float RAGE_HP_COEFF = 0.25f;
    public static final float RAGE_HP_RESTORE_MULT = 1.5f;

    /* renamed from: a, reason: collision with root package name */
    private Wave f3978a;

    /* renamed from: b, reason: collision with root package name */
    private Enemy.EnemyReference f3979b;
    private boolean c;

    @NAGS
    private BossHpBar d;

    @NAGS
    private Drawable e;
    private OnGiveDamageToEnemy f;
    private GameSystemProvider g;

    /* synthetic */ BrootBossWaveProcessor(byte b2) {
        this();
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f3978a, Wave.class);
        kryo.writeObjectOrNull(output, this.f3979b, Enemy.EnemyReference.class);
        output.writeBoolean(this.c);
        kryo.writeObjectOrNull(output, this.f, OnGiveDamageToEnemy.class);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3978a = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.f3979b = (Enemy.EnemyReference) kryo.readObjectOrNull(input, Enemy.EnemyReference.class);
        this.c = input.readBoolean();
        this.f = (OnGiveDamageToEnemy) kryo.readObjectOrNull(input, OnGiveDamageToEnemy.class);
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    private BrootBossWaveProcessor() {
        this.c = false;
        this.f = new OnGiveDamageToEnemy(this, (byte) 0);
    }

    private Drawable a() {
        if (this.e == null) {
            this.e = Game.i.assetManager.getDrawable("particle-anger").tint(new Color(-445418497));
        }
        return this.e;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final Array<EnemyGroup> generateEnemyGroups(int i, int i2) {
        float calculateDefaultBossWaveCoinsSum = Wave.calculateDefaultBossWaveCoinsSum(i);
        float calculateDefaultBossWaveExpSum = Wave.calculateDefaultBossWaveExpSum(i);
        float calculateDefaultBossWaveScoreSum = Wave.calculateDefaultBossWaveScoreSum(i);
        Array<EnemyGroup> array = new Array<>();
        array.add(new EnemyGroup(EnemyType.BROOT_BOSS, 0.2f, (100.0f + (((float) StrictMath.pow(WaveSystem.getWaveValue(i, i2) * 10.0d, 1.275d)) * 2.2f)) * 1.75f, 1, 0.0f, 0.0f, calculateDefaultBossWaveCoinsSum, calculateDefaultBossWaveExpSum, (int) calculateDefaultBossWaveScoreSum));
        return array;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final Wave setup(GameSystemProvider gameSystemProvider, int i, int i2) {
        this.g = gameSystemProvider;
        this.f3978a = new Wave(i, i2, generateEnemyGroups(i, i2));
        this.f3978a.enemiesCanBeSplitBetweenSpawns = false;
        this.f3978a.enemiesCanHaveRandomSideShifts = false;
        this.f3978a.waveMessage = Config.isHeadless() ? null : Game.i.localeManager.i18n.get("enemy_name_BROOT_BOSS");
        this.f3978a.waveProcessor = this;
        gameSystemProvider.events.getListeners(EnemySpawn.class).addStateAffecting(this).setDescription("Stores boss reference for the processor");
        gameSystemProvider.events.getListeners(GiveDamageToEnemy.class).addStateAffectingWithPriority(this.f, 1000);
        return this.f3978a;
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final float getNextWaveDelayMultiplier() {
        return 7.0f;
    }

    private void b() {
        if (this.d == null && this.g._gameUi != null) {
            this.d = new BossHpBar().setBossName(Game.i.localeManager.i18n.get("enemy_name_BROOT_BOSS")).setMainBarColor(new Color(857478399), new Color(-9155841)).setSmallBarsCount(1).setFirstSmallBarColor(new Color(590230527), new Color(1618840575)).setLabelsColor(new Color(-445418497)).addMark(0.25f).setIcon(Game.i.assetManager.getDrawable("boss-wave-icon-BROOT"));
            this.g._gameUi.mainUi.addBossHpBar(this.d);
        }
        if (this.d != null && this.f3979b != null) {
            BrootEnemy brootEnemy = (BrootEnemy) this.f3979b.enemy;
            this.d.setMainHP(brootEnemy.getHealth(), brootEnemy.maxHealth);
            if (brootEnemy.isInRage()) {
                if (!this.d.isEffectIconExists(a())) {
                    this.d.addEffectIcon(a());
                }
                this.d.setSmallBarOneProgress(1.0f - (brootEnemy.getRageDuration() / 15.0f));
            } else {
                this.d.clearEffectIcons().setSmallBarOneProgress(0.0d);
                if (brootEnemy.wasInRage()) {
                    this.d.clearMarks();
                }
            }
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final void draw(Batch batch, float f) {
        b();
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final void update(float f) {
        if (this.c || this.f3979b == null) {
            return;
        }
        if (this.f3979b.enemy == null) {
            this.c = true;
            c();
        } else {
            ((BrootEnemy) this.f3979b.enemy).updateRageState(f);
        }
    }

    @Override // com.prineside.tdi2.WaveProcessor
    public final boolean isDone() {
        return this.c;
    }

    private void c() {
        this.g.events.getListeners(EnemySpawn.class).remove(this);
        this.g.events.getListeners(GiveDamageToEnemy.class).remove(this.f);
        if (this.d != null) {
            this.g._gameUi.mainUi.removeBossHpBar(this.d);
            this.d = null;
        }
        this.g = null;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(EnemySpawn enemySpawn) {
        if (enemySpawn.getEnemy().wave == this.f3978a) {
            this.f3979b = this.g.enemy.getReference(enemySpawn.getEnemy());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/BrootBossWaveProcessor$OnGiveDamageToEnemy.class */
    public static class OnGiveDamageToEnemy implements KryoSerializable, Listener<GiveDamageToEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private BrootBossWaveProcessor f3980a;

        /* synthetic */ OnGiveDamageToEnemy(BrootBossWaveProcessor brootBossWaveProcessor, byte b2) {
            this(brootBossWaveProcessor);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.f3980a, BrootBossWaveProcessor.class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f3980a = (BrootBossWaveProcessor) kryo.readObjectOrNull(input, BrootBossWaveProcessor.class);
        }

        @Deprecated
        private OnGiveDamageToEnemy() {
        }

        private OnGiveDamageToEnemy(BrootBossWaveProcessor brootBossWaveProcessor) {
            this.f3980a = brootBossWaveProcessor;
        }

        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(GiveDamageToEnemy giveDamageToEnemy) {
            DamageRecord record = giveDamageToEnemy.getRecord();
            if (this.f3980a.f3979b != null && record.getEnemy() == this.f3980a.f3979b.enemy) {
                BrootEnemy brootEnemy = (BrootEnemy) record.getEnemy();
                if (brootEnemy.getHealth() / brootEnemy.maxHealth < 0.25f && !brootEnemy.wasInRage()) {
                    brootEnemy.startRage();
                }
                if (brootEnemy.isInRage()) {
                    giveDamageToEnemy.cancel();
                    float health = brootEnemy.getHealth() + (record.getDamage() * 1.5f);
                    float f = health;
                    if (health > brootEnemy.maxHealth) {
                        f = brootEnemy.maxHealth;
                    }
                    brootEnemy.setHealth(f);
                    brootEnemy.healthRestoredWithDamage();
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/processors/BrootBossWaveProcessor$BrootBossWaveProcessorFactory.class */
    public static class BrootBossWaveProcessorFactory extends WaveProcessor.WaveProcessorFactory<BrootBossWaveProcessor> {
        public BrootBossWaveProcessorFactory() {
            super(BossType.BROOT);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.WaveProcessor.WaveProcessorFactory
        public BrootBossWaveProcessor create() {
            return new BrootBossWaveProcessor((byte) 0);
        }
    }
}

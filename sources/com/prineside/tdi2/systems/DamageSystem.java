package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.events.game.GiveDamageToEnemy;
import com.prineside.tdi2.events.game.MdpsUpdate;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/DamageSystem.class */
public final class DamageSystem extends GameSystem {
    public static final int DAMAGE_DRAIN_MAX_ITERATIONS = 20;
    private double d;

    @NAGS
    private double[] f;

    /* renamed from: a, reason: collision with root package name */
    private DpsCounter[] f2934a = new DpsCounter[20];

    /* renamed from: b, reason: collision with root package name */
    private Array<DamageRecord> f2935b = new Array<>(true, 1, DamageRecord.class);
    private Array<DamageRecord> c = new Array<>(true, 1, DamageRecord.class);

    @NAGS
    private final Array<DamageRecord> e = new Array<>(true, 1, DamageRecord.class);

    @NAGS
    private int g = 0;

    @NAGS
    private EnemyTakeDamage h = new EnemyTakeDamage();

    static {
        TLog.forClass(DamageSystem.class);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2934a);
        kryo.writeObject(output, this.f2935b);
        kryo.writeObject(output, this.c);
        output.writeDouble(this.d);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2934a = (DpsCounter[]) kryo.readObject(input, DpsCounter[].class);
        this.f2935b = (Array) kryo.readObject(input, Array.class);
        this.c = (Array) kryo.readObject(input, Array.class);
        this.d = input.readDouble();
    }

    public final DamageRecord obtainRecord() {
        return this.e.size == 0 ? new DamageRecord() : this.e.pop();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (!Config.isHeadless() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DPS_CHART_ENABLED) != 0.0d) {
            this.f = new double[300];
        }
        for (int i = 0; i < 20; i++) {
            this.f2934a[i] = new DpsCounter((byte) 0);
            this.f2934a[i].timeAccumulator = i * 0.25f;
        }
        if (!this.S.CFG.headless) {
            a();
        }
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.DEBUG_DPS_CHART_DRAW, false, (batch, f, f2, f3) -> {
            drawDebugDpsChart(batch);
        }).setName("Damage-drawDebugDpsChart"));
    }

    public final void queueDamage(DamageRecord damageRecord) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Enemy enemy = damageRecord.getEnemy();
        Ability ability = damageRecord.getAbility();
        if (!enemy.isRegistered() || enemy.id == 0) {
            throw new IllegalArgumentException("Enemy is not registered " + enemy);
        }
        if (ability != null && EnemyType.isBoss(enemy.type)) {
            damageRecord.setDamage(damageRecord.getDamage() * 0.075f);
        }
        if (!damageRecord.isIgnoreTowerEfficiency() && damageRecord.getTower() != null && !damageRecord.getTower().canAttackEnemy(enemy)) {
            return;
        }
        Tower tower = damageRecord.getTower();
        if (tower != null && damageRecord.isCleanForDps()) {
            float min = Math.min(damageRecord.getDamage(), tower.loopAbilityDamageBuffer);
            if (min > 0.0f) {
                tower.loopAbilityDamageBuffer -= min;
                queueDamage(damageRecord.copyFor(damageRecord.getEnemy(), obtainRecord()).setDamage(min).setCleanForDps(false).setAbility(tower.affectedByLoopAbility).setEfficiency(damageRecord.getEfficiency() | 8));
                if (tower.loopAbilityDamageBuffer <= 0.0f) {
                    tower.affectedByLoopAbility = null;
                }
            }
        }
        GiveDamageToEnemy giveDamageToEnemy = new GiveDamageToEnemy(damageRecord);
        this.S.events.trigger(giveDamageToEnemy);
        if (giveDamageToEnemy.isCancelled()) {
            return;
        }
        this.f2935b.add(damageRecord);
    }

    private void a(DamageRecord damageRecord) {
        EnemyTakeDamage enemyTakeDamage;
        boolean z;
        Enemy enemy = damageRecord.getEnemy();
        if (enemy.isRegistered()) {
            float f = enemy.getPosition().x;
            float f2 = enemy.getPosition().y;
            float damage = damageRecord.isLethal() ? Float.MAX_VALUE : damageRecord.getDamage();
            DamageType damageType = damageRecord.getDamageType();
            int efficiency = damageRecord.getEfficiency();
            Tower tower = damageRecord.getTower();
            Ability ability = damageRecord.getAbility();
            Projectile projectile = damageRecord.getProjectile();
            boolean isCleanForDps = damageRecord.isCleanForDps();
            Tower tower2 = damageRecord.isIgnoreTowerEfficiency() ? null : tower;
            float giveDamage = enemy.giveDamage(tower2, damage, damageType);
            if (giveDamage <= 0.0f) {
                return;
            }
            if (DamageType.Efficiency.isNormal(efficiency)) {
                float buffedDamageMultiplier = enemy.getBuffedDamageMultiplier(tower2 == null ? null : tower2.type, damageType);
                if (buffedDamageMultiplier < 0.7f) {
                    efficiency |= 16;
                } else if (buffedDamageMultiplier > 1.3f) {
                    efficiency |= 2;
                }
            }
            damageRecord.setEfficiency(efficiency);
            damageRecord.setFactDamage(giveDamage);
            if (this.h == null) {
                enemyTakeDamage = new EnemyTakeDamage();
                z = false;
            } else {
                enemyTakeDamage = this.h;
                this.h = null;
                z = true;
            }
            this.S.events.trigger(enemyTakeDamage.setup(damageRecord));
            if (z) {
                this.h = enemyTakeDamage.reset();
            }
            if (tower != null) {
                tower.damageGiven += giveDamage;
            }
            if (tower != null && ability == null && isCleanForDps) {
                for (int i = 0; i < 20; i++) {
                    this.f2934a[i].damage += giveDamage;
                }
            }
            if (this.S._particle != null) {
                int i2 = 0;
                if (DamageType.Efficiency.isOverTime(efficiency)) {
                    i2 = 7043 + damageType.ordinal();
                    if (tower != null) {
                        i2 = (i2 * 7867) + tower.id;
                    }
                    if (ability != null) {
                        i2 = (i2 * 6521) + ability.getType().ordinal();
                    }
                }
                this.S._particle.addDamageParticle(f, f2, giveDamage, efficiency, i2);
            }
            if (enemy.isRegistered()) {
                if (enemy.getHealth() <= 0.0f) {
                    queueEnemyKill(damageRecord);
                    return;
                }
                if (tower != null && tower.getTile() != null && damageType == DamageType.BULLET && this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                    this.S._particle.addEnemyHitParticle(tower, enemy, giveDamage, projectile);
                }
            }
        }
    }

    public final double getTowersMaxDps() {
        return this.d;
    }

    public final void setTowersMaxDps(double d) {
        Preconditions.checkArgument(d >= 0.0d && PMath.isFinite(d));
        double d2 = this.d;
        this.d = d;
        this.S.events.trigger(new MdpsUpdate(d2));
    }

    public final void queueEnemyKill(DamageRecord damageRecord) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (!damageRecord.getEnemy().isRegistered()) {
            throw new IllegalArgumentException("Enemy is not registered");
        }
        EnemyDie enemyDie = new EnemyDie(damageRecord);
        this.S.events.trigger(enemyDie);
        if (enemyDie.isCancelled()) {
            return;
        }
        this.c.add(damageRecord);
    }

    private void b(DamageRecord damageRecord) {
        TextureRegion texture;
        float f;
        Enemy enemy = damageRecord.getEnemy();
        if (enemy.isRegistered()) {
            enemy.onPreDie();
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect breakParticle = enemy.getBreakParticle();
                breakParticle.setPosition(enemy.drawPosition.x, enemy.drawPosition.y);
                this.S._particle.addLimitedParticle(breakParticle, LimitedParticleType.ENEMY_DEAD, enemy.drawPosition.x, enemy.drawPosition.y);
                if (this.S.enemy.isEmojiEnemies()) {
                    texture = enemy.getEmojiTexture();
                    f = 0.0f;
                } else {
                    texture = enemy.getTexture();
                    f = enemy.drawAngle;
                }
                this.S._particle.addRegularShatterParticle(texture, enemy.drawPosition.x, enemy.drawPosition.y, texture.getRegionWidth(), f, enemy.drawScale);
            }
            this.S.map.despawnEnemy(enemy);
        }
    }

    public final void drainDamageAndKillQueue() {
        int i = this.f2935b.size;
        if (i != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                a(this.f2935b.items[i2]);
            }
        }
        int i3 = this.c.size;
        if (i3 != 0) {
            for (int i4 = 0; i4 < i3; i4++) {
                b(this.c.items[i4]);
            }
            this.c.removeRange(0, i3 - 1);
        }
        if (i != 0) {
            for (int i5 = 0; i5 < i; i5++) {
                this.f2935b.items[i5].reset();
            }
            this.e.addAll(this.f2935b.items, 0, i);
            this.f2935b.removeRange(0, i - 1);
        }
    }

    public final void drainDamageAndKillQueueTillEmpty() {
        for (int i = 0; i < 20; i++) {
            drainDamageAndKillQueue();
            if (this.f2935b.size == 0 && this.c.size == 0) {
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        drainDamageAndKillQueueTillEmpty();
        double d = 0.0d;
        double d2 = 0.0d;
        for (DpsCounter dpsCounter : this.f2934a) {
            dpsCounter.timeAccumulator += f;
            if (dpsCounter.timeAccumulator > 5.0f) {
                if (dpsCounter.damage > dpsCounter.maxDamage) {
                    dpsCounter.maxDamage = dpsCounter.damage;
                }
                if (d2 < dpsCounter.damage) {
                    d2 = dpsCounter.damage;
                }
                dpsCounter.timeAccumulator = 0.0f;
                dpsCounter.damage = 0.0d;
            }
            if (d < dpsCounter.maxDamage) {
                d = dpsCounter.maxDamage;
            }
        }
        double d3 = d / 5.0d;
        if (d3 > this.d) {
            setTowersMaxDps(d3);
        }
        if (this.f != null) {
            double d4 = d2 / 5.0d;
            if (d4 > 0.0d) {
                double[] dArr = this.f;
                int i = this.g;
                this.g = i + 1;
                dArr[i] = d4;
                if (this.g == this.f.length) {
                    this.g = 0;
                }
            }
        }
    }

    public final void drawDebugDpsChart(Batch batch) {
        if (this.f != null) {
            Game.i.renderingManager.stopAnyBatchDrawing();
            Game.i.renderingManager.shapeRenderer.setProjectionMatrix(Game.i.uiManager.viewport.getCamera().projection);
            Game.i.renderingManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Game.i.renderingManager.shapeRenderer.setColor(MaterialColor.ORANGE.P500.cpy());
            Game.i.renderingManager.shapeRenderer.getColor().f889a = 0.5f;
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
            int i = 0;
            float f = (-Game.i.uiManager.viewport.getWorldHeight()) * 0.5f;
            double d = 300.0d / this.d;
            for (int i2 = this.g; i2 < this.f.length; i2++) {
                Game.i.renderingManager.shapeRenderer.rect((i * 3) - 450.0f, f, 2.0f, (float) (this.f[i2] * d));
                i++;
            }
            for (int i3 = 0; i3 < this.g; i3++) {
                Game.i.renderingManager.shapeRenderer.rect((i * 3) - 450.0f, f, 2.0f, (float) (this.f[i3] * d));
                i++;
            }
            Game.i.renderingManager.shapeRenderer.end();
            batch.begin();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Damage";
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/DamageSystem$DpsCounter.class */
    public static class DpsCounter implements KryoSerializable {
        public double damage;
        public float timeAccumulator;
        public double maxDamage;

        /* synthetic */ DpsCounter(byte b2) {
            this();
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeDouble(this.damage);
            output.writeFloat(this.timeAccumulator);
            output.writeDouble(this.maxDamage);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.damage = input.readDouble();
            this.timeAccumulator = input.readFloat();
            this.maxDamage = input.readDouble();
        }

        private DpsCounter() {
        }
    }
}

package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import java.util.Arrays;
import java.util.Comparator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossEnemy.class */
public final class MetaphorBossEnemy extends Enemy {
    public int creepCount;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private LegConfig[] f1902a;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private LegConfig[] f1903b;

    @NAGS
    private ParticleEffectPool.PooledEffect c;
    private static final Comparator<LegConfig> d = (legConfig, legConfig2) -> {
        return Float.compare(legConfig2.f1904a, legConfig.f1904a);
    };

    /* synthetic */ MetaphorBossEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.creepCount, true);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.creepCount = input.readVarInt(true);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossRelated() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossMainBodyPart() {
        return true;
    }

    private void a() {
        this.f1902a = new LegConfig[8];
        this.f1903b = new LegConfig[8];
        this.f1902a[0] = new LegConfig(this, 0, -10.0f, 14.0f, 25.0f, 75.0f, 1.2f);
        this.f1902a[1] = new LegConfig(this, 1, 10.0f, 14.0f, 335.0f, 285.0f, 1.2f);
        this.f1902a[2] = new LegConfig(this, 0, -9.0f, 2.0f, 63.0f, 113.0f, 1.0f);
        this.f1902a[3] = new LegConfig(this, 1, 9.0f, 2.0f, 297.0f, 247.0f, 1.0f);
        this.f1902a[4] = new LegConfig(this, 0, -11.0f, -6.0f, 101.0f, 129.0f, 0.85f);
        this.f1902a[5] = new LegConfig(this, 1, 11.0f, -6.0f, 259.0f, 231.0f, 0.85f);
        this.f1902a[6] = new LegConfig(this, 0, -8.0f, -14.0f, 133.0f, 167.0f, 0.7f);
        this.f1902a[7] = new LegConfig(this, 1, 8.0f, -14.0f, 227.0f, 193.0f, 0.7f);
        System.arraycopy(this.f1902a, 0, this.f1903b, 0, this.f1903b.length);
    }

    private MetaphorBossEnemy() {
        super(EnemyType.METAPHOR_BOSS);
        this.creepCount = 0;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean canHaveRandomSideShift() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        float f = 1.0f - (this.creepCount * 0.02f);
        float f2 = f;
        if (f < 0.0f) {
            f2 = 0.0f;
        }
        return super.getBuffedDamageMultiplier(towerType, damageType) * f2;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getAbilityVulnerability(AbilityType abilityType) {
        float abilityVulnerability = super.getAbilityVulnerability(abilityType);
        if (abilityType == AbilityType.BALL_LIGHTNING) {
            return abilityVulnerability * 0.1f;
        }
        return abilityVulnerability;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 100.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f, Color color) {
        if (this.c == null && Game.i.settingsManager.isParticlesDrawing()) {
            this.c = Game.i.enemyManager.F.METAPHOR_BOSS.c.obtain();
            this.c.start();
        }
        if (this.c != null) {
            this.c.setPosition(this.drawPosition.x, this.drawPosition.y);
            this.c.draw(batch, f);
        }
        if (this.f1902a == null) {
            a();
        }
        for (LegConfig legConfig : this.f1902a) {
            if (legConfig != null) {
                legConfig.a(f * this.S.gameState.getGameSpeed(), this.drawPosition.x, this.drawPosition.y, this.drawAngle);
            }
        }
        if (this.f1902a[0].f1905b > 0.75f && this.f1902a[1].f1905b > 0.75f) {
            this.f1902a[FastRandom.getInt(2)].a(this.drawPosition.x, this.drawPosition.y, this.drawAngle);
        }
        Arrays.sort(this.f1903b, d);
        for (LegConfig legConfig2 : this.f1903b) {
            if (legConfig2 != null) {
                legConfig2.a(batch, this.drawPosition.x, this.drawPosition.y, this.drawAngle, this.drawScale);
            }
        }
        float regionWidth = Game.i.enemyManager.F.METAPHOR_BOSS.f1906a.getRegionWidth() * 1.8f * (this.drawScale + (MathUtils.sin(this.existsTime * 4.0f) * 0.05f));
        batch.draw(Game.i.enemyManager.F.METAPHOR_BOSS.f1906a, this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth * 0.5f, regionWidth * 0.5f, regionWidth, regionWidth, 1.0f, 1.0f, this.drawAngle);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossEnemy$LegConfig.class */
    public class LegConfig {
        private float l;
        private float m;
        private float n;
        private float o;
        private float c = 0.5f;
        private float d = 1.3f;
        private float e = 44.979996f;
        private float f = 153.4f;
        private float g = ((this.c * this.f) * this.c) * this.f;
        private float h = ((this.d * this.f) * this.d) * this.f;
        private Vector2 i = new Vector2();
        private Vector2 j = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE);
        private Vector2 k = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE);
        private float p = 0.0f;

        /* renamed from: a, reason: collision with root package name */
        float f1904a = 1.0f;

        /* renamed from: b, reason: collision with root package name */
        float f1905b = 0.0f;
        private final Vector2 q = new Vector2();

        LegConfig(MetaphorBossEnemy metaphorBossEnemy, int i, float f, float f2, float f3, float f4, float f5) {
            this.i.set(f, f2);
            this.l = f3;
            this.m = f4;
            this.o = f5;
            this.n = PMath.getDistanceBetweenAngles(f3, f4);
        }

        private void a(float f, float f2, float f3, float f4, float f5) {
            float f6 = this.l + ((this.m - this.l) * f4);
            Vector2 vector2 = new Vector2();
            PMath.getPointByAngleFromPoint(0.0f, 0.0f, f6, this.f * f5, vector2);
            this.j.set(this.i).add(vector2).rotateDeg(f3).add(f, f2);
            b(f, f2, f3);
            this.p = 1.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(float f, float f2, float f3) {
            a(f, f2, f3, 0.001f + (FastRandom.getFloat() * 0.1f), this.o);
        }

        private float b(float f, float f2, float f3) {
            this.q.set(this.i);
            this.q.rotate(f3);
            return PMath.getAngleBetweenPoints(f + this.q.x, f2 + this.q.y, this.j.x, this.j.y) - f3;
        }

        private float a(float f) {
            float distanceBetweenAngles = PMath.getDistanceBetweenAngles(this.l, f);
            float f2 = distanceBetweenAngles / this.n;
            if (this.n < 0.0f) {
                f2 = (-distanceBetweenAngles) / (-this.n);
            }
            return f2;
        }

        private Vector2 c(float f, float f2, float f3) {
            this.q.set(this.i);
            this.q.rotate(f3);
            this.q.add(f, f2);
            return this.q;
        }

        final void a(float f, float f2, float f3, float f4) {
            float f5 = -1.0f;
            float f6 = this.o;
            float a2 = a(b(f2, f3, f4));
            if (a2 < 0.0f) {
                f5 = 0.999f - (FastRandom.getFloat() * 0.1f);
            } else if (a2 > 1.0f) {
                f5 = 0.001f + (FastRandom.getFloat() * 0.1f);
            }
            if (this.j.x == Float.MIN_VALUE) {
                f5 = 0.002f + (FastRandom.getFloat() * 0.996f);
            }
            if (f5 == -1.0f) {
                Vector2 c = c(f2, f3, f4);
                float squareDistanceBetweenPoints = PMath.getSquareDistanceBetweenPoints(this.j.x, this.j.y, c.x, c.y);
                if (squareDistanceBetweenPoints < this.g) {
                    f5 = a2 * 0.2f;
                } else if (squareDistanceBetweenPoints > this.h) {
                    f5 = a2 * 0.2f;
                }
            }
            if (f5 != -1.0f) {
                a(f2, f3, f4, f5, f6);
                this.f1905b = a(b(f2, f3, f4));
            } else {
                this.f1905b = a2;
            }
            if (this.k.x == Float.MIN_VALUE) {
                this.k.set(this.j);
            } else if (this.p != 0.0f) {
                this.p -= f * 5.0f;
                if (this.p <= 0.0f) {
                    this.k.set(this.j);
                    this.p = 0.0f;
                } else {
                    this.k.lerp(this.j, 1.0f - this.p);
                }
            }
            Vector2 c2 = c(f2, f3, f4);
            this.f1904a = PMath.getDistanceBetweenPoints(c2.x, c2.y, this.k.x, this.k.y) / this.f;
        }

        final void a(Batch batch, float f, float f2, float f3, float f4) {
            Vector2 c = c(f, f2, f3);
            DrawUtils.texturedLineB(batch, Game.i.enemyManager.F.METAPHOR_BOSS.f1907b, c.x, c.y, c.x + ((this.k.x - c.x) * f4), c.y + ((this.k.y - c.y) * f4), this.e);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossEnemy$MetaphorBossEnemyFactory.class */
    public static class MetaphorBossEnemyFactory extends Enemy.Factory<MetaphorBossEnemy> {
        private TextureRegion d;

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1906a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f1907b;
        ParticleEffectPool c;

        public MetaphorBossEnemyFactory() {
            super(EnemyType.METAPHOR_BOSS);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.d;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.d;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.d = Game.i.assetManager.getTextureRegion("enemy-type-boss-metaphor");
            this.f1906a = Game.i.assetManager.getTextureRegion("enemy-type-boss-metaphor-body");
            this.f1907b = Game.i.assetManager.getTextureRegion("enemy-type-boss-metaphor-leg");
            this.c = Game.i.assetManager.getParticleEffectPool("smoke-cloud.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public MetaphorBossEnemy create() {
            return new MetaphorBossEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.RED.P500;
        }
    }
}

package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/BrootEnemy.class */
public final class BrootEnemy extends Enemy {
    public static final float RAGE_DURATION = 15.0f;
    public static final float RAGE_SPEED_MULT = 0.3f;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1890a;

    /* renamed from: b, reason: collision with root package name */
    private float f1891b;

    @NAGS
    private float c;

    @NAGS
    private ParticleEffectPool.PooledEffect d;

    /* synthetic */ BrootEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.f1890a);
        output.writeFloat(this.f1891b);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1890a = input.readBoolean();
        this.f1891b = input.readFloat();
    }

    private BrootEnemy() {
        super(EnemyType.BROOT_BOSS);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossRelated() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossMainBodyPart() {
        return true;
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        if (this.d != null) {
            this.d.allowCompletion();
            this.d = null;
        }
        super.setUnregistered();
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
    public final float getSize() {
        return 51.2f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSquaredSize() {
        return 2621.4402f;
    }

    public final void startRage() {
        this.f1890a = true;
        this.f1891b = 0.0f;
        if (this.d != null) {
            this.d.allowCompletion();
            this.d = null;
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S._particle.willParticleBeSkipped()) {
            this.d = Game.i.enemyManager.F.BROOT_BOSS.f1892a.obtain();
            this.S._particle.addParticle(this.d, true);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedSpeed() {
        float buffedSpeed = super.getBuffedSpeed();
        if (isInRage()) {
            buffedSpeed *= 0.3f;
        }
        return buffedSpeed;
    }

    public final boolean wasInRage() {
        return this.f1890a;
    }

    public final boolean isInRage() {
        return this.f1890a && this.f1891b < 15.0f;
    }

    public final float getRageDuration() {
        return this.f1891b;
    }

    public final void updateRageState(float f) {
        this.c += f;
        if (this.f1890a) {
            this.f1891b += f;
            if (this.f1891b >= 15.0f && this.d != null) {
                this.d.allowCompletion();
                this.d = null;
            }
        }
        if (isInRage() && this.d != null) {
            this.d.setPosition(getPosition().x, getPosition().y);
        }
    }

    public final void healthRestoredWithDamage() {
        if (this.c > 0.1f) {
            this.c = 0.0f;
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect obtain = Game.i.enemyManager.F.BROOT_BOSS.f1893b.obtain();
                obtain.setPosition(getPosition().x, getPosition().y);
                this.S._particle.addParticle(obtain, true);
            }
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 100.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/BrootEnemy$BrootEnemyFactory.class */
    public static class BrootEnemyFactory extends Enemy.Factory<BrootEnemy> {
        private TextureRegion c;

        /* renamed from: a, reason: collision with root package name */
        ParticleEffectPool f1892a;

        /* renamed from: b, reason: collision with root package name */
        ParticleEffectPool f1893b;

        public BrootEnemyFactory() {
            super(EnemyType.BROOT_BOSS);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-boss-broot");
            this.f1892a = Game.i.assetManager.getParticleEffectPool("anger.prt");
            this.f1893b = Game.i.assetManager.getParticleEffectPool("regeneration-once.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public BrootEnemy create() {
            return new BrootEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.DEEP_ORANGE.P500;
        }
    }
}

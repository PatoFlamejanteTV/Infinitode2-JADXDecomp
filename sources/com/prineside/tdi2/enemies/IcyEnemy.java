package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/IcyEnemy.class */
public final class IcyEnemy extends Enemy {
    public float shieldHealth;
    public float shieldMaxHealth;

    /* synthetic */ IcyEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.shieldHealth);
        output.writeFloat(this.shieldMaxHealth);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.shieldHealth = input.readFloat();
        this.shieldMaxHealth = input.readFloat();
    }

    private IcyEnemy() {
        super(EnemyType.ICY);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffVulnerability(BuffType buffType) {
        if (buffType != BuffType.STUN && buffType != BuffType.SNOWBALL && buffType != BuffType.BLIZZARD && buffType != BuffType.FREEZING) {
            return super.getBuffVulnerability(buffType);
        }
        if (this.shieldHealth <= 0.0f) {
            return super.getBuffVulnerability(buffType);
        }
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void setMaxHealth(float f) {
        super.setMaxHealth(f);
        this.shieldHealth = f * 0.25f;
        this.shieldMaxHealth = this.shieldHealth;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 0.5f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float giveDamage(Tower tower, float f, DamageType damageType) {
        float health = getHealth();
        float giveDamage = super.giveDamage(tower, f, damageType);
        if (this.shieldHealth > 0.0f) {
            float f2 = giveDamage;
            if (damageType != DamageType.BULLET && damageType != DamageType.FIRE) {
                f2 = giveDamage * 0.25f;
            }
            this.shieldHealth -= f2;
            if (this.shieldHealth < 0.0f) {
                this.shieldHealth = 0.0f;
            }
            setHealth(health);
        }
        return giveDamage;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawHealth(Batch batch) {
        super.drawHealth(batch);
        if (this.shieldHealth != 0.0f) {
            float f = this.shieldHealth / this.shieldMaxHealth;
            batch.setColor(HEALTH_BAR_BACKGROUND_COLOR);
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 28.0f, this.drawPosition.y + 58.0f, 56.0f, 8.0f);
            batch.setColor(MaterialColor.LIGHT_BLUE.P500);
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 26.0f, this.drawPosition.y + 60.0f, (int) (52.0f * f), 4.0f);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/IcyEnemy$IcyEnemyFactory.class */
    public static class IcyEnemyFactory extends Enemy.Factory<IcyEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1872a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1873b;
        private TextureRegion c;

        public IcyEnemyFactory() {
            super(EnemyType.ICY);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1872a = Game.i.assetManager.getTextureRegion("enemy-type-icy");
            this.f1873b = Game.i.assetManager.getTextureRegion("enemy-type-icy-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-icy-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1872a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1873b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public IcyEnemy create() {
            return new IcyEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.BLUE.P500;
        }
    }
}

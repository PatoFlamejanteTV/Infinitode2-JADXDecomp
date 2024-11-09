package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/LightEnemy.class */
public final class LightEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    private float f1878a;

    /* renamed from: b, reason: collision with root package name */
    private DamageType f1879b;

    /* synthetic */ LightEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f1878a);
        kryo.writeObjectOrNull(output, this.f1879b, DamageType.class);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1878a = input.readFloat();
        this.f1879b = (DamageType) kryo.readObjectOrNull(input, DamageType.class);
    }

    private LightEnemy() {
        super(EnemyType.LIGHT);
        this.f1878a = 10.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float giveDamage(Tower tower, float f, DamageType damageType) {
        float giveDamage = super.giveDamage(tower, f, damageType);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY && giveDamage != 0.0f && this.f1878a > 10.0f) {
            this.f1879b = damageType;
            this.f1878a = 0.0f;
        }
        return giveDamage;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        float buffedDamageMultiplier = super.getBuffedDamageMultiplier(towerType, damageType);
        if (this.f1879b != null && this.f1879b == damageType) {
            buffedDamageMultiplier *= 0.25f;
        }
        return buffedDamageMultiplier;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void update(float f) {
        super.update(f);
        this.f1878a += f;
        if (this.f1879b != null && this.f1878a > 6.0f) {
            this.f1879b = null;
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        if (this.f1879b != null) {
            TextureRegion damageTypeIcon = Game.i.enemyManager.getDamageTypeIcon(this.f1879b);
            batch.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            batch.draw(damageTypeIcon, this.drawPosition.x - 10.0f, this.drawPosition.y - 14.0f, 24.0f, 24.0f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.draw(damageTypeIcon, this.drawPosition.x - 12.0f, this.drawPosition.y - 12.0f, 24.0f, 24.0f);
            if (this.f1878a < 0.5f) {
                float f2 = this.f1878a / 0.5f;
                float f3 = 24.0f + (42.0f * f2);
                batch.setColor(1.0f, 1.0f, 1.0f, 1.0f - f2);
                batch.draw(damageTypeIcon, this.drawPosition.x - (f3 * 0.5f), this.drawPosition.y - (f3 * 0.5f), f3, f3);
            }
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/LightEnemy$LightEnemyFactory.class */
    public static class LightEnemyFactory extends Enemy.Factory<LightEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1880a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1881b;
        private TextureRegion c;

        public LightEnemyFactory() {
            super(EnemyType.LIGHT);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1880a = Game.i.assetManager.getTextureRegion("enemy-type-light");
            this.f1881b = Game.i.assetManager.getTextureRegion("enemy-type-light-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-light-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1880a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1881b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public LightEnemy create() {
            return new LightEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.CYAN.P500;
        }
    }
}

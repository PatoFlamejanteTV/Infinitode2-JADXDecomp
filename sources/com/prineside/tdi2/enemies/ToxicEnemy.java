package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.RegenerationBuff;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/ToxicEnemy.class */
public final class ToxicEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    private float f1886a;

    /* renamed from: b, reason: collision with root package name */
    private float f1887b;

    /* synthetic */ ToxicEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f1886a);
        output.writeFloat(this.f1887b);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1886a = input.readFloat();
        this.f1887b = input.readFloat();
    }

    private ToxicEnemy() {
        super(EnemyType.TOXIC);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float giveDamage(Tower tower, float f, DamageType damageType) {
        this.f1886a = 0.0f;
        this.f1887b = 9001.0f;
        return super.giveDamage(tower, f, damageType);
    }

    @Override // com.prineside.tdi2.Enemy
    public final void update(float f) {
        super.update(f);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY) {
            this.f1886a += f;
            this.f1887b += f;
            if (this.f1886a > 3.5f && getHealth() != this.maxHealth && this.f1887b > 0.25f) {
                RegenerationBuff regenerationBuff = new RegenerationBuff();
                regenerationBuff.setup(0.5f, 5.0f, this.maxHealth * 0.05f, this.S.enemy.getReference(this));
                this.S.buff.P_REGENERATION.addBuff((Enemy) this, regenerationBuff);
                this.f1887b = 0.0f;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/ToxicEnemy$ToxicEnemyFactory.class */
    public static class ToxicEnemyFactory extends Enemy.Factory<ToxicEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1888a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1889b;
        private TextureRegion c;

        public ToxicEnemyFactory() {
            super(EnemyType.TOXIC);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1888a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1889b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1888a = Game.i.assetManager.getTextureRegion("enemy-type-toxic");
            this.f1889b = Game.i.assetManager.getTextureRegion("enemy-type-toxic-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-toxic-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public ToxicEnemy create() {
            return new ToxicEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_GREEN.P500;
        }
    }
}

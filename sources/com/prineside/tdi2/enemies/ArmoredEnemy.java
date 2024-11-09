package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.buffs.ArmorBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/ArmoredEnemy.class */
public final class ArmoredEnemy extends Enemy {
    public static final byte AURA_CHECK_FRAME_INTERVAL = 7;

    /* renamed from: a, reason: collision with root package name */
    private static final Color f1855a = MaterialColor.TEAL.P500;

    /* renamed from: b, reason: collision with root package name */
    private byte f1856b;

    /* synthetic */ ArmoredEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1856b);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1856b = input.readByte();
    }

    private ArmoredEnemy() {
        super(EnemyType.ARMORED);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void update(float f) {
        super.update(f);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY) {
            this.f1856b = (byte) (this.f1856b + 1);
            if (this.f1856b == 7) {
                this.f1856b = (byte) 0;
                float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime() * 7.1f;
                this.S.map.getEnemiesInCircleV(getPosition(), 128.0f, (enemyReference, f2, f3, f4) -> {
                    Enemy enemy = enemyReference.enemy;
                    if (enemy == null || enemy.type == EnemyType.ARMORED) {
                        return true;
                    }
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.ARMOR);
                    boolean z = false;
                    if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                        ((ArmorBuff) buffsByTypeOrNull.first()).duration = tickRateDeltaTime;
                        z = true;
                    }
                    if (!z) {
                        ArmorBuff armorBuff = new ArmorBuff();
                        armorBuff.setup(tickRateDeltaTime, tickRateDeltaTime);
                        this.S.buff.P_ARMOR.addBuff(enemy, armorBuff);
                        return true;
                    }
                    return true;
                });
            }
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatchAdditive(Batch batch, float f) {
        super.drawBatchAdditive(batch, f);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY) {
            batch.setColor(f1855a);
            batch.draw(Game.i.enemyManager.F.ARMORED.f1858a, this.drawPosition.x - 128.0f, this.drawPosition.y - 128.0f, 256.0f, 256.0f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/ArmoredEnemy$ArmoredEnemyFactory.class */
    public static class ArmoredEnemyFactory extends Enemy.Factory<ArmoredEnemy> {

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1857b;
        private TextureRegion c;

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1858a;
        private TextureRegion d;

        public ArmoredEnemyFactory() {
            super(EnemyType.ARMORED);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1857b = Game.i.assetManager.getTextureRegion("enemy-type-armored");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-armored-hl");
            this.f1858a = Game.i.assetManager.getTextureRegion("aura-range");
            this.d = Game.i.assetManager.getTextureRegion("enemy-type-armored-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1857b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.d;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public ArmoredEnemy create() {
            return new ArmoredEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.TEAL.P500;
        }
    }
}

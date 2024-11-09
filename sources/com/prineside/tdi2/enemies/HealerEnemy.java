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
import com.prineside.tdi2.buffs.RegenerationBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/HealerEnemy.class */
public final class HealerEnemy extends Enemy {
    public static final byte AURA_CHECK_FRAME_INTERVAL = 7;

    /* renamed from: a, reason: collision with root package name */
    private static final Color f1865a = MaterialColor.RED.P500;

    /* renamed from: b, reason: collision with root package name */
    private byte f1866b;

    /* synthetic */ HealerEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1866b);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1866b = input.readByte();
    }

    private HealerEnemy() {
        super(EnemyType.HEALER);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void update(float f) {
        super.update(f);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY) {
            this.f1866b = (byte) (this.f1866b + 1);
            Enemy.EnemyReference reference = this.S.enemy.getReference(this);
            if (this.f1866b == 7) {
                this.f1866b = (byte) 0;
                float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime() * 7.1f;
                this.S.map.getEnemiesInCircleV(getPosition(), 128.0f, (enemyReference, f2, f3, f4) -> {
                    Enemy enemy = enemyReference.enemy;
                    if (enemy == null || enemy.type == EnemyType.HEALER) {
                        return true;
                    }
                    float min = StrictMath.min(enemy.maxHealth, this.maxHealth) * 0.05f;
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.REGENERATION);
                    boolean z = false;
                    if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                        int i = 0;
                        while (true) {
                            if (i >= buffsByTypeOrNull.size) {
                                break;
                            }
                            RegenerationBuff regenerationBuff = (RegenerationBuff) buffsByTypeOrNull.get(i);
                            if (regenerationBuff.issuer != reference) {
                                i++;
                            } else {
                                regenerationBuff.duration = tickRateDeltaTime;
                                regenerationBuff.hpPerSecond = min;
                                z = true;
                                break;
                            }
                        }
                    }
                    if (!z) {
                        RegenerationBuff regenerationBuff2 = new RegenerationBuff();
                        regenerationBuff2.setup(tickRateDeltaTime, tickRateDeltaTime, StrictMath.min(enemy.maxHealth, this.maxHealth) * 0.05f, reference);
                        this.S.buff.P_REGENERATION.addBuff(enemy, regenerationBuff2);
                        return true;
                    }
                    return true;
                });
            }
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatchAdditive(Batch batch, float f) {
        super.drawBatchAdditive(batch, f);
        if (this.S.gameState.difficultyMode != DifficultyMode.EASY) {
            batch.setColor(f1865a);
            batch.draw(Game.i.enemyManager.F.HEALER.f1868a, this.drawPosition.x - 128.0f, this.drawPosition.y - 128.0f, 256.0f, 256.0f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/HealerEnemy$HealerEnemyFactory.class */
    public static class HealerEnemyFactory extends Enemy.Factory<HealerEnemy> {

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1867b;

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1868a;
        private TextureRegion c;
        private TextureRegion d;

        public HealerEnemyFactory() {
            super(EnemyType.HEALER);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1867b = Game.i.assetManager.getTextureRegion("enemy-type-healer");
            this.f1868a = Game.i.assetManager.getTextureRegion("aura-range");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-healer-hl");
            this.d = Game.i.assetManager.getTextureRegion("enemy-type-healer-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1867b;
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
        public HealerEnemy create() {
            return new HealerEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.RED.P500;
        }
    }
}

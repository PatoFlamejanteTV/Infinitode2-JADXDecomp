package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/FighterEnemy.class */
public final class FighterEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1862a;

    /* synthetic */ FighterEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.f1862a);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1862a = input.readBoolean();
    }

    private FighterEnemy() {
        super(EnemyType.FIGHTER);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSize() {
        if (this.f1862a) {
            return 17.92f;
        }
        return 30.72f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSquaredSize() {
        if (this.f1862a) {
            return 943.7184f;
        }
        return 943.7184f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getTexture() {
        if (this.f1862a) {
            return Game.i.enemyManager.F.FIGHTER.f1864b;
        }
        return Game.i.enemyManager.F.FIGHTER.f1863a;
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getEmojiTexture() {
        if (this.f1862a) {
            return Game.i.enemyManager.F.FIGHTER.f1864b;
        }
        return Game.i.enemyManager.F.FIGHTER.getEmojiTexture();
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getHighlightTexture() {
        if (this.f1862a) {
            return Game.i.enemyManager.F.FIGHTER.f1864b;
        }
        return Game.i.enemyManager.F.FIGHTER.c;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return this.f1862a ? 0.5f : 2.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void onPreDie() {
        super.onPreDie();
        if (!this.f1862a && this.graphPath != null) {
            for (int i = 0; i < 3; i++) {
                FighterEnemy fighterEnemy = (FighterEnemy) Game.i.enemyManager.getFactory(EnemyType.FIGHTER).obtain();
                fighterEnemy.f1862a = true;
                fighterEnemy.setMaxHealth(this.maxHealth * 0.5f);
                fighterEnemy.bounty = this.bounty * 0.33f;
                fighterEnemy.setKillExp(getKillExp() * 0.33f);
                fighterEnemy.killScore = (int) (this.killScore * 0.33f);
                fighterEnemy.setSpeed(getSpeed());
                fighterEnemy.setHealth(this.maxHealth * 0.33f);
                fighterEnemy.notAffectsWaveKillCounter.addReason("FighterChild");
                this.S.enemy.addEnemyWithPath(fighterEnemy, this.spawnTile, this.graphPath, -1, this.wave, this.passedTiles);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/FighterEnemy$FighterEnemyFactory.class */
    public static class FighterEnemyFactory extends Enemy.Factory<FighterEnemy> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1863a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f1864b;
        TextureRegion c;
        private TextureRegion d;

        public FighterEnemyFactory() {
            super(EnemyType.FIGHTER);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1863a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.d;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1863a = Game.i.assetManager.getTextureRegion("enemy-type-fighter");
            this.f1864b = Game.i.assetManager.getTextureRegion("enemy-type-fighter-small");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-fighter-hl");
            this.d = Game.i.assetManager.getTextureRegion("enemy-type-fighter-emj");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public FighterEnemy create() {
            return new FighterEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.PURPLE.P500;
        }
    }
}

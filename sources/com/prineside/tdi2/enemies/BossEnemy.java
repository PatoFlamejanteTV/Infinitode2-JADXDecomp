package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/BossEnemy.class */
public final class BossEnemy extends Enemy {
    /* synthetic */ BossEnemy(byte b2) {
        this();
    }

    private BossEnemy() {
        super(EnemyType.BOSS);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 50.0f;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/BossEnemy$BossEnemyFactory.class */
    public static class BossEnemyFactory extends Enemy.Factory<BossEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1859a;

        public BossEnemyFactory() {
            super(EnemyType.BOSS);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1859a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1859a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1859a = Game.i.assetManager.getTextureRegion("enemy-type-boss");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public BossEnemy create() {
            return new BossEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.YELLOW.P500;
        }
    }
}

package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/StrongEnemy.class */
public final class StrongEnemy extends Enemy {
    /* synthetic */ StrongEnemy(byte b2) {
        this();
    }

    private StrongEnemy() {
        super(EnemyType.STRONG);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/StrongEnemy$StrongEnemyFactory.class */
    public static class StrongEnemyFactory extends Enemy.Factory<StrongEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1884a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1885b;
        private TextureRegion c;

        public StrongEnemyFactory() {
            super(EnemyType.STRONG);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1884a = Game.i.assetManager.getTextureRegion("enemy-type-strong");
            this.f1885b = Game.i.assetManager.getTextureRegion("enemy-type-strong-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-strong-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1884a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1885b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public StrongEnemy create() {
            return new StrongEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.DEEP_ORANGE.P500;
        }
    }
}

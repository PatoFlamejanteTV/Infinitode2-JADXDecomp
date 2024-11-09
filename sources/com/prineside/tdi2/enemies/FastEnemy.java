package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/FastEnemy.class */
public final class FastEnemy extends Enemy {
    /* synthetic */ FastEnemy(byte b2) {
        this();
    }

    private FastEnemy() {
        super(EnemyType.FAST);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedSpeed() {
        float f = 1.0f;
        if (this.buffsByType != null) {
            f = 1.0f - (this.buffsByType[BuffType.POISON.ordinal()].size * 0.07f);
        }
        if (f < 0.25f) {
            f = 0.25f;
        }
        return super.getBuffedSpeed() * f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/FastEnemy$FastEnemyFactory.class */
    public static class FastEnemyFactory extends Enemy.Factory<FastEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1860a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1861b;
        private TextureRegion c;

        public FastEnemyFactory() {
            super(EnemyType.FAST);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1860a = Game.i.assetManager.getTextureRegion("enemy-type-fast");
            this.f1861b = Game.i.assetManager.getTextureRegion("enemy-type-fast-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-fast-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1860a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1861b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public FastEnemy create() {
            return new FastEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.AMBER.P500;
        }
    }
}

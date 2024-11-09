package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/HeliEnemy.class */
public final class HeliEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private float f1869a;

    /* synthetic */ HeliEnemy(byte b2) {
        this();
    }

    private HeliEnemy() {
        super(EnemyType.HELI);
        this.f1869a = 0.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedSpeed() {
        float buffedSpeed = super.getBuffedSpeed();
        if (this.buffsAppliedByType != null && this.buffsAppliedByType[BuffType.BURN.ordinal()]) {
            buffedSpeed *= 0.65f;
        }
        return buffedSpeed;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        this.f1869a += f;
        float f2 = this.drawAngle;
        this.drawAngle = ((this.f1869a % 0.5f) / 0.5f) * 360.0f;
        super.drawBatch(batch, f);
        this.drawAngle = f2;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/HeliEnemy$HeliEnemyFactory.class */
    public static class HeliEnemyFactory extends Enemy.Factory<HeliEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1870a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1871b;
        private TextureRegion c;

        public HeliEnemyFactory() {
            super(EnemyType.HELI);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1870a = Game.i.assetManager.getTextureRegion("enemy-type-heli");
            this.f1871b = Game.i.assetManager.getTextureRegion("enemy-type-heli-hl");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-heli-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1870a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1871b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public HeliEnemy create() {
            return new HeliEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_BLUE.P500;
        }
    }
}

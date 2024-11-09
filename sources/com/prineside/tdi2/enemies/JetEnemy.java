package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/JetEnemy.class */
public final class JetEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private float f1874a;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private final Vector2 f1875b;

    /* synthetic */ JetEnemy(byte b2) {
        this();
    }

    private JetEnemy() {
        super(EnemyType.JET);
        this.f1874a = 0.0f;
        this.f1875b = new Vector2();
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
        this.f1874a += f;
        super.drawBatch(batch, f);
        float sin = 0.8f + (PMath.sin(this.f1874a / 0.15f) * 0.2f);
        float sin2 = 0.9f + (PMath.sin((this.f1874a + 0.25f) / 0.15f) * 0.1f);
        this.f1875b.x = this.drawPosition.x;
        this.f1875b.y = this.drawPosition.y;
        PMath.shiftPointByAngle(this.f1875b, this.drawAngle - 180.0f, 16.0f);
        PMath.shiftPointByAngle(this.f1875b, this.drawAngle + 90.0f, 16.0f * sin2);
        batch.draw(Game.i.enemyManager.F.JET.f1877a, this.f1875b.x, this.f1875b.y, 0.0f, 0.0f, 64.0f * sin, 32.0f * sin2, 1.0f, 1.0f, this.drawAngle - 90.0f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/JetEnemy$JetEnemyFactory.class */
    public static class JetEnemyFactory extends Enemy.Factory<JetEnemy> {

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1876b;

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1877a;
        private TextureRegion c;
        private TextureRegion d;

        public JetEnemyFactory() {
            super(EnemyType.JET);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1876b = Game.i.assetManager.getTextureRegion("enemy-type-jet");
            this.f1877a = Game.i.assetManager.getTextureRegion("jet-thrust");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-jet-hl");
            this.d = Game.i.assetManager.getTextureRegion("enemy-type-jet-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1876b;
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
        public JetEnemy create() {
            return new JetEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_BLUE.P500;
        }
    }
}

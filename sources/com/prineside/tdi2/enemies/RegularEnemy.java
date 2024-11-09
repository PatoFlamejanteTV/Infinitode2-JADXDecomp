package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/RegularEnemy.class */
public final class RegularEnemy extends Enemy {
    /* synthetic */ RegularEnemy(byte b2) {
        this();
    }

    private RegularEnemy() {
        super(EnemyType.REGULAR);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getTowerDamageMultiplier(TowerType towerType) {
        return super.getTowerDamageMultiplier(towerType);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/RegularEnemy$RegularEnemyFactory.class */
    public static class RegularEnemyFactory extends Enemy.Factory<RegularEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1882a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1883b;
        private TextureRegion c;

        public RegularEnemyFactory() {
            super(EnemyType.REGULAR);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1882a = Game.i.assetManager.getTextureRegion("enemy-type-regular");
            this.c = Game.i.assetManager.getTextureRegion("enemy-type-regular-hl");
            this.f1883b = Game.i.assetManager.getTextureRegion("enemy-type-regular-emj");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1882a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.c;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return this.f1883b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public RegularEnemy create() {
            return new RegularEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.GREEN.P500;
        }
    }
}

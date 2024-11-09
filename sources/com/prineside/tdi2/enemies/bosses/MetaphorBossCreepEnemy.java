package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossCreepEnemy.class */
public final class MetaphorBossCreepEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    private Kind f1897a;

    /* renamed from: b, reason: collision with root package name */
    private float f1898b;
    private static final Color c = new Color();

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossCreepEnemy$Kind.class */
    public enum Kind {
        LOW_HP,
        HIGH_HP,
        RANDOM_SPEED,
        FRONT,
        REAR,
        BIG,
        SMALL;

        public static final Kind[] values = values();
    }

    /* synthetic */ MetaphorBossCreepEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f1897a);
        output.writeFloat(this.f1898b);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1897a = (Kind) kryo.readObject(input, Kind.class);
        this.f1898b = input.readFloat();
    }

    private MetaphorBossCreepEnemy() {
        super(EnemyType.METAPHOR_BOSS_CREEP);
        this.f1897a = Kind.RANDOM_SPEED;
        this.f1898b = 1.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossRelated() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossMainBodyPart() {
        return false;
    }

    public final void setKind(Kind kind) {
        this.f1897a = kind;
        if (kind == Kind.BIG) {
            this.f1898b = 1.25f;
        } else if (kind == Kind.SMALL) {
            this.f1898b = 0.8f;
        } else {
            this.f1898b = 1.0f;
        }
    }

    public final Kind getKind() {
        return this.f1897a;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSize() {
        return super.getSize() * this.f1898b;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSquaredSize() {
        return super.getSize() * this.f1898b * super.getSize() * this.f1898b;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getTowerDamageMultiplier(TowerType towerType) {
        float towerDamageMultiplier = super.getTowerDamageMultiplier(towerType);
        if (this.f1897a == Kind.HIGH_HP) {
            towerDamageMultiplier *= 5.0f;
        } else if (this.f1897a == Kind.LOW_HP) {
            towerDamageMultiplier *= 0.2f;
        }
        return towerDamageMultiplier;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f, Color color) {
        float f2 = 1.0f;
        c.set(color);
        if (this.existsTime < 1.0f) {
            f2 = Interpolation.pow2Out.apply(this.existsTime);
        }
        float f3 = f2 * 0.75f * this.f1898b;
        batch.setColor(c);
        float regionWidth = r0.getRegionWidth() * f3;
        batch.draw(Game.i.enemyManager.F.METAPHOR_BOSS_CREEP.f1901a.getKeyFrame(this.passedTiles, true), this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth * 0.5f, regionWidth * 0.5f, regionWidth, regionWidth, 1.0f, 1.0f, this.drawAngle);
        if (hasBuffsByType(BuffType.BLIZZARD) || hasBuffsByType(BuffType.SNOWBALL)) {
            batch.draw(Game.i.enemyManager.getIceOverlayTexture(this.id % 2), this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth, regionWidth);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return true;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MetaphorBossCreepEnemy$MetaphorBossCreepEnemyFactory.class */
    public static class MetaphorBossCreepEnemyFactory extends Enemy.Factory<MetaphorBossCreepEnemy> {

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1900b;

        /* renamed from: a, reason: collision with root package name */
        Animation<ResourcePack.AtlasTextureRegion> f1901a;

        public MetaphorBossCreepEnemyFactory() {
            super(EnemyType.MOBCHAIN_BOSS_CREEP);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1900b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1900b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1900b = Game.i.assetManager.getTextureRegions("enemy-type-boss-metaphor-creep").first();
            this.f1901a = new Animation<>(0.07f, Game.i.assetManager.getTextureRegions("enemy-type-boss-metaphor-creep"));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public MetaphorBossCreepEnemy create() {
            return new MetaphorBossCreepEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.RED.P500;
        }
    }
}

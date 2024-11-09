package com.prineside.tdi2.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.towers.MinigunTower;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/MicrogunUnit.class */
public final class MicrogunUnit extends Unit {
    public MinigunTower parent;

    /* renamed from: a, reason: collision with root package name */
    private float f3796a;

    /* renamed from: b, reason: collision with root package name */
    private float f3797b;
    private Enemy.EnemyReference c;

    @NAGS
    private final Vector2 d;

    @NAGS
    private final Vector2 e;

    @NAGS
    private float f;

    @NAGS
    private float g;

    /* synthetic */ MicrogunUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.parent);
        kryo.writeObject(output, this.c);
        output.writeFloat(this.f3796a);
        output.writeFloat(this.f3797b);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.parent = (MinigunTower) kryo.readClassAndObject(input);
        this.c = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.f3796a = input.readFloat();
        this.f3797b = input.readFloat();
    }

    private MicrogunUnit() {
        super(UnitType.MICROGUN);
        this.c = Enemy.EnemyReference.NULL;
        this.d = new Vector2();
        this.e = new Vector2();
    }

    public final void setup(MinigunTower minigunTower, float f, float f2) {
        this.parent = minigunTower;
        this.angle = 0.0f;
        this.staticPosition = true;
        this.position.set(f, f2);
    }

    @Override // com.prineside.tdi2.Unit
    public final float getSize() {
        return 32.0f;
    }

    public final void drawRange(Batch batch, RangeCircle rangeCircle) {
        float f = this.position.x;
        float f2 = this.position.y;
        float b2 = b();
        if (rangeCircle.getX() != f || rangeCircle.getY() != f2 || rangeCircle.getMinRadius() != 0.0f || rangeCircle.getMaxRadius() != b2) {
            rangeCircle.setup(f, f2, 0.0f, b2, MapSystem.TOWER_RANGE_HOVER_COLOR);
        }
        rangeCircle.draw(batch);
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        batch.draw(Game.i.unitManager.F.MICROGUN.f3798a, this.drawPosition.x - 30.0f, this.drawPosition.y - 30.0f, 60.0f, 60.0f);
        batch.draw(Game.i.unitManager.F.MICROGUN.f3799b, this.drawPosition.x - 12.0f, this.drawPosition.y - 12.0f, 12.0f, 12.0f, 24.0f, 48.0f, 1.0f, 1.0f, this.angle);
    }

    private Enemy a() {
        float b2 = b();
        float f = b2 * b2;
        Enemy enemy = null;
        float f2 = Float.MAX_VALUE;
        for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
            Enemy enemy2 = this.S.map.spawnedEnemies.items[i].enemy;
            if (enemy2 != null) {
                float dst2 = this.position.dst2(enemy2.getPosition());
                if (dst2 < f && this.parent.canAttackEnemy(enemy2) && f2 > dst2) {
                    enemy = enemy2;
                    f2 = dst2;
                }
            }
        }
        return enemy;
    }

    private float b() {
        return this.parent.rangeInPixels * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_MICROGUN_RANGE));
    }

    private void a(int i) {
        Enemy enemy = this.c.enemy;
        if (enemy == null) {
            return;
        }
        if (this.S._gameUi != null) {
            this.d.set(this.position);
            PMath.shiftPointByAngle(this.d, this.angle, 38.0f);
            PMath.shiftPointByAngle(this.d, this.angle + 90.0f, 4.0f);
            this.e.x = enemy.getPosition().x + (FastRandom.getFloat() * 4.0f);
            this.e.y = enemy.getPosition().y + (FastRandom.getFloat() * 4.0f);
        }
        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, this.parent.getStat(TowerStatType.DAMAGE) * i, DamageType.BULLET).setTower(this.parent));
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled() && (PMath.getDistanceBetweenAngles(this.f, this.angle) > 4.0f || this.g > 0.2f)) {
            this.f = this.angle;
            this.g = 0.0f;
            Vector2 vector2 = new Vector2();
            BulletSmokeMultiLine obtain = Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.obtain();
            PMath.getPointByAngleFromPoint(this.position.x, this.position.y, this.angle, 36.0f, vector2);
            obtain.setTexture(Game.i.towerManager.F.MINIGUN.bulletTraceTexture, false, FastRandom.getFloat() < 0.5f);
            obtain.setColor(MaterialColor.PURPLE.P200);
            obtain.maxSegmentWidth = 32.0f;
            obtain.nodesDisperseTime = 0.7f;
            obtain.maxAlpha = 0.56f;
            obtain.setup(vector2.x, vector2.y, this.e.x, this.e.y);
            this.S._projectileTrail.addTrail(obtain);
        }
        if (this.S._particle != null) {
            Vector2 vector22 = new Vector2();
            vector22.set(this.position.x, this.position.y);
            PMath.shiftPointByAngle(vector22, this.angle, 35.0f);
            this.S._particle.addFlashParticle(AssetManager.TextureRegions.i().muzzleFlashSmall, vector22.x, vector22.y, 10.4f, 3.8999999f, 20.8f, 31.199999f, this.angle);
        }
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_MINIGUN, this.parent);
        }
    }

    public final void destroy(Enemy enemy) {
        if (this.S._particle != null) {
            this.S._particle.addRegularShatterParticle(Game.i.unitManager.F.MICROGUN.f3798a, this.position.x, this.position.y, 60.0f, 0.0f, 1.0f);
        }
        this.S.unit.killUnit(this, enemy);
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        if (!this.parent.isRegistered()) {
            destroy(null);
            return;
        }
        Tile tileByMapPos = this.S.map.getMap().getTileByMapPos(this.position.x, this.position.y);
        if (tileByMapPos != null && tileByMapPos.enemyCount != 0) {
            this.S.map.getEnemiesInCircle(this.position.x, this.position.y, 16.0f, (enemyReference, f2, f3, f4) -> {
                if (enemyReference.enemy == null) {
                    return true;
                }
                destroy(enemyReference.enemy);
                return false;
            });
        }
        if (this.S == null) {
            return;
        }
        if (tileByMapPos != null && tileByMapPos.type == TileType.PLATFORM && ((PlatformTile) tileByMapPos).building != null) {
            destroy(null);
            return;
        }
        this.g += f;
        if (this.c.enemy == null) {
            this.f3796a += f;
            if (this.f3796a > 0.15f) {
                this.f3796a = 0.0f;
                this.c = this.S.enemy.getReference(a());
            }
        }
        Enemy enemy = this.c.enemy;
        if (enemy != null) {
            float squareDistanceBetweenPoints = PMath.getSquareDistanceBetweenPoints(this.position.x, this.position.y, enemy.getPosition().x, enemy.getPosition().y);
            float b2 = b();
            boolean z = false;
            float stat = 1.0f / ((float) (this.parent.getStat(TowerStatType.ATTACK_SPEED) * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_MICROGUN_ATTACK_SPEED)));
            if (squareDistanceBetweenPoints < b2 * b2) {
                if (!this.parent.isOutOfOrder()) {
                    rotateAt(enemy.getPosition().x, enemy.getPosition().y, f, this.parent.getStat(TowerStatType.ROTATION_SPEED));
                    if (StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(this.position, enemy.getPosition()))) < 3.0f) {
                        z = true;
                        int i = (int) (this.f3797b / stat);
                        if (i > 0) {
                            a(i);
                            this.f3797b -= stat * i;
                            if (this.f3797b < 0.0f) {
                                this.f3797b = 0.0f;
                            }
                        }
                    }
                }
            } else {
                this.c = Enemy.EnemyReference.NULL;
            }
            this.f3797b += f;
            if (!z && this.f3797b > stat) {
                this.f3797b = stat;
            }
        }
    }

    public final void rotateAt(float f, float f2, float f3, float f4) {
        rotateToAngle(PMath.getAngleBetweenPoints(this.position.x, this.position.y, f, f2), f3, f4);
    }

    public final void rotateToAngle(float f, float f2, float f3) {
        if (f == this.angle) {
            return;
        }
        float distanceBetweenAngles = PMath.getDistanceBetweenAngles(this.angle, f);
        float f4 = f2 * f3;
        if (f4 >= StrictMath.abs(distanceBetweenAngles)) {
            this.angle = f;
        } else if (distanceBetweenAngles < 0.0f) {
            this.angle -= f4;
        } else {
            this.angle += f4;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/MicrogunUnit$MicrogunUnitFactory.class */
    public static class MicrogunUnitFactory extends Unit.Factory.BasicAbstractFactory<MicrogunUnit> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3798a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f3799b;
        public ParticleEffectPool highlightParticles;

        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
            this.f3798a = Game.i.assetManager.getTextureRegion("tower-minigun-microgun-base");
            this.f3799b = Game.i.assetManager.getTextureRegion("tower-minigun-microgun-weapon");
            ParticleEffect particleEffect = new ParticleEffect();
            particleEffect.load(Gdx.files.internal("particles/building-highlight.prt"), Game.i.assetManager.getTextureRegion("tower-basic-base").getAtlas());
            particleEffect.setEmittersCleanUpBlendFunction(false);
            particleEffect.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(this.f3798a)}));
            this.highlightParticles = Game.i.assetManager.getParticleEffectPoolWithTemplate("building-highlight.prt@unitType:" + UnitType.MICROGUN.name(), particleEffect);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public MicrogunUnit create() {
            return new MicrogunUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return MaterialColor.PURPLE.P300;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.MICROGUN;
        }
    }
}

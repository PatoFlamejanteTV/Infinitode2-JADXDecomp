package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.Intersector;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/EnemyFollowingProjectile.class */
public abstract class EnemyFollowingProjectile extends Projectile {
    public static final float RAYCAST_INTERVAL_FOLLOWING_MIN = 96.0f;
    public static final float RAYCAST_INTERVAL_FOLLOWING_MIN_SQR = 9216.0f;
    public static final float DEFAULT_MAX_ROT_SPEED = 0.0f;
    public static final float DEFAULT_MAX_ROT_SPEED_DYNAMIC = 120.0f;
    private Enemy.EnemyReference d;
    public float speed;
    public float maxRotationSpeed;
    public float maxRotationSpeedDynamic;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1697a;

    /* renamed from: b, reason: collision with root package name */
    protected Vector2 f1698b;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;

    @NAGS
    public Vector2 drawPosition;

    @NAGS
    public float drawAngle;

    static {
        TLog.forClass(EnemyFollowingProjectile.class);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.d);
        output.writeFloat(this.speed);
        output.writeFloat(this.maxRotationSpeed);
        output.writeFloat(this.maxRotationSpeedDynamic);
        output.writeBoolean(this.f1697a);
        kryo.writeObject(output, this.f1698b);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        kryo.writeObject(output, this.position);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.speed = input.readFloat();
        this.maxRotationSpeed = input.readFloat();
        this.maxRotationSpeedDynamic = input.readFloat();
        this.f1697a = input.readBoolean();
        this.f1698b = (Vector2) kryo.readObject(input, Vector2.class);
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.position = (Vector2) kryo.readObject(input, Vector2.class);
        this.h = input.readFloat();
        this.i = input.readFloat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EnemyFollowingProjectile(ProjectileType projectileType) {
        super(projectileType);
        this.d = Enemy.EnemyReference.NULL;
        this.f1698b = new Vector2();
        this.f = 0.0f;
        this.g = 0.0f;
        this.drawPosition = new Vector2();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setup(Vector2 vector2, Enemy enemy, float f) {
        setup(vector2, enemy, PMath.getAngleBetweenPoints(vector2, enemy.getPosition()), f, 0.0f, 120.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setup(Vector2 vector2, Enemy enemy, float f, float f2, float f3, float f4) {
        super.setup();
        setTarget(enemy);
        this.speed = f2 * 128.0f;
        this.maxRotationSpeed = f3;
        this.maxRotationSpeedDynamic = f4;
        this.e = enemy.getSize();
        this.f1698b.set(enemy.getPosition());
        this.f1697a = false;
        this.position.set(vector2);
        this.f = vector2.x;
        this.g = vector2.y;
        this.h = f;
        this.i = 0.0f;
    }

    public float getTimeExists() {
        return this.i;
    }

    public Enemy getTarget() {
        return this.d.enemy;
    }

    public void setTarget(Enemy enemy) {
        this.d = this.S.enemy.getReference(enemy);
    }

    @Override // com.prineside.tdi2.Projectile
    public void applyDrawInterpolation(float f) {
        if (f == 0.0f) {
            this.drawPosition.set(this.position);
            this.drawAngle = this.h;
            return;
        }
        this.drawAngle = a(f);
        Vector2 vector2 = new Vector2();
        vector2.set(this.speed * f, 0.0f);
        vector2.rotateDeg(this.drawAngle + 90.0f);
        this.drawPosition.set(this.position).add(vector2);
    }

    private float a(float f) {
        float angleBetweenPoints = PMath.getAngleBetweenPoints(this.position, this.f1698b);
        if (this.maxRotationSpeed == 0.0f) {
            return angleBetweenPoints;
        }
        float f2 = this.maxRotationSpeed * f;
        float distanceBetweenAngles = PMath.getDistanceBetweenAngles(this.h, angleBetweenPoints);
        float f3 = angleBetweenPoints;
        if (StrictMath.abs(distanceBetweenAngles) > f2) {
            f3 = this.h + (distanceBetweenAngles * (f2 / StrictMath.abs(distanceBetweenAngles)));
        }
        return f3 % 360.0f;
    }

    @Override // com.prineside.tdi2.Projectile
    public void flyOnEnemy(Enemy enemy) {
        setTarget(enemy);
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        this.d = Enemy.EnemyReference.NULL;
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.d = Enemy.EnemyReference.NULL;
        this.speed = 0.0f;
        this.e = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.drawAngle = 0.0f;
        this.maxRotationSpeed = 0.0f;
        this.maxRotationSpeedDynamic = 120.0f;
        this.f1697a = false;
        this.f1698b.setZero();
        this.f = 0.0f;
        this.g = 0.0f;
        this.drawPosition.setZero();
    }

    @Override // com.prineside.tdi2.Projectile
    public Vector2 getPosition() {
        return this.position;
    }

    private void b(float f) {
        float f2;
        float f3;
        float f4;
        float f5;
        float a2 = a(f);
        float f6 = f * this.speed;
        float f7 = (a2 + 90.0f) * 0.017453292f;
        this.position.add(f6 * MathUtils.cos(f7), f6 * MathUtils.sin(f7));
        this.h = a2;
        if (this.position.dst2(this.f1698b) < 9216.0f) {
            float f8 = this.f1698b.x - this.e;
            float f9 = this.f1698b.y - this.e;
            float f10 = this.f1698b.x + this.e;
            float f11 = this.f1698b.y + this.e;
            if (this.f < this.position.x) {
                f2 = this.f;
                f3 = this.position.x;
            } else {
                f2 = this.position.x;
                f3 = this.f;
            }
            if (this.g < this.position.y) {
                f4 = this.g;
                f5 = this.position.y;
            } else {
                f4 = this.position.y;
                f5 = this.g;
            }
            if (Intersector.rectanglesOverlap(f2, f4, f3, f5, f8, f9, f10, f11) && Intersector.intersectSegmentCircle(this.f, this.g, this.position.x, this.position.y, this.f1698b.x, this.f1698b.y, this.e * this.e) && !this.f1697a) {
                this.f1697a = true;
                hit();
                return;
            }
        }
        this.f = this.position.x;
        this.g = this.position.y;
        this.i += f;
    }

    @Override // com.prineside.tdi2.Projectile
    public void draw(Batch batch, float f) {
        if (this.i > 20.0f && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_PROBLEMATIC_PROJECTILES) != 0.0d) {
            StringBuilder sb = new StringBuilder();
            sb.append("MR:").append((int) this.maxRotationSpeed);
            batch.setColor(MaterialColor.LIME.P500);
            batch.draw(AssetManager.TextureRegions.i().smallCircle, this.f - 3.0f, this.g - 3.0f, 6.0f, 6.0f);
            DrawUtils.texturedLineB(batch, AssetManager.TextureRegions.i().blank, this.f, this.g, this.position.x, this.position.y, 1.0f);
            batch.setColor(Color.PURPLE);
            batch.draw(AssetManager.TextureRegions.i().smallCircle, this.f1698b.x - this.e, this.f1698b.y - this.e, this.e * 2.0f, this.e * 2.0f);
            batch.setColor(Color.WHITE);
            float dst2 = this.position.dst2(this.f1698b);
            if (dst2 >= 9216.0f) {
                sb.append(" C-/").append((int) Math.sqrt(dst2)).append("/96.0");
            } else {
                sb.append(" C+");
            }
            Game.i.assetManager.getSmallDebugFont().draw(batch, sb, this.position.x, this.position.y);
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public void update(float f) {
        Enemy target = getTarget();
        if (target != null) {
            this.f1698b.set(target.getPosition());
            this.e = target.getSize();
        }
        if (this.maxRotationSpeed != 0.0f) {
            this.maxRotationSpeed += f * this.maxRotationSpeedDynamic;
            if (this.maxRotationSpeed > 5400.0f) {
                this.maxRotationSpeed = 0.0f;
            }
        }
        b(f);
    }

    @Override // com.prineside.tdi2.Projectile
    public boolean isDone() {
        return this.f1697a || this.i > 120.0f;
    }

    @Override // com.prineside.tdi2.Projectile
    public boolean hasReachedTarget() {
        return this.f1697a;
    }

    @Override // com.prineside.tdi2.Projectile
    public String toString() {
        return super.toString() + " (target: " + this.d + ", speed: " + this.speed + ", reachedTarget: " + this.f1697a + ", lastRayCastPosition: " + this.f + ":" + this.g + ")";
    }
}

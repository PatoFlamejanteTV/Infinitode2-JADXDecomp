package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.systems.ProjectileSystem;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Projectile.class */
public abstract class Projectile extends Registrable implements Pool.Poolable {
    public ProjectileType type;
    protected float c;
    public int id = 0;
    public Vector2 position = new Vector2();

    public abstract boolean isDone();

    public abstract boolean hasReachedTarget();

    public abstract void applyDrawInterpolation(float f);

    public abstract void update(float f);

    public abstract void draw(Batch batch, float f);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.type, ProjectileType.class);
        output.writeInt(this.id);
        output.writeFloat(this.c);
        kryo.writeObject(output, this.position);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.type = (ProjectileType) kryo.readObjectOrNull(input, ProjectileType.class);
        this.id = input.readInt();
        this.c = input.readFloat();
        this.position = (Vector2) kryo.readObject(input, Vector2.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Projectile(ProjectileType projectileType) {
        this.type = projectileType;
    }

    public void setup() {
        if (!isRegistered()) {
            throw new IllegalStateException("Projectile must be registered by ProjectileSystem before it can be set up");
        }
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public float getDamage() {
        return this.c;
    }

    public void setDamage(float f) {
        this.c = f;
    }

    public void multiplyDamage(float f) {
        this.c *= f;
    }

    public void reset() {
        this.c = 0.0f;
        this.id = 0;
        this.position.setZero();
    }

    public void hit() {
    }

    public void onDone() {
    }

    public void flyOnEnemy(Enemy enemy) {
    }

    public String toString() {
        return super.toString() + " (id: " + this.id + ", damage: " + this.c + ", position: " + this.position + ", S: " + (this.S != null) + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Projectile$Factory.class */
    public static abstract class Factory<T extends Projectile> implements EntityFactory {
        protected abstract T a();

        public void setup(ProjectileSystem projectileSystem) {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public void setupAssets() {
        }

        public final T obtain() {
            return a();
        }

        public final void free(Projectile projectile) {
        }
    }
}

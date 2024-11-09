package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Buff.class */
public abstract class Buff implements KryoSerializable {
    public static final float MAX_DURATION_MULTIPLIER = 10.0f;

    /* renamed from: a, reason: collision with root package name */
    private BuffType f1668a;
    public float duration;
    public float maxDuration;

    public abstract TextureRegion getHealthBarIcon();

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f1668a, BuffType.class);
        output.writeFloat(this.duration);
        output.writeFloat(this.maxDuration);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f1668a = (BuffType) kryo.readObjectOrNull(input, BuffType.class);
        this.duration = input.readFloat();
        this.maxDuration = input.readFloat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Buff(BuffType buffType) {
        this.f1668a = buffType;
    }

    public Buff cpy(float f) {
        return null;
    }

    public void setup(float f, float f2) {
        this.duration = f;
        this.maxDuration = f2;
    }

    public BuffType getType() {
        return this.f1668a;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Buff$Factory.class */
    public static abstract class Factory<T extends Buff> implements Disposable, EntityFactory {
        public abstract BuffProcessor<T> createProcessor();

        public abstract TextureRegion getHealthBarIcon();

        public void setup() {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public void setupAssets() {
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }
}

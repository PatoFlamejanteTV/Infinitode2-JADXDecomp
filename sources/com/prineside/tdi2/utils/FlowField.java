package com.prineside.tdi2.utils;

import com.badlogic.gdx.ai.steer.behaviors.FollowFlowField;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FlowField.class */
public final class FlowField implements FollowFlowField.FlowField<Vector2> {

    /* renamed from: a, reason: collision with root package name */
    private final float[][] f3832a;

    /* renamed from: b, reason: collision with root package name */
    private final int f3833b;
    private final int c;
    private final float d;
    private final float e;

    @NAGS
    private final Vector2 f;

    /* synthetic */ FlowField(float[][] fArr, int i, int i2, float f, byte b2) {
        this(fArr, i, i2, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FlowField$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<FlowField> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, FlowField flowField) {
            kryo.writeObject(output, flowField.f3832a);
            output.writeVarInt(flowField.f3833b, true);
            output.writeVarInt(flowField.c, true);
            output.writeFloat(flowField.d);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public FlowField read2(Kryo kryo, Input input, Class<? extends FlowField> cls) {
            return new FlowField((float[][]) kryo.readObject(input, float[][].class), input.readVarInt(true), input.readVarInt(true), input.readFloat(), (byte) 0);
        }
    }

    private FlowField(float[][] fArr, int i, int i2, float f) {
        this.f = new Vector2();
        this.f3832a = fArr;
        this.f3833b = i;
        this.c = i2;
        this.d = f;
        this.e = 1.0f / f;
    }

    public FlowField(int i, int i2, float f) {
        this.f = new Vector2();
        Preconditions.checkArgument(i > 0, "width can not be %s", i);
        Preconditions.checkArgument(i2 > 0, "height can not be %s", i2);
        this.f3833b = i;
        this.c = i2;
        this.d = f;
        this.e = 1.0f / f;
        this.f3832a = new float[i2][i << 1];
    }

    public final int getHeight() {
        return this.c;
    }

    public final int getWidth() {
        return this.f3833b;
    }

    public final float getCellSize() {
        return this.d;
    }

    public final void setDirection(int i, int i2, float f, float f2) {
        this.f3832a[i2][i << 1] = f;
        this.f3832a[i2][(i << 1) + 1] = f2;
    }

    public final Vector2 getDirection(int i, int i2) {
        if (i < 0) {
            if (i2 < 0) {
                this.f.set(0.70710677f, 0.70710677f);
            } else if (i2 >= this.c) {
                this.f.set(0.70710677f, -0.70710677f);
            } else {
                this.f.set(1.0f, 0.0f);
            }
        } else if (i >= this.f3833b) {
            if (i2 < 0) {
                this.f.set(-0.70710677f, 0.70710677f);
            } else if (i2 >= this.c) {
                this.f.set(-0.70710677f, -0.70710677f);
            } else {
                this.f.set(-1.0f, 0.0f);
            }
        } else if (i2 < 0) {
            this.f.set(0.0f, 1.0f);
        } else if (i2 >= this.c) {
            this.f.set(0.0f, -1.0f);
        } else {
            this.f.x = this.f3832a[i2][i << 1];
            this.f.y = this.f3832a[i2][(i << 1) + 1];
        }
        return this.f;
    }

    private int a(float f) {
        return ((int) ((f * this.e) + (this.d * 128.0f))) - 128;
    }

    @Override // com.badlogic.gdx.ai.steer.behaviors.FollowFlowField.FlowField
    public final Vector2 lookup(Vector2 vector2) {
        return getDirection(a(vector2.x), a(vector2.y));
    }
}

package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.ThrowBackBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ThrowBackBuff.class */
public final class ThrowBackBuff extends Buff {
    public int ownerId;
    public float force;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.ownerId, false);
        output.writeFloat(this.force);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.ownerId = input.readVarInt(false);
        this.force = input.readFloat();
    }

    @Override // com.prineside.tdi2.Buff
    public final ThrowBackBuff cpy(float f) {
        ThrowBackBuff throwBackBuff = new ThrowBackBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        throwBackBuff.setup(this.ownerId, this.force, f3, this.maxDuration);
        return throwBackBuff;
    }

    public ThrowBackBuff() {
        super(BuffType.THROW_BACK);
    }

    public final void setup(int i, float f, float f2, float f3) {
        super.setup(f2, f3);
        this.ownerId = i;
        this.force = f;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconBlastThrowBack;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ThrowBackBuff$BlastThrowBackBuffFactory.class */
    public static class BlastThrowBackBuffFactory extends Buff.Factory<ThrowBackBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<ThrowBackBuff> createProcessor() {
            return new ThrowBackBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconBlastThrowBack;
        }
    }
}

package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.StunBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/StunBuff.class */
public final class StunBuff extends Buff {
    public static final int MAX_STUNS_IN_TOTAL = 6;
    public static final float FULL_IMMUNITY_COEFF = 1.0f;
    public static final float FULL_IMMUNITY_TILES = 3.0f;
    public static final float IMMUNITY_DROP_PER_TILE = 0.2f;
    public static final float[] STUN_DURATION_BY_STUN_COUNT = {1.0f, 0.75f, 0.5f, 0.25f};
    public static final float[] STUN_CHANCE_PENALTY_SAME_TOWER = {1.0f, 0.8f, 0.64f, 0.512f, 0.4096f};
    public int issuerId;
    public boolean copyDisabled;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.issuerId, true);
        output.writeBoolean(this.copyDisabled);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.issuerId = input.readVarInt(true);
        this.copyDisabled = input.readBoolean();
    }

    public StunBuff() {
        super(BuffType.STUN);
    }

    public final void setup(float f, float f2, int i) {
        super.setup(f, f2);
        this.issuerId = i;
        this.copyDisabled = false;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconStun;
    }

    @Override // com.prineside.tdi2.Buff
    public final StunBuff cpy(float f) {
        if (this.copyDisabled) {
            return null;
        }
        StunBuff stunBuff = new StunBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        stunBuff.setup(f3, this.maxDuration, this.issuerId);
        return stunBuff;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/StunBuff$StunBuffFactory.class */
    public static class StunBuffFactory extends Buff.Factory<StunBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<StunBuff> createProcessor() {
            return new StunBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconStun;
        }
    }
}

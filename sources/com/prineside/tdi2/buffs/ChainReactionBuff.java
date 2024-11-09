package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.ChainReactionBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ChainReactionBuff.class */
public final class ChainReactionBuff extends Buff {
    public float rangeInTiles;
    public float chance;
    public float durationMultiplier;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.rangeInTiles);
        output.writeFloat(this.chance);
        output.writeFloat(this.durationMultiplier);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.rangeInTiles = input.readFloat();
        this.chance = input.readFloat();
        this.durationMultiplier = input.readFloat();
    }

    public ChainReactionBuff() {
        super(BuffType.CHAIN_REACTION);
    }

    @Override // com.prineside.tdi2.Buff
    public final ChainReactionBuff cpy(float f) {
        ChainReactionBuff chainReactionBuff = new ChainReactionBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        chainReactionBuff.setup(f3, this.maxDuration, this.chance, this.rangeInTiles, f);
        return chainReactionBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconChainReaction;
    }

    public final void setup(float f, float f2, float f3, float f4, float f5) {
        super.setup(f, f2);
        this.chance = f3;
        this.rangeInTiles = f4;
        this.durationMultiplier = f5;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ChainReactionBuff$ChainReactionBuffFactory.class */
    public static class ChainReactionBuffFactory extends Buff.Factory<ChainReactionBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<ChainReactionBuff> createProcessor() {
            return new ChainReactionBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconChainReaction;
        }
    }
}

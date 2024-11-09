package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.tiles.BossTile;
import java.util.Iterator;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/WaveBossSupplier.class */
public interface WaveBossSupplier {
    @Null
    BossType getWaveBoss(int i);

    WaveBossSupplier cpy();

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/WaveBossSupplier$MapBased.class */
    public static final class MapBased implements KryoSerializable, WaveBossSupplier {

        /* renamed from: a, reason: collision with root package name */
        private IntMap<BossType> f3916a = new IntMap<>();

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f3916a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f3916a = (IntMap) kryo.readObject(input, IntMap.class);
        }

        public final void add(int i, BossType bossType) {
            this.f3916a.put(i, bossType);
        }

        public final IntMap<BossType> getMap() {
            return this.f3916a;
        }

        @Override // com.prineside.tdi2.utils.WaveBossSupplier
        public final BossType getWaveBoss(int i) {
            return this.f3916a.get(i);
        }

        @Override // com.prineside.tdi2.utils.WaveBossSupplier
        public final WaveBossSupplier cpy() {
            MapBased mapBased = new MapBased();
            Iterator<IntMap.Entry<BossType>> it = this.f3916a.iterator();
            while (it.hasNext()) {
                IntMap.Entry<BossType> next = it.next();
                mapBased.f3916a.put(next.key, next.value);
            }
            return mapBased;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/WaveBossSupplier$Procedural.class */
    public static final class Procedural implements KryoSerializable, WaveBossSupplier {

        /* renamed from: a, reason: collision with root package name */
        private BossTile.BossWavesConfig f3917a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f3917a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f3917a = (BossTile.BossWavesConfig) kryo.readObject(input, BossTile.BossWavesConfig.class);
        }

        public Procedural() {
            this.f3917a = new BossTile.BossWavesConfig(20, 1, 0, new Array());
        }

        public Procedural(BossTile.BossWavesConfig bossWavesConfig) {
            this.f3917a = new BossTile.BossWavesConfig(20, 1, 0, new Array());
            Preconditions.checkNotNull(bossWavesConfig, "config can not be null");
            this.f3917a = bossWavesConfig;
        }

        public final BossTile.BossWavesConfig getWavesConfig() {
            return this.f3917a;
        }

        @Override // com.prineside.tdi2.utils.WaveBossSupplier
        public final BossType getWaveBoss(int i) {
            if (i < this.f3917a.startDelay) {
                return null;
            }
            int i2 = i - this.f3917a.startDelay;
            int i3 = (i2 - 1) / this.f3917a.cycleLength;
            int i4 = i2 - (i3 * this.f3917a.cycleLength);
            if (this.f3917a.repeatCount <= 0 || this.f3917a.repeatCount > i3) {
                for (int i5 = 0; i5 < this.f3917a.bossWavePairs.size; i5++) {
                    BossTile.BossTypeWavePair bossTypeWavePair = this.f3917a.bossWavePairs.get(i5);
                    if (bossTypeWavePair.wave == i4) {
                        return bossTypeWavePair.bossType;
                    }
                }
                return null;
            }
            return null;
        }

        @Override // com.prineside.tdi2.utils.WaveBossSupplier
        public final WaveBossSupplier cpy() {
            return new Procedural(this.f3917a.cpy());
        }

        public final String toString() {
            return super.toString() + " (" + this.f3917a + ")";
        }
    }
}

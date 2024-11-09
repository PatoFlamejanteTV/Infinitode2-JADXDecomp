package com.prineside.tdi2.systems.randomEncounter.reward;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/BonusLevelPassReward.class */
public class BonusLevelPassReward implements KryoSerializable, RandomEncounterSystem.RewardType {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3079a = TLog.forClass(BonusLevelPassReward.class);
    public int probability;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
    }

    private BonusLevelPassReward() {
    }

    public BonusLevelPassReward(int i) {
        this.probability = i;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.randomEncounter.isReceivedBonusLevelPass()) {
            return 0;
        }
        return this.probability;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public void giveReward(GameSystemProvider gameSystemProvider, float f, float f2) {
        f3079a.d("secret level pass reward", new Object[0]);
        gameSystemProvider.randomEncounter.setReceivedBonusLevelPass(true);
        gameSystemProvider.randomEncounter.startFireworks(f, f2);
        if (gameSystemProvider._particle != null) {
            ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("encounter-bird-secret-reward.p").obtain();
            obtain.setPosition(f, f2);
            gameSystemProvider._particle.addParticle(obtain, false);
        }
    }
}

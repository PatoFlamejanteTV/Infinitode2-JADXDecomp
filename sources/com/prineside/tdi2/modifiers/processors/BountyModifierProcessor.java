package com.prineside.tdi2.modifiers.processors;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.ModifierProcessor;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.WaveComplete;
import com.prineside.tdi2.modifiers.BountyModifier;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/processors/BountyModifierProcessor.class */
public class BountyModifierProcessor extends ModifierProcessor<BountyModifier> {

    /* renamed from: a, reason: collision with root package name */
    private OnWaveComplete f2601a = new OnWaveComplete(this, 0);

    @Override // com.prineside.tdi2.ModifierProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2601a);
    }

    @Override // com.prineside.tdi2.ModifierProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2601a = (OnWaveComplete) kryo.readObject(input, OnWaveComplete.class);
    }

    @Override // com.prineside.tdi2.Registrable
    public void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.S.events.getListeners(WaveComplete.class).addStateAffecting(this.f2601a);
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        this.S.events.getListeners(WaveComplete.class).remove(this.f2601a);
        super.setUnregistered();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int intValue = this.S.gameValue.getIntValue(GameValueType.MODIFIER_BOUNTY_VALUE);
        int money = (int) (this.S.gameState.getMoney() * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_BOUNTY_PERCENT));
        int i = money;
        if (money > intValue) {
            i = intValue;
        }
        if (i == 0) {
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.S.modifier.modifiers.size; i3++) {
            Modifier modifier = this.S.modifier.modifiers.items[i3];
            if (modifier.type == ModifierType.BOUNTY) {
                BountyModifier bountyModifier = (BountyModifier) modifier;
                int i4 = i;
                if (bountyModifier.boostedByAbility) {
                    i4 = (int) (i4 * 1.3f);
                    bountyModifier.boostedByAbility = false;
                }
                i2 += i4;
                bountyModifier.coinsGained += i4;
                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                    this.S._particle.addCoinParticle(modifier.getTile().center.x, modifier.getTile().center.y + 32.0f, i4);
                }
            }
        }
        this.S.gameState.addMoney(i2, true);
        this.S.statistics.addStatistic(StatisticsType.CG_B, i2);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/processors/BountyModifierProcessor$OnWaveComplete.class */
    public static final class OnWaveComplete extends SerializableListener<BountyModifierProcessor> implements Listener<WaveComplete> {
        /* synthetic */ OnWaveComplete(BountyModifierProcessor bountyModifierProcessor, byte b2) {
            this(bountyModifierProcessor);
        }

        private OnWaveComplete() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnWaveComplete(BountyModifierProcessor bountyModifierProcessor) {
            this.f1759a = bountyModifierProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(WaveComplete waveComplete) {
            BountyModifierProcessor bountyModifierProcessor = (BountyModifierProcessor) this.f1759a;
            waveComplete.getWave();
            bountyModifierProcessor.a();
        }
    }
}

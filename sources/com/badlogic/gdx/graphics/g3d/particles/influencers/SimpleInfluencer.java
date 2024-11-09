package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/SimpleInfluencer.class */
public abstract class SimpleInfluencer extends Influencer {
    public ScaledNumericValue value;
    ParallelArray.FloatChannel valueChannel;
    ParallelArray.FloatChannel interpolationChannel;
    ParallelArray.FloatChannel lifeChannel;
    ParallelArray.ChannelDescriptor valueChannelDescriptor;

    public SimpleInfluencer() {
        this.value = new ScaledNumericValue();
        this.value.setHigh(1.0f);
    }

    public SimpleInfluencer(SimpleInfluencer simpleInfluencer) {
        this();
        set(simpleInfluencer);
    }

    private void set(SimpleInfluencer simpleInfluencer) {
        this.value.load(simpleInfluencer.value);
        this.valueChannelDescriptor = simpleInfluencer.valueChannelDescriptor;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.valueChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(this.valueChannelDescriptor);
        ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
        this.interpolationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Interpolation);
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i, int i2) {
        if (!this.value.isRelative()) {
            int i3 = i * this.valueChannel.strideSize;
            int i4 = i * this.interpolationChannel.strideSize;
            int i5 = i3 + (i2 * this.valueChannel.strideSize);
            while (i3 < i5) {
                float newLowValue = this.value.newLowValue();
                float newHighValue = this.value.newHighValue() - newLowValue;
                this.interpolationChannel.data[i4] = newLowValue;
                this.interpolationChannel.data[i4 + 1] = newHighValue;
                this.valueChannel.data[i3] = newLowValue + (newHighValue * this.value.getScale(0.0f));
                i3 += this.valueChannel.strideSize;
                i4 += this.interpolationChannel.strideSize;
            }
            return;
        }
        int i6 = i * this.valueChannel.strideSize;
        int i7 = i * this.interpolationChannel.strideSize;
        int i8 = i6 + (i2 * this.valueChannel.strideSize);
        while (i6 < i8) {
            float newLowValue2 = this.value.newLowValue();
            float newHighValue2 = this.value.newHighValue();
            this.interpolationChannel.data[i7] = newLowValue2;
            this.interpolationChannel.data[i7 + 1] = newHighValue2;
            this.valueChannel.data[i6] = newLowValue2 + (newHighValue2 * this.value.getScale(0.0f));
            i6 += this.valueChannel.strideSize;
            i7 += this.interpolationChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int i = 0;
        int i2 = 0;
        int i3 = 2;
        int i4 = 0 + (this.controller.particles.size * this.valueChannel.strideSize);
        while (i < i4) {
            this.valueChannel.data[i] = this.interpolationChannel.data[i2] + (this.interpolationChannel.data[i2 + 1] * this.value.getScale(this.lifeChannel.data[i3]));
            i += this.valueChannel.strideSize;
            i2 += this.interpolationChannel.strideSize;
            i3 += this.lifeChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue("value", this.value);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.value = (ScaledNumericValue) json.readValue("value", ScaledNumericValue.class, jsonValue);
    }
}

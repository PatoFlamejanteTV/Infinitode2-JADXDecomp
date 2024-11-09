package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.values.GradientColorValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ColorInfluencer.class */
public abstract class ColorInfluencer extends Influencer {
    ParallelArray.FloatChannel colorChannel;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ColorInfluencer$Random.class */
    public static class Random extends ColorInfluencer {
        ParallelArray.FloatChannel colorChannel;

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            this.colorChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Color);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i * this.colorChannel.strideSize;
            int i4 = i3;
            int i5 = i3 + (i2 * this.colorChannel.strideSize);
            while (i4 < i5) {
                this.colorChannel.data[i4] = MathUtils.random();
                this.colorChannel.data[i4 + 1] = MathUtils.random();
                this.colorChannel.data[i4 + 2] = MathUtils.random();
                this.colorChannel.data[i4 + 3] = MathUtils.random();
                i4 += this.colorChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Random copy() {
            return new Random();
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ColorInfluencer$Single.class */
    public static class Single extends ColorInfluencer {
        ParallelArray.FloatChannel alphaInterpolationChannel;
        ParallelArray.FloatChannel lifeChannel;
        public ScaledNumericValue alphaValue;
        public GradientColorValue colorValue;

        public Single() {
            this.colorValue = new GradientColorValue();
            this.alphaValue = new ScaledNumericValue();
            this.alphaValue.setHigh(1.0f);
        }

        public Single(Single single) {
            this();
            set(single);
        }

        public void set(Single single) {
            this.colorValue.load(single.colorValue);
            this.alphaValue.load(single.alphaValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
            this.alphaInterpolationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Interpolation);
            this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i * this.colorChannel.strideSize;
            int i4 = i * this.alphaInterpolationChannel.strideSize;
            int i5 = (i * this.lifeChannel.strideSize) + 2;
            int i6 = i3 + (i2 * this.colorChannel.strideSize);
            while (i3 < i6) {
                float newLowValue = this.alphaValue.newLowValue();
                float newHighValue = this.alphaValue.newHighValue() - newLowValue;
                this.colorValue.getColor(0.0f, this.colorChannel.data, i3);
                this.colorChannel.data[i3 + 3] = newLowValue + (newHighValue * this.alphaValue.getScale(this.lifeChannel.data[i5]));
                this.alphaInterpolationChannel.data[i4] = newLowValue;
                this.alphaInterpolationChannel.data[i4 + 1] = newHighValue;
                i3 += this.colorChannel.strideSize;
                i4 += this.alphaInterpolationChannel.strideSize;
                i5 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 0;
            int i3 = 2;
            int i4 = 0 + (this.controller.particles.size * this.colorChannel.strideSize);
            while (i < i4) {
                float f = this.lifeChannel.data[i3];
                this.colorValue.getColor(f, this.colorChannel.data, i);
                this.colorChannel.data[i + 3] = this.alphaInterpolationChannel.data[i2] + (this.alphaInterpolationChannel.data[i2 + 1] * this.alphaValue.getScale(f));
                i += this.colorChannel.strideSize;
                i2 += this.alphaInterpolationChannel.strideSize;
                i3 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Single copy() {
            return new Single(this);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void write(Json json) {
            json.writeValue("alpha", this.alphaValue);
            json.writeValue("color", this.colorValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void read(Json json, JsonValue jsonValue) {
            this.alphaValue = (ScaledNumericValue) json.readValue("alpha", ScaledNumericValue.class, jsonValue);
            this.colorValue = (GradientColorValue) json.readValue("color", GradientColorValue.class, jsonValue);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.colorChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Color);
    }
}

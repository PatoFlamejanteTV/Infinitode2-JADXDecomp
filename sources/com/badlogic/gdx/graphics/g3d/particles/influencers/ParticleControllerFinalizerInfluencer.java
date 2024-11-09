package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/ParticleControllerFinalizerInfluencer.class */
public class ParticleControllerFinalizerInfluencer extends Influencer {
    ParallelArray.FloatChannel positionChannel;
    ParallelArray.FloatChannel scaleChannel;
    ParallelArray.FloatChannel rotationChannel;
    ParallelArray.ObjectChannel<ParticleController> controllerChannel;
    boolean hasScale;
    boolean hasRotation;

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        this.controllerChannel = (ParallelArray.ObjectChannel) this.controller.particles.getChannel(ParticleChannels.ParticleController);
        if (this.controllerChannel == null) {
            throw new GdxRuntimeException("ParticleController channel not found, specify an influencer which will allocate it please.");
        }
        this.scaleChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Scale);
        this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        this.hasScale = this.scaleChannel != null;
        this.hasRotation = this.rotationChannel != null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int i = 0;
        int i2 = 0;
        int i3 = this.controller.particles.size;
        while (i < i3) {
            ParticleController particleController = this.controllerChannel.data[i];
            float f = this.hasScale ? this.scaleChannel.data[i] : 1.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 1.0f;
            if (this.hasRotation) {
                int i4 = i * this.rotationChannel.strideSize;
                f2 = this.rotationChannel.data[i4];
                f3 = this.rotationChannel.data[i4 + 1];
                f4 = this.rotationChannel.data[i4 + 2];
                f5 = this.rotationChannel.data[i4 + 3];
            }
            particleController.setTransform(this.positionChannel.data[i2], this.positionChannel.data[i2 + 1], this.positionChannel.data[i2 + 2], f2, f3, f4, f5, f);
            particleController.update();
            i++;
            i2 += this.positionChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerFinalizerInfluencer copy() {
        return new ParticleControllerFinalizerInfluencer();
    }
}

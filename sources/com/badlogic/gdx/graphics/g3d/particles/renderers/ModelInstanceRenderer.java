package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ModelInstanceParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/renderers/ModelInstanceRenderer.class */
public class ModelInstanceRenderer extends ParticleControllerRenderer<ModelInstanceControllerRenderData, ModelInstanceParticleBatch> {
    private boolean hasColor;
    private boolean hasScale;
    private boolean hasRotation;

    public ModelInstanceRenderer() {
        super(new ModelInstanceControllerRenderData());
    }

    public ModelInstanceRenderer(ModelInstanceParticleBatch modelInstanceParticleBatch) {
        this();
        setBatch(modelInstanceParticleBatch);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        ((ModelInstanceControllerRenderData) this.renderData).positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        ((ModelInstanceControllerRenderData) this.renderData).modelInstanceChannel = (ParallelArray.ObjectChannel) this.controller.particles.getChannel(ParticleChannels.ModelInstance);
        ((ModelInstanceControllerRenderData) this.renderData).colorChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Color);
        ((ModelInstanceControllerRenderData) this.renderData).scaleChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Scale);
        ((ModelInstanceControllerRenderData) this.renderData).rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Rotation3D);
        this.hasColor = ((ModelInstanceControllerRenderData) this.renderData).colorChannel != null;
        this.hasScale = ((ModelInstanceControllerRenderData) this.renderData).scaleChannel != null;
        this.hasRotation = ((ModelInstanceControllerRenderData) this.renderData).rotationChannel != null;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        int i = 0;
        int i2 = 0;
        int i3 = this.controller.particles.size;
        while (i < i3) {
            ModelInstance modelInstance = ((ModelInstanceControllerRenderData) this.renderData).modelInstanceChannel.data[i];
            float f = this.hasScale ? ((ModelInstanceControllerRenderData) this.renderData).scaleChannel.data[i] : 1.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 1.0f;
            if (this.hasRotation) {
                int i4 = i * ((ModelInstanceControllerRenderData) this.renderData).rotationChannel.strideSize;
                f2 = ((ModelInstanceControllerRenderData) this.renderData).rotationChannel.data[i4];
                f3 = ((ModelInstanceControllerRenderData) this.renderData).rotationChannel.data[i4 + 1];
                f4 = ((ModelInstanceControllerRenderData) this.renderData).rotationChannel.data[i4 + 2];
                f5 = ((ModelInstanceControllerRenderData) this.renderData).rotationChannel.data[i4 + 3];
            }
            modelInstance.transform.set(((ModelInstanceControllerRenderData) this.renderData).positionChannel.data[i2], ((ModelInstanceControllerRenderData) this.renderData).positionChannel.data[i2 + 1], ((ModelInstanceControllerRenderData) this.renderData).positionChannel.data[i2 + 2], f2, f3, f4, f5, f, f, f);
            if (this.hasColor) {
                int i5 = i * ((ModelInstanceControllerRenderData) this.renderData).colorChannel.strideSize;
                ColorAttribute colorAttribute = (ColorAttribute) modelInstance.materials.get(0).get(ColorAttribute.Diffuse);
                BlendingAttribute blendingAttribute = (BlendingAttribute) modelInstance.materials.get(0).get(BlendingAttribute.Type);
                colorAttribute.color.r = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.data[i5];
                colorAttribute.color.g = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.data[i5 + 1];
                colorAttribute.color.f888b = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.data[i5 + 2];
                if (blendingAttribute != null) {
                    blendingAttribute.opacity = ((ModelInstanceControllerRenderData) this.renderData).colorChannel.data[i5 + 3];
                }
            }
            i++;
            i2 += ((ModelInstanceControllerRenderData) this.renderData).positionChannel.strideSize;
        }
        super.update();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new ModelInstanceRenderer((ModelInstanceParticleBatch) this.batch);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderer
    public boolean isCompatible(ParticleBatch<?> particleBatch) {
        return particleBatch instanceof ModelInstanceParticleBatch;
    }
}

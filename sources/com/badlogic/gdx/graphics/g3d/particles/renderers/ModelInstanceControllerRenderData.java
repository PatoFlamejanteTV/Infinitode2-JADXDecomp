package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/renderers/ModelInstanceControllerRenderData.class */
public class ModelInstanceControllerRenderData extends ParticleControllerRenderData {
    public ParallelArray.ObjectChannel<ModelInstance> modelInstanceChannel;
    public ParallelArray.FloatChannel colorChannel;
    public ParallelArray.FloatChannel scaleChannel;
    public ParallelArray.FloatChannel rotationChannel;
}

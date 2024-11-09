package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleEffect.class */
public class ParticleEffect implements ResourceData.Configurable, Disposable {
    private Array<ParticleController> controllers;
    private BoundingBox bounds;

    public ParticleEffect() {
        this.controllers = new Array<>(true, 3, ParticleController.class);
    }

    public ParticleEffect(ParticleEffect particleEffect) {
        this.controllers = new Array<>(true, particleEffect.controllers.size);
        int i = particleEffect.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.add(particleEffect.controllers.get(i2).copy());
        }
    }

    public ParticleEffect(ParticleController... particleControllerArr) {
        this.controllers = new Array<>(particleControllerArr);
    }

    public void init() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).init();
        }
    }

    public void start() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).start();
        }
    }

    public void end() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).end();
        }
    }

    public void reset() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).reset();
        }
    }

    public void update() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).update();
        }
    }

    public void update(float f) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).update(f);
        }
    }

    public void draw() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).draw();
        }
    }

    public boolean isComplete() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!this.controllers.get(i2).isComplete()) {
                return false;
            }
        }
        return true;
    }

    public void setTransform(Matrix4 matrix4) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).setTransform(matrix4);
        }
    }

    public void rotate(Quaternion quaternion) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).rotate(quaternion);
        }
    }

    public void rotate(Vector3 vector3, float f) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).rotate(vector3, f);
        }
    }

    public void translate(Vector3 vector3) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).translate(vector3);
        }
    }

    public void scale(float f, float f2, float f3) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).scale(f, f2, f3);
        }
    }

    public void scale(Vector3 vector3) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).scale(vector3.x, vector3.y, vector3.z);
        }
    }

    public Array<ParticleController> getControllers() {
        return this.controllers;
    }

    public ParticleController findController(String str) {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            ParticleController particleController = this.controllers.get(i2);
            if (particleController.name.equals(str)) {
                return particleController;
            }
        }
        return null;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        int i = this.controllers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.controllers.get(i2).dispose();
        }
    }

    public BoundingBox getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        BoundingBox boundingBox = this.bounds;
        boundingBox.inf();
        Array.ArrayIterator<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            boundingBox.ext(it.next().getBoundingBox());
        }
        return boundingBox;
    }

    public void setBatch(Array<ParticleBatch<?>> array) {
        Array.ArrayIterator<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            ParticleController next = it.next();
            Array.ArrayIterator<ParticleBatch<?>> it2 = array.iterator();
            while (it2.hasNext()) {
                if (!next.renderer.setBatch(it2.next())) {
                }
            }
        }
    }

    public ParticleEffect copy() {
        return new ParticleEffect(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        Array.ArrayIterator<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            it.next().save(assetManager, resourceData);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        Array.ArrayIterator<ParticleController> it = this.controllers.iterator();
        while (it.hasNext()) {
            it.next().load(assetManager, resourceData);
        }
    }
}

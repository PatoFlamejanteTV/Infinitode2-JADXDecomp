package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsInfluencer.class */
public class DynamicsInfluencer extends Influencer {
    public Array<DynamicsModifier> velocities;
    private ParallelArray.FloatChannel accellerationChannel;
    private ParallelArray.FloatChannel positionChannel;
    private ParallelArray.FloatChannel previousPositionChannel;
    private ParallelArray.FloatChannel rotationChannel;
    private ParallelArray.FloatChannel angularVelocityChannel;
    boolean hasAcceleration;
    boolean has2dAngularVelocity;
    boolean has3dAngularVelocity;

    public DynamicsInfluencer() {
        this.velocities = new Array<>(true, 3, DynamicsModifier.class);
    }

    public DynamicsInfluencer(DynamicsModifier... dynamicsModifierArr) {
        this.velocities = new Array<>(true, dynamicsModifierArr.length, DynamicsModifier.class);
        for (DynamicsModifier dynamicsModifier : dynamicsModifierArr) {
            this.velocities.add((DynamicsModifier) dynamicsModifier.copy());
        }
    }

    public DynamicsInfluencer(DynamicsInfluencer dynamicsInfluencer) {
        this((DynamicsModifier[]) dynamicsInfluencer.velocities.toArray(DynamicsModifier.class));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        for (int i = 0; i < this.velocities.size; i++) {
            this.velocities.items[i].allocateChannels();
        }
        this.accellerationChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.Acceleration);
        this.hasAcceleration = this.accellerationChannel != null;
        if (this.hasAcceleration) {
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
            this.previousPositionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.PreviousPosition);
        }
        this.angularVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.AngularVelocity2D);
        this.has2dAngularVelocity = this.angularVelocityChannel != null;
        if (this.has2dAngularVelocity) {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation2D);
            this.has3dAngularVelocity = false;
            return;
        }
        this.angularVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.getChannel(ParticleChannels.AngularVelocity3D);
        this.has3dAngularVelocity = this.angularVelocityChannel != null;
        if (this.has3dAngularVelocity) {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController) {
        super.set(particleController);
        for (int i = 0; i < this.velocities.size; i++) {
            this.velocities.items[i].set(particleController);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        for (int i = 0; i < this.velocities.size; i++) {
            this.velocities.items[i].init();
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i, int i2) {
        if (this.hasAcceleration) {
            int i3 = i * this.positionChannel.strideSize;
            int i4 = i3;
            int i5 = i3 + (i2 * this.positionChannel.strideSize);
            while (i4 < i5) {
                this.previousPositionChannel.data[i4] = this.positionChannel.data[i4];
                this.previousPositionChannel.data[i4 + 1] = this.positionChannel.data[i4 + 1];
                this.previousPositionChannel.data[i4 + 2] = this.positionChannel.data[i4 + 2];
                i4 += this.positionChannel.strideSize;
            }
        }
        if (this.has2dAngularVelocity) {
            int i6 = i * this.rotationChannel.strideSize;
            int i7 = i6;
            int i8 = i6 + (i2 * this.rotationChannel.strideSize);
            while (i7 < i8) {
                this.rotationChannel.data[i7] = 1.0f;
                this.rotationChannel.data[i7 + 1] = 0.0f;
                i7 += this.rotationChannel.strideSize;
            }
        } else if (this.has3dAngularVelocity) {
            int i9 = i * this.rotationChannel.strideSize;
            int i10 = i9;
            int i11 = i9 + (i2 * this.rotationChannel.strideSize);
            while (i10 < i11) {
                this.rotationChannel.data[i10] = 0.0f;
                this.rotationChannel.data[i10 + 1] = 0.0f;
                this.rotationChannel.data[i10 + 2] = 0.0f;
                this.rotationChannel.data[i10 + 3] = 1.0f;
                i10 += this.rotationChannel.strideSize;
            }
        }
        for (int i12 = 0; i12 < this.velocities.size; i12++) {
            this.velocities.items[i12].activateParticles(i, i2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        if (this.hasAcceleration) {
            Arrays.fill(this.accellerationChannel.data, 0, this.controller.particles.size * this.accellerationChannel.strideSize, 0.0f);
        }
        if (this.has2dAngularVelocity || this.has3dAngularVelocity) {
            Arrays.fill(this.angularVelocityChannel.data, 0, this.controller.particles.size * this.angularVelocityChannel.strideSize, 0.0f);
        }
        for (int i = 0; i < this.velocities.size; i++) {
            this.velocities.items[i].update();
        }
        if (this.hasAcceleration) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i2 >= this.controller.particles.size) {
                    break;
                }
                float f = this.positionChannel.data[i4];
                float f2 = this.positionChannel.data[i4 + 1];
                float f3 = this.positionChannel.data[i4 + 2];
                this.positionChannel.data[i4] = ((f * 2.0f) - this.previousPositionChannel.data[i4]) + (this.accellerationChannel.data[i4] * this.controller.deltaTimeSqr);
                this.positionChannel.data[i4 + 1] = ((f2 * 2.0f) - this.previousPositionChannel.data[i4 + 1]) + (this.accellerationChannel.data[i4 + 1] * this.controller.deltaTimeSqr);
                this.positionChannel.data[i4 + 2] = ((f3 * 2.0f) - this.previousPositionChannel.data[i4 + 2]) + (this.accellerationChannel.data[i4 + 2] * this.controller.deltaTimeSqr);
                this.previousPositionChannel.data[i4] = f;
                this.previousPositionChannel.data[i4 + 1] = f2;
                this.previousPositionChannel.data[i4 + 2] = f3;
                i2++;
                i3 = i4 + this.positionChannel.strideSize;
            }
        }
        if (this.has2dAngularVelocity) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i5 >= this.controller.particles.size) {
                    return;
                }
                float f4 = this.angularVelocityChannel.data[i5] * this.controller.deltaTime;
                if (f4 != 0.0f) {
                    float cosDeg = MathUtils.cosDeg(f4);
                    float sinDeg = MathUtils.sinDeg(f4);
                    float f5 = this.rotationChannel.data[i7];
                    float f6 = this.rotationChannel.data[i7 + 1];
                    this.rotationChannel.data[i7] = (f5 * cosDeg) - (f6 * sinDeg);
                    this.rotationChannel.data[i7 + 1] = (f6 * cosDeg) + (f5 * sinDeg);
                }
                i5++;
                i6 = i7 + this.rotationChannel.strideSize;
            }
        } else if (this.has3dAngularVelocity) {
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                int i11 = i10;
                if (i8 < this.controller.particles.size) {
                    float f7 = this.angularVelocityChannel.data[i11];
                    float f8 = this.angularVelocityChannel.data[i11 + 1];
                    float f9 = this.angularVelocityChannel.data[i11 + 2];
                    float f10 = this.rotationChannel.data[i9];
                    float f11 = this.rotationChannel.data[i9 + 1];
                    float f12 = this.rotationChannel.data[i9 + 2];
                    float f13 = this.rotationChannel.data[i9 + 3];
                    TMP_Q.set(f7, f8, f9, 0.0f).mul(f10, f11, f12, f13).mul(0.5f * this.controller.deltaTime).add(f10, f11, f12, f13).nor();
                    this.rotationChannel.data[i9] = TMP_Q.x;
                    this.rotationChannel.data[i9 + 1] = TMP_Q.y;
                    this.rotationChannel.data[i9 + 2] = TMP_Q.z;
                    this.rotationChannel.data[i9 + 3] = TMP_Q.w;
                    i8++;
                    i9 += this.rotationChannel.strideSize;
                    i10 = i11 + this.angularVelocityChannel.strideSize;
                } else {
                    return;
                }
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public DynamicsInfluencer copy() {
        return new DynamicsInfluencer(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        json.writeValue("velocities", this.velocities, Array.class, DynamicsModifier.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        this.velocities.addAll((Array<? extends DynamicsModifier>) json.readValue("velocities", Array.class, DynamicsModifier.class, jsonValue));
    }
}

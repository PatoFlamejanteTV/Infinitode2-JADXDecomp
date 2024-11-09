package com.badlogic.gdx.graphics.g3d.particles.influencers;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier.class */
public abstract class DynamicsModifier extends Influencer {
    protected static final Vector3 TMP_V1 = new Vector3();
    protected static final Vector3 TMP_V2 = new Vector3();
    protected static final Vector3 TMP_V3 = new Vector3();
    protected static final Quaternion TMP_Q = new Quaternion();
    public boolean isGlobal;
    protected ParallelArray.FloatChannel lifeChannel;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$FaceDirection.class */
    public static class FaceDirection extends DynamicsModifier {
        ParallelArray.FloatChannel rotationChannel;
        ParallelArray.FloatChannel accellerationChannel;

        public FaceDirection() {
        }

        public FaceDirection(FaceDirection faceDirection) {
            super(faceDirection);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.accellerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 0;
            int i3 = 0 + (this.controller.particles.size * this.rotationChannel.strideSize);
            while (i < i3) {
                Vector3 nor = TMP_V1.set(this.accellerationChannel.data[i2], this.accellerationChannel.data[i2 + 1], this.accellerationChannel.data[i2 + 2]).nor();
                Vector3 nor2 = TMP_V2.set(TMP_V1).crs(Vector3.Y).nor().crs(TMP_V1).nor();
                Vector3 nor3 = TMP_V3.set(nor2).crs(nor).nor();
                TMP_Q.setFromAxes(false, nor3.x, nor2.x, nor.x, nor3.y, nor2.y, nor.y, nor3.z, nor2.z, nor.z);
                this.rotationChannel.data[i] = TMP_Q.x;
                this.rotationChannel.data[i + 1] = TMP_Q.y;
                this.rotationChannel.data[i + 2] = TMP_Q.z;
                this.rotationChannel.data[i + 3] = TMP_Q.w;
                i += this.rotationChannel.strideSize;
                i2 += this.accellerationChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public ParticleControllerComponent copy() {
            return new FaceDirection(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$Strength.class */
    public static abstract class Strength extends DynamicsModifier {
        protected ParallelArray.FloatChannel strengthChannel;
        public ScaledNumericValue strengthValue;

        public Strength() {
            this.strengthValue = new ScaledNumericValue();
        }

        public Strength(Strength strength) {
            super(strength);
            this.strengthValue = new ScaledNumericValue();
            this.strengthValue.load(strength.strengthValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation.id = this.controller.particleChannels.newId();
            this.strengthChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Interpolation);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            int i3 = i * this.strengthChannel.strideSize;
            int i4 = i3;
            int i5 = i3 + (i2 * this.strengthChannel.strideSize);
            while (i4 < i5) {
                float newLowValue = this.strengthValue.newLowValue();
                float newHighValue = this.strengthValue.newHighValue();
                if (!this.strengthValue.isRelative()) {
                    newHighValue -= newLowValue;
                }
                this.strengthChannel.data[i4] = newLowValue;
                this.strengthChannel.data[i4 + 1] = newHighValue;
                i4 += this.strengthChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void write(Json json) {
            super.write(json);
            json.writeValue("strengthValue", this.strengthValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void read(Json json, JsonValue jsonValue) {
            super.read(json, jsonValue);
            this.strengthValue = (ScaledNumericValue) json.readValue("strengthValue", ScaledNumericValue.class, jsonValue);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$Angular.class */
    public static abstract class Angular extends Strength {
        protected ParallelArray.FloatChannel angularChannel;
        public ScaledNumericValue thetaValue;
        public ScaledNumericValue phiValue;

        public Angular() {
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
        }

        public Angular(Angular angular) {
            super(angular);
            this.thetaValue = new ScaledNumericValue();
            this.phiValue = new ScaledNumericValue();
            this.thetaValue.load(angular.thetaValue);
            this.phiValue.load(angular.phiValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            ParticleChannels.Interpolation4.id = this.controller.particleChannels.newId();
            this.angularChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Interpolation4);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void activateParticles(int i, int i2) {
            super.activateParticles(i, i2);
            int i3 = i * this.angularChannel.strideSize;
            int i4 = i3;
            int i5 = i3 + (i2 * this.angularChannel.strideSize);
            while (i4 < i5) {
                float newLowValue = this.thetaValue.newLowValue();
                float newHighValue = this.thetaValue.newHighValue();
                if (!this.thetaValue.isRelative()) {
                    newHighValue -= newLowValue;
                }
                this.angularChannel.data[i4] = newLowValue;
                this.angularChannel.data[i4 + 1] = newHighValue;
                float newLowValue2 = this.phiValue.newLowValue();
                float newHighValue2 = this.phiValue.newHighValue();
                if (!this.phiValue.isRelative()) {
                    newHighValue2 -= newLowValue2;
                }
                this.angularChannel.data[i4 + 2] = newLowValue2;
                this.angularChannel.data[i4 + 3] = newHighValue2;
                i4 += this.angularChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void write(Json json) {
            super.write(json);
            json.writeValue("thetaValue", this.thetaValue);
            json.writeValue("phiValue", this.phiValue);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
        public void read(Json json, JsonValue jsonValue) {
            super.read(json, jsonValue);
            this.thetaValue = (ScaledNumericValue) json.readValue("thetaValue", ScaledNumericValue.class, jsonValue);
            this.phiValue = (ScaledNumericValue) json.readValue("phiValue", ScaledNumericValue.class, jsonValue);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$Rotational2D.class */
    public static class Rotational2D extends Strength {
        ParallelArray.FloatChannel rotationalVelocity2dChannel;

        public Rotational2D() {
        }

        public Rotational2D(Rotational2D rotational2D) {
            super(rotational2D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationalVelocity2dChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.AngularVelocity2D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 2;
            int i3 = 0;
            int i4 = 0 + (this.controller.particles.size * this.rotationalVelocity2dChannel.strideSize);
            while (i < i4) {
                float[] fArr = this.rotationalVelocity2dChannel.data;
                int i5 = i;
                fArr[i5] = fArr[i5] + this.strengthChannel.data[i3] + (this.strengthChannel.data[i3 + 1] * this.strengthValue.getScale(this.lifeChannel.data[i2]));
                i3 += this.strengthChannel.strideSize;
                i += this.rotationalVelocity2dChannel.strideSize;
                i2 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Rotational2D copy() {
            return new Rotational2D(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$Rotational3D.class */
    public static class Rotational3D extends Angular {
        ParallelArray.FloatChannel rotationChannel;
        ParallelArray.FloatChannel rotationalForceChannel;

        public Rotational3D() {
        }

        public Rotational3D(Rotational3D rotational3D) {
            super(rotational3D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.rotationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Rotation3D);
            this.rotationalForceChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.AngularVelocity3D);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 2;
            int i3 = 0;
            int i4 = 0;
            int i5 = this.controller.particles.size * this.rotationalForceChannel.strideSize;
            while (i < i5) {
                float f = this.lifeChannel.data[i2];
                float scale = this.strengthChannel.data[i3] + (this.strengthChannel.data[i3 + 1] * this.strengthValue.getScale(f));
                float scale2 = this.angularChannel.data[i4 + 2] + (this.angularChannel.data[i4 + 3] * this.phiValue.getScale(f));
                float scale3 = this.angularChannel.data[i4] + (this.angularChannel.data[i4 + 1] * this.thetaValue.getScale(f));
                float cosDeg = MathUtils.cosDeg(scale3);
                float sinDeg = MathUtils.sinDeg(scale3);
                float cosDeg2 = MathUtils.cosDeg(scale2);
                float sinDeg2 = MathUtils.sinDeg(scale2);
                TMP_V3.set(cosDeg * sinDeg2, cosDeg2, sinDeg * sinDeg2);
                TMP_V3.scl(scale * 0.017453292f);
                float[] fArr = this.rotationalForceChannel.data;
                int i6 = i;
                fArr[i6] = fArr[i6] + TMP_V3.x;
                float[] fArr2 = this.rotationalForceChannel.data;
                int i7 = i + 1;
                fArr2[i7] = fArr2[i7] + TMP_V3.y;
                float[] fArr3 = this.rotationalForceChannel.data;
                int i8 = i + 2;
                fArr3[i8] = fArr3[i8] + TMP_V3.z;
                i3 += this.strengthChannel.strideSize;
                i += this.rotationalForceChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i2 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public Rotational3D copy() {
            return new Rotational3D(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$CentripetalAcceleration.class */
    public static class CentripetalAcceleration extends Strength {
        ParallelArray.FloatChannel accelerationChannel;
        ParallelArray.FloatChannel positionChannel;

        public CentripetalAcceleration() {
        }

        public CentripetalAcceleration(CentripetalAcceleration centripetalAcceleration) {
            super(centripetalAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            float f = 0.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            if (!this.isGlobal) {
                float[] fArr = this.controller.transform.val;
                f = fArr[12];
                f2 = fArr[13];
                f3 = fArr[14];
            }
            int i = 2;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = this.controller.particles.size;
            while (i5 < i6) {
                TMP_V3.set(this.positionChannel.data[i3] - f, this.positionChannel.data[i3 + 1] - f2, this.positionChannel.data[i3 + 2] - f3).nor().scl(this.strengthChannel.data[i2] + (this.strengthChannel.data[i2 + 1] * this.strengthValue.getScale(this.lifeChannel.data[i])));
                float[] fArr2 = this.accelerationChannel.data;
                int i7 = i4;
                fArr2[i7] = fArr2[i7] + TMP_V3.x;
                float[] fArr3 = this.accelerationChannel.data;
                int i8 = i4 + 1;
                fArr3[i8] = fArr3[i8] + TMP_V3.y;
                float[] fArr4 = this.accelerationChannel.data;
                int i9 = i4 + 2;
                fArr4[i9] = fArr4[i9] + TMP_V3.z;
                i5++;
                i3 += this.positionChannel.strideSize;
                i2 += this.strengthChannel.strideSize;
                i4 += this.accelerationChannel.strideSize;
                i += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public CentripetalAcceleration copy() {
            return new CentripetalAcceleration(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$PolarAcceleration.class */
    public static class PolarAcceleration extends Angular {
        ParallelArray.FloatChannel directionalVelocityChannel;

        public PolarAcceleration() {
        }

        public PolarAcceleration(PolarAcceleration polarAcceleration) {
            super(polarAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 2;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0 + (this.controller.particles.size * this.directionalVelocityChannel.strideSize);
            while (i < i5) {
                float f = this.lifeChannel.data[i2];
                float scale = this.strengthChannel.data[i3] + (this.strengthChannel.data[i3 + 1] * this.strengthValue.getScale(f));
                float scale2 = this.angularChannel.data[i4 + 2] + (this.angularChannel.data[i4 + 3] * this.phiValue.getScale(f));
                float scale3 = this.angularChannel.data[i4] + (this.angularChannel.data[i4 + 1] * this.thetaValue.getScale(f));
                float cosDeg = MathUtils.cosDeg(scale3);
                float sinDeg = MathUtils.sinDeg(scale3);
                float cosDeg2 = MathUtils.cosDeg(scale2);
                float sinDeg2 = MathUtils.sinDeg(scale2);
                TMP_V3.set(cosDeg * sinDeg2, cosDeg2, sinDeg * sinDeg2).nor().scl(scale);
                if (!this.isGlobal) {
                    this.controller.transform.getRotation(TMP_Q, true);
                    TMP_V3.mul(TMP_Q);
                }
                float[] fArr = this.directionalVelocityChannel.data;
                int i6 = i;
                fArr[i6] = fArr[i6] + TMP_V3.x;
                float[] fArr2 = this.directionalVelocityChannel.data;
                int i7 = i + 1;
                fArr2[i7] = fArr2[i7] + TMP_V3.y;
                float[] fArr3 = this.directionalVelocityChannel.data;
                int i8 = i + 2;
                fArr3[i8] = fArr3[i8] + TMP_V3.z;
                i3 += this.strengthChannel.strideSize;
                i += this.directionalVelocityChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i2 += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public PolarAcceleration copy() {
            return new PolarAcceleration(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$TangentialAcceleration.class */
    public static class TangentialAcceleration extends Angular {
        ParallelArray.FloatChannel directionalVelocityChannel;
        ParallelArray.FloatChannel positionChannel;

        public TangentialAcceleration() {
        }

        public TangentialAcceleration(TangentialAcceleration tangentialAcceleration) {
            super(tangentialAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Angular, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.directionalVelocityChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
            this.positionChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Position);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 0;
            int i2 = 2;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0 + (this.controller.particles.size * this.directionalVelocityChannel.strideSize);
            while (i < i6) {
                float f = this.lifeChannel.data[i2];
                float scale = this.strengthChannel.data[i3] + (this.strengthChannel.data[i3 + 1] * this.strengthValue.getScale(f));
                float scale2 = this.angularChannel.data[i4 + 2] + (this.angularChannel.data[i4 + 3] * this.phiValue.getScale(f));
                float scale3 = this.angularChannel.data[i4] + (this.angularChannel.data[i4 + 1] * this.thetaValue.getScale(f));
                float cosDeg = MathUtils.cosDeg(scale3);
                float sinDeg = MathUtils.sinDeg(scale3);
                float cosDeg2 = MathUtils.cosDeg(scale2);
                float sinDeg2 = MathUtils.sinDeg(scale2);
                TMP_V3.set(cosDeg * sinDeg2, cosDeg2, sinDeg * sinDeg2);
                TMP_V1.set(this.positionChannel.data[i5], this.positionChannel.data[i5 + 1], this.positionChannel.data[i5 + 2]);
                if (!this.isGlobal) {
                    this.controller.transform.getTranslation(TMP_V2);
                    TMP_V1.sub(TMP_V2);
                    this.controller.transform.getRotation(TMP_Q, true);
                    TMP_V3.mul(TMP_Q);
                }
                TMP_V3.crs(TMP_V1).nor().scl(scale);
                float[] fArr = this.directionalVelocityChannel.data;
                int i7 = i;
                fArr[i7] = fArr[i7] + TMP_V3.x;
                float[] fArr2 = this.directionalVelocityChannel.data;
                int i8 = i + 1;
                fArr2[i8] = fArr2[i8] + TMP_V3.y;
                float[] fArr3 = this.directionalVelocityChannel.data;
                int i9 = i + 2;
                fArr3[i9] = fArr3[i9] + TMP_V3.z;
                i3 += this.strengthChannel.strideSize;
                i += this.directionalVelocityChannel.strideSize;
                i4 += this.angularChannel.strideSize;
                i2 += this.lifeChannel.strideSize;
                i5 += this.positionChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public TangentialAcceleration copy() {
            return new TangentialAcceleration(this);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/influencers/DynamicsModifier$BrownianAcceleration.class */
    public static class BrownianAcceleration extends Strength {
        ParallelArray.FloatChannel accelerationChannel;

        public BrownianAcceleration() {
        }

        public BrownianAcceleration(BrownianAcceleration brownianAcceleration) {
            super(brownianAcceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier.Strength, com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void allocateChannels() {
            super.allocateChannels();
            this.accelerationChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Acceleration);
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public void update() {
            int i = 2;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = this.controller.particles.size;
            while (i4 < i5) {
                TMP_V3.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f)).nor().scl(this.strengthChannel.data[i2] + (this.strengthChannel.data[i2 + 1] * this.strengthValue.getScale(this.lifeChannel.data[i])));
                float[] fArr = this.accelerationChannel.data;
                int i6 = i3;
                fArr[i6] = fArr[i6] + TMP_V3.x;
                float[] fArr2 = this.accelerationChannel.data;
                int i7 = i3 + 1;
                fArr2[i7] = fArr2[i7] + TMP_V3.y;
                float[] fArr3 = this.accelerationChannel.data;
                int i8 = i3 + 2;
                fArr3[i8] = fArr3[i8] + TMP_V3.z;
                i4++;
                i2 += this.strengthChannel.strideSize;
                i3 += this.accelerationChannel.strideSize;
                i += this.lifeChannel.strideSize;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
        public BrownianAcceleration copy() {
            return new BrownianAcceleration(this);
        }
    }

    public DynamicsModifier() {
        this.isGlobal = false;
    }

    public DynamicsModifier(DynamicsModifier dynamicsModifier) {
        this.isGlobal = false;
        this.isGlobal = dynamicsModifier.isGlobal;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        super.write(json);
        json.writeValue("isGlobal", Boolean.valueOf(this.isGlobal));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.isGlobal = ((Boolean) json.readValue("isGlobal", Boolean.TYPE, jsonValue)).booleanValue();
    }
}

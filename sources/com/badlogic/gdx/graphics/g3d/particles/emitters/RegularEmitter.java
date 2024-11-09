package com.badlogic.gdx.graphics.g3d.particles.emitters;

import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.values.RangedNumericValue;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/emitters/RegularEmitter.class */
public class RegularEmitter extends Emitter implements Json.Serializable {
    public RangedNumericValue delayValue;
    public RangedNumericValue durationValue;
    public ScaledNumericValue lifeOffsetValue;
    public ScaledNumericValue lifeValue;
    public ScaledNumericValue emissionValue;
    protected int emission;
    protected int emissionDiff;
    protected int emissionDelta;
    protected int lifeOffset;
    protected int lifeOffsetDiff;
    protected int life;
    protected int lifeDiff;
    protected float duration;
    protected float delay;
    protected float durationTimer;
    protected float delayTimer;
    private boolean continuous;
    private EmissionMode emissionMode;
    private ParallelArray.FloatChannel lifeChannel;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/emitters/RegularEmitter$EmissionMode.class */
    public enum EmissionMode {
        Enabled,
        EnabledUntilCycleEnd,
        Disabled
    }

    public RegularEmitter() {
        this.delayValue = new RangedNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeOffsetValue = new ScaledNumericValue();
        this.lifeValue = new ScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.durationValue.setActive(true);
        this.emissionValue.setActive(true);
        this.lifeValue.setActive(true);
        this.continuous = true;
        this.emissionMode = EmissionMode.Enabled;
    }

    public RegularEmitter(RegularEmitter regularEmitter) {
        this();
        set(regularEmitter);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void allocateChannels() {
        this.lifeChannel = (ParallelArray.FloatChannel) this.controller.particles.addChannel(ParticleChannels.Life);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void start() {
        this.delay = this.delayValue.active ? this.delayValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer = 0.0f;
        this.duration = this.durationValue.newLowValue();
        this.percent = this.durationTimer / this.duration;
        this.emission = (int) this.emissionValue.newLowValue();
        this.emissionDiff = (int) this.emissionValue.newHighValue();
        if (!this.emissionValue.isRelative()) {
            this.emissionDiff -= this.emission;
        }
        this.life = (int) this.lifeValue.newLowValue();
        this.lifeDiff = (int) this.lifeValue.newHighValue();
        if (!this.lifeValue.isRelative()) {
            this.lifeDiff -= this.life;
        }
        this.lifeOffset = this.lifeOffsetValue.active ? (int) this.lifeOffsetValue.newLowValue() : 0;
        this.lifeOffsetDiff = (int) this.lifeOffsetValue.newHighValue();
        if (!this.lifeOffsetValue.isRelative()) {
            this.lifeOffsetDiff -= this.lifeOffset;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void init() {
        super.init();
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void activateParticles(int i, int i2) {
        int scale = this.life + ((int) (this.lifeDiff * this.lifeValue.getScale(this.percent)));
        int i3 = scale;
        int scale2 = (int) (this.lifeOffset + (this.lifeOffsetDiff * this.lifeOffsetValue.getScale(this.percent)));
        int i4 = scale2;
        if (scale2 > 0) {
            if (i4 >= scale) {
                i4 = scale - 1;
            }
            i3 = scale - i4;
        }
        float f = 1.0f - (i3 / scale);
        int i5 = i * this.lifeChannel.strideSize;
        int i6 = i5;
        int i7 = i5 + (i2 * this.lifeChannel.strideSize);
        while (i6 < i7) {
            this.lifeChannel.data[i6] = i3;
            this.lifeChannel.data[i6 + 1] = scale;
            this.lifeChannel.data[i6 + 2] = f;
            i6 += this.lifeChannel.strideSize;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        float f = this.controller.deltaTime * 1000.0f;
        if (this.delayTimer < this.delay) {
            this.delayTimer += f;
        } else {
            boolean z = this.emissionMode != EmissionMode.Disabled;
            if (this.durationTimer < this.duration) {
                this.durationTimer += f;
                this.percent = this.durationTimer / this.duration;
            } else if (this.continuous && z && this.emissionMode == EmissionMode.Enabled) {
                this.controller.start();
            } else {
                z = false;
            }
            if (z) {
                this.emissionDelta = (int) (this.emissionDelta + f);
                float scale = this.emission + (this.emissionDiff * this.emissionValue.getScale(this.percent));
                if (scale > 0.0f) {
                    float f2 = 1000.0f / scale;
                    if (this.emissionDelta >= f2) {
                        int min = Math.min((int) (this.emissionDelta / f2), this.maxParticleCount - this.controller.particles.size);
                        this.emissionDelta = (int) (this.emissionDelta - (min * f2));
                        this.emissionDelta = (int) (this.emissionDelta % f2);
                        addParticles(min);
                    }
                }
                if (this.controller.particles.size < this.minParticleCount) {
                    addParticles(this.minParticleCount - this.controller.particles.size);
                }
            }
        }
        int i = this.controller.particles.size;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.controller.particles.size) {
            float[] fArr = this.lifeChannel.data;
            int i4 = i3;
            float f3 = fArr[i4] - f;
            fArr[i4] = f3;
            if (f3 <= 0.0f) {
                this.controller.particles.removeElement(i2);
            } else {
                this.lifeChannel.data[i3 + 2] = 1.0f - (this.lifeChannel.data[i3] / this.lifeChannel.data[i3 + 1]);
                i2++;
                i3 += this.lifeChannel.strideSize;
            }
        }
        if (this.controller.particles.size < i) {
            this.controller.killParticles(this.controller.particles.size, i - this.controller.particles.size);
        }
    }

    private void addParticles(int i) {
        int min = Math.min(i, this.maxParticleCount - this.controller.particles.size);
        if (min <= 0) {
            return;
        }
        this.controller.activateParticles(this.controller.particles.size, min);
        this.controller.particles.size += min;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public RangedNumericValue getDuration() {
        return this.durationValue;
    }

    public RangedNumericValue getDelay() {
        return this.delayValue;
    }

    public ScaledNumericValue getLifeOffset() {
        return this.lifeOffsetValue;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    public void setContinuous(boolean z) {
        this.continuous = z;
    }

    public EmissionMode getEmissionMode() {
        return this.emissionMode;
    }

    public void setEmissionMode(EmissionMode emissionMode) {
        this.emissionMode = emissionMode;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter
    public boolean isComplete() {
        return this.delayTimer >= this.delay && this.durationTimer >= this.duration && this.controller.particles.size == 0;
    }

    public float getPercentComplete() {
        if (this.delayTimer < this.delay) {
            return 0.0f;
        }
        return Math.min(1.0f, this.durationTimer / this.duration);
    }

    public void set(RegularEmitter regularEmitter) {
        super.set((Emitter) regularEmitter);
        this.delayValue.load(regularEmitter.delayValue);
        this.durationValue.load(regularEmitter.durationValue);
        this.lifeOffsetValue.load(regularEmitter.lifeOffsetValue);
        this.lifeValue.load(regularEmitter.lifeValue);
        this.emissionValue.load(regularEmitter.emissionValue);
        this.emission = regularEmitter.emission;
        this.emissionDiff = regularEmitter.emissionDiff;
        this.emissionDelta = regularEmitter.emissionDelta;
        this.lifeOffset = regularEmitter.lifeOffset;
        this.lifeOffsetDiff = regularEmitter.lifeOffsetDiff;
        this.life = regularEmitter.life;
        this.lifeDiff = regularEmitter.lifeDiff;
        this.duration = regularEmitter.duration;
        this.delay = regularEmitter.delay;
        this.durationTimer = regularEmitter.durationTimer;
        this.delayTimer = regularEmitter.delayTimer;
        this.continuous = regularEmitter.continuous;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public ParticleControllerComponent copy() {
        return new RegularEmitter(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void write(Json json) {
        super.write(json);
        json.writeValue("continous", Boolean.valueOf(this.continuous));
        json.writeValue("emission", this.emissionValue);
        json.writeValue("delay", this.delayValue);
        json.writeValue("duration", this.durationValue);
        json.writeValue("life", this.lifeValue);
        json.writeValue("lifeOffset", this.lifeOffsetValue);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter, com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent, com.badlogic.gdx.utils.Json.Serializable
    public void read(Json json, JsonValue jsonValue) {
        super.read(json, jsonValue);
        this.continuous = ((Boolean) json.readValue("continous", Boolean.TYPE, jsonValue)).booleanValue();
        this.emissionValue = (ScaledNumericValue) json.readValue("emission", ScaledNumericValue.class, jsonValue);
        this.delayValue = (RangedNumericValue) json.readValue("delay", RangedNumericValue.class, jsonValue);
        this.durationValue = (RangedNumericValue) json.readValue("duration", RangedNumericValue.class, jsonValue);
        this.lifeValue = (ScaledNumericValue) json.readValue("life", ScaledNumericValue.class, jsonValue);
        this.lifeOffsetValue = (ScaledNumericValue) json.readValue("lifeOffset", ScaledNumericValue.class, jsonValue);
    }
}

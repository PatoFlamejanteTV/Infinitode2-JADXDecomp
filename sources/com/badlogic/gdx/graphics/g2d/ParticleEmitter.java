package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter.class */
public class ParticleEmitter {
    private static final int UPDATE_SCALE = 1;
    private static final int UPDATE_ANGLE = 2;
    private static final int UPDATE_ROTATION = 4;
    private static final int UPDATE_VELOCITY = 8;
    private static final int UPDATE_WIND = 16;
    private static final int UPDATE_GRAVITY = 32;
    private static final int UPDATE_TINT = 64;
    private static final int UPDATE_SPRITE = 128;
    private RangedNumericValue delayValue;
    private IndependentScaledNumericValue lifeOffsetValue;
    private RangedNumericValue durationValue;
    private IndependentScaledNumericValue lifeValue;
    private ScaledNumericValue emissionValue;
    private ScaledNumericValue xScaleValue;
    private ScaledNumericValue yScaleValue;
    private ScaledNumericValue rotationValue;
    private ScaledNumericValue velocityValue;
    private ScaledNumericValue angleValue;
    private ScaledNumericValue windValue;
    private ScaledNumericValue gravityValue;
    private ScaledNumericValue transparencyValue;
    private GradientColorValue tintValue;
    private RangedNumericValue xOffsetValue;
    private RangedNumericValue yOffsetValue;
    private ScaledNumericValue spawnWidthValue;
    private ScaledNumericValue spawnHeightValue;
    private SpawnShapeValue spawnShapeValue;
    private RangedNumericValue[] xSizeValues;
    private RangedNumericValue[] ySizeValues;
    private RangedNumericValue[] motionValues;
    private float accumulator;
    private Array<Sprite> sprites;
    private SpriteMode spriteMode;
    private Particle[] particles;
    private int minParticleCount;
    private int maxParticleCount;
    private float x;
    private float y;
    private String name;
    private Array<String> imagePaths;
    private int activeCount;
    private boolean[] active;
    private boolean firstUpdate;
    private boolean flipX;
    private boolean flipY;
    private int updateFlags;
    private boolean allowCompletion;
    private BoundingBox bounds;
    private int emission;
    private int emissionDiff;
    private int emissionDelta;
    private int lifeOffset;
    private int lifeOffsetDiff;
    private int life;
    private int lifeDiff;
    private float spawnWidth;
    private float spawnWidthDiff;
    private float spawnHeight;
    private float spawnHeightDiff;
    public float duration;
    public float durationTimer;
    private float delay;
    private float delayTimer;
    private boolean attached;
    private boolean continuous;
    private boolean aligned;
    private boolean behind;
    private boolean additive;
    private boolean premultipliedAlpha;
    boolean cleansUpBlendFunction;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$SpawnEllipseSide.class */
    public enum SpawnEllipseSide {
        both,
        top,
        bottom
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$SpawnShape.class */
    public enum SpawnShape {
        point,
        line,
        square,
        ellipse
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$SpriteMode.class */
    public enum SpriteMode {
        single,
        random,
        animated
    }

    public ParticleEmitter() {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        initialize();
    }

    public ParticleEmitter(BufferedReader bufferedReader) {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        initialize();
        load(bufferedReader);
    }

    public ParticleEmitter(ParticleEmitter particleEmitter) {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        this.sprites = new Array<>(particleEmitter.sprites);
        this.name = particleEmitter.name;
        this.imagePaths = new Array<>(particleEmitter.imagePaths);
        setMaxParticleCount(particleEmitter.maxParticleCount);
        this.minParticleCount = particleEmitter.minParticleCount;
        this.delayValue.load(particleEmitter.delayValue);
        this.durationValue.load(particleEmitter.durationValue);
        this.emissionValue.load(particleEmitter.emissionValue);
        this.lifeValue.load(particleEmitter.lifeValue);
        this.lifeOffsetValue.load(particleEmitter.lifeOffsetValue);
        this.xScaleValue.load(particleEmitter.xScaleValue);
        this.yScaleValue.load(particleEmitter.yScaleValue);
        this.rotationValue.load(particleEmitter.rotationValue);
        this.velocityValue.load(particleEmitter.velocityValue);
        this.angleValue.load(particleEmitter.angleValue);
        this.windValue.load(particleEmitter.windValue);
        this.gravityValue.load(particleEmitter.gravityValue);
        this.transparencyValue.load(particleEmitter.transparencyValue);
        this.tintValue.load(particleEmitter.tintValue);
        this.xOffsetValue.load(particleEmitter.xOffsetValue);
        this.yOffsetValue.load(particleEmitter.yOffsetValue);
        this.spawnWidthValue.load(particleEmitter.spawnWidthValue);
        this.spawnHeightValue.load(particleEmitter.spawnHeightValue);
        this.spawnShapeValue.load(particleEmitter.spawnShapeValue);
        this.attached = particleEmitter.attached;
        this.continuous = particleEmitter.continuous;
        this.aligned = particleEmitter.aligned;
        this.behind = particleEmitter.behind;
        this.additive = particleEmitter.additive;
        this.premultipliedAlpha = particleEmitter.premultipliedAlpha;
        this.cleansUpBlendFunction = particleEmitter.cleansUpBlendFunction;
        this.spriteMode = particleEmitter.spriteMode;
        setPosition(particleEmitter.getX(), particleEmitter.getY());
    }

    private void initialize() {
        this.sprites = new Array<>();
        this.imagePaths = new Array<>();
        this.durationValue.setAlwaysActive(true);
        this.emissionValue.setAlwaysActive(true);
        this.lifeValue.setAlwaysActive(true);
        this.xScaleValue.setAlwaysActive(true);
        this.transparencyValue.setAlwaysActive(true);
        this.spawnShapeValue.setAlwaysActive(true);
        this.spawnWidthValue.setAlwaysActive(true);
        this.spawnHeightValue.setAlwaysActive(true);
    }

    public void setMaxParticleCount(int i) {
        this.maxParticleCount = i;
        this.active = new boolean[i];
        this.activeCount = 0;
        this.particles = new Particle[i];
    }

    public void addParticle() {
        int i = this.activeCount;
        if (i == this.maxParticleCount) {
            return;
        }
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!zArr[i2]) {
                activateParticle(i2);
                zArr[i2] = true;
                this.activeCount = i + 1;
                return;
            }
        }
    }

    public void addParticles(int i) {
        int min = Math.min(i, this.maxParticleCount - this.activeCount);
        if (min == 0) {
            return;
        }
        boolean[] zArr = this.active;
        int i2 = 0;
        int length = zArr.length;
        loop0: for (int i3 = 0; i3 < min; i3++) {
            while (i2 < length) {
                if (zArr[i2]) {
                    i2++;
                } else {
                    activateParticle(i2);
                    int i4 = i2;
                    i2++;
                    zArr[i4] = true;
                }
            }
        }
        this.activeCount += min;
    }

    public void update(float f) {
        this.accumulator += f * 1000.0f;
        if (this.accumulator < 1.0f) {
            return;
        }
        int i = (int) this.accumulator;
        this.accumulator -= i;
        if (this.delayTimer < this.delay) {
            this.delayTimer += i;
        } else {
            boolean z = false;
            if (this.firstUpdate) {
                this.firstUpdate = false;
                addParticle();
            }
            if (this.durationTimer < this.duration) {
                this.durationTimer += i;
            } else if (!this.continuous || this.allowCompletion) {
                z = true;
            } else {
                restart();
            }
            if (!z) {
                this.emissionDelta += i;
                float scale = this.emission + (this.emissionDiff * this.emissionValue.getScale(this.durationTimer / this.duration));
                if (scale > 0.0f) {
                    float f2 = 1000.0f / scale;
                    if (this.emissionDelta >= f2) {
                        int min = Math.min((int) (this.emissionDelta / f2), this.maxParticleCount - this.activeCount);
                        this.emissionDelta = (int) (this.emissionDelta - (min * f2));
                        this.emissionDelta = (int) (this.emissionDelta % f2);
                        addParticles(min);
                    }
                }
                if (this.activeCount < this.minParticleCount) {
                    addParticles(this.minParticleCount - this.activeCount);
                }
            }
        }
        boolean[] zArr = this.active;
        int i2 = this.activeCount;
        Particle[] particleArr = this.particles;
        int length = zArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (zArr[i3] && !updateParticle(particleArr[i3], f, i)) {
                zArr[i3] = false;
                i2--;
            }
        }
        this.activeCount = i2;
    }

    public void draw(Batch batch) {
        if (this.premultipliedAlpha) {
            batch.setBlendFunction(1, 771);
        } else if (this.additive) {
            batch.setBlendFunction(770, 1);
        } else {
            batch.setBlendFunction(770, 771);
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (zArr[i]) {
                particleArr[i].draw(batch);
            }
        }
        if (this.cleansUpBlendFunction) {
            if (this.additive || this.premultipliedAlpha) {
                batch.setBlendFunction(770, 771);
            }
        }
    }

    public void draw(Batch batch, float f) {
        this.accumulator += f * 1000.0f;
        if (this.accumulator < 1.0f) {
            draw(batch);
            return;
        }
        int i = (int) this.accumulator;
        this.accumulator -= i;
        if (this.premultipliedAlpha) {
            batch.setBlendFunction(1, 771);
        } else if (this.additive) {
            batch.setBlendFunction(770, 1);
        } else {
            batch.setBlendFunction(770, 771);
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        int i2 = this.activeCount;
        int length = zArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (zArr[i3]) {
                Particle particle = particleArr[i3];
                if (updateParticle(particle, f, i)) {
                    particle.draw(batch);
                } else {
                    zArr[i3] = false;
                    i2--;
                }
            }
        }
        this.activeCount = i2;
        if (this.cleansUpBlendFunction && (this.additive || this.premultipliedAlpha)) {
            batch.setBlendFunction(770, 771);
        }
        if (this.delayTimer < this.delay) {
            this.delayTimer += i;
            return;
        }
        if (this.firstUpdate) {
            this.firstUpdate = false;
            addParticle();
        }
        if (this.durationTimer < this.duration) {
            this.durationTimer += i;
        } else if (!this.continuous || this.allowCompletion) {
            return;
        } else {
            restart();
        }
        this.emissionDelta += i;
        float scale = this.emission + (this.emissionDiff * this.emissionValue.getScale(this.durationTimer / this.duration));
        if (scale > 0.0f) {
            float f2 = 1000.0f / scale;
            if (this.emissionDelta >= f2) {
                int min = Math.min((int) (this.emissionDelta / f2), this.maxParticleCount - i2);
                this.emissionDelta = (int) (this.emissionDelta - (min * f2));
                this.emissionDelta = (int) (this.emissionDelta % f2);
                addParticles(min);
            }
        }
        if (i2 < this.minParticleCount) {
            addParticles(this.minParticleCount - i2);
        }
    }

    public void start() {
        this.firstUpdate = true;
        this.allowCompletion = false;
        restart();
    }

    public void reset() {
        reset(true);
    }

    public void reset(boolean z) {
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
        boolean[] zArr = this.active;
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            zArr[i] = false;
        }
        this.activeCount = 0;
        if (z) {
            start();
        }
    }

    private void restart() {
        this.delay = this.delayValue.active ? this.delayValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer -= this.duration;
        this.duration = this.durationValue.newLowValue();
        this.emission = (int) this.emissionValue.newLowValue();
        this.emissionDiff = (int) this.emissionValue.newHighValue();
        if (!this.emissionValue.relative) {
            this.emissionDiff -= this.emission;
        }
        if (!this.lifeValue.independent) {
            generateLifeValues();
        }
        if (!this.lifeOffsetValue.independent) {
            generateLifeOffsetValues();
        }
        this.spawnWidth = this.spawnWidthValue.newLowValue();
        this.spawnWidthDiff = this.spawnWidthValue.newHighValue();
        if (!this.spawnWidthValue.relative) {
            this.spawnWidthDiff -= this.spawnWidth;
        }
        this.spawnHeight = this.spawnHeightValue.newLowValue();
        this.spawnHeightDiff = this.spawnHeightValue.newHighValue();
        if (!this.spawnHeightValue.relative) {
            this.spawnHeightDiff -= this.spawnHeight;
        }
        this.updateFlags = 0;
        if (this.angleValue.active && this.angleValue.timeline.length > 1) {
            this.updateFlags |= 2;
        }
        if (this.velocityValue.active) {
            this.updateFlags |= 8;
        }
        if (this.xScaleValue.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        if (this.yScaleValue.active && this.yScaleValue.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        if (this.rotationValue.active && this.rotationValue.timeline.length > 1) {
            this.updateFlags |= 4;
        }
        if (this.windValue.active) {
            this.updateFlags |= 16;
        }
        if (this.gravityValue.active) {
            this.updateFlags |= 32;
        }
        if (this.tintValue.timeline.length > 1) {
            this.updateFlags |= 64;
        }
        if (this.spriteMode == SpriteMode.animated) {
            this.updateFlags |= 128;
        }
    }

    protected Particle newParticle(Sprite sprite) {
        return new Particle(sprite);
    }

    protected Particle[] getParticles() {
        return this.particles;
    }

    private void activateParticle(int i) {
        float random;
        float random2;
        float random3;
        Sprite sprite = null;
        switch (this.spriteMode) {
            case single:
            case animated:
                sprite = this.sprites.first();
                break;
            case random:
                sprite = this.sprites.random();
                break;
        }
        Particle particle = this.particles[i];
        Particle particle2 = particle;
        if (particle == null) {
            Particle[] particleArr = this.particles;
            Particle newParticle = newParticle(sprite);
            particle2 = newParticle;
            particleArr[i] = newParticle;
            particle2.flip(this.flipX, this.flipY);
        } else {
            particle2.set(sprite);
        }
        float f = this.durationTimer / this.duration;
        int i2 = this.updateFlags;
        if (this.lifeValue.independent) {
            generateLifeValues();
        }
        if (this.lifeOffsetValue.independent) {
            generateLifeOffsetValues();
        }
        Particle particle3 = particle2;
        int scale = this.life + ((int) (this.lifeDiff * this.lifeValue.getScale(f)));
        particle3.life = scale;
        particle3.currentLife = scale;
        if (this.velocityValue.active) {
            particle2.velocity = this.velocityValue.newLowValue();
            particle2.velocityDiff = this.velocityValue.newHighValue();
            if (!this.velocityValue.relative) {
                particle2.velocityDiff -= particle2.velocity;
            }
        }
        particle2.angle = this.angleValue.newLowValue();
        particle2.angleDiff = this.angleValue.newHighValue();
        if (!this.angleValue.relative) {
            particle2.angleDiff -= particle2.angle;
        }
        float f2 = 0.0f;
        if ((i2 & 2) == 0) {
            f2 = particle2.angle + (particle2.angleDiff * this.angleValue.getScale(0.0f));
            particle2.angle = f2;
            particle2.angleCos = MathUtils.cosDeg(f2);
            particle2.angleSin = MathUtils.sinDeg(f2);
        }
        float width = sprite.getWidth();
        float height = sprite.getHeight();
        particle2.xScale = this.xScaleValue.newLowValue() / width;
        particle2.xScaleDiff = this.xScaleValue.newHighValue() / width;
        if (!this.xScaleValue.relative) {
            particle2.xScaleDiff -= particle2.xScale;
        }
        if (this.yScaleValue.active) {
            particle2.yScale = this.yScaleValue.newLowValue() / height;
            particle2.yScaleDiff = this.yScaleValue.newHighValue() / height;
            if (!this.yScaleValue.relative) {
                particle2.yScaleDiff -= particle2.yScale;
            }
            Particle particle4 = particle2;
            particle4.setScale(particle4.xScale + (particle2.xScaleDiff * this.xScaleValue.getScale(0.0f)), particle2.yScale + (particle2.yScaleDiff * this.yScaleValue.getScale(0.0f)));
        } else {
            Particle particle5 = particle2;
            particle5.setScale(particle5.xScale + (particle2.xScaleDiff * this.xScaleValue.getScale(0.0f)));
        }
        if (this.rotationValue.active) {
            particle2.rotation = this.rotationValue.newLowValue();
            particle2.rotationDiff = this.rotationValue.newHighValue();
            if (!this.rotationValue.relative) {
                particle2.rotationDiff -= particle2.rotation;
            }
            float scale2 = particle2.rotation + (particle2.rotationDiff * this.rotationValue.getScale(0.0f));
            if (this.aligned) {
                scale2 += f2;
            }
            particle2.setRotation(scale2);
        }
        if (this.windValue.active) {
            particle2.wind = this.windValue.newLowValue();
            particle2.windDiff = this.windValue.newHighValue();
            if (!this.windValue.relative) {
                particle2.windDiff -= particle2.wind;
            }
        }
        if (this.gravityValue.active) {
            particle2.gravity = this.gravityValue.newLowValue();
            particle2.gravityDiff = this.gravityValue.newHighValue();
            if (!this.gravityValue.relative) {
                particle2.gravityDiff -= particle2.gravity;
            }
        }
        float[] fArr = particle2.tint;
        float[] fArr2 = fArr;
        if (fArr == null) {
            float[] fArr3 = new float[3];
            fArr2 = fArr3;
            particle2.tint = fArr3;
        }
        float[] color = this.tintValue.getColor(0.0f);
        fArr2[0] = color[0];
        fArr2[1] = color[1];
        fArr2[2] = color[2];
        particle2.transparency = this.transparencyValue.newLowValue();
        particle2.transparencyDiff = this.transparencyValue.newHighValue() - particle2.transparency;
        float f3 = this.x;
        if (this.xOffsetValue.active) {
            f3 += this.xOffsetValue.newLowValue();
        }
        float f4 = this.y;
        if (this.yOffsetValue.active) {
            f4 += this.yOffsetValue.newLowValue();
        }
        switch (this.spawnShapeValue.shape) {
            case square:
                float scale3 = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
                float scale4 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
                f3 += MathUtils.random(scale3) - (scale3 * 0.5f);
                f4 += MathUtils.random(scale4) - (scale4 * 0.5f);
                break;
            case ellipse:
                float scale5 = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
                float scale6 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
                float f5 = scale5 * 0.5f;
                float f6 = scale6 * 0.5f;
                if (f5 != 0.0f && f6 != 0.0f) {
                    float f7 = f5 / f6;
                    if (this.spawnShapeValue.edges) {
                        switch (this.spawnShapeValue.side) {
                            case top:
                                random3 = -MathUtils.random(179.0f);
                                break;
                            case bottom:
                                random3 = MathUtils.random(179.0f);
                                break;
                            default:
                                random3 = MathUtils.random(360.0f);
                                break;
                        }
                        float cosDeg = MathUtils.cosDeg(random3);
                        float sinDeg = MathUtils.sinDeg(random3);
                        f3 += cosDeg * f5;
                        f4 += (sinDeg * f5) / f7;
                        if ((i2 & 2) == 0) {
                            particle2.angle = random3;
                            particle2.angleCos = cosDeg;
                            particle2.angleSin = sinDeg;
                            break;
                        }
                    } else {
                        float f8 = f5 * f5;
                        do {
                            random = MathUtils.random(scale5) - f5;
                            random2 = MathUtils.random(scale5) - f5;
                        } while ((random * random) + (random2 * random2) > f8);
                        f3 += random;
                        f4 += random2 / f7;
                        break;
                    }
                }
                break;
            case line:
                float scale7 = this.spawnWidth + (this.spawnWidthDiff * this.spawnWidthValue.getScale(f));
                float scale8 = this.spawnHeight + (this.spawnHeightDiff * this.spawnHeightValue.getScale(f));
                if (scale7 != 0.0f) {
                    float random4 = scale7 * MathUtils.random();
                    f3 += random4;
                    f4 += random4 * (scale8 / scale7);
                    break;
                } else {
                    f4 += scale8 * MathUtils.random();
                    break;
                }
        }
        particle2.setBounds(f3 - (width * 0.5f), f4 - (height * 0.5f), width, height);
        int scale9 = (int) (this.lifeOffset + (this.lifeOffsetDiff * this.lifeOffsetValue.getScale(f)));
        int i3 = scale9;
        if (scale9 > 0) {
            if (i3 >= particle2.currentLife) {
                i3 = particle2.currentLife - 1;
            }
            updateParticle(particle2, i3 / 1000.0f, i3);
        }
    }

    private boolean updateParticle(Particle particle, float f, int i) {
        float[] fArr;
        int min;
        float f2;
        float f3;
        int i2 = particle.currentLife - i;
        if (i2 <= 0) {
            return false;
        }
        particle.currentLife = i2;
        float f4 = 1.0f - (particle.currentLife / particle.life);
        int i3 = this.updateFlags;
        if ((i3 & 1) != 0) {
            if (this.yScaleValue.active) {
                particle.setScale(particle.xScale + (particle.xScaleDiff * this.xScaleValue.getScale(f4)), particle.yScale + (particle.yScaleDiff * this.yScaleValue.getScale(f4)));
            } else {
                particle.setScale(particle.xScale + (particle.xScaleDiff * this.xScaleValue.getScale(f4)));
            }
        }
        if ((i3 & 8) != 0) {
            float scale = (particle.velocity + (particle.velocityDiff * this.velocityValue.getScale(f4))) * f;
            if ((i3 & 2) != 0) {
                float scale2 = particle.angle + (particle.angleDiff * this.angleValue.getScale(f4));
                f2 = scale * MathUtils.cosDeg(scale2);
                f3 = scale * MathUtils.sinDeg(scale2);
                if ((i3 & 4) != 0) {
                    float scale3 = particle.rotation + (particle.rotationDiff * this.rotationValue.getScale(f4));
                    if (this.aligned) {
                        scale3 += scale2;
                    }
                    particle.setRotation(scale3);
                }
            } else {
                f2 = scale * particle.angleCos;
                f3 = scale * particle.angleSin;
                if (this.aligned || (i3 & 4) != 0) {
                    float scale4 = particle.rotation + (particle.rotationDiff * this.rotationValue.getScale(f4));
                    if (this.aligned) {
                        scale4 += particle.angle;
                    }
                    particle.setRotation(scale4);
                }
            }
            if ((i3 & 16) != 0) {
                f2 += (particle.wind + (particle.windDiff * this.windValue.getScale(f4))) * f;
            }
            if ((i3 & 32) != 0) {
                f3 += (particle.gravity + (particle.gravityDiff * this.gravityValue.getScale(f4))) * f;
            }
            particle.translate(f2, f3);
        } else if ((i3 & 4) != 0) {
            particle.setRotation(particle.rotation + (particle.rotationDiff * this.rotationValue.getScale(f4)));
        }
        if ((i3 & 64) != 0) {
            fArr = this.tintValue.getColor(f4);
        } else {
            fArr = particle.tint;
        }
        if (this.premultipliedAlpha) {
            float f5 = this.additive ? 0.0f : 1.0f;
            float scale5 = particle.transparency + (particle.transparencyDiff * this.transparencyValue.getScale(f4));
            particle.setColor(fArr[0] * scale5, fArr[1] * scale5, fArr[2] * scale5, scale5 * f5);
        } else {
            particle.setColor(fArr[0], fArr[1], fArr[2], particle.transparency + (particle.transparencyDiff * this.transparencyValue.getScale(f4)));
        }
        if ((i3 & 128) != 0 && particle.frame != (min = Math.min((int) (f4 * this.sprites.size), this.sprites.size - 1))) {
            Sprite sprite = this.sprites.get(min);
            float width = particle.getWidth();
            float height = particle.getHeight();
            particle.setRegion(sprite);
            particle.setSize(sprite.getWidth(), sprite.getHeight());
            particle.setOrigin(sprite.getOriginX(), sprite.getOriginY());
            particle.translate((width - sprite.getWidth()) * 0.5f, (height - sprite.getHeight()) * 0.5f);
            particle.frame = min;
            return true;
        }
        return true;
    }

    private void generateLifeValues() {
        this.life = (int) this.lifeValue.newLowValue();
        this.lifeDiff = (int) this.lifeValue.newHighValue();
        if (!this.lifeValue.relative) {
            this.lifeDiff -= this.life;
        }
    }

    private void generateLifeOffsetValues() {
        this.lifeOffset = this.lifeOffsetValue.active ? (int) this.lifeOffsetValue.newLowValue() : 0;
        this.lifeOffsetDiff = (int) this.lifeOffsetValue.newHighValue();
        if (!this.lifeOffsetValue.relative) {
            this.lifeOffsetDiff -= this.lifeOffset;
        }
    }

    public void setPosition(float f, float f2) {
        if (this.attached) {
            float f3 = f - this.x;
            float f4 = f2 - this.y;
            boolean[] zArr = this.active;
            int length = zArr.length;
            for (int i = 0; i < length; i++) {
                if (zArr[i]) {
                    this.particles[i].translate(f3, f4);
                }
            }
        }
        this.x = f;
        this.y = f2;
    }

    public void setSprites(Array<Sprite> array) {
        Particle particle;
        this.sprites = array;
        if (array.size == 0) {
            return;
        }
        int length = this.particles.length;
        for (int i = 0; i < length && (particle = this.particles[i]) != null; i++) {
            Sprite sprite = null;
            switch (this.spriteMode) {
                case single:
                    sprite = array.first();
                    break;
                case animated:
                    particle.frame = Math.min((int) ((1.0f - (particle.currentLife / particle.life)) * array.size), array.size - 1);
                    sprite = array.get(particle.frame);
                    break;
                case random:
                    sprite = array.random();
                    break;
            }
            particle.setRegion(sprite);
            particle.setOrigin(sprite.getOriginX(), sprite.getOriginY());
        }
    }

    public void setSpriteMode(SpriteMode spriteMode) {
        this.spriteMode = spriteMode;
    }

    public void preAllocateParticles() {
        if (this.sprites.isEmpty()) {
            throw new IllegalStateException("ParticleEmitter.setSprites() must have been called before preAllocateParticles()");
        }
        for (int i = 0; i < this.particles.length; i++) {
            if (this.particles[i] == null) {
                Particle newParticle = newParticle(this.sprites.first());
                this.particles[i] = newParticle;
                newParticle.flip(this.flipX, this.flipY);
            }
        }
    }

    public void allowCompletion() {
        this.allowCompletion = true;
        this.durationTimer = this.duration;
    }

    public boolean getAllowCompletion() {
        return this.allowCompletion;
    }

    public Array<Sprite> getSprites() {
        return this.sprites;
    }

    public SpriteMode getSpriteMode() {
        return this.spriteMode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getXScale() {
        return this.xScaleValue;
    }

    public ScaledNumericValue getYScale() {
        return this.yScaleValue;
    }

    public ScaledNumericValue getRotation() {
        return this.rotationValue;
    }

    public GradientColorValue getTint() {
        return this.tintValue;
    }

    public ScaledNumericValue getVelocity() {
        return this.velocityValue;
    }

    public ScaledNumericValue getWind() {
        return this.windValue;
    }

    public ScaledNumericValue getGravity() {
        return this.gravityValue;
    }

    public ScaledNumericValue getAngle() {
        return this.angleValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public ScaledNumericValue getTransparency() {
        return this.transparencyValue;
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

    public RangedNumericValue getXOffsetValue() {
        return this.xOffsetValue;
    }

    public RangedNumericValue getYOffsetValue() {
        return this.yOffsetValue;
    }

    public ScaledNumericValue getSpawnWidth() {
        return this.spawnWidthValue;
    }

    public ScaledNumericValue getSpawnHeight() {
        return this.spawnHeightValue;
    }

    public SpawnShapeValue getSpawnShape() {
        return this.spawnShapeValue;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public void setAttached(boolean z) {
        this.attached = z;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    public void setContinuous(boolean z) {
        this.continuous = z;
    }

    public boolean isAligned() {
        return this.aligned;
    }

    public void setAligned(boolean z) {
        this.aligned = z;
    }

    public boolean isAdditive() {
        return this.additive;
    }

    public void setAdditive(boolean z) {
        this.additive = z;
    }

    public boolean cleansUpBlendFunction() {
        return this.cleansUpBlendFunction;
    }

    public void setCleansUpBlendFunction(boolean z) {
        this.cleansUpBlendFunction = z;
    }

    public boolean isBehind() {
        return this.behind;
    }

    public void setBehind(boolean z) {
        this.behind = z;
    }

    public boolean isPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public void setPremultipliedAlpha(boolean z) {
        this.premultipliedAlpha = z;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    public void setMinParticleCount(int i) {
        this.minParticleCount = i;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public boolean isComplete() {
        return (!this.continuous || this.allowCompletion) && this.delayTimer >= this.delay && this.durationTimer >= this.duration && this.activeCount == 0;
    }

    public float getPercentComplete() {
        if (this.delayTimer < this.delay) {
            return 0.0f;
        }
        return Math.min(1.0f, this.durationTimer / this.duration);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getActiveCount() {
        return this.activeCount;
    }

    public Array<String> getImagePaths() {
        return this.imagePaths;
    }

    public void setImagePaths(Array<String> array) {
        this.imagePaths = array;
    }

    public void setFlip(boolean z, boolean z2) {
        this.flipX = z;
        this.flipY = z2;
        if (this.particles == null) {
            return;
        }
        int length = this.particles.length;
        for (int i = 0; i < length; i++) {
            Particle particle = this.particles[i];
            if (particle != null) {
                particle.flip(z, z2);
            }
        }
    }

    public void flipY() {
        this.angleValue.setHigh(-this.angleValue.getHighMin(), -this.angleValue.getHighMax());
        this.angleValue.setLow(-this.angleValue.getLowMin(), -this.angleValue.getLowMax());
        this.gravityValue.setHigh(-this.gravityValue.getHighMin(), -this.gravityValue.getHighMax());
        this.gravityValue.setLow(-this.gravityValue.getLowMin(), -this.gravityValue.getLowMax());
        this.windValue.setHigh(-this.windValue.getHighMin(), -this.windValue.getHighMax());
        this.windValue.setLow(-this.windValue.getLowMin(), -this.windValue.getLowMax());
        this.rotationValue.setHigh(-this.rotationValue.getHighMin(), -this.rotationValue.getHighMax());
        this.rotationValue.setLow(-this.rotationValue.getLowMin(), -this.rotationValue.getLowMax());
        this.yOffsetValue.setLow(-this.yOffsetValue.getLowMin(), -this.yOffsetValue.getLowMax());
    }

    public BoundingBox getBoundingBox() {
        if (this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        Particle[] particleArr = this.particles;
        boolean[] zArr = this.active;
        BoundingBox boundingBox = this.bounds;
        boundingBox.inf();
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (zArr[i]) {
                Rectangle boundingRectangle = particleArr[i].getBoundingRectangle();
                boundingBox.ext(boundingRectangle.x, boundingRectangle.y, 0.0f);
                boundingBox.ext(boundingRectangle.x + boundingRectangle.width, boundingRectangle.y + boundingRectangle.height, 0.0f);
            }
        }
        return boundingBox;
    }

    protected RangedNumericValue[] getXSizeValues() {
        if (this.xSizeValues == null) {
            this.xSizeValues = new RangedNumericValue[3];
            this.xSizeValues[0] = this.xScaleValue;
            this.xSizeValues[1] = this.spawnWidthValue;
            this.xSizeValues[2] = this.xOffsetValue;
        }
        return this.xSizeValues;
    }

    protected RangedNumericValue[] getYSizeValues() {
        if (this.ySizeValues == null) {
            this.ySizeValues = new RangedNumericValue[3];
            this.ySizeValues[0] = this.yScaleValue;
            this.ySizeValues[1] = this.spawnHeightValue;
            this.ySizeValues[2] = this.yOffsetValue;
        }
        return this.ySizeValues;
    }

    protected RangedNumericValue[] getMotionValues() {
        if (this.motionValues == null) {
            this.motionValues = new RangedNumericValue[3];
            this.motionValues[0] = this.velocityValue;
            this.motionValues[1] = this.windValue;
            this.motionValues[2] = this.gravityValue;
        }
        return this.motionValues;
    }

    public void scaleSize(float f) {
        if (f == 1.0f) {
            return;
        }
        scaleSize(f, f);
    }

    public void scaleSize(float f, float f2) {
        if (f == 1.0f && f2 == 1.0f) {
            return;
        }
        for (RangedNumericValue rangedNumericValue : getXSizeValues()) {
            rangedNumericValue.scale(f);
        }
        for (RangedNumericValue rangedNumericValue2 : getYSizeValues()) {
            rangedNumericValue2.scale(f2);
        }
    }

    public void scaleMotion(float f) {
        if (f == 1.0f) {
            return;
        }
        for (RangedNumericValue rangedNumericValue : getMotionValues()) {
            rangedNumericValue.scale(f);
        }
    }

    public void matchSize(ParticleEmitter particleEmitter) {
        matchXSize(particleEmitter);
        matchYSize(particleEmitter);
    }

    public void matchXSize(ParticleEmitter particleEmitter) {
        RangedNumericValue[] xSizeValues = getXSizeValues();
        RangedNumericValue[] xSizeValues2 = particleEmitter.getXSizeValues();
        for (int i = 0; i < xSizeValues.length; i++) {
            xSizeValues[i].set(xSizeValues2[i]);
        }
    }

    public void matchYSize(ParticleEmitter particleEmitter) {
        RangedNumericValue[] ySizeValues = getYSizeValues();
        RangedNumericValue[] ySizeValues2 = particleEmitter.getYSizeValues();
        for (int i = 0; i < ySizeValues.length; i++) {
            ySizeValues[i].set(ySizeValues2[i]);
        }
    }

    public void matchMotion(ParticleEmitter particleEmitter) {
        RangedNumericValue[] motionValues = getMotionValues();
        RangedNumericValue[] motionValues2 = particleEmitter.getMotionValues();
        for (int i = 0; i < motionValues.length; i++) {
            motionValues[i].set(motionValues2[i]);
        }
    }

    public void save(Writer writer) {
        writer.write(this.name + SequenceUtils.EOL);
        writer.write("- Delay -\n");
        this.delayValue.save(writer);
        writer.write("- Duration - \n");
        this.durationValue.save(writer);
        writer.write("- Count - \n");
        writer.write("min: " + this.minParticleCount + SequenceUtils.EOL);
        writer.write("max: " + this.maxParticleCount + SequenceUtils.EOL);
        writer.write("- Emission - \n");
        this.emissionValue.save(writer);
        writer.write("- Life - \n");
        this.lifeValue.save(writer);
        writer.write("- Life Offset - \n");
        this.lifeOffsetValue.save(writer);
        writer.write("- X Offset - \n");
        this.xOffsetValue.save(writer);
        writer.write("- Y Offset - \n");
        this.yOffsetValue.save(writer);
        writer.write("- Spawn Shape - \n");
        this.spawnShapeValue.save(writer);
        writer.write("- Spawn Width - \n");
        this.spawnWidthValue.save(writer);
        writer.write("- Spawn Height - \n");
        this.spawnHeightValue.save(writer);
        writer.write("- X Scale - \n");
        this.xScaleValue.save(writer);
        writer.write("- Y Scale - \n");
        this.yScaleValue.save(writer);
        writer.write("- Velocity - \n");
        this.velocityValue.save(writer);
        writer.write("- Angle - \n");
        this.angleValue.save(writer);
        writer.write("- Rotation - \n");
        this.rotationValue.save(writer);
        writer.write("- Wind - \n");
        this.windValue.save(writer);
        writer.write("- Gravity - \n");
        this.gravityValue.save(writer);
        writer.write("- Tint - \n");
        this.tintValue.save(writer);
        writer.write("- Transparency - \n");
        this.transparencyValue.save(writer);
        writer.write("- Options - \n");
        writer.write("attached: " + this.attached + SequenceUtils.EOL);
        writer.write("continuous: " + this.continuous + SequenceUtils.EOL);
        writer.write("aligned: " + this.aligned + SequenceUtils.EOL);
        writer.write("additive: " + this.additive + SequenceUtils.EOL);
        writer.write("behind: " + this.behind + SequenceUtils.EOL);
        writer.write("premultipliedAlpha: " + this.premultipliedAlpha + SequenceUtils.EOL);
        writer.write("spriteMode: " + this.spriteMode.toString() + SequenceUtils.EOL);
        writer.write("- Image Paths -\n");
        Array.ArrayIterator<String> it = this.imagePaths.iterator();
        while (it.hasNext()) {
            writer.write(it.next() + SequenceUtils.EOL);
        }
        writer.write(SequenceUtils.EOL);
    }

    public void load(BufferedReader bufferedReader) {
        try {
            this.name = readString(bufferedReader, Attribute.NAME_ATTR);
            bufferedReader.readLine();
            this.delayValue.load(bufferedReader);
            bufferedReader.readLine();
            this.durationValue.load(bufferedReader);
            bufferedReader.readLine();
            setMinParticleCount(readInt(bufferedReader, "minParticleCount"));
            setMaxParticleCount(readInt(bufferedReader, "maxParticleCount"));
            bufferedReader.readLine();
            this.emissionValue.load(bufferedReader);
            bufferedReader.readLine();
            this.lifeValue.load(bufferedReader);
            bufferedReader.readLine();
            this.lifeOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.xOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.yOffsetValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnShapeValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnWidthValue.load(bufferedReader);
            bufferedReader.readLine();
            this.spawnHeightValue.load(bufferedReader);
            if (bufferedReader.readLine().trim().equals("- Scale -")) {
                this.xScaleValue.load(bufferedReader);
                this.yScaleValue.setActive(false);
            } else {
                this.xScaleValue.load(bufferedReader);
                bufferedReader.readLine();
                this.yScaleValue.load(bufferedReader);
            }
            bufferedReader.readLine();
            this.velocityValue.load(bufferedReader);
            bufferedReader.readLine();
            this.angleValue.load(bufferedReader);
            bufferedReader.readLine();
            this.rotationValue.load(bufferedReader);
            bufferedReader.readLine();
            this.windValue.load(bufferedReader);
            bufferedReader.readLine();
            this.gravityValue.load(bufferedReader);
            bufferedReader.readLine();
            this.tintValue.load(bufferedReader);
            bufferedReader.readLine();
            this.transparencyValue.load(bufferedReader);
            bufferedReader.readLine();
            this.attached = readBoolean(bufferedReader, "attached");
            this.continuous = readBoolean(bufferedReader, "continuous");
            this.aligned = readBoolean(bufferedReader, "aligned");
            this.additive = readBoolean(bufferedReader, "additive");
            this.behind = readBoolean(bufferedReader, "behind");
            String readLine = bufferedReader.readLine();
            String str = readLine;
            if (readLine.startsWith("premultipliedAlpha")) {
                this.premultipliedAlpha = readBoolean(str);
                str = bufferedReader.readLine();
            }
            if (str.startsWith("spriteMode")) {
                this.spriteMode = SpriteMode.valueOf(readString(str));
                bufferedReader.readLine();
            }
            Array<String> array = new Array<>();
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null || readLine2.isEmpty()) {
                    break;
                } else {
                    array.add(readLine2);
                }
            }
            setImagePaths(array);
        } catch (RuntimeException e) {
            if (this.name != null) {
                throw new RuntimeException("Error parsing emitter: " + this.name, e);
            }
            throw e;
        }
    }

    static String readString(String str) {
        return str.substring(str.indexOf(":") + 1).trim();
    }

    static String readString(BufferedReader bufferedReader, String str) {
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            throw new IOException("Missing value: " + str);
        }
        return readString(readLine);
    }

    static boolean readBoolean(String str) {
        return Boolean.parseBoolean(readString(str));
    }

    static boolean readBoolean(BufferedReader bufferedReader, String str) {
        return Boolean.parseBoolean(readString(bufferedReader, str));
    }

    static int readInt(BufferedReader bufferedReader, String str) {
        return Integer.parseInt(readString(bufferedReader, str));
    }

    static float readFloat(BufferedReader bufferedReader, String str) {
        return Float.parseFloat(readString(bufferedReader, str));
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$Particle.class */
    public static class Particle extends Sprite {
        protected int life;
        protected int currentLife;
        protected float xScale;
        protected float xScaleDiff;
        protected float yScale;
        protected float yScaleDiff;
        protected float rotation;
        protected float rotationDiff;
        protected float velocity;
        protected float velocityDiff;
        protected float angle;
        protected float angleDiff;
        protected float angleCos;
        protected float angleSin;
        protected float transparency;
        protected float transparencyDiff;
        protected float wind;
        protected float windDiff;
        protected float gravity;
        protected float gravityDiff;
        protected float[] tint;
        protected int frame;

        public Particle(Sprite sprite) {
            super(sprite);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$ParticleValue.class */
    public static class ParticleValue {
        boolean active;
        boolean alwaysActive;

        public void setAlwaysActive(boolean z) {
            this.alwaysActive = z;
        }

        public boolean isAlwaysActive() {
            return this.alwaysActive;
        }

        public boolean isActive() {
            return this.alwaysActive || this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void save(Writer writer) {
            if (!this.alwaysActive) {
                writer.write("active: " + this.active + SequenceUtils.EOL);
            } else {
                this.active = true;
            }
        }

        public void load(BufferedReader bufferedReader) {
            if (!this.alwaysActive) {
                this.active = ParticleEmitter.readBoolean(bufferedReader, "active");
            } else {
                this.active = true;
            }
        }

        public void load(ParticleValue particleValue) {
            this.active = particleValue.active;
            this.alwaysActive = particleValue.alwaysActive;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$NumericValue.class */
    public static class NumericValue extends ParticleValue {
        private float value;

        public float getValue() {
            return this.value;
        }

        public void setValue(float f) {
            this.value = f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            if (this.active) {
                writer.write("value: " + this.value + SequenceUtils.EOL);
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.value = ParticleEmitter.readFloat(bufferedReader, "value");
            }
        }

        public void load(NumericValue numericValue) {
            super.load((ParticleValue) numericValue);
            this.value = numericValue.value;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$RangedNumericValue.class */
    public static class RangedNumericValue extends ParticleValue {
        private float lowMin;
        private float lowMax;

        public float newLowValue() {
            return this.lowMin + ((this.lowMax - this.lowMin) * MathUtils.random());
        }

        public void setLow(float f) {
            this.lowMin = f;
            this.lowMax = f;
        }

        public void setLow(float f, float f2) {
            this.lowMin = f;
            this.lowMax = f2;
        }

        public float getLowMin() {
            return this.lowMin;
        }

        public void setLowMin(float f) {
            this.lowMin = f;
        }

        public float getLowMax() {
            return this.lowMax;
        }

        public void setLowMax(float f) {
            this.lowMax = f;
        }

        public void scale(float f) {
            this.lowMin *= f;
            this.lowMax *= f;
        }

        public void set(RangedNumericValue rangedNumericValue) {
            this.lowMin = rangedNumericValue.lowMin;
            this.lowMax = rangedNumericValue.lowMax;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            if (this.active) {
                writer.write("lowMin: " + this.lowMin + SequenceUtils.EOL);
                writer.write("lowMax: " + this.lowMax + SequenceUtils.EOL);
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.lowMin = ParticleEmitter.readFloat(bufferedReader, "lowMin");
                this.lowMax = ParticleEmitter.readFloat(bufferedReader, "lowMax");
            }
        }

        public void load(RangedNumericValue rangedNumericValue) {
            super.load((ParticleValue) rangedNumericValue);
            this.lowMax = rangedNumericValue.lowMax;
            this.lowMin = rangedNumericValue.lowMin;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$ScaledNumericValue.class */
    public static class ScaledNumericValue extends RangedNumericValue {
        private float[] scaling = {1.0f};
        float[] timeline = {0.0f};
        private float highMin;
        private float highMax;
        boolean relative;

        public float newHighValue() {
            return this.highMin + ((this.highMax - this.highMin) * MathUtils.random());
        }

        public void setHigh(float f) {
            this.highMin = f;
            this.highMax = f;
        }

        public void setHigh(float f, float f2) {
            this.highMin = f;
            this.highMax = f2;
        }

        public float getHighMin() {
            return this.highMin;
        }

        public void setHighMin(float f) {
            this.highMin = f;
        }

        public float getHighMax() {
            return this.highMax;
        }

        public void setHighMax(float f) {
            this.highMax = f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void scale(float f) {
            super.scale(f);
            this.highMin *= f;
            this.highMax *= f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void set(RangedNumericValue rangedNumericValue) {
            if (rangedNumericValue instanceof ScaledNumericValue) {
                set((ScaledNumericValue) rangedNumericValue);
            } else {
                super.set(rangedNumericValue);
            }
        }

        public void set(ScaledNumericValue scaledNumericValue) {
            super.set((RangedNumericValue) scaledNumericValue);
            this.highMin = scaledNumericValue.highMin;
            this.highMax = scaledNumericValue.highMax;
            if (this.scaling.length != scaledNumericValue.scaling.length) {
                this.scaling = Arrays.copyOf(scaledNumericValue.scaling, scaledNumericValue.scaling.length);
            } else {
                System.arraycopy(scaledNumericValue.scaling, 0, this.scaling, 0, this.scaling.length);
            }
            if (this.timeline.length != scaledNumericValue.timeline.length) {
                this.timeline = Arrays.copyOf(scaledNumericValue.timeline, scaledNumericValue.timeline.length);
            } else {
                System.arraycopy(scaledNumericValue.timeline, 0, this.timeline, 0, this.timeline.length);
            }
            this.relative = scaledNumericValue.relative;
        }

        public float[] getScaling() {
            return this.scaling;
        }

        public void setScaling(float[] fArr) {
            this.scaling = fArr;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        public void setTimeline(float[] fArr) {
            this.timeline = fArr;
        }

        public boolean isRelative() {
            return this.relative;
        }

        public void setRelative(boolean z) {
            this.relative = z;
        }

        public float getScale(float f) {
            int i = -1;
            float[] fArr = this.timeline;
            int length = fArr.length;
            int i2 = 1;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (fArr[i2] <= f) {
                    i2++;
                } else {
                    i = i2;
                    break;
                }
            }
            if (i == -1) {
                return this.scaling[length - 1];
            }
            float[] fArr2 = this.scaling;
            int i3 = i - 1;
            float f2 = fArr2[i3];
            float f3 = fArr[i3];
            return f2 + ((fArr2[i] - f2) * ((f - f3) / (fArr[i] - f3)));
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            if (this.active) {
                writer.write("highMin: " + this.highMin + SequenceUtils.EOL);
                writer.write("highMax: " + this.highMax + SequenceUtils.EOL);
                writer.write("relative: " + this.relative + SequenceUtils.EOL);
                writer.write("scalingCount: " + this.scaling.length + SequenceUtils.EOL);
                for (int i = 0; i < this.scaling.length; i++) {
                    writer.write("scaling" + i + ": " + this.scaling[i] + SequenceUtils.EOL);
                }
                writer.write("timelineCount: " + this.timeline.length + SequenceUtils.EOL);
                for (int i2 = 0; i2 < this.timeline.length; i2++) {
                    writer.write("timeline" + i2 + ": " + this.timeline[i2] + SequenceUtils.EOL);
                }
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.highMin = ParticleEmitter.readFloat(bufferedReader, "highMin");
                this.highMax = ParticleEmitter.readFloat(bufferedReader, "highMax");
                this.relative = ParticleEmitter.readBoolean(bufferedReader, "relative");
                this.scaling = new float[ParticleEmitter.readInt(bufferedReader, "scalingCount")];
                for (int i = 0; i < this.scaling.length; i++) {
                    this.scaling[i] = ParticleEmitter.readFloat(bufferedReader, "scaling" + i);
                }
                this.timeline = new float[ParticleEmitter.readInt(bufferedReader, "timelineCount")];
                for (int i2 = 0; i2 < this.timeline.length; i2++) {
                    this.timeline[i2] = ParticleEmitter.readFloat(bufferedReader, "timeline" + i2);
                }
            }
        }

        public void load(ScaledNumericValue scaledNumericValue) {
            super.load((RangedNumericValue) scaledNumericValue);
            this.highMax = scaledNumericValue.highMax;
            this.highMin = scaledNumericValue.highMin;
            this.scaling = new float[scaledNumericValue.scaling.length];
            System.arraycopy(scaledNumericValue.scaling, 0, this.scaling, 0, this.scaling.length);
            this.timeline = new float[scaledNumericValue.timeline.length];
            System.arraycopy(scaledNumericValue.timeline, 0, this.timeline, 0, this.timeline.length);
            this.relative = scaledNumericValue.relative;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$IndependentScaledNumericValue.class */
    public static class IndependentScaledNumericValue extends ScaledNumericValue {
        boolean independent;

        public boolean isIndependent() {
            return this.independent;
        }

        public void setIndependent(boolean z) {
            this.independent = z;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue
        public void set(RangedNumericValue rangedNumericValue) {
            if (rangedNumericValue instanceof IndependentScaledNumericValue) {
                set((IndependentScaledNumericValue) rangedNumericValue);
            } else {
                super.set(rangedNumericValue);
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue
        public void set(ScaledNumericValue scaledNumericValue) {
            if (scaledNumericValue instanceof IndependentScaledNumericValue) {
                set((IndependentScaledNumericValue) scaledNumericValue);
            } else {
                super.set(scaledNumericValue);
            }
        }

        public void set(IndependentScaledNumericValue independentScaledNumericValue) {
            super.set((ScaledNumericValue) independentScaledNumericValue);
            this.independent = independentScaledNumericValue.independent;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            writer.write("independent: " + this.independent + SequenceUtils.EOL);
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.RangedNumericValue, com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (bufferedReader.markSupported()) {
                bufferedReader.mark(100);
            }
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                throw new IOException("Missing value: independent");
            }
            if (readLine.contains("independent")) {
                this.independent = Boolean.parseBoolean(ParticleEmitter.readString(readLine));
            } else if (bufferedReader.markSupported()) {
                bufferedReader.reset();
            } else {
                Gdx.app.error("ParticleEmitter", "The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
                throw new IOException("The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
            }
        }

        public void load(IndependentScaledNumericValue independentScaledNumericValue) {
            super.load((ScaledNumericValue) independentScaledNumericValue);
            this.independent = independentScaledNumericValue.independent;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$GradientColorValue.class */
    public static class GradientColorValue extends ParticleValue {
        private static float[] temp = new float[4];
        private float[] colors = {1.0f, 1.0f, 1.0f};
        float[] timeline = {0.0f};

        public GradientColorValue() {
            this.alwaysActive = true;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        public void setTimeline(float[] fArr) {
            this.timeline = fArr;
        }

        public float[] getColors() {
            return this.colors;
        }

        public void setColors(float[] fArr) {
            this.colors = fArr;
        }

        public float[] getColor(float f) {
            int i = 0;
            int i2 = -1;
            float[] fArr = this.timeline;
            int length = fArr.length;
            int i3 = 1;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (fArr[i3] > f) {
                    i2 = i3;
                    break;
                }
                i = i3;
                i3++;
            }
            float f2 = fArr[i];
            int i4 = i * 3;
            float f3 = this.colors[i4];
            float f4 = this.colors[i4 + 1];
            float f5 = this.colors[i4 + 2];
            if (i2 != -1) {
                float f6 = (f - f2) / (fArr[i2] - f2);
                int i5 = i2 * 3;
                temp[0] = f3 + ((this.colors[i5] - f3) * f6);
                temp[1] = f4 + ((this.colors[i5 + 1] - f4) * f6);
                temp[2] = f5 + ((this.colors[i5 + 2] - f5) * f6);
                return temp;
            }
            temp[0] = f3;
            temp[1] = f4;
            temp[2] = f5;
            return temp;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            if (this.active) {
                writer.write("colorsCount: " + this.colors.length + SequenceUtils.EOL);
                for (int i = 0; i < this.colors.length; i++) {
                    writer.write("colors" + i + ": " + this.colors[i] + SequenceUtils.EOL);
                }
                writer.write("timelineCount: " + this.timeline.length + SequenceUtils.EOL);
                for (int i2 = 0; i2 < this.timeline.length; i2++) {
                    writer.write("timeline" + i2 + ": " + this.timeline[i2] + SequenceUtils.EOL);
                }
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.colors = new float[ParticleEmitter.readInt(bufferedReader, "colorsCount")];
                for (int i = 0; i < this.colors.length; i++) {
                    this.colors[i] = ParticleEmitter.readFloat(bufferedReader, "colors" + i);
                }
                this.timeline = new float[ParticleEmitter.readInt(bufferedReader, "timelineCount")];
                for (int i2 = 0; i2 < this.timeline.length; i2++) {
                    this.timeline[i2] = ParticleEmitter.readFloat(bufferedReader, "timeline" + i2);
                }
            }
        }

        public void load(GradientColorValue gradientColorValue) {
            super.load((ParticleValue) gradientColorValue);
            this.colors = new float[gradientColorValue.colors.length];
            System.arraycopy(gradientColorValue.colors, 0, this.colors, 0, this.colors.length);
            this.timeline = new float[gradientColorValue.timeline.length];
            System.arraycopy(gradientColorValue.timeline, 0, this.timeline, 0, this.timeline.length);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEmitter$SpawnShapeValue.class */
    public static class SpawnShapeValue extends ParticleValue {
        boolean edges;
        SpawnShape shape = SpawnShape.point;
        SpawnEllipseSide side = SpawnEllipseSide.both;

        public SpawnShape getShape() {
            return this.shape;
        }

        public void setShape(SpawnShape spawnShape) {
            this.shape = spawnShape;
        }

        public boolean isEdges() {
            return this.edges;
        }

        public void setEdges(boolean z) {
            this.edges = z;
        }

        public SpawnEllipseSide getSide() {
            return this.side;
        }

        public void setSide(SpawnEllipseSide spawnEllipseSide) {
            this.side = spawnEllipseSide;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void save(Writer writer) {
            super.save(writer);
            if (this.active) {
                writer.write("shape: " + this.shape + SequenceUtils.EOL);
                if (this.shape == SpawnShape.ellipse) {
                    writer.write("edges: " + this.edges + SequenceUtils.EOL);
                    writer.write("side: " + this.side + SequenceUtils.EOL);
                }
            }
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEmitter.ParticleValue
        public void load(BufferedReader bufferedReader) {
            super.load(bufferedReader);
            if (this.active) {
                this.shape = SpawnShape.valueOf(ParticleEmitter.readString(bufferedReader, "shape"));
                if (this.shape == SpawnShape.ellipse) {
                    this.edges = ParticleEmitter.readBoolean(bufferedReader, "edges");
                    this.side = SpawnEllipseSide.valueOf(ParticleEmitter.readString(bufferedReader, "side"));
                }
            }
        }

        public void load(SpawnShapeValue spawnShapeValue) {
            super.load((ParticleValue) spawnShapeValue);
            this.shape = spawnShapeValue.shape;
            this.edges = spawnShapeValue.edges;
            this.side = spawnShapeValue.side;
        }
    }
}

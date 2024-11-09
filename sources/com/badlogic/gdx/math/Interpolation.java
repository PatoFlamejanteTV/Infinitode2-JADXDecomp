package com.badlogic.gdx.math;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation.class */
public abstract class Interpolation {
    public static final Interpolation linear = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.1
        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f;
        }
    };
    public static final Interpolation smooth = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.2
        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f * f * (3.0f - (f * 2.0f));
        }
    };
    public static final Interpolation smooth2 = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.3
        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            float f2 = f * f * (3.0f - (f * 2.0f));
            return f2 * f2 * (3.0f - (f2 * 2.0f));
        }
    };
    public static final Interpolation smoother;
    public static final Interpolation fade;
    public static final Pow pow2;
    public static final PowIn pow2In;
    public static final PowIn slowFast;
    public static final PowOut pow2Out;
    public static final PowOut fastSlow;
    public static final Interpolation pow2InInverse;
    public static final Interpolation pow2OutInverse;
    public static final Pow pow3;
    public static final PowIn pow3In;
    public static final PowOut pow3Out;
    public static final Interpolation pow3InInverse;
    public static final Interpolation pow3OutInverse;
    public static final Pow pow4;
    public static final PowIn pow4In;
    public static final PowOut pow4Out;
    public static final Pow pow5;
    public static final PowIn pow5In;
    public static final PowOut pow5Out;
    public static final Interpolation sine;
    public static final Interpolation sineIn;
    public static final Interpolation sineOut;
    public static final Exp exp10;
    public static final ExpIn exp10In;
    public static final ExpOut exp10Out;
    public static final Exp exp5;
    public static final ExpIn exp5In;
    public static final ExpOut exp5Out;
    public static final Interpolation circle;
    public static final Interpolation circleIn;
    public static final Interpolation circleOut;
    public static final Elastic elastic;
    public static final ElasticIn elasticIn;
    public static final ElasticOut elasticOut;
    public static final Swing swing;
    public static final SwingIn swingIn;
    public static final SwingOut swingOut;
    public static final Bounce bounce;
    public static final BounceIn bounceIn;
    public static final BounceOut bounceOut;

    public abstract float apply(float f);

    public float apply(float f, float f2, float f3) {
        return f + ((f2 - f) * apply(f3));
    }

    static {
        Interpolation interpolation = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.4
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return f * f * f * ((f * ((f * 6.0f) - 15.0f)) + 10.0f);
            }
        };
        smoother = interpolation;
        fade = interpolation;
        pow2 = new Pow(2);
        PowIn powIn = new PowIn(2);
        pow2In = powIn;
        slowFast = powIn;
        PowOut powOut = new PowOut(2);
        pow2Out = powOut;
        fastSlow = powOut;
        pow2InInverse = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.5
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                if (f < 1.0E-6f) {
                    return 0.0f;
                }
                return (float) Math.sqrt(f);
            }
        };
        pow2OutInverse = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.6
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                if (f < 1.0E-6f) {
                    return 0.0f;
                }
                if (f > 1.0f) {
                    return 1.0f;
                }
                return 1.0f - ((float) Math.sqrt(-(f - 1.0f)));
            }
        };
        pow3 = new Pow(3);
        pow3In = new PowIn(3);
        pow3Out = new PowOut(3);
        pow3InInverse = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.7
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return (float) Math.cbrt(f);
            }
        };
        pow3OutInverse = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.8
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - ((float) Math.cbrt(-(f - 1.0f)));
            }
        };
        pow4 = new Pow(4);
        pow4In = new PowIn(4);
        pow4Out = new PowOut(4);
        pow5 = new Pow(5);
        pow5In = new PowIn(5);
        pow5Out = new PowOut(5);
        sine = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.9
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return (1.0f - MathUtils.cos(f * 3.1415927f)) / 2.0f;
            }
        };
        sineIn = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.10
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - MathUtils.cos(f * 1.5707964f);
            }
        };
        sineOut = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.11
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return MathUtils.sin(f * 1.5707964f);
            }
        };
        exp10 = new Exp(2.0f, 10.0f);
        exp10In = new ExpIn(2.0f, 10.0f);
        exp10Out = new ExpOut(2.0f, 10.0f);
        exp5 = new Exp(2.0f, 5.0f);
        exp5In = new ExpIn(2.0f, 5.0f);
        exp5Out = new ExpOut(2.0f, 5.0f);
        circle = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.12
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                if (f <= 0.5f) {
                    float f2 = f * 2.0f;
                    return (1.0f - ((float) Math.sqrt(1.0f - (f2 * f2)))) / 2.0f;
                }
                float f3 = (f - 1.0f) * 2.0f;
                return (((float) Math.sqrt(1.0f - (f3 * f3))) + 1.0f) / 2.0f;
            }
        };
        circleIn = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.13
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - ((float) Math.sqrt(1.0f - (f * f)));
            }
        };
        circleOut = new Interpolation() { // from class: com.badlogic.gdx.math.Interpolation.14
            @Override // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                float f2 = f - 1.0f;
                return (float) Math.sqrt(1.0f - (f2 * f2));
            }
        };
        elastic = new Elastic(2.0f, 10.0f, 7, 1.0f);
        elasticIn = new ElasticIn(2.0f, 10.0f, 6, 1.0f);
        elasticOut = new ElasticOut(2.0f, 10.0f, 7, 1.0f);
        swing = new Swing(1.5f);
        swingIn = new SwingIn(2.0f);
        swingOut = new SwingOut(2.0f);
        bounce = new Bounce(4);
        bounceIn = new BounceIn(4);
        bounceOut = new BounceOut(4);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$Pow.class */
    public static class Pow extends Interpolation {
        final int power;

        public Pow(int i) {
            this.power = i;
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f <= 0.5f) {
                return ((float) Math.pow(f * 2.0f, this.power)) / 2.0f;
            }
            return (((float) Math.pow((f - 1.0f) * 2.0f, this.power)) / (this.power % 2 == 0 ? -2 : 2)) + 1.0f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$PowIn.class */
    public static class PowIn extends Pow {
        public PowIn(int i) {
            super(i);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Pow, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return (float) Math.pow(f, this.power);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$PowOut.class */
    public static class PowOut extends Pow {
        public PowOut(int i) {
            super(i);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Pow, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return (((float) Math.pow(f - 1.0f, this.power)) * (this.power % 2 == 0 ? -1 : 1)) + 1.0f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$Exp.class */
    public static class Exp extends Interpolation {
        final float value;
        final float power;
        final float min;
        final float scale;

        public Exp(float f, float f2) {
            this.value = f;
            this.power = f2;
            this.min = (float) Math.pow(f, -f2);
            this.scale = 1.0f / (1.0f - this.min);
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f <= 0.5f ? ((((float) Math.pow(this.value, this.power * ((f * 2.0f) - 1.0f))) - this.min) * this.scale) / 2.0f : (2.0f - ((((float) Math.pow(this.value, (-this.power) * ((f * 2.0f) - 1.0f))) - this.min) * this.scale)) / 2.0f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$ExpIn.class */
    public static class ExpIn extends Exp {
        public ExpIn(float f, float f2) {
            super(f, f2);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Exp, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return (((float) Math.pow(this.value, this.power * (f - 1.0f))) - this.min) * this.scale;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$ExpOut.class */
    public static class ExpOut extends Exp {
        public ExpOut(float f, float f2) {
            super(f, f2);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Exp, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return 1.0f - ((((float) Math.pow(this.value, (-this.power) * f)) - this.min) * this.scale);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$Elastic.class */
    public static class Elastic extends Interpolation {
        final float value;
        final float power;
        final float scale;
        final float bounces;

        public Elastic(float f, float f2, int i, float f3) {
            this.value = f;
            this.power = f2;
            this.scale = f3;
            this.bounces = i * 3.1415927f * (i % 2 == 0 ? 1 : -1);
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f <= 0.5f) {
                return ((((float) Math.pow(this.value, this.power * (r0 - 1.0f))) * MathUtils.sin((f * 2.0f) * this.bounces)) * this.scale) / 2.0f;
            }
            return 1.0f - (((((float) Math.pow(this.value, this.power * (r0 - 1.0f))) * MathUtils.sin(((1.0f - f) * 2.0f) * this.bounces)) * this.scale) / 2.0f);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$ElasticIn.class */
    public static class ElasticIn extends Elastic {
        public ElasticIn(float f, float f2, int i, float f3) {
            super(f, f2, i, f3);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Elastic, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f >= 0.99d) {
                return 1.0f;
            }
            return ((float) Math.pow(this.value, this.power * (f - 1.0f))) * MathUtils.sin(f * this.bounces) * this.scale;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$ElasticOut.class */
    public static class ElasticOut extends Elastic {
        public ElasticOut(float f, float f2, int i, float f3) {
            super(f, f2, i, f3);
        }

        @Override // com.badlogic.gdx.math.Interpolation.Elastic, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f == 0.0f) {
                return 0.0f;
            }
            return 1.0f - ((((float) Math.pow(this.value, this.power * (r0 - 1.0f))) * MathUtils.sin((1.0f - f) * this.bounces)) * this.scale);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$Bounce.class */
    public static class Bounce extends BounceOut {
        public Bounce(float[] fArr, float[] fArr2) {
            super(fArr, fArr2);
        }

        public Bounce(int i) {
            super(i);
        }

        private float out(float f) {
            float f2 = f + (this.widths[0] / 2.0f);
            if (f2 < this.widths[0]) {
                return (f2 / (this.widths[0] / 2.0f)) - 1.0f;
            }
            return super.apply(f);
        }

        @Override // com.badlogic.gdx.math.Interpolation.BounceOut, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f <= 0.5f ? (1.0f - out(1.0f - (f * 2.0f))) / 2.0f : (out((f * 2.0f) - 1.0f) / 2.0f) + 0.5f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$BounceOut.class */
    public static class BounceOut extends Interpolation {
        final float[] widths;
        final float[] heights;

        public BounceOut(float[] fArr, float[] fArr2) {
            if (fArr.length != fArr2.length) {
                throw new IllegalArgumentException("Must be the same number of widths and heights.");
            }
            this.widths = fArr;
            this.heights = fArr2;
        }

        public BounceOut(int i) {
            if (i < 2 || i > 5) {
                throw new IllegalArgumentException("bounces cannot be < 2 or > 5: " + i);
            }
            this.widths = new float[i];
            this.heights = new float[i];
            this.heights[0] = 1.0f;
            switch (i) {
                case 2:
                    this.widths[0] = 0.6f;
                    this.widths[1] = 0.4f;
                    this.heights[1] = 0.33f;
                    break;
                case 3:
                    this.widths[0] = 0.4f;
                    this.widths[1] = 0.4f;
                    this.widths[2] = 0.2f;
                    this.heights[1] = 0.33f;
                    this.heights[2] = 0.1f;
                    break;
                case 4:
                    this.widths[0] = 0.34f;
                    this.widths[1] = 0.34f;
                    this.widths[2] = 0.2f;
                    this.widths[3] = 0.15f;
                    this.heights[1] = 0.26f;
                    this.heights[2] = 0.11f;
                    this.heights[3] = 0.03f;
                    break;
                case 5:
                    this.widths[0] = 0.3f;
                    this.widths[1] = 0.3f;
                    this.widths[2] = 0.2f;
                    this.widths[3] = 0.1f;
                    this.widths[4] = 0.1f;
                    this.heights[1] = 0.45f;
                    this.heights[2] = 0.3f;
                    this.heights[3] = 0.15f;
                    this.heights[4] = 0.06f;
                    break;
            }
            float[] fArr = this.widths;
            fArr[0] = fArr[0] * 2.0f;
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f == 1.0f) {
                return 1.0f;
            }
            float f2 = f + (this.widths[0] / 2.0f);
            float f3 = 0.0f;
            float f4 = 0.0f;
            int i = 0;
            int length = this.widths.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                f3 = this.widths[i];
                if (f2 <= f3) {
                    f4 = this.heights[i];
                    break;
                }
                f2 -= f3;
                i++;
            }
            float f5 = f2 / f3;
            float f6 = (4.0f / f3) * f4 * f5;
            return 1.0f - ((f6 - (f6 * f5)) * f3);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$BounceIn.class */
    public static class BounceIn extends BounceOut {
        public BounceIn(float[] fArr, float[] fArr2) {
            super(fArr, fArr2);
        }

        public BounceIn(int i) {
            super(i);
        }

        @Override // com.badlogic.gdx.math.Interpolation.BounceOut, com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return 1.0f - super.apply(1.0f - f);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$Swing.class */
    public static class Swing extends Interpolation {
        private final float scale;

        public Swing(float f) {
            this.scale = f * 2.0f;
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if (f <= 0.5f) {
                float f2 = f * 2.0f;
                return ((f2 * f2) * (((this.scale + 1.0f) * f2) - this.scale)) / 2.0f;
            }
            float f3 = (f - 1.0f) * 2.0f;
            return (((f3 * f3) * (((this.scale + 1.0f) * f3) + this.scale)) / 2.0f) + 1.0f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$SwingOut.class */
    public static class SwingOut extends Interpolation {
        private final float scale;

        public SwingOut(float f) {
            this.scale = f;
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * (((this.scale + 1.0f) * f2) + this.scale)) + 1.0f;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Interpolation$SwingIn.class */
    public static class SwingIn extends Interpolation {
        private final float scale;

        public SwingIn(float f) {
            this.scale = f;
        }

        @Override // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f * f * (((this.scale + 1.0f) * f) - this.scale);
        }
    }
}

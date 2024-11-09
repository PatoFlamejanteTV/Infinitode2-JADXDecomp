package com.prineside.tdi2.configs;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/configs/ShopOfferValues.class */
public final class ShopOfferValues {
    public static final PlayStageFunction REGULAR_BLUEPRINT_VALUE = new PlayStageFunction(0.004166667f, 0.0038461538f, 0.0035714286f, 0.0033333334f, 0.003125f, 0.0029411765f, 0.0027777778f, 0.002631579f, 0.0025f, 0.0023809525f, 0.0022727272f);
    public static final PlayStageFunction REGULAR_BLUEPRINT_QUANTITY = new PlayStageFunction(5.0f, 8.0f, 13.0f, 20.0f, 25.0f, 35.0f, 50.0f, 70.0f, 100.0f, 140.0f, 200.0f);
    public static final PlayStageFunction SPECIAL_BLUEPRINT_VALUE = new PlayStageFunction(0.05f, 0.04761905f, 0.045454547f, 0.04347826f, 0.041666668f, 0.04f, 0.03846154f, 0.037037037f, 0.035714287f, 0.03448276f, 0.033333335f);
    public static final PlayStageFunction SPECIAL_BLUEPRINT_QUANTITY = new PlayStageFunction(1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f);
    public static final PlayStageFunction ACCELERATOR_VALUE = new PlayStageFunction(0.05f, 0.051282052f, 0.05263158f, 0.054054055f, 0.055555556f, 0.057142857f, 0.05882353f, 0.060606062f, 0.0625f, 0.06451613f, 0.06666667f);
    public static final PlayStageFunction GREEN_PAPERS_VALUE = new PlayStageFunction(1.4285714E-5f, 1.0526316E-5f, 4.5454544E-6f, 3.174603E-6f, 2.0E-6f, 1.1111111E-6f, 6.6666666E-7f, 3.3333333E-7f, 1.6666667E-7f, 8.333333E-8f, 5.0E-8f);
    public static final PlayStageFunction GREEN_PAPERS_QUANTITY = new PlayStageFunction(20000.0f, 30000.0f, 50000.0f, 80000.0f, 150000.0f, 250000.0f, 400000.0f, 600000.0f, 800000.0f, 1000000.0f, 1500000.0f);
    public static final PlayStageFunction RESOURCE_VALUE = new PlayStageFunction(3.3333333E-4f, 2.8571428E-4f, 2.5E-4f, 2.2222222E-4f, 1.8181818E-4f, 1.3333333E-4f, 1.0E-4f, 7.6923076E-5f, 5.882353E-5f, 4.5454544E-5f, 3.3333334E-5f);
    public static final PlayStageFunction BIT_DUST_VALUE = new PlayStageFunction(0.04f, 0.033333335f, 0.028571429f, 0.025f, 0.022222223f, 0.02f, 0.018181818f, 0.016666668f, 0.015384615f, 0.014285714f, 0.013333334f);
    public static final PlayStageFunction BIT_DUST_QUANTITY = new PlayStageFunction(1.0f, 3.0f, 10.0f, 20.0f, 40.0f, 80.0f, 130.0f, 200.0f, 300.0f, 600.0f, 1000.0f);
    public static final PlayStageFunction CASE_VALUE = new PlayStageFunction(1.0f, 0.95f, 0.9f, 0.85f, 0.8f, 0.75f, 0.7f, 0.65f, 0.6f, 0.55f, 0.5f);
    public static final PlayStageFunction CASE_QUANTITY = new PlayStageFunction(0.5f, 0.7f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.2f, 5.0f, 7.0f);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/configs/ShopOfferValues$PlayStageFunction.class */
    public static final class PlayStageFunction {

        /* renamed from: a, reason: collision with root package name */
        private final float[] f1830a = new float[11];

        public PlayStageFunction(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
            this.f1830a[0] = f;
            this.f1830a[1] = f2;
            this.f1830a[2] = f3;
            this.f1830a[3] = f4;
            this.f1830a[4] = f5;
            this.f1830a[5] = f6;
            this.f1830a[6] = f7;
            this.f1830a[7] = f8;
            this.f1830a[8] = f9;
            this.f1830a[9] = f10;
            this.f1830a[10] = f11;
        }

        public final float calculate(int i) {
            double stage = getStage(i);
            if (((int) (stage + 1.0E-4d)) + 1 < this.f1830a.length) {
                return (float) (((this.f1830a[r0 + 1] - this.f1830a[r0]) * (stage % 1.0d)) + this.f1830a[r0]);
            }
            return this.f1830a[this.f1830a.length - 1];
        }

        public static double getStage(int i) {
            double d = (i / 60.0f) / 60.0f;
            if (d <= 1.0E-5d) {
                return 0.0d;
            }
            if (d < 4.0d) {
                return d / 4.0d;
            }
            if (d < 10.0d) {
                return 1.0d + ((d - 4.0d) / 6.0d);
            }
            if (d < 20.0d) {
                return 2.0d + ((d - 10.0d) / 10.0d);
            }
            if (d < 40.0d) {
                return 3.0d + ((d - 20.0d) / 20.0d);
            }
            if (d < 80.0d) {
                return 4.0d + ((d - 40.0d) / 40.0d);
            }
            if (d < 160.0d) {
                return 5.0d + ((d - 80.0d) / 80.0d);
            }
            if (d < 320.0d) {
                return 6.0d + ((d - 160.0d) / 160.0d);
            }
            if (d < 640.0d) {
                return 7.0d + ((d - 320.0d) / 320.0d);
            }
            if (d < 1280.0d) {
                return 8.0d + ((d - 640.0d) / 640.0d);
            }
            if (d < 2560.0d) {
                return 9.0d + ((d - 1280.0d) / 1280.0d);
            }
            return 10.0d;
        }
    }

    private ShopOfferValues() {
    }
}

package com.prineside.tdi2.waves.templates;

import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.EnemyType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/templates/ArmoredLow.class */
public class ArmoredLow extends WaveTemplates.WaveTemplate {

    /* renamed from: a, reason: collision with root package name */
    private final WaveTemplates.EnemyGroupConfig[] f3989a = {new WaveTemplates.EnemyGroupConfig(this) { // from class: com.prineside.tdi2.waves.templates.ArmoredLow.1
        private static float a(int i) {
            return MathUtils.clamp(0.4f + ((i / 20.0f) * 0.6f), 0.0f, 1.0f);
        }

        private float b(int i) {
            return 1.0f / a(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public EnemyType getEnemyType() {
            return EnemyType.ARMORED;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getInterval(int i, float f, float f2) {
            return 1.3f * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getCount(int i, float f, float f2) {
            return (int) ((5 + ((int) StrictMath.pow(i, 0.59d))) * a(i));
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getHealth(int i, float f, float f2) {
            return 22.0f + (((float) StrictMath.pow(f2 * 9.0d, 1.26d)) * 0.11f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getBounty(int i, float f, float f2) {
            return (3.8f + (i * 0.11f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getDelay(int i, float f, float f2) {
            return 0.0f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getSpeed(int i, float f, float f2) {
            return 1.0f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getKillExp(int i, float f, float f2) {
            return (1.75f + (i * 0.0175f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getKillScore(int i, float f, float f2) {
            return (int) (20.0f * b(i));
        }
    }};

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public int getProbability(int i, float f, float f2) {
        return WaveTemplates.calculateProbability(i, f2, 3, 0.07f, 0.5f, 0.27f, -71.0f, 25.0f, 0.11f, 4);
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public WaveTemplates.EnemyGroupConfig[] getEnemyGroupConfigs() {
        return this.f3989a;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveMessage() {
        return null;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveName() {
        return "ArmoredLow";
    }
}

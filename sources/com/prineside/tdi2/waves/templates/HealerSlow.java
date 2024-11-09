package com.prineside.tdi2.waves.templates;

import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.EnemyType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/templates/HealerSlow.class */
public class HealerSlow extends WaveTemplates.WaveTemplate {

    /* renamed from: a, reason: collision with root package name */
    private final WaveTemplates.EnemyGroupConfig[] f4002a = {new WaveTemplates.EnemyGroupConfig(this) { // from class: com.prineside.tdi2.waves.templates.HealerSlow.1
        private static float a(int i) {
            return MathUtils.clamp(0.5f + ((i / 10.0f) * 0.5f), 0.0f, 1.0f);
        }

        private float b(int i) {
            return 1.0f / a(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public EnemyType getEnemyType() {
            return EnemyType.HEALER;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getInterval(int i, float f, float f2) {
            return 1.0f * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getCount(int i, float f, float f2) {
            return (int) ((5 + ((int) StrictMath.pow(i * 0.9f, 0.52d))) * a(i));
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getHealth(int i, float f, float f2) {
            return 35.0f + (((float) StrictMath.pow(f2 * 10.0d, 1.24d)) * 0.17f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getBounty(int i, float f, float f2) {
            return (4.5f + (i * 0.13f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getDelay(int i, float f, float f2) {
            return 0.0f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getSpeed(int i, float f, float f2) {
            return 0.6f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getKillExp(int i, float f, float f2) {
            return (2.2f + (i * 0.022f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getKillScore(int i, float f, float f2) {
            return (int) (25.0f * b(i));
        }
    }};

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public int getProbability(int i, float f, float f2) {
        return WaveTemplates.calculateProbability(i, f2, 3, 0.04f, 0.6f, 0.78f, 60.0f, 25.0f, 0.1f, 4);
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public WaveTemplates.EnemyGroupConfig[] getEnemyGroupConfigs() {
        return this.f4002a;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveMessage() {
        return null;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveName() {
        return "HealerSlow";
    }
}

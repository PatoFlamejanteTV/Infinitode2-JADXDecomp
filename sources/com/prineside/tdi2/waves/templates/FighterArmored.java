package com.prineside.tdi2.waves.templates;

import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.EnemyType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/templates/FighterArmored.class */
public class FighterArmored extends WaveTemplates.WaveTemplate {

    /* renamed from: a, reason: collision with root package name */
    private final WaveTemplates.EnemyGroupConfig[] f3995a = {new WaveTemplates.EnemyGroupConfig(this) { // from class: com.prineside.tdi2.waves.templates.FighterArmored.1
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
            return 1.5f * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getCount(int i, float f, float f2) {
            return (int) ((3 + ((int) StrictMath.pow(i * 0.9f, 0.52d))) * 0.75d * a(i));
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getHealth(int i, float f, float f2) {
            return 14.0f + (((float) StrictMath.pow(f2 * 10.0d, 1.23d)) * 0.08f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getBounty(int i, float f, float f2) {
            return (3.5f + (i * 0.09f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getDelay(int i, float f, float f2) {
            return 0.0f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getSpeed(int i, float f, float f2) {
            return 0.9f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getKillExp(int i, float f, float f2) {
            return (1.7f + (i * 0.017f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getKillScore(int i, float f, float f2) {
            return (int) (20.0f * b(i));
        }
    }, new WaveTemplates.EnemyGroupConfig(this) { // from class: com.prineside.tdi2.waves.templates.FighterArmored.2
        private static float a(int i) {
            return MathUtils.clamp(0.6f + ((i / 25.0f) * 0.4f), 0.0f, 1.0f);
        }

        private float b(int i) {
            return 1.0f / a(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public EnemyType getEnemyType() {
            return EnemyType.FIGHTER;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getInterval(int i, float f, float f2) {
            return 1.5f * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getCount(int i, float f, float f2) {
            return (int) ((3 + ((int) StrictMath.pow(i * 0.9f, 0.52d))) * 0.75d * a(i));
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getHealth(int i, float f, float f2) {
            return 10.0f + (((float) StrictMath.pow(f2 * 10.0d, 1.23d)) * 0.05f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getBounty(int i, float f, float f2) {
            return (3.6f + (i * 0.091f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getDelay(int i, float f, float f2) {
            return 0.75f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getSpeed(int i, float f, float f2) {
            return 0.9f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getKillExp(int i, float f, float f2) {
            return (1.75f + (i * 0.018f)) * b(i);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getKillScore(int i, float f, float f2) {
            return (int) (20.0f * b(i));
        }
    }};

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public int getProbability(int i, float f, float f2) {
        return WaveTemplates.calculateProbability(i, f2, 3, 0.04f, 0.6f, 0.28f, -50.0f, 17.0f, 0.1f, 4);
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public WaveTemplates.EnemyGroupConfig[] getEnemyGroupConfigs() {
        return this.f3995a;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveMessage() {
        return null;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveName() {
        return "FighterArmored";
    }
}

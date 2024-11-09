package com.prineside.tdi2.waves.templates;

import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.EnemyType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/waves/templates/RegularMedium.class */
public class RegularMedium extends WaveTemplates.WaveTemplate {

    /* renamed from: a, reason: collision with root package name */
    private final WaveTemplates.EnemyGroupConfig[] f4013a = {new WaveTemplates.EnemyGroupConfig(this) { // from class: com.prineside.tdi2.waves.templates.RegularMedium.1
        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public EnemyType getEnemyType() {
            return EnemyType.REGULAR;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getInterval(int i, float f, float f2) {
            return 0.5f;
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getCount(int i, float f, float f2) {
            return 12 + ((int) StrictMath.pow(i, 0.65d));
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getHealth(int i, float f, float f2) {
            return 18.0f + (((float) StrictMath.pow(f2 * 9.0d, 1.26d)) * 0.075f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public float getBounty(int i, float f, float f2) {
            return 2.0f + (i * 0.05f);
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
            return 1.0f + (i * 0.01f);
        }

        @Override // com.prineside.tdi2.WaveTemplates.EnemyGroupConfig
        public int getKillScore(int i, float f, float f2) {
            return 10;
        }
    }};

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public int getProbability(int i, float f, float f2) {
        return WaveTemplates.calculateProbability(i, f2, 0, 1.0f, 0.5f, 0.25f, 180.0f, 40.0f, 0.16f, 3);
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public WaveTemplates.EnemyGroupConfig[] getEnemyGroupConfigs() {
        return this.f4013a;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveMessage() {
        return null;
    }

    @Override // com.prineside.tdi2.WaveTemplates.WaveTemplate
    public String getWaveName() {
        return "RegularMedium";
    }
}

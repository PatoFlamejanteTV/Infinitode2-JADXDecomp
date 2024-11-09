package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.game.TowerAbilityChange;
import com.prineside.tdi2.events.game.TowerExperienceChange;
import com.prineside.tdi2.events.game.TowerLevelUp;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ExperienceSystem.class */
public final class ExperienceSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private float f2938a;

    /* renamed from: b, reason: collision with root package name */
    private Array<Tower> f2939b = new Array<>(true, 1, Tower.class);
    private FloatArray c = new FloatArray();

    @NAGS
    private final IntIntMap d = new IntIntMap();

    @NAGS
    private final IntArray e = new IntArray();

    static {
        TLog.forClass(ExperienceSystem.class);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2938a);
        kryo.writeObject(output, this.f2939b);
        kryo.writeObject(output, this.c);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2938a = input.readFloat();
        this.f2939b = (Array) kryo.readObject(input, Array.class);
        this.c = (FloatArray) kryo.readObject(input, FloatArray.class);
    }

    public final void addExperienceRaw(Tower tower, float f) {
        if (tower.isRegistered()) {
            tower.addExperience(f);
            this.f2939b.add(tower);
            this.c.add(f);
        }
    }

    public final float addExperienceBuffed(Tower tower, float f) {
        float f2 = f * tower.experienceMultiplier;
        addExperienceRaw(tower, f2);
        return f2;
    }

    public final float removeExperienceRaw(Tower tower, float f) {
        if (f > tower.currentLevelExperience) {
            f = tower.currentLevelExperience;
        }
        tower.setExperience(tower.experience - f);
        this.f2939b.add(tower);
        this.c.add(-f);
        return f;
    }

    public final void updateLevelExperience(Tower tower) {
        short level = tower.getLevel();
        tower.calculateXpLevel(false);
        if (tower.getLevel() != level) {
            this.S.map.markTilesDirtyNearTile(tower.getTile(), 1);
        }
        if (tower.getLevel() > level) {
            notifyTowerLeveledUp(tower);
        }
    }

    public final void notifyTowerLeveledUp(Tower tower) {
        this.S.gameState.checkGameplayUpdateAllowed();
        this.S.events.trigger(new TowerLevelUp(tower));
        if (tower.getLevel() == 10) {
            this.S.events.trigger(new TowerAbilityChange(tower, 3, true));
            tower.onAbilitySet(3, true);
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.lvlUpParticles.obtain();
            obtain.setPosition(tower.getTile().center.x, tower.getTile().center.y);
            this.S._particle.addParticle(obtain, true);
            ParticleEffectPool.PooledEffect obtain2 = Game.i.towerManager.highlightParticles[tower.type.ordinal()].obtain();
            obtain2.setPosition(tower.getTile().center.x, tower.getTile().center.y);
            this.S._particle.addParticle(obtain2, true);
        }
    }

    private void a(float f) {
        this.f2938a += f;
        if (this.S.gameState.isGameRealTimePasses() && this.f2938a > 1.0f) {
            this.f2938a = 0.0f;
            DelayedRemovalArray<Tower> delayedRemovalArray = this.S.tower.towers;
            delayedRemovalArray.begin();
            int i = delayedRemovalArray.size;
            for (int i2 = 0; i2 < i; i2++) {
                Tower tower = delayedRemovalArray.items[i2];
                if (tower.experienceGeneration != 0.0f) {
                    float f2 = tower.experienceGeneration;
                    if (this.S.gameState.isDoubleSpeedActive()) {
                        f2 *= 2.0f;
                    }
                    addExperienceRaw(tower, f2);
                    this.S.statistics.addStatistic(StatisticsType.XPG_TG, f2);
                }
            }
            delayedRemovalArray.end();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        a(f);
        if (this.c.size != 0) {
            this.d.clear();
            this.e.clear();
            int i = this.c.size;
            for (int i2 = 0; i2 < i; i2++) {
                Tower tower = this.f2939b.items[i2];
                int i3 = this.d.get(tower.id, -1);
                if (i3 == -1) {
                    this.d.put(tower.id, i2);
                    this.e.add(i2);
                } else {
                    float[] fArr = this.c.items;
                    fArr[i3] = fArr[i3] + this.c.items[i2];
                }
            }
            int i4 = this.e.size;
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = this.e.items[i5];
                this.S.events.getListeners(TowerExperienceChange.class).trigger(new TowerExperienceChange(this.f2939b.items[i6], this.c.items[i6]));
            }
            this.f2939b.removeRange(0, i - 1);
            this.c.removeRange(0, i - 1);
        }
        updateLevelExperienceOfEveryTower();
    }

    public final void updateLevelExperienceOfEveryTower() {
        DelayedRemovalArray<Tower> delayedRemovalArray = this.S.tower.towers;
        for (int i = 0; i < delayedRemovalArray.size; i++) {
            updateLevelExperience(delayedRemovalArray.items[i]);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Experience";
    }
}

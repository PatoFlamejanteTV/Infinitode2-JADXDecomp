package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.StunBuff;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.buffs.VulnerabilityBuff;
import com.prineside.tdi2.components.StunDebuffStats;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectFilter;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/BlastTower.class */
public final class BlastTower extends Tower {
    public static final String TOWER_OUT_OF_ORDER_REASON_QUAKE = "BlastQuake";
    public static final String[] ABILITY_ALIASES = {"HEAVY_SHELL", "FAST_MECHANISM", "SONIC_WAVE"};
    private static final Color e = new Color(1163551999);

    @NAGS
    private Circle f;
    private State g;
    private float h;
    private float i;
    private float j;
    private Array<Enemy.EnemyReference> k;

    @FrameAccumulatorForPerformance
    private byte l;

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/BlastTower$State.class */
    public enum State {
        NORMAL,
        QUAKING;

        public static final State[] values = values();
    }

    /* synthetic */ BlastTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.g);
        output.writeFloat(this.j);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        kryo.writeObject(output, this.k);
        output.writeByte(this.l);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.g = (State) kryo.readObject(input, State.class);
        this.j = input.readFloat();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.k = (Array) kryo.readObject(input, Array.class);
        this.l = input.readByte();
    }

    private BlastTower() {
        super(TowerType.BLAST);
        this.g = State.NORMAL;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = new Array<>(true, 1, Enemy.EnemyReference.class);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        this.k.clear();
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.BLAST.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.BLAST.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_STUN_DURATION && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_SONIC_WAVE_DURATION));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_DAMAGE));
        }
        if (towerStatType == TowerStatType.ATTACK_SPEED && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_SPEED));
        }
        if (towerStatType == TowerStatType.ATTACK_SPEED && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_FAST_MECHANISM_SPEED));
        }
        if (towerStatType == TowerStatType.STUN_CHANCE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_CHANCE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        if (this.f != null && getTile() != null) {
            this.f.setup(getTile().center.x, getTile().center.y, this.rangeInPixels * 0.5f, this.rangeInPixels, 48, this.f.getInnerColor(), this.f.getOuterColor());
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(1)) {
            group.clear();
            PieChartActor pieChartActor = new PieChartActor();
            Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
            array.add(new PieChart.ChartEntryConfig(MaterialColor.AMBER.P500, 20.0f, 0.0f));
            array.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
            pieChartActor.setPosition(40.0f, 0.0f);
            pieChartActor.setSize(64.0f, 64.0f);
            pieChartActor.setSegmentCount(200);
            pieChartActor.setConfigs(array);
            group.addActor(pieChartActor);
            Actor image = new Image(Game.i.assetManager.getDrawable("circle"));
            image.setColor(new Color(724249599));
            image.setSize(36.0f, 36.0f);
            image.setPosition(54.0f, 14.0f);
            group.addActor(image);
            Actor image2 = new Image(Game.i.assetManager.getDrawable("icon-quake"));
            image2.setSize(28.0f, 28.0f);
            image2.setPosition(58.0f, 18.0f);
            group.addActor(image2);
            Actor label = new Label("", Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.ORANGE.P500);
            label.setPosition(120.0f, 38.0f);
            label.setSize(100.0f, 18.0f);
            group.addActor(label);
            Actor label2 = new Label(Game.i.localeManager.i18n.get("quake_charge"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
            label2.setPosition(120.0f, 12.0f);
            label2.setSize(100.0f, 16.0f);
            group.addActor(label2);
            objectMap.put("quakeChargeChart", pieChartActor);
            objectMap.put("chargeTitle", label);
            objectMap.put("state", 1);
        }
        float f = this.h / 100.0f;
        float f2 = f;
        if (f < 0.0f) {
            f2 = 0.0f;
        }
        Label label3 = (Label) objectMap.get("chargeTitle");
        PieChartActor pieChartActor2 = (PieChartActor) objectMap.get("quakeChargeChart");
        Array<PieChart.ChartEntryConfig> configs = pieChartActor2.getConfigs();
        configs.get(0).setValue(f2);
        if (this.h >= 100.0f) {
            configs.get(0).color = MaterialColor.RED.P500;
        } else {
            configs.get(0).color = MaterialColor.AMBER.P500;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        configs.get(1).setValue(1.0f - f2);
        pieChartActor2.setConfigs(configs);
        if (this.h >= 100.0f) {
            label3.setColor(MaterialColor.RED.P500);
        } else {
            label3.setColor(MaterialColor.AMBER.P500);
        }
        d.setLength(0);
        d.append(MathUtils.round(f2 * 100.0f));
        d.append("%");
        label3.setText(d);
        super.fillTowerMenu(group, objectMap);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawWeapon(Batch batch, float f, float f2, float f3, float f4) {
        if (getTile().visibleOnScreen && !isOutOfOrder()) {
            float f5 = 0.0f;
            switch (this.g) {
                case NORMAL:
                    if (this.j > getAttackDelay()) {
                        f5 = 1.0f;
                        break;
                    } else {
                        f5 = 0.7f + ((this.j / getAttackDelay()) * 0.3f);
                        break;
                    }
                case QUAKING:
                    float f6 = 1.0f + ((this.i / 2.0f) * 0.6f);
                    f5 = f6;
                    if (f6 > 1.6f) {
                        f5 = 1.6f;
                        break;
                    }
                    break;
            }
            float f7 = f5;
            getWeaponTextures().draw(batch, f, f2, f3, f3, f7, f7, 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float a() {
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_MIN_DIST);
        return floatValue + (getUpgradeLevel() * 0.1f * (this.S.gameValue.getFloatValue(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_MAX_DIST) - floatValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b() {
        float upgradeLevel = getUpgradeLevel() / 10.0f;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_ULTIMATE_MIN);
        return percentValueAsMultiplier + ((((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_ULTIMATE_MAX)) - percentValueAsMultiplier) * upgradeLevel);
    }

    private boolean a(Enemy enemy, float f, float f2) {
        float f3;
        if (enemy == null) {
            return false;
        }
        StunDebuffStats stunDebuffStats = enemy.stunDebuffStats;
        int countByTower = stunDebuffStats == null ? 0 : stunDebuffStats.getCountByTower(this.id);
        float f4 = f2;
        if (countByTower >= StunBuff.STUN_DURATION_BY_STUN_COUNT.length) {
            f3 = 0.0f;
        } else {
            f3 = f * StunBuff.STUN_CHANCE_PENALTY_SAME_TOWER[countByTower];
            f4 *= StunBuff.STUN_DURATION_BY_STUN_COUNT[countByTower];
        }
        boolean z = false;
        if (f3 != 0.0f && this.S.gameState.randomFloat() < f3) {
            StunBuff stunBuff = new StunBuff();
            float buffVulnerability = f4 * enemy.getBuffVulnerability(BuffType.STUN);
            stunBuff.setup(buffVulnerability, buffVulnerability * 10.0f, this.id);
            if (this.S.buff.P_STUN.addBuff(enemy, stunBuff)) {
                z = true;
            }
        }
        if (isAbilityInstalled(3)) {
            if (this.S.gameState.randomFloat() < this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_CHANCE)) {
                float a2 = a();
                ThrowBackBuff throwBackBuff = new ThrowBackBuff();
                throwBackBuff.setup(this.id, a2 * 2.0f, 0.5f, 10.0f);
                if (this.S.buff.P_THROW_BACK.addBuff(enemy, throwBackBuff) && isAbilityInstalled(4)) {
                    float b2 = b();
                    VulnerabilityBuff vulnerabilityBuff = new VulnerabilityBuff();
                    vulnerabilityBuff.setup(this.id, b2, 2.0f, 20.0f);
                    this.S.buff.P_VULNERABILITY.addBuff(enemy, vulnerabilityBuff);
                }
            }
        }
        return z;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (!isOutOfOrder()) {
            this.l = (byte) (this.l + 1);
            if (this.l == 4) {
                this.l = (byte) 0;
                this.k.clear();
                this.S.map.getEnemiesInCircleV(getTile().center, this.rangeInPixels, (enemyReference, f2, f3, f4) -> {
                    Enemy enemy = enemyReference.enemy;
                    if (enemy == null || !canAttackEnemy(enemy)) {
                        return true;
                    }
                    this.k.add(enemyReference);
                    return true;
                });
            }
            boolean isAbilityInstalled = isAbilityInstalled(2);
            switch (this.g) {
                case NORMAL:
                    this.j += f;
                    if ((this.k.size != 0 || (isAbilityInstalled && this.h >= 100.0f)) && this.j >= getAttackDelay()) {
                        if (this.h >= 100.0f) {
                            this.g = State.QUAKING;
                            this.i = 0.0f;
                            break;
                        } else {
                            boolean z = false;
                            float stat = getStat(TowerStatType.DAMAGE);
                            float stat2 = getStat(TowerStatType.STUN_CHANCE) * 0.01f;
                            float stat3 = getStat(TowerStatType.U_STUN_DURATION);
                            float f5 = this.rangeInPixels * this.rangeInPixels;
                            int i = 0;
                            for (int i2 = 0; i2 < this.k.size; i2++) {
                                Enemy enemy = this.k.items[i2].enemy;
                                if (enemy != null && getTile().center.dst2(enemy.getPosition()) < f5) {
                                    float dst = getTile().center.dst(enemy.getPosition());
                                    if (stat * (1.0f - (dst / this.rangeInPixels)) > 0.0f) {
                                        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, stat * (1.0f - (dst / this.rangeInPixels)), DamageType.EXPLOSION).setTower(this));
                                        z = true;
                                        if (i < 3 && a(enemy, stat2, stat3)) {
                                            i++;
                                            this.S.achievement.setProgress(AchievementType.MASS_STUN_ENEMIES_ONE_SHOT, i);
                                        }
                                    }
                                }
                            }
                            if (z) {
                                this.j = 0.0f;
                                this.shotCount++;
                                if (this.S._particle != null && Game.i.settingsManager.isExplosionsDrawing()) {
                                    ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.F.BLAST.c.obtain();
                                    float x = (getTile().getX() << 7) + 64;
                                    float y = (getTile().getY() << 7) + 64;
                                    obtain.setPosition(x, y);
                                    ParticleEmitter first = obtain.getEmitters().first();
                                    ParticleEmitter.GradientColorValue tint = first.getTint();
                                    float[] colors = tint.getColors();
                                    colors[0] = e.r;
                                    colors[1] = e.g;
                                    colors[2] = e.f888b;
                                    tint.setColors(colors);
                                    first.getXScale().setHigh(this.rangeInPixels * 2.0f);
                                    first.getYScale().setHigh(this.rangeInPixels * 2.0f);
                                    this.S._particle.addLimitedParticle(obtain, LimitedParticleType.EXPLOSION_BLAST, x, y);
                                }
                                if (this.S._sound != null) {
                                    this.S._sound.playShotSound(StaticSoundType.SHOT_BLAST, this);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case QUAKING:
                    this.i += f;
                    if (this.i >= 2.0f) {
                        float stat4 = getStat(TowerStatType.DAMAGE) * getStat(TowerStatType.ATTACK_SPEED) * this.i;
                        float stat5 = getStat(TowerStatType.U_STUN_DURATION) * 1.5f;
                        float f6 = this.rangeInPixels * this.rangeInPixels;
                        int i3 = 0;
                        for (int i4 = 0; i4 < this.k.size; i4++) {
                            Enemy enemy2 = this.k.items[i4].enemy;
                            if (enemy2 != null && getTile().center.dst2(enemy2.getPosition()) < f6) {
                                float dst2 = getTile().center.dst(enemy2.getPosition());
                                if (stat4 * (1.0f - (dst2 / this.rangeInPixels)) > 0.0f) {
                                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy2, stat4 * (1.0f - (dst2 / this.rangeInPixels)), DamageType.EXPLOSION).setTower(this));
                                    if (a(enemy2, 1.0f, stat5)) {
                                        i3++;
                                        this.S.achievement.setProgress(AchievementType.MASS_STUN_ENEMIES_ONE_SHOT, i3);
                                        if (this.S._particle != null) {
                                            ParticleEffectPool.PooledEffect obtain2 = Game.i.towerManager.F.BLAST.e.obtain();
                                            obtain2.setPosition(enemy2.getPosition().x, enemy2.getPosition().y);
                                            this.S._particle.addLimitedParticle(obtain2, LimitedParticleType.ENEMY_STUN, enemy2.getPosition().x, enemy2.getPosition().y);
                                        }
                                    }
                                }
                            }
                        }
                        this.S.map.traverseNeighborTilesAroundTile(getTile(), new ObjectFilter<Tile>(this) { // from class: com.prineside.tdi2.towers.BlastTower.1
                            @Override // com.prineside.tdi2.utils.ObjectFilter
                            public boolean test(Tile tile) {
                                if (tile instanceof PlatformTile) {
                                    PlatformTile platformTile = (PlatformTile) tile;
                                    if (platformTile.building instanceof Tower) {
                                        ((Tower) platformTile.building).outOfOrder.addReasonForDuration(BlastTower.TOWER_OUT_OF_ORDER_REASON_QUAKE, 2.0f);
                                        return true;
                                    }
                                    return true;
                                }
                                return true;
                            }
                        });
                        if (isAbilityInstalled) {
                            Array<Enemy> enemyArray = this.S.TSH.getEnemyArray();
                            for (int i5 = 0; i5 < this.S.map.spawnedEnemies.size; i5++) {
                                Enemy.EnemyReference enemyReference2 = this.S.map.spawnedEnemies.items[i5];
                                if (enemyReference2.enemy != null && enemyReference2.enemy.getPosition().dst2(getTile().center) > f6 && canAttackEnemy(enemyReference2.enemy) && enemyReference2.enemy.canBeBuffed(BuffType.STUN)) {
                                    enemyArray.add(enemyReference2.enemy);
                                }
                            }
                            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_BLAST_A_SONIC_WAVE_QUAKE_ENEMIES);
                            for (int i6 = 0; i6 < intValue; i6++) {
                                if (enemyArray.size != 0) {
                                    Enemy removeIndex = enemyArray.removeIndex(this.S.gameState.randomInt(enemyArray.size));
                                    if (a(removeIndex, 1.0f, stat5)) {
                                        i3++;
                                        this.S.achievement.setProgress(AchievementType.MASS_STUN_ENEMIES_ONE_SHOT, i3);
                                        if (this.S._particle != null) {
                                            ParticleEffectPool.PooledEffect obtain3 = Game.i.towerManager.F.BLAST.e.obtain();
                                            obtain3.setPosition(removeIndex.getPosition().x, removeIndex.getPosition().y);
                                            this.S._particle.addLimitedParticle(obtain3, LimitedParticleType.ENEMY_STUN, removeIndex.getPosition().x, removeIndex.getPosition().y);
                                        }
                                    }
                                }
                            }
                            this.S.TSH.freeEnemyArray(enemyArray);
                        }
                        if (this.S._particle != null && Game.i.settingsManager.isExplosionsDrawing()) {
                            ParticleEffectPool.PooledEffect obtain4 = Game.i.towerManager.F.BLAST.d.obtain();
                            float x2 = (getTile().getX() << 7) + 64;
                            float y2 = (getTile().getY() << 7) + 64;
                            obtain4.setPosition(x2, y2);
                            ParticleEmitter first2 = obtain4.getEmitters().first();
                            first2.getXScale().setHigh(this.rangeInPixels * 2.0f);
                            first2.getYScale().setHigh(this.rangeInPixels * 2.0f);
                            this.S._particle.addLimitedParticle(obtain4, LimitedParticleType.EXPLOSION_BLAST, x2, y2);
                        }
                        if (this.S._sound != null) {
                            this.S._sound.playShotSound(StaticSoundType.SHOT_BLAST_QUAKE, this);
                        }
                        this.h = 0.0f;
                        this.i = 0.0f;
                        this.j = 0.0f;
                        this.g = State.NORMAL;
                        break;
                    }
                    break;
            }
            if (this.S.gameState.isGameRealTimePasses()) {
                this.h += getStat(TowerStatType.U_QUAKE_CHARGE_SPEED) * f;
                if (this.h > 100.0f) {
                    this.h = 100.0f;
                    this.i += f;
                } else {
                    this.i = 0.0f;
                }
            }
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            if (this.f == null && Game.i.shapeManager != null) {
                this.f = (Circle) Game.i.shapeManager.getFactory(ShapeType.CIRCLE).obtain();
                Color cpy = MaterialColor.BLUE_GREY.P500.cpy();
                Color cpy2 = MaterialColor.BLUE_GREY.P500.cpy();
                cpy.f889a = 0.0f;
                cpy2.f889a = 0.14f;
                this.f.setup(getTile().center.x, getTile().center.y, this.rangeInPixels * 0.5f, this.rangeInPixels, 48, cpy.toFloatBits(), cpy2.toFloatBits());
            }
            if (this.f != null) {
                this.f.draw(batch);
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    protected final void a(StringBuilder stringBuilder) {
        stringBuilder.append("state: ").append(this.g.name()).append(SequenceUtils.EOL);
        stringBuilder.append("quakeCharge: ").append(this.h).append(SequenceUtils.EOL);
        stringBuilder.append("quakeProgress: ").append(this.i).append(SequenceUtils.EOL);
        stringBuilder.append("timeSinceLastAttack: ").append(this.j).append(SequenceUtils.EOL);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/BlastTower$BlastTowerFactory.class */
    public static class BlastTowerFactory extends Tower.Factory<BlastTower> {
        ParticleEffectPool c;
        ParticleEffectPool d;
        ParticleEffectPool e;

        public BlastTowerFactory() {
            super("tower-blast", TowerType.BLAST);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[1].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new Object[]{"", 1};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new Object[]{"", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.STUN_CHANCE) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_BLAST_STUN_CHANCE", StringFormatter.compactNumberWithPrecisionTrimZeros(100.0d, 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(3.0d, 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(20.0d, 1, true).toString(), 6);
            }
            if (towerStatType == TowerStatType.U_STUN_DURATION) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_BLAST_U_STUN_DURATION", 25, 0);
            }
            if (towerStatType == TowerStatType.U_QUAKE_CHARGE_SPEED) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_BLAST_U_QUAKE_CHARGE_SPEED", StringFormatter.compactNumberWithPrecisionTrimZeros(1.5d, 1, true).toString()) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("quake_disables_nearest_towers_for", Float.valueOf(2.0f));
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_SPEED), 2, true).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_DAMAGE), 2, true).toString();
            abilityConfigs[0].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_HEAVY_SHELL_CHANCE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_FAST_MECHANISM_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BLAST_A_SONIC_WAVE_DURATION), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = Integer.valueOf(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_BLAST_A_SONIC_WAVE_QUAKE_ENEMIES));
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(((BlastTower) tower).a(), 2, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_BLAST_A_STOPPING_FORCE_CHANCE), false).toString();
            abilityConfigs[4].descriptionArgs[0] = "+" + StringFormatter.compactNumber((((BlastTower) tower).b() - 1.0f) * 100.0f, false).toString();
            abilityConfigs[4].descriptionArgs[1] = "2";
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 0) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.BLUE_GREY.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 2;
                case ATTACK_SPEED:
                    return 1;
                case DAMAGE:
                    return 1;
                case CROWD_DAMAGE:
                    return 3;
                case AGILITY:
                    return 3;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return BlastTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getParticleEffectPool("shockwave.prt");
            this.d = Game.i.assetManager.getParticleEffectPool("shockwave-quake.prt");
            this.e = Game.i.assetManager.getParticleEffectPool("stun.prt");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 32;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public BlastTower create() {
            return new BlastTower((byte) 0);
        }
    }
}

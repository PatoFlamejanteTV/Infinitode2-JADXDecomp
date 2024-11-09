package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.buffs.PoisonBuff;
import com.prineside.tdi2.buffs.VulnerabilityBuff;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.units.MicrogunUnit;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MinigunTower.class */
public final class MinigunTower extends Tower {
    public static final String[] ABILITY_ALIASES;
    public static Color muzzleFlashColor;

    @NAGS
    private Color e;

    @NAGS
    private float f;

    @NAGS
    private float g;
    private byte h;
    private float i;
    private float j;
    private int k;
    private int l;
    private float m;
    private float n;
    private float o;
    private float p;

    /* synthetic */ MinigunTower(byte b2) {
        this();
    }

    static {
        TLog.forClass(MinigunTower.class);
        ABILITY_ALIASES = new String[]{"HEAVY_WEAPONS", "HEAVY_MECHANISM", "FOUNDATION"};
        muzzleFlashColor = Color.WHITE.cpy();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.h);
        output.writeFloat(this.i);
        output.writeFloat(this.j);
        output.writeVarInt(this.k, true);
        output.writeVarInt(this.l, true);
        output.writeFloat(this.m);
        output.writeFloat(this.n);
        output.writeFloat(this.o);
        output.writeFloat(this.p);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.h = input.readByte();
        this.i = input.readFloat();
        this.j = input.readFloat();
        this.k = input.readVarInt(true);
        this.l = input.readVarInt(true);
        this.m = input.readFloat();
        this.n = input.readFloat();
        this.o = input.readFloat();
        this.p = input.readFloat();
    }

    private MinigunTower() {
        super(TowerType.MINIGUN);
        this.e = Color.WHITE.cpy();
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.MINIGUN.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.MINIGUN.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final float getAttackDelay() {
        float f = this.i / this.n;
        if (f == 0.0f) {
            return Float.MAX_VALUE;
        }
        return 1.0f / (f * getStat(TowerStatType.ATTACK_SPEED));
    }

    public final int getBulletsInMagazine() {
        return this.l;
    }

    public final void setBulletsInMagazine(int i) {
        this.l = i;
    }

    public final int getMagazineSize() {
        return MathUtils.round(getStat(TowerStatType.U_MAGAZINE_SIZE));
    }

    public final float getReloadDuration() {
        return this.S.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_RELOAD_DURATION);
    }

    public final float getTimeSinceLastAttack() {
        return this.j;
    }

    public final float getTimeSinceReloadStart() {
        return this.m;
    }

    @Override // com.prineside.tdi2.Tower
    protected final void a(StringBuilder stringBuilder) {
        stringBuilder.append("framesSinceTargetLost: ").append((int) this.h).append(SequenceUtils.EOL);
        stringBuilder.append("speedingUpTime: ").append(this.i).append(SequenceUtils.EOL);
        stringBuilder.append("timeSinceLastAttack: ").append(this.j).append(SequenceUtils.EOL);
        stringBuilder.append("shotCount: ").append(this.k).append(SequenceUtils.EOL);
        stringBuilder.append("bulletsInMagazine: ").append(this.l).append(SequenceUtils.EOL);
        stringBuilder.append("magazineSize: ").append(getMagazineSize()).append(SequenceUtils.EOL);
        stringBuilder.append("timeSinceReloadStart: ").append(this.m).append(SequenceUtils.EOL);
        stringBuilder.append("speedUpRequiredTime: ").append(this.n).append(SequenceUtils.EOL);
        stringBuilder.append("timeSinceMicrogunPlaced: ").append(this.o).append(SequenceUtils.EOL);
        stringBuilder.append("timeSinceSpecialHandled: ").append(this.p).append(SequenceUtils.EOL);
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttack() {
        Enemy target = getTarget();
        if (target == null || this.attackDisabled) {
            return false;
        }
        return StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(getTile().center, target.getPosition()))) < this.S.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_KEEP_SHOOTING_ANGLE);
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null || this.l <= 0) {
            return;
        }
        if (i > this.l) {
            i = this.l;
        }
        this.l -= i;
        this.j = 0.0f;
        if (this.S._particle != null) {
            Vector2 vector2 = new Vector2();
            vector2.set(getTile().center);
            PMath.shiftPointByAngle(vector2, this.angle, 45.0f);
            this.S._particle.addFlashParticle(AssetManager.TextureRegions.i().muzzleFlashSmall, vector2.x, vector2.y, 12.0f, 4.5f, 24.0f, 36.0f, this.angle);
        }
        float randomFloat = this.angle + ((this.S.gameState.randomFloat() - 0.5f) * this.S.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_BULLET_SPREAD) * (this.i / this.n));
        float f = this.rangeInPixels;
        Vector2 vector22 = new Vector2();
        PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, randomFloat, f, vector22);
        Enemy[] enemyArr = {null};
        this.S.map.rayCastEnemiesSorted(getTile().center.x, getTile().center.y, vector22.x, vector22.y, 0.0f, enemyReference -> {
            Enemy enemy = enemyReference.enemy;
            if (canAttackEnemy(enemy)) {
                enemyArr[0] = enemy;
                return false;
            }
            return true;
        });
        if (enemyArr[0] != null) {
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, randomFloat, PMath.getDistanceBetweenPoints(getTile().center, enemyArr[0].getPosition()), vector22);
        }
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled() && (PMath.getDistanceBetweenAngles(this.f, this.angle) > 2.0f || this.g > 0.05f)) {
            this.f = this.angle;
            this.g = 0.0f;
            BulletSmokeMultiLine obtain = Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.obtain();
            Vector2 vector23 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 30.0f, vector23);
            obtain.setTexture(Game.i.towerManager.F.MINIGUN.bulletTraceTexture, false, FastRandom.getFloat() < 0.5f);
            if (this.i >= this.n && isAbilityInstalled(3)) {
                if (isAbilityInstalled(2)) {
                    obtain.setColor(MaterialColor.LIGHT_GREEN.P500);
                } else {
                    obtain.setColor(MaterialColor.DEEP_ORANGE.P500);
                }
            } else {
                obtain.setColor(MaterialColor.PURPLE.P200);
            }
            obtain.maxSegmentWidth = 32.0f;
            obtain.nodesDisperseTime = 0.7f;
            obtain.maxAlpha = 0.56f;
            obtain.setup(vector23.x, vector23.y, vector22.x, vector22.y);
            this.S._projectileTrail.addTrail(obtain);
        }
        if (this.p > 0.2f) {
            this.p = 0.0f;
            if (enemyArr[0] != null && isAbilityInstalled(3) && this.i >= this.n * 0.9f) {
                float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HOT_DAMAGE);
                if (isAbilityInstalled(2)) {
                    percentValueAsMultiplier += (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_FOUNDATION_SPECIAL_BONUS);
                }
                float stat = getStat(TowerStatType.ATTACK_SPEED) * getStat(TowerStatType.DAMAGE) * percentValueAsMultiplier;
                float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_HOT_DURATION);
                if (isAbilityInstalled(2)) {
                    PoisonBuff poisonBuff = new PoisonBuff();
                    poisonBuff.setup(this, floatValue, floatValue * 10.0f, stat, stat, null);
                    this.S.buff.P_POISON.addBuff(enemyArr[0], poisonBuff);
                } else {
                    BurnBuff burnBuff = new BurnBuff();
                    burnBuff.setup(this, floatValue, floatValue * 10.0f, stat, null);
                    this.S.buff.P_BURN.addBuff(enemyArr[0], burnBuff);
                }
            }
        }
        if (enemyArr[0] != null) {
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemyArr[0], getStat(TowerStatType.DAMAGE) * i, DamageType.BULLET).setTower(this));
            if (isAbilityInstalled(0)) {
                float a2 = a();
                VulnerabilityBuff vulnerabilityBuff = new VulnerabilityBuff();
                vulnerabilityBuff.setup(this.id, a2, 2.0f, 20.0f);
                this.S.buff.P_VULNERABILITY.addBuff(enemyArr[0], vulnerabilityBuff);
            }
        }
        this.k += i;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_MINIGUN, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_ACCELERATION && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_ACCELERATION));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_WEAPONS_DAMAGE));
        }
        if (towerStatType == TowerStatType.ATTACK_SPEED && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_SPEED));
        }
        if (towerStatType == TowerStatType.ROTATION_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_FOUNDATION_ROTATION));
        }
        if (towerStatType == TowerStatType.U_MAGAZINE_SIZE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_MAGAZINE));
        }
        return calculateStat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float a() {
        float upgradeLevel = getUpgradeLevel() / 10.0f;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_WEAPONS_VULNERABILITY_MIN);
        return percentValueAsMultiplier + ((((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_WEAPONS_VULNERABILITY_MAX)) - percentValueAsMultiplier) * upgradeLevel);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        this.n = (1.0f / getStat(TowerStatType.U_ACCELERATION)) * 100.0f;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        boolean canAttack = canAttack();
        boolean isOutOfOrder = isOutOfOrder();
        boolean z = canAttack && !isOutOfOrder && this.l > 0;
        boolean z2 = z;
        if (z || this.h < 6) {
            this.i += f;
            if (this.i > this.n) {
                this.i = this.n;
            }
        } else {
            this.i -= f * 2.0f;
            if (this.i < 0.0f) {
                this.i = 0.0f;
            }
        }
        if (z2) {
            this.h = (byte) 0;
        } else if (this.h < 6) {
            this.h = (byte) (this.h + 1);
        }
        if (isOutOfOrder) {
            super.update(f);
            return;
        }
        float reloadDuration = getReloadDuration();
        if (this.l == 0) {
            this.m += f;
            if (this.m >= reloadDuration) {
                this.l = getMagazineSize();
                this.m = 0.0f;
            }
        } else {
            this.m = 0.0f;
            if (this.l < getMagazineSize() && this.j > reloadDuration) {
                this.l = getMagazineSize();
            }
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        this.j += f;
        this.g += f;
        this.p += f;
        if (isAbilityInstalled(4)) {
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_COUNT);
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_BUILD_DELAY);
            this.o += f;
            if (this.o > floatValue) {
                this.o = 0.0f;
                int i = 0;
                for (int i2 = 0; i2 < this.S.map.spawnedUnits.size; i2++) {
                    Unit unit = this.S.map.spawnedUnits.items[i2];
                    if (unit.type == UnitType.MICROGUN && ((MicrogunUnit) unit).parent == this) {
                        i++;
                    }
                }
                if (i < intValue) {
                    Array<Tile> tileArray = this.S.TSH.getTileArray();
                    boolean[] zArr = {false};
                    this.S.tower.traverseTilesInRange(this, tile -> {
                        if (tile.type == TileType.ROAD || tile.type == TileType.PLATFORM) {
                            boolean z3 = false;
                            if (tile.type == TileType.PLATFORM && ((PlatformTile) tile).building != null) {
                                z3 = true;
                            } else if (tile.enemyCount != 0) {
                                z3 = true;
                            } else {
                                DelayedRemovalArray<Unit> delayedRemovalArray = this.S.map.spawnedUnits;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= delayedRemovalArray.size) {
                                        break;
                                    }
                                    Unit unit2 = delayedRemovalArray.items[i3];
                                    if (unit2.type != UnitType.MICROGUN || PMath.getSquareDistanceBetweenPoints(tile.center.x, tile.center.y, unit2.position.x, unit2.position.y) >= 64.0f) {
                                        i3++;
                                    } else {
                                        z3 = true;
                                        break;
                                    }
                                }
                            }
                            if (!z3) {
                                if (tile.type == TileType.PLATFORM) {
                                    zArr[0] = true;
                                }
                                tileArray.add(tile);
                                return true;
                            }
                            return true;
                        }
                        return true;
                    });
                    if (tileArray.size != 0) {
                        if (zArr[0]) {
                            for (int i3 = tileArray.size - 1; i3 >= 0; i3--) {
                                if (tileArray.items[i3].type == TileType.ROAD) {
                                    tileArray.removeIndex(i3);
                                }
                            }
                        }
                        Tile tile2 = tileArray.items[this.S.gameState.randomInt(tileArray.size)];
                        MicrogunUnit create = Game.i.unitManager.F.MICROGUN.create();
                        create.setup(this, tile2.center.x, tile2.center.y);
                        this.S.unit.register(create);
                        this.S.map.spawnUnit(create);
                        if (this.S._sound != null) {
                            this.S._sound.playStatic(StaticSoundType.BUILDING_BUILT);
                        }
                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                            ParticleEffectPool.PooledEffect obtain = Game.i.unitManager.F.MICROGUN.highlightParticles.obtain();
                            obtain.setPosition(tile2.center.x, tile2.center.y);
                            this.S._particle.addParticle(obtain, true);
                        }
                    }
                    this.S.TSH.freeTileArray(tileArray);
                }
            }
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        Quad quad;
        super.drawBatch(batch, f);
        if (getTile().visibleOnScreen && this.i != 0.0f) {
            this.e.f889a = this.i / this.n;
            batch.setColor(this.e);
            if (isAbilityInstalled(0)) {
                quad = Game.i.towerManager.F.MINIGUN.d;
            } else {
                quad = Game.i.towerManager.F.MINIGUN.c;
            }
            quad.draw(batch, getTile().boundingBox.minX, getTile().boundingBox.minY, 64.0f, 64.0f, 128.0f, 128.0f, 1.0f, 1.0f, this.angle);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
            debugFont.draw(batch, "S: " + ((Object) StringFormatter.compactNumberWithPrecision(this.i / this.n, 2)), getTile().boundingBox.minX, (getTile().boundingBox.minY - 20.0f) + 64.0f, 128.0f, 1, false);
            debugFont.setColor(Color.WHITE);
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void drawSelectedRange(Batch batch, RangeCircle rangeCircle) {
        RangeCircle rangeCircle2;
        super.drawSelectedRange(batch, rangeCircle);
        Array array = Game.i.towerManager.F.MINIGUN.e;
        for (int i = 0; i < this.S.map.spawnedUnits.size; i++) {
            Unit unit = this.S.map.spawnedUnits.items[i];
            if (unit.type == UnitType.MICROGUN) {
                MicrogunUnit microgunUnit = (MicrogunUnit) unit;
                if (microgunUnit.parent == this) {
                    if (array.size <= 0) {
                        rangeCircle2 = Game.i.shapeManager.F.RANGE_CIRCLE.obtain();
                        array.add(rangeCircle2);
                    } else {
                        rangeCircle2 = ((RangeCircle[]) array.items)[0];
                    }
                    microgunUnit.drawRange(batch, rangeCircle2);
                }
            }
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
            Actor image2 = new Image(Game.i.assetManager.getDrawable("icon-aim-time"));
            image2.setSize(28.0f, 28.0f);
            image2.setPosition(58.0f, 18.0f);
            group.addActor(image2);
            Actor label = new Label("", Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.ORANGE.P500);
            label.setPosition(120.0f, 38.0f);
            label.setSize(100.0f, 18.0f);
            group.addActor(label);
            Actor label2 = new Label(Game.i.localeManager.i18n.get("tower_stat_U_ACCELERATION"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
            label2.setPosition(120.0f, 12.0f);
            label2.setSize(100.0f, 16.0f);
            group.addActor(label2);
            PieChartActor pieChartActor2 = new PieChartActor();
            Array<PieChart.ChartEntryConfig> array2 = new Array<>(PieChart.ChartEntryConfig.class);
            array2.add(new PieChart.ChartEntryConfig(MaterialColor.PURPLE.P700, 20.0f, 0.0f));
            array2.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
            pieChartActor2.setPosition(296.0f, -6.0f);
            pieChartActor2.setSize(76.0f, 76.0f);
            pieChartActor2.setSegmentCount(200);
            pieChartActor2.setConfigs(array2);
            group.addActor(pieChartActor2);
            Actor image3 = new Image(Game.i.assetManager.getDrawable("circle"));
            image3.setColor(new Color(724249599));
            image3.setSize(68.0f, 68.0f);
            image3.setPosition(300.0f, -2.0f);
            group.addActor(image3);
            PieChartActor pieChartActor3 = new PieChartActor();
            Array<PieChart.ChartEntryConfig> array3 = new Array<>(PieChart.ChartEntryConfig.class);
            array3.add(new PieChart.ChartEntryConfig(MaterialColor.PURPLE.P500, 20.0f, 0.0f));
            array3.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
            pieChartActor3.setPosition(302.0f, 0.0f);
            pieChartActor3.setSize(64.0f, 64.0f);
            pieChartActor3.setSegmentCount(200);
            pieChartActor3.setConfigs(array3);
            group.addActor(pieChartActor3);
            Actor image4 = new Image(Game.i.assetManager.getDrawable("circle"));
            image4.setColor(new Color(724249599));
            image4.setSize(36.0f, 36.0f);
            image4.setPosition(316.0f, 14.0f);
            group.addActor(image4);
            Actor image5 = new Image(Game.i.assetManager.getDrawable("icon-bullet-wall"));
            image5.setSize(28.0f, 28.0f);
            image5.setPosition(320.0f, 18.0f);
            group.addActor(image5);
            Actor label3 = new Label("", Game.i.assetManager.getLabelStyle(24));
            label3.setColor(MaterialColor.PURPLE.P400);
            label3.setPosition(382.0f, 38.0f);
            label3.setSize(100.0f, 18.0f);
            group.addActor(label3);
            Actor label4 = new Label("", Game.i.assetManager.getLabelStyle(21));
            label4.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
            label4.setPosition(382.0f, 12.0f);
            label4.setSize(100.0f, 16.0f);
            group.addActor(label4);
            objectMap.put("speedUpChart", pieChartActor);
            objectMap.put("magazineReloadChart", pieChartActor2);
            objectMap.put("magazineChart", pieChartActor3);
            objectMap.put("accelerationTitle", label);
            objectMap.put("magazineTitle", label3);
            objectMap.put("magazineReloadingDescription", label4);
            objectMap.put("state", 1);
        }
        float f = this.i / this.n;
        Label label5 = (Label) objectMap.get("accelerationTitle");
        Label label6 = (Label) objectMap.get("magazineTitle");
        Label label7 = (Label) objectMap.get("magazineReloadingDescription");
        PieChartActor pieChartActor4 = (PieChartActor) objectMap.get("speedUpChart");
        Array<PieChart.ChartEntryConfig> configs = pieChartActor4.getConfigs();
        configs.get(0).setValue(f);
        if (f >= 0.9f) {
            configs.get(0).color = MaterialColor.RED.P500;
        } else {
            configs.get(0).color = MaterialColor.AMBER.P500;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        configs.get(1).setValue(1.0f - f);
        pieChartActor4.setConfigs(configs);
        if (f >= 0.9f) {
            label5.setColor(MaterialColor.RED.P500);
        } else {
            label5.setColor(MaterialColor.AMBER.P500);
        }
        d.setLength(0);
        d.append(MathUtils.round(f * 100.0f));
        d.append("%");
        label5.setText(d);
        PieChartActor pieChartActor5 = (PieChartActor) objectMap.get("magazineChart");
        PieChartActor pieChartActor6 = (PieChartActor) objectMap.get("magazineReloadChart");
        Array<PieChart.ChartEntryConfig> configs2 = pieChartActor5.getConfigs();
        Array<PieChart.ChartEntryConfig> configs3 = pieChartActor6.getConfigs();
        if (getBulletsInMagazine() == 0) {
            float reloadDuration = this.m / getReloadDuration();
            float f2 = reloadDuration;
            if (reloadDuration > 1.0f) {
                f2 = 1.0f;
            }
            configs3.get(0).setValue(f2);
            configs3.get(0).color = MaterialColor.PURPLE.P500;
            configs3.get(1).setValue(1.0f - f2);
            configs2.get(0).setValue(0.0f);
            configs2.get(1).setValue(1.0f);
            label7.setText(Game.i.localeManager.i18n.get("ammo_reloading_status_hint"));
        } else {
            float bulletsInMagazine = getBulletsInMagazine() / getMagazineSize();
            float f3 = bulletsInMagazine;
            if (bulletsInMagazine > 1.0f) {
                f3 = 1.0f;
            }
            configs2.get(0).setValue(f3);
            configs2.get(1).setValue(1.0f - f3);
            float clamp = MathUtils.clamp(((f3 < 1.0f ? this.j / getReloadDuration() : 0.0f) - 0.05f) / 0.95f, 0.0f, 1.0f);
            configs3.get(0).setValue(clamp);
            configs3.get(0).color = MaterialColor.PURPLE.P700;
            configs3.get(1).setValue(1.0f - clamp);
            label7.setText(Game.i.localeManager.i18n.get("bullets_in_magazine_status_hint"));
        }
        pieChartActor6.setConfigs(configs3);
        pieChartActor5.setConfigs(configs2);
        d.setLength(0);
        d.append(getBulletsInMagazine()).append(" / ").append(getMagazineSize());
        label6.setText(d);
        super.fillTowerMenu(group, objectMap);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MinigunTower$MinigunTowerFactory.class */
    public static class MinigunTowerFactory extends Tower.Factory<MinigunTower> {
        public TextureRegion bulletTraceTexture;
        Quad c;
        Quad d;
        private Array<RangeCircle> e;

        public MinigunTowerFactory() {
            super("tower-minigun", TowerType.MINIGUN);
            this.e = new Array<>(RangeCircle.class);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.bulletTraceTexture = Game.i.assetManager.getTextureRegion("bullet-trace-smoke");
            this.c = Game.i.assetManager.getQuad("towers." + TowerType.MINIGUN.name() + ".weaponHeat");
            this.d = Game.i.assetManager.getQuad("towers." + TowerType.MINIGUN.name() + ".weaponHeatHeavy");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.PURPLE.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_ACCELERATION) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_MINIGUN_U_ACCELERATION", StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.TOWER_MINIGUN_BULLET_SPREAD), 1, true).toString());
            }
            if (towerStatType == TowerStatType.U_MAGAZINE_SIZE) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_MINIGUN_U_MAGAZINE_SIZE", StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.TOWER_MINIGUN_RELOAD_DURATION), 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_WEAPONS_DAMAGE), 2, true).toString();
            abilityConfigs[0].descriptionArgs[1] = "+" + StringFormatter.compactNumberWithPrecisionTrimZeros((((MinigunTower) tower).a() * 100.0f) - 100.0f, 2, true).toString();
            abilityConfigs[0].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(2.0d, 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_ACCELERATION), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_SPEED), 2, true).toString();
            abilityConfigs[1].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_HEAVY_MECH_MAGAZINE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MINIGUN_A_FOUNDATION_ROTATION), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_FOUNDATION_SPECIAL_BONUS), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_HOT_DURATION), 2, true).toString();
            float floatValue = gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_HOT_DAMAGE);
            if (tower.isAbilityInstalled(2)) {
                floatValue += gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_FOUNDATION_SPECIAL_BONUS);
            }
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(floatValue, 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_BUILD_DELAY), 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_ATTACK_SPEED), 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_RANGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[3] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_MINIGUN_A_MICROGUN_COUNT), false).toString();
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
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 4;
                case ATTACK_SPEED:
                    return 5;
                case DAMAGE:
                    return 4;
                case CROWD_DAMAGE:
                    return 1;
                case AGILITY:
                    return 2;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 54;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return MinigunTower.ABILITY_ALIASES;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public MinigunTower create() {
            return new MinigunTower((byte) 0);
        }
    }
}

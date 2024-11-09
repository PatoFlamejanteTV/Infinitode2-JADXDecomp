package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusXpBuff;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.units.DisorientedUnit;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.Intersector;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/CrusherTower.class */
public final class CrusherTower extends Tower {
    public static final String[] ABILITY_ALIASES;
    private static final float e;
    private static final float f;

    @NAGS
    private float g;

    @NAGS
    private float h;
    private int i;
    private Array<Hook> j;

    /* synthetic */ CrusherTower(byte b2) {
        this();
    }

    static {
        TLog.forClass(CrusherTower.class);
        ABILITY_ALIASES = new String[]{"HEAVY_VICE", "INCREASED_CAPACITY", "CAREFUL_PROCESSING"};
        e = new Color(1.0f, 1.0f, 1.0f, 0.0f).toFloatBits();
        f = new Color(1.0f, 1.0f, 1.0f, 1.0f).toFloatBits();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.i, true);
        kryo.writeObject(output, this.j);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.i = input.readVarInt(true);
        this.j = (Array) kryo.readObject(input, Array.class);
    }

    private CrusherTower() {
        super(TowerType.CRUSHER);
        this.j = new Array<>(Hook.class);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.CRUSHER.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.CRUSHER.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final void applyDrawInterpolation(float f2) {
        float f3;
        super.applyDrawInterpolation(f2);
        if (f2 != 0.0f) {
            float stat = getStat(TowerStatType.PROJECTILE_SPEED) * 128.0f;
            for (int i = 0; i < this.j.size; i++) {
                Hook hook = this.j.items[i];
                Enemy target = hook.getTarget();
                if (hook.status == 1) {
                    if (target != null) {
                        Vector2 vector2 = new Vector2(target.getPosition());
                        vector2.sub(hook.pos);
                        vector2.nor();
                        vector2.scl(stat * f2);
                        hook.drawPos.set(hook.pos).add(vector2);
                    }
                } else if (hook.status == 2) {
                    if (target == null) {
                        f3 = 256.0f * f2;
                    } else {
                        f3 = 192.0f * f2;
                    }
                    Vector2 vector22 = new Vector2(hook.pos);
                    vector22.sub(hook.basePos);
                    vector22.nor();
                    vector22.scl(f3);
                    hook.drawPos.set(hook.pos).sub(vector22);
                }
            }
            return;
        }
        for (int i2 = 0; i2 < this.j.size; i2++) {
            Hook hook2 = this.j.items[i2];
            hook2.drawPos.set(hook2.pos);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawWeapon(Batch batch, float f2, float f3, float f4, float f5) {
        Quad quad;
        Quad quad2;
        Enemy target;
        float f6 = this.h - this.g;
        float abs = StrictMath.abs(f6);
        float f7 = f6 < 0.0f ? f5 * 2.0f : f5 * 0.5f;
        float f8 = f7;
        if (f7 >= abs) {
            this.g = this.h;
            if (this.g == 0.0f) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= this.j.size) {
                        break;
                    }
                    if (this.j.items[i].status != 3) {
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.h = 1.0f;
                }
            } else {
                this.h = 0.0f;
            }
        } else {
            this.g += f6 * (f8 / abs);
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= this.j.size) {
                    break;
                }
                if (this.j.items[i2].status != 2 || PMath.getSquareDistanceBetweenPoints(this.j.items[i2].drawPos.x, this.j.items[i2].drawPos.y, this.j.items[i2].basePos.x, this.j.items[i2].basePos.y) >= 4096.0f) {
                    i2++;
                } else {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                this.h = 0.0f;
            }
        }
        if (getTile().visibleOnScreen && !isOutOfOrder()) {
            for (int i3 = 0; i3 < this.j.size; i3++) {
                Hook hook = this.j.items[i3];
                float distanceBetweenPoints = PMath.getDistanceBetweenPoints(hook.basePos.x, hook.basePos.y, hook.pos.x, hook.pos.y) / 64.0f;
                float f9 = distanceBetweenPoints;
                if (distanceBetweenPoints > 1.0f) {
                    f9 = 1.0f;
                }
                if (hook.status != 2) {
                    if (hook.status == 1) {
                        DrawUtils.texturedLineC(batch, Game.i.towerManager.F.CRUSHER.f, hook.basePos.x, hook.basePos.y, hook.drawPos.x, hook.drawPos.y, 8.0f * f9, e, f);
                        batch.draw(Game.i.towerManager.F.CRUSHER.e, hook.drawPos.x - (23.0f * f9), hook.drawPos.y - (22.0f * f9), 23.0f * f9, 22.0f * f9, 46.0f * f9, 44.0f * f9, 1.0f, 1.0f, PMath.getAngleBetweenPoints(hook.basePos, hook.drawPos));
                    }
                } else {
                    float f10 = 1.0f;
                    if (hook.enemyStartDistance != 0.0f) {
                        f10 = ((PMath.getDistanceBetweenPoints(hook.drawPos, hook.basePos) / hook.enemyStartDistance) * 0.4f) + 0.6f;
                    }
                    float f11 = f10 * f9;
                    DrawUtils.texturedLineC(batch, Game.i.towerManager.F.CRUSHER.f, hook.basePos.x, hook.basePos.y, hook.drawPos.x, hook.drawPos.y, 8.0f * f9, e, f);
                    batch.draw(Game.i.towerManager.F.CRUSHER.e, hook.drawPos.x - (23.0f * f11), hook.drawPos.y - (22.0f * f11), 23.0f * f11, 22.0f * f11, 46.0f * f11, 44.0f * f11, 1.0f, 1.0f, PMath.getAngleBetweenPoints(hook.basePos, hook.drawPos));
                }
            }
            for (int i4 = 0; i4 < this.j.size; i4++) {
                Hook hook2 = this.j.items[i4];
                if (hook2.status == 3 && (target = hook2.getTarget()) != null) {
                    TextureRegion texture = target.getTexture();
                    float regionWidth = texture.getRegionWidth() * (24.0f / (target.getSize() * 2.0f));
                    batch.draw(texture, hook2.basePos.x - (regionWidth * 0.5f), hook2.basePos.y - (regionWidth * 0.5f), regionWidth * 0.5f, regionWidth * 0.5f, regionWidth, regionWidth, 1.0f + (this.g * 0.3f), 1.0f - (this.g * 0.6f), 0.0f);
                }
            }
            float f12 = this.g * 8.0f;
            if (isAbilityInstalled(0)) {
                quad = Game.i.towerManager.F.CRUSHER.d[0];
                quad2 = Game.i.towerManager.F.CRUSHER.d[1];
            } else {
                quad = Game.i.towerManager.F.CRUSHER.c[0];
                quad2 = Game.i.towerManager.F.CRUSHER.c[1];
            }
            quad.draw(batch, getTile().boundingBox.minX, getTile().boundingBox.minY - f12, 128.0f, 128.0f);
            quad2.draw(batch, getTile().boundingBox.minX, getTile().boundingBox.minY + f12, 128.0f, 128.0f);
            for (int i5 = 0; i5 < this.j.size; i5++) {
                Hook hook3 = this.j.items[i5];
                Enemy target2 = hook3.getTarget();
                if (hook3.status == 2 && target2 != null) {
                    float f13 = 1.0f;
                    if (hook3.enemyStartDistance != 0.0f) {
                        f13 = PMath.getDistanceBetweenPoints(hook3.drawPos, hook3.basePos) / hook3.enemyStartDistance;
                    }
                    TextureRegion texture2 = target2.getTexture();
                    float size = 24.0f / (target2.getSize() * 2.0f);
                    float regionWidth2 = texture2.getRegionWidth() * (((1.0f - size) * f13) + size);
                    batch.draw(texture2, hook3.drawPos.x - (regionWidth2 * 0.5f), hook3.drawPos.y - (regionWidth2 * 0.5f), regionWidth2 * 0.5f, regionWidth2 * 0.5f, regionWidth2, regionWidth2, 1.0f, 1.0f, target2.drawAngle * f13);
                    float packedColor = batch.getPackedColor();
                    target2.healthBarInvisible = true;
                    target2.drawPosition.set(hook3.drawPos.x, hook3.drawPos.y);
                    target2.drawHealth(batch);
                    batch.setPackedColor(packedColor);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatchAdditive(Batch batch, float f2) {
        super.drawBatchAdditive(batch, f2);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            BitmapFont smallDebugFont = Game.i.assetManager.getSmallDebugFont();
            for (int i = 0; i < this.j.size; i++) {
                Hook hook = this.j.items[i];
                smallDebugFont.draw(batch, new StringBuilder().append(hook.status).toString(), hook.drawPos.x, hook.drawPos.y);
                if (hook.f3198a != null && hook.f3198a.enemy != null) {
                    DrawUtils.texturedLineB(batch, AssetManager.TextureRegions.i().blank, hook.drawPos.x, hook.drawPos.y, hook.f3198a.enemy.getPosition().x, hook.f3198a.enemy.getPosition().y, 1.0f);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttack() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_BONUS_EXPERIENCE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_CAREFUL_PROCESSING));
        }
        if (towerStatType == TowerStatType.U_SHARED_DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_HEAVY_VICE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        this.i = isAbilityInstalled(1) ? this.S.gameValue.getIntValue(GameValueType.TOWER_CRUSHER_A_INCREASED_CAPACITY) : 2;
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f2) {
        Enemy target;
        for (int i = 0; i < this.j.size; i++) {
            Hook hook = this.j.items[i];
            if (hook.status == 3 && (target = hook.getTarget()) != null) {
                target.drawPosition.set(hook.basePos.x, hook.basePos.y);
                target.healthBarInvisible = true;
                target.drawHealth(batch);
            }
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        super.drawBatch(batch, f2);
    }

    private void a() {
        for (int i = 0; i < this.j.size; i++) {
            Hook hook = this.j.items[i];
            if ((hook.status == 2 || hook.status == 3) && hook.getTarget() != null) {
                Enemy target = hook.getTarget();
                target.disabled.removeReason("CrusherTower");
                target.invisible.removeReason("CrusherTower");
                target.healthBarInvisible = false;
                target.setPositionToPath();
                target.chasedByCrusher = false;
                hook.status = 0;
                hook.f3198a = Enemy.EnemyReference.NULL;
                hook.recruiting = false;
            }
            if (hook.status == 1 && hook.getTarget() != null) {
                hook.getTarget().chasedByCrusher = false;
                hook.status = 0;
                hook.f3198a = Enemy.EnemyReference.NULL;
                hook.recruiting = false;
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void onPreSell() {
        a();
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f2) {
        float f3;
        float f4;
        if (this.j.size != this.i) {
            if (this.j.size > this.i) {
                a();
                this.j.clear();
            }
            if (this.j.size < this.i) {
                while (this.j.size < this.i) {
                    Hook hook = new Hook();
                    hook.basePos.set(getTile().center);
                    hook.pos.set(getTile().center);
                    this.j.add(hook);
                }
                for (int i = 0; i < this.j.size; i++) {
                    if (this.j.size == 1) {
                        f4 = 0.5f;
                    } else {
                        f4 = i / (this.j.size - 1.0f);
                    }
                    this.j.items[i].basePos.set((getTile().center.x - 64.0f) + 56.0f + (16.0f * f4), getTile().center.y + (-0.5f) + f4);
                    if (this.j.items[i].status == 0) {
                        this.j.items[i].pos.set(this.j.items[i].basePos);
                    }
                }
            }
        }
        if (!isOutOfOrder()) {
            float stat = getStat(TowerStatType.PROJECTILE_SPEED) * 128.0f;
            int i2 = 0;
            for (int i3 = 0; i3 < this.j.size; i3++) {
                if (this.j.items[i3].status == 3) {
                    i2++;
                }
            }
            for (int i4 = 0; i4 < this.j.size; i4++) {
                Hook hook2 = this.j.items[i4];
                if ((hook2.status == 0 || (hook2.status == 2 && hook2.getTarget() == null)) && !hook2.missedTarget) {
                    hook2.framesSinceEnemySearch = (byte) (hook2.framesSinceEnemySearch + 1);
                    if (hook2.framesSinceEnemySearch == 3) {
                        hook2.framesSinceEnemySearch = (byte) 0;
                        Enemy enemy = null;
                        if (!this.attackDisabled) {
                            Vector2 vector2 = hook2.basePos;
                            enemy = findTargetFiltered(enemy2 -> {
                                if (enemy2.chasedByCrusher) {
                                    return false;
                                }
                                return (enemy2.caughtByCrushersSet == null || !enemy2.caughtByCrushersSet.contains(this.id)) && vector2.dst2(enemy2.getPosition()) < this.rangeInPixels * this.rangeInPixels;
                            });
                        }
                        if (enemy != null) {
                            hook2.status = 1;
                            hook2.f3198a = this.S.enemy.getReference(enemy);
                            hook2.chewingTime = 0.0f;
                            hook2.startingHealth = 0.0f;
                            enemy.chasedByCrusher = true;
                        }
                    }
                }
                if (hook2.status == 1 && hook2.getTarget() == null) {
                    hook2.status = 2;
                }
                if (hook2.status != 1) {
                    if (hook2.status != 2) {
                        if (hook2.status == 3) {
                            Enemy target = hook2.getTarget();
                            if (target == null) {
                                hook2.status = 0;
                                hook2.recruiting = false;
                            } else {
                                float stat2 = getStat(TowerStatType.DAMAGE) * f2;
                                float stat3 = stat2 + (stat2 * ((getStat(TowerStatType.U_SHARED_DAMAGE) * 0.01f) / Math.max(1, i2)));
                                if (stat3 > 0.0f) {
                                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(target, stat3, DamageType.BULLET).setTower(this));
                                }
                                hook2.chewingTime += f2;
                                boolean z = false;
                                if (hook2.chewingTime >= getStat(TowerStatType.DURATION)) {
                                    if (hook2.recruiting) {
                                        float health = (hook2.startingHealth - target.getHealth()) / (target.maxHealth * 0.33f);
                                        if (target.canNotBeDisoriented) {
                                            health = 0.0f;
                                        }
                                        if (this.S.gameState.randomFloat() < health) {
                                            z = true;
                                        }
                                    }
                                    if (!z) {
                                        target.disabled.removeReason("CrusherTower");
                                        target.invisible.removeReason("CrusherTower");
                                        target.healthBarInvisible = false;
                                        target.chasedByCrusher = false;
                                        target.setPositionToPath();
                                        hook2.status = 0;
                                        hook2.f3198a = Enemy.EnemyReference.NULL;
                                        hook2.recruiting = false;
                                    }
                                } else if (hook2.recruiting && hook2.startingHealth - target.getHealth() > target.maxHealth * 0.33f) {
                                    z = true;
                                }
                                if (z) {
                                    DisorientedUnit create = Game.i.unitManager.F.DISORIENTED.create();
                                    float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_ULTIMATE_UNIT_HP);
                                    create.setup(this, target.type, target.getHealth() * percentValueAsMultiplier, target.maxHealth * percentValueAsMultiplier);
                                    if (this.S.unit.preparePathToRandomSpawn(create, this.S.map.getMap().getTileByMapPos(target.getPosition().x, target.getPosition().y))) {
                                        this.S.unit.register(create);
                                        this.S.map.spawnUnit(create);
                                        if (isAbilityInstalled(4)) {
                                            if (this.bountyModifiersNearby != 0) {
                                                create.coinsPerTilePassed = 0.0f;
                                                create.maxSumCoins = 0;
                                            } else {
                                                create.setCoinsPerTilePassed(getDisorientedUnitCoinPerTile(), (int) (target.bounty * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_ULTIMATE_COINS_LIMIT)));
                                            }
                                        }
                                        if (target.type == EnemyType.METAPHOR_BOSS_CREEP) {
                                            this.S.achievement.registerDelta(AchievementType.RECRUIT_SPIDER, 1);
                                        }
                                    }
                                    this.S.damage.queueEnemyKill(this.S.damage.obtainRecord().setup(target, 1.0f, DamageType.BULLET).setTower(this));
                                    hook2.status = 0;
                                    hook2.f3198a = Enemy.EnemyReference.NULL;
                                    hook2.recruiting = false;
                                }
                            }
                        }
                    } else {
                        if (hook2.getTarget() == null) {
                            f3 = 256.0f * f2;
                        } else {
                            f3 = 192.0f * f2;
                        }
                        float f5 = f3;
                        if (PMath.getSquareDistanceBetweenPoints(hook2.pos.x, hook2.pos.y, hook2.basePos.x, hook2.basePos.y) < f5 * f5) {
                            hook2.pos.set(hook2.basePos);
                            hook2.missedTarget = false;
                            Enemy target2 = hook2.getTarget();
                            if (target2 == null) {
                                hook2.status = 0;
                                hook2.recruiting = false;
                            } else {
                                boolean z2 = false;
                                if (isAbilityInstalled(3)) {
                                    float percentValueAsMultiplier2 = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_DISORIENTATION_CHANCE_MAX);
                                    float percentValueAsMultiplier3 = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_DISORIENTATION_CHANCE_MAX);
                                    if (this.S.gameState.randomFloat() < percentValueAsMultiplier3 + ((percentValueAsMultiplier2 - percentValueAsMultiplier3) * (getUpgradeLevel() / 10.0f))) {
                                        z2 = true;
                                    }
                                }
                                target2.setPosition(hook2.basePos);
                                target2.drawPosition.set(hook2.basePos);
                                hook2.status = 3;
                                hook2.chewingTime = 0.0f;
                                hook2.startingHealth = target2.getHealth();
                                hook2.recruiting = z2;
                                BonusXpBuff bonusXpBuff = new BonusXpBuff();
                                bonusXpBuff.setup(getStat(TowerStatType.DURATION), getStat(TowerStatType.DURATION) * 10.0f, getStat(TowerStatType.U_BONUS_EXPERIENCE) * 0.01f, this);
                                this.S.buff.P_BONUS_XP.addBuff(target2, bonusXpBuff);
                            }
                        } else {
                            Vector2 vector22 = new Vector2(hook2.pos);
                            vector22.sub(hook2.basePos);
                            vector22.nor();
                            vector22.scl(f3);
                            hook2.pos.sub(vector22);
                        }
                        Enemy target3 = hook2.getTarget();
                        if (target3 != null) {
                            if (this.S.map.getMap().getTileByMapPos(hook2.pos.x, hook2.pos.y) != null) {
                                target3.setPosition(hook2.pos);
                                target3.drawPosition.set(hook2.pos);
                            }
                        } else {
                            hook2.recruiting = false;
                        }
                    }
                } else {
                    Enemy target4 = hook2.getTarget();
                    Vector2 vector23 = new Vector2(target4.getPosition());
                    vector23.sub(hook2.pos);
                    vector23.nor();
                    vector23.scl(stat * f2);
                    float f6 = hook2.pos.x;
                    float f7 = hook2.pos.y;
                    hook2.pos.add(vector23);
                    if (hook2.basePos.dst2(target4.getPosition()) > this.rangeInPixels * this.rangeInPixels) {
                        target4.chasedByCrusher = false;
                        hook2.status = 2;
                        hook2.f3198a = Enemy.EnemyReference.NULL;
                    } else {
                        Vector2 position = target4.getPosition();
                        if (Intersector.intersectSegmentCircle(hook2.pos.x, hook2.pos.y, f6, f7, position.x, position.y, 40.960003f) && (target4.caughtByCrushersSet == null || !target4.caughtByCrushersSet.contains(this.id))) {
                            if (this.S.gameState.randomFloat() <= StrictMath.pow(0.699999988079071d, target4.totalCatchesByCrushers)) {
                                hook2.status = 2;
                                hook2.enemyStartPos.set(target4.drawPosition);
                                hook2.enemyStartDistance = PMath.getDistanceBetweenPoints(hook2.basePos, target4.drawPosition);
                                target4.invisible.addReason("CrusherTower");
                                target4.disabled.addReason("CrusherTower");
                                if (target4.caughtByCrushersSet == null) {
                                    target4.caughtByCrushersSet = new IntSet();
                                }
                                target4.caughtByCrushersSet.add(this.id);
                                target4.totalCatchesByCrushers++;
                            } else {
                                target4.chasedByCrusher = false;
                                hook2.status = 2;
                                hook2.missedTarget = true;
                                hook2.f3198a = Enemy.EnemyReference.NULL;
                            }
                        }
                    }
                }
            }
        } else {
            a();
        }
        super.update(f2);
    }

    public final float getDisorientedUnitCoinPerTile() {
        return this.S.gameValue.getFloatValue(GameValueType.TOWER_CRUSHER_A_ULTIMATE_COINS);
    }

    public final float getDisorientationChance() {
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_CRUSHER_A_DISORIENTATION_CHANCE_MIN);
        return floatValue + ((this.S.gameValue.getFloatValue(GameValueType.TOWER_CRUSHER_A_DISORIENTATION_CHANCE_MAX) - floatValue) * (getUpgradeLevel() / 10.0f));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/CrusherTower$CrusherTowerFactory.class */
    public static class CrusherTowerFactory extends Tower.Factory<CrusherTower> {
        Quad[] c;
        Quad[] d;
        TextureRegion e;
        TextureRegion f;

        public CrusherTowerFactory() {
            super("tower-crusher", TowerType.CRUSHER);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new String[]{""};
            this.f1784b[3].descriptionArgs = new String[]{""};
            this.f1784b[4].descriptionArgs = new String[]{"", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_BONUS_EXPERIENCE) {
                return Game.i.localeManager.i18n.get("tower_stat_more_info_CRUSHER_U_BONUS_EXPERIENCE");
            }
            if (towerStatType == TowerStatType.U_SHARED_DAMAGE) {
                return Game.i.localeManager.i18n.get("tower_stat_more_info_CRUSHER_U_SHARED_DAMAGE");
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_HEAVY_VICE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_CRUSHER_A_INCREASED_CAPACITY), 0, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CRUSHER_A_CAREFUL_PROCESSING), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(((CrusherTower) tower).getDisorientationChance(), 1, true).toString();
            int intValue = gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_CRUSHER_A_ULTIMATE_COINS_LIMIT);
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(((CrusherTower) tower).getDisorientedUnitCoinPerTile(), 1, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(intValue, 1, true).toString();
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
        public void setupAssets() {
            this.e = Game.i.assetManager.getTextureRegion("tower-crusher-hook");
            this.f = Game.i.assetManager.getTextureRegion("crusher-chain");
            this.c = new Quad[]{Game.i.assetManager.getQuad("towers." + TowerType.CRUSHER.name() + ".vices.regular.top"), Game.i.assetManager.getQuad("towers." + TowerType.CRUSHER.name() + ".vices.regular.bottom")};
            this.d = new Quad[]{Game.i.assetManager.getQuad("towers." + TowerType.CRUSHER.name() + ".vices.heavy.top"), Game.i.assetManager.getQuad("towers." + TowerType.CRUSHER.name() + ".vices.heavy.bottom")};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider) {
            int buildPrice = super.getBuildPrice(gameSystemProvider);
            int i = 0;
            for (int i2 = 0; i2 < gameSystemProvider.tower.towers.size; i2++) {
                if (gameSystemProvider.tower.towers.items[i2].type == TowerType.CRUSHER) {
                    i++;
                }
            }
            return (int) (buildPrice * (1.0f + ((float) StrictMath.pow(i, 1.600000023841858d))));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.BROWN.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 1;
                case ATTACK_SPEED:
                    return 1;
                case DAMAGE:
                    return 5;
                case CROWD_DAMAGE:
                    return 1;
                case AGILITY:
                    return 1;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return CrusherTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 49;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public CrusherTower create() {
            return new CrusherTower((byte) 0);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/CrusherTower$Hook.class */
    public static class Hook implements KryoSerializable {
        public boolean missedTarget;
        public float startingHealth;
        public float chewingTime;

        @FrameAccumulatorForPerformance
        public byte framesSinceEnemySearch;
        public boolean recruiting;

        @NAGS
        public float enemyStartDistance;

        /* renamed from: a, reason: collision with root package name */
        private Enemy.EnemyReference f3198a = Enemy.EnemyReference.NULL;
        public Vector2 basePos = new Vector2();
        public Vector2 pos = new Vector2();
        public int status = 0;

        @NAGS
        public Vector2 drawPos = new Vector2();

        @NAGS
        public Vector2 enemyStartPos = new Vector2();

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f3198a);
            kryo.writeObject(output, this.basePos);
            kryo.writeObject(output, this.pos);
            output.writeByte(this.status);
            output.writeBoolean(this.missedTarget);
            output.writeFloat(this.startingHealth);
            output.writeFloat(this.chewingTime);
            output.writeByte(this.framesSinceEnemySearch);
            output.writeBoolean(this.recruiting);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f3198a = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
            this.basePos = (Vector2) kryo.readObject(input, Vector2.class);
            this.pos = (Vector2) kryo.readObject(input, Vector2.class);
            this.status = input.readByte();
            this.missedTarget = input.readBoolean();
            this.startingHealth = input.readFloat();
            this.chewingTime = input.readFloat();
            this.framesSinceEnemySearch = input.readByte();
            this.recruiting = input.readBoolean();
        }

        public Enemy getTarget() {
            return this.f3198a.enemy;
        }
    }
}

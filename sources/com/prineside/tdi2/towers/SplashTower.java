package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.projectiles.SplashProjectile;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SplashTower.class */
public final class SplashTower extends Tower {
    public static final int ABILITY_PENETRATING_BULLETS = 0;
    public static final int ABILITY_FAST_MECHANISM = 1;
    public static final int ABILITY_FAST_BULLETS = 2;
    private boolean f;
    private float g;
    private int h;
    private float i;
    private int j;
    private float k;
    private float l;

    @FrameAccumulatorForPerformance
    private byte m;
    private float n;
    private static final float e = new Color(-7707137).toFloatBits();
    public static final String[] ABILITY_ALIASES = {"PENETRATING_BULLETS", "FAST_MECHANISM", "FAST_BULLETS"};

    /* synthetic */ SplashTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.f);
        output.writeFloat(this.g);
        output.writeVarInt(this.h, true);
        output.writeFloat(this.i);
        output.writeVarInt(this.j, true);
        output.writeFloat(this.k);
        output.writeFloat(this.l);
        output.writeByte(this.m);
        output.writeFloat(this.n);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = input.readBoolean();
        this.g = input.readFloat();
        this.h = input.readVarInt(true);
        this.i = input.readFloat();
        this.j = input.readVarInt(true);
        this.k = input.readFloat();
        this.l = input.readFloat();
        this.m = input.readByte();
        this.n = input.readFloat();
    }

    private SplashTower() {
        super(TowerType.SPLASH);
        this.f = false;
        this.h = 0;
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        return Game.i.towerManager.F.SPLASH.getWeaponTexture();
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
    public final float getAttackDelay() {
        return this.n;
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_DAMAGE));
        }
        if (towerStatType == TowerStatType.ATTACK_SPEED && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_MECHANISM_SPEED));
        }
        if (towerStatType == TowerStatType.PROJECTILE_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_SPEED));
        }
        if (towerStatType == TowerStatType.U_PIERCING && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_PENETRATING_DAMAGE_CHAIN));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        this.i = getStat(TowerStatType.U_PROJECTILE_COUNT);
        this.n = (1.0f / getStat(TowerStatType.ATTACK_SPEED)) / this.i;
        this.j = (int) this.i;
        if (isAbilityInstalled(0)) {
            this.k = 26.0f;
            this.l = 6.0f;
        } else {
            this.k = 22.0f;
            this.l = 8.0f;
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawWeapon(Batch batch, float f, float f2, float f3, float f4) {
        super.drawWeapon(batch, f, f2, f3, f4);
        if (getTile().visibleOnScreen && !isOutOfOrder() && batch.getColor().f889a == 1.0f) {
            Vector2 vector2 = new Vector2();
            float f5 = f3 / 128.0f;
            float f6 = 360.0f / this.j;
            for (int i = 0; i < this.j; i++) {
                PMath.getPointByAngleFromPoint(f + (f3 * 0.5f), f2 + (f3 * 0.5f), (i * f6) + this.angle, this.k * f5, vector2);
                float f7 = vector2.x;
                float f8 = vector2.y;
                float f9 = this.l * f5;
                float f10 = e;
                DrawUtils.texturedLineC(batch, Game.i.towerManager.F.SPLASH.c, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f7, f8, f9, f10, f10);
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
            debugFont.draw(batch, "TSS: " + this.g, getTile().boundingBox.minX, (getTile().boundingBox.minY - 20.0f) + 64.0f, 128.0f, 1, false);
            debugFont.setColor(Color.WHITE);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        this.m = (byte) (this.m + 1);
        if (this.m == 3) {
            this.m = (byte) 0;
            this.f = false;
            this.S.map.getEnemiesInCircleV(getTile().center, this.rangeInPixels, (enemyReference, f2, f3, f4) -> {
                if (enemyReference.enemy != null && canAttackEnemy(enemyReference.enemy)) {
                    this.f = true;
                    return false;
                }
                return true;
            });
        }
        this.g += f;
        if (this.f) {
            this.angle += 45.0f * f;
            Vector2 vector2 = new Vector2();
            Vector2 vector22 = new Vector2();
            float stat = getStat(TowerStatType.DAMAGE);
            float stat2 = getStat(TowerStatType.PROJECTILE_SPEED);
            while (this.g > this.n) {
                this.h++;
                if (this.h >= this.j) {
                    this.h = 0;
                }
                float f5 = (this.h * (360.0f / this.j)) + this.angle;
                vector2.set(getTile().center);
                PMath.shiftPointByAngle(vector2, f5, this.k + 1.0f);
                vector22.set(getTile().center);
                PMath.shiftPointByAngle(vector22, f5, this.rangeInPixels);
                SplashProjectile obtain = this.S.projectile.F.SPLASH.obtain();
                this.S.projectile.register(obtain);
                obtain.setup(this, stat, vector2, vector22, stat2);
                obtain.chainKillGeneration = 0;
                if (this.S._particle != null) {
                    this.S._particle.addFlashParticle(AssetManager.TextureRegions.i().muzzleFlashSmall, vector2.x, vector2.y, 9.6f, 3.6000001f, 19.2f, 28.800001f, f5);
                }
                this.shotCount++;
                this.g -= this.n;
                if (this.S._sound != null) {
                    this.S._sound.playShotSound(StaticSoundType.SHOT_SPLASH, this);
                }
            }
        } else {
            this.g = getAttackDelay() / this.i;
        }
        super.update(f);
    }

    public static void triggerChainReaction(GameSystemProvider gameSystemProvider, SplashTower splashTower, SplashProjectile splashProjectile) {
        if (splashTower.isAbilityInstalled(4) && splashProjectile.chainKillGeneration < gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_MAX_CHAIN_LENGTH)) {
            Vector2 vector2 = new Vector2();
            Vector2 vector22 = new Vector2();
            Vector2 vector23 = new Vector2();
            int intValue = gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_SPLINTERS);
            float f = 1.0f;
            if (splashProjectile.chainKillGeneration == 0) {
                f = (float) (1.0d * gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_ULTIMATE_BASE_DAMAGE));
            }
            float damage = splashProjectile.getDamage() * f;
            if (damage > 0.1f) {
                for (int i = 0; i < intValue; i++) {
                    SplashProjectile obtain = gameSystemProvider.projectile.F.SPLASH.obtain();
                    gameSystemProvider.projectile.register(obtain);
                    float randomFloat = gameSystemProvider.gameState.randomFloat() * 6.2831855f;
                    vector23.set(PMath.cos(randomFloat), PMath.sin(randomFloat));
                    vector23.scl(51.2f);
                    vector2.set(vector23).add(splashProjectile.position.x, splashProjectile.position.y);
                    vector23.scl(13.0f);
                    vector22.set(vector23).add(splashProjectile.position.x, splashProjectile.position.y);
                    obtain.setup(splashTower, damage, vector2, vector22, 2.0f);
                    obtain.chainKillGeneration = splashProjectile.chainKillGeneration + 1;
                    obtain.hitCount = (byte) (splashProjectile.hitCount + 1);
                }
            }
            gameSystemProvider.achievement.setProgress(AchievementType.SPLASH_CHAIN_KILL, splashProjectile.chainKillGeneration + 1);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SplashTower$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<GameSystemProvider> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(GameSystemProvider gameSystemProvider) {
            this.f1759a = gameSystemProvider;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Tower tower = lastDamage.getTower();
            if (tower instanceof SplashTower) {
                Projectile projectile = lastDamage.getProjectile();
                if (projectile instanceof SplashProjectile) {
                    SplashTower.triggerChainReaction((GameSystemProvider) this.f1759a, (SplashTower) tower, (SplashProjectile) projectile);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SplashTower$SplashTowerFactory.class */
    public static class SplashTowerFactory extends Tower.Factory<SplashTower> {
        TextureRegion c;

        public SplashTowerFactory() {
            super("tower-splash", TowerType.SPLASH);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"0.9", "50"};
            this.f1784b[1].descriptionArgs = new String[]{"1.25"};
            this.f1784b[2].descriptionArgs = new String[]{"1.25", "1.25", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{"1.1", "0.8"};
            this.f1784b[4].descriptionArgs = new String[]{"5", "", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void configureSystems(GameSystemProvider gameSystemProvider) {
            gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(gameSystemProvider));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion(AssetManager.BLANK_REGION_NAME);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_PIERCING) {
                return Game.i.localeManager.i18n.get("tower_stat_more_info_SPLASH_U_PIERCING");
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_PENETRATING_DAMAGE_CHAIN), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_MECHANISM_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_BONUS_XP), 2, true).toString();
            abilityConfigs[2].descriptionArgs[3] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_BONUS_XP_DURATION), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_RIFFLED_DAMAGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_RIFFLED_SPEED_MARK), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_ON_HIT_CHANCE), false).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_SPLINTERS), false).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_BASE_DAMAGE), false).toString();
            abilityConfigs[4].descriptionArgs[3] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_SPLASH_A_ULTIMATE_MAX_CHAIN_LENGTH), false).toString();
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
        public int getBuildHotKey() {
            return 47;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.DEEP_ORANGE.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 4;
                case ATTACK_SPEED:
                    return 5;
                case DAMAGE:
                    return 3;
                case CROWD_DAMAGE:
                    return 3;
                case AGILITY:
                    return 2;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return SplashTower.ABILITY_ALIASES;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public SplashTower create() {
            return new SplashTower((byte) 0);
        }
    }
}

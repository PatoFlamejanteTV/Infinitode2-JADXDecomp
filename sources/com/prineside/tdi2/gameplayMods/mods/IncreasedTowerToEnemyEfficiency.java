package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.JsonHandler;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.QuadRegion;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;
import java.util.Locale;
import net.bytebuddy.utility.JavaConstant;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/IncreasedTowerToEnemyEfficiency.class */
public final class IncreasedTowerToEnemyEfficiency extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2138a = TLog.forClass(IncreasedTowerToEnemyEfficiency.class);
    public static final ObjectPair<TowerType, EnemyType>[] DEFAULT_PAIRS = {new ObjectPair<>(TowerType.BASIC, EnemyType.ICY), new ObjectPair<>(TowerType.BASIC, EnemyType.HEALER), new ObjectPair<>(TowerType.BASIC, EnemyType.ARMORED), new ObjectPair<>(TowerType.BASIC, EnemyType.FIGHTER), new ObjectPair<>(TowerType.SNIPER, EnemyType.FAST), new ObjectPair<>(TowerType.SNIPER, EnemyType.TOXIC), new ObjectPair<>(TowerType.SNIPER, EnemyType.HELI), new ObjectPair<>(TowerType.CANNON, EnemyType.STRONG), new ObjectPair<>(TowerType.CANNON, EnemyType.TOXIC), new ObjectPair<>(TowerType.CANNON, EnemyType.ARMORED), new ObjectPair<>(TowerType.CANNON, EnemyType.HELI), new ObjectPair<>(TowerType.FREEZING, EnemyType.ARMORED), new ObjectPair<>(TowerType.FREEZING, EnemyType.ICY), new ObjectPair<>(TowerType.FREEZING, EnemyType.FAST), new ObjectPair<>(TowerType.VENOM, EnemyType.ICY), new ObjectPair<>(TowerType.VENOM, EnemyType.FAST), new ObjectPair<>(TowerType.VENOM, EnemyType.TOXIC), new ObjectPair<>(TowerType.SPLASH, EnemyType.REGULAR), new ObjectPair<>(TowerType.SPLASH, EnemyType.JET), new ObjectPair<>(TowerType.SPLASH, EnemyType.ARMORED), new ObjectPair<>(TowerType.BLAST, EnemyType.STRONG), new ObjectPair<>(TowerType.BLAST, EnemyType.LIGHT), new ObjectPair<>(TowerType.BLAST, EnemyType.ICY), new ObjectPair<>(TowerType.MULTISHOT, EnemyType.HEALER), new ObjectPair<>(TowerType.MULTISHOT, EnemyType.FIGHTER), new ObjectPair<>(TowerType.MULTISHOT, EnemyType.ARMORED), new ObjectPair<>(TowerType.MINIGUN, EnemyType.STRONG), new ObjectPair<>(TowerType.MINIGUN, EnemyType.BOSS), new ObjectPair<>(TowerType.MINIGUN, EnemyType.LIGHT), new ObjectPair<>(TowerType.AIR, EnemyType.ARMORED), new ObjectPair<>(TowerType.AIR, EnemyType.HEALER), new ObjectPair<>(TowerType.AIR, EnemyType.BOSS), new ObjectPair<>(TowerType.AIR, EnemyType.HELI), new ObjectPair<>(TowerType.TESLA, EnemyType.FAST), new ObjectPair<>(TowerType.TESLA, EnemyType.HELI), new ObjectPair<>(TowerType.TESLA, EnemyType.ARMORED), new ObjectPair<>(TowerType.MISSILE, EnemyType.REGULAR), new ObjectPair<>(TowerType.MISSILE, EnemyType.FAST), new ObjectPair<>(TowerType.MISSILE, EnemyType.TOXIC), new ObjectPair<>(TowerType.FLAMETHROWER, EnemyType.HEALER), new ObjectPair<>(TowerType.FLAMETHROWER, EnemyType.ARMORED), new ObjectPair<>(TowerType.FLAMETHROWER, EnemyType.FAST), new ObjectPair<>(TowerType.LASER, EnemyType.HEALER), new ObjectPair<>(TowerType.LASER, EnemyType.REGULAR), new ObjectPair<>(TowerType.LASER, EnemyType.ICY), new ObjectPair<>(TowerType.GAUSS, EnemyType.FAST), new ObjectPair<>(TowerType.GAUSS, EnemyType.JET), new ObjectPair<>(TowerType.GAUSS, EnemyType.TOXIC), new ObjectPair<>(TowerType.CRUSHER, EnemyType.HELI), new ObjectPair<>(TowerType.CRUSHER, EnemyType.JET), new ObjectPair<>(TowerType.CRUSHER, EnemyType.STRONG)};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private String f2139b;
    private float c;
    private float d;
    private TowerType e;
    private EnemyType f;

    @Null
    private GameSystemProvider g;
    private float h;

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.c);
        output.writeFloat(this.d);
        kryo.writeObject(output, this.e);
        kryo.writeObject(output, this.f);
        kryo.writeObjectOrNull(output, this.g, GameSystemProvider.class);
        output.writeFloat(this.h);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.c = input.readFloat();
        this.d = input.readFloat();
        this.e = (TowerType) kryo.readObject(input, TowerType.class);
        this.f = (EnemyType) kryo.readObject(input, EnemyType.class);
        this.g = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
        this.h = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public final String getId() {
        if (this.f2139b == null) {
            this.f2139b = getClass().getSimpleName() + JavaConstant.Dynamic.DEFAULT_NAME + this.e.name() + JavaConstant.Dynamic.DEFAULT_NAME + this.f;
        }
        return this.f2139b;
    }

    private IncreasedTowerToEnemyEfficiency() {
        this.c = 0.0f;
        this.d = 50.0f;
        this.e = TowerType.BASIC;
        this.f = EnemyType.REGULAR;
        this.maxPower = 3;
        this.multipleInstances = false;
    }

    public IncreasedTowerToEnemyEfficiency(TowerType towerType, EnemyType enemyType) {
        this.c = 0.0f;
        this.d = 50.0f;
        this.e = TowerType.BASIC;
        this.f = EnemyType.REGULAR;
        this.maxPower = 3;
        this.multipleInstances = false;
        Preconditions.checkNotNull(towerType, "towerType can not be null");
        Preconditions.checkNotNull(enemyType, "enemyType can not be null");
        this.e = towerType;
        this.f = enemyType;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        Quad quad = new Quad(64.0f, 64.0f);
        quad.addRegion(new QuadRegion((ResourcePack.AtlasTextureRegion) Game.i.towerManager.getFactory(this.e).getIconTexture(), -9.0f, -3.0f, 82.0f, 82.0f, new Color(0.0f, 0.0f, 0.0f, 0.56f)));
        quad.addRegion(new QuadRegion((ResourcePack.AtlasTextureRegion) Game.i.towerManager.getFactory(this.e).getIconTexture(), -6.0f, 0.0f, 76.0f, 76.0f));
        quad.addRegion(new QuadRegion((ResourcePack.AtlasTextureRegion) Game.i.enemyManager.getFactory(this.f).getTexture(), 30.0f, -2.0f, 36.0f, 36.0f, new Color(0.0f, 0.0f, 0.0f, 0.4f)));
        quad.addRegion(new QuadRegion((ResourcePack.AtlasTextureRegion) Game.i.enemyManager.getFactory(this.f).getTexture(), 32.0f, 0.0f, 32.0f, 32.0f));
        return quad;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        boolean z = Game.i.towerManager.canTowerAttackEnemy[this.f.ordinal()][this.e.ordinal()];
        String format = Game.i.localeManager.i18n.format("gmod_descr_increased_tower_to_enemy_efficiency", "<@tower-" + this.e.name().toLowerCase(Locale.US) + ">[#" + Game.i.towerManager.getFactory(this.e).getColor() + "]" + Game.i.towerManager.getTitle(this.e) + "[]", StringFormatter.compactNumberWithPrecisionTrimZeros(this.c + (getPower() * this.d), 1, true), "<@enemy-type-" + this.f.name().toLowerCase(Locale.US) + ">[#FFFFFF88](" + Game.i.enemyManager.getFactory(this.f).getTitle() + ")[]");
        if (!z) {
            format = format + SequenceUtils.SPACE + Game.i.localeManager.i18n.get("gmod_descr_increased_tower_to_enemy_efficiency_abble_to_attack_sfx");
        }
        return format;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.prineside.tdi2.gameplayMods.GameplayMod
    public final void setRegisteredPower(int i) {
        super.setRegisteredPower(i);
        a();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayMod cpy() {
        IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency = new IncreasedTowerToEnemyEfficiency();
        a((GenericGameplayMod) increasedTowerToEnemyEfficiency);
        increasedTowerToEnemyEfficiency.d = this.d;
        increasedTowerToEnemyEfficiency.c = this.c;
        increasedTowerToEnemyEfficiency.e = this.e;
        increasedTowerToEnemyEfficiency.f = this.f;
        return increasedTowerToEnemyEfficiency;
    }

    public final String toString() {
        return super.toString() + " {tower: " + this.e + ", enemy: " + this.f + "}";
    }

    private void a() {
        if (this.g == null) {
            throw new IllegalStateException("Bonus not registered");
        }
        EnemyType[] enemyTypeArr = {this.f};
        if (this.f == EnemyType.BOSS) {
            enemyTypeArr = new EnemyType[]{EnemyType.BOSS, EnemyType.SNAKE_BOSS_HEAD, EnemyType.SNAKE_BOSS_BODY, EnemyType.SNAKE_BOSS_TAIL, EnemyType.BROOT_BOSS, EnemyType.CONSTRUCTOR_BOSS, EnemyType.MOBCHAIN_BOSS_HEAD, EnemyType.MOBCHAIN_BOSS_BODY, EnemyType.MOBCHAIN_BOSS_CREEP, EnemyType.METAPHOR_BOSS, EnemyType.METAPHOR_BOSS_CREEP};
        }
        for (EnemyType enemyType : enemyTypeArr) {
            this.g.tower.canTowerAttackEnemy[enemyType.ordinal()][this.e.ordinal()] = true;
            float[] fArr = this.g.tower.towerEnemyDamageMultiplier[enemyType.ordinal()];
            int ordinal = this.e.ordinal();
            fArr[ordinal] = fArr[ordinal] - this.h;
        }
        this.h = (this.c + (getPower() * this.d)) * 0.01f;
        for (EnemyType enemyType2 : enemyTypeArr) {
            float[] fArr2 = this.g.tower.towerEnemyDamageMultiplier[enemyType2.ordinal()];
            int ordinal2 = this.e.ordinal();
            fArr2[ordinal2] = fArr2[ordinal2] + this.h;
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getAllowedEnemiesSet().contains(this.f) && Game.i.towerManager.getFactory(this.e).isAvailable(gameSystemProvider.gameValue)) {
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getAllowedEnemiesSet().contains(this.f) && Game.i.towerManager.getFactory(this.e).isAvailable(gameSystemProvider.gameValue)) {
            return null;
        }
        return () -> {
            return Game.i.localeManager.i18n.get("gpmod_precondition_increased_tower_to_enemy_efficiency");
        };
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency = null;
        int i = 0;
        while (true) {
            if (i >= gameSystemProvider.gameplayMod.getActiveMods().size) {
                break;
            }
            GameplayModSystem.ActiveMod activeMod = gameSystemProvider.gameplayMod.getActiveMods().get(i);
            GameplayMod mod = activeMod.getMod();
            if ((mod instanceof IncreasedTowerToEnemyEfficiency) && activeMod.getSource().equals(str)) {
                IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency2 = (IncreasedTowerToEnemyEfficiency) mod;
                if (increasedTowerToEnemyEfficiency2.e == this.e && increasedTowerToEnemyEfficiency2.f == this.f) {
                    increasedTowerToEnemyEfficiency = increasedTowerToEnemyEfficiency2;
                    break;
                }
            }
            i++;
        }
        if (increasedTowerToEnemyEfficiency == null) {
            this.g = gameSystemProvider;
            a();
            return true;
        }
        increasedTowerToEnemyEfficiency.setRegisteredPower(this.power);
        return false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.OFFENSIVE;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final IncreasedTowerToEnemyEfficiency applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.d = jsonValue.getFloat("damagePerPwr", this.d);
        this.c = jsonValue.getFloat("baseDamage", this.c);
        String string = jsonValue.getString("tower", null);
        if (string != null) {
            try {
                this.e = TowerType.valueOf(string);
            } catch (Exception e) {
                f2138a.e("failed to read tower type cfg", e);
            }
        }
        String string2 = jsonValue.getString("enemy", null);
        if (string2 != null) {
            try {
                this.f = EnemyType.valueOf(string2);
            } catch (Exception e2) {
                f2138a.e("failed to read enemy type cfg", e2);
            }
        }
        this.f2139b = null;
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/IncreasedTowerToEnemyEfficiency$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2140a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2140a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2140a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("IncreasedTowerToEnemyEfficiency");
            ProbableBonusesProvider.BonusProviderConfig applyConfig = new ProbableBonusesProvider.BonusProviderConfig(0.03f).setPowerUpProbabilityMultiplier(0.9f).applyConfig(bonusConfig);
            Array array3 = new Array(true, 1, IncreasedTowerToEnemyEfficiency.class);
            if (bonusConfig.getBoolean("useDefaultTowerEnemyCombos", true)) {
                for (ObjectPair<TowerType, EnemyType> objectPair : IncreasedTowerToEnemyEfficiency.DEFAULT_PAIRS) {
                    array3.add(new IncreasedTowerToEnemyEfficiency(objectPair.first, objectPair.second));
                }
            }
            JsonValue jsonValue = bonusConfig.get("towerEnemyCombos");
            if (jsonValue != null) {
                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        TowerType valueOf = TowerType.valueOf(next.getString("tower"));
                        EnemyType valueOf2 = EnemyType.valueOf(next.getString("enemy"));
                        IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency = null;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= array3.size) {
                                break;
                            }
                            IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency2 = ((IncreasedTowerToEnemyEfficiency[]) array3.items)[i2];
                            if (increasedTowerToEnemyEfficiency2.e != valueOf || increasedTowerToEnemyEfficiency2.f != valueOf2) {
                                i2++;
                            } else {
                                increasedTowerToEnemyEfficiency = increasedTowerToEnemyEfficiency2;
                                break;
                            }
                        }
                        if (increasedTowerToEnemyEfficiency == null) {
                            array3.add(new IncreasedTowerToEnemyEfficiency(valueOf, valueOf2));
                        }
                    } catch (Exception e) {
                        logger.e("failed to read combo: " + next, e);
                    }
                }
            }
            if (array3.size == 0) {
                return;
            }
            logger.i("variant list: " + array3, new Object[0]);
            Array.ArrayIterator it = array3.iterator();
            while (it.hasNext()) {
                IncreasedTowerToEnemyEfficiency increasedTowerToEnemyEfficiency3 = (IncreasedTowerToEnemyEfficiency) it.next();
                JsonValue jsonValue2 = JsonHandler.EMPTY_JSON_OBJECT;
                if (jsonValue != null) {
                    Iterator<JsonValue> iterator22 = jsonValue.iterator2();
                    while (true) {
                        if (!iterator22.hasNext()) {
                            break;
                        }
                        JsonValue next2 = iterator22.next();
                        try {
                            TowerType valueOf3 = TowerType.valueOf(next2.getString("tower"));
                            EnemyType valueOf4 = EnemyType.valueOf(next2.getString("enemy"));
                            if (valueOf3 == increasedTowerToEnemyEfficiency3.e && valueOf4 == increasedTowerToEnemyEfficiency3.f) {
                                jsonValue2 = next2;
                                break;
                            }
                        } catch (Exception e2) {
                            logger.e("failed to read combo: " + next2, e2);
                        }
                    }
                }
                increasedTowerToEnemyEfficiency3.applyConfig(bonusConfig);
                increasedTowerToEnemyEfficiency3.applyConfig(jsonValue2);
                ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(increasedTowerToEnemyEfficiency3, i, array, applyConfig.cpy().applyConfig(jsonValue2));
                if (addOrModify != null) {
                    array2.add(addOrModify);
                }
            }
        }
    }
}

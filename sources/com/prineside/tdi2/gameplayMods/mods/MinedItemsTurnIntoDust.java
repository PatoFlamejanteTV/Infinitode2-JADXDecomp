package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.MinerMineItem;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.items.PrestigeDustItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MinedItemsTurnIntoDust.class */
public final class MinedItemsTurnIntoDust extends GenericGameplayMod implements Listener<MinerMineItem> {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2151a = TLog.forClass(MinedItemsTurnIntoDust.class);
    public float baseBonus;
    public float bonusPerPower;
    public int stackSize;
    public int stackSizePerPower;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private GameSystemProvider f2152b;

    public MinedItemsTurnIntoDust() {
        this.maxPower = 3;
        this.multipleInstances = false;
        this.baseBonus = 0.0f;
        this.bonusPerPower = 0.2f;
        this.stackSize = 500;
        this.stackSizePerPower = User32.VK_PLAY;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.baseBonus);
        output.writeFloat(this.bonusPerPower);
        output.writeInt(this.stackSize);
        output.writeInt(this.stackSizePerPower);
        kryo.writeObjectOrNull(output, this.f2152b, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.baseBonus = input.readFloat();
        this.bonusPerPower = input.readFloat();
        this.stackSize = input.readInt();
        this.stackSizePerPower = input.readInt();
        this.f2152b = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.MinedItemsTurnIntoDust");
    }

    public final float getBonusMultiplier() {
        return this.baseBonus + (this.bonusPerPower * this.power);
    }

    public final int getStackSize() {
        return MathUtils.round(this.stackSize + (this.stackSizePerPower * this.power));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_mined_items_turn_into_dust", StringFormatter.compactNumberWithPrecisionTrimZeros(getBonusMultiplier() * 100.0f, 1, true).toString(), StringFormatter.commaSeparatedNumber(getStackSize()).toString());
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final MinedItemsTurnIntoDust cpy() {
        MinedItemsTurnIntoDust minedItemsTurnIntoDust = new MinedItemsTurnIntoDust();
        a(minedItemsTurnIntoDust);
        minedItemsTurnIntoDust.baseBonus = this.baseBonus;
        minedItemsTurnIntoDust.bonusPerPower = this.bonusPerPower;
        minedItemsTurnIntoDust.stackSize = this.stackSize;
        minedItemsTurnIntoDust.stackSizePerPower = this.stackSizePerPower;
        return minedItemsTurnIntoDust;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isAlwaysUseless(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ObjectSupplier<CharSequence> getNotSatisfiedPreconditions(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.map.getMap().getTilesByType(SourceTile.class).size == 0) {
            return () -> {
                return Game.i.localeManager.i18n.get("gpmod_precondition_no_source_tiles_on_map");
            };
        }
        return null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(MinedItemsTurnIntoDust.class, str);
        if (activeModFromSource == null) {
            this.f2152b = gameSystemProvider;
            gameSystemProvider.events.getListeners(MinerMineItem.class).addStateAffectingWithPriority(this, 1000);
        } else {
            activeModFromSource.getMod().setRegisteredPower(this.power);
        }
        return activeModFromSource == null;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final MinedItemsTurnIntoDust applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.baseBonus = jsonValue.getFloat("baseBonus", this.baseBonus);
        this.bonusPerPower = jsonValue.getFloat("bonusPerPower", this.bonusPerPower);
        this.stackSize = jsonValue.getInt("stackSize", this.stackSize);
        this.stackSizePerPower = jsonValue.getInt("stackSizePerPower", this.stackSizePerPower);
        return this;
    }

    @Override // com.prineside.tdi2.events.Listener
    public final void handleEvent(MinerMineItem minerMineItem) {
        int count;
        if (minerMineItem.getItemStack().getItem() instanceof TileItem) {
            Miner miner = minerMineItem.getMiner();
            int prestigeScore = (int) (((TileItem) minerMineItem.getItemStack().getItem()).tile.getPrestigeScore() * 0.25d * 1000.0d * this.f2152b.gameValue.getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE) * (1.0f + getBonusMultiplier()));
            int i = prestigeScore;
            if (prestigeScore > 0) {
                f2151a.i("turning " + minerMineItem.getItemStack() + " into " + i + " dust", new Object[0]);
                Array<ItemStack> orCreateSourceMinedItems = this.f2152b.loot.getOrCreateSourceMinedItems(miner.getTile().getX(), miner.getTile().getY());
                int stackSize = getStackSize();
                int i2 = 0;
                for (int i3 = 0; i3 < orCreateSourceMinedItems.size; i3++) {
                    ItemStack itemStack = orCreateSourceMinedItems.get(i3);
                    if ((itemStack.getItem() instanceof PrestigeDustItem) && (count = stackSize - itemStack.getCount()) > 0) {
                        int min = Math.min(i, count);
                        i -= min;
                        i2 += min;
                        itemStack.setCount(itemStack.getCount() + min);
                        if (i == 0) {
                            break;
                        }
                    }
                }
                if (i > 0) {
                    int lootSlots = this.f2152b.loot.getLootSlots(miner.type);
                    while (i > 0 && orCreateSourceMinedItems.size < lootSlots) {
                        int min2 = Math.min(i, stackSize);
                        i -= min2;
                        i2 += min2;
                        orCreateSourceMinedItems.add(new ItemStack(Item.D.PRESTIGE_DUST, min2));
                        if (i == 0) {
                            break;
                        }
                    }
                }
                if (i2 > 0) {
                    minerMineItem.setCancelled(false);
                    minerMineItem.setItemStack(new ItemStack(Item.D.PRESTIGE_DUST, i2));
                    minerMineItem.setAddToEmptyItemSlot(false);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MinedItemsTurnIntoDust$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2153a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2153a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2153a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("MinedItemsTurnIntoDust");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new MinedItemsTurnIntoDust().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(1.0f).setPowerUpProbabilityMultiplier(0.8f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}

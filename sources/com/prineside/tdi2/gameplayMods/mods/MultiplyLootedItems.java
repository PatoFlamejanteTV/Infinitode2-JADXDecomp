package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MultiplyLootedItems.class */
public final class MultiplyLootedItems extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private float f2159a = 2.0f;

    public MultiplyLootedItems() {
        this.maxPower = 2;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2159a);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2159a = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.MultiplyLootedItems");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_multiply_looted_items", StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2159a, 1, true));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final MultiplyLootedItems cpy() {
        MultiplyLootedItems multiplyLootedItems = new MultiplyLootedItems();
        a(multiplyLootedItems);
        multiplyLootedItems.f2159a = this.f2159a;
        return multiplyLootedItems;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        int count;
        Array array = new Array(true, 1, ItemStack.class);
        float f = this.f2159a - 1.0f;
        if (f > 0.0f) {
            int calculatePrizeGreenPapers = (int) (gameSystemProvider.gameState.calculatePrizeGreenPapers() * f);
            if (calculatePrizeGreenPapers > 0) {
                array.add(new ItemStack(Item.D.GREEN_PAPER, calculatePrizeGreenPapers));
            }
            for (ResourceType resourceType : ResourceType.values) {
                int resources = (int) ((gameSystemProvider.gameState.getResources(resourceType) * f) + 0.01f);
                if (resources > 0) {
                    array.add(new ItemStack(Item.D.F_RESOURCE.create(resourceType), resources));
                }
            }
            IssuedItems gameLootIssuedItems = gameSystemProvider.gameState.getGameLootIssuedItems();
            for (int i = 0; i < gameLootIssuedItems.items.size; i++) {
                ItemStack itemStack = gameLootIssuedItems.items.get(i);
                if (canMultiplyItem(itemStack.getItem()) && (count = (int) ((itemStack.getCount() * f) + 0.01f)) > 0) {
                    array.add(new ItemStack(itemStack.getItem(), count));
                }
            }
            float f2 = 0.0f;
            if (gameSystemProvider._gameUi != null) {
                f2 = Game.i.uiManager.stage.getWidth();
            }
            for (int i2 = 0; i2 < array.size; i2++) {
                ItemStack itemStack2 = (ItemStack) array.get(i2);
                float f3 = i2 / (array.size - 1.0f);
                gameSystemProvider.gameState.addLootIssuedPrizes(itemStack2, (f2 * 0.33f) + (f2 * f3 * 0.34f), 80.0f + (MathUtils.sin(f3 * 3.1415927f) * 40.0f), 2);
            }
        }
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(MultiplyLootedItems.class, str);
        if (activeModFromSource != null) {
            activeModFromSource.getMod().setRegisteredPower(this.power);
            return false;
        }
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final GameplayModCategory getCategory() {
        return GameplayModCategory.LOOTING;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod
    public final MultiplyLootedItems applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2159a = jsonValue.getFloat("multiplier", this.f2159a);
        return this;
    }

    public static boolean canMultiplyItem(Item item) {
        switch (item.getType()) {
            case TILE:
            case GATE:
            case RANDOM_TILE:
            case RANDOM_BARRIER:
            case RANDOM_TELEPORT:
            case RESOURCE:
            case CASE:
            case GREEN_PAPER:
            case BIT_DUST:
            case PRESTIGE_DUST:
            case BLUEPRINT:
            case ABILITY_TOKEN:
            case CASE_KEY:
                return true;
            default:
                return false;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/MultiplyLootedItems$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2161a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2161a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2161a;
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("MultiplyLootedItems");
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(new MultiplyLootedItems().applyConfig(bonusConfig), i, array, new ProbableBonusesProvider.BonusProviderConfig(0.1f).setPowerUpProbabilityMultiplier(0.3f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}

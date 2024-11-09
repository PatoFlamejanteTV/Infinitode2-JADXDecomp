package com.prineside.tdi2.gameplayMods.mods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.gameplayMods.GameplayModCategory;
import com.prineside.tdi2.gameplayMods.GenericGameplayMod;
import com.prineside.tdi2.gameplayMods.ProbableBonus;
import com.prineside.tdi2.gameplayMods.ProbableBonusesProvider;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ReceiveGreenPapers.class */
public final class ReceiveGreenPapers extends GenericGameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2170a = TLog.forClass(ReceiveGreenPapers.class);

    /* renamed from: b, reason: collision with root package name */
    private int f2171b = 100;
    private int c;
    private float d;

    public ReceiveGreenPapers() {
        this.maxPower = 2048;
        this.multipleInstances = false;
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.f2171b);
        output.writeVarInt(this.c, true);
        output.writeFloat(this.d);
    }

    @Override // com.prineside.tdi2.gameplayMods.GenericGameplayMod, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2171b = input.readInt();
        this.c = input.readVarInt(true);
        this.d = input.readFloat();
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean isImmediateAndNotListed() {
        return true;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final Drawable getIcon() {
        return Game.i.assetManager.getQuad("gpMods.ReceiveGreenPapers");
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("gmod_descr_receive_green_papers", Integer.valueOf(this.f2171b));
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final ReceiveGreenPapers cpy() {
        ReceiveGreenPapers receiveGreenPapers = new ReceiveGreenPapers();
        a(receiveGreenPapers);
        receiveGreenPapers.f2171b = this.f2171b;
        receiveGreenPapers.c = this.c;
        receiveGreenPapers.d = this.d;
        return receiveGreenPapers;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final void configure(GameSystemProvider gameSystemProvider) {
        int calculatePrizeGreenPapers = gameSystemProvider.gameState.calculatePrizeGreenPapers();
        Array<ItemStack> array = gameSystemProvider.gameState.getGameLootIssuedItems().items;
        for (int i = 0; i < array.size; i++) {
            ItemStack itemStack = array.get(i);
            if (itemStack.getItem().getType() == ItemType.GREEN_PAPER) {
                calculatePrizeGreenPapers += itemStack.getCount();
            }
        }
        f2170a.i("sum this game: " + calculatePrizeGreenPapers, new Object[0]);
        int round = MathUtils.round((calculatePrizeGreenPapers * this.d) / 100.0f);
        int i2 = round;
        if (round < 500) {
            i2 = 500;
        }
        if (this.c > 0 && i2 > this.c) {
            i2 = this.c;
        }
        this.f2171b = i2;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public final boolean register(GameSystemProvider gameSystemProvider, String str) {
        float f = 0.0f;
        if (gameSystemProvider._gameUi != null) {
            f = Game.i.uiManager.stage.getWidth();
        }
        gameSystemProvider.gameState.addLootIssuedPrizes(new ItemStack(Item.D.GREEN_PAPER, this.f2171b), f * 0.5f, 80.0f, 2);
        GameplayModSystem.ActiveMod activeModFromSource = gameSystemProvider.gameplayMod.getActiveModFromSource(ReceiveGreenPapers.class, str);
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
    public final ReceiveGreenPapers applyConfig(JsonValue jsonValue) {
        super.applyConfig(jsonValue);
        this.f2171b = jsonValue.getInt("amount", this.f2171b);
        return this;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/mods/ReceiveGreenPapers$BonusProvider.class */
    public static final class BonusProvider implements ProbableBonusesProvider, NoFieldKryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static final BonusProvider f2172a;

        static {
            BonusProvider bonusProvider = new BonusProvider();
            f2172a = bonusProvider;
            SyncChecker.addSyncShareableObject(bonusProvider);
        }

        public static BonusProvider getInstance() {
            return f2172a;
        }

        public final ReceiveGreenPapers provideFallback(BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("ReceiveGreenPapers");
            ReceiveGreenPapers applyConfig = new ReceiveGreenPapers().applyConfig(bonusConfig);
            applyConfig.d = bonusConfig.getFloat("amountPercentage", 1.0f);
            applyConfig.c = bonusConfig.getInt("maxAmount", 100000);
            ReceiveGreenPapers receiveGreenPapers = null;
            int i = 0;
            while (true) {
                if (i >= array.size) {
                    break;
                }
                GameplayModSystem.ActiveMod activeMod = array.items[i];
                if (!activeMod.getMod().getId().equals(applyConfig.getId()) || !activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME)) {
                    i++;
                } else {
                    receiveGreenPapers = (ReceiveGreenPapers) activeMod.getMod();
                    break;
                }
            }
            if (receiveGreenPapers != null) {
                int min = Math.min(receiveGreenPapers.getMaxPower(), receiveGreenPapers.getPower() + 1);
                logger.i("found existing for proto (proto: " + applyConfig + ", existing: " + receiveGreenPapers + ")", new Object[0]);
                ReceiveGreenPapers cpy = applyConfig.cpy();
                cpy.power = min;
                return cpy;
            }
            return applyConfig.cpy();
        }

        @Override // com.prineside.tdi2.gameplayMods.ProbableBonusesProvider
        public final void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2) {
            JsonValue bonusConfig = bonusStagesConfig.getBonusConfig("ReceiveGreenPapers");
            ReceiveGreenPapers applyConfig = new ReceiveGreenPapers().applyConfig(bonusConfig);
            applyConfig.d = bonusConfig.getFloat("amountPercentage", 1.0f);
            applyConfig.c = bonusConfig.getInt("maxAmount", 100000);
            ProbableBonus addOrModify = ProbableBonusesProvider.addOrModify(applyConfig, i, array, new ProbableBonusesProvider.BonusProviderConfig(0.0f).applyConfig(bonusConfig));
            if (addOrModify != null) {
                array2.add(addOrModify);
            }
        }
    }
}

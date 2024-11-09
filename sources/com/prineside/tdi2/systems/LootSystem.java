package com.prineside.tdi2.systems;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.actions.RewardingAdAction;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyLootAdd;
import com.prineside.tdi2.events.game.MinerMineItem;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.events.game.RewardingAdBecameAvailable;
import com.prineside.tdi2.events.game.RewardingAdRegistered;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.glfw.GLFW;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/LootSystem.class */
public final class LootSystem extends GameSystem {
    public static final float ENCRYPTED_CASE_GLOBAL_INTERVAL = 2400.0f;
    public RandomXS128 random;
    public ProgressManager.InventoryStatistics inventoryStatistics;

    /* renamed from: b, reason: collision with root package name */
    private ProgressManager.InventoryStatistics f2973b;
    private float c;
    private int k;
    private float l;
    private int m;
    private int n;
    private float p;
    private int q;
    private int v;
    private float x;
    private float y;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2972a = TLog.forClass(LootSystem.class);
    public static final int[] REWARDING_AD_VIEW_BONUSES = {5, 10, 15, 20, 25};
    private static float A = 0.0f;
    private static int B = -1;
    private float d = 60.0f;
    private float e = 1.0f;
    private int f = 0;
    private boolean g = false;
    private float h = 0.0f;
    private float i = 0.0f;
    private float j = 0.0f;
    private int o = 0;
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private int[] u = new int[0];
    private int w = -1;
    public int[] lootFillsByRarity = new int[RarityType.values.length];
    private IntMap<Array<ItemStack>> z = new IntMap<>();
    public boolean minersMineOnlyLegendaries = false;

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.random, RandomXS128.class);
        kryo.writeObject(output, this.inventoryStatistics);
        kryo.writeObject(output, this.f2973b);
        output.writeFloat(this.c);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeVarInt(this.f, true);
        output.writeBoolean(this.g);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        output.writeFloat(this.j);
        output.writeInt(this.k);
        output.writeFloat(this.l);
        output.writeInt(this.m);
        output.writeInt(this.n);
        output.writeInt(this.o);
        output.writeFloat(this.p);
        output.writeVarInt(this.q, true);
        output.writeInt(this.r);
        output.writeInt(this.s);
        output.writeVarInt(this.t, true);
        kryo.writeObject(output, this.u);
        output.writeVarInt(this.v, true);
        output.writeInt(this.w);
        output.writeFloat(this.x);
        output.writeFloat(this.y);
        kryo.writeObject(output, this.lootFillsByRarity);
        kryo.writeObject(output, this.z);
        output.writeBoolean(this.minersMineOnlyLegendaries);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.random = (RandomXS128) kryo.readObjectOrNull(input, RandomXS128.class);
        this.inventoryStatistics = (ProgressManager.InventoryStatistics) kryo.readObject(input, ProgressManager.InventoryStatistics.class);
        this.f2973b = (ProgressManager.InventoryStatistics) kryo.readObject(input, ProgressManager.InventoryStatistics.class);
        this.c = input.readFloat();
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readVarInt(true);
        this.g = input.readBoolean();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.j = input.readFloat();
        this.k = input.readInt();
        this.l = input.readFloat();
        this.m = input.readInt();
        this.n = input.readInt();
        this.o = input.readInt();
        this.p = input.readFloat();
        this.q = input.readVarInt(true);
        this.r = input.readInt();
        this.s = input.readInt();
        this.t = input.readVarInt(true);
        this.u = (int[]) kryo.readObject(input, int[].class);
        this.v = input.readVarInt(true);
        this.w = input.readInt();
        this.x = input.readFloat();
        this.y = input.readFloat();
        this.lootFillsByRarity = (int[]) kryo.readObject(input, int[].class);
        this.z = (IntMap) kryo.readObject(input, IntMap.class);
        this.minersMineOnlyLegendaries = input.readBoolean();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    private static float a(float f) {
        if (f <= 100.0f) {
            return 1.0f + ((100.0f - f) * 0.01f);
        }
        if (f > 880.0f) {
            f = 880.0f + (720.0f * (1.0f - (1.0f / (((f - 880.0f) * 0.00125f) + 1.0f))));
        }
        return 1.0f / ((1.0f + (MathUtils.sin(((f - 100.0f) * 0.002f) - 1.5707964f) * 40.0f)) + 40.0f);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (Config.isHeadless()) {
            return;
        }
        if (this.S.gameState.getSeed() == -1) {
            throw new IllegalStateException("GameStateSystem seed not set");
        }
        if (this.S.gameState.gameStartTimestamp == -1) {
            throw new IllegalStateException("GameStartTimestamp not set");
        }
        if (this.inventoryStatistics == null) {
            throw new IllegalStateException("inventoryStatistics not set");
        }
        this.f2973b = this.inventoryStatistics.cpy();
        this.random = new RandomXS128(this.S.gameState.getSeed() + (this.S.gameState.gameStartTimestamp * 29));
        this.c = a(this.S.gameState.averageDifficulty);
        this.c *= 210.0f;
        if (this.S.gameState.averageDifficulty < 100) {
            this.l = this.S.gameState.averageDifficulty * 0.01f;
            this.k = (int) (this.l * 125.0f);
        } else if (this.S.gameState.averageDifficulty > 100) {
            this.l = 1.0f + (((this.S.gameState.averageDifficulty - 100) / 400.0f) * 1.5f);
            this.k = (int) (this.l * 125.0f);
        } else {
            this.l = 1.0f;
            this.k = 125;
        }
        this.l *= 0.15f;
        this.c *= 1.0f / ProgressManager.getDifficultyModePrizeMultiplier(this.S.gameState.difficultyMode);
        if (this.S.gameState.lootBoostEnabled) {
            this.c /= 1.5f;
        }
        if (this.S.gameState.rarityBoostEnabled) {
            this.e *= 1.5f;
        }
        if (this.c <= 0.0f) {
            throw new IllegalStateException("itemsStep is " + this.c);
        }
        b();
        c();
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this));
        this.S.events.getListeners(MinerResourceChange.class).addStateAffecting(new OnMinerResourceChange(this)).setDescription("Notifies loot system about the mined resource");
    }

    private void a() {
        this.f++;
        this.j = 0.0f;
        f2972a.i("rewarding ad view registered, current multiplier: " + getRewardingAdsLootMultiplier(), new Object[0]);
        this.S.events.trigger(new RewardingAdRegistered());
    }

    public final float getActiveSecondsPlayed() {
        return this.i;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        if (Config.isHeadless()) {
            return;
        }
        if (isRewardingAdAvailableByState() && Game.i.progressManager.isPremiumStatusActive()) {
            if (this.f == 2 || this.f == 4) {
                ProgressPrefs.i().progress.registerVideoWatched();
                ProgressPrefs.i().requireSave();
            }
            a();
        }
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                if (currentUpdateActions.actions[i].getType() == ActionType.RA) {
                    if (isRewardingAdAvailableByState()) {
                        a();
                        this.S.gameState.registerPlayerActivity();
                    } else {
                        f2972a.e("failed to handle rewarding ads action - not available (" + getTimeToRewardingAds(false) + ")", new Object[0]);
                    }
                }
            }
        }
        if (this.S.gameState.isGameRealTimePasses()) {
            float rewardingAdsLootMultiplier = f * getRewardingAdsLootMultiplier() * 1.25f;
            if (this.S.statistics.getStatistic(StatisticsType.PT) - this.y < 30.0d && this.S.gameState.updateNumber - this.S.gameState.getPxpLastActionFrame() < 3600) {
                this.h += rewardingAdsLootMultiplier;
                this.i += f;
            }
            this.j += rewardingAdsLootMultiplier;
            if (!this.g && isRewardingAdAvailableByState()) {
                this.S.events.trigger(new RewardingAdBecameAvailable());
                this.g = true;
            }
        }
        this.x += f;
        if (this.x > 1.0f) {
            this.x -= 1.0f;
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_FLAMETHROWER_A_INSTAKILL_BANK_TIME);
            int i2 = intValue;
            if (intValue < 0) {
                i2 = 0;
            } else if (i2 > 900) {
                i2 = 900;
            }
            if (this.u.length != i2) {
                this.u = new int[i2];
                this.v = 0;
            }
            if (this.u.length > 0) {
                int calculatePrizeGreenPapers = this.S.gameState.calculatePrizeGreenPapers();
                int percentValueAsMultiplier = (int) ((this.w < 0 ? 0 : calculatePrizeGreenPapers - this.w) * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_INSTAKILL_PAPER_BANK));
                int i3 = percentValueAsMultiplier;
                if (percentValueAsMultiplier < 0) {
                    i3 = 0;
                }
                if (this.v == this.u.length) {
                    System.arraycopy(this.u, 1, this.u, 0, this.u.length - 1);
                    this.v--;
                }
                int[] iArr = this.u;
                int i4 = this.v;
                this.v = i4 + 1;
                iArr[i4] = i3;
                this.w = calculatePrizeGreenPapers;
            }
        }
    }

    public final int getPapersFromFlamethrowerUltBank(int i) {
        if (i <= 0) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= this.v) {
                break;
            }
            int i4 = this.u[i3];
            int i5 = i - i2;
            if (i5 <= i4) {
                int[] iArr = this.u;
                int i6 = i3;
                iArr[i6] = iArr[i6] - i5;
                i2 += i5;
                break;
            }
            i2 += i4;
            this.u[i3] = 0;
            i3++;
        }
        return i2;
    }

    public final int getRewardingAdViews() {
        return this.f;
    }

    public final float getTimeToRewardingAds(boolean z) {
        if (this.f >= 5) {
            return -1.0f;
        }
        if (!Config.isHeadless() && !Game.i.purchaseManager.rewardingAdsAvailable()) {
            return -1.0f;
        }
        float f = 300.0f - this.j;
        float f2 = f;
        if (f < 0.0f) {
            f2 = 0.0f;
        }
        if (z) {
            return Math.max(Game.i.purchaseManager.getSecondsTillAdIsReady(PurchaseManager.RewardingAdsType.LOOT_MULTIPLIER), f2);
        }
        return f2;
    }

    public final boolean isRewardingAdAvailableInReality() {
        return getTimeToRewardingAds(true) == 0.0f;
    }

    public final boolean isRewardingAdAvailableByState() {
        return getTimeToRewardingAds(false) == 0.0f;
    }

    public final void viewRewardingAdAction() {
        if (isRewardingAdAvailableByState()) {
            this.S.gameState.pushActionNextUpdate(new RewardingAdAction());
        } else {
            f2972a.e("failed to add rewarding ads action - not available " + getTimeToRewardingAds(false), new Object[0]);
            f2972a.i(this.f + "/5" + SequenceUtils.SPACE + Game.i.purchaseManager.rewardingAdsAvailable() + SequenceUtils.SPACE + Game.i.actionResolver.rewardAdsAvailable(), new Object[0]);
        }
    }

    public final float getRewardingAdsLootMultiplier() {
        float f = 1.0f;
        if (this.f > 0 && this.f <= REWARDING_AD_VIEW_BONUSES.length) {
            f = 1.0f + (REWARDING_AD_VIEW_BONUSES[this.f - 1] * 0.01f);
        }
        return f;
    }

    private static float a(float f, float f2, int i) {
        float clamp = (float) MathUtils.clamp(f, 0.001d, 1.0d);
        return f2 + (f2 * 0.15f * clamp * i) + ((((float) StrictMath.pow(i, 1.0d + (clamp * 0.2d))) - (i * (1.0f - clamp))) * 0.075f * f2);
    }

    public static float calculateBaseLootCountGraph(float f) {
        if (f < 600.0f) {
            return (1.0f - MathUtils.cos(((f * 0.0016666667f) * 1.6f) * 1.5707964f)) * 0.55555f * 5.0f;
        }
        if (f < 1800.0f) {
            return 5.0f + ((f - 600.0f) * 8.3333335E-4f * 10.0f);
        }
        return 15.0f + ((((float) StrictMath.pow((((f - 600.0f) - 1200.0f) * 8.3333335E-4f) + 0.2f, 0.75d)) - 0.3f) * 10.0f);
    }

    public static void main(String[] strArr) {
        for (int i = 0; i < 5400.0d; i += 60) {
            System.out.println((i / 60.0f) + "," + calculateBaseLootCountGraph(i));
        }
    }

    private void b() {
        float a2;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.LOOT_FREQUENCY);
        if (percentValueAsMultiplier > 0.0f) {
            a2 = a((1.0f - (((float) StrictMath.pow(percentValueAsMultiplier - 1.0f, 1.5d)) * 0.031625f)) * 0.5f, this.c, this.r) / percentValueAsMultiplier;
        } else {
            a2 = 3.1536E7f;
        }
        this.d += a2;
        float f = a2;
        if (this.r == 0) {
            f *= 0.35f;
        }
        this.d += (this.random.nextFloat() - this.random.nextFloat()) * f;
    }

    private void c() {
        this.S.gameState.checkGameplayUpdateAllowed();
        float f = this.k - (this.k * 0.4f);
        this.n = (int) (f + (((this.k + (this.k * 0.4f)) - f) * this.random.nextFloat()));
        if (this.n == 0) {
            this.n = 1;
        }
    }

    public final boolean canGiveChests() {
        return this.S.gameState.canLootCases;
    }

    private static float a(int i) {
        if (B == i) {
            return A;
        }
        B = i;
        if (i < 100) {
            float f = i * 0.01f;
            A = f;
            return f;
        }
        float f2 = i;
        float f3 = f2;
        if (f2 > 2000.0f) {
            f3 = 2000.0f + (270.91f * (1.0f - (1.0f / (((f3 - 2000.0f) * 0.00375f) + 1.0f))));
        }
        float pow = (float) ((1.0d + (((f3 - 100.0d) / 400.0d) * 0.85d)) - (StrictMath.pow(f3 * 2.5E-4d, 3.5d) * 10.0d));
        A = pow;
        return pow;
    }

    public final ItemStack forceFillWithLoot(Enemy enemy) {
        float f;
        float f2;
        if (Config.isHeadless()) {
            return new ItemStack(Item.D.GREEN_PAPER, 1);
        }
        float a2 = this.h * a(this.S.gameState.averageDifficulty);
        if (this.S.gameState.averageDifficulty < 100) {
            f = (this.S.gameState.averageDifficulty / 100.0f) * 0.35f;
        } else if (this.S.gameState.averageDifficulty < 150) {
            f = 0.35f + (((this.S.gameState.averageDifficulty - 100) / 50.0f) * 0.2f);
        } else if (this.S.gameState.averageDifficulty < 325) {
            f = 0.55f + (((this.S.gameState.averageDifficulty - 150) / 175.0f) * 0.15f);
        } else if (this.S.gameState.averageDifficulty < 500) {
            f = 0.7f + (((this.S.gameState.averageDifficulty - GLFW.GLFW_KEY_KP_5) / 175.0f) * 0.05f);
        } else {
            f = 0.75f + (((this.S.gameState.averageDifficulty - 500) / 250.0f) * 0.025f);
        }
        float f3 = 0.0f;
        if (a2 < 60.0f) {
            f2 = f * 0.1f;
        } else if (a2 < 150.0f) {
            f2 = f * 0.2f;
        } else if (a2 < 300.0f) {
            f2 = f * 0.35f;
        } else if (a2 < 600.0f) {
            f2 = f * 0.5f;
        } else if (a2 < 1200.0f) {
            f2 = f * 0.65f;
        } else if (a2 < 1800.0f) {
            f2 = f * 0.8f;
        } else if (a2 < 2400.0f) {
            f2 = f;
        } else if (a2 < 3000.0f) {
            f2 = f * 1.15f;
        } else if (a2 < 3600.0f) {
            f2 = f * 1.3f;
        } else {
            f2 = f * 1.4f;
        }
        if (this.S.gameState.averageDifficulty <= 75 && f2 > 0.4f) {
            f2 = 0.4f;
        } else if (this.S.gameState.averageDifficulty <= 87 && f2 > 0.5f) {
            f2 = 0.5f;
        } else if (this.S.gameState.averageDifficulty <= 100 && f2 > 0.6f) {
            f2 = 0.6f;
        } else if (this.S.gameState.averageDifficulty <= 110 && f2 > 0.65f) {
            f2 = 0.65f;
        } else if (this.S.gameState.averageDifficulty <= 120 && f2 > 0.7f) {
            f2 = 0.7f;
        } else if (this.S.gameState.averageDifficulty <= 130 && f2 > 0.75f) {
            f2 = 0.75f;
        } else if (this.S.gameState.averageDifficulty <= 140 && f2 > 0.82f) {
            f2 = 0.82f;
        } else if (this.S.gameState.averageDifficulty <= 155 && f2 > 0.9f) {
            f2 = 0.9f;
        }
        float percentValueAsMultiplier = (float) (f2 * this.e * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.LOOT_RARITY));
        float f4 = percentValueAsMultiplier;
        if (percentValueAsMultiplier > 1.0f) {
            f3 = (float) (0.0d + (StrictMath.pow(f4 - 1.0f, 0.8d) * 0.10000000149011612d));
        }
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        if (f3 > f4) {
            f3 = f4;
        }
        float f5 = f4 - f3;
        float f6 = f5;
        if (f5 < 0.0f) {
            f6 = 0.0f;
        }
        float abs = StrictMath.abs(this.random.nextFloat() - this.random.nextFloat()) * 1.06f;
        float f7 = abs;
        if (abs > 1.0f) {
            f7 = 1.0f;
        }
        float f8 = f3 + (f7 * f6);
        RarityType rarityFromQuality = ProgressManager.getRarityFromQuality(f8);
        float globalQualityToRarityQualuty = ProgressManager.globalQualityToRarityQualuty(f8);
        float percentValueAsMultiplier2 = (float) ((DifficultyMode.isEndless(this.S.gameState.difficultyMode) ? 0.5f : 0.0f) * (((this.S.gameValue.getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE) - 1.0d) * 0.4000000059604645d) + 1.0d));
        int i = 0;
        Array<ItemStack> array = this.S.gameState.getGameLootIssuedItems().items;
        for (int i2 = 0; i2 < array.size; i2++) {
            ItemStack itemStack = array.get(i2);
            if (itemStack.getItem() == Item.D.BIT_DUST) {
                i += itemStack.getCount();
            }
        }
        float f9 = 1.0f;
        for (int i3 = 1; i3 <= 12; i3++) {
            if (i > this.q * i3) {
                f9 -= 0.05f;
            }
        }
        ItemStack generateItemByRarity = ItemManager.generateItemByRarity(this.random, rarityFromQuality, globalQualityToRarityQualuty, 0.5f, 0.0f, 0.25f, percentValueAsMultiplier2 * f9, canGiveChests() ? 1.0f : 0.0f, 1.0f, f8 > 0.5f + (this.random.nextFloat() * 0.5f), this.f2973b);
        this.f2973b.countItem(generateItemByRarity.getItem(), generateItemByRarity.getCount());
        int[] iArr = this.lootFillsByRarity;
        int ordinal = rarityFromQuality.ordinal();
        iArr[ordinal] = iArr[ordinal] + 1;
        addLoot(enemy, generateItemByRarity.getItem(), generateItemByRarity.getCount());
        return generateItemByRarity;
    }

    public final void fillWithLoot(Enemy enemy) {
        float f;
        if (Config.isHeadless()) {
            return;
        }
        this.S.gameState.checkGameplayUpdateAllowed();
        if (this.S.wave.mode == WaveSystem.Mode.PREDEFINED) {
            return;
        }
        this.o++;
        float a2 = this.h * a(this.S.gameState.averageDifficulty);
        boolean canGiveChests = canGiveChests();
        int i = 5;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.LOOT_FREQUENCY);
        if (percentValueAsMultiplier >= 10.0f) {
            i = 1;
        } else if (percentValueAsMultiplier >= 5.0f) {
            i = 2;
        } else if (percentValueAsMultiplier >= 2.5f) {
            i = 3;
        } else if (percentValueAsMultiplier >= 1.25f) {
            i = 4;
        }
        if (this.o % i == 0 || EnemyType.isBoss(enemy.type)) {
            for (int i2 = 0; i2 < 3 && a2 >= this.d; i2++) {
                ItemStack forceFillWithLoot = forceFillWithLoot(enemy);
                this.r++;
                b();
                if (a2 >= this.d) {
                    addLoot(enemy, forceFillWithLoot.getItem(), forceFillWithLoot.getCount());
                    this.r++;
                    b();
                }
            }
            int calculatePrizeGreenPapers = (int) (this.S.gameState.calculatePrizeGreenPapers() * this.l);
            int i3 = calculatePrizeGreenPapers - this.m;
            if (i3 >= this.n) {
                addLoot(enemy, Item.D.GREEN_PAPER, i3);
                this.m = calculatePrizeGreenPapers;
                c();
            }
        }
        if (this.o % 7 == 0 || EnemyType.isBoss(enemy.type)) {
            if (this.s == 0) {
                f = 1200.0f;
            } else if (this.s == 1) {
                f = 3600.0f;
            } else {
                f = 10800.0f * (this.s - 1);
            }
            if (a2 > f) {
                if (this.random.nextFloat() < (a2 - f) / 1000000.0f) {
                    addLoot(enemy, Item.D.ACCELERATOR, 1);
                    this.s++;
                }
            }
        }
        if (canGiveChests && (this.o % 11 == 0 || EnemyType.isBoss(enemy.type))) {
            if (((float) (this.S.gameState.gameStartProgressSnapshot.statsPlayTimeCasesLoot + this.S.statistics.getStatistic(StatisticsType.PTCL))) > MathUtils.floor((this.S.gameState.gameStartProgressSnapshot.statsPlayTimeCasesLoot / 2400.0f) + 1.0f + this.t) * 2400.0f) {
                this.S.statistics.addStatistic(StatisticsType.EQCG, 1.0d);
                addLoot(enemy, Item.D.F_CASE.create(Game.i.itemManager.getQueuedEncryptedCaseType(MathUtils.round(this.S.gameState.gameStartProgressSnapshot.statsQueuedCasesGiven) + this.t), true), 1);
                this.t++;
            }
        }
        if (DifficultyMode.isEndless(this.S.gameState.difficultyMode)) {
            if (this.p == 0.0f) {
                this.p = d();
            }
            if (((float) this.S.statistics.getStatistic(StatisticsType.PTEMWD)) > this.p) {
                this.p += d();
                float pow = (float) (1.5d + (Math.pow((float) (this.S.statistics.getStatistic(StatisticsType.PT) / 60.0d), 1.2d) * 0.015d));
                float f2 = pow;
                if (pow > 4.5f) {
                    f2 = 4.5f;
                }
                int i4 = this.S.gameState.averageDifficulty;
                int i5 = i4;
                if (i4 > 4500) {
                    i5 = 4500;
                }
                float f3 = 1.0f + (((i5 - 100) / 4500.0f) * 2.0f);
                float f4 = f3;
                if (f3 < 0.1f) {
                    f4 = 0.1f;
                }
                float f5 = f2 * f4;
                int i6 = (int) f5;
                if (this.random.nextFloat() < f5 - i6) {
                    i6++;
                }
                if (i6 > 0) {
                    this.S.statistics.addStatistic(StatisticsType.BDFTPG, i6);
                    addLoot(enemy, Item.D.BIT_DUST, i6);
                    this.q += i6;
                }
            }
        }
    }

    @Null
    public final ItemStack addLoot(Enemy enemy, Item item, int i) {
        if (((EnemyLootAdd) this.S.events.trigger(new EnemyLootAdd(item, i))).getCount() > 0) {
            return enemy.addLoot(item, i);
        }
        return null;
    }

    private float d() {
        return (1500.0f / ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE))) * (0.8f + (this.random.nextFloat() * 0.4f));
    }

    public final int getLootSlots(MinerType minerType) {
        int intValue = this.S.gameValue.getIntValue(GameValueType.MINERS_LOOT_SLOTS);
        switch (minerType) {
            case SCALAR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_SCALAR_LOOT_SLOTS);
                break;
            case VECTOR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_VECTOR_LOOT_SLOTS);
                break;
            case MATRIX:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_MATRIX_LOOT_SLOTS);
                break;
            case TENSOR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_TENSOR_LOOT_SLOTS);
                break;
            case INFIAR:
                intValue += this.S.gameValue.getIntValue(GameValueType.MINER_INFIAR_LOOT_SLOTS);
                break;
        }
        return intValue;
    }

    private static double a(Miner miner, int i) {
        double d;
        if (i <= 10) {
            d = 1.0d - (i * 0.07d);
        } else {
            double d2 = 0.3d - ((i - 10) * 0.05d);
            d = d2;
            if (d2 < 0.01d) {
                d = 0.01d;
            }
        }
        double d3 = d * 0.0025d;
        float f = 15.0f + (i * 7.0f);
        if (miner.existsTime - miner.lastMinedItemTime < f) {
            return 0.0d;
        }
        return d3 * (r0 - f) * 0.1d;
    }

    @Null
    public final Array<ItemStack> getSourceMinedItems(int i, int i2) {
        return this.z.get((i2 << 13) + i, null);
    }

    public final Array<ItemStack> getOrCreateSourceMinedItems(int i, int i2) {
        this.S.gameState.checkGameplayUpdateAllowed();
        int i3 = (i2 << 13) + i;
        Array<ItemStack> array = this.z.get(i3, null);
        Array<ItemStack> array2 = array;
        if (array == null) {
            array2 = new Array<>(true, 1, ItemStack.class);
            this.z.put(i3, array2);
        }
        return array2;
    }

    private static float a(RandomXS128 randomXS128, ResourceType resourceType, int i) {
        float ordinal = (resourceType.ordinal() + 1) * 0.2f;
        if (i == 0) {
            ordinal *= 0.7f;
        } else if (i == 1) {
            ordinal *= 0.85f;
        }
        return StrictMath.abs(randomXS128.nextFloat() - randomXS128.nextFloat()) * ((ordinal * 0.75f) + (ordinal * StrictMath.abs(randomXS128.nextFloat() - randomXS128.nextFloat()) * 0.25f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Miner miner, ResourceType resourceType, int i, boolean z) {
        double a2;
        if (z && i > 0) {
            int y = (miner.getTile().getY() << 13) + miner.getTile().getX();
            Array<ItemStack> array = this.z.get(y, null);
            int i2 = 0;
            if (array != null) {
                i2 = array.size;
            }
            if (this.random.nextDouble() > 0.1d) {
                a2 = 0.0d;
            } else {
                a2 = a(miner, i2);
                if (this.minersMineOnlyLegendaries) {
                    a2 *= 0.5d;
                }
            }
            if (a2 != 0.0d && this.random.nextDouble() < a2) {
                float a3 = a(this.random, resourceType, i2);
                RarityType rarityFromQuality = ProgressManager.getRarityFromQuality(a3);
                float globalQualityToRarityQualuty = ProgressManager.globalQualityToRarityQualuty(a3);
                if (this.minersMineOnlyLegendaries) {
                    rarityFromQuality = RarityType.LEGENDARY;
                    globalQualityToRarityQualuty = this.random.nextFloat();
                }
                MinerMineItem minerMineItem = new MinerMineItem(miner, resourceType, ItemManager.generateItemByRarity(this.random, rarityFromQuality, globalQualityToRarityQualuty, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, false, this.f2973b), a3, rarityFromQuality, globalQualityToRarityQualuty);
                if (i2 >= getLootSlots(miner.type)) {
                    minerMineItem.setCancelled(true);
                }
                this.S.events.getListeners(MinerMineItem.class).trigger(minerMineItem);
                if (!minerMineItem.isCancelled()) {
                    miner = minerMineItem.getMiner();
                    ItemStack itemStack = minerMineItem.getItemStack();
                    if (minerMineItem.isCountTowardsInventoryStatistics()) {
                        this.f2973b.countItem(itemStack.getItem(), itemStack.getCount());
                    }
                    if (minerMineItem.isAddAndShowActualLoot()) {
                        Vector2 vector2 = new Vector2(miner.getTile().center);
                        if (this.S._input != null) {
                            this.S._input.cameraController.mapToStage(vector2);
                        }
                        this.S.gameState.addLootIssuedPrizes(itemStack, vector2.x, vector2.y, 2);
                    }
                    if (minerMineItem.isAddToEmptyItemSlot()) {
                        if (array == null) {
                            array = new Array<>(true, 1, ItemStack.class);
                            this.z.put(y, array);
                        }
                        array.add(itemStack);
                    }
                }
                Miner miner2 = miner;
                miner2.lastMinedItemTime = miner2.existsTime;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Loot";
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/LootSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<LootSystem> implements Listener<EnemyDie> {

        /* renamed from: b, reason: collision with root package name */
        @NAGS
        private final Vector2 f2975b = new Vector2();

        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(LootSystem lootSystem) {
            this.f1759a = lootSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Enemy enemy = enemyDie.getLastDamage().getEnemy();
            if (enemy.loot != null) {
                this.f2975b.set(enemy.getPosition());
                if (((LootSystem) this.f1759a).S._input != null) {
                    ((LootSystem) this.f1759a).S._input.cameraController.mapToStage(this.f2975b);
                }
                float f = ((-enemy.loot.size) * 96.0f * 0.5f) + 48.0f;
                GameStateSystem gameStateSystem = ((LootSystem) this.f1759a).S.gameState;
                for (int i = 0; i < enemy.loot.size; i++) {
                    if (enemy.loot.items[i] != null) {
                        gameStateSystem.addLootIssuedPrizes(enemy.loot.items[i], this.f2975b.x + f + (96.0f * i), this.f2975b.y, 2);
                    }
                }
            }
            ((LootSystem) this.f1759a).y = (float) ((LootSystem) this.f1759a).S.statistics.getStatistic(StatisticsType.PT);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/LootSystem$OnMinerResourceChange.class */
    public static final class OnMinerResourceChange extends SerializableListener<LootSystem> implements Listener<MinerResourceChange> {
        private OnMinerResourceChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnMinerResourceChange(LootSystem lootSystem) {
            this.f1759a = lootSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(MinerResourceChange minerResourceChange) {
            ((LootSystem) this.f1759a).a(minerResourceChange.getMiner(), minerResourceChange.getResourceType(), minerResourceChange.getDelta(), minerResourceChange.isMined());
        }
    }
}

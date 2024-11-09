package com.prineside.tdi2.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.PredefinedCoreTileType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TriggeredActionType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.AnimatedImage;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile.class */
public final class CoreTile extends Tile {
    private static final TLog c = TLog.forClass(CoreTile.class);
    public static final int MAX_LEVEL = 256;
    public static final int FIXED_LEVEL_XP_REQUIREMENT = 1000;
    public static final int[] LEVEL_EXPERIENCE;
    public static final int[] LEVEL_EXPERIENCE_MILESTONES;
    private static final Color[] d;
    public static final int[] TIER_COLS;
    public static final int[] TIER_ROWS;
    public static final int[] LINK_DIRECTION_BITS;
    public PredefinedCoreTileType predefinedType;
    private String e;
    private String f;
    private Tier g;
    private float h;
    private boolean i;
    private Array<Upgrade> j;

    @NAGS
    private int k;
    private static final Color[] l;

    @NAGS
    public float timeDrawn;

    @NAGS
    public ParticleEffectPool.PooledEffect upgradeAvailableParticleEffect;
    public float doubleSpeedTimeLeft;
    private int m;
    private float n;
    private float o;
    private float p;
    private IntIntMap q;

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile$Tier.class */
    public enum Tier {
        REGULAR,
        RARE,
        LEGENDARY;

        public static final Tier[] values = values();
    }

    /* synthetic */ CoreTile(byte b2) {
        this();
    }

    static {
        int[] iArr = new int[257];
        LEVEL_EXPERIENCE = iArr;
        iArr[0] = 0;
        LEVEL_EXPERIENCE[1] = 0;
        for (int i = 2; i < 257; i++) {
            int i2 = i;
            LEVEL_EXPERIENCE[i2] = ((i2 - 2) * 10) + (((i - 2) / 10) * 10) + 180;
        }
        LEVEL_EXPERIENCE_MILESTONES = new int[257];
        int i3 = 0;
        for (int i4 = 0; i4 <= 256; i4++) {
            i3 += LEVEL_EXPERIENCE[i4];
            LEVEL_EXPERIENCE_MILESTONES[i4] = i3;
        }
        d = new Color[]{MaterialColor.RED.P500, MaterialColor.PINK.P500, MaterialColor.PURPLE.P400, MaterialColor.DEEP_PURPLE.P300, MaterialColor.BLUE.P500, MaterialColor.CYAN.P500, MaterialColor.GREEN.P500, MaterialColor.LIME.P500, MaterialColor.YELLOW.P500, MaterialColor.ORANGE.P500};
        TIER_COLS = new int[]{3, 4, 4};
        TIER_ROWS = new int[]{4, 4, 5};
        LINK_DIRECTION_BITS = new int[]{1, 2, 4, 8, 16, 32, 64, 128};
        l = new Color[4];
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile$LinkDirection.class */
    public enum LinkDirection {
        TOP_LEFT,
        TOP,
        TOP_RIGHT,
        LEFT,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM,
        BOTTOM_RIGHT;

        public static final LinkDirection[] values = values();

        public static LinkDirection getOpposite(LinkDirection linkDirection) {
            switch (linkDirection) {
                case TOP:
                    return BOTTOM;
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
                case BOTTOM:
                    return TOP;
                case TOP_LEFT:
                    return BOTTOM_RIGHT;
                case TOP_RIGHT:
                    return BOTTOM_LEFT;
                case BOTTOM_LEFT:
                    return TOP_RIGHT;
                case BOTTOM_RIGHT:
                    return TOP_LEFT;
                default:
                    return null;
            }
        }

        public static int getDeltaCol(LinkDirection linkDirection) {
            if (linkDirection == LEFT || linkDirection == TOP_LEFT || linkDirection == BOTTOM_LEFT) {
                return -1;
            }
            if (linkDirection == RIGHT || linkDirection == TOP_RIGHT || linkDirection == BOTTOM_RIGHT) {
                return 1;
            }
            return 0;
        }

        public static int getDeltaRow(LinkDirection linkDirection) {
            if (linkDirection == BOTTOM || linkDirection == BOTTOM_LEFT || linkDirection == BOTTOM_RIGHT) {
                return 1;
            }
            if (linkDirection == TOP || linkDirection == TOP_LEFT || linkDirection == TOP_RIGHT) {
                return -1;
            }
            return 0;
        }
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.predefinedType, PredefinedCoreTileType.class);
        kryo.writeObject(output, this.e);
        kryo.writeObjectOrNull(output, this.f, String.class);
        kryo.writeObject(output, this.g);
        output.writeFloat(this.h);
        output.writeBoolean(this.i);
        kryo.writeObject(output, this.j);
        output.writeFloat(this.doubleSpeedTimeLeft);
        output.writeVarInt(this.m, true);
        output.writeFloat(this.n);
        output.writeFloat(this.o);
        output.writeFloat(this.p);
        kryo.writeObject(output, this.q);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.predefinedType = (PredefinedCoreTileType) kryo.readObjectOrNull(input, PredefinedCoreTileType.class);
        this.e = (String) kryo.readObject(input, String.class);
        this.f = (String) kryo.readObjectOrNull(input, String.class);
        this.g = (Tier) kryo.readObject(input, Tier.class);
        this.h = input.readFloat();
        this.i = input.readBoolean();
        this.j = (Array) kryo.readObject(input, Array.class);
        this.doubleSpeedTimeLeft = input.readFloat();
        this.m = input.readVarInt(true);
        this.n = input.readFloat();
        this.o = input.readFloat();
        this.p = input.readFloat();
        this.q = (IntIntMap) kryo.readObject(input, IntIntMap.class);
    }

    private CoreTile() {
        super(TileType.CORE);
        this.predefinedType = null;
        this.e = "";
        this.g = Tier.REGULAR;
        this.h = 1.0f;
        this.i = false;
        this.j = new Array<>(Upgrade.class);
        this.k = -1;
        this.timeDrawn = 0.0f;
        this.doubleSpeedTimeLeft = 0.0f;
        this.m = 1;
        this.q = new IntIntMap();
    }

    @Override // com.prineside.tdi2.Tile
    public final void getData(IntArray intArray) {
        if (this.predefinedType != null) {
            intArray.add(ItemDataType.TILE_PREDEFINED_CORE_TYPE.ordinal(), this.predefinedType.ordinal());
        }
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        setExperience(this.n);
    }

    public final float getExperienceGeneration() {
        return this.predefinedType == null ? this.h : Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].h;
    }

    public final void setExperienceGeneration(float f) {
        if (this.predefinedType != null) {
            throw new IllegalStateException("can't edit predefined core");
        }
        this.h = f;
        this.k = -1;
    }

    public final boolean isXpLevelRequirementFixed() {
        return this.i;
    }

    public final void setXpLevelRequirementFixed(boolean z) {
        this.i = z;
        this.k = -1;
    }

    public final String getName() {
        if (this.predefinedType == null) {
            return this.e;
        }
        return Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].e;
    }

    public final Tier getTier() {
        if (this.predefinedType == null) {
            return this.g;
        }
        return Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].g;
    }

    public final void setTier(Tier tier) {
        if (this.predefinedType != null) {
            throw new IllegalStateException("can't edit predefined core");
        }
        this.g = tier;
        this.k = -1;
    }

    public final void setTierKeepLayout(Tier tier) {
        Upgrade upgrade;
        if (this.predefinedType != null) {
            throw new IllegalStateException("can't edit predefined core");
        }
        if (this.g != tier) {
            int i = TIER_COLS[this.g.ordinal()];
            int i2 = TIER_ROWS[this.g.ordinal()];
            int i3 = TIER_COLS[tier.ordinal()];
            int i4 = TIER_ROWS[tier.ordinal()];
            if (i != i3) {
                Upgrade[][] upgradeArr = new Upgrade[i2][i];
                for (int i5 = 0; i5 < i2; i5++) {
                    for (int i6 = 0; i6 < i; i6++) {
                        int upgradeIdx = getUpgradeIdx(this.g, i6, i5);
                        Upgrade upgrade2 = this.j.size > upgradeIdx ? this.j.get(upgradeIdx) : null;
                        Upgrade upgrade3 = upgrade2;
                        if (upgrade2 != null) {
                            upgradeArr[i5][i6] = upgrade3.cloneUpgrade();
                        }
                    }
                }
                this.j.clear();
                this.j.setSize(i3 * i4);
                for (int i7 = 0; i7 < i4; i7++) {
                    for (int i8 = 0; i8 < i3; i8++) {
                        if (i7 < upgradeArr.length && i8 < upgradeArr[i7].length && (upgrade = upgradeArr[i7][i8]) != null) {
                            this.j.set(getUpgradeIdx(tier, i8, i7), upgrade);
                        }
                    }
                }
            }
            this.g = tier;
            this.k = -1;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, ((getTier().ordinal() << 1) + 2) * 500));
        switch (getTier()) {
            case REGULAR:
                array.add(new ItemStack(Item.D.RESOURCE_SCALAR, 200));
                array.add(new ItemStack(Item.D.RESOURCE_VECTOR, 100));
                return;
            case RARE:
                array.add(new ItemStack(Item.D.RESOURCE_VECTOR, 200));
                array.add(new ItemStack(Item.D.RESOURCE_MATRIX, 100));
                array.add(new ItemStack(Item.D.RESOURCE_TENSOR, 50));
                return;
            case LEGENDARY:
                array.add(new ItemStack(Item.D.RESOURCE_MATRIX, 200));
                array.add(new ItemStack(Item.D.RESOURCE_TENSOR, 100));
                array.add(new ItemStack(Item.D.RESOURCE_INFIAR, 50));
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        switch (getRarity()) {
            case VERY_RARE:
                return 0.05d;
            case EPIC:
                return 0.6d;
            case LEGENDARY:
                return 7.5d;
            default:
                return 0.0d;
        }
    }

    public final Array<Upgrade> getUpgrades() {
        if (this.predefinedType == null) {
            return this.j;
        }
        return Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].j;
    }

    @Override // com.prineside.tdi2.Tile
    public final CharSequence getTitle() {
        return getName();
    }

    public final void setName(String str) {
        if (this.predefinedType != null) {
            throw new IllegalStateException("can't edit predefined core");
        }
        this.e = str;
    }

    public final String getIcon() {
        if (this.predefinedType == null) {
            return this.f;
        }
        return Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].f;
    }

    public final void setIcon(String str) {
        if (this.predefinedType != null) {
            throw new IllegalStateException("can't edit predefined core");
        }
        this.f = str;
        this.k = -1;
    }

    @Override // com.prineside.tdi2.Tile
    public final CharSequence getDescription() {
        return Game.i.tileManager.F.CORE.getTierDescription(getTier());
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        switch (getTier()) {
            case REGULAR:
                return RarityType.VERY_RARE;
            case RARE:
                return RarityType.EPIC;
            case LEGENDARY:
                return RarityType.LEGENDARY;
            default:
                return RarityType.COMMON;
        }
    }

    public final int getUpgradeCols() {
        return TIER_COLS[getTier().ordinal()];
    }

    public final int getUpgradeRows() {
        return TIER_ROWS[getTier().ordinal()];
    }

    public final boolean isValidUpgradePos(int i, int i2) {
        Tier tier = getTier();
        return i >= 0 && i < TIER_COLS[tier.ordinal()] && i2 >= 0 && i2 < TIER_ROWS[tier.ordinal()];
    }

    public final float getExperience() {
        return this.n;
    }

    public final int getLevel() {
        return this.m;
    }

    public final float getNextLevelExperience() {
        return this.o;
    }

    public final float getCurrentLevelExperience() {
        return this.p;
    }

    public final int getUpgradeInstallLevelByIdx(int i) {
        return this.q.get(i, 0);
    }

    public final int getUpgradeInstallLevel(int i, int i2) {
        return getUpgradeInstallLevelByIdx(getUpgradeIdx(getTier(), i, i2));
    }

    public final void setUpgradeInstallLevel(int i, int i2, int i3) {
        if (i3 != 0) {
            this.q.put(getUpgradeIdx(getTier(), i, i2), i3);
        } else {
            this.q.remove(getUpgradeIdx(getTier(), i, i2), 0);
        }
    }

    public final boolean hasSomethingToUpgrade() {
        int upgradeInstallLevel;
        int freeUpgradePoints = getFreeUpgradePoints();
        int upgradeCols = getUpgradeCols();
        int upgradeRows = getUpgradeRows();
        int money = this.S.gameState.getMoney();
        for (int i = 0; i < upgradeRows; i++) {
            for (int i2 = 0; i2 < upgradeCols; i2++) {
                Upgrade upgrade = getUpgrade(i2, i);
                if (upgrade != null && (upgradeInstallLevel = getUpgradeInstallLevel(i2, i)) < upgrade.upgradeLevels.size) {
                    Upgrade.UpgradeLevel upgradeLevel = upgrade.upgradeLevels.items[upgradeInstallLevel];
                    if ((upgrade.costsCoins ? upgradeLevel.price <= money : upgradeLevel.price <= freeUpgradePoints) && canUpgradeBeInstalled(i2, i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int getFreeUpgradePoints() {
        int level = getLevel() - 1;
        int i = 0;
        int upgradeCols = getUpgradeCols();
        int upgradeRows = getUpgradeRows();
        for (int i2 = 0; i2 < upgradeRows; i2++) {
            for (int i3 = 0; i3 < upgradeCols; i3++) {
                Upgrade upgrade = getUpgrade(i3, i2);
                if (upgrade != null && !upgrade.costsCoins) {
                    int upgradeInstallLevel = getUpgradeInstallLevel(i3, i2);
                    for (int i4 = 0; i4 < upgradeInstallLevel; i4++) {
                        i += upgrade.upgradeLevels.items[i4].price;
                    }
                }
            }
        }
        return level - i;
    }

    private int a(int i) {
        if (this.i) {
            return (i - 1) * 1000;
        }
        return LEVEL_EXPERIENCE_MILESTONES[i];
    }

    public final void setExperience(float f) {
        this.n = f;
        if (this.m != 256) {
            for (int i = this.m + 1; i <= 256 && ((int) this.n) >= a(i); i++) {
                this.m = i;
            }
            if (this.m != 256) {
                if (this.i) {
                    this.o = 1000.0f;
                } else {
                    this.o = LEVEL_EXPERIENCE[this.m + 1];
                }
            } else {
                this.o = 0.0f;
            }
        }
        this.p = this.n - a(this.m);
    }

    public static int getUpgradeIdx(Tier tier, int i, int i2) {
        int i3 = TIER_COLS[tier.ordinal()];
        int i4 = TIER_ROWS[tier.ordinal()];
        if (i < 0 || i >= i3) {
            throw new IllegalArgumentException("core tier has max " + (i3 - 1) + " col idx, " + i + " given");
        }
        if (i2 < 0 || i2 >= i4) {
            throw new IllegalArgumentException("core tier has max " + (i4 - 1) + " row idx, " + i2 + " given");
        }
        return (i2 * i3) + i;
    }

    public final Upgrade getUpgrade(int i, int i2) {
        Array<Upgrade> array = this.predefinedType == null ? this.j : Game.i.tileManager.F.CORE.f3108b[this.predefinedType.ordinal()].j;
        int upgradeIdx = getUpgradeIdx(getTier(), i, i2);
        if (array.size > upgradeIdx) {
            return array.items[upgradeIdx];
        }
        return null;
    }

    private int b() {
        if (this.k == -1) {
            this.k = generateSeedSalt();
        }
        return this.k;
    }

    public final boolean canUpgradeBeInstalled(int i, int i2) {
        Upgrade upgrade;
        if (getUpgrade(i, i2).starting) {
            return true;
        }
        int upgradeCols = getUpgradeCols();
        int upgradeRows = getUpgradeRows();
        for (LinkDirection linkDirection : LinkDirection.values) {
            int deltaCol = LinkDirection.getDeltaCol(linkDirection) + i;
            int deltaRow = LinkDirection.getDeltaRow(linkDirection) + i2;
            if (deltaCol >= 0 && deltaCol < upgradeCols && deltaRow >= 0 && deltaRow < upgradeRows && (upgrade = getUpgrade(deltaCol, deltaRow)) != null && getUpgradeInstallLevel(deltaCol, deltaRow) != 0 && upgrade.hasLink(LinkDirection.getOpposite(linkDirection))) {
                return true;
            }
        }
        return false;
    }

    public final void setUpgrade(int i, int i2, Upgrade upgrade) {
        if (this.predefinedType != null) {
            throw new IllegalStateException("Can't change upgrades of predefined Cores");
        }
        int upgradeIdx = getUpgradeIdx(getTier(), i, i2);
        while (this.j.size <= upgradeIdx) {
            this.j.add(null);
        }
        this.j.set(upgradeIdx, upgrade);
        this.k = -1;
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        int generateHash;
        int ordinal = ((((31 + (this.predefinedType == null ? 0 : this.predefinedType.ordinal())) * 31) + getTier().ordinal()) * 31) + ((int) (getExperienceGeneration() * 100.0f));
        Array<Upgrade> upgrades = getUpgrades();
        for (int i = 0; i < upgrades.size; i++) {
            if (upgrades.items[i] == null) {
                generateHash = ordinal * 31;
            } else {
                generateHash = (ordinal * 31) + upgrades.items[i].generateHash();
            }
            ordinal = generateHash;
        }
        return ordinal;
    }

    public final Color[] getBaseColors() {
        FastRandom.random.setSeed(b());
        l[0] = d[FastRandom.random.nextInt(d.length)];
        l[1] = d[FastRandom.random.nextInt(d.length)];
        l[2] = d[FastRandom.random.nextInt(d.length)];
        l[3] = d[FastRandom.random.nextInt(d.length)];
        return l;
    }

    public final Color getSphereColor() {
        switch (getTier()) {
            case REGULAR:
                return MaterialColor.PURPLE.P500;
            case RARE:
                return MaterialColor.ORANGE.P700;
            case LEGENDARY:
                return MaterialColor.CYAN.P600;
            default:
                return null;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        CoreTile coreTile = (CoreTile) tile;
        if (this.predefinedType != null && coreTile.predefinedType == this.predefinedType) {
            return true;
        }
        if (coreTile.predefinedType != this.predefinedType || coreTile.g != this.g || !coreTile.e.equals(this.e)) {
            return false;
        }
        if (coreTile.f == null && this.f != null) {
            return false;
        }
        if (coreTile.f != null && this.f == null) {
            return false;
        }
        if ((this.f != null && !coreTile.f.equals(this.f)) || coreTile.h != this.h || coreTile.i != this.i || coreTile.j.size != this.j.size) {
            return false;
        }
        for (int i = 0; i < this.j.size; i++) {
            if (this.j.items[i] == null && coreTile.j.items[i] != null) {
                return false;
            }
            if (this.j.items[i] == null || coreTile.j.items[i] != null) {
                if (this.j.items[i] != null && !this.j.items[i].sameAs(coreTile.j.items[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        Color[] baseColors = getBaseColors();
        for (int i = 0; i < 4; i++) {
            if ((getTier() != Tier.REGULAR || (i != 0 && i != 1)) && (getTier() != Tier.RARE || (i != 2 && i != 3))) {
                batch.setColor(baseColors[i]);
                batch.draw(Game.i.tileManager.F.CORE.f3107a[i], f, f2, f3, f4);
            }
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        ResourcePack.AtlasTextureRegion textureRegionSetThrowing;
        Preconditions.checkArgument(f >= 0.0f, "Delta time must be >= 0, %s provided", Float.valueOf(f));
        this.timeDrawn += f;
        float f6 = f4 * 0.0078125f;
        float f7 = f5 * 0.0078125f;
        batch.setColor(getSphereColor());
        if (this.timeDrawn < 0.0f) {
            throw new IllegalStateException("timeDrawn is " + this.timeDrawn);
        }
        batch.draw((TextureRegion) Game.i.tileManager.F.CORE.c.getKeyFrame(this.timeDrawn, true), f2 + (32.0f * f6), f3 + (32.0f * f7), 64.0f * f6, 64.0f * f7);
        String icon = getIcon();
        if (icon != null && (textureRegionSetThrowing = Game.i.assetManager.getTextureRegionSetThrowing(icon, false)) != null) {
            batch.setColor(1.0f, 1.0f, 1.0f, 0.65f);
            batch.draw(textureRegionSetThrowing, f2 + (42.24f * f6), f3 + (42.24f * f7), 43.52f * f6, 43.52f * f7);
        }
        if (this.S != null && this.S.gameState != null && drawMode == MapRenderingSystem.DrawMode.DETAILED) {
            int freeUpgradePoints = getFreeUpgradePoints();
            int i = freeUpgradePoints;
            if (freeUpgradePoints > 7) {
                i = 7;
            }
            for (int i2 = 0; i2 < i; i2++) {
                batch.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                batch.draw(AssetManager.TextureRegions.i().particlePentagon, f2 + (12.0f * f6), f3 + (12.0f * f7) + ((i2 << 7) * 0.125f * f7), 12.0f * f6, 12.0f * f7);
            }
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        ResourcePack.AtlasTextureRegion textureRegionSetThrowing;
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Color[] baseColors = getBaseColors();
        for (int i = 0; i < 4; i++) {
            if ((getTier() != Tier.REGULAR || (i != 0 && i != 1)) && (getTier() != Tier.RARE || (i != 2 && i != 3))) {
                Image image = new Image(Game.i.tileManager.F.CORE.f3107a[i]);
                image.setSize(128.0f * f2, 128.0f * f2);
                image.setColor(baseColors[i]);
                group.addActor(image);
            }
        }
        AnimatedImage animatedImage = new AnimatedImage(Game.i.tileManager.F.CORE.c);
        animatedImage.setSize(64.0f * f2, 64.0f * f2);
        animatedImage.setPosition(32.0f * f2, 32.0f * f2);
        animatedImage.setColor(getSphereColor());
        group.addActor(animatedImage);
        String icon = getIcon();
        if (icon != null && (textureRegionSetThrowing = Game.i.assetManager.getTextureRegionSetThrowing(icon, false)) != null) {
            Image image2 = new Image(textureRegionSetThrowing);
            image2.setSize(42.24f * f2, 42.24f * f2);
            image2.setPosition(42.66624f * f2, 42.666622f * f2);
            image2.setColor(1.0f, 1.0f, 1.0f, 0.65f);
            group.addActor(image2);
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("d");
        json.writeValue("n", this.e);
        if (this.f != null) {
            json.writeValue("icon", this.f);
        }
        json.writeValue("t", getTier());
        json.writeValue("eg", Float.valueOf(this.h));
        json.writeValue("flx", Boolean.valueOf(this.i));
        if (this.predefinedType != null) {
            json.writeValue("pt", this.predefinedType.name());
        } else {
            json.writeArrayStart(FlexmarkHtmlConverter.U_NODE);
            for (int i = this.j.size - 1; i >= 0 && this.j.items[i] == null; i--) {
                this.j.size = i;
            }
            for (int i2 = 0; i2 < this.j.size && i2 < getUpgradeCols() * getUpgradeRows(); i2++) {
                if (this.j.items[i2] == null) {
                    json.writeValue(null);
                } else {
                    json.writeObjectStart();
                    this.j.items[i2].toJson(json);
                    json.writeObjectEnd();
                }
            }
            json.writeArrayEnd();
        }
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_SPECIAL;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        int ordinal;
        if (itemSortingType == ItemSortingType.KIND) {
            if (this.predefinedType != null) {
                ordinal = 15000 + (this.predefinedType.ordinal() * 10);
            } else {
                ordinal = 15000 + 300 + (getRarity().ordinal() * 10);
            }
            return ordinal;
        }
        return (getRarity().ordinal() * 1000) + (5 - getTier().ordinal()) + 100;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        CoreTile coreTile = (CoreTile) tile;
        this.e = coreTile.e;
        this.f = coreTile.f;
        this.g = coreTile.g;
        this.h = coreTile.h;
        this.i = coreTile.i;
        this.j.clear();
        if (coreTile.predefinedType == null) {
            this.predefinedType = null;
            for (int i = 0; i < coreTile.j.size; i++) {
                if (coreTile.j.items[i] == null) {
                    this.j.add(null);
                } else {
                    this.j.add(coreTile.j.items[i].cloneUpgrade());
                }
            }
            return;
        }
        this.predefinedType = coreTile.predefinedType;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x033d, code lost:            r0.addActor(r0);     */
    @Override // com.prineside.tdi2.Tile
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void fillMapEditorMenu(com.prineside.tdi2.scene2d.ui.Table r9, final com.prineside.tdi2.ui.components.MapEditorItemInfoMenu r10) {
        /*
            Method dump skipped, instructions count: 1477
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.tiles.CoreTile.fillMapEditorMenu(com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.ui.components.MapEditorItemInfoMenu):void");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:94:0x08a9. Please report as an issue. */
    @Override // com.prineside.tdi2.Tile
    public final void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Predefined type");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        Array array = new Array();
        array.add("None");
        for (PredefinedCoreTileType predefinedCoreTileType : PredefinedCoreTileType.values) {
            array.add(predefinedCoreTileType.name());
        }
        selectBox.setItems(array);
        selectBox.setSelected(this.predefinedType == null ? "None" : this.predefinedType.name());
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                if (((String) selectBox.getSelected()).equals("None")) {
                    CoreTile.this.predefinedType = null;
                } else {
                    CoreTile.this.predefinedType = PredefinedCoreTileType.valueOf((String) selectBox.getSelected());
                }
                itemCreationOverlay.updateForm();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        if (this.predefinedType == null) {
            itemCreationOverlay.label("Tier");
            final SelectBox selectBox2 = new SelectBox(itemCreationOverlay.selectBoxStyle);
            Array array2 = new Array();
            for (Tier tier : Tier.values) {
                array2.add(tier.name());
            }
            selectBox2.setItems(array2);
            selectBox2.setSelected(getTier().name());
            selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.3
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    CoreTile.this.setTierKeepLayout(Tier.valueOf((String) selectBox2.getSelected()));
                    itemCreationOverlay.updateForm();
                }
            });
            itemCreationOverlay.selectBox(selectBox2);
            itemCreationOverlay.label("Name");
            itemCreationOverlay.textField(String.valueOf(getName()), str -> {
                try {
                    setName(str);
                    itemCreationOverlay.updateItemIcon();
                } catch (Exception unused) {
                    c.e("bad value: " + str, new Object[0]);
                }
            });
            itemCreationOverlay.label("Icon");
            itemCreationOverlay.textField(getIcon() == null ? "" : getIcon(), str2 -> {
                try {
                    if (str2.equals("")) {
                        str2 = null;
                    }
                    setIcon(str2);
                    itemCreationOverlay.updateItemIcon();
                } catch (Exception unused) {
                    c.e("bad value: " + str2, new Object[0]);
                }
            });
            itemCreationOverlay.label("XP generation");
            itemCreationOverlay.textField(String.valueOf(getExperienceGeneration()), str3 -> {
                try {
                    setExperienceGeneration(Float.valueOf(str3).floatValue());
                    itemCreationOverlay.updateItemIcon();
                } catch (Exception unused) {
                    c.e("bad value: " + str3, new Object[0]);
                }
            });
            itemCreationOverlay.toggle("Fixed XP levels (1000 XP)", isXpLevelRequirementFixed(), bool -> {
                setXpLevelRequirementFixed(bool.booleanValue());
                itemCreationOverlay.updateForm();
            });
            itemCreationOverlay.label("Upgrades");
            Table table = new Table();
            itemCreationOverlay.form.add(table).top().left().pad(10.0f).top().left().row();
            Group group = new Group();
            group.setTransform(false);
            int upgradeRows = getUpgradeRows();
            for (int i = 0; i < upgradeRows; i++) {
                int upgradeCols = getUpgradeCols();
                for (int i2 = 0; i2 < upgradeCols; i2++) {
                    Upgrade upgrade = getUpgrade(i2, i);
                    float f = (i2 * 64.0f) + (i2 * 32.0f);
                    float upgradeRows2 = ((getUpgradeRows() - 1) * 96.0f) - (i * 96.0f);
                    if (itemCreationOverlay.customIntA == i2 && itemCreationOverlay.customIntB == i) {
                        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image.setSize(68.0f, 68.0f);
                        image.setPosition(f - 2.0f, upgradeRows2 - 2.0f);
                        group.addActor(image);
                    }
                    Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image2.setSize(64.0f, 64.0f);
                    image2.setPosition(f, upgradeRows2);
                    image2.setTouchable(Touchable.enabled);
                    final int i3 = i2;
                    final int i4 = i;
                    image2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.tiles.CoreTile.4
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f2, float f3) {
                            itemCreationOverlay.customIntA = i3;
                            itemCreationOverlay.customIntB = i4;
                            itemCreationOverlay.updateForm();
                            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                        }
                    });
                    group.addActor(image2);
                    if (upgrade == null) {
                        image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                    } else {
                        if (upgrade.starting) {
                            image2.setColor(MaterialColor.LIGHT_GREEN.P800);
                        } else {
                            image2.setColor(MaterialColor.LIGHT_BLUE.P800);
                        }
                        if (upgrade.isAction) {
                            Group generateIcon = Game.i.triggeredActionManager.generateIcon(upgrade.getActionType(), 51.2f, new Color(1.0f, 1.0f, 1.0f, 0.78f));
                            generateIcon.setPosition(f + 6.4f, upgradeRows2 + 6.4f);
                            generateIcon.setTouchable(Touchable.disabled);
                            group.addActor(generateIcon);
                        } else {
                            Quad quad = new Quad(Game.i.gameValueManager.getStockValueConfig(upgrade.getGameValueType()).getIcon(), true);
                            quad.multiplyRegionColors(new Color(1.0f, 1.0f, 1.0f, 0.78f));
                            Image image3 = new Image(quad);
                            image3.setTouchable(Touchable.disabled);
                            image3.setPosition(f + 6.4f, upgradeRows2 + 6.4f);
                            image3.setSize(51.2f, 51.2f);
                            group.addActor(image3);
                        }
                        String str4 = "L" + upgrade.upgradeLevels.size;
                        if (upgrade.costsCoins) {
                            str4 = str4 + " <@icon-coin>";
                        }
                        String charSequence = Game.i.assetManager.replaceRegionAliasesWithChars(str4).toString();
                        Label label = new Label(charSequence, Game.i.assetManager.getLabelStyle(21));
                        label.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                        label.setPosition(f + 3.0f + 2.0f, upgradeRows2 - 2.0f);
                        label.setTouchable(Touchable.disabled);
                        group.addActor(label);
                        Label label2 = new Label(charSequence, Game.i.assetManager.getLabelStyle(21));
                        label2.setPosition(f + 3.0f, upgradeRows2);
                        label2.setTouchable(Touchable.disabled);
                        group.addActor(label2);
                        if (upgrade.hasLink(LinkDirection.LEFT)) {
                            Image image4 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-left"));
                            image4.setSize(32.0f, 32.0f);
                            image4.setPosition(f - 32.0f, (upgradeRows2 + 32.0f) - 16.0f);
                            group.addActor(image4);
                        }
                        if (upgrade.hasLink(LinkDirection.RIGHT)) {
                            Image image5 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-right"));
                            image5.setSize(32.0f, 32.0f);
                            image5.setPosition(f + 64.0f, (upgradeRows2 + 32.0f) - 16.0f);
                            group.addActor(image5);
                        }
                        if (upgrade.hasLink(LinkDirection.TOP)) {
                            Image image6 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-top"));
                            image6.setSize(32.0f, 32.0f);
                            image6.setPosition((f + 32.0f) - 16.0f, upgradeRows2 + 64.0f);
                            group.addActor(image6);
                        }
                        if (upgrade.hasLink(LinkDirection.BOTTOM)) {
                            Image image7 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-bottom"));
                            image7.setSize(32.0f, 32.0f);
                            image7.setPosition((f + 32.0f) - 16.0f, upgradeRows2 - 32.0f);
                            group.addActor(image7);
                        }
                        if (upgrade.hasLink(LinkDirection.TOP_LEFT)) {
                            Image image8 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-top-left"));
                            image8.setSize(32.0f, 32.0f);
                            image8.setPosition(f - 32.0f, upgradeRows2 + 64.0f);
                            group.addActor(image8);
                        }
                        if (upgrade.hasLink(LinkDirection.TOP_RIGHT)) {
                            Image image9 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-top-right"));
                            image9.setSize(32.0f, 32.0f);
                            image9.setPosition(f + 64.0f, upgradeRows2 + 64.0f);
                            group.addActor(image9);
                        }
                        if (upgrade.hasLink(LinkDirection.BOTTOM_LEFT)) {
                            Image image10 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-bottom-left"));
                            image10.setSize(32.0f, 32.0f);
                            image10.setPosition(f - 32.0f, upgradeRows2 - 32.0f);
                            group.addActor(image10);
                        }
                        if (upgrade.hasLink(LinkDirection.BOTTOM_RIGHT)) {
                            Image image11 = new Image(Game.i.assetManager.getDrawable("tiny-arrow-bottom-right"));
                            image11.setSize(32.0f, 32.0f);
                            image11.setPosition(f + 64.0f, upgradeRows2 - 32.0f);
                            group.addActor(image11);
                        }
                    }
                }
            }
            table.add((Table) group).size((getUpgradeCols() * 64.0f) + ((getUpgradeCols() - 1) * 32.0f), (getUpgradeRows() * 64.0f) + ((getUpgradeRows() - 1) * 32.0f)).padRight(16.0f);
            if (isValidUpgradePos(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB)) {
                Table table2 = new Table();
                table.add(table2);
                final Upgrade upgrade2 = getUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB);
                if (upgrade2 == null) {
                    table2.add((Table) new RectButton("Add upgrade", Game.i.assetManager.getLabelStyle(24), () -> {
                        setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, new Upgrade(false, false, GameValueType.COINS_GENERATION, null, null, 0, false));
                        itemCreationOverlay.updateForm();
                    })).size(200.0f, 48.0f);
                    return;
                }
                table2.add((Table) new RectButton("Remove upgrade", Game.i.assetManager.getLabelStyle(24), () -> {
                    setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, null);
                    itemCreationOverlay.updateForm();
                })).size(200.0f, 48.0f).top().left().row();
                table2.add(new LabelToggleButton("Is action", upgrade2.isAction, 24, 24.0f, false, bool2 -> {
                    upgrade2.isAction = bool2.booleanValue();
                    setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                    itemCreationOverlay.updateForm();
                })).top().left().padTop(8.0f).padBottom(8.0f).row();
                if (upgrade2.isAction) {
                    final SelectBox selectBox3 = new SelectBox(itemCreationOverlay.selectBoxStyle);
                    Array array3 = new Array(TriggeredActionType.class);
                    for (TriggeredActionType triggeredActionType : TriggeredActionType.values) {
                        array3.add(triggeredActionType);
                    }
                    selectBox3.setItems(array3);
                    selectBox3.setSelected(upgrade2.getActionType());
                    selectBox3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.5
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            upgrade2.setActionType((TriggeredActionType) selectBox3.getSelected());
                            CoreTile.this.setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            itemCreationOverlay.updateForm();
                        }
                    });
                    table2.add((Table) selectBox3).height(48.0f).width(320.0f).top().left().row();
                } else {
                    final SelectBox selectBox4 = new SelectBox(itemCreationOverlay.selectBoxStyle);
                    Array array4 = new Array(GameValueType.class);
                    for (GameValueType gameValueType : GameValueType.values) {
                        array4.add(gameValueType);
                    }
                    selectBox4.setItems(array4);
                    selectBox4.setSelected(upgrade2.getGameValueType());
                    selectBox4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.6
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            upgrade2.setGameValueType((GameValueType) selectBox4.getSelected());
                            CoreTile.this.setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            itemCreationOverlay.updateForm();
                        }
                    });
                    table2.add((Table) selectBox4).height(48.0f).width(320.0f).top().left().row();
                }
                Table table3 = new Table();
                table2.add(table3).row();
                Table table4 = new Table();
                table3.add(table4);
                Table table5 = new Table();
                table3.add(table5).padLeft(30.0f);
                table4.add(itemCreationOverlay.toggle(false, "Starting", upgrade2.starting, bool3 -> {
                    upgrade2.starting = bool3.booleanValue();
                    setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                    itemCreationOverlay.updateForm();
                })).height(48.0f).top().left().row();
                table4.add(itemCreationOverlay.toggle(false, "Costs coins", upgrade2.costsCoins, bool4 -> {
                    upgrade2.costsCoins = bool4.booleanValue();
                    setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                    itemCreationOverlay.updateForm();
                })).height(48.0f).top().left().row();
                Table table6 = new Table();
                table5.add(table6);
                for (final LinkDirection linkDirection : LinkDirection.values) {
                    String str5 = AssetManager.BLANK_REGION_NAME;
                    switch (linkDirection) {
                        case TOP:
                            str5 = "tiny-arrow-top";
                            break;
                        case LEFT:
                            str5 = "tiny-arrow-left";
                            break;
                        case RIGHT:
                            str5 = "tiny-arrow-right";
                            break;
                        case BOTTOM:
                            str5 = "tiny-arrow-bottom";
                            break;
                        case TOP_LEFT:
                            str5 = "tiny-arrow-top-left";
                            break;
                        case TOP_RIGHT:
                            str5 = "tiny-arrow-top-right";
                            break;
                        case BOTTOM_LEFT:
                            str5 = "tiny-arrow-bottom-left";
                            break;
                        case BOTTOM_RIGHT:
                            str5 = "tiny-arrow-bottom-right";
                            break;
                    }
                    Image image12 = new Image(Game.i.assetManager.getDrawable(str5));
                    if (!upgrade2.hasLink(linkDirection)) {
                        image12.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    }
                    image12.setTouchable(Touchable.enabled);
                    if (linkDirection == LinkDirection.RIGHT) {
                        table6.add();
                    }
                    table6.add((Table) image12).size(32.0f).pad(8.0f);
                    image12.addListener(new ClickListener() { // from class: com.prineside.tdi2.tiles.CoreTile.7
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f2, float f3) {
                            upgrade2.setHasLink(linkDirection, !upgrade2.hasLink(linkDirection));
                            CoreTile.this.setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            itemCreationOverlay.updateForm();
                        }
                    });
                    if (linkDirection == LinkDirection.TOP_RIGHT || linkDirection == LinkDirection.RIGHT) {
                        table6.row();
                    }
                }
                Table table7 = new Table();
                table2.row();
                table2.add(table7).top().left();
                table7.add();
                Label label3 = new Label("Delta", Game.i.assetManager.getLabelStyle(21));
                label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                table7.add((Table) label3).height(40.0f).padBottom(2.0f);
                Label label4 = new Label("Price", Game.i.assetManager.getLabelStyle(21));
                label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                table7.add((Table) label4).height(40.0f).padBottom(2.0f);
                table7.add();
                table7.row();
                for (int i5 = 0; i5 < upgrade2.upgradeLevels.size; i5++) {
                    final int i6 = i5;
                    Upgrade.UpgradeLevel upgradeLevel = upgrade2.upgradeLevels.items[i5];
                    table7.add((Table) new Label(new StringBuilder().append(i5 + 1).toString(), Game.i.assetManager.getLabelStyle(21))).minWidth(40.0f).padRight(8.0f);
                    final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(new StringBuilder().append(upgradeLevel.delta).toString(), itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.8
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            String text = textFieldXPlatform.getText();
                            try {
                                upgrade2.upgradeLevels.items[i6].delta = Float.parseFloat(text);
                                CoreTile.this.setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            } catch (Exception unused) {
                                CoreTile.c.e("bad value: " + text, new Object[0]);
                            }
                            itemCreationOverlay.updateItemIcon();
                        }
                    });
                    table7.add((Table) textFieldXPlatform).minWidth(60.0f).height(40.0f).padLeft(2.0f).padBottom(2.0f);
                    final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(new StringBuilder().append(upgradeLevel.price).toString(), itemCreationOverlay.textFieldStyle);
                    textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.CoreTile.9
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            String text = textFieldXPlatform2.getText();
                            try {
                                upgrade2.upgradeLevels.items[i6].price = Integer.parseInt(text);
                                CoreTile.this.setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            } catch (Exception unused) {
                                CoreTile.c.e("bad value: " + text, new Object[0]);
                            }
                            itemCreationOverlay.updateItemIcon();
                        }
                    });
                    table7.add((Table) textFieldXPlatform2).minWidth(60.0f).height(40.0f).padLeft(2.0f).padBottom(2.0f);
                    if (i5 == 0) {
                        table7.add();
                    } else {
                        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                            upgrade2.upgradeLevels.removeIndex(i6);
                            setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                            itemCreationOverlay.updateForm();
                        });
                        complexButton.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 40.0f, 40.0f);
                        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 32.0f, 32.0f);
                        table7.add((Table) complexButton).size(40.0f).padLeft(2.0f);
                    }
                    table7.row();
                }
                table7.add((Table) new RectButton("Add new level", Game.i.assetManager.getLabelStyle(21), () -> {
                    upgrade2.upgradeLevels.add(new Upgrade.UpgradeLevel(0.0f, 1));
                    setUpgrade(itemCreationOverlay.customIntA, itemCreationOverlay.customIntB, upgrade2);
                    itemCreationOverlay.updateForm();
                })).size(164.0f, 40.0f).colspan(3).top().left();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile$CoreTileFactory.class */
    public static class CoreTileFactory extends Tile.Factory.AbstractFactory<CoreTile> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion[] f3107a;

        /* renamed from: b, reason: collision with root package name */
        private CoreTile[] f3108b;
        private Animation<ResourcePack.AtlasTextureRegion> c;

        public CoreTileFactory() {
            super(TileType.CORE);
            this.f3107a = new TextureRegion[4];
            this.f3108b = new CoreTile[PredefinedCoreTileType.values.length];
            Iterator<JsonValue> iterator2 = new JsonReader().parse(Gdx.files.internal("res/core-tiles.json")).iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                try {
                    this.f3108b[PredefinedCoreTileType.valueOf(next.name).ordinal()] = fromJson(next);
                } catch (Exception e) {
                    CoreTile.c.e("failed to load predefined core tile '" + next.name + "'", e);
                }
            }
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            if (f < 0.4f) {
                return 0;
            }
            if (f < 0.6f) {
                return 10;
            }
            if (f < 0.8f) {
                return 2;
            }
            return 0;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3107a[0] = Game.i.assetManager.getTextureRegion("tile-type-core-left");
            this.f3107a[1] = Game.i.assetManager.getTextureRegion("tile-type-core-right");
            this.f3107a[2] = Game.i.assetManager.getTextureRegion("tile-type-core-top");
            this.f3107a[3] = Game.i.assetManager.getTextureRegion("tile-type-core-bottom");
            this.c = new Animation<>(0.033f, Game.i.assetManager.getTextureRegions("3d-sphere"), Animation.PlayMode.LOOP);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public CoreTile create() {
            return new CoreTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public CoreTile createRandom(float f, RandomXS128 randomXS128) {
            CoreTile create = create();
            if (f < 0.6f) {
                create.g = Tier.REGULAR;
            } else if (f < 0.8f) {
                create.g = Tier.RARE;
            } else {
                create.g = Tier.LEGENDARY;
            }
            switch (create.g) {
                case REGULAR:
                    switch (randomXS128.nextInt(2)) {
                        case 0:
                            create.predefinedType = PredefinedCoreTileType.ALPHA;
                            break;
                        case 1:
                            create.predefinedType = PredefinedCoreTileType.BETA;
                            break;
                    }
                case RARE:
                    switch (randomXS128.nextInt(2)) {
                        case 0:
                            create.predefinedType = PredefinedCoreTileType.DELTA;
                            break;
                        case 1:
                            create.predefinedType = PredefinedCoreTileType.THETA;
                            break;
                    }
                case LEGENDARY:
                    switch (randomXS128.nextInt(2)) {
                        case 0:
                            create.predefinedType = PredefinedCoreTileType.ZETA;
                            break;
                        case 1:
                            create.predefinedType = PredefinedCoreTileType.XI;
                            break;
                    }
            }
            return create;
        }

        public CharSequence getTierDescription(Tier tier) {
            switch (tier) {
                case REGULAR:
                    return Game.i.progressManager.getRarityName(RarityType.VERY_RARE);
                case RARE:
                    return Game.i.progressManager.getRarityName(RarityType.EPIC);
                case LEGENDARY:
                    return Game.i.progressManager.getRarityName(RarityType.LEGENDARY);
                default:
                    return null;
            }
        }

        public Color getTierColor(Tier tier) {
            switch (tier) {
                case REGULAR:
                    return MaterialColor.PURPLE.P400;
                case RARE:
                    return MaterialColor.ORANGE.P500;
                case LEGENDARY:
                    return MaterialColor.CYAN.P500;
                default:
                    return null;
            }
        }

        public float getExperienceGeneration(CoreTile coreTile, GameValueProvider gameValueProvider) {
            return coreTile.getExperienceGeneration() * ((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.CORES_LEVEL_UP_SPEED));
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public CoreTile fromJson(JsonValue jsonValue) {
            CoreTile coreTile = (CoreTile) super.fromJson(jsonValue);
            JsonValue jsonValue2 = jsonValue.get("d");
            if (jsonValue2.has("pt")) {
                coreTile.predefinedType = PredefinedCoreTileType.valueOf(jsonValue2.getString("pt"));
            }
            coreTile.e = jsonValue2.getString("n", "");
            coreTile.f = jsonValue2.getString("icon", null);
            coreTile.h = jsonValue2.getFloat("eg", 1.0f);
            coreTile.i = jsonValue2.getBoolean("flx", false);
            coreTile.g = Tier.valueOf(jsonValue2.getString("t"));
            JsonValue jsonValue3 = jsonValue2.get(FlexmarkHtmlConverter.U_NODE);
            if (jsonValue3 != null) {
                Iterator<JsonValue> iterator2 = jsonValue3.iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    if (next.isNull()) {
                        coreTile.j.add(null);
                    } else {
                        coreTile.j.add(Upgrade.fromJson(next));
                    }
                }
            }
            return coreTile;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile$Upgrade.class */
    public static class Upgrade implements KryoSerializable {
        public boolean starting;
        public boolean isAction;

        /* renamed from: a, reason: collision with root package name */
        private GameValueType f3111a;

        /* renamed from: b, reason: collision with root package name */
        private TriggeredActionType f3112b;
        public boolean costsCoins;
        public int links;
        public Array<UpgradeLevel> upgradeLevels = new Array<>(true, 1, UpgradeLevel.class);

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeBoolean(this.starting);
            output.writeBoolean(this.isAction);
            kryo.writeObjectOrNull(output, this.f3111a, GameValueType.class);
            kryo.writeObjectOrNull(output, this.f3112b, TriggeredActionType.class);
            output.writeBoolean(this.costsCoins);
            output.writeInt(this.links);
            kryo.writeObject(output, this.upgradeLevels);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.starting = input.readBoolean();
            this.isAction = input.readBoolean();
            this.f3111a = (GameValueType) kryo.readObjectOrNull(input, GameValueType.class);
            this.f3112b = (TriggeredActionType) kryo.readObjectOrNull(input, TriggeredActionType.class);
            this.costsCoins = input.readBoolean();
            this.links = input.readInt();
            this.upgradeLevels = (Array) kryo.readObject(input, Array.class);
        }

        public Upgrade() {
        }

        public Upgrade(boolean z, boolean z2, GameValueType gameValueType, TriggeredActionType triggeredActionType, Array<UpgradeLevel> array, int i, boolean z3) {
            this.starting = z;
            this.isAction = z2;
            this.f3112b = triggeredActionType;
            this.f3111a = gameValueType;
            this.links = i;
            this.costsCoins = z3;
            if (array == null) {
                Array<UpgradeLevel> array2 = new Array<>(UpgradeLevel.class);
                array = array2;
                array2.add(new UpgradeLevel(5.0f, 1));
            }
            this.upgradeLevels.addAll(array);
        }

        public GameValueType getGameValueType() {
            if (this.isAction) {
                throw new IllegalStateException("upgrade is an action");
            }
            return this.f3111a == null ? GameValueType.DUMMY : this.f3111a;
        }

        public void setGameValueType(GameValueType gameValueType) {
            if (gameValueType == null) {
                throw new IllegalStateException("type is null");
            }
            if (this.isAction) {
                throw new IllegalStateException("upgrade is an action");
            }
            this.f3111a = gameValueType;
        }

        public TriggeredActionType getActionType() {
            if (this.isAction) {
                return this.f3112b == null ? TriggeredActionType.DUMMY : this.f3112b;
            }
            throw new IllegalStateException("upgrade is a GameValue");
        }

        public void setActionType(TriggeredActionType triggeredActionType) {
            if (triggeredActionType == null) {
                throw new IllegalStateException("type is null");
            }
            if (!this.isAction) {
                throw new IllegalStateException("upgrade is a GameValue");
            }
            this.f3112b = triggeredActionType;
        }

        public void toJson(Json json) {
            json.writeValue("s", Boolean.valueOf(this.starting));
            json.writeValue("ia", Boolean.valueOf(this.isAction));
            if (this.f3111a != null) {
                json.writeValue("gv", this.f3111a.name());
            }
            if (this.f3112b != null) {
                json.writeValue(FlexmarkHtmlConverter.A_NODE, this.f3112b.name());
            }
            json.writeValue("l", Integer.valueOf(this.links));
            json.writeValue("cc", Boolean.valueOf(this.costsCoins));
            json.writeArrayStart(FlexmarkHtmlConverter.UL_NODE);
            for (int i = 0; i < this.upgradeLevels.size; i++) {
                json.writeObjectStart();
                this.upgradeLevels.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public static Upgrade fromJson(JsonValue jsonValue) {
            boolean z = jsonValue.getBoolean("s");
            boolean z2 = jsonValue.getBoolean("ia", false);
            GameValueType gameValueType = null;
            TriggeredActionType triggeredActionType = null;
            if (z2) {
                try {
                    triggeredActionType = TriggeredActionType.valueOf(jsonValue.getString(FlexmarkHtmlConverter.A_NODE, TriggeredActionType.GIVE_COINS.name()));
                } catch (Exception e) {
                    triggeredActionType = TriggeredActionType.GIVE_COINS;
                    CoreTile.c.e("failed to load action type from json (" + jsonValue.getString(FlexmarkHtmlConverter.A_NODE) + ", fallback to GIVE_COINS\n" + jsonValue, e);
                }
            } else {
                try {
                    gameValueType = GameValueType.valueOf(jsonValue.getString("gv", GameValueType.EMOJI_ENEMIES.name()));
                } catch (Exception e2) {
                    gameValueType = GameValueType.EMOJI_ENEMIES;
                    CoreTile.c.e("failed to load GV type from json (" + jsonValue.getString("gv") + ", fallback to EMOJI_ENEMIES\n" + jsonValue, e2);
                }
            }
            int i = jsonValue.getInt("l");
            boolean z3 = jsonValue.getBoolean("cc", false);
            Array array = new Array(UpgradeLevel.class);
            if (jsonValue.get("d") != null && jsonValue.get(FlexmarkHtmlConverter.P_NODE) != null) {
                float f = jsonValue.getFloat("d");
                int i2 = jsonValue.getInt(FlexmarkHtmlConverter.P_NODE);
                UpgradeLevel upgradeLevel = new UpgradeLevel();
                upgradeLevel.delta = f;
                upgradeLevel.price = i2;
                array.add(upgradeLevel);
            } else {
                Iterator<JsonValue> iterator2 = jsonValue.get(FlexmarkHtmlConverter.UL_NODE).iterator2();
                while (iterator2.hasNext()) {
                    array.add(UpgradeLevel.fromJson(iterator2.next()));
                }
            }
            return new Upgrade(z, z2, gameValueType, triggeredActionType, array, i, z3);
        }

        public boolean sameAs(Upgrade upgrade) {
            if (upgrade.starting != this.starting || upgrade.isAction != this.isAction) {
                return false;
            }
            if (this.isAction) {
                if (upgrade.f3112b != this.f3112b) {
                    return false;
                }
            } else if (upgrade.f3111a != this.f3111a) {
                return false;
            }
            if (upgrade.links != this.links || upgrade.costsCoins != this.costsCoins || upgrade.upgradeLevels.size != this.upgradeLevels.size) {
                return false;
            }
            for (int i = 0; i < this.upgradeLevels.size; i++) {
                if (upgrade.upgradeLevels.items[i].price != this.upgradeLevels.items[i].price || upgrade.upgradeLevels.items[i].delta != this.upgradeLevels.items[i].delta) {
                    return false;
                }
            }
            return true;
        }

        public int generateHash() {
            int ordinal = ((((((((31 + (this.starting ? 1 : 0)) * 31) + (this.isAction ? 1 : 0)) * 31) + (this.f3111a == null ? this.f3112b.ordinal() : this.f3111a.ordinal())) * 31) + this.links) * 31) + (this.costsCoins ? 1 : 0);
            for (int i = 0; i < this.upgradeLevels.size; i++) {
                UpgradeLevel upgradeLevel = this.upgradeLevels.items[i];
                ordinal = (((ordinal * 31) + upgradeLevel.price) * 31) + ((int) (upgradeLevel.delta * 1000.0f));
            }
            return ordinal;
        }

        public Upgrade cloneUpgrade() {
            Array array = new Array(UpgradeLevel.class);
            for (int i = 0; i < this.upgradeLevels.size; i++) {
                array.add(this.upgradeLevels.items[i].cpy());
            }
            return new Upgrade(this.starting, this.isAction, this.f3111a, this.f3112b, array, this.links, this.costsCoins);
        }

        public boolean hasLink(LinkDirection linkDirection) {
            return (this.links & CoreTile.LINK_DIRECTION_BITS[linkDirection.ordinal()]) != 0;
        }

        public void setHasLink(LinkDirection linkDirection, boolean z) {
            if (z) {
                this.links |= CoreTile.LINK_DIRECTION_BITS[linkDirection.ordinal()];
            } else {
                int i = CoreTile.LINK_DIRECTION_BITS[linkDirection.ordinal()];
                this.links = (this.links | i) ^ i;
            }
        }

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/CoreTile$Upgrade$UpgradeLevel.class */
        public static class UpgradeLevel implements KryoSerializable {
            public float delta;
            public int price;

            public UpgradeLevel() {
            }

            public UpgradeLevel(float f, int i) {
                this.delta = f;
                this.price = i;
            }

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public void write(Kryo kryo, Output output) {
                output.writeFloat(this.delta);
                output.writeVarInt(this.price, true);
            }

            @Override // com.esotericsoftware.kryo.KryoSerializable
            public void read(Kryo kryo, Input input) {
                this.delta = input.readFloat();
                this.price = input.readVarInt(true);
            }

            public UpgradeLevel cpy() {
                UpgradeLevel upgradeLevel = new UpgradeLevel();
                upgradeLevel.delta = this.delta;
                upgradeLevel.price = this.price;
                return upgradeLevel;
            }

            public void toJson(Json json) {
                json.writeValue("d", Float.valueOf(this.delta));
                json.writeValue(FlexmarkHtmlConverter.P_NODE, Integer.valueOf(this.price));
            }

            public static UpgradeLevel fromJson(JsonValue jsonValue) {
                UpgradeLevel upgradeLevel = new UpgradeLevel();
                upgradeLevel.delta = jsonValue.getFloat("d");
                upgradeLevel.price = jsonValue.getInt(FlexmarkHtmlConverter.P_NODE);
                return upgradeLevel;
            }
        }
    }
}

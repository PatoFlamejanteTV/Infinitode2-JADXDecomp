package com.prineside.tdi2.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/CaseItem.class */
public class CaseItem extends Item {
    public CaseType caseType;
    public boolean containsPapers;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private String f2225a;

    /* synthetic */ CaseItem(CaseType caseType, boolean z, byte b2) {
        this(caseType, z);
    }

    @Override // com.prineside.tdi2.Item, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.caseType);
        output.writeBoolean(this.containsPapers);
    }

    @Override // com.prineside.tdi2.Item, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.caseType = (CaseType) kryo.readObject(input, CaseType.class);
        this.containsPapers = input.readBoolean();
    }

    private CaseItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    private CaseItem(CaseType caseType, boolean z) {
        if (caseType == null) {
            throw new IllegalArgumentException("CaseType is null");
        }
        this.caseType = caseType;
        this.containsPapers = z;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        CaseItem caseItem = (CaseItem) item;
        return Item.D.F_CASE.create(caseItem.caseType, caseItem.containsPapers);
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.caseType.ordinal());
        data.add(ItemDataType.CASE_CONTAINS_PAPERS.ordinal(), this.containsPapers ? 1 : 0);
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "case_" + this.caseType;
    }

    public Color getColor() {
        switch (this.caseType) {
            case GREEN:
                return MaterialColor.GREEN.P500;
            case BLUE:
                return MaterialColor.INDIGO.P400;
            case ORANGE:
                return MaterialColor.ORANGE.P500;
            case PURPLE:
                return MaterialColor.PURPLE.P400;
            case CYAN:
                return MaterialColor.CYAN.P500;
            case BLUEPRINT:
                return MaterialColor.PINK.P600;
            default:
                return null;
        }
    }

    public int getItemCount() {
        switch (this.caseType) {
            case GREEN:
                return 4;
            case BLUE:
                return 5;
            case ORANGE:
                return 7;
            case PURPLE:
                return 6;
            case CYAN:
                return 8;
            case BLUEPRINT:
                return 3;
            default:
                return 0;
        }
    }

    public int[] getItemRarityChances() {
        switch (this.caseType) {
            case GREEN:
                return new int[]{88, 12, 0, 0, 0};
            case BLUE:
                return new int[]{70, 25, 5, 0, 0};
            case ORANGE:
                return new int[]{29, 38, 18, 12, 3};
            case PURPLE:
                return new int[]{51, 34, 12, 3, 0};
            case CYAN:
                return new int[]{14, 30, 24, 20, 12};
            case BLUEPRINT:
                return new int[]{100, 0, 0, 0, 0};
            default:
                return null;
        }
    }

    public RarityType getGuaranteedItemType() {
        switch (this.caseType) {
            case ORANGE:
                return RarityType.EPIC;
            case PURPLE:
                return RarityType.VERY_RARE;
            case CYAN:
                return RarityType.LEGENDARY;
            default:
                return null;
        }
    }

    public int getCasePriceInKeys() {
        switch (this.caseType) {
            case GREEN:
                return 0;
            case BLUE:
                return 10;
            case ORANGE:
                return 10;
            case PURPLE:
                return 10;
            case CYAN:
                return 10;
            default:
                return 0;
        }
    }

    public int getCasePriceInPapers() {
        switch (this.caseType) {
            case GREEN:
                return 7000;
            case BLUE:
                return 7500;
            case ORANGE:
                return 120000;
            case PURPLE:
                return 30000;
            default:
                return 0;
        }
    }

    public int getCasePriceInAccelerators() {
        if (this.caseType == CaseType.CYAN) {
            return 100;
        }
        return 0;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_CASE.create(this.caseType, this.containsPapers);
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeUnpacked() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Array<ItemStack> openPack(ProgressManager.InventoryStatistics inventoryStatistics) {
        BlueprintItem blueprintItem;
        int byChance;
        BlueprintItem blueprintItem2;
        int byChance2;
        BlueprintItem blueprintItem3;
        int byChance3;
        BlueprintItem blueprintItem4;
        Array<ItemStack> array = new Array<>(false, 8, ItemStack.class);
        RandomXS128 caseRandom = ProgressPrefs.i().progress.getCaseRandom(this.caseType);
        IntArray intArray = new IntArray();
        switch (this.caseType) {
            case GREEN:
                intArray.add(88, RarityType.COMMON.ordinal());
                intArray.add(12, RarityType.RARE.ordinal());
                boolean z = false;
                while (array.size != 4) {
                    if (this.containsPapers && !z) {
                        ProgressManager.addItemToStacksArray(array, Item.D.GREEN_PAPER, (8 + caseRandom.nextInt(5)) * 100);
                        z = true;
                    } else {
                        int byChance4 = PMath.getByChance(caseRandom, intArray);
                        ItemStack generateItemByRarity = ItemManager.generateItemByRarity(caseRandom, RarityType.values[byChance4], caseRandom.nextFloat(), 1.0f, Game.i.progressManager.isResourceOpened(ResourceType.SCALAR) ? 1.0f : 0.0f, 1.0f, (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE), 0.0f, byChance4 >= RarityType.RARE.ordinal() ? 2.0f : 0.0f, false, inventoryStatistics);
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (i < array.size) {
                                if (!array.items[i].getItem().sameAs(generateItemByRarity.getItem())) {
                                    i++;
                                } else {
                                    z2 = true;
                                }
                            }
                        }
                        if (!z2 && generateItemByRarity.getItem().getType() == ItemType.RESOURCE) {
                            if (!Game.i.progressManager.isResourceOpened(((ResourceItem) generateItemByRarity.getItem()).resourceType)) {
                                z2 = true;
                            }
                        }
                        if (!z2) {
                            array.add(generateItemByRarity);
                        }
                    }
                }
                if (this.containsPapers) {
                    int nextInt = caseRandom.nextInt(3);
                    ProgressManager.addItemToStacksArray(array, nextInt == 0 ? Item.D.BLUEPRINT_AGILITY : nextInt == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER, 2 + caseRandom.nextInt(3));
                    break;
                }
                break;
            case BLUE:
                intArray.add(70, RarityType.COMMON.ordinal());
                intArray.add(25, RarityType.RARE.ordinal());
                intArray.add(5, RarityType.VERY_RARE.ordinal());
                boolean z3 = false;
                while (array.size != 5) {
                    if (this.containsPapers && !z3) {
                        array.add(new ItemStack(Item.D.GREEN_PAPER, (20 + caseRandom.nextInt(11)) * 100));
                        z3 = true;
                    } else {
                        int byChance5 = PMath.getByChance(caseRandom, intArray);
                        float nextFloat = caseRandom.nextFloat();
                        float f = 1.0f;
                        if (byChance5 == RarityType.COMMON.ordinal()) {
                            f = 2.0f;
                        }
                        ItemStack generateItemByRarity2 = ItemManager.generateItemByRarity(caseRandom, RarityType.values[byChance5], nextFloat, f, 1.0f, 1.0f, (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE), 0.0f, byChance5 >= RarityType.VERY_RARE.ordinal() ? 2.0f : 0.0f, false, inventoryStatistics);
                        boolean z4 = false;
                        int i2 = 0;
                        while (true) {
                            if (i2 < array.size) {
                                if (!array.items[i2].getItem().sameAs(generateItemByRarity2.getItem())) {
                                    i2++;
                                } else {
                                    z4 = true;
                                }
                            }
                        }
                        if (!z4 && generateItemByRarity2.getItem().getType() == ItemType.RESOURCE) {
                            if (!Game.i.progressManager.isResourceOpened(((ResourceItem) generateItemByRarity2.getItem()).resourceType)) {
                                z4 = true;
                            }
                        }
                        if (!z4) {
                            array.add(generateItemByRarity2);
                        }
                    }
                }
                if (this.containsPapers) {
                    int nextInt2 = caseRandom.nextInt(3);
                    while (true) {
                        int i3 = nextInt2;
                        if (i3 == nextInt2) {
                            nextInt2 = caseRandom.nextInt(3);
                        } else {
                            if (nextInt2 == 0) {
                                blueprintItem4 = Item.D.BLUEPRINT_AGILITY;
                            } else {
                                blueprintItem4 = nextInt2 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER;
                            }
                            ProgressManager.addItemToStacksArray(array, blueprintItem4, 3 + caseRandom.nextInt(4));
                            ProgressManager.addItemToStacksArray(array, i3 == 0 ? Item.D.BLUEPRINT_AGILITY : i3 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER, 3 + caseRandom.nextInt(4));
                            break;
                        }
                    }
                }
                break;
            case ORANGE:
                intArray.add(29, RarityType.COMMON.ordinal());
                intArray.add(38, RarityType.RARE.ordinal());
                intArray.add(18, RarityType.VERY_RARE.ordinal());
                intArray.add(12, RarityType.EPIC.ordinal());
                intArray.add(3, RarityType.LEGENDARY.ordinal());
                boolean z5 = false;
                while (array.size != 7) {
                    if (this.containsPapers && !z5) {
                        array.add(new ItemStack(Item.D.GREEN_PAPER, (25 + caseRandom.nextInt(11)) * 500));
                        z5 = true;
                    } else {
                        if (array.size == 1) {
                            byChance2 = RarityType.EPIC.ordinal();
                        } else {
                            byChance2 = PMath.getByChance(caseRandom, intArray);
                        }
                        float nextFloat2 = caseRandom.nextFloat();
                        float f2 = 1.0f;
                        if (byChance2 == RarityType.COMMON.ordinal()) {
                            f2 = 8.0f;
                        } else if (byChance2 == RarityType.RARE.ordinal()) {
                            f2 = 4.0f;
                        } else if (byChance2 == RarityType.VERY_RARE.ordinal()) {
                            f2 = 2.0f;
                        }
                        ItemStack generateItemByRarity3 = ItemManager.generateItemByRarity(caseRandom, RarityType.values[byChance2], nextFloat2, f2, 1.0f, 1.0f, (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE), 0.0f, byChance2 >= RarityType.LEGENDARY.ordinal() ? 2.0f : 0.0f, false, inventoryStatistics);
                        boolean z6 = false;
                        int i4 = 0;
                        while (true) {
                            if (i4 < array.size) {
                                if (!array.items[i4].getItem().sameAs(generateItemByRarity3.getItem())) {
                                    i4++;
                                } else {
                                    z6 = true;
                                }
                            }
                        }
                        if (!z6 && generateItemByRarity3.getItem().getType() == ItemType.RESOURCE) {
                            if (!Game.i.progressManager.isResourceOpened(((ResourceItem) generateItemByRarity3.getItem()).resourceType)) {
                                z6 = true;
                            }
                        }
                        if (!z6) {
                            array.add(generateItemByRarity3);
                        }
                    }
                }
                if (this.containsPapers) {
                    int nextInt3 = caseRandom.nextInt(3);
                    while (true) {
                        int i5 = nextInt3;
                        if (i5 == nextInt3) {
                            nextInt3 = caseRandom.nextInt(3);
                        } else {
                            if (nextInt3 == 0) {
                                blueprintItem2 = Item.D.BLUEPRINT_AGILITY;
                            } else {
                                blueprintItem2 = nextInt3 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER;
                            }
                            ProgressManager.addItemToStacksArray(array, blueprintItem2, 6 + caseRandom.nextInt(7));
                            ProgressManager.addItemToStacksArray(array, i5 == 0 ? Item.D.BLUEPRINT_AGILITY : i5 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER, 6 + caseRandom.nextInt(7));
                            ProgressManager.addItemToStacksArray(array, Item.D.BLUEPRINT_SPECIAL_I, 1 + caseRandom.nextInt(3));
                            break;
                        }
                    }
                }
                break;
            case PURPLE:
                intArray.add(51, RarityType.COMMON.ordinal());
                intArray.add(34, RarityType.RARE.ordinal());
                intArray.add(12, RarityType.VERY_RARE.ordinal());
                intArray.add(3, RarityType.EPIC.ordinal());
                boolean z7 = false;
                while (array.size != 6) {
                    if (this.containsPapers && !z7) {
                        array.add(new ItemStack(Item.D.GREEN_PAPER, (25 + caseRandom.nextInt(11)) * 200));
                        z7 = true;
                    } else {
                        if (array.size == 1) {
                            byChance3 = RarityType.VERY_RARE.ordinal();
                        } else {
                            byChance3 = PMath.getByChance(caseRandom, intArray);
                        }
                        float nextFloat3 = caseRandom.nextFloat();
                        float f3 = 1.0f;
                        if (byChance3 == RarityType.COMMON.ordinal()) {
                            f3 = 4.0f;
                        } else if (byChance3 == RarityType.RARE.ordinal()) {
                            f3 = 2.0f;
                        }
                        ItemStack generateItemByRarity4 = ItemManager.generateItemByRarity(caseRandom, RarityType.values[byChance3], nextFloat3, f3, 1.0f, 1.0f, (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE), 0.0f, byChance3 >= RarityType.EPIC.ordinal() ? 2.0f : 0.0f, false, inventoryStatistics);
                        boolean z8 = false;
                        int i6 = 0;
                        while (true) {
                            if (i6 < array.size) {
                                if (!array.items[i6].getItem().sameAs(generateItemByRarity4.getItem())) {
                                    i6++;
                                } else {
                                    z8 = true;
                                }
                            }
                        }
                        if (!z8 && generateItemByRarity4.getItem().getType() == ItemType.RESOURCE) {
                            if (!Game.i.progressManager.isResourceOpened(((ResourceItem) generateItemByRarity4.getItem()).resourceType)) {
                                z8 = true;
                            }
                        }
                        if (!z8) {
                            array.add(generateItemByRarity4);
                        }
                    }
                }
                if (this.containsPapers) {
                    int nextInt4 = caseRandom.nextInt(3);
                    while (true) {
                        int i7 = nextInt4;
                        if (i7 == nextInt4) {
                            nextInt4 = caseRandom.nextInt(3);
                        } else {
                            if (nextInt4 == 0) {
                                blueprintItem3 = Item.D.BLUEPRINT_AGILITY;
                            } else {
                                blueprintItem3 = nextInt4 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER;
                            }
                            ProgressManager.addItemToStacksArray(array, blueprintItem3, 4 + caseRandom.nextInt(5));
                            ProgressManager.addItemToStacksArray(array, i7 == 0 ? Item.D.BLUEPRINT_AGILITY : i7 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER, 4 + caseRandom.nextInt(5));
                            ProgressManager.addItemToStacksArray(array, Item.D.BLUEPRINT_SPECIAL_I, 1);
                            break;
                        }
                    }
                }
                break;
            case CYAN:
                intArray.add(14, RarityType.COMMON.ordinal());
                intArray.add(30, RarityType.RARE.ordinal());
                intArray.add(24, RarityType.VERY_RARE.ordinal());
                intArray.add(20, RarityType.EPIC.ordinal());
                intArray.add(12, RarityType.LEGENDARY.ordinal());
                boolean z9 = false;
                while (array.size != 8) {
                    if (this.containsPapers && !z9) {
                        array.add(new ItemStack(Item.D.GREEN_PAPER, (28 + caseRandom.nextInt(5)) * 1000));
                        z9 = true;
                    } else {
                        if (array.size == 1) {
                            byChance = RarityType.LEGENDARY.ordinal();
                        } else {
                            byChance = PMath.getByChance(caseRandom, intArray);
                        }
                        float nextFloat4 = caseRandom.nextFloat();
                        float f4 = 1.0f;
                        if (byChance == RarityType.COMMON.ordinal()) {
                            f4 = 16.0f;
                        } else if (byChance == RarityType.RARE.ordinal()) {
                            f4 = 8.0f;
                        } else if (byChance == RarityType.VERY_RARE.ordinal()) {
                            f4 = 4.0f;
                        } else if (byChance == RarityType.EPIC.ordinal()) {
                            f4 = 2.0f;
                        }
                        ItemStack generateItemByRarity5 = ItemManager.generateItemByRarity(caseRandom, RarityType.values[byChance], nextFloat4, f4, 1.0f, 1.0f, (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE), 0.0f, 0.0f, false, inventoryStatistics);
                        boolean z10 = false;
                        int i8 = 0;
                        while (true) {
                            if (i8 < array.size) {
                                if (!array.items[i8].getItem().sameAs(generateItemByRarity5.getItem())) {
                                    i8++;
                                } else {
                                    z10 = true;
                                }
                            }
                        }
                        if (!z10 && generateItemByRarity5.getItem().getType() == ItemType.RESOURCE) {
                            if (!Game.i.progressManager.isResourceOpened(((ResourceItem) generateItemByRarity5.getItem()).resourceType)) {
                                z10 = true;
                            }
                        }
                        if (!z10) {
                            array.add(generateItemByRarity5);
                        }
                    }
                }
                if (this.containsPapers) {
                    int nextInt5 = caseRandom.nextInt(3);
                    while (true) {
                        int i9 = nextInt5;
                        if (i9 == nextInt5) {
                            nextInt5 = caseRandom.nextInt(3);
                        } else {
                            if (nextInt5 == 0) {
                                blueprintItem = Item.D.BLUEPRINT_AGILITY;
                            } else {
                                blueprintItem = nextInt5 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER;
                            }
                            ProgressManager.addItemToStacksArray(array, blueprintItem, 8 + caseRandom.nextInt(9));
                            ProgressManager.addItemToStacksArray(array, i9 == 0 ? Item.D.BLUEPRINT_AGILITY : i9 == 1 ? Item.D.BLUEPRINT_EXPERIENCE : Item.D.BLUEPRINT_POWER, 8 + caseRandom.nextInt(9));
                            ProgressManager.addItemToStacksArray(array, Item.D.BLUEPRINT_SPECIAL_II, 1);
                            break;
                        }
                    }
                }
                break;
            case BLUEPRINT:
                array.add(new ItemStack(Item.D.F_BLUEPRINT.create(BlueprintType.AGILITY), 38 + caseRandom.nextInt(19)));
                array.add(new ItemStack(Item.D.F_BLUEPRINT.create(BlueprintType.POWER), 38 + caseRandom.nextInt(19)));
                array.add(new ItemStack(Item.D.F_BLUEPRINT.create(BlueprintType.EXPERIENCE), 38 + caseRandom.nextInt(19)));
                break;
        }
        ProgressPrefs.i().requireSave();
        return array;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.CASE;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.PACKS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.P_DECRYPTED;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        if (this.f2225a == null) {
            this.f2225a = "item_title_CASE_" + this.caseType.name();
        }
        return Game.i.localeManager.i18n.get(this.f2225a);
    }

    public float getDecryptionTime() {
        switch (this.caseType) {
            case GREEN:
                return 5400.0f;
            case BLUE:
                return 10800.0f;
            case ORANGE:
                return 43200.0f;
            case PURPLE:
                return 21600.0f;
            case CYAN:
                return 64800.0f;
            case BLUEPRINT:
                return 21600.0f;
            default:
                return 86400.0f;
        }
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_CASE");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        switch (this.caseType) {
            case GREEN:
                return RarityType.COMMON;
            case BLUE:
                return RarityType.RARE;
            case ORANGE:
                return RarityType.EPIC;
            case PURPLE:
                return RarityType.VERY_RARE;
            case CYAN:
                return RarityType.LEGENDARY;
            case BLUEPRINT:
                return RarityType.EPIC;
            default:
                return RarityType.COMMON;
        }
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((CaseItem) item).caseType == this.caseType;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("caseType", this.caseType.toString());
        json.writeValue("containsPapers", Boolean.valueOf(this.containsPapers));
    }

    @Override // com.prineside.tdi2.Item
    public Drawable getIconDrawable() {
        return Item.D.F_CASE.f2230b[this.caseType.ordinal()][0];
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Drawable drawable = Item.D.F_CASE.f2230b[this.caseType.ordinal()][0];
        if (z) {
            Image image = new Image(drawable);
            image.setSize(f, f);
            image.setPosition(a(f), -a(f));
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(image);
        }
        Image image2 = new Image(drawable);
        image2.setSize(f, f);
        group.addActor(image2);
        return group;
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Case type");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems(CaseType.values);
        selectBox.setSelected(this.caseType);
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.items.CaseItem.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                itemCreationOverlay.currentItem = Item.D.F_CASE.create((CaseType) selectBox.getSelected(), CaseItem.this.containsPapers);
                itemCreationOverlay.updateItemIcon();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        itemCreationOverlay.toggle("Contains papers", this.containsPapers, bool -> {
            itemCreationOverlay.currentItem = Item.D.F_CASE.create(this.caseType, bool.booleanValue());
            itemCreationOverlay.updateForm();
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
        });
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/CaseItem$CaseItemFactory.class */
    public static class CaseItemFactory implements Item.Factory<CaseItem> {

        /* renamed from: a, reason: collision with root package name */
        private final CaseItem[][] f2229a = new CaseItem[CaseType.values.length][2];

        /* renamed from: b, reason: collision with root package name */
        private final Drawable[][] f2230b = new Drawable[CaseType.values.length][2];

        public CaseItemFactory() {
            for (CaseType caseType : CaseType.values) {
                this.f2229a[caseType.ordinal()][0] = new CaseItem(caseType, false, (byte) 0);
                this.f2229a[caseType.ordinal()][1] = new CaseItem(caseType, true, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2230b[CaseType.GREEN.ordinal()][0] = Game.i.assetManager.getDrawable("chest-green");
                this.f2230b[CaseType.BLUE.ordinal()][0] = Game.i.assetManager.getDrawable("chest-blue");
                this.f2230b[CaseType.ORANGE.ordinal()][0] = Game.i.assetManager.getDrawable("chest-orange");
                this.f2230b[CaseType.PURPLE.ordinal()][0] = Game.i.assetManager.getDrawable("chest-purple");
                this.f2230b[CaseType.CYAN.ordinal()][0] = Game.i.assetManager.getDrawable("chest-cyan");
                this.f2230b[CaseType.BLUEPRINT.ordinal()][0] = Game.i.assetManager.getDrawable("chest-pink");
                this.f2230b[CaseType.GREEN.ordinal()][1] = Game.i.assetManager.getDrawable("chest-green-encrypted");
                this.f2230b[CaseType.BLUE.ordinal()][1] = Game.i.assetManager.getDrawable("chest-blue-encrypted");
                this.f2230b[CaseType.ORANGE.ordinal()][1] = Game.i.assetManager.getDrawable("chest-orange-encrypted");
                this.f2230b[CaseType.PURPLE.ordinal()][1] = Game.i.assetManager.getDrawable("chest-purple-encrypted");
                this.f2230b[CaseType.CYAN.ordinal()][1] = Game.i.assetManager.getDrawable("chest-cyan-encrypted");
                this.f2230b[CaseType.BLUEPRINT.ordinal()][1] = Game.i.assetManager.getDrawable("chest-pink-encrypted");
            }
        }

        public CaseItem create(CaseType caseType, boolean z) {
            if (caseType == null) {
                throw new IllegalArgumentException("CaseType is null");
            }
            return this.f2229a[caseType.ordinal()][z ? (char) 1 : (char) 0];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public CaseItem fromJson(JsonValue jsonValue) {
            return create(CaseType.valueOf(jsonValue.getString("caseType")), jsonValue.getBoolean("containsPapers", true));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public CaseItem createDefault() {
            return create(CaseType.values[0], false);
        }
    }
}

package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.CraftRecipe;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.PredefinedCoreTileType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.items.AbilityItem;
import com.prineside.tdi2.items.AbilityTokenItem;
import com.prineside.tdi2.items.AcceleratorItem;
import com.prineside.tdi2.items.BitDustItem;
import com.prineside.tdi2.items.BlueprintItem;
import com.prineside.tdi2.items.CaseItem;
import com.prineside.tdi2.items.CaseKeyItem;
import com.prineside.tdi2.items.DatPaperItem;
import com.prineside.tdi2.items.DoubleGainShardItem;
import com.prineside.tdi2.items.GameValueGlobalItem;
import com.prineside.tdi2.items.GameValueLevelItem;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.GreenPaperItem;
import com.prineside.tdi2.items.LootBoostItem;
import com.prineside.tdi2.items.LuckyShotTokenItem;
import com.prineside.tdi2.items.OpenedResearchItem;
import com.prineside.tdi2.items.PrestigeDustItem;
import com.prineside.tdi2.items.PrestigeTokenItem;
import com.prineside.tdi2.items.RandomBarrierItem;
import com.prineside.tdi2.items.RandomTeleportItem;
import com.prineside.tdi2.items.RandomTileItem;
import com.prineside.tdi2.items.RarityBoostItem;
import com.prineside.tdi2.items.ResearchTokenItem;
import com.prineside.tdi2.items.ResearchTokenUsedItem;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.items.SkillPointItem;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.items.TrophyItem;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ItemManager.class */
public class ItemManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f2349a;
    public static final CaseType[] ENCRYPTED_CASES_QUEUE;

    /* renamed from: b, reason: collision with root package name */
    private final Item.Factory[] f2350b = new Item.Factory[ItemType.values.length];
    public Array<CraftRecipe> craftRecipes = new Array<>(CraftRecipe.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ItemManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ItemManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ItemManager read() {
            return Game.i.itemManager;
        }
    }

    static {
        String[] strArr = new String[ItemSubcategoryType.values().length];
        f2349a = strArr;
        strArr[ItemSubcategoryType.P_DECRYPTED.ordinal()] = "icon-lock-unlocked";
        f2349a[ItemSubcategoryType.P_ENCRYPTED.ordinal()] = "icon-lock";
        f2349a[ItemSubcategoryType.ME_ROADS.ordinal()] = "icon-road";
        f2349a[ItemSubcategoryType.ME_SOUNDS.ordinal()] = "icon-note";
        f2349a[ItemSubcategoryType.ME_SOURCES.ordinal()] = "icon-pickaxe";
        f2349a[ItemSubcategoryType.ME_PLATFORMS.ordinal()] = "icon-platform";
        f2349a[ItemSubcategoryType.ME_BASES.ordinal()] = "icon-flag";
        f2349a[ItemSubcategoryType.ME_SPAWNS.ordinal()] = "icon-skull";
        f2349a[ItemSubcategoryType.ME_SPECIAL.ordinal()] = "icon-star";
        f2349a[ItemSubcategoryType.M_CURRENCY.ordinal()] = "icon-money";
        f2349a[ItemSubcategoryType.M_TOKENS.ordinal()] = "icon-token";
        f2349a[ItemSubcategoryType.M_DUST.ordinal()] = "icon-dust";
        f2349a[ItemSubcategoryType.M_BLUEPRINT.ordinal()] = "icon-blueprint";
        f2349a[ItemSubcategoryType.M_RESOURCE.ordinal()] = "resource-scalar";
        f2349a[ItemSubcategoryType.O_OTHER.ordinal()] = "icon-cubes-stacked";
        ENCRYPTED_CASES_QUEUE = new CaseType[120];
    }

    public ItemManager() {
        for (int i = 0; i < 120; i++) {
            ENCRYPTED_CASES_QUEUE[i] = CaseType.GREEN;
        }
        float f = 3.0f;
        while (true) {
            float f2 = f;
            if (f2 >= 120.0f) {
                break;
            }
            ENCRYPTED_CASES_QUEUE[MathUtils.floor(f2)] = CaseType.BLUE;
            f = f2 + 2.5f;
        }
        for (int i2 = 18; i2 < 120; i2 += 12) {
            ENCRYPTED_CASES_QUEUE[i2] = CaseType.PURPLE;
        }
        for (int i3 = 36; i3 < 120; i3 += 24) {
            ENCRYPTED_CASES_QUEUE[i3] = CaseType.ORANGE;
        }
        ENCRYPTED_CASES_QUEUE[119] = CaseType.CYAN;
        this.f2350b[ItemType.ACCELERATOR.ordinal()] = new AcceleratorItem.AcceleratorItemFactory();
        this.f2350b[ItemType.GREEN_PAPER.ordinal()] = new GreenPaperItem.GreenPaperItemFactory();
        this.f2350b[ItemType.TILE.ordinal()] = new TileItem.TileItemFactory();
        this.f2350b[ItemType.GATE.ordinal()] = new GateItem.GateItemFactory();
        this.f2350b[ItemType.RANDOM_TILE.ordinal()] = new RandomTileItem.RandomTileItemFactory();
        this.f2350b[ItemType.RANDOM_BARRIER.ordinal()] = new RandomBarrierItem.RandomBarrierItemFactory();
        this.f2350b[ItemType.RANDOM_TELEPORT.ordinal()] = new RandomTeleportItem.RandomTeleportItemFactory();
        this.f2350b[ItemType.RESOURCE.ordinal()] = new ResourceItem.ResourceItemFactory();
        this.f2350b[ItemType.CASE.ordinal()] = new CaseItem.CaseItemFactory();
        this.f2350b[ItemType.TROPHY.ordinal()] = new TrophyItem.TrophyItemFactory();
        this.f2350b[ItemType.GAME_VALUE_GLOBAL.ordinal()] = new GameValueGlobalItem.GameValueGlobalItemFactory();
        this.f2350b[ItemType.GAME_VALUE_LEVEL.ordinal()] = new GameValueLevelItem.GameValueLevelItemFactory();
        this.f2350b[ItemType.OPENED_RESEARCH.ordinal()] = new OpenedResearchItem.OpenedResearchItemFactory();
        this.f2350b[ItemType.STAR.ordinal()] = new StarItem.StarItemFactory();
        this.f2350b[ItemType.SKILL_POINT.ordinal()] = new SkillPointItem.SkillPointItemFactory();
        this.f2350b[ItemType.ABILITY.ordinal()] = new AbilityItem.AbilityItemFactory();
        this.f2350b[ItemType.BIT_DUST.ordinal()] = new BitDustItem.BitDustItemFactory();
        this.f2350b[ItemType.PRESTIGE_DUST.ordinal()] = new PrestigeDustItem.PrestigeDustItemFactory();
        this.f2350b[ItemType.BLUEPRINT.ordinal()] = new BlueprintItem.BlueprintItemFactory();
        this.f2350b[ItemType.ABILITY_TOKEN.ordinal()] = new AbilityTokenItem.AbilityTokenItemFactory();
        this.f2350b[ItemType.CASE_KEY.ordinal()] = new CaseKeyItem.CaseKeyItemFactory();
        this.f2350b[ItemType.RARITY_BOOST.ordinal()] = new RarityBoostItem.RarityBoostItemFactory();
        this.f2350b[ItemType.LOOT_BOOST.ordinal()] = new LootBoostItem.LootBoostItemFactory();
        this.f2350b[ItemType.PRESTIGE_TOKEN.ordinal()] = new PrestigeTokenItem.PrestigeTokenItemFactory();
        this.f2350b[ItemType.RESEARCH_TOKEN.ordinal()] = new ResearchTokenItem.ResearchTokenItemFactory();
        this.f2350b[ItemType.RESEARCH_TOKEN_USED.ordinal()] = new ResearchTokenUsedItem.ResearchTokenUsedItemFactory();
        this.f2350b[ItemType.LUCKY_SHOT_TOKEN.ordinal()] = new LuckyShotTokenItem.LuckyShotTokenItemFactory();
        this.f2350b[ItemType.DOUBLE_GAIN_SHARD.ordinal()] = new DoubleGainShardItem.DoubleGainShardItemFactory();
        this.f2350b[ItemType.DAT_PAPER.ordinal()] = new DatPaperItem.DatPaperItemFactory();
        for (ItemType itemType : ItemType.values) {
            if (this.f2350b[itemType.ordinal()] == null) {
                throw new RuntimeException("Not all item factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        super.setup();
        for (Item.Factory factory : this.f2350b) {
            factory.setup();
        }
        Item.D.setup();
        this.craftRecipes.clear();
        BlueprintType[] blueprintTypeArr = {BlueprintType.SPECIAL_II, BlueprintType.SPECIAL_III, BlueprintType.SPECIAL_IV};
        for (int i = 0; i < 3; i++) {
            BlueprintType blueprintType = blueprintTypeArr[i];
            CraftRecipe craftRecipe = new CraftRecipe();
            this.craftRecipes.add(craftRecipe);
            craftRecipe.result = new ItemStack(Item.D.F_BLUEPRINT.create(blueprintType), 1);
            int i2 = 0;
            switch (blueprintType) {
                case SPECIAL_II:
                    craftRecipe.setStockTime(300.0f);
                    i2 = 500;
                    break;
                case SPECIAL_III:
                    craftRecipe.setStockTime(1800.0f);
                    i2 = 3000;
                    break;
                case SPECIAL_IV:
                    craftRecipe.setStockTime(7200.0f);
                    i2 = 15000;
                    break;
            }
            craftRecipe.setStockMaxQueueStack(10);
            craftRecipe.ingredients.add(new CraftRecipe.Ingredient(ItemType.GREEN_PAPER, i2, new int[0]));
            BlueprintType blueprintType2 = BlueprintType.values[blueprintType.ordinal() - 1];
            CraftRecipe.Ingredient ingredient = new CraftRecipe.Ingredient(ItemType.BLUEPRINT, 5, new int[]{ItemDataType.TYPE.ordinal(), blueprintType2.ordinal()});
            ingredient.minCount = 2;
            ingredient.exampleItems = new Item[]{Item.D.F_BLUEPRINT.create(blueprintType2)};
            craftRecipe.ingredients.add(ingredient);
        }
        BlueprintType[] blueprintTypeArr2 = {BlueprintType.SPECIAL_I, BlueprintType.SPECIAL_II, BlueprintType.SPECIAL_III};
        for (int i3 = 0; i3 < 3; i3++) {
            BlueprintType blueprintType3 = blueprintTypeArr2[i3];
            CraftRecipe craftRecipe2 = new CraftRecipe();
            this.craftRecipes.add(craftRecipe2);
            craftRecipe2.result = new ItemStack(Item.D.F_BLUEPRINT.create(blueprintType3), 3);
            int i4 = 0;
            switch (blueprintType3) {
                case SPECIAL_II:
                    craftRecipe2.setStockTime(300.0f);
                    i4 = 500;
                    break;
                case SPECIAL_III:
                    craftRecipe2.setStockTime(1800.0f);
                    i4 = 3000;
                    break;
                case SPECIAL_I:
                    craftRecipe2.setStockTime(60.0f);
                    i4 = 100;
                    break;
            }
            craftRecipe2.setStockMaxQueueStack(10);
            craftRecipe2.ingredients.add(new CraftRecipe.Ingredient(ItemType.GREEN_PAPER, i4, new int[0]));
            BlueprintType blueprintType4 = BlueprintType.values[blueprintType3.ordinal() + 1];
            CraftRecipe.Ingredient ingredient2 = new CraftRecipe.Ingredient(ItemType.BLUEPRINT, 1, new int[]{ItemDataType.TYPE.ordinal(), blueprintType4.ordinal()});
            ingredient2.exampleItems = new Item[]{Item.D.F_BLUEPRINT.create(blueprintType4)};
            craftRecipe2.ingredients.add(ingredient2);
        }
        CraftRecipe craftRecipe3 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe3);
        craftRecipe3.result = new ItemStack(Item.D.F_CASE.create(CaseType.BLUEPRINT, false), 1);
        craftRecipe3.setStockTime(14400.0f);
        craftRecipe3.setStockMaxQueueStack(3);
        CraftRecipe.Ingredient ingredient3 = new CraftRecipe.Ingredient(ItemType.BLUEPRINT, 1, new int[]{ItemDataType.TYPE.ordinal(), BlueprintType.SPECIAL_III.ordinal()});
        ingredient3.exampleItems = new Item[]{Item.D.F_BLUEPRINT.create(BlueprintType.SPECIAL_III)};
        craftRecipe3.ingredients.add(ingredient3);
        ResourceType[] resourceTypeArr = {ResourceType.VECTOR, ResourceType.MATRIX, ResourceType.TENSOR, ResourceType.INFIAR};
        for (int i5 = 0; i5 < 4; i5++) {
            final ResourceType resourceType = resourceTypeArr[i5];
            CraftRecipe craftRecipe4 = new CraftRecipe(this) { // from class: com.prineside.tdi2.managers.ItemManager.1
                @Override // com.prineside.tdi2.CraftRecipe
                public boolean isAvailable() {
                    return Game.i.progressManager.isResourceOpened(resourceType);
                }
            };
            this.craftRecipes.add(craftRecipe4);
            craftRecipe4.result = new ItemStack(Item.D.F_RESOURCE.create(resourceType), 1);
            int i6 = 0;
            switch (resourceType) {
                case VECTOR:
                    craftRecipe4.setStockTime(5.0f);
                    i6 = 1;
                    break;
                case MATRIX:
                    craftRecipe4.setStockTime(10.0f);
                    i6 = 2;
                    break;
                case TENSOR:
                    craftRecipe4.setStockTime(15.0f);
                    i6 = 3;
                    break;
                case INFIAR:
                    craftRecipe4.setStockTime(20.0f);
                    i6 = 4;
                    break;
            }
            craftRecipe4.setStockMaxQueueStack(1000);
            craftRecipe4.ingredients.add(new CraftRecipe.Ingredient(ItemType.GREEN_PAPER, i6));
            ResourceType resourceType2 = ResourceType.values[resourceType.ordinal() - 1];
            CraftRecipe.Ingredient ingredient4 = new CraftRecipe.Ingredient(ItemType.RESOURCE, 3, new int[]{ItemDataType.TYPE.ordinal(), resourceType2.ordinal()});
            ingredient4.minCount = 2;
            ingredient4.exampleItems = new Item[]{Item.D.F_RESOURCE.create(resourceType2)};
            craftRecipe4.ingredients.add(ingredient4);
        }
        CraftRecipe craftRecipe5 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe5);
        craftRecipe5.result = new ItemStack(Item.D.BLUEPRINT_SPECIAL_I, 1);
        craftRecipe5.setStockTime(600.0f);
        craftRecipe5.setStockMaxQueueStack(5);
        CraftRecipe.Ingredient ingredient5 = new CraftRecipe.Ingredient(ItemType.BLUEPRINT, 20, RarityType.COMMON);
        ingredient5.minCount = 5;
        ingredient5.exampleItems = new Item[]{Item.D.BLUEPRINT_EXPERIENCE, Item.D.BLUEPRINT_AGILITY, Item.D.BLUEPRINT_POWER};
        craftRecipe5.ingredients.add(ingredient5);
        CraftRecipe craftRecipe6 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe6);
        craftRecipe6.result = new ItemStack(Item.D.PRESTIGE_TOKEN, 1);
        craftRecipe6.setStockTime(360.0f);
        craftRecipe6.setStockMaxQueueStack(100);
        CraftRecipe.Ingredient ingredient6 = new CraftRecipe.Ingredient(ItemType.PRESTIGE_DUST, 1500);
        ingredient6.exampleItems = new Item[]{Item.D.PRESTIGE_DUST};
        craftRecipe6.ingredients.add(ingredient6);
        craftRecipe6.ingredients.add(new CraftRecipe.Ingredient(ItemType.GREEN_PAPER, 1000));
        CraftRecipe craftRecipe7 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe7);
        craftRecipe7.result = new ItemStack(Item.D.RESEARCH_TOKEN, 1);
        craftRecipe7.setStockTime(3600.0f);
        craftRecipe7.setStockMaxQueueStack(10);
        craftRecipe7.ingredients.add(new CraftRecipe.Ingredient(ItemType.PRESTIGE_TOKEN, 100));
        craftRecipe7.ingredients.add(new CraftRecipe.Ingredient(ItemType.LUCKY_SHOT_TOKEN, 7));
        craftRecipe7.ingredients.add(new CraftRecipe.Ingredient(ItemType.ACCELERATOR, 80));
        CraftRecipe craftRecipe8 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe8);
        craftRecipe8.result = new ItemStack(Item.D.RESEARCH_TOKEN, 1);
        craftRecipe8.setStockTime(3600.0f);
        craftRecipe8.setStockMaxQueueStack(10);
        CraftRecipe.Ingredient ingredient7 = new CraftRecipe.Ingredient(ItemType.RESEARCH_TOKEN_USED, 2);
        ingredient7.ignoresDiscounts = true;
        craftRecipe8.ingredients.add(ingredient7);
        CoreTile.CoreTileFactory coreTileFactory = (CoreTile.CoreTileFactory) Game.i.tileManager.getFactory(TileType.CORE);
        CoreTile create = coreTileFactory.create();
        create.predefinedType = PredefinedCoreTileType.DELTA;
        CraftRecipe craftRecipe9 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe9);
        craftRecipe9.result = new ItemStack(Item.D.F_TILE.create(create), 1);
        craftRecipe9.setStockTime(14400.0f);
        craftRecipe9.setStockMaxQueueStack(1);
        CraftRecipe.Ingredient ingredient8 = new CraftRecipe.Ingredient(ItemType.TILE, 10, new int[]{ItemDataType.TYPE.ordinal(), TileType.CORE.ordinal(), ItemDataType.TILE_PREDEFINED_CORE_TYPE.ordinal(), PredefinedCoreTileType.ALPHA.ordinal()});
        ingredient8.minCount = 2;
        CoreTile create2 = coreTileFactory.create();
        create2.predefinedType = PredefinedCoreTileType.ALPHA;
        ingredient8.exampleItems = new Item[]{Item.D.F_TILE.create(create2)};
        craftRecipe9.ingredients.add(ingredient8);
        CoreTile create3 = coreTileFactory.create();
        create3.predefinedType = PredefinedCoreTileType.ZETA;
        CraftRecipe craftRecipe10 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe10);
        craftRecipe10.result = new ItemStack(Item.D.F_TILE.create(create3), 1);
        craftRecipe10.setStockTime(64800.0f);
        craftRecipe10.setStockMaxQueueStack(1);
        CraftRecipe.Ingredient ingredient9 = new CraftRecipe.Ingredient(ItemType.TILE, 10, new int[]{ItemDataType.TYPE.ordinal(), TileType.CORE.ordinal(), ItemDataType.TILE_PREDEFINED_CORE_TYPE.ordinal(), PredefinedCoreTileType.DELTA.ordinal()});
        ingredient9.minCount = 2;
        CoreTile create4 = coreTileFactory.create();
        create4.predefinedType = PredefinedCoreTileType.DELTA;
        ingredient9.exampleItems = new Item[]{Item.D.F_TILE.create(create4)};
        craftRecipe10.ingredients.add(ingredient9);
        CoreTile create5 = coreTileFactory.create();
        create5.predefinedType = PredefinedCoreTileType.THETA;
        CraftRecipe craftRecipe11 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe11);
        craftRecipe11.result = new ItemStack(Item.D.F_TILE.create(create5), 1);
        craftRecipe11.setStockTime(14400.0f);
        craftRecipe11.setStockMaxQueueStack(1);
        CraftRecipe.Ingredient ingredient10 = new CraftRecipe.Ingredient(ItemType.TILE, 10, new int[]{ItemDataType.TYPE.ordinal(), TileType.CORE.ordinal(), ItemDataType.TILE_PREDEFINED_CORE_TYPE.ordinal(), PredefinedCoreTileType.BETA.ordinal()});
        ingredient10.minCount = 2;
        CoreTile create6 = coreTileFactory.create();
        create6.predefinedType = PredefinedCoreTileType.BETA;
        ingredient10.exampleItems = new Item[]{Item.D.F_TILE.create(create6)};
        craftRecipe11.ingredients.add(ingredient10);
        CoreTile create7 = coreTileFactory.create();
        create7.predefinedType = PredefinedCoreTileType.XI;
        CraftRecipe craftRecipe12 = new CraftRecipe();
        this.craftRecipes.add(craftRecipe12);
        craftRecipe12.result = new ItemStack(Item.D.F_TILE.create(create7), 1);
        craftRecipe12.setStockTime(64800.0f);
        craftRecipe12.setStockMaxQueueStack(1);
        CraftRecipe.Ingredient ingredient11 = new CraftRecipe.Ingredient(ItemType.TILE, 10, new int[]{ItemDataType.TYPE.ordinal(), TileType.CORE.ordinal(), ItemDataType.TILE_PREDEFINED_CORE_TYPE.ordinal(), PredefinedCoreTileType.THETA.ordinal()});
        ingredient11.minCount = 2;
        CoreTile create8 = coreTileFactory.create();
        create8.predefinedType = PredefinedCoreTileType.THETA;
        ingredient11.exampleItems = new Item[]{Item.D.F_TILE.create(create8)};
        craftRecipe12.ingredients.add(ingredient11);
        PlatformTile.SpaceTileFactory spaceTileFactory = (PlatformTile.SpaceTileFactory) Game.i.tileManager.getFactory(TileType.PLATFORM);
        for (SpaceTileBonusType spaceTileBonusType : SpaceTileBonusType.values) {
            for (int i7 = 2; i7 <= 5; i7++) {
                PlatformTile create9 = spaceTileFactory.create();
                create9.bonusType = spaceTileBonusType;
                create9.bonusLevel = i7;
                CraftRecipe craftRecipe13 = new CraftRecipe();
                this.craftRecipes.add(craftRecipe13);
                craftRecipe13.result = new ItemStack(Item.D.F_TILE.create(create9), 1);
                int i8 = 0;
                switch (i7) {
                    case 2:
                        i8 = 500;
                        craftRecipe13.setStockTime(15.0f);
                        break;
                    case 3:
                        i8 = 1500;
                        craftRecipe13.setStockTime(60.0f);
                        break;
                    case 4:
                        i8 = 5000;
                        craftRecipe13.setStockTime(180.0f);
                        break;
                    case 5:
                        i8 = 15000;
                        craftRecipe13.setStockTime(480.0f);
                        break;
                }
                craftRecipe13.setStockMaxQueueStack(5);
                CraftRecipe.Ingredient ingredient12 = new CraftRecipe.Ingredient(ItemType.TILE, 3, new int[]{ItemDataType.TYPE.ordinal(), TileType.PLATFORM.ordinal(), ItemDataType.BONUS_TYPE.ordinal(), spaceTileBonusType.ordinal(), ItemDataType.BONUS_LEVEL.ordinal(), i7 - 1});
                ingredient12.minCount = 2;
                PlatformTile create10 = spaceTileFactory.create();
                create10.bonusLevel = i7 - 1;
                create10.bonusType = spaceTileBonusType;
                ingredient12.exampleItems = new Item[]{Item.D.F_TILE.create(create10)};
                craftRecipe13.ingredients.add(ingredient12);
                craftRecipe13.ingredients.add(new CraftRecipe.Ingredient(ItemType.GREEN_PAPER, i8));
            }
        }
    }

    public Array<CraftRecipe> getCraftRecipes(Item item) {
        Array<CraftRecipe> array = new Array<>(CraftRecipe.class);
        for (int i = 0; i < this.craftRecipes.size; i++) {
            if (this.craftRecipes.items[i].result.getItem().sameAs(item)) {
                array.add(this.craftRecipes.items[i]);
            }
        }
        return array;
    }

    public String getCategoryNameAlias(ItemCategoryType itemCategoryType) {
        return Game.i.localeManager.i18n.get("item_category_" + itemCategoryType.name());
    }

    public String getSubcategoryName(ItemSubcategoryType itemSubcategoryType) {
        return Game.i.localeManager.i18n.get("item_subcategory_" + itemSubcategoryType.name());
    }

    public String getSubcategoryIconAlias(ItemSubcategoryType itemSubcategoryType) {
        return f2349a[itemSubcategoryType.ordinal()];
    }

    public Color getSubcategoryColor(ItemSubcategoryType itemSubcategoryType) {
        switch (itemSubcategoryType) {
            case M_DUST:
                return MaterialColor.CYAN.P500;
            case M_BLUEPRINT:
                return MaterialColor.LIGHT_GREEN.P500;
            case M_CURRENCY:
                return MaterialColor.AMBER.P500;
            case M_RESOURCE:
                return MaterialColor.PINK.P400;
            case M_TOKENS:
                return MaterialColor.PURPLE.P400;
            case ME_PLATFORMS:
                return MaterialColor.BLUE.P500;
            case ME_ROADS:
                return MaterialColor.GREEN.P500;
            case ME_SOUNDS:
                return MaterialColor.DEEP_ORANGE.P400;
            case ME_SOURCES:
                return MaterialColor.ORANGE.P500;
            case ME_SPAWNS:
                return MaterialColor.PURPLE.P300;
            case ME_BASES:
                return MaterialColor.TEAL.P400;
            case ME_SPECIAL:
                return MaterialColor.YELLOW.P500;
            case O_OTHER:
                return MaterialColor.GREY.P500;
            case P_ENCRYPTED:
                return MaterialColor.PURPLE.P400;
            case P_DECRYPTED:
                return MaterialColor.LIGHT_GREEN.P500;
            default:
                return Color.WHITE;
        }
    }

    public CaseType getQueuedEncryptedCaseType(int i) {
        CaseType[] caseTypeArr = ENCRYPTED_CASES_QUEUE;
        return caseTypeArr[i % caseTypeArr.length];
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processFallThroughCases(RegionMaker.java:841)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:800)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001e. Please report as an issue. */
    public static com.prineside.tdi2.ItemStack generateItemByRarity(com.badlogic.gdx.math.RandomXS128 r8, com.prineside.tdi2.enums.RarityType r9, float r10, float r11, float r12, float r13, float r14, float r15, float r16, boolean r17, com.prineside.tdi2.managers.ProgressManager.InventoryStatistics r18) {
        /*
            Method dump skipped, instructions count: 1791
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ItemManager.generateItemByRarity(com.badlogic.gdx.math.RandomXS128, com.prineside.tdi2.enums.RarityType, float, float, float, float, float, float, float, boolean, com.prineside.tdi2.managers.ProgressManager$InventoryStatistics):com.prineside.tdi2.ItemStack");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.prineside.tdi2.managers.ItemManager$2, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ItemManager$2.class */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] d = new int[RarityType.values().length];

        static {
            try {
                d[RarityType.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[RarityType.RARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[RarityType.VERY_RARE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[RarityType.EPIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[RarityType.LEGENDARY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            c = new int[ItemSubcategoryType.values().length];
            try {
                c[ItemSubcategoryType.M_DUST.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[ItemSubcategoryType.M_BLUEPRINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[ItemSubcategoryType.M_CURRENCY.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[ItemSubcategoryType.M_RESOURCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[ItemSubcategoryType.M_TOKENS.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[ItemSubcategoryType.ME_PLATFORMS.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                c[ItemSubcategoryType.ME_ROADS.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                c[ItemSubcategoryType.ME_SOUNDS.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                c[ItemSubcategoryType.ME_SOURCES.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                c[ItemSubcategoryType.ME_SPAWNS.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                c[ItemSubcategoryType.ME_BASES.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                c[ItemSubcategoryType.ME_SPECIAL.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                c[ItemSubcategoryType.O_OTHER.ordinal()] = 13;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                c[ItemSubcategoryType.P_ENCRYPTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                c[ItemSubcategoryType.P_DECRYPTED.ordinal()] = 15;
            } catch (NoSuchFieldError unused20) {
            }
            f2353b = new int[ResourceType.values().length];
            try {
                f2353b[ResourceType.VECTOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f2353b[ResourceType.MATRIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f2353b[ResourceType.TENSOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f2353b[ResourceType.INFIAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused24) {
            }
            f2352a = new int[BlueprintType.values().length];
            try {
                f2352a[BlueprintType.SPECIAL_II.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f2352a[BlueprintType.SPECIAL_III.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f2352a[BlueprintType.SPECIAL_IV.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                f2352a[BlueprintType.SPECIAL_I.ordinal()] = 4;
            } catch (NoSuchFieldError unused28) {
            }
        }
    }

    public Item.Factory<? extends Item> getFactory(ItemType itemType) {
        return this.f2350b[itemType.ordinal()];
    }
}

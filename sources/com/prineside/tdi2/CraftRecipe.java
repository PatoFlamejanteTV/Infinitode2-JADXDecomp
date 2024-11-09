package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/CraftRecipe.class */
public class CraftRecipe {

    /* renamed from: a, reason: collision with root package name */
    private float f1688a;

    /* renamed from: b, reason: collision with root package name */
    private int f1689b = 1;
    public Array<Ingredient> ingredients = new Array<>(Ingredient.class);
    public ItemStack result;

    public boolean isAvailable() {
        return true;
    }

    public boolean hasEnoughItemsToRun() {
        for (int i = 0; i < this.ingredients.size; i++) {
            Ingredient ingredient = this.ingredients.items[i];
            Array<ItemStack> suitableItemsFromInventory = ingredient.getSuitableItemsFromInventory();
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= suitableItemsFromInventory.size) {
                    break;
                }
                if (suitableItemsFromInventory.items[i2].getCount() < ingredient.getCountWithGVs()) {
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public void setStockTime(float f) {
        this.f1688a = f;
    }

    public void setStockMaxQueueStack(int i) {
        this.f1689b = i;
    }

    public float getTimeWithGVs() {
        return Math.max(this.f1688a * ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.CRAFTING_TIME)), 0.1f);
    }

    public int getMaxQueueStackWithGVs() {
        int max = Math.max(MathUtils.round(this.f1689b * ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.CRAFTING_MAX_STACK))), 1) * User32.VK_PLAY;
        int i = max;
        if (max > 1000000) {
            i = 1000000;
        }
        return i;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CraftRecipe$Ingredient.class */
    public static class Ingredient {
        public ItemType itemType;
        public RarityType itemRarity;
        public int[] itemParams;
        public int count;
        public int minCount;
        public boolean ignoresDiscounts;
        public Item[] exampleItems;

        /* renamed from: a, reason: collision with root package name */
        private static final Array<ItemStack> f1690a = new Array<>(ItemStack.class);

        public Item[] getExampleItems() {
            if (this.exampleItems == null) {
                this.exampleItems = new Item[1];
                this.exampleItems[0] = Game.i.itemManager.getFactory(this.itemType).createDefault();
            }
            return this.exampleItems;
        }

        public Ingredient(ItemType itemType, int i, RarityType rarityType) {
            this.itemRarity = null;
            this.itemParams = null;
            this.minCount = 1;
            this.itemType = itemType;
            this.count = i;
            this.itemRarity = rarityType;
        }

        public Ingredient(ItemType itemType, int i, RarityType rarityType, int[] iArr) {
            this.itemRarity = null;
            this.itemParams = null;
            this.minCount = 1;
            this.itemType = itemType;
            this.count = i;
            this.itemRarity = rarityType;
            this.itemParams = iArr;
        }

        public Ingredient(ItemType itemType, int i, int[] iArr) {
            this.itemRarity = null;
            this.itemParams = null;
            this.minCount = 1;
            this.itemType = itemType;
            this.count = i;
            this.itemParams = iArr;
        }

        public Ingredient(ItemType itemType, int i) {
            this.itemRarity = null;
            this.itemParams = null;
            this.minCount = 1;
            this.itemType = itemType;
            this.count = i;
        }

        public int getCountWithGVs() {
            if (this.ignoresDiscounts) {
                return this.count;
            }
            int round = MathUtils.round((float) (this.count * Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.CRAFTING_PRICE)));
            int i = round;
            if (round < this.minCount) {
                i = this.minCount;
            }
            return i;
        }

        public boolean fits(Item item) {
            if (item.getType() != this.itemType) {
                return false;
            }
            if (this.itemRarity != null && item.getRarity() != this.itemRarity) {
                return false;
            }
            if (this.itemParams != null) {
                for (int i = 0; i < this.itemParams.length; i += 2) {
                    int i2 = this.itemParams[i + 1];
                    if (i2 != Integer.MIN_VALUE && item.getDataOfType(ItemDataType.values[this.itemParams[i]]) != i2) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }

        public Array<ItemStack> getSuitableItemsFromInventory() {
            f1690a.clear();
            DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(this.itemType);
            for (int i = 0; i < itemsByType.size; i++) {
                if (fits(itemsByType.items[i].getItem())) {
                    f1690a.add(itemsByType.items[i]);
                }
            }
            return f1690a;
        }
    }
}

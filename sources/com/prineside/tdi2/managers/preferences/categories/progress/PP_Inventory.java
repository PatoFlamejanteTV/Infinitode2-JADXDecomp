package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Inventory.class */
public class PP_Inventory implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2529a = TLog.forClass(PP_Inventory.class);

    /* renamed from: b, reason: collision with root package name */
    private final DelayedRemovalArray<ItemStack> f2530b = new DelayedRemovalArray<>(false, 1, ItemStack.class);
    private final ObjectMap<ItemType, DelayedRemovalArray<Item>> c = new ObjectMap<>();
    public Array<AbilityType> lastSelectedAbilities = new Array<>(true, 1, AbilityType.class);
    private final ObjectMap<ItemType, DelayedRemovalArray<ItemStack>> d = new ObjectMap<>();
    private final ObjectMap<ItemCategoryType, DelayedRemovalArray<ItemStack>> e = new ObjectMap<>();
    private final ObjectMap<ItemSubcategoryType, DelayedRemovalArray<ItemStack>> f = new ObjectMap<>();

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void load(PrefMap prefMap) {
        ItemStack fromJson;
        Item fromJson2;
        boolean z;
        this.f2530b.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.c.clear();
        String str = prefMap.get("items", null);
        boolean isHeadless = Config.isHeadless();
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    try {
                        fromJson = ItemStack.fromJson(iterator2.next());
                    } catch (Exception e) {
                        f2529a.e("failed to load items", e);
                    }
                    if (isHeadless) {
                        if (fromJson.getItem().getType() == ItemType.TROPHY) {
                            addItems(fromJson.getItem(), fromJson.getCount());
                        }
                    } else {
                        addItems(fromJson.getItem(), fromJson.getCount());
                    }
                }
            } catch (Exception e2) {
                f2529a.e("failed to load items", e2);
            }
        }
        String str2 = prefMap.get("starredItems", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str2).iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next = iterator22.next();
                    try {
                        fromJson2 = Item.fromJson(next);
                        z = false;
                        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(fromJson2.getType());
                        int i = 0;
                        while (true) {
                            if (i >= itemsByType.size) {
                                break;
                            }
                            if (!itemsByType.get(i).getItem().sameAs(fromJson2)) {
                                i++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                    } catch (Exception e3) {
                        f2529a.e("failed to load starred item: " + next, e3);
                    }
                    if (z) {
                        setStarred(fromJson2, true);
                    } else {
                        f2529a.i("warning: item not found in inventory and won't be starred: " + fromJson2, new Object[0]);
                        f2529a.i("  " + fromJson2.toJsonString(), new Object[0]);
                    }
                }
            } catch (Exception e4) {
                f2529a.e("failed to load starred items", e4);
            }
        }
        this.lastSelectedAbilities.clear();
        String str3 = prefMap.get("lastAbilitiesConfiguration", null);
        if (str3 != null) {
            try {
                Iterator<JsonValue> iterator23 = new JsonReader().parse(str3).iterator2();
                while (iterator23.hasNext()) {
                    JsonValue next2 = iterator23.next();
                    if (next2.type() == JsonValue.ValueType.stringValue) {
                        try {
                            this.lastSelectedAbilities.add(AbilityType.valueOf(next2.asString()));
                        } catch (Exception unused) {
                        }
                    } else {
                        this.lastSelectedAbilities.add(null);
                    }
                }
            } catch (Exception e5) {
                f2529a.e("failed to load previous abilities configuration", e5);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void save(PrefMap prefMap) {
        if (this.f2530b.size != 0) {
            Json json = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeArrayStart();
            for (int i = 0; i < this.f2530b.size; i++) {
                json.writeObjectStart();
                this.f2530b.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
            prefMap.set("items", stringWriter.toString());
        }
        if (this.c.size != 0) {
            Json json2 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter2 = new StringWriter();
            json2.setWriter(stringWriter2);
            json2.writeArrayStart();
            ObjectMap.Values<DelayedRemovalArray<Item>> it = this.c.values().iterator();
            while (it.hasNext()) {
                Array.ArrayIterator<Item> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    Item next = it2.next();
                    json2.writeObjectStart();
                    next.toJson(json2);
                    json2.writeObjectEnd();
                }
            }
            json2.writeArrayEnd();
            prefMap.set("starredItems", stringWriter2.toString());
        }
        Json json3 = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter3 = new StringWriter();
        json3.setWriter(stringWriter3);
        json3.writeArrayStart();
        for (int i2 = 0; i2 < this.lastSelectedAbilities.size; i2++) {
            if (this.lastSelectedAbilities.get(i2) == null) {
                json3.writeValue(Boolean.FALSE);
            } else {
                json3.writeValue(this.lastSelectedAbilities.get(i2).name());
            }
        }
        json3.writeArrayEnd();
        prefMap.set("lastAbilitiesConfiguration", stringWriter3.toString());
    }

    public boolean isStarred(Item item) {
        DelayedRemovalArray<Item> delayedRemovalArray = this.c.get(item.getType());
        if (delayedRemovalArray == null) {
            return false;
        }
        for (int i = 0; i < delayedRemovalArray.size; i++) {
            if (delayedRemovalArray.get(i).sameAs(item)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean setStarred(Item item, boolean z) {
        DelayedRemovalArray<Item> delayedRemovalArray = this.c.get(item.getType());
        DelayedRemovalArray<Item> delayedRemovalArray2 = delayedRemovalArray;
        if (delayedRemovalArray == null) {
            if (!z) {
                return false;
            }
            delayedRemovalArray2 = new DelayedRemovalArray<>(false, 1, Item.class);
            this.c.put(item.getType(), delayedRemovalArray2);
        }
        if (z) {
            for (int i = 0; i < delayedRemovalArray2.size; i++) {
                if (delayedRemovalArray2.get(i).sameAs(item)) {
                    return false;
                }
            }
            delayedRemovalArray2.add(item);
            return true;
        }
        for (int i2 = 0; i2 < delayedRemovalArray2.size; i2++) {
            if (delayedRemovalArray2.get(i2).sameAs(item)) {
                delayedRemovalArray2.removeIndex(i2);
                return true;
            }
        }
        return false;
    }

    public DelayedRemovalArray<ItemStack> getItemsByType(ItemType itemType) {
        if (this.d.get(itemType) == null) {
            this.d.put(itemType, new DelayedRemovalArray<>(false, 1, ItemStack.class));
        }
        return this.d.get(itemType);
    }

    public int getItemsCount(Item item) {
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(item.getType());
        for (int i = 0; i < itemsByType.size; i++) {
            if (itemsByType.get(i).getItem().sameAs(item)) {
                return itemsByType.get(i).getCount();
            }
        }
        return 0;
    }

    public DelayedRemovalArray<ItemStack> getItemsByCategory(ItemCategoryType itemCategoryType) {
        if (this.e.get(itemCategoryType) == null) {
            this.e.put(itemCategoryType, new DelayedRemovalArray<>(false, 1));
        }
        return this.e.get(itemCategoryType);
    }

    public DelayedRemovalArray<ItemStack> getItemsBySubcategory(ItemSubcategoryType itemSubcategoryType) {
        if (this.f.get(itemSubcategoryType) == null) {
            this.f.put(itemSubcategoryType, new DelayedRemovalArray<>(false, 1));
        }
        return this.f.get(itemSubcategoryType);
    }

    public synchronized void addItems(Item item, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Count is " + i);
        }
        ItemStack addItemToStacksArray = ProgressManager.addItemToStacksArray(this.f2530b, item, i);
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(item.getType());
        if (!itemsByType.contains(addItemToStacksArray, true)) {
            itemsByType.add(addItemToStacksArray);
        }
        DelayedRemovalArray<ItemStack> itemsByCategory = getItemsByCategory(item.getCategory());
        if (!itemsByCategory.contains(addItemToStacksArray, true)) {
            itemsByCategory.add(addItemToStacksArray);
        }
        DelayedRemovalArray<ItemStack> itemsBySubcategory = getItemsBySubcategory(item.getSubcategory());
        if (!itemsBySubcategory.contains(addItemToStacksArray, true)) {
            itemsBySubcategory.add(addItemToStacksArray);
        }
    }

    public Array<ItemStack> getAllItems() {
        return this.f2530b;
    }

    public synchronized void removeAllItems() {
        this.f2530b.clear();
        this.e.clear();
        this.f.clear();
        this.d.clear();
        this.c.clear();
    }

    public boolean hasAnyItem() {
        return this.f2530b.size != 0;
    }

    public synchronized ItemRemoveResult removeItems(Item item, int i) {
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(item.getType());
        ItemRemoveResult itemRemoveResult = new ItemRemoveResult((byte) 0);
        itemsByType.begin();
        int i2 = 0;
        while (true) {
            if (i2 >= itemsByType.size) {
                break;
            }
            ItemStack itemStack = itemsByType.get(i2);
            if (!itemStack.getItem().sameAs(item)) {
                i2++;
            } else {
                int count = itemStack.getCount();
                if (count == i) {
                    itemsByType.removeIndex(i2);
                    if (!this.f2530b.removeValue(itemStack, true)) {
                        f2529a.e("allItems had no such item", new Object[0]);
                    }
                    if (!getItemsByCategory(item.getCategory()).removeValue(itemStack, true)) {
                        f2529a.e("itemsByCategory had no such item", new Object[0]);
                    }
                    if (!getItemsBySubcategory(item.getSubcategory()).removeValue(itemStack, true)) {
                        f2529a.e("itemsBySubcategory had no such item", new Object[0]);
                    }
                    itemRemoveResult.removedRequiredAmount = true;
                    itemRemoveResult.remainingCount = 0;
                } else if (count > i) {
                    itemStack.setCount(count - i);
                    itemRemoveResult.removedRequiredAmount = true;
                    itemRemoveResult.remainingCount = itemStack.getCount();
                }
            }
        }
        itemsByType.end();
        return itemRemoveResult;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Inventory$ItemRemoveResult.class */
    public static final class ItemRemoveResult {
        public boolean removedRequiredAmount;
        public int remainingCount;

        /* synthetic */ ItemRemoveResult(byte b2) {
            this();
        }

        private ItemRemoveResult() {
        }
    }
}

package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.events.mapEditor.InventoryItemAdd;
import com.prineside.tdi2.events.mapEditor.InventoryItemRemove;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.ProgressManager;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/InventorySystem.class */
public class InventorySystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private final Array<DelayedRemovalArray<ItemStack>> f2969a = new Array<>(true, ItemSubcategoryType.values.length, DelayedRemovalArray.class);

    /* renamed from: b, reason: collision with root package name */
    private final _ProgressManagerListener f2970b = new _ProgressManagerListener(this, 0);
    private boolean c;

    public InventorySystem() {
        this.f2969a.setSize(ItemSubcategoryType.values.length);
        for (ItemSubcategoryType itemSubcategoryType : ItemSubcategoryType.values) {
            this.f2969a.set(itemSubcategoryType.ordinal(), new DelayedRemovalArray<>(true, 8, ItemStack.class));
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public void setup() {
        Game.i.progressManager.addListener(this.f2970b);
    }

    @Override // com.prineside.tdi2.GameSystem
    public boolean affectsGameState() {
        return false;
    }

    public DelayedRemovalArray<ItemStack> getItemsBySubCategory(ItemSubcategoryType itemSubcategoryType) {
        return this.f2969a.items[itemSubcategoryType.ordinal()];
    }

    public Array<DelayedRemovalArray<ItemStack>> getAllItems() {
        return this.f2969a;
    }

    public void initAddItem(Item item, int i) {
        ProgressManager.addItemToStacksArray(getItemsBySubCategory(item.getSubcategory()), item, i);
        this.S._mapEditorUi.inventoryMenu.rebuildItemList();
    }

    public void initAddItems(Array<ItemStack> array) {
        for (int i = 0; i < array.size; i++) {
            ItemStack itemStack = array.get(i);
            ProgressManager.addItemToStacksArray(getItemsBySubCategory(itemStack.getItem().getSubcategory()), itemStack.getItem(), itemStack.getCount());
        }
    }

    public void add(Item item, int i) {
        Preconditions.checkArgument(i > 0, "Count must be > 0, %s given", i);
        if (item instanceof TileItem) {
            item = Item.D.F_TILE.create(((TileItem) item).tile.cloneTile().removeExtrasForInventory());
        }
        InventoryItemAdd inventoryItemAdd = (InventoryItemAdd) this.S.events.trigger(new InventoryItemAdd(item, i));
        if (!inventoryItemAdd.isCancelled()) {
            if (!this.c) {
                Item item2 = inventoryItemAdd.getItem();
                ProgressManager.addItemToStacksArray(getItemsBySubCategory(item2.getSubcategory()), item2, inventoryItemAdd.getCount());
            } else if (ProgressManager.getItemStackFromArray(getItemsBySubCategory(item.getSubcategory()), item) == null) {
                ProgressManager.addItemToStacksArray(getItemsBySubCategory(item.getSubcategory()), item, 1);
            }
        }
    }

    public void addTile(Tile tile, int i) {
        add(Item.D.F_TILE.create(tile.cloneTile()), i);
    }

    public void addGate(Gate gate, int i) {
        add(Item.D.F_GATE.create(gate.cloneGate()), i);
    }

    public void setStaticMode(boolean z) {
        this.c = z;
    }

    public boolean contains(Item item, int i) {
        if (this.c) {
            return true;
        }
        Preconditions.checkArgument(i > 0, "Count must be > 0, %s given", i);
        ItemStack itemStackFromArray = ProgressManager.getItemStackFromArray(getItemsBySubCategory(item.getSubcategory()), item);
        return itemStackFromArray != null && i <= itemStackFromArray.getCount();
    }

    public boolean removeMany(Item item, int i) {
        if (this.c) {
            return true;
        }
        Preconditions.checkArgument(i > 0, "Count must be > 0, %s given", i);
        DelayedRemovalArray<ItemStack> itemsBySubCategory = getItemsBySubCategory(item.getSubcategory());
        ItemStack itemStackFromArray = ProgressManager.getItemStackFromArray(itemsBySubCategory, item);
        if (itemStackFromArray == null || i > itemStackFromArray.getCount()) {
            return false;
        }
        ItemStack removeItemFromStacksArray = ProgressManager.removeItemFromStacksArray(itemsBySubCategory, itemStackFromArray.getItem(), ((InventoryItemRemove) this.S.events.trigger(new InventoryItemRemove(item, i, itemStackFromArray.getCount()))).getCount());
        if (removeItemFromStacksArray != null) {
            if (removeItemFromStacksArray.getCount() == 0) {
                itemsBySubCategory.removeValue(removeItemFromStacksArray, true);
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean remove(Item item) {
        return removeMany(item, 1);
    }

    @Override // com.prineside.tdi2.GameSystem
    public boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public String getSystemName() {
        return "Inventory";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.f2969a.clear();
        Game.i.progressManager.removeListener(this.f2970b);
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/InventorySystem$_ProgressManagerListener.class */
    private class _ProgressManagerListener extends ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter {
        private _ProgressManagerListener() {
        }

        /* synthetic */ _ProgressManagerListener(InventorySystem inventorySystem, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
        public void itemsChanged(Item item, int i, int i2) {
            if (i2 > 0) {
                InventorySystem.this.initAddItem(item, i2);
            }
        }
    }
}

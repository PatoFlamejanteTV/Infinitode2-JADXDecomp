package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.events.mapEditor.InventoryItemAdd;
import com.prineside.tdi2.events.mapEditor.InventoryItemRemove;
import com.prineside.tdi2.events.mapEditor.MapEditorSelectionChange;
import com.prineside.tdi2.events.mapEditor.SelectionChange;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.InputListenerExtended;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorInventoryMenu.class */
public class MapEditorInventoryMenu implements Disposable {
    private final SideMenu d;
    private boolean e;
    private boolean f;
    private final SideMenu.SideMenuContainer g;
    private boolean i;
    public ScrollPane scrollPane;
    private final Group k;
    private final Label l;
    private final Table m;
    private ItemSubcategoryType n;
    private final CameraController p;
    private final GameSystemProvider q;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3333a = TLog.forClass(MapEditorInventoryMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private static final ItemSubcategoryType[] f3334b = {ItemSubcategoryType.ME_PLATFORMS, ItemSubcategoryType.ME_ROADS, ItemSubcategoryType.ME_SOURCES, ItemSubcategoryType.ME_SPAWNS, ItemSubcategoryType.ME_BASES, ItemSubcategoryType.ME_SPECIAL, ItemSubcategoryType.ME_SOUNDS};
    private static final Vector2 r = new Vector2();
    private static final Array<ItemStack> s = new Array<>();
    private final DelayedRemovalArray<MapEditorInventoryMenuListener> c = new DelayedRemovalArray<>();
    private final ObjectMap<ItemSubcategoryType, ItemCategoryTab> h = new ObjectMap<>();
    private final Array<ItemSlot> j = new Array<>();
    public Array<ItemSlot> cells = new Array<>();
    private final ObjectMap<Item, ItemSlot> o = new ObjectMap<>();

    public MapEditorInventoryMenu(GameSystemProvider gameSystemProvider) {
        this.q = gameSystemProvider;
        gameSystemProvider.events.getListeners(Render.class).add(render -> {
            if (this.i) {
                rebuildItemList();
            }
        });
        gameSystemProvider.events.getListeners(InventoryItemAdd.class).add(inventoryItemAdd -> {
            if (isVisible() && inventoryItemAdd.getItem().getSubcategory() == this.n) {
                Game.i.uiManager.runOnStageActOnce(() -> {
                    rebuildSlotOfItem(inventoryItemAdd.getItem(), false);
                });
            }
        });
        gameSystemProvider.events.getListeners(InventoryItemRemove.class).add(inventoryItemRemove -> {
            if (isVisible() && inventoryItemRemove.getItem().getSubcategory() == this.n) {
                Game.i.uiManager.runOnStageActOnce(() -> {
                    rebuildSlotOfItem(inventoryItemRemove.getItem(), false);
                });
            }
        });
        gameSystemProvider.events.getListeners(SelectionChange.class).add(selectionChange -> {
            b();
        });
        this.p = gameSystemProvider._input.getCameraController();
        this.d = new SideMenu(508.0f);
        this.d.addOffscreenBackground();
        this.g = this.d.createContainer("MapEditorInventoryMenu");
        this.g.setName("tile_inventory_menu_container");
        float f = 160.0f;
        for (int length = f3334b.length - 1; length >= 0; length--) {
            final ItemSubcategoryType itemSubcategoryType = f3334b[length];
            ItemCategoryTab itemCategoryTab = new ItemCategoryTab(itemSubcategoryType, (byte) 0);
            itemCategoryTab.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MapEditorInventoryMenu.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f2, float f3) {
                    MapEditorInventoryMenu.this.setActiveTab(itemSubcategoryType);
                }
            });
            this.h.put(itemSubcategoryType, itemCategoryTab);
            f += 112.0f;
            itemCategoryTab.setPosition(-112.0f, f);
            this.g.addActor(itemCategoryTab);
        }
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(new Color(724249599));
        image.setTouchable(Touchable.enabled);
        image.setSize(508.0f, Game.i.settingsManager.getScaledViewportHeight());
        this.g.addActor(image);
        Table table = new Table();
        Drawable tint = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        tint.setMinWidth(4.0f);
        Drawable tint2 = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_BLUE.P800);
        tint2.setMinWidth(4.0f);
        this.scrollPane = new ScrollPane(table, new ScrollPane.ScrollPaneStyle(null, null, null, tint, tint2));
        this.scrollPane.setScrollBarPositions(true, false);
        this.scrollPane.setScrollingDisabled(true, false);
        this.scrollPane.setSize(508.0f, Game.i.settingsManager.getScaledViewportHeight());
        this.g.addActor(this.scrollPane);
        this.scrollPane.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.MapEditorInventoryMenu.2
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f2, float f3, int i, @Null Actor actor) {
                Game.i.uiManager.stage.setScrollFocus(MapEditorInventoryMenu.this.scrollPane);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i, @Null Actor actor) {
                if (Game.i.uiManager.stage.getScrollFocus() == MapEditorInventoryMenu.this.scrollPane) {
                    Game.i.uiManager.stage.setScrollFocus(null);
                }
            }
        });
        this.m = new Table();
        table.add(this.m).width(384.0f).padLeft(32.0f).top().left();
        table.add().size(104.0f, Game.i.settingsManager.getScaledViewportHeight());
        this.m.addListener(new InputListenerExtended() { // from class: com.prineside.tdi2.ui.components.MapEditorInventoryMenu.3
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                MapEditorInventoryMenu.f3333a.i("touchDown setFlickScroll(false)", new Object[0]);
                if (MapEditorInventoryMenu.this.e) {
                    MapEditorInventoryMenu.this.scrollPane.setFlickScroll(false);
                    return true;
                }
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                MapEditorInventoryMenu.f3333a.i("touchUp setFlickScroll(true)", new Object[0]);
                MapEditorInventoryMenu.this.scrollPane.setFlickScroll(true);
            }
        }.setMode(InputEvent.Type.touchDragged, 2));
        this.l = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.k = new Group();
        this.k.setTransform(false);
        Group group = new Group();
        group.setTransform(false);
        group.setPosition(416.0f, 0.0f);
        group.setSize(80.0f, Game.i.settingsManager.getScaledViewportHeight());
        group.setTouchable(Touchable.disabled);
        this.g.addActor(group);
        for (int i = 0; i < 8; i++) {
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-triangle-top"));
            image2.setColor(new Color(555819519));
            image2.setSize(32.0f, 32.0f);
            image2.setPosition(24.0f, (Game.i.settingsManager.getScaledViewportHeight() / 2.0f) + 32.0f + (i * 48.0f));
            group.addActor(image2);
            Image image3 = new Image(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
            image3.setColor(new Color(555819519));
            image3.setSize(32.0f, 32.0f);
            image3.setPosition(24.0f, (((Game.i.settingsManager.getScaledViewportHeight() / 2.0f) - 32.0f) - 32.0f) - (i * 48.0f));
            group.addActor(image3);
        }
        this.g.show();
        setActiveTab(f3334b[0]);
        gameSystemProvider.events.getListeners(MapEditorSelectionChange.class).add(mapEditorSelectionChange -> {
            b();
        }).setDescription("MapEditorInventoryMenu");
    }

    public SideMenu getSideMenu() {
        return this.d;
    }

    public void setItemDraggingMode(boolean z) {
        this.e = z;
    }

    public void addListener(MapEditorInventoryMenuListener mapEditorInventoryMenuListener) {
        Preconditions.checkNotNull(mapEditorInventoryMenuListener);
        if (!this.c.contains(mapEditorInventoryMenuListener, true)) {
            this.c.add(mapEditorInventoryMenuListener);
        }
    }

    public void removeListener(MapEditorInventoryMenuListener mapEditorInventoryMenuListener) {
        Preconditions.checkNotNull(mapEditorInventoryMenuListener);
        this.c.removeValue(mapEditorInventoryMenuListener, true);
    }

    public void clearListeners() {
        this.c.clear();
    }

    public boolean isStagePointOnInventory(float f, float f2) {
        r.set(f, f2);
        this.g.stageToLocalCoordinates(r);
        return r.x >= 0.0f && r.x <= this.g.getWidth();
    }

    public void setActiveTab(ItemSubcategoryType itemSubcategoryType) {
        this.n = itemSubcategoryType;
        ObjectMap.Values<ItemCategoryTab> it = this.h.values().iterator();
        while (it.hasNext()) {
            it.next().setActive(false);
        }
        this.h.get(itemSubcategoryType).setActive(true);
        rebuildItemList();
        this.c.begin();
        for (int i = 0; i < this.c.size; i++) {
            this.c.get(i).categoryTabChanged();
        }
        this.c.end();
    }

    public void deselectAll() {
        for (int i = 0; i < this.cells.size; i++) {
            this.cells.get(i).setActive(false);
        }
    }

    public boolean isVisible() {
        return true;
    }

    public void rebuildItemList() {
        ItemSlot itemSlot;
        this.i = false;
        deselectAll();
        this.l.setText(Game.i.itemManager.getSubcategoryName(this.n).toUpperCase());
        s.clear();
        s.addAll(this.q._inventory.getItemsBySubCategory(this.n));
        s.sort((itemStack, itemStack2) -> {
            int sortingScore = itemStack.getItem().getSortingScore(ItemSortingType.KIND);
            int sortingScore2 = itemStack2.getItem().getSortingScore(ItemSortingType.KIND);
            if (sortingScore == sortingScore2) {
                return 0;
            }
            return sortingScore < sortingScore2 ? 1 : -1;
        });
        this.k.clearChildren();
        float ceil = MathUtils.ceil(s.size / 3.0f) * 128.0f;
        float f = ceil;
        this.cells.clear();
        this.o.clear();
        for (int i = 0; i < s.size; i++) {
            ItemStack itemStack3 = s.get(i);
            if (i >= this.j.size) {
                itemSlot = new ItemSlot(this, i, (byte) 0);
                this.j.add(itemSlot);
            } else {
                itemSlot = this.j.get(i);
            }
            float f2 = (i % 3) * 128.0f;
            if (i % 3 == 0) {
                f -= 128.0f;
            }
            itemSlot.setPosition(f2, f);
            this.k.addActor(itemSlot);
            this.cells.add(itemSlot);
            this.o.put(itemStack3.getItem(), itemSlot);
            itemSlot.setItemStack(itemStack3, false);
            itemSlot.setIconCountVisible(true);
        }
        this.m.clearChildren();
        this.m.add((Table) this.l).top().left().padTop(32.0f).padBottom(10.0f).row();
        this.m.add((Table) this.k).top().left().width(384.0f).height(ceil).padBottom(40.0f);
        b();
    }

    public void rebuildSlot(ItemSlot itemSlot, boolean z) {
        Preconditions.checkNotNull(itemSlot, "Slot can not be null");
        ItemStack itemStack = itemSlot.getItemStack();
        if (itemStack.getCount() <= 0) {
            if (this.f) {
                itemSlot.setIconCountVisible(false);
                return;
            } else {
                this.i = true;
                return;
            }
        }
        itemSlot.setItemStack(itemStack, z);
        itemSlot.setIconCountVisible(true);
    }

    private ItemSlot a(Item item) {
        Preconditions.checkNotNull(item);
        Array.ArrayIterator<ItemSlot> it = this.cells.iterator();
        while (it.hasNext()) {
            ItemSlot next = it.next();
            if (next.getItemStack().getItem().sameAs(item)) {
                return next;
            }
        }
        return null;
    }

    public void rebuildSlotOfItem(Item item, boolean z) {
        ItemSlot a2 = a(item);
        if (a2 == null) {
            this.i = true;
        } else {
            rebuildSlot(a2, z);
        }
    }

    private void b() {
        deselectAll();
        if (this.q._mapEditor.selection.isFromInventory()) {
            Array.ArrayIterator<Tile> it = this.q._mapEditor.selection.tiles.iterator();
            while (it.hasNext()) {
                ItemSlot a2 = a(Item.D.F_TILE.create(it.next()));
                if (a2 != null) {
                    a2.setActive(true);
                }
            }
            Array.ArrayIterator<Gate> it2 = this.q._mapEditor.selection.gates.iterator();
            while (it2.hasNext()) {
                ItemSlot a3 = a(Item.D.F_GATE.create(it2.next()));
                if (a3 != null) {
                    a3.setActive(true);
                }
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.d.dispose();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorInventoryMenu$ItemSlot.class */
    public class ItemSlot extends Group {
        private final Image l;
        private final Group m;
        private final Image n;
        private final Label o;
        private final Image p;
        private ItemStack q;
        private boolean r;

        /* synthetic */ ItemSlot(MapEditorInventoryMenu mapEditorInventoryMenu, int i, byte b2) {
            this(i);
        }

        private ItemSlot(int i) {
            setTransform(false);
            setSize(128.0f, 128.0f);
            this.l = new Image(Game.i.assetManager.getQuad(i % 2 == 0 ? "ui.components.mapEditorInventory.itemCellA" : "ui.components.mapEditorInventory.itemCellB"));
            this.l.setSize(128.0f, 128.0f);
            addActor(this.l);
            this.m = new Group();
            this.m.setTransform(false);
            this.m.setPosition(10.0f, 10.0f);
            addActor(this.m);
            this.n = new Image(Game.i.assetManager.getQuad(i % 2 == 0 ? "ui.components.mapEditorInventory.itemCellACountBg" : "ui.components.mapEditorInventory.itemCellBCountBg"));
            this.n.setSize(128.0f, 128.0f);
            this.n.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            addActor(this.n);
            this.o = new Label("122", Game.i.assetManager.getLabelStyle(21));
            this.o.setSize(42.0f, 32.0f);
            this.o.setPosition(75.0f, 3.0f);
            this.o.setAlignment(1);
            addActor(this.o);
            this.p = new Image(Game.i.assetManager.getDrawable("icon-star"));
            this.p.setColor(MaterialColor.YELLOW.P500);
            this.p.setSize(20.0f, 20.0f);
            this.p.setPosition(104.0f, 104.0f);
            this.p.setVisible(false);
            addActor(this.p);
            addListener(new InputListenerExtended(MapEditorInventoryMenu.this) { // from class: com.prineside.tdi2.ui.components.MapEditorInventoryMenu.ItemSlot.1

                /* renamed from: a, reason: collision with root package name */
                private final Vector2 f3339a = new Vector2();

                /* renamed from: b, reason: collision with root package name */
                private final Vector2 f3340b = new Vector2();
                private final Vector2 c = new Vector2();
                private boolean d = false;
                private int e = -1;

                @Override // com.prineside.tdi2.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                    if (i3 == 0 && this.e == -1) {
                        if (MapEditorInventoryMenu.this.e) {
                            this.f3339a.x = f;
                            this.f3339a.y = f2;
                            ItemSlot.this.localToStageCoordinates(this.f3339a);
                            MapEditorInventoryMenu.this.f = true;
                        }
                        this.e = i2;
                        return true;
                    }
                    return false;
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void touchDragged(InputEvent inputEvent, float f, float f2, int i2) {
                    if (this.e == i2) {
                        this.f3340b.set(f, f2);
                        ItemSlot.this.localToStageCoordinates(this.f3340b);
                        this.c.set(this.f3340b);
                        MapEditorInventoryMenu.this.p.stageToScreen(this.c);
                        if (!this.d && MapEditorInventoryMenu.this.e && this.f3339a.dst2(this.f3340b) > 16.0f) {
                            this.d = true;
                            MapEditorInventoryMenu.this.c.begin();
                            for (int i3 = 0; i3 < MapEditorInventoryMenu.this.c.size; i3++) {
                                ((MapEditorInventoryMenuListener) MapEditorInventoryMenu.this.c.get(i3)).itemSlotDragStart(ItemSlot.this, this.f3339a);
                            }
                            MapEditorInventoryMenu.this.c.end();
                        }
                        if (this.d) {
                            MapEditorInventoryMenu.this.c.begin();
                            for (int i4 = 0; i4 < MapEditorInventoryMenu.this.c.size; i4++) {
                                ((MapEditorInventoryMenuListener) MapEditorInventoryMenu.this.c.get(i4)).itemSlotDrag(ItemSlot.this, this.c);
                            }
                            MapEditorInventoryMenu.this.c.end();
                        }
                    }
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void touchUp(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                    if (i2 == this.e) {
                        MapEditorInventoryMenu.this.f = false;
                        this.f3340b.set(f, f2);
                        ItemSlot.this.localToStageCoordinates(this.f3340b);
                        this.c.set(this.f3340b);
                        MapEditorInventoryMenu.this.p.stageToScreen(this.c);
                        if (this.d) {
                            this.d = false;
                            MapEditorInventoryMenu.this.c.begin();
                            for (int i4 = 0; i4 < MapEditorInventoryMenu.this.c.size; i4++) {
                                ((MapEditorInventoryMenuListener) MapEditorInventoryMenu.this.c.get(i4)).itemSlotDragEnd(ItemSlot.this, this.c);
                            }
                            MapEditorInventoryMenu.this.c.end();
                            MapEditorInventoryMenu.this.rebuildSlot(ItemSlot.this, false);
                        } else if (!inputEvent.isTouchFocusCancel()) {
                            MapEditorInventoryMenu.this.c.begin();
                            for (int i5 = 0; i5 < MapEditorInventoryMenu.this.c.size; i5++) {
                                ((MapEditorInventoryMenuListener) MapEditorInventoryMenu.this.c.get(i5)).itemSlotClicked(ItemSlot.this);
                            }
                            MapEditorInventoryMenu.this.c.end();
                        }
                        this.e = -1;
                    }
                }
            }.setMode(InputEvent.Type.touchDragged, 2));
            this.r = true;
            setActive(false);
        }

        public void setIconCountVisible(boolean z) {
            this.m.setVisible(z);
            this.o.setVisible(z);
            this.n.setVisible(z);
        }

        public void setItemStack(ItemStack itemStack, boolean z) {
            if (itemStack == null) {
                return;
            }
            if (z || this.q == null || !this.q.getItem().sameAs(itemStack.getItem())) {
                this.m.clearChildren();
                this.m.addActor(itemStack.getItem().generateIcon(108.0f, false));
            }
            this.q = itemStack;
            setIconCountVisible(true);
            this.p.setVisible(ProgressPrefs.i().inventory.isStarred(itemStack.getItem()));
            this.o.setText(String.valueOf(itemStack.getCount()));
        }

        public ItemStack getItemStack() {
            return this.q;
        }

        public void setActive(boolean z) {
            if (this.r != z) {
                if (z) {
                    this.l.setColor(MaterialColor.LIGHT_BLUE.P800);
                } else {
                    this.l.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                }
                this.r = z;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorInventoryMenu$ItemCategoryTab.class */
    public static class ItemCategoryTab extends Group {
        public static final float SIZE = 112.0f;
        private final Image k;
        private final Image l;

        /* synthetic */ ItemCategoryTab(ItemSubcategoryType itemSubcategoryType, byte b2) {
            this(itemSubcategoryType);
        }

        private ItemCategoryTab(ItemSubcategoryType itemSubcategoryType) {
            setTransform(false);
            setSize(112.0f, 112.0f);
            this.k = new Image(Game.i.assetManager.getQuad("ui.components.mapEditorInventory.categoryTab.normal"));
            this.k.setSize(112.0f, 112.0f);
            addActor(this.k);
            this.l = new Image(Game.i.assetManager.getDrawable(Game.i.itemManager.getSubcategoryIconAlias(itemSubcategoryType)));
            this.l.setSize(64.0f, 64.0f);
            this.l.setPosition(24.0f, 24.0f);
            addActor(this.l);
            setActive(false);
        }

        public void setActive(boolean z) {
            if (z) {
                this.k.setDrawable(Game.i.assetManager.getQuad("ui.components.mapEditorInventory.categoryTab.active"));
                this.k.setColor(new Color(724249599));
                this.l.setColor(Color.WHITE);
            } else {
                this.k.setDrawable(Game.i.assetManager.getQuad("ui.components.mapEditorInventory.categoryTab.normal"));
                this.k.setColor(new Color(555819519));
                this.l.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorInventoryMenu$MapEditorInventoryMenuListener.class */
    public interface MapEditorInventoryMenuListener {
        void itemSlotDragStart(ItemSlot itemSlot, Vector2 vector2);

        void itemSlotDragEnd(ItemSlot itemSlot, Vector2 vector2);

        void itemSlotDrag(ItemSlot itemSlot, Vector2 vector2);

        void itemSlotClicked(ItemSlot itemSlot);

        void categoryTabChanged();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorInventoryMenu$MapEditorInventoryMenuListener$Adapter.class */
        public static class Adapter implements MapEditorInventoryMenuListener {
            @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
            public void itemSlotDragStart(ItemSlot itemSlot, Vector2 vector2) {
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
            public void itemSlotDragEnd(ItemSlot itemSlot, Vector2 vector2) {
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
            public void itemSlotDrag(ItemSlot itemSlot, Vector2 vector2) {
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
            public void itemSlotClicked(ItemSlot itemSlot) {
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorInventoryMenu.MapEditorInventoryMenuListener
            public void categoryTabChanged() {
            }
        }
    }
}

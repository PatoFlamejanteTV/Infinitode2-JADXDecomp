package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.CraftRecipe;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.items.CaseKeyItem;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.RandomBarrierItem;
import com.prineside.tdi2.items.RandomTileItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.ButtonHoldHint;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay.class */
public final class InventoryOverlay implements Disposable, UiManager.UiComponent {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3539a = TLog.forClass(InventoryOverlay.class);
    private ComplexButton d;
    private Group e;
    private Group f;
    private Group g;
    private Group h;
    private Group i;
    private QuadActor j;
    private ScrollPane k;
    private Image m;
    private Group n;
    private Image o;
    private CraftRecipe q;
    private int[] r;
    private boolean s;
    private static final TabConfig[] u;
    private boolean v;
    private boolean C;
    private static final Array<ItemStack> G;
    private ButtonHoldHint H;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3540b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 190, "InventoryOverlay toggle button");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 192, "InventoryOverlay main");
    private float l = -1.0f;
    private ObjectMap<ItemStack, ItemCell> p = new ObjectMap<>();
    private Array<SubcategoryButtonConfig> t = new Array<>(SubcategoryButtonConfig.class);
    private TabType w = TabType.ITEMS_MATERIALS;
    private float[] x = new float[TabType.values.length];
    private ItemSortingType y = ItemSortingType.KIND;

    @Null
    private ItemStack z = null;
    private Array<ItemStack> A = new Array<>(ItemStack.class);
    private Array<ItemStack> B = new Array<>(ItemStack.class);
    private final ItemCountSelectionOverlay.ItemCountSelectionListener D = new ItemCountSelectionOverlay.ItemCountSelectionListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.1
        @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
        public void countChanged(int i) {
            InventoryOverlay.this.e();
        }

        @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
        public void selectionConfirmed(int i) {
            if (InventoryOverlay.this.z != null) {
                Game.i.progressManager.sellItems(InventoryOverlay.this.z.getItem(), i);
            }
            InventoryOverlay.this.update();
            InventoryOverlay.this.k();
        }

        @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
        public void selectionCanceled() {
        }
    };
    private final Array<Label> E = new Array<>(Label.class);
    private final _ProgressManagerListener F = new _ProgressManagerListener(this, 0);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay$TabType.class */
    public enum TabType {
        ITEMS_MATERIALS,
        ITEMS_MAP_EDITOR,
        ITEMS_OTHER,
        CRAFTING,
        PACKS;

        public static final TabType[] values = values();
    }

    static /* synthetic */ boolean a(InventoryOverlay inventoryOverlay, boolean z) {
        inventoryOverlay.C = true;
        return true;
    }

    static {
        TabConfig[] tabConfigArr = new TabConfig[TabType.values.length];
        u = tabConfigArr;
        tabConfigArr[TabType.ITEMS_MATERIALS.ordinal()] = new TabConfig(TabType.ITEMS_MATERIALS, 92.0f, "icon-backpack", MaterialColor.CYAN.P800, MaterialColor.CYAN.P500, new ItemSubcategoryType[]{ItemSubcategoryType.M_RESOURCE, ItemSubcategoryType.M_BLUEPRINT, ItemSubcategoryType.M_DUST, ItemSubcategoryType.M_TOKENS, ItemSubcategoryType.M_CURRENCY}, (byte) 0);
        u[TabType.ITEMS_MAP_EDITOR.ordinal()] = new TabConfig(TabType.ITEMS_MAP_EDITOR, 216.0f, "icon-edit", MaterialColor.GREEN.P800, MaterialColor.GREEN.P500, new ItemSubcategoryType[]{ItemSubcategoryType.ME_PLATFORMS, ItemSubcategoryType.ME_ROADS, ItemSubcategoryType.ME_SOURCES, ItemSubcategoryType.ME_SPAWNS, ItemSubcategoryType.ME_BASES, ItemSubcategoryType.ME_SOUNDS, ItemSubcategoryType.ME_SPECIAL}, (byte) 0);
        u[TabType.ITEMS_OTHER.ordinal()] = new TabConfig(TabType.ITEMS_OTHER, 340.0f, "icon-question", MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P500, new ItemSubcategoryType[]{ItemSubcategoryType.O_OTHER}, (byte) 0);
        u[TabType.CRAFTING.ordinal()] = new TabConfig(TabType.CRAFTING, 647.0f, "icon-tools", MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P500, new ItemSubcategoryType[]{ItemSubcategoryType.M_RESOURCE, ItemSubcategoryType.M_BLUEPRINT, ItemSubcategoryType.M_DUST, ItemSubcategoryType.M_TOKENS, ItemSubcategoryType.M_CURRENCY, ItemSubcategoryType.ME_PLATFORMS, ItemSubcategoryType.ME_ROADS, ItemSubcategoryType.ME_SOURCES, ItemSubcategoryType.ME_SPAWNS, ItemSubcategoryType.ME_BASES, ItemSubcategoryType.ME_SOUNDS, ItemSubcategoryType.ME_SPECIAL, ItemSubcategoryType.P_DECRYPTED, ItemSubcategoryType.P_ENCRYPTED}, (byte) 0);
        u[TabType.PACKS.ordinal()] = new TabConfig(TabType.PACKS, 771.0f, "icon-chest", MaterialColor.PINK.P800, MaterialColor.PINK.P500, new ItemSubcategoryType[]{ItemSubcategoryType.P_DECRYPTED, ItemSubcategoryType.P_ENCRYPTED}, (byte) 0);
        new StringBuilder();
        G = new Array<>(ItemStack.class);
    }

    public static InventoryOverlay i() {
        return (InventoryOverlay) Game.i.uiManager.getComponent(InventoryOverlay.class);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.i.uiManager.removeLayer(this.f3540b);
        Game.i.uiManager.removeLayer(this.c);
    }

    public InventoryOverlay() {
        Game.i.progressManager.addListener(this.F);
        Group group = new Group();
        group.setTouchable(Touchable.childrenOnly);
        group.setTransform(false);
        this.f3540b.getTable().add((Table) group).bottom().left().size(112.0f, 105.0f).padBottom(723.0f);
        this.f3540b.getTable().add().height(1.0f).expand().fill();
        this.d = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            show();
        });
        this.d.setBackground(Game.i.assetManager.getDrawable("ui-inventory-toggle-button"), -100.0f, 0.0f, 212.0f, 105.0f);
        this.d.setBackgroundColors(MaterialColor.CYAN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), MaterialColor.CYAN.P900, MaterialColor.CYAN.P700, MaterialColor.GREY.P800);
        ComplexButton complexButton = this.d;
        Color color = Color.WHITE;
        Color color2 = Color.WHITE;
        complexButton.setIconLabelColors(color, color, color2, color2);
        this.d.setIconPositioned(Game.i.assetManager.getDrawable("icon-backpack"), 28.0f, 25.0f, 64.0f, 64.0f);
        this.m = new Image(Game.i.assetManager.getDrawable("count-bubble"));
        this.m.setPosition(86.0f, 72.0f);
        this.m.setSize(32.25f, 36.75f);
        this.m.setVisible(false);
        this.d.addActor(this.m);
        group.addActor(this.d);
        Table table = new Table();
        table.setPosition(24.0f, -75.0f);
        table.setSize(190.0f, 75.0f);
        table.setTouchable(Touchable.disabled);
        this.d.addActor(table);
        rebuildLayoutIfRequired();
        this.c.getTable().setVisible(false);
        this.v = false;
        this.d.setPosition(-212.0f, 723.0f);
        this.d.setVisible(false);
        this.c.getTable().setVisible(false);
        n();
    }

    public final void requireLayoutRebuild() {
    }

    public final void rebuildLayoutIfRequired() {
        this.c.getTable().clear();
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(598.0f, (860.0f + scaledViewportHeight) * 0.5f);
        this.c.getTable().add((Table) group).size(1196.0f, 860.0f + scaledViewportHeight);
        this.e = new Group();
        this.e.setTransform(false);
        this.e.setSize(1196.0f, 860.0f + scaledViewportHeight);
        this.e.setOrigin(598.0f, (860.0f + scaledViewportHeight) * 0.5f);
        group.addActor(this.e);
        QuadActor quadActor = new QuadActor(new Color(72), new float[]{0.0f, 13.0f, 0.0f, 40.0f, 872.0f, 40.0f, 858.0f, 0.0f});
        quadActor.setPosition(20.0f, -11.0f);
        this.e.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(72), new float[]{0.0f, 0.0f, 321.0f, 870.0f + scaledViewportHeight, 347.0f, 871.0f + scaledViewportHeight, 330.0f, 7.0f});
        quadActor2.setPosition(872.0f, -24.0f);
        this.e.addActor(quadActor2);
        this.j = new QuadActor(MaterialColor.GREY.P500, new float[]{0.0f, 0.0f, 110.0f, 17.0f, 135.0f, 17.0f, 119.0f, 4.0f});
        this.e.addActor(this.j);
        this.e.addActor(new QuadActor(new Color(791621631), new float[]{7.0f, 11.0f, 0.0f, 849.0f + scaledViewportHeight, 1196.0f, 860.0f + scaledViewportHeight, 1185.0f, 0.0f}));
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(862.0f, 832.0f + scaledViewportHeight);
        this.g.setPosition(0.0f, 14.0f);
        this.e.addActor(this.g);
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setPosition(-82.0f, 14.0f);
        this.i.setSize(82.0f, 832.0f + scaledViewportHeight);
        this.e.addActor(this.i);
        QuadActor quadActor3 = new QuadActor(new Color(36), new float[]{15.0f, 0.0f, 0.0f, 854.0f + scaledViewportHeight, 17.0f, 854.0f + scaledViewportHeight, 22.0f, 0.0f});
        quadActor3.setPosition(837.0f, 3.0f);
        this.e.addActor(quadActor3);
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            hideWithToggleButton(true);
        });
        complexButton.setBackground(Game.i.assetManager.getDrawable("ui-inventory-toggle-button"), -100.0f, 0.0f, 212.0f, 105.0f);
        complexButton.setBackgroundColors(MaterialColor.CYAN.P800, MaterialColor.CYAN.P900, MaterialColor.CYAN.P700, MaterialColor.GREY.P800);
        Color color = Color.WHITE;
        Color color2 = Color.WHITE;
        complexButton.setIconLabelColors(color, color, color2, color2);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 28.0f, 25.0f, 64.0f, 64.0f);
        complexButton.setPosition(1193.0f, 633.0f + scaledViewportHeight);
        complexButton.setSize(112.0f, 105.0f);
        this.e.addActor(complexButton);
        this.n = new Group();
        this.n.setPosition(854.0f, 0.0f);
        this.n.setSize(342.0f, 860.0f + scaledViewportHeight);
        this.e.addActor(this.n);
        QuadActor quadActor4 = new QuadActor(new Color(909522687), new float[]{6.0f, 0.0f, 0.0f, 877.0f + scaledViewportHeight, 342.0f, 867.0f + scaledViewportHeight, 331.0f, 7.0f});
        quadActor4.setPosition(854.0f, -7.0f);
        this.e.addActor(quadActor4);
        QuadActor quadActor5 = new QuadActor(new Color(-239), new float[]{6.0f, 0.0f, 0.0f, 877.0f + scaledViewportHeight, 12.0f, 871.0f + scaledViewportHeight, 8.0f, 0.0f});
        quadActor5.setPosition(854.0f, -7.0f);
        this.e.addActor(quadActor5);
        QuadActor quadActor6 = new QuadActor(new Color(-239), new float[]{12.0f, 4.0f, 0.0f, 10.0f, 342.0f, 0.0f, 342.0f, 0.0f});
        quadActor6.setPosition(854.0f, 860.0f + scaledViewportHeight);
        this.e.addActor(quadActor6);
        this.h = new Group();
        this.h.setPosition(854.0f, 0.0f);
        this.h.setSize(342.0f, 860.0f + scaledViewportHeight);
        this.e.addActor(this.h);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setSize(854.0f, 110.0f);
        this.f.setTouchable(Touchable.childrenOnly);
        this.e.addActor(this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        return this.A.size > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ItemStack itemStack) {
        if (this.z != null) {
            setSelectedItem(null);
        }
        if (this.A.contains(itemStack, true)) {
            ItemCell itemCell = this.p.get(itemStack, null);
            if (itemCell != null) {
                itemCell.setSelected(false);
            }
            this.A.removeValue(itemStack, true);
            if (this.A.size == 0) {
                c();
            }
        } else {
            this.A.add(itemStack);
            ItemCell itemCell2 = this.p.get(itemStack, null);
            if (itemCell2 != null) {
                itemCell2.setSelected(true);
            }
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.A.size > 0) {
            for (int i = 0; i < this.A.size; i++) {
                ItemCell itemCell = this.p.get(this.A.items[i], null);
                if (itemCell != null) {
                    itemCell.setSelected(false);
                }
            }
            this.A.clear();
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void d() {
        if (this.w != TabType.ITEMS_MAP_EDITOR) {
            return;
        }
        float scrollY = this.k.getScrollY();
        float height = this.k.getHeight();
        float maxY = this.k.getMaxY();
        float f = (maxY - scrollY) + 70.0f;
        float f2 = (maxY - (scrollY - height)) + 70.0f;
        if (this.z != null) {
            setSelectedItem(null);
        }
        ObjectMap.Entries<ItemStack, ItemCell> it = this.p.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            float top = ((ItemCell) next.value).getTop();
            if (top >= f && top <= f2 && !this.A.contains((ItemStack) next.key, true)) {
                this.A.add((ItemStack) next.key);
                ((ItemCell) next.value).setSelected(true);
            }
        }
        j();
    }

    private void a(TabType tabType) {
        if (this.w != tabType) {
            this.z = null;
            c();
            TabType tabType2 = this.w;
            this.w = tabType;
            if (this.k != null) {
                this.x[tabType2.ordinal()] = this.k.getScrollY();
                this.k.setScrollY(this.x[tabType.ordinal()]);
            }
            update();
            for (TabConfig tabConfig : u) {
                if (this.w == tabConfig.tabType) {
                    Label label = new Label(tabConfig.getName(), Game.i.assetManager.getLabelStyle(24));
                    label.setPosition(tabConfig.buttonShiftX - 1.0f, -40.0f);
                    label.setSize(2.0f, 18.0f);
                    label.setAlignment(1);
                    label.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.3f), Actions.delay(3.0f), Actions.fadeOut(1.0f), Actions.removeActor()));
                    this.f.addActor(label);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Item item = this.z.getItem();
        int selectedCount = ItemCountSelectionOverlay.i().getSelectedCount();
        this.B.clear();
        item.addSellItems(this.B);
        for (int i = 0; i < G.size; i++) {
            this.E.items[i].setText(StringFormatter.commaSeparatedNumber(this.B.items[i].getCount() * selectedCount));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Item item;
        Table infoContainer = ItemCountSelectionOverlay.i().getInfoContainer();
        infoContainer.clear();
        CraftRecipe craftRecipe = this.q;
        if (this.r == null) {
            this.r = new int[craftRecipe.ingredients.size];
        }
        int selectedCount = ItemCountSelectionOverlay.i().getSelectedCount();
        int maxQueueStackWithGVs = craftRecipe.getMaxQueueStackWithGVs();
        boolean z = true;
        for (int i = 0; i < craftRecipe.ingredients.size; i++) {
            CraftRecipe.Ingredient ingredient = craftRecipe.ingredients.items[i];
            Array<ItemStack> suitableItemsFromInventory = ingredient.getSuitableItemsFromInventory();
            int i2 = 0;
            int countWithGVs = ingredient.getCountWithGVs() * selectedCount;
            if (suitableItemsFromInventory.size == 0) {
                item = ingredient.getExampleItems()[0];
            } else {
                int i3 = this.r[i];
                if (suitableItemsFromInventory.size <= i3) {
                    i3 = 0;
                }
                item = suitableItemsFromInventory.items[i3].getItem();
                i2 = suitableItemsFromInventory.items[i3].getCount();
            }
            Group group = new Group();
            group.setTransform(false);
            infoContainer.add((Table) group).size(64.0f, 80.0f);
            Actor generateIcon = item.generateIcon(64.0f, true);
            generateIcon.setPosition(0.0f, 8.0f);
            group.addActor(generateIcon);
            Label label = new Label(new StringBuilder().append(countWithGVs).toString(), Game.i.assetManager.getLabelStyle(21));
            label.setAlignment(1);
            label.setPosition(61.0f, 7.0f);
            label.setSize(2.0f, 16.0f);
            label.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(label);
            Label label2 = new Label(new StringBuilder().append(countWithGVs).toString(), Game.i.assetManager.getLabelStyle(21));
            if (i2 < countWithGVs) {
                z = false;
                label.setText(i2 + " / " + countWithGVs);
                label2.setText(i2 + " / " + countWithGVs);
                label2.setColor(MaterialColor.RED.P300);
            }
            label2.setAlignment(1);
            label2.setPosition(63.0f, 5.0f);
            label2.setSize(2.0f, 16.0f);
            group.addActor(label2);
            if (suitableItemsFromInventory.size > 1) {
                int i4 = i;
                if (this.r[i] > 0) {
                    PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-top"), () -> {
                        int[] iArr = this.r;
                        iArr[i4] = iArr[i4] - 1;
                        f();
                    }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P600);
                    paddedImageButton.setIconSize(24.0f, 24.0f);
                    paddedImageButton.setIconPosition(20.0f, 33.0f);
                    paddedImageButton.setSize(64.0f, 64.0f);
                    paddedImageButton.setPosition(0.0f, 38.0f);
                    group.addActor(paddedImageButton);
                }
                if (this.r[i] < suitableItemsFromInventory.size - 1) {
                    PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-bottom"), () -> {
                        int[] iArr = this.r;
                        iArr[i4] = iArr[i4] + 1;
                        f();
                    }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P600);
                    paddedImageButton2.setIconSize(24.0f, 24.0f);
                    paddedImageButton2.setIconPosition(20.0f, 6.0f);
                    paddedImageButton2.setSize(64.0f, 64.0f);
                    paddedImageButton2.setPosition(0.0f, -26.0f);
                    group.addActor(paddedImageButton2);
                }
            }
            if (i + 1 != craftRecipe.ingredients.size) {
                Image image = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                infoContainer.add((Table) image).size(24.0f).padLeft(20.0f).padRight(20.0f);
            }
            int countWithGVs2 = i2 / ingredient.getCountWithGVs();
            if (countWithGVs2 < maxQueueStackWithGVs) {
                maxQueueStackWithGVs = countWithGVs2;
            }
        }
        if (maxQueueStackWithGVs <= 0) {
            maxQueueStackWithGVs = 1;
        }
        infoContainer.add().height(1.0f).expandX().fillX();
        if (selectedCount > maxQueueStackWithGVs || ItemCountSelectionOverlay.i().getMaxCount() != maxQueueStackWithGVs) {
            f3539a.e("selected " + selectedCount + " available " + maxQueueStackWithGVs, new Object[0]);
            ItemCountSelectionOverlay.i().setMinMaxCount(1, maxQueueStackWithGVs);
            f();
        }
        ItemCountSelectionOverlay.i().setConfirmButtonEnabled(z);
    }

    private void g() {
        if (this.k != null) {
            float visualScrollY = this.k.getVisualScrollY();
            float height = this.i.getHeight();
            for (int i = 0; i < this.t.size; i++) {
                SubcategoryButtonConfig subcategoryButtonConfig = this.t.items[i];
                float height2 = subcategoryButtonConfig.button.getHeight();
                float height3 = this.i.getHeight() - (subcategoryButtonConfig.distanceY - visualScrollY);
                float f = height3;
                if (height3 + (height2 * 0.5f) > height) {
                    f = height - (height2 * 0.5f);
                }
                subcategoryButtonConfig.button.setPosition(0.0f, f - (height2 * 0.5f));
                height = f - (height2 * 0.5f);
            }
            float f2 = 0.0f;
            for (int i2 = this.t.size - 1; i2 >= 0; i2--) {
                SubcategoryButtonConfig subcategoryButtonConfig2 = this.t.items[i2];
                if (subcategoryButtonConfig2.button.getY() < f2) {
                    subcategoryButtonConfig2.button.setY(f2);
                    f2 += subcategoryButtonConfig2.button.getHeight();
                }
            }
        }
    }

    private void h() {
        this.f.clearChildren();
        for (TabConfig tabConfig : u) {
            if (tabConfig.tabType != TabType.ITEMS_OTHER) {
                if (this.w == tabConfig.tabType) {
                    this.j.setPosition(tabConfig.buttonShiftX - 60.0f, -6.0f);
                    this.j.setColor(tabConfig.colorDark);
                    Group group = new Group();
                    group.setTransform(false);
                    group.setTouchable(Touchable.disabled);
                    group.setSize(132.0f, 132.0f);
                    group.setPosition(tabConfig.buttonShiftX - 66.0f, -6.0f);
                    this.f.addActor(group);
                    QuadActor quadActor = new QuadActor(new Color(32), new float[]{0.0f, 4.0f, 7.0f, 120.0f, 17.0f, 119.0f, 5.0f, 7.0f});
                    quadActor.setPosition(125.0f, 0.0f);
                    group.addActor(quadActor);
                    group.addActor(new QuadActor(tabConfig.colorDark, new float[]{6.0f, 0.0f, 0.0f, 132.0f, 132.0f, 129.0f, 125.0f, 4.0f}));
                    Image image = new Image(Game.i.assetManager.getDrawable(tabConfig.iconAlias));
                    image.setSize(80.0f, 80.0f);
                    image.setPosition(25.0f, 28.0f);
                    group.addActor(image);
                } else {
                    ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                        a(tabConfig.tabType);
                    });
                    complexButton.setPosition(tabConfig.buttonShiftX - 54.0f, 25.0f);
                    complexButton.setSize(108.0f, 85.0f);
                    complexButton.setIconPositioned(Game.i.assetManager.getDrawable(tabConfig.iconAlias), 22.0f, 18.0f, 64.0f, 64.0f);
                    complexButton.setIconLabelColors(Color.WHITE, tabConfig.colorBright, tabConfig.colorDark, MaterialColor.GREY.P500);
                    complexButton.setIconLabelShadowEnabled(true);
                    this.f.addActor(complexButton);
                }
                if (tabConfig.tabType == TabType.PACKS) {
                    this.o = new Image(Game.i.assetManager.getTextureRegion("count-bubble"));
                    this.o.setSize(21.5f, 24.5f);
                    this.o.setPosition(tabConfig.buttonShiftX + 24.0f, 84.0f);
                    this.o.setVisible(false);
                    if (this.w != TabType.PACKS) {
                        this.f.addActor(this.o);
                    }
                }
            }
        }
        o();
    }

    private static ComplexButton a(String str, Drawable drawable, Color[] colorArr, Runnable runnable, Runnable runnable2, Table table, Table table2) {
        ComplexButton complexButton = new ComplexButton(str, Game.i.assetManager.getLabelStyle(24), runnable, runnable2);
        complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 80.0f, 328.0f, 77.0f, 323.0f, 3.0f})), 0.0f, 10.0f, 328.0f, 80.0f);
        complexButton.backgroundShadow.setVisible(true);
        complexButton.backgroundShadow.setSize(327.0f, 87.0f);
        complexButton.backgroundShadow.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        complexButton.backgroundShadow.setDrawable(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{14.0f, 0.0f, 6.0f, 87.0f, 327.0f, 85.0f, 320.0f, 11.0f})));
        complexButton.setBackgroundColors(colorArr[MaterialColor.Variants.P800.ordinal()], colorArr[MaterialColor.Variants.P900.ordinal()], colorArr[MaterialColor.Variants.P700.ordinal()], MaterialColor.GREY.P700);
        complexButton.setIconPositioned(drawable, 21.0f, 25.0f, 48.0f, 48.0f);
        complexButton.setLabel(77.0f, 25.0f, 240.0f, 48.0f, 8);
        complexButton.label.setWrap(true);
        table.add((Table) complexButton).size(328.0f, 90.0f).padTop(8.0f).padLeft(23.0f).left().row();
        Image image = new Image(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 13.0f, 19.0f, 13.0f, 19.0f, 13.0f})));
        image.setColor(colorArr[MaterialColor.Variants.P800.ordinal()]);
        image.getColor().lerp(Color.BLACK, 0.5f);
        table2.add((Table) image).size(19.0f, 13.0f).padLeft(326.0f).padTop(85.0f).left().row();
        return complexButton;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v156, types: [com.prineside.tdi2.scene2d.Actor, com.prineside.tdi2.ui.actors.ComplexButton] */
    /* JADX WARN: Type inference failed for: r0v476, types: [T[]] */
    /* JADX WARN: Type inference failed for: r3v133 */
    /* JADX WARN: Type inference failed for: r3v150 */
    /* JADX WARN: Type inference failed for: r3v151 */
    /* JADX WARN: Type inference failed for: r3v152 */
    /* JADX WARN: Type inference failed for: r3v167 */
    /* JADX WARN: Type inference failed for: r3v168 */
    private void j() {
        if (this.w != TabType.CRAFTING && this.z != null && Game.i.progressManager.getItemsCount(this.z.getItem()) == 0) {
            setSelectedItem(null);
            return;
        }
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        this.h.clearChildren();
        this.n.clearChildren();
        Table table = new Table();
        table.setSize(342.0f, 820.0f + scaledViewportHeight);
        table.setTouchable(Touchable.childrenOnly);
        table.setPosition(0.0f, 20.0f);
        this.h.addActor(table);
        Table table2 = new Table();
        table2.setSize(342.0f, 820.0f + scaledViewportHeight);
        table2.setTouchable(Touchable.disabled);
        table2.setPosition(0.0f, 20.0f);
        this.n.addActor(table2);
        table.add().width(1.0f).expandY().fillY().row();
        table2.add().width(1.0f).expandY().fillY().row();
        Item item = null;
        boolean z = false;
        boolean z2 = true;
        if (b()) {
            z2 = false;
            Array array = new Array(Actor.class);
            for (int i = this.A.size - 1; i >= 0; i--) {
                array.add(this.A.items[i].getItem().generateIcon(96.0f, true));
                if (array.size == 8) {
                    break;
                }
            }
            item = this.A.items[this.A.size - 1].getItem();
            z = true;
            float f = array.size * 24.0f * 0.5f;
            for (int i2 = 0; i2 < array.size; i2++) {
                Actor actor = ((Actor[]) array.items)[i2];
                actor.setPosition((132.0f - f) + (i2 * 24.0f), 708.0f + scaledViewportHeight);
                this.h.addActor(actor);
                actor.setZIndex(0);
            }
            Label label = new Label(Game.i.localeManager.i18n.get("selected_items_count") + SequenceUtils.SPACE + this.A.size, Game.i.assetManager.getLabelStyle(30));
            label.setAlignment(1);
            label.setPosition(108.0f, 632.0f + scaledViewportHeight);
            label.setSize(128.0f, 32.0f);
            label.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            this.h.addActor(label);
            Label label2 = new Label(Game.i.localeManager.i18n.get("selected_items_count") + SequenceUtils.SPACE + this.A.size, Game.i.assetManager.getLabelStyle(30));
            label2.setAlignment(1);
            label2.setPosition(107.0f, 635.0f + scaledViewportHeight);
            label2.setSize(128.0f, 32.0f);
            this.h.addActor(label2);
            Label label3 = new Label(Game.i.localeManager.i18n.get("cancel"), Game.i.assetManager.getLabelStyle(24));
            label3.setAlignment(1);
            label3.setPosition(43.0f, 600.0f + scaledViewportHeight);
            label3.setSize(256.0f, 32.0f);
            label3.setColor(MaterialColor.LIGHT_BLUE.P500);
            label3.setTouchable(Touchable.enabled);
            InventoryOverlay inventoryOverlay = this;
            label3.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.2
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f2, float f3) {
                    InventoryOverlay.this.c();
                }
            });
            this.h.addActor(label3);
            InventoryOverlay inventoryOverlay2 = inventoryOverlay;
            if (this.w == TabType.ITEMS_MAP_EDITOR) {
                String str = Game.i.localeManager.i18n.get("select_visible_items");
                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                    str = str + " (A)";
                }
                Label label4 = new Label(str, Game.i.assetManager.getLabelStyle(24));
                label4.setAlignment(1);
                label4.setPosition(43.0f, 536.0f + scaledViewportHeight);
                label4.setSize(256.0f, 40.0f);
                label4.setColor(MaterialColor.LIGHT_BLUE.P500);
                label4.setTouchable(Touchable.enabled);
                InventoryOverlay inventoryOverlay3 = this;
                label4.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.3
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f2, float f3) {
                        InventoryOverlay.this.d();
                    }
                });
                this.h.addActor(label4);
                inventoryOverlay2 = inventoryOverlay3;
            }
            double d = 0.0d;
            int i3 = 0;
            ?? r3 = inventoryOverlay2;
            while (i3 < this.A.size) {
                Item item2 = this.A.items[i3].getItem();
                if (item2 instanceof TileItem) {
                    if (!Game.i.progressManager.isStarred(item2)) {
                        r3 = i3;
                        d += ((TileItem) item2).tile.getPrestigeScore() * this.A.items[r3 == true ? 1 : 0].getCount();
                    }
                } else if ((item2 instanceof GateItem) && !Game.i.progressManager.isStarred(item2)) {
                    r3 = i3;
                    d += ((GateItem) item2).gate.getPrestigeScore() * this.A.items[r3 == true ? 1 : 0].getCount();
                }
                i3++;
                r3 = r3;
            }
            double d2 = r3 == true ? 1 : 0;
            if (((int) (d * 250.0d)) > 0) {
                a(Game.i.localeManager.i18n.get("gv_title_PRESTIGE_MODE") + " (" + ((Object) StringFormatter.compactNumber(d2, false)) + " mP)", Game.i.assetManager.getDrawable("icon-crown"), MaterialColor.BLUE.values, () -> {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("dialog_confirm_mass_item_prestige"), () -> {
                        double d3 = 0.0d;
                        for (int i4 = 0; i4 < this.A.size; i4++) {
                            Item item3 = this.A.items[i4].getItem();
                            if (d3 >= 9.99999999E8d) {
                                break;
                            }
                            if (!Game.i.progressManager.isStarred(item3)) {
                                if (item3 instanceof TileItem) {
                                    if (Game.i.progressManager.removeItems(this.A.items[i4].getItem(), this.A.items[i4].getCount())) {
                                        d3 += ((TileItem) item3).tile.getPrestigeScore() * this.A.items[i4].getCount();
                                    }
                                } else if ((item3 instanceof GateItem) && Game.i.progressManager.removeItems(this.A.items[i4].getItem(), this.A.items[i4].getCount())) {
                                    d3 += ((GateItem) item3).gate.getPrestigeScore() * this.A.items[i4].getCount();
                                }
                            }
                        }
                        Game.i.progressManager.addItems(Item.D.PRESTIGE_DUST, (int) (d3 * 250.0d * ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE))), "inventory_item_prestige");
                        c();
                        update();
                    });
                }, null, table, table2);
            }
            G.clear();
            for (int i4 = 0; i4 < this.A.size; i4++) {
                ItemStack itemStack = this.A.items[i4];
                if (itemStack.getItem().canBeSold() && !Game.i.progressManager.isStarred(itemStack.getItem())) {
                    itemStack.getItem().addSellItemsMultiplied(G, itemStack.getCount());
                }
            }
            ProgressManager.compressStacksArray(G);
            if (G.size != 0) {
                a(Game.i.localeManager.i18n.get("sell_button"), Game.i.assetManager.getDrawable("icon-dollar"), MaterialColor.RED.values, () -> {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("dialog_confirm_mass_item_sell"), () -> {
                        for (int i5 = 0; i5 < this.A.size; i5++) {
                            Game.i.progressManager.sellItems(this.A.items[i5].getItem(), this.A.items[i5].getCount());
                        }
                        c();
                        update();
                    });
                }, null, table, table2);
                Table table3 = new Table();
                for (int i5 = 0; i5 < G.size; i5++) {
                    ItemStack itemStack2 = G.items[i5];
                    Table table4 = new Table();
                    table4.add((Table) itemStack2.getItem().generateIcon(24.0f, false)).size(24.0f).padRight(6.0f).padLeft(8.0f);
                    table4.add((Table) new Label(new StringBuilder().append((Object) StringFormatter.compactNumber(itemStack2.getCount(), false)).toString(), Game.i.assetManager.getLabelStyle(21))).padRight(8.0f);
                    table3.add(table4);
                    if (i5 == 2) {
                        break;
                    }
                }
                if (G.size > 3) {
                    table3.add((Table) new Label("+", Game.i.assetManager.getLabelStyle(24))).padRight(8.0f);
                }
                table.add(table3).padBottom(8.0f).padTop(8.0f).height(24.0f).row();
                table2.add().width(1.0f).height(40.0f).row();
            }
        } else if (this.z != null) {
            item = this.z.getItem();
            if (this.w == TabType.CRAFTING) {
                Array<CraftRecipe> craftRecipes = Game.i.itemManager.getCraftRecipes(item);
                for (int i6 = 0; i6 < craftRecipes.size; i6++) {
                    CraftRecipe craftRecipe = craftRecipes.items[i6];
                    ?? complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
                        this.q = craftRecipe;
                        this.r = null;
                        ItemCountSelectionOverlay.i().show(Game.i.localeManager.i18n.get("craft_button") + " - " + ((Object) craftRecipe.result.getItem().getTitle()), 1, 1, craftRecipe.result.getItem(), new ItemCountSelectionOverlay.ItemCountSelectionListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.4
                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void countChanged(int i7) {
                                InventoryOverlay.this.f();
                            }

                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void selectionConfirmed(int i7) {
                                Array<Item> array2 = new Array<>(Item.class);
                                for (int i8 = 0; i8 < craftRecipe.ingredients.size; i8++) {
                                    Array<ItemStack> suitableItemsFromInventory = craftRecipe.ingredients.items[i8].getSuitableItemsFromInventory();
                                    Item item3 = null;
                                    if (suitableItemsFromInventory.size != 0) {
                                        int i9 = InventoryOverlay.this.r[i8];
                                        if (suitableItemsFromInventory.size <= i9) {
                                            i9 = 0;
                                        }
                                        item3 = suitableItemsFromInventory.items[i9].getItem();
                                    }
                                    array2.add(item3);
                                }
                                Game.i.progressManager.startCrafting(craftRecipe, array2, i7);
                                InventoryOverlay.this.update();
                            }

                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void selectionCanceled() {
                            }
                        });
                        f();
                    });
                    complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 120.0f, 328.0f, 117.0f, 323.0f, 3.0f})), 0.0f, 10.0f, 328.0f, 120.0f);
                    complexButton.backgroundShadow.setVisible(true);
                    complexButton.backgroundShadow.setSize(327.0f, 87.0f);
                    complexButton.backgroundShadow.setColor(0.0f, 0.0f, 0.0f, 0.14f);
                    complexButton.backgroundShadow.setDrawable(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{14.0f, 0.0f, 6.0f, 87.0f, 327.0f, 85.0f, 320.0f, 11.0f})));
                    complexButton.setBackgroundColors(MaterialColor.BLUE_GREY.P800, MaterialColor.BLUE_GREY.P900, MaterialColor.BLUE_GREY.P700, MaterialColor.GREY.P700);
                    table.add((Table) complexButton).size(328.0f, 130.0f).padTop(8.0f).padLeft(23.0f).left().row();
                    Group group = new Group();
                    group.setTransform(false);
                    group.setPosition(14.0f, 22.0f);
                    group.setSize(290.0f, 96.0f);
                    complexButton.addActor(group);
                    for (int i7 = 0; i7 < craftRecipe.ingredients.size; i7++) {
                        float f2 = i7 * 96.0f;
                        CraftRecipe.Ingredient ingredient = craftRecipe.ingredients.items[i7];
                        Group group2 = new Group();
                        group2.setPosition(f2 + 16.0f, 20.0f);
                        group2.setSize(64.0f, 64.0f);
                        group.addActor(group2);
                        Item[] exampleItems = ingredient.getExampleItems();
                        if (exampleItems.length == 1) {
                            group2.addActor(exampleItems[0].generateIcon(64.0f, true));
                        } else {
                            Array array2 = new Array();
                            for (Item item3 : exampleItems) {
                                array2.add(item3.generateIcon(64.0f, true));
                            }
                            int[] iArr = {0};
                            group2.addActor((Actor) array2.first());
                            group2.addAction(Actions.forever(Actions.sequence(Actions.delay(0.75f), Actions.run(() -> {
                                iArr[0] = iArr[0] + 1;
                                if (iArr[0] == array2.size) {
                                    iArr[0] = 0;
                                }
                                group2.clearChildren();
                                group2.addActor((Actor) array2.get(iArr[0]));
                            }))));
                        }
                        int countWithGVs = ingredient.getCountWithGVs();
                        Label label5 = new Label(new StringBuilder().append(countWithGVs).toString(), Game.i.assetManager.getLabelStyle(21));
                        label5.setAlignment(1);
                        label5.setPosition((f2 + 72.0f) - 2.0f, 14.0f);
                        label5.setSize(1.0f, 16.0f);
                        label5.setColor(MaterialColor.BLUE_GREY.P800);
                        group.addActor(label5);
                        Label label6 = new Label(new StringBuilder().append(countWithGVs).toString(), Game.i.assetManager.getLabelStyle(21));
                        label6.setAlignment(1);
                        label6.setPosition(f2 + 72.0f, 12.0f);
                        label6.setSize(1.0f, 16.0f);
                        group.addActor(label6);
                        if (i7 != 0) {
                            Image image = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                            image.setSize(24.0f, 24.0f);
                            image.setPosition(f2 - 12.0f, 36.0f);
                            group.addActor(image);
                        }
                    }
                    Image image2 = new Image(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 13.0f, 19.0f, 13.0f, 19.0f, 13.0f})));
                    image2.setColor(MaterialColor.BLUE_GREY.P900);
                    image2.getColor().lerp(Color.BLACK, 0.5f);
                    table2.add((Table) image2).size(19.0f, 13.0f).padLeft(326.0f).padTop(125.0f).left().row();
                }
            } else {
                G.clear();
                if (this.z.getItem().canBeSold()) {
                    this.z.getItem().addSellItems(G);
                }
                if (G.size != 0) {
                    int i8 = 0;
                    for (int i9 = 0; i9 < G.size; i9++) {
                        if (G.items[i9].getCount() > i8) {
                            i8 = G.items[i9].getCount();
                        }
                    }
                    int i10 = i8;
                    ComplexButton a2 = a(Game.i.localeManager.i18n.get("sell_button"), Game.i.assetManager.getDrawable("icon-dollar"), MaterialColor.RED.values, () -> {
                        if (Game.i.progressManager.isStarred(this.z.getItem())) {
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("starred_item_cant_be_sold"));
                            return;
                        }
                        int count = this.z.getCount();
                        int i11 = 500000000 / i10;
                        if (count > i11) {
                            count = i11;
                        }
                        ItemCountSelectionOverlay.i().show(Game.i.localeManager.i18n.get("sell_button") + " - " + ((Object) this.z.getItem().getTitle()), 1, count, this.z.getItem(), this.D);
                        Table infoContainer = ItemCountSelectionOverlay.i().getInfoContainer();
                        infoContainer.clear();
                        this.E.clear();
                        for (int i12 = 0; i12 < G.size; i12++) {
                            infoContainer.add((Table) G.items[i12].getItem().generateIcon(32.0f, false)).size(32.0f).padLeft(8.0f);
                            Label label7 = new Label("0", Game.i.assetManager.getLabelStyle(24));
                            infoContainer.add((Table) label7).padLeft(6.0f).padRight(8.0f);
                            this.E.add(label7);
                        }
                        e();
                    }, null, table, table2);
                    if (Game.i.progressManager.isStarred(this.z.getItem())) {
                        a2.setBackgroundColors(MaterialColor.GREY.P800, MaterialColor.GREY.P900, MaterialColor.GREY.P700, MaterialColor.GREY.P800);
                    }
                    Table table5 = new Table();
                    for (int i11 = 0; i11 < G.size; i11++) {
                        ItemStack itemStack3 = G.items[i11];
                        Table table6 = new Table();
                        table6.add((Table) itemStack3.getItem().generateIcon(24.0f, false)).size(24.0f).padRight(6.0f).padLeft(8.0f);
                        table6.add((Table) new Label(new StringBuilder().append(itemStack3.getCount()).toString(), Game.i.assetManager.getLabelStyle(21))).padRight(8.0f);
                        table5.add(table6);
                        if (i11 == 2) {
                            break;
                        }
                    }
                    if (G.size > 3) {
                        table5.add((Table) new Label("+", Game.i.assetManager.getLabelStyle(24))).padRight(8.0f);
                    }
                    table.add(table5).padBottom(8.0f).padTop(8.0f).height(24.0f).row();
                    table2.add().width(1.0f).height(40.0f).row();
                }
                if (this.z.getItem().canBeUnpacked()) {
                    Runnable runnable = null;
                    if (this.z.getCount() > 1) {
                        runnable = () -> {
                            int i12 = 200;
                            if ((this.z.getItem() instanceof RandomTileItem) || (this.z.getItem() instanceof RandomBarrierItem)) {
                                i12 = 2000;
                            }
                            int min = Math.min(i12, this.z.getCount());
                            ItemCountSelectionOverlay.i().show(Game.i.localeManager.i18n.get("open_pack_button"), 1, min, this.z.getItem(), new ItemCountSelectionOverlay.ItemCountSelectionListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.5
                                @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                                public void countChanged(int i13) {
                                }

                                @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                                public void selectionConfirmed(int i13) {
                                    if (InventoryOverlay.this.z != null) {
                                        Game.i.progressManager.openPack(InventoryOverlay.this.z.getItem(), i13, true, false);
                                    }
                                    InventoryOverlay.this.update();
                                    InventoryOverlay.this.k();
                                }

                                @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                                public void selectionCanceled() {
                                }
                            });
                            ItemCountSelectionOverlay.i().setSelectedCount(min);
                        };
                    }
                    a(Game.i.localeManager.i18n.get("open_pack_button"), Game.i.assetManager.getDrawable("icon-eye"), MaterialColor.GREEN.values, () -> {
                        Game.i.progressManager.openPack(this.z.getItem(), 1, true, false);
                        update();
                        k();
                    }, runnable, table, table2);
                }
                if (this.z.getItem().getType() == ItemType.LUCKY_SHOT_TOKEN) {
                    a(Game.i.localeManager.i18n.get("lucky_shot"), Game.i.assetManager.getDrawable("icon-lucky-wheel"), MaterialColor.GREEN.values, () -> {
                        hideWithToggleButton(true);
                        LuckyWheelOverlay.i().setVisible(true);
                    }, null, table, table2);
                }
                if (this.z.getItem() instanceof Item.UsableItem) {
                    Item.UsableItem usableItem = (Item.UsableItem) this.z.getItem();
                    a(Game.i.localeManager.i18n.get("use_item_button"), Game.i.assetManager.getDrawable("icon-check"), MaterialColor.LIGHT_BLUE.values, () -> {
                        if (usableItem.useItemNeedsConfirmation()) {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("dialog_confirm_item_usage"), () -> {
                                usableItem.useItem();
                                update();
                            });
                        } else {
                            usableItem.useItem();
                            update();
                        }
                    }, null, table, table2);
                }
            }
        }
        if (item != null) {
            if (!z) {
                Actor generateIcon = item.generateIcon(128.0f, true);
                generateIcon.setPosition(107.0f, 692.0f + scaledViewportHeight);
                this.h.addActor(generateIcon);
                Item item4 = item;
                TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("icon-star");
                Runnable runnable2 = () -> {
                    Game.i.progressManager.setStarred(item4, !Game.i.progressManager.isStarred(item4));
                    update();
                };
                Color color = Color.WHITE;
                PaddedImageButton paddedImageButton = new PaddedImageButton(drawable, runnable2, color, color, Color.WHITE);
                paddedImageButton.setIconSize(40.0f, 40.0f);
                paddedImageButton.setIconPosition(8.0f, 8.0f);
                paddedImageButton.setSize(56.0f, 56.0f);
                paddedImageButton.setPosition(260.0f, ((692.0f + scaledViewportHeight) + 128.0f) - 32.0f);
                this.h.addActor(paddedImageButton);
                if (Game.i.progressManager.isStarred(item)) {
                    paddedImageButton.setColors(MaterialColor.YELLOW.P500, MaterialColor.YELLOW.P200, MaterialColor.YELLOW.P700);
                } else {
                    paddedImageButton.setIcon(Game.i.assetManager.getDrawable("icon-star-hollow"));
                    paddedImageButton.setColors(new Color(1.0f, 1.0f, 1.0f, 0.28f), MaterialColor.YELLOW.P200, MaterialColor.YELLOW.P700);
                }
            }
            if (Game.i.progressManager.isDeveloperModeEnabled() && this.z != null) {
                Image image3 = new Image(Game.i.assetManager.getDrawable("icon-edit"));
                image3.setSize(48.0f, 48.0f);
                image3.setColor(MaterialColor.LIGHT_BLUE.P500);
                image3.setTouchable(Touchable.enabled);
                image3.setPosition(32.0f, 788.0f + scaledViewportHeight);
                image3.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.6
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f3, float f4) {
                        InventoryOverlay.this.hideWithToggleButton(true);
                        ItemCreationOverlay.i().showForItem(InventoryOverlay.this.z.getItem());
                    }
                });
                this.h.addActor(image3);
            }
            if (!z) {
                RarityType rarity = item.getRarity();
                Label label7 = new Label(item.getTitle(), Game.i.assetManager.getLabelStyle(30));
                label7.setAlignment(1);
                label7.setPosition(108.0f, 632.0f + scaledViewportHeight);
                label7.setSize(128.0f, 32.0f);
                label7.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                this.h.addActor(label7);
                Label label8 = new Label(item.getTitle(), Game.i.assetManager.getLabelStyle(30));
                label8.setAlignment(1);
                label8.setPosition(107.0f, 635.0f + scaledViewportHeight);
                label8.setSize(128.0f, 32.0f);
                label8.setColor(Game.i.progressManager.getRarityBrightColor(rarity));
                this.h.addActor(label8);
                Label label9 = new Label(Game.i.progressManager.getRarityName(rarity).toUpperCase(), Game.i.assetManager.getLabelStyle(21));
                label9.setAlignment(1);
                label9.setPosition(107.0f, 613.0f + scaledViewportHeight);
                label9.setSize(128.0f, 16.0f);
                label9.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.h.addActor(label9);
                Label label10 = new Label(item.getDescription(), Game.i.assetManager.getLabelStyle(24));
                label10.setPosition(21.0f, 535.0f + scaledViewportHeight);
                label10.setSize(300.0f, 64.0f);
                label10.setWrap(true);
                label10.setAlignment(2);
                this.h.addActor(label10);
            }
            Table table7 = new Table();
            table7.add().height(8.0f).width(300.0f).row();
            item.fillWithInfo(table7, 300.0f);
            table7.row();
            table7.add().expandY().fillY().padBottom(120.0f);
            ScrollPane scrollPane = new ScrollPane(table7, Game.i.assetManager.getScrollPaneStyle(8.0f));
            UiUtils.enableMouseMoveScrollFocus(scrollPane);
            scrollPane.setPosition(21.0f, 124.0f);
            scrollPane.setSize(300.0f, 380.0f + scaledViewportHeight);
            scrollPane.setScrollingDisabled(true, false);
            this.h.addActor(scrollPane);
        } else if (z2) {
            Label label11 = new Label(Game.i.localeManager.i18n.get("inventory_no_item_selected_hint"), Game.i.assetManager.getLabelStyle(21));
            label11.setSize(282.0f, 860.0f + scaledViewportHeight);
            label11.setAlignment(1);
            label11.setWrap(true);
            label11.setPosition(30.0f, 0.0f);
            this.h.addActor(label11);
        }
        table.setZIndex(9001);
    }

    public final void setSelectedItem(ItemStack itemStack) {
        ItemCell itemCell;
        if (this.z != null) {
            ItemCell itemCell2 = this.p.get(this.z, null);
            if (itemCell2 != null) {
                itemCell2.setSelected(false);
            }
            this.z = null;
        }
        this.z = itemStack;
        if (itemStack != null && (itemCell = this.p.get(itemStack, null)) != null) {
            itemCell.setSelected(true);
        }
        j();
    }

    public final void update() {
        updateAndScroll(this.k == null ? -1.0f : this.k.getScrollY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.z != null && Game.i.progressManager.getItemsCount(this.z.getItem()) == 0) {
            setSelectedItem(null);
        }
    }

    private void l() {
        this.g.clearChildren();
        this.t.clear();
        this.i.clearChildren();
        this.p.clear();
        this.h.clearChildren();
        this.n.clearChildren();
        if (this.k != null) {
            if (Game.i.uiManager.stage.getScrollFocus() == this.k) {
                Game.i.uiManager.stage.setScrollFocus(null);
            }
            this.k.remove();
        }
        this.k = null;
    }

    public final void updateAndScroll(float f) {
        h();
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        l();
        switch (this.w) {
            case CRAFTING:
                Table table = new Table();
                this.k = new ScrollPane(table);
                UiUtils.enableMouseMoveScrollFocus(this.k);
                this.k.setName("Inventory itemsScroll");
                this.k.setSize(862.0f, 832.0f + scaledViewportHeight);
                this.g.addActor(this.k);
                Game.i.uiManager.stage.setScrollFocus(this.k);
                Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
                image.setColor(new Color(791621631));
                image.setSize(862.0f, 24.0f);
                image.setPosition(0.0f, 809.0f + scaledViewportHeight);
                image.setTouchable(Touchable.disabled);
                this.g.addActor(image);
                Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
                image2.setColor(new Color(791621631));
                image2.setSize(478.0f, 96.0f);
                image2.setPosition(0.0f, -1.0f);
                image2.setTouchable(Touchable.disabled);
                this.g.addActor(image2);
                table.add().colspan(6).height(16.0f).row();
                float f2 = 16.0f;
                Array<SubcategoryItems> m = m();
                int i = 0;
                for (int i2 = 0; i2 < m.size; i2++) {
                    SubcategoryItems subcategoryItems = m.items[i2];
                    float f3 = f2 + 30.0f;
                    TextureRegionDrawable drawable = Game.i.assetManager.getDrawable(subcategoryItems.getIconAlias());
                    PaddedImageButton paddedImageButton = new PaddedImageButton(drawable, () -> {
                        this.k.setScrollY(f3 - 40.0f);
                    }, subcategoryItems.getColor(), Color.WHITE, subcategoryItems.getColor());
                    paddedImageButton.setIconSize(48.0f, 48.0f);
                    paddedImageButton.setIconPosition(16.0f, 8.0f);
                    paddedImageButton.setShadow(drawable, 19.0f, 5.0f, 48.0f, 48.0f, new Color(0.0f, 0.0f, 0.0f, 0.28f));
                    paddedImageButton.setSize(80.0f, 64.0f);
                    this.i.addActor(paddedImageButton);
                    SubcategoryButtonConfig subcategoryButtonConfig = new SubcategoryButtonConfig(this, (byte) 0);
                    subcategoryButtonConfig.distanceY = f3;
                    subcategoryButtonConfig.button = paddedImageButton;
                    this.t.add(subcategoryButtonConfig);
                    Label label = new Label(subcategoryItems.getTitle(), Game.i.assetManager.getLabelStyle(30));
                    label.setColor(subcategoryItems.getColor());
                    table.add((Table) label).height(52.0f).width(768.0f).padTop(8.0f).colspan(6).row();
                    float f4 = f2 + 60.0f;
                    int ceil = MathUtils.ceil(subcategoryItems.items.size / 6.0f);
                    for (int i3 = 0; i3 < ceil * 6; i3++) {
                        ItemCell itemCell = new ItemCell();
                        itemCell.setColRow(i3 % 6, i);
                        if (i3 < subcategoryItems.items.size) {
                            final ItemStack itemStack = subcategoryItems.items.items[i3];
                            this.p.put(itemStack, itemCell);
                            itemCell.setItem(itemStack.getItem(), 0);
                            if (Game.i.progressManager.isStarred(itemStack.getItem())) {
                                itemCell.markStarred(true);
                            }
                            if (itemStack.isCovered()) {
                                itemCell.setColor(1.0f, 1.0f, 1.0f, 0.44f);
                            }
                            if (this.z != null && itemStack.getItem().sameAs(this.z.getItem())) {
                                itemCell.setSelected(true);
                                this.z = itemStack;
                            }
                            itemCell.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.7
                                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                public void clicked(InputEvent inputEvent, float f5, float f6) {
                                    InventoryOverlay.this.setSelectedItem(itemStack);
                                }
                            });
                        }
                        Cell add = table.add((Table) itemCell);
                        if ((i3 + 1) % 6 == 0) {
                            i++;
                            add.row();
                        }
                    }
                    f2 = f4 + (ceil * 140.0f);
                }
                table.row();
                float f5 = (832.0f + scaledViewportHeight) - f2;
                float f6 = f5;
                if (f5 < 0.0f) {
                    f6 = 0.0f;
                }
                table.add().colspan(6).height(f6 + 128.0f);
                break;
            default:
                Table table2 = new Table();
                this.k = new ScrollPane(table2);
                UiUtils.enableMouseMoveScrollFocus(this.k);
                this.k.setName("Inventory itemsScroll");
                this.k.setSize(862.0f, 832.0f + scaledViewportHeight);
                this.g.addActor(this.k);
                Game.i.uiManager.stage.setScrollFocus(this.k);
                Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
                image3.setColor(new Color(791621631));
                image3.setSize(862.0f, 24.0f);
                image3.setPosition(0.0f, 809.0f + scaledViewportHeight);
                image3.setTouchable(Touchable.disabled);
                this.g.addActor(image3);
                Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
                image4.setColor(new Color(791621631));
                image4.setSize(862.0f, 96.0f);
                image4.setPosition(0.0f, -1.0f);
                image4.setTouchable(Touchable.disabled);
                this.g.addActor(image4);
                table2.add().colspan(6).height(16.0f).row();
                float f7 = 16.0f;
                Array<SubcategoryItems> m2 = m();
                int i4 = 0;
                for (int i5 = 0; i5 < m2.size; i5++) {
                    SubcategoryItems subcategoryItems2 = m2.items[i5];
                    if (subcategoryItems2.subcategoryType != ItemSubcategoryType.P_ENCRYPTED || subcategoryItems2.items.size != 0) {
                        float f8 = f7 + 30.0f;
                        TextureRegionDrawable drawable2 = Game.i.assetManager.getDrawable(subcategoryItems2.getIconAlias());
                        PaddedImageButton paddedImageButton2 = new PaddedImageButton(drawable2, () -> {
                            this.k.setScrollY(f8 - 40.0f);
                        }, subcategoryItems2.getColor(), Color.WHITE, subcategoryItems2.getColor());
                        paddedImageButton2.setIconSize(48.0f, 48.0f);
                        paddedImageButton2.setIconPosition(16.0f, 8.0f);
                        paddedImageButton2.setShadow(drawable2, 19.0f, 5.0f, 48.0f, 48.0f, new Color(0.0f, 0.0f, 0.0f, 0.28f));
                        paddedImageButton2.setSize(80.0f, 64.0f);
                        this.i.addActor(paddedImageButton2);
                        SubcategoryButtonConfig subcategoryButtonConfig2 = new SubcategoryButtonConfig(this, (byte) 0);
                        subcategoryButtonConfig2.distanceY = f8;
                        subcategoryButtonConfig2.button = paddedImageButton2;
                        this.t.add(subcategoryButtonConfig2);
                        String str = "";
                        if (subcategoryItems2.subcategoryType == ItemSubcategoryType.ME_SPAWNS) {
                            str = Game.i.progressManager.getInventoryStatistics().byTileType[TileType.SPAWN.ordinal()] + " / 500";
                        } else if (subcategoryItems2.subcategoryType == ItemSubcategoryType.ME_SOURCES) {
                            str = Game.i.progressManager.getInventoryStatistics().byTileType[TileType.SOURCE.ordinal()] + " / 500";
                        } else if (subcategoryItems2.subcategoryType == ItemSubcategoryType.ME_BASES) {
                            str = Game.i.progressManager.getInventoryStatistics().byTileType[TileType.TARGET.ordinal()] + " / 500";
                        } else if (subcategoryItems2.subcategoryType == ItemSubcategoryType.ME_SOUNDS) {
                            str = Game.i.progressManager.getInventoryStatistics().byTileType[TileType.XM_MUSIC_TRACK.ordinal()] + " / 50";
                        }
                        Label label2 = new Label(subcategoryItems2.getTitle(), Game.i.assetManager.getLabelStyle(30));
                        label2.setColor(subcategoryItems2.getColor());
                        Label label3 = new Label(str, Game.i.assetManager.getLabelStyle(24));
                        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        label3.setAlignment(16);
                        table2.add((Table) label2).height(52.0f).width(512.0f).padTop(8.0f).colspan(4);
                        table2.add((Table) label3).height(52.0f).width(256.0f).fillX().padTop(8.0f).colspan(4).row();
                        float f9 = f7 + 60.0f;
                        int ceil2 = MathUtils.ceil(subcategoryItems2.items.size / 6.0f);
                        for (int i6 = 0; i6 < ceil2 * 6; i6++) {
                            final ItemCell itemCell2 = new ItemCell();
                            itemCell2.setColRow(i6 % 6, i4);
                            if (i6 < subcategoryItems2.items.size) {
                                final ItemStack itemStack2 = subcategoryItems2.items.items[i6];
                                this.p.put(itemStack2, itemCell2);
                                itemCell2.setItemStack(itemStack2);
                                if (Game.i.progressManager.isStarred(itemStack2.getItem())) {
                                    itemCell2.markStarred(true);
                                }
                                if (itemStack2.getItem().getType() == ItemType.CASE) {
                                    itemCell2.setNotificationBubbleEnabled(true);
                                } else if ((itemStack2.getItem() instanceof CaseKeyItem) && itemStack2.getCount() >= 10) {
                                    itemCell2.setNotificationBubbleEnabled(true);
                                }
                                itemCell2.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.8
                                    private Timer.Task c;
                                    private float d;
                                    private float e;

                                    static /* synthetic */ float a(AnonymousClass8 anonymousClass8, float f10) {
                                        anonymousClass8.d = -100.0f;
                                        return -100.0f;
                                    }

                                    static /* synthetic */ float b(AnonymousClass8 anonymousClass8, float f10) {
                                        anonymousClass8.e = -100.0f;
                                        return -100.0f;
                                    }

                                    @Override // com.prineside.tdi2.scene2d.InputListener
                                    public boolean touchDown(InputEvent inputEvent, float f10, float f11, int i7, int i8) {
                                        if (i7 > 0) {
                                            return false;
                                        }
                                        if (i8 == 0) {
                                            this.d = f10;
                                            this.e = f11;
                                            if (InventoryOverlay.this.b()) {
                                                this.c = null;
                                                return true;
                                            }
                                            this.c = new Timer.Task() { // from class: com.prineside.tdi2.ui.shared.InventoryOverlay.8.1
                                                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                                                public void run() {
                                                    InventoryOverlay.this.a(itemStack2);
                                                    AnonymousClass8.a(AnonymousClass8.this, -100.0f);
                                                    AnonymousClass8.b(AnonymousClass8.this, -100.0f);
                                                    if (InventoryOverlay.this.H != null) {
                                                        InventoryOverlay.this.H.disappearing = true;
                                                        InventoryOverlay.this.H = null;
                                                    }
                                                }
                                            };
                                            Timer.schedule(this.c, 0.75f);
                                            InventoryOverlay.this.H = new ButtonHoldHint(f10, f11, 0.75f);
                                            itemCell2.addActor(InventoryOverlay.this.H);
                                            return true;
                                        }
                                        if (i8 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                                            if (InventoryOverlay.this.b()) {
                                                this.c = null;
                                                return true;
                                            }
                                            if (this.c != null && this.c.isScheduled()) {
                                                this.c.cancel();
                                            }
                                            InventoryOverlay.this.a(itemStack2);
                                            this.d = -100.0f;
                                            this.e = -100.0f;
                                            if (InventoryOverlay.this.H != null) {
                                                InventoryOverlay.this.H.disappearing = true;
                                                InventoryOverlay.this.H = null;
                                                return true;
                                            }
                                            return true;
                                        }
                                        return true;
                                    }

                                    @Override // com.prineside.tdi2.scene2d.InputListener
                                    public void touchUp(InputEvent inputEvent, float f10, float f11, int i7, int i8) {
                                        super.touchUp(inputEvent, f10, f11, i7, i8);
                                        if (InventoryOverlay.this.H != null) {
                                            ButtonHoldHint buttonHoldHint = InventoryOverlay.this.H;
                                            Threads i9 = Threads.i();
                                            Objects.requireNonNull(buttonHoldHint);
                                            i9.postRunnable(buttonHoldHint::remove);
                                            InventoryOverlay.this.H = null;
                                        }
                                        if (PMath.getDistanceBetweenPoints(f10, f11, this.d, this.e) <= 16.0f) {
                                            if (InventoryOverlay.this.b()) {
                                                InventoryOverlay.this.a(itemStack2);
                                                return;
                                            } else {
                                                if (this.c != null) {
                                                    this.c.cancel();
                                                    this.c = null;
                                                    InventoryOverlay.this.setSelectedItem(itemStack2);
                                                    return;
                                                }
                                                return;
                                            }
                                        }
                                        if (this.c != null) {
                                            this.c.cancel();
                                            this.c = null;
                                        }
                                    }
                                });
                            }
                            Cell add2 = table2.add((Table) itemCell2);
                            if ((i6 + 1) % 6 == 0) {
                                i4++;
                                add2.row();
                            }
                        }
                        f7 = f9 + (ceil2 * 140.0f);
                    }
                }
                table2.row();
                float f10 = (832.0f - f7) + scaledViewportHeight;
                float f11 = f10;
                if (f10 < 0.0f) {
                    f11 = 0.0f;
                }
                table2.add().colspan(6).height(f11 + 128.0f);
                break;
        }
        if (this.p.size > 2000 && !this.s) {
            Notifications.i().add(Game.i.localeManager.i18n.get("too_many_items_sell_advise"), Game.i.assetManager.getDrawable("icon-trash-bin-dollar"), MaterialColor.DEEP_ORANGE.P800, StaticSoundType.WARNING);
            this.s = true;
        }
        g();
        if (b()) {
            for (int i7 = 0; i7 < this.A.size; i7++) {
                ItemCell itemCell3 = this.p.get(this.A.items[i7], null);
                if (itemCell3 != null) {
                    itemCell3.setSelected(true);
                }
            }
        } else if (this.z != null) {
            setSelectedItem(this.z);
        }
        this.k.layout();
        if (f != -1.0f && this.k != null) {
            this.k.setScrollY(f);
            this.k.updateVisualScroll();
        }
        j();
    }

    private Array<SubcategoryItems> m() {
        SubcategoryItems subcategoryItems;
        SubcategoryItems subcategoryItems2;
        Array<SubcategoryItems> array = new Array<>(SubcategoryItems.class);
        TabConfig tabConfig = u[this.w.ordinal()];
        if (this.w == TabType.CRAFTING) {
            this.B.clear();
            for (int i = 0; i < Game.i.itemManager.craftRecipes.size; i++) {
                CraftRecipe craftRecipe = Game.i.itemManager.craftRecipes.items[i];
                if (craftRecipe.isAvailable()) {
                    boolean hasEnoughItemsToRun = craftRecipe.hasEnoughItemsToRun();
                    Item item = craftRecipe.result.getItem();
                    boolean z = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.B.size) {
                            break;
                        }
                        ItemStack itemStack = this.B.items[i2];
                        if (!itemStack.getItem().sameAs(item)) {
                            i2++;
                        } else {
                            z = true;
                            itemStack.setCount(this.B.items[i2].getCount() + 1);
                            if (hasEnoughItemsToRun && itemStack.isCovered()) {
                                itemStack.setCovered(false);
                            }
                        }
                    }
                    if (!z) {
                        ItemStack itemStack2 = new ItemStack(item, 1);
                        itemStack2.setCovered(!hasEnoughItemsToRun);
                        this.B.add(itemStack2);
                    }
                }
            }
            if (this.y == ItemSortingType.RARITY) {
                this.B.sort(ItemStack.SORT_COMPARATOR_RARITY_DESC);
                RarityType rarityType = null;
                SubcategoryItems subcategoryItems3 = null;
                for (int i3 = 0; i3 < this.B.size; i3++) {
                    ItemStack itemStack3 = this.B.items[i3];
                    RarityType rarity = itemStack3.getItem().getRarity();
                    if (rarity != rarityType) {
                        SubcategoryItems subcategoryItems4 = new SubcategoryItems(this, (byte) 0);
                        subcategoryItems2 = subcategoryItems4;
                        subcategoryItems4.rarityType = rarity;
                        array.add(subcategoryItems2);
                        rarityType = rarity;
                        subcategoryItems3 = subcategoryItems2;
                    } else {
                        subcategoryItems2 = subcategoryItems3;
                    }
                    subcategoryItems2.items.add(itemStack3);
                }
                for (int i4 = 0; i4 < array.size; i4++) {
                    array.items[i4].items.sort(ItemStack.SORT_COMPARATOR_KIND);
                }
            } else {
                for (ItemSubcategoryType itemSubcategoryType : tabConfig.subcategories) {
                    SubcategoryItems subcategoryItems5 = new SubcategoryItems(this, (byte) 0);
                    subcategoryItems5.subcategoryType = itemSubcategoryType;
                    for (int i5 = 0; i5 < this.B.size; i5++) {
                        ItemStack itemStack4 = this.B.items[i5];
                        if (itemStack4.getItem().getSubcategory() == itemSubcategoryType) {
                            subcategoryItems5.items.add(itemStack4);
                        }
                    }
                    if (subcategoryItems5.items.size != 0) {
                        subcategoryItems5.items.sort(ItemStack.SORT_COMPARATOR_RARITY_DESC);
                        array.add(subcategoryItems5);
                    }
                }
            }
        } else if (this.y == ItemSortingType.KIND) {
            for (ItemSubcategoryType itemSubcategoryType2 : tabConfig.subcategories) {
                SubcategoryItems subcategoryItems6 = new SubcategoryItems(this, (byte) 0);
                subcategoryItems6.subcategoryType = itemSubcategoryType2;
                subcategoryItems6.items.addAll(Game.i.progressManager.getItemsBySubcategory(itemSubcategoryType2));
                subcategoryItems6.items.sort(ItemStack.SORT_COMPARATOR_RARITY_DESC);
                array.add(subcategoryItems6);
            }
        } else if (this.y == ItemSortingType.RARITY) {
            this.B.clear();
            for (ItemSubcategoryType itemSubcategoryType3 : tabConfig.subcategories) {
                this.B.addAll(Game.i.progressManager.getItemsBySubcategory(itemSubcategoryType3));
            }
            this.B.sort(ItemStack.SORT_COMPARATOR_RARITY_DESC);
            RarityType rarityType2 = null;
            SubcategoryItems subcategoryItems7 = null;
            for (int i6 = 0; i6 < this.B.size; i6++) {
                ItemStack itemStack5 = this.B.items[i6];
                RarityType rarity2 = itemStack5.getItem().getRarity();
                if (rarity2 != rarityType2) {
                    SubcategoryItems subcategoryItems8 = new SubcategoryItems(this, (byte) 0);
                    subcategoryItems = subcategoryItems8;
                    subcategoryItems8.rarityType = rarity2;
                    array.add(subcategoryItems);
                    rarityType2 = rarity2;
                    subcategoryItems7 = subcategoryItems;
                } else {
                    subcategoryItems = subcategoryItems7;
                }
                subcategoryItems.items.add(itemStack5);
            }
            for (int i7 = 0; i7 < array.size; i7++) {
                array.items[i7].items.sort(ItemStack.SORT_COMPARATOR_KIND);
            }
        }
        return array;
    }

    private void a(boolean z) {
        this.d.clearActions();
        if (z) {
            this.d.addAction(Actions.sequence(Actions.show(), Actions.moveTo(0.0f, 0.0f, 0.15f)));
            if (Game.i.progressManager.getItemsByType(ItemType.TILE).size != 0 && !TooltipsOverlay.i().isTagShown("InventoryOverlay.button")) {
                TooltipsOverlay.i().showText("InventoryOverlay.button", this.d.icon, Game.i.localeManager.i18n.get("tooltip_inventory_button"), this.c.mainUiLayer, this.c.zIndex + 1, 16);
                return;
            }
            return;
        }
        this.d.addAction(Actions.sequence(Actions.moveTo(-212.0f, 0.0f, 0.15f), Actions.hide()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.m.setVisible(false);
        if (Game.i.progressManager.getItemsByType(ItemType.CASE).size != 0) {
            this.m.setVisible(true);
            return;
        }
        DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.CASE_KEY);
        for (int i = 0; i < itemsByType.size; i++) {
            if (itemsByType.items[i].getCount() >= 10) {
                this.m.setVisible(true);
                return;
            }
        }
    }

    private void o() {
        this.o.setVisible(false);
        if (Game.i.progressManager.getItemsByType(ItemType.CASE).size != 0) {
            this.o.setVisible(true);
            return;
        }
        DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.CASE_KEY);
        for (int i = 0; i < itemsByType.size; i++) {
            if (itemsByType.get(i).getCount() >= 10) {
                this.o.setVisible(true);
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        hideWithToggleButton(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final boolean isPersistent() {
        return false;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void preRender(float f) {
        if (this.C) {
            update();
            this.C = false;
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (this.v) {
            if (this.k != null && this.l != this.k.getVisualScrollY()) {
                g();
                this.l = this.k.getVisualScrollY();
            }
            if (Gdx.input.isKeyJustPressed(29)) {
                d();
            }
        }
        if (this.v) {
            o();
        }
    }

    public final void show() {
        this.v = true;
        TooltipsOverlay.i().markTagShown("InventoryOverlay.button");
        TooltipsOverlay.i().hideEntry("InventoryOverlay.button");
        rebuildLayoutIfRequired();
        a(false);
        DarkOverlay.i().addCallerOverlayLayer("InventoryOverlay", this.c.zIndex - 1, () -> {
            hideWithToggleButton(true);
            return true;
        });
        UiUtils.bouncyShowOverlay(null, this.c.getTable(), this.e);
        update();
    }

    public final void hideWithToggleButton(boolean z) {
        DarkOverlay.i().removeCaller("InventoryOverlay");
        UiUtils.bouncyHideOverlayWithCallback(null, this.c.getTable(), this.e, () -> {
            l();
        });
        this.v = false;
        c();
        a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay$TabConfig.class */
    public static class TabConfig {
        public float buttonShiftX;
        public TabType tabType;
        public String iconAlias;
        public Color colorDark;
        public Color colorBright;
        public ItemSubcategoryType[] subcategories;

        /* renamed from: a, reason: collision with root package name */
        private String f3554a;

        /* synthetic */ TabConfig(TabType tabType, float f, String str, Color color, Color color2, ItemSubcategoryType[] itemSubcategoryTypeArr, byte b2) {
            this(tabType, f, str, color, color2, itemSubcategoryTypeArr);
        }

        private TabConfig(TabType tabType, float f, String str, Color color, Color color2, ItemSubcategoryType[] itemSubcategoryTypeArr) {
            this.buttonShiftX = f;
            this.iconAlias = str;
            this.tabType = tabType;
            this.colorDark = color;
            this.colorBright = color2;
            this.subcategories = itemSubcategoryTypeArr;
            this.f3554a = "inventory_tab_name_" + tabType.name();
        }

        public String getName() {
            return Game.i.localeManager.i18n.get(this.f3554a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay$SubcategoryItems.class */
    public class SubcategoryItems {
        public ItemSubcategoryType subcategoryType;
        public RarityType rarityType;
        public Array<ItemStack> items;

        private SubcategoryItems(InventoryOverlay inventoryOverlay) {
            this.items = new Array<>(ItemStack.class);
        }

        /* synthetic */ SubcategoryItems(InventoryOverlay inventoryOverlay, byte b2) {
            this(inventoryOverlay);
        }

        public String getIconAlias() {
            if (this.subcategoryType != null) {
                return Game.i.itemManager.getSubcategoryIconAlias(this.subcategoryType);
            }
            return Game.i.progressManager.getRarityIcon(this.rarityType);
        }

        public String getTitle() {
            if (this.subcategoryType != null) {
                return Game.i.itemManager.getSubcategoryName(this.subcategoryType);
            }
            return Game.i.progressManager.getRarityName(this.rarityType);
        }

        public Color getColor() {
            if (this.subcategoryType != null) {
                return Game.i.itemManager.getSubcategoryColor(this.subcategoryType);
            }
            return Game.i.progressManager.getRarityBrightColor(this.rarityType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay$SubcategoryButtonConfig.class */
    public class SubcategoryButtonConfig {
        public float distanceY;
        public PaddedImageButton button;

        private SubcategoryButtonConfig(InventoryOverlay inventoryOverlay) {
        }

        /* synthetic */ SubcategoryButtonConfig(InventoryOverlay inventoryOverlay, byte b2) {
            this(inventoryOverlay);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/InventoryOverlay$_ProgressManagerListener.class */
    private class _ProgressManagerListener extends ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter {
        private _ProgressManagerListener() {
        }

        /* synthetic */ _ProgressManagerListener(InventoryOverlay inventoryOverlay, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
        public void itemsChanged(Item item, int i, int i2) {
            if (InventoryOverlay.this.v) {
                for (ItemSubcategoryType itemSubcategoryType : InventoryOverlay.u[InventoryOverlay.this.w.ordinal()].subcategories) {
                    if (itemSubcategoryType == item.getSubcategory()) {
                        InventoryOverlay.a(InventoryOverlay.this, true);
                        return;
                    }
                }
            }
            InventoryOverlay.this.n();
        }
    }
}

package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ResearchScreenInventory.class */
public class ResearchScreenInventory extends Group implements Disposable {
    public static final float WIDTH = 292.0f;
    public static final float HEIGHT = 780.0f;
    private boolean k;
    private Array<Item> l = new Array<>(true, 1, Item.class);
    private Array<Label> m = new Array<>(true, 1, Label.class);
    private Array<PaddedImageButton> n = new Array<>(true, 1, PaddedImageButton.class);
    private ProgressManager.ProgressManagerListener o;

    static /* synthetic */ boolean a(ResearchScreenInventory researchScreenInventory, boolean z) {
        researchScreenInventory.k = true;
        return true;
    }

    public ResearchScreenInventory() {
        this.k = false;
        this.l.add(Item.D.GREEN_PAPER);
        this.l.add(Item.D.ACCELERATOR);
        this.l.add(Item.D.RESOURCE_SCALAR);
        this.l.add(Item.D.RESOURCE_VECTOR);
        this.l.add(Item.D.RESOURCE_MATRIX);
        this.l.add(Item.D.RESOURCE_TENSOR);
        this.l.add(Item.D.RESOURCE_INFIAR);
        this.l.add(Item.D.BLUEPRINT_AGILITY);
        this.l.add(Item.D.BLUEPRINT_EXPERIENCE);
        this.l.add(Item.D.BLUEPRINT_POWER);
        this.l.add(Item.D.BLUEPRINT_SPECIAL_I);
        this.l.add(Item.D.BLUEPRINT_SPECIAL_II);
        this.l.add(Item.D.BLUEPRINT_SPECIAL_III);
        this.l.add(Item.D.BLUEPRINT_SPECIAL_IV);
        this.l.add(Item.D.PRESTIGE_TOKEN);
        this.l.add(Item.D.BIT_DUST);
        setSize(292.0f, 780.0f);
        setTransform(false);
        Actor image = new Image(Game.i.assetManager.getQuad("ui.researchScreenInventory.background"));
        image.setSize(292.0f, 780.0f);
        addActor(image);
        Actor label = new Label(Game.i.localeManager.i18n.get("inventory"), Game.i.assetManager.getLabelStyle(21));
        label.setPosition(23.0f, 740.0f);
        label.setSize(100.0f, 14.0f);
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        addActor(label);
        for (int i = 0; i < this.l.size; i++) {
            Group group = new Group();
            group.setTransform(false);
            group.setPosition(0.0f, 688.0f - (i * 38.0f));
            addActor(group);
            if (i % 2 == 0) {
                Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image2.setColor(1.0f, 1.0f, 1.0f, 0.04f);
                image2.setSize(205.0f, 38.0f);
                group.addActor(image2);
            }
            Actor generateIcon = this.l.get(i).generateIcon(30.0f, false);
            generateIcon.setPosition(24.0f, 4.0f);
            group.addActor(generateIcon);
            Label label2 = new Label("0", Game.i.assetManager.getLabelStyle(21));
            group.addActor(label2);
            label2.setAlignment(16);
            label2.setWidth(182.0f);
            label2.setHeight(38.0f);
            this.m.add(label2);
            TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("small-circle");
            Color color = Color.WHITE;
            PaddedImageButton paddedImageButton = new PaddedImageButton(drawable, null, color, color, Color.WHITE);
            paddedImageButton.setSize(64.0f, 38.0f);
            paddedImageButton.setIconSize(10.0f, 10.0f);
            paddedImageButton.setIconPosition(8.0f, 14.0f);
            group.addActor(paddedImageButton);
            this.n.add(paddedImageButton);
        }
        this.k = true;
        this.o = new ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter() { // from class: com.prineside.tdi2.ui.components.ResearchScreenInventory.1
            @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
            public void itemsChanged(Item item, int i2, int i3) {
                for (int i4 = 0; i4 < ResearchScreenInventory.this.l.size; i4++) {
                    if (((Item) ResearchScreenInventory.this.l.get(i4)).sameAs(item)) {
                        ResearchScreenInventory.a(ResearchScreenInventory.this, true);
                        return;
                    }
                }
            }
        };
        Game.i.progressManager.addListener(this.o);
    }

    public void preRender(float f) {
        if (this.k) {
            update();
            this.k = false;
        }
    }

    public void update() {
        boolean z;
        Array<ItemStack> cumulativePrice;
        Array<ItemStack> price;
        ObjectIntMap objectIntMap = new ObjectIntMap();
        ObjectMap objectMap = new ObjectMap();
        Array<Research> instances = Game.i.researchManager.getInstances();
        for (int i = 0; i < instances.size; i++) {
            Research research = instances.get(i);
            if (!Game.i.researchManager.canStartResearching(research, false)) {
                boolean z2 = false;
                if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.EASY || Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL) {
                    if (!research.isMaxNormalLevel()) {
                        z2 = true;
                    }
                } else if (!research.isMaxEndlessLevel()) {
                    z2 = true;
                }
                if (z2) {
                    boolean z3 = true;
                    if (research.priceInStars > 0) {
                        boolean z4 = false;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= research.linksToParents.size) {
                                break;
                            }
                            if (research.linksToParents.get(i2).parent.getInstalledLevel() <= 0) {
                                i2++;
                            } else {
                                z4 = true;
                                break;
                            }
                        }
                        int i3 = 0;
                        while (true) {
                            if (i3 >= research.linksToChildren.size) {
                                break;
                            }
                            if (research.linksToChildren.get(i3).child.getInstalledLevel() <= 0) {
                                i3++;
                            } else {
                                z4 = true;
                                break;
                            }
                        }
                        if (!z4) {
                            z3 = false;
                        }
                        if (Game.i.researchManager.getUnusedStarsCount() < research.priceInStars) {
                            z3 = false;
                        }
                    } else {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= research.linksToParents.size) {
                                break;
                            }
                            Research.ResearchLink researchLink = research.linksToParents.get(i4);
                            if (researchLink.requiredLevels <= researchLink.parent.getInstalledLevel()) {
                                i4++;
                            } else {
                                z3 = false;
                                break;
                            }
                        }
                    }
                    if (z3) {
                        if (research.levels.length > research.getInstalledLevel()) {
                            price = research.levels[research.getInstalledLevel()].price;
                        } else {
                            price = research.endlessLevel.getPrice(research.getInstalledLevel() + 1);
                        }
                        for (int i5 = 0; i5 < price.size; i5++) {
                            ItemStack itemStack = price.items[i5];
                            if (itemStack.getItem().getType() == ItemType.GREEN_PAPER) {
                                if (itemStack.getCount() > Game.i.progressManager.getGreenPapers()) {
                                    objectIntMap.getAndIncrement(Item.D.GREEN_PAPER, 0, 1);
                                }
                            } else if (itemStack.getItem().getType() != ItemType.RESOURCE) {
                                if (itemStack.getCount() > Game.i.progressManager.getItemsCount(itemStack.getItem())) {
                                    objectIntMap.getAndIncrement(itemStack.getItem(), 0, 1);
                                }
                            } else {
                                ResourceItem resourceItem = (ResourceItem) itemStack.getItem();
                                if (itemStack.getCount() > Game.i.progressManager.getResources(resourceItem.resourceType)) {
                                    objectIntMap.getAndIncrement(resourceItem, 0, 1);
                                }
                            }
                        }
                    }
                }
            }
            if (research.priceInStars == 0) {
                if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                    z = false;
                    cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.maxEndlessLevel, false);
                } else {
                    z = false;
                    cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.levels.length, false);
                }
                for (int i6 = 0; i6 < cumulativePrice.size; i6++) {
                    ItemStack itemStack2 = cumulativePrice.get(i6);
                    objectMap.put(itemStack2.getItem(), Long.valueOf(((Long) objectMap.get(itemStack2.getItem(), 0L)).longValue() + itemStack2.getCount()));
                }
            }
        }
        for (int i7 = 0; i7 < this.l.size; i7++) {
            Item item = this.l.get(i7);
            this.m.get(i7).setText(StringFormatter.commaSeparatedNumber(Game.i.progressManager.getItemsCount(item)));
            PaddedImageButton paddedImageButton = this.n.get(i7);
            int i8 = objectIntMap.get(item, 0);
            paddedImageButton.setVisible(true);
            if (i8 == 0) {
                paddedImageButton.setColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
            } else if (i8 == 1) {
                paddedImageButton.setColors(MaterialColor.YELLOW.P500, MaterialColor.YELLOW.P200, MaterialColor.YELLOW.P700);
            } else if (i8 <= 3) {
                paddedImageButton.setColors(MaterialColor.AMBER.P500, MaterialColor.AMBER.P200, MaterialColor.AMBER.P700);
            } else if (i8 <= 10) {
                paddedImageButton.setColors(MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P200, MaterialColor.ORANGE.P700);
            } else {
                paddedImageButton.setColors(MaterialColor.RED.P500, MaterialColor.RED.P200, MaterialColor.RED.P700);
            }
            long longValue = ((Long) objectMap.get(item, 0L)).longValue();
            paddedImageButton.setClickHandler(() -> {
                String str = ((item.getTitle().toString() + "\n[#888888]") + Game.i.localeManager.i18n.format("item_required_for_full_research_tooltip", StringFormatter.compactNumber(longValue, false).toString())) + "[]";
                if (i8 != 0) {
                    str = ((str + "\n[#FFD54F]") + Game.i.localeManager.i18n.format("item_required_by_research_count_tooltip", Integer.valueOf(i8))) + "[]";
                }
                TooltipsOverlay.i().showText("resourceRequiredByResearch", paddedImageButton, str, UiManager.MainUiLayer.SCREEN, 110, 8);
            });
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.progressManager.removeListener(this.o);
    }
}

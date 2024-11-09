package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Building;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.SpaceTileBonus;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.ui.actors.AimStrategySelector;
import com.prineside.tdi2.ui.actors.EffectTooltip;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/PlatformTile.class */
public final class PlatformTile extends Tile implements KryoSerializable {
    public SpaceTileBonusType bonusType;
    public int bonusLevel;
    public Building building;

    /* synthetic */ PlatformTile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.bonusType, SpaceTileBonusType.class);
        output.writeVarInt(this.bonusLevel, true);
        kryo.writeClassAndObject(output, this.building);
    }

    @Override // com.prineside.tdi2.Tile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.bonusType = (SpaceTileBonusType) kryo.readObjectOrNull(input, SpaceTileBonusType.class);
        this.bonusLevel = input.readVarInt(true);
        this.building = (Building) kryo.readClassAndObject(input);
    }

    private PlatformTile() {
        super(TileType.PLATFORM);
        this.bonusType = null;
        this.bonusLevel = 0;
    }

    @Override // com.prineside.tdi2.Tile
    public final void getData(IntArray intArray) {
        if (this.bonusType != null) {
            intArray.add(ItemDataType.BONUS_TYPE.ordinal(), this.bonusType.ordinal());
        }
        intArray.add(ItemDataType.BONUS_LEVEL.ordinal(), this.bonusType == null ? 0 : this.bonusLevel);
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillInventoryWithInfo(Table table, float f) {
        if (this.bonusType != null && this.bonusLevel != 0) {
            Color brightColor = SpaceTileBonus.getBrightColor(this.bonusType);
            Image image = new Image(Game.i.assetManager.getDrawable(SpaceTileBonus.getIconName(this.bonusType)));
            image.setColor(brightColor);
            table.add((Table) image).size(64.0f).row();
            Label label = new Label(SpaceTileBonus.getDetailedName(this.bonusType, this.bonusLevel), Game.i.assetManager.getLabelStyle(21));
            label.setColor(brightColor);
            label.setWrap(true);
            label.setAlignment(1);
            table.add((Table) label).width(f).padTop(4.0f).row();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void fillMapEditorMenu(Table table, final MapEditorItemInfoMenu mapEditorItemInfoMenu) {
        super.fillMapEditorMenu(table, mapEditorItemInfoMenu);
        if (this.bonusLevel > 0 && this.bonusType != null) {
            EffectTooltip effectTooltip = new EffectTooltip(Game.i.assetManager.getDrawable(SpaceTileBonus.getIconName(this.bonusType)), SpaceTileBonus.getDetailedName(this.bonusType, this.bonusLevel));
            effectTooltip.setHint(Game.i.localeManager.i18n.get("tile_effect"));
            effectTooltip.setColor(SpaceTileBonus.getBrightColor(this.bonusType));
            table.add(effectTooltip).growX().row();
        }
        if (!mapEditorItemInfoMenu.isSelectionFromInventory() && Game.i.progressManager.isDeveloperModeEnabled()) {
            if (this.building == null || this.building.buildingType == BuildingType.TOWER) {
                Label label = new Label("Tower type", Game.i.assetManager.getLabelStyle(18));
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label).padBottom(4.0f).padTop(4.0f).growX().row();
                final SelectBox selectBox = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
                selectBox.setName("map_editor_menu_tower_type_select");
                Array array = new Array();
                array.add(Game.i.localeManager.i18n.get("no_tower"));
                for (int i = 0; i < TowerType.values.length; i++) {
                    array.add(TowerType.values[i].name());
                }
                selectBox.setItems(array);
                table.add((Table) selectBox).height(48.0f).padBottom(8.0f).growX().row();
                if (this.building != null && this.building.buildingType == BuildingType.TOWER) {
                    selectBox.setSelected(((Tower) this.building).type.name());
                } else {
                    selectBox.setSelected(Game.i.localeManager.i18n.get("no_tower"));
                }
                selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.PlatformTile.1
                    @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                    public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                        String str = (String) selectBox.getSelected();
                        if (str.equals(Game.i.localeManager.i18n.get("no_tower"))) {
                            if (PlatformTile.this.building != null) {
                                PlatformTile.this.building.setTile(null);
                                PlatformTile.this.building = null;
                            }
                        } else {
                            if (PlatformTile.this.building != null) {
                                PlatformTile.this.building.setTile(null);
                            }
                            PlatformTile.this.building = Game.i.towerManager.getFactory(TowerType.valueOf(str)).create();
                            PlatformTile.this.building.setTile(PlatformTile.this);
                        }
                        mapEditorItemInfoMenu.notifySelectedElementChanged();
                    }
                });
            }
            if (this.building == null || this.building.buildingType == BuildingType.MODIFIER) {
                Label label2 = new Label("Modifier type", Game.i.assetManager.getLabelStyle(18));
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label2).padBottom(4.0f).padTop(4.0f).growX().row();
                final SelectBox selectBox2 = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
                selectBox2.setName("map_editor_menu_modifier_type_select");
                Array array2 = new Array();
                array2.add("No modifier");
                for (int i2 = 0; i2 < ModifierType.values.length; i2++) {
                    array2.add(ModifierType.values[i2].name());
                }
                selectBox2.setItems(array2);
                table.add((Table) selectBox2).height(48.0f).padBottom(8.0f).growX().row();
                if (this.building != null && this.building.buildingType == BuildingType.MODIFIER) {
                    selectBox2.setSelected(((Modifier) this.building).type.name());
                } else {
                    selectBox2.setSelected("No modifier");
                }
                selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.tiles.PlatformTile.2
                    @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                    public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                        String str = (String) selectBox2.getSelected();
                        if (str.equals("No modifier")) {
                            if (PlatformTile.this.building != null) {
                                PlatformTile.this.building.setTile(null);
                                PlatformTile.this.building = null;
                            }
                        } else {
                            if (PlatformTile.this.building != null) {
                                PlatformTile.this.building.setTile(null);
                            }
                            PlatformTile.this.building = Game.i.modifierManager.getFactory(ModifierType.valueOf(str)).create();
                            PlatformTile.this.building.setTile(PlatformTile.this);
                        }
                        mapEditorItemInfoMenu.notifySelectedElementChanged();
                    }
                });
            }
            if (this.building != null) {
                if (this.building.buildingType == BuildingType.TOWER) {
                    final Tower tower = (Tower) this.building;
                    Label label3 = new Label("Aim mode", Game.i.assetManager.getLabelStyle(18));
                    label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label3).padBottom(4.0f).padTop(4.0f).growX().row();
                    AimStrategySelector aimStrategySelector = new AimStrategySelector();
                    table.add((Table) aimStrategySelector).padBottom(8.0f).row();
                    aimStrategySelector.setStrategy(tower.aimStrategy, false, false);
                    aimStrategySelector.addListener(aimStrategy -> {
                        tower.aimStrategy = aimStrategy;
                        mapEditorItemInfoMenu.notifySelectedElementChanged();
                    });
                    Label label4 = new Label("XP level", Game.i.assetManager.getLabelStyle(18));
                    label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label4).padBottom(4.0f).padTop(4.0f).growX().row();
                    final SelectBox selectBox3 = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
                    selectBox3.setName("map_editor_menu_tower_experience_select");
                    Array array3 = new Array();
                    for (int i3 = 1; i3 <= 999; i3++) {
                        array3.add(Tower.LEVEL_EXPERIENCE_MILESTONES[i3] + " - " + i3 + "LVL");
                    }
                    selectBox3.setItems(array3);
                    table.add((Table) selectBox3).growX().height(48.0f).padBottom(8.0f).row();
                    selectBox3.setSelected(StrictMath.round(tower.experience) + " - " + Tower.getLevelForExperienceFast(tower.experience) + "LVL");
                    selectBox3.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.tiles.PlatformTile.3
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            String[] split = ((String) selectBox3.getSelected()).split(SequenceUtils.SPACE);
                            tower.experience = Integer.parseInt(split[0]);
                            tower.calculateXpLevel(true);
                            mapEditorItemInfoMenu.notifySelectedElementChanged();
                        }
                    });
                    Label label5 = new Label("Upgrade level", Game.i.assetManager.getLabelStyle(18));
                    label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label5).padBottom(4.0f).padTop(4.0f).growX().row();
                    final SelectBox selectBox4 = new SelectBox(mapEditorItemInfoMenu.selectBoxStyle);
                    selectBox4.setName("map_editor_menu_tower_upgrade_level_select");
                    Array array4 = new Array();
                    for (int i4 = 0; i4 <= 10; i4++) {
                        array4.add(String.valueOf(i4));
                    }
                    selectBox4.setItems(array4);
                    table.add((Table) selectBox4).growX().height(48.0f).padBottom(8.0f).row();
                    selectBox4.setSelected(String.valueOf((int) tower.getUpgradeLevel()));
                    selectBox4.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.tiles.PlatformTile.4
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            tower.setUpgradeLevel((byte) Integer.parseInt((String) selectBox4.getSelected()));
                            mapEditorItemInfoMenu.notifySelectedElementChanged();
                        }
                    });
                    Label label6 = new Label("Abilities", Game.i.assetManager.getLabelStyle(18));
                    label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label6).padBottom(4.0f).padTop(4.0f).growX().row();
                    Table table2 = new Table();
                    table.add(table2).growX().height(56.0f).row();
                    for (int i5 = 0; i5 < 6; i5++) {
                        Group group = new Group();
                        group.setTransform(false);
                        table2.add((Table) group).size(56.0f);
                        Image image = new Image(Game.i.towerManager.getFactory(tower.type).getBaseTextures());
                        image.setSize(56.0f, 56.0f);
                        image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        group.addActor(image);
                        Image image2 = new Image(Game.i.towerManager.getFactory(tower.type).getAbilityTextures(i5));
                        image2.setSize(56.0f, 56.0f);
                        image2.setTouchable(Touchable.disabled);
                        group.addActor(image2);
                        if (!tower.installedAbilities[i5]) {
                            image2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        }
                        if (!tower.installedAbilities[i5]) {
                            image.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                        }
                        group.setTouchable(Touchable.enabled);
                        final int i6 = i5;
                        group.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.tiles.PlatformTile.5
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                tower.installedAbilities[i6] = !tower.installedAbilities[i6];
                                mapEditorItemInfoMenu.notifySelectedElementChanged();
                            }
                        });
                    }
                    return;
                }
                BuildingType buildingType = BuildingType.MODIFIER;
            }
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final int generateSeedSalt() {
        if (this.bonusType == null || this.bonusLevel == 0) {
            return 0;
        }
        return this.bonusLevel + (6 * this.bonusType.ordinal());
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        this.building = null;
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawSelectedRange(Batch batch, RangeCircle rangeCircle) {
        if (this.building != null) {
            this.building.drawSelectedRange(batch, rangeCircle);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawHoveredRange(Batch batch, RangeCircle rangeCircle) {
        if (this.building != null) {
            this.building.drawHoveredRange(batch, rangeCircle);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(new TextureRegionDrawable(Game.i.tileManager.F.PLATFORM.f3147a));
        image.setSize(128.0f * f2, 128.0f * f2);
        group.addActor(image);
        Image image2 = new Image(new TextureRegionDrawable(Game.i.tileManager.F.PLATFORM.c[this.bonusType == null ? 0 : this.bonusLevel]));
        image2.setSize(128.0f * f2, 128.0f * f2);
        group.addActor(image2);
        if (this.bonusType != null && this.bonusLevel != 0) {
            float f3 = (f / 32.0f) * 11.0f;
            float f4 = f - (f3 * 2.0f);
            Image image3 = new Image(Game.i.tileManager.F.PLATFORM.d[this.bonusType.ordinal()]);
            image3.setSize(f4, f4);
            image3.setPosition(f3, f3);
            image3.setColor(SpaceTileBonus.getColor(this.bonusType));
            group.addActor(image3);
            if (this.bonusLevel > 1) {
                Image image4 = new Image(Game.i.tileManager.F.PLATFORM.f3148b[this.bonusLevel - 2]);
                image4.setSize(f, f);
                image4.setColor(SpaceTileBonus.getColor(this.bonusType));
                group.addActor(image4);
            }
        }
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        switch (this.bonusLevel) {
            case 2:
                return RarityType.RARE;
            case 3:
                return RarityType.VERY_RARE;
            case 4:
                return RarityType.EPIC;
            case 5:
                return RarityType.LEGENDARY;
            default:
                return RarityType.COMMON;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final float getWalkCost(boolean z) {
        if (z) {
            if (this.building == null) {
                return 1.0f;
            }
            return 1.0f * this.building.getWalkCost();
        }
        if (this.building == null) {
            return 512.0f;
        }
        return 512.0f * this.building.getWalkCost();
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_PLATFORMS;
    }

    @Override // com.prineside.tdi2.Tile
    public final void from(Tile tile) {
        super.from(tile);
        PlatformTile platformTile = (PlatformTile) tile;
        this.bonusLevel = platformTile.bonusLevel;
        this.bonusType = platformTile.bonusType;
        this.building = platformTile.building == null ? null : platformTile.building.cloneBuilding();
        if (this.building != null) {
            this.building.setTile(this);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void updateCache() {
        if (this.building != null && this.building.isRegistered()) {
            this.building.updateCache();
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawStatic(Batch batch, float f, float f2, float f3, float f4, Map map, MapRenderingSystem.DrawMode drawMode) {
        super.drawStatic(batch, f, f2, f3, f4, map, drawMode);
        batch.draw(Game.i.tileManager.F.PLATFORM.f3147a, f, f2, f3, f4);
    }

    @Override // com.prineside.tdi2.Tile
    public final float getValue() {
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, (this.bonusLevel + 2) * 50));
        switch (this.bonusLevel) {
            case 1:
                array.add(new ItemStack(Item.D.RESOURCE_SCALAR, MathUtils.round((this.bonusType.ordinal() + 5) * 1.5f)));
                return;
            case 2:
                array.add(new ItemStack(Item.D.RESOURCE_SCALAR, MathUtils.round((this.bonusType.ordinal() + 7) * 1.6f)));
                array.add(new ItemStack(Item.D.RESOURCE_VECTOR, MathUtils.round((this.bonusType.ordinal() + 5) * 1.2f)));
                return;
            case 3:
                array.add(new ItemStack(Item.D.RESOURCE_SCALAR, MathUtils.round((this.bonusType.ordinal() + 3) * 1.4f)));
                array.add(new ItemStack(Item.D.RESOURCE_VECTOR, MathUtils.round((this.bonusType.ordinal() + 7) * 1.3f)));
                array.add(new ItemStack(Item.D.RESOURCE_MATRIX, MathUtils.round((this.bonusType.ordinal() + 6) * 0.95f)));
                return;
            case 4:
                array.add(new ItemStack(Item.D.RESOURCE_VECTOR, MathUtils.round((this.bonusType.ordinal() + 4) * 1.5f)));
                array.add(new ItemStack(Item.D.RESOURCE_MATRIX, MathUtils.round((this.bonusType.ordinal() + 8) * 1.1f)));
                array.add(new ItemStack(Item.D.RESOURCE_TENSOR, MathUtils.round((this.bonusType.ordinal() + 7) * 0.8f)));
                return;
            case 5:
                array.add(new ItemStack(Item.D.RESOURCE_MATRIX, MathUtils.round((this.bonusType.ordinal() + 4) * 1.2f)));
                array.add(new ItemStack(Item.D.RESOURCE_TENSOR, MathUtils.round((this.bonusType.ordinal() + 7) * 1.1f)));
                array.add(new ItemStack(Item.D.RESOURCE_INFIAR, MathUtils.round((this.bonusType.ordinal() + 8) * 0.55f)));
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        if (this.bonusType == null) {
            return 0.002d;
        }
        double ordinal = 1.0d + (this.bonusType.ordinal() * 0.03d);
        switch (this.bonusLevel) {
            case 1:
                return ordinal * 0.002d;
            case 2:
                return ordinal * 0.008d;
            case 3:
                return ordinal * 0.025d;
            case 4:
                return ordinal * 0.077d;
            case 5:
                return ordinal * 0.24d;
            default:
                return 0.002d;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return this.bonusLevel < 5 && this.bonusType != null;
    }

    @Override // com.prineside.tdi2.Tile
    public final Tile createUpgradedTile() {
        if (!canBeUpgraded()) {
            throw new IllegalStateException("PlatformTile can't be upgraded more");
        }
        PlatformTile create = Game.i.tileManager.F.PLATFORM.create();
        create.bonusType = this.bonusType;
        create.bonusLevel = this.bonusLevel + 1;
        create.building = this.building != null ? this.building.cloneBuilding() : null;
        return create;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getUpgradePriceInGreenPapers() {
        int i = 250;
        for (int i2 = 1; i2 <= this.bonusLevel; i2++) {
            i += i2 * 150;
        }
        return i;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getUpgradePriceInAccelerators() {
        return this.bonusLevel;
    }

    @Override // com.prineside.tdi2.Tile
    public final int getUpgradePriceInResources(ResourceType resourceType) {
        if (!canBeUpgraded()) {
            return 0;
        }
        switch (this.bonusLevel + 1) {
            case 2:
                if (resourceType == ResourceType.SCALAR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 8) * 6.5f);
                }
                if (resourceType == ResourceType.VECTOR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 12) * 5.5f);
                }
                return 0;
            case 3:
                if (resourceType == ResourceType.VECTOR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 12) * 7.0f);
                }
                if (resourceType == ResourceType.MATRIX) {
                    return MathUtils.floor((this.bonusType.ordinal() + 17) * 6.0f);
                }
                return 0;
            case 4:
                if (resourceType == ResourceType.MATRIX) {
                    return MathUtils.floor((this.bonusType.ordinal() + 17) * 7.5f);
                }
                if (resourceType == ResourceType.TENSOR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 28) * 7.0f);
                }
                return 0;
            case 5:
                if (resourceType == ResourceType.TENSOR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 15) * 8.5f);
                }
                if (resourceType == ResourceType.INFIAR) {
                    return MathUtils.floor((this.bonusType.ordinal() + 28) * 8.0f);
                }
                return 0;
            default:
                return 0;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        switch (itemSortingType) {
            case KIND:
                if (this.bonusType == null) {
                    return 0;
                }
                return (this.bonusType.ordinal() * 1000) + this.bonusLevel;
            case RARITY:
                int ordinal = getRarity().ordinal() * 1000;
                if (this.bonusType != null) {
                    ordinal += this.bonusType.ordinal() + (this.bonusLevel * SpaceTileBonusType.values.length);
                }
                return ordinal;
            default:
                return 1;
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return false;
    }

    @Override // com.prineside.tdi2.Tile
    public final void toJson(Json json) {
        super.toJson(json);
        if (this.bonusType != null && this.bonusLevel > 0) {
            json.writeObjectStart("d");
            json.writeValue("bt", this.bonusType.name());
            json.writeValue("bl", Integer.valueOf(this.bonusLevel));
            json.writeObjectEnd();
        }
        if (this.building != null) {
            json.writeObjectStart("building");
            this.building.toJson(json);
            json.writeObjectEnd();
        }
    }

    public final void drawBonusExtras(Batch batch, float f, float f2, float f3, float f4, boolean z, boolean z2) {
        if (z) {
            batch.draw(Game.i.tileManager.F.PLATFORM.c[this.bonusType == null ? 0 : this.bonusLevel], f, f2, f3, f4);
        }
        if (z2 && this.bonusType != null && this.bonusLevel > 0) {
            boolean z3 = false;
            if (isRegistered()) {
                if (this.S == null) {
                    throw new IllegalStateException("S is null");
                }
                if (this.S._mapRendering == null) {
                    throw new IllegalStateException("S._mapRendering is null " + this.S);
                }
                ObjectSet<SpaceTileBonusType> objectSet = this.S._mapRendering.spaceTileBonusesToDrawColoredOnFreeTiles;
                if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED || this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.MAP_EDITOR || this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.FULL || objectSet.contains(this.bonusType) || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_ALWAYS_DRAW_TILE_EXTRAS) == 1.0d) {
                    z3 = true;
                }
            } else {
                z3 = true;
            }
            float f5 = (f3 / 32.0f) * 11.0f;
            float f6 = f3 - (f5 * 2.0f);
            if (z3) {
                batch.setColor(SpaceTileBonus.getColor(this.bonusType));
            } else {
                batch.setColor(1.0f, 1.0f, 1.0f, 0.18f);
            }
            if (this.bonusLevel >= 2) {
                batch.draw(Game.i.tileManager.F.PLATFORM.f3148b[this.bonusLevel - 2], f, f2, f3, f4);
            }
            batch.draw(Game.i.tileManager.F.PLATFORM.d[this.bonusType.ordinal()], f + f5, f2 + f5, f6, f6);
            batch.setColor(Color.WHITE);
        }
    }

    @Override // com.prineside.tdi2.Tile
    public final void drawExtras(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        drawBonusExtras(batch, f, f2, f3, f4, true, drawMode == MapRenderingSystem.DrawMode.FULL || this.building == null);
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAs(Tile tile) {
        if (!super.sameAs(tile)) {
            return false;
        }
        PlatformTile platformTile = (PlatformTile) tile;
        if ((platformTile.bonusType == null || platformTile.bonusLevel == 0) && (this.bonusType == null || this.bonusLevel == 0)) {
            return true;
        }
        return platformTile.bonusType == this.bonusType && platformTile.bonusLevel == this.bonusLevel;
    }

    @Override // com.prineside.tdi2.Tile
    public final PlatformTile removeExtrasForInventory() {
        this.building = null;
        return this;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean sameAsWithExtras(Tile tile) {
        if (!sameAs(tile)) {
            return false;
        }
        PlatformTile platformTile = (PlatformTile) tile;
        if (this.building == null) {
            return platformTile.building == null;
        }
        return this.building.sameAs(platformTile.building);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/PlatformTile$SpaceTileFactory.class */
    public static class SpaceTileFactory extends Tile.Factory.AbstractFactory<PlatformTile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3147a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion[] f3148b;
        TextureRegion[] c;
        final TextureRegion[] d;

        public SpaceTileFactory() {
            super(TileType.PLATFORM);
            this.f3148b = new TextureRegion[4];
            this.c = new TextureRegion[6];
            this.d = new TextureRegion[SpaceTileBonusType.values.length];
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            return 500;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory
        public void setupAssets() {
            this.f3147a = Game.i.assetManager.getTextureRegion("tile-type-platform");
            for (int i = 2; i <= 5; i++) {
                this.f3148b[i - 2] = Game.i.assetManager.getTextureRegion("tile-type-platform-extra-" + i);
            }
            for (int i2 = 0; i2 <= 5; i2++) {
                this.c[i2] = Game.i.assetManager.getTextureRegion("tile-type-platform-shade-" + i2);
            }
            for (SpaceTileBonusType spaceTileBonusType : SpaceTileBonusType.values) {
                this.d[spaceTileBonusType.ordinal()] = Game.i.assetManager.getTextureRegion(SpaceTileBonus.getIconName(spaceTileBonusType));
            }
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public PlatformTile create() {
            return new PlatformTile((byte) 0);
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public PlatformTile createRandom(float f, RandomXS128 randomXS128) {
            PlatformTile create = create();
            if (f < 0.075d) {
                create.bonusLevel = 0;
            } else {
                create.bonusType = SpaceTileBonusType.values[randomXS128.nextInt(SpaceTileBonusType.values.length)];
                if (f < 0.2d) {
                    create.bonusLevel = 1;
                } else if (f < 0.4d) {
                    create.bonusLevel = 2;
                } else if (f < 0.6d) {
                    create.bonusLevel = 3;
                } else if (f < 0.8d) {
                    create.bonusLevel = 4;
                } else {
                    create.bonusLevel = 5;
                }
            }
            return create;
        }

        @Override // com.prineside.tdi2.Tile.Factory.AbstractFactory, com.prineside.tdi2.Tile.Factory
        public PlatformTile fromJson(JsonValue jsonValue) {
            PlatformTile platformTile = (PlatformTile) super.fromJson(jsonValue);
            if (jsonValue.has("d")) {
                JsonValue jsonValue2 = jsonValue.get("d");
                platformTile.bonusType = SpaceTileBonusType.valueOf(jsonValue2.getString("bt"));
                platformTile.bonusLevel = jsonValue2.getInt("bl");
                if (platformTile.bonusLevel < 0) {
                    platformTile.bonusLevel = 0;
                }
                if (platformTile.bonusLevel > 5) {
                    platformTile.bonusLevel = 5;
                }
            }
            if (jsonValue.has("building")) {
                Building fromJson = Building.fromJson(jsonValue.get("building"));
                platformTile.building = fromJson;
                fromJson.setTile(platformTile);
            }
            return platformTile;
        }
    }
}

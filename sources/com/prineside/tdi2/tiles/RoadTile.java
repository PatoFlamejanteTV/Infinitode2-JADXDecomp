package com.prineside.tdi2.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.utils.REGS;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/RoadTile.class */
public final class RoadTile extends Tile {
    /* synthetic */ RoadTile(byte b2) {
        this();
    }

    private RoadTile() {
        super(TileType.ROAD);
    }

    @Override // com.prineside.tdi2.Tile
    public final int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            return getRarity().ordinal() * 1000;
        }
        return CGL.kCGLBadAttribute;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean isRoadType() {
        return true;
    }

    @Override // com.prineside.tdi2.Tile
    public final RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Tile
    public final float getWalkCost(boolean z) {
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Tile
    public final ItemSubcategoryType getInventorySubcategory() {
        return ItemSubcategoryType.ME_ROADS;
    }

    @Override // com.prineside.tdi2.Tile
    public final Group generateUiIcon(float f) {
        TextureRegion roadTexture = Game.i.tileManager.getRoadTexture(null, null, null, null);
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(new TextureRegionDrawable(roadTexture));
        image.setSize(f, f);
        group.addActor(image);
        return group;
    }

    @Override // com.prineside.tdi2.Tile
    public final void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 150));
    }

    @Override // com.prineside.tdi2.Tile
    public final double getPrestigeScore() {
        return 0.05d;
    }

    @Override // com.prineside.tdi2.Tile
    public final boolean canBeUpgraded() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/tiles/RoadTile$RoadTileFactory.class */
    public static class RoadTileFactory extends Tile.Factory.AbstractFactory<RoadTile> {
        public RoadTileFactory() {
            super(TileType.ROAD);
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public int getProbabilityForPrize(float f, ProgressManager.InventoryStatistics inventoryStatistics) {
            if (f >= 0.2f) {
                return 0;
            }
            int i = inventoryStatistics.byTileTypeCount[TileType.ROAD.ordinal()];
            if (i < 5) {
                return User32.VK_PLAY;
            }
            if (i < 15) {
                return 150;
            }
            if (i < 50) {
                return 100;
            }
            if (i < 150) {
                return 50;
            }
            return 10;
        }

        @Override // com.prineside.tdi2.Tile.Factory
        public RoadTile create() {
            return new RoadTile((byte) 0);
        }
    }
}

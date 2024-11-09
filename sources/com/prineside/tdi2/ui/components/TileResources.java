package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TileResources.class */
public class TileResources extends Group {
    private SourceTile k;
    private float l;
    private boolean m;

    public TileResources(float f) {
        this.l = f;
        this.m = f < 350.0f;
        setTransform(false);
        setSize(f, 80.0f);
        setTouchable(Touchable.disabled);
    }

    public SourceTile getTile() {
        return this.k;
    }

    public void setTile(SourceTile sourceTile) {
        String str;
        Label.LabelStyle labelStyle = this.m ? Game.i.assetManager.getLabelStyle(21) : Game.i.assetManager.getLabelStyle(24);
        float f = this.m ? 24.0f : 32.0f;
        this.k = sourceTile;
        clearChildren();
        Table table = new Table();
        table.setPosition(0.0f, 48.0f);
        table.setSize(this.l, f);
        addActor(table);
        int i = 0;
        for (ResourceType resourceType : ResourceType.values) {
            int resourcesCount = sourceTile.getResourcesCount(resourceType);
            if (resourcesCount > 0) {
                if (i > 0) {
                    table.add().height(f).width(this.m ? 8.0f : 16.0f);
                }
                i += resourcesCount;
                Image image = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[resourceType.ordinal()]));
                image.setColor(Game.i.resourceManager.getColor(resourceType));
                table.add((Table) image).size(f).padRight(4.0f);
                Label label = new Label(StringFormatter.compactNumber(resourcesCount, false), labelStyle);
                label.setColor(Game.i.resourceManager.getColor(resourceType));
                table.add((Table) label).height(f);
            }
        }
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME);
        if (i == 0) {
            Actor image2 = new Image(drawable);
            image2.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            image2.setSize(this.l, 8.0f);
            image2.setPosition(0.0f, 29.0f);
            addActor(image2);
            Label label2 = new Label(Game.i.localeManager.i18n.get("no_resources"), labelStyle);
            label2.setAlignment(1);
            label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            table.add((Table) label2).size(this.l, 32.0f);
            return;
        }
        float f2 = 0.0f;
        float resourceDensity = sourceTile.getResourceDensity() > 1.0f ? 1.0f / sourceTile.getResourceDensity() : 1.0f;
        for (ResourceType resourceType2 : ResourceType.values) {
            float resourcesCount2 = ((sourceTile.getResourcesCount(resourceType2) * sourceTile.getResourceDensity()) / i) * resourceDensity;
            if (resourcesCount2 > 0.0f) {
                Actor image3 = new Image(drawable);
                image3.setColor(Game.i.resourceManager.getColor(resourceType2));
                image3.setSize(this.l * resourcesCount2, 8.0f);
                image3.setPosition(this.l * f2, 29.0f);
                addActor(image3);
                f2 += resourcesCount2;
            }
        }
        if (sourceTile.getResourceDensity() > 1.0f) {
            for (int i2 = 0; i2 < 10; i2++) {
                float f3 = this.m ? 8.0f : 14.0f;
                float f4 = this.m ? 2.0f : 4.0f;
                float f5 = (this.m ? 0 : 40) + (i2 * (f4 + f3));
                float f6 = this.m ? (29.0f - f3) - f4 : 5.0f;
                if (sourceTile.getResourceDensity() <= i2) {
                    break;
                }
                if (sourceTile.getResourceDensity() - i2 > 1.0f) {
                    Actor image4 = new Image(drawable);
                    image4.setSize(f3, f3);
                    image4.setPosition(f5, f6);
                    addActor(image4);
                } else {
                    Actor image5 = new Image(drawable);
                    image5.setSize(f3, f3);
                    image5.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                    image5.setPosition(f5, f6);
                    addActor(image5);
                    Actor image6 = new Image(drawable);
                    image6.setSize(f3 * (sourceTile.getResourceDensity() - i2), f3);
                    image6.setPosition(f5, f6);
                    addActor(image6);
                }
            }
        }
        if (f2 < 1.0f) {
            Actor image7 = new Image(drawable);
            image7.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            image7.setSize(this.l * (1.0f - f2), 8.0f);
            image7.setPosition(this.l * f2, 29.0f);
            addActor(image7);
            Actor image8 = new Image(drawable);
            image8.setColor(new Color(-1970631937));
            image8.setSize(2.0f, 35.0f);
            image8.setPosition(this.l * f2, 6.0f);
            addActor(image8);
        }
        if (this.l < 360.0f) {
            str = ((int) (sourceTile.getResourceDensity() * 100.0f)) + "%";
        } else {
            str = Game.i.localeManager.i18n.get("resource_density") + ": " + ((int) (sourceTile.getResourceDensity() * 100.0f)) + "%";
        }
        Label label3 = new Label(str, new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label3.setSize(0.0f, 24.0f);
        addActor(label3);
        if (sourceTile.getResourceDensity() > 0.5f) {
            label3.setAlignment(16);
            float resourceDensity2 = (this.l * sourceTile.getResourceDensity()) - 10.0f;
            float f7 = resourceDensity2;
            if (resourceDensity2 > this.l - 40.0f) {
                f7 = this.l - 40.0f;
            }
            label3.setPosition(f7, 0.0f);
            return;
        }
        label3.setAlignment(8);
        float resourceDensity3 = (this.l * sourceTile.getResourceDensity()) + 10.0f;
        float f8 = resourceDensity3;
        if (resourceDensity3 < 40.0f) {
            f8 = 40.0f;
        }
        label3.setPosition(f8, 0.0f);
    }
}

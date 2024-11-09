package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/UserMap.class */
public class UserMap {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1786a = TLog.forClass(UserMap.class);
    public String id;
    public String name;
    public long creationTimestamp;
    public Map map;
    public boolean submittedOnline;

    public UserMap cpy() {
        UserMap userMap = new UserMap();
        userMap.id = this.id;
        userMap.name = this.name;
        userMap.creationTimestamp = this.creationTimestamp;
        userMap.map = this.map.cpy();
        userMap.submittedOnline = this.submittedOnline;
        return userMap;
    }

    public UserMap(String str) {
        this.id = "M-" + FastRandom.generateUniqueDistinguishableId();
        this.name = str;
        this.creationTimestamp = Game.getTimestampMillis();
        this.map = new Map(Game.i.userMapManager.getMaxMapSize(), Game.i.userMapManager.getMaxMapSize());
        int width = (this.map.getWidth() / 2) - 1;
        int height = (this.map.getHeight() / 2) - 1;
        Tile tile = null;
        Tile tile2 = null;
        DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TILE);
        for (int i = 0; i < itemsByType.size; i++) {
            Tile tile3 = ((TileItem) itemsByType.get(i).getItem()).tile;
            if (tile3.type == TileType.SPAWN) {
                tile = tile3;
            } else if (tile3.type == TileType.TARGET) {
                tile2 = tile3;
            }
        }
        this.map.setTile(width, height, tile);
        this.map.setTile(width + 1, height, tile2);
    }

    private UserMap() {
    }

    public boolean removeUnexistentTilesFromMap() {
        boolean z = false;
        Array array = new Array();
        DelayedRemovalArray<ItemStack> itemsByType = Game.i.progressManager.getItemsByType(ItemType.TILE);
        for (int i = 0; i < itemsByType.size; i++) {
            array.add(itemsByType.get(i).cpy());
        }
        for (int i2 = 0; i2 < this.map.getHeight(); i2++) {
            for (int i3 = 0; i3 < this.map.getWidth(); i3++) {
                Tile tile = this.map.getTile(i3, i2);
                if (tile != null) {
                    boolean z2 = false;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= array.size) {
                            break;
                        }
                        ItemStack itemStack = (ItemStack) array.get(i4);
                        if (!((TileItem) itemStack.getItem()).tile.sameAs(tile)) {
                            i4++;
                        } else if (itemStack.getCount() > 0) {
                            itemStack.setCount(itemStack.getCount() - 1);
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        z = true;
                        this.map.setTile(i3, i2, null);
                        f1786a.i("removed tile at " + i3 + ":" + i2 + " (" + tile.toString() + ")", new Object[0]);
                    }
                }
            }
        }
        return z;
    }

    public void toJson(Json json) {
        json.writeValue(Attribute.ID_ATTR, this.id);
        json.writeValue(Attribute.NAME_ATTR, this.name);
        json.writeValue("creationTimestamp", Long.valueOf(this.creationTimestamp));
        json.writeValue("submittedOnline", Boolean.valueOf(this.submittedOnline));
        json.writeObjectStart("map");
        this.map.toJson(json);
        json.writeObjectEnd();
    }

    public static UserMap fromJson(JsonValue jsonValue) {
        UserMap userMap = new UserMap();
        userMap.id = jsonValue.getString(Attribute.ID_ATTR);
        userMap.name = jsonValue.getString(Attribute.NAME_ATTR);
        userMap.creationTimestamp = jsonValue.getLong("creationTimestamp");
        userMap.submittedOnline = jsonValue.getBoolean("submittedOnline");
        userMap.map = Map.fromJson(jsonValue.get("map"));
        return userMap;
    }
}

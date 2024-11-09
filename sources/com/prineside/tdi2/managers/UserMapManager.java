package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.MapPrestigeConfig;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UserMapManager.class */
public class UserMapManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2498a = TLog.forClass(UserMapManager.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/UserMapManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<UserMapManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public UserMapManager read() {
            return Game.i.userMapManager;
        }
    }

    public void prestigeSellMap(MapPrestigeConfig mapPrestigeConfig) {
        UserMap userMap = Game.i.userMapManager.getUserMap(mapPrestigeConfig.userMapId);
        if (userMap == null) {
            Dialog.i().showAlert("Map not found");
            return;
        }
        if (mapPrestigeConfig.getFinalPrestigeTokens() <= 0) {
            Dialog.i().showAlert("You will get no tokens");
            return;
        }
        int i = 0;
        int i2 = 0;
        DelayedRemovalArray<Tile> allTiles = userMap.map.getAllTiles();
        for (int i3 = 0; i3 < allTiles.size; i3++) {
            Tile tile = allTiles.items[i3];
            if (tile.getPrestigeScore() > 0.0d) {
                i++;
                if (Game.i.progressManager.removeItems(Item.D.F_TILE.create(tile), 1)) {
                    i2++;
                }
            }
        }
        DelayedRemovalArray<Gate> allGates = userMap.map.getAllGates();
        for (int i4 = 0; i4 < allGates.size; i4++) {
            Gate gate = allGates.items[i4];
            if (gate.getPrestigeScore() > 0.0d) {
                i++;
                if (Game.i.progressManager.removeItems(Item.D.F_GATE.create(gate), 1)) {
                    i2++;
                }
            }
        }
        f2498a.i("map prestige: removed " + i2 + "/" + i + " map pieces", new Object[0]);
        int finalPrestigeTokens = mapPrestigeConfig.getFinalPrestigeTokens();
        Game.i.progressManager.addItems(Item.D.PRESTIGE_TOKEN, finalPrestigeTokens, "map_prestige");
        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.MAP_PRESTIGE, Game.getTimestampSeconds());
        issuedItems.mapPrestigeConfig = mapPrestigeConfig;
        issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, finalPrestigeTokens));
        Game.i.progressManager.addIssuedPrizes(issuedItems, true);
        removeUserMap(mapPrestigeConfig.userMapId);
        if (finalPrestigeTokens >= 50) {
            Game.i.statisticsManager.registerDelta(StatisticsType.PMS, 1.0d);
        }
        Game.i.statisticsManager.registerDelta(StatisticsType.PPG, finalPrestigeTokens);
        Game.i.achievementManager.setProgress(AchievementType.PRESTIGE, 1);
    }

    public BossType[] getDefaultBosses() {
        Array array = new Array(BossType.class);
        if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(EnemyType.BROOT_BOSS, false)) {
            array.add(BossType.BROOT);
        }
        if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(EnemyType.SNAKE_BOSS_HEAD, false)) {
            array.add(BossType.SNAKE);
        }
        if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(EnemyType.CONSTRUCTOR_BOSS, false)) {
            array.add(BossType.CONSTRUCTOR);
        }
        if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(EnemyType.MOBCHAIN_BOSS_HEAD, false)) {
            array.add(BossType.MOBCHAIN);
        }
        if (!Game.i.enemyManager.isEnemyTypeNewForPlayer(EnemyType.METAPHOR_BOSS, false)) {
            array.add(BossType.METAPHOR);
        }
        if (array.size != 0) {
            return (BossType[]) array.toArray();
        }
        return null;
    }

    public boolean isMapEditorAvailable() {
        return Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.MAP_EDITOR);
    }

    public int getMaxMapSize() {
        return Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.USER_MAP_MAX_SIZE);
    }

    public void rename(UserMap userMap, String str) {
        if (userMap.name.equals(str)) {
            return;
        }
        userMap.name = str;
        ProgressPrefs.i().requireSave();
    }

    public Array<UserMap> getUserMaps() {
        return ProgressPrefs.i().userMaps.getUserMapsOrdered();
    }

    public UserMap getUserMap(String str) {
        Array<UserMap> userMapsOrdered = ProgressPrefs.i().userMaps.getUserMapsOrdered();
        for (int i = 0; i < userMapsOrdered.size; i++) {
            if (userMapsOrdered.items[i].id.equals(str)) {
                return userMapsOrdered.get(i);
            }
        }
        return null;
    }

    public UserMap addUserMap(String str) {
        UserMap userMap = new UserMap(str);
        ProgressPrefs.i().userMaps.addUserMap(userMap);
        ProgressPrefs.i().requireSave();
        return userMap;
    }

    public void removeUserMap(String str) {
        ProgressPrefs.i().userMaps.removeUserMap(str);
    }
}

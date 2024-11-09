package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BossTileType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResearchCategoryType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.enums.TriggeredActionType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ResearchManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.tiles.ScriptTile;
import com.prineside.tdi2.ui.components.GameOverOverlay;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/I18nGenerator.class */
public class I18nGenerator {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3842a = TLog.forClass(I18nGenerator.class);

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f3843b = {".java", ".lua"};
    private static final String[] c = {"../src", "scripts", "../../core/src", "../../desktop/src", "../../ios/src"};

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v664, types: [boolean] */
    public void run() {
        String script;
        Array<File> array = new Array<>();
        for (String str : c) {
            a(new File(str), f3843b, array);
        }
        Array array2 = new Array();
        ObjectMap objectMap = new ObjectMap();
        Pattern compile = Pattern.compile("i18n[()]*([.:])(get|format)\\(([^)]+)\\)((?!//).)*(//)*(.*)");
        Pattern compile2 = Pattern.compile("i18n[()]*([.:])(get|format)");
        int i = 0;
        while (true) {
            FileNotFoundException fileNotFoundException = i;
            if (fileNotFoundException >= array.size) {
                break;
            }
            try {
                File file = array.get(i);
                Scanner scanner = new Scanner(file, "UTF-8");
                int i2 = 1;
                while (true) {
                    fileNotFoundException = scanner.hasNextLine();
                    if (fileNotFoundException == 0) {
                        break;
                    }
                    String nextLine = scanner.nextLine();
                    Matcher matcher = compile.matcher(nextLine);
                    while (true) {
                        if (matcher.find()) {
                            Matcher matcher2 = compile2.matcher(nextLine);
                            boolean z = false;
                            while (matcher2.find()) {
                                if (z) {
                                    f3842a.w(file.getPath() + ":" + i2 + " - multiple i18n calls", new Object[0]);
                                    break;
                                }
                                z = true;
                            }
                            String trim = matcher.group(2).trim();
                            String trim2 = matcher.group(3).trim();
                            String trim3 = matcher.group(6).trim();
                            if (trim2.contains("(")) {
                                String[] split = trim2.split(",");
                                if (split[0].startsWith("\"") && split[0].endsWith("\"") && a(split[0], "\"") == 2) {
                                    array2.add(split[0].substring(1, split[0].length() - 1));
                                } else {
                                    f3842a.w(file.getPath() + ":" + i2 + " - do not use '(' inside i18n.get() or i18n.format() to properly generate i18n lists", new Object[0]);
                                }
                            } else if (trim2.startsWith("\"") && trim2.endsWith("\"") && a(trim2, "\"") == 2) {
                                array2.add(trim2.substring(1, trim2.length() - 1));
                            } else if (trim3.length() == 0) {
                                if (trim.equals("format")) {
                                    String[] split2 = trim2.split(",");
                                    if (split2[0].startsWith("\"") && split2[0].endsWith("\"") && a(split2[0], "\"") == 2) {
                                        array2.add(split2[0].substring(1, split2[0].length() - 1));
                                    } else {
                                        f3842a.i(file.getPath() + ":" + i2 + " - skipping format TODO", new Object[0]);
                                    }
                                } else {
                                    f3842a.w(file.getPath() + ":" + i2 + " - no comment found for runtime generated alias", new Object[0]);
                                }
                            } else if (!trim3.equals("_ignored")) {
                                if (objectMap.containsKey(trim3)) {
                                    f3842a.i(file.getPath() + ":" + i2 + " - warning: comment already used in " + ((String) objectMap.get(trim3)), new Object[0]);
                                } else {
                                    objectMap.put(trim3, file.getPath() + ":" + i2);
                                }
                            }
                        }
                    }
                    i2++;
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                fileNotFoundException.printStackTrace();
            }
            i++;
        }
        Array array3 = new Array();
        if (objectMap.remove("ability_name_ + AbilityType") != null) {
            for (AbilityType abilityType : AbilityType.values) {
                array3.add("ability_name_" + abilityType.name());
            }
        }
        if (objectMap.remove("ability_name_fancy_ + AbilityType") != null) {
            for (AbilityType abilityType2 : AbilityType.values) {
                array3.add("ability_name_fancy_" + abilityType2.name());
            }
        }
        if (objectMap.remove("ability_description_ + AbilityType") != null) {
            for (AbilityType abilityType3 : AbilityType.values) {
                array3.add("ability_description_" + abilityType3.name());
            }
        }
        if (objectMap.remove("achievement_name_ + AchievementType") != null) {
            for (AchievementType achievementType : AchievementType.values) {
                array3.add("achievement_name_" + achievementType.name());
            }
        }
        if (objectMap.remove("achievement_description_ + AchievementType") != null) {
            for (AchievementType achievementType2 : AchievementType.values) {
                array3.add("achievement_description_" + achievementType2.name());
            }
        }
        if (objectMap.remove("aim_strategy_ + Tower.AimStrategy") != null) {
            for (Tower.AimStrategy aimStrategy : Tower.AimStrategy.values) {
                array3.add("aim_strategy_" + aimStrategy.name());
            }
        }
        if (objectMap.remove("boss_tile_name_ + BossTileType") != null) {
            for (BossTileType bossTileType : BossTileType.values) {
                array3.add("boss_tile_name_" + bossTileType.name());
            }
        }
        if (objectMap.remove("case_key_title_ + CaseType") != null) {
            for (CaseType caseType : CaseType.values) {
                array3.add("case_key_title_" + caseType.name());
            }
        }
        if (objectMap.remove("continue_game_status_ + GameStateSystem.ContinueGameStatus") != null) {
            for (GameStateSystem.ContinueGameStatus continueGameStatus : GameStateSystem.ContinueGameStatus.values) {
                array3.add("continue_game_status_" + continueGameStatus.name());
            }
        }
        if (objectMap.remove("difficulty_mode_ + DifficultyMode") != null) {
            for (DifficultyMode difficultyMode : DifficultyMode.values) {
                array3.add("difficulty_mode_" + difficultyMode.name());
            }
        }
        if (objectMap.remove("enemy_name_ + EnemyType") != null) {
            for (EnemyType enemyType : EnemyType.values) {
                array3.add("enemy_name_" + enemyType.name());
            }
        }
        if (objectMap.remove("enemy_description_ + EnemyType") != null) {
            for (EnemyType enemyType2 : EnemyType.values) {
                array3.add("enemy_description_" + enemyType2.name());
            }
        }
        if (objectMap.remove("gv_title_ + GameValueType") != null) {
            for (GameValueType gameValueType : GameValueType.values) {
                array3.add("gv_title_" + gameValueType.name());
            }
        }
        if (objectMap.remove("gv_title_disabled_ + GameValueType") != null) {
            for (GameValueType gameValueType2 : GameValueType.values) {
                if (Game.i.gameValueManager.getStockValueConfig(gameValueType2).units == GameValueManager.ValueUnits.BOOLEAN) {
                    array3.add("gv_title_disabled_" + gameValueType2.name());
                }
            }
        }
        if (objectMap.remove("game_over_reason_ + GameOverReason") != null) {
            for (GameStateSystem.GameOverReason gameOverReason : GameStateSystem.GameOverReason.values()) {
                array3.add("game_over_reason_" + gameOverReason.name());
            }
        }
        if (objectMap.remove("gate_name_ + GateType") != null) {
            for (GateType gateType : GateType.values) {
                array3.add("gate_name_" + gateType.name());
            }
        }
        if (objectMap.remove("gate_description_ + GateType") != null) {
            for (GateType gateType2 : GateType.values) {
                array3.add("gate_description_" + gateType2.name());
            }
        }
        if (objectMap.remove("hotkey_ + HotkeyAction") != null) {
            for (SettingsManager.HotkeyAction hotkeyAction : SettingsManager.HotkeyAction.values) {
                array3.add("hotkey_" + hotkeyAction.name());
            }
        }
        if (objectMap.remove("invalid_map_format_hint_ + Reason") != null) {
            for (Map.InvalidMapException.Reason reason : Map.InvalidMapException.Reason.values()) {
                array3.add("invalid_map_format_hint_" + reason.name());
            }
        }
        if (objectMap.remove("item_title_CASE_ + CaseType") != null) {
            for (CaseType caseType2 : CaseType.values) {
                array3.add("item_title_CASE_" + caseType2.name());
            }
        }
        if (objectMap.remove("item_description_CASE_ + CaseType") != null) {
            for (CaseType caseType3 : CaseType.values) {
                array3.add("item_description_CASE_" + caseType3.name());
            }
        }
        if (objectMap.remove("item_category_ + ItemCategoryType") != null) {
            for (ItemCategoryType itemCategoryType : ItemCategoryType.values()) {
                array3.add("item_category_" + itemCategoryType.name());
            }
        }
        if (objectMap.remove("item_subcategory_ + ItemSubcategoryType") != null) {
            for (ItemSubcategoryType itemSubcategoryType : ItemSubcategoryType.values()) {
                array3.add("item_subcategory_" + itemSubcategoryType.name());
            }
        }
        if (objectMap.remove("inventory_tab_name_ + TabType") != null) {
            for (InventoryOverlay.TabType tabType : InventoryOverlay.TabType.values()) {
                array3.add("inventory_tab_name_" + tabType.name());
            }
        }
        if (objectMap.remove("levels.json stage titles") != null) {
            Array.ArrayIterator<BasicLevelStage> it = Game.i.basicLevelManager.stagesOrdered.iterator();
            while (it.hasNext()) {
                array3.add(it.next().titleAlias);
            }
        }
        if (objectMap.remove("measure_units_ + units") != null) {
            for (StringFormatter.MeasureUnits measureUnits : StringFormatter.MeasureUnits.values) {
                array3.add("measure_units_" + measureUnits.name());
            }
        }
        if (objectMap.remove("miner_name_ + MinerType") != null) {
            for (MinerType minerType : MinerType.values) {
                array3.add("miner_name_" + minerType.name());
            }
        }
        if (objectMap.remove("modifier_name_ + ModifierType") != null) {
            for (ModifierType modifierType : ModifierType.values) {
                array3.add("modifier_name_" + modifierType.name());
            }
        }
        if (objectMap.remove("modifier_name_fancy_ + ModifierType") != null) {
            for (ModifierType modifierType2 : ModifierType.values) {
                array3.add("modifier_name_fancy_" + modifierType2.name());
            }
        }
        if (objectMap.remove("modifier_description_ + ModifierType") != null) {
            for (ModifierType modifierType3 : ModifierType.values) {
                array3.add("modifier_description_" + modifierType3.name());
            }
        }
        if (objectMap.remove("modifier_simple_description_ + ModifierType") != null) {
            for (ModifierType modifierType4 : ModifierType.values) {
                array3.add("modifier_simple_description_" + modifierType4.name());
            }
        }
        if (objectMap.remove("rarity_ + RarityType") != null) {
            for (RarityType rarityType : RarityType.values) {
                array3.add("rarity_" + rarityType.name());
            }
        }
        if (objectMap.remove("research_title_ + ResearchCategoryType") != null) {
            for (ResearchCategoryType researchCategoryType : ResearchCategoryType.values) {
                array3.add("research_title_" + researchCategoryType.name());
            }
        }
        if (objectMap.remove("research_description_ + ResearchCategoryType") != null) {
            for (ResearchCategoryType researchCategoryType2 : ResearchCategoryType.values) {
                array3.add("research_description_" + researchCategoryType2.name());
            }
        }
        if (objectMap.remove("resource_name_ + ResourceType") != null) {
            for (ResourceType resourceType : ResourceType.values) {
                array3.add("resource_name_" + resourceType.name());
            }
        }
        if (objectMap.remove("levels.json SCRIPTED quests") != null) {
            Array.ArrayIterator<BasicLevel> it2 = Game.i.basicLevelManager.levelsOrdered.iterator();
            while (it2.hasNext()) {
                Array.ArrayIterator<BasicLevelQuestConfig> it3 = it2.next().quests.iterator();
                while (it3.hasNext()) {
                    BasicLevelQuestConfig next = it3.next();
                    if (next.isScripted()) {
                        array3.add(next.scriptedTitle);
                    }
                }
            }
        }
        if (objectMap.remove("space_tile_bonus_ + SpaceTileBonusType") != null) {
            for (SpaceTileBonusType spaceTileBonusType : SpaceTileBonusType.values) {
                array3.add("space_tile_bonus_" + spaceTileBonusType.name());
            }
        }
        if (objectMap.remove("statistics_ + StatisticsType") != null) {
            for (StatisticsType statisticsType : StatisticsType.values) {
                array3.add("statistics_" + statisticsType.name());
            }
        }
        if (objectMap.remove("start_research_fail_reason_ + StartResearchFailReason") != null) {
            for (ResearchManager.StartResearchFailReason startResearchFailReason : ResearchManager.StartResearchFailReason.values) {
                array3.add("start_research_fail_reason_" + startResearchFailReason.name());
            }
        }
        if (objectMap.remove("tower_name_ + TowerType") != null) {
            for (TowerType towerType : TowerType.values) {
                array3.add("tower_name_" + towerType.name());
            }
        }
        if (objectMap.remove("tower_description_ + TowerType") != null) {
            for (TowerType towerType2 : TowerType.values) {
                array3.add("tower_description_" + towerType2.name());
            }
        }
        if (objectMap.remove("tower_ability_TOWER_ABILITY_name") != null) {
            objectMap.remove("tower_ability_TOWER_ABILITY_description");
            for (TowerType towerType3 : TowerType.values) {
                for (String str2 : Game.i.towerManager.getFactory(towerType3).getAbilityAliases()) {
                    array3.add("tower_ability_" + towerType3.name() + JavaConstant.Dynamic.DEFAULT_NAME + str2 + "_name");
                    array3.add("tower_ability_" + towerType3.name() + JavaConstant.Dynamic.DEFAULT_NAME + str2 + "_description");
                }
            }
        }
        if (objectMap.remove("tower_unique_stat_description_ + TowerType") != null) {
            for (TowerType towerType4 : TowerType.values) {
                array3.add("tower_unique_stat_description_" + towerType4.name());
            }
        }
        if (objectMap.remove("tower_stat_ + TowerStatType") != null) {
            for (TowerStatType towerStatType : TowerStatType.values) {
                array3.add("tower_stat_" + towerStatType.name());
            }
        }
        if (objectMap.remove("tr_title_ + TrophyType") != null) {
            for (TrophyType trophyType : TrophyType.values) {
                array3.add("tr_title_" + trophyType.name());
            }
        }
        if (objectMap.remove("triggered_action_ + TriggeredActionType") != null) {
            for (TriggeredActionType triggeredActionType : TriggeredActionType.values) {
                array3.add("triggered_action_" + triggeredActionType.name());
            }
        }
        if (objectMap.remove("tile_name_ + TileType") != null) {
            for (TileType tileType : TileType.values) {
                array3.add("tile_name_" + tileType.name());
            }
        }
        if (objectMap.remove("tile_description_ + TileType") != null) {
            for (TileType tileType2 : TileType.values) {
                array3.add("tile_description_" + tileType2.name());
            }
        }
        if (objectMap.remove("game_over_hints") != null) {
            for (String str3 : GameOverOverlay.HINT_ALIASES) {
                array3.add(str3);
            }
        }
        Pattern compile3 = Pattern.compile("I18nGenerator[\\s]*\\{([^\\}]+)");
        Array.ArrayIterator<BasicLevel> it4 = Game.i.basicLevelManager.levelsOrdered.iterator();
        while (it4.hasNext()) {
            BasicLevel next2 = it4.next();
            Array.ArrayIterator<Tile> it5 = next2.getMap().getAllTiles().iterator();
            while (it5.hasNext()) {
                Tile next3 = it5.next();
                if (next3.type == TileType.SCRIPT && (script = ((ScriptTile) next3).getScript()) != null) {
                    Matcher matcher3 = compile3.matcher(script);
                    if (!matcher3.find()) {
                        f3842a.i("nothing found in " + next2.name, new Object[0]);
                    } else {
                        for (String str4 : matcher3.group(1).replaceAll("\"", "").split(",")) {
                            String trim4 = str4.trim();
                            if (trim4.length() > 0) {
                                array3.add(trim4);
                            }
                        }
                    }
                }
            }
        }
        ObjectMap.Keys it6 = objectMap.keys().iterator();
        while (it6.hasNext()) {
            String str5 = (String) it6.next();
            f3842a.w("runtime generated alias not implemented in I18nGenerator: " + str5 + " (" + ((String) objectMap.get(str5)) + ")", new Object[0]);
        }
        Array array4 = new Array();
        ObjectSet objectSet = new ObjectSet();
        for (int i3 = 0; i3 < array3.size; i3++) {
            objectSet.add((String) array3.get(i3));
        }
        for (int i4 = 0; i4 < array2.size; i4++) {
            String str6 = (String) array2.get(i4);
            if (!objectSet.contains(str6)) {
                objectSet.add(str6);
                array4.add(str6);
            }
        }
        array4.sort();
        Array array5 = new Array();
        array5.addAll(array4);
        array5.addAll(array3);
        try {
            ObjectMap objectMap2 = new ObjectMap();
            for (int i5 = 0; i5 < array5.size; i5++) {
                objectMap2.put((String) array5.get(i5), "");
            }
            DelayedRemovalArray delayedRemovalArray = new DelayedRemovalArray();
            DelayedRemovalArray delayedRemovalArray2 = new DelayedRemovalArray();
            FileInputStream fileInputStream = new FileInputStream(new File("i18n/MainBundle.properties"));
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            Pattern compile4 = Pattern.compile("\\[@([a-zA-Z0-9_-]+)]");
            ObjectMap objectMap3 = new ObjectMap();
            Enumeration keys = properties.keys();
            while (keys.hasMoreElements()) {
                String str7 = (String) keys.nextElement();
                String replace = properties.getProperty(str7).replace(SequenceUtils.EOL, "\\n");
                if (replace.length() != 0) {
                    objectMap3.put(str7, replace);
                    Matcher matcher4 = compile4.matcher(replace);
                    while (matcher4.find()) {
                        String trim5 = matcher4.group(1).trim();
                        if (!delayedRemovalArray2.contains(trim5, false)) {
                            delayedRemovalArray2.add(trim5);
                        }
                    }
                    if (!objectMap2.containsKey(str7)) {
                        delayedRemovalArray.add(str7);
                    } else {
                        objectMap2.put(str7, replace);
                    }
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("i18n/MainBundle.properties"));
            bufferedWriter.write("#B208\n");
            for (int i6 = 0; i6 < array5.size; i6++) {
                String str8 = (String) array5.get(i6);
                bufferedWriter.write(str8);
                if (!str8.startsWith("#")) {
                    bufferedWriter.write("=");
                    bufferedWriter.write((String) objectMap2.get(str8));
                }
                bufferedWriter.write(SequenceUtils.EOL);
            }
            delayedRemovalArray.begin();
            for (int i7 = 0; i7 < delayedRemovalArray.size; i7++) {
                int i8 = 0;
                while (true) {
                    if (i8 >= delayedRemovalArray2.size) {
                        break;
                    }
                    if (!((String) delayedRemovalArray2.get(i8)).equals(delayedRemovalArray.get(i7))) {
                        i8++;
                    } else {
                        delayedRemovalArray.removeIndex(i7);
                        break;
                    }
                }
            }
            delayedRemovalArray.end();
            delayedRemovalArray2.begin();
            for (int i9 = 0; i9 < delayedRemovalArray2.size; i9++) {
                if (objectMap2.containsKey((String) delayedRemovalArray2.get(i9))) {
                    delayedRemovalArray2.removeIndex(i9);
                }
            }
            delayedRemovalArray2.end();
            if (delayedRemovalArray2.size != 0) {
                delayedRemovalArray2.sort();
                for (int i10 = 0; i10 < delayedRemovalArray2.size; i10++) {
                    bufferedWriter.write((String) delayedRemovalArray2.get(i10));
                    bufferedWriter.write("=");
                    if (objectMap3.containsKey((String) delayedRemovalArray2.get(i10))) {
                        bufferedWriter.write((String) objectMap3.get((String) delayedRemovalArray2.get(i10)));
                    }
                    bufferedWriter.write(SequenceUtils.EOL);
                }
            }
            if (delayedRemovalArray.size != 0) {
                bufferedWriter.write("#\n");
                bufferedWriter.write("# Obsolete ----\n");
                bufferedWriter.write("#\n");
                delayedRemovalArray.sort((v0, v1) -> {
                    return v0.compareTo(v1);
                });
                for (int i11 = 0; i11 < delayedRemovalArray.size; i11++) {
                    bufferedWriter.write((String) delayedRemovalArray.get(i11));
                    bufferedWriter.write("=");
                    if (objectMap3.containsKey((String) delayedRemovalArray.get(i11))) {
                        bufferedWriter.write((String) objectMap3.get((String) delayedRemovalArray.get(i11)));
                    }
                    bufferedWriter.write(SequenceUtils.EOL);
                }
            }
            bufferedWriter.close();
        } catch (Exception e2) {
            f3842a.e("Failed to handle MainBundle.properties", e2);
        }
        f3842a.i("Done", new Object[0]);
    }

    private static int a(String str, String str2) {
        return str.length() - str.replace(str2, "").length();
    }

    private void a(File file, String[] strArr, Array<File> array) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2, strArr, array);
                }
                return;
            }
            return;
        }
        if (file.isFile()) {
            boolean z = false;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (!file.getName().endsWith(strArr[i])) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                array.add(file);
                return;
            }
            return;
        }
        f3842a.e("unable to search in '" + file.getName() + "', it's not a file or dir", new Object[0]);
    }
}

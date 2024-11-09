package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.BossTileType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.InterpolationType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.PredefinedCoreTileType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.RequirementType;
import com.prineside.tdi2.enums.ResearchCategoryType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.SoundType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/GameResourcesJsonGenerator.class */
public class GameResourcesJsonGenerator {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3834a = TLog.forClass(GameResourcesJsonGenerator.class);

    public void run() {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue("generatedInBuild", (Object) 208);
        json.writeObjectStart("enums");
        a(json, AbilityType.class);
        a(json, AchievementType.class);
        a(json, ActionType.class);
        a(json, BlueprintType.class);
        a(json, BossTileType.class);
        a(json, BossType.class);
        a(json, BuffType.class);
        a(json, BuildingType.class);
        a(json, CaseType.class);
        a(json, DamageType.class);
        a(json, DifficultyMode.class);
        a(json, EnemyType.class);
        a(json, ExplosionType.class);
        a(json, GameValueType.class);
        a(json, GateType.class);
        a(json, GeneralizedTowerStatType.class);
        a(json, InterpolationType.class);
        a(json, ItemCategoryType.class);
        a(json, ItemDataType.class);
        a(json, ItemSortingType.class);
        a(json, ItemSubcategoryType.class);
        a(json, ItemType.class);
        a(json, LimitedParticleType.class);
        a(json, MinerType.class);
        a(json, ModifierType.class);
        a(json, PredefinedCoreTileType.class);
        a(json, ProjectileType.class);
        a(json, RarityType.class);
        a(json, RequirementType.class);
        a(json, ResearchCategoryType.class);
        a(json, ResearchType.class);
        a(json, ResourceType.class);
        a(json, ShapeType.class);
        a(json, SoundType.class);
        a(json, SpaceTileBonusType.class);
        a(json, StaticSoundType.class);
        a(json, StatisticsType.class);
        a(json, TileType.class);
        a(json, TowerStatType.class);
        a(json, TowerType.class);
        a(json, TrophyType.class);
        json.writeObjectEnd();
        json.writeObjectStart("enumTitles");
        json.writeArrayStart(StatisticsType.class.getSimpleName());
        for (StatisticsType statisticsType : (StatisticsType[]) StatisticsType.class.getEnumConstants()) {
            json.writeValue(Game.i.statisticsManager.getStatisticsTitle(statisticsType));
        }
        json.writeObjectEnd();
        json.writeObjectEnd();
        json.writeObjectEnd();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("res/game-resources.json"));
            bufferedWriter.write(json.prettyPrint(stringWriter.toString()));
            bufferedWriter.close();
        } catch (Exception e) {
            f3834a.e("failed to write resources file", e);
        }
        f3834a.i("Done", new Object[0]);
    }

    private static void a(Json json, Class<? extends Enum> cls) {
        json.writeArrayStart(cls.getSimpleName());
        for (Enum r0 : (Enum[]) cls.getEnumConstants()) {
            json.writeValue(r0.name());
        }
        json.writeArrayEnd();
    }
}

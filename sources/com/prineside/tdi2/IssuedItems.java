package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/IssuedItems.class */
public class IssuedItems implements KryoSerializable {

    @NAGS
    public int issueTimestamp;

    @NAGS
    public boolean shown;
    public IssueReason reason;
    public Array<ItemStack> items = new Array<>(ItemStack.class);
    public GameStateSystem.GameMode basicLevelGameMode;
    public String gameOverBasicLevel;
    public String userMapId;
    public int userMapHash;
    public String dailyQuestDate;
    public String questBasicLevel;
    public String questId;
    public String waveQuestBasicLevel;
    public String waveQuestId;
    public String secretCode;
    public String secretCodeDescription;
    public float randomTilePackQuality;
    public float randomBarrierPackQuality;
    public CaseType caseType;
    public int dqBoardPlace;
    public int dqBoardTotalPlaces;
    public int dqBoardRankPercentage;
    public String dqDate;
    public String invitedPlayerId;
    public String invitedPlayerNickname;
    public MapPrestigeConfig mapPrestigeConfig;
    public String dailyLootDate;
    public AchievementType achievementType;
    public String failureCompensationDescription;
    public boolean addedToIssuedItemsArray;
    public int massUnpackCount;

    /* renamed from: a, reason: collision with root package name */
    private static final StringBuilder f1726a = new StringBuilder();

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/IssuedItems$IssueReason.class */
    public enum IssueReason {
        REGULAR_REWARD,
        BASIC_LEVEL_BONUS_ITEMS,
        GAME_OVER_BASIC_LEVEL,
        GAME_OVER_USER_MAP,
        GAME_OVER_DAILY_QUEST,
        QUEST,
        WAVE_QUEST,
        DAILY_QUEST,
        REWARD_VIDEO,
        PREMIUM_REWARD_VIDEO,
        SECRET_CODE,
        PURCHASE,
        RANDOM_TILE_PACK,
        RANDOM_BARRIER_PACK,
        RANDOM_TELEPORT_PACK,
        CASE,
        DAILY_QUEST_LEADER_BOARD,
        MAP_PRESTIGE,
        QUESTS_PRESTIGE,
        DAILY_LOOT,
        SIGNED_UP_BY_INVITE,
        FOR_INVITED_PLAYER,
        FAILURE_COMPENSATION,
        LUCKY_SHOT,
        ACHIEVEMENT
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeInt(this.issueTimestamp);
        output.writeBoolean(this.shown);
        kryo.writeObject(output, this.reason);
        kryo.writeObject(output, this.items);
        kryo.writeObjectOrNull(output, this.basicLevelGameMode, GameStateSystem.GameMode.class);
        kryo.writeObjectOrNull(output, this.gameOverBasicLevel, String.class);
        kryo.writeObjectOrNull(output, this.userMapId, String.class);
        output.writeInt(this.userMapHash);
        kryo.writeObjectOrNull(output, this.dailyQuestDate, String.class);
        kryo.writeObjectOrNull(output, this.questBasicLevel, String.class);
        kryo.writeObjectOrNull(output, this.questId, String.class);
        kryo.writeObjectOrNull(output, this.waveQuestBasicLevel, String.class);
        kryo.writeObjectOrNull(output, this.waveQuestId, String.class);
        kryo.writeObjectOrNull(output, this.secretCode, String.class);
        kryo.writeObjectOrNull(output, this.secretCodeDescription, String.class);
        output.writeFloat(this.randomTilePackQuality);
        output.writeFloat(this.randomBarrierPackQuality);
        kryo.writeObjectOrNull(output, this.caseType, CaseType.class);
        output.writeVarInt(this.dqBoardPlace, false);
        output.writeVarInt(this.dqBoardTotalPlaces, true);
        output.writeVarInt(this.dqBoardRankPercentage, false);
        kryo.writeObjectOrNull(output, this.dqDate, String.class);
        kryo.writeObjectOrNull(output, this.invitedPlayerId, String.class);
        kryo.writeObjectOrNull(output, this.invitedPlayerNickname, String.class);
        kryo.writeObjectOrNull(output, this.mapPrestigeConfig, MapPrestigeConfig.class);
        kryo.writeObjectOrNull(output, this.dailyLootDate, String.class);
        kryo.writeObjectOrNull(output, this.achievementType, AchievementType.class);
        kryo.writeObjectOrNull(output, this.failureCompensationDescription, String.class);
        output.writeBoolean(this.addedToIssuedItemsArray);
        output.writeVarInt(this.massUnpackCount, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.issueTimestamp = input.readInt();
        this.shown = input.readBoolean();
        this.reason = (IssueReason) kryo.readObject(input, IssueReason.class);
        this.items = (Array) kryo.readObject(input, Array.class);
        this.basicLevelGameMode = (GameStateSystem.GameMode) kryo.readObjectOrNull(input, GameStateSystem.GameMode.class);
        this.gameOverBasicLevel = (String) kryo.readObjectOrNull(input, String.class);
        this.userMapId = (String) kryo.readObjectOrNull(input, String.class);
        this.userMapHash = input.readInt();
        this.dailyQuestDate = (String) kryo.readObjectOrNull(input, String.class);
        this.questBasicLevel = (String) kryo.readObjectOrNull(input, String.class);
        this.questId = (String) kryo.readObjectOrNull(input, String.class);
        this.waveQuestBasicLevel = (String) kryo.readObjectOrNull(input, String.class);
        this.waveQuestId = (String) kryo.readObjectOrNull(input, String.class);
        this.secretCode = (String) kryo.readObjectOrNull(input, String.class);
        this.secretCodeDescription = (String) kryo.readObjectOrNull(input, String.class);
        this.randomTilePackQuality = input.readFloat();
        this.randomBarrierPackQuality = input.readFloat();
        this.caseType = (CaseType) kryo.readObjectOrNull(input, CaseType.class);
        this.dqBoardPlace = input.readVarInt(false);
        this.dqBoardTotalPlaces = input.readVarInt(true);
        this.dqBoardRankPercentage = input.readVarInt(false);
        this.dqDate = (String) kryo.readObjectOrNull(input, String.class);
        this.invitedPlayerId = (String) kryo.readObjectOrNull(input, String.class);
        this.invitedPlayerNickname = (String) kryo.readObjectOrNull(input, String.class);
        this.mapPrestigeConfig = (MapPrestigeConfig) kryo.readObjectOrNull(input, MapPrestigeConfig.class);
        this.dailyLootDate = (String) kryo.readObjectOrNull(input, String.class);
        this.achievementType = (AchievementType) kryo.readObjectOrNull(input, AchievementType.class);
        this.failureCompensationDescription = (String) kryo.readObjectOrNull(input, String.class);
        this.addedToIssuedItemsArray = input.readBoolean();
        this.massUnpackCount = input.readVarInt(true);
    }

    private IssuedItems() {
    }

    public IssuedItems(IssueReason issueReason, int i) {
        this.issueTimestamp = i;
        this.reason = issueReason;
    }

    public void compactItems() {
        for (int i = this.items.size - 1; i >= 0; i--) {
            ItemStack itemStack = this.items.items[i];
            for (int i2 = i - 1; i2 >= 0; i2--) {
                ItemStack itemStack2 = this.items.items[i2];
                if (itemStack.getItem().sameAs(itemStack2.getItem())) {
                    itemStack2.setCount(PMath.addWithoutOverflow(itemStack2.getCount(), itemStack.getCount()));
                    this.items.removeIndex(i);
                }
            }
        }
    }

    public CharSequence getReasonDescription() {
        f1726a.setLength(0);
        switch (this.reason) {
            case QUEST:
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.questBasicLevel);
                if (level != null) {
                    BasicLevelQuestConfig quest = level.getQuest(this.questId);
                    f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_QUEST", quest.getTitle(false, true), quest.formatValueForUi(quest.getRequiredValue(Game.i.gameValueManager.getSnapshot())), this.questBasicLevel));
                    break;
                } else {
                    f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_QUEST_lite", this.questBasicLevel));
                    break;
                }
            case WAVE_QUEST:
                BasicLevel level2 = Game.i.basicLevelManager.getLevel(this.waveQuestBasicLevel);
                if (level2 != null) {
                    f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_WAVE_QUEST", Integer.valueOf(level2.getWaveQuest(this.waveQuestId).waves), level2.name));
                    break;
                } else {
                    f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_WAVE_QUEST_lite"));
                    break;
                }
            case GAME_OVER_BASIC_LEVEL:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_GAME_OVER_BASIC_LEVEL", this.gameOverBasicLevel));
                break;
            case BASIC_LEVEL_BONUS_ITEMS:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_BASIC_LEVEL_BONUS_ITEMS"));
                break;
            case GAME_OVER_DAILY_QUEST:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_DAILY_QUEST")).append(SequenceUtils.SPACE).append(this.dailyQuestDate);
                break;
            case DAILY_QUEST:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_DAILY_QUEST"));
                break;
            case REWARD_VIDEO:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_REWARD_VIDEO"));
                break;
            case PREMIUM_REWARD_VIDEO:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_PREMIUM_REWARD_VIDEO"));
                break;
            case SECRET_CODE:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_SECRET_CODE")).append(" \"").append(this.secretCode).append("\" (").append(this.secretCodeDescription).append(")");
                break;
            case GAME_OVER_USER_MAP:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_GAME_OVER_USER_MAP"));
                break;
            case PURCHASE:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_PURCHASE"));
                break;
            case RANDOM_TILE_PACK:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_RANDOM_TILE_PACK", PMath.toString(StrictMath.round(this.randomTilePackQuality * 100.0f))));
                break;
            case RANDOM_BARRIER_PACK:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_RANDOM_BARRIER_PACK", PMath.toString(StrictMath.round(this.randomBarrierPackQuality * 100.0f))));
                break;
            case MAP_PRESTIGE:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_MAP_PRESTIGE"));
                break;
            case DAILY_LOOT:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_DAILY_LOOT", this.dailyLootDate));
                break;
            case LUCKY_SHOT:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_LUCKY_SHOT", this.dailyLootDate));
                break;
            case QUESTS_PRESTIGE:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_QUESTS_PRESTIGE"));
                break;
            case RANDOM_TELEPORT_PACK:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_RANDOM_TELEPORT_PACK"));
                break;
            case CASE:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_CASE", String.valueOf(Item.D.F_CASE.create(this.caseType, true).getTitle())));
                break;
            case DAILY_QUEST_LEADER_BOARD:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_DAILY_QUEST_LEADER_BOARD", this.dqDate, Integer.valueOf(this.dqBoardPlace), Integer.valueOf(this.dqBoardTotalPlaces), Integer.valueOf(this.dqBoardRankPercentage)));
                break;
            case SIGNED_UP_BY_INVITE:
                f1726a.append(Game.i.localeManager.i18n.get("issued_items_reason_description_SIGNED_UP_BY_INVITE"));
                break;
            case FOR_INVITED_PLAYER:
                f1726a.append(Game.i.localeManager.i18n.format("issued_items_reason_description_FOR_INVITED_PLAYER", this.invitedPlayerNickname));
                break;
            case ACHIEVEMENT:
                f1726a.append(Game.i.localeManager.i18n.format("achievement_complete", Game.i.achievementManager.getName(this.achievementType)));
                break;
            case FAILURE_COMPENSATION:
                if (this.failureCompensationDescription != null) {
                    f1726a.append(this.failureCompensationDescription);
                    break;
                }
                break;
        }
        if (this.massUnpackCount > 1) {
            f1726a.append(" x").append(StringFormatter.commaSeparatedNumber(this.massUnpackCount));
        }
        return f1726a;
    }

    public void toJson(Json json) {
        json.writeValue("r", this.reason.name());
        json.writeValue("it", Integer.valueOf(this.issueTimestamp));
        json.writeValue("s", Boolean.valueOf(this.shown));
        if (this.massUnpackCount != 0) {
            json.writeValue("muc", Integer.valueOf(this.massUnpackCount));
        }
        switch (this.reason) {
            case QUEST:
                json.writeValue("bl", this.questBasicLevel);
                json.writeValue("q", this.questId);
                break;
            case WAVE_QUEST:
                json.writeValue("bl", this.waveQuestBasicLevel);
                json.writeValue("q", this.waveQuestId);
                break;
            case GAME_OVER_BASIC_LEVEL:
                json.writeValue("bl", this.gameOverBasicLevel);
                json.writeValue("blgm", this.basicLevelGameMode);
                break;
            case GAME_OVER_DAILY_QUEST:
                json.writeValue("dqd", this.dailyQuestDate);
                break;
            case SECRET_CODE:
                json.writeValue("c", this.secretCode);
                json.writeValue("m", this.secretCodeDescription);
                break;
            case GAME_OVER_USER_MAP:
                json.writeValue("umi", this.userMapId);
                json.writeValue("umh", Integer.valueOf(this.userMapHash));
                break;
            case RANDOM_TILE_PACK:
                json.writeValue("rtpq", Float.valueOf(this.randomTilePackQuality));
                break;
            case RANDOM_BARRIER_PACK:
                json.writeValue("rbpq", Float.valueOf(this.randomBarrierPackQuality));
                break;
            case MAP_PRESTIGE:
                json.writeObjectStart("mpc");
                this.mapPrestigeConfig.toJson(json);
                json.writeObjectEnd();
                break;
            case DAILY_LOOT:
                json.writeValue("dld", this.dailyLootDate);
                break;
            case CASE:
                json.writeValue("ct", this.caseType.name());
                break;
            case DAILY_QUEST_LEADER_BOARD:
                json.writeValue("dqlbp", Integer.valueOf(this.dqBoardPlace));
                json.writeValue("dqlbrp", Integer.valueOf(this.dqBoardRankPercentage));
                json.writeValue("dqlbtp", Integer.valueOf(this.dqBoardTotalPlaces));
                json.writeValue("dqlbd", this.dqDate);
                break;
            case FOR_INVITED_PLAYER:
                json.writeValue("ipipi", this.invitedPlayerId);
                json.writeValue("ipipn", this.invitedPlayerNickname);
                break;
            case ACHIEVEMENT:
                json.writeValue("at", this.achievementType.name());
                break;
            case FAILURE_COMPENSATION:
                if (this.failureCompensationDescription != null) {
                    json.writeValue("fcd", this.failureCompensationDescription);
                    break;
                }
                break;
        }
        json.writeArrayStart(FlexmarkHtmlConverter.I_NODE);
        for (int i = 0; i < this.items.size; i++) {
            json.writeObjectStart();
            this.items.items[i].toJson(json);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
    }

    public static IssuedItems fromJson(JsonValue jsonValue) {
        IssuedItems issuedItems = new IssuedItems(IssueReason.valueOf(jsonValue.getString("r")), jsonValue.getInt("it"));
        issuedItems.shown = jsonValue.getBoolean("s", false);
        issuedItems.massUnpackCount = jsonValue.getInt("muc", 0);
        Iterator<JsonValue> iterator2 = jsonValue.get(FlexmarkHtmlConverter.I_NODE).iterator2();
        while (iterator2.hasNext()) {
            issuedItems.items.add(ItemStack.fromJson(iterator2.next()));
        }
        switch (issuedItems.reason) {
            case QUEST:
                issuedItems.questBasicLevel = jsonValue.getString("bl");
                issuedItems.questId = jsonValue.getString("q");
                break;
            case WAVE_QUEST:
                issuedItems.waveQuestBasicLevel = jsonValue.getString("bl");
                issuedItems.waveQuestId = jsonValue.getString("q");
                break;
            case GAME_OVER_BASIC_LEVEL:
                issuedItems.gameOverBasicLevel = jsonValue.getString("bl");
                issuedItems.basicLevelGameMode = GameStateSystem.GameMode.valueOf(jsonValue.getString("blgm", GameStateSystem.GameMode.BASIC_LEVELS.name()));
                break;
            case GAME_OVER_DAILY_QUEST:
                issuedItems.dailyQuestDate = jsonValue.getString("dqd");
                break;
            case SECRET_CODE:
                issuedItems.secretCode = jsonValue.getString("c");
                issuedItems.secretCodeDescription = jsonValue.getString("m");
                break;
            case GAME_OVER_USER_MAP:
                issuedItems.userMapHash = jsonValue.getInt("umh");
                issuedItems.userMapId = jsonValue.getString("umi");
                break;
            case RANDOM_TILE_PACK:
                issuedItems.randomTilePackQuality = jsonValue.getFloat("rtpq");
                break;
            case RANDOM_BARRIER_PACK:
                issuedItems.randomBarrierPackQuality = jsonValue.getFloat("rbpq");
                break;
            case MAP_PRESTIGE:
                issuedItems.mapPrestigeConfig = MapPrestigeConfig.fromJson(jsonValue.get("mpc"));
                break;
            case DAILY_LOOT:
                issuedItems.dailyLootDate = jsonValue.getString("dld");
                break;
            case CASE:
                issuedItems.caseType = CaseType.valueOf(jsonValue.getString("ct"));
                break;
            case DAILY_QUEST_LEADER_BOARD:
                issuedItems.dqBoardPlace = jsonValue.getInt("dqlbp");
                issuedItems.dqBoardRankPercentage = jsonValue.getInt("dqlbrp");
                issuedItems.dqBoardTotalPlaces = jsonValue.getInt("dqlbtp");
                issuedItems.dqDate = jsonValue.getString("dqlbd");
                break;
            case FOR_INVITED_PLAYER:
                issuedItems.invitedPlayerId = jsonValue.getString("ipipi");
                issuedItems.invitedPlayerNickname = jsonValue.getString("ipipn");
                break;
            case ACHIEVEMENT:
                issuedItems.achievementType = AchievementType.valueOf(jsonValue.getString("at"));
                break;
            case FAILURE_COMPENSATION:
                issuedItems.failureCompensationDescription = jsonValue.getString("fcd", null);
                break;
        }
        return issuedItems;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (reason: " + this.reason + ", item types: " + this.items.size + ")";
    }
}

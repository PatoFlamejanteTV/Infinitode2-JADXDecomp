package com.prineside.tdi2.managers.preferences.categories.settings;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Auth.class */
public final class SP_Auth implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2545a = TLog.forClass(SP_Auth.class);

    @Null
    private String c;
    public int cloudSaveSlotTimestamp;
    public boolean autoSavesEnabled;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2546b = false;
    public int cloudSaveSlotId = -1;

    @Null
    public SessionData sessionData = new SessionData();

    @Null
    public final String getOfflinePlayerId() {
        return this.c;
    }

    public final synchronized void setOfflinePlayerId(String str) {
        this.c = str;
    }

    public final boolean isHasUnsavedProgressForCloud() {
        return this.f2546b;
    }

    public final synchronized void setHasUnsavedProgressForCloud(boolean z) {
        this.f2546b = z;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.f2546b = false;
        try {
            this.f2546b = Boolean.parseBoolean(prefMap.get("hasUnsavedProgressForCloud", "false"));
        } catch (Exception e) {
            f2545a.e("failed to load hasUnsavedProgressForCloud", e);
        }
        this.c = prefMap.get("playerid", null);
        if (this.c != null && this.c.length() > 32) {
            f2545a.e("Invalid playerid, set to null: " + this.c, new Object[0]);
            this.c = null;
        }
        this.cloudSaveSlotId = -1;
        this.cloudSaveSlotTimestamp = 0;
        try {
            String str = prefMap.get("cloudSaveSlot", null);
            if (str != null) {
                JsonValue parse = new JsonReader().parse(str);
                this.cloudSaveSlotId = parse.getInt(Attribute.ID_ATTR);
                this.cloudSaveSlotTimestamp = parse.getInt("timestamp");
            }
            this.autoSavesEnabled = Boolean.parseBoolean(prefMap.get("authAutoSaves", "false"));
        } catch (Exception e2) {
            f2545a.e("failed to load cloudSaveSlot or authAutoSaves", e2);
        }
        try {
            String str2 = prefMap.get("authSession", null);
            if (str2 != null) {
                this.sessionData.fromJson(new JsonReader().parse(str2));
            }
        } catch (Exception e3) {
            f2545a.e("failed to load authSession data", e3);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("hasUnsavedProgressForCloud", this.f2546b ? "true" : "false");
        prefMap.set("playerid", this.c);
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue(Attribute.ID_ATTR, Integer.valueOf(this.cloudSaveSlotId));
        json.writeValue("timestamp", Integer.valueOf(this.cloudSaveSlotTimestamp));
        json.writeObjectEnd();
        prefMap.set("cloudSaveSlot", stringWriter.toString());
        prefMap.set("authAutoSaves", String.valueOf(this.autoSavesEnabled));
        if (this.sessionData.sessionId != null) {
            Json json2 = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter2 = new StringWriter();
            json2.setWriter(stringWriter2);
            json2.writeObjectStart();
            this.sessionData.toJson(json2);
            json2.writeObjectEnd();
            prefMap.set("authSession", stringWriter2.toString());
        }
    }

    public final void setNoCloudSaveSlot() {
        this.cloudSaveSlotId = -1;
        this.cloudSaveSlotTimestamp = 0;
        this.f2546b = false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Auth$SessionData.class */
    public static final class SessionData {

        @Null
        public String sessionId;

        @Null
        public String nickname;
        public long updateTimestamp;

        @Null
        public String playerId;

        @Null
        public String inviteCode;

        @Null
        public String invitedBy;

        @Null
        public String emailHint;

        @Null
        public String steamAccountId;
        public boolean hasAvatar;
        public long lastLoadFromCloudTimestamp;
        public int currentLevelXp;
        public int bonusXpRemaining;
        public int regularXpRemaining;
        public int tempXp;
        public int nextXpRefreshTimestamp;
        public int nextLevelXp;
        public int profileLevel;
        public int maxProfileLevel;
        public float playedLevelXpCoeff;
        public float bonusLevelXpCoeff;

        @Null
        public String bonusXpLevel;
        public boolean passwordSet = true;
        public AuthManager.XpStatus xpStatus = AuthManager.XpStatus.NORMAL;
        public Array<String> xpPlayedLevels = new Array<>(String.class);
        public Array<AuthManager.ProfileStatus> profileStatuses = new Array<>(true, 1, AuthManager.ProfileStatus.class);

        public final void toJson(Json json) {
            json.writeValue(Attribute.ID_ATTR, this.sessionId);
            json.writeValue("nickname", this.nickname);
            json.writeValue("playerId", this.playerId);
            json.writeValue("emailHint", this.emailHint);
            json.writeValue("steamAccountId", this.steamAccountId);
            json.writeValue("lastLoadFromCloudTimestamp", Long.valueOf(this.lastLoadFromCloudTimestamp));
            if (this.inviteCode != null) {
                json.writeValue("inviteCode", this.inviteCode);
            }
            if (this.invitedBy != null) {
                json.writeValue("invitedBy", this.invitedBy);
            }
            json.writeValue("updateTimestamp", Long.valueOf(this.updateTimestamp));
            json.writeValue("xpStatus", Integer.valueOf(this.xpStatus.ordinal()));
            json.writeValue("currentLevelXp", Integer.valueOf(this.currentLevelXp));
            json.writeValue("tempXp", Integer.valueOf(this.tempXp));
            json.writeValue("nextLevelXp", Integer.valueOf(this.nextLevelXp));
            json.writeValue("profileLevel", Integer.valueOf(this.profileLevel));
            json.writeValue("maxProfileLevel", Integer.valueOf(this.maxProfileLevel));
            json.writeValue("playedLevelXpCoeff", Float.valueOf(this.playedLevelXpCoeff));
            json.writeValue("bonusLevelXpCoeff", Float.valueOf(this.bonusLevelXpCoeff));
            if (this.bonusXpLevel != null) {
                json.writeValue("bonusXpLevel", this.bonusXpLevel);
            }
            json.writeValue("bonusXpRemaining", Integer.valueOf(this.bonusXpRemaining));
            json.writeValue("regularXpRemaining", Integer.valueOf(this.regularXpRemaining));
            json.writeValue("nextXpRefresh", Integer.valueOf(this.nextXpRefreshTimestamp));
            json.writeArrayStart("xpPlayedLevels");
            for (int i = 0; i < this.xpPlayedLevels.size; i++) {
                json.writeValue(this.xpPlayedLevels.items[i]);
            }
            json.writeArrayEnd();
            json.writeArrayStart("profileStatuses");
            for (int i2 = 0; i2 < this.profileStatuses.size; i2++) {
                AuthManager.ProfileStatus profileStatus = this.profileStatuses.get(i2);
                json.writeObjectStart();
                json.writeValue(Attribute.ID_ATTR, profileStatus.id);
                json.writeValue("reason", profileStatus.reason);
                json.writeValue("receivedAt", Integer.valueOf(profileStatus.receivedAt));
                json.writeValue("expiresAt", Integer.valueOf(profileStatus.expiresAt));
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public final void fromJson(JsonValue jsonValue) {
            this.sessionId = jsonValue.getString(Attribute.ID_ATTR);
            this.nickname = jsonValue.getString("nickname");
            this.updateTimestamp = jsonValue.getLong("updateTimestamp");
            this.playerId = jsonValue.getString("playerId");
            this.inviteCode = jsonValue.getString("inviteCode", null);
            this.invitedBy = jsonValue.getString("invitedBy", null);
            this.passwordSet = true;
            this.emailHint = jsonValue.getString("emailHint", "");
            this.steamAccountId = jsonValue.getString("steamAccountId", null);
            this.lastLoadFromCloudTimestamp = jsonValue.getLong("lastLoadFromCloudTimestamp", 0L);
            this.xpStatus = AuthManager.XpStatus.values[jsonValue.getInt("xpStatus", AuthManager.XpStatus.NORMAL.ordinal())];
            this.currentLevelXp = jsonValue.getInt("currentLevelXp", 0);
            this.bonusXpRemaining = jsonValue.getInt("bonusXpRemaining", 0);
            this.regularXpRemaining = jsonValue.getInt("regularXpRemaining", 0);
            this.nextXpRefreshTimestamp = jsonValue.getInt("nextXpRefresh", Game.getTimestampSeconds() + 432000);
            this.tempXp = jsonValue.getInt("tempXp", 0);
            this.nextLevelXp = jsonValue.getInt("nextLevelXp", 1000);
            this.profileLevel = jsonValue.getInt("profileLevel", 1);
            this.maxProfileLevel = jsonValue.getInt("maxProfileLevel", 30);
            this.playedLevelXpCoeff = jsonValue.getFloat("playedLevelXpCoeff", 0.5f);
            this.bonusLevelXpCoeff = jsonValue.getFloat("bonusLevelXpCoeff", 1.5f);
            this.bonusXpLevel = jsonValue.getString("bonusXpLevel", null);
            if (jsonValue.get("xpPlayedLevels") != null) {
                Iterator<JsonValue> iterator2 = jsonValue.get("xpPlayedLevels").iterator2();
                while (iterator2.hasNext()) {
                    this.xpPlayedLevels.add(iterator2.next().asString());
                }
            }
            this.profileStatuses.clear();
            JsonValue jsonValue2 = jsonValue.get("profileStatuses");
            if (jsonValue2 != null) {
                Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next = iterator22.next();
                    try {
                        AuthManager.ProfileStatus profileStatus = new AuthManager.ProfileStatus();
                        profileStatus.id = next.getString(Attribute.ID_ATTR);
                        profileStatus.reason = next.getString("reason");
                        profileStatus.receivedAt = next.getInt("receivedAt");
                        profileStatus.expiresAt = next.getInt("expiresAt", -1);
                        this.profileStatuses.add(profileStatus);
                    } catch (Exception e) {
                        SP_Auth.f2545a.e("failed to load profile status: " + next.toString(), e);
                    }
                }
            }
        }

        public final void fromServerResponseJson(JsonValue jsonValue) {
            this.sessionId = jsonValue.getString("sessionId");
            String string = jsonValue.getString("newSession", null);
            if (string != null) {
                this.sessionId = string;
            }
            this.hasAvatar = jsonValue.getBoolean("hasPfp", false);
            this.passwordSet = jsonValue.getBoolean("passwordSet", true);
            this.steamAccountId = jsonValue.getString("steamAccountId", null);
            this.nickname = jsonValue.getString("nickname");
            this.emailHint = jsonValue.getString("emailHint", "");
            this.updateTimestamp = Game.getTimestampMillis();
            this.playerId = jsonValue.getString("playerid");
            this.inviteCode = jsonValue.getString("inviteCode", null);
            this.invitedBy = jsonValue.getString("invitedBy", null);
            JsonValue jsonValue2 = jsonValue.get("xp");
            if (jsonValue2 != null) {
                this.currentLevelXp = jsonValue2.get("current").getInt("level-xp");
                this.bonusXpRemaining = jsonValue2.getInt("bonusXpRemaining", 0);
                this.regularXpRemaining = jsonValue2.getInt("regularXpRemaining", 0);
                this.nextXpRefreshTimestamp = jsonValue2.getInt("nextXpRefresh", Game.getTimestampSeconds() + 432000);
                this.tempXp = jsonValue2.getInt("temp");
                this.nextLevelXp = jsonValue2.get("current").getInt("next-level");
                this.profileLevel = jsonValue2.get("current").getInt("level");
                this.maxProfileLevel = jsonValue2.get("current").getInt("maxLevel");
                this.playedLevelXpCoeff = jsonValue2.get("dailyXp").getFloat("playedLevelXpCoeff");
                this.bonusLevelXpCoeff = jsonValue2.get("dailyXp").getFloat("bonusLevelXpCoeff");
                this.bonusXpLevel = jsonValue2.get("dailyXp").getString("bonusLevel");
                this.xpPlayedLevels.clear();
                Iterator<JsonValue> iterator2 = jsonValue2.get("dailyXp").get("playedToday").iterator2();
                while (iterator2.hasNext()) {
                    this.xpPlayedLevels.add(iterator2.next().asString());
                }
                this.xpStatus = AuthManager.XpStatus.NORMAL;
                if (jsonValue2.getString("status").equals("bonus")) {
                    this.xpStatus = AuthManager.XpStatus.BONUS;
                } else if (jsonValue2.getString("status").equals("reduced")) {
                    this.xpStatus = AuthManager.XpStatus.REDUCED;
                }
            }
            this.profileStatuses.clear();
            JsonValue jsonValue3 = jsonValue.get("profileStatuses");
            if (jsonValue3 != null) {
                Iterator<JsonValue> iterator22 = jsonValue3.iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next = iterator22.next();
                    try {
                        AuthManager.ProfileStatus profileStatus = new AuthManager.ProfileStatus();
                        profileStatus.id = next.getString("status");
                        profileStatus.receivedAt = next.getInt("received_at", Game.getTimestampSeconds());
                        profileStatus.expiresAt = next.getInt("expires_at", -1);
                        profileStatus.reason = next.getString("reason");
                        this.profileStatuses.add(profileStatus);
                    } catch (Exception e) {
                        SP_Auth.f2545a.e("failed to store profile status: " + next.toJson(JsonWriter.OutputType.json), e);
                    }
                }
            }
        }
    }
}

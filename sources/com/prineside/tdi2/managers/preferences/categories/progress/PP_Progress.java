package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.ui.shared.LuckyWheelOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Progress.class */
public final class PP_Progress implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2533a = TLog.forClass(PP_Progress.class);
    private boolean e;
    private float f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final RandomXS128 n;

    @Null
    private Array<LuckyWheelOverlay.WheelOption> o;
    private boolean p;
    private boolean q;
    private int r;
    private float s;
    private float t;
    private final RandomXS128 u;
    private final RandomXS128 v;
    private final IntArray w;
    private int x;
    private int y;
    private String z;

    @Null
    private Array<ProgressManager.ShopOffer> A;
    private int B;
    private boolean C;

    /* renamed from: b, reason: collision with root package name */
    private DifficultyMode f2534b = DifficultyMode.NORMAL;
    private boolean c = false;
    private boolean d = false;
    private final RandomXS128[] m = new RandomXS128[CaseType.values.length];

    public PP_Progress() {
        for (int i = 0; i < this.m.length; i++) {
            this.m[i] = new RandomXS128(i * 1023);
        }
        this.n = new RandomXS128(1025L);
        this.r = 1;
        this.u = new RandomXS128();
        this.v = new RandomXS128();
        this.w = new IntArray();
        this.x = 208;
        this.y = Game.getTimestampSeconds();
        this.z = FastRandom.getDistinguishableString(12, null);
    }

    public final boolean isCurrentShopOffersAreAfterSkip() {
        return this.C;
    }

    public final synchronized void setCurrentShopOffersAreAfterSkip(boolean z) {
        this.C = z;
    }

    public final int getPlayTimeUntilShopOffersUpdate() {
        return this.B;
    }

    public final synchronized void setPlayTimeUntilShopOffersUpdate(int i) {
        this.B = i;
    }

    @Null
    public final Array<ProgressManager.ShopOffer> getShopOffers() {
        return this.A;
    }

    public final synchronized void setShopOffers(@Null Array<ProgressManager.ShopOffer> array) {
        this.A = array;
    }

    public final String getGameStartHash() {
        return this.z;
    }

    public final int getGameStartTimestamp() {
        return this.y;
    }

    public final int getGameStartGameVersion() {
        return this.x;
    }

    public final boolean isConditionalCompensationHandled(int i) {
        return this.w.contains(i);
    }

    public final synchronized void addHandledConditionalCompensation(int i) {
        if (!this.w.contains(i)) {
            this.w.add(i);
        }
    }

    public final RandomXS128 getLuckyWheelSpinRandom() {
        return this.v;
    }

    public final RandomXS128 getLuckyWheelWheelRandom() {
        return this.u;
    }

    public final float getLuckyWheelLastWeaponAngle() {
        return this.t;
    }

    public final void setLuckyWheelLastWeaponAngle(float f) {
        this.t = f;
    }

    public final float getLuckyWheelLastRotation() {
        return this.s;
    }

    public final void setLuckyWheelLastRotation(float f) {
        this.s = f;
    }

    public final int getLuckyWheelCurrentMultiplier() {
        return this.r;
    }

    public final void setLuckyWheelCurrentMultiplier(int i) {
        this.r = i;
    }

    public final boolean isLuckyWheelSpinInProgress() {
        return this.q;
    }

    public final synchronized void setLuckyWheelSpinInProgress(boolean z) {
        this.q = z;
    }

    public final boolean isLuckyWheelSpinAvailable() {
        return this.p;
    }

    public final synchronized void setLuckyWheelSpinAvailable(boolean z) {
        this.p = z;
    }

    @Null
    public final Array<LuckyWheelOverlay.WheelOption> getLuckyWheelOptions() {
        return this.o;
    }

    public final synchronized void setLuckyWheelOptions(@Null Array<LuckyWheelOverlay.WheelOption> array) {
        this.o = array;
    }

    public final RandomXS128 getOtherItemsRandom() {
        return this.n;
    }

    public final RandomXS128 getCaseRandom(CaseType caseType) {
        return this.m[caseType.ordinal()];
    }

    public final int getTempDoubleGainStartDate() {
        return this.k;
    }

    public final synchronized void setTempDoubleGainStartDate(int i) {
        this.k = i;
    }

    public final int getTempDoubleGainEndDate() {
        return this.l;
    }

    public final synchronized void setTempDoubleGainEndDate(int i) {
        this.l = i;
    }

    public final synchronized void registerVideoWatched() {
        this.i++;
        this.j++;
    }

    public final int getVideosWatchedForDoubleGain() {
        return this.i;
    }

    public final synchronized void setVideosWatchedForDoubleGain(int i) {
        this.i = i;
    }

    public final int getVideosWatchedForLuckyShot() {
        return this.j;
    }

    public final synchronized void setVideosWatchedForLuckyShot(int i) {
        this.j = i;
    }

    public final boolean isMidGameDgRewardGiven() {
        return this.g;
    }

    public final boolean isEncounterBirdEncountered() {
        return this.h;
    }

    public final synchronized void setMidGameDgRewardGiven(boolean z) {
        this.g = z;
    }

    public final synchronized void setEncounterBirdEncountered(boolean z) {
        this.h = z;
    }

    public final float getLootBoostTimeLeft() {
        return this.f;
    }

    public final synchronized void setLootBoostTimeLeft(float f) {
        this.f = f;
    }

    public final boolean isSteamRewardReceived() {
        return this.d;
    }

    public final synchronized void setSteamRewardReceived(boolean z) {
        this.d = z;
    }

    public final boolean isDoubleGainEnabled() {
        return this.c;
    }

    public final synchronized void setDoubleGainEnabled(boolean z) {
        this.c = z;
    }

    public final DifficultyMode getDifficultyMode() {
        return this.f2534b;
    }

    public final synchronized void setDifficultyMode(DifficultyMode difficultyMode) {
        this.f2534b = difficultyMode;
    }

    public final boolean isBonusGivenForRegByInvite() {
        return this.e;
    }

    public final synchronized void setBonusGivenForRegByInvite(boolean z) {
        this.e = z;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2534b = DifficultyMode.NORMAL;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 0.0f;
        this.g = false;
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = 1;
        this.w.clear();
        this.x = 208;
        this.y = Game.getTimestampSeconds();
        this.z = FastRandom.getDistinguishableString(12, null);
        this.B = 0;
        this.C = false;
        this.A = null;
        String str = prefMap.get("gameStartGameVersion", null);
        if (str != null) {
            try {
                this.x = Integer.parseInt(str);
            } catch (Exception unused) {
                f2533a.e("failed to load gameStartGameVersion from " + str + ", using default", new Object[0]);
            }
        }
        String str2 = prefMap.get("gameStartTimestamp", null);
        if (str2 != null) {
            try {
                this.y = Integer.parseInt(str2);
            } catch (Exception unused2) {
                f2533a.e("failed to load gameStartTimestamp from " + str2 + ", using default", new Object[0]);
            }
        }
        String str3 = prefMap.get("gameStartHash", null);
        if (str3 == null || str3.length() < 4 || str3.length() > 16) {
            f2533a.e("failed to load gameStartHash from " + str3 + ", using default", new Object[0]);
        } else {
            this.z = str3;
        }
        this.c = prefMap.get("doubleGainEnabled", "false").equals("true");
        if (!Config.isHeadless()) {
            f2533a.i("Double gain " + (this.c ? "enabled" : "disabled") + " by preferences", new Object[0]);
        }
        this.d = prefMap.get("steamRewardReceived", "false").equals("true");
        if (!Config.isHeadless()) {
            f2533a.i("Steam reward " + (this.d ? "received" : "not received") + " according to preferences", new Object[0]);
        }
        try {
            int timestampSeconds = Game.getTimestampSeconds();
            this.k = Integer.parseInt(prefMap.get("tempDoubleGainStartDate", "0"));
            this.l = Integer.parseInt(prefMap.get("tempDoubleGainEndDate", "0"));
            if (timestampSeconds < this.k || timestampSeconds > this.l) {
                this.k = 0;
                this.l = 0;
            }
        } catch (Exception e) {
            f2533a.e("failed to load temp double gain", e);
        }
        this.e = prefMap.get("bonusGivenForRegByInvite", "false").equals("true");
        String str4 = prefMap.get("difficultyMode", null);
        if (str4 != null) {
            try {
                this.f2534b = DifficultyMode.valueOf(str4);
            } catch (Exception unused3) {
                this.f2534b = DifficultyMode.NORMAL;
            }
        }
        f2533a.i("difficulty mode: " + this.f2534b, new Object[0]);
        try {
            this.f = Float.parseFloat(prefMap.get("lootBoostTimeLeft", "0"));
        } catch (Exception e2) {
            f2533a.e("failed to load lootBoostTimeLeft", e2);
        }
        try {
            this.g = prefMap.get("midGameDgRewardGiven", "false").equals("true");
        } catch (Exception e3) {
            f2533a.e("failed to load midGameDgRewardGiven", e3);
        }
        try {
            this.h = prefMap.get("encounterBirdEncountered", "false").equals("true");
        } catch (Exception e4) {
            f2533a.e("failed to load encounterBirdEncountered", e4);
        }
        String str5 = prefMap.get("caseRandoms", null);
        if (str5 != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str5).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        this.m[CaseType.valueOf(next.getString("type")).ordinal()].setState(next.getLong("stateA"), next.getLong("stateB"));
                    } catch (Exception e5) {
                        f2533a.e("failed to load case random", e5);
                    }
                }
            } catch (Exception e6) {
                f2533a.e("failed to load case randoms", e6);
            }
        }
        String str6 = prefMap.get("otherItemsRandom", null);
        if (str6 != null) {
            try {
                try {
                    JsonValue parse = new JsonReader().parse(str6);
                    this.n.setState(parse.getLong("stateA"), parse.getLong("stateB"));
                } catch (Exception e7) {
                    f2533a.e("failed to load other items random", e7);
                }
            } catch (Exception e8) {
                f2533a.e("failed to load other items random", e8);
            }
        }
        String str7 = prefMap.get("luckyWheelOptions", null);
        if (str7 != null) {
            try {
                JsonValue parse2 = new JsonReader().parse(str7);
                Array<LuckyWheelOverlay.WheelOption> array = new Array<>(LuckyWheelOverlay.WheelOption.class);
                Iterator<JsonValue> iterator22 = parse2.iterator2();
                while (iterator22.hasNext()) {
                    array.add(LuckyWheelOverlay.WheelOption.fromJson(iterator22.next()));
                }
                this.o = array;
            } catch (Exception e9) {
                f2533a.e("failed to load luckyWheelOptions", e9);
            }
        }
        try {
            this.p = prefMap.get("luckyWheelSpinAvailable", "false").equals("true");
            this.q = prefMap.get("luckyWheelSpinInProgress", "false").equals("true");
            this.r = Integer.parseInt(prefMap.get("luckyWheelCurrentMultiplier", "1"));
            this.s = Float.parseFloat(prefMap.get("luckyWheelLastRotation", "0"));
            this.t = Float.parseFloat(prefMap.get("luckyWheelLastWeaponAngle", "0"));
            long j = -1;
            long j2 = -1;
            try {
                j = Long.parseLong(prefMap.get("luckyWheelWR1", "-1"));
                j2 = Long.parseLong(prefMap.get("luckyWheelWR2", "-1"));
            } catch (Exception unused4) {
                f2533a.e("failed to load lucky spin wheel seeds", new Object[0]);
            }
            if (j == -1 || j2 == -1) {
                j = FastRandom.random.nextInt();
                j2 = FastRandom.random.nextInt();
            }
            this.u.setState(j, j2);
            long j3 = -1;
            long j4 = -1;
            try {
                j3 = Long.parseLong(prefMap.get("luckyWheelSR1", "-1"));
                j4 = Long.parseLong(prefMap.get("luckyWheelSR2", "-1"));
            } catch (Exception unused5) {
                f2533a.e("failed to load lucky spin handle seeds", new Object[0]);
            }
            if (j3 == -1 || j4 == -1) {
                j3 = FastRandom.random.nextInt();
                j4 = FastRandom.random.nextInt();
            }
            this.v.setState(j3, j4);
        } catch (Exception e10) {
            f2533a.e("failed to load lucky wheel status", e10);
        }
        String str8 = prefMap.get("handledConditionalCompensations", null);
        if (str8 != null) {
            try {
                Iterator<JsonValue> iterator23 = new JsonReader().parse(str8).iterator2();
                while (iterator23.hasNext()) {
                    this.w.add(iterator23.next().asInt());
                }
            } catch (Exception e11) {
                f2533a.e("failed to load handledConditionalCompensations, marking all as completed", e11);
                for (ProgressManager.ConditionalCompensation conditionalCompensation : ProgressManager.CONDITIONAL_COMPENSATIONS) {
                    this.w.add(conditionalCompensation.id);
                }
            }
        }
        try {
            this.j = Integer.parseInt(prefMap.get("videosWatchedForLuckyShot", "0"));
            this.i = Integer.parseInt(prefMap.get("videosWatchedForDoubleGain", "0"));
        } catch (Exception e12) {
            f2533a.e("failed to load videos stats", e12);
        }
        try {
            this.B = Integer.parseInt(prefMap.get("playTimeUntilShopOffersUpdate", "0"));
        } catch (Exception e13) {
            f2533a.e("failed to load playTimeUntilShopOffersUpdate", e13);
        }
        try {
            this.C = Boolean.parseBoolean(prefMap.get("currentShopOffersAreAfterSkip", "false"));
        } catch (Exception e14) {
            f2533a.e("failed to load currentShopOffersAreAfterSkip", e14);
        }
        try {
            String str9 = prefMap.get("shopOffers", null);
            if (str9 != null) {
                JsonValue parse3 = new JsonReader().parse(str9);
                Array<ProgressManager.ShopOffer> array2 = new Array<>(true, 1, ProgressManager.ShopOffer.class);
                Iterator<JsonValue> iterator24 = parse3.iterator2();
                while (iterator24.hasNext()) {
                    array2.add(ProgressManager.ShopOffer.fromJson(iterator24.next()));
                }
                this.A = array2;
            }
        } catch (Exception e15) {
            f2533a.e("failed to load shopOffers", e15);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("difficultyMode", this.f2534b.name());
        if (this.c) {
            prefMap.set("doubleGainEnabled", "true");
        }
        if (this.d) {
            prefMap.set("steamRewardReceived", "true");
        }
        if (this.e) {
            prefMap.set("bonusGivenForRegByInvite", "true");
        }
        if (this.f > 0.0f) {
            prefMap.set("lootBoostTimeLeft", String.valueOf(this.f));
        }
        if (this.g) {
            prefMap.set("midGameDgRewardGiven", "true");
        }
        if (this.h) {
            prefMap.set("encounterBirdEncountered", "true");
        }
        if (this.i != 0) {
            prefMap.set("videosWatchedForDoubleGain", String.valueOf(this.i));
        }
        if (this.j != 0) {
            prefMap.set("videosWatchedForLuckyShot", String.valueOf(this.j));
        }
        if (this.k != 0) {
            prefMap.set("tempDoubleGainStartDate", String.valueOf(this.k));
        }
        if (this.l != 0) {
            prefMap.set("tempDoubleGainEndDate", String.valueOf(this.l));
        }
        prefMap.set("gameStartGameVersion", String.valueOf(this.x));
        prefMap.set("gameStartTimestamp", String.valueOf(this.y));
        prefMap.set("gameStartHash", this.z);
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeArrayStart();
        for (CaseType caseType : CaseType.values) {
            json.writeObjectStart();
            json.writeValue("type", caseType.name());
            json.writeValue("stateA", Long.valueOf(this.m[caseType.ordinal()].getState(0)));
            json.writeValue("stateB", Long.valueOf(this.m[caseType.ordinal()].getState(1)));
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        prefMap.set("caseRandoms", stringWriter.toString());
        Json json2 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeObjectStart();
        json2.writeValue("stateA", Long.valueOf(this.n.getState(0)));
        json2.writeValue("stateB", Long.valueOf(this.n.getState(1)));
        json2.writeObjectEnd();
        prefMap.set("otherItemsRandom", stringWriter2.toString());
        if (this.o != null) {
            Json json3 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter3 = new StringWriter();
            json3.setWriter(stringWriter3);
            json3.writeArrayStart();
            for (int i = 0; i < this.o.size; i++) {
                json3.writeObjectStart();
                this.o.items[i].toJson(json3);
                json3.writeObjectEnd();
            }
            json3.writeArrayEnd();
            prefMap.set("luckyWheelOptions", stringWriter3.toString());
        }
        if (this.p) {
            prefMap.set("luckyWheelSpinAvailable", "true");
        }
        if (this.q) {
            prefMap.set("luckyWheelSpinInProgress", "true");
        }
        prefMap.set("luckyWheelCurrentMultiplier", String.valueOf(this.r));
        prefMap.set("luckyWheelLastRotation", String.valueOf(this.s));
        prefMap.set("luckyWheelLastWeaponAngle", String.valueOf(this.t));
        prefMap.set("luckyWheelWR1", String.valueOf(this.u.getState(0)));
        prefMap.set("luckyWheelWR2", String.valueOf(this.u.getState(1)));
        prefMap.set("luckyWheelSR1", String.valueOf(this.v.getState(0)));
        prefMap.set("luckyWheelSR2", String.valueOf(this.v.getState(1)));
        if (this.w.size != 0) {
            Json json4 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter4 = new StringWriter();
            json4.setWriter(stringWriter4);
            json4.writeArrayStart();
            for (int i2 = 0; i2 < this.w.size; i2++) {
                json4.writeValue(Integer.valueOf(this.w.items[i2]));
            }
            json4.writeArrayEnd();
            prefMap.set("handledConditionalCompensations", stringWriter4.toString());
        }
        prefMap.set("playTimeUntilShopOffersUpdate", String.valueOf(this.B));
        if (this.C) {
            prefMap.set("currentShopOffersAreAfterSkip", "true");
        }
        if (this.A == null || this.A.size == 0) {
            prefMap.set("shopOffers", null);
            return;
        }
        Json json5 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter5 = new StringWriter();
        json5.setWriter(stringWriter5);
        json5.writeArrayStart();
        for (int i3 = 0; i3 < this.A.size; i3++) {
            if (this.A.get(i3) != null) {
                json5.writeObjectStart();
                this.A.get(i3).toJson(json5);
                json5.writeObjectEnd();
            }
        }
        json5.writeArrayEnd();
        prefMap.set("shopOffers", stringWriter5.toString());
    }
}

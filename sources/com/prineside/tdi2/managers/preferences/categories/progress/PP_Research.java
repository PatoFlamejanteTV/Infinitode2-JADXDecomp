package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Research.class */
public final class PP_Research implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2537a = TLog.forClass(PP_Research.class);

    /* renamed from: b, reason: collision with root package name */
    @Null
    private ResearchType f2538b;
    private long c;
    private final short[] d = new short[ResearchType.values.length];
    private final IntArray[] e = new IntArray[ResearchType.values.length];

    public final boolean isLevelInstalledForToken(ResearchType researchType, int i) {
        IntArray intArray = this.e[researchType.ordinal()];
        if (intArray == null) {
            return false;
        }
        return intArray.contains(i);
    }

    public final synchronized void addLevelInstalledForTokens(ResearchType researchType, int i) {
        if (this.e[researchType.ordinal()] == null) {
            this.e[researchType.ordinal()] = new IntArray();
        }
        if (!this.e[researchType.ordinal()].contains(i)) {
            this.e[researchType.ordinal()].add(i);
        }
    }

    public final synchronized void resetLevelsInstalledForTokens(ResearchType researchType) {
        this.e[researchType.ordinal()] = null;
    }

    public final synchronized void setInstalledLevel(ResearchType researchType, short s) {
        this.d[researchType.ordinal()] = s;
    }

    @Null
    public final ResearchType getCurrentlyResearching() {
        return this.f2538b;
    }

    public final synchronized void setCurrentlyResearching(@Null ResearchType researchType) {
        this.f2538b = researchType;
    }

    public final long getCurrentResearchEndTime() {
        return this.c;
    }

    public final synchronized void setCurrentResearchEndTime(long j) {
        this.c = j;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.f2538b = null;
        Arrays.fill(this.d, (short) 0);
        Arrays.fill(this.e, (Object) null);
        this.c = 0L;
        String str = prefMap.get("currentlyResearching", null);
        if (str != null) {
            try {
                if (str.length() != 0) {
                    this.f2538b = ResearchType.valueOf(str);
                    this.c = Long.parseLong(prefMap.get("currentResearchEndTime", "0"));
                    f2537a.i("currently researching: " + this.f2538b.name(), new Object[0]);
                }
            } catch (Exception e) {
                f2537a.e("failed to load current research", e);
            }
        }
        String str2 = prefMap.get("installedResearches", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str2).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        this.d[ResearchType.valueOf(next.name).ordinal()] = (short) next.asInt();
                    } catch (Exception e2) {
                        f2537a.i("!!! Saved installed research '" + next.name + "' not found and was ignored: " + e2.getMessage(), new Object[0]);
                    }
                }
            } catch (Exception e3) {
                f2537a.e("failed to parse json: " + str2, e3);
            }
        }
        String str3 = prefMap.get("installedResearchesForTokens", null);
        if (str3 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str3).iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next2 = iterator22.next();
                    try {
                        ResearchType valueOf = ResearchType.valueOf(next2.name);
                        Iterator<JsonValue> iterator23 = next2.iterator2();
                        while (iterator23.hasNext()) {
                            int asInt = iterator23.next().asInt();
                            if (getInstalledLevel(valueOf) >= asInt) {
                                if (this.e[valueOf.ordinal()] == null) {
                                    this.e[valueOf.ordinal()] = new IntArray();
                                }
                                this.e[valueOf.ordinal()].add(asInt);
                            } else {
                                f2537a.e("ilft > research.installedLevel " + next2.name + ", " + asInt + " > " + getInstalledLevel(valueOf), new Object[0]);
                            }
                        }
                    } catch (Exception e4) {
                        f2537a.e("Saved installed research levels for token '" + next2.name + "' not found and was ignored: " + e4.getMessage(), new Object[0]);
                    }
                }
            } catch (Exception e5) {
                f2537a.e("failed to parse json: " + str3, e5);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("currentlyResearching", this.f2538b == null ? null : this.f2538b.name());
        if (this.c != 0) {
            prefMap.set("currentResearchEndTime", String.valueOf(this.c));
        }
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        for (ResearchType researchType : ResearchType.values) {
            short s = this.d[researchType.ordinal()];
            if (s != 0) {
                json.writeValue(researchType.name(), Integer.valueOf(s));
            }
        }
        json.writeObjectEnd();
        prefMap.set("installedResearches", stringWriter.toString());
        Json json2 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeObjectStart();
        for (ResearchType researchType2 : ResearchType.values()) {
            IntArray intArray = this.e[researchType2.ordinal()];
            if (intArray != null && intArray.size != 0) {
                json2.writeArrayStart(researchType2.name());
                for (int i = 0; i < intArray.size; i++) {
                    json2.writeValue(Integer.valueOf(intArray.items[i]));
                }
                json2.writeArrayEnd();
            }
        }
        json2.writeObjectEnd();
        prefMap.set("installedResearchesForTokens", stringWriter2.toString());
    }

    public final int getInstalledLevel(ResearchType researchType) {
        return this.d[researchType.ordinal()];
    }
}

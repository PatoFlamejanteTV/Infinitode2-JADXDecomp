package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Achievement.class */
public final class PP_Achievement implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2516a = TLog.forClass(PP_Achievement.class);

    /* renamed from: b, reason: collision with root package name */
    private final int[] f2517b = new int[AchievementType.values.length];
    private final boolean[] c = new boolean[AchievementType.values.length];

    public final int getProgress(AchievementType achievementType) {
        return this.f2517b[achievementType.ordinal()];
    }

    public final boolean isRedeemed(AchievementType achievementType) {
        return this.c[achievementType.ordinal()];
    }

    public final synchronized void setRedeemed(AchievementType achievementType, boolean z) {
        this.c[achievementType.ordinal()] = z;
    }

    public final synchronized void setProgress(AchievementType achievementType, int i) {
        this.f2517b[achievementType.ordinal()] = i;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        Arrays.fill(this.f2517b, 0);
        Arrays.fill(this.c, false);
        String str = prefMap.get("achievements", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        AchievementType valueOf = AchievementType.valueOf(next.getString(0));
                        this.f2517b[valueOf.ordinal()] = next.getInt(1);
                        this.c[valueOf.ordinal()] = next.getInt(2) == 1;
                    } catch (Exception e) {
                        f2516a.e("failed to load achievement progress", e);
                    }
                }
            } catch (Exception e2) {
                f2516a.e("failed to parse json: " + str, e2);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeArrayStart();
        for (AchievementType achievementType : AchievementType.values) {
            if (this.f2517b[achievementType.ordinal()] != 0) {
                json.writeArrayStart();
                json.writeValue(achievementType.name());
                json.writeValue(Integer.valueOf(this.f2517b[achievementType.ordinal()]));
                json.writeValue(Integer.valueOf(this.c[achievementType.ordinal()] ? 1 : 0));
                json.writeArrayEnd();
            }
        }
        json.writeArrayEnd();
        prefMap.set("achievements", stringWriter.toString());
    }
}

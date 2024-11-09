package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Statistics.class */
public class PP_Statistics implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2541a = TLog.forClass(PP_Statistics.class);

    /* renamed from: b, reason: collision with root package name */
    private final double[] f2542b = new double[StatisticsType.values.length];
    private final double[] c = new double[StatisticsType.values.length];

    public double getAllTime(StatisticsType statisticsType) {
        return this.f2542b[statisticsType.ordinal()];
    }

    public double getMaxOneGame(StatisticsType statisticsType) {
        return this.c[statisticsType.ordinal()];
    }

    public synchronized void setMaxOneGame(StatisticsType statisticsType, double d) {
        this.c[statisticsType.ordinal()] = d;
    }

    public synchronized void addAllTime(StatisticsType statisticsType, double d) {
        double[] dArr = this.f2542b;
        int ordinal = statisticsType.ordinal();
        dArr[ordinal] = dArr[ordinal] + d;
    }

    public synchronized void setAllTime(StatisticsType statisticsType, double d) {
        this.f2542b[statisticsType.ordinal()] = d;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public void load(PrefMap prefMap) {
        Arrays.fill(this.f2542b, 0.0d);
        Arrays.fill(this.c, 0.0d);
        String str = prefMap.get("statsAllTime", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        StatisticsType valueOf = StatisticsType.valueOf(next.name);
                        this.f2542b[valueOf.ordinal()] = next.asDouble();
                        if (valueOf == StatisticsType.PRT && (this.f2542b[valueOf.ordinal()] < 0.0d || this.f2542b[valueOf.ordinal()] > 3.1536E7d)) {
                            f2541a.i("reset PRT statistics - invalid value " + this.f2542b[valueOf.ordinal()], new Object[0]);
                            this.f2542b[valueOf.ordinal()] = 0.0d;
                        }
                    } catch (Exception e) {
                        f2541a.e("skipping all time stat '" + next.name + "': " + e.getMessage(), new Object[0]);
                    }
                }
            } catch (Exception e2) {
                f2541a.e("failed to parse json: " + str, e2);
            }
        }
        String str2 = prefMap.get("statsMaxOneGame", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str2).iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next2 = iterator22.next();
                    try {
                        StatisticsType valueOf2 = StatisticsType.valueOf(next2.name);
                        this.c[valueOf2.ordinal()] = next2.asDouble();
                        if (valueOf2 == StatisticsType.PRT && (this.c[valueOf2.ordinal()] < 0.0d || this.f2542b[valueOf2.ordinal()] > 3.1536E7d)) {
                            f2541a.i("reset PRT mpg statistics - invalid value " + this.f2542b[valueOf2.ordinal()], new Object[0]);
                            this.c[valueOf2.ordinal()] = 0.0d;
                        }
                    } catch (Exception e3) {
                        f2541a.e("skipping max one game stat '" + next2.name + "': " + e3.getMessage(), new Object[0]);
                    }
                }
            } catch (Exception e4) {
                f2541a.e("failed to parse json: " + str, e4);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        for (StatisticsType statisticsType : StatisticsType.values) {
            if (this.f2542b[statisticsType.ordinal()] != 0.0d) {
                json.writeValue(statisticsType.name(), Double.valueOf(this.f2542b[statisticsType.ordinal()]));
            }
        }
        json.writeObjectEnd();
        prefMap.set("statsAllTime", stringWriter.toString());
        Json json2 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeObjectStart();
        for (StatisticsType statisticsType2 : StatisticsType.values) {
            if (this.c[statisticsType2.ordinal()] != 0.0d) {
                json2.writeValue(statisticsType2.name(), Double.valueOf(this.c[statisticsType2.ordinal()]));
            }
        }
        json2.writeObjectEnd();
        prefMap.set("statsMaxOneGame", stringWriter2.toString());
    }
}

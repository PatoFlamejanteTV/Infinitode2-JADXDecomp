package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_DailyQuest.class */
public final class PP_DailyQuest implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2525a = TLog.forClass(PP_DailyQuest.class);

    /* renamed from: b, reason: collision with root package name */
    private int f2526b;

    @Null
    private String c;
    private int d = -1;
    private int e = 0;
    private int f;

    @Null
    private String g;

    @Null
    private String h;

    @Null
    private String i;

    public final String getDailyLootCurrentDay() {
        return this.i;
    }

    public final synchronized void setDailyLootCurrentDay(String str) {
        this.i = str;
    }

    @Null
    public final String getDailyLootCurrentQuest() {
        return this.h;
    }

    public final synchronized void setDailyLootCurrentQuest(@Null String str) {
        this.h = str;
    }

    @Null
    public final String getDailyLootLastCompletedDay() {
        return this.g;
    }

    public final synchronized void setDailyLootLastCompletedDay(@Null String str) {
        this.g = str;
    }

    @Deprecated
    public final int getDailyLootDaysInRow() {
        return this.f;
    }

    @Deprecated
    public final synchronized void setDailyLootDaysInRow(int i) {
        this.f = i;
    }

    public final int getDailyLootCurrentDayIndex() {
        return this.e;
    }

    public final synchronized void setDailyLootCurrentDayIndex(int i) {
        this.e = i;
    }

    public final int getLastLoadedQuestId() {
        return this.d;
    }

    public final synchronized void setLastLoadedQuestId(int i) {
        this.d = i;
    }

    public final int getLastCompletedDailyQuestTimestamp() {
        return this.f2526b;
    }

    public final synchronized void setLastCompletedDailyQuestTimestamp(int i) {
        this.f2526b = i;
    }

    @Null
    public final String getLastLoadedQuestDate() {
        return this.c;
    }

    public final synchronized void setLastLoadedQuestDate(@Null String str) {
        this.c = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.prineside.tdi2.managers.preferences.categories.progress.PP_DailyQuest] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Exception] */
    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2526b = 0;
        this.c = null;
        this.d = -1;
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        ?? r0 = this;
        r0.i = null;
        try {
            String str = prefMap.get("lastCompletedDailyQuestTimestamp", null);
            if (str != null) {
                this.f2526b = Integer.parseInt(str);
                TLog tLog = f2525a;
                tLog.i("last completed daily quest timestamp: " + this.f2526b, new Object[0]);
                r0 = tLog;
            } else {
                TLog tLog2 = f2525a;
                tLog2.i("no last completed daily quest timestamp", new Object[0]);
                r0 = tLog2;
            }
        } catch (Exception e) {
            r0.printStackTrace();
        }
        try {
            this.c = prefMap.get("dailyQuestLastQuestDate", null);
            this.d = Integer.parseInt(prefMap.get("dailyQuestLastQuestId", "-1"));
            if (this.d <= 0 || this.c == null) {
                this.d = -1;
                this.c = null;
                f2525a.i("no last loaded daily quest", new Object[0]);
            } else {
                f2525a.i("last loaded daily quest id: " + this.d + " at " + this.c, new Object[0]);
            }
        } catch (Exception e2) {
            r0.printStackTrace();
        }
        try {
            this.e = Integer.parseInt(prefMap.get("dailyLootCurrentDayIndex", "0"));
            this.f = Integer.parseInt(prefMap.get("dailyLootDaysInRow", "0"));
            f2525a.i("dailyLootDaysInRow " + this.f, new Object[0]);
            this.g = prefMap.get("dailyLootLastCompletedDay", null);
            this.h = prefMap.get("dailyLootCurrentQuest", null);
            this.i = prefMap.get("dailyLootCurrentDay", null);
        } catch (Exception e3) {
            f2525a.e("failed to load daily loot config", e3);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("dailyLootCurrentDayIndex", new StringBuilder().append(this.e).toString());
        prefMap.set("dailyLootDaysInRow", new StringBuilder().append(this.f).toString());
        prefMap.set("dailyLootLastCompletedDay", this.g);
        prefMap.set("dailyLootCurrentQuest", this.h);
        prefMap.set("dailyLootCurrentDay", this.i);
        prefMap.set("dailyQuestLastQuestDate", this.c);
        prefMap.set("dailyQuestLastQuestId", String.valueOf(this.d));
        if (this.f2526b != 0) {
            prefMap.set("lastCompletedDailyQuestTimestamp", String.valueOf(this.f2526b));
        }
    }
}

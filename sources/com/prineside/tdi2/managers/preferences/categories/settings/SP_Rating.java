package com.prineside.tdi2.managers.preferences.categories.settings;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Rating.class */
public final class SP_Rating implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2548a = TLog.forClass(SP_Rating.class);
    public static final int NO_RESPONSE = 0;
    public static final int NEVER = 1;
    public static final int LATER = 2;
    public static final int REVIEWED = 3;
    public int lastResponse = 0;
    public long responseTimestamp;

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:34:0x0092
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(com.prineside.tdi2.managers.preferences.PrefMap r6) {
        /*
            Method dump skipped, instructions count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.preferences.categories.settings.SP_Rating.load(com.prineside.tdi2.managers.preferences.PrefMap):void");
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        prefMap.set("ratingLastResponse", String.valueOf(this.lastResponse));
        prefMap.set("ratingResponseTimestamp", String.valueOf(this.responseTimestamp));
        f2548a.i("save lastResponse " + this.lastResponse, new Object[0]);
        f2548a.i("save responseTimestamp " + this.responseTimestamp + " / " + Game.getTimestampMillis(), new Object[0]);
    }
}

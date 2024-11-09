package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResearchCategoryType;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ResearchCategory.class */
public class ResearchCategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1754a = TLog.forClass(ResearchCategory.class);
    public final ResearchCategoryType alias;
    public final String titleAlias;
    public final String descriptionAlias;

    /* renamed from: b, reason: collision with root package name */
    private final String f1755b;
    private Drawable c;

    public ResearchCategory(ResearchCategoryType researchCategoryType, String str, String str2, String str3) {
        this.alias = researchCategoryType;
        this.titleAlias = str;
        this.descriptionAlias = str2;
        this.f1755b = str3;
    }

    public String getIconString() {
        return this.f1755b;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0078 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0080 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.prineside.tdi2.ResearchCategory fromJson(com.a.a.b.l r7) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ResearchCategory.fromJson(com.a.a.b.l):com.prineside.tdi2.ResearchCategory");
    }

    public Drawable getIcon() {
        if (this.c == null && Game.i.assetManager != null) {
            if (this.f1755b.startsWith("@gv:")) {
                try {
                    this.c = Game.i.gameValueManager.getStockValueConfig(GameValueType.valueOf(this.f1755b.substring(4))).createIconForBackground(Color.BLACK);
                } catch (Exception e) {
                    f1754a.e("failed to read icon GV for " + this.alias, e);
                }
            } else {
                this.c = Game.i.assetManager.getDrawable(this.f1755b);
            }
        }
        return this.c;
    }

    public String getTitle() {
        return Game.i.localeManager.i18n.get(this.titleAlias);
    }

    public String getDescription() {
        return Game.i.localeManager.i18n.get(this.descriptionAlias);
    }
}

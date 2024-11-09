package com.prineside.tdi2.desktop.luadef;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.desktop.LuaDefinitionsGenerator;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocSite.class */
public class JavadocSite {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1848a = TLog.forClass(JavadocSite.class);
    public final String allClassesUrl;
    public final String rootUrl;
    public final Array<String> classUrls = new Array<>();

    public JavadocSite(String str) {
        this.allClassesUrl = str;
        String[] split = str.split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            stringBuilder.append(split[i]).append("/");
        }
        this.rootUrl = stringBuilder.toString();
        if (LuaDefinitionsGenerator.verbose) {
            f1848a.i("Javadoc: " + this.rootUrl, new Object[0]);
        }
    }

    public void getClassUrls() {
        this.classUrls.clear();
        Iterator<Element> it = Jsoup.connect(this.allClassesUrl).get().select(FlexmarkHtmlConverter.A_NODE).iterator();
        while (it.hasNext()) {
            String attr = it.next().attr("href");
            if (attr.contains("/") && !attr.contains("://") && !attr.contains("-") && !attr.contains("#") && attr.endsWith(".html")) {
                this.classUrls.add(attr);
            } else if (LuaDefinitionsGenerator.verbose) {
                f1848a.i("skip href: " + attr, new Object[0]);
            }
        }
        if (LuaDefinitionsGenerator.verbose) {
            f1848a.i("found " + this.classUrls.size + " class URLs", new Object[0]);
        }
    }

    @Null
    public Class<?> getClassFromClassUrl(String str) {
        if (!str.endsWith(".html")) {
            if (LuaDefinitionsGenerator.verbose) {
                f1848a.i("can't parse " + str + " which does not end with .html", new Object[0]);
                return null;
            }
            return null;
        }
        String replaceAll = str.substring(0, str.length() - 5).replaceAll("\\.", "\\$").replaceAll("/", "\\.");
        try {
            return Class.forName(replaceAll);
        } catch (Exception unused) {
            if (LuaDefinitionsGenerator.verbose) {
                f1848a.i("can't get runtime class from " + replaceAll + " - its javadoc will be skipped", new Object[0]);
                return null;
            }
            return null;
        }
    }
}

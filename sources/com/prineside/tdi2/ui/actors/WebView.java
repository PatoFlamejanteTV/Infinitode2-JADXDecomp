package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.net.HttpResponseHeader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.XmlReader;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.TextureRegionConfig;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/WebView.class */
public class WebView extends Table implements Disposable {
    private static final TLog k = TLog.forClass(WebView.class);
    private Net.HttpRequest l;
    private final ObjectMap<String, String> n = new ObjectMap<>();
    private Array<WebViewListener> o = new Array<>(WebViewListener.class);
    public int pageWidth;
    public int pageHeight;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/WebView$WebViewListener.class */
    public interface WebViewListener {
        void urlLoadStart(Net.HttpRequest httpRequest);

        void urlLoadFinish(boolean z, String str, boolean z2);

        void modalLoadRequested(String str);
    }

    static /* synthetic */ boolean b(WebView webView) {
        return false;
    }

    public WebView() {
        this.n.put("tdi2-build", "208");
    }

    public void copyCookies(WebView webView) {
        this.n.clear();
        this.n.putAll(webView.n);
    }

    public void addListener(WebViewListener webViewListener) {
        if (!this.o.contains(webViewListener, true)) {
            this.o.add(webViewListener);
        }
    }

    public void removeListener(WebViewListener webViewListener) {
        this.o.removeValue(webViewListener, true);
    }

    private static String a(Net.HttpRequest httpRequest, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpRequest.getUrl()).append(httpRequest.getMethod()).append(httpRequest.getContent()).append(str);
        return StringFormatter.md5Hash(stringBuilder.toString());
    }

    private static Color a(String str) {
        Color cpy = Color.WHITE.cpy();
        try {
            if (str.startsWith("#")) {
                if (str.length() == 9) {
                    Color.rgb888ToColor(cpy, Integer.parseInt(str.substring(1, 7), 16));
                    cpy.f889a = Integer.parseInt(str.substring(7), 16) / 255.0f;
                } else {
                    Color.rgb888ToColor(cpy, Integer.parseInt(str.substring(1), 16));
                }
            } else if (str.contains(":")) {
                String[] split = str.split(":");
                cpy.set(MaterialColor.allColors[MaterialColor.Colors.valueOf(split[0]).ordinal()][MaterialColor.Variants.valueOf(split[1]).ordinal()]);
            } else {
                cpy.set(MaterialColor.allColors[MaterialColor.Colors.valueOf(str).ordinal()][MaterialColor.Variants.P500.ordinal()]);
            }
        } catch (Exception e) {
            k.e("Failed to read color", e);
        }
        return cpy;
    }

    private static Color a(XmlReader.Element element) {
        String attribute = element.getAttribute("color", null);
        if (attribute != null) {
            return a(attribute);
        }
        return Color.WHITE.cpy();
    }

    private static String a(XmlReader.Element element, String str, String str2) {
        String attribute = element.getAttribute(str, str2);
        if (attribute.contains("&")) {
            return attribute.replaceAll("&amp;", "&").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
        }
        return attribute;
    }

    private static void a(Group group, XmlReader.Element element, String str) {
        float floatAttribute = element.getFloatAttribute("size", 0.0f);
        float floatAttribute2 = element.getFloatAttribute("width", floatAttribute);
        element.getFloatAttribute("height", floatAttribute);
        String[] split = str.split(":");
        try {
            String str2 = split[0];
            boolean z = -1;
            switch (str2.hashCode()) {
                case -841024722:
                    if (str2.equals("player-level-badge")) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    Array<TextureRegionConfig> profileLevelTextures = Game.i.authManager.getProfileLevelTextures(Integer.parseInt(split[1]));
                    for (int i = 0; i < profileLevelTextures.size; i++) {
                        group.addActor(profileLevelTextures.items[i].createImage(0.0f, 0.0f, floatAttribute2));
                    }
                    return;
                default:
                    k.e("div data not recognized (" + str + ")", new Object[0]);
                    return;
            }
        } catch (Exception e) {
            k.e("failed to generate div data", e);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x006a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:170:0x0d4a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:283:0x033e. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0db4 A[Catch: Exception -> 0x0dcf, TryCatch #15 {Exception -> 0x0dcf, blocks: (B:169:0x0d36, B:170:0x0d4a, B:171:0x0d6c, B:175:0x0d7c, B:178:0x0d8c, B:182:0x0d9b, B:183:0x0db4, B:184:0x0dbd, B:185:0x0dc6), top: B:168:0x0d36 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0dbd A[Catch: Exception -> 0x0dcf, TryCatch #15 {Exception -> 0x0dcf, blocks: (B:169:0x0d36, B:170:0x0d4a, B:171:0x0d6c, B:175:0x0d7c, B:178:0x0d8c, B:182:0x0d9b, B:183:0x0db4, B:184:0x0dbd, B:185:0x0dc6), top: B:168:0x0d36 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0dc6 A[Catch: Exception -> 0x0dcf, TryCatch #15 {Exception -> 0x0dcf, blocks: (B:169:0x0d36, B:170:0x0d4a, B:171:0x0d6c, B:175:0x0d7c, B:178:0x0d8c, B:182:0x0d9b, B:183:0x0db4, B:184:0x0dbd, B:185:0x0dc6), top: B:168:0x0d36 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0454 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x045e A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0467 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0471 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x047a A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0483 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x048d A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0497 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x04a1 A[Catch: Exception -> 0x04ab, TryCatch #5 {Exception -> 0x04ab, blocks: (B:282:0x032a, B:283:0x033e, B:284:0x0390, B:288:0x03a0, B:291:0x03b0, B:294:0x03c0, B:297:0x03d0, B:300:0x03e0, B:303:0x03f0, B:306:0x0401, B:309:0x0412, B:313:0x0422, B:314:0x0454, B:315:0x045e, B:316:0x0467, B:317:0x0471, B:318:0x047a, B:319:0x0483, B:320:0x048d, B:321:0x0497, B:322:0x04a1), top: B:281:0x032a }] */
    /* JADX WARN: Type inference failed for: r0v172, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v185, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v186, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v187, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v188, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v189, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v190, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v191, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v192, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v193, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v225, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v226, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v227, types: [com.prineside.tdi2.ui.actors.Label] */
    /* JADX WARN: Type inference failed for: r0v231, types: [com.prineside.tdi2.ui.actors.LimitedWidthLabel] */
    /* JADX WARN: Type inference failed for: r0v25, types: [com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.Actor] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.prineside.tdi2.scene2d.Group] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.prineside.tdi2.scene2d.Group r9, com.badlogic.gdx.utils.XmlReader.Element r10, com.prineside.tdi2.ui.actors.WebView.SharedStyle r11) {
        /*
            Method dump skipped, instructions count: 3634
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.actors.WebView.a(com.prineside.tdi2.scene2d.Group, com.badlogic.gdx.utils.XmlReader$Element, com.prineside.tdi2.ui.actors.WebView$SharedStyle):void");
    }

    public void stopCurrentRequest() {
        if (this.l != null) {
            Gdx.f881net.cancelHttpRequest(this.l);
            this.l = null;
        }
    }

    private static void a(Actor actor, ObjectMap<String, String> objectMap) {
        String str;
        if ((actor.getUserObject() instanceof ObjectMap) && (str = (String) ((ObjectMap) actor.getUserObject()).get("form-name")) != null) {
            if (actor instanceof TextField) {
                objectMap.put(str, ((TextField) actor).getText());
            }
            if (actor instanceof SelectBox) {
                objectMap.put(str, ((SelectOption) ((SelectBox) actor).getSelected()).value);
            }
        }
        if (actor instanceof Table) {
            Array.ArrayIterator<Cell> it = ((Table) actor).getCells().iterator();
            while (it.hasNext()) {
                Actor actor2 = it.next().getActor();
                if (actor2 != null) {
                    a(actor2, objectMap);
                }
            }
            return;
        }
        if (actor instanceof Group) {
            Array.ArrayIterator<Actor> it2 = ((Group) actor).getChildren().iterator();
            while (it2.hasNext()) {
                a(it2.next(), objectMap);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(String str) {
        k.i("submitForm " + str, new Object[0]);
        Actor findActor = findActor(str);
        if (findActor == null) {
            k.e("submitForm failed - no form with id '" + str + "'", new Object[0]);
            return;
        }
        if (!(findActor.getUserObject() instanceof ObjectMap)) {
            k.e("submitForm failed - form actor '" + str + "' has no ObjectMap with attributes", new Object[0]);
            return;
        }
        ObjectMap objectMap = (ObjectMap) findActor.getUserObject();
        String str2 = (String) objectMap.get("form-method", Net.HttpMethods.POST);
        String str3 = (String) objectMap.get("form-url", null);
        if (str3 == null) {
            k.e("submitForm failed - form-url not set", new Object[0]);
            return;
        }
        String str4 = (String) objectMap.get("form-target", "this");
        ObjectMap objectMap2 = new ObjectMap();
        a(findActor, (ObjectMap<String, String>) objectMap2);
        k.i("form method: " + str2, new Object[0]);
        k.i("form url: " + str3, new Object[0]);
        k.i("form target: " + str4, new Object[0]);
        k.i("form fields: " + objectMap2.toString(","), new Object[0]);
        if (str3.startsWith("xdx:") && !"modal".equals(str4)) {
            HashMap hashMap = new HashMap();
            ObjectMap.Entries it = objectMap2.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry next = it.next();
                hashMap.put((String) next.key, (String) next.value);
            }
            loadUrl(str2, str3.substring(4), hashMap);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadUrl(final String str, String str2, Map<String, String> map) {
        stopCurrentRequest();
        if (Config.isModdingMode()) {
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(str);
        if (str.equals(Net.HttpMethods.GET) && map != null && str2.contains(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
            int indexOf = str2.indexOf(63);
            String substring = str2.substring(indexOf + 1);
            str2 = str2.substring(0, indexOf);
            k.i("splitting get part: " + str2 + " | " + substring, new Object[0]);
            for (String str3 : substring.split("&")) {
                String[] split = str3.split("=");
                map.put(split[0], split[1]);
            }
        }
        if (map != null) {
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(map));
        }
        httpRequest.setUrl(str2);
        k.i("url: " + str2, new Object[0]);
        k.i("data: " + map, new Object[0]);
        k.i("method: " + str, new Object[0]);
        synchronized (this.n) {
            this.n.put("tdi2-locale", Game.i.localeManager.getLocale());
            this.n.put("tdi2-platform", Gdx.app.getType().name());
            this.n.put("tdi2-build", "208");
            if (Game.i.authManager.getSignInStatus() == AuthManager.SignInStatus.SIGNED_IN && Game.i.authManager.getSessionId() != null) {
                this.n.put("tdi2-session", Game.i.authManager.getSessionId());
            } else {
                this.n.remove("tdi2-session");
            }
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            ObjectMap.Entries<String, String> it = this.n.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry next = it.next();
                stringBuilder.append((String) next.key).append("=").append((String) next.value);
                i++;
                if (i != this.n.size) {
                    stringBuilder.append("; ");
                }
            }
            k.i("cookie: " + ((Object) stringBuilder), new Object[0]);
            ObjectMap.Entries<String, String> it2 = this.n.iterator();
            while (it2.hasNext()) {
                ObjectMap.Entry next2 = it2.next();
                k.i("- c: " + ((String) next2.key) + " = " + ((String) next2.value), new Object[0]);
            }
            httpRequest.setHeader(HttpRequestHeader.Cookie, stringBuilder.toString());
        }
        final String a2 = a(httpRequest, Game.i.localeManager.getLocale());
        for (int i2 = 0; i2 < this.o.size; i2++) {
            this.o.items[i2].urlLoadStart(httpRequest);
        }
        final String str4 = str2;
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.ui.actors.WebView.3
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                for (Map.Entry<String, List<String>> entry : httpResponse.getHeaders().entrySet()) {
                    if (entry.getKey() != null && entry.getKey().equals(HttpResponseHeader.SetCookie)) {
                        WebView.k.i("Set-Cookie received (" + entry.getValue().size() + " entries)", new Object[0]);
                        synchronized (WebView.this.n) {
                            for (String str5 : entry.getValue()) {
                                WebView.k.i("- " + str5, new Object[0]);
                                String[] split2 = str5.split("=");
                                String[] split3 = split2[1].split(";");
                                WebView.this.n.put(split2[0], split3[0]);
                                WebView.k.i("cookie set: " + split2[0] + SequenceUtils.SPACE + split3[0], new Object[0]);
                            }
                        }
                    }
                }
                if (WebView.b(WebView.this)) {
                    try {
                        Gdx.files.local("cache/web/" + a2 + ".xml").writeString(resultAsString, false, "UTF-8");
                    } catch (Exception unused) {
                    }
                }
                Threads.i().runOnMainThread(() -> {
                    WebView.this.loadPage(resultAsString);
                    WebView.this.l = null;
                    for (int i3 = 0; i3 < WebView.this.o.size; i3++) {
                        ((WebViewListener[]) WebView.this.o.items)[i3].urlLoadFinish(true, resultAsString, false);
                    }
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                WebView.k.e("failed: " + str4 + " (" + str + ")", th);
                WebView.this.l = null;
                Threads i3 = Threads.i();
                String str5 = a2;
                i3.runOnMainThread(() -> {
                    boolean z = false;
                    try {
                        FileHandle local = Gdx.files.local("cache/web/" + str5 + ".xml");
                        if (local.exists()) {
                            String readString = local.readString("UTF-8");
                            WebView.this.loadPage(readString);
                            z = true;
                            for (int i4 = 0; i4 < WebView.this.o.size; i4++) {
                                ((WebViewListener[]) WebView.this.o.items)[i4].urlLoadFinish(true, readString, true);
                            }
                        }
                    } catch (Exception unused) {
                    }
                    if (!z) {
                        for (int i5 = 0; i5 < WebView.this.o.size; i5++) {
                            ((WebViewListener[]) WebView.this.o.items)[i5].urlLoadFinish(false, null, false);
                        }
                    }
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                WebView.k.i("cancelled: " + str4 + " (" + str + ")", new Object[0]);
                WebView.this.l = null;
                Threads.i().runOnMainThread(() -> {
                    for (int i3 = 0; i3 < WebView.this.o.size; i3++) {
                        ((WebViewListener[]) WebView.this.o.items)[i3].urlLoadFinish(false, null, false);
                    }
                });
            }
        });
        this.l = httpRequest;
    }

    public void loadPage(String str) {
        Preconditions.checkNotNull(str, "xml is null");
        clear();
        if (Game.i.settingsManager.isInDebugDetailedMode()) {
            k.i(str, new Object[0]);
        }
        try {
            XmlReader.Element parse = new XmlReader().parse(str);
            this.pageWidth = 0;
            this.pageHeight = 0;
            if (parse.hasAttribute("width") && parse.hasAttribute("height")) {
                try {
                    this.pageWidth = Integer.parseInt(parse.getAttribute("width"));
                    this.pageHeight = Integer.parseInt(parse.getAttribute("height"));
                } catch (Exception e) {
                    k.e("failed to size of body", e);
                }
            }
            a(this, parse, new SharedStyle(this, (byte) 0));
            row();
            add().expand().fill().row();
        } catch (Exception e2) {
            k.e("failed to load page", e2);
            k.e(str, new Object[0]);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/WebView$SharedStyle.class */
    public class SharedStyle {
        public int fontSize;

        private SharedStyle() {
            this.fontSize = 24;
        }

        /* synthetic */ SharedStyle(WebView webView, byte b2) {
            this();
        }

        public SharedStyle cpy() {
            SharedStyle sharedStyle = new SharedStyle();
            sharedStyle.fontSize = this.fontSize;
            return sharedStyle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/WebView$SelectOption.class */
    public static class SelectOption {
        public String value;
        public String text;

        private SelectOption() {
        }

        /* synthetic */ SelectOption(byte b2) {
            this();
        }

        public String toString() {
            return this.text;
        }
    }
}

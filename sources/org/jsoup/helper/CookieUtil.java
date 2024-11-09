package org.jsoup.helper;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.internal.StringUtil;

/* loaded from: infinitode-2.jar:org/jsoup/helper/CookieUtil.class */
class CookieUtil {
    private static final Map<String, List<String>> EmptyRequestHeaders = Collections.unmodifiableMap(new HashMap());
    private static final String Sep = "; ";
    private static final String CookieName = "Cookie";
    private static final String Cookie2Name = "Cookie2";

    CookieUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyCookiesToRequest(HttpConnection.Request request, HttpURLConnection httpURLConnection) {
        AbstractSet abstractSet;
        AbstractSet requestCookieSet = requestCookieSet(request);
        AbstractSet abstractSet2 = null;
        for (Map.Entry<String, List<String>> entry : request.cookieManager().get(asUri(request.url), EmptyRequestHeaders).entrySet()) {
            List<String> value = entry.getValue();
            if (value != null && value.size() != 0) {
                String key = entry.getKey();
                if ("Cookie".equals(key)) {
                    abstractSet = requestCookieSet;
                } else if (Cookie2Name.equals(key)) {
                    AbstractSet hashSet = new HashSet();
                    abstractSet = hashSet;
                    abstractSet2 = hashSet;
                }
                abstractSet.addAll(value);
            }
        }
        if (requestCookieSet.size() > 0) {
            httpURLConnection.addRequestProperty("Cookie", StringUtil.join(requestCookieSet, Sep));
        }
        if (abstractSet2 != null && abstractSet2.size() > 0) {
            httpURLConnection.addRequestProperty(Cookie2Name, StringUtil.join(abstractSet2, Sep));
        }
    }

    private static LinkedHashSet<String> requestCookieSet(Connection.Request request) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (Map.Entry<String, String> entry : request.cookies().entrySet()) {
            linkedHashSet.add(entry.getKey() + "=" + entry.getValue());
        }
        return linkedHashSet;
    }

    static URI asUri(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            MalformedURLException malformedURLException = new MalformedURLException(e.getMessage());
            malformedURLException.initCause(e);
            throw malformedURLException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void storeCookies(HttpConnection.Request request, URL url, Map<String, List<String>> map) {
        request.cookieManager().put(asUri(url), map);
    }
}

package org.jsoup.helper;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.jsoup.Connection;
import org.jsoup.internal.StringUtil;

/* loaded from: infinitode-2.jar:org/jsoup/helper/UrlBuilder.class */
final class UrlBuilder {
    URL u;
    StringBuilder q;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !UrlBuilder.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UrlBuilder(URL url) {
        this.u = url;
        if (this.u.getQuery() != null) {
            this.q = StringUtil.borrowBuilder().append(this.u.getQuery());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final URL build() {
        try {
            StringBuilder append = StringUtil.borrowBuilder().append(new URI(this.u.getProtocol(), this.u.getUserInfo(), IDN.toASCII(decodePart(this.u.getHost())), this.u.getPort(), null, null, null).toASCIIString());
            appendToAscii(this.u.getPath(), false, append);
            if (this.q != null) {
                append.append('?');
                appendToAscii(StringUtil.releaseBuilder(this.q), true, append);
            }
            if (this.u.getRef() != null) {
                append.append('#');
                appendToAscii(this.u.getRef(), false, append);
            }
            this.u = new URL(StringUtil.releaseBuilder(append));
            return this.u;
        } catch (UnsupportedEncodingException | MalformedURLException | URISyntaxException e) {
            if ($assertionsDisabled || Validate.assertFail(e.toString())) {
                return this.u;
            }
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void appendKeyVal(Connection.KeyVal keyVal) {
        if (this.q == null) {
            this.q = StringUtil.borrowBuilder();
        } else {
            this.q.append('&');
        }
        this.q.append(URLEncoder.encode(keyVal.key(), DataUtil.UTF_8.name())).append('=').append(URLEncoder.encode(keyVal.value(), DataUtil.UTF_8.name()));
    }

    private static String decodePart(String str) {
        try {
            return URLDecoder.decode(str, DataUtil.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendToAscii(String str, boolean z, StringBuilder sb) {
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt == 32) {
                sb.append(z ? '+' : "%20");
            } else if (codePointAt > 127) {
                sb.append(URLEncoder.encode(new String(Character.toChars(codePointAt)), DataUtil.UTF_8.name()));
                if (Character.charCount(codePointAt) == 2) {
                    i++;
                }
            } else {
                sb.append((char) codePointAt);
            }
            i++;
        }
    }
}

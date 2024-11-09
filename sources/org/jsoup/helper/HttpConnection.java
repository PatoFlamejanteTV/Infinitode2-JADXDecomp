package org.jsoup.helper;

import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.net.HttpResponseHeader;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.UncheckedIOException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.internal.ControllableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

/* loaded from: infinitode-2.jar:org/jsoup/helper/HttpConnection.class */
public class HttpConnection implements Connection {
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    private static final String USER_AGENT = "User-Agent";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final int HTTP_TEMP_REDIR = 307;
    private static final String DefaultUploadType = "application/octet-stream";
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    private Request req;
    private Connection.Response res;

    public static Connection connect(String str) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(str);
        return httpConnection;
    }

    public static Connection connect(URL url) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(url);
        return httpConnection;
    }

    public HttpConnection() {
        this.req = new Request();
    }

    HttpConnection(Request request) {
        this.req = new Request(request);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String encodeMimeName(String str) {
        return str.replace("\"", "%22");
    }

    @Override // org.jsoup.Connection
    public Connection newRequest() {
        return new HttpConnection(this.req);
    }

    private HttpConnection(Request request, Response response) {
        this.req = request;
        this.res = response;
    }

    @Override // org.jsoup.Connection
    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection url(String str) {
        Validate.notEmptyParam(str, "url");
        try {
            this.req.url(new URL(str));
            return this;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format("The supplied URL, '%s', is malformed. Make sure it is an absolute URL, and starts with 'http://' or 'https://'. See https://jsoup.org/cookbook/extracting-data/working-with-urls", str), e);
        }
    }

    @Override // org.jsoup.Connection
    public Connection proxy(Proxy proxy) {
        this.req.proxy(proxy);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection proxy(String str, int i) {
        this.req.proxy(str, i);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection userAgent(String str) {
        Validate.notNullParam(str, "userAgent");
        this.req.header("User-Agent", str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection timeout(int i) {
        this.req.timeout(i);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection maxBodySize(int i) {
        this.req.maxBodySize(i);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection followRedirects(boolean z) {
        this.req.followRedirects(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection referrer(String str) {
        Validate.notNullParam(str, "referrer");
        this.req.header(HttpRequestHeader.Referer, str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection method(Connection.Method method) {
        this.req.method(method);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreHttpErrors(boolean z) {
        this.req.ignoreHttpErrors(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreContentType(boolean z) {
        this.req.ignoreContentType(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2) {
        this.req.data((Connection.KeyVal) KeyVal.create(str, str2));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.req.sslSocketFactory(sSLSocketFactory);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2, InputStream inputStream) {
        this.req.data((Connection.KeyVal) KeyVal.create(str, str2, inputStream));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2, InputStream inputStream, String str3) {
        this.req.data(KeyVal.create(str, str2, inputStream).contentType(str3));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(Map<String, String> map) {
        Validate.notNullParam(map, "data");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.data((Connection.KeyVal) KeyVal.create(entry.getKey(), entry.getValue()));
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String... strArr) {
        Validate.notNullParam(strArr, "keyvals");
        Validate.isTrue(strArr.length % 2 == 0, "Must supply an even number of key value pairs");
        for (int i = 0; i < strArr.length; i += 2) {
            String str = strArr[i];
            String str2 = strArr[i + 1];
            Validate.notEmpty(str, "Data key must not be empty");
            Validate.notNull(str2, "Data value must not be null");
            this.req.data((Connection.KeyVal) KeyVal.create(str, str2));
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(Collection<Connection.KeyVal> collection) {
        Validate.notNullParam(collection, "data");
        Iterator<Connection.KeyVal> it = collection.iterator();
        while (it.hasNext()) {
            this.req.data(it.next());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.KeyVal data(String str) {
        Validate.notEmptyParam(str, "key");
        for (Connection.KeyVal keyVal : request().data()) {
            if (keyVal.key().equals(str)) {
                return keyVal;
            }
        }
        return null;
    }

    @Override // org.jsoup.Connection
    public Connection requestBody(String str) {
        this.req.requestBody(str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection header(String str, String str2) {
        this.req.header(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection headers(Map<String, String> map) {
        Validate.notNullParam(map, "headers");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.header(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookie(String str, String str2) {
        this.req.cookie(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookies(Map<String, String> map) {
        Validate.notNullParam(map, "cookies");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.cookie(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookieStore(CookieStore cookieStore) {
        this.req.cookieManager = new CookieManager(cookieStore, null);
        return this;
    }

    @Override // org.jsoup.Connection
    public CookieStore cookieStore() {
        return this.req.cookieManager.getCookieStore();
    }

    @Override // org.jsoup.Connection
    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    @Override // org.jsoup.Connection
    public Document get() {
        this.req.method(Connection.Method.GET);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Document post() {
        this.req.method(Connection.Method.POST);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Connection.Response execute() {
        this.res = Response.execute(this.req);
        return this.res;
    }

    @Override // org.jsoup.Connection
    public Connection.Request request() {
        return this.req;
    }

    @Override // org.jsoup.Connection
    public Connection request(Connection.Request request) {
        this.req = (Request) request;
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.Response response() {
        if (this.res == null) {
            throw new IllegalArgumentException("You must execute the request before getting a response.");
        }
        return this.res;
    }

    @Override // org.jsoup.Connection
    public Connection response(Connection.Response response) {
        this.res = response;
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection postDataCharset(String str) {
        this.req.postDataCharset(str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection auth(RequestAuthenticator requestAuthenticator) {
        this.req.auth(requestAuthenticator);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/helper/HttpConnection$Base.class */
    public static abstract class Base<T extends Connection.Base<T>> implements Connection.Base<T> {
        private static final URL UnsetUrl;
        URL url;
        Connection.Method method;
        Map<String, List<String>> headers;
        Map<String, String> cookies;

        static {
            try {
                UnsetUrl = new URL("http://undefined/");
            } catch (MalformedURLException e) {
                throw new IllegalStateException(e);
            }
        }

        private Base() {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.headers = new LinkedHashMap();
            this.cookies = new LinkedHashMap();
        }

        private Base(Base<T> base) {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.url = base.url;
            this.method = base.method;
            this.headers = new LinkedHashMap();
            for (Map.Entry<String, List<String>> entry : base.headers.entrySet()) {
                this.headers.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            this.cookies = new LinkedHashMap();
            this.cookies.putAll(base.cookies);
        }

        @Override // org.jsoup.Connection.Base
        public URL url() {
            if (this.url == UnsetUrl) {
                throw new IllegalArgumentException("URL not set. Make sure to call #url(...) before executing the request.");
            }
            return this.url;
        }

        @Override // org.jsoup.Connection.Base
        public T url(URL url) {
            Validate.notNullParam(url, "url");
            this.url = new UrlBuilder(url).build();
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Connection.Method method() {
            return this.method;
        }

        @Override // org.jsoup.Connection.Base
        public T method(Connection.Method method) {
            Validate.notNullParam(method, "method");
            this.method = method;
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public String header(String str) {
            Validate.notNullParam(str, Attribute.NAME_ATTR);
            List<String> headersCaseInsensitive = getHeadersCaseInsensitive(str);
            if (headersCaseInsensitive.size() > 0) {
                return StringUtil.join(headersCaseInsensitive, ", ");
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public T addHeader(String str, String str2) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            String str3 = str2 == null ? "" : str2;
            List<String> headers = headers(str);
            List<String> list = headers;
            if (headers.isEmpty()) {
                list = new ArrayList();
                this.headers.put(str, list);
            }
            list.add(str3);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public List<String> headers(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            return getHeadersCaseInsensitive(str);
        }

        @Override // org.jsoup.Connection.Base
        public T header(String str, String str2) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            removeHeader(str);
            addHeader(str, str2);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeader(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            return !getHeadersCaseInsensitive(str).isEmpty();
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeaderWithValue(String str, String str2) {
            Validate.notEmpty(str);
            Validate.notEmpty(str2);
            Iterator<String> it = headers(str).iterator();
            while (it.hasNext()) {
                if (str2.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jsoup.Connection.Base
        public T removeHeader(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            Map.Entry<String, List<String>> scanHeaders = scanHeaders(str);
            if (scanHeaders != null) {
                this.headers.remove(scanHeaders.getKey());
            }
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> headers() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.headers.size());
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value.size() > 0) {
                    linkedHashMap.put(key, value.get(0));
                }
            }
            return linkedHashMap;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, List<String>> multiHeaders() {
            return this.headers;
        }

        private List<String> getHeadersCaseInsensitive(String str) {
            Validate.notNull(str);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (str.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return Collections.emptyList();
        }

        private Map.Entry<String, List<String>> scanHeaders(String str) {
            String lowerCase = Normalizer.lowerCase(str);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (Normalizer.lowerCase(entry.getKey()).equals(lowerCase)) {
                    return entry;
                }
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public String cookie(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            return this.cookies.get(str);
        }

        @Override // org.jsoup.Connection.Base
        public T cookie(String str, String str2) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            Validate.notNullParam(str2, "value");
            this.cookies.put(str, str2);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasCookie(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            return this.cookies.containsKey(str);
        }

        @Override // org.jsoup.Connection.Base
        public T removeCookie(String str) {
            Validate.notEmptyParam(str, Attribute.NAME_ATTR);
            this.cookies.remove(str);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> cookies() {
            return this.cookies;
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/helper/HttpConnection$Request.class */
    public static class Request extends Base<Connection.Request> implements Connection.Request {
        private Proxy proxy;
        private int timeoutMilliseconds;
        private int maxBodySizeBytes;
        private boolean followRedirects;
        private final Collection<Connection.KeyVal> data;
        private String body;
        private boolean ignoreHttpErrors;
        private boolean ignoreContentType;
        private Parser parser;
        private boolean parserDefined;
        private String postDataCharset;
        private SSLSocketFactory sslSocketFactory;
        private CookieManager cookieManager;
        private RequestAuthenticator authenticator;
        private volatile boolean executing;

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Base, org.jsoup.Connection$Request] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Request url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        static {
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        }

        Request() {
            super();
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.timeoutMilliseconds = 30000;
            this.maxBodySizeBytes = 2097152;
            this.followRedirects = true;
            this.data = new ArrayList();
            this.method = Connection.Method.GET;
            addHeader(HttpRequestHeader.AcceptEncoding, "gzip");
            addHeader("User-Agent", HttpConnection.DEFAULT_UA);
            this.parser = Parser.htmlParser();
            this.cookieManager = new CookieManager();
        }

        Request(Request request) {
            super(request);
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.proxy = request.proxy;
            this.postDataCharset = request.postDataCharset;
            this.timeoutMilliseconds = request.timeoutMilliseconds;
            this.maxBodySizeBytes = request.maxBodySizeBytes;
            this.followRedirects = request.followRedirects;
            this.data = new ArrayList();
            this.ignoreHttpErrors = request.ignoreHttpErrors;
            this.ignoreContentType = request.ignoreContentType;
            this.parser = request.parser.newInstance();
            this.parserDefined = request.parserDefined;
            this.sslSocketFactory = request.sslSocketFactory;
            this.cookieManager = request.cookieManager;
            this.authenticator = request.authenticator;
            this.executing = false;
        }

        @Override // org.jsoup.Connection.Request
        public Proxy proxy() {
            return this.proxy;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(String str, int i) {
            this.proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i));
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public int timeout() {
            return this.timeoutMilliseconds;
        }

        @Override // org.jsoup.Connection.Request
        public Request timeout(int i) {
            Validate.isTrue(i >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.timeoutMilliseconds = i;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public int maxBodySize() {
            return this.maxBodySizeBytes;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request maxBodySize(int i) {
            Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
            this.maxBodySizeBytes = i;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean followRedirects() {
            return this.followRedirects;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreHttpErrors() {
            return this.ignoreHttpErrors;
        }

        @Override // org.jsoup.Connection.Request
        public SSLSocketFactory sslSocketFactory() {
            return this.sslSocketFactory;
        }

        @Override // org.jsoup.Connection.Request
        public void sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreHttpErrors(boolean z) {
            this.ignoreHttpErrors = z;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreContentType() {
            return this.ignoreContentType;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreContentType(boolean z) {
            this.ignoreContentType = z;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Request data(Connection.KeyVal keyVal) {
            Validate.notNullParam(keyVal, "keyval");
            this.data.add(keyVal);
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Collection<Connection.KeyVal> data() {
            return this.data;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request requestBody(String str) {
            this.body = str;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String requestBody() {
            return this.body;
        }

        @Override // org.jsoup.Connection.Request
        public Request parser(Parser parser) {
            this.parser = parser;
            this.parserDefined = true;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Parser parser() {
            return this.parser;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request postDataCharset(String str) {
            Validate.notNullParam(str, "charset");
            if (!Charset.isSupported(str)) {
                throw new IllegalCharsetNameException(str);
            }
            this.postDataCharset = str;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String postDataCharset() {
            return this.postDataCharset;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CookieManager cookieManager() {
            return this.cookieManager;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request auth(RequestAuthenticator requestAuthenticator) {
            this.authenticator = requestAuthenticator;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public RequestAuthenticator auth() {
            return this.authenticator;
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/helper/HttpConnection$Response.class */
    public static class Response extends Base<Connection.Response> implements Connection.Response {
        private static final int MAX_REDIRECTS = 20;
        private static final String LOCATION = "Location";
        private final int statusCode;
        private final String statusMessage;
        private ByteBuffer byteData;
        private ControllableInputStream bodyStream;
        private HttpURLConnection conn;
        private String charset;
        private final String contentType;
        private boolean executed;
        private boolean inputStreamRead;
        private int numRedirects;
        private final Request req;
        private static final Pattern xmlContentTypeRxp = Pattern.compile("(\\w+)/\\w*\\+?xml.*");

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [org.jsoup.Connection$Response, org.jsoup.Connection$Base] */
        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Response url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        Response() {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.statusCode = 400;
            this.statusMessage = "Request not made";
            this.req = new Request();
            this.contentType = null;
        }

        static Response execute(Request request) {
            return execute(request, null);
        }

        static Response execute(Request request, Response response) {
            synchronized (request) {
                Validate.isFalse(request.executing, "Multiple threads were detected trying to execute the same request concurrently. Make sure to use Connection#newRequest() and do not share an executing request between threads.");
                request.executing = true;
            }
            Validate.notNullParam(request, "req");
            URL url = request.url();
            Validate.notNull(url, "URL must be specified to connect");
            String protocol = url.getProtocol();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                throw new MalformedURLException("Only http & https protocols supported");
            }
            boolean hasBody = request.method().hasBody();
            boolean z = request.requestBody() != null;
            if (!hasBody) {
                Validate.isFalse(z, "Cannot set a request body for HTTP method " + request.method());
            }
            String str = null;
            if (request.data().size() > 0 && (!hasBody || z)) {
                serialiseRequestUrl(request);
            } else if (hasBody) {
                str = setOutputContentType(request);
            }
            long nanoTime = System.nanoTime();
            HttpURLConnection createConnection = createConnection(request);
            Response response2 = null;
            try {
                try {
                    createConnection.connect();
                    if (createConnection.getDoOutput()) {
                        OutputStream outputStream = createConnection.getOutputStream();
                        try {
                            try {
                                writePost(request, outputStream, str);
                                outputStream.close();
                            } catch (Throwable th) {
                                outputStream.close();
                                throw th;
                            }
                        } catch (IOException e) {
                            createConnection.disconnect();
                            throw e;
                        }
                    }
                    int responseCode = createConnection.getResponseCode();
                    Response response3 = new Response(createConnection, request, response);
                    if (response3.hasHeader("Location") && request.followRedirects()) {
                        if (responseCode != 307) {
                            request.method(Connection.Method.GET);
                            request.data().clear();
                            request.requestBody(null);
                            request.removeHeader("Content-Type");
                        }
                        String header = response3.header("Location");
                        String str2 = header;
                        Validate.notNull(header);
                        if (str2.startsWith("http:/") && str2.charAt(6) != '/') {
                            str2 = str2.substring(6);
                        }
                        request.url(StringUtil.resolve(request.url(), str2));
                        request.executing = false;
                        Response execute = execute(request, response3);
                        request.executing = false;
                        if (request.authenticator != null) {
                            AuthenticationHandler.handler.remove();
                        }
                        return execute;
                    }
                    if ((responseCode < 200 || responseCode >= 400) && !request.ignoreHttpErrors()) {
                        throw new HttpStatusException("HTTP error fetching URL", responseCode, request.url().toString());
                    }
                    String contentType = response3.contentType();
                    if (contentType != null && !request.ignoreContentType() && !contentType.startsWith("text/") && !xmlContentTypeRxp.matcher(contentType).matches()) {
                        throw new UnsupportedMimeTypeException("Unhandled content type. Must be text/*, */xml, or */*+xml", contentType, request.url().toString());
                    }
                    if (contentType != null && xmlContentTypeRxp.matcher(contentType).matches() && !request.parserDefined) {
                        request.parser(Parser.xmlParser());
                    }
                    response3.charset = DataUtil.getCharsetFromContentType(response3.contentType);
                    if (createConnection.getContentLength() != 0 && request.method() != Connection.Method.HEAD) {
                        InputStream errorStream = createConnection.getErrorStream() != null ? createConnection.getErrorStream() : createConnection.getInputStream();
                        if (response3.hasHeaderWithValue("Content-Encoding", "gzip")) {
                            errorStream = new GZIPInputStream(errorStream);
                        } else if (response3.hasHeaderWithValue("Content-Encoding", "deflate")) {
                            errorStream = new InflaterInputStream(errorStream, new Inflater(true));
                        }
                        response3.bodyStream = ControllableInputStream.wrap(errorStream, 32768, request.maxBodySize()).timeout(nanoTime, request.timeout());
                    } else {
                        response3.byteData = DataUtil.emptyByteBuffer();
                    }
                    response3.executed = true;
                    return response3;
                } catch (IOException e2) {
                    if (0 != 0) {
                        response2.safeClose();
                    }
                    throw e2;
                }
            } finally {
                request.executing = false;
                if (request.authenticator != null) {
                    AuthenticationHandler.handler.remove();
                }
            }
        }

        @Override // org.jsoup.Connection.Response
        public int statusCode() {
            return this.statusCode;
        }

        @Override // org.jsoup.Connection.Response
        public String statusMessage() {
            return this.statusMessage;
        }

        @Override // org.jsoup.Connection.Response
        public String charset() {
            return this.charset;
        }

        @Override // org.jsoup.Connection.Response
        public Response charset(String str) {
            this.charset = str;
            return this;
        }

        @Override // org.jsoup.Connection.Response
        public String contentType() {
            return this.contentType;
        }

        @Override // org.jsoup.Connection.Response
        public Document parse() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            InputStream inputStream = this.bodyStream;
            if (this.byteData != null) {
                inputStream = new ByteArrayInputStream(this.byteData.array());
                this.inputStreamRead = false;
            }
            Validate.isFalse(this.inputStreamRead, "Input stream already read and parsed, cannot re-read.");
            Document parseInputStream = DataUtil.parseInputStream(inputStream, this.charset, this.url.toExternalForm(), this.req.parser());
            parseInputStream.connection(new HttpConnection(this.req, this));
            this.charset = parseInputStream.outputSettings().charset().name();
            this.inputStreamRead = true;
            safeClose();
            return parseInputStream;
        }

        private void prepareByteData() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.bodyStream != null && this.byteData == null) {
                Validate.isFalse(this.inputStreamRead, "Request has already been read (with .parse())");
                try {
                    try {
                        this.byteData = DataUtil.readToByteBuffer(this.bodyStream, this.req.maxBodySize());
                        this.inputStreamRead = true;
                        safeClose();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                } catch (Throwable th) {
                    this.inputStreamRead = true;
                    safeClose();
                    throw th;
                }
            }
        }

        @Override // org.jsoup.Connection.Response
        public String body() {
            prepareByteData();
            Validate.notNull(this.byteData);
            String charBuffer = (this.charset == null ? DataUtil.UTF_8 : Charset.forName(this.charset)).decode(this.byteData).toString();
            this.byteData.rewind();
            return charBuffer;
        }

        @Override // org.jsoup.Connection.Response
        public byte[] bodyAsBytes() {
            prepareByteData();
            Validate.notNull(this.byteData);
            return this.byteData.array();
        }

        @Override // org.jsoup.Connection.Response
        public Connection.Response bufferUp() {
            prepareByteData();
            return this;
        }

        @Override // org.jsoup.Connection.Response
        public BufferedInputStream bodyStream() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.byteData != null) {
                return new BufferedInputStream(new ByteArrayInputStream(this.byteData.array()), 32768);
            }
            Validate.isFalse(this.inputStreamRead, "Request has already been read");
            Validate.notNull(this.bodyStream);
            this.inputStreamRead = true;
            return this.bodyStream.inputStream();
        }

        private static HttpURLConnection createConnection(Request request) {
            URLConnection openConnection;
            Proxy proxy = request.proxy();
            if (proxy != null) {
                openConnection = request.url().openConnection(proxy);
            } else {
                openConnection = request.url().openConnection();
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestMethod(request.method().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(request.timeout());
            httpURLConnection.setReadTimeout(request.timeout() / 2);
            if (request.sslSocketFactory() != null && (httpURLConnection instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(request.sslSocketFactory());
            }
            if (request.authenticator != null) {
                AuthenticationHandler.handler.enable(request.authenticator, httpURLConnection);
            }
            if (request.method().hasBody()) {
                httpURLConnection.setDoOutput(true);
            }
            CookieUtil.applyCookiesToRequest(request, httpURLConnection);
            for (Map.Entry entry : request.multiHeaders().entrySet()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    httpURLConnection.addRequestProperty((String) entry.getKey(), (String) it.next());
                }
            }
            return httpURLConnection;
        }

        private void safeClose() {
            if (this.bodyStream != null) {
                try {
                    this.bodyStream.close();
                } catch (IOException unused) {
                } finally {
                    this.bodyStream = null;
                }
            }
            if (this.conn != null) {
                this.conn.disconnect();
                this.conn = null;
            }
        }

        private Response(HttpURLConnection httpURLConnection, Request request, Response response) {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.conn = httpURLConnection;
            this.req = request;
            this.method = Connection.Method.valueOf(httpURLConnection.getRequestMethod());
            this.url = httpURLConnection.getURL();
            this.statusCode = httpURLConnection.getResponseCode();
            this.statusMessage = httpURLConnection.getResponseMessage();
            this.contentType = httpURLConnection.getContentType();
            LinkedHashMap<String, List<String>> createHeaderMap = createHeaderMap(httpURLConnection);
            processResponseHeaders(createHeaderMap);
            CookieUtil.storeCookies(this.req, this.url, createHeaderMap);
            if (response != null) {
                for (Map.Entry entry : response.cookies().entrySet()) {
                    if (!hasCookie((String) entry.getKey())) {
                        cookie((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                response.safeClose();
                this.numRedirects = response.numRedirects + 1;
                if (this.numRedirects >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", response.url()));
                }
            }
        }

        private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection httpURLConnection) {
            LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
            int i = 0;
            while (true) {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                String headerField = httpURLConnection.getHeaderField(i);
                if (headerFieldKey != null || headerField != null) {
                    i++;
                    if (headerFieldKey != null && headerField != null) {
                        if (linkedHashMap.containsKey(headerFieldKey)) {
                            linkedHashMap.get(headerFieldKey).add(headerField);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(headerField);
                            linkedHashMap.put(headerFieldKey, arrayList);
                        }
                    }
                } else {
                    return linkedHashMap;
                }
            }
        }

        void processResponseHeaders(Map<String, List<String>> map) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null) {
                    List<String> value = entry.getValue();
                    if (key.equalsIgnoreCase(HttpResponseHeader.SetCookie)) {
                        for (String str : value) {
                            if (str != null) {
                                TokenQueue tokenQueue = new TokenQueue(str);
                                String trim = tokenQueue.chompTo("=").trim();
                                String trim2 = tokenQueue.consumeTo(";").trim();
                                if (trim.length() > 0 && !this.cookies.containsKey(trim)) {
                                    cookie(trim, trim2);
                                }
                            }
                        }
                    }
                    Iterator<String> it = value.iterator();
                    while (it.hasNext()) {
                        addHeader(key, fixHeaderEncoding(it.next()));
                    }
                }
            }
        }

        private static String fixHeaderEncoding(String str) {
            if (str == null) {
                return str;
            }
            byte[] bytes = str.getBytes(HttpConnection.ISO_8859_1);
            if (looksLikeUtf8(bytes)) {
                return new String(bytes, DataUtil.UTF_8);
            }
            return str;
        }

        private static boolean looksLikeUtf8(byte[] bArr) {
            int i;
            int i2 = 0;
            if (bArr.length >= 3 && (bArr[0] & 255) == 239 && (bArr[1] & 255) == 187 && (bArr[2] & 255) == 191) {
                i2 = 3;
            }
            boolean z = false;
            int length = bArr.length;
            while (i2 < length) {
                byte b2 = bArr[i2];
                if ((b2 & 128) != 0) {
                    z = true;
                    if ((b2 & 224) == 192) {
                        i = i2 + 1;
                    } else if ((b2 & 240) == 224) {
                        i = i2 + 2;
                    } else if ((b2 & 248) == 240) {
                        i = i2 + 3;
                    } else {
                        return false;
                    }
                    if (i >= bArr.length) {
                        return false;
                    }
                    while (i2 < i) {
                        i2++;
                        if ((bArr[i2] & 192) != 128) {
                            return false;
                        }
                    }
                }
                i2++;
            }
            return z;
        }

        private static String setOutputContentType(Connection.Request request) {
            String header = request.header("Content-Type");
            String str = null;
            if (header == null) {
                if (HttpConnection.needsMultipart(request)) {
                    str = DataUtil.mimeBoundary();
                    request.header("Content-Type", "multipart/form-data; boundary=" + str);
                } else {
                    request.header("Content-Type", "application/x-www-form-urlencoded; charset=" + request.postDataCharset());
                }
            } else if (header.contains(HttpConnection.MULTIPART_FORM_DATA) && !header.contains("boundary")) {
                str = DataUtil.mimeBoundary();
                request.header("Content-Type", "multipart/form-data; boundary=" + str);
            }
            return str;
        }

        private static void writePost(Connection.Request request, OutputStream outputStream, String str) {
            Collection<Connection.KeyVal> data = request.data();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName(request.postDataCharset())));
            if (str != null) {
                for (Connection.KeyVal keyVal : data) {
                    bufferedWriter.write("--");
                    bufferedWriter.write(str);
                    bufferedWriter.write("\r\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"");
                    bufferedWriter.write(HttpConnection.encodeMimeName(keyVal.key()));
                    bufferedWriter.write("\"");
                    InputStream inputStream = keyVal.inputStream();
                    if (inputStream != null) {
                        bufferedWriter.write("; filename=\"");
                        bufferedWriter.write(HttpConnection.encodeMimeName(keyVal.value()));
                        bufferedWriter.write("\"\r\nContent-Type: ");
                        String contentType = keyVal.contentType();
                        bufferedWriter.write(contentType != null ? contentType : HttpConnection.DefaultUploadType);
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.flush();
                        DataUtil.crossStreams(inputStream, outputStream);
                        outputStream.flush();
                    } else {
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.write(keyVal.value());
                    }
                    bufferedWriter.write("\r\n");
                }
                bufferedWriter.write("--");
                bufferedWriter.write(str);
                bufferedWriter.write("--");
            } else {
                String requestBody = request.requestBody();
                if (requestBody != null) {
                    bufferedWriter.write(requestBody);
                } else {
                    boolean z = true;
                    for (Connection.KeyVal keyVal2 : data) {
                        if (!z) {
                            bufferedWriter.append('&');
                        } else {
                            z = false;
                        }
                        bufferedWriter.write(URLEncoder.encode(keyVal2.key(), request.postDataCharset()));
                        bufferedWriter.write(61);
                        bufferedWriter.write(URLEncoder.encode(keyVal2.value(), request.postDataCharset()));
                    }
                }
            }
            bufferedWriter.close();
        }

        private static void serialiseRequestUrl(Connection.Request request) {
            UrlBuilder urlBuilder = new UrlBuilder(request.url());
            for (Connection.KeyVal keyVal : request.data()) {
                Validate.isFalse(keyVal.hasInputStream(), "InputStream data not supported in URL query string.");
                urlBuilder.appendKeyVal(keyVal);
            }
            request.url(urlBuilder.build());
            request.data().clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean needsMultipart(Connection.Request request) {
        Iterator<Connection.KeyVal> it = request.data().iterator();
        while (it.hasNext()) {
            if (it.next().hasInputStream()) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:org/jsoup/helper/HttpConnection$KeyVal.class */
    public static class KeyVal implements Connection.KeyVal {
        private String key;
        private String value;
        private InputStream stream;
        private String contentType;

        public static KeyVal create(String str, String str2) {
            return new KeyVal(str, str2);
        }

        public static KeyVal create(String str, String str2, InputStream inputStream) {
            return new KeyVal(str, str2).inputStream(inputStream);
        }

        private KeyVal(String str, String str2) {
            Validate.notEmptyParam(str, "key");
            Validate.notNullParam(str2, "value");
            this.key = str;
            this.value = str2;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal key(String str) {
            Validate.notEmptyParam(str, "key");
            this.key = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String key() {
            return this.key;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal value(String str) {
            Validate.notNullParam(str, "value");
            this.value = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String value() {
            return this.value;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal inputStream(InputStream inputStream) {
            Validate.notNullParam(this.value, "inputStream");
            this.stream = inputStream;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public InputStream inputStream() {
            return this.stream;
        }

        @Override // org.jsoup.Connection.KeyVal
        public boolean hasInputStream() {
            return this.stream != null;
        }

        @Override // org.jsoup.Connection.KeyVal
        public Connection.KeyVal contentType(String str) {
            Validate.notEmpty(str);
            this.contentType = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String contentType() {
            return this.contentType;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}

package com.badlogic.gdx;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Net.class */
public interface Net {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Net$HttpMethods.class */
    public interface HttpMethods {
        public static final String HEAD = "HEAD";
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
        public static final String DELETE = "DELETE";
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Net$HttpResponse.class */
    public interface HttpResponse {
        byte[] getResult();

        String getResultAsString();

        InputStream getResultAsStream();

        HttpStatus getStatus();

        String getHeader(String str);

        Map<String, List<String>> getHeaders();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Net$HttpResponseListener.class */
    public interface HttpResponseListener {
        void handleHttpResponse(HttpResponse httpResponse);

        void failed(Throwable th);

        void cancelled();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Net$Protocol.class */
    public enum Protocol {
        TCP
    }

    void sendHttpRequest(HttpRequest httpRequest, @Null HttpResponseListener httpResponseListener);

    void cancelHttpRequest(HttpRequest httpRequest);

    boolean isHttpRequestPending(HttpRequest httpRequest);

    ServerSocket newServerSocket(Protocol protocol, String str, int i, ServerSocketHints serverSocketHints);

    ServerSocket newServerSocket(Protocol protocol, int i, ServerSocketHints serverSocketHints);

    Socket newClientSocket(Protocol protocol, String str, int i, SocketHints socketHints);

    boolean openURI(String str);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/Net$HttpRequest.class */
    public static class HttpRequest implements Pool.Poolable {
        private String httpMethod;
        private String url;
        private Map<String, String> headers;
        private int timeOut;
        private String content;
        private InputStream contentStream;
        private long contentLength;
        private boolean followRedirects;
        private boolean includeCredentials;

        public HttpRequest() {
            this.timeOut = 0;
            this.followRedirects = true;
            this.includeCredentials = false;
            this.headers = new HashMap();
        }

        public HttpRequest(String str) {
            this();
            this.httpMethod = str;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setHeader(String str, String str2) {
            this.headers.put(str, str2);
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setContent(InputStream inputStream, long j) {
            this.contentStream = inputStream;
            this.contentLength = j;
        }

        public void setTimeOut(int i) {
            this.timeOut = i;
        }

        public void setFollowRedirects(boolean z) {
            if (z || Gdx.app.getType() != Application.ApplicationType.WebGL) {
                this.followRedirects = z;
                return;
            }
            throw new IllegalArgumentException("Following redirects can't be disabled using the GWT/WebGL backend!");
        }

        public void setIncludeCredentials(boolean z) {
            this.includeCredentials = z;
        }

        public void setMethod(String str) {
            this.httpMethod = str;
        }

        public int getTimeOut() {
            return this.timeOut;
        }

        public String getMethod() {
            return this.httpMethod;
        }

        public String getUrl() {
            return this.url;
        }

        public String getContent() {
            return this.content;
        }

        public InputStream getContentStream() {
            return this.contentStream;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public Map<String, String> getHeaders() {
            return this.headers;
        }

        public boolean getFollowRedirects() {
            return this.followRedirects;
        }

        public boolean getIncludeCredentials() {
            return this.includeCredentials;
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.httpMethod = null;
            this.url = null;
            this.headers.clear();
            this.timeOut = 0;
            this.content = null;
            this.contentStream = null;
            this.contentLength = 0L;
            this.followRedirects = true;
        }
    }
}

package com.badlogic.gdx.net;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/net/NetJavaImpl.class */
public class NetJavaImpl {
    private final ThreadPoolExecutor executorService;
    final ObjectMap<Net.HttpRequest, HttpURLConnection> connections;
    final ObjectMap<Net.HttpRequest, Net.HttpResponseListener> listeners;
    final ObjectMap<Net.HttpRequest, Future<?>> tasks;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/net/NetJavaImpl$HttpClientResponse.class */
    static class HttpClientResponse implements Net.HttpResponse {
        private final HttpURLConnection connection;
        private HttpStatus status;

        public HttpClientResponse(HttpURLConnection httpURLConnection) {
            this.connection = httpURLConnection;
            try {
                this.status = new HttpStatus(httpURLConnection.getResponseCode());
            } catch (IOException unused) {
                this.status = new HttpStatus(-1);
            }
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public byte[] getResult() {
            InputStream inputStream = getInputStream();
            try {
                if (inputStream == null) {
                    return StreamUtils.EMPTY_BYTES;
                }
                return StreamUtils.copyStreamToByteArray(inputStream, this.connection.getContentLength());
            } catch (IOException unused) {
                return StreamUtils.EMPTY_BYTES;
            } finally {
                StreamUtils.closeQuietly(inputStream);
            }
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public String getResultAsString() {
            InputStream inputStream = getInputStream();
            if (inputStream == null) {
                return "";
            }
            try {
                return StreamUtils.copyStreamToString(inputStream, this.connection.getContentLength(), "UTF8");
            } catch (IOException unused) {
                return "";
            } finally {
                StreamUtils.closeQuietly(inputStream);
            }
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public InputStream getResultAsStream() {
            return getInputStream();
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public HttpStatus getStatus() {
            return this.status;
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public String getHeader(String str) {
            return this.connection.getHeaderField(str);
        }

        @Override // com.badlogic.gdx.Net.HttpResponse
        public Map<String, List<String>> getHeaders() {
            return this.connection.getHeaderFields();
        }

        private InputStream getInputStream() {
            try {
                return this.connection.getInputStream();
            } catch (IOException unused) {
                return this.connection.getErrorStream();
            }
        }
    }

    public NetJavaImpl() {
        this(Integer.MAX_VALUE);
    }

    public NetJavaImpl(int i) {
        boolean z = i == Integer.MAX_VALUE;
        this.executorService = new ThreadPoolExecutor(z ? 0 : i, i, 60L, TimeUnit.SECONDS, (BlockingQueue<Runnable>) (z ? new SynchronousQueue() : new LinkedBlockingQueue()), new ThreadFactory() { // from class: com.badlogic.gdx.net.NetJavaImpl.1
            AtomicInteger threadID = new AtomicInteger();

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "NetThread" + this.threadID.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            }
        });
        this.executorService.allowCoreThreadTimeOut(!z);
        this.connections = new ObjectMap<>();
        this.listeners = new ObjectMap<>();
        this.tasks = new ObjectMap<>();
    }

    public void sendHttpRequest(final Net.HttpRequest httpRequest, final Net.HttpResponseListener httpResponseListener) {
        URL url;
        if (httpRequest.getUrl() == null) {
            httpResponseListener.failed(new GdxRuntimeException("can't process a HTTP request without URL set"));
            return;
        }
        try {
            String method = httpRequest.getMethod();
            boolean z = !method.equalsIgnoreCase(Net.HttpMethods.HEAD);
            final boolean z2 = method.equalsIgnoreCase(Net.HttpMethods.POST) || method.equalsIgnoreCase(Net.HttpMethods.PUT) || method.equalsIgnoreCase(Net.HttpMethods.PATCH);
            if (method.equalsIgnoreCase(Net.HttpMethods.GET) || method.equalsIgnoreCase(Net.HttpMethods.HEAD)) {
                String str = "";
                String content = httpRequest.getContent();
                if (content != null && !"".equals(content)) {
                    str = TypeDescription.Generic.OfWildcardType.SYMBOL + content;
                }
                url = new URL(httpRequest.getUrl() + str);
            } else {
                url = new URL(httpRequest.getUrl());
            }
            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(z2);
            httpURLConnection.setDoInput(z);
            httpURLConnection.setRequestMethod(method);
            HttpURLConnection.setFollowRedirects(httpRequest.getFollowRedirects());
            putIntoConnectionsAndListeners(httpRequest, httpResponseListener, httpURLConnection);
            for (Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
                httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection.setConnectTimeout(httpRequest.getTimeOut());
            httpURLConnection.setReadTimeout(httpRequest.getTimeOut());
            this.tasks.put(httpRequest, this.executorService.submit(new Runnable() { // from class: com.badlogic.gdx.net.NetJavaImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (z2) {
                            String content2 = httpRequest.getContent();
                            if (content2 != null) {
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF8");
                                try {
                                    outputStreamWriter.write(content2);
                                    StreamUtils.closeQuietly(outputStreamWriter);
                                } catch (Throwable th) {
                                    StreamUtils.closeQuietly(outputStreamWriter);
                                    throw th;
                                }
                            } else {
                                InputStream contentStream = httpRequest.getContentStream();
                                if (contentStream != null) {
                                    OutputStream outputStream = httpURLConnection.getOutputStream();
                                    try {
                                        StreamUtils.copyStream(contentStream, outputStream);
                                        StreamUtils.closeQuietly(outputStream);
                                    } catch (Throwable th2) {
                                        StreamUtils.closeQuietly(outputStream);
                                        throw th2;
                                    }
                                }
                            }
                        }
                        httpURLConnection.connect();
                        HttpClientResponse httpClientResponse = new HttpClientResponse(httpURLConnection);
                        try {
                            Net.HttpResponseListener fromListeners = NetJavaImpl.this.getFromListeners(httpRequest);
                            if (fromListeners != null) {
                                fromListeners.handleHttpResponse(httpClientResponse);
                            }
                            NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest);
                            httpURLConnection.disconnect();
                        } catch (Throwable th3) {
                            NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest);
                            httpURLConnection.disconnect();
                            throw th3;
                        }
                    } catch (Exception e) {
                        httpURLConnection.disconnect();
                        try {
                            httpResponseListener.failed(e);
                        } finally {
                            NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest);
                        }
                    }
                }
            }));
        } catch (Exception e) {
            try {
                httpResponseListener.failed(e);
            } finally {
                removeFromConnectionsAndListeners(httpRequest);
            }
        }
    }

    public void cancelHttpRequest(Net.HttpRequest httpRequest) {
        Net.HttpResponseListener fromListeners = getFromListeners(httpRequest);
        if (fromListeners != null) {
            fromListeners.cancelled();
            cancelTask(httpRequest);
            removeFromConnectionsAndListeners(httpRequest);
        }
    }

    public boolean isHttpRequestPending(Net.HttpRequest httpRequest) {
        return getFromListeners(httpRequest) != null;
    }

    private void cancelTask(Net.HttpRequest httpRequest) {
        Future<?> future = this.tasks.get(httpRequest);
        if (future != null) {
            future.cancel(false);
        }
    }

    synchronized void removeFromConnectionsAndListeners(Net.HttpRequest httpRequest) {
        this.connections.remove(httpRequest);
        this.listeners.remove(httpRequest);
        this.tasks.remove(httpRequest);
    }

    synchronized void putIntoConnectionsAndListeners(Net.HttpRequest httpRequest, Net.HttpResponseListener httpResponseListener, HttpURLConnection httpURLConnection) {
        this.connections.put(httpRequest, httpURLConnection);
        this.listeners.put(httpRequest, httpResponseListener);
    }

    synchronized Net.HttpResponseListener getFromListeners(Net.HttpRequest httpRequest) {
        return this.listeners.get(httpRequest);
    }
}

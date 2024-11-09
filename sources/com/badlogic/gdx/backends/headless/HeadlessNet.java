package com.badlogic.gdx.backends.headless;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.NetJavaImpl;
import com.badlogic.gdx.net.NetJavaServerSocketImpl;
import com.badlogic.gdx.net.NetJavaSocketImpl;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.net.URI;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/headless/HeadlessNet.class */
public class HeadlessNet implements Net {
    NetJavaImpl netJavaImpl;

    public HeadlessNet(HeadlessApplicationConfiguration headlessApplicationConfiguration) {
        this.netJavaImpl = new NetJavaImpl(headlessApplicationConfiguration.maxNetThreads);
    }

    @Override // com.badlogic.gdx.Net
    public void sendHttpRequest(Net.HttpRequest httpRequest, Net.HttpResponseListener httpResponseListener) {
        this.netJavaImpl.sendHttpRequest(httpRequest, httpResponseListener);
    }

    @Override // com.badlogic.gdx.Net
    public void cancelHttpRequest(Net.HttpRequest httpRequest) {
        this.netJavaImpl.cancelHttpRequest(httpRequest);
    }

    @Override // com.badlogic.gdx.Net
    public boolean isHttpRequestPending(Net.HttpRequest httpRequest) {
        return this.netJavaImpl.isHttpRequestPending(httpRequest);
    }

    @Override // com.badlogic.gdx.Net
    public ServerSocket newServerSocket(Net.Protocol protocol, String str, int i, ServerSocketHints serverSocketHints) {
        return new NetJavaServerSocketImpl(protocol, str, i, serverSocketHints);
    }

    @Override // com.badlogic.gdx.Net
    public ServerSocket newServerSocket(Net.Protocol protocol, int i, ServerSocketHints serverSocketHints) {
        return new NetJavaServerSocketImpl(protocol, i, serverSocketHints);
    }

    @Override // com.badlogic.gdx.Net
    public Socket newClientSocket(Net.Protocol protocol, String str, int i, SocketHints socketHints) {
        return new NetJavaSocketImpl(protocol, str, i, socketHints);
    }

    @Override // com.badlogic.gdx.Net
    public boolean openURI(String str) {
        boolean z = false;
        try {
        } catch (Throwable th) {
            Gdx.app.error("HeadlessNet", "Failed to open URI. ", th);
        }
        if (!GraphicsEnvironment.isHeadless() && Desktop.isDesktopSupported()) {
            if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(str));
                z = true;
                return z;
            }
        } else {
            Gdx.app.error("HeadlessNet", "Opening URIs on this environment is not supported. Ignoring.");
        }
        return z;
    }
}

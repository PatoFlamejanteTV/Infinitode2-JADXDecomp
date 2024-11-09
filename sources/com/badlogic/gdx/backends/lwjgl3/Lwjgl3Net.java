package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.NetJavaImpl;
import com.badlogic.gdx.net.NetJavaServerSocketImpl;
import com.badlogic.gdx.net.NetJavaSocketImpl;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Os;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import java.awt.Desktop;
import java.net.URI;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Net.class */
public class Lwjgl3Net implements Net {
    NetJavaImpl netJavaImpl;

    public Lwjgl3Net(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration) {
        this.netJavaImpl = new NetJavaImpl(lwjgl3ApplicationConfiguration.maxNetThreads);
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
        if (SharedLibraryLoader.os == Os.MacOsX) {
            try {
                new ProcessBuilder("open", new URI(str).toString()).start();
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(str));
                return true;
            } catch (Throwable unused2) {
                return false;
            }
        }
        if (SharedLibraryLoader.os == Os.Linux) {
            try {
                new ProcessBuilder("xdg-open", new URI(str).toString()).start();
                return true;
            } catch (Throwable unused3) {
                return false;
            }
        }
        return false;
    }
}

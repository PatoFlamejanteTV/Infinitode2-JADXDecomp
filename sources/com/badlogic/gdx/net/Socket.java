package com.badlogic.gdx.net;

import com.badlogic.gdx.utils.Disposable;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/net/Socket.class */
public interface Socket extends Disposable {
    boolean isConnected();

    InputStream getInputStream();

    OutputStream getOutputStream();

    String getRemoteAddress();
}

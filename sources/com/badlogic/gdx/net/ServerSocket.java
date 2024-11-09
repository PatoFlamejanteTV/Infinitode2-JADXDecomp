package com.badlogic.gdx.net;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/net/ServerSocket.class */
public interface ServerSocket extends Disposable {
    Net.Protocol getProtocol();

    Socket accept(SocketHints socketHints);
}

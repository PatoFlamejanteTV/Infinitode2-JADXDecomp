package com.badlogic.gdx.ai.msg;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/msg/MessageManager.class */
public final class MessageManager extends MessageDispatcher {
    private static final MessageManager INSTANCE = new MessageManager();

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        return INSTANCE;
    }
}

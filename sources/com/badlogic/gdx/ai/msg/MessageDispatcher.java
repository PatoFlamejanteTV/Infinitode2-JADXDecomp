package com.badlogic.gdx.ai.msg;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/msg/MessageDispatcher.class */
public class MessageDispatcher implements Telegraph {
    private static final String LOG_TAG = MessageDispatcher.class.getSimpleName();
    private static final Pool<Telegram> POOL = new Pool<Telegram>(16) { // from class: com.badlogic.gdx.ai.msg.MessageDispatcher.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.Pool
        public final Telegram newObject() {
            return new Telegram();
        }
    };
    private PriorityQueue<Telegram> queue = new PriorityQueue<>();
    private IntMap<Array<Telegraph>> msgListeners = new IntMap<>();
    private IntMap<Array<TelegramProvider>> msgProviders = new IntMap<>();
    private boolean debugEnabled;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/msg/MessageDispatcher$PendingMessageCallback.class */
    public interface PendingMessageCallback {
        void report(float f, Telegraph telegraph, Telegraph telegraph2, int i, Object obj, int i2);
    }

    public boolean isDebugEnabled() {
        return this.debugEnabled;
    }

    public void setDebugEnabled(boolean z) {
        this.debugEnabled = z;
    }

    public void addListener(Telegraph telegraph, int i) {
        Array<Telegraph> array = this.msgListeners.get(i);
        Array<Telegraph> array2 = array;
        if (array == null) {
            array2 = new Array<>(false, 16);
            this.msgListeners.put(i, array2);
        }
        array2.add(telegraph);
        Array<TelegramProvider> array3 = this.msgProviders.get(i);
        if (array3 != null) {
            int i2 = array3.size;
            for (int i3 = 0; i3 < i2; i3++) {
                TelegramProvider telegramProvider = array3.get(i3);
                Object provideMessageInfo = telegramProvider.provideMessageInfo(i, telegraph);
                if (provideMessageInfo != null) {
                    dispatchMessage(0.0f, ClassReflection.isInstance(Telegraph.class, telegramProvider) ? (Telegraph) telegramProvider : null, telegraph, i, provideMessageInfo, false);
                }
            }
        }
    }

    public void addListeners(Telegraph telegraph, int... iArr) {
        for (int i : iArr) {
            addListener(telegraph, i);
        }
    }

    public void addProvider(TelegramProvider telegramProvider, int i) {
        Array<TelegramProvider> array = this.msgProviders.get(i);
        Array<TelegramProvider> array2 = array;
        if (array == null) {
            array2 = new Array<>(false, 16);
            this.msgProviders.put(i, array2);
        }
        array2.add(telegramProvider);
    }

    public void addProviders(TelegramProvider telegramProvider, int... iArr) {
        for (int i : iArr) {
            addProvider(telegramProvider, i);
        }
    }

    public void removeListener(Telegraph telegraph, int i) {
        Array<Telegraph> array = this.msgListeners.get(i);
        if (array != null) {
            array.removeValue(telegraph, true);
        }
    }

    public void removeListener(Telegraph telegraph, int... iArr) {
        for (int i : iArr) {
            removeListener(telegraph, i);
        }
    }

    public void clearListeners(int i) {
        this.msgListeners.remove(i);
    }

    public void clearListeners(int... iArr) {
        for (int i : iArr) {
            clearListeners(i);
        }
    }

    public void clearListeners() {
        this.msgListeners.clear();
    }

    public void clearProviders(int i) {
        this.msgProviders.remove(i);
    }

    public void clearProviders(int... iArr) {
        for (int i : iArr) {
            clearProviders(i);
        }
    }

    public void clearProviders() {
        this.msgProviders.clear();
    }

    public void clearQueue() {
        for (int i = 0; i < this.queue.size(); i++) {
            POOL.free(this.queue.get(i));
        }
        this.queue.clear();
    }

    public void clear() {
        clearQueue();
        clearListeners();
        clearProviders();
    }

    public void dispatchMessage(int i) {
        dispatchMessage(0.0f, null, null, i, null, false);
    }

    public void dispatchMessage(Telegraph telegraph, int i) {
        dispatchMessage(0.0f, telegraph, null, i, null, false);
    }

    public void dispatchMessage(Telegraph telegraph, int i, boolean z) {
        dispatchMessage(0.0f, telegraph, null, i, null, z);
    }

    public void dispatchMessage(int i, Object obj) {
        dispatchMessage(0.0f, null, null, i, obj, false);
    }

    public void dispatchMessage(Telegraph telegraph, int i, Object obj) {
        dispatchMessage(0.0f, telegraph, null, i, obj, false);
    }

    public void dispatchMessage(Telegraph telegraph, int i, Object obj, boolean z) {
        dispatchMessage(0.0f, telegraph, null, i, obj, z);
    }

    public void dispatchMessage(Telegraph telegraph, Telegraph telegraph2, int i) {
        dispatchMessage(0.0f, telegraph, telegraph2, i, null, false);
    }

    public void dispatchMessage(Telegraph telegraph, Telegraph telegraph2, int i, boolean z) {
        dispatchMessage(0.0f, telegraph, telegraph2, i, null, z);
    }

    public void dispatchMessage(Telegraph telegraph, Telegraph telegraph2, int i, Object obj) {
        dispatchMessage(0.0f, telegraph, telegraph2, i, obj, false);
    }

    public void dispatchMessage(Telegraph telegraph, Telegraph telegraph2, int i, Object obj, boolean z) {
        dispatchMessage(0.0f, telegraph, telegraph2, i, obj, z);
    }

    public void dispatchMessage(float f, int i) {
        dispatchMessage(f, null, null, i, null, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, int i) {
        dispatchMessage(f, telegraph, null, i, null, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, int i, boolean z) {
        dispatchMessage(f, telegraph, null, i, null, z);
    }

    public void dispatchMessage(float f, int i, Object obj) {
        dispatchMessage(f, null, null, i, obj, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, int i, Object obj) {
        dispatchMessage(f, telegraph, null, i, obj, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, int i, Object obj, boolean z) {
        dispatchMessage(f, telegraph, null, i, obj, z);
    }

    public void dispatchMessage(float f, Telegraph telegraph, Telegraph telegraph2, int i) {
        dispatchMessage(f, telegraph, telegraph2, i, null, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, Telegraph telegraph2, int i, boolean z) {
        dispatchMessage(f, telegraph, telegraph2, i, null, z);
    }

    public void dispatchMessage(float f, Telegraph telegraph, Telegraph telegraph2, int i, Object obj) {
        dispatchMessage(f, telegraph, telegraph2, i, obj, false);
    }

    public void dispatchMessage(float f, Telegraph telegraph, Telegraph telegraph2, int i, Object obj, boolean z) {
        if (telegraph == null && z) {
            throw new IllegalArgumentException("Sender cannot be null when a return receipt is needed");
        }
        Telegram obtain = POOL.obtain();
        obtain.sender = telegraph;
        obtain.receiver = telegraph2;
        obtain.message = i;
        obtain.extraInfo = obj;
        obtain.returnReceiptStatus = z ? 1 : 0;
        if (f <= 0.0f) {
            if (this.debugEnabled) {
                GdxAI.getLogger().info(LOG_TAG, "Instant telegram dispatched at time: " + GdxAI.getTimepiece().getTime() + " by " + telegraph + " for " + telegraph2 + ". Message code is " + i);
            }
            discharge(obtain);
            return;
        }
        float time = GdxAI.getTimepiece().getTime();
        obtain.setTimestamp(time + f);
        boolean add = this.queue.add(obtain);
        if (!add) {
            POOL.free(obtain);
        }
        if (this.debugEnabled) {
            if (add) {
                GdxAI.getLogger().info(LOG_TAG, "Delayed telegram from " + telegraph + " for " + telegraph2 + " recorded at time " + time + ". Message code is " + i);
            } else {
                GdxAI.getLogger().info(LOG_TAG, "Delayed telegram from " + telegraph + " for " + telegraph2 + " rejected by the queue. Message code is " + i);
            }
        }
    }

    public void update() {
        float time = GdxAI.getTimepiece().getTime();
        while (true) {
            Telegram peek = this.queue.peek();
            if (peek != null && peek.getTimestamp() <= time) {
                if (this.debugEnabled) {
                    GdxAI.getLogger().info(LOG_TAG, "Queued telegram ready for dispatch: Sent to " + peek.receiver + ". Message code is " + peek.message);
                }
                discharge(peek);
                this.queue.poll();
            } else {
                return;
            }
        }
    }

    public void scanQueue(PendingMessageCallback pendingMessageCallback) {
        float time = GdxAI.getTimepiece().getTime();
        int size = this.queue.size();
        for (int i = 0; i < size; i++) {
            Telegram telegram = this.queue.get(i);
            pendingMessageCallback.report(telegram.getTimestamp() - time, telegram.sender, telegram.receiver, telegram.message, telegram.extraInfo, telegram.returnReceiptStatus);
        }
    }

    private void discharge(Telegram telegram) {
        if (telegram.receiver != null) {
            if (!telegram.receiver.handleMessage(telegram) && this.debugEnabled) {
                GdxAI.getLogger().info(LOG_TAG, "Message " + telegram.message + " not handled");
            }
        } else {
            int i = 0;
            Array<Telegraph> array = this.msgListeners.get(telegram.message);
            if (array != null) {
                for (int i2 = 0; i2 < array.size; i2++) {
                    if (array.get(i2).handleMessage(telegram)) {
                        i++;
                    }
                }
            }
            if (this.debugEnabled && i == 0) {
                GdxAI.getLogger().info(LOG_TAG, "Message " + telegram.message + " not handled");
            }
        }
        if (telegram.returnReceiptStatus == 1) {
            telegram.receiver = telegram.sender;
            telegram.sender = this;
            telegram.returnReceiptStatus = 2;
            discharge(telegram);
            return;
        }
        POOL.free(telegram);
    }

    @Override // com.badlogic.gdx.ai.msg.Telegraph
    public boolean handleMessage(Telegram telegram) {
        return false;
    }
}

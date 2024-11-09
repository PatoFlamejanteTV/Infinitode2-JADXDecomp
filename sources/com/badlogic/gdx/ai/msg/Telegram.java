package com.badlogic.gdx.ai.msg;

import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/msg/Telegram.class */
public class Telegram implements Pool.Poolable, Comparable<Telegram> {
    public static final int RETURN_RECEIPT_UNNEEDED = 0;
    public static final int RETURN_RECEIPT_NEEDED = 1;
    public static final int RETURN_RECEIPT_SENT = 2;
    public Telegraph sender;
    public Telegraph receiver;
    public int message;
    public int returnReceiptStatus;
    private float timestamp;
    public Object extraInfo;

    public float getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(float f) {
        this.timestamp = f;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.sender = null;
        this.receiver = null;
        this.message = 0;
        this.returnReceiptStatus = 0;
        this.extraInfo = null;
        this.timestamp = 0.0f;
    }

    @Override // java.lang.Comparable
    public int compareTo(Telegram telegram) {
        if (equals(telegram)) {
            return 0;
        }
        return this.timestamp - telegram.timestamp < 0.0f ? -1 : 1;
    }

    public int hashCode() {
        return ((((((31 + this.message) * 31) + (this.receiver == null ? 0 : this.receiver.hashCode())) * 31) + (this.sender == null ? 0 : this.sender.hashCode())) * 31) + Float.floatToIntBits(this.timestamp);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Telegram telegram = (Telegram) obj;
        if (this.message != telegram.message || Float.floatToIntBits(this.timestamp) != Float.floatToIntBits(telegram.timestamp)) {
            return false;
        }
        if (this.sender == null) {
            if (telegram.sender != null) {
                return false;
            }
        } else if (!this.sender.equals(telegram.sender)) {
            return false;
        }
        return this.receiver == null ? telegram.receiver == null : this.receiver.equals(telegram.receiver);
    }
}

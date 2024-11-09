package com.prineside.tdi2.utils.logging;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/TLog.class */
public final class TLog {

    /* renamed from: a, reason: collision with root package name */
    private final String f3932a;

    /* synthetic */ TLog(String str, byte b2) {
        this(str);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/TLog$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<TLog> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, TLog tLog) {
            output.writeString(tLog.f3932a);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        public TLog read(Kryo kryo, Input input, Class<? extends TLog> cls) {
            return new TLog(input.readString(), (byte) 0);
        }
    }

    public static TLog forClass(Class<?> cls) {
        return new TLog(cls.getSimpleName());
    }

    public static TLog forTag(String str) {
        return new TLog(str);
    }

    private TLog(String str) {
        Preconditions.checkNotNull(str, "tag can not be null");
        String replaceAll = str.replaceAll("[^a-zA-Z0-9\\-_./]", "");
        String str2 = replaceAll.length() == 0 ? "incorrect-tag" : replaceAll;
        if (str2.length() > 20) {
            while (str2.length() > 20) {
                String shortenFirstWord = StringFormatter.shortenFirstWord(str2);
                if (shortenFirstWord.equals(str2)) {
                    break;
                } else {
                    str2 = shortenFirstWord;
                }
            }
            this.f3932a = str2;
            return;
        }
        this.f3932a = str2;
    }

    public final String getTag() {
        return this.f3932a;
    }

    public final void d(String str, Object... objArr) {
        if (LogLevel.isDebug()) {
            Logger.a((byte) 0, getTag(), str, objArr);
        }
    }

    public final void i(String str, Object... objArr) {
        if (LogLevel.isInfo()) {
            Logger.a((byte) 1, getTag(), str, objArr);
        }
    }

    public final void w(String str, Object... objArr) {
        if (LogLevel.isWarning()) {
            Logger.a((byte) 2, getTag(), str, objArr);
        }
    }

    public final void e(String str, Object... objArr) {
        Logger.a((byte) 3, getTag(), str, objArr);
    }
}

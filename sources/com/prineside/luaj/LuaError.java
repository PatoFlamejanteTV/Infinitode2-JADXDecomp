package com.prineside.luaj;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaError.class */
public class LuaError extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    protected int f1480a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    protected String f1481b;
    protected String c;
    private Throwable d;
    public LuaValue object;

    @Null
    public String extraMessage;

    static {
        TLog.forClass(LuaError.class);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.c != null) {
            return this.c;
        }
        StringBuilder sb = new StringBuilder(super.getMessage());
        if (sb.length() == 0) {
            sb.append(this.extraMessage);
        } else if (this.extraMessage != null) {
            sb.append(SequenceUtils.EOL).append(this.extraMessage);
        }
        if (this.f1481b != null) {
            if (sb.length() == 0) {
                sb.append(this.f1481b);
            } else {
                sb.insert(0, this.f1481b + SequenceUtils.SPACE);
            }
        }
        Throwable cause = getCause();
        while (true) {
            Throwable th = cause;
            if (th != null) {
                sb.append("\n    Caused by: ").append(th.getMessage()).append(" (").append(th.getStackTrace()[0]).append(")");
                cause = th.getCause();
            } else {
                return sb.toString();
            }
        }
    }

    @Null
    public String getExtraMessage() {
        return this.extraMessage;
    }

    public void setExtraMessage(String str) {
        this.extraMessage = str;
    }

    public void appendExtraMessage(String str) {
        if (this.extraMessage == null) {
            this.extraMessage = str;
        } else {
            this.extraMessage += SequenceUtils.EOL + str;
        }
    }

    @Null
    public String getFileline() {
        return this.f1481b;
    }

    public LuaValue getMessageObject() {
        if (this.object != null) {
            return this.object;
        }
        String message = getMessage();
        if (message != null) {
            return LuaValue.valueOf(message);
        }
        return null;
    }

    public LuaError(Throwable th) {
        super("vm error: " + th, th);
        this.d = th;
        this.f1480a = 1;
    }

    public LuaError setCause(Throwable th) {
        this.d = th;
        return this;
    }

    public LuaError(String str) {
        super(str);
        this.f1480a = 1;
    }

    public LuaError(String str, int i) {
        super(str);
        this.f1480a = i;
    }

    public LuaError(String str, int i, Throwable th) {
        super(str, th);
        this.f1480a = i;
        this.d = th;
    }

    public LuaError(LuaValue luaValue) {
        super(luaValue.tojstring());
        this.object = luaValue;
        this.f1480a = 1;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.d;
    }
}

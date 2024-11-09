package com.prineside.luaj.debug;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaClosure;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/debug/CallStack.class */
public final class CallStack implements KryoSerializable {
    public static final CallStack DUMMY = new CallStack();

    /* renamed from: a, reason: collision with root package name */
    private static CallFrame[] f1560a = new CallFrame[0];

    /* renamed from: b, reason: collision with root package name */
    private CallFrame[] f1561b = f1560a;
    public int calls = 0;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f1561b);
        output.writeInt(this.calls);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1561b = (CallFrame[]) kryo.readObject(input, CallFrame[].class);
        this.calls = input.readInt();
    }

    private CallFrame a() {
        if (this.calls >= this.f1561b.length) {
            int max = Math.max(4, (this.f1561b.length * 3) / 2);
            CallFrame[] callFrameArr = new CallFrame[max];
            System.arraycopy(this.f1561b, 0, callFrameArr, 0, this.f1561b.length);
            for (int length = this.f1561b.length; length < max; length++) {
                callFrameArr[length] = new CallFrame();
            }
            this.f1561b = callFrameArr;
            for (int i = 1; i < max; i++) {
                callFrameArr[i].f1559a = callFrameArr[i - 1];
            }
        }
        CallFrame[] callFrameArr2 = this.f1561b;
        int i2 = this.calls;
        this.calls = i2 + 1;
        return callFrameArr2[i2];
    }

    public final void onCall(LuaFunction luaFunction) {
        a().a(luaFunction);
    }

    public final void onCall(LuaClosure luaClosure, Varargs varargs, LuaValue[] luaValueArr) {
        a().a(luaClosure, varargs, luaValueArr);
    }

    public final void onReturn() {
        if (this.calls > 0) {
            CallFrame[] callFrameArr = this.f1561b;
            int i = this.calls - 1;
            this.calls = i;
            callFrameArr[i].a();
        }
    }

    public final void onInstruction(int i, Varargs varargs, int i2) {
        if (this.calls > 0) {
            this.f1561b[this.calls - 1].a(i, varargs, i2);
        }
    }

    public final String traceback(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("stack traceback:");
        while (true) {
            int i2 = i;
            i++;
            CallFrame callFrame = getCallFrame(i2);
            if (callFrame != null) {
                stringBuffer.append("\n\t");
                stringBuffer.append(callFrame.shortsource());
                stringBuffer.append(':');
                if (callFrame.currentline() > 0) {
                    stringBuffer.append(callFrame.currentline() + ":");
                }
                stringBuffer.append(" in ");
                DebugInfo a2 = a("n", callFrame.f, callFrame);
                if (callFrame.b() == 0) {
                    stringBuffer.append("main chunk");
                } else if (a2.f1562a != null) {
                    stringBuffer.append("function '");
                    stringBuffer.append(a2.f1562a);
                    stringBuffer.append('\'');
                } else {
                    stringBuffer.append("function <");
                    stringBuffer.append(callFrame.shortsource());
                    stringBuffer.append(':');
                    stringBuffer.append(callFrame.b());
                    stringBuffer.append('>');
                }
            } else {
                stringBuffer.append("\n\t[Java]: in ?");
                return stringBuffer.toString();
            }
        }
    }

    public final CallFrame getCallFrame(int i) {
        if (i <= 0 || i > this.calls) {
            return null;
        }
        return this.f1561b[this.calls - i];
    }

    private static DebugInfo a(String str, LuaFunction luaFunction, CallFrame callFrame) {
        NameWhat nameWhat;
        DebugInfo debugInfo = new DebugInfo();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            switch (str.charAt(i)) {
                case 'S':
                    debugInfo.funcinfo(luaFunction);
                    break;
                case 'l':
                    if (callFrame != null && callFrame.f.isclosure()) {
                        callFrame.currentline();
                        break;
                    }
                    break;
                case 'n':
                    if (callFrame != null && callFrame.f1559a != null && callFrame.f1559a.f.isclosure() && (nameWhat = CallFrame.getfuncname(callFrame.f1559a)) != null) {
                        debugInfo.f1562a = nameWhat.name;
                        debugInfo.f1563b = nameWhat.namewhat;
                    }
                    if (debugInfo.f1563b == null) {
                        debugInfo.f1563b = "";
                        debugInfo.f1562a = null;
                        break;
                    } else {
                        break;
                    }
                case 'u':
                    if (luaFunction != null && luaFunction.isclosure()) {
                        luaFunction.checkclosure();
                        break;
                    }
                    break;
            }
        }
        return debugInfo;
    }
}

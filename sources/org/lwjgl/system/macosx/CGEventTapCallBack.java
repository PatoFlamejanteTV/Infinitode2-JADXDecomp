package org.lwjgl.system.macosx;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGEventTapCallBack.class */
public abstract class CGEventTapCallBack extends Callback implements CGEventTapCallBackI {
    public static CGEventTapCallBack create(long j) {
        CGEventTapCallBackI cGEventTapCallBackI = (CGEventTapCallBackI) Callback.get(j);
        return cGEventTapCallBackI instanceof CGEventTapCallBack ? (CGEventTapCallBack) cGEventTapCallBackI : new Container(j, cGEventTapCallBackI);
    }

    public static CGEventTapCallBack createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static CGEventTapCallBack create(CGEventTapCallBackI cGEventTapCallBackI) {
        return cGEventTapCallBackI instanceof CGEventTapCallBack ? (CGEventTapCallBack) cGEventTapCallBackI : new Container(cGEventTapCallBackI.address(), cGEventTapCallBackI);
    }

    protected CGEventTapCallBack() {
        super(CIF);
    }

    CGEventTapCallBack(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGEventTapCallBack$Container.class */
    public static final class Container extends CGEventTapCallBack {
        private final CGEventTapCallBackI delegate;

        Container(long j, CGEventTapCallBackI cGEventTapCallBackI) {
            super(j);
            this.delegate = cGEventTapCallBackI;
        }

        @Override // org.lwjgl.system.macosx.CGEventTapCallBackI
        public final long invoke(long j, int i, long j2, long j3) {
            return this.delegate.invoke(j, i, j2, j3);
        }
    }
}

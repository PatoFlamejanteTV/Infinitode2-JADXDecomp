package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/profiling/GLErrorListener.class */
public interface GLErrorListener {
    public static final GLErrorListener LOGGING_LISTENER = new GLErrorListener() { // from class: com.badlogic.gdx.graphics.profiling.GLErrorListener.1
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:            if ((r10 + 1) >= r0.length) goto L12;     */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:            r8 = r0[r10 + 1].getMethodName();     */
        @Override // com.badlogic.gdx.graphics.profiling.GLErrorListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onError(int r7) {
            /*
                r6 = this;
                r0 = 0
                r8 = r0
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Exception -> L43
                java.lang.StackTraceElement[] r0 = r0.getStackTrace()     // Catch: java.lang.Exception -> L43
                r9 = r0
                r0 = 0
                r10 = r0
            Lc:
                r0 = r10
                r1 = r9
                int r1 = r1.length     // Catch: java.lang.Exception -> L43
                if (r0 >= r1) goto L40
                java.lang.String r0 = "check"
                r1 = r9
                r2 = r10
                r1 = r1[r2]     // Catch: java.lang.Exception -> L43
                java.lang.String r1 = r1.getMethodName()     // Catch: java.lang.Exception -> L43
                boolean r0 = r0.equals(r1)     // Catch: java.lang.Exception -> L43
                if (r0 == 0) goto L3a
                r0 = r10
                r1 = 1
                int r0 = r0 + r1
                r1 = r9
                int r1 = r1.length     // Catch: java.lang.Exception -> L43
                if (r0 >= r1) goto L40
                r0 = r9
                r1 = r10
                r2 = 1
                int r1 = r1 + r2
                r0 = r0[r1]     // Catch: java.lang.Exception -> L43
                r1 = r0
                r9 = r1
                java.lang.String r0 = r0.getMethodName()     // Catch: java.lang.Exception -> L43
                r8 = r0
                goto L44
            L3a:
                int r10 = r10 + 1
                goto Lc
            L40:
                goto L44
            L43:
            L44:
                r0 = r8
                if (r0 == 0) goto L6f
                com.badlogic.gdx.Application r0 = com.badlogic.gdx.Gdx.app
                java.lang.String r1 = "GLProfiler"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                java.lang.String r4 = "Error "
                r3.<init>(r4)
                r3 = r7
                java.lang.String r3 = com.badlogic.gdx.graphics.profiling.GLInterceptor.resolveErrorNumber(r3)
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r3 = " from "
                java.lang.StringBuilder r2 = r2.append(r3)
                r3 = r8
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r0.error(r1, r2)
                return
            L6f:
                com.badlogic.gdx.Application r0 = com.badlogic.gdx.Gdx.app
                java.lang.String r1 = "GLProfiler"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                java.lang.String r4 = "Error "
                r3.<init>(r4)
                r3 = r7
                java.lang.String r3 = com.badlogic.gdx.graphics.profiling.GLInterceptor.resolveErrorNumber(r3)
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r3 = " at: "
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.Exception r3 = new java.lang.Exception
                r4 = r3
                r4.<init>()
                r0.error(r1, r2, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.profiling.GLErrorListener.AnonymousClass1.onError(int):void");
        }
    };
    public static final GLErrorListener THROWING_LISTENER = new GLErrorListener() { // from class: com.badlogic.gdx.graphics.profiling.GLErrorListener.2
        @Override // com.badlogic.gdx.graphics.profiling.GLErrorListener
        public void onError(int i) {
            throw new GdxRuntimeException("GLProfiler: Got GL error " + GLInterceptor.resolveErrorNumber(i));
        }
    };

    void onError(int i);
}

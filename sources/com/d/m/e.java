package com.d.m;

import java.text.DecimalFormat;

/* loaded from: infinitode-2.jar:com/d/m/e.class */
public final class e {
    static {
        new DecimalFormat("0000000000");
    }

    public static void a(Exception exc) {
        String message = exc.getMessage();
        String str = message;
        if (message == null || str.trim().equals("null")) {
            str = "{no ex. message}";
        }
        System.out.println(str + ", " + exc.getClass());
        StackTraceElement[] stackTrace = exc.getStackTrace();
        for (int i = 0; i < stackTrace.length && i < 5; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            System.out.println("  " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(ln " + stackTraceElement.getLineNumber() + ")");
        }
    }
}

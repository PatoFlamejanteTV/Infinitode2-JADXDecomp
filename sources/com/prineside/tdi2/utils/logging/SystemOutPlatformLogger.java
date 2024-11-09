package com.prineside.tdi2.utils.logging;

import com.prineside.tdi2.utils.AEC;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/logging/SystemOutPlatformLogger.class */
public final class SystemOutPlatformLogger implements PlatformLogger {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f3930a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f3931b;

    public SystemOutPlatformLogger(boolean z, boolean z2) {
        this.f3930a = z;
        this.f3931b = z2;
    }

    private StringBuilder a(byte b2, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (this.f3930a) {
            switch (b2) {
                case 0:
                    sb.append(AEC.F_WHITE);
                    break;
                case 2:
                    sb.append(AEC.F_YELLOW);
                    break;
                case 3:
                    sb.append(AEC.F_RED);
                    break;
            }
        }
        sb.append("[").append(str).append("]");
        if (this.f3931b && (b2 == 2 || b2 == 1)) {
            sb.append(' ');
        }
        sb.append("[").append(str2).append("]");
        if (this.f3931b) {
            for (int length = str2.length(); length < 20; length++) {
                sb.append(' ');
            }
        }
        sb.append(' ').append(str3);
        if (this.f3930a) {
            sb.append(AEC.RESET);
        }
        return sb;
    }

    @Override // com.prineside.tdi2.utils.logging.PlatformLogger
    public final void debug(String str, String str2) {
        System.out.println(a((byte) 0, "debug", str, str2));
    }

    @Override // com.prineside.tdi2.utils.logging.PlatformLogger
    public final void info(String str, String str2) {
        System.out.println(a((byte) 1, "info", str, str2));
    }

    @Override // com.prineside.tdi2.utils.logging.PlatformLogger
    public final void warn(String str, String str2) {
        System.out.println(a((byte) 2, "warn", str, str2));
    }

    @Override // com.prineside.tdi2.utils.logging.PlatformLogger
    public final void error(String str, String str2) {
        System.out.println(a((byte) 3, "error", str, str2));
    }
}

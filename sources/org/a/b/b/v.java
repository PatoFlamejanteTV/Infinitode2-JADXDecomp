package org.a.b.b;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/v.class */
public class v {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4249a = org.a.a.a.c.a(v.class);

    /* renamed from: b, reason: collision with root package name */
    private final String f4250b;
    private final String c;

    public v(String str, String str2) {
        this.f4250b = str;
        this.c = str2;
    }

    public final List<Object> a(byte[] bArr, List<byte[]> list) {
        return a(bArr, list, new ArrayList());
    }

    private List<Object> a(byte[] bArr, List<byte[]> list, List<Object> list2) {
        b bVar = new b(bArr);
        while (bVar.a()) {
            int d = bVar.d();
            if (d == 10) {
                Object remove = list2.remove(list2.size() - 1);
                if (!(remove instanceof Integer)) {
                    new StringBuilder("Parameter ").append(remove).append(" for CALLSUBR is ignored, integer expected in glyph '").append(this.c).append("' of font ").append(this.f4250b);
                } else {
                    Integer num = (Integer) remove;
                    if (num.intValue() >= 0 && num.intValue() < list.size()) {
                        a(list.get(num.intValue()), list, list2);
                        Object obj = list2.get(list2.size() - 1);
                        if ((obj instanceof q) && ((q) obj).a().a()[0] == 11) {
                            list2.remove(list2.size() - 1);
                        }
                    } else {
                        new StringBuilder("CALLSUBR is ignored, operand: ").append(num).append(", subrs.size(): ").append(list.size()).append(" in glyph '").append(this.c).append("' of font ").append(this.f4250b);
                        while (list2.get(list2.size() - 1) instanceof Integer) {
                            list2.remove(list2.size() - 1);
                        }
                    }
                }
            } else if (d == 12 && bVar.b(0) == 16) {
                bVar.c();
                Integer num2 = (Integer) list2.remove(list2.size() - 1);
                Integer num3 = (Integer) list2.remove(list2.size() - 1);
                Stack stack = new Stack();
                switch (num2.intValue()) {
                    case 0:
                        stack.push(a(list2));
                        stack.push(a(list2));
                        list2.remove(list2.size() - 1);
                        list2.add(0);
                        list2.add(new q(12, 16));
                        break;
                    case 1:
                        list2.add(1);
                        list2.add(new q(12, 16));
                        break;
                    case 2:
                    default:
                        for (int i = 0; i < num3.intValue(); i++) {
                            stack.push(a(list2));
                        }
                    case 3:
                        stack.push(a(list2));
                        break;
                }
                while (bVar.b(0) == 12 && bVar.b(1) == 17) {
                    bVar.c();
                    bVar.c();
                    list2.add(stack.pop());
                }
                if (stack.size() > 0) {
                    new StringBuilder("Value left on the PostScript stack in glyph ").append(this.c).append(" of font ").append(this.f4250b);
                }
            } else if (d >= 0 && d <= 31) {
                list2.add(a(bVar, d));
            } else if (d >= 32 && d <= 255) {
                list2.add(b(bVar, d));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return list2;
    }

    private static Integer a(List<Object> list) {
        Object remove = list.remove(list.size() - 1);
        if (remove instanceof Integer) {
            return (Integer) remove;
        }
        q qVar = (q) remove;
        if (qVar.a().a()[0] == 12 && qVar.a().a()[1] == 12) {
            return Integer.valueOf(((Integer) list.remove(list.size() - 1)).intValue() / ((Integer) list.remove(list.size() - 1)).intValue());
        }
        throw new IOException("Unexpected char string command: " + qVar.a());
    }

    private static q a(b bVar, int i) {
        if (i == 12) {
            return new q(i, bVar.d());
        }
        return new q(i);
    }

    private static Integer b(b bVar, int i) {
        if (i >= 32 && i <= 246) {
            return Integer.valueOf(i - 139);
        }
        if (i >= 247 && i <= 250) {
            return Integer.valueOf(((i - User32.VK_CRSEL) << 8) + bVar.d() + 108);
        }
        if (i >= 251 && i <= 254) {
            return Integer.valueOf((((-(i - User32.VK_ZOOM)) << 8) - bVar.d()) - 108);
        }
        if (i == 255) {
            return Integer.valueOf(bVar.g());
        }
        throw new IllegalArgumentException();
    }
}

package com.d.c.a;

import com.d.h.w;
import com.d.m.l;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/c/a/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final List f974a;

    /* renamed from: b, reason: collision with root package name */
    private static final Map f975b;

    public static String a(short s) {
        return (String) f975b.get(Short.valueOf(s));
    }

    public static boolean b(short s) {
        switch (s) {
            case 0:
                l.c(Level.WARNING, "Asked whether type was absolute, given CSS_UNKNOWN as the type. Might be one of those funny values like background-position.");
                com.d.m.e.a(new Exception());
                return false;
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                return true;
            case 2:
                return false;
            default:
                return false;
        }
    }

    static {
        TreeMap treeMap = new TreeMap();
        f974a = new ArrayList();
        try {
            for (Field field : com.d.c.d.d.class.getFields()) {
                int modifiers = field.getModifiers();
                if (Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                    Short sh = (Short) field.get(null);
                    String name = field.getName();
                    if (name.startsWith("CSS_") && !name.equals("CSS_INHERIT") && !name.equals("CSS_PRIMITIVE_VALUE") && !name.equals("CSS_VALUE_LIST") && !name.equals("CSS_CUSTOM")) {
                        treeMap.put(sh, name.substring(4));
                    }
                }
            }
            ArrayList arrayList = new ArrayList(treeMap.keySet());
            Collections.sort(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                f974a.add(treeMap.get(it.next()));
            }
            HashMap hashMap = new HashMap(25);
            f975b = hashMap;
            hashMap.put((short) 3, FlexmarkHtmlConverter.EM_NODE);
            f975b.put((short) 4, "ex");
            f975b.put((short) 5, "px");
            f975b.put((short) 2, "%");
            f975b.put((short) 8, "in");
            f975b.put((short) 6, "cm");
            f975b.put((short) 7, "mm");
            f975b.put((short) 9, "pt");
            f975b.put((short) 10, "pc");
        } catch (Exception e) {
            throw new w.a("Could not build static list of CSS type descriptions.", (Throwable) e);
        }
    }
}

package org.a.c.h.e;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/ai.class */
final class ai {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Integer, String> f4525a = new TreeMap();

    /* renamed from: b, reason: collision with root package name */
    private int f4526b = 0;

    public final void a(int i, String str) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("CID is not valid");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Text is null or empty");
        }
        this.f4525a.put(Integer.valueOf(i), str);
    }

    public final void a(OutputStream outputStream) {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, org.a.c.i.a.f4651a));
        a(bufferedWriter, "/CIDInit /ProcSet findresource begin");
        a(bufferedWriter, "12 dict begin\n");
        a(bufferedWriter, "begincmap");
        a(bufferedWriter, "/CIDSystemInfo");
        a(bufferedWriter, "<< /Registry (Adobe)");
        a(bufferedWriter, "/Ordering (UCS)");
        a(bufferedWriter, "/Supplement 0");
        a(bufferedWriter, ">> def\n");
        a(bufferedWriter, "/CMapName /Adobe-Identity-UCS def");
        a(bufferedWriter, "/CMapType 2 def\n");
        a(bufferedWriter, "1 begincodespacerange");
        a(bufferedWriter, "<0000> <FFFF>");
        a(bufferedWriter, "endcodespacerange\n");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = -1;
        String str = null;
        int i2 = -1;
        for (Map.Entry<Integer, String> entry : this.f4525a.entrySet()) {
            int intValue = entry.getKey().intValue();
            String value = entry.getValue();
            if (intValue == i + 1 && str.codePointCount(0, str.length()) == 1 && value.codePointAt(0) == str.codePointAt(0) + 1 && str.codePointAt(0) + 1 <= 255 - (intValue - i2)) {
                arrayList2.set(arrayList2.size() - 1, Integer.valueOf(intValue));
            } else {
                i2 = intValue;
                arrayList.add(Integer.valueOf(intValue));
                arrayList2.add(Integer.valueOf(intValue));
                arrayList3.add(value);
            }
            i = intValue;
            str = value;
        }
        int ceil = (int) Math.ceil(arrayList.size() / 100.0d);
        int i3 = 0;
        while (i3 < ceil) {
            int size = i3 == ceil - 1 ? arrayList.size() - (i3 * 100) : 100;
            bufferedWriter.write(size + " beginbfrange\n");
            for (int i4 = 0; i4 < size; i4++) {
                int i5 = (i3 * 100) + i4;
                bufferedWriter.write(60);
                bufferedWriter.write(org.a.c.i.c.a(((Integer) arrayList.get(i5)).shortValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write(60);
                bufferedWriter.write(org.a.c.i.c.a(((Integer) arrayList2.get(i5)).shortValue()));
                bufferedWriter.write("> ");
                bufferedWriter.write(60);
                bufferedWriter.write(org.a.c.i.c.a((String) arrayList3.get(i5)));
                bufferedWriter.write(">\n");
            }
            a(bufferedWriter, "endbfrange\n");
            i3++;
        }
        a(bufferedWriter, "endcmap");
        a(bufferedWriter, "CMapName currentdict /CMap defineresource pop");
        a(bufferedWriter, "end");
        a(bufferedWriter, "end");
        bufferedWriter.flush();
    }

    private static void a(BufferedWriter bufferedWriter, String str) {
        bufferedWriter.write(str);
        bufferedWriter.write(10);
    }
}

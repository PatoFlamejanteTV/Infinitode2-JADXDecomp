package org.a.c.h.g.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/g/e/s.class */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    private final List<b> f4628a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(String str) {
        List<String> asList = Arrays.asList(str.replaceAll("\t", SequenceUtils.SPACE).split("\\r\\n|\\n|\\r|\\u2028|\\u2029"));
        this.f4628a = new ArrayList();
        for (String str2 : asList) {
            String str3 = str2;
            if (str2.length() == 0) {
                str3 = SequenceUtils.SPACE;
            }
            this.f4628a.add(new b(str3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<b> a() {
        return this.f4628a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/s$c.class */
    public static class c extends AttributedCharacterIterator.Attribute {

        /* renamed from: a, reason: collision with root package name */
        public static final AttributedCharacterIterator.Attribute f4632a = new c("width");

        private c(String str) {
            super(str);
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/s$b.class */
    static class b {

        /* renamed from: a, reason: collision with root package name */
        private final String f4631a;

        b(String str) {
            this.f4631a = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String a() {
            return this.f4631a;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final List<a> a(org.a.c.h.e.u uVar, float f, float f2) {
            BreakIterator lineInstance = BreakIterator.getLineInstance();
            lineInstance.setText(this.f4631a);
            float f3 = f / 1000.0f;
            int first = lineInstance.first();
            float f4 = 0.0f;
            ArrayList arrayList = new ArrayList();
            a aVar = new a();
            for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
                String substring = this.f4631a.substring(first, next);
                float b2 = uVar.b(substring) * f3;
                float f5 = f4 + b2;
                f4 = f5;
                if (f5 >= f2 && Character.isWhitespace(substring.charAt(substring.length() - 1))) {
                    f4 -= uVar.b(substring.substring(substring.length() - 1)) * f3;
                }
                if (f4 >= f2) {
                    a aVar2 = aVar;
                    aVar2.a(aVar2.a(uVar, f));
                    arrayList.add(aVar);
                    aVar = new a();
                    f4 = uVar.b(substring) * f3;
                }
                AttributedString attributedString = new AttributedString(substring);
                attributedString.addAttribute(c.f4632a, Float.valueOf(b2));
                d dVar = new d(substring);
                dVar.a(attributedString);
                aVar.a(dVar);
                first = next;
            }
            a aVar3 = aVar;
            aVar3.a(aVar3.a(uVar, f));
            arrayList.add(aVar);
            return arrayList;
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/s$a.class */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final List<d> f4629a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        private float f4630b;

        a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final float a() {
            return this.f4630b;
        }

        final void a(float f) {
            this.f4630b = f;
        }

        final float a(org.a.c.h.e.u uVar, float f) {
            float f2 = f / 1000.0f;
            float f3 = 0.0f;
            for (d dVar : this.f4629a) {
                f3 += ((Float) dVar.b().getIterator().getAttribute(c.f4632a)).floatValue();
                String a2 = dVar.a();
                if (this.f4629a.indexOf(dVar) == this.f4629a.size() - 1 && Character.isWhitespace(a2.charAt(a2.length() - 1))) {
                    f3 -= uVar.b(a2.substring(a2.length() - 1)) * f2;
                }
            }
            return f3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final List<d> b() {
            return this.f4629a;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final float b(float f) {
            return (f - this.f4630b) / (this.f4629a.size() - 1);
        }

        final void a(d dVar) {
            this.f4629a.add(dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/s$d.class */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private AttributedString f4633a;

        /* renamed from: b, reason: collision with root package name */
        private final String f4634b;

        d(String str) {
            this.f4634b = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String a() {
            return this.f4634b;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final AttributedString b() {
            return this.f4633a;
        }

        final void a(AttributedString attributedString) {
            this.f4633a = attributedString;
        }
    }
}

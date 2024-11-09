package org.a.c.h.a.b.a;

import java.util.Stack;
import java.util.regex.Pattern;
import org.a.c.h.a.b.a.h;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/e.class */
public final class e extends h.a {

    /* renamed from: a, reason: collision with root package name */
    private final d f4466a = new d();

    /* renamed from: b, reason: collision with root package name */
    private final Stack<d> f4467b = new Stack<>();
    private static final Pattern c = Pattern.compile("[\\+\\-]?\\d+");
    private static final Pattern d = Pattern.compile("[\\-]?\\d*\\.\\d*([Ee]\\-?\\d+)?");

    private e() {
        this.f4467b.push(this.f4466a);
    }

    private d a() {
        return this.f4466a;
    }

    public static d a(CharSequence charSequence) {
        e eVar = new e();
        h.a(charSequence, eVar);
        return eVar.a();
    }

    private d b() {
        return this.f4467b.peek();
    }

    @Override // org.a.c.h.a.b.a.h.c
    public final void b(CharSequence charSequence) {
        a(charSequence.toString());
    }

    private void a(String str) {
        if ("{".equals(str)) {
            d dVar = new d();
            b().a(dVar);
            this.f4467b.push(dVar);
        } else {
            if ("}".equals(str)) {
                this.f4467b.pop();
                return;
            }
            if (c.matcher(str).matches()) {
                b().a(b(str));
            } else if (d.matcher(str).matches()) {
                b().a(c(str));
            } else {
                b().a(str);
            }
        }
    }

    private static int b(String str) {
        return Integer.parseInt(str.startsWith("+") ? str.substring(1) : str);
    }

    private static float c(String str) {
        return Float.parseFloat(str);
    }
}

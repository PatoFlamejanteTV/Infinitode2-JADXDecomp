package com.a.a.c.k.b;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ak.class */
public class ak implements com.d.a.a {
    public static Collection<Map.Entry<Class<?>, Object>> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(URL.class, new as(URL.class));
        hashMap.put(URI.class, new as(URI.class));
        hashMap.put(Currency.class, new as(Currency.class));
        hashMap.put(UUID.class, new av());
        hashMap.put(Pattern.class, new as(Pattern.class));
        hashMap.put(Locale.class, new as(Locale.class));
        hashMap.put(AtomicBoolean.class, a.class);
        hashMap.put(AtomicInteger.class, b.class);
        hashMap.put(AtomicLong.class, c.class);
        hashMap.put(File.class, p.class);
        hashMap.put(Class.class, j.class);
        hashMap.put(Void.class, x.f636a);
        hashMap.put(Void.TYPE, x.f636a);
        return hashMap.entrySet();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ak$a.class */
    public static class a extends an<AtomicBoolean> {
        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((AtomicBoolean) obj, hVar);
        }

        public a() {
            super(AtomicBoolean.class, (byte) 0);
        }

        private static void a(AtomicBoolean atomicBoolean, com.a.a.b.h hVar) {
            hVar.a(atomicBoolean.get());
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ak$b.class */
    public static class b extends an<AtomicInteger> {
        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((AtomicInteger) obj, hVar);
        }

        public b() {
            super(AtomicInteger.class, (byte) 0);
        }

        private static void a(AtomicInteger atomicInteger, com.a.a.b.h hVar) {
            hVar.c(atomicInteger.get());
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ak$c.class */
    public static class c extends an<AtomicLong> {
        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((AtomicLong) obj, hVar);
        }

        public c() {
            super(AtomicLong.class, (byte) 0);
        }

        private static void a(AtomicLong atomicLong, com.a.a.b.h hVar) {
            hVar.b(atomicLong.get());
        }
    }

    @Override // com.d.a.a
    public String a(String str) {
        return str;
    }

    @Override // com.d.a.a
    public String b(String str) {
        return str;
    }

    @Override // com.d.a.a
    public String c(String str) {
        return str;
    }

    @Override // com.d.a.a
    public boolean b() {
        return false;
    }
}

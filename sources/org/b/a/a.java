package org.b.a;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.b.a.a.e;

/* loaded from: infinitode-2.jar:org/b/a/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final org.b.a.a.b f4689a;

    /* renamed from: b, reason: collision with root package name */
    private final org.b.a.a.b f4690b;
    private final org.b.a.a.b c;

    /* synthetic */ a(org.b.a.a.d dVar, e eVar, org.b.a.a.c cVar, byte b2) {
        this(dVar, eVar, cVar);
    }

    private a(org.b.a.a.d dVar, e eVar, org.b.a.a.c cVar) {
        this.f4689a = dVar;
        this.f4690b = eVar;
        this.c = cVar;
    }

    public static C0049a a() {
        return new C0049a((byte) 0);
    }

    public final Iterable<c> a(CharSequence charSequence) {
        return new org.b.a.b(this, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public org.b.a.a.b a(char c) {
        switch (c) {
            case ':':
                return this.f4689a;
            case '@':
                return this.c;
            case 'w':
                return this.f4690b;
            default:
                return null;
        }
    }

    /* renamed from: org.b.a.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/b/a/a$a.class */
    public static class C0049a {

        /* renamed from: a, reason: collision with root package name */
        private Set<d> f4693a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4694b;

        /* synthetic */ C0049a(byte b2) {
            this();
        }

        private C0049a() {
            this.f4693a = EnumSet.allOf(d.class);
            this.f4694b = true;
        }

        public final C0049a a(Set<d> set) {
            if (set == null) {
                throw new NullPointerException("linkTypes must not be null");
            }
            this.f4693a = new HashSet(set);
            return this;
        }

        public final a a() {
            return new a(this.f4693a.contains(d.URL) ? new org.b.a.a.d() : null, this.f4693a.contains(d.WWW) ? new e() : null, this.f4693a.contains(d.EMAIL) ? new org.b.a.a.c(this.f4694b) : null, (byte) 0);
        }
    }

    /* loaded from: infinitode-2.jar:org/b/a/a$b.class */
    class b implements Iterator<c> {

        /* renamed from: a, reason: collision with root package name */
        private final CharSequence f4695a;

        /* renamed from: b, reason: collision with root package name */
        private c f4696b = null;
        private int c = 0;
        private int d = 0;

        public b(CharSequence charSequence) {
            this.f4695a = charSequence;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            b();
            return this.f4696b != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c next() {
            if (hasNext()) {
                c cVar = this.f4696b;
                this.f4696b = null;
                return cVar;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("remove");
        }

        private void b() {
            if (this.f4696b != null) {
                return;
            }
            int length = this.f4695a.length();
            while (this.c < length) {
                org.b.a.a.b a2 = a.this.a(this.f4695a.charAt(this.c));
                if (a2 != null) {
                    c a3 = a2.a(this.f4695a, this.c, this.d);
                    if (a3 != null) {
                        this.f4696b = a3;
                        this.c = a3.getEndIndex();
                        this.d = this.c;
                        return;
                    }
                    this.c++;
                } else {
                    this.c++;
                }
            }
        }
    }
}

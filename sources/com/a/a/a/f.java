package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/f.class */
public @interface f {
    b a() default b.DEFAULT;

    b b() default b.DEFAULT;

    b c() default b.DEFAULT;

    b d() default b.DEFAULT;

    b e() default b.DEFAULT;

    /* loaded from: infinitode-2.jar:com/a/a/a/f$b.class */
    public enum b {
        ANY,
        NON_PRIVATE,
        PROTECTED_AND_PUBLIC,
        PUBLIC_ONLY,
        NONE,
        DEFAULT;

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
        public final boolean a(Member member) {
            switch (g.f61a[ordinal()]) {
                case 1:
                    return true;
                case 2:
                    return false;
                case 3:
                    return !Modifier.isPrivate(member.getModifiers());
                case 4:
                    if (Modifier.isProtected(member.getModifiers())) {
                        return true;
                    }
                case 5:
                    return Modifier.isPublic(member.getModifiers());
                default:
                    return false;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/a/f$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static final b f57a = b.PUBLIC_ONLY;

        /* renamed from: b, reason: collision with root package name */
        private b f58b;
        private b c;
        private b d;
        private b e;
        private b f;

        static {
            b bVar = f57a;
            b bVar2 = b.PUBLIC_ONLY;
            new a(bVar, bVar2, bVar2, b.ANY, b.PUBLIC_ONLY);
            b bVar3 = b.DEFAULT;
            b bVar4 = b.DEFAULT;
            new a(bVar3, bVar3, bVar4, bVar4, b.DEFAULT);
        }

        private a(b bVar, b bVar2, b bVar3, b bVar4, b bVar5) {
            this.f58b = bVar;
            this.c = bVar2;
            this.d = bVar3;
            this.e = bVar4;
            this.f = bVar5;
        }

        public final b a() {
            return this.f58b;
        }

        public final b b() {
            return this.c;
        }

        public final b c() {
            return this.d;
        }

        public final b d() {
            return this.e;
        }

        public final b e() {
            return this.f;
        }

        public final String toString() {
            return String.format("JsonAutoDetect.Value(fields=%s,getters=%s,isGetters=%s,setters=%s,creators=%s)", this.f58b, this.c, this.d, this.e, this.f);
        }

        public final int hashCode() {
            return ((1 + this.f58b.ordinal()) ^ (((3 * this.c.ordinal()) - (7 * this.d.ordinal())) + (11 * this.e.ordinal()))) ^ (13 * this.f.ordinal());
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && a(this, (a) obj);
        }

        private static boolean a(a aVar, a aVar2) {
            return aVar.f58b == aVar2.f58b && aVar.c == aVar2.c && aVar.d == aVar2.d && aVar.e == aVar2.e && aVar.f == aVar2.f;
        }
    }
}

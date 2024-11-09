package com.a.a.c.c.a;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/l.class */
public abstract class l {
    public abstract l E();

    public abstract String G();

    public static com.a.a.c.k<?> c(com.a.a.c.j jVar) {
        String name = jVar.b().getName();
        if (!name.startsWith("java.util.")) {
            return null;
        }
        String b2 = b(name);
        if (b2 != null) {
            a aVar = null;
            String f = f(b2);
            if (f != null) {
                if (f.endsWith("Set")) {
                    aVar = a(4, jVar, Set.class);
                } else if (f.endsWith("List")) {
                    aVar = a(5, jVar, List.class);
                }
            } else {
                String d = d(b2);
                if (d != null) {
                    if (d.endsWith("Set")) {
                        aVar = a(1, jVar, Set.class);
                    } else if (d.endsWith("List")) {
                        aVar = a(2, jVar, List.class);
                    }
                } else {
                    String e = e(b2);
                    if (e != null) {
                        if (e.endsWith("Set")) {
                            aVar = a(7, jVar, Set.class);
                        } else if (e.endsWith("List")) {
                            aVar = a(9, jVar, List.class);
                        } else if (e.endsWith("Collection")) {
                            aVar = a(8, jVar, Collection.class);
                        }
                    }
                }
            }
            if (aVar == null) {
                return null;
            }
            return new com.a.a.c.c.b.ad(aVar);
        }
        String a2 = a(name);
        if (a2 != null) {
            if (a2.contains("List")) {
                return new com.a.a.c.c.b.ad(a(11, jVar, List.class));
            }
            return null;
        }
        String c = c(name);
        if (c != null) {
            if (c.contains("List")) {
                return new com.a.a.c.c.b.ad(a(11, jVar, List.class));
            }
            if (c.contains("Set")) {
                return new com.a.a.c.c.b.ad(a(4, jVar, Set.class));
            }
            return null;
        }
        return null;
    }

    public static com.a.a.c.k<?> d(com.a.a.c.j jVar) {
        String name = jVar.b().getName();
        a aVar = null;
        String b2 = b(name);
        if (b2 != null) {
            String f = f(b2);
            if (f != null) {
                if (f.contains("Map")) {
                    aVar = a(6, jVar, Map.class);
                }
            } else {
                String d = d(b2);
                if (d != null) {
                    if (d.contains("Map")) {
                        aVar = a(3, jVar, Map.class);
                    }
                } else {
                    String e = e(b2);
                    if (e != null && e.contains("Map")) {
                        aVar = a(10, jVar, Map.class);
                    }
                }
            }
        } else {
            String c = c(name);
            if (c != null && c.contains("Map")) {
                aVar = a(6, jVar, Map.class);
            }
        }
        if (aVar == null) {
            return null;
        }
        return new com.a.a.c.c.b.ad(aVar);
    }

    private static a a(int i, com.a.a.c.j jVar, Class<?> cls) {
        return new a(i, jVar.d(cls));
    }

    private static String a(String str) {
        if (str.startsWith("java.util.Arrays$")) {
            return str.substring(17);
        }
        return null;
    }

    private static String b(String str) {
        if (str.startsWith("java.util.Collections$")) {
            return str.substring(22);
        }
        return null;
    }

    private static String c(String str) {
        if (str.startsWith("java.util.ImmutableCollections$")) {
            return str.substring(31);
        }
        return null;
    }

    private static String d(String str) {
        if (str.startsWith("Singleton")) {
            return str.substring(9);
        }
        return null;
    }

    private static String e(String str) {
        if (str.startsWith("Synchronized")) {
            return str.substring(12);
        }
        return null;
    }

    private static String f(String str) {
        if (str.startsWith("Unmodifiable")) {
            return str.substring(12);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/l$a.class */
    public static class a implements com.a.a.c.m.k<Object, Object> {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.j f273a;

        /* renamed from: b, reason: collision with root package name */
        private final int f274b;

        a(int i, com.a.a.c.j jVar) {
            this.f273a = jVar;
            this.f274b = i;
        }

        @Override // com.a.a.c.m.k
        public final Object a(Object obj) {
            if (obj == null) {
                return null;
            }
            switch (this.f274b) {
                case 1:
                    Set set = (Set) obj;
                    a(set.size());
                    return Collections.singleton(set.iterator().next());
                case 2:
                    List list = (List) obj;
                    a(list.size());
                    return Collections.singletonList(list.get(0));
                case 3:
                    Map map = (Map) obj;
                    a(map.size());
                    Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                    return Collections.singletonMap(entry.getKey(), entry.getValue());
                case 4:
                    return Collections.unmodifiableSet((Set) obj);
                case 5:
                    return Collections.unmodifiableList((List) obj);
                case 6:
                    return Collections.unmodifiableMap((Map) obj);
                case 7:
                    return Collections.synchronizedSet((Set) obj);
                case 8:
                    return Collections.synchronizedCollection((Collection) obj);
                case 9:
                    return Collections.synchronizedList((List) obj);
                case 10:
                    return Collections.synchronizedMap((Map) obj);
                default:
                    return obj;
            }
        }

        @Override // com.a.a.c.m.k
        public final com.a.a.c.j a() {
            return this.f273a;
        }

        @Override // com.a.a.c.m.k
        public final com.a.a.c.j b() {
            return this.f273a;
        }

        private static void a(int i) {
            if (i != 1) {
                throw new IllegalArgumentException("Can not deserialize Singleton container from " + i + " entries");
            }
        }
    }

    public boolean F() {
        return E() != null;
    }
}

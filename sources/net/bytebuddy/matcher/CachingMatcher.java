package net.bytebuddy.matcher;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance(permitSubclassEquality = true)
/* loaded from: infinitode-2.jar:net/bytebuddy/matcher/CachingMatcher.class */
public class CachingMatcher<T> extends ElementMatcher.Junction.AbstractBase<T> {
    private static final Object NULL_VALUE = new Object();
    private final ElementMatcher<? super T> matcher;

    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    protected final ConcurrentMap<? super T, Boolean> map;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CachingMatcher) && this.matcher.equals(((CachingMatcher) obj).matcher);
    }

    public int hashCode() {
        return (CachingMatcher.class.hashCode() * 31) + this.matcher.hashCode();
    }

    public CachingMatcher(ElementMatcher<? super T> elementMatcher, ConcurrentMap<? super T, Boolean> concurrentMap) {
        this.matcher = elementMatcher;
        this.map = concurrentMap;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(@MaybeNull T t) {
        Boolean bool = this.map.get(t == null ? NULL_VALUE : t);
        Boolean bool2 = bool;
        if (bool == null) {
            bool2 = Boolean.valueOf(onCacheMiss(t));
        }
        return bool2.booleanValue();
    }

    protected boolean onCacheMiss(@MaybeNull T t) {
        boolean matches = this.matcher.matches(t);
        this.map.put(t == null ? (Object) NULL_VALUE : (Object) t, Boolean.valueOf(matches));
        return matches;
    }

    public String toString() {
        return "cached(" + this.matcher + ")";
    }

    @SuppressFBWarnings(value = {"EQ_DOESNT_OVERRIDE_EQUALS"}, justification = "Equality does not consider eviction size.")
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/CachingMatcher$WithInlineEviction.class */
    public static class WithInlineEviction<S> extends CachingMatcher<S> {
        private final int evictionSize;

        public WithInlineEviction(ElementMatcher<? super S> elementMatcher, ConcurrentMap<? super S, Boolean> concurrentMap, int i) {
            super(elementMatcher, concurrentMap);
            this.evictionSize = i;
        }

        @Override // net.bytebuddy.matcher.CachingMatcher
        protected boolean onCacheMiss(@MaybeNull S s) {
            if (this.map.size() >= this.evictionSize) {
                Iterator<Map.Entry<? super T, Boolean>> it = this.map.entrySet().iterator();
                it.next();
                it.remove();
            }
            return super.onCacheMiss(s);
        }
    }
}

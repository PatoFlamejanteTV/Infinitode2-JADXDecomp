package net.bytebuddy.matcher;

import java.lang.ClassLoader;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/matcher/ClassLoaderParentMatcher.class */
public class ClassLoaderParentMatcher<T extends ClassLoader> extends ElementMatcher.Junction.AbstractBase<T> {

    @MaybeNull
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
    private final ClassLoader classLoader;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClassLoader classLoader = this.classLoader;
        ClassLoader classLoader2 = ((ClassLoaderParentMatcher) obj).classLoader;
        return classLoader2 != null ? classLoader != null && classLoader.equals(classLoader2) : classLoader == null;
    }

    public int hashCode() {
        int hashCode = getClass().hashCode() * 31;
        ClassLoader classLoader = this.classLoader;
        return classLoader != null ? hashCode + classLoader.hashCode() : hashCode;
    }

    public ClassLoaderParentMatcher(@MaybeNull ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override // net.bytebuddy.matcher.ElementMatcher
    public boolean matches(@MaybeNull T t) {
        ClassLoader classLoader = this.classLoader;
        while (true) {
            T t2 = classLoader;
            if (t2 == null) {
                return t == null;
            }
            if (t2 == t) {
                return true;
            }
            classLoader = t2.getParent();
        }
    }

    public String toString() {
        return "isParentOf(" + this.classLoader + ')';
    }
}

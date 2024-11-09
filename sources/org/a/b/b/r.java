package org.a.b.b;

import java.util.List;
import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/b/b/r.class */
public abstract class r {
    public abstract List<Number> a(List<Number> list, q qVar);

    public final List<Number> a(List<Object> list) {
        Stack stack = new Stack();
        for (Object obj : list) {
            if (obj instanceof q) {
                List<Number> a2 = a(stack, (q) obj);
                stack.clear();
                if (a2 != null) {
                    stack.addAll(a2);
                }
            } else {
                stack.push((Number) obj);
            }
        }
        return stack;
    }
}

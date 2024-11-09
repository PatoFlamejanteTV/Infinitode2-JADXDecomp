package com.prineside.tdi2.utils;

@REGS(classOnly = true)
@FunctionalInterface
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ObjectFilter.class */
public interface ObjectFilter<T> {
    boolean test(T t);
}

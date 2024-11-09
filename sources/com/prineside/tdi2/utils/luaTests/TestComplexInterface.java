package com.prineside.tdi2.utils.luaTests;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/luaTests/TestComplexInterface.class */
public interface TestComplexInterface {
    @TestAnnotationRuntimeRetention
    String getSomeString();

    int getSomeInt(int i);

    void end(String str);
}

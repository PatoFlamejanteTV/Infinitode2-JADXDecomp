package com.prineside.tdi2.utils.luaTests;

import java.util.Arrays;
import org.lwjgl.system.windows.User32;

@TestAnnotationClassRetention
@TestAnnotationRuntimeRetention
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/luaTests/TestClass.class */
public class TestClass implements TestInterfaceWithBlacklistedMethod {

    @TestAnnotationRuntimeRetention
    public static final int SOME_STATIC_FINAL_VAR = 42;

    @TestAnnotationClassRetention
    public int someField;
    public String someBlacklistedField;

    @TestAnnotationRuntimeRetention
    public int someOtherField;

    @TestAnnotationClassRetention
    public static int SOME_STATIC_VAR = 1337;
    public static TestClass SINGLETON = new TestClass(9001);
    public static float SOME_BLACKLISTED_STATIC_FIELD = 17.4f;

    public TestClass(@TestAnnotationClassRetention int i) {
        this.someBlacklistedField = "Secret";
        this.someField = i;
    }

    public TestClass(int... iArr) {
        this.someBlacklistedField = "Secret";
        if (iArr.length > 0) {
            this.someField = iArr[0];
        }
        if (iArr.length > 1) {
            this.someOtherField = iArr[1];
        }
    }

    public TestClass(int i, Object... objArr) {
        this.someBlacklistedField = "Secret";
        this.someField = i;
        this.someBlacklistedField = Arrays.toString(objArr);
    }

    public TestClass(String str) {
        this.someBlacklistedField = "Secret";
        throw new IllegalStateException("Test failed - blacklisted constructor called");
    }

    @TestAnnotationClassRetention
    public int getSomeField() {
        return this.someField;
    }

    @TestAnnotationRuntimeRetention
    public void setSomeField(@TestAnnotationRuntimeRetention int i) {
        this.someField = i;
    }

    public void someBlacklistedField() {
        throw new IllegalStateException("Test failed - blacklisted method called");
    }

    @Override // com.prineside.tdi2.utils.luaTests.TestInterfaceWithBlacklistedMethod
    public int thisMethodIsWhitelisted() {
        return User32.WM_PAINTCLIPBOARD;
    }

    @Override // com.prineside.tdi2.utils.luaTests.TestInterfaceWithBlacklistedMethod
    public int thisMethodIsBlacklisted() {
        throw new IllegalStateException("Test failed - blacklisted interface method called");
    }

    public String sameNameMethod(int i) {
        return "int|" + i;
    }

    public String sameNameMethod(float f) {
        return "float|" + f;
    }

    public String sameNameMethod(String str) {
        return "string|" + str;
    }

    public String sameNameMethod(int i, int i2) {
        return "ints|" + i + "|" + i2;
    }

    public String varArgMethodA(Object... objArr) {
        return Arrays.toString(objArr);
    }

    public String varArgMethodB(String str, Object... objArr) {
        return str + Arrays.toString(objArr);
    }

    public String varArgMethodC(String str, String... strArr) {
        return str + Arrays.toString(strArr);
    }

    public String varArgMethodD(String str, TestClass... testClassArr) {
        return str + Arrays.toString(testClassArr);
    }

    public static String staticVarArgMethodA(Object... objArr) {
        return Arrays.toString(objArr);
    }

    public static String staticVarArgMethodB(String str, Object... objArr) {
        return str + Arrays.toString(objArr);
    }

    public static String staticVarArgMethodC(String str, String... strArr) {
        return str + Arrays.toString(strArr);
    }

    public static String staticVarArgMethodD(String str, TestClass... testClassArr) {
        return str + Arrays.toString(testClassArr);
    }

    public static String staticVarArgMethodE(String str, int[]... iArr) {
        return str + Arrays.toString(iArr);
    }

    public static String staticVarArgMethodF(String str, TestClass[]... testClassArr) {
        return str + testClassArr.length + (testClassArr.length == 0 ? "none" : Arrays.toString(testClassArr[0]));
    }

    public String toString() {
        return this.someBlacklistedField + this.someField;
    }
}

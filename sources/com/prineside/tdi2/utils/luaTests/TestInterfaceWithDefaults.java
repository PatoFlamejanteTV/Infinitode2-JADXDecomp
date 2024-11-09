package com.prineside.tdi2.utils.luaTests;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/luaTests/TestInterfaceWithDefaults.class */
public interface TestInterfaceWithDefaults {
    int getIntMultipliedByTwo(int i);

    default String getAsStringDefaultMethod(int i) {
        return Defaults.getAsStringDefaultMethod(this, i);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/luaTests/TestInterfaceWithDefaults$Defaults.class */
    public static final class Defaults {
        public static String getAsStringDefaultMethod(TestInterfaceWithDefaults testInterfaceWithDefaults, int i) {
            return "value as string: " + testInterfaceWithDefaults.getIntMultipliedByTwo(i);
        }
    }
}

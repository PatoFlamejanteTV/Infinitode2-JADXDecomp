package com.badlogic.gdx;

import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Preferences.class */
public interface Preferences {
    Preferences putBoolean(String str, boolean z);

    Preferences putInteger(String str, int i);

    Preferences putLong(String str, long j);

    Preferences putFloat(String str, float f);

    Preferences putString(String str, String str2);

    Preferences put(Map<String, ?> map);

    boolean getBoolean(String str);

    int getInteger(String str);

    long getLong(String str);

    float getFloat(String str);

    String getString(String str);

    boolean getBoolean(String str, boolean z);

    int getInteger(String str, int i);

    long getLong(String str, long j);

    float getFloat(String str, float f);

    String getString(String str, String str2);

    Map<String, ?> get();

    boolean contains(String str);

    void clear();

    void remove(String str);

    void flush();
}

package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Preferences.class */
public class Lwjgl3Preferences implements Preferences {
    private final Properties properties;
    private final FileHandle file;

    public Lwjgl3Preferences(String str, String str2) {
        this(new Lwjgl3FileHandle(new File(str2, str), Files.FileType.External));
    }

    public Lwjgl3Preferences(FileHandle fileHandle) {
        this.properties = new Properties();
        this.file = fileHandle;
        if (fileHandle.exists()) {
            Throwable th = null;
            BufferedInputStream bufferedInputStream = null;
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(fileHandle.read());
                    this.properties.loadFromXML(bufferedInputStream);
                    StreamUtils.closeQuietly(bufferedInputStream);
                } catch (Throwable th2) {
                    th.printStackTrace();
                    StreamUtils.closeQuietly(bufferedInputStream);
                }
            } catch (Throwable th3) {
                StreamUtils.closeQuietly(bufferedInputStream);
                throw th3;
            }
        }
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences putBoolean(String str, boolean z) {
        this.properties.put(str, Boolean.toString(z));
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences putInteger(String str, int i) {
        this.properties.put(str, Integer.toString(i));
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences putLong(String str, long j) {
        this.properties.put(str, Long.toString(j));
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences putFloat(String str, float f) {
        this.properties.put(str, Float.toString(f));
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences putString(String str, String str2) {
        this.properties.put(str, str2);
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public Preferences put(Map<String, ?> map) {
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                putBoolean(entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            }
            if (entry.getValue() instanceof Integer) {
                putInteger(entry.getKey(), ((Integer) entry.getValue()).intValue());
            }
            if (entry.getValue() instanceof Long) {
                putLong(entry.getKey(), ((Long) entry.getValue()).longValue());
            }
            if (entry.getValue() instanceof String) {
                putString(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Float) {
                putFloat(entry.getKey(), ((Float) entry.getValue()).floatValue());
            }
        }
        return this;
    }

    @Override // com.badlogic.gdx.Preferences
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override // com.badlogic.gdx.Preferences
    public int getInteger(String str) {
        return getInteger(str, 0);
    }

    @Override // com.badlogic.gdx.Preferences
    public long getLong(String str) {
        return getLong(str, 0L);
    }

    @Override // com.badlogic.gdx.Preferences
    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    @Override // com.badlogic.gdx.Preferences
    public String getString(String str) {
        return getString(str, "");
    }

    @Override // com.badlogic.gdx.Preferences
    public boolean getBoolean(String str, boolean z) {
        return Boolean.parseBoolean(this.properties.getProperty(str, Boolean.toString(z)));
    }

    @Override // com.badlogic.gdx.Preferences
    public int getInteger(String str, int i) {
        return Integer.parseInt(this.properties.getProperty(str, Integer.toString(i)));
    }

    @Override // com.badlogic.gdx.Preferences
    public long getLong(String str, long j) {
        return Long.parseLong(this.properties.getProperty(str, Long.toString(j)));
    }

    @Override // com.badlogic.gdx.Preferences
    public float getFloat(String str, float f) {
        return Float.parseFloat(this.properties.getProperty(str, Float.toString(f)));
    }

    @Override // com.badlogic.gdx.Preferences
    public String getString(String str, String str2) {
        return this.properties.getProperty(str, str2);
    }

    @Override // com.badlogic.gdx.Preferences
    public Map<String, ?> get() {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.properties.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                hashMap.put((String) entry.getKey(), Boolean.valueOf(Boolean.parseBoolean((String) entry.getValue())));
            }
            if (entry.getValue() instanceof Integer) {
                hashMap.put((String) entry.getKey(), Integer.valueOf(Integer.parseInt((String) entry.getValue())));
            }
            if (entry.getValue() instanceof Long) {
                hashMap.put((String) entry.getKey(), Long.valueOf(Long.parseLong((String) entry.getValue())));
            }
            if (entry.getValue() instanceof String) {
                hashMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Float) {
                hashMap.put((String) entry.getKey(), Float.valueOf(Float.parseFloat((String) entry.getValue())));
            }
        }
        return hashMap;
    }

    @Override // com.badlogic.gdx.Preferences
    public boolean contains(String str) {
        return this.properties.containsKey(str);
    }

    @Override // com.badlogic.gdx.Preferences
    public void clear() {
        this.properties.clear();
    }

    @Override // com.badlogic.gdx.Preferences
    public void flush() {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(this.file.write(false));
                this.properties.storeToXML(bufferedOutputStream, null);
                StreamUtils.closeQuietly(bufferedOutputStream);
            } catch (Exception e) {
                throw new GdxRuntimeException("Error writing preferences: " + this.file, e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(bufferedOutputStream);
            throw th;
        }
    }

    @Override // com.badlogic.gdx.Preferences
    public void remove(String str) {
        this.properties.remove(str);
    }
}

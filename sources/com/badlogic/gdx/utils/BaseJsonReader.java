package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/BaseJsonReader.class */
public interface BaseJsonReader {
    JsonValue parse(InputStream inputStream);

    JsonValue parse(FileHandle fileHandle);
}

package com.prineside.tdi2.utils;

import com.badlogic.gdx.files.FileHandle;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileChooser.class */
public interface FileChooser {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileChooser$Callback.class */
    public interface Callback {
        void onFileChoose(FileHandle fileHandle);

        void onCancel();

        void onError(Exception exc);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileChooser$FileChooseIntent.class */
    public enum FileChooseIntent {
        OPEN,
        SAVE
    }

    void choose(Configuration configuration, Callback callback);

    boolean intentSupported(FileChooseIntent fileChooseIntent);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FileChooser$Configuration.class */
    public static class Configuration {
        public FileHandle directory;
        public String title;
        public FileChooseIntent intent = FileChooseIntent.OPEN;

        public static Pattern mimePattern(String str) {
            return Pattern.compile(str.replaceAll("/", "\\\\/").replace("*", ".*"));
        }
    }
}

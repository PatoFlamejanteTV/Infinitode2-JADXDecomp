package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/resolvers/ResolutionFileResolver.class */
public class ResolutionFileResolver implements FileHandleResolver {
    protected final FileHandleResolver baseResolver;
    protected final Resolution[] descriptors;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/resolvers/ResolutionFileResolver$Resolution.class */
    public static class Resolution {
        public final int portraitWidth;
        public final int portraitHeight;
        public final String folder;

        public Resolution(int i, int i2, String str) {
            this.portraitWidth = i;
            this.portraitHeight = i2;
            this.folder = str;
        }
    }

    public ResolutionFileResolver(FileHandleResolver fileHandleResolver, Resolution... resolutionArr) {
        if (resolutionArr.length == 0) {
            throw new IllegalArgumentException("At least one Resolution needs to be supplied.");
        }
        this.baseResolver = fileHandleResolver;
        this.descriptors = resolutionArr;
    }

    @Override // com.badlogic.gdx.assets.loaders.FileHandleResolver
    public FileHandle resolve(String str) {
        Resolution choose = choose(this.descriptors);
        FileHandle resolve = this.baseResolver.resolve(resolve(new FileHandle(str), choose.folder));
        FileHandle fileHandle = resolve;
        if (!resolve.exists()) {
            fileHandle = this.baseResolver.resolve(str);
        }
        return fileHandle;
    }

    protected String resolve(FileHandle fileHandle, String str) {
        String str2 = "";
        FileHandle parent = fileHandle.parent();
        if (parent != null && !parent.name().equals("")) {
            str2 = parent + "/";
        }
        return str2 + str + "/" + fileHandle.name();
    }

    public static Resolution choose(Resolution... resolutionArr) {
        int backBufferWidth = Gdx.graphics.getBackBufferWidth();
        int backBufferHeight = Gdx.graphics.getBackBufferHeight();
        Resolution resolution = resolutionArr[0];
        if (backBufferWidth < backBufferHeight) {
            int length = resolutionArr.length;
            for (int i = 0; i < length; i++) {
                Resolution resolution2 = resolutionArr[i];
                if (backBufferWidth >= resolution2.portraitWidth && resolution2.portraitWidth >= resolution.portraitWidth && backBufferHeight >= resolution2.portraitHeight && resolution2.portraitHeight >= resolution.portraitHeight) {
                    resolution = resolutionArr[i];
                }
            }
        } else {
            int length2 = resolutionArr.length;
            for (int i2 = 0; i2 < length2; i2++) {
                Resolution resolution3 = resolutionArr[i2];
                if (backBufferWidth >= resolution3.portraitHeight && resolution3.portraitHeight >= resolution.portraitHeight && backBufferHeight >= resolution3.portraitWidth && resolution3.portraitWidth >= resolution.portraitWidth) {
                    resolution = resolutionArr[i2];
                }
            }
        }
        return resolution;
    }
}

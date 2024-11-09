package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/resolvers/PrefixFileHandleResolver.class */
public class PrefixFileHandleResolver implements FileHandleResolver {
    private String prefix;
    private FileHandleResolver baseResolver;

    public PrefixFileHandleResolver(FileHandleResolver fileHandleResolver, String str) {
        this.baseResolver = fileHandleResolver;
        this.prefix = str;
    }

    public void setBaseResolver(FileHandleResolver fileHandleResolver) {
        this.baseResolver = fileHandleResolver;
    }

    public FileHandleResolver getBaseResolver() {
        return this.baseResolver;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override // com.badlogic.gdx.assets.loaders.FileHandleResolver
    public FileHandle resolve(String str) {
        return this.baseResolver.resolve(this.prefix + str);
    }
}

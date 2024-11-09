package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ShaderProgramLoader.class */
public class ShaderProgramLoader extends AsynchronousAssetLoader<ShaderProgram, ShaderProgramParameter> {
    private String vertexFileSuffix;
    private String fragmentFileSuffix;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/loaders/ShaderProgramLoader$ShaderProgramParameter.class */
    public static class ShaderProgramParameter extends AssetLoaderParameters<ShaderProgram> {
        public String vertexFile;
        public String fragmentFile;
        public boolean logOnCompileFailure = true;
        public String prependVertexCode;
        public String prependFragmentCode;
    }

    public ShaderProgramLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.vertexFileSuffix = ".vert";
        this.fragmentFileSuffix = ".frag";
    }

    public ShaderProgramLoader(FileHandleResolver fileHandleResolver, String str, String str2) {
        super(fileHandleResolver);
        this.vertexFileSuffix = ".vert";
        this.fragmentFileSuffix = ".frag";
        this.vertexFileSuffix = str;
        this.fragmentFileSuffix = str2;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, ShaderProgramParameter shaderProgramParameter) {
        return null;
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, ShaderProgramParameter shaderProgramParameter) {
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public ShaderProgram loadSync(AssetManager assetManager, String str, FileHandle fileHandle, ShaderProgramParameter shaderProgramParameter) {
        String str2 = null;
        String str3 = null;
        if (shaderProgramParameter != null) {
            if (shaderProgramParameter.vertexFile != null) {
                str2 = shaderProgramParameter.vertexFile;
            }
            if (shaderProgramParameter.fragmentFile != null) {
                str3 = shaderProgramParameter.fragmentFile;
            }
        }
        if (str2 == null && str.endsWith(this.fragmentFileSuffix)) {
            str2 = str.substring(0, str.length() - this.fragmentFileSuffix.length()) + this.vertexFileSuffix;
        }
        if (str3 == null && str.endsWith(this.vertexFileSuffix)) {
            str3 = str.substring(0, str.length() - this.vertexFileSuffix.length()) + this.fragmentFileSuffix;
        }
        FileHandle resolve = str2 == null ? fileHandle : resolve(str2);
        FileHandle resolve2 = str3 == null ? fileHandle : resolve(str3);
        String readString = resolve.readString();
        String readString2 = resolve.equals(resolve2) ? readString : resolve2.readString();
        if (shaderProgramParameter != null) {
            if (shaderProgramParameter.prependVertexCode != null) {
                readString = shaderProgramParameter.prependVertexCode + readString;
            }
            if (shaderProgramParameter.prependFragmentCode != null) {
                readString2 = shaderProgramParameter.prependFragmentCode + readString2;
            }
        }
        ShaderProgram shaderProgram = new ShaderProgram(readString, readString2);
        if ((shaderProgramParameter == null || shaderProgramParameter.logOnCompileFailure) && !shaderProgram.isCompiled()) {
            assetManager.getLogger().error("ShaderProgram " + str + " failed to compile:\n" + shaderProgram.getLog());
        }
        return shaderProgram;
    }
}

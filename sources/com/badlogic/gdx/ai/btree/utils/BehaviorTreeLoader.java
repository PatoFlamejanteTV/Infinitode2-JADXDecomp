package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeLoader.class */
public class BehaviorTreeLoader extends AsynchronousAssetLoader<BehaviorTree, BehaviorTreeParameter> {
    BehaviorTree behaviorTree;

    public BehaviorTreeLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public void loadAsync(AssetManager assetManager, String str, FileHandle fileHandle, BehaviorTreeParameter behaviorTreeParameter) {
        this.behaviorTree = null;
        Object obj = null;
        BehaviorTreeParser behaviorTreeParser = null;
        if (behaviorTreeParameter != null) {
            obj = behaviorTreeParameter.blackboard;
            behaviorTreeParser = behaviorTreeParameter.parser;
        }
        if (behaviorTreeParser == null) {
            behaviorTreeParser = new BehaviorTreeParser();
        }
        Reader reader = null;
        try {
            reader = fileHandle.reader();
            this.behaviorTree = behaviorTreeParser.parse(reader, (Reader) obj);
            StreamUtils.closeQuietly(reader);
        } catch (Throwable th) {
            StreamUtils.closeQuietly(reader);
            throw th;
        }
    }

    @Override // com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader
    public BehaviorTree loadSync(AssetManager assetManager, String str, FileHandle fileHandle, BehaviorTreeParameter behaviorTreeParameter) {
        BehaviorTree behaviorTree = this.behaviorTree;
        this.behaviorTree = null;
        return behaviorTree;
    }

    @Override // com.badlogic.gdx.assets.loaders.AssetLoader
    public Array<AssetDescriptor> getDependencies(String str, FileHandle fileHandle, BehaviorTreeParameter behaviorTreeParameter) {
        return null;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeLoader$BehaviorTreeParameter.class */
    public static class BehaviorTreeParameter extends AssetLoaderParameters<BehaviorTree> {
        public final Object blackboard;
        public final BehaviorTreeParser parser;

        public BehaviorTreeParameter() {
            this(null);
        }

        public BehaviorTreeParameter(Object obj) {
            this(obj, null);
        }

        public BehaviorTreeParameter(Object obj, BehaviorTreeParser behaviorTreeParser) {
            this.blackboard = obj;
            this.parser = behaviorTreeParser;
        }
    }
}

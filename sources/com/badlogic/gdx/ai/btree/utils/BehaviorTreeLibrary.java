package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeLibrary.class */
public class BehaviorTreeLibrary {
    protected ObjectMap<String, BehaviorTree<?>> repository;
    protected FileHandleResolver resolver;
    protected BehaviorTreeParser<?> parser;

    public BehaviorTreeLibrary() {
        this(0);
    }

    public BehaviorTreeLibrary(int i) {
        this(GdxAI.getFileSystem().newResolver(Files.FileType.Internal), i);
    }

    public BehaviorTreeLibrary(FileHandleResolver fileHandleResolver) {
        this(fileHandleResolver, 0);
    }

    public BehaviorTreeLibrary(FileHandleResolver fileHandleResolver, int i) {
        this(fileHandleResolver, null, i);
    }

    private BehaviorTreeLibrary(FileHandleResolver fileHandleResolver, AssetManager assetManager, int i) {
        this.resolver = fileHandleResolver;
        this.repository = new ObjectMap<>();
        this.parser = new BehaviorTreeParser<>(i);
    }

    public <T> Task<T> createRootTask(String str) {
        return (Task<T>) retrieveArchetypeTree(str).getChild(0).cloneTask();
    }

    public <T> BehaviorTree<T> createBehaviorTree(String str) {
        return createBehaviorTree(str, null);
    }

    public <T> BehaviorTree<T> createBehaviorTree(String str, T t) {
        BehaviorTree<T> behaviorTree = (BehaviorTree) retrieveArchetypeTree(str).cloneTask();
        behaviorTree.setObject(t);
        return behaviorTree;
    }

    protected BehaviorTree<?> retrieveArchetypeTree(String str) {
        BehaviorTree<?> behaviorTree = this.repository.get(str);
        BehaviorTree<?> behaviorTree2 = behaviorTree;
        if (behaviorTree == null) {
            behaviorTree2 = this.parser.parse(this.resolver.resolve(str), (FileHandle) null);
            registerArchetypeTree(str, behaviorTree2);
        }
        return behaviorTree2;
    }

    public void registerArchetypeTree(String str, BehaviorTree<?> behaviorTree) {
        if (behaviorTree == null) {
            throw new IllegalArgumentException("The registered archetype must not be null.");
        }
        this.repository.put(str, behaviorTree);
    }

    public boolean hasArchetypeTree(String str) {
        return this.repository.containsKey(str);
    }

    public void disposeBehaviorTree(String str, BehaviorTree<?> behaviorTree) {
        if (Task.TASK_CLONER != null) {
            Task.TASK_CLONER.freeTask(behaviorTree);
        }
    }
}

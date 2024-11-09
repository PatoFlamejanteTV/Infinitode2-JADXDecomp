package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/PooledBehaviorTreeLibrary.class */
public class PooledBehaviorTreeLibrary extends BehaviorTreeLibrary {
    protected ObjectMap<String, Pool<BehaviorTree>> pools = new ObjectMap<>();

    protected Pool<BehaviorTree> getPool(final String str) {
        Pool<BehaviorTree> pool = this.pools.get(str);
        Pool<BehaviorTree> pool2 = pool;
        if (pool == null) {
            pool2 = new Pool<BehaviorTree>() { // from class: com.badlogic.gdx.ai.btree.utils.PooledBehaviorTreeLibrary.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.badlogic.gdx.utils.Pool
                public BehaviorTree newObject() {
                    return PooledBehaviorTreeLibrary.this.newBehaviorTree(str);
                }
            };
            this.pools.put(str, pool2);
        }
        return pool2;
    }

    protected <T> BehaviorTree<T> newBehaviorTree(String str) {
        return super.createBehaviorTree(str, null);
    }

    @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary
    public <T> BehaviorTree<T> createBehaviorTree(String str, T t) {
        BehaviorTree<T> obtain = getPool(str).obtain();
        obtain.setObject(t);
        return obtain;
    }

    @Override // com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary
    public void disposeBehaviorTree(String str, BehaviorTree<?> behaviorTree) {
        getPool(str).free(behaviorTree);
    }

    public void clear(String str) {
        Pool<BehaviorTree> pool = this.pools.get(str);
        if (pool != null) {
            pool.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clear() {
        ObjectMap.Entries<String, Pool<BehaviorTree>> it = this.pools.entries().iterator();
        while (it.hasNext()) {
            ((Pool) it.next().value).clear();
        }
        this.pools.clear();
    }
}

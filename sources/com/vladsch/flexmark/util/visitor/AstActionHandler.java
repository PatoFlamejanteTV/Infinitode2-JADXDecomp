package com.vladsch.flexmark.util.visitor;

import com.vladsch.flexmark.util.visitor.AstAction;
import com.vladsch.flexmark.util.visitor.AstActionHandler;
import com.vladsch.flexmark.util.visitor.AstHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/visitor/AstActionHandler.class */
public abstract class AstActionHandler<C extends AstActionHandler<C, N, A, H>, N, A extends AstAction<N>, H extends AstHandler<N, A>> {
    private final Map<Class<? extends N>, H> customHandlersMap = new HashMap();
    private final AstNode<N> astAdapter;

    public AstActionHandler(AstNode<N> astNode) {
        this.astAdapter = astNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public final C addActionHandlers(H[]... hArr) {
        for (H[] hArr2 : hArr) {
            for (H h : hArr2) {
                this.customHandlersMap.put(h.getNodeType(), h);
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C addActionHandler(H h) {
        this.customHandlersMap.put(h.getNodeType(), h);
        return this;
    }

    private A getAction(H h) {
        if (h == null) {
            return null;
        }
        return (A) h.getAdapter();
    }

    public A getAction(N n) {
        return getAction((AstActionHandler<C, N, A, H>) this.customHandlersMap.get(n.getClass()));
    }

    public A getAction(Class<?> cls) {
        return getAction((AstActionHandler<C, N, A, H>) this.customHandlersMap.get(cls));
    }

    protected H getHandler(N n) {
        return this.customHandlersMap.get(n.getClass());
    }

    protected H getHandler(Class<?> cls) {
        return this.customHandlersMap.get(cls);
    }

    public Set<Class<? extends N>> getNodeClasses() {
        return this.customHandlersMap.keySet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processNode(N n, boolean z, BiConsumer<N, A> biConsumer) {
        A action = getAction((AstActionHandler<C, N, A, H>) n);
        if (action != null) {
            biConsumer.accept(n, action);
        } else if (z) {
            processChildren(n, biConsumer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void processChildren(N n, BiConsumer<N, A> biConsumer) {
        N firstChild = this.astAdapter.getFirstChild(n);
        while (true) {
            N n2 = firstChild;
            if (n2 != null) {
                N next = this.astAdapter.getNext(n2);
                processNode(n2, true, biConsumer);
                firstChild = next;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <R> R processNodeOnly(N n, R r, BiFunction<N, A, R> biFunction) {
        Object[] objArr = {r};
        processNode(n, false, (obj, astAction) -> {
            objArr[0] = biFunction.apply(obj, astAction);
        });
        return (R) objArr[0];
    }
}

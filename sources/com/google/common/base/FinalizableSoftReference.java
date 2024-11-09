package com.google.common.base;

import java.lang.ref.SoftReference;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/FinalizableSoftReference.class */
public abstract class FinalizableSoftReference<T> extends SoftReference<T> implements FinalizableReference {
    protected FinalizableSoftReference(T t, FinalizableReferenceQueue finalizableReferenceQueue) {
        super(t, finalizableReferenceQueue.queue);
        finalizableReferenceQueue.cleanUp();
    }
}

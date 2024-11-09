package org.lwjgl.system.macosx;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/EnumerationMutationHandler.class */
public abstract class EnumerationMutationHandler extends Callback implements EnumerationMutationHandlerI {
    public static EnumerationMutationHandler create(long j) {
        EnumerationMutationHandlerI enumerationMutationHandlerI = (EnumerationMutationHandlerI) Callback.get(j);
        return enumerationMutationHandlerI instanceof EnumerationMutationHandler ? (EnumerationMutationHandler) enumerationMutationHandlerI : new Container(j, enumerationMutationHandlerI);
    }

    public static EnumerationMutationHandler createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static EnumerationMutationHandler create(EnumerationMutationHandlerI enumerationMutationHandlerI) {
        return enumerationMutationHandlerI instanceof EnumerationMutationHandler ? (EnumerationMutationHandler) enumerationMutationHandlerI : new Container(enumerationMutationHandlerI.address(), enumerationMutationHandlerI);
    }

    protected EnumerationMutationHandler() {
        super(CIF);
    }

    EnumerationMutationHandler(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/EnumerationMutationHandler$Container.class */
    public static final class Container extends EnumerationMutationHandler {
        private final EnumerationMutationHandlerI delegate;

        Container(long j, EnumerationMutationHandlerI enumerationMutationHandlerI) {
            super(j);
            this.delegate = enumerationMutationHandlerI;
        }

        @Override // org.lwjgl.system.macosx.EnumerationMutationHandlerI
        public final void invoke(long j) {
            this.delegate.invoke(j);
        }
    }
}

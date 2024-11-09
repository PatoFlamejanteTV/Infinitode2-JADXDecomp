package org.lwjgl.system;

import org.lwjgl.system.Pointer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/SharedLibrary.class */
public interface SharedLibrary extends FunctionProvider, NativeResource, Pointer {
    String getName();

    String getPath();

    /* loaded from: infinitode-2.jar:org/lwjgl/system/SharedLibrary$Default.class */
    public static abstract class Default extends Pointer.Default implements SharedLibrary {
        private final String name;

        /* JADX INFO: Access modifiers changed from: protected */
        public Default(String str, long j) {
            super(j);
            this.name = str;
        }

        @Override // org.lwjgl.system.SharedLibrary
        public String getName() {
            return this.name;
        }
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/SharedLibrary$Delegate.class */
    public static abstract class Delegate implements SharedLibrary {
        protected final SharedLibrary library;

        /* JADX INFO: Access modifiers changed from: protected */
        public Delegate(SharedLibrary sharedLibrary) {
            this.library = sharedLibrary;
        }

        @Override // org.lwjgl.system.SharedLibrary
        public String getName() {
            return this.library.getName();
        }

        @Override // org.lwjgl.system.SharedLibrary
        public String getPath() {
            return this.library.getPath();
        }

        @Override // org.lwjgl.system.Pointer
        public long address() {
            return this.library.address();
        }

        @Override // org.lwjgl.system.NativeResource
        public void free() {
            this.library.free();
        }
    }
}

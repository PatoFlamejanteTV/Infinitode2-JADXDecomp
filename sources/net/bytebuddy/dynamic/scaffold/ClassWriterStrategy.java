package net.bytebuddy.dynamic.scaffold;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.pool.TypePool;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/ClassWriterStrategy.class */
public interface ClassWriterStrategy {
    ClassWriter resolve(int i, TypePool typePool);

    ClassWriter resolve(int i, TypePool typePool, ClassReader classReader);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/ClassWriterStrategy$Default.class */
    public enum Default implements ClassWriterStrategy {
        CONSTANT_POOL_RETAINING { // from class: net.bytebuddy.dynamic.scaffold.ClassWriterStrategy.Default.1
            @Override // net.bytebuddy.dynamic.scaffold.ClassWriterStrategy
            public final ClassWriter resolve(int i, TypePool typePool, ClassReader classReader) {
                return new FrameComputingClassWriter(classReader, i, typePool);
            }
        },
        CONSTANT_POOL_DISCARDING { // from class: net.bytebuddy.dynamic.scaffold.ClassWriterStrategy.Default.2
            @Override // net.bytebuddy.dynamic.scaffold.ClassWriterStrategy
            public final ClassWriter resolve(int i, TypePool typePool, ClassReader classReader) {
                return resolve(i, typePool);
            }
        };

        /* synthetic */ Default(byte b2) {
            this();
        }

        @Override // net.bytebuddy.dynamic.scaffold.ClassWriterStrategy
        public ClassWriter resolve(int i, TypePool typePool) {
            return new FrameComputingClassWriter(i, typePool);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/ClassWriterStrategy$FrameComputingClassWriter.class */
    public static class FrameComputingClassWriter extends ClassWriter {
        private final TypePool typePool;

        public FrameComputingClassWriter(int i, TypePool typePool) {
            super(i);
            this.typePool = typePool;
        }

        public FrameComputingClassWriter(ClassReader classReader, int i, TypePool typePool) {
            super(classReader, i);
            this.typePool = typePool;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.jar.asm.ClassWriter
        public String getCommonSuperClass(String str, String str2) {
            TypeDescription asErasure;
            TypeDescription resolve = this.typePool.describe(str.replace('/', '.')).resolve();
            TypeDescription resolve2 = this.typePool.describe(str2.replace('/', '.')).resolve();
            if (resolve.isAssignableFrom(resolve2)) {
                return resolve.getInternalName();
            }
            if (resolve.isAssignableTo(resolve2)) {
                return resolve2.getInternalName();
            }
            if (resolve.isInterface() || resolve2.isInterface()) {
                return TypeDescription.ForLoadedType.of(Object.class).getInternalName();
            }
            do {
                TypeDescription.Generic superClass = resolve.getSuperClass();
                if (superClass == null) {
                    return TypeDescription.ForLoadedType.of(Object.class).getInternalName();
                }
                asErasure = superClass.asErasure();
                resolve = asErasure;
            } while (!asErasure.isAssignableFrom(resolve2));
            return resolve.getInternalName();
        }
    }
}

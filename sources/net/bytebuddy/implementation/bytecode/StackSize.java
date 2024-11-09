package net.bytebuddy.implementation.bytecode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.bytecode.StackManipulation;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/StackSize.class */
public enum StackSize {
    ZERO(0),
    SINGLE(1),
    DOUBLE(2);

    private final int size;

    StackSize(int i) {
        this.size = i;
    }

    public static StackSize of(Class<?> cls) {
        if (cls == Void.TYPE) {
            return ZERO;
        }
        if (cls == Double.TYPE || cls == Long.TYPE) {
            return DOUBLE;
        }
        return SINGLE;
    }

    public static StackSize of(int i) {
        switch (i) {
            case 0:
                return ZERO;
            case 1:
                return SINGLE;
            case 2:
                return DOUBLE;
            default:
                throw new IllegalArgumentException("Unexpected stack size value: " + i);
        }
    }

    public static int of(TypeDefinition... typeDefinitionArr) {
        return of(Arrays.asList(typeDefinitionArr));
    }

    public static int of(Collection<? extends TypeDefinition> collection) {
        int i = 0;
        Iterator<? extends TypeDefinition> it = collection.iterator();
        while (it.hasNext()) {
            i += it.next().getStackSize().getSize();
        }
        return i;
    }

    public final int getSize() {
        return this.size;
    }

    public final StackManipulation.Size toIncreasingSize() {
        return new StackManipulation.Size(getSize(), getSize());
    }

    public final StackManipulation.Size toDecreasingSize() {
        return new StackManipulation.Size((-1) * getSize(), 0);
    }

    public final StackSize maximum(StackSize stackSize) {
        switch (this) {
            case DOUBLE:
                return this;
            case SINGLE:
                switch (stackSize) {
                    case DOUBLE:
                        return stackSize;
                    case SINGLE:
                    case ZERO:
                        return this;
                    default:
                        throw new AssertionError();
                }
            case ZERO:
                return stackSize;
            default:
                throw new AssertionError();
        }
    }
}

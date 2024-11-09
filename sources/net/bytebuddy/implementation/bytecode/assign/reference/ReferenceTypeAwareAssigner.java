package net.bytebuddy.implementation.bytecode.assign.reference;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/bytecode/assign/reference/ReferenceTypeAwareAssigner.class */
public enum ReferenceTypeAwareAssigner implements Assigner {
    INSTANCE;

    @Override // net.bytebuddy.implementation.bytecode.assign.Assigner
    public final StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
        if (generic.isPrimitive() || generic2.isPrimitive()) {
            return generic.equals(generic2) ? StackManipulation.Trivial.INSTANCE : StackManipulation.Illegal.INSTANCE;
        }
        if (generic.asErasure().isAssignableTo(generic2.asErasure())) {
            return StackManipulation.Trivial.INSTANCE;
        }
        if (typing.isDynamic()) {
            return TypeCasting.to(generic2);
        }
        return StackManipulation.Illegal.INSTANCE;
    }
}

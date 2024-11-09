package net.bytebuddy.implementation;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/StubMethod.class */
public enum StubMethod implements Implementation.Composable, ByteCodeAppender {
    INSTANCE;

    @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
    public final InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    @Override // net.bytebuddy.implementation.Implementation
    public final ByteCodeAppender appender(Implementation.Target target) {
        return this;
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public final Implementation andThen(Implementation implementation) {
        return implementation;
    }

    @Override // net.bytebuddy.implementation.Implementation.Composable
    public final Implementation.Composable andThen(Implementation.Composable composable) {
        return composable;
    }

    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
    public final ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
        return new ByteCodeAppender.Size(new StackManipulation.Compound(DefaultValue.of(methodDescription.getReturnType()), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
    }
}

package net.bytebuddy.description;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/DeclaredByType.class */
public interface DeclaredByType {

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/DeclaredByType$WithMandatoryDeclaration.class */
    public interface WithMandatoryDeclaration extends DeclaredByType {
        @Override // net.bytebuddy.description.DeclaredByType
        TypeDefinition getDeclaringType();
    }

    @MaybeNull
    TypeDefinition getDeclaringType();
}

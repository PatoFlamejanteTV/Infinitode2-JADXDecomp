package net.bytebuddy.build;

import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:net/bytebuddy/build/AndroidDescriptor.class */
public interface AndroidDescriptor {

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/AndroidDescriptor$TypeScope.class */
    public enum TypeScope {
        LOCAL,
        EXTERNAL
    }

    TypeScope getTypeScope(TypeDescription typeDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/AndroidDescriptor$Trivial.class */
    public enum Trivial implements AndroidDescriptor {
        LOCAL(TypeScope.LOCAL),
        EXTERNAL(TypeScope.EXTERNAL);

        private final TypeScope typeScope;

        Trivial(TypeScope typeScope) {
            this.typeScope = typeScope;
        }

        @Override // net.bytebuddy.build.AndroidDescriptor
        public final TypeScope getTypeScope(TypeDescription typeDescription) {
            return this.typeScope;
        }
    }
}

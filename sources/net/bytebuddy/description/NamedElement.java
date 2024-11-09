package net.bytebuddy.description;

import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/NamedElement.class */
public interface NamedElement {

    @AlwaysNull
    public static final String NO_NAME = null;
    public static final String EMPTY_NAME = "";

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/NamedElement$WithDescriptor.class */
    public interface WithDescriptor extends NamedElement {

        @AlwaysNull
        public static final String NON_GENERIC_SIGNATURE = null;

        String getDescriptor();

        @MaybeNull
        String getGenericSignature();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/NamedElement$WithGenericName.class */
    public interface WithGenericName extends WithRuntimeName {
        String toGenericString();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/NamedElement$WithOptionalName.class */
    public interface WithOptionalName extends NamedElement {
        boolean isNamed();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/NamedElement$WithRuntimeName.class */
    public interface WithRuntimeName extends NamedElement {
        String getName();

        String getInternalName();
    }

    String getActualName();
}

package net.bytebuddy.implementation.auxiliary;

import com.vladsch.flexmark.util.html.Attribute;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.RandomString;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/PrivilegedMemberLookupAction.class */
public enum PrivilegedMemberLookupAction implements AuxiliaryType {
    FOR_PUBLIC_METHOD("getMethod", Attribute.NAME_ATTR, String.class, "parameters", Class[].class),
    FOR_DECLARED_METHOD("getDeclaredMethod", Attribute.NAME_ATTR, String.class, "parameters", Class[].class),
    FOR_PUBLIC_CONSTRUCTOR("getConstructor", "parameters", Class[].class),
    FOR_DECLARED_CONSTRUCTOR(TypeProxy.SilentConstruction.Appender.GET_DECLARED_CONSTRUCTOR_METHOD_NAME, "parameters", Class[].class);

    private static final String TYPE_FIELD = "type";
    private static final MethodDescription.InDefinedShape DEFAULT_CONSTRUCTOR = (MethodDescription.InDefinedShape) TypeDescription.ForLoadedType.of(Object.class).getDeclaredMethods().filter(ElementMatchers.isConstructor()).getOnly();
    private final MethodDescription.InDefinedShape methodDescription;
    private final Map<String, Class<?>> fields;

    PrivilegedMemberLookupAction(String str, String str2, Class cls) {
        try {
            this.methodDescription = new MethodDescription.ForLoadedMethod(Class.class.getMethod(str, cls));
            this.fields = Collections.singletonMap(str2, cls);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate method: " + str, e);
        }
    }

    PrivilegedMemberLookupAction(String str, String str2, Class cls, String str3, Class cls2) {
        try {
            this.methodDescription = new MethodDescription.ForLoadedMethod(Class.class.getMethod(str, cls, cls2));
            this.fields = new LinkedHashMap();
            this.fields.put(str2, cls);
            this.fields.put(str3, cls2);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate method: " + str, e);
        }
    }

    public static AuxiliaryType of(MethodDescription methodDescription) {
        if (methodDescription.isConstructor()) {
            return methodDescription.isPublic() ? FOR_PUBLIC_CONSTRUCTOR : FOR_DECLARED_CONSTRUCTOR;
        }
        if (methodDescription.isMethod()) {
            return methodDescription.isPublic() ? FOR_PUBLIC_METHOD : FOR_DECLARED_METHOD;
        }
        throw new IllegalStateException("Cannot load constant for type initializer: " + methodDescription);
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public final String getSuffix() {
        return RandomString.hashOf(name().hashCode());
    }

    @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType
    public final DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        Implementation.Composable andThen = MethodCall.invoke(DEFAULT_CONSTRUCTOR).andThen(FieldAccessor.ofField(TYPE_FIELD).setsArgumentAt(0));
        int i = 1;
        Iterator<String> it = this.fields.keySet().iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            andThen = andThen.andThen(FieldAccessor.ofField(it.next()).setsArgumentAt(i2));
        }
        DynamicType.Builder.FieldDefinition.Optional.Valuable defineField = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).subclass(PrivilegedExceptionAction.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).defineConstructor(Visibility.PUBLIC).withParameters(CompoundList.of(Class.class, new ArrayList(this.fields.values()))).intercept(andThen).method(ElementMatchers.named("run")).intercept(MethodCall.invoke(this.methodDescription).onField(TYPE_FIELD).withField((String[]) this.fields.keySet().toArray(new String[0]))).defineField(TYPE_FIELD, Class.class, Visibility.PRIVATE);
        for (Map.Entry<String, Class<?>> entry : this.fields.entrySet()) {
            defineField = defineField.defineField(entry.getKey(), entry.getValue(), Visibility.PRIVATE);
        }
        return defineField.make();
    }
}

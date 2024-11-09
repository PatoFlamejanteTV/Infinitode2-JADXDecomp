package net.bytebuddy.description.type;

import java.util.Collections;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/type/TypeVariableToken.class */
public class TypeVariableToken implements ByteCodeElement.Token<TypeVariableToken> {
    private final String symbol;
    private final List<? extends TypeDescription.Generic> bounds;
    private final List<? extends AnnotationDescription> annotations;
    private transient /* synthetic */ int hashCode;

    @Override // net.bytebuddy.description.ByteCodeElement.Token
    public /* bridge */ /* synthetic */ TypeVariableToken accept(TypeDescription.Generic.Visitor visitor) {
        return accept((TypeDescription.Generic.Visitor<? extends TypeDescription.Generic>) visitor);
    }

    public TypeVariableToken(String str, List<? extends TypeDescription.Generic> list) {
        this(str, list, Collections.emptyList());
    }

    public TypeVariableToken(String str, List<? extends TypeDescription.Generic> list, List<? extends AnnotationDescription> list2) {
        this.symbol = str;
        this.bounds = list;
        this.annotations = list2;
    }

    public static TypeVariableToken of(TypeDescription.Generic generic, ElementMatcher<? super TypeDescription> elementMatcher) {
        return new TypeVariableToken(generic.getSymbol(), generic.getUpperBounds().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), generic.getDeclaredAnnotations());
    }

    public String getSymbol() {
        return this.symbol;
    }

    public TypeList.Generic getBounds() {
        return new TypeList.Generic.Explicit(this.bounds);
    }

    public AnnotationList getAnnotations() {
        return new AnnotationList.Explicit(this.annotations);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // net.bytebuddy.description.ByteCodeElement.Token
    public TypeVariableToken accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
        return new TypeVariableToken(this.symbol, getBounds().accept(visitor), this.annotations);
    }

    @CachedReturnPlugin.Enhance("hashCode")
    public int hashCode() {
        int hashCode = this.hashCode != 0 ? 0 : (((this.symbol.hashCode() * 31) + this.bounds.hashCode()) * 31) + this.annotations.hashCode();
        int i = hashCode;
        if (hashCode == 0) {
            i = this.hashCode;
        } else {
            this.hashCode = i;
        }
        return i;
    }

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeVariableToken)) {
            return false;
        }
        TypeVariableToken typeVariableToken = (TypeVariableToken) obj;
        return this.symbol.equals(typeVariableToken.symbol) && this.bounds.equals(typeVariableToken.bounds) && this.annotations.equals(typeVariableToken.annotations);
    }

    public String toString() {
        return this.symbol;
    }
}

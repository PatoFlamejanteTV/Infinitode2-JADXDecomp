package net.bytebuddy.description.field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList.class */
public interface FieldList<T extends FieldDescription> extends FilterableList<T, FieldList<T>> {
    ByteCodeElement.Token.TokenList<FieldDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

    FieldList<FieldDescription.InDefinedShape> asDefined();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$AbstractBase.class */
    public static abstract class AbstractBase<S extends FieldDescription> extends FilterableList.AbstractBase<S, FieldList<S>> implements FieldList<S> {
        @Override // net.bytebuddy.description.field.FieldList
        public ByteCodeElement.Token.TokenList<FieldDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((FieldDescription) it.next()).asToken(elementMatcher));
            }
            return new ByteCodeElement.Token.TokenList<>(arrayList);
        }

        @Override // net.bytebuddy.description.field.FieldList
        public FieldList<FieldDescription.InDefinedShape> asDefined() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((FieldDescription) it.next()).asDefined());
            }
            return new Explicit(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.matcher.FilterableList.AbstractBase
        public FieldList<S> wrap(List<S> list) {
            return new Explicit(list);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$ForLoadedFields.class */
    public static class ForLoadedFields extends AbstractBase<FieldDescription.InDefinedShape> {
        private final List<? extends Field> fields;

        public ForLoadedFields(Field... fieldArr) {
            this((List<? extends Field>) Arrays.asList(fieldArr));
        }

        public ForLoadedFields(List<? extends Field> list) {
            this.fields = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public FieldDescription.InDefinedShape get(int i) {
            return new FieldDescription.ForLoadedField(this.fields.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fields.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$Explicit.class */
    public static class Explicit<S extends FieldDescription> extends AbstractBase<S> {
        private final List<? extends S> fieldDescriptions;

        public Explicit(S... sArr) {
            this(Arrays.asList(sArr));
        }

        public Explicit(List<? extends S> list) {
            this.fieldDescriptions = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public S get(int i) {
            return this.fieldDescriptions.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fieldDescriptions.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$ForTokens.class */
    public static class ForTokens extends AbstractBase<FieldDescription.InDefinedShape> {
        private final TypeDescription declaringType;
        private final List<? extends FieldDescription.Token> tokens;

        public ForTokens(TypeDescription typeDescription, FieldDescription.Token... tokenArr) {
            this(typeDescription, (List<? extends FieldDescription.Token>) Arrays.asList(tokenArr));
        }

        public ForTokens(TypeDescription typeDescription, List<? extends FieldDescription.Token> list) {
            this.declaringType = typeDescription;
            this.tokens = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public FieldDescription.InDefinedShape get(int i) {
            return new FieldDescription.Latent(this.declaringType, this.tokens.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.tokens.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$TypeSubstituting.class */
    public static class TypeSubstituting extends AbstractBase<FieldDescription.InGenericShape> {
        private final TypeDescription.Generic declaringType;
        private final List<? extends FieldDescription> fieldDescriptions;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, List<? extends FieldDescription> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringType = generic;
            this.fieldDescriptions = list;
            this.visitor = visitor;
        }

        @Override // java.util.AbstractList, java.util.List
        public FieldDescription.InGenericShape get(int i) {
            return new FieldDescription.TypeSubstituting(this.declaringType, this.fieldDescriptions.get(i), this.visitor);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fieldDescriptions.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/field/FieldList$Empty.class */
    public static class Empty<S extends FieldDescription> extends FilterableList.Empty<S, FieldList<S>> implements FieldList<S> {
        @Override // net.bytebuddy.description.field.FieldList
        public ByteCodeElement.Token.TokenList<FieldDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new ByteCodeElement.Token.TokenList<>(new FieldDescription.Token[0]);
        }

        @Override // net.bytebuddy.description.field.FieldList
        public FieldList<FieldDescription.InDefinedShape> asDefined() {
            return this;
        }
    }
}

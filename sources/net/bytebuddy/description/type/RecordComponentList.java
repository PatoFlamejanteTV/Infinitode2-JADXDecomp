package net.bytebuddy.description.type;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList.class */
public interface RecordComponentList<T extends RecordComponentDescription> extends FilterableList<T, RecordComponentList<T>> {
    ByteCodeElement.Token.TokenList<RecordComponentDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

    TypeList.Generic asTypeList();

    RecordComponentList<RecordComponentDescription.InDefinedShape> asDefined();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$AbstractBase.class */
    public static abstract class AbstractBase<S extends RecordComponentDescription> extends FilterableList.AbstractBase<S, RecordComponentList<S>> implements RecordComponentList<S> {
        @Override // net.bytebuddy.description.type.RecordComponentList
        public ByteCodeElement.Token.TokenList<RecordComponentDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((RecordComponentDescription) it.next()).asToken(elementMatcher));
            }
            return new ByteCodeElement.Token.TokenList<>(arrayList);
        }

        @Override // net.bytebuddy.description.type.RecordComponentList
        public TypeList.Generic asTypeList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((RecordComponentDescription) it.next()).getType());
            }
            return new TypeList.Generic.Explicit(arrayList);
        }

        @Override // net.bytebuddy.description.type.RecordComponentList
        public RecordComponentList<RecordComponentDescription.InDefinedShape> asDefined() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((RecordComponentDescription) it.next()).asDefined());
            }
            return new Explicit(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.bytebuddy.matcher.FilterableList.AbstractBase
        public RecordComponentList<S> wrap(List<S> list) {
            return new Explicit(list);
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$ForLoadedRecordComponents.class */
    public static class ForLoadedRecordComponents extends AbstractBase<RecordComponentDescription.InDefinedShape> {
        private final List<?> recordComponents;

        /* JADX INFO: Access modifiers changed from: protected */
        public ForLoadedRecordComponents(Object... objArr) {
            this((List<?>) Arrays.asList(objArr));
        }

        protected ForLoadedRecordComponents(List<?> list) {
            this.recordComponents = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public RecordComponentDescription.InDefinedShape get(int i) {
            return new RecordComponentDescription.ForLoadedRecordComponent((AnnotatedElement) this.recordComponents.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.recordComponents.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$Explicit.class */
    public static class Explicit<S extends RecordComponentDescription> extends AbstractBase<S> {
        private final List<? extends S> recordComponents;

        public Explicit(S... sArr) {
            this(Arrays.asList(sArr));
        }

        public Explicit(List<? extends S> list) {
            this.recordComponents = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public S get(int i) {
            return this.recordComponents.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.recordComponents.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$ForTokens.class */
    public static class ForTokens extends AbstractBase<RecordComponentDescription.InDefinedShape> {
        private final TypeDescription typeDescription;
        private final List<? extends RecordComponentDescription.Token> tokens;

        public ForTokens(TypeDescription typeDescription, RecordComponentDescription.Token... tokenArr) {
            this(typeDescription, (List<? extends RecordComponentDescription.Token>) Arrays.asList(tokenArr));
        }

        public ForTokens(TypeDescription typeDescription, List<? extends RecordComponentDescription.Token> list) {
            this.typeDescription = typeDescription;
            this.tokens = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public RecordComponentDescription.InDefinedShape get(int i) {
            return new RecordComponentDescription.Latent(this.typeDescription, this.tokens.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.tokens.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$TypeSubstituting.class */
    public static class TypeSubstituting extends AbstractBase<RecordComponentDescription.InGenericShape> {
        private final TypeDescription.Generic declaringType;
        private final List<? extends RecordComponentDescription> recordComponentDescriptions;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, List<? extends RecordComponentDescription> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            this.declaringType = generic;
            this.recordComponentDescriptions = list;
            this.visitor = visitor;
        }

        @Override // java.util.AbstractList, java.util.List
        public RecordComponentDescription.InGenericShape get(int i) {
            return new RecordComponentDescription.TypeSubstituting(this.declaringType, this.recordComponentDescriptions.get(i), this.visitor);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.recordComponentDescriptions.size();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/RecordComponentList$Empty.class */
    public static class Empty<S extends RecordComponentDescription> extends FilterableList.Empty<S, RecordComponentList<S>> implements RecordComponentList<S> {
        @Override // net.bytebuddy.description.type.RecordComponentList
        public RecordComponentList<RecordComponentDescription.InDefinedShape> asDefined() {
            return new Empty();
        }

        @Override // net.bytebuddy.description.type.RecordComponentList
        public ByteCodeElement.Token.TokenList<RecordComponentDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new ByteCodeElement.Token.TokenList<>(new RecordComponentDescription.Token[0]);
        }

        @Override // net.bytebuddy.description.type.RecordComponentList
        public TypeList.Generic asTypeList() {
            return new TypeList.Generic.Empty();
        }
    }
}

package net.bytebuddy.matcher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher.class */
public interface LatentMatcher<T> {
    ElementMatcher<? super T> resolve(TypeDescription typeDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForSelfDeclaredMethod.class */
    public enum ForSelfDeclaredMethod implements LatentMatcher<MethodDescription> {
        DECLARED(false),
        NOT_DECLARED(true);

        private final boolean inverted;

        ForSelfDeclaredMethod(boolean z) {
            this.inverted = z;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public final ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            if (this.inverted) {
                return ElementMatchers.not(ElementMatchers.isDeclaredBy(typeDescription));
            }
            return ElementMatchers.isDeclaredBy(typeDescription);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$Resolved.class */
    public static class Resolved<S> implements LatentMatcher<S> {
        private final ElementMatcher<? super S> matcher;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Resolved) obj).matcher);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.matcher.hashCode();
        }

        public Resolved(ElementMatcher<? super S> elementMatcher) {
            this.matcher = elementMatcher;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            return this.matcher;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForFieldToken.class */
    public static class ForFieldToken implements LatentMatcher<FieldDescription> {
        private final FieldDescription.Token token;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.token.equals(((ForFieldToken) obj).token);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.token.hashCode();
        }

        public ForFieldToken(FieldDescription.Token token) {
            this.token = token;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super FieldDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForFieldToken$ResolvedMatcher.class */
        protected static class ResolvedMatcher extends ElementMatcher.Junction.ForNonNullValues<FieldDescription> {
            private final FieldDescription.SignatureToken signatureToken;

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.signatureToken.equals(((ResolvedMatcher) obj).signatureToken);
            }

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public int hashCode() {
                return (super.hashCode() * 31) + this.signatureToken.hashCode();
            }

            protected ResolvedMatcher(FieldDescription.SignatureToken signatureToken) {
                this.signatureToken = signatureToken;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public boolean doMatch(FieldDescription fieldDescription) {
                return fieldDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForMethodToken.class */
    public static class ForMethodToken implements LatentMatcher<MethodDescription> {
        private final MethodDescription.Token token;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.token.equals(((ForMethodToken) obj).token);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.token.hashCode();
        }

        public ForMethodToken(MethodDescription.Token token) {
            this.token = token;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForMethodToken$ResolvedMatcher.class */
        protected static class ResolvedMatcher extends ElementMatcher.Junction.ForNonNullValues<MethodDescription> {
            private final MethodDescription.SignatureToken signatureToken;

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.signatureToken.equals(((ResolvedMatcher) obj).signatureToken);
            }

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public int hashCode() {
                return (super.hashCode() * 31) + this.signatureToken.hashCode();
            }

            protected ResolvedMatcher(MethodDescription.SignatureToken signatureToken) {
                this.signatureToken = signatureToken;
            }

            @Override // net.bytebuddy.matcher.ElementMatcher.Junction.ForNonNullValues
            public boolean doMatch(MethodDescription methodDescription) {
                return methodDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$ForRecordComponentToken.class */
    public static class ForRecordComponentToken implements LatentMatcher<RecordComponentDescription> {
        private final RecordComponentDescription.Token token;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.token.equals(((ForRecordComponentToken) obj).token);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.token.hashCode();
        }

        public ForRecordComponentToken(RecordComponentDescription.Token token) {
            this.token = token;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super RecordComponentDescription> resolve(TypeDescription typeDescription) {
            return ElementMatchers.named(this.token.getName());
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$Conjunction.class */
    public static class Conjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matchers.equals(((Conjunction) obj).matchers);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.matchers.hashCode();
        }

        public Conjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Conjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction any = ElementMatchers.any();
            Iterator<? extends LatentMatcher<? super S>> it = this.matchers.iterator();
            while (it.hasNext()) {
                any = any.and(it.next().resolve(typeDescription));
            }
            return any;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/matcher/LatentMatcher$Disjunction.class */
    public static class Disjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matchers.equals(((Disjunction) obj).matchers);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.matchers.hashCode();
        }

        public Disjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Disjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        @Override // net.bytebuddy.matcher.LatentMatcher
        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction none = ElementMatchers.none();
            Iterator<? extends LatentMatcher<? super S>> it = this.matchers.iterator();
            while (it.hasNext()) {
                none = none.or(it.next().resolve(typeDescription));
            }
            return none;
        }
    }
}

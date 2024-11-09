package com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Converter.class */
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    private transient Converter<B, A> reverse;

    protected abstract B doForward(A a2);

    protected abstract A doBackward(B b2);

    /* JADX INFO: Access modifiers changed from: protected */
    public Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    public final B convert(A a2) {
        return correctedDoForward(a2);
    }

    B correctedDoForward(A a2) {
        if (this.handleNullAutomatically) {
            if (a2 == null) {
                return null;
            }
            return (B) Preconditions.checkNotNull(doForward(a2));
        }
        return unsafeDoForward(a2);
    }

    A correctedDoBackward(B b2) {
        if (this.handleNullAutomatically) {
            if (b2 == null) {
                return null;
            }
            return (A) Preconditions.checkNotNull(doBackward(b2));
        }
        return unsafeDoBackward(b2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private B unsafeDoForward(A a2) {
        return (B) doForward(NullnessCasts.uncheckedCastNullableTToT(a2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private A unsafeDoBackward(B b2) {
        return (A) doBackward(NullnessCasts.uncheckedCastNullableTToT(b2));
    }

    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() { // from class: com.google.common.base.Converter.1
            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() { // from class: com.google.common.base.Converter.1.1
                    private final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = iterable.iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public B next() {
                        return (B) Converter.this.convert(this.fromIterator.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Converter$ReverseConverter.class */
    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        final Converter<A, B> original;
        private static final long serialVersionUID = 0;

        ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        @Override // com.google.common.base.Converter
        protected final A doForward(B b2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected final B doBackward(A a2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        final A correctedDoForward(B b2) {
            return this.original.correctedDoBackward(b2);
        }

        @Override // com.google.common.base.Converter
        final B correctedDoBackward(A a2) {
            return this.original.correctedDoForward(a2);
        }

        @Override // com.google.common.base.Converter
        public final Converter<A, B> reverse() {
            return this.original;
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public final boolean equals(Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        public final int hashCode() {
            return this.original.hashCode() ^ (-1);
        }

        public final String toString() {
            String valueOf = String.valueOf(this.original);
            return new StringBuilder(10 + String.valueOf(valueOf).length()).append(valueOf).append(".reverse()").toString();
        }
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/google/common/base/Converter$ConverterComposition.class */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        final Converter<A, B> first;
        final Converter<B, C> second;
        private static final long serialVersionUID = 0;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        @Override // com.google.common.base.Converter
        protected final C doForward(A a2) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected final A doBackward(C c) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        final C correctedDoForward(A a2) {
            return (C) this.second.correctedDoForward(this.first.correctedDoForward(a2));
        }

        @Override // com.google.common.base.Converter
        final A correctedDoBackward(C c) {
            return (A) this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public final boolean equals(Object obj) {
            if (obj instanceof ConverterComposition) {
                ConverterComposition converterComposition = (ConverterComposition) obj;
                return this.first.equals(converterComposition.first) && this.second.equals(converterComposition.second);
            }
            return false;
        }

        public final int hashCode() {
            return (31 * this.first.hashCode()) + this.second.hashCode();
        }

        public final String toString() {
            String valueOf = String.valueOf(this.first);
            String valueOf2 = String.valueOf(this.second);
            return new StringBuilder(10 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length()).append(valueOf).append(".andThen(").append(valueOf2).append(")").toString();
        }
    }

    @Override // com.google.common.base.Function
    @Deprecated
    public final B apply(A a2) {
        return convert(a2);
    }

    @Override // com.google.common.base.Function
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Converter$FunctionBasedConverter.class */
    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super A, ? extends B> forwardFunction;
        private final Function<? super B, ? extends A> backwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }

        @Override // com.google.common.base.Converter
        protected final B doForward(A a2) {
            return this.forwardFunction.apply(a2);
        }

        @Override // com.google.common.base.Converter
        protected final A doBackward(B b2) {
            return this.backwardFunction.apply(b2);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public final boolean equals(Object obj) {
            if (obj instanceof FunctionBasedConverter) {
                FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
                return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
            }
            return false;
        }

        public final int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public final String toString() {
            String valueOf = String.valueOf(this.forwardFunction);
            String valueOf2 = String.valueOf(this.backwardFunction);
            return new StringBuilder(18 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length()).append("Converter.from(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Converter$IdentityConverter.class */
    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter<?> INSTANCE = new IdentityConverter<>();
        private static final long serialVersionUID = 0;

        private IdentityConverter() {
        }

        @Override // com.google.common.base.Converter
        protected final T doForward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        protected final T doBackward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public final IdentityConverter<T> reverse() {
            return this;
        }

        @Override // com.google.common.base.Converter
        final <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        public final String toString() {
            return "Converter.identity()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}

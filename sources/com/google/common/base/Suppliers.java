package com.google.common.base;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Suppliers.class */
public final class Suppliers {

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$SupplierFunction.class */
    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$SupplierComposition.class */
    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        final Function<? super F, T> function;
        final Supplier<F> supplier;
        private static final long serialVersionUID = 0;

        SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            this.function = (Function) Preconditions.checkNotNull(function);
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierComposition) {
                SupplierComposition supplierComposition = (SupplierComposition) obj;
                return this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.function, this.supplier);
        }

        public String toString() {
            String valueOf = String.valueOf(this.function);
            String valueOf2 = String.valueOf(this.supplier);
            return new StringBuilder(21 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length()).append("Suppliers.compose(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        if ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) {
            return supplier;
        }
        if (supplier instanceof Serializable) {
            return new MemoizingSupplier(supplier);
        }
        return new NonSerializableMemoizingSupplier(supplier);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$MemoizingSupplier.class */
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        transient T value;
        private static final long serialVersionUID = 0;

        MemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }
            return (T) NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj;
            if (this.initialized) {
                String valueOf = String.valueOf(this.value);
                obj = new StringBuilder(25 + String.valueOf(valueOf).length()).append("<supplier that returned ").append(valueOf).append(">").toString();
            } else {
                obj = this.delegate;
            }
            String valueOf2 = String.valueOf(obj);
            return new StringBuilder(19 + String.valueOf(valueOf2).length()).append("Suppliers.memoize(").append(valueOf2).append(")").toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$NonSerializableMemoizingSupplier.class */
    static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {
        volatile Supplier<T> delegate;
        volatile boolean initialized;
        T value;

        NonSerializableMemoizingSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = (T) ((Supplier) java.util.Objects.requireNonNull(this.delegate)).get();
                        this.value = t;
                        this.initialized = true;
                        this.delegate = null;
                        return t;
                    }
                }
            }
            return (T) NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            Object obj;
            Supplier<T> supplier = this.delegate;
            if (supplier == null) {
                String valueOf = String.valueOf(this.value);
                obj = new StringBuilder(25 + String.valueOf(valueOf).length()).append("<supplier that returned ").append(valueOf).append(">").toString();
            } else {
                obj = supplier;
            }
            String valueOf2 = String.valueOf(obj);
            return new StringBuilder(19 + String.valueOf(valueOf2).length()).append("Suppliers.memoize(").append(valueOf2).append(")").toString();
        }
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j, timeUnit);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$ExpiringMemoizingSupplier.class */
    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        final Supplier<T> delegate;
        final long durationNanos;
        volatile transient T value;
        volatile transient long expirationNanos;
        private static final long serialVersionUID = 0;

        ExpiringMemoizingSupplier(Supplier<T> supplier, long j, TimeUnit timeUnit) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
            this.durationNanos = timeUnit.toNanos(j);
            Preconditions.checkArgument(j > 0, "duration (%s %s) must be > 0", j, timeUnit);
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            long j = this.expirationNanos;
            long systemNanoTime = Platform.systemNanoTime();
            if (j == 0 || systemNanoTime - j >= 0) {
                synchronized (this) {
                    if (j == this.expirationNanos) {
                        T t = this.delegate.get();
                        this.value = t;
                        long j2 = systemNanoTime + this.durationNanos;
                        this.expirationNanos = j2 == 0 ? 1L : j2;
                        return t;
                    }
                }
            }
            return (T) NullnessCasts.uncheckedCastNullableTToT(this.value);
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            return new StringBuilder(62 + String.valueOf(valueOf).length()).append("Suppliers.memoizeWithExpiration(").append(valueOf).append(", ").append(this.durationNanos).append(", NANOS)").toString();
        }
    }

    public static <T> Supplier<T> ofInstance(@ParametricNullness T t) {
        return new SupplierOfInstance(t);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$SupplierOfInstance.class */
    private static class SupplierOfInstance<T> implements Supplier<T>, Serializable {

        @ParametricNullness
        final T instance;
        private static final long serialVersionUID = 0;

        SupplierOfInstance(@ParametricNullness T t) {
            this.instance = t;
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            return this.instance;
        }

        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.instance);
        }

        public String toString() {
            String valueOf = String.valueOf(this.instance);
            return new StringBuilder(22 + String.valueOf(valueOf).length()).append("Suppliers.ofInstance(").append(valueOf).append(")").toString();
        }
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier(supplier);
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$ThreadSafeSupplier.class */
    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        final Supplier<T> delegate;
        private static final long serialVersionUID = 0;

        ThreadSafeSupplier(Supplier<T> supplier) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        @ParametricNullness
        public T get() {
            T t;
            synchronized (this.delegate) {
                t = this.delegate.get();
            }
            return t;
        }

        public String toString() {
            String valueOf = String.valueOf(this.delegate);
            return new StringBuilder(32 + String.valueOf(valueOf).length()).append("Suppliers.synchronizedSupplier(").append(valueOf).append(")").toString();
        }
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    /* loaded from: infinitode-2.jar:com/google/common/base/Suppliers$SupplierFunctionImpl.class */
    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @Override // com.google.common.base.Function
        public final Object apply(Supplier<Object> supplier) {
            return supplier.get();
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "Suppliers.supplierFunction()";
        }
    }
}

package net.bytebuddy;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy.class */
public interface NamingStrategy {
    public static final String BYTE_BUDDY_RENAME_PACKAGE = "net.bytebuddy.renamed";
    public static final String NO_PREFIX = "";

    String subclass(TypeDescription.Generic generic);

    String redefine(TypeDescription typeDescription);

    String rebase(TypeDescription typeDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$AbstractBase.class */
    public static abstract class AbstractBase implements NamingStrategy {
        protected abstract String name(TypeDescription typeDescription);

        @Override // net.bytebuddy.NamingStrategy
        public String subclass(TypeDescription.Generic generic) {
            return name(generic.asErasure());
        }

        @Override // net.bytebuddy.NamingStrategy
        public String redefine(TypeDescription typeDescription) {
            return typeDescription.getName();
        }

        @Override // net.bytebuddy.NamingStrategy
        public String rebase(TypeDescription typeDescription) {
            return typeDescription.getName();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing.class */
    public static class Suffixing extends AbstractBase {
        private static final String JAVA_PACKAGE = "java.";
        private final String suffix;
        private final String javaLangPackagePrefix;
        private final BaseNameResolver baseNameResolver;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.suffix.equals(((Suffixing) obj).suffix) && this.javaLangPackagePrefix.equals(((Suffixing) obj).javaLangPackagePrefix) && this.baseNameResolver.equals(((Suffixing) obj).baseNameResolver);
        }

        public int hashCode() {
            return (((((getClass().hashCode() * 31) + this.suffix.hashCode()) * 31) + this.javaLangPackagePrefix.hashCode()) * 31) + this.baseNameResolver.hashCode();
        }

        public Suffixing(String str) {
            this(str, BaseNameResolver.ForUnnamedType.INSTANCE);
        }

        public Suffixing(String str, String str2) {
            this(str, BaseNameResolver.ForUnnamedType.INSTANCE, str2);
        }

        public Suffixing(String str, BaseNameResolver baseNameResolver) {
            this(str, baseNameResolver, NamingStrategy.BYTE_BUDDY_RENAME_PACKAGE);
        }

        public Suffixing(String str, BaseNameResolver baseNameResolver, String str2) {
            this.suffix = str;
            this.baseNameResolver = baseNameResolver;
            this.javaLangPackagePrefix = str2;
        }

        @Override // net.bytebuddy.NamingStrategy.AbstractBase
        protected String name(TypeDescription typeDescription) {
            String resolve = this.baseNameResolver.resolve(typeDescription);
            String str = resolve;
            if (resolve.startsWith(JAVA_PACKAGE) && !this.javaLangPackagePrefix.equals("")) {
                str = this.javaLangPackagePrefix + "." + str;
            }
            return str + "$" + this.suffix;
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing$BaseNameResolver.class */
        public interface BaseNameResolver {
            String resolve(TypeDescription typeDescription);

            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing$BaseNameResolver$ForUnnamedType.class */
            public enum ForUnnamedType implements BaseNameResolver {
                INSTANCE;

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver
                public final String resolve(TypeDescription typeDescription) {
                    return typeDescription.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing$BaseNameResolver$ForGivenType.class */
            public static class ForGivenType implements BaseNameResolver {
                private final TypeDescription typeDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForGivenType) obj).typeDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.typeDescription.hashCode();
                }

                public ForGivenType(TypeDescription typeDescription) {
                    this.typeDescription = typeDescription;
                }

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    return this.typeDescription.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing$BaseNameResolver$ForFixedValue.class */
            public static class ForFixedValue implements BaseNameResolver {
                private final String name;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.name.equals(((ForFixedValue) obj).name);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.name.hashCode();
                }

                public ForFixedValue(String str) {
                    this.name = str;
                }

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    return this.name;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$Suffixing$BaseNameResolver$WithCallerSuffix.class */
            public static class WithCallerSuffix implements BaseNameResolver {
                private final BaseNameResolver delegate;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithCallerSuffix) obj).delegate);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.delegate.hashCode();
                }

                public WithCallerSuffix(BaseNameResolver baseNameResolver) {
                    this.delegate = baseNameResolver;
                }

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver
                public String resolve(TypeDescription typeDescription) {
                    boolean z = false;
                    String str = null;
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    int length = stackTrace.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        StackTraceElement stackTraceElement = stackTrace[i];
                        if (stackTraceElement.getClassName().equals(ByteBuddy.class.getName())) {
                            z = true;
                        } else if (z) {
                            str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
                            break;
                        }
                        i++;
                    }
                    if (str == null) {
                        throw new IllegalStateException("Base name resolver not invoked via " + ByteBuddy.class);
                    }
                    return this.delegate.resolve(typeDescription) + "$" + str.replace('.', '$');
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$SuffixingRandom.class */
    public static class SuffixingRandom extends Suffixing {

        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        private final RandomString randomString;

        @Override // net.bytebuddy.NamingStrategy.Suffixing
        public boolean equals(@MaybeNull Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        @Override // net.bytebuddy.NamingStrategy.Suffixing
        public int hashCode() {
            return super.hashCode();
        }

        public SuffixingRandom(String str) {
            this(str, Suffixing.BaseNameResolver.ForUnnamedType.INSTANCE);
        }

        public SuffixingRandom(String str, String str2) {
            this(str, Suffixing.BaseNameResolver.ForUnnamedType.INSTANCE, str2);
        }

        @Deprecated
        public SuffixingRandom(String str, BaseNameResolver baseNameResolver) {
            this(str, (Suffixing.BaseNameResolver) baseNameResolver);
        }

        public SuffixingRandom(String str, Suffixing.BaseNameResolver baseNameResolver) {
            this(str, baseNameResolver, NamingStrategy.BYTE_BUDDY_RENAME_PACKAGE);
        }

        @Deprecated
        public SuffixingRandom(String str, BaseNameResolver baseNameResolver, String str2) {
            this(str, (Suffixing.BaseNameResolver) baseNameResolver, str2);
        }

        public SuffixingRandom(String str, Suffixing.BaseNameResolver baseNameResolver, String str2) {
            this(str, baseNameResolver, str2, new RandomString());
        }

        @Deprecated
        public SuffixingRandom(String str, BaseNameResolver baseNameResolver, String str2, RandomString randomString) {
            this(str, (Suffixing.BaseNameResolver) baseNameResolver, str2, randomString);
        }

        public SuffixingRandom(String str, Suffixing.BaseNameResolver baseNameResolver, String str2, RandomString randomString) {
            super(str, baseNameResolver, str2);
            this.randomString = randomString;
        }

        @Override // net.bytebuddy.NamingStrategy.Suffixing, net.bytebuddy.NamingStrategy.AbstractBase
        protected String name(TypeDescription typeDescription) {
            return super.name(typeDescription) + "$" + this.randomString.nextString();
        }

        @Deprecated
        /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$SuffixingRandom$BaseNameResolver.class */
        public interface BaseNameResolver extends Suffixing.BaseNameResolver {

            @Deprecated
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$SuffixingRandom$BaseNameResolver$ForUnnamedType.class */
            public enum ForUnnamedType implements BaseNameResolver {
                INSTANCE;

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver
                public final String resolve(TypeDescription typeDescription) {
                    return typeDescription.getName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            @Deprecated
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$SuffixingRandom$BaseNameResolver$ForGivenType.class */
            public static class ForGivenType extends Suffixing.BaseNameResolver.ForGivenType implements BaseNameResolver {
                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass();
                }

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType
                public int hashCode() {
                    return super.hashCode();
                }

                public ForGivenType(TypeDescription typeDescription) {
                    super(typeDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            @Deprecated
            /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$SuffixingRandom$BaseNameResolver$ForFixedValue.class */
            public static class ForFixedValue extends Suffixing.BaseNameResolver.ForFixedValue implements BaseNameResolver {
                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForFixedValue
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass();
                }

                @Override // net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForFixedValue
                public int hashCode() {
                    return super.hashCode();
                }

                public ForFixedValue(String str) {
                    super(str);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/NamingStrategy$PrefixingRandom.class */
    public static class PrefixingRandom extends AbstractBase {
        private final String prefix;

        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        private final RandomString randomString = new RandomString();

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.prefix.equals(((PrefixingRandom) obj).prefix);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.prefix.hashCode();
        }

        public PrefixingRandom(String str) {
            this.prefix = str;
        }

        @Override // net.bytebuddy.NamingStrategy.AbstractBase
        protected String name(TypeDescription typeDescription) {
            return this.prefix + "." + typeDescription.getName() + "$" + this.randomString.nextString();
        }
    }
}

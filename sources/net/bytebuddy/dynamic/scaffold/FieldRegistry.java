package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry.class */
public interface FieldRegistry {
    FieldRegistry prepend(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, @MaybeNull Object obj, Transformer<FieldDescription> transformer);

    Compiled compile(TypeDescription typeDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Compiled.class */
    public interface Compiled extends TypeWriter.FieldPool {

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Compiled$NoOp.class */
        public enum NoOp implements Compiled {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool
            public final TypeWriter.FieldPool.Record target(FieldDescription fieldDescription) {
                return new TypeWriter.FieldPool.Record.ForImplicitField(fieldDescription);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Default.class */
    public static class Default implements FieldRegistry {
        private final List<Entry> entries;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.entries.equals(((Default) obj).entries);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.entries.hashCode();
        }

        public Default() {
            this(Collections.emptyList());
        }

        private Default(List<Entry> list) {
            this.entries = list;
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldRegistry
        public FieldRegistry prepend(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, @MaybeNull Object obj, Transformer<FieldDescription> transformer) {
            ArrayList arrayList = new ArrayList(this.entries.size() + 1);
            arrayList.add(new Entry(latentMatcher, factory, obj, transformer));
            arrayList.addAll(this.entries);
            return new Default(arrayList);
        }

        @Override // net.bytebuddy.dynamic.scaffold.FieldRegistry
        public Compiled compile(TypeDescription typeDescription) {
            ArrayList arrayList = new ArrayList(this.entries.size());
            HashMap hashMap = new HashMap();
            for (Entry entry : this.entries) {
                FieldAttributeAppender fieldAttributeAppender = (FieldAttributeAppender) hashMap.get(entry.getFieldAttributeAppenderFactory());
                FieldAttributeAppender fieldAttributeAppender2 = fieldAttributeAppender;
                if (fieldAttributeAppender == null) {
                    fieldAttributeAppender2 = entry.getFieldAttributeAppenderFactory().make(typeDescription);
                    hashMap.put(entry.getFieldAttributeAppenderFactory(), fieldAttributeAppender2);
                }
                arrayList.add(new Compiled.Entry(entry.resolve(typeDescription), fieldAttributeAppender2, entry.getDefaultValue(), entry.getTransformer()));
            }
            return new Compiled(typeDescription, arrayList);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Default$Entry.class */
        protected static class Entry implements LatentMatcher<FieldDescription> {
            private final LatentMatcher<? super FieldDescription> matcher;
            private final FieldAttributeAppender.Factory fieldAttributeAppenderFactory;

            @MaybeNull
            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
            private final Object defaultValue;
            private final Transformer<FieldDescription> transformer;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass() || !this.matcher.equals(((Entry) obj).matcher) || !this.fieldAttributeAppenderFactory.equals(((Entry) obj).fieldAttributeAppenderFactory)) {
                    return false;
                }
                Object obj2 = this.defaultValue;
                Object obj3 = ((Entry) obj).defaultValue;
                if (obj3 != null) {
                    if (obj2 == null || !obj2.equals(obj3)) {
                        return false;
                    }
                } else if (obj2 != null) {
                    return false;
                }
                return this.transformer.equals(((Entry) obj).transformer);
            }

            public int hashCode() {
                int hashCode = ((((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.fieldAttributeAppenderFactory.hashCode()) * 31;
                Object obj = this.defaultValue;
                if (obj != null) {
                    hashCode += obj.hashCode();
                }
                return (hashCode * 31) + this.transformer.hashCode();
            }

            protected Entry(LatentMatcher<? super FieldDescription> latentMatcher, FieldAttributeAppender.Factory factory, @MaybeNull Object obj, Transformer<FieldDescription> transformer) {
                this.matcher = latentMatcher;
                this.fieldAttributeAppenderFactory = factory;
                this.defaultValue = obj;
                this.transformer = transformer;
            }

            protected FieldAttributeAppender.Factory getFieldAttributeAppenderFactory() {
                return this.fieldAttributeAppenderFactory;
            }

            @MaybeNull
            protected Object getDefaultValue() {
                return this.defaultValue;
            }

            protected Transformer<FieldDescription> getTransformer() {
                return this.transformer;
            }

            @Override // net.bytebuddy.matcher.LatentMatcher
            public ElementMatcher<? super FieldDescription> resolve(TypeDescription typeDescription) {
                return this.matcher.resolve(typeDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Default$Compiled.class */
        protected static class Compiled implements Compiled {
            private final TypeDescription instrumentedType;
            private final List<Entry> entries;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Compiled) obj).instrumentedType) && this.entries.equals(((Compiled) obj).entries);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.entries.hashCode();
            }

            protected Compiled(TypeDescription typeDescription, List<Entry> list) {
                this.instrumentedType = typeDescription;
                this.entries = list;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool
            public TypeWriter.FieldPool.Record target(FieldDescription fieldDescription) {
                for (Entry entry : this.entries) {
                    if (entry.matches(fieldDescription)) {
                        return entry.bind(this.instrumentedType, fieldDescription);
                    }
                }
                return new TypeWriter.FieldPool.Record.ForImplicitField(fieldDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/FieldRegistry$Default$Compiled$Entry.class */
            protected static class Entry implements ElementMatcher<FieldDescription> {
                private final ElementMatcher<? super FieldDescription> matcher;
                private final FieldAttributeAppender fieldAttributeAppender;

                @MaybeNull
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final Object defaultValue;
                private final Transformer<FieldDescription> transformer;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass() || !this.matcher.equals(((Entry) obj).matcher) || !this.fieldAttributeAppender.equals(((Entry) obj).fieldAttributeAppender)) {
                        return false;
                    }
                    Object obj2 = this.defaultValue;
                    Object obj3 = ((Entry) obj).defaultValue;
                    if (obj3 != null) {
                        if (obj2 == null || !obj2.equals(obj3)) {
                            return false;
                        }
                    } else if (obj2 != null) {
                        return false;
                    }
                    return this.transformer.equals(((Entry) obj).transformer);
                }

                public int hashCode() {
                    int hashCode = ((((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.fieldAttributeAppender.hashCode()) * 31;
                    Object obj = this.defaultValue;
                    if (obj != null) {
                        hashCode += obj.hashCode();
                    }
                    return (hashCode * 31) + this.transformer.hashCode();
                }

                protected Entry(ElementMatcher<? super FieldDescription> elementMatcher, FieldAttributeAppender fieldAttributeAppender, @MaybeNull Object obj, Transformer<FieldDescription> transformer) {
                    this.matcher = elementMatcher;
                    this.fieldAttributeAppender = fieldAttributeAppender;
                    this.defaultValue = obj;
                    this.transformer = transformer;
                }

                protected TypeWriter.FieldPool.Record bind(TypeDescription typeDescription, FieldDescription fieldDescription) {
                    return new TypeWriter.FieldPool.Record.ForExplicitField(this.fieldAttributeAppender, this.defaultValue, this.transformer.transform(typeDescription, fieldDescription));
                }

                @Override // net.bytebuddy.matcher.ElementMatcher
                public boolean matches(@MaybeNull FieldDescription fieldDescription) {
                    return this.matcher.matches(fieldDescription);
                }
            }
        }
    }
}

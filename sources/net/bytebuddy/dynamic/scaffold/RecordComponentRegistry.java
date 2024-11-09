package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry.class */
public interface RecordComponentRegistry {
    RecordComponentRegistry prepend(LatentMatcher<? super RecordComponentDescription> latentMatcher, RecordComponentAttributeAppender.Factory factory, Transformer<RecordComponentDescription> transformer);

    Compiled compile(TypeDescription typeDescription);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Compiled.class */
    public interface Compiled extends TypeWriter.RecordComponentPool {

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Compiled$NoOp.class */
        public enum NoOp implements Compiled {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool
            public final TypeWriter.RecordComponentPool.Record target(RecordComponentDescription recordComponentDescription) {
                return new TypeWriter.RecordComponentPool.Record.ForImplicitRecordComponent(recordComponentDescription);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Default.class */
    public static class Default implements RecordComponentRegistry {
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

        @Override // net.bytebuddy.dynamic.scaffold.RecordComponentRegistry
        public RecordComponentRegistry prepend(LatentMatcher<? super RecordComponentDescription> latentMatcher, RecordComponentAttributeAppender.Factory factory, Transformer<RecordComponentDescription> transformer) {
            ArrayList arrayList = new ArrayList(this.entries.size() + 1);
            arrayList.add(new Entry(latentMatcher, factory, transformer));
            arrayList.addAll(this.entries);
            return new Default(arrayList);
        }

        @Override // net.bytebuddy.dynamic.scaffold.RecordComponentRegistry
        public Compiled compile(TypeDescription typeDescription) {
            ArrayList arrayList = new ArrayList(this.entries.size());
            HashMap hashMap = new HashMap();
            for (Entry entry : this.entries) {
                RecordComponentAttributeAppender recordComponentAttributeAppender = (RecordComponentAttributeAppender) hashMap.get(entry.getRecordComponentAttributeAppender());
                RecordComponentAttributeAppender recordComponentAttributeAppender2 = recordComponentAttributeAppender;
                if (recordComponentAttributeAppender == null) {
                    recordComponentAttributeAppender2 = entry.getRecordComponentAttributeAppender().make(typeDescription);
                    hashMap.put(entry.getRecordComponentAttributeAppender(), recordComponentAttributeAppender2);
                }
                arrayList.add(new Compiled.Entry(entry.resolve(typeDescription), recordComponentAttributeAppender2, entry.getTransformer()));
            }
            return new Compiled(typeDescription, arrayList);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Default$Entry.class */
        protected static class Entry implements LatentMatcher<RecordComponentDescription> {
            private final LatentMatcher<? super RecordComponentDescription> matcher;
            private final RecordComponentAttributeAppender.Factory recordComponentAttributeAppender;
            private final Transformer<RecordComponentDescription> transformer;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Entry) obj).matcher) && this.recordComponentAttributeAppender.equals(((Entry) obj).recordComponentAttributeAppender) && this.transformer.equals(((Entry) obj).transformer);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.recordComponentAttributeAppender.hashCode()) * 31) + this.transformer.hashCode();
            }

            protected Entry(LatentMatcher<? super RecordComponentDescription> latentMatcher, RecordComponentAttributeAppender.Factory factory, Transformer<RecordComponentDescription> transformer) {
                this.matcher = latentMatcher;
                this.recordComponentAttributeAppender = factory;
                this.transformer = transformer;
            }

            protected RecordComponentAttributeAppender.Factory getRecordComponentAttributeAppender() {
                return this.recordComponentAttributeAppender;
            }

            protected Transformer<RecordComponentDescription> getTransformer() {
                return this.transformer;
            }

            @Override // net.bytebuddy.matcher.LatentMatcher
            public ElementMatcher<? super RecordComponentDescription> resolve(TypeDescription typeDescription) {
                return this.matcher.resolve(typeDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Default$Compiled.class */
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

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool
            public TypeWriter.RecordComponentPool.Record target(RecordComponentDescription recordComponentDescription) {
                for (Entry entry : this.entries) {
                    if (entry.matches(recordComponentDescription)) {
                        return entry.bind(this.instrumentedType, recordComponentDescription);
                    }
                }
                return new TypeWriter.RecordComponentPool.Record.ForImplicitRecordComponent(recordComponentDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/RecordComponentRegistry$Default$Compiled$Entry.class */
            protected static class Entry implements ElementMatcher<RecordComponentDescription> {
                private final ElementMatcher<? super RecordComponentDescription> matcher;
                private final RecordComponentAttributeAppender recordComponentAttributeAppender;
                private final Transformer<RecordComponentDescription> transformer;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Entry) obj).matcher) && this.recordComponentAttributeAppender.equals(((Entry) obj).recordComponentAttributeAppender) && this.transformer.equals(((Entry) obj).transformer);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.recordComponentAttributeAppender.hashCode()) * 31) + this.transformer.hashCode();
                }

                protected Entry(ElementMatcher<? super RecordComponentDescription> elementMatcher, RecordComponentAttributeAppender recordComponentAttributeAppender, Transformer<RecordComponentDescription> transformer) {
                    this.matcher = elementMatcher;
                    this.recordComponentAttributeAppender = recordComponentAttributeAppender;
                    this.transformer = transformer;
                }

                protected TypeWriter.RecordComponentPool.Record bind(TypeDescription typeDescription, RecordComponentDescription recordComponentDescription) {
                    return new TypeWriter.RecordComponentPool.Record.ForExplicitRecordComponent(this.recordComponentAttributeAppender, this.transformer.transform(typeDescription, recordComponentDescription));
                }

                @Override // net.bytebuddy.matcher.ElementMatcher
                public boolean matches(@MaybeNull RecordComponentDescription recordComponentDescription) {
                    return this.matcher.matches(recordComponentDescription);
                }
            }
        }
    }
}

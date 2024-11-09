package net.bytebuddy.dynamic.scaffold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.VisibilityBridgeStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry.class */
public interface MethodRegistry {

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Compiled.class */
    public interface Compiled extends TypeWriter.MethodPool {
        TypeDescription getInstrumentedType();

        MethodList<?> getMethods();

        MethodList<?> getInstrumentedMethods();

        LoadedTypeInitializer getLoadedTypeInitializer();

        TypeInitializer getTypeInitializer();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Prepared.class */
    public interface Prepared {
        TypeDescription getInstrumentedType();

        MethodList<?> getMethods();

        MethodList<?> getInstrumentedMethods();

        LoadedTypeInitializer getLoadedTypeInitializer();

        TypeInitializer getTypeInitializer();

        Compiled compile(Implementation.Target.Factory factory, ClassFileVersion classFileVersion);
    }

    MethodRegistry prepend(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

    MethodRegistry append(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer);

    Prepared prepare(InstrumentedType instrumentedType, MethodGraph.Compiler compiler, TypeValidation typeValidation, VisibilityBridgeStrategy visibilityBridgeStrategy, LatentMatcher<? super MethodDescription> latentMatcher);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler.class */
    public interface Handler extends InstrumentedType.Prepareable {

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$Compiled.class */
        public interface Compiled {
            TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility);
        }

        Compiled compile(Implementation.Target target);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForAbstractMethod.class */
        public enum ForAbstractMethod implements Handler, Compiled {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public final Compiled compile(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
            public final TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithoutBody(methodDescription, methodAttributeAppender, visibility);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForVisibilityBridge.class */
        public enum ForVisibilityBridge implements Handler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public final InstrumentedType prepare(InstrumentedType instrumentedType) {
                throw new IllegalStateException("A visibility bridge handler must not apply any preparations");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public final Compiled compile(Implementation.Target target) {
                return new Compiled(target.getInstrumentedType());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForVisibilityBridge$Compiled.class */
            public static class Compiled implements Compiled {
                private final TypeDescription instrumentedType;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Compiled) obj).instrumentedType);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.instrumentedType.hashCode();
                }

                protected Compiled(TypeDescription typeDescription) {
                    this.instrumentedType = typeDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
                public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                    return TypeWriter.MethodPool.Record.ForDefinedMethod.OfVisibilityBridge.of(this.instrumentedType, methodDescription, methodAttributeAppender);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForImplementation.class */
        public static class ForImplementation implements Handler {
            private final Implementation implementation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.implementation.equals(((ForImplementation) obj).implementation);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.implementation.hashCode();
            }

            public ForImplementation(Implementation implementation) {
                this.implementation = implementation;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return this.implementation.prepare(instrumentedType);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return new Compiled(this.implementation.appender(target));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForImplementation$Compiled.class */
            public static class Compiled implements Compiled {
                private final ByteCodeAppender byteCodeAppender;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.byteCodeAppender.equals(((Compiled) obj).byteCodeAppender);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.byteCodeAppender.hashCode();
                }

                protected Compiled(ByteCodeAppender byteCodeAppender) {
                    this.byteCodeAppender = byteCodeAppender;
                }

                @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
                public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                    return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithBody(methodDescription, this.byteCodeAppender, methodAttributeAppender, visibility);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Handler$ForAnnotationValue.class */
        public static class ForAnnotationValue implements Handler, Compiled {
            private final AnnotationValue<?, ?> annotationValue;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.annotationValue.equals(((ForAnnotationValue) obj).annotationValue);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.annotationValue.hashCode();
            }

            public ForAnnotationValue(AnnotationValue<?, ?> annotationValue) {
                this.annotationValue = annotationValue;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler
            public Compiled compile(Implementation.Target target) {
                return this;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.Compiled
            public TypeWriter.MethodPool.Record assemble(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                return new TypeWriter.MethodPool.Record.ForDefinedMethod.WithAnnotationDefaultValue(methodDescription, this.annotationValue, methodAttributeAppender);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default.class */
    public static class Default implements MethodRegistry {
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
            this.entries = Collections.emptyList();
        }

        private Default(List<Entry> list) {
            this.entries = list;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public MethodRegistry prepend(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
            return new Default(CompoundList.of(new Entry(latentMatcher, handler, factory, transformer), this.entries));
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public MethodRegistry append(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
            return new Default(CompoundList.of(this.entries, new Entry(latentMatcher, handler, factory, transformer)));
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry
        public Prepared prepare(InstrumentedType instrumentedType, MethodGraph.Compiler compiler, TypeValidation typeValidation, VisibilityBridgeStrategy visibilityBridgeStrategy, LatentMatcher<? super MethodDescription> latentMatcher) {
            InstrumentedType prepare;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet(instrumentedType.getDeclaredMethods());
            for (Entry entry : this.entries) {
                if (hashSet.add(entry.getHandler()) && instrumentedType != (prepare = entry.getHandler().prepare(instrumentedType))) {
                    for (MethodDescription methodDescription : prepare.getDeclaredMethods()) {
                        if (!hashSet2.contains(methodDescription)) {
                            linkedHashMap.put(methodDescription, entry.asSupplementaryEntry(methodDescription));
                            hashSet2.add(methodDescription);
                        }
                    }
                    instrumentedType = prepare;
                }
            }
            MethodGraph.Linked compile = compiler.compile((TypeDefinition) instrumentedType);
            ElementMatcher.Junction and = ElementMatchers.not(ElementMatchers.anyOf(linkedHashMap.keySet())).and(ElementMatchers.returns(ElementMatchers.isVisibleTo(instrumentedType))).and(ElementMatchers.hasParameters(ElementMatchers.whereNone(ElementMatchers.hasType(ElementMatchers.not(ElementMatchers.isVisibleTo(instrumentedType)))))).and(latentMatcher.resolve(instrumentedType));
            ArrayList arrayList = new ArrayList();
            Iterator it = compile.listNodes().iterator();
            while (it.hasNext()) {
                MethodGraph.Node node = (MethodGraph.Node) it.next();
                MethodDescription representative = node.getRepresentative();
                boolean z = instrumentedType.isPublic() && !instrumentedType.isInterface();
                if (and.matches(representative)) {
                    Iterator<Entry> it2 = this.entries.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Entry next = it2.next();
                        if (next.resolve(instrumentedType).matches(representative)) {
                            linkedHashMap.put(representative, next.asPreparedEntry(instrumentedType, representative, node.getMethodTypes(), node.getVisibility()));
                            z = false;
                            break;
                        }
                    }
                }
                if (z && !node.getSort().isMadeVisible() && representative.isPublic() && !representative.isAbstract() && !representative.isFinal() && representative.getDeclaringType().isPackagePrivate() && visibilityBridgeStrategy.generateVisibilityBridge(representative)) {
                    linkedHashMap.put(representative, Prepared.Entry.forVisibilityBridge(representative, node.getVisibility()));
                }
                arrayList.add(representative);
            }
            for (MethodDescription methodDescription2 : CompoundList.of((List<? extends MethodDescription.Latent.TypeInitializer>) instrumentedType.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isVirtual()).and(and)), new MethodDescription.Latent.TypeInitializer(instrumentedType))) {
                Iterator<Entry> it3 = this.entries.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Entry next2 = it3.next();
                        if (next2.resolve(instrumentedType).matches(methodDescription2)) {
                            linkedHashMap.put(methodDescription2, next2.asPreparedEntry(instrumentedType, methodDescription2, methodDescription2.getVisibility()));
                            break;
                        }
                    }
                }
                arrayList.add(methodDescription2);
            }
            return new Prepared(linkedHashMap, instrumentedType.getLoadedTypeInitializer(), instrumentedType.getTypeInitializer(), typeValidation.isEnabled() ? instrumentedType.validated() : instrumentedType, compile, new MethodList.Explicit(arrayList));
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default$Entry.class */
        protected static class Entry implements LatentMatcher<MethodDescription> {
            private final LatentMatcher<? super MethodDescription> matcher;
            private final Handler handler;
            private final MethodAttributeAppender.Factory attributeAppenderFactory;
            private final Transformer<MethodDescription> transformer;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Entry) obj).matcher) && this.handler.equals(((Entry) obj).handler) && this.attributeAppenderFactory.equals(((Entry) obj).attributeAppenderFactory) && this.transformer.equals(((Entry) obj).transformer);
            }

            public int hashCode() {
                return (((((((getClass().hashCode() * 31) + this.matcher.hashCode()) * 31) + this.handler.hashCode()) * 31) + this.attributeAppenderFactory.hashCode()) * 31) + this.transformer.hashCode();
            }

            protected Entry(LatentMatcher<? super MethodDescription> latentMatcher, Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                this.matcher = latentMatcher;
                this.handler = handler;
                this.attributeAppenderFactory = factory;
                this.transformer = transformer;
            }

            protected Prepared.Entry asPreparedEntry(TypeDescription typeDescription, MethodDescription methodDescription, Visibility visibility) {
                return asPreparedEntry(typeDescription, methodDescription, Collections.emptySet(), visibility);
            }

            protected Prepared.Entry asPreparedEntry(TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility) {
                return new Prepared.Entry(this.handler, this.attributeAppenderFactory, this.transformer.transform(typeDescription, methodDescription), set, visibility, false);
            }

            protected Prepared.Entry asSupplementaryEntry(MethodDescription methodDescription) {
                return new Prepared.Entry(this.handler, MethodAttributeAppender.Explicit.of(methodDescription), methodDescription, Collections.emptySet(), methodDescription.getVisibility(), false);
            }

            protected Handler getHandler() {
                return this.handler;
            }

            @Override // net.bytebuddy.matcher.LatentMatcher
            public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
                return this.matcher.resolve(typeDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default$Prepared.class */
        protected static class Prepared implements Prepared {
            private final LinkedHashMap<MethodDescription, Entry> implementations;
            private final LoadedTypeInitializer loadedTypeInitializer;
            private final TypeInitializer typeInitializer;
            private final TypeDescription instrumentedType;
            private final MethodGraph.Linked methodGraph;
            private final MethodList<?> methods;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.implementations.equals(((Prepared) obj).implementations) && this.loadedTypeInitializer.equals(((Prepared) obj).loadedTypeInitializer) && this.typeInitializer.equals(((Prepared) obj).typeInitializer) && this.instrumentedType.equals(((Prepared) obj).instrumentedType) && this.methodGraph.equals(((Prepared) obj).methodGraph) && this.methods.equals(((Prepared) obj).methods);
            }

            public int hashCode() {
                return (((((((((((getClass().hashCode() * 31) + this.implementations.hashCode()) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.typeInitializer.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.methodGraph.hashCode()) * 31) + this.methods.hashCode();
            }

            protected Prepared(LinkedHashMap<MethodDescription, Entry> linkedHashMap, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeDescription typeDescription, MethodGraph.Linked linked, MethodList<?> methodList) {
                this.implementations = linkedHashMap;
                this.loadedTypeInitializer = loadedTypeInitializer;
                this.typeInitializer = typeInitializer;
                this.instrumentedType = typeDescription;
                this.methodGraph = linked;
                this.methods = methodList;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public LoadedTypeInitializer getLoadedTypeInitializer() {
                return this.loadedTypeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public TypeInitializer getTypeInitializer() {
                return this.typeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public MethodList<?> getMethods() {
                return this.methods;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public MethodList<?> getInstrumentedMethods() {
                return (MethodList) new MethodList.Explicit(new ArrayList(this.implementations.keySet())).filter(ElementMatchers.not(ElementMatchers.isTypeInitializer()));
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Prepared
            public Compiled compile(Implementation.Target.Factory factory, ClassFileVersion classFileVersion) {
                HashMap hashMap = new HashMap();
                HashMap hashMap2 = new HashMap();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Implementation.Target make = factory.make(this.instrumentedType, this.methodGraph, classFileVersion);
                for (Map.Entry<MethodDescription, Entry> entry : this.implementations.entrySet()) {
                    Handler.Compiled compiled = (Handler.Compiled) hashMap.get(entry.getValue().getHandler());
                    Handler.Compiled compiled2 = compiled;
                    if (compiled == null) {
                        compiled2 = entry.getValue().getHandler().compile(make);
                        hashMap.put(entry.getValue().getHandler(), compiled2);
                    }
                    MethodAttributeAppender methodAttributeAppender = (MethodAttributeAppender) hashMap2.get(entry.getValue().getAppenderFactory());
                    MethodAttributeAppender methodAttributeAppender2 = methodAttributeAppender;
                    if (methodAttributeAppender == null) {
                        methodAttributeAppender2 = entry.getValue().getAppenderFactory().make(this.instrumentedType);
                        hashMap2.put(entry.getValue().getAppenderFactory(), methodAttributeAppender2);
                    }
                    linkedHashMap.put(entry.getKey(), new Compiled.Entry(compiled2, methodAttributeAppender2, entry.getValue().getMethodDescription(), entry.getValue().resolveBridgeTypes(), entry.getValue().getVisibility(), entry.getValue().isBridgeMethod()));
                }
                return new Compiled(this.instrumentedType, this.loadedTypeInitializer, this.typeInitializer, this.methods, linkedHashMap, classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default$Prepared$Entry.class */
            public static class Entry {
                private final Handler handler;
                private final MethodAttributeAppender.Factory attributeAppenderFactory;
                private final MethodDescription methodDescription;
                private final Set<MethodDescription.TypeToken> typeTokens;
                private final Visibility visibility;
                private final boolean bridgeMethod;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.bridgeMethod == ((Entry) obj).bridgeMethod && this.visibility.equals(((Entry) obj).visibility) && this.handler.equals(((Entry) obj).handler) && this.attributeAppenderFactory.equals(((Entry) obj).attributeAppenderFactory) && this.methodDescription.equals(((Entry) obj).methodDescription) && this.typeTokens.equals(((Entry) obj).typeTokens);
                }

                public int hashCode() {
                    return (((((((((((getClass().hashCode() * 31) + this.handler.hashCode()) * 31) + this.attributeAppenderFactory.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.typeTokens.hashCode()) * 31) + this.visibility.hashCode()) * 31) + (this.bridgeMethod ? 1 : 0);
                }

                protected Entry(Handler handler, MethodAttributeAppender.Factory factory, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility, boolean z) {
                    this.handler = handler;
                    this.attributeAppenderFactory = factory;
                    this.methodDescription = methodDescription;
                    this.typeTokens = set;
                    this.visibility = visibility;
                    this.bridgeMethod = z;
                }

                protected static Entry forVisibilityBridge(MethodDescription methodDescription, Visibility visibility) {
                    return new Entry(Handler.ForVisibilityBridge.INSTANCE, MethodAttributeAppender.Explicit.of(methodDescription), methodDescription, Collections.emptySet(), visibility, true);
                }

                protected Handler getHandler() {
                    return this.handler;
                }

                protected MethodAttributeAppender.Factory getAppenderFactory() {
                    return this.attributeAppenderFactory;
                }

                protected MethodDescription getMethodDescription() {
                    return this.methodDescription;
                }

                protected Set<MethodDescription.TypeToken> resolveBridgeTypes() {
                    HashSet hashSet = new HashSet(this.typeTokens);
                    hashSet.remove(this.methodDescription.asTypeToken());
                    return hashSet;
                }

                protected Visibility getVisibility() {
                    return this.visibility;
                }

                protected boolean isBridgeMethod() {
                    return this.bridgeMethod;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default$Compiled.class */
        protected static class Compiled implements Compiled {
            private final TypeDescription instrumentedType;
            private final LoadedTypeInitializer loadedTypeInitializer;
            private final TypeInitializer typeInitializer;
            private final MethodList<?> methods;
            private final LinkedHashMap<MethodDescription, Entry> implementations;
            private final boolean supportsBridges;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.supportsBridges == ((Compiled) obj).supportsBridges && this.instrumentedType.equals(((Compiled) obj).instrumentedType) && this.loadedTypeInitializer.equals(((Compiled) obj).loadedTypeInitializer) && this.typeInitializer.equals(((Compiled) obj).typeInitializer) && this.methods.equals(((Compiled) obj).methods) && this.implementations.equals(((Compiled) obj).implementations);
            }

            public int hashCode() {
                return (((((((((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.typeInitializer.hashCode()) * 31) + this.methods.hashCode()) * 31) + this.implementations.hashCode()) * 31) + (this.supportsBridges ? 1 : 0);
            }

            protected Compiled(TypeDescription typeDescription, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, MethodList<?> methodList, LinkedHashMap<MethodDescription, Entry> linkedHashMap, boolean z) {
                this.instrumentedType = typeDescription;
                this.loadedTypeInitializer = loadedTypeInitializer;
                this.typeInitializer = typeInitializer;
                this.methods = methodList;
                this.implementations = linkedHashMap;
                this.supportsBridges = z;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public LoadedTypeInitializer getLoadedTypeInitializer() {
                return this.loadedTypeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public TypeInitializer getTypeInitializer() {
                return this.typeInitializer;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public MethodList<?> getMethods() {
                return this.methods;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodRegistry.Compiled
            public MethodList<?> getInstrumentedMethods() {
                return (MethodList) new MethodList.Explicit(new ArrayList(this.implementations.keySet())).filter(ElementMatchers.not(ElementMatchers.isTypeInitializer()));
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool
            public TypeWriter.MethodPool.Record target(MethodDescription methodDescription) {
                Entry entry = this.implementations.get(methodDescription);
                return entry == null ? new TypeWriter.MethodPool.Record.ForNonImplementedMethod(methodDescription) : entry.bind(this.instrumentedType, this.supportsBridges);
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodRegistry$Default$Compiled$Entry.class */
            protected static class Entry {
                private final Handler.Compiled handler;
                private final MethodAttributeAppender attributeAppender;
                private final MethodDescription methodDescription;
                private final Set<MethodDescription.TypeToken> bridgeTypes;
                private final Visibility visibility;
                private final boolean bridgeMethod;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.bridgeMethod == ((Entry) obj).bridgeMethod && this.visibility.equals(((Entry) obj).visibility) && this.handler.equals(((Entry) obj).handler) && this.attributeAppender.equals(((Entry) obj).attributeAppender) && this.methodDescription.equals(((Entry) obj).methodDescription) && this.bridgeTypes.equals(((Entry) obj).bridgeTypes);
                }

                public int hashCode() {
                    return (((((((((((getClass().hashCode() * 31) + this.handler.hashCode()) * 31) + this.attributeAppender.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.bridgeTypes.hashCode()) * 31) + this.visibility.hashCode()) * 31) + (this.bridgeMethod ? 1 : 0);
                }

                protected Entry(Handler.Compiled compiled, MethodAttributeAppender methodAttributeAppender, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, Visibility visibility, boolean z) {
                    this.handler = compiled;
                    this.attributeAppender = methodAttributeAppender;
                    this.methodDescription = methodDescription;
                    this.bridgeTypes = set;
                    this.visibility = visibility;
                    this.bridgeMethod = z;
                }

                protected TypeWriter.MethodPool.Record bind(TypeDescription typeDescription, boolean z) {
                    if (this.bridgeMethod && !z) {
                        return new TypeWriter.MethodPool.Record.ForNonImplementedMethod(this.methodDescription);
                    }
                    TypeWriter.MethodPool.Record assemble = this.handler.assemble(this.methodDescription, this.attributeAppender, this.visibility);
                    return z ? TypeWriter.MethodPool.Record.AccessBridgeWrapper.of(assemble, typeDescription, this.methodDescription, this.bridgeTypes, this.attributeAppender) : assemble;
                }
            }
        }
    }
}

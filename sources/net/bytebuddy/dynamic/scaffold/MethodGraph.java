package net.bytebuddy.dynamic.scaffold;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.FilterableList;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph.class */
public interface MethodGraph {
    Node locate(MethodDescription.SignatureToken signatureToken);

    NodeList listNodes();

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Empty.class */
    public enum Empty implements Compiler, Linked {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public final Node locate(MethodDescription.SignatureToken signatureToken) {
            return Node.Unresolved.INSTANCE;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public final NodeList listNodes() {
            return new NodeList(Collections.emptyList());
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
        public final MethodGraph getSuperClassGraph() {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
        public final MethodGraph getInterfaceGraph(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public final Linked compile(TypeDefinition typeDefinition) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        @Deprecated
        public final Linked compile(TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        public final Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
            return this;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
        @Deprecated
        public final Linked compile(TypeDescription typeDescription, TypeDescription typeDescription2) {
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Linked.class */
    public interface Linked extends MethodGraph {
        MethodGraph getSuperClassGraph();

        MethodGraph getInterfaceGraph(TypeDescription typeDescription);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Linked$Delegation.class */
        public static class Delegation implements Linked {
            private final MethodGraph methodGraph;
            private final MethodGraph superClassGraph;
            private final Map<TypeDescription, MethodGraph> interfaceGraphs;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodGraph.equals(((Delegation) obj).methodGraph) && this.superClassGraph.equals(((Delegation) obj).superClassGraph) && this.interfaceGraphs.equals(((Delegation) obj).interfaceGraphs);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.methodGraph.hashCode()) * 31) + this.superClassGraph.hashCode()) * 31) + this.interfaceGraphs.hashCode();
            }

            public Delegation(MethodGraph methodGraph, MethodGraph methodGraph2, Map<TypeDescription, MethodGraph> map) {
                this.methodGraph = methodGraph;
                this.superClassGraph = methodGraph2;
                this.interfaceGraphs = map;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
            public MethodGraph getSuperClassGraph() {
                return this.superClassGraph;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Linked
            public MethodGraph getInterfaceGraph(TypeDescription typeDescription) {
                MethodGraph methodGraph = this.interfaceGraphs.get(typeDescription);
                return methodGraph == null ? Empty.INSTANCE : methodGraph;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
            public Node locate(MethodDescription.SignatureToken signatureToken) {
                return this.methodGraph.locate(signatureToken);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
            public NodeList listNodes() {
                return this.methodGraph.listNodes();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Node.class */
    public interface Node {
        Sort getSort();

        MethodDescription getRepresentative();

        Set<MethodDescription.TypeToken> getMethodTypes();

        Visibility getVisibility();

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Node$Sort.class */
        public enum Sort {
            VISIBLE(true, true, true),
            RESOLVED(true, true, false),
            AMBIGUOUS(true, false, false),
            UNRESOLVED(false, false, false);

            private final boolean resolved;
            private final boolean unique;
            private final boolean madeVisible;

            Sort(boolean z, boolean z2, boolean z3) {
                this.resolved = z;
                this.unique = z2;
                this.madeVisible = z3;
            }

            public final boolean isResolved() {
                return this.resolved;
            }

            public final boolean isUnique() {
                return this.unique;
            }

            public final boolean isMadeVisible() {
                return this.madeVisible;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Node$Unresolved.class */
        public enum Unresolved implements Node {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public final Sort getSort() {
                return Sort.UNRESOLVED;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public final MethodDescription getRepresentative() {
                throw new IllegalStateException("Cannot resolve the method of an illegal node");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public final Set<MethodDescription.TypeToken> getMethodTypes() {
                throw new IllegalStateException("Cannot resolve bridge method of an illegal node");
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public final Visibility getVisibility() {
                throw new IllegalStateException("Cannot resolve visibility of an illegal node");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Node$Simple.class */
        public static class Simple implements Node {
            private final MethodDescription methodDescription;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((Simple) obj).methodDescription);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
            }

            public Simple(MethodDescription methodDescription) {
                this.methodDescription = methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Sort getSort() {
                return Sort.RESOLVED;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public MethodDescription getRepresentative() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Set<MethodDescription.TypeToken> getMethodTypes() {
                return Collections.emptySet();
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
            public Visibility getVisibility() {
                return this.methodDescription.getVisibility();
            }
        }
    }

    @SuppressFBWarnings(value = {"IC_SUPERCLASS_USES_SUBCLASS_DURING_INITIALIZATION"}, justification = "Safe initialization is implied.")
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler.class */
    public interface Compiler {
        public static final Compiler DEFAULT = Default.forJavaHierarchy();

        Linked compile(TypeDefinition typeDefinition);

        @Deprecated
        Linked compile(TypeDescription typeDescription);

        Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription);

        @Deprecated
        Linked compile(TypeDescription typeDescription, TypeDescription typeDescription2);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$ForDeclaredMethods.class */
        public enum ForDeclaredMethods implements Compiler {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public final Linked compile(TypeDefinition typeDefinition) {
                return compile(typeDefinition, typeDefinition.asErasure());
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            @Deprecated
            public final Linked compile(TypeDescription typeDescription) {
                return compile((TypeDefinition) typeDescription, typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public final Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (MethodDescription methodDescription : typeDefinition.getDeclaredMethods().filter(ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isBridge())).and(ElementMatchers.isVisibleTo(typeDescription)))) {
                    linkedHashMap.put(methodDescription.asSignatureToken(), new Node.Simple(methodDescription));
                }
                return new Linked.Delegation(new Simple(linkedHashMap), Empty.INSTANCE, Collections.emptyMap());
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            @Deprecated
            public final Linked compile(TypeDescription typeDescription, TypeDescription typeDescription2) {
                return compile((TypeDefinition) typeDescription, typeDescription2);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$AbstractBase.class */
        public static abstract class AbstractBase implements Compiler {
            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDefinition typeDefinition) {
                return compile(typeDefinition, typeDefinition.asErasure());
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            @Deprecated
            public Linked compile(TypeDescription typeDescription) {
                return compile((TypeDefinition) typeDescription, typeDescription);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            @Deprecated
            public Linked compile(TypeDescription typeDescription, TypeDescription typeDescription2) {
                return compile((TypeDefinition) typeDescription, typeDescription2);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default.class */
        public static class Default<T> extends AbstractBase {
            private final Harmonizer<T> harmonizer;
            private final Merger merger;
            private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.harmonizer.equals(((Default) obj).harmonizer) && this.merger.equals(((Default) obj).merger) && this.visitor.equals(((Default) obj).visitor);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.harmonizer.hashCode()) * 31) + this.merger.hashCode()) * 31) + this.visitor.hashCode();
            }

            protected Default(Harmonizer<T> harmonizer, Merger merger, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                this.harmonizer = harmonizer;
                this.merger = merger;
                this.visitor = visitor;
            }

            public static <S> Compiler of(Harmonizer<S> harmonizer, Merger merger) {
                return new Default(harmonizer, merger, TypeDescription.Generic.Visitor.Reifying.INITIATING);
            }

            public static <S> Compiler of(Harmonizer<S> harmonizer, Merger merger, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                return new Default(harmonizer, merger, visitor);
            }

            public static Compiler forJavaHierarchy() {
                return of(Harmonizer.ForJavaMethod.INSTANCE, Merger.Directional.LEFT);
            }

            public static Compiler forJVMHierarchy() {
                return of(Harmonizer.ForJVMMethod.INSTANCE, Merger.Directional.LEFT);
            }

            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler
            public Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
                Key.Store<T> store;
                Map<TypeDefinition, Key.Store<T>> hashMap = new HashMap<>();
                Key.Store<T> doAnalyze = doAnalyze(typeDefinition, hashMap, ElementMatchers.isVirtual().and(ElementMatchers.isVisibleTo(typeDescription)));
                TypeDescription.Generic superClass = typeDefinition.getSuperClass();
                TypeList.Generic interfaces = typeDefinition.getInterfaces();
                HashMap hashMap2 = new HashMap();
                for (TypeDescription.Generic generic : interfaces) {
                    Key.Store<T> store2 = hashMap.get(generic);
                    if (store2 == null) {
                        throw new IllegalStateException("Failed to resolve interface type " + generic + " from " + hashMap.keySet());
                    }
                    hashMap2.put(generic.asErasure(), store2.asGraph(this.merger));
                }
                if (superClass == null) {
                    store = null;
                } else {
                    Key.Store<T> store3 = hashMap.get(superClass);
                    store = store3;
                    if (store3 == null) {
                        throw new IllegalStateException("Failed to resolve super class " + superClass + " from " + hashMap.keySet());
                    }
                }
                return new Linked.Delegation(doAnalyze.asGraph(this.merger), store == null ? Empty.INSTANCE : store.asGraph(this.merger), hashMap2);
            }

            protected Key.Store<T> analyze(TypeDefinition typeDefinition, TypeDefinition typeDefinition2, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                Key.Store<T> store = map.get(typeDefinition2);
                Key.Store<T> store2 = store;
                if (store == null) {
                    store2 = doAnalyze(typeDefinition, map, elementMatcher);
                    map.put(typeDefinition2, store2);
                }
                return store2;
            }

            protected Key.Store<T> analyzeNullable(@MaybeNull TypeDescription.Generic generic, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                return generic == null ? new Key.Store<>() : analyze((TypeDefinition) generic.accept(this.visitor), generic, map, elementMatcher);
            }

            /* JADX WARN: Multi-variable type inference failed */
            protected Key.Store<T> doAnalyze(TypeDefinition typeDefinition, Map<TypeDefinition, Key.Store<T>> map, ElementMatcher<? super MethodDescription> elementMatcher) {
                Key.Store<T> analyzeNullable = analyzeNullable(typeDefinition.getSuperClass(), map, elementMatcher);
                Key.Store<T> store = new Key.Store<>();
                for (TypeDescription.Generic generic : typeDefinition.getInterfaces()) {
                    store = store.combineWith(analyze((TypeDefinition) generic.accept(this.visitor), generic, map, elementMatcher));
                }
                return analyzeNullable.inject(store).registerTopLevel(typeDefinition.getDeclaredMethods().filter(elementMatcher), this.harmonizer);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Harmonizer.class */
            public interface Harmonizer<S> {
                S harmonize(MethodDescription.TypeToken typeToken);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Harmonizer$ForJavaMethod.class */
                public enum ForJavaMethod implements Harmonizer<Token> {
                    INSTANCE;

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Harmonizer
                    public final Token harmonize(MethodDescription.TypeToken typeToken) {
                        return new Token(typeToken);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Harmonizer$ForJavaMethod$Token.class */
                    public static class Token {
                        private final MethodDescription.TypeToken typeToken;
                        private final int hashCode;

                        protected Token(MethodDescription.TypeToken typeToken) {
                            this.typeToken = typeToken;
                            this.hashCode = typeToken.getParameterTypes().hashCode();
                        }

                        public int hashCode() {
                            return this.hashCode;
                        }

                        public boolean equals(@MaybeNull Object obj) {
                            if (this != obj) {
                                return (obj instanceof Token) && this.typeToken.getParameterTypes().equals(((Token) obj).typeToken.getParameterTypes());
                            }
                            return true;
                        }

                        public String toString() {
                            return this.typeToken.getParameterTypes().toString();
                        }
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Harmonizer$ForJVMMethod.class */
                public enum ForJVMMethod implements Harmonizer<Token> {
                    INSTANCE;

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Harmonizer
                    public final Token harmonize(MethodDescription.TypeToken typeToken) {
                        return new Token(typeToken);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Harmonizer$ForJVMMethod$Token.class */
                    public static class Token {
                        private final MethodDescription.TypeToken typeToken;
                        private final int hashCode;

                        public Token(MethodDescription.TypeToken typeToken) {
                            this.typeToken = typeToken;
                            this.hashCode = typeToken.getReturnType().hashCode() + (31 * typeToken.getParameterTypes().hashCode());
                        }

                        public int hashCode() {
                            return this.hashCode;
                        }

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (!(obj instanceof Token)) {
                                return false;
                            }
                            Token token = (Token) obj;
                            return this.typeToken.getReturnType().equals(token.typeToken.getReturnType()) && this.typeToken.getParameterTypes().equals(token.typeToken.getParameterTypes());
                        }

                        public String toString() {
                            return this.typeToken.toString();
                        }
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Merger.class */
            public interface Merger {
                MethodDescription merge(MethodDescription methodDescription, MethodDescription methodDescription2);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Merger$Directional.class */
                public enum Directional implements Merger {
                    LEFT(true),
                    RIGHT(false);

                    private final boolean left;

                    Directional(boolean z) {
                        this.left = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Merger
                    public final MethodDescription merge(MethodDescription methodDescription, MethodDescription methodDescription2) {
                        return this.left ? methodDescription : methodDescription2;
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key.class */
            public static abstract class Key<S> {
                protected final String internalName;
                protected final int parameterCount;

                protected abstract Set<S> getIdentifiers();

                protected Key(String str, int i) {
                    this.internalName = str;
                    this.parameterCount = i;
                }

                public int hashCode() {
                    return this.internalName.hashCode() + (31 * this.parameterCount);
                }

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Key)) {
                        return false;
                    }
                    Key key = (Key) obj;
                    return this.internalName.equals(key.internalName) && this.parameterCount == key.parameterCount && !Collections.disjoint(getIdentifiers(), key.getIdentifiers());
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Harmonized.class */
                public static class Harmonized<V> extends Key<V> {
                    private final Map<V, Set<MethodDescription.TypeToken>> identifiers;

                    protected Harmonized(String str, int i, Map<V, Set<MethodDescription.TypeToken>> map) {
                        super(str, i);
                        this.identifiers = map;
                    }

                    protected static <Q> Harmonized<Q> of(MethodDescription methodDescription, Harmonizer<Q> harmonizer) {
                        return new Harmonized<>(methodDescription.getInternalName(), methodDescription.getParameters().size(), Collections.singletonMap(harmonizer.harmonize(methodDescription.asTypeToken()), Collections.emptySet()));
                    }

                    protected Detached detach(MethodDescription.TypeToken typeToken) {
                        HashSet hashSet = new HashSet();
                        Iterator<Set<MethodDescription.TypeToken>> it = this.identifiers.values().iterator();
                        while (it.hasNext()) {
                            hashSet.addAll(it.next());
                        }
                        hashSet.add(typeToken);
                        return new Detached(this.internalName, this.parameterCount, hashSet);
                    }

                    protected Harmonized<V> combineWith(Harmonized<V> harmonized) {
                        HashMap hashMap = new HashMap(this.identifiers);
                        for (Map.Entry<V, Set<MethodDescription.TypeToken>> entry : harmonized.identifiers.entrySet()) {
                            Set set = (Set) hashMap.get(entry.getKey());
                            if (set != null) {
                                HashSet hashSet = new HashSet(set);
                                hashSet.addAll(entry.getValue());
                                hashMap.put(entry.getKey(), hashSet);
                            } else {
                                hashMap.put(entry.getKey(), entry.getValue());
                            }
                        }
                        return new Harmonized<>(this.internalName, this.parameterCount, hashMap);
                    }

                    protected Harmonized<V> extend(MethodDescription.InDefinedShape inDefinedShape, Harmonizer<V> harmonizer) {
                        HashMap hashMap = new HashMap(this.identifiers);
                        MethodDescription.TypeToken asTypeToken = inDefinedShape.asTypeToken();
                        V harmonize = harmonizer.harmonize(asTypeToken);
                        Set set = (Set) hashMap.get(harmonize);
                        if (set != null) {
                            HashSet hashSet = new HashSet(set);
                            hashSet.add(asTypeToken);
                            hashMap.put(harmonize, hashSet);
                        } else {
                            hashMap.put(harmonize, Collections.singleton(asTypeToken));
                        }
                        return new Harmonized<>(this.internalName, this.parameterCount, hashMap);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key
                    protected Set<V> getIdentifiers() {
                        return this.identifiers.keySet();
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Detached.class */
                public static class Detached extends Key<MethodDescription.TypeToken> {
                    private final Set<MethodDescription.TypeToken> identifiers;

                    protected Detached(String str, int i, Set<MethodDescription.TypeToken> set) {
                        super(str, i);
                        this.identifiers = set;
                    }

                    protected static Detached of(MethodDescription.SignatureToken signatureToken) {
                        return new Detached(signatureToken.getName(), signatureToken.getParameterTypes().size(), Collections.singleton(signatureToken.asTypeToken()));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key
                    protected Set<MethodDescription.TypeToken> getIdentifiers() {
                        return this.identifiers;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store.class */
                public static class Store<V> {
                    private final LinkedHashMap<Harmonized<V>, Entry<V>> entries;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.entries.equals(((Store) obj).entries);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.entries.hashCode();
                    }

                    protected Store() {
                        this(new LinkedHashMap());
                    }

                    private Store(LinkedHashMap<Harmonized<V>, Entry<V>> linkedHashMap) {
                        this.entries = linkedHashMap;
                    }

                    private static <W> Entry<W> combine(Entry<W> entry, Entry<W> entry2) {
                        Set<MethodDescription> candidates = entry.getCandidates();
                        Set<MethodDescription> candidates2 = entry2.getCandidates();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        linkedHashSet.addAll(candidates);
                        linkedHashSet.addAll(candidates2);
                        for (MethodDescription methodDescription : candidates) {
                            TypeDescription asErasure = methodDescription.getDeclaringType().asErasure();
                            Iterator<MethodDescription> it = candidates2.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    MethodDescription next = it.next();
                                    TypeDescription asErasure2 = next.getDeclaringType().asErasure();
                                    if (asErasure.equals(asErasure2)) {
                                        break;
                                    }
                                    if (asErasure.isAssignableTo(asErasure2)) {
                                        linkedHashSet.remove(next);
                                        break;
                                    }
                                    if (asErasure.isAssignableFrom(asErasure2)) {
                                        linkedHashSet.remove(methodDescription);
                                        break;
                                    }
                                }
                            }
                        }
                        Harmonized<W> combineWith = entry.getKey().combineWith(entry2.getKey());
                        Visibility expandTo = entry.getVisibility().expandTo(entry2.getVisibility());
                        if (linkedHashSet.size() != 1) {
                            return new Entry.Ambiguous(combineWith, linkedHashSet, expandTo);
                        }
                        return new Entry.Resolved(combineWith, (MethodDescription) linkedHashSet.iterator().next(), expandTo, false);
                    }

                    protected Store<V> registerTopLevel(List<? extends MethodDescription> list, Harmonizer<V> harmonizer) {
                        if (list.isEmpty()) {
                            return this;
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        for (MethodDescription methodDescription : list) {
                            Harmonized of = Harmonized.of(methodDescription, harmonizer);
                            Entry entry = (Entry) linkedHashMap.remove(of);
                            Entry extendBy = (entry == null ? new Entry.Initial(of) : entry).extendBy(methodDescription, harmonizer);
                            linkedHashMap.put(extendBy.getKey(), extendBy);
                        }
                        return new Store<>(linkedHashMap);
                    }

                    protected Store<V> combineWith(Store<V> store) {
                        if (this.entries.isEmpty()) {
                            return store;
                        }
                        if (store.entries.isEmpty()) {
                            return this;
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        for (Entry<V> entry : store.entries.values()) {
                            Entry entry2 = (Entry) linkedHashMap.remove(entry.getKey());
                            Entry<V> combine = entry2 == null ? entry : combine(entry2, entry);
                            linkedHashMap.put(combine.getKey(), combine);
                        }
                        return new Store<>(linkedHashMap);
                    }

                    protected Store<V> inject(Store<V> store) {
                        if (this.entries.isEmpty()) {
                            return store;
                        }
                        if (store.entries.isEmpty()) {
                            return this;
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap(this.entries);
                        for (Entry<V> entry : store.entries.values()) {
                            Entry entry2 = (Entry) linkedHashMap.remove(entry.getKey());
                            Entry<V> inject = entry2 == null ? entry : entry2.inject(entry);
                            linkedHashMap.put(inject.getKey(), inject);
                        }
                        return new Store<>(linkedHashMap);
                    }

                    protected MethodGraph asGraph(Merger merger) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        for (Entry<V> entry : this.entries.values()) {
                            Node asNode = entry.asNode(merger);
                            linkedHashMap.put(entry.getKey().detach(asNode.getRepresentative().asTypeToken()), asNode);
                        }
                        return new Graph(linkedHashMap);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry.class */
                    public interface Entry<W> {
                        Harmonized<W> getKey();

                        Set<MethodDescription> getCandidates();

                        Visibility getVisibility();

                        Entry<W> extendBy(MethodDescription methodDescription, Harmonizer<W> harmonizer);

                        Entry<W> inject(Entry<W> entry);

                        Node asNode(Merger merger);

                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry$Initial.class */
                        public static class Initial<U> implements Entry<U> {
                            private final Harmonized<U> key;

                            protected Initial(Harmonized<U> harmonized) {
                                this.key = harmonized;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                throw new IllegalStateException("Cannot extract key from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                throw new IllegalStateException("Cannot extract method from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                throw new IllegalStateException("Cannot extract visibility from initial entry:" + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                return new Resolved(this.key.extend(methodDescription.asDefined(), harmonizer), methodDescription, methodDescription.getVisibility(), false);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Entry<U> entry) {
                                throw new IllegalStateException("Cannot inject into initial entry without a registered method: " + this);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                throw new IllegalStateException("Cannot transform initial entry without a registered method: " + this);
                            }

                            public int hashCode() {
                                return this.key.hashCode();
                            }

                            public boolean equals(@MaybeNull Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                if (obj == null || getClass() != obj.getClass()) {
                                    return false;
                                }
                                return this.key.equals(((Initial) obj).key);
                            }
                        }

                        @HashCodeAndEqualsPlugin.Enhance
                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry$Resolved.class */
                        public static class Resolved<U> implements Entry<U> {
                            private static final int MADE_VISIBLE = 5;
                            private static final boolean NOT_MADE_VISIBLE = false;
                            private final Harmonized<U> key;
                            private final MethodDescription methodDescription;
                            private final Visibility visibility;
                            private final boolean madeVisible;

                            public boolean equals(@MaybeNull Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                return obj != null && getClass() == obj.getClass() && this.madeVisible == ((Resolved) obj).madeVisible && this.visibility.equals(((Resolved) obj).visibility) && this.key.equals(((Resolved) obj).key) && this.methodDescription.equals(((Resolved) obj).methodDescription);
                            }

                            public int hashCode() {
                                return (((((((getClass().hashCode() * 31) + this.key.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.visibility.hashCode()) * 31) + (this.madeVisible ? 1 : 0);
                            }

                            protected Resolved(Harmonized<U> harmonized, MethodDescription methodDescription, Visibility visibility) {
                                this(harmonized, methodDescription, visibility, false);
                            }

                            protected Resolved(Harmonized<U> harmonized, MethodDescription methodDescription, Visibility visibility, boolean z) {
                                this.key = harmonized;
                                this.methodDescription = methodDescription;
                                this.visibility = visibility;
                                this.madeVisible = z;
                            }

                            private static <V> Entry<V> of(Harmonized<V> harmonized, MethodDescription methodDescription, MethodDescription methodDescription2, Visibility visibility) {
                                Visibility expandTo = visibility.expandTo(methodDescription2.getVisibility()).expandTo(methodDescription.getVisibility());
                                if (methodDescription.isBridge()) {
                                    return new Resolved(harmonized, methodDescription2, expandTo, (methodDescription2.getDeclaringType().getModifiers() & 5) == 0);
                                }
                                return new Resolved(harmonized, methodDescription, expandTo, false);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                return this.key;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                return Collections.singleton(this.methodDescription);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                return this.visibility;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                Harmonized<U> extend = this.key.extend(methodDescription.asDefined(), harmonizer);
                                Visibility expandTo = this.visibility.expandTo(methodDescription.getVisibility());
                                if (methodDescription.getDeclaringType().equals(this.methodDescription.getDeclaringType())) {
                                    return Ambiguous.of(extend, methodDescription, this.methodDescription, expandTo);
                                }
                                return of(extend, methodDescription, this.methodDescription, expandTo);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Entry<U> entry) {
                                if (this.methodDescription.getDeclaringType().isInterface()) {
                                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                                    linkedHashSet.add(this.methodDescription);
                                    TypeDescription asErasure = this.methodDescription.getDeclaringType().asErasure();
                                    for (MethodDescription methodDescription : entry.getCandidates()) {
                                        if (methodDescription.getDeclaringType().asErasure().isAssignableTo(asErasure)) {
                                            linkedHashSet.remove(this.methodDescription);
                                            linkedHashSet.add(methodDescription);
                                        } else if (!methodDescription.getDeclaringType().asErasure().isAssignableFrom(asErasure)) {
                                            linkedHashSet.add(methodDescription);
                                        }
                                    }
                                    return linkedHashSet.size() == 1 ? new Resolved(this.key.combineWith(entry.getKey()), (MethodDescription) linkedHashSet.iterator().next(), this.visibility.expandTo(entry.getVisibility()), this.madeVisible) : new Ambiguous(this.key.combineWith(entry.getKey()), linkedHashSet, this.visibility.expandTo(entry.getVisibility()));
                                }
                                return new Resolved(this.key.combineWith(entry.getKey()), this.methodDescription, this.visibility.expandTo(entry.getVisibility()), this.madeVisible);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                return new Node(this.key.detach(this.methodDescription.asTypeToken()), this.methodDescription, this.visibility, this.madeVisible);
                            }

                            @HashCodeAndEqualsPlugin.Enhance
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry$Resolved$Node.class */
                            protected static class Node implements Node {
                                private final Detached key;
                                private final MethodDescription methodDescription;
                                private final Visibility visibility;
                                private final boolean visible;

                                public boolean equals(@MaybeNull Object obj) {
                                    if (this == obj) {
                                        return true;
                                    }
                                    return obj != null && getClass() == obj.getClass() && this.visible == ((Node) obj).visible && this.visibility.equals(((Node) obj).visibility) && this.key.equals(((Node) obj).key) && this.methodDescription.equals(((Node) obj).methodDescription);
                                }

                                public int hashCode() {
                                    return (((((((getClass().hashCode() * 31) + this.key.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.visibility.hashCode()) * 31) + (this.visible ? 1 : 0);
                                }

                                protected Node(Detached detached, MethodDescription methodDescription, Visibility visibility, boolean z) {
                                    this.key = detached;
                                    this.methodDescription = methodDescription;
                                    this.visibility = visibility;
                                    this.visible = z;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Node.Sort getSort() {
                                    return this.visible ? Node.Sort.VISIBLE : Node.Sort.RESOLVED;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public MethodDescription getRepresentative() {
                                    return this.methodDescription;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Set<MethodDescription.TypeToken> getMethodTypes() {
                                    return this.key.getIdentifiers();
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Visibility getVisibility() {
                                    return this.visibility;
                                }
                            }
                        }

                        @HashCodeAndEqualsPlugin.Enhance
                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry$Ambiguous.class */
                        public static class Ambiguous<U> implements Entry<U> {
                            private final Harmonized<U> key;
                            private final LinkedHashSet<MethodDescription> methodDescriptions;
                            private final Visibility visibility;

                            public boolean equals(@MaybeNull Object obj) {
                                if (this == obj) {
                                    return true;
                                }
                                return obj != null && getClass() == obj.getClass() && this.visibility.equals(((Ambiguous) obj).visibility) && this.key.equals(((Ambiguous) obj).key) && this.methodDescriptions.equals(((Ambiguous) obj).methodDescriptions);
                            }

                            public int hashCode() {
                                return (((((getClass().hashCode() * 31) + this.key.hashCode()) * 31) + this.methodDescriptions.hashCode()) * 31) + this.visibility.hashCode();
                            }

                            protected Ambiguous(Harmonized<U> harmonized, LinkedHashSet<MethodDescription> linkedHashSet, Visibility visibility) {
                                this.key = harmonized;
                                this.methodDescriptions = linkedHashSet;
                                this.visibility = visibility;
                            }

                            protected static <Q> Entry<Q> of(Harmonized<Q> harmonized, MethodDescription methodDescription, MethodDescription methodDescription2, Visibility visibility) {
                                Visibility expandTo = visibility.expandTo(methodDescription.getVisibility()).expandTo(methodDescription2.getVisibility());
                                if (methodDescription.isBridge() ^ methodDescription2.isBridge()) {
                                    return new Resolved(harmonized, methodDescription.isBridge() ? methodDescription2 : methodDescription, expandTo, false);
                                }
                                return new Ambiguous(harmonized, new LinkedHashSet(Arrays.asList(methodDescription, methodDescription2)), expandTo);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Harmonized<U> getKey() {
                                return this.key;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Set<MethodDescription> getCandidates() {
                                return this.methodDescriptions;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Visibility getVisibility() {
                                return this.visibility;
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> extendBy(MethodDescription methodDescription, Harmonizer<U> harmonizer) {
                                Harmonized<U> extend = this.key.extend(methodDescription.asDefined(), harmonizer);
                                LinkedHashSet linkedHashSet = new LinkedHashSet();
                                TypeDescription asErasure = methodDescription.getDeclaringType().asErasure();
                                boolean isBridge = methodDescription.isBridge();
                                Visibility visibility = this.visibility;
                                Iterator<MethodDescription> it = this.methodDescriptions.iterator();
                                while (it.hasNext()) {
                                    MethodDescription next = it.next();
                                    if (next.getDeclaringType().asErasure().equals(asErasure)) {
                                        if (next.isBridge() ^ isBridge) {
                                            linkedHashSet.add(isBridge ? next : methodDescription);
                                        } else {
                                            linkedHashSet.add(methodDescription);
                                            linkedHashSet.add(next);
                                        }
                                    }
                                    visibility = visibility.expandTo(next.getVisibility());
                                }
                                if (linkedHashSet.isEmpty()) {
                                    return new Resolved(extend, methodDescription, visibility, isBridge);
                                }
                                if (linkedHashSet.size() == 1) {
                                    return new Resolved(extend, (MethodDescription) linkedHashSet.iterator().next(), visibility, false);
                                }
                                return new Ambiguous(extend, linkedHashSet, visibility);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Entry<U> inject(Entry<U> entry) {
                                LinkedHashSet linkedHashSet = new LinkedHashSet();
                                Iterator<MethodDescription> it = this.methodDescriptions.iterator();
                                while (it.hasNext()) {
                                    MethodDescription next = it.next();
                                    TypeDescription asErasure = next.getDeclaringType().asErasure();
                                    Iterator<MethodDescription> it2 = entry.getCandidates().iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            TypeDescription asErasure2 = it2.next().getDeclaringType().asErasure();
                                            if (asErasure2.equals(asErasure) || !asErasure2.isAssignableTo(asErasure)) {
                                            }
                                        } else {
                                            linkedHashSet.add(next);
                                            break;
                                        }
                                    }
                                }
                                for (MethodDescription methodDescription : entry.getCandidates()) {
                                    TypeDescription asErasure3 = methodDescription.getDeclaringType().asErasure();
                                    Iterator<MethodDescription> it3 = this.methodDescriptions.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            linkedHashSet.add(methodDescription);
                                            break;
                                        }
                                        if (!it3.next().getDeclaringType().asErasure().isAssignableTo(asErasure3)) {
                                        }
                                    }
                                }
                                if (linkedHashSet.size() == 1) {
                                    return new Resolved(this.key.combineWith(entry.getKey()), (MethodDescription) linkedHashSet.iterator().next(), this.visibility.expandTo(entry.getVisibility()));
                                }
                                return new Ambiguous(this.key.combineWith(entry.getKey()), linkedHashSet, this.visibility.expandTo(entry.getVisibility()));
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Key.Store.Entry
                            public Node asNode(Merger merger) {
                                Iterator<MethodDescription> it = this.methodDescriptions.iterator();
                                MethodDescription next = it.next();
                                while (true) {
                                    MethodDescription methodDescription = next;
                                    if (it.hasNext()) {
                                        next = merger.merge(methodDescription, it.next());
                                    } else {
                                        return new Node(this.key.detach(methodDescription.asTypeToken()), methodDescription, this.visibility);
                                    }
                                }
                            }

                            @HashCodeAndEqualsPlugin.Enhance
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Entry$Ambiguous$Node.class */
                            protected static class Node implements Node {
                                private final Detached key;
                                private final MethodDescription methodDescription;
                                private final Visibility visibility;

                                public boolean equals(@MaybeNull Object obj) {
                                    if (this == obj) {
                                        return true;
                                    }
                                    return obj != null && getClass() == obj.getClass() && this.visibility.equals(((Node) obj).visibility) && this.key.equals(((Node) obj).key) && this.methodDescription.equals(((Node) obj).methodDescription);
                                }

                                public int hashCode() {
                                    return (((((getClass().hashCode() * 31) + this.key.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.visibility.hashCode();
                                }

                                protected Node(Detached detached, MethodDescription methodDescription, Visibility visibility) {
                                    this.key = detached;
                                    this.methodDescription = methodDescription;
                                    this.visibility = visibility;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Node.Sort getSort() {
                                    return Node.Sort.AMBIGUOUS;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public MethodDescription getRepresentative() {
                                    return this.methodDescription;
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Set<MethodDescription.TypeToken> getMethodTypes() {
                                    return this.key.getIdentifiers();
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.MethodGraph.Node
                                public Visibility getVisibility() {
                                    return this.visibility;
                                }
                            }
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @HashCodeAndEqualsPlugin.Enhance
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Compiler$Default$Key$Store$Graph.class */
                    public static class Graph implements MethodGraph {
                        private final LinkedHashMap<Key<MethodDescription.TypeToken>, Node> entries;

                        public boolean equals(@MaybeNull Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.entries.equals(((Graph) obj).entries);
                        }

                        public int hashCode() {
                            return (getClass().hashCode() * 31) + this.entries.hashCode();
                        }

                        protected Graph(LinkedHashMap<Key<MethodDescription.TypeToken>, Node> linkedHashMap) {
                            this.entries = linkedHashMap;
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
                        public Node locate(MethodDescription.SignatureToken signatureToken) {
                            Node node = this.entries.get(Detached.of(signatureToken));
                            return node == null ? Node.Unresolved.INSTANCE : node;
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
                        public NodeList listNodes() {
                            return new NodeList(new ArrayList(this.entries.values()));
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$NodeList.class */
    public static class NodeList extends FilterableList.AbstractBase<Node, NodeList> {
        private final List<? extends Node> nodes;

        public NodeList(List<? extends Node> list) {
            this.nodes = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public Node get(int i) {
            return this.nodes.get(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.nodes.size();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.matcher.FilterableList.AbstractBase
        public NodeList wrap(List<Node> list) {
            return new NodeList(list);
        }

        public MethodList<?> asMethodList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator<? extends Node> it = this.nodes.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getRepresentative());
            }
            return new MethodList.Explicit(arrayList);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/MethodGraph$Simple.class */
    public static class Simple implements MethodGraph {
        private final LinkedHashMap<MethodDescription.SignatureToken, Node> nodes;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.nodes.equals(((Simple) obj).nodes);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.nodes.hashCode();
        }

        public Simple(LinkedHashMap<MethodDescription.SignatureToken, Node> linkedHashMap) {
            this.nodes = linkedHashMap;
        }

        public static MethodGraph of(List<? extends MethodDescription> list) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (MethodDescription methodDescription : list) {
                linkedHashMap.put(methodDescription.asSignatureToken(), new Node.Simple(methodDescription));
            }
            return new Simple(linkedHashMap);
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public Node locate(MethodDescription.SignatureToken signatureToken) {
            Node node = this.nodes.get(signatureToken);
            return node == null ? Node.Unresolved.INSTANCE : node;
        }

        @Override // net.bytebuddy.dynamic.scaffold.MethodGraph
        public NodeList listNodes() {
            return new NodeList(new ArrayList(this.nodes.values()));
        }
    }
}

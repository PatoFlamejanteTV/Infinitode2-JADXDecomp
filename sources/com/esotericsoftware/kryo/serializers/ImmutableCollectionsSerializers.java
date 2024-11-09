package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ImmutableCollectionsSerializers.class */
public final class ImmutableCollectionsSerializers {
    public static void addDefaultSerializers(Kryo kryo) {
        if (Util.isClassAvailable("java.util.ImmutableCollections")) {
            JdkImmutableListSerializer.addDefaultSerializers(kryo);
            JdkImmutableMapSerializer.addDefaultSerializers(kryo);
            JdkImmutableSetSerializer.addDefaultSerializers(kryo);
        }
    }

    public static void registerSerializers(Kryo kryo) {
        JdkImmutableListSerializer.registerSerializers(kryo);
        JdkImmutableMapSerializer.registerSerializers(kryo);
        JdkImmutableSetSerializer.registerSerializers(kryo);
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ImmutableCollectionsSerializers$JdkImmutableListSerializer.class */
    public static final class JdkImmutableListSerializer extends CollectionSerializer<List<Object>> {
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final /* bridge */ /* synthetic */ Collection read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends List<Object>>) cls);
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends List<Object>>) cls);
        }

        private JdkImmutableListSerializer() {
            setElementsCanBeNull(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create */
        public final List<Object> create2(Kryo kryo, Input input, Class<? extends List<Object>> cls, int i) {
            return new ArrayList(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public final List<Object> createCopy(Kryo kryo, List<Object> list) {
            return new ArrayList(list.size());
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final List<Object> read2(Kryo kryo, Input input, Class<? extends List<Object>> cls) {
            List list = (List) super.read2(kryo, input, (Class) cls);
            if (list == null) {
                return null;
            }
            return List.of(list.toArray());
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        public final List<Object> copy(Kryo kryo, List<Object> list) {
            return List.copyOf((List) super.copy(kryo, (Kryo) list));
        }

        static void addDefaultSerializers(Kryo kryo) {
            JdkImmutableListSerializer jdkImmutableListSerializer = new JdkImmutableListSerializer();
            kryo.addDefaultSerializer(List.of().getClass(), jdkImmutableListSerializer);
            kryo.addDefaultSerializer(List.of(1).getClass(), jdkImmutableListSerializer);
            kryo.addDefaultSerializer(List.of(1, 2, 3, 4).getClass(), jdkImmutableListSerializer);
            kryo.addDefaultSerializer(List.of(1, 2, 3, 4).subList(0, 2).getClass(), jdkImmutableListSerializer);
        }

        static void registerSerializers(Kryo kryo) {
            JdkImmutableListSerializer jdkImmutableListSerializer = new JdkImmutableListSerializer();
            kryo.register(List.of().getClass(), jdkImmutableListSerializer);
            kryo.register(List.of(1).getClass(), jdkImmutableListSerializer);
            kryo.register(List.of(1, 2, 3, 4).getClass(), jdkImmutableListSerializer);
            kryo.register(List.of(1, 2, 3, 4).subList(0, 2).getClass(), jdkImmutableListSerializer);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ImmutableCollectionsSerializers$JdkImmutableMapSerializer.class */
    public static final class JdkImmutableMapSerializer extends MapSerializer<Map<Object, Object>> {
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Map<Object, Object>>) cls);
        }

        private JdkImmutableMapSerializer() {
            setKeysCanBeNull(false);
            setValuesCanBeNull(false);
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        protected final Map<Object, Object> create(Kryo kryo, Input input, Class<? extends Map<Object, Object>> cls, int i) {
            return new HashMap();
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        protected final Map<Object, Object> createCopy(Kryo kryo, Map<Object, Object> map) {
            return new HashMap();
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final Map<Object, Object> read2(Kryo kryo, Input input, Class<? extends Map<Object, Object>> cls) {
            Map read2 = super.read2(kryo, input, (Class<? extends Map>) cls);
            if (read2 == null) {
                return null;
            }
            return Map.copyOf(read2);
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer, com.esotericsoftware.kryo.Serializer
        public final Map<Object, Object> copy(Kryo kryo, Map<Object, Object> map) {
            return Map.copyOf(super.copy(kryo, (Kryo) map));
        }

        static void addDefaultSerializers(Kryo kryo) {
            JdkImmutableMapSerializer jdkImmutableMapSerializer = new JdkImmutableMapSerializer();
            kryo.addDefaultSerializer(Map.of().getClass(), jdkImmutableMapSerializer);
            kryo.addDefaultSerializer(Map.of(1, 2).getClass(), jdkImmutableMapSerializer);
            kryo.addDefaultSerializer(Map.of(1, 2, 3, 4).getClass(), jdkImmutableMapSerializer);
        }

        static void registerSerializers(Kryo kryo) {
            JdkImmutableMapSerializer jdkImmutableMapSerializer = new JdkImmutableMapSerializer();
            kryo.register(Map.of().getClass(), jdkImmutableMapSerializer);
            kryo.register(Map.of(1, 2).getClass(), jdkImmutableMapSerializer);
            kryo.register(Map.of(1, 2, 3, 4).getClass(), jdkImmutableMapSerializer);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ImmutableCollectionsSerializers$JdkImmutableSetSerializer.class */
    public static final class JdkImmutableSetSerializer extends CollectionSerializer<Set<Object>> {
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final /* bridge */ /* synthetic */ Collection read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Set<Object>>) cls);
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Set<Object>>) cls);
        }

        private JdkImmutableSetSerializer() {
            setElementsCanBeNull(false);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create */
        public final Set<Object> create2(Kryo kryo, Input input, Class<? extends Set<Object>> cls, int i) {
            return new HashSet();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public final Set<Object> createCopy(Kryo kryo, Set<Object> set) {
            return new HashSet();
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public final Set<Object> read2(Kryo kryo, Input input, Class<? extends Set<Object>> cls) {
            Set set = (Set) super.read2(kryo, input, (Class) cls);
            if (set == null) {
                return null;
            }
            return Set.of(set.toArray());
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        public final Set<Object> copy(Kryo kryo, Set<Object> set) {
            return Set.copyOf((Set) super.copy(kryo, (Kryo) set));
        }

        static void addDefaultSerializers(Kryo kryo) {
            JdkImmutableSetSerializer jdkImmutableSetSerializer = new JdkImmutableSetSerializer();
            kryo.addDefaultSerializer(Set.of().getClass(), jdkImmutableSetSerializer);
            kryo.addDefaultSerializer(Set.of(1).getClass(), jdkImmutableSetSerializer);
            kryo.addDefaultSerializer(Set.of(1, 2, 3, 4).getClass(), jdkImmutableSetSerializer);
        }

        static void registerSerializers(Kryo kryo) {
            JdkImmutableSetSerializer jdkImmutableSetSerializer = new JdkImmutableSetSerializer();
            kryo.register(Set.of().getClass(), jdkImmutableSetSerializer);
            kryo.register(Set.of(1).getClass(), jdkImmutableSetSerializer);
            kryo.register(Set.of(1, 2, 3, 4).getClass(), jdkImmutableSetSerializer);
        }
    }
}

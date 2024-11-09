package com.prineside.tdi2.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.MapReferenceResolver;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.actions.BuildMinerAction;
import com.prineside.tdi2.actions.BuildModifierAction;
import com.prineside.tdi2.actions.BuildTowerAction;
import com.prineside.tdi2.actions.CallWaveAction;
import com.prineside.tdi2.actions.ChangeTowerAimStrategyAction;
import com.prineside.tdi2.actions.CustomTowerButtonAction;
import com.prineside.tdi2.actions.GlobalUpgradeMinerAction;
import com.prineside.tdi2.actions.GlobalUpgradeTowerAction;
import com.prineside.tdi2.actions.RewardingAdAction;
import com.prineside.tdi2.actions.SelectGlobalTowerAbilityAction;
import com.prineside.tdi2.actions.SelectTowerAbilityAction;
import com.prineside.tdi2.actions.SellMinerAction;
import com.prineside.tdi2.actions.SellModifierAction;
import com.prineside.tdi2.actions.SellTowerAction;
import com.prineside.tdi2.actions.UpgradeMinerAction;
import com.prineside.tdi2.actions.UpgradeTowerAction;
import com.prineside.tdi2.actions.UseAbilityAction;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.serializers.ArraySerializer;
import com.prineside.tdi2.serializers.ColorSerializer;
import com.prineside.tdi2.serializers.FloatArraySerializer;
import com.prineside.tdi2.serializers.GameStateSerializer;
import com.prineside.tdi2.serializers.IntArraySerializer;
import com.prineside.tdi2.serializers.IntFloatMapSerializer;
import com.prineside.tdi2.serializers.IntIntMapSerializer;
import com.prineside.tdi2.serializers.IntMapSerializer;
import com.prineside.tdi2.serializers.IntSetSerializer;
import com.prineside.tdi2.serializers.JsonValueSerializer;
import com.prineside.tdi2.serializers.ObjectFloatMapSerializer;
import com.prineside.tdi2.serializers.ObjectIntMapSerializer;
import com.prineside.tdi2.serializers.ObjectMapSerializer;
import com.prineside.tdi2.serializers.ObjectSetSerializer;
import com.prineside.tdi2.serializers.ProxySerializer;
import com.prineside.tdi2.serializers.RandomXS128Serializer;
import com.prineside.tdi2.serializers.RectangleSerializer;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.serializers.Vector2Serializer;
import com.prineside.tdi2.serializers.Vector3Serializer;
import com.prineside.tdi2.serializers.WeakReferenceSerializer;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager.class */
public class NetworkManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2393a = TLog.forClass(NetworkManager.class);

    /* renamed from: b, reason: collision with root package name */
    private KryoForState f2394b;
    private final AtomicBoolean c = new AtomicBoolean(false);
    public Array<Registration> registeredClasses = new Array<>(Registration.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<NetworkManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public NetworkManager read() {
            return Game.i.networkManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        this.f2394b = new KryoForState();
        this.f2394b.setReferenceResolver(new MapReferenceResolver(this) { // from class: com.prineside.tdi2.managers.NetworkManager.1
            @Override // com.esotericsoftware.kryo.util.MapReferenceResolver, com.esotericsoftware.kryo.ReferenceResolver
            public Object getReadObject(Class cls, int i) {
                Object readObject = super.getReadObject(cls, i);
                if (readObject == null) {
                    NetworkManager.f2393a.e("the object by ref " + i + " of type " + cls + " is null", new Throwable());
                }
                return readObject;
            }
        });
        Threads.i().runAsync(() -> {
            try {
                a(this.f2394b);
            } catch (Exception e) {
                throw new IllegalStateException("fullKryo failed to init", e);
            }
        });
    }

    public byte[] objectToBytes(Object obj) {
        KryoForState fullKryo = getFullKryo();
        Output output = new Output(8192, -1);
        fullKryo.writeClassAndObject(output, obj);
        return output.toBytes();
    }

    public Object bytesToObject(byte[] bArr) {
        return getFullKryo().readClassAndObject(new Input(bArr));
    }

    public void prepareMultiplayerKryo(Kryo kryo) {
        kryo.setMaxDepth(4);
        kryo.setRegistrationRequired(true);
        kryo.register(NetReqConnect.class);
        kryo.register(NetRespConnect.class);
        kryo.register(NetBundle.class);
        kryo.register(Application.ApplicationType.class);
    }

    public KryoForState getFullKryo() {
        if (this.c.get()) {
            return this.f2394b;
        }
        f2393a.i("waiting for fullKryo to load...", new Object[0]);
        long timestampMillis = Game.getTimestampMillis();
        while (!this.c.get()) {
            try {
                Thread.sleep(2L);
                if (Game.getTimestampMillis() - timestampMillis > 7000) {
                    throw new RuntimeException("Full kryo init takes too long");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        f2393a.d("blocked main thread for " + (Game.getTimestampMillis() - timestampMillis) + "ms to wait for a full kryo", new Object[0]);
        return this.f2394b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(KryoForState kryoForState) {
        f2393a.i("prepareFullKryo called...", new Object[0]);
        kryoForState.setDefaultSerializer(GameStateSerializer.class);
        kryoForState.setMaxDepth(2048);
        kryoForState.setReferences(true);
        kryoForState.setRegistrationRequired(true);
        kryoForState.register(Class.class);
        kryoForState.register(Class[].class);
        kryoForState.register(float[].class);
        kryoForState.register(float[][].class);
        kryoForState.register(double[].class);
        kryoForState.register(double[][].class);
        kryoForState.register(int[].class);
        kryoForState.register(int[][].class);
        kryoForState.register(boolean[].class);
        kryoForState.register(boolean[][].class);
        kryoForState.register(byte[].class);
        kryoForState.register(byte[][].class);
        kryoForState.register(long[].class);
        kryoForState.register(long[][].class);
        kryoForState.register(short[].class);
        kryoForState.register(short[][].class);
        kryoForState.register(Object.class, GameStateSerializer.CLASS_ONLY_SERIALIZER);
        kryoForState.register(Object[].class);
        kryoForState.register(Object[][].class);
        kryoForState.register(String[].class);
        kryoForState.register(String[][].class);
        kryoForState.register(Array.class, new ArraySerializer());
        kryoForState.register(IntArray.class, new IntArraySerializer());
        kryoForState.register(FloatArray.class, new FloatArraySerializer());
        kryoForState.register(Vector2.class, new Vector2Serializer());
        kryoForState.register(Vector2[].class);
        kryoForState.register(Vector3.class, new Vector3Serializer());
        kryoForState.register(Vector3[].class);
        kryoForState.register(Rectangle.class, new RectangleSerializer());
        kryoForState.register(IntIntMap.class, new IntIntMapSerializer());
        kryoForState.register(IntFloatMap.class, new IntFloatMapSerializer());
        kryoForState.register(IntSet.class, new IntSetSerializer());
        kryoForState.register(ObjectSet.class, new ObjectSetSerializer());
        kryoForState.register(ObjectMap.class, new ObjectMapSerializer());
        kryoForState.register(ObjectIntMap.class, new ObjectIntMapSerializer());
        kryoForState.register(ObjectFloatMap.class, new ObjectFloatMapSerializer());
        kryoForState.register(IntMap.class, new IntMapSerializer());
        kryoForState.register(DelayedRemovalArray.class, new ArraySerializer());
        kryoForState.register(RandomXS128.class, new RandomXS128Serializer());
        kryoForState.register(DelayedRemovalArray[].class, new DefaultArraySerializers.ObjectArraySerializer(kryoForState, DelayedRemovalArray[].class));
        kryoForState.register(Array[].class, new DefaultArraySerializers.ObjectArraySerializer(kryoForState, Array[].class));
        kryoForState.register(InvocationHandler.class, new ProxySerializer());
        kryoForState.register(WeakReference.class, new WeakReferenceSerializer());
        kryoForState.register(Color.class, new ColorSerializer());
        kryoForState.register(JsonValue.class, new JsonValueSerializer());
        Array array = new Array(Class.class);
        f2393a.i("reading kryo-registry.txt...", new Object[0]);
        for (String str : Gdx.files.internal("res/kryo-registry.txt").readString("UTF-8").split(SequenceUtils.EOL)) {
            if (str.length() > 0) {
                try {
                    array.add(Class.forName(str));
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException("Failed to load class from kryo-registry: " + str, e);
                }
            }
        }
        f2393a.i("found " + array.size + " classes in kryo-registry.txt", new Object[0]);
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            Class cls = (Class) it.next();
            REGS regs = (REGS) cls.getAnnotation(REGS.class);
            if (regs == null) {
                f2393a.e("REGS not found for " + cls, new Object[0]);
            } else {
                boolean isAbstract = Modifier.isAbstract(cls.getModifiers());
                boolean z = cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers());
                if (isAbstract && regs.arrayLevels() == 0 && !regs.classOnly()) {
                    f2393a.e("REGS should not be used on abstract class " + cls, new Object[0]);
                } else if (z) {
                    f2393a.e("REGS should not be used on non-static inner class " + cls, new Object[0]);
                } else {
                    boolean z2 = false;
                    Class<?>[] interfaces = cls.getInterfaces();
                    int length = interfaces.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        if (interfaces[i] != KryoSerializable.class) {
                            i++;
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                    boolean z3 = false;
                    try {
                        cls.getDeclaredConstructor(new Class[0]);
                        z3 = true;
                    } catch (Exception unused) {
                    }
                    boolean isEnum = cls.isEnum();
                    boolean z4 = false;
                    Class<?>[] interfaces2 = cls.getInterfaces();
                    int length2 = interfaces2.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        if (interfaces2[i2] != KryoSerializable.class) {
                            i2++;
                        } else {
                            z4 = true;
                            break;
                        }
                    }
                    Class superclass = cls.getSuperclass();
                    while (true) {
                        Class cls2 = superclass;
                        if (cls2 == null || cls2 == Object.class) {
                            break;
                        }
                        for (Class<?> cls3 : cls2.getInterfaces()) {
                            if (cls3 == KryoSerializable.class) {
                                z4 = true;
                                break;
                            }
                        }
                        superclass = cls2.getSuperclass();
                    }
                    if (!isAbstract || regs.classOnly()) {
                        Class<? extends com.esotericsoftware.kryo.Serializer> serializer = regs.serializer();
                        if (!isAbstract && z4) {
                            try {
                                if (regs.classOnly()) {
                                    f2393a.e(cls.getName() + " uses class only serializer while extending the serializable class with fields", new Object[0]);
                                }
                            } catch (Exception e2) {
                                f2393a.e("failed to register class: " + cls.getName() + " with serializer: " + serializer.getName(), e2);
                            }
                        }
                        if (regs.classOnly()) {
                            kryoForState.register(cls, GameStateSerializer.CLASS_ONLY_SERIALIZER);
                            if (z2 && !isAbstract) {
                                f2393a.e(" ^^^ " + cls.getName() + " implements KryoSerializable - incorrect REGS configuration?", new Object[0]);
                            }
                        } else if (serializer == GameStateSerializer.class) {
                            kryoForState.register(cls);
                            if (!z3 && !isEnum) {
                                f2393a.e(" ^^^ " + cls.getName() + " has no declared constructor", new Object[0]);
                            }
                        } else {
                            kryoForState.register(cls, serializer.newInstance());
                        }
                    }
                    int arrayLevels = regs.arrayLevels();
                    for (int i3 = 1; i3 <= arrayLevels; i3++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i4 = 0; i4 < i3; i4++) {
                            stringBuilder.append("[");
                        }
                        stringBuilder.append("L").append(cls.getName()).append(";");
                        try {
                            kryoForState.register(Class.forName(stringBuilder.toString()));
                        } catch (ClassNotFoundException e3) {
                            f2393a.e("failed to register class: " + ((Object) stringBuilder), e3);
                        }
                    }
                }
            }
        }
        for (int i5 = 0; i5 < this.registeredClasses.size; i5++) {
            try {
                Registration registration = this.registeredClasses.items[i5];
                if (registration.getSerializer() instanceof DefaultSerializers.KryoSerializableSerializer) {
                    try {
                        this.f2394b.newInstance(registration.getType());
                    } catch (Exception e4) {
                        f2393a.e("failed to create instance of " + registration.getType(), e4);
                    }
                }
            } catch (Exception e5) {
                f2393a.e("failed to print registered classes", e5);
            }
        }
        f2393a.i("kryo registry loaded", new Object[0]);
        this.c.set(true);
    }

    public static void prepareNetworkKryo(KryoForState kryoForState) {
        kryoForState.setMaxDepth(8);
        kryoForState.setRegistrationRequired(true);
        kryoForState.register(IntIntMap.class, new IntIntMapSerializer());
        kryoForState.register(Array.class, new ArraySerializer());
        kryoForState.register(AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        kryoForState.register(AbilityType.class);
        kryoForState.register(TowerType.class);
        kryoForState.register(Tower.AimStrategy.class);
        kryoForState.register(ModifierType.class);
        kryoForState.register(MinerType.class);
        kryoForState.register(StateSystem.ActionUpdatePair.class);
        kryoForState.register(BuildMinerAction.class);
        kryoForState.register(BuildModifierAction.class);
        kryoForState.register(BuildTowerAction.class);
        kryoForState.register(CallWaveAction.class);
        kryoForState.register(RewardingAdAction.class);
        kryoForState.register(ChangeTowerAimStrategyAction.class);
        kryoForState.register(CustomTowerButtonAction.class);
        kryoForState.register(GlobalUpgradeMinerAction.class);
        kryoForState.register(GlobalUpgradeTowerAction.class);
        kryoForState.register(SelectGlobalTowerAbilityAction.class);
        kryoForState.register(SelectTowerAbilityAction.class);
        kryoForState.register(SellMinerAction.class);
        kryoForState.register(SellModifierAction.class);
        kryoForState.register(SellTowerAction.class);
        kryoForState.register(UpgradeMinerAction.class);
        kryoForState.register(UpgradeTowerAction.class);
        kryoForState.register(UseAbilityAction.class);
        kryoForState.register(FrameworkMessage.RegisterUDP.class, new FieldSerializer(kryoForState, FrameworkMessage.RegisterUDP.class));
        kryoForState.register(FrameworkMessage.DiscoverHost.class, new FieldSerializer(kryoForState, FrameworkMessage.DiscoverHost.class));
        kryoForState.register(FrameworkMessage.KeepAlive.class, new FieldSerializer(kryoForState, FrameworkMessage.KeepAlive.class));
        kryoForState.register(FrameworkMessage.Ping.class, new FieldSerializer(kryoForState, FrameworkMessage.Ping.class));
        kryoForState.register(FrameworkMessage.RegisterTCP.class, new FieldSerializer(kryoForState, FrameworkMessage.RegisterTCP.class));
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$KryoForState.class */
    public class KryoForState extends Kryo {
        public KryoForState() {
        }

        @Override // com.esotericsoftware.kryo.Kryo
        public Registration register(Registration registration) {
            super.register(registration);
            NetworkManager.this.registeredClasses.add(registration);
            return registration;
        }

        @Override // com.esotericsoftware.kryo.Kryo
        public Registration register(Class cls, com.esotericsoftware.kryo.Serializer serializer) {
            Registration register = super.register(cls, serializer);
            NetworkManager.this.registeredClasses.add(register);
            return register;
        }

        public boolean hasRegistration(Class cls) {
            if (cls == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            Registration registration = getClassResolver().getRegistration(cls);
            Registration registration2 = registration;
            if (registration == null) {
                if (Proxy.isProxyClass(cls)) {
                    registration2 = getRegistration(InvocationHandler.class);
                } else if (!cls.isEnum() && Enum.class.isAssignableFrom(cls) && !Enum.class.equals(cls)) {
                    registration2 = getRegistration(cls.getEnclosingClass());
                } else if (EnumSet.class.isAssignableFrom(cls)) {
                    registration2 = getClassResolver().getRegistration(EnumSet.class);
                }
            }
            return registration2 != null;
        }

        @Override // com.esotericsoftware.kryo.Kryo
        public Registration getRegistration(Class cls) {
            if (cls == null) {
                throw new IllegalArgumentException("type cannot be null.");
            }
            Registration registration = getClassResolver().getRegistration(cls);
            Registration registration2 = registration;
            if (registration == null) {
                if (Proxy.isProxyClass(cls)) {
                    registration2 = getRegistration(InvocationHandler.class);
                } else if (!cls.isEnum() && Enum.class.isAssignableFrom(cls) && !Enum.class.equals(cls)) {
                    registration2 = getRegistration(cls.getEnclosingClass());
                } else if (EnumSet.class.isAssignableFrom(cls)) {
                    registration2 = getClassResolver().getRegistration(EnumSet.class);
                }
                if (registration2 == null) {
                    if (!isRegistrationRequired()) {
                        NetworkManager.f2393a.e("not registered: " + Util.className(cls), new Object[0]);
                        registration2 = getClassResolver().registerImplicit(cls);
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Class is not registered: ").append(Util.className(cls)).append(SequenceUtils.EOL);
                        stringBuilder.append("  Registry:\n");
                        for (int i = 0; i < NetworkManager.this.registeredClasses.size; i++) {
                            Registration registration3 = NetworkManager.this.registeredClasses.items[i];
                            stringBuilder.append("  - ").append(registration3.getId()).append(SequenceUtils.SPACE).append(registration3.getType().getName()).append(SequenceUtils.SPACE).append(registration3.getSerializer().getClass().getSimpleName()).append(SequenceUtils.EOL);
                        }
                        throw new IllegalArgumentException(stringBuilder.toString());
                    }
                }
            }
            return registration2;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$NetReqConnect.class */
    public static class NetReqConnect implements KryoSerializable {
        public String userId;
        public String locale;
        public Application.ApplicationType applicationType;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.userId);
            kryo.writeObject(output, this.locale);
            kryo.writeObject(output, this.applicationType);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.userId = (String) kryo.readObject(input, String.class);
            this.locale = (String) kryo.readObject(input, String.class);
            this.applicationType = (Application.ApplicationType) kryo.readObject(input, Application.ApplicationType.class);
        }

        public String toString() {
            return "NetReqConnect (" + this.userId + ", " + this.applicationType.name() + ", " + this.locale + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$NetRespConnect.class */
    public static class NetRespConnect implements KryoSerializable {
        public String text;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.text);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.text = (String) kryo.readObject(input, String.class);
        }

        public String toString() {
            return "NetRespConnect (" + this.text + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$NetRespBroadcast.class */
    public static class NetRespBroadcast implements KryoSerializable {
        public String text;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.text);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.text = (String) kryo.readObject(input, String.class);
        }

        public String toString() {
            return "NetRespBroadcast (" + this.text + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/NetworkManager$NetBundle.class */
    public static class NetBundle implements KryoSerializable {
        public int id;
        public String message;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeVarInt(this.id, true);
            kryo.writeObject(output, this.message);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.id = input.readVarInt(true);
            this.message = (String) kryo.readObject(input, String.class);
        }

        public String toString() {
            return "NetBundle (" + this.id + ", " + this.message + ")";
        }
    }
}

package com.prineside.tdi2.utils.syncchecker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Pool;
import com.prineside.luaj.LuaBoolean;
import com.prineside.luaj.LuaNil;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.lib.jse.JavaClass;
import com.prineside.luaj.lib.jse.JavaInstance;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.utils.EnumKeyArray;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.syncchecker.comparators.BooleanArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ByteArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.CharArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.CheatSafeIntComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.CheatSafeLongComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.DoubleArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.FloatArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.GdxArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.GdxFloatArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.GdxIntArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IdentityMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IdentityObjectIntMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IntArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IntFloatMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IntIntMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IntMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.IntSetComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.JsonValueComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.LongArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ObjectArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ObjectIntMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ObjectMapComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ObjectSetComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.RandomXS128Comparator;
import com.prineside.tdi2.utils.syncchecker.comparators.RectangleComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.ShortArrayComparator;
import com.prineside.tdi2.utils.syncchecker.comparators.Vector2Comparator;
import com.prineside.tdi2.utils.syncchecker.comparators.WeakReferenceComparator;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/SyncChecker.class */
public class SyncChecker {
    public static boolean VERBOSE_MODE = false;

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f3976b = new String[1024];

    /* renamed from: a, reason: collision with root package name */
    static final Pool<Set<Object>> f3977a = new Pool<Set<Object>>(1, 512) { // from class: com.prineside.tdi2.utils.syncchecker.SyncChecker.1
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ void reset(Set<Object> set) {
            a(set);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ Set<Object> newObject() {
            return a();
        }

        private static Set<Object> a() {
            return Collections.newSetFromMap(new IdentityHashMap());
        }

        private static void a(Set<Object> set) {
            set.clear();
        }
    };
    private static final ObjectMap<Class<?>, DeepClassComparator<?>> c = new ObjectMap<>();
    public static final ObjectSet<Class<?>> SYNC_SHAREABLE_CLASSES = new ObjectSet<>();
    private static final ObjectSet<Object> d = new ObjectSet<>();
    private static final ObjectSet<Class<?>> e;
    public static final Array<DeepClassComparator<?>> CLASS_COMPARATORS;

    static {
        SYNC_SHAREABLE_CLASSES.add(Integer.class);
        SYNC_SHAREABLE_CLASSES.add(Float.class);
        SYNC_SHAREABLE_CLASSES.add(Double.class);
        SYNC_SHAREABLE_CLASSES.add(Boolean.class);
        SYNC_SHAREABLE_CLASSES.add(Short.class);
        SYNC_SHAREABLE_CLASSES.add(Long.class);
        SYNC_SHAREABLE_CLASSES.add(Byte.class);
        SYNC_SHAREABLE_CLASSES.add(Character.class);
        SYNC_SHAREABLE_CLASSES.add(LuaString.class);
        SYNC_SHAREABLE_CLASSES.add(LuaBoolean.class);
        SYNC_SHAREABLE_CLASSES.add(LuaNil.class);
        SYNC_SHAREABLE_CLASSES.add(LuaNumber.class);
        SYNC_SHAREABLE_CLASSES.add(JavaClass.class);
        SYNC_SHAREABLE_CLASSES.add(JavaInstance.class);
        SYNC_SHAREABLE_CLASSES.add(Color.class);
        SYNC_SHAREABLE_CLASSES.add(Item.class);
        SYNC_SHAREABLE_CLASSES.add(Action.class);
        SYNC_SHAREABLE_CLASSES.add(GameValueManager.GameValuesSnapshot.class);
        SYNC_SHAREABLE_CLASSES.add(AbilitySelectionOverlay.SelectedAbilitiesConfiguration.class);
        SYNC_SHAREABLE_CLASSES.add(BasicLevel.class);
        SYNC_SHAREABLE_CLASSES.add(ProgressManager.InventoryStatistics.class);
        ObjectSet<Class<?>> objectSet = new ObjectSet<>();
        e = objectSet;
        objectSet.add(Character.class);
        e.add(Byte.class);
        e.add(Short.class);
        e.add(Long.class);
        e.add(Float.class);
        e.add(Integer.class);
        e.add(Double.class);
        e.add(Boolean.class);
        e.add(String.class);
        CLASS_COMPARATORS = new Array<>(new DeepClassComparator[]{new BooleanArrayComparator(), new ByteArrayComparator(), new CharArrayComparator(), new CheatSafeIntComparator(), new CheatSafeLongComparator(), new DoubleArrayComparator(), new FloatArrayComparator(), new GdxArrayComparator(), new GdxFloatArrayComparator(), new GdxIntArrayComparator(), new IdentityMapComparator(), new IdentityObjectIntMapComparator(), new IntArrayComparator(), new IntFloatMapComparator(), new IntIntMapComparator(), new IntMapComparator(), new IntSetComparator(), new LongArrayComparator(), new ObjectArrayComparator(), new ObjectIntMapComparator(), new ObjectMapComparator(), new ObjectSetComparator(), new RandomXS128Comparator(), new RectangleComparator(), new ShortArrayComparator(), new Vector2Comparator(), new WeakReferenceComparator(), new JsonValueComparator()});
    }

    public static void addSyncShareableObject(Object obj) {
        synchronized (d) {
            d.add(obj);
        }
    }

    public static boolean isSyncShareableObject(Object obj) {
        boolean contains;
        synchronized (d) {
            contains = d.contains(obj);
        }
        return contains;
    }

    public static void compareObjects(Object obj, Object obj2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (deepClassComparisonConfig.depth < 0) {
            if (deepClassComparisonConfig.debug) {
                deepClassComparisonConfig.sb.append("> too deep\n");
                return;
            }
            return;
        }
        deepClassComparisonConfig.depth--;
        if (obj == obj2) {
            if (obj != null && !d.contains(obj)) {
                Class<?> cls = obj.getClass();
                if (!cls.isEnum() && !cls.isPrimitive() && cls != String.class && cls != Class.class) {
                    Class<?> cls2 = cls;
                    boolean z = false;
                    while (true) {
                        if (cls2 == null || cls2 == Object.class) {
                            break;
                        }
                        if (SYNC_SHAREABLE_CLASSES.contains(cls2)) {
                            z = true;
                            break;
                        }
                        cls2 = cls2.getSuperclass();
                    }
                    if (!z) {
                        deepClassComparisonConfig.appendPrefix().append(": same object that can not be shared ").append(obj.getClass().getName()).append(SequenceUtils.SPACE).append(obj);
                        if (obj instanceof Object[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((Object[]) obj)).append(")");
                        } else if (obj instanceof float[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((float[]) obj)).append(")");
                        } else if (obj instanceof int[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((int[]) obj)).append(")");
                        } else if (obj instanceof double[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((double[]) obj)).append(")");
                        } else if (obj instanceof long[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((long[]) obj)).append(")");
                        } else if (obj instanceof boolean[]) {
                            deepClassComparisonConfig.sb.append(" (is array: ").append(Arrays.toString((boolean[]) obj)).append(")");
                        }
                        deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                    }
                }
            }
            deepClassComparisonConfig.depth++;
            return;
        }
        if (obj2 != null && deepClassComparisonConfig.comparesMap.containsKey(obj2) && !SYNC_SHAREABLE_CLASSES.contains(obj2.getClass())) {
            deepClassComparisonConfig.appendPrefix().append(": shared object ").append(obj2.getClass().getName()).append(SequenceUtils.SPACE).append(Integer.toHexString(obj.hashCode())).append("/").append(Integer.toHexString(obj2.hashCode())).append(SequenceUtils.SPACE).append(obj2).append(SequenceUtils.EOL);
            deepClassComparisonConfig.depth++;
            return;
        }
        if (obj != null && obj2 != null) {
            Set<Object> set = deepClassComparisonConfig.comparesMap.get(obj);
            Set<Object> set2 = set;
            if (set != null && set2.contains(obj2)) {
                if (deepClassComparisonConfig.debug) {
                    deepClassComparisonConfig.sb.append("> skipped (already compared) ");
                    for (int i = 0; i < deepClassComparisonConfig.f3975a.size; i++) {
                        deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i]);
                    }
                    deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                }
                deepClassComparisonConfig.depth++;
                return;
            }
            if (set2 == null) {
                set2 = f3977a.obtain();
            }
            set2.add(obj2);
            deepClassComparisonConfig.comparesMap.put(obj, set2);
        }
        if (deepClassComparisonConfig.debug) {
            deepClassComparisonConfig.sb.append("> comparing ");
            for (int i2 = 0; i2 < deepClassComparisonConfig.f3975a.size; i2++) {
                deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i2]);
            }
            deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
        }
        if (obj == null || obj2 == null) {
            deepClassComparisonConfig.appendPrefix().append(": one object is null\n");
        } else if (obj.getClass() != obj2.getClass()) {
            deepClassComparisonConfig.appendPrefix().append(": classes differ (").append(obj.getClass().getName()).append(", ").append(obj2.getClass().getName()).append(")\n");
        } else {
            Class<?> cls3 = obj.getClass();
            DeepClassComparator<?> deepClassComparator = null;
            if (c.containsKey(cls3)) {
                deepClassComparator = c.get(cls3);
            } else {
                Array.ArrayIterator<DeepClassComparator<?>> it = CLASS_COMPARATORS.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DeepClassComparator<?> next = it.next();
                    if (next.forClass().isAssignableFrom(cls3)) {
                        deepClassComparator = next;
                        break;
                    }
                }
                c.put(cls3, deepClassComparator);
            }
            if (deepClassComparator != null) {
                deepClassComparator.compare(obj, obj2, deepClassComparisonConfig);
            } else if (obj.getClass().isPrimitive() || obj.getClass().isEnum() || e.contains(obj.getClass())) {
                if (!obj.equals(obj2)) {
                    deepClassComparisonConfig.appendPrefix().append(": ").append(obj).append(" != ").append(obj2).append(SequenceUtils.EOL);
                }
            } else {
                Array<Field> allSerializableFields = ReflectionUtils.getAllSerializableFields(obj.getClass());
                for (int i3 = 0; i3 < allSerializableFields.size; i3++) {
                    Field field = allSerializableFields.items[i3];
                    try {
                        Class<?> type = field.getType();
                        if (type != Float.TYPE) {
                            if (type != Integer.TYPE) {
                                if (type == Long.TYPE) {
                                    if (deepClassComparisonConfig.debug) {
                                        deepClassComparisonConfig.sb.append("> comparing ");
                                        for (int i4 = 0; i4 < deepClassComparisonConfig.f3975a.size; i4++) {
                                            deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i4]);
                                        }
                                        deepClassComparisonConfig.sb.append(".(long)").append(field.getName());
                                        deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                                    }
                                    if (field.getLong(obj) != field.getLong(obj2)) {
                                        deepClassComparisonConfig.appendPrefix().append(".(long)").append(field.getName()).append(SequenceUtils.SPACE).append(field.getLong(obj)).append(" != ").append(field.getLong(obj2)).append(SequenceUtils.EOL);
                                    }
                                } else if (type == Boolean.TYPE) {
                                    if (deepClassComparisonConfig.debug) {
                                        deepClassComparisonConfig.sb.append("> comparing ");
                                        for (int i5 = 0; i5 < deepClassComparisonConfig.f3975a.size; i5++) {
                                            deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i5]);
                                        }
                                        deepClassComparisonConfig.sb.append(".(boolean)").append(field.getName());
                                        deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                                    }
                                    if (field.getBoolean(obj) != field.getBoolean(obj2)) {
                                        deepClassComparisonConfig.appendPrefix().append(".(boolean)").append(field.getName()).append(SequenceUtils.SPACE).append(field.getBoolean(obj)).append(" != ").append(field.getBoolean(obj2)).append(SequenceUtils.EOL);
                                    }
                                } else if (type == Double.TYPE) {
                                    if (deepClassComparisonConfig.debug) {
                                        deepClassComparisonConfig.sb.append("> comparing ");
                                        for (int i6 = 0; i6 < deepClassComparisonConfig.f3975a.size; i6++) {
                                            deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i6]);
                                        }
                                        deepClassComparisonConfig.sb.append(".(double)").append(field.getName());
                                        deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                                    }
                                    if (field.getDouble(obj) != field.getDouble(obj2)) {
                                        deepClassComparisonConfig.appendPrefix().append(".(double)").append(field.getName()).append(SequenceUtils.SPACE).append(field.getDouble(obj)).append(" != ").append(field.getDouble(obj2)).append(SequenceUtils.EOL);
                                    }
                                } else if (type == Byte.TYPE) {
                                    if (deepClassComparisonConfig.debug) {
                                        deepClassComparisonConfig.sb.append("> comparing ");
                                        for (int i7 = 0; i7 < deepClassComparisonConfig.f3975a.size; i7++) {
                                            deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i7]);
                                        }
                                        deepClassComparisonConfig.sb.append(".(byte)").append(field.getName());
                                        deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                                    }
                                    if (field.getByte(obj) != field.getByte(obj2)) {
                                        deepClassComparisonConfig.appendPrefix().append(".(byte)").append(field.getName()).append(SequenceUtils.SPACE).append((int) field.getByte(obj)).append(" != ").append((int) field.getByte(obj2)).append(SequenceUtils.EOL);
                                    }
                                } else {
                                    String str = TypeDescription.Generic.OfWildcardType.SYMBOL;
                                    if (field.get(obj) != null) {
                                        if (VERBOSE_MODE) {
                                            str = field.get(obj).getClass().getSimpleName() + "@" + Integer.toHexString(field.get(obj).hashCode());
                                            if (field.get(obj2) != null) {
                                                str = str + " <> @" + Integer.toHexString(field.get(obj2).hashCode());
                                            }
                                        } else {
                                            str = field.get(obj).getClass().getSimpleName();
                                        }
                                    } else if (field.get(obj2) != null) {
                                        if (VERBOSE_MODE) {
                                            if (field.get(obj) != null) {
                                                str = field.get(obj).getClass().getSimpleName() + "@" + Integer.toHexString(field.get(obj).hashCode());
                                            }
                                            str = str + " <> @" + Integer.toHexString(field.get(obj2).hashCode());
                                        } else {
                                            str = field.get(obj2).getClass().getSimpleName();
                                        }
                                    }
                                    Enum<?>[] enumArr = null;
                                    EnumKeyArray enumKeyArrayFieldAnnotationCached = ReflectionUtils.getEnumKeyArrayFieldAnnotationCached(field);
                                    if (enumKeyArrayFieldAnnotationCached != null) {
                                        enumArr = (Enum[]) enumKeyArrayFieldAnnotationCached.enumerator().getEnumConstants();
                                    }
                                    deepClassComparisonConfig.addPrefix(".(", str, ")", field.getName());
                                    deepClassComparisonConfig.keyEnum = enumArr;
                                    compareObjects(field.get(obj), field.get(obj2), deepClassComparisonConfig);
                                    deepClassComparisonConfig.keyEnum = null;
                                    deepClassComparisonConfig.popPrefix(4);
                                }
                            } else {
                                int i8 = field.getInt(obj);
                                int i9 = field.getInt(obj2);
                                if (deepClassComparisonConfig.debug) {
                                    deepClassComparisonConfig.sb.append("> comparing ");
                                    for (int i10 = 0; i10 < deepClassComparisonConfig.f3975a.size; i10++) {
                                        deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i10]);
                                    }
                                    deepClassComparisonConfig.sb.append(".(int)").append(field.getName());
                                    deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                                }
                                if (i8 != i9) {
                                    deepClassComparisonConfig.appendPrefix().append(".(int)").append(field.getName()).append(SequenceUtils.SPACE).append(i8).append(" != ").append(i9).append(SequenceUtils.EOL);
                                }
                            }
                        } else {
                            float f = field.getFloat(obj);
                            float f2 = field.getFloat(obj2);
                            if (deepClassComparisonConfig.debug) {
                                deepClassComparisonConfig.sb.append("> comparing ");
                                for (int i11 = 0; i11 < deepClassComparisonConfig.f3975a.size; i11++) {
                                    deepClassComparisonConfig.sb.append(deepClassComparisonConfig.f3975a.items[i11]);
                                }
                                deepClassComparisonConfig.sb.append(".(float)").append(field.getName());
                                deepClassComparisonConfig.sb.append(SequenceUtils.EOL);
                            }
                            if (f != f2) {
                                deepClassComparisonConfig.appendPrefix().append(".(float)").append(field.getName()).append(SequenceUtils.SPACE).append(f).append(" != ").append(f2).append(SequenceUtils.EOL);
                            }
                        }
                    } catch (Exception e2) {
                        deepClassComparisonConfig.depth++;
                        throw new RuntimeException("failed for " + deepClassComparisonConfig.f3975a.toString("") + "." + field.getName() + SequenceUtils.EOL + deepClassComparisonConfig.sb.toString(), e2);
                    }
                }
            }
        }
        deepClassComparisonConfig.depth++;
    }

    public static String toString(int i) {
        int i2 = i + 256;
        if (i2 >= 0 && i2 < f3976b.length) {
            String str = f3976b[i2];
            if (str == null) {
                f3976b[i2] = Integer.toString(i);
                return f3976b[i2];
            }
            return str;
        }
        return Integer.toString(i);
    }
}

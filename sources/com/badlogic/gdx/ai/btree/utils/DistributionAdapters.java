package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.ai.utils.random.ConstantDoubleDistribution;
import com.badlogic.gdx.ai.utils.random.ConstantFloatDistribution;
import com.badlogic.gdx.ai.utils.random.ConstantIntegerDistribution;
import com.badlogic.gdx.ai.utils.random.ConstantLongDistribution;
import com.badlogic.gdx.ai.utils.random.Distribution;
import com.badlogic.gdx.ai.utils.random.DoubleDistribution;
import com.badlogic.gdx.ai.utils.random.FloatDistribution;
import com.badlogic.gdx.ai.utils.random.GaussianDoubleDistribution;
import com.badlogic.gdx.ai.utils.random.GaussianFloatDistribution;
import com.badlogic.gdx.ai.utils.random.IntegerDistribution;
import com.badlogic.gdx.ai.utils.random.LongDistribution;
import com.badlogic.gdx.ai.utils.random.TriangularDoubleDistribution;
import com.badlogic.gdx.ai.utils.random.TriangularFloatDistribution;
import com.badlogic.gdx.ai.utils.random.TriangularIntegerDistribution;
import com.badlogic.gdx.ai.utils.random.TriangularLongDistribution;
import com.badlogic.gdx.ai.utils.random.UniformDoubleDistribution;
import com.badlogic.gdx.ai.utils.random.UniformFloatDistribution;
import com.badlogic.gdx.ai.utils.random.UniformIntegerDistribution;
import com.badlogic.gdx.ai.utils.random.UniformLongDistribution;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.StringTokenizer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters.class */
public class DistributionAdapters {
    private static final ObjectMap<Class<?>, Adapter<?>> ADAPTERS;
    ObjectMap<Class<?>, Adapter<?>> map = new ObjectMap<>();
    ObjectMap<Class<?>, ObjectMap<String, Adapter<?>>> typeMap = new ObjectMap<>();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$DistributionFormatException.class */
    public static class DistributionFormatException extends RuntimeException {
        public DistributionFormatException() {
        }

        public DistributionFormatException(String str) {
            super(str);
        }

        public DistributionFormatException(String str, Throwable th) {
            super(str, th);
        }

        public DistributionFormatException(Throwable th) {
            super(th);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$Adapter.class */
    public static abstract class Adapter<D extends Distribution> {
        final String category;
        final Class<?> type;

        public abstract D toDistribution(String[] strArr);

        public abstract String[] toParameters(D d);

        public Adapter(String str, Class<?> cls) {
            this.category = str;
            this.type = cls;
        }

        public static double parseDouble(String str) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                throw new DistributionFormatException("Not a double value: " + str, e);
            }
        }

        public static float parseFloat(String str) {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException e) {
                throw new DistributionFormatException("Not a float value: " + str, e);
            }
        }

        public static int parseInteger(String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new DistributionFormatException("Not an int value: " + str, e);
            }
        }

        public static long parseLong(String str) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                throw new DistributionFormatException("Not a long value: " + str, e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$DoubleAdapter.class */
    public static abstract class DoubleAdapter<D extends DoubleDistribution> extends Adapter<D> {
        public DoubleAdapter(String str) {
            super(str, DoubleDistribution.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$FloatAdapter.class */
    public static abstract class FloatAdapter<D extends FloatDistribution> extends Adapter<D> {
        public FloatAdapter(String str) {
            super(str, FloatDistribution.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$IntegerAdapter.class */
    public static abstract class IntegerAdapter<D extends IntegerDistribution> extends Adapter<D> {
        public IntegerAdapter(String str) {
            super(str, IntegerDistribution.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/DistributionAdapters$LongAdapter.class */
    public static abstract class LongAdapter<D extends LongDistribution> extends Adapter<D> {
        public LongAdapter(String str) {
            super(str, LongDistribution.class);
        }
    }

    static {
        ObjectMap<Class<?>, Adapter<?>> objectMap = new ObjectMap<>();
        ADAPTERS = objectMap;
        objectMap.put(ConstantDoubleDistribution.class, new DoubleAdapter<ConstantDoubleDistribution>("constant") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.1
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final ConstantDoubleDistribution toDistribution(String[] strArr) {
                if (strArr.length != 1) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1);
                }
                return new ConstantDoubleDistribution(parseDouble(strArr[0]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(ConstantDoubleDistribution constantDoubleDistribution) {
                return new String[]{Double.toString(constantDoubleDistribution.getValue())};
            }
        });
        ADAPTERS.put(ConstantFloatDistribution.class, new FloatAdapter<ConstantFloatDistribution>("constant") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.2
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final ConstantFloatDistribution toDistribution(String[] strArr) {
                if (strArr.length != 1) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1);
                }
                return new ConstantFloatDistribution(parseFloat(strArr[0]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(ConstantFloatDistribution constantFloatDistribution) {
                return new String[]{Float.toString(constantFloatDistribution.getValue())};
            }
        });
        ADAPTERS.put(ConstantIntegerDistribution.class, new IntegerAdapter<ConstantIntegerDistribution>("constant") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.3
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final ConstantIntegerDistribution toDistribution(String[] strArr) {
                if (strArr.length != 1) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1);
                }
                return new ConstantIntegerDistribution(parseInteger(strArr[0]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(ConstantIntegerDistribution constantIntegerDistribution) {
                return new String[]{Integer.toString(constantIntegerDistribution.getValue())};
            }
        });
        ADAPTERS.put(ConstantLongDistribution.class, new LongAdapter<ConstantLongDistribution>("constant") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.4
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final ConstantLongDistribution toDistribution(String[] strArr) {
                if (strArr.length != 1) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1);
                }
                return new ConstantLongDistribution(parseLong(strArr[0]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(ConstantLongDistribution constantLongDistribution) {
                return new String[]{Long.toString(constantLongDistribution.getValue())};
            }
        });
        ADAPTERS.put(GaussianDoubleDistribution.class, new DoubleAdapter<GaussianDoubleDistribution>("gaussian") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.5
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final GaussianDoubleDistribution toDistribution(String[] strArr) {
                if (strArr.length != 2) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 2);
                }
                return new GaussianDoubleDistribution(parseDouble(strArr[0]), parseDouble(strArr[1]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(GaussianDoubleDistribution gaussianDoubleDistribution) {
                return new String[]{Double.toString(gaussianDoubleDistribution.getMean()), Double.toString(gaussianDoubleDistribution.getStandardDeviation())};
            }
        });
        ADAPTERS.put(GaussianFloatDistribution.class, new FloatAdapter<GaussianFloatDistribution>("gaussian") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.6
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final GaussianFloatDistribution toDistribution(String[] strArr) {
                if (strArr.length != 2) {
                    throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 2);
                }
                return new GaussianFloatDistribution(parseFloat(strArr[0]), parseFloat(strArr[1]));
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(GaussianFloatDistribution gaussianFloatDistribution) {
                return new String[]{Float.toString(gaussianFloatDistribution.getMean()), Float.toString(gaussianFloatDistribution.getStandardDeviation())};
            }
        });
        ADAPTERS.put(TriangularDoubleDistribution.class, new DoubleAdapter<TriangularDoubleDistribution>("triangular") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.7
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final TriangularDoubleDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new TriangularDoubleDistribution(parseDouble(strArr[0]));
                    case 2:
                        return new TriangularDoubleDistribution(parseDouble(strArr[0]), parseDouble(strArr[1]));
                    case 3:
                        return new TriangularDoubleDistribution(parseDouble(strArr[0]), parseDouble(strArr[1]), parseDouble(strArr[2]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2, 3);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(TriangularDoubleDistribution triangularDoubleDistribution) {
                return new String[]{Double.toString(triangularDoubleDistribution.getLow()), Double.toString(triangularDoubleDistribution.getHigh()), Double.toString(triangularDoubleDistribution.getMode())};
            }
        });
        ADAPTERS.put(TriangularFloatDistribution.class, new FloatAdapter<TriangularFloatDistribution>("triangular") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.8
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final TriangularFloatDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new TriangularFloatDistribution(parseFloat(strArr[0]));
                    case 2:
                        return new TriangularFloatDistribution(parseFloat(strArr[0]), parseFloat(strArr[1]));
                    case 3:
                        return new TriangularFloatDistribution(parseFloat(strArr[0]), parseFloat(strArr[1]), parseFloat(strArr[2]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2, 3);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(TriangularFloatDistribution triangularFloatDistribution) {
                return new String[]{Float.toString(triangularFloatDistribution.getLow()), Float.toString(triangularFloatDistribution.getHigh()), Float.toString(triangularFloatDistribution.getMode())};
            }
        });
        ADAPTERS.put(TriangularIntegerDistribution.class, new IntegerAdapter<TriangularIntegerDistribution>("triangular") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.9
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final TriangularIntegerDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new TriangularIntegerDistribution(parseInteger(strArr[0]));
                    case 2:
                        return new TriangularIntegerDistribution(parseInteger(strArr[0]), parseInteger(strArr[1]));
                    case 3:
                        return new TriangularIntegerDistribution(parseInteger(strArr[0]), parseInteger(strArr[1]), Float.valueOf(strArr[2]).floatValue());
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2, 3);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(TriangularIntegerDistribution triangularIntegerDistribution) {
                return new String[]{Integer.toString(triangularIntegerDistribution.getLow()), Integer.toString(triangularIntegerDistribution.getHigh()), Float.toString(triangularIntegerDistribution.getMode())};
            }
        });
        ADAPTERS.put(TriangularLongDistribution.class, new LongAdapter<TriangularLongDistribution>("triangular") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.10
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final TriangularLongDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new TriangularLongDistribution(parseLong(strArr[0]));
                    case 2:
                        return new TriangularLongDistribution(parseLong(strArr[0]), parseLong(strArr[1]));
                    case 3:
                        return new TriangularLongDistribution(parseLong(strArr[0]), parseLong(strArr[1]), parseDouble(strArr[2]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2, 3);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(TriangularLongDistribution triangularLongDistribution) {
                return new String[]{Long.toString(triangularLongDistribution.getLow()), Long.toString(triangularLongDistribution.getHigh()), Double.toString(triangularLongDistribution.getMode())};
            }
        });
        ADAPTERS.put(UniformDoubleDistribution.class, new DoubleAdapter<UniformDoubleDistribution>("uniform") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.11
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final UniformDoubleDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new UniformDoubleDistribution(parseDouble(strArr[0]));
                    case 2:
                        return new UniformDoubleDistribution(parseDouble(strArr[0]), parseDouble(strArr[1]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(UniformDoubleDistribution uniformDoubleDistribution) {
                return new String[]{Double.toString(uniformDoubleDistribution.getLow()), Double.toString(uniformDoubleDistribution.getHigh())};
            }
        });
        ADAPTERS.put(UniformFloatDistribution.class, new FloatAdapter<UniformFloatDistribution>("uniform") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.12
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final UniformFloatDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new UniformFloatDistribution(parseFloat(strArr[0]));
                    case 2:
                        return new UniformFloatDistribution(parseFloat(strArr[0]), parseFloat(strArr[1]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(UniformFloatDistribution uniformFloatDistribution) {
                return new String[]{Float.toString(uniformFloatDistribution.getLow()), Float.toString(uniformFloatDistribution.getHigh())};
            }
        });
        ADAPTERS.put(UniformIntegerDistribution.class, new IntegerAdapter<UniformIntegerDistribution>("uniform") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.13
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final UniformIntegerDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new UniformIntegerDistribution(parseInteger(strArr[0]));
                    case 2:
                        return new UniformIntegerDistribution(parseInteger(strArr[0]), parseInteger(strArr[1]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(UniformIntegerDistribution uniformIntegerDistribution) {
                return new String[]{Integer.toString(uniformIntegerDistribution.getLow()), Integer.toString(uniformIntegerDistribution.getHigh())};
            }
        });
        ADAPTERS.put(UniformLongDistribution.class, new LongAdapter<UniformLongDistribution>("uniform") { // from class: com.badlogic.gdx.ai.btree.utils.DistributionAdapters.14
            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final UniformLongDistribution toDistribution(String[] strArr) {
                switch (strArr.length) {
                    case 1:
                        return new UniformLongDistribution(parseLong(strArr[0]));
                    case 2:
                        return new UniformLongDistribution(parseLong(strArr[0]), parseLong(strArr[1]));
                    default:
                        throw DistributionAdapters.invalidNumberOfArgumentsException(strArr.length, 1, 2);
                }
            }

            @Override // com.badlogic.gdx.ai.btree.utils.DistributionAdapters.Adapter
            public final String[] toParameters(UniformLongDistribution uniformLongDistribution) {
                return new String[]{Long.toString(uniformLongDistribution.getLow()), Long.toString(uniformLongDistribution.getHigh())};
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DistributionAdapters() {
        ObjectMap.Entries<Class<?>, Adapter<?>> it = ADAPTERS.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            add((Class) next.key, (Adapter) next.value);
        }
    }

    public final void add(Class<?> cls, Adapter<?> adapter) {
        this.map.put(cls, adapter);
        ObjectMap<String, Adapter<?>> objectMap = this.typeMap.get(adapter.type);
        ObjectMap<String, Adapter<?>> objectMap2 = objectMap;
        if (objectMap == null) {
            objectMap2 = new ObjectMap<>();
            this.typeMap.put(adapter.type, objectMap2);
        }
        objectMap2.put(adapter.category, adapter);
    }

    public <T extends Distribution> T toDistribution(String str, Class<T> cls) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ", \t\f");
        if (!stringTokenizer.hasMoreTokens()) {
            throw new DistributionFormatException("Missing ditribution type");
        }
        String nextToken = stringTokenizer.nextToken();
        Adapter<?> adapter = this.typeMap.get(cls).get(nextToken);
        if (adapter == null) {
            throw new DistributionFormatException("Cannot create a '" + cls.getSimpleName() + "' of type '" + nextToken + "'");
        }
        String[] strArr = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = stringTokenizer.nextToken();
        }
        return (T) adapter.toDistribution(strArr);
    }

    public String toString(Distribution distribution) {
        Adapter<?> adapter = this.map.get(distribution.getClass());
        String[] parameters = adapter.toParameters(distribution);
        String str = adapter.category;
        for (String str2 : parameters) {
            str = str + "," + str2;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DistributionFormatException invalidNumberOfArgumentsException(int i, int... iArr) {
        String str;
        String str2 = "Found " + i + " arguments in triangular distribution; expected ";
        if (iArr.length < 2) {
            str = str2 + iArr.length;
        } else {
            String str3 = "";
            int i2 = 0;
            while (i2 < iArr.length - 1) {
                int i3 = i2;
                i2++;
                str2 = str2 + str3 + iArr[i3];
                str3 = ", ";
            }
            str = str2 + " or " + iArr[i2];
        }
        return new DistributionFormatException(str);
    }
}

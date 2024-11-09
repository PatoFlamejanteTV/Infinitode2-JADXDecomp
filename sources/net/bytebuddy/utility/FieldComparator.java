package net.bytebuddy.utility;

import java.lang.reflect.Field;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/FieldComparator.class */
public enum FieldComparator implements Comparator<Field> {
    INSTANCE;

    @Override // java.util.Comparator
    public final int compare(Field field, Field field2) {
        if (field == field2) {
            return 0;
        }
        int compareTo = field.getName().compareTo(field2.getName());
        if (compareTo == 0) {
            return field.getType().getName().compareTo(field2.getType().getName());
        }
        return compareTo;
    }
}

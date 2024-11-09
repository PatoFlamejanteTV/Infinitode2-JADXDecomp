package net.bytebuddy.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/CompoundList.class */
public class CompoundList {
    private CompoundList() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static <S> List<S> of(S s, List<? extends S> list) {
        if (list.isEmpty()) {
            return Collections.singletonList(s);
        }
        ArrayList arrayList = new ArrayList(1 + list.size());
        arrayList.add(s);
        arrayList.addAll(list);
        return arrayList;
    }

    public static <S> List<S> of(List<? extends S> list, S s) {
        if (list.isEmpty()) {
            return Collections.singletonList(s);
        }
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(s);
        return arrayList;
    }

    public static <S> List<S> of(List<? extends S> list, List<? extends S> list2) {
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return arrayList;
    }

    public static <S> List<S> of(List<? extends S> list, List<? extends S> list2, List<? extends S> list3) {
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        arrayList.addAll(list3);
        return arrayList;
    }
}

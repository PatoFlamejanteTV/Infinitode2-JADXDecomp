package org.jsoup.nodes;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.SharedConstants;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Range;
import org.jsoup.parser.ParseSettings;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Attributes.class */
public class Attributes implements Cloneable, Iterable<Attribute> {
    static final char InternalPrefix = '/';
    protected static final String dataPrefix = "data-";
    private static final int InitialCapacity = 3;
    private static final int GrowthFactor = 2;
    static final int NotFound = -1;
    private static final String EmptyString = "";
    private int size = 0;
    String[] keys = new String[3];
    Object[] vals = new Object[3];

    private void checkCapacity(int i) {
        Validate.isTrue(i >= this.size);
        int length = this.keys.length;
        if (length >= i) {
            return;
        }
        int i2 = length >= 3 ? this.size << 1 : 3;
        if (i > i2) {
            i2 = i;
        }
        this.keys = (String[]) Arrays.copyOf(this.keys, i2);
        this.vals = Arrays.copyOf(this.vals, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOfKey(String str) {
        Validate.notNull(str);
        for (int i = 0; i < this.size; i++) {
            if (str.equals(this.keys[i])) {
                return i;
            }
        }
        return -1;
    }

    private int indexOfKeyIgnoreCase(String str) {
        Validate.notNull(str);
        for (int i = 0; i < this.size; i++) {
            if (str.equalsIgnoreCase(this.keys[i])) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String checkNotNull(Object obj) {
        return obj == null ? "" : (String) obj;
    }

    public String get(String str) {
        int indexOfKey = indexOfKey(str);
        return indexOfKey == -1 ? "" : checkNotNull(this.vals[indexOfKey]);
    }

    public Attribute attribute(String str) {
        int indexOfKey = indexOfKey(str);
        if (indexOfKey == -1) {
            return null;
        }
        return new Attribute(str, checkNotNull(this.vals[indexOfKey]), this);
    }

    public String getIgnoreCase(String str) {
        int indexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        return indexOfKeyIgnoreCase == -1 ? "" : checkNotNull(this.vals[indexOfKeyIgnoreCase]);
    }

    public Attributes add(String str, String str2) {
        addObject(str, str2);
        return this;
    }

    private void addObject(String str, Object obj) {
        checkCapacity(this.size + 1);
        this.keys[this.size] = str;
        this.vals[this.size] = obj;
        this.size++;
    }

    public Attributes put(String str, String str2) {
        Validate.notNull(str);
        int indexOfKey = indexOfKey(str);
        if (indexOfKey != -1) {
            this.vals[indexOfKey] = str2;
        } else {
            add(str, str2);
        }
        return this;
    }

    Map<String, Object> userData() {
        Map<String, Object> map;
        int indexOfKey = indexOfKey(SharedConstants.UserDataKey);
        if (indexOfKey != -1) {
            map = (Map) this.vals[indexOfKey];
        } else {
            map = new HashMap();
            addObject(SharedConstants.UserDataKey, map);
        }
        return map;
    }

    public Object userData(String str) {
        Validate.notNull(str);
        if (hasKey(SharedConstants.UserDataKey)) {
            return userData().get(str);
        }
        return null;
    }

    public Attributes userData(String str, Object obj) {
        Validate.notNull(str);
        userData().put(str, obj);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putIgnoreCase(String str, String str2) {
        int indexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        if (indexOfKeyIgnoreCase != -1) {
            this.vals[indexOfKeyIgnoreCase] = str2;
            if (!this.keys[indexOfKeyIgnoreCase].equals(str)) {
                this.keys[indexOfKeyIgnoreCase] = str;
                return;
            }
            return;
        }
        add(str, str2);
    }

    public Attributes put(String str, boolean z) {
        if (z) {
            putIgnoreCase(str, null);
        } else {
            remove(str);
        }
        return this;
    }

    public Attributes put(Attribute attribute) {
        Validate.notNull(attribute);
        put(attribute.getKey(), attribute.getValue());
        attribute.parent = this;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove(int i) {
        Validate.isFalse(i >= this.size);
        int i2 = (this.size - i) - 1;
        if (i2 > 0) {
            System.arraycopy(this.keys, i + 1, this.keys, i, i2);
            System.arraycopy(this.vals, i + 1, this.vals, i, i2);
        }
        this.size--;
        this.keys[this.size] = null;
        this.vals[this.size] = null;
    }

    public void remove(String str) {
        int indexOfKey = indexOfKey(str);
        if (indexOfKey != -1) {
            remove(indexOfKey);
        }
    }

    public void removeIgnoreCase(String str) {
        int indexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        if (indexOfKeyIgnoreCase != -1) {
            remove(indexOfKeyIgnoreCase);
        }
    }

    public boolean hasKey(String str) {
        return indexOfKey(str) != -1;
    }

    public boolean hasKeyIgnoreCase(String str) {
        return indexOfKeyIgnoreCase(str) != -1;
    }

    public boolean hasDeclaredValueForKey(String str) {
        int indexOfKey = indexOfKey(str);
        return (indexOfKey == -1 || this.vals[indexOfKey] == null) ? false : true;
    }

    public boolean hasDeclaredValueForKeyIgnoreCase(String str) {
        int indexOfKeyIgnoreCase = indexOfKeyIgnoreCase(str);
        return (indexOfKeyIgnoreCase == -1 || this.vals[indexOfKeyIgnoreCase] == null) ? false : true;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addAll(Attributes attributes) {
        if (attributes.size() == 0) {
            return;
        }
        checkCapacity(this.size + attributes.size);
        boolean z = this.size != 0;
        Iterator<Attribute> it = attributes.iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            if (z) {
                put(next);
            } else {
                add(next.getKey(), next.getValue());
            }
        }
    }

    public Range.AttributeRange sourceRange(String str) {
        Map<String, Range.AttributeRange> ranges;
        Range.AttributeRange attributeRange;
        if (hasKey(str) && (ranges = getRanges()) != null && (attributeRange = ranges.get(str)) != null) {
            return attributeRange;
        }
        return Range.AttributeRange.UntrackedAttr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Range.AttributeRange> getRanges() {
        return (Map) userData(SharedConstants.AttrRangeKey);
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return new Iterator<Attribute>() { // from class: org.jsoup.nodes.Attributes.1
            int expectedSize;
            int i = 0;

            {
                this.expectedSize = Attributes.this.size;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                checkModified();
                while (this.i < Attributes.this.size && Attributes.isInternalKey(Attributes.this.keys[this.i])) {
                    this.i++;
                }
                return this.i < Attributes.this.size;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Attribute next() {
                checkModified();
                if (this.i >= Attributes.this.size) {
                    throw new NoSuchElementException();
                }
                Attribute attribute = new Attribute(Attributes.this.keys[this.i], (String) Attributes.this.vals[this.i], Attributes.this);
                this.i++;
                return attribute;
            }

            private void checkModified() {
                if (Attributes.this.size != this.expectedSize) {
                    throw new ConcurrentModificationException("Use Iterator#remove() instead to remove attributes while iterating.");
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                Attributes attributes = Attributes.this;
                int i = this.i - 1;
                this.i = i;
                attributes.remove(i);
                this.expectedSize--;
            }
        };
    }

    public List<Attribute> asList() {
        ArrayList arrayList = new ArrayList(this.size);
        for (int i = 0; i < this.size; i++) {
            if (!isInternalKey(this.keys[i])) {
                arrayList.add(new Attribute(this.keys[i], (String) this.vals[i], this));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Map<String, String> dataset() {
        return new Dataset();
    }

    public String html() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        try {
            html(borrowBuilder, new Document("").outputSettings());
            return StringUtil.releaseBuilder(borrowBuilder);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void html(Appendable appendable, Document.OutputSettings outputSettings) {
        String validKey;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (!isInternalKey(this.keys[i2]) && (validKey = Attribute.getValidKey(this.keys[i2], outputSettings.syntax())) != null) {
                Attribute.htmlNoValidate(validKey, (String) this.vals[i2], appendable.append(' '), outputSettings);
            }
        }
    }

    public String toString() {
        return html();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.size != attributes.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            int indexOfKey = attributes.indexOfKey(this.keys[i]);
            if (indexOfKey == -1) {
                return false;
            }
            Object obj2 = this.vals[i];
            Object obj3 = attributes.vals[indexOfKey];
            if (obj2 == null) {
                if (obj3 != null) {
                    return false;
                }
            } else if (!obj2.equals(obj3)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return (((this.size * 31) + Arrays.hashCode(this.keys)) * 31) + Arrays.hashCode(this.vals);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Attributes m2529clone() {
        try {
            Attributes attributes = (Attributes) super.clone();
            attributes.size = this.size;
            attributes.keys = (String[]) Arrays.copyOf(this.keys, this.size);
            attributes.vals = Arrays.copyOf(this.vals, this.size);
            return attributes;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void normalize() {
        for (int i = 0; i < this.size; i++) {
            if (!isInternalKey(this.keys[i])) {
                String[] strArr = this.keys;
                strArr[i] = Normalizer.lowerCase(strArr[i]);
            }
        }
    }

    public int deduplicate(ParseSettings parseSettings) {
        if (isEmpty()) {
            return 0;
        }
        boolean preserveAttributeCase = parseSettings.preserveAttributeCase();
        int i = 0;
        for (int i2 = 0; i2 < this.keys.length; i2++) {
            int i3 = i2 + 1;
            while (i3 < this.keys.length && this.keys[i3] != null) {
                if ((preserveAttributeCase && this.keys[i2].equals(this.keys[i3])) || (!preserveAttributeCase && this.keys[i2].equalsIgnoreCase(this.keys[i3]))) {
                    i++;
                    remove(i3);
                    i3--;
                }
                i3++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Attributes$Dataset.class */
    public static class Dataset extends AbstractMap<String, String> {
        private final Attributes attributes;

        private Dataset(Attributes attributes) {
            this.attributes = attributes;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<String, String>> entrySet() {
            return new EntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public String put(String str, String str2) {
            String dataKey = Attributes.dataKey(str);
            String str3 = this.attributes.hasKey(dataKey) ? this.attributes.get(dataKey) : null;
            this.attributes.put(dataKey, str2);
            return str3;
        }

        /* loaded from: infinitode-2.jar:org/jsoup/nodes/Attributes$Dataset$EntrySet.class */
        private class EntrySet extends AbstractSet<Map.Entry<String, String>> {
            private EntrySet() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<String, String>> iterator() {
                return new DatasetIterator();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int i = 0;
                while (new DatasetIterator().hasNext()) {
                    i++;
                }
                return i;
            }
        }

        /* loaded from: infinitode-2.jar:org/jsoup/nodes/Attributes$Dataset$DatasetIterator.class */
        private class DatasetIterator implements Iterator<Map.Entry<String, String>> {
            private Iterator<Attribute> attrIter;
            private Attribute attr;

            private DatasetIterator() {
                this.attrIter = Dataset.this.attributes.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                while (this.attrIter.hasNext()) {
                    this.attr = this.attrIter.next();
                    if (this.attr.isDataAttribute()) {
                        return true;
                    }
                }
                return false;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Map.Entry<String, String> next() {
                return new Attribute(this.attr.getKey().substring(5), this.attr.getValue());
            }

            @Override // java.util.Iterator
            public void remove() {
                Dataset.this.attributes.remove(this.attr.getKey());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String dataKey(String str) {
        return dataPrefix + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String internalKey(String str) {
        return "/" + str;
    }

    static boolean isInternalKey(String str) {
        return str != null && str.length() > 1 && str.charAt(0) == '/';
    }
}

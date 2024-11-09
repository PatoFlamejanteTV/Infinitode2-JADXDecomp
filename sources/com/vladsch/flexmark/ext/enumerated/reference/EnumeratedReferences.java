package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferences.class */
public class EnumeratedReferences {
    public static final String EMPTY_TYPE = "";
    public static final int[] EMPTY_ORDINALS = new int[0];
    private final EnumeratedReferenceRepository referenceRepository;
    private final HashMap<String, Integer> enumerationCounters = new HashMap<>();
    private final HashMap<String, int[]> enumeratedReferenceOrdinals = new HashMap<>();

    public EnumeratedReferences(DataHolder dataHolder) {
        this.referenceRepository = EnumeratedReferenceExtension.ENUMERATED_REFERENCES.get(dataHolder);
    }

    public void add(String str) {
        int intValue;
        String[] split = EnumeratedReferenceRepository.getType(str).split(":");
        int[] iArr = new int[split.length];
        StringBuilder sb = new StringBuilder();
        int length = split.length;
        for (int i = 0; i < length; i++) {
            sb.append(split[i]);
            String sb2 = sb.toString();
            if (i < length - 1) {
                Integer num = this.enumerationCounters.get(sb2);
                int intValue2 = num == null ? 0 : num.intValue();
                sb.append(':').append(intValue2).append(':');
                iArr[i] = intValue2;
            } else {
                if (!this.enumerationCounters.containsKey(sb2)) {
                    this.enumerationCounters.put(sb2, 1);
                    intValue = 1;
                } else {
                    intValue = this.enumerationCounters.get(sb2).intValue() + 1;
                    this.enumerationCounters.put(sb2, Integer.valueOf(intValue));
                }
                iArr[i] = intValue;
            }
        }
        this.enumeratedReferenceOrdinals.put(str, iArr);
    }

    public EnumeratedReferenceRendering[] getEnumeratedReferenceOrdinals(String str) {
        String[] split = EnumeratedReferenceRepository.getType(str).split(":");
        EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr = new EnumeratedReferenceRendering[split.length];
        int[] iArr = this.enumeratedReferenceOrdinals.get(str);
        int[] iArr2 = iArr;
        if (iArr == null) {
            iArr2 = EMPTY_ORDINALS;
        }
        int length = split.length;
        int i = 0;
        while (i < length) {
            String str2 = split[i];
            enumeratedReferenceRenderingArr[i] = new EnumeratedReferenceRendering(this.referenceRepository.get(str2), str2, i < iArr2.length ? iArr2[i] : 0);
            i++;
        }
        return enumeratedReferenceRenderingArr;
    }

    public void renderReferenceOrdinals(String str, EnumeratedOrdinalRenderer enumeratedOrdinalRenderer) {
        renderReferenceOrdinals(getEnumeratedReferenceOrdinals(str), enumeratedOrdinalRenderer);
    }

    public static void renderReferenceOrdinals(EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr, EnumeratedOrdinalRenderer enumeratedOrdinalRenderer) {
        Node node;
        enumeratedOrdinalRenderer.startRendering(enumeratedReferenceRenderingArr);
        ArrayList arrayList = new ArrayList();
        EnumeratedReferenceRendering enumeratedReferenceRendering = enumeratedReferenceRenderingArr[enumeratedReferenceRenderingArr.length - 1];
        for (EnumeratedReferenceRendering enumeratedReferenceRendering2 : enumeratedReferenceRenderingArr) {
            int i = enumeratedReferenceRendering2.referenceOrdinal;
            String str = enumeratedReferenceRendering2.referenceType;
            boolean z = false;
            if (enumeratedReferenceRendering2 != enumeratedReferenceRendering) {
                if (enumeratedReferenceRendering2.referenceFormat != null) {
                    Node lastChild = enumeratedReferenceRendering2.referenceFormat.getLastChild();
                    while (true) {
                        node = lastChild;
                        if (node == null || (node instanceof EnumeratedReferenceBase)) {
                            break;
                        } else {
                            lastChild = node.getLastChild();
                        }
                    }
                    z = (node instanceof EnumeratedReferenceBase) && ((EnumeratedReferenceBase) node).getText().isEmpty();
                } else {
                    z = true;
                }
            }
            arrayList.add(new CompoundEnumeratedReferenceRendering(i, enumeratedReferenceRendering2.referenceFormat, str, z));
        }
        int size = arrayList.size() - 1;
        Runnable enumOrdinalRunnable = enumeratedOrdinalRenderer.getEnumOrdinalRunnable();
        enumeratedOrdinalRenderer.setEnumOrdinalRunnable(() -> {
            for (int i2 = 0; i2 < size; i2++) {
                CompoundEnumeratedReferenceRendering compoundEnumeratedReferenceRendering = (CompoundEnumeratedReferenceRendering) arrayList.get(i2);
                Runnable enumOrdinalRunnable2 = enumeratedOrdinalRenderer.getEnumOrdinalRunnable();
                enumeratedOrdinalRenderer.setEnumOrdinalRunnable(null);
                enumeratedOrdinalRenderer.render(compoundEnumeratedReferenceRendering.ordinal, compoundEnumeratedReferenceRendering.referenceFormat, compoundEnumeratedReferenceRendering.defaultText, compoundEnumeratedReferenceRendering.needSeparator);
                enumeratedOrdinalRenderer.setEnumOrdinalRunnable(enumOrdinalRunnable2);
            }
        });
        CompoundEnumeratedReferenceRendering compoundEnumeratedReferenceRendering = (CompoundEnumeratedReferenceRendering) arrayList.get(size);
        enumeratedOrdinalRenderer.render(compoundEnumeratedReferenceRendering.ordinal, compoundEnumeratedReferenceRendering.referenceFormat, compoundEnumeratedReferenceRendering.defaultText, compoundEnumeratedReferenceRendering.needSeparator);
        enumeratedOrdinalRenderer.setEnumOrdinalRunnable(enumOrdinalRunnable);
        enumeratedOrdinalRenderer.endRendering();
    }
}

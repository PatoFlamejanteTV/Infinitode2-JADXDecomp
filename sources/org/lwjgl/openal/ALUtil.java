package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALUtil.class */
public final class ALUtil {
    private ALUtil() {
    }

    public static List<String> getStringList(long j, int i) {
        long nalcGetString = ALC10.nalcGetString(j, i);
        if (nalcGetString == 0) {
            return null;
        }
        ByteBuffer memByteBuffer = MemoryUtil.memByteBuffer(nalcGetString, Integer.MAX_VALUE);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            if (memByteBuffer.get() == 0) {
                int position = memByteBuffer.position() - 1;
                if (position != i2) {
                    arrayList.add(MemoryUtil.memUTF8(memByteBuffer, position - i2, i2));
                    i2 = memByteBuffer.position();
                } else {
                    return arrayList;
                }
            }
        }
    }
}

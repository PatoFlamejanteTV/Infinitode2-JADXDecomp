package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeReader.class */
public abstract class BehaviorTreeReader {
    private static final String LOG_TAG = "BehaviorTreeReader";
    protected boolean debug;
    protected int lineNumber;
    protected boolean reportsComments;
    private static final byte[] _btree_actions = init__btree_actions_0();
    private static final short[] _btree_key_offsets = init__btree_key_offsets_0();
    private static final char[] _btree_trans_keys = init__btree_trans_keys_0();
    private static final byte[] _btree_single_lengths = init__btree_single_lengths_0();
    private static final byte[] _btree_range_lengths = init__btree_range_lengths_0();
    private static final short[] _btree_index_offsets = init__btree_index_offsets_0();
    private static final byte[] _btree_indicies = init__btree_indicies_0();
    private static final byte[] _btree_trans_targs = init__btree_trans_targs_0();
    private static final byte[] _btree_trans_actions = init__btree_trans_actions_0();
    private static final byte[] _btree_eof_actions = init__btree_eof_actions_0();
    static final int btree_start = 29;
    static final int btree_first_final = 29;
    static final int btree_error = 0;
    static final int btree_en_main = 29;

    protected abstract void startLine(int i);

    protected abstract void startStatement(String str, boolean z, boolean z2);

    protected abstract void attribute(String str, Object obj);

    protected abstract void endStatement();

    protected abstract void endLine();

    protected void comment(String str) {
    }

    public BehaviorTreeReader() {
        this(false);
    }

    public BehaviorTreeReader(boolean z) {
        this.debug = false;
        this.reportsComments = z;
    }

    public void parse(String str) {
        char[] charArray = str.toCharArray();
        parse(charArray, 0, charArray.length);
    }

    public void parse(Reader reader) {
        try {
            try {
                char[] cArr = new char[1024];
                int i = 0;
                while (true) {
                    int read = reader.read(cArr, i, cArr.length - i);
                    if (read != -1) {
                        if (read != 0) {
                            i += read;
                        } else {
                            char[] cArr2 = new char[cArr.length << 1];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            cArr = cArr2;
                        }
                    } else {
                        parse(cArr, 0, i);
                        StreamUtils.closeQuietly(reader);
                        return;
                    }
                }
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(reader);
            throw th;
        }
    }

    public void parse(InputStream inputStream) {
        try {
            try {
                parse(new InputStreamReader(inputStream, "UTF-8"));
                StreamUtils.closeQuietly(inputStream);
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    public void parse(FileHandle fileHandle) {
        try {
            parse(fileHandle.reader("UTF-8"));
        } catch (Exception e) {
            throw new SerializationException("Error parsing file: " + fileHandle, e);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:194:0x0604. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x019a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:40:0x03b5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:57:0x0431. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0031. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0083 A[Catch: RuntimeException -> 0x08f0, TryCatch #1 {RuntimeException -> 0x08f0, blocks: (B:5:0x0031, B:12:0x006a, B:14:0x0083, B:17:0x0097, B:307:0x00b0, B:20:0x00b9, B:22:0x00c6, B:26:0x00cf, B:27:0x015e, B:29:0x0176, B:30:0x0189, B:32:0x0191, B:33:0x019a, B:134:0x01e0, B:136:0x01fa, B:139:0x0206, B:141:0x020d, B:142:0x0232, B:144:0x023c, B:146:0x0243, B:147:0x0263, B:150:0x026f, B:152:0x0279, B:154:0x0280, B:155:0x02a0, B:156:0x02ac, B:158:0x02b6, B:160:0x02c0, B:162:0x02c8, B:164:0x02cf, B:165:0x02f7, B:166:0x0309, B:168:0x0310, B:169:0x0338, B:171:0x034a, B:172:0x0354, B:174:0x0355, B:176:0x035c, B:177:0x0386, B:35:0x0394, B:37:0x039b, B:39:0x03b1, B:40:0x03b5, B:45:0x040a, B:42:0x0401, B:52:0x0410, B:54:0x0417, B:55:0x0423, B:56:0x042d, B:57:0x0431, B:58:0x044c, B:62:0x0461, B:59:0x0458, B:68:0x0467, B:74:0x048a, B:77:0x04aa, B:82:0x04b5, B:85:0x04c1, B:86:0x04c5, B:89:0x04cc, B:92:0x051b, B:94:0x0503, B:100:0x0530, B:103:0x0537, B:106:0x054d, B:109:0x0554, B:113:0x0563, B:115:0x056b, B:116:0x0578, B:118:0x0574, B:120:0x0596, B:310:0x00dc, B:311:0x00ea, B:313:0x00f5, B:316:0x010b, B:327:0x0127, B:319:0x0130, B:321:0x013f, B:325:0x0148, B:330:0x0157, B:182:0x05cc, B:190:0x05e1, B:191:0x05f3, B:193:0x05fb, B:194:0x0604, B:245:0x0644, B:247:0x065e, B:250:0x066a, B:252:0x0671, B:253:0x0696, B:255:0x06a0, B:257:0x06a7, B:258:0x06c7, B:261:0x06d3, B:263:0x06dd, B:265:0x06e4, B:266:0x0704, B:267:0x0710, B:269:0x071a, B:271:0x0724, B:273:0x072c, B:275:0x0733, B:276:0x075b, B:277:0x076d, B:279:0x0774, B:280:0x079c, B:282:0x07ae, B:283:0x07b8, B:284:0x07b9, B:286:0x07c0, B:287:0x07ea, B:198:0x07fd, B:201:0x0809, B:202:0x080d, B:209:0x0814, B:212:0x0863, B:214:0x084b, B:219:0x0878, B:222:0x087f, B:225:0x0895, B:228:0x089c, B:232:0x08ab, B:234:0x08b3, B:235:0x08c0, B:237:0x08bc), top: B:4:0x0031, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x05cc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0176 A[Catch: RuntimeException -> 0x08f0, TryCatch #1 {RuntimeException -> 0x08f0, blocks: (B:5:0x0031, B:12:0x006a, B:14:0x0083, B:17:0x0097, B:307:0x00b0, B:20:0x00b9, B:22:0x00c6, B:26:0x00cf, B:27:0x015e, B:29:0x0176, B:30:0x0189, B:32:0x0191, B:33:0x019a, B:134:0x01e0, B:136:0x01fa, B:139:0x0206, B:141:0x020d, B:142:0x0232, B:144:0x023c, B:146:0x0243, B:147:0x0263, B:150:0x026f, B:152:0x0279, B:154:0x0280, B:155:0x02a0, B:156:0x02ac, B:158:0x02b6, B:160:0x02c0, B:162:0x02c8, B:164:0x02cf, B:165:0x02f7, B:166:0x0309, B:168:0x0310, B:169:0x0338, B:171:0x034a, B:172:0x0354, B:174:0x0355, B:176:0x035c, B:177:0x0386, B:35:0x0394, B:37:0x039b, B:39:0x03b1, B:40:0x03b5, B:45:0x040a, B:42:0x0401, B:52:0x0410, B:54:0x0417, B:55:0x0423, B:56:0x042d, B:57:0x0431, B:58:0x044c, B:62:0x0461, B:59:0x0458, B:68:0x0467, B:74:0x048a, B:77:0x04aa, B:82:0x04b5, B:85:0x04c1, B:86:0x04c5, B:89:0x04cc, B:92:0x051b, B:94:0x0503, B:100:0x0530, B:103:0x0537, B:106:0x054d, B:109:0x0554, B:113:0x0563, B:115:0x056b, B:116:0x0578, B:118:0x0574, B:120:0x0596, B:310:0x00dc, B:311:0x00ea, B:313:0x00f5, B:316:0x010b, B:327:0x0127, B:319:0x0130, B:321:0x013f, B:325:0x0148, B:330:0x0157, B:182:0x05cc, B:190:0x05e1, B:191:0x05f3, B:193:0x05fb, B:194:0x0604, B:245:0x0644, B:247:0x065e, B:250:0x066a, B:252:0x0671, B:253:0x0696, B:255:0x06a0, B:257:0x06a7, B:258:0x06c7, B:261:0x06d3, B:263:0x06dd, B:265:0x06e4, B:266:0x0704, B:267:0x0710, B:269:0x071a, B:271:0x0724, B:273:0x072c, B:275:0x0733, B:276:0x075b, B:277:0x076d, B:279:0x0774, B:280:0x079c, B:282:0x07ae, B:283:0x07b8, B:284:0x07b9, B:286:0x07c0, B:287:0x07ea, B:198:0x07fd, B:201:0x0809, B:202:0x080d, B:209:0x0814, B:212:0x0863, B:214:0x084b, B:219:0x0878, B:222:0x087f, B:225:0x0895, B:228:0x089c, B:232:0x08ab, B:234:0x08b3, B:235:0x08c0, B:237:0x08bc), top: B:4:0x0031, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x05c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:313:0x00f5 A[Catch: RuntimeException -> 0x08f0, TryCatch #1 {RuntimeException -> 0x08f0, blocks: (B:5:0x0031, B:12:0x006a, B:14:0x0083, B:17:0x0097, B:307:0x00b0, B:20:0x00b9, B:22:0x00c6, B:26:0x00cf, B:27:0x015e, B:29:0x0176, B:30:0x0189, B:32:0x0191, B:33:0x019a, B:134:0x01e0, B:136:0x01fa, B:139:0x0206, B:141:0x020d, B:142:0x0232, B:144:0x023c, B:146:0x0243, B:147:0x0263, B:150:0x026f, B:152:0x0279, B:154:0x0280, B:155:0x02a0, B:156:0x02ac, B:158:0x02b6, B:160:0x02c0, B:162:0x02c8, B:164:0x02cf, B:165:0x02f7, B:166:0x0309, B:168:0x0310, B:169:0x0338, B:171:0x034a, B:172:0x0354, B:174:0x0355, B:176:0x035c, B:177:0x0386, B:35:0x0394, B:37:0x039b, B:39:0x03b1, B:40:0x03b5, B:45:0x040a, B:42:0x0401, B:52:0x0410, B:54:0x0417, B:55:0x0423, B:56:0x042d, B:57:0x0431, B:58:0x044c, B:62:0x0461, B:59:0x0458, B:68:0x0467, B:74:0x048a, B:77:0x04aa, B:82:0x04b5, B:85:0x04c1, B:86:0x04c5, B:89:0x04cc, B:92:0x051b, B:94:0x0503, B:100:0x0530, B:103:0x0537, B:106:0x054d, B:109:0x0554, B:113:0x0563, B:115:0x056b, B:116:0x0578, B:118:0x0574, B:120:0x0596, B:310:0x00dc, B:311:0x00ea, B:313:0x00f5, B:316:0x010b, B:327:0x0127, B:319:0x0130, B:321:0x013f, B:325:0x0148, B:330:0x0157, B:182:0x05cc, B:190:0x05e1, B:191:0x05f3, B:193:0x05fb, B:194:0x0604, B:245:0x0644, B:247:0x065e, B:250:0x066a, B:252:0x0671, B:253:0x0696, B:255:0x06a0, B:257:0x06a7, B:258:0x06c7, B:261:0x06d3, B:263:0x06dd, B:265:0x06e4, B:266:0x0704, B:267:0x0710, B:269:0x071a, B:271:0x0724, B:273:0x072c, B:275:0x0733, B:276:0x075b, B:277:0x076d, B:279:0x0774, B:280:0x079c, B:282:0x07ae, B:283:0x07b8, B:284:0x07b9, B:286:0x07c0, B:287:0x07ea, B:198:0x07fd, B:201:0x0809, B:202:0x080d, B:209:0x0814, B:212:0x0863, B:214:0x084b, B:219:0x0878, B:222:0x087f, B:225:0x0895, B:228:0x089c, B:232:0x08ab, B:234:0x08b3, B:235:0x08c0, B:237:0x08bc), top: B:4:0x0031, inners: #0, #2 }] */
    /* JADX WARN: Type inference failed for: r0v109, types: [int] */
    /* JADX WARN: Type inference failed for: r0v112, types: [int] */
    /* JADX WARN: Type inference failed for: r0v118, types: [int] */
    /* JADX WARN: Type inference failed for: r0v122, types: [int] */
    /* JADX WARN: Type inference failed for: r0v124, types: [int] */
    /* JADX WARN: Type inference failed for: r0v235, types: [int] */
    /* JADX WARN: Type inference failed for: r0v238, types: [int] */
    /* JADX WARN: Type inference failed for: r0v240, types: [int] */
    /* JADX WARN: Type inference failed for: r0v246, types: [int] */
    /* JADX WARN: Type inference failed for: r0v250, types: [int] */
    /* JADX WARN: Type inference failed for: r0v252, types: [int] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(char[] r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 2399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.ai.btree.utils.BehaviorTreeReader.parse(char[], int, int):void");
    }

    private static byte[] init__btree_actions_0() {
        return new byte[]{0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 9, 1, 12, 1, 13, 2, 0, 5, 2, 0, 13, 2, 5, 3, 2, 7, 5, 2, 10, 8, 2, 11, 8, 3, 0, 5, 3, 3, 6, 7, 5, 3, 7, 5, 3, 3, 10, 8, 5, 3, 10, 8, 13, 3, 11, 8, 5, 3, 11, 8, 13, 4, 6, 7, 5, 3, 4, 10, 8, 5, 3, 4, 11, 8, 5, 3};
    }

    private static short[] init__btree_key_offsets_0() {
        return new short[]{0, 0, 1, 6, 16, 21, 33, 37, 47, 59, 63, 72, 73, 77, 82, 87, 91, 105, 114, 126, 130, 139, 143, 144, 148, 152, 157, 170, 174, 179, 191, 196, 198, 200, 213, 218, 233, 243, 248, 253, 267};
    }

    private static char[] init__btree_trans_keys_0() {
        return new char[]{'\n', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', '$', ')', '_', 'A', 'Z', 'a', 'z', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ')', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ')', '\t', '\r', ' ', '$', '(', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ':', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ':', '\t', '\n', '\r', ' ', '\"', '#', ':', '(', ')', '\"', '\t', '\r', ' ', ':', '_', 'A', 'Z', 'a', 'z', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ')', '\t', '\r', ' ', '$', ')', '.', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ')', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ':', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ':', '\t', '\n', '\r', ' ', '\"', '#', ':', '(', ')', '\t', '\r', ' ', ')', '\"', '\t', '\r', ' ', ')', '\t', '\r', ' ', ':', '_', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', '$', ')', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\r', ' ', ')', '_', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', '$', '(', '_', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', '\n', '\r', '\n', '\r', '\t', '\n', '\r', ' ', '#', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', '\t', '\n', '\r', ' ', '#', '$', '.', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', '_', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', '\t', '\n', '\r', ' ', '#', '\t', '\n', '\r', ' ', '#', '$', '?', '_', '0', '9', 'A', 'Z', 'a', 'z', '\t', '\n', '\r', ' ', '#', 0};
    }

    private static byte[] init__btree_single_lengths_0() {
        return new byte[]{0, 1, 1, 6, 1, 6, 4, 6, 6, 4, 7, 1, 4, 1, 1, 4, 8, 5, 6, 4, 7, 4, 1, 4, 4, 1, 7, 4, 1, 8, 5, 2, 2, 7, 5, 9, 6, 5, 5, 8, 5};
    }

    private static byte[] init__btree_range_lengths_0() {
        return new byte[]{0, 0, 2, 2, 2, 3, 0, 2, 3, 0, 1, 0, 0, 2, 2, 0, 3, 2, 3, 0, 1, 0, 0, 0, 0, 2, 3, 0, 2, 2, 0, 0, 0, 3, 0, 3, 2, 0, 0, 3, 0};
    }

    private static short[] init__btree_index_offsets_0() {
        return new short[]{0, 0, 2, 6, 15, 19, 29, 34, 43, 53, 58, 67, 69, 74, 78, 82, 87, 99, 107, 117, 122, 131, 136, 138, 143, 148, 152, 163, 168, 172, 183, 189, 192, 195, 206, 212, 225, 234, 240, 246, 258};
    }

    private static byte[] init__btree_indicies_0() {
        return new byte[]{0, 1, 2, 2, 2, 1, 3, 3, 3, 4, 5, 6, 6, 6, 1, 7, 7, 7, 1, 8, 8, 8, 9, 11, 10, 10, 10, 10, 1, 12, 12, 12, 5, 1, 13, 13, 13, 14, 15, 16, 16, 16, 1, 17, 17, 17, 19, 20, 18, 18, 18, 18, 1, 21, 21, 21, 22, 1, 22, 1, 22, 22, 24, 1, 1, 1, 23, 25, 1, 17, 17, 17, 19, 1, 26, 26, 26, 1, 27, 27, 27, 1, 8, 8, 8, 9, 1, 28, 28, 28, 29, 30, 31, 33, 32, 32, 32, 32, 1, 34, 34, 34, 5, 35, 35, 35, 1, 36, 36, 36, 38, 39, 37, 37, 37, 37, 1, 40, 40, 40, 41, 1, 41, 1, 41, 41, 43, 1, 1, 1, 42, 44, 44, 44, 45, 1, 46, 1, 34, 34, 34, 5, 1, 36, 36, 36, 38, 1, 47, 47, 47, 1, 28, 28, 28, 29, 30, 33, 47, 47, 47, 47, 1, 28, 28, 28, 30, 1, 32, 32, 32, 1, 48, 49, 50, 48, 51, 14, 15, 16, 16, 16, 1, 50, 49, 50, 50, 51, 1, 53, 54, 52, 56, 57, 55, 58, 59, 58, 58, 60, 62, 61, 61, 61, 61, 1, 58, 59, 58, 58, 60, 1, 63, 64, 63, 63, 65, 66, 67, 68, 27, 27, 27, 27, 1, 69, 49, 69, 69, 51, 70, 70, 70, 1, 71, 72, 71, 71, 73, 1, 69, 49, 69, 69, 51, 1, 63, 64, 63, 63, 65, 66, 68, 26, 26, 26, 26, 1, 63, 64, 63, 63, 65, 1, 0};
    }

    private static byte[] init__btree_trans_targs_0() {
        return new byte[]{29, 0, 33, 3, 4, 7, 16, 5, 6, 7, 5, 15, 6, 7, 2, 3, 35, 9, 8, 10, 12, 9, 10, 37, 11, 38, 39, 35, 17, 25, 7, 28, 16, 27, 17, 18, 19, 18, 20, 24, 19, 20, 21, 22, 17, 7, 23, 26, 29, 29, 30, 31, 32, 29, 1, 32, 29, 1, 30, 29, 31, 33, 34, 36, 29, 31, 13, 14, 40, 36, 8, 36, 29, 31};
    }

    private static byte[] init__btree_trans_actions_0() {
        return new byte[]{7, 0, 13, 0, 0, 19, 13, 13, 36, 63, 0, 0, 0, 0, 0, 17, 13, 15, 0, 15, 0, 0, 0, 3, 5, 1, 0, 0, 33, 0, 55, 0, 0, 0, 0, 13, 15, 0, 15, 0, 0, 0, 3, 5, 1, 24, 1, 0, 9, 27, 0, 0, 13, 67, 43, 0, 47, 30, 36, 77, 36, 0, 0, 33, 72, 33, 0, 0, 0, 0, 13, 1, 39, 1};
    }

    private static byte[] init__btree_eof_actions_0() {
        return new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 11, 43, 30, 59, 59, 51, 11, 21, 11, 51, 51};
    }

    private static boolean containsFloatingPointCharacters(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            switch (str.charAt(i)) {
                case '.':
                case 'E':
                case 'e':
                    return true;
                default:
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0064. Please report as an issue. */
    private static String unescape(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 16);
        int i = 0;
        while (i < length) {
            int i2 = i;
            i++;
            char charAt = str.charAt(i2);
            if (charAt != '\\') {
                sb.append(charAt);
            } else if (i != length) {
                i++;
                char charAt2 = str.charAt(i);
                char c = charAt2;
                if (charAt2 == 'u') {
                    sb.append(Character.toChars(Integer.parseInt(str.substring(i, i + 4), 16)));
                    i += 4;
                } else {
                    switch (c) {
                        case '\"':
                        case '/':
                        case '\\':
                            sb.append(c);
                            break;
                        case 'b':
                            c = '\b';
                            sb.append(c);
                            break;
                        case 'f':
                            c = '\f';
                            sb.append(c);
                            break;
                        case 'n':
                            c = '\n';
                            sb.append(c);
                            break;
                        case 'r':
                            c = '\r';
                            sb.append(c);
                            break;
                        case 't':
                            c = '\t';
                            sb.append(c);
                            break;
                        default:
                            throw new SerializationException("Illegal escaped character: \\" + c);
                    }
                }
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }
}

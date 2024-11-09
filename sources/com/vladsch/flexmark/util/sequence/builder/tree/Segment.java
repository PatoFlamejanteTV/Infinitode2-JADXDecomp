package com.vladsch.flexmark.util.sequence.builder.tree;

import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.Seg;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment.class */
public abstract class Segment {
    static final int TYPE_MASK = 224;
    static final int TYPE_NO_SIZE_BYTES = 16;
    static final int TYPE_START_BYTES = 3;
    static final int TYPE_LENGTH_BYTES = 12;
    static final int TYPE_ANCHOR = 0;
    static final int TYPE_BASE = 32;
    static final int TYPE_TEXT = 64;
    static final int TYPE_REPEATED_TEXT = 96;
    static final int TYPE_TEXT_ASCII = 128;
    static final int TYPE_REPEATED_ASCII = 160;
    static final int TYPE_REPEATED_SPACE = 192;
    static final int TYPE_REPEATED_EOL = 224;
    static final int TYPE_HAS_OFFSET = 256;
    static final int TYPE_HAS_LENGTH = 512;
    static final int TYPE_HAS_BOTH = 768;
    static final int TYPE_HAS_CHAR = 1024;
    static final int TYPE_HAS_CHARS = 2048;
    static final int TYPE_HAS_BYTE = 4096;
    static final int TYPE_HAS_BYTES = 8192;
    protected final int pos;
    protected final byte[] bytes;
    protected final int byteOffset;
    protected final int startIndex;
    static final /* synthetic */ boolean $assertionsDisabled;

    public abstract int length();

    public abstract boolean isBase();

    public abstract boolean isAnchor();

    public abstract boolean isText();

    public abstract boolean isFirst256Start();

    public abstract boolean isRepeatedTextEnd();

    public abstract int getStartOffset();

    public abstract int getEndOffset();

    public abstract CharSequence getCharSequence();

    public abstract char charAt(int i);

    static {
        $assertionsDisabled = !Segment.class.desiredAssertionStatus();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$SegType.class */
    public enum SegType {
        ANCHOR(256),
        BASE(User32.WM_DWMCOLORIZATIONCOLORCHANGED),
        TEXT(2624),
        REPEATED_TEXT(1632),
        TEXT_ASCII(8832),
        REPEATED_ASCII(4768),
        REPEATED_SPACE(User32.WM_TABLET_FIRST),
        REPEATED_EOL(736);

        public final int flags;

        SegType(int i) {
            this.flags = i;
        }

        public final boolean hasAll(int i) {
            return (this.flags & i) == i;
        }

        public final boolean hasLength() {
            return hasAll(512);
        }

        public final boolean hasOffset() {
            return hasAll(256);
        }

        public final boolean hasBoth() {
            return hasAll(768);
        }

        public final boolean hasChar() {
            return hasAll(1024);
        }

        public final boolean hasChars() {
            return hasAll(2048);
        }

        public final boolean hasByte() {
            return hasAll(4096);
        }

        public final boolean hasBytes() {
            return hasAll(8192);
        }

        public static SegType fromTypeMask(int i) {
            switch (i & CGL.kCGLCPDispatchTableSize) {
                case 0:
                    return ANCHOR;
                case 32:
                    return BASE;
                case 64:
                    return TEXT;
                case 96:
                    return REPEATED_TEXT;
                case 128:
                    return TEXT_ASCII;
                case 160:
                    return REPEATED_ASCII;
                case 192:
                    return REPEATED_SPACE;
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                    return REPEATED_EOL;
                default:
                    throw new IllegalStateException(String.format("Invalid text type %02x", Integer.valueOf(i)));
            }
        }
    }

    public boolean hasAll(int i, int i2) {
        return (i & i2) == i2;
    }

    public Segment(int i, byte[] bArr, int i2, int i3) {
        this.pos = i;
        this.bytes = bArr;
        this.byteOffset = i2;
        this.startIndex = i3;
    }

    public int getPos() {
        return this.pos;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public final int getByteOffset() {
        return this.byteOffset;
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final int getEndIndex() {
        return this.startIndex + length();
    }

    public boolean notInSegment(int i) {
        return i < this.startIndex || i >= this.startIndex + length();
    }

    public boolean offsetNotInSegment(int i) {
        return i < getStartOffset() || i >= getEndOffset();
    }

    public final SegType getType() {
        return SegType.fromTypeMask(this.bytes[this.byteOffset]);
    }

    public final int getByteLength() {
        return getSegByteLength(getType(), getStartOffset(), length());
    }

    public String toString() {
        if (isBase()) {
            if (isAnchor()) {
                return "[" + getStartOffset() + ")";
            }
            return "[" + getStartOffset() + ", " + getEndOffset() + ")";
        }
        CharSequence charSequence = getCharSequence();
        if (isRepeatedTextEnd() && length() > 1) {
            if (isFirst256Start()) {
                return "a:" + length() + "x'" + Utils.escapeJavaString(charSequence.subSequence(0, 1)) + "'";
            }
            return length() + "x'" + Utils.escapeJavaString(charSequence.subSequence(0, 1)) + "'";
        }
        int length = charSequence.length();
        String charSequence2 = length <= 20 ? charSequence.toString() : charSequence.subSequence(0, 10).toString() + "…" + charSequence.subSequence(length - 10, length).toString();
        if (isFirst256Start()) {
            return "a:'" + Utils.escapeJavaString(charSequence2) + "'";
        }
        return "'" + Utils.escapeJavaString(charSequence2) + "'";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$Base.class */
    public static class Base extends Segment {
        protected final int startOffset;
        protected final int endOffset;
        protected final BasedSequence baseSeq;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !Segment.class.desiredAssertionStatus();
        }

        public Base(int i, byte[] bArr, int i2, int i3, BasedSequence basedSequence) {
            super(i, bArr, i2, i3);
            this.baseSeq = basedSequence;
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            if ((i5 & CGL.kCGLCPDispatchTableSize) == 0) {
                if (hasAll(i5, 16)) {
                    int i6 = i5 & 15;
                    this.startOffset = i6;
                    this.endOffset = i6;
                    return;
                } else {
                    int i7 = getInt(bArr, i4, (i5 & 3) + 1);
                    this.startOffset = i7;
                    this.endOffset = i7;
                    return;
                }
            }
            if (!$assertionsDisabled && hasAll(i5, 16)) {
                throw new AssertionError();
            }
            int i8 = i5 & 3;
            this.startOffset = getInt(bArr, i4, i8 + 1);
            this.endOffset = this.startOffset + getInt(bArr, i4 + i8 + 1, ((i5 & 12) >> 2) + 1);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int length() {
            return this.endOffset - this.startOffset;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isBase() {
            return true;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isAnchor() {
            return this.startOffset == this.endOffset;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isText() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isFirst256Start() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isRepeatedTextEnd() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int getStartOffset() {
            return this.startOffset;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int getEndOffset() {
            return this.endOffset;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public char charAt(int i) {
            if (i < this.startIndex || i - this.startIndex >= length()) {
                throw new IndexOutOfBoundsException("index " + i + " out of bounds [" + this.startIndex + ", " + this.startIndex + length() + ")");
            }
            return this.baseSeq.charAt((this.startOffset + i) - this.startIndex);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public CharSequence getCharSequence() {
            return this.baseSeq.subSequence(this.startOffset, this.endOffset);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$TextCharSequenceBase.class */
    static abstract class TextCharSequenceBase implements CharSequence {
        protected final byte[] bytes;
        protected final int byteOffset;
        protected final int startOffset;
        protected final int length;

        @Override // java.lang.CharSequence
        public abstract char charAt(int i);

        abstract CharSequence create(int i, int i2);

        public TextCharSequenceBase(byte[] bArr, int i, int i2, int i3) {
            this.bytes = bArr;
            this.byteOffset = i;
            this.startOffset = i2;
            this.length = i3;
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.length;
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            if (i < 0 || i > i2 || i2 > this.length) {
                throw new IndexOutOfBoundsException("Invalid index range [" + i + ", " + i2 + "] out of bounds [0, " + length() + ")");
            }
            return create(this.startOffset + i, i2 - i);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.length; i++) {
                sb.append(charAt(i));
            }
            return sb.toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$TextCharSequence.class */
    static class TextCharSequence extends TextCharSequenceBase {
        public TextCharSequence(byte[] bArr, int i, int i2, int i3) {
            super(bArr, i, i2, i3);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment.TextCharSequenceBase, java.lang.CharSequence
        public char charAt(int i) {
            if (i < 0 || i >= this.length) {
                throw new IndexOutOfBoundsException("index " + i + " out of bounds [0, " + this.length + ")");
            }
            return Segment.getChar(this.bytes, this.byteOffset + ((this.startOffset + i) << 1));
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment.TextCharSequenceBase
        CharSequence create(int i, int i2) {
            return new TextCharSequence(this.bytes, this.byteOffset, i, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$TextAsciiCharSequence.class */
    static class TextAsciiCharSequence extends TextCharSequenceBase {
        public TextAsciiCharSequence(byte[] bArr, int i, int i2, int i3) {
            super(bArr, i, i2, i3);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment.TextCharSequenceBase, java.lang.CharSequence
        public char charAt(int i) {
            if (i < 0 || i >= this.length) {
                throw new IndexOutOfBoundsException("index " + i + " out of bounds [0, " + this.length + ")");
            }
            return (char) (255 & this.bytes[this.byteOffset + this.startOffset + i]);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment.TextCharSequenceBase
        CharSequence create(int i, int i2) {
            return new TextAsciiCharSequence(this.bytes, this.byteOffset, i, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$TextRepeatedSequence.class */
    static class TextRepeatedSequence implements CharSequence {
        protected final char c;
        protected final int length;

        public TextRepeatedSequence(char c, int i) {
            this.c = c;
            this.length = i;
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.length;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            if (i < 0 || i >= this.length) {
                throw new IndexOutOfBoundsException("index " + i + " out of bounds [0, " + this.length + ")");
            }
            return this.c;
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            if (i < 0 || i > i2 || i2 > this.length) {
                throw new IndexOutOfBoundsException("Invalid index range [" + i + ", " + i2 + "] out of bounds [0, " + length() + ")");
            }
            return new TextRepeatedSequence(this.c, i2 - i);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.length; i++) {
                sb.append(this.c);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/tree/Segment$Text.class */
    public static class Text extends Segment {
        protected final CharSequence chars;

        public Text(int i, byte[] bArr, int i2, int i3) {
            super(i, bArr, i2, i3);
            int i4;
            int i5 = i2 + 1;
            int i6 = bArr[i2] & 255;
            int i7 = i6 & CGL.kCGLCPDispatchTableSize;
            if (hasAll(i6, 16)) {
                i4 = i6 & 15;
            } else {
                int i8 = (i6 & 12) >> 2;
                i4 = getInt(bArr, i5, i8 + 1);
                i5 += i8 + 1;
            }
            switch (i7) {
                case 64:
                    this.chars = new TextCharSequence(bArr, i5, 0, i4);
                    return;
                case 96:
                    this.chars = new TextRepeatedSequence(getChar(bArr, i5), i4);
                    return;
                case 128:
                    this.chars = new TextAsciiCharSequence(bArr, i5, 0, i4);
                    return;
                case 160:
                    this.chars = new TextRepeatedSequence((char) (255 & bArr[i5]), i4);
                    return;
                case 192:
                    this.chars = new TextRepeatedSequence(' ', i4);
                    return;
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                    this.chars = new TextRepeatedSequence('\n', i4);
                    return;
                default:
                    throw new IllegalStateException("Invalid text type " + i7);
            }
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int length() {
            return this.chars.length();
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public char charAt(int i) {
            if (i < this.startIndex || i - this.startIndex >= this.chars.length()) {
                throw new IndexOutOfBoundsException("index " + i + " out of bounds [" + this.startIndex + ", " + this.startIndex + this.chars.length() + ")");
            }
            return this.chars.charAt(i - this.startIndex);
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isBase() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isAnchor() {
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isText() {
            return true;
        }

        int textType() {
            return this.bytes[this.byteOffset] & 224;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isFirst256Start() {
            int textType = textType();
            return textType == 128 || textType == 160 || textType == 192 || textType == 224;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public boolean isRepeatedTextEnd() {
            int textType = textType();
            return textType == 96 || textType == 160 || textType == 192 || textType == 224;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int getStartOffset() {
            return -1;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public int getEndOffset() {
            return -1;
        }

        @Override // com.vladsch.flexmark.util.sequence.builder.tree.Segment
        public CharSequence getCharSequence() {
            return this.chars;
        }
    }

    public static Segment getSegment(byte[] bArr, int i, int i2, int i3, BasedSequence basedSequence) {
        int i4 = bArr[i] & 224;
        switch (i4) {
            case 0:
            case 32:
                return new Base(i2, bArr, i, i3, basedSequence);
            case 64:
            case 96:
            case 128:
            case 160:
            case 192:
            case CGL.kCGLCPDispatchTableSize /* 224 */:
                return new Text(i2, bArr, i, i3);
            default:
                throw new IllegalStateException("Invalid text type " + i4);
        }
    }

    public static SegType getSegType(Seg seg, CharSequence charSequence) {
        if (seg.isBase()) {
            return seg.isAnchor() ? SegType.ANCHOR : SegType.BASE;
        }
        if (seg.isText()) {
            boolean isFirst256Start = seg.isFirst256Start();
            boolean isRepeatedTextEnd = seg.isRepeatedTextEnd();
            if (!isFirst256Start) {
                return isRepeatedTextEnd ? SegType.REPEATED_TEXT : SegType.TEXT;
            }
            if (isRepeatedTextEnd) {
                char charAt = charSequence.charAt(seg.getTextStart());
                return charAt == ' ' ? SegType.REPEATED_SPACE : charAt == '\n' ? SegType.REPEATED_EOL : SegType.REPEATED_ASCII;
            }
            return SegType.TEXT_ASCII;
        }
        throw new IllegalStateException("Unknown seg type " + seg);
    }

    public static int getOffsetBytes(int i) {
        if (i < 16) {
            return 0;
        }
        if (i < 256) {
            return 1;
        }
        if (i < 65536) {
            return 2;
        }
        return i < 16777216 ? 3 : 4;
    }

    public static int getLengthBytes(int i) {
        if (i < 16) {
            return 0;
        }
        if (i < 256) {
            return 1;
        }
        if (i < 65536) {
            return 2;
        }
        return i < 16777216 ? 3 : 4;
    }

    public static int getIntBytes(int i) {
        if (i < 256) {
            return 1;
        }
        if (i < 65536) {
            return 2;
        }
        return i < 16777216 ? 3 : 4;
    }

    public static int getSegByteLength(SegType segType, int i, int i2) {
        int i3 = 1;
        if (segType.hasBoth()) {
            i3 = 1 + getIntBytes(i) + getIntBytes(i2);
        } else if (segType.hasOffset()) {
            i3 = 1 + getOffsetBytes(i);
        } else if (segType.hasLength()) {
            i3 = 1 + getLengthBytes(i2);
        }
        if (segType.hasChar()) {
            i3 += 2;
        } else if (segType.hasChars()) {
            i3 += 2 * i2;
        } else if (segType.hasByte()) {
            i3++;
        } else if (segType.hasBytes()) {
            i3 += i2;
        }
        return i3;
    }

    public static int getSegByteLength(Seg seg, CharSequence charSequence) {
        return getSegByteLength(getSegType(seg, charSequence), seg.getSegStart(), seg.length());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int addIntBytes(byte[] bArr, int i, int i2, int i3) {
        switch (i3) {
            case 1:
                int i4 = i;
                i++;
                bArr[i4] = (byte) i2;
                break;
            case 2:
                int i5 = i;
                i++;
                bArr[i5] = (byte) ((i2 >> 8) & 255);
                int i42 = i;
                i++;
                bArr[i42] = (byte) i2;
                break;
            case 3:
                int i6 = i;
                i++;
                bArr[i6] = (byte) ((i2 >> 16) & 255);
                int i52 = i;
                i++;
                bArr[i52] = (byte) ((i2 >> 8) & 255);
                int i422 = i;
                i++;
                bArr[i422] = (byte) i2;
                break;
            case 4:
                i++;
                bArr[i] = (byte) (i2 >> 24);
                int i62 = i;
                i++;
                bArr[i62] = (byte) ((i2 >> 16) & 255);
                int i522 = i;
                i++;
                bArr[i522] = (byte) ((i2 >> 8) & 255);
                int i4222 = i;
                i++;
                bArr[i4222] = (byte) i2;
                break;
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getInt(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (i2) {
            case 1:
                i3 |= 255 & bArr[i];
                break;
            case 2:
                int i4 = i;
                i++;
                i3 |= (255 & bArr[i4]) << 8;
                i3 |= 255 & bArr[i];
                break;
            case 3:
                int i5 = i;
                i++;
                i3 |= (255 & bArr[i5]) << 16;
                int i42 = i;
                i++;
                i3 |= (255 & bArr[i42]) << 8;
                i3 |= 255 & bArr[i];
                break;
            case 4:
                i++;
                i3 = 0 | ((255 & bArr[i]) << 24);
                int i52 = i;
                i++;
                i3 |= (255 & bArr[i52]) << 16;
                int i422 = i;
                i++;
                i3 |= (255 & bArr[i422]) << 8;
                i3 |= 255 & bArr[i];
                break;
        }
        return i3;
    }

    public static int addChar(byte[] bArr, int i, char c) {
        int i2 = i + 1;
        bArr[i] = (byte) ((c >> '\b') & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) c;
        return i3;
    }

    public static char getChar(byte[] bArr, int i) {
        return (char) (((char) ((255 & bArr[i]) << 8)) | (255 & bArr[i + 1]));
    }

    public static int addChars(byte[] bArr, int i, CharSequence charSequence, int i2, int i3) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = charSequence.charAt(i4);
            int i5 = i;
            int i6 = i + 1;
            bArr[i5] = (byte) ((charAt >> '\b') & 255);
            i = i6 + 1;
            bArr[i6] = (byte) charAt;
        }
        return i;
    }

    public static int addCharAscii(byte[] bArr, int i, char c) {
        if (!$assertionsDisabled && c >= 256) {
            throw new AssertionError();
        }
        int i2 = i + 1;
        bArr[i] = (byte) c;
        return i2;
    }

    public static int addCharsAscii(byte[] bArr, int i, CharSequence charSequence, int i2, int i3) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = charSequence.charAt(i4);
            if (!$assertionsDisabled && charAt >= 256) {
                throw new AssertionError();
            }
            int i5 = i;
            i++;
            bArr[i5] = (byte) charAt;
        }
        return i;
    }

    public static char getCharAscii(byte[] bArr, int i) {
        return (char) (255 & bArr[i]);
    }

    public static int addSegBytes(byte[] bArr, int i, Seg seg, CharSequence charSequence) {
        SegType segType = getSegType(seg, charSequence);
        int length = seg.length();
        if (segType.hasOffset()) {
            int start = seg.getStart();
            if (segType.hasLength()) {
                int intBytes = getIntBytes(start);
                int intBytes2 = getIntBytes(length);
                bArr[i] = (byte) (segType.flags | (intBytes - 1) | ((intBytes2 - 1) << 2));
                i = addIntBytes(bArr, addIntBytes(bArr, i + 1, start, intBytes), length, intBytes2);
            } else {
                int offsetBytes = getOffsetBytes(start);
                if (offsetBytes == 0) {
                    if (!$assertionsDisabled && start >= 16) {
                        throw new AssertionError();
                    }
                    i++;
                    bArr[i] = (byte) (segType.flags | 16 | start);
                } else {
                    bArr[i] = (byte) (segType.flags | (offsetBytes - 1));
                    i = addIntBytes(bArr, i + 1, start, offsetBytes);
                }
            }
        } else if (segType.hasLength()) {
            int lengthBytes = getLengthBytes(length);
            if (lengthBytes == 0) {
                if (!$assertionsDisabled && length >= 16) {
                    throw new AssertionError();
                }
                i++;
                bArr[i] = (byte) (segType.flags | 16 | length);
            } else {
                bArr[i] = (byte) (segType.flags | ((lengthBytes - 1) << 2));
                i = addIntBytes(bArr, i + 1, length, lengthBytes);
            }
        }
        if (segType.hasChar()) {
            i = addChar(bArr, i, charSequence.charAt(seg.getTextStart()));
        } else if (segType.hasChars()) {
            i = addChars(bArr, i, charSequence, seg.getTextStart(), seg.getTextEnd());
        } else if (segType.hasByte()) {
            i = addCharAscii(bArr, i, charSequence.charAt(seg.getTextStart()));
        } else if (segType.hasBytes()) {
            i = addCharsAscii(bArr, i, charSequence, seg.getTextStart(), seg.getTextEnd());
        }
        return i;
    }
}

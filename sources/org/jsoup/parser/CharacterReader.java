package org.jsoup.parser;

import com.badlogic.gdx.net.HttpStatus;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

/* loaded from: infinitode-2.jar:org/jsoup/parser/CharacterReader.class */
public final class CharacterReader {
    static final char EOF = 65535;
    private static final int maxStringCacheLen = 12;
    static final int maxBufferLen = 32768;
    static final int readAheadLimit = 24576;
    private static final int minReadAheadLen = 1024;
    private char[] charBuf;
    private Reader reader;
    private int bufLength;
    private int bufSplitPoint;
    private int bufPos;
    private int readerPos;
    private int bufMark;
    private static final int stringCacheSize = 512;
    private String[] stringCache;
    private ArrayList<Integer> newlinePositions;
    private int lineNumberOffset;
    private boolean readFully;
    private String lastIcSeq;
    private int lastIcIndex;

    public CharacterReader(Reader reader, int i) {
        this.bufMark = -1;
        this.stringCache = new String[512];
        this.newlinePositions = null;
        this.lineNumberOffset = 1;
        Validate.notNull(reader);
        Validate.isTrue(reader.markSupported());
        this.reader = reader;
        this.charBuf = new char[Math.min(i, 32768)];
        bufferUp();
    }

    public CharacterReader(Reader reader) {
        this(reader, 32768);
    }

    public CharacterReader(String str) {
        this(new StringReader(str), str.length());
    }

    public final void close() {
        if (this.reader == null) {
            return;
        }
        try {
            this.reader.close();
        } catch (IOException unused) {
        } finally {
            this.reader = null;
            this.charBuf = null;
            this.stringCache = null;
        }
    }

    private void bufferUp() {
        int i;
        int i2;
        if (this.readFully || this.bufPos < this.bufSplitPoint) {
            return;
        }
        if (this.bufMark != -1) {
            i = this.bufMark;
            i2 = this.bufPos - this.bufMark;
        } else {
            i = this.bufPos;
            i2 = 0;
        }
        try {
            long skip = this.reader.skip(i);
            this.reader.mark(32768);
            int i3 = 0;
            while (i3 <= 1024) {
                int read = this.reader.read(this.charBuf, i3, this.charBuf.length - i3);
                if (read == -1) {
                    this.readFully = true;
                }
                if (read <= 0) {
                    break;
                } else {
                    i3 += read;
                }
            }
            this.reader.reset();
            if (i3 > 0) {
                Validate.isTrue(skip == ((long) i));
                this.bufLength = i3;
                this.readerPos += i;
                this.bufPos = i2;
                if (this.bufMark != -1) {
                    this.bufMark = 0;
                }
                this.bufSplitPoint = Math.min(this.bufLength, 24576);
            }
            scanBufferForNewlines();
            this.lastIcSeq = null;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public final int pos() {
        return this.readerPos + this.bufPos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean readFully() {
        return this.readFully;
    }

    public final void trackNewlines(boolean z) {
        if (z && this.newlinePositions == null) {
            this.newlinePositions = new ArrayList<>(HttpStatus.SC_CONFLICT);
            scanBufferForNewlines();
        } else if (!z) {
            this.newlinePositions = null;
        }
    }

    public final boolean isTrackNewlines() {
        return this.newlinePositions != null;
    }

    public final int lineNumber() {
        return lineNumber(pos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int lineNumber(int i) {
        if (!isTrackNewlines()) {
            return 1;
        }
        int lineNumIndex = lineNumIndex(i);
        if (lineNumIndex == -1) {
            return this.lineNumberOffset;
        }
        return lineNumIndex + this.lineNumberOffset + 1;
    }

    public final int columnNumber() {
        return columnNumber(pos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int columnNumber(int i) {
        if (!isTrackNewlines()) {
            return i + 1;
        }
        int lineNumIndex = lineNumIndex(i);
        if (lineNumIndex == -1) {
            return i + 1;
        }
        return (i - this.newlinePositions.get(lineNumIndex).intValue()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String posLineCol() {
        return lineNumber() + ":" + columnNumber();
    }

    private int lineNumIndex(int i) {
        if (!isTrackNewlines()) {
            return 0;
        }
        int binarySearch = Collections.binarySearch(this.newlinePositions, Integer.valueOf(i));
        int i2 = binarySearch;
        if (binarySearch < -1) {
            i2 = Math.abs(i2) - 2;
        }
        return i2;
    }

    private void scanBufferForNewlines() {
        if (!isTrackNewlines()) {
            return;
        }
        if (this.newlinePositions.size() > 0) {
            int lineNumIndex = lineNumIndex(this.readerPos);
            int i = lineNumIndex;
            if (lineNumIndex == -1) {
                i = 0;
            }
            int intValue = this.newlinePositions.get(i).intValue();
            this.lineNumberOffset += i;
            this.newlinePositions.clear();
            this.newlinePositions.add(Integer.valueOf(intValue));
        }
        for (int i2 = this.bufPos; i2 < this.bufLength; i2++) {
            if (this.charBuf[i2] == '\n') {
                this.newlinePositions.add(Integer.valueOf(1 + this.readerPos + i2));
            }
        }
    }

    public final boolean isEmpty() {
        bufferUp();
        return this.bufPos >= this.bufLength;
    }

    private boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    public final char current() {
        bufferUp();
        if (isEmptyNoBufferUp()) {
            return (char) 65535;
        }
        return this.charBuf[this.bufPos];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char consume() {
        bufferUp();
        char c = isEmptyNoBufferUp() ? (char) 65535 : this.charBuf[this.bufPos];
        this.bufPos++;
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void unconsume() {
        if (this.bufPos <= 0) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        this.bufPos--;
    }

    public final void advance() {
        this.bufPos++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void mark() {
        if (this.bufLength - this.bufPos < 1024) {
            this.bufSplitPoint = 0;
        }
        bufferUp();
        this.bufMark = this.bufPos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void unmark() {
        this.bufMark = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void rewindToMark() {
        if (this.bufMark == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = this.bufMark;
        unmark();
    }

    final int nextIndexOf(char c) {
        bufferUp();
        for (int i = this.bufPos; i < this.bufLength; i++) {
            if (c == this.charBuf[i]) {
                return i - this.bufPos;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:            r0 = r7 + 1;        r8 = r0;        r0 = (r0 + r5.length()) - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004e, code lost:            if (r7 >= r4.bufLength) goto L30;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:            if (r0 > r4.bufLength) goto L31;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:            r10 = 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0061, code lost:            if (r8 >= r0) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0073, code lost:            if (r5.charAt(r10) != r4.charBuf[r8]) goto L37;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0076, code lost:            r8 = r8 + 1;        r10 = r10 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0083, code lost:            if (r8 != r0) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008c, code lost:            return r7 - r4.bufPos;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008d, code lost:            r7 = r7 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008d, code lost:            continue;     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0020, code lost:            if (r0 != r4.charBuf[r7]) goto L7;     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0023, code lost:            r7 = r7 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002b, code lost:            if (r7 >= r4.bufLength) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:            if (r0 != r4.charBuf[r7]) goto L35;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int nextIndexOf(java.lang.CharSequence r5) {
        /*
            r4 = this;
            r0 = r4
            r0.bufferUp()
            r0 = r5
            r1 = 0
            char r0 = r0.charAt(r1)
            r6 = r0
            r0 = r4
            int r0 = r0.bufPos
            r7 = r0
        L11:
            r0 = r7
            r1 = r4
            int r1 = r1.bufLength
            if (r0 >= r1) goto L93
            r0 = r6
            r1 = r4
            char[] r1 = r1.charBuf
            r2 = r7
            char r1 = r1[r2]
            if (r0 == r1) goto L38
        L23:
            int r7 = r7 + 1
            r0 = r7
            r1 = r4
            int r1 = r1.bufLength
            if (r0 >= r1) goto L38
            r0 = r6
            r1 = r4
            char[] r1 = r1.charBuf
            r2 = r7
            char r1 = r1[r2]
            if (r0 != r1) goto L23
        L38:
            r0 = r7
            r1 = 1
            int r0 = r0 + r1
            r1 = r0
            r8 = r1
            r1 = r5
            int r1 = r1.length()
            int r0 = r0 + r1
            r1 = 1
            int r0 = r0 - r1
            r9 = r0
            r0 = r7
            r1 = r4
            int r1 = r1.bufLength
            if (r0 >= r1) goto L8d
            r0 = r9
            r1 = r4
            int r1 = r1.bufLength
            if (r0 > r1) goto L8d
            r0 = 1
            r10 = r0
        L5d:
            r0 = r8
            r1 = r9
            if (r0 >= r1) goto L7f
            r0 = r5
            r1 = r10
            char r0 = r0.charAt(r1)
            r1 = r4
            char[] r1 = r1.charBuf
            r2 = r8
            char r1 = r1[r2]
            if (r0 != r1) goto L7f
            int r8 = r8 + 1
            int r10 = r10 + 1
            goto L5d
        L7f:
            r0 = r8
            r1 = r9
            if (r0 != r1) goto L8d
            r0 = r7
            r1 = r4
            int r1 = r1.bufPos
            int r0 = r0 - r1
            return r0
        L8d:
            int r7 = r7 + 1
            goto L11
        L93:
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.nextIndexOf(java.lang.CharSequence):int");
    }

    public final String consumeTo(char c) {
        int nextIndexOf = nextIndexOf(c);
        if (nextIndexOf != -1) {
            String cacheString = cacheString(this.charBuf, this.stringCache, this.bufPos, nextIndexOf);
            this.bufPos += nextIndexOf;
            return cacheString;
        }
        return consumeToEnd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeTo(String str) {
        int nextIndexOf = nextIndexOf(str);
        if (nextIndexOf != -1) {
            String cacheString = cacheString(this.charBuf, this.stringCache, this.bufPos, nextIndexOf);
            this.bufPos += nextIndexOf;
            return cacheString;
        }
        if (this.bufLength - this.bufPos < str.length()) {
            return consumeToEnd();
        }
        int length = (this.bufLength - str.length()) + 1;
        char[] cArr = this.charBuf;
        String[] strArr = this.stringCache;
        int i = this.bufPos;
        String cacheString2 = cacheString(cArr, strArr, i, length - i);
        this.bufPos = length;
        return cacheString2;
    }

    public final String consumeToAny(char... cArr) {
        bufferUp();
        int i = this.bufPos;
        int i2 = i;
        int i3 = this.bufLength;
        char[] cArr2 = this.charBuf;
        loop0: while (i2 < i3) {
            for (char c : cArr) {
                if (cArr2[i2] == c) {
                    break loop0;
                }
            }
            i2++;
        }
        this.bufPos = i2;
        return i2 > i ? cacheString(this.charBuf, this.stringCache, i, i2 - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeToAnySorted(char... cArr) {
        bufferUp();
        int i = this.bufPos;
        int i2 = i;
        int i3 = this.bufLength;
        char[] cArr2 = this.charBuf;
        while (i2 < i3 && Arrays.binarySearch(cArr, cArr2[i2]) < 0) {
            i2++;
        }
        this.bufPos = i2;
        return this.bufPos > i ? cacheString(this.charBuf, this.stringCache, i, i2 - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String consumeData() {
        /*
            r6 = this;
            r0 = r6
            int r0 = r0.bufPos
            r1 = r0
            r7 = r1
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L12:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L45
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L3c;
                case 38: goto L3c;
                case 60: goto L3c;
                default: goto L3f;
            }
        L3c:
            goto L45
        L3f:
            int r7 = r7 + 1
            goto L12
        L45:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L5f
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            return r0
        L5f:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeData():java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeAttributeQuoted(boolean z) {
        int i = this.bufPos;
        int i2 = i;
        int i3 = this.bufLength;
        char[] cArr = this.charBuf;
        while (i2 < i3) {
            switch (cArr[i2]) {
                case 0:
                case '&':
                    break;
                case '\"':
                    if (!z) {
                        break;
                    } else {
                        break;
                    }
                case '\'':
                    if (!z) {
                        break;
                    } else {
                        break;
                    }
            }
            i2++;
        }
        this.bufPos = i2;
        return i2 > i ? cacheString(this.charBuf, this.stringCache, i, i2 - i) : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String consumeRawData() {
        /*
            r6 = this;
            r0 = r6
            int r0 = r0.bufPos
            r1 = r0
            r7 = r1
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L12:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L3d
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 0: goto L34;
                case 60: goto L34;
                default: goto L37;
            }
        L34:
            goto L3d
        L37:
            int r7 = r7 + 1
            goto L12
        L3d:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L57
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            return r0
        L57:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeRawData():java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String consumeTagName() {
        /*
            r6 = this;
            r0 = r6
            r0.bufferUp()
            r0 = r6
            int r0 = r0.bufPos
            r1 = r0
            r7 = r1
            r8 = r0
            r0 = r6
            int r0 = r0.bufLength
            r9 = r0
            r0 = r6
            char[] r0 = r0.charBuf
            r10 = r0
        L16:
            r0 = r7
            r1 = r9
            if (r0 >= r1) goto L71
            r0 = r10
            r1 = r7
            char r0 = r0[r1]
            switch(r0) {
                case 9: goto L68;
                case 10: goto L68;
                case 12: goto L68;
                case 13: goto L68;
                case 32: goto L68;
                case 47: goto L68;
                case 60: goto L68;
                case 62: goto L68;
                default: goto L6b;
            }
        L68:
            goto L71
        L6b:
            int r7 = r7 + 1
            goto L16
        L71:
            r0 = r6
            r1 = r7
            r0.bufPos = r1
            r0 = r7
            r1 = r8
            if (r0 <= r1) goto L8b
            r0 = r6
            char[] r0 = r0.charBuf
            r1 = r6
            java.lang.String[] r1 = r1.stringCache
            r2 = r8
            r3 = r7
            r4 = r8
            int r3 = r3 - r4
            java.lang.String r0 = cacheString(r0, r1, r2, r3)
            return r0
        L8b:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.CharacterReader.consumeTagName():java.lang.String");
    }

    final String consumeToEnd() {
        bufferUp();
        String cacheString = cacheString(this.charBuf, this.stringCache, this.bufPos, this.bufLength - this.bufPos);
        this.bufPos = this.bufLength;
        return cacheString;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeLetterSequence() {
        char c;
        bufferUp();
        int i = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || Character.isLetter(c)))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, i, this.bufPos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeLetterThenDigitSequence() {
        char c;
        char c2;
        bufferUp();
        int i = this.bufPos;
        while (this.bufPos < this.bufLength && (((c2 = this.charBuf[this.bufPos]) >= 'A' && c2 <= 'Z') || ((c2 >= 'a' && c2 <= 'z') || Character.isLetter(c2)))) {
            this.bufPos++;
        }
        while (!isEmptyNoBufferUp() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, i, this.bufPos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeHexSequence() {
        char c;
        bufferUp();
        int i = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, i, this.bufPos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String consumeDigitSequence() {
        char c;
        bufferUp();
        int i = this.bufPos;
        while (this.bufPos < this.bufLength && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, i, this.bufPos - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matches(char c) {
        return !isEmpty() && this.charBuf[this.bufPos] == c;
    }

    final boolean matches(String str) {
        bufferUp();
        int length = str.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) != this.charBuf[this.bufPos + i]) {
                return false;
            }
        }
        return true;
    }

    final boolean matchesIgnoreCase(String str) {
        bufferUp();
        int length = str.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (Character.toUpperCase(str.charAt(i)) != Character.toUpperCase(this.charBuf[this.bufPos + i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        bufferUp();
        char c = this.charBuf[this.bufPos];
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchesAnySorted(char[] cArr) {
        bufferUp();
        return !isEmpty() && Arrays.binarySearch(cArr, this.charBuf[this.bufPos]) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.charBuf[this.bufPos];
        if (c < 'A' || c > 'Z') {
            return (c >= 'a' && c <= 'z') || Character.isLetter(c);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchesAsciiAlpha() {
        if (isEmpty()) {
            return false;
        }
        char c = this.charBuf[this.bufPos];
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchesDigit() {
        char c;
        return !isEmpty() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchConsume(String str) {
        bufferUp();
        if (matches(str)) {
            this.bufPos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean matchConsumeIgnoreCase(String str) {
        if (matchesIgnoreCase(str)) {
            this.bufPos += str.length();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean containsIgnoreCase(String str) {
        if (str.equals(this.lastIcSeq)) {
            if (this.lastIcIndex == -1) {
                return false;
            }
            if (this.lastIcIndex >= this.bufPos) {
                return true;
            }
        }
        this.lastIcSeq = str;
        int nextIndexOf = nextIndexOf(str.toLowerCase(Locale.ENGLISH));
        if (nextIndexOf >= 0) {
            this.lastIcIndex = this.bufPos + nextIndexOf;
            return true;
        }
        int nextIndexOf2 = nextIndexOf(str.toUpperCase(Locale.ENGLISH));
        boolean z = nextIndexOf2 >= 0;
        this.lastIcIndex = z ? this.bufPos + nextIndexOf2 : -1;
        return z;
    }

    public final String toString() {
        if (this.bufLength - this.bufPos < 0) {
            return "";
        }
        return new String(this.charBuf, this.bufPos, this.bufLength - this.bufPos);
    }

    private static String cacheString(char[] cArr, String[] strArr, int i, int i2) {
        if (i2 > 12) {
            return new String(cArr, i, i2);
        }
        if (i2 <= 0) {
            return "";
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 31) + cArr[i + i4];
        }
        int i5 = i3 & 511;
        String str = strArr[i5];
        if (str != null && rangeEquals(cArr, i, i2, str)) {
            return str;
        }
        String str2 = new String(cArr, i, i2);
        strArr[i5] = str2;
        return str2;
    }

    static boolean rangeEquals(char[] cArr, int i, int i2, String str) {
        int i3;
        int i4;
        if (i2 == str.length()) {
            int i5 = i;
            int i6 = 0;
            do {
                int i7 = i2;
                i2--;
                if (i7 == 0) {
                    return true;
                }
                i3 = i5;
                i5++;
                i4 = i6;
                i6++;
            } while (cArr[i3] == str.charAt(i4));
            return false;
        }
        return false;
    }

    final boolean rangeEquals(int i, int i2, String str) {
        return rangeEquals(this.charBuf, i, i2, str);
    }
}

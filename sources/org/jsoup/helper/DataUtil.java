package org.jsoup.helper;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.internal.ControllableInputStream;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;

/* loaded from: infinitode-2.jar:org/jsoup/helper/DataUtil.class */
public final class DataUtil {
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");
    public static final Charset UTF_8;
    static final String defaultCharsetName;
    private static final int firstReadBufferSize = 5120;
    private static final char[] mimeBoundaryChars;
    static final int boundaryLength = 32;

    static {
        Charset forName = Charset.forName("UTF-8");
        UTF_8 = forName;
        defaultCharsetName = forName.name();
        mimeBoundaryChars = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    private DataUtil() {
    }

    public static Document load(File file, String str, String str2) {
        return load(file, str, str2, Parser.htmlParser());
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.jsoup.nodes.Document load(java.io.File r6, java.lang.String r7, java.lang.String r8, org.jsoup.parser.Parser r9) {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r10 = r0
            r0 = r6
            java.lang.String r0 = r0.getName()
            java.lang.String r0 = org.jsoup.internal.Normalizer.lowerCase(r0)
            r1 = r0
            r11 = r1
            java.lang.String r1 = ".gz"
            boolean r0 = r0.endsWith(r1)
            if (r0 != 0) goto L26
            r0 = r11
            java.lang.String r1 = ".z"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L73
        L26:
            r0 = r10
            int r0 = r0.read()     // Catch: java.lang.Throwable -> L4a
            r1 = 31
            if (r0 != r1) goto L3f
            r0 = r10
            int r0 = r0.read()     // Catch: java.lang.Throwable -> L4a
            r1 = 139(0x8b, float:1.95E-43)
            if (r0 != r1) goto L3f
            r0 = 1
            goto L40
        L3f:
            r0 = 0
        L40:
            r11 = r0
            r0 = r10
            r0.close()
            goto L52
        L4a:
            r6 = move-exception
            r0 = r10
            r0.close()
            r0 = r6
            throw r0
        L52:
            r0 = r11
            if (r0 == 0) goto L69
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream
            r1 = r0
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r3 = r2
            r4 = r6
            r3.<init>(r4)
            r1.<init>(r2)
            goto L71
        L69:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r1 = r0
            r2 = r6
            r1.<init>(r2)
        L71:
            r10 = r0
        L73:
            r0 = r10
            r1 = r7
            r2 = r8
            r3 = r9
            org.jsoup.nodes.Document r0 = parseInputStream(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.DataUtil.load(java.io.File, java.lang.String, java.lang.String, org.jsoup.parser.Parser):org.jsoup.nodes.Document");
    }

    public static Document load(InputStream inputStream, String str, String str2) {
        return parseInputStream(inputStream, str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2, Parser parser) {
        return parseInputStream(inputStream, str, str2, parser);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void crossStreams(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[32768];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v119 */
    /* JADX WARN: Type inference failed for: r0v120 */
    /* JADX WARN: Type inference failed for: r0v121 */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.io.UncheckedIOException] */
    /* JADX WARN: Type inference failed for: r0v84 */
    /* JADX WARN: Type inference failed for: r0v85, types: [java.io.UncheckedIOException] */
    /* JADX WARN: Type inference failed for: r0v88, types: [org.jsoup.nodes.Document] */
    /* JADX WARN: Type inference failed for: r10v0, types: [org.jsoup.parser.Parser] */
    /* JADX WARN: Type inference failed for: r5v3, types: [long, java.nio.charset.Charset] */
    public static Document parseInputStream(InputStream inputStream, String str, String str2, Parser parser) {
        if (inputStream == null) {
            return new Document(str2);
        }
        ControllableInputStream wrap = ControllableInputStream.wrap(inputStream, 32768, 0);
        Document document = null;
        try {
            wrap.mark(32768);
            ByteBuffer readToByteBuffer = readToByteBuffer(wrap, 5119);
            boolean z = wrap.read() == -1;
            wrap.reset();
            BomCharset detectCharsetFromBom = detectCharsetFromBom(readToByteBuffer);
            if (detectCharsetFromBom != null) {
                str = detectCharsetFromBom.charset;
            }
            ?? r0 = str;
            if (r0 == 0) {
                try {
                    CharBuffer decode = UTF_8.decode(readToByteBuffer);
                    if (decode.hasArray()) {
                        document = parser.parseInput(new CharArrayReader(decode.array(), decode.arrayOffset(), decode.limit()), str2);
                    } else {
                        document = parser.parseInput(decode.toString(), str2);
                    }
                    String str3 = null;
                    Iterator<Element> it = document.select("meta[http-equiv=content-type], meta[charset]").iterator();
                    while (it.hasNext()) {
                        Element next = it.next();
                        if (next.hasAttr("http-equiv")) {
                            str3 = getCharsetFromContentType(next.attr("content"));
                        }
                        if (str3 == null && next.hasAttr("charset")) {
                            str3 = next.attr("charset");
                        }
                        if (str3 != null) {
                            break;
                        }
                    }
                    if (str3 == null && document.childNodeSize() > 0) {
                        Node childNode = document.childNode(0);
                        XmlDeclaration xmlDeclaration = null;
                        if (childNode instanceof XmlDeclaration) {
                            xmlDeclaration = (XmlDeclaration) childNode;
                        } else if (childNode instanceof Comment) {
                            Comment comment = (Comment) childNode;
                            if (comment.isXmlDeclaration()) {
                                xmlDeclaration = comment.asXmlDeclaration();
                            }
                        }
                        if (xmlDeclaration != null && xmlDeclaration.name().equalsIgnoreCase("xml")) {
                            str3 = xmlDeclaration.attr("encoding");
                        }
                    }
                    String validateCharset = validateCharset(str3);
                    if (validateCharset != null && !validateCharset.equalsIgnoreCase(defaultCharsetName)) {
                        str = validateCharset.trim().replaceAll("[\"']", "");
                        document = null;
                    } else if (!z) {
                        document = null;
                    }
                } catch (UncheckedIOException e) {
                    throw r0.getCause();
                }
            } else {
                Validate.notEmpty(str, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
            }
            if (document == null) {
                if (str == null) {
                    str = defaultCharsetName;
                }
                ?? forName = Charset.forName(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(wrap, (Charset) forName), 32768);
                BomCharset bomCharset = detectCharsetFromBom;
                ?? r02 = bomCharset;
                if (bomCharset != null) {
                    try {
                        boolean z2 = detectCharsetFromBom.offset;
                        r02 = z2;
                        if (z2) {
                            bufferedReader.skip(1L);
                            boolean z3 = forName == 1;
                            Validate.isTrue(z3);
                            r02 = z3;
                        }
                    } catch (Throwable th) {
                        bufferedReader.close();
                        throw th;
                    }
                }
                try {
                    r02 = parser.parseInput(bufferedReader, str2);
                    document = r02;
                    Charset forName2 = str.equals(defaultCharsetName) ? UTF_8 : Charset.forName(str);
                    document.outputSettings().charset(forName2);
                    if (!forName2.canEncode()) {
                        document.charset(UTF_8);
                    }
                    bufferedReader.close();
                } catch (UncheckedIOException e2) {
                    throw r02.getCause();
                }
            }
            return document;
        } finally {
            wrap.close();
        }
    }

    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i) {
        return ControllableInputStream.readToByteBuffer(inputStream, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCharsetFromContentType(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = charsetPattern.matcher(str);
        if (matcher.find()) {
            return validateCharset(matcher.group(1).trim().replace("charset=", ""));
        }
        return null;
    }

    private static String validateCharset(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String replaceAll = str.trim().replaceAll("[\"']", "");
        try {
            if (Charset.isSupported(replaceAll)) {
                return replaceAll;
            }
            String upperCase = replaceAll.toUpperCase(Locale.ENGLISH);
            if (Charset.isSupported(upperCase)) {
                return upperCase;
            }
            return null;
        } catch (IllegalCharsetNameException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String mimeBoundary() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            borrowBuilder.append(mimeBoundaryChars[random.nextInt(mimeBoundaryChars.length)]);
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    private static BomCharset detectCharsetFromBom(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        byte[] bArr = new byte[4];
        if (byteBuffer.remaining() >= 4) {
            byteBuffer.get(bArr);
            byteBuffer.rewind();
        }
        if ((bArr[0] == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) || (bArr[0] == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0)) {
            return new BomCharset("UTF-32", false);
        }
        if ((bArr[0] == -2 && bArr[1] == -1) || (bArr[0] == -1 && bArr[1] == -2)) {
            return new BomCharset("UTF-16", false);
        }
        if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            return new BomCharset("UTF-8", true);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/helper/DataUtil$BomCharset.class */
    public static class BomCharset {
        private final String charset;
        private final boolean offset;

        public BomCharset(String str, boolean z) {
            this.charset = str;
            this.offset = z;
        }
    }
}

package com.prineside.tdi2.desktop.luadef;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.CharArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.desktop.LuaDefinitionsGenerator;
import com.prineside.tdi2.desktop.luadef.javadoc.ParserGdx;
import com.prineside.tdi2.desktop.luadef.javadoc.ParserInfinitode;
import com.prineside.tdi2.desktop.luadef.javadoc.ParserJava8;
import com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataSet;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import net.bytebuddy.utility.JavaConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler.class */
public class JavadocHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1846a = TLog.forClass(JavadocHandler.class);

    /* renamed from: b, reason: collision with root package name */
    private static DataHolder f1847b;
    public static final FlexmarkHtmlConverter htmlMarkdownConverter;
    public static ParserVariant[] PARSERS;
    public final Set<Class<?>> classesToFetchFor = new HashSet();
    private final ConcurrentHashMap<Class<?>, String> c = new ConcurrentHashMap<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$ClassJD.class */
    public static class ClassJD {
        public static final ClassJD EMPTY = new ClassJD();

        @Null
        public String javadocUrl;

        @Null
        public String description;

        @Null
        public String genericsString;
        public HashMap<Constructor<?>, ConstructorJD> constructors = new HashMap<>();
        public HashMap<Method, MethodJD> methods = new HashMap<>();
        public HashMap<Field, FieldJD> fields = new HashMap<>();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$ClassJavadoc.class */
    public static class ClassJavadoc {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$ConstructorJD.class */
    public static class ConstructorJD {
        public Constructor<?> ctor;

        @Null
        public String description;
        public Array<ParamJD> params = new Array<>();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$FieldJD.class */
    public static class FieldJD {
        public Field field;

        @Null
        public String description;

        @Null
        public String generics;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$MethodJD.class */
    public static class MethodJD {
        public Method method;

        @Null
        public String description;
        public Array<ParamJD> params = new Array<>();

        @Null
        public String returnDescription;
        public boolean specifiedByInterface;
        public boolean overridesSuperMethod;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$ParamJD.class */
    public static class ParamJD {
        public String name;

        @Null
        public String description;
    }

    static {
        DataSet immutable = new MutableDataSet().toImmutable();
        f1847b = immutable;
        htmlMarkdownConverter = FlexmarkHtmlConverter.builder(immutable).build();
        PARSERS = new ParserVariant[]{new ParserGdx(), new ParserInfinitode(), new ParserJava8()};
    }

    public static void main(String[] strArr) {
        new JavadocHandler().run();
    }

    public ClassJD getForClass(Class<?> cls) {
        ClassJD a2 = a(cls);
        if (a2 == null) {
            if (LuaDefinitionsGenerator.verbose) {
                f1846a.w("No JD for " + cls, new Object[0]);
            }
            return ClassJD.EMPTY;
        }
        return a2;
    }

    public void run() {
        b();
        c();
    }

    private void b() {
        long millis = TimeUtils.millis();
        ArrayList arrayList = new ArrayList();
        FileReader fileReader = new FileReader("res/luaj/javadoc-sources.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    if (trim.length() != 0) {
                        arrayList.add(new JavadocSite(trim));
                    }
                } else {
                    fileReader.close();
                    this.c.clear();
                    arrayList.parallelStream().forEach(javadocSite -> {
                        javadocSite.fetchClassUrls();
                        Array.ArrayIterator<String> it = javadocSite.classUrls.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            Class<?> classFromClassUrl = javadocSite.getClassFromClassUrl(next);
                            if (classFromClassUrl != null && (this.classesToFetchFor.size() == 0 || this.classesToFetchFor.contains(classFromClassUrl))) {
                                this.c.put(classFromClassUrl, javadocSite.rootUrl + next);
                            }
                        }
                    });
                    f1846a.i("found javadoc class URLs for " + this.c.size() + " classes in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                fileReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void c() {
        long millis = TimeUtils.millis();
        LuaDefUtils.createDirs("cache/javadoc-cache/index.html");
        AtomicInteger atomicInteger = new AtomicInteger();
        this.c.entrySet().parallelStream().forEach(entry -> {
            try {
                a((Class<?>) entry.getKey(), (String) entry.getValue());
            } catch (Exception e) {
                f1846a.e("failed to cache class page " + ((String) entry.getValue()), e);
            }
            int incrementAndGet = atomicInteger.incrementAndGet();
            if (incrementAndGet % 200 == 0) {
                f1846a.i("- " + incrementAndGet + " / " + this.c.size(), new Object[0]);
            }
        });
        f1846a.i("cached all class javadoc pages locally in " + (TimeUtils.millis() - millis) + "ms", new Object[0]);
    }

    private ClassJD a(Class<?> cls) {
        if (ParserVariant.verbose) {
            f1846a.i("----- parse " + cls, new Object[0]);
        }
        File file = new File(getJavadocPageCacheFilePath(cls));
        if (!file.exists()) {
            f1846a.w("cache file not found for " + cls + ", its javadoc will be skipped", new Object[0]);
            return null;
        }
        try {
            Document parse = Jsoup.parse(file);
            int i = -1;
            ParserVariant parserVariant = null;
            int i2 = 0;
            for (ParserVariant parserVariant2 : PARSERS) {
                int score = parserVariant2.getScore(parse);
                if (score > 0) {
                    if (score > i) {
                        i = score;
                        parserVariant = parserVariant2;
                        i2 = 0;
                    } else if (score == i) {
                        i2++;
                    }
                }
            }
            if (parserVariant == null) {
                throw new IllegalStateException("failed to find a parser for " + cls + " - all parsers gave zero score");
            }
            if (i2 > 0) {
                throw new IllegalStateException("multiple parsers (" + (i2 + 1) + ") have same score");
            }
            if (ParserVariant.verbose) {
                f1846a.i("- using parser '" + parserVariant.getName() + "' with score " + i, new Object[0]);
            }
            ClassJD classJD = new ClassJD();
            classJD.javadocUrl = this.c.get(cls);
            classJD.description = parserVariant.getClassDescription(parse);
            classJD.genericsString = parserVariant.getClassGenericsString(parse);
            Array.ArrayIterator<ConstructorJD> it = parserVariant.getConstructors(parse, cls).iterator();
            while (it.hasNext()) {
                ConstructorJD next = it.next();
                classJD.constructors.put(next.ctor, next);
            }
            Array.ArrayIterator<FieldJD> it2 = parserVariant.getFields(parse, cls).iterator();
            while (it2.hasNext()) {
                FieldJD next2 = it2.next();
                classJD.fields.put(next2.field, next2);
            }
            Array.ArrayIterator<MethodJD> it3 = parserVariant.getMethods(parse, cls).iterator();
            while (it3.hasNext()) {
                MethodJD next3 = it3.next();
                classJD.methods.put(next3.method, next3);
            }
            return classJD;
        } catch (IOException e) {
            f1846a.e("failed to parse class page for " + cls, e);
            return null;
        }
    }

    public static String getJavadocPageCacheFilePath(Class<?> cls) {
        return "cache/javadoc-cache/" + cls.getName().replaceAll("\\$", JavaConstant.Dynamic.DEFAULT_NAME) + ".html";
    }

    private static void a(Class<?> cls, String str) {
        File file = new File(getJavadocPageCacheFilePath(cls));
        if (!file.exists()) {
            f1846a.i("fetching " + str, new Object[0]);
            InputStream openStream = new URL(str).openStream();
            try {
                OutputStream newOutputStream = Files.newOutputStream(file.toPath(), new OpenOption[0]);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = openStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else {
                            newOutputStream.write(bArr, 0, read);
                        }
                    }
                    newOutputStream.flush();
                    if (newOutputStream != null) {
                        newOutputStream.close();
                    }
                    if (openStream != null) {
                        openStream.close();
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (openStream != null) {
                    try {
                        openStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/JavadocHandler$JavadocSite.class */
    public static class JavadocSite {
        public final String allClassesUrl;
        public final String rootUrl;
        public final Array<String> classUrls = new Array<>();

        public JavadocSite(String str) {
            this.allClassesUrl = str;
            String[] split = str.split("/");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < split.length - 1; i++) {
                stringBuilder.append(split[i]).append("/");
            }
            this.rootUrl = stringBuilder.toString();
            if (LuaDefinitionsGenerator.verbose) {
                JavadocHandler.f1846a.i("Javadoc: " + this.rootUrl, new Object[0]);
            }
        }

        public void fetchClassUrls() {
            this.classUrls.clear();
            JavadocHandler.f1846a.i("fetching class list from " + this.allClassesUrl, new Object[0]);
            try {
                Document document = Jsoup.connect(this.allClassesUrl).get();
                JavadocHandler.f1846a.i("got response from " + this.allClassesUrl, new Object[0]);
                Iterator<Element> it = document.select(FlexmarkHtmlConverter.A_NODE).iterator();
                while (it.hasNext()) {
                    String attr = it.next().attr("href");
                    if (attr.contains("/") && !attr.contains("://") && !attr.contains("-") && !attr.contains("#") && attr.endsWith(".html")) {
                        this.classUrls.add(attr);
                    } else if (LuaDefinitionsGenerator.verbose) {
                        JavadocHandler.f1846a.i("skip href: " + attr, new Object[0]);
                    }
                }
                if (LuaDefinitionsGenerator.verbose) {
                    JavadocHandler.f1846a.i("found " + this.classUrls.size + " class URLs", new Object[0]);
                }
            } catch (Exception unused) {
                JavadocHandler.f1846a.e("failed to fetch " + this.allClassesUrl, new Object[0]);
                System.exit(1);
            }
        }

        @Null
        public Class<?> getClassFromClassUrl(String str) {
            if (!str.endsWith(".html")) {
                if (LuaDefinitionsGenerator.verbose) {
                    JavadocHandler.f1846a.i("can't parse " + str + " which does not end with .html", new Object[0]);
                    return null;
                }
                return null;
            }
            String replaceAll = str.substring(0, str.length() - 5).replaceAll("\\.", "\\$").replaceAll("/", "\\.");
            try {
                return Class.forName(replaceAll);
            } catch (Exception unused) {
                if (LuaDefinitionsGenerator.verbose) {
                    JavadocHandler.f1846a.i("can't get runtime class from " + replaceAll + " - its javadoc will be skipped", new Object[0]);
                    return null;
                }
                return null;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x00eb. Please report as an issue. */
    public static String formatDocumentation(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append(SequenceUtils.SPACE);
        }
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        String convert = htmlMarkdownConverter.convert(str);
        Parser.builder().build().parse(convert);
        for (String str2 : convert.split(SequenceUtils.EOL)) {
            String replaceAll = str2.replaceAll("\\[([^]]+)]\\(([^)]+)\\)", "$1");
            String trim = replaceAll.trim();
            char charAt = trim.length() == 0 ? 'X' : trim.charAt(0);
            if (replaceAll.length() <= 77 || charAt == '>' || charAt == '|' || charAt == '*') {
                stringBuilder3.append(stringBuilder2).append("--- ").append(replaceAll).append(SequenceUtils.EOL);
            } else {
                stringBuilder3.append(stringBuilder2).append("--- ");
                int i3 = 4;
                CharArray charArray = new CharArray();
                for (int i4 = 0; i4 < replaceAll.length(); i4++) {
                    char charAt2 = replaceAll.charAt(i4);
                    stringBuilder3.append(charAt2);
                    i3++;
                    switch (charAt2) {
                        case '(':
                        case '[':
                            charArray.add(charAt2);
                            break;
                        case ')':
                            charArray.removeValue('(');
                            break;
                        case '/':
                            if (replaceAll.length() > i4 + 1 && replaceAll.charAt(i4 + 1) == '/') {
                                charArray.add('/');
                                break;
                            }
                            break;
                        case ']':
                            charArray.removeValue('[');
                            break;
                        case '`':
                            if (charArray.contains('`')) {
                                charArray.removeValue('`');
                                break;
                            } else {
                                charArray.add('`');
                                break;
                            }
                    }
                    if (i3 >= 80 && charAt2 == ' ' && charArray.size == 0) {
                        stringBuilder3.append(SequenceUtils.EOL).append(stringBuilder2).append("--- ");
                        i3 = 4;
                    }
                }
                if (i3 > 4) {
                    stringBuilder3.append(SequenceUtils.EOL);
                }
            }
        }
        return stringBuilder3.toString();
    }
}

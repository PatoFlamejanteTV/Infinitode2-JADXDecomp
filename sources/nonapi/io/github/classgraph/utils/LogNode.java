package nonapi.io.github.classgraph.utils;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.ClassGraph;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import net.bytebuddy.ClassFileVersion;
import nonapi.io.github.classgraph.classpath.SystemJarFinder;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/LogNode.class */
public final class LogNode {
    private static final Logger log;
    private final long timeStampNano;
    private final long timeStampMillis;
    private final String msg;
    private String stackTrace;
    private long elapsedTimeNanos;
    private LogNode parent;
    private final Map<String, LogNode> children;
    private final String sortKeyPrefix;
    private static AtomicInteger sortKeyUniqueSuffix;
    private static final SimpleDateFormat dateTimeFormatter;
    private static final DecimalFormat nanoFormatter;
    private static boolean logInRealtime;

    static {
        System.getProperties().setProperty("log4j2.formatMsgNoLookups", "true");
        log = Logger.getLogger(ClassGraph.class.getName());
        sortKeyUniqueSuffix = new AtomicInteger(0);
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.US);
        nanoFormatter = new DecimalFormat("0.000000");
    }

    public static void logInRealtime(boolean z) {
        logInRealtime = z;
    }

    private LogNode(String str, String str2, long j, Throwable th) {
        this.timeStampNano = System.nanoTime();
        this.timeStampMillis = System.currentTimeMillis();
        this.children = new ConcurrentSkipListMap();
        this.sortKeyPrefix = str;
        this.msg = str2;
        this.elapsedTimeNanos = j;
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            this.stackTrace = stringWriter.toString();
        } else {
            this.stackTrace = null;
        }
        if (logInRealtime) {
            log.info(toString());
        }
    }

    public LogNode() {
        this("", "", -1L, null);
        log("ClassGraph version " + VersionFinder.getVersion());
        logJavaInfo();
    }

    private void logJavaInfo() {
        log("Operating system: " + VersionFinder.getProperty("os.name") + SequenceUtils.SPACE + VersionFinder.getProperty("os.version") + SequenceUtils.SPACE + VersionFinder.getProperty("os.arch"));
        log("Java version: " + VersionFinder.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION) + " / " + VersionFinder.getProperty("java.runtime.version") + " (" + VersionFinder.getProperty("java.vendor") + ")");
        log("Java home: " + VersionFinder.getProperty("java.home"));
        String jreRtJarPath = SystemJarFinder.getJreRtJarPath();
        if (jreRtJarPath != null) {
            log("JRE rt.jar:").log(jreRtJarPath);
        }
    }

    private void appendLine(String str, int i, String str2, StringBuilder sb) {
        sb.append(str);
        sb.append('\t');
        sb.append(ClassGraph.class.getSimpleName());
        sb.append('\t');
        int i2 = 2 * (i - 1);
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append('-');
        }
        if (i2 > 0) {
            sb.append(' ');
        }
        sb.append(str2);
        sb.append('\n');
    }

    private void toString(int i, StringBuilder sb) {
        String format;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.timeStampMillis);
        synchronized (dateTimeFormatter) {
            format = dateTimeFormatter.format(calendar.getTime());
        }
        if (this.msg != null && !this.msg.isEmpty()) {
            appendLine(format, i, this.elapsedTimeNanos > 0 ? this.msg + " (took " + nanoFormatter.format(this.elapsedTimeNanos * 1.0E-9d) + " sec)" : this.msg, sb);
        }
        if (this.stackTrace != null && !this.stackTrace.isEmpty()) {
            for (String str : this.stackTrace.split(SequenceUtils.EOL)) {
                appendLine(format, i, str, sb);
            }
        }
        Iterator<Map.Entry<String, LogNode>> it = this.children.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().toString(i + 1, sb);
        }
    }

    public final String toString() {
        String sb;
        synchronized (dateTimeFormatter) {
            StringBuilder sb2 = new StringBuilder();
            toString(0, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public final void addElapsedTime() {
        this.elapsedTimeNanos = System.nanoTime() - this.timeStampNano;
    }

    private LogNode addChild(String str, String str2, long j, Throwable th) {
        String str3 = this.sortKeyPrefix + "\t" + (str == null ? "" : str) + "\t" + String.format("%09d", Integer.valueOf(sortKeyUniqueSuffix.getAndIncrement()));
        LogNode logNode = new LogNode(str3, str2, j, th);
        logNode.parent = this;
        this.children.put(str3, logNode);
        return logNode;
    }

    private LogNode addChild(String str, String str2, long j) {
        return addChild(str, str2, j, null);
    }

    private LogNode addChild(Throwable th) {
        return addChild("", "", -1L, th);
    }

    public final LogNode log(String str, String str2, long j, Throwable th) {
        return addChild(str, str2, j).addChild(th);
    }

    public final LogNode log(String str, String str2, long j) {
        return addChild(str, str2, j);
    }

    public final LogNode log(String str, String str2, Throwable th) {
        return addChild(str, str2, -1L).addChild(th);
    }

    public final LogNode log(String str, String str2) {
        return addChild(str, str2, -1L);
    }

    public final LogNode log(String str, long j, Throwable th) {
        return addChild("", str, j).addChild(th);
    }

    public final LogNode log(String str, long j) {
        return addChild("", str, j);
    }

    public final LogNode log(String str, Throwable th) {
        return addChild("", str, -1L).addChild(th);
    }

    public final LogNode log(String str) {
        return addChild("", str, -1L);
    }

    public final LogNode log(Collection<String> collection) {
        LogNode logNode = null;
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            logNode = log(it.next());
        }
        return logNode;
    }

    public final LogNode log(Throwable th) {
        return log("Exception thrown").addChild(th);
    }

    public final void flush() {
        if (this.parent != null) {
            throw new IllegalArgumentException("Only flush the toplevel LogNode");
        }
        if (!this.children.isEmpty()) {
            String logNode = toString();
            this.children.clear();
            log.info(logNode);
        }
    }
}

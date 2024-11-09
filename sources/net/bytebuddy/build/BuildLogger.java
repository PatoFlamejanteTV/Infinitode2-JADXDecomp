package net.bytebuddy.build;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/build/BuildLogger.class */
public interface BuildLogger {
    boolean isDebugEnabled();

    void debug(String str);

    void debug(String str, Throwable th);

    boolean isInfoEnabled();

    void info(String str);

    void info(String str, Throwable th);

    boolean isWarnEnabled();

    void warn(String str);

    void warn(String str, Throwable th);

    boolean isErrorEnabled();

    void error(String str);

    void error(String str, Throwable th);

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/BuildLogger$NoOp.class */
    public enum NoOp implements BuildLogger {
        INSTANCE;

        @Override // net.bytebuddy.build.BuildLogger
        public final boolean isDebugEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void debug(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void debug(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final boolean isInfoEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void info(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void info(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final boolean isWarnEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void warn(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void warn(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final boolean isErrorEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void error(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public final void error(String str, Throwable th) {
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/build/BuildLogger$Adapter.class */
    public static abstract class Adapter implements BuildLogger {
        @Override // net.bytebuddy.build.BuildLogger
        public boolean isDebugEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isInfoEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isWarnEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str, Throwable th) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isErrorEnabled() {
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str) {
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str, Throwable th) {
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/BuildLogger$StreamWriting.class */
    public static class StreamWriting implements BuildLogger {
        private final PrintStream printStream;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.printStream.hashCode();
        }

        public StreamWriting(PrintStream printStream) {
            this.printStream = printStream;
        }

        public static BuildLogger toSystemOut() {
            return new StreamWriting(System.out);
        }

        public static BuildLogger toSystemError() {
            return new StreamWriting(System.err);
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isDebugEnabled() {
            return true;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str) {
            this.printStream.print(str);
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str, Throwable th) {
            synchronized (this.printStream) {
                this.printStream.print(str);
                th.printStackTrace(this.printStream);
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isInfoEnabled() {
            return true;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str) {
            this.printStream.print(str);
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str, Throwable th) {
            synchronized (this.printStream) {
                this.printStream.print(str);
                th.printStackTrace(this.printStream);
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isWarnEnabled() {
            return true;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str) {
            this.printStream.print(str);
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str, Throwable th) {
            synchronized (this.printStream) {
                this.printStream.print(str);
                th.printStackTrace(this.printStream);
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isErrorEnabled() {
            return true;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str) {
            this.printStream.print(str);
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str, Throwable th) {
            synchronized (this.printStream) {
                this.printStream.print(str);
                th.printStackTrace(this.printStream);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/build/BuildLogger$Compound.class */
    public static class Compound implements BuildLogger {
        private final List<BuildLogger> buildLoggers;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.buildLoggers.equals(((Compound) obj).buildLoggers);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.buildLoggers.hashCode();
        }

        public Compound(BuildLogger... buildLoggerArr) {
            this((List<? extends BuildLogger>) Arrays.asList(buildLoggerArr));
        }

        public Compound(List<? extends BuildLogger> list) {
            this.buildLoggers = new ArrayList();
            for (BuildLogger buildLogger : list) {
                if (buildLogger instanceof Compound) {
                    this.buildLoggers.addAll(((Compound) buildLogger).buildLoggers);
                } else if (!(buildLogger instanceof NoOp)) {
                    this.buildLoggers.add(buildLogger);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isDebugEnabled() {
            Iterator<BuildLogger> it = this.buildLoggers.iterator();
            while (it.hasNext()) {
                if (it.next().isDebugEnabled()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isDebugEnabled()) {
                    buildLogger.debug(str);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void debug(String str, Throwable th) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isDebugEnabled()) {
                    buildLogger.debug(str, th);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isInfoEnabled() {
            Iterator<BuildLogger> it = this.buildLoggers.iterator();
            while (it.hasNext()) {
                if (it.next().isInfoEnabled()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isInfoEnabled()) {
                    buildLogger.info(str);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void info(String str, Throwable th) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isInfoEnabled()) {
                    buildLogger.info(str, th);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isWarnEnabled() {
            Iterator<BuildLogger> it = this.buildLoggers.iterator();
            while (it.hasNext()) {
                if (it.next().isWarnEnabled()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isWarnEnabled()) {
                    buildLogger.warn(str);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void warn(String str, Throwable th) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isWarnEnabled()) {
                    buildLogger.warn(str, th);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public boolean isErrorEnabled() {
            Iterator<BuildLogger> it = this.buildLoggers.iterator();
            while (it.hasNext()) {
                if (it.next().isErrorEnabled()) {
                    return true;
                }
            }
            return false;
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isErrorEnabled()) {
                    buildLogger.error(str);
                }
            }
        }

        @Override // net.bytebuddy.build.BuildLogger
        public void error(String str, Throwable th) {
            for (BuildLogger buildLogger : this.buildLoggers) {
                if (buildLogger.isErrorEnabled()) {
                    buildLogger.error(str, th);
                }
            }
        }
    }
}

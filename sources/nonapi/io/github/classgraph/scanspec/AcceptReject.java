package nonapi.io.github.classgraph.scanspec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/AcceptReject.class */
public abstract class AcceptReject {
    protected Set<String> accept;
    protected Set<String> reject;
    protected Set<String> acceptPrefixesSet;
    protected List<String> acceptPrefixes;
    protected List<String> rejectPrefixes;
    protected Set<String> acceptGlobs;
    protected Set<String> rejectGlobs;
    protected transient List<Pattern> acceptPatterns;
    protected transient List<Pattern> rejectPatterns;
    protected char separatorChar;

    public abstract void addToAccept(String str);

    public abstract void addToReject(String str);

    public abstract boolean isAcceptedAndNotRejected(String str);

    public abstract boolean isAccepted(String str);

    public abstract boolean acceptHasPrefix(String str);

    public abstract boolean isRejected(String str);

    public AcceptReject() {
    }

    public AcceptReject(char c) {
        this.separatorChar = c;
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/AcceptReject$AcceptRejectPrefix.class */
    public static class AcceptRejectPrefix extends AcceptReject {
        public AcceptRejectPrefix() {
        }

        public AcceptRejectPrefix(char c) {
            super(c);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToAccept(String str) {
            if (str.contains("*")) {
                throw new IllegalArgumentException("Cannot use a glob wildcard here: " + str);
            }
            if (this.acceptPrefixesSet == null) {
                this.acceptPrefixesSet = new HashSet();
            }
            this.acceptPrefixesSet.add(str);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToReject(String str) {
            if (str.contains("*")) {
                throw new IllegalArgumentException("Cannot use a glob wildcard here: " + str);
            }
            if (this.rejectPrefixes == null) {
                this.rejectPrefixes = new ArrayList();
            }
            this.rejectPrefixes.add(str);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAcceptedAndNotRejected(String str) {
            boolean z = this.acceptPrefixes == null;
            boolean z2 = z;
            if (!z) {
                Iterator<String> it = this.acceptPrefixes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (str.startsWith(it.next())) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (!z2) {
                return false;
            }
            if (this.rejectPrefixes != null) {
                Iterator<String> it2 = this.rejectPrefixes.iterator();
                while (it2.hasNext()) {
                    if (str.startsWith(it2.next())) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAccepted(String str) {
            boolean z = this.acceptPrefixes == null;
            boolean z2 = z;
            if (!z) {
                Iterator<String> it = this.acceptPrefixes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (str.startsWith(it.next())) {
                        z2 = true;
                        break;
                    }
                }
            }
            return z2;
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean acceptHasPrefix(String str) {
            throw new IllegalArgumentException("Can only find prefixes of whole strings");
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isRejected(String str) {
            if (this.rejectPrefixes != null) {
                Iterator<String> it = this.rejectPrefixes.iterator();
                while (it.hasNext()) {
                    if (str.startsWith(it.next())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/AcceptReject$AcceptRejectWholeString.class */
    public static class AcceptRejectWholeString extends AcceptReject {
        public AcceptRejectWholeString() {
        }

        public AcceptRejectWholeString(char c) {
            super(c);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToAccept(String str) {
            if (str.contains("*")) {
                if (this.acceptGlobs == null) {
                    this.acceptGlobs = new HashSet();
                    this.acceptPatterns = new ArrayList();
                }
                this.acceptGlobs.add(str);
                this.acceptPatterns.add(globToPattern(str, true));
            } else {
                if (this.accept == null) {
                    this.accept = new HashSet();
                }
                this.accept.add(str);
            }
            if (this.acceptPrefixesSet == null) {
                this.acceptPrefixesSet = new HashSet();
                this.acceptPrefixesSet.add("");
                this.acceptPrefixesSet.add("/");
            }
            String ch = Character.toString(this.separatorChar);
            String str2 = str;
            if (str.contains("*")) {
                String substring = str2.substring(0, str2.indexOf(42));
                if (substring.lastIndexOf(this.separatorChar) < 0) {
                    str2 = "";
                } else {
                    str2 = substring.substring(0, substring.lastIndexOf(this.separatorChar));
                }
            }
            while (str2.endsWith(ch)) {
                str2 = str2.substring(0, str2.length() - 1);
            }
            while (!str2.isEmpty()) {
                this.acceptPrefixesSet.add(str2 + this.separatorChar);
                str2 = FileUtils.getParentDirPath(str2, this.separatorChar);
            }
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToReject(String str) {
            if (str.contains("*")) {
                if (this.rejectGlobs == null) {
                    this.rejectGlobs = new HashSet();
                    this.rejectPatterns = new ArrayList();
                }
                this.rejectGlobs.add(str);
                this.rejectPatterns.add(globToPattern(str, true));
                return;
            }
            if (this.reject == null) {
                this.reject = new HashSet();
            }
            this.reject.add(str);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAcceptedAndNotRejected(String str) {
            return isAccepted(str) && !isRejected(str);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAccepted(String str) {
            if (this.accept == null && this.acceptPatterns == null) {
                return true;
            }
            return (this.accept != null && this.accept.contains(str)) || AcceptReject.matchesPatternList(str, this.acceptPatterns);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean acceptHasPrefix(String str) {
            if (this.acceptPrefixesSet == null) {
                return false;
            }
            return this.acceptPrefixesSet.contains(str);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isRejected(String str) {
            return (this.reject != null && this.reject.contains(str)) || AcceptReject.matchesPatternList(str, this.rejectPatterns);
        }
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/AcceptReject$AcceptRejectLeafname.class */
    public static class AcceptRejectLeafname extends AcceptRejectWholeString {
        public AcceptRejectLeafname() {
        }

        public AcceptRejectLeafname(char c) {
            super(c);
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToAccept(String str) {
            super.addToAccept(JarUtils.leafName(str));
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public void addToReject(String str) {
            super.addToReject(JarUtils.leafName(str));
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAcceptedAndNotRejected(String str) {
            return super.isAcceptedAndNotRejected(JarUtils.leafName(str));
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isAccepted(String str) {
            return super.isAccepted(JarUtils.leafName(str));
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean acceptHasPrefix(String str) {
            throw new IllegalArgumentException("Can only find prefixes of whole strings");
        }

        @Override // nonapi.io.github.classgraph.scanspec.AcceptReject.AcceptRejectWholeString, nonapi.io.github.classgraph.scanspec.AcceptReject
        public boolean isRejected(String str) {
            return super.isRejected(JarUtils.leafName(str));
        }
    }

    public static String normalizePath(String str) {
        String resolve = FastPathResolver.resolve(str);
        while (true) {
            String str2 = resolve;
            if (str2.startsWith("/")) {
                resolve = str2.substring(1);
            } else {
                return str2;
            }
        }
    }

    public static String normalizePackageOrClassName(String str) {
        return normalizePath(str.replace('.', '/')).replace('/', '.');
    }

    public static String pathToPackageName(String str) {
        return str.replace('/', '.');
    }

    public static String packageNameToPath(String str) {
        return str.replace('.', '/');
    }

    public static String classNameToClassfilePath(String str) {
        return JarUtils.classNameToClassfilePath(str);
    }

    public static Pattern globToPattern(String str, boolean z) {
        String replace;
        StringBuilder sb = new StringBuilder("^");
        if (z) {
            replace = str.replace(".", "\\.").replace("*", ".*");
        } else {
            replace = str.replace(".", "\\.").replace("*", "[^/]*").replace("[^/]*[^/]*", ".*").replace('?', '.');
        }
        return Pattern.compile(sb.append(replace).append("$").toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean matchesPatternList(String str, List<Pattern> list) {
        if (list != null) {
            Iterator<Pattern> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().matcher(str).matches()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean acceptIsEmpty() {
        return this.accept == null && this.acceptPrefixes == null && this.acceptGlobs == null;
    }

    public boolean rejectIsEmpty() {
        return this.reject == null && this.rejectPrefixes == null && this.rejectGlobs == null;
    }

    public boolean acceptAndRejectAreEmpty() {
        return acceptIsEmpty() && rejectIsEmpty();
    }

    public boolean isSpecificallyAcceptedAndNotRejected(String str) {
        return !acceptIsEmpty() && isAcceptedAndNotRejected(str);
    }

    public boolean isSpecificallyAccepted(String str) {
        return !acceptIsEmpty() && isAccepted(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sortPrefixes() {
        if (this.acceptPrefixesSet != null) {
            this.acceptPrefixes = new ArrayList(this.acceptPrefixesSet);
        }
        if (this.acceptPrefixes != null) {
            CollectionUtils.sortIfNotEmpty(this.acceptPrefixes);
        }
        if (this.rejectPrefixes != null) {
            CollectionUtils.sortIfNotEmpty(this.rejectPrefixes);
        }
    }

    private static void quoteList(Collection<String> collection, StringBuilder sb) {
        sb.append('[');
        boolean z = true;
        for (String str : collection) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append('\"');
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '\"') {
                    sb.append("\\\"");
                } else {
                    sb.append(charAt);
                }
            }
            sb.append('\"');
        }
        sb.append(']');
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.accept != null) {
            sb.append("accept: ");
            quoteList(this.accept, sb);
        }
        if (this.acceptPrefixes != null) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("acceptPrefixes: ");
            quoteList(this.acceptPrefixes, sb);
        }
        if (this.acceptGlobs != null) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("acceptGlobs: ");
            quoteList(this.acceptGlobs, sb);
        }
        if (this.reject != null) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("reject: ");
            quoteList(this.reject, sb);
        }
        if (this.rejectPrefixes != null) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("rejectPrefixes: ");
            quoteList(this.rejectPrefixes, sb);
        }
        if (this.rejectGlobs != null) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("rejectGlobs: ");
            quoteList(this.rejectGlobs, sb);
        }
        return sb.toString();
    }
}

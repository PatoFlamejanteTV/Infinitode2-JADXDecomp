package io.github.classgraph;

import com.vladsch.flexmark.util.html.Attribute;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import net.bytebuddy.description.method.MethodDescription;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import org.lwjgl.opengl.WGLARBPixelFormat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/GraphvizDotfileGenerator.class */
public final class GraphvizDotfileGenerator {
    private static final String STANDARD_CLASS_COLOR = "fff2b6";
    private static final String INTERFACE_COLOR = "b6e7ff";
    private static final String ANNOTATION_COLOR = "f3c9ff";
    private static final int PARAM_WRAP_WIDTH = 40;
    private static final BitSet IS_UNICODE_WHITESPACE = new BitSet(65536);

    static {
        for (int i = 0; i < 26; i++) {
            IS_UNICODE_WHITESPACE.set(" \t\n\u000b\f\r\u0085 \u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006 \u2008\u2009\u200a\u2028\u2029 \u205f\u3000".charAt(i));
        }
    }

    private GraphvizDotfileGenerator() {
    }

    private static boolean isUnicodeWhitespace(char c) {
        return IS_UNICODE_WHITESPACE.get(c);
    }

    private static void htmlEncode(CharSequence charSequence, boolean z, StringBuilder sb) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            switch (charAt) {
                case '\n':
                    if (z) {
                        sb.append("<br>");
                        break;
                    } else {
                        sb.append(' ');
                        break;
                    }
                case '\"':
                    sb.append("&quot;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\'':
                    sb.append("&#x27;");
                    break;
                case '/':
                    sb.append("&#x2F;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '\\':
                    sb.append("&lsol;");
                    break;
                case 160:
                    sb.append("&nbsp;");
                    break;
                case 163:
                    sb.append("&pound;");
                    break;
                case 169:
                    sb.append("&copy;");
                    break;
                case 171:
                    sb.append("&laquo;");
                    break;
                case 174:
                    sb.append("&reg;");
                    break;
                case 187:
                    sb.append("&raquo;");
                    break;
                case WGLARBPixelFormat.WGL_PIXEL_TYPE_ARB /* 8211 */:
                    sb.append("&ndash;");
                    break;
                case WGLARBPixelFormat.WGL_COLOR_BITS_ARB /* 8212 */:
                    sb.append("&mdash;");
                    break;
                case WGLARBPixelFormat.WGL_GREEN_SHIFT_ARB /* 8216 */:
                    sb.append("&lsquo;");
                    break;
                case WGLARBPixelFormat.WGL_BLUE_BITS_ARB /* 8217 */:
                    sb.append("&rsquo;");
                    break;
                case WGLARBPixelFormat.WGL_ALPHA_SHIFT_ARB /* 8220 */:
                    sb.append("&ldquo;");
                    break;
                case WGLARBPixelFormat.WGL_ACCUM_BITS_ARB /* 8221 */:
                    sb.append("&rdquo;");
                    break;
                default:
                    if (charAt <= ' ' || isUnicodeWhitespace(charAt)) {
                        sb.append(' ');
                        break;
                    } else {
                        sb.append(charAt);
                        break;
                    }
            }
        }
    }

    private static void htmlEncode(CharSequence charSequence, StringBuilder sb) {
        htmlEncode(charSequence, false, sb);
    }

    private static void labelClassNodeHTML(ClassInfo classInfo, String str, String str2, boolean z, boolean z2, boolean z3, ScanSpec scanSpec, StringBuilder sb) {
        String str3;
        sb.append("[shape=").append(str).append(",style=filled,fillcolor=\"#").append(str2).append("\",label=");
        sb.append('<');
        sb.append("<table border='0' cellborder='0' cellspacing='1'>");
        StringBuilder append = sb.append("<tr><td><font point-size='12'>").append(classInfo.getModifiersStr()).append(' ');
        if (classInfo.isEnum()) {
            str3 = "enum";
        } else {
            str3 = classInfo.isAnnotation() ? "@interface" : classInfo.isInterface() ? "interface" : Attribute.CLASS_ATTR;
        }
        append.append(str3).append("</font></td></tr>");
        if (classInfo.getName().contains(".")) {
            sb.append("<tr><td><font point-size='14'><b>");
            htmlEncode(classInfo.getPackageName() + ".", sb);
            sb.append("</b></font></td></tr>");
        }
        sb.append("<tr><td><font point-size='20'><b>");
        htmlEncode(classInfo.getSimpleName(), sb);
        sb.append("</b></font></td></tr>");
        int parseInt = (int) (Integer.parseInt(str2.substring(0, 2), 16) * 0.8f);
        int parseInt2 = (int) (Integer.parseInt(str2.substring(2, 4), 16) * 0.8f);
        int parseInt3 = (int) (Integer.parseInt(str2.substring(4, 6), 16) * 0.8f);
        String format = String.format("#%s%s%s%s%s%s", Integer.toString(parseInt >> 4, 16), Integer.toString(parseInt & 15, 16), Integer.toString(parseInt2 >> 4, 16), Integer.toString(parseInt2 & 15, 16), Integer.toString(parseInt3 >> 4, 16), Integer.toString(parseInt3 & 15, 16));
        AnnotationInfoList annotationInfoList = classInfo.annotationInfo;
        if (annotationInfoList != null && !annotationInfoList.isEmpty()) {
            sb.append("<tr><td colspan='3' bgcolor='").append(format).append("'><font point-size='12'><b>ANNOTATIONS</b></font></td></tr>");
            AnnotationInfoList annotationInfoList2 = new AnnotationInfoList(annotationInfoList);
            CollectionUtils.sortIfNotEmpty(annotationInfoList2);
            Iterator it = annotationInfoList2.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                if (!annotationInfo.getName().startsWith("java.lang.annotation.")) {
                    sb.append("<tr>");
                    sb.append("<td align='center' valign='top'>");
                    htmlEncode(annotationInfo.toString(), sb);
                    sb.append("</td></tr>");
                }
            }
        }
        FieldInfoList fieldInfoList = classInfo.fieldInfo;
        if (z && fieldInfoList != null && !fieldInfoList.isEmpty()) {
            FieldInfoList fieldInfoList2 = new FieldInfoList(fieldInfoList);
            CollectionUtils.sortIfNotEmpty(fieldInfoList2);
            for (int size = fieldInfoList2.size() - 1; size >= 0; size--) {
                if (((FieldInfo) fieldInfoList2.get(size)).getName().equals("serialVersionUID")) {
                    fieldInfoList2.remove(size);
                }
            }
            if (!fieldInfoList2.isEmpty()) {
                sb.append("<tr><td colspan='3' bgcolor='").append(format).append("'><font point-size='12'><b>").append(scanSpec.ignoreFieldVisibility ? "" : "PUBLIC ").append("FIELDS</b></font></td></tr>");
                sb.append("<tr><td cellpadding='0'>");
                sb.append("<table border='0' cellborder='0'>");
                Iterator it2 = fieldInfoList2.iterator();
                while (it2.hasNext()) {
                    FieldInfo fieldInfo = (FieldInfo) it2.next();
                    sb.append("<tr>");
                    sb.append("<td align='right' valign='top'>");
                    AnnotationInfoList annotationInfoList3 = fieldInfo.annotationInfo;
                    if (annotationInfoList3 != null) {
                        Iterator it3 = annotationInfoList3.iterator();
                        while (it3.hasNext()) {
                            AnnotationInfo annotationInfo2 = (AnnotationInfo) it3.next();
                            if (sb.charAt(sb.length() - 1) != ' ') {
                                sb.append(' ');
                            }
                            htmlEncode(annotationInfo2.toString(), sb);
                        }
                    }
                    if (scanSpec.ignoreFieldVisibility) {
                        if (sb.charAt(sb.length() - 1) != ' ') {
                            sb.append(' ');
                        }
                        sb.append(fieldInfo.getModifiersStr());
                    }
                    if (sb.charAt(sb.length() - 1) != ' ') {
                        sb.append(' ');
                    }
                    TypeSignature typeSignatureOrTypeDescriptor = fieldInfo.getTypeSignatureOrTypeDescriptor();
                    htmlEncode(z3 ? typeSignatureOrTypeDescriptor.toStringWithSimpleNames() : typeSignatureOrTypeDescriptor.toString(), sb);
                    sb.append("</td>");
                    sb.append("<td align='left' valign='top'><b>");
                    htmlEncode(fieldInfo.getName(), sb);
                    sb.append("</b></td></tr>");
                }
                sb.append("</table>");
                sb.append("</td></tr>");
            }
        }
        MethodInfoList methodInfoList = classInfo.methodInfo;
        if (z2 && methodInfoList != null) {
            MethodInfoList methodInfoList2 = new MethodInfoList(methodInfoList);
            CollectionUtils.sortIfNotEmpty(methodInfoList2);
            for (int size2 = methodInfoList2.size() - 1; size2 >= 0; size2--) {
                MethodInfo methodInfo = (MethodInfo) methodInfoList2.get(size2);
                String name = methodInfo.getName();
                int length = methodInfo.getParameterInfo().length;
                if (name.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME) || ((name.equals("hashCode") && length == 0) || ((name.equals("toString") && length == 0) || (name.equals("equals") && length == 1 && methodInfo.getTypeDescriptor().toString().equals("boolean (java.lang.Object)"))))) {
                    methodInfoList2.remove(size2);
                }
            }
            if (!methodInfoList2.isEmpty()) {
                sb.append("<tr><td cellpadding='0'>");
                sb.append("<table border='0' cellborder='0'>");
                sb.append("<tr><td colspan='3' bgcolor='").append(format).append("'><font point-size='12'><b>").append(scanSpec.ignoreMethodVisibility ? "" : "PUBLIC ").append("METHODS</b></font></td></tr>");
                Iterator it4 = methodInfoList2.iterator();
                while (it4.hasNext()) {
                    MethodInfo methodInfo2 = (MethodInfo) it4.next();
                    sb.append("<tr>");
                    sb.append("<td align='right' valign='top'>");
                    AnnotationInfoList annotationInfoList4 = methodInfo2.annotationInfo;
                    if (annotationInfoList4 != null) {
                        Iterator it5 = annotationInfoList4.iterator();
                        while (it5.hasNext()) {
                            AnnotationInfo annotationInfo3 = (AnnotationInfo) it5.next();
                            if (sb.charAt(sb.length() - 1) != ' ') {
                                sb.append(' ');
                            }
                            htmlEncode(annotationInfo3.toString(), sb);
                        }
                    }
                    if (scanSpec.ignoreMethodVisibility) {
                        if (sb.charAt(sb.length() - 1) != ' ') {
                            sb.append(' ');
                        }
                        sb.append(methodInfo2.getModifiersStr());
                    }
                    if (sb.charAt(sb.length() - 1) != ' ') {
                        sb.append(' ');
                    }
                    if (!methodInfo2.getName().equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                        TypeSignature resultType = methodInfo2.getTypeSignatureOrTypeDescriptor().getResultType();
                        htmlEncode(z3 ? resultType.toStringWithSimpleNames() : resultType.toString(), sb);
                    } else {
                        sb.append("<b>&lt;constructor&gt;</b>");
                    }
                    sb.append("</td>");
                    sb.append("<td align='left' valign='top'>");
                    sb.append("<b>");
                    if (methodInfo2.getName().equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                        htmlEncode(classInfo.getSimpleName(), sb);
                    } else {
                        htmlEncode(methodInfo2.getName(), sb);
                    }
                    sb.append("</b>&nbsp;");
                    sb.append("</td>");
                    sb.append("<td align='left' valign='top'>");
                    sb.append('(');
                    MethodParameterInfo[] parameterInfo = methodInfo2.getParameterInfo();
                    if (parameterInfo.length != 0) {
                        int i = 0;
                        for (int i2 = 0; i2 < parameterInfo.length; i2++) {
                            if (i2 > 0) {
                                sb.append(", ");
                                i += 2;
                            }
                            if (i > 40) {
                                sb.append("</td></tr><tr><td></td><td></td><td align='left' valign='top'>");
                                i = 0;
                            }
                            AnnotationInfo[] annotationInfoArr = parameterInfo[i2].annotationInfo;
                            if (annotationInfoArr != null) {
                                for (AnnotationInfo annotationInfo4 : annotationInfoArr) {
                                    String annotationInfo5 = annotationInfo4.toString();
                                    if (!annotationInfo5.isEmpty()) {
                                        if (sb.charAt(sb.length() - 1) != ' ') {
                                            sb.append(' ');
                                        }
                                        htmlEncode(annotationInfo5, sb);
                                        int length2 = i + 1 + annotationInfo5.length();
                                        i = length2;
                                        if (length2 > 40) {
                                            sb.append("</td></tr><tr><td></td><td></td><td align='left' valign='top'>");
                                            i = 0;
                                        }
                                    }
                                }
                            }
                            TypeSignature typeSignatureOrTypeDescriptor2 = parameterInfo[i2].getTypeSignatureOrTypeDescriptor();
                            String stringWithSimpleNames = z3 ? typeSignatureOrTypeDescriptor2.toStringWithSimpleNames() : typeSignatureOrTypeDescriptor2.toString();
                            htmlEncode(stringWithSimpleNames, sb);
                            i += stringWithSimpleNames.length();
                            String name2 = parameterInfo[i2].getName();
                            if (name2 != null) {
                                sb.append(" <B>");
                                htmlEncode(name2, sb);
                                i += 1 + name2.length();
                                sb.append("</B>");
                            }
                        }
                    }
                    sb.append(')');
                    sb.append("</td></tr>");
                }
                sb.append("</table>");
                sb.append("</td></tr>");
            }
        }
        sb.append("</table>");
        sb.append(">]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String generateGraphVizDotFile(ClassInfoList classInfoList, float f, float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, ScanSpec scanSpec) {
        StringBuilder sb = new StringBuilder(1048576);
        sb.append("digraph {\n");
        sb.append("size=\"").append(f).append(',').append(f2).append("\";\n");
        sb.append("layout=dot;\n");
        sb.append("rankdir=\"BT\";\n");
        sb.append("overlap=false;\n");
        sb.append("splines=true;\n");
        sb.append("pack=true;\n");
        sb.append("graph [fontname = \"Courier, Regular\"]\n");
        sb.append("node [fontname = \"Courier, Regular\"]\n");
        sb.append("edge [fontname = \"Courier, Regular\"]\n");
        ClassInfoList standardClasses = classInfoList.getStandardClasses();
        ClassInfoList interfaces = classInfoList.getInterfaces();
        ClassInfoList annotations = classInfoList.getAnnotations();
        Iterator it = standardClasses.iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = (ClassInfo) it.next();
            sb.append('\"').append(classInfo.getName()).append('\"');
            labelClassNodeHTML(classInfo, "box", STANDARD_CLASS_COLOR, z, z3, z6, scanSpec, sb);
            sb.append(";\n");
        }
        Iterator it2 = interfaces.iterator();
        while (it2.hasNext()) {
            ClassInfo classInfo2 = (ClassInfo) it2.next();
            sb.append('\"').append(classInfo2.getName()).append('\"');
            labelClassNodeHTML(classInfo2, "diamond", INTERFACE_COLOR, z, z3, z6, scanSpec, sb);
            sb.append(";\n");
        }
        Iterator it3 = annotations.iterator();
        while (it3.hasNext()) {
            ClassInfo classInfo3 = (ClassInfo) it3.next();
            sb.append('\"').append(classInfo3.getName()).append('\"');
            labelClassNodeHTML(classInfo3, "oval", ANNOTATION_COLOR, z, z3, z6, scanSpec, sb);
            sb.append(";\n");
        }
        HashSet hashSet = new HashSet();
        hashSet.addAll(standardClasses.getNames());
        hashSet.addAll(interfaces.getNames());
        hashSet.addAll(annotations.getNames());
        sb.append('\n');
        Iterator it4 = standardClasses.iterator();
        while (it4.hasNext()) {
            ClassInfo classInfo4 = (ClassInfo) it4.next();
            Iterator it5 = classInfo4.getSuperclasses().directOnly().iterator();
            while (it5.hasNext()) {
                ClassInfo classInfo5 = (ClassInfo) it5.next();
                if (classInfo5 != null && hashSet.contains(classInfo5.getName()) && !classInfo5.getName().equals("java.lang.Object")) {
                    sb.append("  \"").append(classInfo4.getName()).append("\" -> \"").append(classInfo5.getName()).append("\" [arrowsize=2.5]\n");
                }
            }
            Iterator it6 = classInfo4.getInterfaces().directOnly().iterator();
            while (it6.hasNext()) {
                ClassInfo classInfo6 = (ClassInfo) it6.next();
                if (hashSet.contains(classInfo6.getName())) {
                    sb.append("  \"").append(classInfo4.getName()).append("\" -> \"").append(classInfo6.getName()).append("\" [arrowhead=diamond, arrowsize=2.5]\n");
                }
            }
            if (z2 && classInfo4.fieldInfo != null) {
                Iterator it7 = classInfo4.fieldInfo.iterator();
                while (it7.hasNext()) {
                    for (ClassInfo classInfo7 : ((FieldInfo) it7.next()).findReferencedClassInfo(null)) {
                        if (hashSet.contains(classInfo7.getName())) {
                            sb.append("  \"").append(classInfo7.getName()).append("\" -> \"").append(classInfo4.getName()).append("\" [arrowtail=obox, arrowsize=2.5, dir=back]\n");
                        }
                    }
                }
            }
            if (z4 && classInfo4.methodInfo != null) {
                Iterator it8 = classInfo4.methodInfo.iterator();
                while (it8.hasNext()) {
                    for (ClassInfo classInfo8 : ((MethodInfo) it8.next()).findReferencedClassInfo(null)) {
                        if (hashSet.contains(classInfo8.getName())) {
                            sb.append("  \"").append(classInfo8.getName()).append("\" -> \"").append(classInfo4.getName()).append("\" [arrowtail=box, arrowsize=2.5, dir=back]\n");
                        }
                    }
                }
            }
        }
        Iterator it9 = interfaces.iterator();
        while (it9.hasNext()) {
            ClassInfo classInfo9 = (ClassInfo) it9.next();
            Iterator it10 = classInfo9.getInterfaces().directOnly().iterator();
            while (it10.hasNext()) {
                ClassInfo classInfo10 = (ClassInfo) it10.next();
                if (hashSet.contains(classInfo10.getName())) {
                    sb.append("  \"").append(classInfo9.getName()).append("\" -> \"").append(classInfo10.getName()).append("\" [arrowhead=diamond, arrowsize=2.5]\n");
                }
            }
        }
        if (z5) {
            Iterator it11 = annotations.iterator();
            while (it11.hasNext()) {
                ClassInfo classInfo11 = (ClassInfo) it11.next();
                Iterator it12 = classInfo11.getClassesWithAnnotationDirectOnly().iterator();
                while (it12.hasNext()) {
                    ClassInfo classInfo12 = (ClassInfo) it12.next();
                    if (hashSet.contains(classInfo12.getName())) {
                        sb.append("  \"").append(classInfo12.getName()).append("\" -> \"").append(classInfo11.getName()).append("\" [arrowhead=dot, arrowsize=2.5]\n");
                    }
                }
                Iterator it13 = classInfo11.getClassesWithMethodAnnotationDirectOnly().iterator();
                while (it13.hasNext()) {
                    ClassInfo classInfo13 = (ClassInfo) it13.next();
                    if (hashSet.contains(classInfo13.getName())) {
                        sb.append("  \"").append(classInfo13.getName()).append("\" -> \"").append(classInfo11.getName()).append("\" [arrowhead=odot, arrowsize=2.5]\n");
                    }
                }
                Iterator it14 = classInfo11.getClassesWithFieldAnnotationDirectOnly().iterator();
                while (it14.hasNext()) {
                    ClassInfo classInfo14 = (ClassInfo) it14.next();
                    if (hashSet.contains(classInfo14.getName())) {
                        sb.append("  \"").append(classInfo14.getName()).append("\" -> \"").append(classInfo11.getName()).append("\" [arrowhead=odot, arrowsize=2.5]\n");
                    }
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String generateGraphVizDotFileFromInterClassDependencies(ClassInfoList classInfoList, float f, float f2, boolean z) {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder(1048576);
        sb.append("digraph {\n");
        sb.append("size=\"").append(f).append(',').append(f2).append("\";\n");
        sb.append("layout=dot;\n");
        sb.append("rankdir=\"BT\";\n");
        sb.append("overlap=false;\n");
        sb.append("splines=true;\n");
        sb.append("pack=true;\n");
        sb.append("graph [fontname = \"Courier, Regular\"]\n");
        sb.append("node [fontname = \"Courier, Regular\"]\n");
        sb.append("edge [fontname = \"Courier, Regular\"]\n");
        HashSet<ClassInfo> hashSet = new HashSet(classInfoList);
        if (z) {
            Iterator it = classInfoList.iterator();
            while (it.hasNext()) {
                hashSet.addAll(((ClassInfo) it.next()).getClassDependencies());
            }
        }
        for (ClassInfo classInfo : hashSet) {
            sb.append('\"').append(classInfo.getName()).append('\"');
            StringBuilder append = sb.append("[shape=").append(classInfo.isAnnotation() ? "oval" : classInfo.isInterface() ? "diamond" : "box").append(",style=filled,fillcolor=\"#");
            if (classInfo.isAnnotation()) {
                str = ANNOTATION_COLOR;
            } else {
                str = classInfo.isInterface() ? INTERFACE_COLOR : STANDARD_CLASS_COLOR;
            }
            append.append(str).append("\",label=");
            sb.append('<');
            sb.append("<table border='0' cellborder='0' cellspacing='1'>");
            StringBuilder append2 = sb.append("<tr><td><font point-size='12'>").append(classInfo.getModifiersStr()).append(' ');
            if (classInfo.isEnum()) {
                str2 = "enum";
            } else {
                str2 = classInfo.isAnnotation() ? "@interface" : classInfo.isInterface() ? "interface" : Attribute.CLASS_ATTR;
            }
            append2.append(str2).append("</font></td></tr>");
            if (classInfo.getName().contains(".")) {
                sb.append("<tr><td><font point-size='14'><b>");
                htmlEncode(classInfo.getPackageName(), sb);
                sb.append("</b></font></td></tr>");
            }
            sb.append("<tr><td><font point-size='20'><b>");
            htmlEncode(classInfo.getSimpleName(), sb);
            sb.append("</b></font></td></tr>");
            sb.append("</table>");
            sb.append(">];\n");
        }
        sb.append('\n');
        Iterator it2 = classInfoList.iterator();
        while (it2.hasNext()) {
            ClassInfo classInfo2 = (ClassInfo) it2.next();
            Iterator it3 = classInfo2.getClassDependencies().iterator();
            while (it3.hasNext()) {
                ClassInfo classInfo3 = (ClassInfo) it3.next();
                if (z || hashSet.contains(classInfo3)) {
                    sb.append("  \"").append(classInfo2.getName()).append("\" -> \"").append(classInfo3.getName()).append("\" [arrowsize=2.5]\n");
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

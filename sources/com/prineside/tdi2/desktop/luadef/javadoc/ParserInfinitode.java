package com.prineside.tdi2.desktop.luadef.javadoc;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.desktop.luadef.JavadocHandler;
import com.prineside.tdi2.desktop.luadef.LuaDefUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/javadoc/ParserInfinitode.class */
public final class ParserInfinitode extends ParserVariant {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1852a = TLog.forClass(ParserInfinitode.class);

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final int getScore(Document document) {
        int i = 0;
        if (document.select("#class-description .block").size() != 0) {
            i = 0 + 1;
        }
        if (document.select("body.class-declaration-page > .flex-box").size() != 0) {
            i++;
        }
        if (document.select("#method-summary").size() != 0) {
            i++;
        }
        if (document.select("#constructor-summary").size() != 0) {
            i++;
        }
        if (document.select("#method-detail").size() != 0) {
            i++;
        }
        return i;
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Array<JavadocHandler.FieldJD> getFields(Document document, Class<?> cls) {
        Array<Field> gatherFieldsCached = LuaDefUtils.gatherFieldsCached(cls);
        Array<JavadocHandler.FieldJD> array = new Array<>();
        Element first = document.select("#field-detail").first();
        if (first != null) {
            Iterator<Element> it = first.select("ul.member-list > li").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                String sanitizeHtmlString = sanitizeHtmlString(next.select(FlexmarkHtmlConverter.H3_NODE).first().text());
                Element first2 = next.select(".member-signature").first();
                String str = null;
                if (first2 != null) {
                    String sanitizeHtmlString2 = sanitizeHtmlString(first2.text());
                    if (sanitizeHtmlString2.contains("<") && sanitizeHtmlString2.contains(">")) {
                        str = sanitizeHtmlString2.substring(sanitizeHtmlString2.indexOf(60), sanitizeHtmlString2.lastIndexOf(62) + 1);
                    }
                }
                String str2 = null;
                Element first3 = next.select(".block").first();
                if (first3 != null) {
                    String sanitizeHtmlString3 = sanitizeHtmlString(first3.text());
                    str2 = sanitizeHtmlString3;
                    if (sanitizeHtmlString3.length() == 0) {
                        str2 = null;
                    }
                }
                Field field = null;
                int i = 0;
                while (true) {
                    if (i >= gatherFieldsCached.size) {
                        break;
                    }
                    if (!gatherFieldsCached.items[i].getName().equals(sanitizeHtmlString)) {
                        i++;
                    } else {
                        field = gatherFieldsCached.items[i];
                        break;
                    }
                }
                if (field != null) {
                    JavadocHandler.FieldJD fieldJD = new JavadocHandler.FieldJD();
                    fieldJD.field = field;
                    fieldJD.description = str2;
                    fieldJD.generics = str;
                    array.add(fieldJD);
                    if (verbose) {
                        f1852a.i("/+\\ field '" + sanitizeHtmlString + "' in " + cls, new Object[0]);
                    }
                }
            }
        }
        return array;
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Map<String, String> getClassGenerics(Document document, Class<?> cls) {
        return parseGenerics(document.select(FlexmarkHtmlConverter.H1_NODE).first().text(), cls);
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final String getClassGenericsString(Document document) {
        return parseGenericsString(document.select(FlexmarkHtmlConverter.H1_NODE).first().text());
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final String getClassDescription(Document document) {
        Element first = document.select("#class-description .block").first();
        if (first == null) {
            return null;
        }
        return JavadocHandler.formatDocumentation(first.outerHtml(), 0);
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Array<JavadocHandler.ConstructorJD> getConstructors(Document document, Class<?> cls) {
        Map<String, String> classGenerics = getClassGenerics(document, cls);
        Array<JavadocHandler.ConstructorJD> array = new Array<>();
        Element first = document.select("#constructor-detail").first();
        if (first != null) {
            Iterator<Element> it = first.select(".member-list > li").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                Element first2 = next.select("section > .member-signature").first();
                if (first2 == null) {
                    f1852a.w("ctor sig (section > .member-signature) not found in \"" + sanitizeHtmlString(next.outerHtml()) + "\" of " + cls, new Object[0]);
                } else {
                    String text = first2.text();
                    try {
                        JavadocHandler.ConstructorJD parseConstructorSignature = parseConstructorSignature(text, cls, classGenerics);
                        if (parseConstructorSignature != null) {
                            Element first3 = next.select("section > .block").first();
                            if (first3 != null) {
                                parseConstructorSignature.description = JavadocHandler.formatDocumentation(first3.outerHtml(), 4);
                            }
                            if (parseConstructorSignature.params.size != 0) {
                                Element first4 = next.select("section > dl.notes").first();
                                if (first4 != null) {
                                    boolean z = false;
                                    Iterator<Element> it2 = first4.children().iterator();
                                    while (it2.hasNext()) {
                                        Element next2 = it2.next();
                                        if (next2.tagName().equals(FlexmarkHtmlConverter.DT_NODE)) {
                                            z = next2.text().equals("Parameters:");
                                        } else if (next2.tagName().equals(FlexmarkHtmlConverter.DD_NODE) && z) {
                                            parseConstructorParamDD(next2, parseConstructorSignature);
                                        }
                                    }
                                } else if (verbose) {
                                    f1852a.i("<dl> not found for " + parseConstructorSignature.ctor + " - probably no parameter descriptions, will be skipped", new Object[0]);
                                }
                            }
                            array.add(parseConstructorSignature);
                        }
                    } catch (Exception e) {
                        f1852a.w("Exception while parsing ctor signature \"" + text + "\" of " + cls, e);
                    }
                }
            }
        }
        return array;
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Array<JavadocHandler.MethodJD> getMethods(Document document, Class<?> cls) {
        Map<String, String> classGenerics = getClassGenerics(document, cls);
        Array<JavadocHandler.MethodJD> array = new Array<>();
        Element first = document.select("#method-detail").first();
        if (first != null) {
            Iterator<Element> it = first.select("ul.member-list > li > section.detail").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                Element first2 = next.select(".member-signature").first();
                if (first2 == null) {
                    f1852a.w("method sig (.member-signature) not found in \"" + sanitizeHtmlString(next.outerHtml()) + "\" of " + cls, new Object[0]);
                } else {
                    String text = first2.text();
                    try {
                        JavadocHandler.MethodJD parseMethodSignature = parseMethodSignature(sanitizeHtmlString(next.select(FlexmarkHtmlConverter.H3_NODE).first().text()), text, cls, classGenerics);
                        if (parseMethodSignature != null) {
                            Iterator<Element> it2 = next.select(".block").iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Element next2 = it2.next();
                                if (!sanitizeHtmlString(next2.text()).contains("Description copied from")) {
                                    parseMethodSignature.description = JavadocHandler.formatDocumentation(next2.outerHtml(), 4);
                                    break;
                                }
                            }
                            Element first3 = next.select("dl.notes").first();
                            if (first3 != null) {
                                boolean z = false;
                                boolean z2 = false;
                                Iterator<Element> it3 = first3.children().iterator();
                                while (it3.hasNext()) {
                                    Element next3 = it3.next();
                                    if (!next3.tagName().equals(FlexmarkHtmlConverter.DT_NODE)) {
                                        if (next3.tagName().equals(FlexmarkHtmlConverter.DD_NODE)) {
                                            if (z) {
                                                parseMethodParamDD(next3, parseMethodSignature);
                                            } else if (z2) {
                                                if (parseMethodSignature.returnDescription != null) {
                                                    f1852a.w("multiple \"Returns:\" <dd> elements in " + parseMethodSignature.method, new Object[0]);
                                                } else {
                                                    parseMethodSignature.returnDescription = sanitizeHtmlString(next3.text());
                                                }
                                            }
                                        }
                                    } else {
                                        String sanitizeHtmlString = sanitizeHtmlString(next3.text());
                                        z = sanitizeHtmlString.equals("Parameters:");
                                        z2 = sanitizeHtmlString.equals("Returns:");
                                        if (sanitizeHtmlString.equals("Specified by:")) {
                                            parseMethodSignature.specifiedByInterface = true;
                                        }
                                        if (sanitizeHtmlString.equals("Overrides:")) {
                                            parseMethodSignature.overridesSuperMethod = true;
                                        }
                                    }
                                }
                            } else if (verbose) {
                                f1852a.i("<dl> not found for " + parseMethodSignature.method + " - probably no parameter descriptions, will be skipped", new Object[0]);
                            }
                            array.add(parseMethodSignature);
                        }
                    } catch (Exception e) {
                        f1852a.w("Exception while parsing method signature \"" + text + "\" of " + cls, e);
                    }
                }
            }
        }
        return array;
    }
}

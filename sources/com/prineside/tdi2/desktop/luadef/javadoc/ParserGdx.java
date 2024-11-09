package com.prineside.tdi2.desktop.luadef.javadoc;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.desktop.luadef.JavadocHandler;
import com.prineside.tdi2.desktop.luadef.LuaDefUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/javadoc/ParserGdx.class */
public final class ParserGdx extends ParserVariant {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1851a = TLog.forClass(ParserGdx.class);

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final int getScore(Document document) {
        int i = 0;
        if (document.select("body > main > .header > .subTitle > .packageLabelInType").size() != 0 && document.select("body > main > .header > h2.title").size() != 0) {
            i = 0 + 1;
        }
        if (document.select("body > main > .contentContainer > .summary").size() != 0) {
            i++;
        }
        if (document.select("a#method\\.detail").size() != 0) {
            i++;
        }
        if (document.select("a#constructor\\.detail").size() != 0) {
            i++;
        }
        if (document.select("body > main > .contentContainer > .description > .blockList > .blockList > .block").size() != 0) {
            i++;
        }
        return i;
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Array<JavadocHandler.FieldJD> getFields(Document document, Class<?> cls) {
        Array<Field> gatherFieldsCached = LuaDefUtils.gatherFieldsCached(cls);
        Array<JavadocHandler.FieldJD> array = new Array<>();
        Element first = document.select("#field\\.summary").first();
        if (first != null) {
            Iterator<Element> it = first.parent().select("table > tbody > tr").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                if (next.hasAttr(Attribute.CLASS_ATTR)) {
                    String sanitizeHtmlString = sanitizeHtmlString(next.select(".colSecond").text());
                    String sanitizeHtmlString2 = sanitizeHtmlString(next.select("td.colLast").first().text());
                    if (sanitizeHtmlString2.length() != 0) {
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
                            fieldJD.description = sanitizeHtmlString2;
                            array.add(fieldJD);
                            if (verbose) {
                                f1851a.i("/+\\ field '" + sanitizeHtmlString + "' in " + cls, new Object[0]);
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final String getClassDescription(Document document) {
        Element first = document.select("body > main > .contentContainer > .description > .blockList > .blockList > .block").first();
        if (first == null) {
            return null;
        }
        return JavadocHandler.formatDocumentation(first.outerHtml(), 0);
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Map<String, String> getClassGenerics(Document document, Class<?> cls) {
        return parseGenerics(document.select("body > main > .header > .title").first().text(), cls);
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final String getClassGenericsString(Document document) {
        return parseGenericsString(document.select("body > main > .header > .title").first().text());
    }

    @Override // com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant
    public final Array<JavadocHandler.ConstructorJD> getConstructors(Document document, Class<?> cls) {
        Map<String, String> classGenerics = getClassGenerics(document, cls);
        Array<JavadocHandler.ConstructorJD> array = new Array<>();
        Element first = document.select("#constructor\\.detail").first();
        if (first != null) {
            Iterator<Element> it = first.parent().select("ul > li").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                Element first2 = next.select(FlexmarkHtmlConverter.PRE_NODE).first();
                if (first2 == null) {
                    f1851a.w("ctor sig (pre) not found in \"" + sanitizeHtmlString(next.outerHtml()) + "\" of " + cls, new Object[0]);
                } else {
                    String text = first2.text();
                    try {
                        JavadocHandler.ConstructorJD parseConstructorSignature = parseConstructorSignature(text, cls, classGenerics);
                        if (parseConstructorSignature != null) {
                            Element first3 = next.select(".block").first();
                            if (first3 != null) {
                                parseConstructorSignature.description = JavadocHandler.formatDocumentation(first3.outerHtml(), 4);
                            }
                            if (parseConstructorSignature.params.size != 0) {
                                Element first4 = next.select(FlexmarkHtmlConverter.DL_NODE).first();
                                if (first4 != null) {
                                    boolean z = false;
                                    Iterator<Element> it2 = first4.children().iterator();
                                    while (it2.hasNext()) {
                                        Element next2 = it2.next();
                                        if (next2.tagName().equals(FlexmarkHtmlConverter.DT_NODE)) {
                                            z = next2.select(".paramLabel").size() != 0;
                                        } else if (next2.tagName().equals(FlexmarkHtmlConverter.DD_NODE) && z) {
                                            parseConstructorParamDD(next2, parseConstructorSignature);
                                        }
                                    }
                                } else if (verbose) {
                                    f1851a.i("<dl> not found for " + parseConstructorSignature.ctor + " - probably no parameter descriptions, will be skipped", new Object[0]);
                                }
                            }
                            array.add(parseConstructorSignature);
                        }
                    } catch (Exception e) {
                        f1851a.w("Exception while parsing ctor signature \"" + text + "\" of " + cls, e);
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
        Element first = document.select("#method\\.detail").first();
        if (first != null) {
            Iterator<Element> it = first.parent().select("ul.blockList > li.blockList").iterator();
            while (it.hasNext()) {
                JavadocHandler.MethodJD methodOldImpl = getMethodOldImpl(it.next(), classGenerics, cls);
                if (methodOldImpl != null) {
                    array.add(methodOldImpl);
                }
            }
        }
        return array;
    }
}
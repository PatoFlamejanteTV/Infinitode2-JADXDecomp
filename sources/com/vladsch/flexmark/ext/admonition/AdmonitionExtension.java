package com.vladsch.flexmark.ext.admonition;

import com.vladsch.flexmark.ext.admonition.internal.AdmonitionBlockParser;
import com.vladsch.flexmark.ext.admonition.internal.AdmonitionNodeFormatter;
import com.vladsch.flexmark.ext.admonition.internal.AdmonitionNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/AdmonitionExtension.class */
public class AdmonitionExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final DataKey<Integer> CONTENT_INDENT = new DataKey<>("ADMONITION.CONTENT_INDENT", 4);
    public static final DataKey<Boolean> ALLOW_LEADING_SPACE = new DataKey<>("ADMONITION.ALLOW_LEADING_SPACE", Boolean.TRUE);
    public static final DataKey<Boolean> INTERRUPTS_PARAGRAPH = new DataKey<>("ADMONITION.INTERRUPTS_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("ADMONITION.INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> WITH_SPACES_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("ADMONITION.WITH_SPACES_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> ALLOW_LAZY_CONTINUATION = new DataKey<>("ADMONITION.ALLOW_LAZY_CONTINUATION", Boolean.TRUE);
    public static final DataKey<String> UNRESOLVED_QUALIFIER = new DataKey<>("ADMONITION.UNRESOLVED_QUALIFIER", "note");
    public static final DataKey<Map<String, String>> QUALIFIER_TYPE_MAP = new DataKey<>("ADMONITION.QUALIFIER_TYPE_MAP", AdmonitionExtension::getQualifierTypeMap);
    public static final DataKey<Map<String, String>> QUALIFIER_TITLE_MAP = new DataKey<>("ADMONITION.QUALIFIER_TITLE_MAP", AdmonitionExtension::getQualifierTitleMap);
    public static final DataKey<Map<String, String>> TYPE_SVG_MAP = new DataKey<>("ADMONITION.TYPE_SVG_MAP", AdmonitionExtension::getQualifierSvgValueMap);

    public static Map<String, String> getQualifierTypeMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("abstract", "abstract");
        hashMap.put("summary", "abstract");
        hashMap.put("tldr", "abstract");
        hashMap.put("bug", "bug");
        hashMap.put("danger", "danger");
        hashMap.put("error", "danger");
        hashMap.put("example", "example");
        hashMap.put("snippet", "example");
        hashMap.put("fail", "fail");
        hashMap.put("failure", "fail");
        hashMap.put("missing", "fail");
        hashMap.put("faq", "faq");
        hashMap.put("question", "faq");
        hashMap.put("help", "faq");
        hashMap.put("info", "info");
        hashMap.put("todo", "info");
        hashMap.put("note", "note");
        hashMap.put("seealso", "note");
        hashMap.put("quote", "quote");
        hashMap.put("cite", "quote");
        hashMap.put("success", "success");
        hashMap.put("check", "success");
        hashMap.put("done", "success");
        hashMap.put("tip", "tip");
        hashMap.put("hint", "tip");
        hashMap.put("important", "tip");
        hashMap.put("warning", "warning");
        hashMap.put("caution", "warning");
        hashMap.put("attention", "warning");
        return hashMap;
    }

    public static Map<String, String> getQualifierTitleMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("abstract", "Abstract");
        hashMap.put("summary", "Summary");
        hashMap.put("tldr", "TLDR");
        hashMap.put("bug", "Bug");
        hashMap.put("danger", "Danger");
        hashMap.put("error", "Error");
        hashMap.put("example", "Example");
        hashMap.put("snippet", "Snippet");
        hashMap.put("fail", "Fail");
        hashMap.put("failure", "Failure");
        hashMap.put("missing", "Missing");
        hashMap.put("faq", "Faq");
        hashMap.put("question", "Question");
        hashMap.put("help", "Help");
        hashMap.put("info", "Info");
        hashMap.put("todo", "To Do");
        hashMap.put("note", "Note");
        hashMap.put("seealso", "See Also");
        hashMap.put("quote", "Quote");
        hashMap.put("cite", "Cite");
        hashMap.put("success", "Success");
        hashMap.put("check", "Check");
        hashMap.put("done", "Done");
        hashMap.put("tip", "Tip");
        hashMap.put("hint", "Hint");
        hashMap.put("important", "Important");
        hashMap.put("warning", "Warning");
        hashMap.put("caution", "Caution");
        hashMap.put("attention", "Attention");
        return hashMap;
    }

    public static Map<String, String> getQualifierSvgValueMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("abstract", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-abstract.svg")));
        hashMap.put("bug", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-bug.svg")));
        hashMap.put("danger", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-danger.svg")));
        hashMap.put("example", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-example.svg")));
        hashMap.put("fail", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-fail.svg")));
        hashMap.put("faq", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-faq.svg")));
        hashMap.put("info", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-info.svg")));
        hashMap.put("note", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-note.svg")));
        hashMap.put("quote", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-quote.svg")));
        hashMap.put("success", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-success.svg")));
        hashMap.put("tip", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-tip.svg")));
        hashMap.put("warning", getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/images/adm-warning.svg")));
        return hashMap;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String, java.lang.Exception] */
    public static String getInputStreamContent(InputStream inputStream) {
        ?? stringWriter;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringWriter stringWriter2 = new StringWriter();
            copy(inputStreamReader, stringWriter2);
            stringWriter2.close();
            stringWriter = stringWriter2.toString();
            return stringWriter;
        } catch (Exception e) {
            stringWriter.printStackTrace();
            return "";
        }
    }

    public static String getDefaultCSS() {
        return getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/admonition.css"));
    }

    public static String getDefaultScript() {
        return getInputStreamContent(AdmonitionExtension.class.getResourceAsStream("/admonition.js"));
    }

    public static void copy(Reader reader, Writer writer) {
        char[] cArr = new char[4096];
        while (true) {
            int read = reader.read(cArr);
            if (-1 != read) {
                writer.write(cArr, 0, read);
            } else {
                writer.flush();
                reader.close();
                return;
            }
        }
    }

    private AdmonitionExtension() {
    }

    public static AdmonitionExtension create() {
        return new AdmonitionExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new AdmonitionNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.customBlockParserFactory(new AdmonitionBlockParser.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new AdmonitionNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}

package org.jsoup.parser;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/* loaded from: infinitode-2.jar:org/jsoup/parser/HtmlTreeBuilder.class */
public class HtmlTreeBuilder extends TreeBuilder {
    static final String[] TagsSearchInScope;
    static final String[] TagSearchList;
    static final String[] TagSearchButton;
    static final String[] TagSearchTableScope;
    static final String[] TagSearchSelectScope;
    static final String[] TagSearchEndTags;
    static final String[] TagThoroughSearchEndTags;
    static final String[] TagSearchSpecial;
    static final String[] TagMathMlTextIntegration;
    static final String[] TagSvgHtmlIntegration;
    public static final int MaxScopeSearchDepth = 100;
    private HtmlTreeBuilderState state;
    private HtmlTreeBuilderState originalState;
    private boolean baseUriSetFromDoc;
    private Element headElement;
    private FormElement formElement;
    private Element contextElement;
    private ArrayList<Element> formattingElements;
    private ArrayList<HtmlTreeBuilderState> tmplInsertMode;
    private List<Token.Character> pendingTableCharacters;
    private Token.EndTag emptyEnd;
    private boolean framesetOk;
    private boolean fosterInserts;
    private boolean fragmentParsing;
    private static final int maxQueueDepth = 256;
    private final String[] specificScopeTarget = {null};
    private static final int maxUsedFormattingElements = 12;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !HtmlTreeBuilder.class.desiredAssertionStatus();
        TagsSearchInScope = new String[]{"applet", FlexmarkHtmlConverter.CAPTION_NODE, "html", "marquee", "object", FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE};
        TagSearchList = new String[]{FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.UL_NODE};
        TagSearchButton = new String[]{"button"};
        TagSearchTableScope = new String[]{"html", FlexmarkHtmlConverter.TABLE_NODE};
        TagSearchSelectScope = new String[]{"optgroup", "option"};
        TagSearchEndTags = new String[]{FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.LI_NODE, "optgroup", "option", FlexmarkHtmlConverter.P_NODE, "rb", "rp", "rt", "rtc"};
        TagThoroughSearchEndTags = new String[]{FlexmarkHtmlConverter.CAPTION_NODE, "colgroup", FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.LI_NODE, "optgroup", "option", FlexmarkHtmlConverter.P_NODE, "rb", "rp", "rt", "rtc", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        TagSearchSpecial = new String[]{"address", "applet", "area", "article", FlexmarkHtmlConverter.ASIDE_NODE, "base", "basefont", "bgsound", FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "body", FlexmarkHtmlConverter.BR_NODE, "button", FlexmarkHtmlConverter.CAPTION_NODE, "center", "col", "colgroup", "command", FlexmarkHtmlConverter.DD_NODE, "details", "dir", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, "head", "header", "hgroup", FlexmarkHtmlConverter.HR_NODE, "html", "iframe", FlexmarkHtmlConverter.IMG_NODE, FlexmarkHtmlConverter.INPUT_NODE, "isindex", FlexmarkHtmlConverter.LI_NODE, "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, "param", "plaintext", FlexmarkHtmlConverter.PRE_NODE, "script", "section", "select", Attribute.STYLE_ATTR, "summary", FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "textarea", "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, Attribute.TITLE_ATTR, FlexmarkHtmlConverter.TR_NODE, FlexmarkHtmlConverter.UL_NODE, "wbr", "xmp"};
        TagMathMlTextIntegration = new String[]{"mi", "mn", "mo", "ms", "mtext"};
        TagSvgHtmlIntegration = new String[]{"desc", "foreignObject", Attribute.TITLE_ATTR};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public void initialiseParse(Reader reader, String str, Parser parser) {
        super.initialiseParse(reader, str, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList<>();
        this.tmplInsertMode = new ArrayList<>();
        this.pendingTableCharacters = new ArrayList();
        this.emptyEnd = new Token.EndTag(this);
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public List<Node> parseFragment(String str, Element element, String str2, Parser parser) {
        this.state = HtmlTreeBuilderState.Initial;
        initialiseParse(new StringReader(str), str2, parser);
        this.contextElement = element;
        this.fragmentParsing = true;
        Element element2 = null;
        if (element != null) {
            if (element.ownerDocument() != null) {
                this.doc.quirksMode(element.ownerDocument().quirksMode());
            }
            String normalName = element.normalName();
            boolean z = -1;
            switch (normalName.hashCode()) {
                case -1321546630:
                    if (normalName.equals("template")) {
                        z = 9;
                        break;
                    }
                    break;
                case -1191214428:
                    if (normalName.equals("iframe")) {
                        z = 2;
                        break;
                    }
                    break;
                case -1003243718:
                    if (normalName.equals("textarea")) {
                        z = true;
                        break;
                    }
                    break;
                case -907685685:
                    if (normalName.equals("script")) {
                        z = 7;
                        break;
                    }
                    break;
                case 118811:
                    if (normalName.equals("xmp")) {
                        z = 6;
                        break;
                    }
                    break;
                case 109780401:
                    if (normalName.equals(Attribute.STYLE_ATTR)) {
                        z = 5;
                        break;
                    }
                    break;
                case 110371416:
                    if (normalName.equals(Attribute.TITLE_ATTR)) {
                        z = false;
                        break;
                    }
                    break;
                case 1192721831:
                    if (normalName.equals("noframes")) {
                        z = 4;
                        break;
                    }
                    break;
                case 1973234167:
                    if (normalName.equals("plaintext")) {
                        z = 8;
                        break;
                    }
                    break;
                case 2115613112:
                    if (normalName.equals("noembed")) {
                        z = 3;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                case true:
                    this.tokeniser.transition(TokeniserState.Rcdata);
                    break;
                case true:
                case true:
                case true:
                case true:
                case true:
                    this.tokeniser.transition(TokeniserState.Rawtext);
                    break;
                case true:
                    this.tokeniser.transition(TokeniserState.ScriptData);
                    break;
                case true:
                    this.tokeniser.transition(TokeniserState.PLAINTEXT);
                    break;
                case true:
                    this.tokeniser.transition(TokeniserState.Data);
                    pushTemplateMode(HtmlTreeBuilderState.InTemplate);
                    break;
                default:
                    this.tokeniser.transition(TokeniserState.Data);
                    break;
            }
            element2 = new Element(tagFor(normalName, this.settings), str2);
            this.doc.appendChild(element2);
            push(element2);
            resetInsertionMode();
            Element element3 = element;
            while (true) {
                Element element4 = element3;
                if (element4 != null) {
                    if (element4 instanceof FormElement) {
                        this.formElement = (FormElement) element4;
                    } else {
                        element3 = element4.parent();
                    }
                }
            }
        }
        runParser();
        if (element != null) {
            List<Node> siblingNodes = element2.siblingNodes();
            if (!siblingNodes.isEmpty()) {
                element2.insertChildren(-1, siblingNodes);
            }
            return element2.childNodes();
        }
        return this.doc.childNodes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        return (useCurrentOrForeignInsert(token) ? this.state : HtmlTreeBuilderState.ForeignContent).process(token, this);
    }

    boolean useCurrentOrForeignInsert(Token token) {
        if (this.stack.isEmpty()) {
            return true;
        }
        Element currentElement = currentElement();
        String namespace = currentElement.tag().namespace();
        if (Parser.NamespaceHtml.equals(namespace)) {
            return true;
        }
        if (isMathmlTextIntegration(currentElement) && ((token.isStartTag() && !"mglyph".equals(token.asStartTag().normalName) && !"malignmark".equals(token.asStartTag().normalName)) || token.isCharacter())) {
            return true;
        }
        if (Parser.NamespaceMathml.equals(namespace) && currentElement.nameIs("annotation-xml") && token.isStartTag() && FlexmarkHtmlConverter.SVG_NODE.equals(token.asStartTag().normalName)) {
            return true;
        }
        if (isHtmlIntegration(currentElement) && (token.isStartTag() || token.isCharacter())) {
            return true;
        }
        return token.isEOF();
    }

    static boolean isMathmlTextIntegration(Element element) {
        return Parser.NamespaceMathml.equals(element.tag().namespace()) && StringUtil.inSorted(element.normalName(), TagMathMlTextIntegration);
    }

    static boolean isHtmlIntegration(Element element) {
        if (Parser.NamespaceMathml.equals(element.tag().namespace()) && element.nameIs("annotation-xml")) {
            String normalize = Normalizer.normalize(element.attr("encoding"));
            if (normalize.equals("text/html") || normalize.equals("application/xhtml+xml")) {
                return true;
            }
        }
        if (Parser.NamespaceSvg.equals(element.tag().namespace()) && StringUtil.in(element.tagName(), TagSvgHtmlIntegration)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean process(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        return htmlTreeBuilderState.process(token, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transition(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.state = htmlTreeBuilderState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlTreeBuilderState state() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markInsertionMode() {
        this.originalState = this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void framesetOk(boolean z) {
        this.framesetOk = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean framesetOk() {
        return this.framesetOk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getBaseUri() {
        return this.baseUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void maybeSetBaseUri(Element element) {
        if (this.baseUriSetFromDoc) {
            return;
        }
        String absUrl = element.absUrl("href");
        if (absUrl.length() != 0) {
            this.baseUri = absUrl;
            this.baseUriSetFromDoc = true;
            this.doc.setBaseUri(absUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.parser.getErrors().canAddError()) {
            this.parser.getErrors().add(new ParseError(this.reader, "Unexpected %s token [%s] when in state [%s]", this.currentToken.tokenType(), this.currentToken, htmlTreeBuilderState));
        }
    }

    Element createElementFor(Token.StartTag startTag, String str, boolean z) {
        Attributes attributes = startTag.attributes;
        if (!z) {
            attributes = this.settings.normalizeAttributes(attributes);
        }
        if (attributes != null && !attributes.isEmpty() && attributes.deduplicate(this.settings) > 0) {
            error("Dropped duplicate attribute(s) in tag [%s]", startTag.normalName);
        }
        Tag tagFor = tagFor(startTag.tagName, str, z ? ParseSettings.preserveCase : this.settings);
        return tagFor.normalName().equals("form") ? new FormElement(tagFor, null, attributes) : new Element(tagFor, null, attributes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insertElementFor(Token.StartTag startTag) {
        Element createElementFor = createElementFor(startTag, Parser.NamespaceHtml, false);
        doInsertElement(createElementFor, startTag);
        if (startTag.isSelfClosing()) {
            Tag tag = createElementFor.tag();
            if (tag.isKnownTag()) {
                if (!tag.isEmpty()) {
                    this.tokeniser.error("Tag [%s] cannot be self closing; not a void tag", tag.normalName());
                }
            } else {
                tag.setSelfClosing();
            }
            this.tokeniser.transition(TokeniserState.Data);
            this.tokeniser.emit(this.emptyEnd.reset().name(createElementFor.tagName()));
        }
        return createElementFor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insertForeignElementFor(Token.StartTag startTag, String str) {
        Element createElementFor = createElementFor(startTag, str, true);
        doInsertElement(createElementFor, startTag);
        if (startTag.isSelfClosing()) {
            createElementFor.tag().setSelfClosing();
            pop();
        }
        return createElementFor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element insertEmptyElementFor(Token.StartTag startTag) {
        Element createElementFor = createElementFor(startTag, Parser.NamespaceHtml, false);
        doInsertElement(createElementFor, startTag);
        pop();
        return createElementFor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormElement insertFormElement(Token.StartTag startTag, boolean z, boolean z2) {
        FormElement formElement = (FormElement) createElementFor(startTag, Parser.NamespaceHtml, false);
        if (!z2 || !onStack("template")) {
            setFormElement(formElement);
        }
        doInsertElement(formElement, startTag);
        if (!z) {
            pop();
        }
        return formElement;
    }

    private void doInsertElement(Element element, Token token) {
        if (element.tag().isFormListed() && this.formElement != null) {
            this.formElement.addElement(element);
        }
        if (element.hasAttr("xmlns") && !element.attr("xmlns").equals(element.tag().namespace())) {
            error("Invalid xmlns attribute [%s] on tag [%s]", element.attr("xmlns"), element.tagName());
        }
        if (isFosterInserts() && StringUtil.inSorted(currentElement().normalName(), HtmlTreeBuilderState.Constants.InTableFoster)) {
            insertInFosterParent(element);
        } else {
            currentElement().appendChild(element);
        }
        push(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertCommentNode(Token.Comment comment) {
        Comment comment2 = new Comment(comment.getData());
        currentElement().appendChild(comment2);
        onNodeInserted(comment2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertCharacterNode(Token.Character character) {
        insertCharacterToElement(character, currentElement());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertCharacterToElement(Token.Character character, Element element) {
        Node textNode;
        String normalName = element.normalName();
        String data = character.getData();
        if (character.isCData()) {
            textNode = new CDataNode(data);
        } else if (isContentForTagData(normalName)) {
            textNode = new DataNode(data);
        } else {
            textNode = new TextNode(data);
        }
        element.appendChild(textNode);
        onNodeInserted(textNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<Element> getStack() {
        return this.stack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onStack(Element element) {
        return onStack(this.stack, element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onStack(String str) {
        return getFromStack(str) != null;
    }

    private static boolean onStack(ArrayList<Element> arrayList, Element element) {
        int size = arrayList.size() - 1;
        int i = size >= 256 ? size - 256 : 0;
        for (int i2 = size; i2 >= i; i2--) {
            if (arrayList.get(i2) == element) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getFromStack(String str) {
        int size = this.stack.size() - 1;
        int i = size >= 256 ? size - 256 : 0;
        for (int i2 = size; i2 >= i; i2--) {
            Element element = this.stack.get(i2);
            if (element.elementIs(str, Parser.NamespaceHtml)) {
                return element;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeFromStack(Element element) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                this.stack.remove(size);
                onNodeClosed(element);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element popStackToClose(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element pop = pop();
            if (pop.elementIs(str, Parser.NamespaceHtml)) {
                return pop;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element popStackToCloseAnyNamespace(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element pop = pop();
            if (pop.nameIs(str)) {
                return pop;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popStackToClose(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element pop = pop();
            if (StringUtil.inSorted(pop.normalName(), strArr) && Parser.NamespaceHtml.equals(pop.tag().namespace())) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableContext() {
        clearStackToContext(FlexmarkHtmlConverter.TABLE_NODE, "template");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableBodyContext() {
        clearStackToContext(FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE, "template");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearStackToTableRowContext() {
        clearStackToContext(FlexmarkHtmlConverter.TR_NODE, "template");
    }

    private void clearStackToContext(String... strArr) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            Element element = this.stack.get(size);
            if (!Parser.NamespaceHtml.equals(element.tag().namespace()) || (!StringUtil.in(element.normalName(), strArr) && !element.nameIs("html"))) {
                pop();
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element aboveOnStack(Element element) {
        if (!$assertionsDisabled && !onStack(element)) {
            throw new AssertionError();
        }
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            if (this.stack.get(size) == element) {
                return this.stack.get(size - 1);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertOnStackAfter(Element element, Element element2) {
        int lastIndexOf = this.stack.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        this.stack.add(lastIndexOf + 1, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceOnStack(Element element, Element element2) {
        replaceInQueue(this.stack, element, element2);
    }

    private static void replaceInQueue(ArrayList<Element> arrayList, Element element, Element element2) {
        int lastIndexOf = arrayList.lastIndexOf(element);
        Validate.isTrue(lastIndexOf != -1);
        arrayList.set(lastIndexOf, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0089. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:68:0x0204. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02ea A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02f4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean resetInsertionMode() {
        /*
            Method dump skipped, instructions count: 775
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.resetInsertionMode():boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetBody() {
        if (!onStack("body")) {
            this.stack.add(this.doc.body());
        }
        transition(HtmlTreeBuilderState.InBody);
    }

    private boolean inSpecificScope(String str, String[] strArr, String[] strArr2) {
        this.specificScopeTarget[0] = str;
        return inSpecificScope(this.specificScopeTarget, strArr, strArr2);
    }

    private boolean inSpecificScope(String[] strArr, String[] strArr2, String[] strArr3) {
        int size = this.stack.size() - 1;
        int i = size > 100 ? size - 100 : 0;
        for (int i2 = size; i2 >= i; i2--) {
            Element element = this.stack.get(i2);
            if (element.tag().namespace().equals(Parser.NamespaceHtml)) {
                String normalName = element.normalName();
                if (StringUtil.inSorted(normalName, strArr)) {
                    return true;
                }
                if (StringUtil.inSorted(normalName, strArr2)) {
                    return false;
                }
                if (strArr3 != null && StringUtil.inSorted(normalName, strArr3)) {
                    return false;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inScope(String[] strArr) {
        return inSpecificScope(strArr, TagsSearchInScope, (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inScope(String str) {
        return inScope(str, null);
    }

    boolean inScope(String str, String[] strArr) {
        return inSpecificScope(str, TagsSearchInScope, strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inListItemScope(String str) {
        return inScope(str, TagSearchList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inButtonScope(String str) {
        return inScope(str, TagSearchButton);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inTableScope(String str) {
        return inSpecificScope(str, TagSearchTableScope, (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean inSelectScope(String str) {
        for (int size = this.stack.size() - 1; size >= 0; size--) {
            String normalName = this.stack.get(size).normalName();
            if (normalName.equals(str)) {
                return true;
            }
            if (!StringUtil.inSorted(normalName, TagSearchSelectScope)) {
                return false;
            }
        }
        Validate.fail("Should not be reachable");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onStackNot(String[] strArr) {
        int size = this.stack.size() - 1;
        int i = size > 100 ? size - 100 : 0;
        for (int i2 = size; i2 >= i; i2--) {
            if (!StringUtil.inSorted(this.stack.get(i2).normalName(), strArr)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHeadElement(Element element) {
        this.headElement = element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getHeadElement() {
        return this.headElement;
    }

    boolean isFosterInserts() {
        return this.fosterInserts;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFosterInserts(boolean z) {
        this.fosterInserts = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FormElement getFormElement() {
        return this.formElement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetPendingTableCharacters() {
        this.pendingTableCharacters.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Token.Character> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPendingTableCharacters(Token.Character character) {
        this.pendingTableCharacters.add(character.m2551clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateImpliedEndTags(String str) {
        while (StringUtil.inSorted(currentElement().normalName(), TagSearchEndTags)) {
            if (str == null || !currentElementIs(str)) {
                pop();
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateImpliedEndTags() {
        generateImpliedEndTags(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void generateImpliedEndTags(boolean z) {
        String[] strArr = z ? TagThoroughSearchEndTags : TagSearchEndTags;
        while (Parser.NamespaceHtml.equals(currentElement().tag().namespace()) && StringUtil.inSorted(currentElement().normalName(), strArr)) {
            pop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeElement(String str) {
        generateImpliedEndTags(str);
        if (!str.equals(currentElement().normalName())) {
            error(state());
        }
        popStackToClose(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSpecial(Element element) {
        return StringUtil.inSorted(element.normalName(), TagSearchSpecial);
    }

    Element lastFormattingElement() {
        if (this.formattingElements.size() > 0) {
            return this.formattingElements.get(this.formattingElements.size() - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int positionOfElement(Element element) {
        for (int i = 0; i < this.formattingElements.size(); i++) {
            if (element == this.formattingElements.get(i)) {
                return i;
            }
        }
        return -1;
    }

    Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size > 0) {
            return this.formattingElements.remove(size - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushActiveFormattingElements(Element element) {
        checkActiveFormattingElements(element);
        this.formattingElements.add(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushWithBookmark(Element element, int i) {
        checkActiveFormattingElements(element);
        try {
            this.formattingElements.add(i, element);
        } catch (IndexOutOfBoundsException unused) {
            this.formattingElements.add(element);
        }
    }

    void checkActiveFormattingElements(Element element) {
        Element element2;
        int i = 0;
        int size = this.formattingElements.size() - 1;
        int i2 = size - 12;
        int i3 = i2;
        if (i2 < 0) {
            i3 = 0;
        }
        for (int i4 = size; i4 >= i3 && (element2 = this.formattingElements.get(i4)) != null; i4--) {
            if (isSameFormattingElement(element, element2)) {
                i++;
            }
            if (i == 3) {
                this.formattingElements.remove(i4);
                return;
            }
        }
    }

    private static boolean isSameFormattingElement(Element element, Element element2) {
        return element.normalName().equals(element2.normalName()) && element.attributes().equals(element2.attributes());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reconstructFormattingElements() {
        Element lastFormattingElement;
        if (this.stack.size() > 256 || (lastFormattingElement = lastFormattingElement()) == null || onStack(lastFormattingElement)) {
            return;
        }
        Element element = lastFormattingElement;
        int size = this.formattingElements.size();
        int i = size - 12;
        int i2 = i;
        if (i < 0) {
            i2 = 0;
        }
        int i3 = size - 1;
        boolean z = false;
        while (true) {
            if (i3 == i2) {
                z = true;
                break;
            }
            i3--;
            Element element2 = this.formattingElements.get(i3);
            element = element2;
            if (element2 == null || onStack(element)) {
                break;
            }
        }
        do {
            if (!z) {
                i3++;
                element = this.formattingElements.get(i3);
            }
            Validate.notNull(element);
            z = false;
            Element element3 = new Element(tagFor(element.normalName(), this.settings), null, element.attributes().m2529clone());
            doInsertElement(element3, null);
            this.formattingElements.set(i3, element3);
        } while (i3 != size - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearFormattingElementsToLastMarker() {
        while (!this.formattingElements.isEmpty() && removeLastFormattingElement() != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeFromActiveFormattingElements(Element element) {
        for (int size = this.formattingElements.size() - 1; size >= 0; size--) {
            if (this.formattingElements.get(size) == element) {
                this.formattingElements.remove(size);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInActiveFormattingElements(Element element) {
        return onStack(this.formattingElements, element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element getActiveFormattingElement(String str) {
        Element element;
        for (int size = this.formattingElements.size() - 1; size >= 0 && (element = this.formattingElements.get(size)) != null; size--) {
            if (element.nameIs(str)) {
                return element;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceActiveFormattingElement(Element element, Element element2) {
        replaceInQueue(this.formattingElements, element, element2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertInFosterParent(Node node) {
        Element element;
        Element fromStack = getFromStack(FlexmarkHtmlConverter.TABLE_NODE);
        boolean z = false;
        if (fromStack != null) {
            if (fromStack.parent() != null) {
                fromStack.parent();
                element = null;
                z = true;
            } else {
                element = aboveOnStack(fromStack);
            }
        } else {
            element = this.stack.get(0);
        }
        if (z) {
            Validate.notNull(fromStack);
            fromStack.before(node);
        } else {
            element.appendChild(node);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushTemplateMode(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.tmplInsertMode.add(htmlTreeBuilderState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlTreeBuilderState popTemplateMode() {
        if (this.tmplInsertMode.size() > 0) {
            return this.tmplInsertMode.remove(this.tmplInsertMode.size() - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int templateModeSize() {
        return this.tmplInsertMode.size();
    }

    HtmlTreeBuilderState currentTemplateMode() {
        if (this.tmplInsertMode.size() > 0) {
            return this.tmplInsertMode.get(this.tmplInsertMode.size() - 1);
        }
        return null;
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + currentElement() + '}';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public boolean isContentForTagData(String str) {
        return str.equals("script") || str.equals(Attribute.STYLE_ATTR);
    }
}

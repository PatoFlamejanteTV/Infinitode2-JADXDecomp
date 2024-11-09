package org.jsoup.parser;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.Token;
import org.lwjgl.opengl.GL11;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/jsoup/parser/HtmlTreeBuilderState.class */
public enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                Token.Doctype asDoctype = token.asDoctype();
                DocumentType documentType = new DocumentType(htmlTreeBuilder.settings.normalizeTag(asDoctype.getName()), asDoctype.getPublicIdentifier(), asDoctype.getSystemIdentifier());
                documentType.setPubSysKey(asDoctype.getPubSysKey());
                htmlTreeBuilder.getDocument().appendChild(documentType);
                htmlTreeBuilder.onNodeInserted(documentType);
                if (asDoctype.isForceQuirks()) {
                    htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(BeforeHtml);
                return true;
            }
            htmlTreeBuilder.transition(BeforeHtml);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!token.isComment()) {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                    return true;
                }
                if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                    htmlTreeBuilder.insertElementFor(token.asStartTag());
                    htmlTreeBuilder.transition(BeforeHead);
                    return true;
                }
                if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if (token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.insertCommentNode(token.asComment());
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag("html");
            htmlTreeBuilder.transition(BeforeHead);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("head")) {
                htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insertElementFor(token.asStartTag()));
                htmlTreeBuilder.transition(InHead);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.BeforeHtmlToHead)) {
                htmlTreeBuilder.processStartTag("head");
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.processStartTag("head");
            return htmlTreeBuilder.process(token);
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            switch (token.type) {
                case Comment:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case Doctype:
                    htmlTreeBuilder.error(this);
                    return false;
                case StartTag:
                    Token.StartTag asStartTag = token.asStartTag();
                    String normalName = asStartTag.normalName();
                    if (normalName.equals("html")) {
                        return InBody.process(token, htmlTreeBuilder);
                    }
                    if (!StringUtil.inSorted(normalName, Constants.InHeadEmpty)) {
                        if (normalName.equals("meta")) {
                            htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                            return true;
                        }
                        if (normalName.equals(Attribute.TITLE_ATTR)) {
                            HtmlTreeBuilderState.handleRcData(asStartTag, htmlTreeBuilder);
                            return true;
                        }
                        if (StringUtil.inSorted(normalName, Constants.InHeadRaw)) {
                            HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                            return true;
                        }
                        if (normalName.equals("noscript")) {
                            htmlTreeBuilder.insertElementFor(asStartTag);
                            htmlTreeBuilder.transition(InHeadNoscript);
                            return true;
                        }
                        if (normalName.equals("script")) {
                            htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                            htmlTreeBuilder.markInsertionMode();
                            htmlTreeBuilder.transition(Text);
                            htmlTreeBuilder.insertElementFor(asStartTag);
                            return true;
                        }
                        if (normalName.equals("head")) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        if (normalName.equals("template")) {
                            htmlTreeBuilder.insertElementFor(asStartTag);
                            htmlTreeBuilder.insertMarkerToFormattingElements();
                            htmlTreeBuilder.framesetOk(false);
                            htmlTreeBuilder.transition(InTemplate);
                            htmlTreeBuilder.pushTemplateMode(InTemplate);
                            return true;
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    Element insertEmptyElementFor = htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                    if (normalName.equals("base") && insertEmptyElementFor.hasAttr("href")) {
                        htmlTreeBuilder.maybeSetBaseUri(insertEmptyElementFor);
                        return true;
                    }
                    return true;
                case EndTag:
                    String normalName2 = token.asEndTag().normalName();
                    if (normalName2.equals("head")) {
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(AfterHead);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName2, Constants.InHeadEnd)) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if (normalName2.equals("template")) {
                        if (!htmlTreeBuilder.onStack(normalName2)) {
                            htmlTreeBuilder.error(this);
                            return true;
                        }
                        htmlTreeBuilder.generateImpliedEndTags(true);
                        if (!htmlTreeBuilder.currentElementIs(normalName2)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(normalName2);
                        htmlTreeBuilder.clearFormattingElementsToLastMarker();
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.resetInsertionMode();
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.processEndTag("head");
            return treeBuilder.process(token);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (!token.isEndTag() || !token.asEndTag().normalName().equals("noscript")) {
                if (HtmlTreeBuilderState.isWhitespace(token) || token.isComment() || (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoScriptHead))) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                if (token.isEndTag() && token.asEndTag().normalName().equals(FlexmarkHtmlConverter.BR_NODE)) {
                    return anythingElse(token, htmlTreeBuilder);
                }
                if ((token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InHeadNoscriptIgnore)) || token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(InHead);
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.insertCharacterNode(new Token.Character().data(token.toString()));
            return true;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            }
            if (!token.isStartTag()) {
                if (token.isEndTag()) {
                    String normalName = token.asEndTag().normalName();
                    if (StringUtil.inSorted(normalName, Constants.AfterHeadBody)) {
                        anythingElse(token, htmlTreeBuilder);
                        return true;
                    }
                    if (normalName.equals("template")) {
                        htmlTreeBuilder.process(token, InHead);
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
                anythingElse(token, htmlTreeBuilder);
                return true;
            }
            Token.StartTag asStartTag = token.asStartTag();
            String normalName2 = asStartTag.normalName();
            if (normalName2.equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (normalName2.equals("body")) {
                htmlTreeBuilder.insertElementFor(asStartTag);
                htmlTreeBuilder.framesetOk(false);
                htmlTreeBuilder.transition(InBody);
                return true;
            }
            if (normalName2.equals("frameset")) {
                htmlTreeBuilder.insertElementFor(asStartTag);
                htmlTreeBuilder.transition(InFrameset);
                return true;
            }
            if (!StringUtil.inSorted(normalName2, Constants.InBodyStartToHead)) {
                if (normalName2.equals("head")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                anythingElse(token, htmlTreeBuilder);
                return true;
            }
            htmlTreeBuilder.error(this);
            Element headElement = htmlTreeBuilder.getHeadElement();
            htmlTreeBuilder.push(headElement);
            htmlTreeBuilder.process(token, InHead);
            htmlTreeBuilder.removeFromStack(headElement);
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.processStartTag("body");
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        private static final int MaxStackScan = 24;

        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (token.type) {
                case Comment:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case Doctype:
                    htmlTreeBuilder.error(this);
                    return false;
                case StartTag:
                    return inBodyStartTag(token, htmlTreeBuilder);
                case EndTag:
                    return inBodyEndTag(token, htmlTreeBuilder);
                case Character:
                    Token.Character asCharacter = token.asCharacter();
                    if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.framesetOk() && HtmlTreeBuilderState.isWhitespace(asCharacter)) {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insertCharacterNode(asCharacter);
                        return true;
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertCharacterNode(asCharacter);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case EOF:
                    if (htmlTreeBuilder.templateModeSize() > 0) {
                        return htmlTreeBuilder.process(token, InTemplate);
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }

        private boolean inBodyStartTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String str;
            FormElement formElement;
            Element fromStack;
            Token.StartTag asStartTag = token.asStartTag();
            String normalName = asStartTag.normalName();
            boolean z = -1;
            switch (normalName.hashCode()) {
                case -1644953643:
                    if (normalName.equals("frameset")) {
                        z = 5;
                        break;
                    }
                    break;
                case -1377687758:
                    if (normalName.equals("button")) {
                        z = 8;
                        break;
                    }
                    break;
                case -1191214428:
                    if (normalName.equals("iframe")) {
                        z = 17;
                        break;
                    }
                    break;
                case -1134665583:
                    if (normalName.equals("keygen")) {
                        z = 42;
                        break;
                    }
                    break;
                case -1010136971:
                    if (normalName.equals("option")) {
                        z = 33;
                        break;
                    }
                    break;
                case -1003243718:
                    if (normalName.equals("textarea")) {
                        z = 15;
                        break;
                    }
                    break;
                case -906021636:
                    if (normalName.equals("select")) {
                        z = 19;
                        break;
                    }
                    break;
                case -891985998:
                    if (normalName.equals(FlexmarkHtmlConverter.STRIKE_NODE)) {
                        z = 52;
                        break;
                    }
                    break;
                case -891980137:
                    if (normalName.equals(FlexmarkHtmlConverter.STRONG_NODE)) {
                        z = 53;
                        break;
                    }
                    break;
                case -80773204:
                    if (normalName.equals("optgroup")) {
                        z = 32;
                        break;
                    }
                    break;
                case 97:
                    if (normalName.equals(FlexmarkHtmlConverter.A_NODE)) {
                        z = false;
                        break;
                    }
                    break;
                case 98:
                    if (normalName.equals(FlexmarkHtmlConverter.B_NODE)) {
                        z = 44;
                        break;
                    }
                    break;
                case 105:
                    if (normalName.equals(FlexmarkHtmlConverter.I_NODE)) {
                        z = 49;
                        break;
                    }
                    break;
                case 115:
                    if (normalName.equals("s")) {
                        z = 50;
                        break;
                    }
                    break;
                case 117:
                    if (normalName.equals(FlexmarkHtmlConverter.U_NODE)) {
                        z = 55;
                        break;
                    }
                    break;
                case GL11.GL_PERSPECTIVE_CORRECTION_HINT /* 3152 */:
                    if (normalName.equals(FlexmarkHtmlConverter.BR_NODE)) {
                        z = 39;
                        break;
                    }
                    break;
                case 3200:
                    if (normalName.equals(FlexmarkHtmlConverter.DD_NODE)) {
                        z = 30;
                        break;
                    }
                    break;
                case 3216:
                    if (normalName.equals(FlexmarkHtmlConverter.DT_NODE)) {
                        z = 31;
                        break;
                    }
                    break;
                case 3240:
                    if (normalName.equals(FlexmarkHtmlConverter.EM_NODE)) {
                        z = 47;
                        break;
                    }
                    break;
                case 3273:
                    if (normalName.equals(FlexmarkHtmlConverter.H1_NODE)) {
                        z = 22;
                        break;
                    }
                    break;
                case 3274:
                    if (normalName.equals(FlexmarkHtmlConverter.H2_NODE)) {
                        z = 23;
                        break;
                    }
                    break;
                case 3275:
                    if (normalName.equals(FlexmarkHtmlConverter.H3_NODE)) {
                        z = 24;
                        break;
                    }
                    break;
                case 3276:
                    if (normalName.equals(FlexmarkHtmlConverter.H4_NODE)) {
                        z = 25;
                        break;
                    }
                    break;
                case 3277:
                    if (normalName.equals(FlexmarkHtmlConverter.H5_NODE)) {
                        z = 26;
                        break;
                    }
                    break;
                case 3278:
                    if (normalName.equals(FlexmarkHtmlConverter.H6_NODE)) {
                        z = 27;
                        break;
                    }
                    break;
                case 3338:
                    if (normalName.equals(FlexmarkHtmlConverter.HR_NODE)) {
                        z = 12;
                        break;
                    }
                    break;
                case 3453:
                    if (normalName.equals(FlexmarkHtmlConverter.LI_NODE)) {
                        z = 2;
                        break;
                    }
                    break;
                case 3632:
                    if (normalName.equals("rb")) {
                        z = 34;
                        break;
                    }
                    break;
                case 3646:
                    if (normalName.equals("rp")) {
                        z = 36;
                        break;
                    }
                    break;
                case 3650:
                    if (normalName.equals("rt")) {
                        z = 37;
                        break;
                    }
                    break;
                case 3712:
                    if (normalName.equals("tt")) {
                        z = 54;
                        break;
                    }
                    break;
                case 97536:
                    if (normalName.equals("big")) {
                        z = 45;
                        break;
                    }
                    break;
                case 104387:
                    if (normalName.equals(FlexmarkHtmlConverter.IMG_NODE)) {
                        z = 41;
                        break;
                    }
                    break;
                case 111267:
                    if (normalName.equals(FlexmarkHtmlConverter.PRE_NODE)) {
                        z = 28;
                        break;
                    }
                    break;
                case 113249:
                    if (normalName.equals("rtc")) {
                        z = 35;
                        break;
                    }
                    break;
                case 114276:
                    if (normalName.equals(FlexmarkHtmlConverter.SVG_NODE)) {
                        z = 21;
                        break;
                    }
                    break;
                case 117511:
                    if (normalName.equals("wbr")) {
                        z = 43;
                        break;
                    }
                    break;
                case 118811:
                    if (normalName.equals("xmp")) {
                        z = 16;
                        break;
                    }
                    break;
                case 3002509:
                    if (normalName.equals("area")) {
                        z = 38;
                        break;
                    }
                    break;
                case 3029410:
                    if (normalName.equals("body")) {
                        z = 4;
                        break;
                    }
                    break;
                case 3059181:
                    if (normalName.equals(FlexmarkHtmlConverter.CODE_NODE)) {
                        z = 46;
                        break;
                    }
                    break;
                case 3148879:
                    if (normalName.equals("font")) {
                        z = 48;
                        break;
                    }
                    break;
                case 3148996:
                    if (normalName.equals("form")) {
                        z = 6;
                        break;
                    }
                    break;
                case 3213227:
                    if (normalName.equals("html")) {
                        z = 3;
                        break;
                    }
                    break;
                case 3344136:
                    if (normalName.equals(FlexmarkHtmlConverter.MATH_NODE)) {
                        z = 20;
                        break;
                    }
                    break;
                case 3386833:
                    if (normalName.equals("nobr")) {
                        z = 9;
                        break;
                    }
                    break;
                case 3536714:
                    if (normalName.equals(FlexmarkHtmlConverter.SPAN_NODE)) {
                        z = true;
                        break;
                    }
                    break;
                case 96620249:
                    if (normalName.equals("embed")) {
                        z = 40;
                        break;
                    }
                    break;
                case 100313435:
                    if (normalName.equals("image")) {
                        z = 13;
                        break;
                    }
                    break;
                case 100358090:
                    if (normalName.equals(FlexmarkHtmlConverter.INPUT_NODE)) {
                        z = 11;
                        break;
                    }
                    break;
                case 109548807:
                    if (normalName.equals("small")) {
                        z = 51;
                        break;
                    }
                    break;
                case 110115790:
                    if (normalName.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                        z = 10;
                        break;
                    }
                    break;
                case 181975684:
                    if (normalName.equals("listing")) {
                        z = 29;
                        break;
                    }
                    break;
                case 1973234167:
                    if (normalName.equals("plaintext")) {
                        z = 7;
                        break;
                    }
                    break;
                case 2091304424:
                    if (normalName.equals("isindex")) {
                        z = 14;
                        break;
                    }
                    break;
                case 2115613112:
                    if (normalName.equals("noembed")) {
                        z = 18;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    if (htmlTreeBuilder.getActiveFormattingElement(FlexmarkHtmlConverter.A_NODE) != null) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.A_NODE);
                        Element fromStack2 = htmlTreeBuilder.getFromStack(FlexmarkHtmlConverter.A_NODE);
                        if (fromStack2 != null) {
                            htmlTreeBuilder.removeFromActiveFormattingElements(fromStack2);
                            htmlTreeBuilder.removeFromStack(fromStack2);
                        }
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(asStartTag));
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                    htmlTreeBuilder.framesetOk(false);
                    ArrayList<Element> stack = htmlTreeBuilder.getStack();
                    int size = stack.size() - 1;
                    while (true) {
                        if (size > 0) {
                            Element element = stack.get(size);
                            if (element.nameIs(FlexmarkHtmlConverter.LI_NODE)) {
                                htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.LI_NODE);
                            } else if (!HtmlTreeBuilder.isSpecial(element) || StringUtil.inSorted(element.normalName(), Constants.InBodyStartLiBreakers)) {
                                size--;
                            }
                        }
                    }
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.onStack("template")) {
                        return false;
                    }
                    if (htmlTreeBuilder.getStack().size() > 0) {
                        Element element2 = htmlTreeBuilder.getStack().get(0);
                        if (asStartTag.hasAttributes()) {
                            Iterator<org.jsoup.nodes.Attribute> it = asStartTag.attributes.iterator();
                            while (it.hasNext()) {
                                org.jsoup.nodes.Attribute next = it.next();
                                if (!element2.hasAttr(next.getKey())) {
                                    element2.attributes().put(next);
                                }
                            }
                            return true;
                        }
                        return true;
                    }
                    return true;
                case true:
                    htmlTreeBuilder.error(this);
                    ArrayList<Element> stack2 = htmlTreeBuilder.getStack();
                    if (stack2.size() != 1) {
                        if ((stack2.size() > 2 && !stack2.get(1).nameIs("body")) || htmlTreeBuilder.onStack("template")) {
                            return false;
                        }
                        htmlTreeBuilder.framesetOk(false);
                        if (asStartTag.hasAttributes() && (fromStack = htmlTreeBuilder.getFromStack("body")) != null) {
                            Iterator<org.jsoup.nodes.Attribute> it2 = asStartTag.attributes.iterator();
                            while (it2.hasNext()) {
                                org.jsoup.nodes.Attribute next2 = it2.next();
                                if (!fromStack.hasAttr(next2.getKey())) {
                                    fromStack.attributes().put(next2);
                                }
                            }
                            return true;
                        }
                        return true;
                    }
                    return false;
                case true:
                    htmlTreeBuilder.error(this);
                    ArrayList<Element> stack3 = htmlTreeBuilder.getStack();
                    if (stack3.size() != 1) {
                        if ((stack3.size() > 2 && !stack3.get(1).nameIs("body")) || !htmlTreeBuilder.framesetOk()) {
                            return false;
                        }
                        Element element3 = stack3.get(1);
                        if (element3.parent() != null) {
                            element3.remove();
                        }
                        while (stack3.size() > 1) {
                            stack3.remove(stack3.size() - 1);
                        }
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        htmlTreeBuilder.transition(InFrameset);
                        return true;
                    }
                    return false;
                case true:
                    if (htmlTreeBuilder.getFormElement() != null && !htmlTreeBuilder.onStack("template")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.closeElement(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertFormElement(asStartTag, true, true);
                    return true;
                case true:
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.tokeniser.transition(TokeniserState.PLAINTEXT);
                    return true;
                case true:
                    if (htmlTreeBuilder.inButtonScope("button")) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag("button");
                        htmlTreeBuilder.process(asStartTag);
                        return true;
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    if (htmlTreeBuilder.inScope("nobr")) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processEndTag("nobr");
                        htmlTreeBuilder.reconstructFormattingElements();
                    }
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(asStartTag));
                    return true;
                case true:
                    if (htmlTreeBuilder.getDocument().quirksMode() != Document.QuirksMode.quirks && htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InTable);
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    if (!htmlTreeBuilder.insertEmptyElementFor(asStartTag).attr("type").equalsIgnoreCase("hidden")) {
                        htmlTreeBuilder.framesetOk(false);
                        return true;
                    }
                    return true;
                case true:
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case true:
                    if (htmlTreeBuilder.getFromStack(FlexmarkHtmlConverter.SVG_NODE) == null) {
                        return htmlTreeBuilder.process(asStartTag.name(FlexmarkHtmlConverter.IMG_NODE));
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.getFormElement() != null) {
                        return false;
                    }
                    htmlTreeBuilder.processStartTag("form");
                    if (asStartTag.hasAttribute("action") && (formElement = htmlTreeBuilder.getFormElement()) != null && asStartTag.hasAttribute("action")) {
                        formElement.attributes().put("action", asStartTag.attributes.get("action"));
                    }
                    htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.HR_NODE);
                    htmlTreeBuilder.processStartTag("label");
                    if (asStartTag.hasAttribute("prompt")) {
                        str = asStartTag.attributes.get("prompt");
                    } else {
                        str = "This is a searchable index. Enter search keywords: ";
                    }
                    htmlTreeBuilder.process(new Token.Character().data(str));
                    Attributes attributes = new Attributes();
                    if (asStartTag.hasAttributes()) {
                        Iterator<org.jsoup.nodes.Attribute> it3 = asStartTag.attributes.iterator();
                        while (it3.hasNext()) {
                            org.jsoup.nodes.Attribute next3 = it3.next();
                            if (!StringUtil.inSorted(next3.getKey(), Constants.InBodyStartInputAttribs)) {
                                attributes.put(next3);
                            }
                        }
                    }
                    attributes.put(Attribute.NAME_ATTR, "isindex");
                    htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.INPUT_NODE, attributes);
                    htmlTreeBuilder.processEndTag("label");
                    htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.HR_NODE);
                    htmlTreeBuilder.processEndTag("form");
                    return true;
                case true:
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    if (!asStartTag.isSelfClosing()) {
                        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
                        htmlTreeBuilder.markInsertionMode();
                        htmlTreeBuilder.framesetOk(false);
                        htmlTreeBuilder.transition(Text);
                        return true;
                    }
                    return true;
                case true:
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                    return true;
                case true:
                    htmlTreeBuilder.framesetOk(false);
                    HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                    return true;
                case true:
                    HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    if (!asStartTag.selfClosing) {
                        HtmlTreeBuilderState state = htmlTreeBuilder.state();
                        if (state.equals(InTable) || state.equals(InCaption) || state.equals(InTableBody) || state.equals(InRow) || state.equals(InCell)) {
                            htmlTreeBuilder.transition(InSelectInTable);
                            return true;
                        }
                        htmlTreeBuilder.transition(InSelect);
                        return true;
                    }
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertForeignElementFor(asStartTag, Parser.NamespaceMathml);
                    return true;
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertForeignElementFor(asStartTag, Parser.NamespaceSvg);
                    return true;
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.Headings)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.pop();
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                case true:
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.reader.matchConsume(SequenceUtils.EOL);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case true:
                case true:
                    htmlTreeBuilder.framesetOk(false);
                    ArrayList<Element> stack4 = htmlTreeBuilder.getStack();
                    int size2 = stack4.size() - 1;
                    int i = size2 >= 24 ? size2 - 24 : 0;
                    int i2 = size2;
                    while (true) {
                        if (i2 >= i) {
                            Element element4 = stack4.get(i2);
                            if (StringUtil.inSorted(element4.normalName(), Constants.DdDt)) {
                                htmlTreeBuilder.processEndTag(element4.normalName());
                            } else if (!HtmlTreeBuilder.isSpecial(element4) || StringUtil.inSorted(element4.normalName(), Constants.InBodyStartLiBreakers)) {
                                i2--;
                            }
                        }
                    }
                    if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                        htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                case true:
                    if (htmlTreeBuilder.currentElementIs("option")) {
                        htmlTreeBuilder.processEndTag("option");
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                case true:
                    if (htmlTreeBuilder.inScope("ruby")) {
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs("ruby")) {
                            htmlTreeBuilder.error(this);
                        }
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                case true:
                    if (htmlTreeBuilder.inScope("ruby")) {
                        htmlTreeBuilder.generateImpliedEndTags("rtc");
                        if (!htmlTreeBuilder.currentElementIs("rtc") && !htmlTreeBuilder.currentElementIs("ruby")) {
                            htmlTreeBuilder.error(this);
                        }
                    }
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    return true;
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.pushActiveFormattingElements(htmlTreeBuilder.insertElementFor(asStartTag));
                    return true;
                default:
                    if (!Tag.isKnownTag(normalName)) {
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartPClosers)) {
                        if (htmlTreeBuilder.inButtonScope(FlexmarkHtmlConverter.P_NODE)) {
                            htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.P_NODE);
                        }
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartToHead)) {
                        return htmlTreeBuilder.process(token, InHead);
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartApplets)) {
                        htmlTreeBuilder.reconstructFormattingElements();
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        htmlTreeBuilder.insertMarkerToFormattingElements();
                        htmlTreeBuilder.framesetOk(false);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartMedia)) {
                        htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartDrop)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.reconstructFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    return true;
            }
        }

        private boolean inBodyEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Token.EndTag asEndTag = token.asEndTag();
            String normalName = asEndTag.normalName();
            boolean z = -1;
            switch (normalName.hashCode()) {
                case -1321546630:
                    if (normalName.equals("template")) {
                        z = false;
                        break;
                    }
                    break;
                case 112:
                    if (normalName.equals(FlexmarkHtmlConverter.P_NODE)) {
                        z = 7;
                        break;
                    }
                    break;
                case GL11.GL_PERSPECTIVE_CORRECTION_HINT /* 3152 */:
                    if (normalName.equals(FlexmarkHtmlConverter.BR_NODE)) {
                        z = 16;
                        break;
                    }
                    break;
                case 3200:
                    if (normalName.equals(FlexmarkHtmlConverter.DD_NODE)) {
                        z = 8;
                        break;
                    }
                    break;
                case 3216:
                    if (normalName.equals(FlexmarkHtmlConverter.DT_NODE)) {
                        z = 9;
                        break;
                    }
                    break;
                case 3273:
                    if (normalName.equals(FlexmarkHtmlConverter.H1_NODE)) {
                        z = 10;
                        break;
                    }
                    break;
                case 3274:
                    if (normalName.equals(FlexmarkHtmlConverter.H2_NODE)) {
                        z = 11;
                        break;
                    }
                    break;
                case 3275:
                    if (normalName.equals(FlexmarkHtmlConverter.H3_NODE)) {
                        z = 12;
                        break;
                    }
                    break;
                case 3276:
                    if (normalName.equals(FlexmarkHtmlConverter.H4_NODE)) {
                        z = 13;
                        break;
                    }
                    break;
                case 3277:
                    if (normalName.equals(FlexmarkHtmlConverter.H5_NODE)) {
                        z = 14;
                        break;
                    }
                    break;
                case 3278:
                    if (normalName.equals(FlexmarkHtmlConverter.H6_NODE)) {
                        z = 15;
                        break;
                    }
                    break;
                case 3453:
                    if (normalName.equals(FlexmarkHtmlConverter.LI_NODE)) {
                        z = 3;
                        break;
                    }
                    break;
                case 3029410:
                    if (normalName.equals("body")) {
                        z = 4;
                        break;
                    }
                    break;
                case 3148996:
                    if (normalName.equals("form")) {
                        z = 6;
                        break;
                    }
                    break;
                case 3213227:
                    if (normalName.equals("html")) {
                        z = 5;
                        break;
                    }
                    break;
                case 3536714:
                    if (normalName.equals(FlexmarkHtmlConverter.SPAN_NODE)) {
                        z = 2;
                        break;
                    }
                    break;
                case 1869063452:
                    if (normalName.equals("sarcasm")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    htmlTreeBuilder.process(token, InHead);
                    return true;
                case true:
                case true:
                    return anyOtherEndTag(token, htmlTreeBuilder);
                case true:
                    if (!htmlTreeBuilder.inListItemScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(normalName);
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    return true;
                case true:
                    if (!htmlTreeBuilder.inScope("body")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.onNodeClosed(htmlTreeBuilder.getFromStack("body"));
                    htmlTreeBuilder.transition(AfterBody);
                    return true;
                case true:
                    if (!htmlTreeBuilder.onStack("body")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (htmlTreeBuilder.onStackNot(Constants.InBodyEndOtherErrors)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.transition(AfterBody);
                    return htmlTreeBuilder.process(token);
                case true:
                    if (!htmlTreeBuilder.onStack("template")) {
                        FormElement formElement = htmlTreeBuilder.getFormElement();
                        htmlTreeBuilder.setFormElement(null);
                        if (formElement == null || !htmlTreeBuilder.inScope(normalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(normalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.removeFromStack(formElement);
                        return true;
                    }
                    if (!htmlTreeBuilder.inScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    return true;
                case true:
                    if (!htmlTreeBuilder.inButtonScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processStartTag(normalName);
                        return htmlTreeBuilder.process(asEndTag);
                    }
                    htmlTreeBuilder.generateImpliedEndTags(normalName);
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    return true;
                case true:
                case true:
                    if (!htmlTreeBuilder.inScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(normalName);
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    return true;
                case true:
                case true:
                case true:
                case true:
                case true:
                case true:
                    if (!htmlTreeBuilder.inScope(Constants.Headings)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags(normalName);
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(Constants.Headings);
                    return true;
                case true:
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.BR_NODE);
                    return false;
                default:
                    if (StringUtil.inSorted(normalName, Constants.InBodyEndAdoptionFormatters)) {
                        return inBodyEndTagAdoption(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyEndClosers)) {
                        if (!htmlTreeBuilder.inScope(normalName)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.generateImpliedEndTags();
                        if (!htmlTreeBuilder.currentElementIs(normalName)) {
                            htmlTreeBuilder.error(this);
                        }
                        htmlTreeBuilder.popStackToClose(normalName);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InBodyStartApplets)) {
                        if (!htmlTreeBuilder.inScope(Attribute.NAME_ATTR)) {
                            if (!htmlTreeBuilder.inScope(normalName)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.generateImpliedEndTags();
                            if (!htmlTreeBuilder.currentElementIs(normalName)) {
                                htmlTreeBuilder.error(this);
                            }
                            htmlTreeBuilder.popStackToClose(normalName);
                            htmlTreeBuilder.clearFormattingElementsToLastMarker();
                            return true;
                        }
                        return true;
                    }
                    return anyOtherEndTag(token, htmlTreeBuilder);
            }
        }

        final boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String str = token.asEndTag().normalName;
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            if (htmlTreeBuilder.getFromStack(str) == null) {
                htmlTreeBuilder.error(this);
                return false;
            }
            for (int size = stack.size() - 1; size >= 0; size--) {
                Element element = stack.get(size);
                if (element.nameIs(str)) {
                    htmlTreeBuilder.generateImpliedEndTags(str);
                    if (!htmlTreeBuilder.currentElementIs(str)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(str);
                    return true;
                }
                if (HtmlTreeBuilder.isSpecial(element)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }

        private boolean inBodyEndTagAdoption(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String normalName = token.asEndTag().normalName();
            ArrayList<Element> stack = htmlTreeBuilder.getStack();
            for (int i = 0; i < 8; i++) {
                Element activeFormattingElement = htmlTreeBuilder.getActiveFormattingElement(normalName);
                if (activeFormattingElement == null) {
                    return anyOtherEndTag(token, htmlTreeBuilder);
                }
                if (!htmlTreeBuilder.onStack(activeFormattingElement)) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    return true;
                }
                if (htmlTreeBuilder.inScope(activeFormattingElement.normalName())) {
                    if (htmlTreeBuilder.currentElement() != activeFormattingElement) {
                        htmlTreeBuilder.error(this);
                    }
                    Element element = null;
                    Element element2 = null;
                    boolean z = false;
                    int size = stack.size();
                    int i2 = -1;
                    int i3 = 1;
                    while (true) {
                        if (i3 >= size || i3 >= 64) {
                            break;
                        }
                        Element element3 = stack.get(i3);
                        if (element3 == activeFormattingElement) {
                            element2 = stack.get(i3 - 1);
                            z = true;
                            i2 = htmlTreeBuilder.positionOfElement(element3);
                        } else if (z && HtmlTreeBuilder.isSpecial(element3)) {
                            element = element3;
                            break;
                        }
                        i3++;
                    }
                    if (element == null) {
                        htmlTreeBuilder.popStackToClose(activeFormattingElement.normalName());
                        htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                        return true;
                    }
                    Element element4 = element;
                    Element element5 = element;
                    for (int i4 = 0; i4 < 3; i4++) {
                        if (htmlTreeBuilder.onStack(element4)) {
                            element4 = htmlTreeBuilder.aboveOnStack(element4);
                        }
                        if (htmlTreeBuilder.isInActiveFormattingElements(element4)) {
                            if (element4 == activeFormattingElement) {
                                break;
                            }
                            Element element6 = new Element(htmlTreeBuilder.tagFor(element4.nodeName(), ParseSettings.preserveCase), htmlTreeBuilder.getBaseUri());
                            htmlTreeBuilder.replaceActiveFormattingElement(element4, element6);
                            htmlTreeBuilder.replaceOnStack(element4, element6);
                            element4 = element6;
                            if (element5 == element) {
                                i2 = htmlTreeBuilder.positionOfElement(element4) + 1;
                            }
                            if (element5.parent() != null) {
                                element5.remove();
                            }
                            element4.appendChild(element5);
                            element5 = element4;
                        } else {
                            htmlTreeBuilder.removeFromStack(element4);
                        }
                    }
                    if (element2 != null) {
                        if (StringUtil.inSorted(element2.normalName(), Constants.InBodyEndTableFosters)) {
                            if (element5.parent() != null) {
                                element5.remove();
                            }
                            htmlTreeBuilder.insertInFosterParent(element5);
                        } else {
                            if (element5.parent() != null) {
                                element5.remove();
                            }
                            element2.appendChild(element5);
                        }
                    }
                    Element element7 = new Element(activeFormattingElement.tag(), htmlTreeBuilder.getBaseUri());
                    element7.attributes().addAll(activeFormattingElement.attributes());
                    element7.appendChildren(element.childNodes());
                    element.appendChild(element7);
                    htmlTreeBuilder.removeFromActiveFormattingElements(activeFormattingElement);
                    htmlTreeBuilder.pushWithBookmark(element7, i2);
                    htmlTreeBuilder.removeFromStack(activeFormattingElement);
                    htmlTreeBuilder.insertOnStackAfter(element, element7);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag()) {
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return true;
            }
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter() && StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                htmlTreeBuilder.resetPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(InTableText);
                return htmlTreeBuilder.process(token);
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (normalName.equals(FlexmarkHtmlConverter.CAPTION_NODE)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.transition(InCaption);
                    return true;
                }
                if (normalName.equals("colgroup")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.transition(InColumnGroup);
                    return true;
                }
                if (normalName.equals("col")) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.processStartTag("colgroup");
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(normalName, Constants.InTableToBody)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.transition(InTableBody);
                    return true;
                }
                if (StringUtil.inSorted(normalName, Constants.InTableAddBody)) {
                    htmlTreeBuilder.clearStackToTableContext();
                    htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.TBODY_NODE);
                    return htmlTreeBuilder.process(token);
                }
                if (normalName.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                    htmlTreeBuilder.error(this);
                    if (!htmlTreeBuilder.inTableScope(normalName)) {
                        return false;
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    if (!htmlTreeBuilder.resetInsertionMode()) {
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    }
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(normalName, Constants.InTableToHead)) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                if (normalName.equals(FlexmarkHtmlConverter.INPUT_NODE)) {
                    if (!asStartTag.hasAttributes() || !asStartTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                    return true;
                }
                if (normalName.equals("form")) {
                    htmlTreeBuilder.error(this);
                    if (htmlTreeBuilder.getFormElement() != null || htmlTreeBuilder.onStack("template")) {
                        return false;
                    }
                    htmlTreeBuilder.insertFormElement(asStartTag, false, false);
                    return true;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isEndTag()) {
                String normalName2 = token.asEndTag().normalName();
                if (normalName2.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.popStackToClose(FlexmarkHtmlConverter.TABLE_NODE);
                    htmlTreeBuilder.resetInsertionMode();
                    return true;
                }
                if (StringUtil.inSorted(normalName2, Constants.InTableEndErr)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (normalName2.equals("template")) {
                    htmlTreeBuilder.process(token, InHead);
                    return true;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isEOF()) {
                if (htmlTreeBuilder.currentElementIs("html")) {
                    htmlTreeBuilder.error(this);
                    return true;
                }
                return true;
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        final boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.setFosterInserts(true);
            htmlTreeBuilder.process(token, InBody);
            htmlTreeBuilder.setFosterInserts(false);
            return true;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.type == Token.TokenType.Character) {
                Token.Character asCharacter = token.asCharacter();
                if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.addPendingTableCharacters(asCharacter);
                return true;
            }
            if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                Token token2 = htmlTreeBuilder.currentToken;
                for (Token.Character character : htmlTreeBuilder.getPendingTableCharacters()) {
                    htmlTreeBuilder.currentToken = character;
                    if (!HtmlTreeBuilderState.isWhitespace(character)) {
                        htmlTreeBuilder.error(this);
                        if (StringUtil.inSorted(htmlTreeBuilder.currentElement().normalName(), Constants.InTableFoster)) {
                            htmlTreeBuilder.setFosterInserts(true);
                            htmlTreeBuilder.process(character, InBody);
                            htmlTreeBuilder.setFosterInserts(false);
                        } else {
                            htmlTreeBuilder.process(character, InBody);
                        }
                    } else {
                        htmlTreeBuilder.insertCharacterNode(character);
                    }
                }
                htmlTreeBuilder.currentToken = token2;
                htmlTreeBuilder.resetPendingTableCharacters();
            }
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return htmlTreeBuilder.process(token);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag() && token.asEndTag().normalName().equals(FlexmarkHtmlConverter.CAPTION_NODE)) {
                if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.CAPTION_NODE)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags();
                if (!htmlTreeBuilder.currentElementIs(FlexmarkHtmlConverter.CAPTION_NODE)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(FlexmarkHtmlConverter.CAPTION_NODE);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                return true;
            }
            if ((token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) || (token.isEndTag() && token.asEndTag().normalName().equals(FlexmarkHtmlConverter.TABLE_NODE))) {
                if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.CAPTION_NODE)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags(false);
                if (!htmlTreeBuilder.currentElementIs(FlexmarkHtmlConverter.CAPTION_NODE)) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose(FlexmarkHtmlConverter.CAPTION_NODE);
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                InTable.process(token, htmlTreeBuilder);
                return true;
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.InCaptionIgnore)) {
                htmlTreeBuilder.error(this);
                return false;
            }
            return htmlTreeBuilder.process(token, InBody);
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return true;
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String normalName = asStartTag.normalName();
                    boolean z = -1;
                    switch (normalName.hashCode()) {
                        case -1321546630:
                            if (normalName.equals("template")) {
                                z = 2;
                                break;
                            }
                            break;
                        case 98688:
                            if (normalName.equals("col")) {
                                z = true;
                                break;
                            }
                            break;
                        case 3213227:
                            if (normalName.equals("html")) {
                                z = false;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                            return htmlTreeBuilder.process(token, InBody);
                        case true:
                            htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                            return true;
                        case true:
                            htmlTreeBuilder.process(token, InHead);
                            return true;
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String normalName2 = token.asEndTag().normalName();
                    boolean z2 = -1;
                    switch (normalName2.hashCode()) {
                        case -1321546630:
                            if (normalName2.equals("template")) {
                                z2 = true;
                                break;
                            }
                            break;
                        case -636197633:
                            if (normalName2.equals("colgroup")) {
                                z2 = false;
                                break;
                            }
                            break;
                    }
                    switch (z2) {
                        case false:
                            if (!htmlTreeBuilder.currentElementIs(normalName2)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.pop();
                            htmlTreeBuilder.transition(InTable);
                            return true;
                        case true:
                            htmlTreeBuilder.process(token, InHead);
                            return true;
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                default:
                    return anythingElse(token, htmlTreeBuilder);
                case 6:
                    if (htmlTreeBuilder.currentElementIs("html")) {
                        return true;
                    }
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.currentElementIs("colgroup")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.pop();
            htmlTreeBuilder.transition(InTable);
            htmlTreeBuilder.process(token);
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String normalName = asStartTag.normalName();
                    if (normalName.equals(FlexmarkHtmlConverter.TR_NODE)) {
                        htmlTreeBuilder.clearStackToTableBodyContext();
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        htmlTreeBuilder.transition(InRow);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.processStartTag(FlexmarkHtmlConverter.TR_NODE);
                        return htmlTreeBuilder.process(asStartTag);
                    }
                    if (StringUtil.inSorted(normalName, Constants.InTableBodyExit)) {
                        return exitTableBody(token, htmlTreeBuilder);
                    }
                    return anythingElse(token, htmlTreeBuilder);
                case 4:
                    String normalName2 = token.asEndTag().normalName();
                    if (StringUtil.inSorted(normalName2, Constants.InTableEndIgnore)) {
                        if (!htmlTreeBuilder.inTableScope(normalName2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.clearStackToTableBodyContext();
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(InTable);
                        return true;
                    }
                    if (normalName2.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                        return exitTableBody(token, htmlTreeBuilder);
                    }
                    if (StringUtil.inSorted(normalName2, Constants.InTableBodyEndIgnore)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    return anythingElse(token, htmlTreeBuilder);
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TBODY_NODE) && !htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.THEAD_NODE) && !htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.clearStackToTableBodyContext();
            htmlTreeBuilder.processEndTag(htmlTreeBuilder.currentElement().normalName());
            return htmlTreeBuilder.process(token);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insertElementFor(asStartTag);
                    htmlTreeBuilder.transition(InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    return true;
                }
                if (StringUtil.inSorted(normalName, Constants.InRowMissing)) {
                    if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TR_NODE)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isEndTag()) {
                String normalName2 = token.asEndTag().normalName();
                if (normalName2.equals(FlexmarkHtmlConverter.TR_NODE)) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return true;
                }
                if (normalName2.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                    if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TR_NODE)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(normalName2, Constants.InTableToBody)) {
                    if (!htmlTreeBuilder.inTableScope(normalName2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TR_NODE)) {
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return htmlTreeBuilder.process(token);
                }
                if (StringUtil.inSorted(normalName2, Constants.InRowIgnore)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag()) {
                String normalName = token.asEndTag().normalName();
                if (StringUtil.inSorted(normalName, Constants.InCellNames)) {
                    if (!htmlTreeBuilder.inTableScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.transition(InRow);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElementIs(normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(normalName);
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.transition(InRow);
                    return true;
                }
                if (StringUtil.inSorted(normalName, Constants.InCellBody)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (StringUtil.inSorted(normalName, Constants.InCellTable)) {
                    if (!htmlTreeBuilder.inTableScope(normalName)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                }
                return anythingElse(token, htmlTreeBuilder);
            }
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InCellCol)) {
                if (!htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TD_NODE) && !htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TH_NODE)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                closeCell(htmlTreeBuilder);
                return htmlTreeBuilder.process(token);
            }
            return anythingElse(token, htmlTreeBuilder);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope(FlexmarkHtmlConverter.TD_NODE)) {
                htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.TD_NODE);
            } else {
                htmlTreeBuilder.processEndTag(FlexmarkHtmlConverter.TH_NODE);
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String normalName = asStartTag.normalName();
                    if (normalName.equals("html")) {
                        return htmlTreeBuilder.process(asStartTag, InBody);
                    }
                    if (normalName.equals("option")) {
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    }
                    if (normalName.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElementIs("option")) {
                            htmlTreeBuilder.processEndTag("option");
                        }
                        if (htmlTreeBuilder.currentElementIs("optgroup")) {
                            htmlTreeBuilder.processEndTag("optgroup");
                        }
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    }
                    if (normalName.equals("select")) {
                        htmlTreeBuilder.error(this);
                        return htmlTreeBuilder.processEndTag("select");
                    }
                    if (StringUtil.inSorted(normalName, Constants.InSelectEnd)) {
                        htmlTreeBuilder.error(this);
                        if (!htmlTreeBuilder.inSelectScope("select")) {
                            return false;
                        }
                        htmlTreeBuilder.processEndTag("select");
                        return htmlTreeBuilder.process(asStartTag);
                    }
                    if (normalName.equals("script") || normalName.equals("template")) {
                        return htmlTreeBuilder.process(token, InHead);
                    }
                    return anythingElse(token, htmlTreeBuilder);
                case 4:
                    String normalName2 = token.asEndTag().normalName();
                    boolean z = -1;
                    switch (normalName2.hashCode()) {
                        case -1321546630:
                            if (normalName2.equals("template")) {
                                z = 3;
                                break;
                            }
                            break;
                        case -1010136971:
                            if (normalName2.equals("option")) {
                                z = true;
                                break;
                            }
                            break;
                        case -906021636:
                            if (normalName2.equals("select")) {
                                z = 2;
                                break;
                            }
                            break;
                        case -80773204:
                            if (normalName2.equals("optgroup")) {
                                z = false;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                            if (htmlTreeBuilder.currentElementIs("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).nameIs("optgroup")) {
                                htmlTreeBuilder.processEndTag("option");
                            }
                            if (htmlTreeBuilder.currentElementIs("optgroup")) {
                                htmlTreeBuilder.pop();
                                return true;
                            }
                            htmlTreeBuilder.error(this);
                            return true;
                        case true:
                            if (htmlTreeBuilder.currentElementIs("option")) {
                                htmlTreeBuilder.pop();
                                return true;
                            }
                            htmlTreeBuilder.error(this);
                            return true;
                        case true:
                            if (!htmlTreeBuilder.inSelectScope(normalName2)) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.popStackToClose(normalName2);
                            htmlTreeBuilder.resetInsertionMode();
                            return true;
                        case true:
                            return htmlTreeBuilder.process(token, InHead);
                        default:
                            return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                    Token.Character asCharacter = token.asCharacter();
                    if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.insertCharacterNode(asCharacter);
                    return true;
                case 6:
                    if (!htmlTreeBuilder.currentElementIs("html")) {
                        htmlTreeBuilder.error(this);
                        return true;
                    }
                    return true;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag() && StringUtil.inSorted(token.asStartTag().normalName(), Constants.InSelectTableEnd)) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.popStackToClose("select");
                htmlTreeBuilder.resetInsertionMode();
                return htmlTreeBuilder.process(token);
            }
            if (token.isEndTag() && StringUtil.inSorted(token.asEndTag().normalName(), Constants.InSelectTableEnd)) {
                htmlTreeBuilder.error(this);
                if (htmlTreeBuilder.inTableScope(token.asEndTag().normalName())) {
                    htmlTreeBuilder.popStackToClose("select");
                    htmlTreeBuilder.resetInsertionMode();
                    return htmlTreeBuilder.process(token);
                }
                return false;
            }
            return htmlTreeBuilder.process(token, InSelect);
        }
    },
    InTemplate { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    htmlTreeBuilder.process(token, InBody);
                    return true;
                case 3:
                    String normalName = token.asStartTag().normalName();
                    if (StringUtil.inSorted(normalName, Constants.InTemplateToHead)) {
                        htmlTreeBuilder.process(token, InHead);
                        return true;
                    }
                    if (StringUtil.inSorted(normalName, Constants.InTemplateToTable)) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InTable);
                        htmlTreeBuilder.transition(InTable);
                        return htmlTreeBuilder.process(token);
                    }
                    if (normalName.equals("col")) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InColumnGroup);
                        htmlTreeBuilder.transition(InColumnGroup);
                        return htmlTreeBuilder.process(token);
                    }
                    if (normalName.equals(FlexmarkHtmlConverter.TR_NODE)) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InTableBody);
                        htmlTreeBuilder.transition(InTableBody);
                        return htmlTreeBuilder.process(token);
                    }
                    if (normalName.equals(FlexmarkHtmlConverter.TD_NODE) || normalName.equals(FlexmarkHtmlConverter.TH_NODE)) {
                        htmlTreeBuilder.popTemplateMode();
                        htmlTreeBuilder.pushTemplateMode(InRow);
                        htmlTreeBuilder.transition(InRow);
                        return htmlTreeBuilder.process(token);
                    }
                    htmlTreeBuilder.popTemplateMode();
                    htmlTreeBuilder.pushTemplateMode(InBody);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                case 4:
                    if (token.asEndTag().normalName().equals("template")) {
                        htmlTreeBuilder.process(token, InHead);
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                case 6:
                    if (!htmlTreeBuilder.onStack("template")) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.popStackToClose("template");
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.popTemplateMode();
                    htmlTreeBuilder.resetInsertionMode();
                    if (htmlTreeBuilder.state() != InTemplate && htmlTreeBuilder.templateModeSize() < 12) {
                        return htmlTreeBuilder.process(token);
                    }
                    return true;
                default:
                    return true;
            }
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            Element fromStack = htmlTreeBuilder.getFromStack("html");
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                if (fromStack != null) {
                    htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), fromStack);
                    return true;
                }
                htmlTreeBuilder.process(token, InBody);
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                if (htmlTreeBuilder.isFragmentParsing()) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                if (fromStack != null) {
                    htmlTreeBuilder.onNodeClosed(fromStack);
                }
                htmlTreeBuilder.transition(AfterAfterBody);
                return true;
            }
            if (!token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.resetBody();
                return htmlTreeBuilder.process(token);
            }
            return true;
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String normalName = asStartTag.normalName();
                boolean z = -1;
                switch (normalName.hashCode()) {
                    case -1644953643:
                        if (normalName.equals("frameset")) {
                            z = true;
                            break;
                        }
                        break;
                    case 3213227:
                        if (normalName.equals("html")) {
                            z = false;
                            break;
                        }
                        break;
                    case 97692013:
                        if (normalName.equals("frame")) {
                            z = 2;
                            break;
                        }
                        break;
                    case 1192721831:
                        if (normalName.equals("noframes")) {
                            z = 3;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        return htmlTreeBuilder.process(asStartTag, InBody);
                    case true:
                        htmlTreeBuilder.insertElementFor(asStartTag);
                        return true;
                    case true:
                        htmlTreeBuilder.insertEmptyElementFor(asStartTag);
                        return true;
                    case true:
                        return htmlTreeBuilder.process(asStartTag, InHead);
                    default:
                        htmlTreeBuilder.error(this);
                        return false;
                }
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("frameset")) {
                if (htmlTreeBuilder.currentElementIs("html")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.pop();
                if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElementIs("frameset")) {
                    htmlTreeBuilder.transition(AfterFrameset);
                    return true;
                }
                return true;
            }
            if (token.isEOF()) {
                if (!htmlTreeBuilder.currentElementIs("html")) {
                    htmlTreeBuilder.error(this);
                    return true;
                }
                return true;
            }
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insertCharacterNode(token.asCharacter());
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isEndTag() && token.asEndTag().normalName().equals("html")) {
                htmlTreeBuilder.transition(AfterAfterFrameset);
                return true;
            }
            if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                return htmlTreeBuilder.process(token, InHead);
            }
            if (!token.isEOF()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            return true;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (!token.isDoctype() && (!token.isStartTag() || !token.asStartTag().normalName().equals("html"))) {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    htmlTreeBuilder.insertCharacterToElement(token.asCharacter(), htmlTreeBuilder.getDocument());
                    return true;
                }
                if (!token.isEOF()) {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.resetBody();
                    return htmlTreeBuilder.process(token);
                }
                return true;
            }
            return htmlTreeBuilder.process(token, InBody);
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insertCommentNode(token.asComment());
                return true;
            }
            if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().normalName().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (!token.isEOF()) {
                if (token.isStartTag() && token.asStartTag().normalName().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
            return true;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.24
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        final boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass25.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insertCommentNode(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return true;
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    if (StringUtil.in(asStartTag.normalName, Constants.InForeignToHtml)) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    if (asStartTag.normalName.equals("font") && (asStartTag.hasAttributeIgnoreCase("color") || asStartTag.hasAttributeIgnoreCase("face") || asStartTag.hasAttributeIgnoreCase("size"))) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.insertForeignElementFor(asStartTag, htmlTreeBuilder.currentElement().tag().namespace());
                    return true;
                case 4:
                    Token.EndTag asEndTag = token.asEndTag();
                    if (asEndTag.normalName.equals(FlexmarkHtmlConverter.BR_NODE) || asEndTag.normalName.equals(FlexmarkHtmlConverter.P_NODE)) {
                        return processAsHtml(token, htmlTreeBuilder);
                    }
                    if (asEndTag.normalName.equals("script") && htmlTreeBuilder.currentElementIs("script", Parser.NamespaceSvg)) {
                        htmlTreeBuilder.pop();
                        return true;
                    }
                    ArrayList<Element> stack = htmlTreeBuilder.getStack();
                    if (stack.isEmpty()) {
                        Validate.wtf("Stack unexpectedly empty");
                    }
                    int size = stack.size() - 1;
                    Element element = stack.get(size);
                    Element element2 = element;
                    if (!element.nameIs(asEndTag.normalName)) {
                        htmlTreeBuilder.error(this);
                    }
                    while (size != 0) {
                        if (element2.nameIs(asEndTag.normalName)) {
                            htmlTreeBuilder.popStackToCloseAnyNamespace(element2.normalName());
                            return true;
                        }
                        size--;
                        Element element3 = stack.get(size);
                        element2 = element3;
                        if (element3.tag().namespace().equals(Parser.NamespaceHtml)) {
                            return processAsHtml(token, htmlTreeBuilder);
                        }
                    }
                    return true;
                case 5:
                    Token.Character asCharacter = token.asCharacter();
                    if (!asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        if (HtmlTreeBuilderState.isWhitespace(asCharacter)) {
                            htmlTreeBuilder.insertCharacterNode(asCharacter);
                            return true;
                        }
                        htmlTreeBuilder.insertCharacterNode(asCharacter);
                        htmlTreeBuilder.framesetOk(false);
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return true;
                default:
                    return true;
            }
        }

        final boolean processAsHtml(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.state().process(token, htmlTreeBuilder);
        }
    };

    private static final String nullString = "��";

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.isCharacter()) {
            return StringUtil.isBlank(token.asCharacter().getData());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insertElementFor(startTag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
        htmlTreeBuilder.insertElementFor(startTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/parser/HtmlTreeBuilderState$Constants.class */
    public static final class Constants {
        static final String[] InHeadEmpty = {"base", "basefont", "bgsound", "command", "link"};
        static final String[] InHeadRaw = {"noframes", Attribute.STYLE_ATTR};
        static final String[] InHeadEnd = {"body", FlexmarkHtmlConverter.BR_NODE, "html"};
        static final String[] AfterHeadBody = {"body", FlexmarkHtmlConverter.BR_NODE, "html"};
        static final String[] BeforeHtmlToHead = {"body", FlexmarkHtmlConverter.BR_NODE, "head", "html"};
        static final String[] InHeadNoScriptHead = {"basefont", "bgsound", "link", "meta", "noframes", Attribute.STYLE_ATTR};
        static final String[] InBodyStartToHead = {"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", Attribute.STYLE_ATTR, "template", Attribute.TITLE_ATTR};
        static final String[] InBodyStartPClosers = {"address", "article", FlexmarkHtmlConverter.ASIDE_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "center", "details", "dir", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, "section", "summary", FlexmarkHtmlConverter.UL_NODE};
        static final String[] Headings = {FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE};
        static final String[] InBodyStartLiBreakers = {"address", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.P_NODE};
        static final String[] DdDt = {FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DT_NODE};
        static final String[] InBodyStartApplets = {"applet", "marquee", "object"};
        static final String[] InBodyStartMedia = {"param", "source", "track"};
        static final String[] InBodyStartInputAttribs = {"action", Attribute.NAME_ATTR, "prompt"};
        static final String[] InBodyStartDrop = {FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", "frame", "head", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InBodyEndClosers = {"address", "article", FlexmarkHtmlConverter.ASIDE_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "button", "center", "details", "dir", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.PRE_NODE, "section", "summary", FlexmarkHtmlConverter.UL_NODE};
        static final String[] InBodyEndOtherErrors = {"body", FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DT_NODE, "html", FlexmarkHtmlConverter.LI_NODE, "optgroup", "option", FlexmarkHtmlConverter.P_NODE, "rb", "rp", "rt", "rtc", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InBodyEndAdoptionFormatters = {FlexmarkHtmlConverter.A_NODE, FlexmarkHtmlConverter.B_NODE, "big", FlexmarkHtmlConverter.CODE_NODE, FlexmarkHtmlConverter.EM_NODE, "font", FlexmarkHtmlConverter.I_NODE, "nobr", "s", "small", FlexmarkHtmlConverter.STRIKE_NODE, FlexmarkHtmlConverter.STRONG_NODE, "tt", FlexmarkHtmlConverter.U_NODE};
        static final String[] InBodyEndTableFosters = {FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableToBody = {FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE};
        static final String[] InTableAddBody = {FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableToHead = {"script", Attribute.STYLE_ATTR, "template"};
        static final String[] InCellNames = {FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE};
        static final String[] InCellBody = {"body", FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", "html"};
        static final String[] InCellTable = {FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InCellCol = {FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableEndErr = {"body", FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", "html", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableFoster = {FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableBodyExit = {FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE};
        static final String[] InTableBodyEndIgnore = {"body", FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", "html", FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InRowMissing = {FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InRowIgnore = {"body", FlexmarkHtmlConverter.CAPTION_NODE, "col", "colgroup", "html", FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE};
        static final String[] InSelectEnd = {FlexmarkHtmlConverter.INPUT_NODE, "keygen", "textarea"};
        static final String[] InSelectTableEnd = {FlexmarkHtmlConverter.CAPTION_NODE, FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTableEndIgnore = {FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE};
        static final String[] InHeadNoscriptIgnore = {"head", "noscript"};
        static final String[] InCaptionIgnore = {"body", "col", "colgroup", "html", FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE};
        static final String[] InTemplateToHead = {"base", "basefont", "bgsound", "link", "meta", "noframes", "script", Attribute.STYLE_ATTR, "template", Attribute.TITLE_ATTR};
        static final String[] InTemplateToTable = {FlexmarkHtmlConverter.CAPTION_NODE, "colgroup", FlexmarkHtmlConverter.TBODY_NODE, "tfoot", FlexmarkHtmlConverter.THEAD_NODE};
        static final String[] InForeignToHtml = {FlexmarkHtmlConverter.B_NODE, "big", FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "body", FlexmarkHtmlConverter.BR_NODE, "center", FlexmarkHtmlConverter.CODE_NODE, FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.EM_NODE, "embed", FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, "head", FlexmarkHtmlConverter.HR_NODE, FlexmarkHtmlConverter.I_NODE, FlexmarkHtmlConverter.IMG_NODE, FlexmarkHtmlConverter.LI_NODE, "listing", "menu", "meta", "nobr", FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.PRE_NODE, "ruby", "s", "small", FlexmarkHtmlConverter.SPAN_NODE, FlexmarkHtmlConverter.STRIKE_NODE, FlexmarkHtmlConverter.STRONG_NODE, FlexmarkHtmlConverter.SUB_NODE, FlexmarkHtmlConverter.SUP_NODE, FlexmarkHtmlConverter.TABLE_NODE, "tt", FlexmarkHtmlConverter.U_NODE, FlexmarkHtmlConverter.UL_NODE, "var"};

        Constants() {
        }
    }
}

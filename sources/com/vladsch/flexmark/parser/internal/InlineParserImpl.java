package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.HtmlInline;
import com.vladsch.flexmark.ast.HtmlInlineComment;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.ImageRef;
import com.vladsch.flexmark.ast.InlineLinkNode;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.LinkRef;
import com.vladsch.flexmark.ast.LinkRefDerived;
import com.vladsch.flexmark.ast.LinkRendered;
import com.vladsch.flexmark.ast.MailLink;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.RefNode;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.WhiteSpace;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.ast.util.TextNodeConverter;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.parser.LightInlineParserImpl;
import com.vladsch.flexmark.parser.LinkRefProcessor;
import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.CharacterNodeFactory;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessor;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.delimiter.AsteriskDelimiterProcessor;
import com.vladsch.flexmark.parser.core.delimiter.Bracket;
import com.vladsch.flexmark.parser.core.delimiter.Delimiter;
import com.vladsch.flexmark.parser.core.delimiter.UnderscoreDelimiterProcessor;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.DoNotTrim;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/InlineParserImpl.class */
public class InlineParserImpl extends LightInlineParserImpl implements InlineParser, ParagraphPreProcessor {
    protected final BitSet originalSpecialCharacters;
    protected final BitSet delimiterCharacters;
    protected final Map<Character, DelimiterProcessor> delimiterProcessors;
    protected final LinkRefProcessorData linkRefProcessorsData;
    protected List<LinkRefProcessor> linkRefProcessors;
    protected Map<Character, List<InlineParserExtension>> inlineParserExtensions;
    protected List<InlineParserExtensionFactory> inlineParserExtensionFactories;
    protected LinkDestinationParser linkDestinationParser;
    protected BitSet specialCharacters;
    protected BitSet customCharacters;
    protected Map<Character, CharacterNodeFactory> customSpecialCharacterFactoryMap;
    protected ArrayList<Node> customSpecialCharacterNodes;
    protected ReferenceRepository referenceRepository;
    protected Delimiter lastDelimiter;
    private Bracket lastBracket;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !InlineParserImpl.class.desiredAssertionStatus();
    }

    public InlineParserImpl(DataHolder dataHolder, BitSet bitSet, BitSet bitSet2, Map<Character, DelimiterProcessor> map, LinkRefProcessorData linkRefProcessorData, List<InlineParserExtensionFactory> list) {
        super(dataHolder);
        this.linkRefProcessors = null;
        this.inlineParserExtensions = null;
        this.inlineParserExtensionFactories = null;
        this.linkDestinationParser = null;
        this.customCharacters = null;
        this.customSpecialCharacterFactoryMap = null;
        this.customSpecialCharacterNodes = null;
        this.delimiterProcessors = map;
        this.linkRefProcessorsData = linkRefProcessorData;
        this.delimiterCharacters = bitSet2;
        this.originalSpecialCharacters = bitSet;
        this.specialCharacters = bitSet;
        this.inlineParserExtensionFactories = !list.isEmpty() ? list : null;
        if (this.options.useHardcodedLinkAddressParser) {
            this.linkDestinationParser = new LinkDestinationParser(this.options.linksAllowMatchedParentheses, this.options.spaceInLinkUrls, this.options.parseJekyllMacrosInUrls, this.options.intellijDummyIdentifier);
        }
    }

    public void initializeDocument(Document document) {
        this.document = document;
        this.referenceRepository = Parser.REFERENCES.get(document);
        this.linkRefProcessors = new ArrayList(this.linkRefProcessorsData.processors.size());
        Iterator<LinkRefProcessorFactory> it = this.linkRefProcessorsData.processors.iterator();
        while (it.hasNext()) {
            this.linkRefProcessors.add(it.next().apply(document));
        }
        if (this.inlineParserExtensionFactories != null) {
            Map<Character, List<InlineParserExtensionFactory>> calculateInlineParserExtensions = calculateInlineParserExtensions(document, this.inlineParserExtensionFactories);
            this.inlineParserExtensions = new HashMap(calculateInlineParserExtensions.size());
            HashMap hashMap = new HashMap();
            for (Map.Entry<Character, List<InlineParserExtensionFactory>> entry : calculateInlineParserExtensions.entrySet()) {
                ArrayList arrayList = new ArrayList(entry.getValue().size());
                for (InlineParserExtensionFactory inlineParserExtensionFactory : entry.getValue()) {
                    InlineParserExtension inlineParserExtension = (InlineParserExtension) hashMap.get(inlineParserExtensionFactory);
                    InlineParserExtension inlineParserExtension2 = inlineParserExtension;
                    if (inlineParserExtension == null) {
                        inlineParserExtension2 = inlineParserExtensionFactory.apply((LightInlineParser) this);
                        hashMap.put(inlineParserExtensionFactory, inlineParserExtension2);
                    }
                    arrayList.add(inlineParserExtension2);
                }
                this.inlineParserExtensions.put(entry.getKey(), arrayList);
                this.specialCharacters.set(entry.getKey().charValue());
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void finalizeDocument(Document document) {
        if (!$assertionsDisabled && this.referenceRepository != Parser.REFERENCES.get(document)) {
            throw new AssertionError();
        }
        if (this.inlineParserExtensions != null) {
            Iterator<List<InlineParserExtension>> it = this.inlineParserExtensions.values().iterator();
            while (it.hasNext()) {
                Iterator<InlineParserExtension> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    it2.next().finalizeDocument(this);
                }
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public Delimiter getLastDelimiter() {
        return this.lastDelimiter;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public Bracket getLastBracket() {
        return this.lastBracket;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public List<Node> parseCustom(BasedSequence basedSequence, Node node, BitSet bitSet, Map<Character, CharacterNodeFactory> map) {
        this.customCharacters = bitSet;
        this.specialCharacters.or(bitSet);
        this.customSpecialCharacterFactoryMap = map;
        this.customSpecialCharacterNodes = null;
        parse(basedSequence, node);
        this.specialCharacters = this.originalSpecialCharacters;
        this.customSpecialCharacterFactoryMap = null;
        this.customCharacters = null;
        return this.customSpecialCharacterNodes;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void parse(BasedSequence basedSequence, Node node) {
        this.block = node;
        this.input = basedSequence.trim();
        this.index = 0;
        this.lastDelimiter = null;
        this.lastBracket = null;
        boolean z = node instanceof DoNotDecorate;
        do {
        } while (parseInline(z));
        processDelimiters(null);
        flushTextNode();
        if (!z && this.inlineParserExtensions != null) {
            Iterator<List<InlineParserExtension>> it = this.inlineParserExtensions.values().iterator();
            while (it.hasNext()) {
                Iterator<InlineParserExtension> it2 = it.next().iterator();
                while (it2.hasNext()) {
                    it2.next().finalizeBlock(this);
                }
            }
        }
        mergeTextNodes(node.getFirstChild(), node.getLastChild());
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void mergeTextNodes(Node node, Node node2) {
        Text text = null;
        Text text2 = null;
        Node node3 = node;
        while (true) {
            Node node4 = node3;
            if (node4 != null) {
                if (node4 instanceof Text) {
                    Text text3 = (Text) node4;
                    if (text == null) {
                        text = text3;
                    }
                    text2 = text3;
                } else {
                    mergeIfNeeded(text, text2);
                    text = null;
                    text2 = null;
                }
                if (node4 == node2) {
                    break;
                } else {
                    node3 = node4.getNext();
                }
            } else {
                break;
            }
        }
        mergeIfNeeded(text, text2);
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void mergeIfNeeded(Text text, Text text2) {
        if (text != null && text2 != null && text != text2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(text.getChars());
            Node next = text.getNext();
            Node next2 = text2.getNext();
            while (next != next2) {
                arrayList.add(next.getChars());
                Node node = next;
                next = next.getNext();
                node.unlink();
            }
            text.setChars(SegmentedSequence.create(text.getChars(), arrayList));
        }
    }

    @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessor
    public int preProcessBlock(Paragraph paragraph, ParserState parserState) {
        BasedSequence chars = paragraph.getChars();
        BasedSequence basedSequence = chars;
        int countLeading = chars.countLeading(CharPredicate.SPACE_TAB);
        int length = basedSequence.length();
        while (countLeading <= 3 && length > countLeading + 3 && basedSequence.charAt(countLeading) == '[') {
            if (countLeading > 0) {
                basedSequence = basedSequence.subSequence(countLeading, length);
                length -= countLeading;
            }
            int parseReference = parseReference(paragraph, basedSequence);
            if (parseReference == 0) {
                break;
            }
            BasedSequence subSequence = basedSequence.subSequence(parseReference, length);
            basedSequence = subSequence;
            length = subSequence.length();
            countLeading = basedSequence.countLeading(CharPredicate.SPACE_TAB);
        }
        return basedSequence.getStartOffset() - paragraph.getStartOffset();
    }

    protected int parseReference(Block block, BasedSequence basedSequence) {
        boolean z;
        this.input = basedSequence;
        this.index = 0;
        int i = this.index;
        int parseLinkLabel = parseLinkLabel();
        if (parseLinkLabel != 0 && peek() == ':') {
            BasedSequence subSequence = this.input.subSequence(0, parseLinkLabel + 1);
            this.index++;
            spnl();
            BasedSequence parseLinkDestination = parseLinkDestination();
            if (parseLinkDestination == null || parseLinkDestination.length() == 0) {
                return 0;
            }
            int i2 = this.index;
            spnl();
            BasedSequence parseLinkTitle = parseLinkTitle();
            BasedSequence basedSequence2 = parseLinkTitle;
            if (parseLinkTitle == null) {
                this.index = i2;
            }
            boolean z2 = true;
            if (this.index != this.input.length() && match(this.myParsing.LINE_END) == null) {
                if (basedSequence2 != null) {
                    basedSequence2 = null;
                    this.index = i2;
                    if (match(this.myParsing.LINE_END) != null) {
                        z = true;
                        z2 = z;
                    }
                }
                z = false;
                z2 = z;
            }
            if (!z2) {
                return 0;
            }
            String normalizeReferenceChars = Escaping.normalizeReferenceChars(subSequence, true);
            if (!normalizeReferenceChars.isEmpty()) {
                Reference reference = new Reference(subSequence, parseLinkDestination, basedSequence2);
                this.referenceRepository.put2(normalizeReferenceChars, (String) reference);
                block.insertBefore(reference);
                return this.index - i;
            }
            return 0;
        }
        return 0;
    }

    protected boolean parseInline() {
        return parseInline(false);
    }

    protected boolean parseInline(boolean z) {
        boolean parseString;
        boolean z2;
        List<InlineParserExtension> list;
        char peek = peek();
        if (peek == 0) {
            return false;
        }
        if (!z && this.inlineParserExtensions != null && (list = this.inlineParserExtensions.get(Character.valueOf(peek))) != null) {
            Iterator<InlineParserExtension> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().parse(this)) {
                    return true;
                }
            }
        }
        if (this.customCharacters != null && this.customCharacters.get(peek)) {
            if (!processCustomCharacters()) {
                this.index++;
                appendText(this.input.subSequence(this.index - 1, this.index));
                return true;
            }
            processDelimiters(null);
            this.lastDelimiter = null;
            return true;
        }
        switch (peek) {
            case '\n':
            case '\r':
                parseString = parseNewline();
                break;
            case '!':
                parseString = parseBang();
                break;
            case '&':
                parseString = parseEntity();
                break;
            case '<':
                if (this.delimiterCharacters.get(peek) && peek(1) == '<') {
                    z2 = parseDelimiters(this.delimiterProcessors.get(Character.valueOf(peek)), peek);
                } else {
                    z2 = parseAutolink() || parseHtmlInline();
                }
                parseString = z2;
                break;
            case '[':
                parseString = parseOpenBracket();
                break;
            case '\\':
                parseString = parseBackslash();
                break;
            case ']':
                parseString = parseCloseBracket();
                break;
            case '`':
                parseString = parseBackticks();
                break;
            default:
                if (this.delimiterCharacters.get(peek)) {
                    parseString = parseDelimiters(this.delimiterProcessors.get(Character.valueOf(peek)), peek);
                    break;
                } else {
                    parseString = parseString();
                    break;
                }
        }
        if (!parseString) {
            this.index++;
            appendText(this.input.subSequence(this.index - 1, this.index));
            return true;
        }
        return true;
    }

    private boolean processCustomCharacters() {
        char peek;
        CharacterNodeFactory characterNodeFactory = this.customSpecialCharacterFactoryMap.get(Character.valueOf(peek()));
        if (characterNodeFactory == null) {
            return false;
        }
        Node node = characterNodeFactory.get();
        node.setChars(this.input.subSequence(this.index, this.index + 1));
        if (this.currentText != null) {
            BasedSequence create = SegmentedSequence.create(node.getChars(), this.currentText);
            this.currentText = null;
            int length = create.length();
            BasedSequence basedSequence = null;
            while (length > 0 && characterNodeFactory.skipPrev(create.charAt(length - 1))) {
                length--;
            }
            if (length < create.length()) {
                basedSequence = create.subSequence(length);
                create = create.subSequence(0, length);
            }
            this.block.appendChild(new Text(create));
            if (basedSequence != null && characterNodeFactory.wantSkippedWhitespace()) {
                this.block.appendChild(new WhiteSpace(basedSequence));
            }
        }
        appendNode(node);
        if (this.customSpecialCharacterNodes == null) {
            this.customSpecialCharacterNodes = new ArrayList<>();
        }
        this.customSpecialCharacterNodes.add(node);
        int i = this.index + 1;
        do {
            this.index++;
            peek = peek();
            if (peek == 0) {
                break;
            }
        } while (characterNodeFactory.skipNext(peek));
        if (i < this.index && characterNodeFactory.wantSkippedWhitespace()) {
            this.block.appendChild(new WhiteSpace(this.input.subSequence(i, this.index)));
            return true;
        }
        return true;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public boolean parseNewline() {
        Node softLineBreak;
        int i = this.index < this.input.length() - 1 && this.input.charAt(this.index + 1) == '\n' ? 1 : 0;
        this.index += i + 1;
        flushTextNode();
        Node lastChild = this.block.getLastChild();
        if ((lastChild instanceof Text) && lastChild.getChars().endsWith(SequenceUtils.SPACE)) {
            BasedSequence chars = ((Text) lastChild).getChars();
            Matcher matcher = this.myParsing.FINAL_SPACE.matcher(chars);
            int end = matcher.find() ? matcher.end() - matcher.start() : 0;
            if (end >= 2) {
                softLineBreak = new HardLineBreak(this.input.subSequence(this.index - (this.options.hardLineBreakLimit ? i + 3 : (end + 1) + i), this.index));
            } else {
                softLineBreak = new SoftLineBreak(this.input.subSequence((this.index - 1) - i, this.index));
            }
            appendNode(softLineBreak);
            if (end > 0) {
                if (chars.length() > end) {
                    lastChild.setChars(chars.subSequence(0, chars.length() - end).trimEnd());
                } else {
                    lastChild.unlink();
                }
            }
        } else {
            appendNode(new SoftLineBreak(this.input.subSequence((this.index - 1) - i, this.index)));
        }
        while (peek() == ' ') {
            this.index++;
        }
        return true;
    }

    protected boolean parseBackslash() {
        this.index++;
        if (peek() == '\n' || peek() == '\r') {
            int i = peek(1) == '\n' ? 2 : 1;
            appendNode(new HardLineBreak(this.input.subSequence(this.index - 1, this.index + i)));
            this.index += i;
            return true;
        }
        if (this.index < this.input.length() && this.myParsing.ESCAPABLE.matcher(this.input.subSequence(this.index, this.index + 1)).matches()) {
            appendText(this.input, this.index - 1, this.index + 1);
            this.index++;
            return true;
        }
        appendText(this.input.subSequence(this.index - 1, this.index));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00e6, code lost:            if (r0.charAt(r12) == '\n') goto L28;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean parseBackticks() {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.parser.internal.InlineParserImpl.parseBackticks():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/InlineParserImpl$DelimiterData.class */
    public static class DelimiterData {
        final int count;
        final boolean canClose;
        final boolean canOpen;

        DelimiterData(int i, boolean z, boolean z2) {
            this.count = i;
            this.canOpen = z;
            this.canClose = z2;
        }
    }

    protected boolean parseDelimiters(DelimiterProcessor delimiterProcessor, char c) {
        DelimiterData scanDelimiters = scanDelimiters(delimiterProcessor, c);
        if (scanDelimiters == null) {
            return false;
        }
        int i = scanDelimiters.count;
        int i2 = this.index;
        this.index += i;
        this.lastDelimiter = new Delimiter(this.input, appendSeparateText(this.input.subSequence(i2, this.index)), c, scanDelimiters.canOpen, scanDelimiters.canClose, this.lastDelimiter, i2);
        this.lastDelimiter.setNumDelims(i);
        if (this.lastDelimiter.getPrevious() != null) {
            this.lastDelimiter.getPrevious().setNext(this.lastDelimiter);
            return true;
        }
        return true;
    }

    protected boolean parseOpenBracket() {
        int i = this.index;
        this.index++;
        addBracket(Bracket.link(this.input, appendSeparateText(this.input.subSequence(this.index - 1, this.index)), i, this.lastBracket, this.lastDelimiter));
        return true;
    }

    protected boolean parseBang() {
        int i = this.index;
        this.index++;
        if (peek() == '[') {
            this.index++;
            addBracket(Bracket.image(this.input, appendSeparateText(this.input.subSequence(this.index - 2, this.index)), i + 1, this.lastBracket, this.lastDelimiter));
            return true;
        }
        appendText(this.input.subSequence(this.index - 1, this.index));
        return true;
    }

    private void addBracket(Bracket bracket) {
        if (this.lastBracket != null) {
            this.lastBracket.setBracketAfter(true);
        }
        this.lastBracket = bracket;
    }

    private void removeLastBracket() {
        this.lastBracket = this.lastBracket.getPrevious();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/InlineParserImpl$ReferenceProcessorMatch.class */
    public static class ReferenceProcessorMatch {
        public final LinkRefProcessor processor;
        public final BasedSequence nodeChars;
        public final boolean wantExclamation;

        public ReferenceProcessorMatch(LinkRefProcessor linkRefProcessor, boolean z, BasedSequence basedSequence) {
            this.processor = linkRefProcessor;
            this.nodeChars = basedSequence;
            this.wantExclamation = z;
        }
    }

    private ReferenceProcessorMatch matchLinkRef(Bracket bracket, int i, int i2, int i3) {
        BasedSequence basedSequence;
        if (this.linkRefProcessorsData.nestingIndex.length == 0) {
            return null;
        }
        ReferenceProcessorMatch referenceProcessorMatch = null;
        BasedSequence basedSequence2 = null;
        BasedSequence basedSequence3 = null;
        int size = this.linkRefProcessorsData.processors.size();
        int i4 = this.linkRefProcessorsData.nestingIndex[i2 + i3];
        while (true) {
            if (i4 >= size) {
                break;
            }
            LinkRefProcessor linkRefProcessor = this.linkRefProcessors.get(i4);
            if (i2 + i3 < linkRefProcessor.getBracketNestingLevel()) {
                break;
            }
            boolean wantExclamationPrefix = linkRefProcessor.getWantExclamationPrefix();
            if (bracket.isImage() && wantExclamationPrefix) {
                if (basedSequence3 == null) {
                    basedSequence3 = this.input.subSequence((bracket.getStartIndex() - 1) - i2, i + i2);
                }
                basedSequence = basedSequence3;
            } else if (wantExclamationPrefix && bracket.getStartIndex() >= i2 + 1 && this.input.charAt((bracket.getStartIndex() - 1) - i2) == '!') {
                if (basedSequence3 == null) {
                    basedSequence3 = this.input.subSequence((bracket.getStartIndex() - 1) - i2, i + i2);
                }
                basedSequence = basedSequence3;
            } else {
                if (basedSequence2 == null) {
                    basedSequence2 = this.input.subSequence(bracket.getStartIndex() - i2, i + i2);
                }
                basedSequence = basedSequence2;
            }
            if (!linkRefProcessor.isMatch(basedSequence)) {
                i4++;
            } else {
                referenceProcessorMatch = new ReferenceProcessorMatch(linkRefProcessor, wantExclamationPrefix, basedSequence);
                break;
            }
        }
        return referenceProcessorMatch;
    }

    protected boolean parseCloseBracket() {
        Node imageRef;
        this.index++;
        int i = this.index;
        Bracket bracket = this.lastBracket;
        Bracket bracket2 = bracket;
        if (bracket == null) {
            appendText(this.input.subSequence(this.index - 1, this.index));
            return true;
        }
        if (!bracket2.isAllowed()) {
            appendText(this.input.subSequence(this.index - 1, this.index));
            removeLastBracket();
            return true;
        }
        BasedSequence basedSequence = null;
        BasedSequence basedSequence2 = null;
        BasedSequence basedSequence3 = null;
        boolean z = false;
        boolean z2 = false;
        ReferenceProcessorMatch referenceProcessorMatch = null;
        boolean z3 = false;
        BasedSequence basedSequence4 = BasedSequence.NULL;
        BasedSequence basedSequence5 = BasedSequence.NULL;
        BasedSequence basedSequence6 = BasedSequence.NULL;
        BasedSequence basedSequence7 = null;
        int i2 = this.index;
        if (this.options.spaceInLinkElements && peek() == ' ') {
            sp();
        }
        if (peek() == '(') {
            int i3 = this.index;
            basedSequence4 = this.input.subSequence(this.index, this.index + 1);
            this.index++;
            spnl();
            BasedSequence parseLinkDestination = parseLinkDestination();
            basedSequence = parseLinkDestination;
            if (parseLinkDestination != null) {
                if (this.options.parseMultiLineImageUrls && bracket2.isImage() && !basedSequence.startsWith("<") && basedSequence.endsWith(TypeDescription.Generic.OfWildcardType.SYMBOL) && spnlUrl()) {
                    int i4 = this.index;
                    while (true) {
                        int i5 = i4;
                        sp();
                        BasedSequence parseLinkTitle = parseLinkTitle();
                        if (parseLinkTitle != null) {
                            sp();
                        }
                        if (peek() == ')') {
                            basedSequence5 = this.input.subSequence(this.index, this.index + 1);
                            this.index++;
                            basedSequence7 = this.input.subSequence(i4, i5);
                            basedSequence2 = parseLinkTitle;
                            z = true;
                            break;
                        }
                        if (toEOL() == null) {
                            break;
                        }
                        i4 = this.index;
                    }
                } else {
                    spnl();
                    if (this.myParsing.WHITESPACE.matcher(this.input.subSequence(this.index - 1, this.index)).matches()) {
                        basedSequence2 = parseLinkTitle();
                        spnl();
                    }
                    if (peek() == ')') {
                        basedSequence5 = this.input.subSequence(this.index, this.index + 1);
                        this.index++;
                        z = true;
                    } else {
                        this.index = i3;
                    }
                }
            } else {
                this.index = i3;
            }
        } else {
            this.index = i2;
        }
        if (!z) {
            if (!this.options.matchLookaheadFirst) {
                referenceProcessorMatch = matchLinkRef(bracket2, i, 0, 0);
            }
            if (referenceProcessorMatch == null) {
                int i6 = this.linkRefProcessorsData.maxNesting;
                int i7 = 0;
                if (i6 > 0) {
                    Bracket bracket3 = bracket2;
                    while (bracket3.getPrevious() != null && bracket3.getStartIndex() == bracket3.getPrevious().getStartIndex() + 1 && peek(i7) == ']') {
                        bracket3 = bracket3.getPrevious();
                        i7++;
                        if (i7 == i6 || bracket3.isImage()) {
                            break;
                        }
                    }
                }
                int i8 = i7 + 1;
                while (true) {
                    int i9 = i8;
                    i8--;
                    if (i9 <= 0) {
                        break;
                    }
                    ReferenceProcessorMatch matchLinkRef = matchLinkRef(bracket2, i, i8, 0);
                    referenceProcessorMatch = matchLinkRef;
                    if (matchLinkRef != null) {
                        if (i8 > 0) {
                            while (true) {
                                int i10 = i8;
                                i8--;
                                if (i10 <= 0) {
                                    break;
                                }
                                this.index++;
                                this.lastBracket.getNode().unlink();
                                removeLastBracket();
                            }
                            bracket2 = this.lastBracket;
                        }
                    }
                }
            }
            if (referenceProcessorMatch == null) {
                int i11 = this.index;
                int parseLinkLabel = parseLinkLabel();
                if (parseLinkLabel > 2) {
                    basedSequence3 = this.input.subSequence(i11, i11 + parseLinkLabel);
                } else if (!bracket2.isBracketAfter()) {
                    basedSequence6 = this.input.subSequence(i11, i11 + parseLinkLabel);
                    if (bracket2.isImage()) {
                        basedSequence3 = this.input.subSequence(bracket2.getStartIndex() - 1, i);
                    } else {
                        basedSequence3 = this.input.subSequence(bracket2.getStartIndex(), i);
                    }
                    z2 = true;
                }
                if (basedSequence3 != null) {
                    if (this.referenceRepository.containsKey(Escaping.normalizeReferenceChars(basedSequence3, true))) {
                        z = !containsLinkRefs(z2 ? basedSequence3 : this.input.subSequence(bracket2.getStartIndex(), i), bracket2.getNode().getNext(), Boolean.FALSE);
                        z3 = true;
                    } else if (!bracket2.isStraddling(basedSequence3)) {
                        if (!z2 && peek() == '[') {
                            if (parseLinkLabel() > 0) {
                                this.index = i11;
                            } else if (!containsLinkRefs(basedSequence3, bracket2.getNode().getNext(), null)) {
                                z2 = true;
                                z = true;
                            }
                        } else if (!containsLinkRefs(basedSequence3, bracket2.getNode().getNext(), null)) {
                            z = true;
                        }
                    }
                }
            }
        }
        if (z || referenceProcessorMatch != null) {
            flushTextNode();
            boolean isImage = bracket2.isImage();
            if (referenceProcessorMatch != null) {
                if (!referenceProcessorMatch.wantExclamation && isImage) {
                    appendText(this.input.subSequence(bracket2.getStartIndex() - 1, bracket2.getStartIndex()));
                    bracket2.getNode().setChars(bracket2.getNode().getChars().subSequence(1));
                    isImage = false;
                }
                imageRef = referenceProcessorMatch.processor.createNode(referenceProcessorMatch.nodeChars);
            } else {
                imageRef = basedSequence3 != null ? isImage ? new ImageRef() : new LinkRef() : isImage ? new Image() : new Link();
            }
            Node node = imageRef;
            Node next = bracket2.getNode().getNext();
            while (true) {
                Node node2 = next;
                if (node2 == null) {
                    break;
                }
                Node next2 = node2.getNext();
                node.appendChild(node2);
                next = next2;
            }
            if (referenceProcessorMatch != null && node.hasChildren()) {
                BasedSequence childChars = node.getChildChars();
                BasedSequence adjustInlineText = referenceProcessorMatch.processor.adjustInlineText(this.document, node);
                Delimiter delimiter = this.lastDelimiter;
                while (true) {
                    Delimiter delimiter2 = delimiter;
                    if (delimiter2 == null) {
                        break;
                    }
                    Delimiter previous = delimiter2.getPrevious();
                    BasedSequence subSequence = delimiter2.getInput().subSequence(delimiter2.getStartIndex(), delimiter2.getEndIndex());
                    if (childChars.containsAllOf(subSequence) && (!adjustInlineText.containsAllOf(subSequence) || !referenceProcessorMatch.processor.allowDelimiters(subSequence, this.document, node))) {
                        removeDelimiterKeepNode(delimiter2);
                    }
                    delimiter = previous;
                }
                if (!adjustInlineText.containsAllOf(childChars)) {
                    ReversiblePeekingIterator<Node> it = node.getChildren().iterator();
                    while (it.hasNext()) {
                        Node next3 = it.next();
                        BasedSequence chars = next3.getChars();
                        if (adjustInlineText.containsSomeOf(chars)) {
                            if (!adjustInlineText.containsAllOf(chars)) {
                                next3.setChars(adjustInlineText.intersect(chars));
                            }
                        } else {
                            next3.unlink();
                        }
                    }
                }
            }
            appendNode(node);
            if (node instanceof RefNode) {
                RefNode refNode = (RefNode) node;
                refNode.setReferenceChars(basedSequence3);
                if (z3) {
                    refNode.setDefined(true);
                }
                if (!z2) {
                    refNode.setTextChars(this.input.subSequence(isImage ? bracket2.getStartIndex() - 1 : bracket2.getStartIndex(), i));
                } else if (!basedSequence6.isEmpty()) {
                    refNode.setTextOpeningMarker(basedSequence6.subSequence(0, 1));
                    refNode.setTextClosingMarker(basedSequence6.endSequence(1));
                }
                node.setCharsFromContent();
            } else if (node instanceof InlineLinkNode) {
                InlineLinkNode inlineLinkNode = (InlineLinkNode) node;
                inlineLinkNode.setUrlChars(basedSequence);
                inlineLinkNode.setTitleChars(basedSequence2);
                inlineLinkNode.setLinkOpeningMarker(basedSequence4);
                inlineLinkNode.setLinkClosingMarker(basedSequence5);
                inlineLinkNode.setTextChars(isImage ? this.input.subSequence(bracket2.getStartIndex() - 1, i) : this.input.subSequence(bracket2.getStartIndex(), i));
                if (basedSequence7 != null) {
                    ((Image) node).setUrlContent(basedSequence7);
                }
                node.setCharsFromContent();
            }
            processDelimiters(bracket2.getPreviousDelimiter());
            Text node3 = bracket2.getNode();
            removeLastBracket();
            if (referenceProcessorMatch != null) {
                referenceProcessorMatch.processor.updateNodeElements(this.document, node);
            }
            if (node instanceof Link) {
                Bracket bracket4 = this.lastBracket;
                while (true) {
                    Bracket bracket5 = bracket4;
                    if (bracket5 == null) {
                        break;
                    }
                    if (!bracket5.isImage()) {
                        bracket5.setAllowed(false);
                    }
                    bracket4 = bracket5.getPrevious();
                }
                if (this.options.linkTextPriorityOverLinkRef || !containsLinkRefs(node, Boolean.FALSE)) {
                    collapseLinkRefChildren(node, linkRefDerived -> {
                        return Boolean.valueOf((linkRefDerived instanceof LinkRendered) || linkRefDerived.isTentative());
                    }, true);
                    node3.unlink();
                    return true;
                }
                node.unlink();
                this.block.takeChildren(node);
                appendText(node.baseSubSequence(((Link) node).getTextClosingMarker().getStartOffset(), node.getEndOffset()));
                return true;
            }
            if (node instanceof RefNode) {
                collapseLinkRefChildren(node, linkRefDerived2 -> {
                    return Boolean.valueOf((linkRefDerived2 instanceof LinkRendered) || linkRefDerived2.isTentative());
                }, true);
            }
            node3.unlink();
            return true;
        }
        this.index = i;
        appendText(this.input.subSequence(this.index - 1, this.index));
        removeLastBracket();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected static boolean containsLinkRefs(Node node, Boolean bool) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != 0) {
                if ((node2 instanceof LinkRendered) && (bool == null || ((LinkRendered) node2).isTentative() == bool.booleanValue())) {
                    return true;
                }
                firstChild = node2.getNext();
            } else {
                return false;
            }
        }
    }

    protected static boolean containsLinkRefs(BasedSequence basedSequence, Node node, Boolean bool) {
        int startOffset = basedSequence.getStartOffset();
        int endOffset = basedSequence.getEndOffset();
        while (node != null) {
            if ((node instanceof LinkRefDerived) && ((bool == null || ((LinkRefDerived) node).isTentative() == bool.booleanValue()) && node.getStartOffset() < endOffset && node.getEndOffset() > startOffset)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    protected static void collapseLinkRefChildren(Node node, Function<LinkRefDerived, Boolean> function, boolean z) {
        Node firstChild = node.getFirstChild();
        boolean z2 = false;
        while (firstChild != null) {
            Node next = firstChild.getNext();
            if ((firstChild instanceof LinkRefDerived) && (function == null || function.apply((LinkRefDerived) firstChild).booleanValue())) {
                collapseLinkRefChildren(firstChild, function, false);
                firstChild.unlink();
                TextNodeConverter textNodeConverter = new TextNodeConverter(firstChild.getChars());
                textNodeConverter.addChildrenOf(firstChild);
                if (next != null) {
                    textNodeConverter.insertMergedBefore(next);
                } else {
                    textNodeConverter.appendMergedTo(node);
                }
                z2 = true;
            }
            firstChild = next;
        }
        if (z2) {
            TextNodeConverter.mergeTextNodes(node);
        }
        if (z) {
            Node firstChild2 = node.getFirstChild();
            Node lastChild = node.getLastChild();
            if (firstChild2 == lastChild) {
                if (firstChild2 == null || (firstChild2 instanceof DoNotTrim)) {
                    return;
                }
                firstChild2.setChars(firstChild2.getChars().trim());
                return;
            }
            if (firstChild2 != null && !(firstChild2 instanceof DoNotTrim)) {
                firstChild2.setChars(firstChild2.getChars().trimStart());
            }
            if (lastChild == null || (lastChild instanceof DoNotTrim)) {
                return;
            }
            lastChild.setChars(lastChild.getChars().trimEnd());
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public BasedSequence parseLinkDestination() {
        BasedSequence match = match(this.myParsing.LINK_DESTINATION_ANGLES);
        if (match != null) {
            return match;
        }
        if (this.linkDestinationParser != null) {
            BasedSequence parseLinkDestination = this.linkDestinationParser.parseLinkDestination(this.input, this.index);
            this.index += parseLinkDestination.length();
            return parseLinkDestination;
        }
        boolean z = this.options.spaceInLinkUrls;
        if (this.options.linksAllowMatchedParentheses) {
            BasedSequence match2 = match(this.myParsing.LINK_DESTINATION_MATCHED_PARENS);
            BasedSequence basedSequence = match2;
            if (match2 != null) {
                int i = 0;
                int length = basedSequence.length();
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    char charAt = basedSequence.charAt(i2);
                    if (charAt == '\\') {
                        if (i2 + 1 < length && this.myParsing.ESCAPABLE.matcher(basedSequence.subSequence(i2 + 1, i2 + 2)).matches()) {
                            i2++;
                        }
                    } else if (charAt == '(') {
                        i++;
                    } else if (charAt != ')') {
                        continue;
                    } else {
                        if (i == 0) {
                            this.index -= length - i2;
                            basedSequence = basedSequence.subSequence(0, i2);
                            break;
                        }
                        i--;
                    }
                    i2++;
                }
                return z ? basedSequence.trimEnd(CharPredicate.SPACE) : basedSequence;
            }
            return null;
        }
        BasedSequence match3 = match(this.myParsing.LINK_DESTINATION);
        return (match3 == null || !z) ? match3 : match3.trimEnd(CharPredicate.SPACE);
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public BasedSequence parseLinkTitle() {
        return match(this.myParsing.LINK_TITLE);
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public int parseLinkLabel() {
        BasedSequence match = match(this.myParsing.LINK_LABEL);
        if (match == null) {
            return 0;
        }
        return match.length();
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public boolean parseAutolink() {
        BasedSequence match;
        BasedSequence match2 = match(this.myParsing.EMAIL_AUTOLINK);
        if (match2 != null) {
            appendNode(new MailLink(match2.subSequence(0, 1), match2.subSequence(1, match2.length() - 1), match2.subSequence(match2.length() - 1, match2.length())));
            return true;
        }
        BasedSequence match3 = match(this.myParsing.AUTOLINK);
        if (match3 != null) {
            appendNode(new AutoLink(match3.subSequence(0, 1), match3.subSequence(1, match3.length() - 1), match3.subSequence(match3.length() - 1, match3.length())));
            return true;
        }
        if (this.options.wwwAutoLinkElement && (match = match(this.myParsing.WWW_AUTOLINK)) != null) {
            appendNode(new AutoLink(match.subSequence(0, 1), match.subSequence(1, match.length() - 1), match.subSequence(match.length() - 1, match.length())));
            return true;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public boolean parseHtmlInline() {
        BasedSequence match = match(this.myParsing.HTML_TAG);
        if (match != null) {
            appendNode((match.startsWith("<!--") && match.endsWith("-->")) ? new HtmlInlineComment(match) : new HtmlInline(match));
            return true;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public boolean parseEntity() {
        BasedSequence match = match(this.myParsing.ENTITY_HERE);
        if (match != null) {
            appendNode(new HtmlEntity(match));
            return true;
        }
        return false;
    }

    protected boolean parseString() {
        int i = this.index;
        int length = this.input.length();
        while (this.index != length && !this.specialCharacters.get(this.input.charAt(this.index))) {
            this.index++;
        }
        if (i != this.index) {
            appendText(this.input, i, this.index);
            return true;
        }
        return false;
    }

    protected Object clone() {
        return super.clone();
    }

    protected DelimiterData scanDelimiters(DelimiterProcessor delimiterProcessor, char c) {
        boolean matches;
        boolean matches2;
        boolean z;
        boolean z2;
        int i = this.index;
        int i2 = 0;
        while (peek() == c) {
            i2++;
            this.index++;
        }
        if (i2 < delimiterProcessor.getMinLength()) {
            this.index = i;
            return null;
        }
        String valueOf = i == 0 ? SequenceUtils.EOL : String.valueOf(this.input.charAt(i - 1));
        char peek = peek();
        String valueOf2 = peek == 0 ? SequenceUtils.EOL : String.valueOf(peek);
        boolean matches3 = this.myParsing.UNICODE_WHITESPACE_CHAR.matcher(valueOf).matches();
        boolean matches4 = this.myParsing.UNICODE_WHITESPACE_CHAR.matcher(valueOf2).matches();
        if (this.options.inlineDelimiterDirectionalPunctuations) {
            matches = this.myParsing.PUNCTUATION_OPEN.matcher(valueOf).matches();
            matches2 = this.myParsing.PUNCTUATION_CLOSE.matcher(valueOf2).matches();
            z = !matches4 && (!matches2 || matches3 || matches);
            z2 = !matches3 && (!matches || matches4 || matches2);
        } else {
            matches = this.myParsing.PUNCTUATION.matcher(valueOf).matches();
            matches2 = this.myParsing.PUNCTUATION.matcher(valueOf2).matches();
            z = !matches4 && (!matches2 || matches3 || matches);
            z2 = !matches3 && (!matches || matches4 || matches2);
        }
        boolean z3 = c == delimiterProcessor.getOpeningCharacter() && delimiterProcessor.canBeOpener(valueOf, valueOf2, z, z2, matches, matches2, matches3, matches4);
        boolean z4 = c == delimiterProcessor.getClosingCharacter() && delimiterProcessor.canBeCloser(valueOf, valueOf2, z, z2, matches, matches2, matches3, matches4);
        this.index = i;
        if (z3 || z4 || !delimiterProcessor.skipNonOpenerCloser()) {
            return new DelimiterData(i2, z3, z4);
        }
        return null;
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void processDelimiters(Delimiter delimiter) {
        Delimiter delimiter2;
        Delimiter delimiter3;
        HashMap hashMap = new HashMap();
        Delimiter delimiter4 = this.lastDelimiter;
        while (true) {
            delimiter2 = delimiter4;
            if (delimiter2 == null || delimiter2.getPrevious() == delimiter) {
                break;
            } else {
                delimiter4 = delimiter2.getPrevious();
            }
        }
        while (delimiter2 != null) {
            char delimiterChar = delimiter2.getDelimiterChar();
            DelimiterProcessor delimiterProcessor = this.delimiterProcessors.get(Character.valueOf(delimiterChar));
            if (!delimiter2.canClose() || delimiterProcessor == null) {
                delimiter2 = delimiter2.getNext();
            } else {
                char openingCharacter = delimiterProcessor.getOpeningCharacter();
                int i = 0;
                boolean z = false;
                boolean z2 = false;
                Delimiter previous = delimiter2.getPrevious();
                while (true) {
                    delimiter3 = previous;
                    if (delimiter3 == null || delimiter3 == delimiter || delimiter3 == hashMap.get(Character.valueOf(delimiterChar))) {
                        break;
                    }
                    if (delimiter3.canOpen() && delimiter3.getDelimiterChar() == openingCharacter) {
                        z2 = true;
                        int delimiterUse = delimiterProcessor.getDelimiterUse(delimiter3, delimiter2);
                        i = delimiterUse;
                        if (delimiterUse > 0) {
                            z = true;
                            break;
                        }
                    }
                    previous = delimiter3.getPrevious();
                }
                if (!z) {
                    if (!z2) {
                        hashMap.put(Character.valueOf(delimiterChar), delimiter2.getPrevious());
                        if (!delimiter2.canOpen()) {
                            removeDelimiterKeepNode(delimiter2);
                        }
                    }
                    delimiter2 = delimiter2.getNext();
                } else {
                    delimiter3.setNumDelims(delimiter3.getNumDelims() - i);
                    Delimiter delimiter5 = delimiter2;
                    delimiter5.setNumDelims(delimiter5.getNumDelims() - i);
                    removeDelimitersBetween(delimiter3, delimiter2);
                    delimiter3.setNumDelims(delimiter3.getNumDelims() + i);
                    Delimiter delimiter6 = delimiter2;
                    delimiter6.setNumDelims(delimiter6.getNumDelims() + i);
                    delimiterProcessor.process(delimiter3, delimiter2, i);
                    delimiter3.setNumDelims(delimiter3.getNumDelims() - i);
                    Delimiter delimiter7 = delimiter2;
                    delimiter7.setNumDelims(delimiter7.getNumDelims() - i);
                    if (delimiter3.getNumDelims() == 0) {
                        removeDelimiterAndNode(delimiter3);
                    } else {
                        delimiter3.getNode().setChars(delimiter3.getNode().getChars().subSequence(0, delimiter3.getNumDelims()));
                    }
                    if (delimiter2.getNumDelims() == 0) {
                        Delimiter next = delimiter2.getNext();
                        removeDelimiterAndNode(delimiter2);
                        delimiter2 = next;
                    } else {
                        BasedSequence chars = delimiter2.getNode().getChars();
                        int length = chars.length();
                        delimiter2.getNode().setChars(chars.subSequence(length - delimiter2.getNumDelims(), length));
                        Delimiter delimiter8 = delimiter2;
                        delimiter8.setIndex(delimiter8.getIndex() + i);
                    }
                }
            }
        }
        while (this.lastDelimiter != null && this.lastDelimiter != delimiter) {
            removeDelimiterKeepNode(this.lastDelimiter);
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void removeDelimitersBetween(Delimiter delimiter, Delimiter delimiter2) {
        Delimiter previous = delimiter2.getPrevious();
        while (true) {
            Delimiter delimiter3 = previous;
            if (delimiter3 != null && delimiter3 != delimiter) {
                Delimiter previous2 = delimiter3.getPrevious();
                removeDelimiterKeepNode(delimiter3);
                previous = previous2;
            } else {
                return;
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void removeDelimiterAndNode(Delimiter delimiter) {
        Text node = delimiter.getNode();
        Text previousNonDelimiterTextNode = delimiter.getPreviousNonDelimiterTextNode();
        Text nextNonDelimiterTextNode = delimiter.getNextNonDelimiterTextNode();
        if (previousNonDelimiterTextNode != null && nextNonDelimiterTextNode != null) {
            previousNonDelimiterTextNode.setChars(this.input.baseSubSequence(previousNonDelimiterTextNode.getStartOffset(), nextNonDelimiterTextNode.getEndOffset()));
            nextNonDelimiterTextNode.unlink();
        }
        node.unlink();
        removeDelimiter(delimiter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v33, types: [com.vladsch.flexmark.util.ast.Node] */
    @Override // com.vladsch.flexmark.parser.InlineParser
    public void removeDelimiterKeepNode(Delimiter delimiter) {
        DelimiterProcessor delimiterProcessor = this.delimiterProcessors.get(Character.valueOf(delimiter.getDelimiterChar()));
        Text unmatchedDelimiterNode = delimiterProcessor != null ? delimiterProcessor.unmatchedDelimiterNode(this, delimiter) : null;
        Text text = unmatchedDelimiterNode;
        if (unmatchedDelimiterNode != null) {
            if (text != delimiter.getNode()) {
                delimiter.getNode().insertAfter(text);
                delimiter.getNode().unlink();
            }
        } else {
            text = delimiter.getNode();
        }
        Text previousNonDelimiterTextNode = delimiter.getPreviousNonDelimiterTextNode();
        Text nextNonDelimiterTextNode = delimiter.getNextNonDelimiterTextNode();
        if ((text instanceof Text) && (previousNonDelimiterTextNode != null || nextNonDelimiterTextNode != null)) {
            if (nextNonDelimiterTextNode != null && previousNonDelimiterTextNode != null) {
                text.setChars(this.input.baseSubSequence(previousNonDelimiterTextNode.getStartOffset(), nextNonDelimiterTextNode.getEndOffset()));
                previousNonDelimiterTextNode.unlink();
                nextNonDelimiterTextNode.unlink();
            } else if (previousNonDelimiterTextNode != null) {
                text.setChars(this.input.baseSubSequence(previousNonDelimiterTextNode.getStartOffset(), text.getEndOffset()));
                previousNonDelimiterTextNode.unlink();
            } else {
                text.setChars(this.input.baseSubSequence(text.getStartOffset(), nextNonDelimiterTextNode.getEndOffset()));
                nextNonDelimiterTextNode.unlink();
            }
        }
        removeDelimiter(delimiter);
    }

    @Override // com.vladsch.flexmark.parser.InlineParser
    public void removeDelimiter(Delimiter delimiter) {
        if (delimiter.getPrevious() != null) {
            delimiter.getPrevious().setNext(delimiter.getNext());
        }
        if (delimiter.getNext() == null) {
            this.lastDelimiter = delimiter.getPrevious();
        } else {
            delimiter.getNext().setPrevious(delimiter.getPrevious());
        }
    }

    static Map<Character, List<InlineParserExtensionFactory>> calculateInlineParserExtensions(DataHolder dataHolder, List<InlineParserExtensionFactory> list) {
        HashMap hashMap = new HashMap();
        for (InlineParserExtensionFactory inlineParserExtensionFactory : list) {
            CharSequence characters = inlineParserExtensionFactory.getCharacters();
            for (int i = 0; i < characters.length(); i++) {
                ((List) hashMap.computeIfAbsent(Character.valueOf(characters.charAt(i)), ch -> {
                    return new ArrayList();
                })).add(inlineParserExtensionFactory);
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Character ch2 : hashMap.keySet()) {
            hashMap2.put(ch2, DependencyResolver.resolveFlatDependencies((List) hashMap.get(ch2), null, null));
        }
        return hashMap2;
    }

    public static BitSet calculateDelimiterCharacters(DataHolder dataHolder, Set<Character> set) {
        BitSet bitSet = new BitSet();
        Iterator<Character> it = set.iterator();
        while (it.hasNext()) {
            bitSet.set(it.next().charValue());
        }
        return bitSet;
    }

    public static BitSet calculateSpecialCharacters(DataHolder dataHolder, BitSet bitSet) {
        BitSet bitSet2 = new BitSet();
        bitSet2.or(bitSet);
        bitSet2.set(13);
        bitSet2.set(10);
        bitSet2.set(96);
        bitSet2.set(91);
        bitSet2.set(93);
        bitSet2.set(92);
        bitSet2.set(33);
        bitSet2.set(60);
        bitSet2.set(38);
        return bitSet2;
    }

    public static Map<Character, DelimiterProcessor> calculateDelimiterProcessors(DataHolder dataHolder, List<DelimiterProcessor> list) {
        HashMap hashMap = new HashMap();
        if (Parser.ASTERISK_DELIMITER_PROCESSOR.get(dataHolder).booleanValue()) {
            addDelimiterProcessors(Collections.singletonList(new AsteriskDelimiterProcessor(Parser.STRONG_WRAPS_EMPHASIS.get(dataHolder).booleanValue())), hashMap);
        }
        if (Parser.UNDERSCORE_DELIMITER_PROCESSOR.get(dataHolder).booleanValue()) {
            addDelimiterProcessors(Collections.singletonList(new UnderscoreDelimiterProcessor(Parser.STRONG_WRAPS_EMPHASIS.get(dataHolder).booleanValue())), hashMap);
        }
        addDelimiterProcessors(list, hashMap);
        return hashMap;
    }

    public static LinkRefProcessorData calculateLinkRefProcessors(DataHolder dataHolder, List<LinkRefProcessorFactory> list) {
        if (list.size() > 1) {
            ArrayList<LinkRefProcessorFactory> arrayList = new ArrayList(list.size());
            arrayList.addAll(list);
            int[] iArr = {0};
            Collections.sort(arrayList, (linkRefProcessorFactory, linkRefProcessorFactory2) -> {
                int bracketNestingLevel = linkRefProcessorFactory.getBracketNestingLevel(dataHolder);
                int bracketNestingLevel2 = linkRefProcessorFactory2.getBracketNestingLevel(dataHolder);
                int i = iArr[0];
                int i2 = i;
                if (i < bracketNestingLevel) {
                    i2 = bracketNestingLevel;
                }
                if (i2 < bracketNestingLevel2) {
                    i2 = bracketNestingLevel2;
                }
                iArr[0] = i2;
                if (bracketNestingLevel == bracketNestingLevel2) {
                    if (!linkRefProcessorFactory.getWantExclamationPrefix(dataHolder)) {
                        bracketNestingLevel++;
                    }
                    if (!linkRefProcessorFactory2.getWantExclamationPrefix(dataHolder)) {
                        bracketNestingLevel2++;
                    }
                }
                return Integer.compare(bracketNestingLevel, bracketNestingLevel2);
            });
            int i = iArr[0];
            int[] iArr2 = new int[i + 1];
            int i2 = -1;
            int i3 = 0;
            for (LinkRefProcessorFactory linkRefProcessorFactory3 : arrayList) {
                if (i2 < linkRefProcessorFactory3.getBracketNestingLevel(dataHolder)) {
                    i2 = linkRefProcessorFactory3.getBracketNestingLevel(dataHolder);
                    iArr2[i2] = i3;
                    if (i2 == i) {
                        break;
                    }
                }
                i3++;
            }
            return new LinkRefProcessorData(arrayList, i, iArr2);
        }
        if (list.size() > 0) {
            int bracketNestingLevel = list.get(0).getBracketNestingLevel(dataHolder);
            return new LinkRefProcessorData(list, bracketNestingLevel, new int[bracketNestingLevel + 1]);
        }
        return new LinkRefProcessorData(list, 0, new int[0]);
    }

    private static void addDelimiterProcessors(List<? extends DelimiterProcessor> list, Map<Character, DelimiterProcessor> map) {
        for (DelimiterProcessor delimiterProcessor : list) {
            char openingCharacter = delimiterProcessor.getOpeningCharacter();
            addDelimiterProcessorForChar(openingCharacter, delimiterProcessor, map);
            char closingCharacter = delimiterProcessor.getClosingCharacter();
            if (openingCharacter != closingCharacter) {
                addDelimiterProcessorForChar(closingCharacter, delimiterProcessor, map);
            }
        }
    }

    private static void addDelimiterProcessorForChar(char c, DelimiterProcessor delimiterProcessor, Map<Character, DelimiterProcessor> map) {
        DelimiterProcessor put = map.put(Character.valueOf(c), delimiterProcessor);
        if (put != null) {
            if (put.getClass() == delimiterProcessor.getClass()) {
                System.out.println("Delimiter processor for char '" + c + "', added more than once " + put.getClass().getCanonicalName());
                return;
            }
            throw new IllegalArgumentException("Delimiter processor conflict with delimiter char '" + c + "', existing " + put.getClass().getCanonicalName() + ", added " + delimiterProcessor.getClass().getCanonicalName());
        }
    }
}

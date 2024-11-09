package com.vladsch.flexmark.ext.autolink.internal;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.MailLink;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.DoNotLinkDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.ast.TypographicText;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.b.a.a;
import org.b.a.c;
import org.b.a.d;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/autolink/internal/AutolinkNodePostProcessor.class */
public class AutolinkNodePostProcessor extends NodePostProcessor {
    private static final Pattern URI_PREFIX = Pattern.compile("\\b([a-z][a-z0-9+.-]*://)(?:\\s|$)");
    private final Pattern ignoredLinks;
    private final boolean intellijDummyIdentifier;
    private final a linkExtractor = a.a().a(EnumSet.of(d.URL, d.WWW, d.EMAIL)).a();

    public AutolinkNodePostProcessor(Document document) {
        String str = AutolinkExtension.IGNORE_LINKS.get(document);
        this.ignoredLinks = str.isEmpty() ? null : Pattern.compile(str);
        this.intellijDummyIdentifier = Parser.INTELLIJ_DUMMY_IDENTIFIER.get(document).booleanValue();
    }

    public boolean isIgnoredLinkPrefix(CharSequence charSequence) {
        if (this.ignoredLinks != null) {
            return this.ignoredLinks.matcher(charSequence).matches();
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/autolink/internal/AutolinkNodePostProcessor$DummyLinkSpan.class */
    private static class DummyLinkSpan implements c {
        private final d linkType;
        private final int beginIndex;
        private final int endIndex;

        public DummyLinkSpan(d dVar, int i, int i2) {
            this.linkType = dVar;
            this.beginIndex = i;
            this.endIndex = i2;
        }

        @Override // org.b.a.c
        public d getType() {
            return this.linkType;
        }

        @Override // org.b.a.c
        public int getBeginIndex() {
            return this.beginIndex;
        }

        @Override // org.b.a.c
        public int getEndIndex() {
            return this.endIndex;
        }
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        Node node2;
        if (node.getAncestorOfType(DoNotDecorate.class, DoNotLinkDecorate.class) != null) {
            return;
        }
        BasedSequence chars = node.getChars();
        BasedSequence basedSequence = chars;
        BasedSequence basedSequence2 = chars;
        Node node3 = node;
        ArrayList arrayList = new ArrayList();
        if (((node.getNext() instanceof TypographicText) || (node.getNext() instanceof HtmlEntity)) && node.getNext().getChars().isContinuationOf(basedSequence)) {
            Node next = node.getNext();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(basedSequence);
            while (true) {
                if (((next instanceof TypographicText) || (next instanceof HtmlEntity) || (next instanceof Text)) && next.getChars().isContinuationOf(basedSequence) && !next.getChars().startsWith(SequenceUtils.SPACE) && !basedSequence.endsWith(SequenceUtils.SPACE)) {
                    basedSequence = next.getChars();
                    if (next instanceof HtmlEntity) {
                        arrayList.add(Range.of(basedSequence.getStartOffset(), basedSequence.getEndOffset()));
                    }
                    arrayList2.add(basedSequence);
                    node3 = next;
                    next = next.getNext();
                }
            }
            basedSequence2 = SegmentedSequence.create(node.getChars(), arrayList2);
        }
        ReplacedTextMapper replacedTextMapper = new ReplacedTextMapper(basedSequence2);
        BasedSequence basedSequence3 = basedSequence2;
        if (!arrayList.isEmpty()) {
            basedSequence3 = Escaping.unescapeHtml(basedSequence2, arrayList, replacedTextMapper);
        }
        BasedSequence unescape = Escaping.unescape(basedSequence3, replacedTextMapper);
        if (this.intellijDummyIdentifier) {
            unescape = Escaping.removeAll(unescape, "\u001f", replacedTextMapper);
        }
        Iterable<c> a2 = this.linkExtractor.a(unescape);
        ArrayList arrayList3 = new ArrayList();
        Iterator<c> it = a2.iterator();
        while (it.hasNext()) {
            arrayList3.add(it.next());
        }
        Matcher matcher = URI_PREFIX.matcher(unescape);
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            if (arrayList3.isEmpty()) {
                arrayList3.add(new DummyLinkSpan(d.URL, start, end));
            } else {
                int size = arrayList3.size();
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    c cVar = (c) arrayList3.get(i);
                    if (end < cVar.getBeginIndex()) {
                        arrayList3.add(i, new DummyLinkSpan(d.URL, start, end));
                        z = true;
                        break;
                    } else if (start < cVar.getBeginIndex() || end > cVar.getEndIndex()) {
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    arrayList3.add(new DummyLinkSpan(d.URL, start, end));
                }
            }
        }
        int i2 = 0;
        boolean z2 = !(node.getParent() instanceof TextBase);
        boolean z3 = z2;
        TextBase textBase = (z2 || !(node.getParent() instanceof TextBase)) ? null : (TextBase) node.getParent();
        boolean z4 = false;
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            c cVar2 = (c) it2.next();
            BasedSequence trimEnd = unescape.subSequence(cVar2.getBeginIndex(), cVar2.getEndIndex()).trimEnd();
            if (!isIgnoredLinkPrefix(trimEnd)) {
                int originalOffset = replacedTextMapper.originalOffset(cVar2.getBeginIndex());
                z4 = true;
                if (i2 == 0 && node != node3 && originalOffset >= node.getChars().length()) {
                    return;
                }
                if (z3) {
                    z3 = false;
                    textBase = new TextBase(basedSequence2);
                    node.insertBefore(textBase);
                    nodeTracker.nodeAdded(textBase);
                }
                if (originalOffset > i2) {
                    Text text = new Text(basedSequence2.subSequence(i2, originalOffset));
                    if (textBase != null) {
                        textBase.appendChild(text);
                    } else {
                        node.insertBefore(text);
                    }
                    nodeTracker.nodeAdded(text);
                }
                BasedSequence baseSubSequence = trimEnd.baseSubSequence(trimEnd.getStartOffset(), trimEnd.getEndOffset());
                Text text2 = new Text(baseSubSequence);
                if (cVar2.getType() == d.EMAIL) {
                    MailLink mailLink = new MailLink();
                    node2 = mailLink;
                    mailLink.setText(baseSubSequence);
                } else {
                    AutoLink autoLink = new AutoLink();
                    node2 = autoLink;
                    autoLink.setText(baseSubSequence);
                    ((AutoLink) node2).setUrlChars(baseSubSequence);
                }
                node2.setCharsFromContent();
                node2.appendChild(text2);
                if (textBase != null) {
                    textBase.appendChild(node2);
                } else {
                    node.insertBefore(node2);
                }
                nodeTracker.nodeAddedWithChildren(node2);
                i2 = replacedTextMapper.originalOffset(cVar2.getBeginIndex() + trimEnd.length());
            }
        }
        if (i2 > 0) {
            if (node != node3) {
                Node next2 = node.getNext();
                int length = node.getChars().length();
                while (true) {
                    if (next2 != null) {
                        if (length >= i2) {
                            basedSequence2 = basedSequence2.subSequence(0, length);
                            break;
                        }
                        length += next2.getChars().length();
                        Node next3 = next2.getNext();
                        next2.unlink();
                        nodeTracker.nodeRemoved(next2);
                        if (next2 == node3) {
                            break;
                        } else {
                            next2 = next3;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (i2 < basedSequence2.length()) {
                Text text3 = new Text(basedSequence2.subSequence(i2, basedSequence2.length()));
                if (textBase != null) {
                    textBase.appendChild(text3);
                } else {
                    node.insertBefore(text3);
                }
                nodeTracker.nodeAdded(text3);
            }
        }
        if (z4) {
            node.unlink();
            nodeTracker.nodeRemoved(node);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/autolink/internal/AutolinkNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory() {
            super(false);
            addNodes(Text.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new AutolinkNodePostProcessor(document);
        }
    }
}

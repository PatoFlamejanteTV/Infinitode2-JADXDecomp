package com.vladsch.flexmark.ext.escaped.character.internal;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.escaped.character.EscapedCharacter;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.ReplacedTextRegion;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/internal/EscapedCharacterNodePostProcessor.class */
public class EscapedCharacterNodePostProcessor extends NodePostProcessor {
    public EscapedCharacterNodePostProcessor(Document document) {
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        BasedSequence chars = node.getChars();
        ReplacedTextMapper replacedTextMapper = new ReplacedTextMapper(chars);
        Escaping.unescape(chars, replacedTextMapper);
        int i = 0;
        boolean z = !(node.getParent() instanceof TextBase);
        boolean z2 = z;
        TextBase textBase = z ? null : (TextBase) node.getParent();
        Iterator<ReplacedTextRegion> it = replacedTextMapper.getRegions().iterator();
        while (it.hasNext()) {
            ReplacedTextRegion next = it.next();
            int start = next.getOriginalRange().getStart();
            int end = next.getOriginalRange().getEnd();
            if (chars.charAt(start) == '\\' && next.getReplacedRange().getSpan() == 1 && start + 1 < chars.length()) {
                if (z2) {
                    z2 = false;
                    textBase = new TextBase(chars);
                    node.insertBefore(textBase);
                    nodeTracker.nodeAdded(textBase);
                }
                if (start != i) {
                    Text text = new Text(chars.subSequence(i, start));
                    textBase.appendChild(text);
                    nodeTracker.nodeAdded(text);
                }
                BasedSequence subSequence = chars.subSequence(start, end);
                EscapedCharacter escapedCharacter = new EscapedCharacter(subSequence.subSequence(0, 1), subSequence.subSequence(1));
                textBase.appendChild(escapedCharacter);
                nodeTracker.nodeAdded(escapedCharacter);
                i = end;
            }
        }
        if (i > 0) {
            if (i != chars.length()) {
                Text text2 = new Text(chars.subSequence(i, chars.length()));
                textBase.appendChild(text2);
                nodeTracker.nodeAdded(text2);
            }
            node.unlink();
            nodeTracker.nodeRemoved(node);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/internal/EscapedCharacterNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory() {
            super(false);
            addNodeWithExclusions(Text.class, DoNotDecorate.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new EscapedCharacterNodePostProcessor(document);
        }
    }
}

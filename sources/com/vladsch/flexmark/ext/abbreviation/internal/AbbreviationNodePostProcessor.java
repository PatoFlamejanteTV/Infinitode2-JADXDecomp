package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.abbreviation.Abbreviation;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationBlock;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.autolink.internal.AutolinkNodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.DoNotLinkDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodePostProcessor.class */
public class AbbreviationNodePostProcessor extends NodePostProcessor {
    private Pattern abbreviations;
    private HashMap<String, BasedSequence> abbreviationMap;

    private AbbreviationNodePostProcessor(Document document) {
        this.abbreviations = null;
        this.abbreviationMap = null;
        computeAbbreviations(document);
    }

    private void computeAbbreviations(Document document) {
        AbbreviationBlock abbreviationBlock;
        AbbreviationRepository abbreviationRepository = AbbreviationExtension.ABBREVIATIONS.get(document);
        if (!abbreviationRepository.isEmpty()) {
            this.abbreviationMap = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList(abbreviationRepository.keySet());
            arrayList.sort(Comparator.reverseOrder());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!str.isEmpty() && (abbreviationBlock = (AbbreviationBlock) abbreviationRepository.get(str)) != null) {
                    BasedSequence abbreviation = abbreviationBlock.getAbbreviation();
                    if (!abbreviation.isEmpty()) {
                        this.abbreviationMap.put(str, abbreviation);
                        if (sb.length() > 0) {
                            sb.append("|");
                        }
                        if (Character.isLetterOrDigit(str.charAt(0))) {
                            sb.append("\\b");
                        }
                        sb.append("\\Q").append(str).append("\\E");
                        if (Character.isLetterOrDigit(str.charAt(str.length() - 1))) {
                            sb.append("\\b");
                        }
                    }
                }
            }
            if (sb.length() > 0) {
                this.abbreviations = Pattern.compile(sb.toString());
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        if (this.abbreviations == null) {
            return;
        }
        BasedSequence chars = node.getChars();
        ReplacedTextMapper replacedTextMapper = new ReplacedTextMapper(chars);
        Matcher matcher = this.abbreviations.matcher(Escaping.unescape(chars, replacedTextMapper));
        int i = 0;
        boolean z = !(node.getParent() instanceof TextBase);
        boolean z2 = z;
        TextBase textBase = z ? null : (TextBase) node.getParent();
        while (matcher.find()) {
            BasedSequence basedSequence = this.abbreviationMap.get(matcher.group(0));
            if (basedSequence != null) {
                int originalOffset = replacedTextMapper.originalOffset(matcher.start(0));
                int originalOffset2 = replacedTextMapper.originalOffset(matcher.end(0));
                if (z2) {
                    z2 = false;
                    textBase = new TextBase(chars);
                    node.insertBefore(textBase);
                    nodeTracker.nodeAdded(textBase);
                }
                if (originalOffset != i) {
                    Text text = new Text(chars.subSequence(i, originalOffset));
                    textBase.appendChild(text);
                    nodeTracker.nodeAdded(text);
                }
                Abbreviation abbreviation = new Abbreviation(chars.subSequence(originalOffset, originalOffset2), basedSequence);
                textBase.appendChild(abbreviation);
                nodeTracker.nodeAdded(abbreviation);
                i = originalOffset2;
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            HashSet hashSet = new HashSet();
            hashSet.add(AutolinkNodePostProcessor.Factory.class);
            return hashSet;
        }

        public Factory() {
            super(false);
            addNodeWithExclusions(Text.class, DoNotDecorate.class, DoNotLinkDecorate.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new AbbreviationNodePostProcessor(document);
        }
    }
}

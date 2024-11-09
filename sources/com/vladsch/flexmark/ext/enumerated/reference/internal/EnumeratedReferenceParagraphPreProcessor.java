package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRepository;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessor;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.ReferencePreProcessorFactory;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceParagraphPreProcessor.class */
public class EnumeratedReferenceParagraphPreProcessor implements ParagraphPreProcessor {
    static String ENUM_REF_ID = "(?:[^0-9].*)?";
    static Pattern ENUM_REF_DEF_PARAGRAPH_PATTERN = Pattern.compile("\\s{0,3}(\\[[\\@]\\s*(" + ENUM_REF_ID + ")\\s*\\]:)\\s+(.*\n)");
    private final EnumeratedReferenceOptions options;
    private final EnumeratedReferenceRepository enumeratedReferences;

    EnumeratedReferenceParagraphPreProcessor(DataHolder dataHolder) {
        this.options = new EnumeratedReferenceOptions(dataHolder);
        this.enumeratedReferences = EnumeratedReferenceExtension.ENUMERATED_REFERENCES.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessor
    public int preProcessBlock(Paragraph paragraph, ParserState parserState) {
        BasedSequence chars = paragraph.getChars();
        Matcher matcher = ENUM_REF_DEF_PARAGRAPH_PATTERN.matcher(chars);
        int i = 0;
        while (matcher.find() && matcher.start() == i) {
            i = matcher.end();
            int start = matcher.start(1);
            int end = matcher.end(1);
            BasedSequence subSequence = chars.subSequence(start, start + 2);
            BasedSequence trim = chars.subSequence(start + 2, end - 2).trim();
            BasedSequence subSequence2 = chars.subSequence(end - 2, end);
            EnumeratedReferenceBlock enumeratedReferenceBlock = new EnumeratedReferenceBlock();
            enumeratedReferenceBlock.setOpeningMarker(subSequence);
            enumeratedReferenceBlock.setText(trim);
            enumeratedReferenceBlock.setClosingMarker(subSequence2);
            BasedSequence subSequence3 = chars.subSequence(matcher.start(3), matcher.end(3));
            enumeratedReferenceBlock.setEnumeratedReference(subSequence3);
            enumeratedReferenceBlock.appendChild(new Paragraph(subSequence3));
            enumeratedReferenceBlock.setCharsFromContent();
            paragraph.insertBefore(enumeratedReferenceBlock);
            parserState.blockAdded(enumeratedReferenceBlock);
            this.enumeratedReferences.put2(enumeratedReferenceBlock.getText().toString(), (String) enumeratedReferenceBlock);
        }
        return i;
    }

    public static ParagraphPreProcessorFactory Factory() {
        return new ParagraphPreProcessorFactory() { // from class: com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceParagraphPreProcessor.1
            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final boolean affectsGlobalScope() {
                return true;
            }

            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final Set<Class<?>> getAfterDependents() {
                return null;
            }

            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final Set<Class<?>> getBeforeDependents() {
                HashSet hashSet = new HashSet();
                hashSet.add(ReferencePreProcessorFactory.class);
                return hashSet;
            }

            @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory, java.util.function.Function
            public final ParagraphPreProcessor apply(ParserState parserState) {
                return new EnumeratedReferenceParagraphPreProcessor(parserState.getProperties());
            }
        };
    }
}

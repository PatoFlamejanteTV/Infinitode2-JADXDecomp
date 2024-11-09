package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ext.xwiki.macros.Macro;
import com.vladsch.flexmark.ext.xwiki.macros.MacroAttribute;
import com.vladsch.flexmark.ext.xwiki.macros.MacroClose;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserExtension;
import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroInlineParser.class */
public class MacroInlineParser implements InlineParserExtension {
    private final MacroParsing parsing;
    private List<Macro> openMacros = new ArrayList();

    public MacroInlineParser(LightInlineParser lightInlineParser) {
        this.parsing = new MacroParsing(lightInlineParser.getParsing());
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeDocument(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public void finalizeBlock(InlineParser inlineParser) {
        int size = this.openMacros.size();
        while (true) {
            int i = size;
            size--;
            if (i > 0) {
                inlineParser.moveNodes(this.openMacros.get(size), inlineParser.getBlock().getLastChild());
            } else {
                this.openMacros.clear();
                return;
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.InlineParserExtension
    public boolean parse(LightInlineParser lightInlineParser) {
        if (lightInlineParser.peek(1) == '{') {
            BasedSequence input = lightInlineParser.getInput();
            int index = lightInlineParser.getIndex();
            Matcher matcher = lightInlineParser.matcher(this.parsing.MACRO_TAG);
            if (matcher != null) {
                BasedSequence subSequence = input.subSequence(matcher.start(), matcher.end());
                if (subSequence.charAt(2) == '/') {
                    BasedSequence subSequence2 = input.subSequence(matcher.start(2), matcher.end(2));
                    int size = this.openMacros.size();
                    do {
                        int i = size;
                        size--;
                        if (i <= 0) {
                            lightInlineParser.setIndex(index);
                            return false;
                        }
                    } while (!this.openMacros.get(size).getName().equals(subSequence2));
                    lightInlineParser.flushTextNode();
                    int size2 = this.openMacros.size();
                    while (true) {
                        int i2 = size2;
                        size2--;
                        if (i2 <= size) {
                            break;
                        }
                        lightInlineParser.moveNodes(this.openMacros.get(size2), lightInlineParser.getBlock().getLastChild());
                    }
                    MacroClose macroClose = new MacroClose(subSequence.subSequence(0, 3), subSequence2, subSequence.endSequence(2));
                    lightInlineParser.getBlock().appendChild(macroClose);
                    lightInlineParser.moveNodes(this.openMacros.get(size), macroClose);
                    if (size == 0) {
                        this.openMacros.clear();
                        return true;
                    }
                    this.openMacros = this.openMacros.subList(0, size);
                    return true;
                }
                BasedSequence subSequence3 = input.subSequence(matcher.start(1), matcher.end(1));
                boolean z = subSequence.endCharAt(3) == '/';
                Macro macro = new Macro(subSequence.subSequence(0, 2), subSequence3, subSequence.endSequence(z ? 3 : 2));
                macro.setCharsFromContent();
                lightInlineParser.flushTextNode();
                lightInlineParser.getBlock().appendChild(macro);
                if (!z) {
                    this.openMacros.add(macro);
                }
                BasedSequence trim = subSequence.baseSubSequence(subSequence3.getEndOffset(), macro.getClosingMarker().getStartOffset()).trim();
                if (!trim.isEmpty()) {
                    macro.setAttributeText(trim);
                    Matcher matcher2 = this.parsing.MACRO_ATTRIBUTE.matcher(trim);
                    while (matcher2.find()) {
                        BasedSequence subSequence4 = trim.subSequence(matcher2.start(1), matcher2.end(1));
                        BasedSequence trim2 = (matcher2.groupCount() == 1 || matcher2.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher2.end(1), matcher2.start(2)).trim();
                        BasedSequence subSequence5 = (matcher2.groupCount() == 1 || matcher2.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher2.start(2), matcher2.end(2));
                        BasedSequence basedSequence = subSequence5;
                        boolean z2 = subSequence5.length() >= 2 && ((basedSequence.charAt(0) == '\"' && basedSequence.endCharAt(1) == '\"') || (basedSequence.charAt(0) == '\'' && basedSequence.endCharAt(1) == '\''));
                        boolean z3 = z2;
                        BasedSequence subSequence6 = !z2 ? BasedSequence.NULL : basedSequence.subSequence(0, 1);
                        BasedSequence endSequence = !z3 ? BasedSequence.NULL : basedSequence.endSequence(1, 0);
                        if (z3) {
                            basedSequence = basedSequence.midSequence(1, -1);
                        }
                        macro.appendChild(new MacroAttribute(subSequence4, trim2, subSequence6, basedSequence, endSequence));
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroInlineParser$Factory.class */
    public static class Factory implements InlineParserExtensionFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory
        public CharSequence getCharacters() {
            return "{";
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.InlineParserExtensionFactory, java.util.function.Function
        public InlineParserExtension apply(LightInlineParser lightInlineParser) {
            return new MacroInlineParser(lightInlineParser);
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }
    }
}

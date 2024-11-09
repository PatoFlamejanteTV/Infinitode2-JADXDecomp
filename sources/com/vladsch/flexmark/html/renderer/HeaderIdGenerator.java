package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.ast.util.AnchorRefTargetBlockPreVisitor;
import com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor;
import com.vladsch.flexmark.html.Disposable;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/HeaderIdGenerator.class */
public class HeaderIdGenerator implements Disposable, HtmlIdGenerator {
    HashMap<String, Integer> headerBaseIds;
    boolean resolveDupes;
    String toDashChars;
    String nonDashChars;
    boolean noDupedDashes;
    boolean nonAsciiToLowercase;

    public HeaderIdGenerator() {
        this(null);
    }

    public HeaderIdGenerator(DataHolder dataHolder) {
        this.headerBaseIds = new HashMap<>();
        this.resolveDupes = HtmlRenderer.HEADER_ID_GENERATOR_RESOLVE_DUPES.get(dataHolder).booleanValue();
        this.toDashChars = HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS.get(dataHolder);
        this.nonDashChars = HtmlRenderer.HEADER_ID_GENERATOR_NON_DASH_CHARS.get(dataHolder);
        this.noDupedDashes = HtmlRenderer.HEADER_ID_GENERATOR_NO_DUPED_DASHES.get(dataHolder).booleanValue();
        this.nonAsciiToLowercase = HtmlRenderer.HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.html.Disposable
    public void dispose() {
        this.headerBaseIds = null;
    }

    public boolean isResolveDupes() {
        return this.resolveDupes;
    }

    public void setResolveDupes(boolean z) {
        this.resolveDupes = z;
    }

    public String getToDashChars() {
        return this.toDashChars;
    }

    public void setToDashChars(String str) {
        this.toDashChars = str;
    }

    public String getNonDashChars() {
        return this.nonDashChars;
    }

    public void setNonDashChars(String str) {
        this.nonDashChars = str;
    }

    public boolean isNoDupedDashes() {
        return this.noDupedDashes;
    }

    public void setNoDupedDashes(boolean z) {
        this.noDupedDashes = z;
    }

    public boolean isNonAsciiToLowercase() {
        return this.nonAsciiToLowercase;
    }

    public void setNonAsciiToLowercase(boolean z) {
        this.nonAsciiToLowercase = z;
    }

    @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
    public void generateIds(Document document) {
        generateIds(document, null);
    }

    @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
    public void generateIds(Document document, final AnchorRefTargetBlockPreVisitor anchorRefTargetBlockPreVisitor) {
        this.headerBaseIds.clear();
        this.resolveDupes = HtmlRenderer.HEADER_ID_GENERATOR_RESOLVE_DUPES.get(document).booleanValue();
        this.toDashChars = HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS.get(document);
        this.nonDashChars = HtmlRenderer.HEADER_ID_GENERATOR_NON_DASH_CHARS.get(document);
        this.noDupedDashes = HtmlRenderer.HEADER_ID_GENERATOR_NO_DUPED_DASHES.get(document).booleanValue();
        this.nonAsciiToLowercase = HtmlRenderer.HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE.get(document).booleanValue();
        new AnchorRefTargetBlockVisitor() { // from class: com.vladsch.flexmark.html.renderer.HeaderIdGenerator.1
            @Override // com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor
            protected boolean preVisit(Node node) {
                return anchorRefTargetBlockPreVisitor == null || anchorRefTargetBlockPreVisitor.preVisit(node, this);
            }

            @Override // com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor
            protected void visit(AnchorRefTarget anchorRefTarget) {
                if (anchorRefTarget.getAnchorRefId().isEmpty()) {
                    String generateId = HeaderIdGenerator.this.generateId(anchorRefTarget.getAnchorRefText());
                    if (generateId != null) {
                        anchorRefTarget.setAnchorRefId(generateId);
                    }
                }
            }
        }.visit(document);
    }

    String generateId(String str) {
        if (!str.isEmpty()) {
            String generateId = generateId(str, this.toDashChars, this.nonDashChars, this.noDupedDashes, this.nonAsciiToLowercase);
            if (this.resolveDupes) {
                if (this.headerBaseIds.containsKey(generateId)) {
                    int intValue = this.headerBaseIds.get(generateId).intValue() + 1;
                    this.headerBaseIds.put(generateId, Integer.valueOf(intValue));
                    generateId = generateId + "-" + intValue;
                } else {
                    this.headerBaseIds.put(generateId, 0);
                }
            }
            return generateId;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
    public String getId(Node node) {
        if (node instanceof AnchorRefTarget) {
            return ((AnchorRefTarget) node).getAnchorRefId();
        }
        return null;
    }

    @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
    public String getId(CharSequence charSequence) {
        return generateId(charSequence.toString());
    }

    public static String generateId(CharSequence charSequence, String str, boolean z, boolean z2) {
        return generateId(charSequence, str, null, z, z2);
    }

    public static String generateId(CharSequence charSequence, String str, String str2, boolean z, boolean z2) {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        if (str == null) {
            str = HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS.get(null);
        }
        if (str2 == null) {
            str2 = HtmlRenderer.HEADER_ID_GENERATOR_NON_DASH_CHARS.get(null);
        }
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (isAlphabetic(charAt)) {
                if (!z2 && (charAt < 'A' || charAt > 'Z')) {
                    sb.append(charAt);
                } else {
                    sb.append(Character.toLowerCase(charAt));
                }
            } else if (Character.isDigit(charAt)) {
                sb.append(charAt);
            } else if (str2.indexOf(charAt) != -1) {
                sb.append(charAt);
            } else if (str.indexOf(charAt) != -1 && (!z || ((charAt == '-' && sb.length() == 0) || (sb.length() != 0 && sb.charAt(sb.length() - 1) != '-')))) {
                sb.append('-');
            }
        }
        return sb.toString();
    }

    public static boolean isAlphabetic(char c) {
        return ((1086 >> Character.getType((int) c)) & 1) != 0;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/HeaderIdGenerator$Factory.class */
    public static class Factory implements HeaderIdGeneratorFactory {
        @Override // com.vladsch.flexmark.html.renderer.HeaderIdGeneratorFactory
        public HeaderIdGenerator create(LinkResolverContext linkResolverContext) {
            return new HeaderIdGenerator();
        }

        @Override // com.vladsch.flexmark.html.renderer.HtmlIdGeneratorFactory
        public HeaderIdGenerator create() {
            return new HeaderIdGenerator();
        }
    }
}

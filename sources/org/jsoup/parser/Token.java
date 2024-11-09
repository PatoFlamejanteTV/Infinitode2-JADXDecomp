package org.jsoup.parser;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.SharedConstants;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Range;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/jsoup/parser/Token.class */
public abstract class Token {
    final TokenType type;
    static final int Unset = -1;
    private int startPos;
    private int endPos;

    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$TokenType.class */
    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    private Token(TokenType tokenType) {
        this.endPos = -1;
        this.type = tokenType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String tokenType() {
        return getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Token reset() {
        this.startPos = -1;
        this.endPos = -1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int startPos() {
        return this.startPos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startPos(int i) {
        this.startPos = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int endPos() {
        return this.endPos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endPos(int i) {
        this.endPos = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reset(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$Doctype.class */
    static final class Doctype extends Token {
        final StringBuilder name;
        String pubSysKey;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;
        boolean forceQuirks;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Doctype() {
            super(TokenType.Doctype);
            this.name = new StringBuilder();
            this.pubSysKey = null;
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public final Token reset() {
            super.reset();
            reset(this.name);
            this.pubSysKey = null;
            reset(this.publicIdentifier);
            reset(this.systemIdentifier);
            this.forceQuirks = false;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getName() {
            return this.name.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getPubSysKey() {
            return this.pubSysKey;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public final String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public final boolean isForceQuirks() {
            return this.forceQuirks;
        }

        public final String toString() {
            return "<!doctype " + getName() + ">";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$Tag.class */
    public static abstract class Tag extends Token {
        protected String tagName;
        protected String normalName;
        boolean selfClosing;
        Attributes attributes;
        private String attrName;
        private final StringBuilder attrNameSb;
        private boolean hasAttrName;
        private String attrValue;
        private final StringBuilder attrValueSb;
        private boolean hasAttrValue;
        private boolean hasEmptyAttrValue;
        final TreeBuilder treeBuilder;
        final boolean trackSource;
        int attrNameStart;
        int attrNameEnd;
        int attrValStart;
        int attrValEnd;
        private static final int MaxAttributes = 512;
        static final /* synthetic */ boolean $assertionsDisabled;

        public abstract String toString();

        static {
            $assertionsDisabled = !Token.class.desiredAssertionStatus();
        }

        Tag(TokenType tokenType, TreeBuilder treeBuilder) {
            super(tokenType);
            this.selfClosing = false;
            this.attrNameSb = new StringBuilder();
            this.hasAttrName = false;
            this.attrValueSb = new StringBuilder();
            this.hasAttrValue = false;
            this.hasEmptyAttrValue = false;
            this.treeBuilder = treeBuilder;
            this.trackSource = treeBuilder.trackSourceRange;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public Tag reset() {
            super.reset();
            this.tagName = null;
            this.normalName = null;
            this.selfClosing = false;
            this.attributes = null;
            resetPendingAttr();
            return this;
        }

        private void resetPendingAttr() {
            reset(this.attrNameSb);
            this.attrName = null;
            this.hasAttrName = false;
            reset(this.attrValueSb);
            this.attrValue = null;
            this.hasEmptyAttrValue = false;
            this.hasAttrValue = false;
            if (this.trackSource) {
                this.attrValEnd = -1;
                this.attrValStart = -1;
                this.attrNameEnd = -1;
                this.attrNameStart = -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void newAttribute() {
            String str;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            if (this.hasAttrName && this.attributes.size() < 512) {
                String trim = (this.attrNameSb.length() > 0 ? this.attrNameSb.toString() : this.attrName).trim();
                if (trim.length() > 0) {
                    if (this.hasAttrValue) {
                        str = this.attrValueSb.length() > 0 ? this.attrValueSb.toString() : this.attrValue;
                    } else if (this.hasEmptyAttrValue) {
                        str = "";
                    } else {
                        str = null;
                    }
                    this.attributes.add(trim, str);
                    trackAttributeRange(trim);
                }
            }
            resetPendingAttr();
        }

        private void trackAttributeRange(String str) {
            if (this.trackSource && isStartTag()) {
                StartTag asStartTag = asStartTag();
                CharacterReader characterReader = asStartTag.treeBuilder.reader;
                boolean preserveAttributeCase = asStartTag.treeBuilder.settings.preserveAttributeCase();
                if (!$assertionsDisabled && this.attributes == null) {
                    throw new AssertionError();
                }
                Map map = (Map) this.attributes.userData(SharedConstants.AttrRangeKey);
                Map map2 = map;
                if (map == null) {
                    map2 = new HashMap();
                    this.attributes.userData(SharedConstants.AttrRangeKey, map2);
                }
                if (!preserveAttributeCase) {
                    str = Normalizer.lowerCase(str);
                }
                if (map2.containsKey(str)) {
                    return;
                }
                if (!this.hasAttrValue) {
                    int i = this.attrNameEnd;
                    this.attrValEnd = i;
                    this.attrValStart = i;
                }
                map2.put(str, new Range.AttributeRange(new Range(new Range.Position(this.attrNameStart, characterReader.lineNumber(this.attrNameStart), characterReader.columnNumber(this.attrNameStart)), new Range.Position(this.attrNameEnd, characterReader.lineNumber(this.attrNameEnd), characterReader.columnNumber(this.attrNameEnd))), new Range(new Range.Position(this.attrValStart, characterReader.lineNumber(this.attrValStart), characterReader.columnNumber(this.attrValStart)), new Range.Position(this.attrValEnd, characterReader.lineNumber(this.attrValEnd), characterReader.columnNumber(this.attrValEnd)))));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean hasAttributes() {
            return this.attributes != null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean hasAttribute(String str) {
            return this.attributes != null && this.attributes.hasKey(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean hasAttributeIgnoreCase(String str) {
            return this.attributes != null && this.attributes.hasKeyIgnoreCase(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void finaliseTag() {
            if (this.hasAttrName) {
                newAttribute();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String name() {
            Validate.isFalse(this.tagName == null || this.tagName.length() == 0);
            return this.tagName;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String normalName() {
            return this.normalName;
        }

        final String toStringName() {
            return this.tagName != null ? this.tagName : "[unset]";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Tag name(String str) {
            this.tagName = str;
            this.normalName = ParseSettings.normalName(this.tagName);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean isSelfClosing() {
            return this.selfClosing;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendTagName(String str) {
            String replace = str.replace((char) 0, (char) 65533);
            this.tagName = this.tagName == null ? replace : this.tagName.concat(replace);
            this.normalName = ParseSettings.normalName(this.tagName);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendTagName(char c) {
            appendTagName(String.valueOf(c));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeName(String str, int i, int i2) {
            String replace = str.replace((char) 0, (char) 65533);
            ensureAttrName(i, i2);
            if (this.attrNameSb.length() == 0) {
                this.attrName = replace;
            } else {
                this.attrNameSb.append(replace);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeName(char c, int i, int i2) {
            ensureAttrName(i, i2);
            this.attrNameSb.append(c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(String str, int i, int i2) {
            ensureAttrValue(i, i2);
            if (this.attrValueSb.length() == 0) {
                this.attrValue = str;
            } else {
                this.attrValueSb.append(str);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(char c, int i, int i2) {
            ensureAttrValue(i, i2);
            this.attrValueSb.append(c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void appendAttributeValue(int[] iArr, int i, int i2) {
            ensureAttrValue(i, i2);
            for (int i3 : iArr) {
                this.attrValueSb.appendCodePoint(i3);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void setEmptyAttributeValue() {
            this.hasEmptyAttrValue = true;
        }

        private void ensureAttrName(int i, int i2) {
            this.hasAttrName = true;
            if (this.attrName != null) {
                this.attrNameSb.append(this.attrName);
                this.attrName = null;
            }
            if (this.trackSource) {
                this.attrNameStart = this.attrNameStart >= 0 ? this.attrNameStart : i;
                this.attrNameEnd = i2;
            }
        }

        private void ensureAttrValue(int i, int i2) {
            this.hasAttrValue = true;
            if (this.attrValue != null) {
                this.attrValueSb.append(this.attrValue);
                this.attrValue = null;
            }
            if (this.trackSource) {
                this.attrValStart = this.attrValStart >= 0 ? this.attrValStart : i;
                this.attrValEnd = i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$StartTag.class */
    public static final class StartTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag(TreeBuilder treeBuilder) {
            super(TokenType.StartTag, treeBuilder);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token.Tag, org.jsoup.parser.Token
        public final Tag reset() {
            super.reset();
            this.attributes = null;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final StartTag nameAttr(String str, Attributes attributes) {
            this.tagName = str;
            this.attributes = attributes;
            this.normalName = ParseSettings.normalName(this.tagName);
            return this;
        }

        @Override // org.jsoup.parser.Token.Tag
        public final String toString() {
            String str = isSelfClosing() ? "/>" : ">";
            if (hasAttributes() && this.attributes.size() > 0) {
                return "<" + toStringName() + SequenceUtils.SPACE + this.attributes.toString() + str;
            }
            return "<" + toStringName() + str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$EndTag.class */
    public static final class EndTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EndTag(TreeBuilder treeBuilder) {
            super(TokenType.EndTag, treeBuilder);
        }

        @Override // org.jsoup.parser.Token.Tag
        public final String toString() {
            return "</" + toStringName() + ">";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$Comment.class */
    static final class Comment extends Token {
        private final StringBuilder data;
        private String dataS;
        boolean bogus;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.jsoup.parser.Token
        public final Token reset() {
            super.reset();
            reset(this.data);
            this.dataS = null;
            this.bogus = false;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Comment() {
            super(TokenType.Comment);
            this.data = new StringBuilder();
            this.bogus = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String getData() {
            return this.dataS != null ? this.dataS : this.data.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Comment append(String str) {
            ensureData();
            if (this.data.length() == 0) {
                this.dataS = str;
            } else {
                this.data.append(str);
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Comment append(char c) {
            ensureData();
            this.data.append(c);
            return this;
        }

        private void ensureData() {
            if (this.dataS != null) {
                this.data.append(this.dataS);
                this.dataS = null;
            }
        }

        public final String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$Character.class */
    public static class Character extends Token implements Cloneable {
        private String data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character() {
            super(TokenType.Character);
        }

        @Override // org.jsoup.parser.Token
        Token reset() {
            super.reset();
            this.data = null;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character data(String str) {
            this.data = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Character m2551clone() {
            try {
                return (Character) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$CData.class */
    static final class CData extends Character {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CData(String str) {
            data(str);
        }

        @Override // org.jsoup.parser.Token.Character
        public final String toString() {
            return "<![CDATA[" + getData() + "]]>";
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/parser/Token$EOF.class */
    static final class EOF extends Token {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EOF() {
            super(TokenType.EOF);
        }

        @Override // org.jsoup.parser.Token
        final Token reset() {
            super.reset();
            return this;
        }

        public final String toString() {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Doctype asDoctype() {
        return (Doctype) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final StartTag asStartTag() {
        return (StartTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final EndTag asEndTag() {
        return (EndTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Comment asComment() {
        return (Comment) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isCData() {
        return this instanceof CData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Character asCharacter() {
        return (Character) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}

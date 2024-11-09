package org.jsoup.parser;

import com.badlogic.gdx.net.HttpStatus;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import java.util.Arrays;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Token;
import org.lwjgl.opengl.WGLARBPbuffer;
import org.lwjgl.opengl.WGLARBPixelFormat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/jsoup/parser/Tokeniser.class */
public final class Tokeniser {
    static final char replacementChar = 65533;
    private static final char[] notCharRefCharsSorted;
    static final int win1252ExtensionsStart = 128;
    static final int[] win1252Extensions;
    private final CharacterReader reader;
    private final ParseErrorList errors;
    final Token.StartTag startPending;
    final Token.EndTag endPending;
    Token.Tag tagPending;
    private String lastStartTag;
    private String lastStartCloseSeq;
    private static final int Unset = -1;
    private int markupStartPos;
    static final /* synthetic */ boolean $assertionsDisabled;
    private TokeniserState state = TokeniserState.Data;
    private Token emitPending = null;
    private boolean isEmitPending = false;
    private String charsString = null;
    private final StringBuilder charsBuilder = new StringBuilder(1024);
    final StringBuilder dataBuffer = new StringBuilder(1024);
    final Token.Character charPending = new Token.Character();
    final Token.Doctype doctypePending = new Token.Doctype();
    final Token.Comment commentPending = new Token.Comment();
    private int charStartPos = -1;
    private final int[] codepointHolder = new int[1];
    private final int[] multipointHolder = new int[2];

    static {
        $assertionsDisabled = !Tokeniser.class.desiredAssertionStatus();
        notCharRefCharsSorted = new char[]{'\t', '\n', '\r', '\f', ' ', '<', '&'};
        win1252Extensions = new int[]{8364, 129, WGLARBPixelFormat.WGL_BLUE_SHIFT_ARB, HttpStatus.SC_PAYMENT_REQUIRED, WGLARBPixelFormat.WGL_ACCUM_RED_BITS_ARB, WGLARBPixelFormat.WGL_GENERIC_ACCELERATION_ARB, WGLARBPixelFormat.WGL_ACCUM_BLUE_BITS_ARB, WGLARBPixelFormat.WGL_ACCUM_ALPHA_BITS_ARB, 710, WGLARBPbuffer.WGL_MAX_PBUFFER_HEIGHT_ARB, MapEditorItemInfoMenu.MENU_CONTENT_WIDTH, WGLARBPixelFormat.WGL_TRANSPARENT_BLUE_VALUE_ARB, 338, 141, 381, 143, 144, WGLARBPixelFormat.WGL_GREEN_SHIFT_ARB, WGLARBPixelFormat.WGL_BLUE_BITS_ARB, WGLARBPixelFormat.WGL_ALPHA_SHIFT_ARB, WGLARBPixelFormat.WGL_ACCUM_BITS_ARB, WGLARBPixelFormat.WGL_DEPTH_BITS_ARB, WGLARBPixelFormat.WGL_PIXEL_TYPE_ARB, WGLARBPixelFormat.WGL_COLOR_BITS_ARB, 732, 8482, 353, WGLARBPixelFormat.WGL_TRANSPARENT_ALPHA_VALUE_ARB, 339, 157, 382, 376};
        Arrays.sort(notCharRefCharsSorted);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tokeniser(TreeBuilder treeBuilder) {
        Token.StartTag startTag = new Token.StartTag(treeBuilder);
        this.startPending = startTag;
        this.tagPending = startTag;
        this.endPending = new Token.EndTag(treeBuilder);
        this.reader = treeBuilder.reader;
        this.errors = treeBuilder.parser.getErrors();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Token read() {
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        StringBuilder sb = this.charsBuilder;
        if (sb.length() != 0) {
            String sb2 = sb.toString();
            sb.delete(0, sb.length());
            Token.Character data = this.charPending.data(sb2);
            this.charsString = null;
            return data;
        }
        if (this.charsString != null) {
            Token.Character data2 = this.charPending.data(this.charsString);
            this.charsString = null;
            return data2;
        }
        this.isEmitPending = false;
        if ($assertionsDisabled || this.emitPending != null) {
            return this.emitPending;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emit(Token token) {
        Validate.isFalse(this.isEmitPending);
        this.emitPending = token;
        this.isEmitPending = true;
        token.startPos(this.markupStartPos);
        token.endPos(this.reader.pos());
        this.charStartPos = -1;
        if (token.type == Token.TokenType.StartTag) {
            this.lastStartTag = ((Token.StartTag) token).tagName;
            this.lastStartCloseSeq = null;
        } else if (token.type == Token.TokenType.EndTag) {
            Token.EndTag endTag = (Token.EndTag) token;
            if (endTag.hasAttributes()) {
                error("Attributes incorrectly present on end tag [/%s]", endTag.normalName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emit(String str) {
        if (this.charsString == null) {
            this.charsString = str;
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(str);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emit(StringBuilder sb) {
        if (this.charsString == null) {
            this.charsString = sb.toString();
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append((CharSequence) sb);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emit(char c) {
        if (this.charsString == null) {
            this.charsString = String.valueOf(c);
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(c);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    final void emit(char[] cArr) {
        emit(String.valueOf(cArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emit(int[] iArr) {
        emit(new String(iArr, 0, iArr.length));
    }

    final TokeniserState getState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void transition(TokeniserState tokeniserState) {
        switch (tokeniserState) {
            case TagOpen:
                this.markupStartPos = this.reader.pos();
                break;
            case Data:
                if (this.charStartPos == -1) {
                    this.charStartPos = this.reader.pos();
                    break;
                }
                break;
        }
        this.state = tokeniserState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void advanceTransition(TokeniserState tokeniserState) {
        transition(tokeniserState);
        this.reader.advance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int[] consumeCharacterReference(Character ch, boolean z) {
        if (this.reader.isEmpty()) {
            return null;
        }
        if ((ch != null && ch.charValue() == this.reader.current()) || this.reader.matchesAnySorted(notCharRefCharsSorted)) {
            return null;
        }
        int[] iArr = this.codepointHolder;
        this.reader.mark();
        if (this.reader.matchConsume("#")) {
            boolean matchConsumeIgnoreCase = this.reader.matchConsumeIgnoreCase("X");
            String consumeHexSequence = matchConsumeIgnoreCase ? this.reader.consumeHexSequence() : this.reader.consumeDigitSequence();
            String str = consumeHexSequence;
            if (consumeHexSequence.length() == 0) {
                characterReferenceError("numeric reference with no numerals", new Object[0]);
                this.reader.rewindToMark();
                return null;
            }
            this.reader.unmark();
            if (!this.reader.matchConsume(";")) {
                characterReferenceError("missing semicolon on [&#%s]", str);
            }
            int i = -1;
            try {
                i = Integer.valueOf(str, matchConsumeIgnoreCase ? 16 : 10).intValue();
            } catch (NumberFormatException unused) {
            }
            if (i == -1 || i > 1114111) {
                characterReferenceError("character [%s] outside of valid range", Integer.valueOf(i));
                iArr[0] = 65533;
            } else {
                if (i >= 128 && i < 128 + win1252Extensions.length) {
                    characterReferenceError("character [%s] is not a valid unicode code point", Integer.valueOf(i));
                    i = win1252Extensions[i - 128];
                }
                iArr[0] = i;
            }
            return iArr;
        }
        String consumeLetterThenDigitSequence = this.reader.consumeLetterThenDigitSequence();
        boolean matches = this.reader.matches(';');
        if (!(Entities.isBaseNamedEntity(consumeLetterThenDigitSequence) || (Entities.isNamedEntity(consumeLetterThenDigitSequence) && matches))) {
            this.reader.rewindToMark();
            if (matches) {
                characterReferenceError("invalid named reference [%s]", consumeLetterThenDigitSequence);
                return null;
            }
            return null;
        }
        if (z && (this.reader.matchesLetter() || this.reader.matchesDigit() || this.reader.matchesAny('=', '-', '_'))) {
            this.reader.rewindToMark();
            return null;
        }
        this.reader.unmark();
        if (!this.reader.matchConsume(";")) {
            characterReferenceError("missing semicolon on [&%s]", consumeLetterThenDigitSequence);
        }
        int codepointsForName = Entities.codepointsForName(consumeLetterThenDigitSequence, this.multipointHolder);
        if (codepointsForName == 1) {
            iArr[0] = this.multipointHolder[0];
            return iArr;
        }
        if (codepointsForName == 2) {
            return this.multipointHolder;
        }
        Validate.fail("Unexpected characters returned for " + consumeLetterThenDigitSequence);
        return this.multipointHolder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Token.Tag createTagPending(boolean z) {
        this.tagPending = z ? this.startPending.reset() : this.endPending.reset();
        return this.tagPending;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emitTagPending() {
        this.tagPending.finaliseTag();
        emit(this.tagPending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void createCommentPending() {
        this.commentPending.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emitCommentPending() {
        emit(this.commentPending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void createBogusCommentPending() {
        this.commentPending.reset();
        this.commentPending.bogus = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void createDoctypePending() {
        this.doctypePending.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void emitDoctypePending() {
        emit(this.doctypePending);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void createTempBuffer() {
        Token.reset(this.dataBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && this.tagPending.name().equalsIgnoreCase(this.lastStartTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String appropriateEndTagName() {
        return this.lastStartTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String appropriateEndTagSeq() {
        if (this.lastStartCloseSeq == null) {
            this.lastStartCloseSeq = "</" + this.lastStartTag;
        }
        return this.lastStartCloseSeq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void error(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Unexpected character '%s' in input state [%s]", Character.valueOf(this.reader.current()), tokeniserState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void eofError(TokeniserState tokeniserState) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    private void characterReferenceError(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, String.format("Invalid character reference: " + str, objArr)));
        }
    }

    final void error(String str) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void error(String str, Object... objArr) {
        if (this.errors.canAddError()) {
            this.errors.add(new ParseError(this.reader, str, objArr));
        }
    }

    static boolean currentNodeInHtmlNS() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String unescapeEntities(boolean z) {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        while (!this.reader.isEmpty()) {
            borrowBuilder.append(this.reader.consumeTo('&'));
            if (this.reader.matches('&')) {
                this.reader.consume();
                int[] consumeCharacterReference = consumeCharacterReference(null, z);
                if (consumeCharacterReference == null || consumeCharacterReference.length == 0) {
                    borrowBuilder.append('&');
                } else {
                    borrowBuilder.appendCodePoint(consumeCharacterReference[0]);
                    if (consumeCharacterReference.length == 2) {
                        borrowBuilder.appendCodePoint(consumeCharacterReference[1]);
                    }
                }
            }
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }
}

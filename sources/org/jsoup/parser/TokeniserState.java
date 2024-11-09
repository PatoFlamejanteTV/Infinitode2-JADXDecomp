package org.jsoup.parser;

import org.jsoup.nodes.DocumentType;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/jsoup/parser/TokeniserState.class */
public enum TokeniserState {
    Data { // from class: org.jsoup.parser.TokeniserState.1
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.emit(characterReader.consume());
                    return;
                case '&':
                    tokeniser.advanceTransition(CharacterReferenceInData);
                    return;
                case '<':
                    tokeniser.advanceTransition(TagOpen);
                    return;
                case 65535:
                    tokeniser.emit(new Token.EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeData());
                    return;
            }
        }
    },
    CharacterReferenceInData { // from class: org.jsoup.parser.TokeniserState.2
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readCharRef(tokeniser, Data);
        }
    },
    Rcdata { // from class: org.jsoup.parser.TokeniserState.3
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error(this);
                    characterReader.advance();
                    tokeniser.emit((char) 65533);
                    return;
                case '&':
                    tokeniser.advanceTransition(CharacterReferenceInRcdata);
                    return;
                case '<':
                    tokeniser.advanceTransition(RcdataLessthanSign);
                    return;
                case 65535:
                    tokeniser.emit(new Token.EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeData());
                    return;
            }
        }
    },
    CharacterReferenceInRcdata { // from class: org.jsoup.parser.TokeniserState.4
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readCharRef(tokeniser, Rcdata);
        }
    },
    Rawtext { // from class: org.jsoup.parser.TokeniserState.5
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readRawData(tokeniser, characterReader, this, RawtextLessthanSign);
        }
    },
    ScriptData { // from class: org.jsoup.parser.TokeniserState.6
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readRawData(tokeniser, characterReader, this, ScriptDataLessthanSign);
        }
    },
    PLAINTEXT { // from class: org.jsoup.parser.TokeniserState.7
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error(this);
                    characterReader.advance();
                    tokeniser.emit((char) 65533);
                    return;
                case 65535:
                    tokeniser.emit(new Token.EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeTo((char) 0));
                    return;
            }
        }
    },
    TagOpen { // from class: org.jsoup.parser.TokeniserState.8
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case '!':
                    tokeniser.advanceTransition(MarkupDeclarationOpen);
                    return;
                case '/':
                    tokeniser.advanceTransition(EndTagOpen);
                    return;
                case '?':
                    tokeniser.createBogusCommentPending();
                    tokeniser.transition(BogusComment);
                    return;
                default:
                    if (characterReader.matchesAsciiAlpha()) {
                        tokeniser.createTagPending(true);
                        tokeniser.transition(TagName);
                        return;
                    } else {
                        tokeniser.error(this);
                        tokeniser.emit('<');
                        tokeniser.transition(Data);
                        return;
                    }
            }
        }
    },
    EndTagOpen { // from class: org.jsoup.parser.TokeniserState.9
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.emit("</");
                tokeniser.transition(Data);
            } else if (characterReader.matchesAsciiAlpha()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(TagName);
            } else if (characterReader.matches('>')) {
                tokeniser.error(this);
                tokeniser.advanceTransition(Data);
            } else {
                tokeniser.error(this);
                tokeniser.createBogusCommentPending();
                tokeniser.commentPending.append('/');
                tokeniser.transition(BogusComment);
            }
        }
    },
    TagName { // from class: org.jsoup.parser.TokeniserState.10
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.tagPending.appendTagName(characterReader.consumeTagName());
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.tagPending.appendTagName(TokeniserState.replacementStr);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '<':
                    characterReader.unconsume();
                    tokeniser.error(this);
                    break;
                case '>':
                    break;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.appendTagName(consume);
                    return;
            }
            tokeniser.emitTagPending();
            tokeniser.transition(Data);
        }
    },
    RcdataLessthanSign { // from class: org.jsoup.parser.TokeniserState.11
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RCDATAEndTagOpen);
            } else if (characterReader.readFully() && characterReader.matchesAsciiAlpha() && tokeniser.appropriateEndTagName() != null && !characterReader.containsIgnoreCase(tokeniser.appropriateEndTagSeq())) {
                tokeniser.tagPending = tokeniser.createTagPending(false).name(tokeniser.appropriateEndTagName());
                tokeniser.emitTagPending();
                tokeniser.transition(TagOpen);
            } else {
                tokeniser.emit("<");
                tokeniser.transition(Rcdata);
            }
        }
    },
    RCDATAEndTagOpen { // from class: org.jsoup.parser.TokeniserState.12
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesAsciiAlpha()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(characterReader.current());
                tokeniser.dataBuffer.append(characterReader.current());
                tokeniser.advanceTransition(RCDATAEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(Rcdata);
        }
    },
    RCDATAEndTagName { // from class: org.jsoup.parser.TokeniserState.13
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesAsciiAlpha()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence);
                tokeniser.dataBuffer.append(consumeLetterSequence);
                return;
            }
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '/':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '>':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                default:
                    anythingElse(tokeniser, characterReader);
                    return;
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</");
            tokeniser.emit(tokeniser.dataBuffer);
            characterReader.unconsume();
            tokeniser.transition(Rcdata);
        }
    },
    RawtextLessthanSign { // from class: org.jsoup.parser.TokeniserState.14
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RawtextEndTagOpen);
            } else {
                tokeniser.emit('<');
                tokeniser.transition(Rawtext);
            }
        }
    },
    RawtextEndTagOpen { // from class: org.jsoup.parser.TokeniserState.15
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readEndTag(tokeniser, characterReader, RawtextEndTagName, Rawtext);
        }
    },
    RawtextEndTagName { // from class: org.jsoup.parser.TokeniserState.16
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, Rawtext);
        }
    },
    ScriptDataLessthanSign { // from class: org.jsoup.parser.TokeniserState.17
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '!':
                    tokeniser.emit("<!");
                    tokeniser.transition(ScriptDataEscapeStart);
                    return;
                case '/':
                    tokeniser.createTempBuffer();
                    tokeniser.transition(ScriptDataEndTagOpen);
                    return;
                case 65535:
                    tokeniser.emit("<");
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit("<");
                    characterReader.unconsume();
                    tokeniser.transition(ScriptData);
                    return;
            }
        }
    },
    ScriptDataEndTagOpen { // from class: org.jsoup.parser.TokeniserState.18
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readEndTag(tokeniser, characterReader, ScriptDataEndTagName, ScriptData);
        }
    },
    ScriptDataEndTagName { // from class: org.jsoup.parser.TokeniserState.19
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptData);
        }
    },
    ScriptDataEscapeStart { // from class: org.jsoup.parser.TokeniserState.20
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapeStartDash);
            } else {
                tokeniser.transition(ScriptData);
            }
        }
    },
    ScriptDataEscapeStartDash { // from class: org.jsoup.parser.TokeniserState.21
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapedDashDash);
            } else {
                tokeniser.transition(ScriptData);
            }
        }
    },
    ScriptDataEscaped { // from class: org.jsoup.parser.TokeniserState.22
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error(this);
                    characterReader.advance();
                    tokeniser.emit((char) 65533);
                    return;
                case '-':
                    tokeniser.emit('-');
                    tokeniser.advanceTransition(ScriptDataEscapedDash);
                    return;
                case '<':
                    tokeniser.advanceTransition(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('-', '<', 0));
                    return;
            }
        }
    },
    ScriptDataEscapedDash { // from class: org.jsoup.parser.TokeniserState.23
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.emit((char) 65533);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscapedDashDash);
                    return;
                case '<':
                    tokeniser.transition(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.24
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.emit((char) 65533);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    return;
                case '<':
                    tokeniser.transition(ScriptDataEscapedLessthanSign);
                    return;
                case '>':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptData);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.25
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesAsciiAlpha()) {
                tokeniser.createTempBuffer();
                tokeniser.dataBuffer.append(characterReader.current());
                tokeniser.emit("<");
                tokeniser.emit(characterReader.current());
                tokeniser.advanceTransition(ScriptDataDoubleEscapeStart);
                return;
            }
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataEscapedEndTagOpen);
            } else {
                tokeniser.emit('<');
                tokeniser.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen { // from class: org.jsoup.parser.TokeniserState.26
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesAsciiAlpha()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(characterReader.current());
                tokeniser.dataBuffer.append(characterReader.current());
                tokeniser.advanceTransition(ScriptDataEscapedEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName { // from class: org.jsoup.parser.TokeniserState.27
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart { // from class: org.jsoup.parser.TokeniserState.28
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataDoubleEscaped, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped { // from class: org.jsoup.parser.TokeniserState.29
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            switch (current) {
                case 0:
                    tokeniser.error(this);
                    characterReader.advance();
                    tokeniser.emit((char) 65533);
                    return;
                case '-':
                    tokeniser.emit(current);
                    tokeniser.advanceTransition(ScriptDataDoubleEscapedDash);
                    return;
                case '<':
                    tokeniser.emit(current);
                    tokeniser.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('-', '<', 0));
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDash { // from class: org.jsoup.parser.TokeniserState.30
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.emit((char) 65533);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedDashDash);
                    return;
                case '<':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.31
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.emit((char) 65533);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    return;
                case '<':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case '>':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptData);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.32
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.emit('/');
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataDoubleEscapeEnd);
                return;
            }
            tokeniser.transition(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd { // from class: org.jsoup.parser.TokeniserState.33
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataEscaped, ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName { // from class: org.jsoup.parser.TokeniserState.34
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    characterReader.unconsume();
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.transition(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '=':
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume, characterReader.pos() - 1, characterReader.pos());
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '<':
                    characterReader.unconsume();
                    tokeniser.error(this);
                    break;
                case '>':
                    break;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
            tokeniser.emitTagPending();
            tokeniser.transition(Data);
        }
    },
    AttributeName { // from class: org.jsoup.parser.TokeniserState.35
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            int pos = characterReader.pos();
            tokeniser.tagPending.appendAttributeName(characterReader.consumeToAnySorted(attributeNameCharsSorted), pos, characterReader.pos());
            int pos2 = characterReader.pos();
            char consume = characterReader.consume();
            switch (consume) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(AfterAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error(this);
                    break;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
            }
            tokeniser.tagPending.appendAttributeName(consume, pos2, characterReader.pos());
        }
    },
    AfterAttributeName { // from class: org.jsoup.parser.TokeniserState.36
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeName((char) 65533, characterReader.pos() - 1, characterReader.pos());
                    tokeniser.transition(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume, characterReader.pos() - 1, characterReader.pos());
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
        }
    },
    BeforeAttributeValue { // from class: org.jsoup.parser.TokeniserState.37
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533, characterReader.pos() - 1, characterReader.pos());
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(AttributeValue_doubleQuoted);
                    return;
                case '&':
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '\'':
                    tokeniser.transition(AttributeValue_singleQuoted);
                    return;
                case '<':
                case '=':
                case '`':
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue(consume, characterReader.pos() - 1, characterReader.pos());
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
            }
        }
    },
    AttributeValue_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.38
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            int pos = characterReader.pos();
            String consumeAttributeQuoted = characterReader.consumeAttributeQuoted(false);
            if (consumeAttributeQuoted.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeAttributeQuoted, pos, characterReader.pos());
            } else {
                tokeniser.tagPending.setEmptyAttributeValue();
            }
            int pos2 = characterReader.pos();
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533, pos2, characterReader.pos());
                    return;
                case '\"':
                    tokeniser.transition(AfterAttributeValue_quoted);
                    return;
                case '&':
                    int[] consumeCharacterReference = tokeniser.consumeCharacterReference('\"', true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference, pos2, characterReader.pos());
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&', pos2, characterReader.pos());
                        return;
                    }
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.appendAttributeValue(consume, pos2, characterReader.pos());
                    return;
            }
        }
    },
    AttributeValue_singleQuoted { // from class: org.jsoup.parser.TokeniserState.39
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            int pos = characterReader.pos();
            String consumeAttributeQuoted = characterReader.consumeAttributeQuoted(true);
            if (consumeAttributeQuoted.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeAttributeQuoted, pos, characterReader.pos());
            } else {
                tokeniser.tagPending.setEmptyAttributeValue();
            }
            int pos2 = characterReader.pos();
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533, pos2, characterReader.pos());
                    return;
                case '&':
                    int[] consumeCharacterReference = tokeniser.consumeCharacterReference('\'', true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference, pos2, characterReader.pos());
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&', pos2, characterReader.pos());
                        return;
                    }
                case '\'':
                    tokeniser.transition(AfterAttributeValue_quoted);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.appendAttributeValue(consume, pos2, characterReader.pos());
                    return;
            }
        }
    },
    AttributeValue_unquoted { // from class: org.jsoup.parser.TokeniserState.40
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            int pos = characterReader.pos();
            String consumeToAnySorted = characterReader.consumeToAnySorted(attributeValueUnquoted);
            if (consumeToAnySorted.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAnySorted, pos, characterReader.pos());
            }
            int pos2 = characterReader.pos();
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533, pos2, characterReader.pos());
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case '`':
                    tokeniser.error(this);
                    break;
                case '&':
                    int[] consumeCharacterReference = tokeniser.consumeCharacterReference('>', true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference, pos2, characterReader.pos());
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&', pos2, characterReader.pos());
                        return;
                    }
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
            }
            tokeniser.tagPending.appendAttributeValue(consume, pos2, characterReader.pos());
        }
    },
    AfterAttributeValue_quoted { // from class: org.jsoup.parser.TokeniserState.41
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.error(this);
                    tokeniser.transition(BeforeAttributeName);
                    return;
            }
        }
    },
    SelfClosingStartTag { // from class: org.jsoup.parser.TokeniserState.42
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '>':
                    tokeniser.tagPending.selfClosing = true;
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.error(this);
                    tokeniser.transition(BeforeAttributeName);
                    return;
            }
        }
    },
    BogusComment { // from class: org.jsoup.parser.TokeniserState.43
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.commentPending.append(characterReader.consumeTo('>'));
            char current = characterReader.current();
            if (current == '>' || current == 65535) {
                characterReader.consume();
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            }
        }
    },
    MarkupDeclarationOpen { // from class: org.jsoup.parser.TokeniserState.44
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchConsume("--")) {
                tokeniser.createCommentPending();
                tokeniser.transition(CommentStart);
            } else {
                if (characterReader.matchConsumeIgnoreCase("DOCTYPE")) {
                    tokeniser.transition(Doctype);
                    return;
                }
                if (characterReader.matchConsume("[CDATA[")) {
                    tokeniser.createTempBuffer();
                    tokeniser.transition(CdataSection);
                } else {
                    tokeniser.error(this);
                    tokeniser.createBogusCommentPending();
                    tokeniser.transition(BogusComment);
                }
            }
        }
    },
    CommentStart { // from class: org.jsoup.parser.TokeniserState.45
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.commentPending.append((char) 65533);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentStartDash);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentStartDash { // from class: org.jsoup.parser.TokeniserState.46
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.commentPending.append((char) 65533);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentEnd);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    Comment { // from class: org.jsoup.parser.TokeniserState.47
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error(this);
                    characterReader.advance();
                    tokeniser.commentPending.append((char) 65533);
                    return;
                case '-':
                    tokeniser.advanceTransition(CommentEndDash);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.append(characterReader.consumeToAny('-', 0));
                    return;
            }
        }
    },
    CommentEndDash { // from class: org.jsoup.parser.TokeniserState.48
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.commentPending.append('-').append((char) 65533);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentEnd);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.append('-').append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentEnd { // from class: org.jsoup.parser.TokeniserState.49
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.commentPending.append("--").append((char) 65533);
                    tokeniser.transition(Comment);
                    return;
                case '!':
                    tokeniser.transition(CommentEndBang);
                    return;
                case '-':
                    tokeniser.commentPending.append('-');
                    return;
                case '>':
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.append("--").append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentEndBang { // from class: org.jsoup.parser.TokeniserState.50
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.commentPending.append("--!").append((char) 65533);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.commentPending.append("--!");
                    tokeniser.transition(CommentEndDash);
                    return;
                case '>':
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.append("--!").append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    Doctype { // from class: org.jsoup.parser.TokeniserState.51
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypeName);
                    return;
                case '>':
                    break;
                case 65535:
                    tokeniser.eofError(this);
                    break;
                default:
                    tokeniser.error(this);
                    tokeniser.transition(BeforeDoctypeName);
                    return;
            }
            tokeniser.error(this);
            tokeniser.createDoctypePending();
            tokeniser.doctypePending.forceQuirks = true;
            tokeniser.emitDoctypePending();
            tokeniser.transition(Data);
        }
    },
    BeforeDoctypeName { // from class: org.jsoup.parser.TokeniserState.52
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesAsciiAlpha()) {
                tokeniser.createDoctypePending();
                tokeniser.transition(DoctypeName);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.name.append((char) 65533);
                    tokeniser.transition(DoctypeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.name.append(consume);
                    tokeniser.transition(DoctypeName);
                    return;
            }
        }
    },
    DoctypeName { // from class: org.jsoup.parser.TokeniserState.53
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.doctypePending.name.append(characterReader.consumeLetterSequence());
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.name.append((char) 65533);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(AfterDoctypeName);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.name.append(consume);
                    return;
            }
        }
    },
    AfterDoctypeName { // from class: org.jsoup.parser.TokeniserState.54
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
                return;
            }
            if (characterReader.matchesAny('\t', '\n', '\r', '\f', ' ')) {
                characterReader.advance();
                return;
            }
            if (characterReader.matches('>')) {
                tokeniser.emitDoctypePending();
                tokeniser.advanceTransition(Data);
                return;
            }
            if (characterReader.matchConsumeIgnoreCase(DocumentType.PUBLIC_KEY)) {
                tokeniser.doctypePending.pubSysKey = DocumentType.PUBLIC_KEY;
                tokeniser.transition(AfterDoctypePublicKeyword);
            } else if (characterReader.matchConsumeIgnoreCase(DocumentType.SYSTEM_KEY)) {
                tokeniser.doctypePending.pubSysKey = DocumentType.SYSTEM_KEY;
                tokeniser.transition(AfterDoctypeSystemKeyword);
            } else {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.advanceTransition(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword { // from class: org.jsoup.parser.TokeniserState.55
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypePublicIdentifier);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BeforeDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.56
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.57
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.publicIdentifier.append((char) 65533);
                    return;
                case '\"':
                    tokeniser.transition(AfterDoctypePublicIdentifier);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.publicIdentifier.append(consume);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.58
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.publicIdentifier.append((char) 65533);
                    return;
                case '\'':
                    tokeniser.transition(AfterDoctypePublicIdentifier);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.publicIdentifier.append(consume);
                    return;
            }
        }
    },
    AfterDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.59
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BetweenDoctypePublicAndSystemIdentifiers);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers { // from class: org.jsoup.parser.TokeniserState.60
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    AfterDoctypeSystemKeyword { // from class: org.jsoup.parser.TokeniserState.61
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypeSystemIdentifier);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    return;
            }
        }
    },
    BeforeDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.62
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.63
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.systemIdentifier.append((char) 65533);
                    return;
                case '\"':
                    tokeniser.transition(AfterDoctypeSystemIdentifier);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.systemIdentifier.append(consume);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.64
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.systemIdentifier.append((char) 65533);
                    return;
                case '\'':
                    tokeniser.transition(AfterDoctypeSystemIdentifier);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.systemIdentifier.append(consume);
                    return;
            }
        }
    },
    AfterDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.65
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BogusDoctype { // from class: org.jsoup.parser.TokeniserState.66
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    CdataSection { // from class: org.jsoup.parser.TokeniserState.67
        @Override // org.jsoup.parser.TokeniserState
        final void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.dataBuffer.append(characterReader.consumeTo("]]>"));
            if (characterReader.matchConsume("]]>") || characterReader.isEmpty()) {
                tokeniser.emit(new Token.CData(tokeniser.dataBuffer.toString()));
                tokeniser.transition(Data);
            }
        }
    };

    static final char nullChar = 0;
    private static final char replacementChar = 65533;
    private static final char eof = 65535;
    static final char[] attributeNameCharsSorted = {'\t', '\n', '\f', '\r', ' ', '\"', '\'', '/', '<', '=', '>'};
    static final char[] attributeValueUnquoted = {0, '\t', '\n', '\f', '\r', ' ', '\"', '&', '\'', '<', '=', '>', '`'};
    private static final String replacementStr = "�";

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void read(Tokeniser tokeniser, CharacterReader characterReader);

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleDataEndTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState) {
        if (characterReader.matchesLetter()) {
            String consumeLetterSequence = characterReader.consumeLetterSequence();
            tokeniser.tagPending.appendTagName(consumeLetterSequence);
            tokeniser.dataBuffer.append(consumeLetterSequence);
            return;
        }
        boolean z = false;
        if (tokeniser.isAppropriateEndTagToken() && !characterReader.isEmpty()) {
            char consume = characterReader.consume();
            switch (consume) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    break;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    break;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    break;
                default:
                    tokeniser.dataBuffer.append(consume);
                    z = true;
                    break;
            }
        } else {
            z = true;
        }
        if (z) {
            tokeniser.emit("</");
            tokeniser.emit(tokeniser.dataBuffer);
            tokeniser.transition(tokeniserState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readRawData(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        switch (characterReader.current()) {
            case 0:
                tokeniser.error(tokeniserState);
                characterReader.advance();
                tokeniser.emit((char) 65533);
                return;
            case '<':
                tokeniser.advanceTransition(tokeniserState2);
                return;
            case 65535:
                tokeniser.emit(new Token.EOF());
                return;
            default:
                tokeniser.emit(characterReader.consumeRawData());
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readCharRef(Tokeniser tokeniser, TokeniserState tokeniserState) {
        int[] consumeCharacterReference = tokeniser.consumeCharacterReference(null, false);
        if (consumeCharacterReference == null) {
            tokeniser.emit('&');
        } else {
            tokeniser.emit(consumeCharacterReference);
        }
        tokeniser.transition(tokeniserState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readEndTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (characterReader.matchesAsciiAlpha()) {
            tokeniser.createTagPending(false);
            tokeniser.transition(tokeniserState);
        } else {
            tokeniser.emit("</");
            tokeniser.transition(tokeniserState2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleDataDoubleEscapeTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (characterReader.matchesLetter()) {
            String consumeLetterSequence = characterReader.consumeLetterSequence();
            tokeniser.dataBuffer.append(consumeLetterSequence);
            tokeniser.emit(consumeLetterSequence);
            return;
        }
        char consume = characterReader.consume();
        switch (consume) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case '/':
            case '>':
                if (tokeniser.dataBuffer.toString().equals("script")) {
                    tokeniser.transition(tokeniserState);
                } else {
                    tokeniser.transition(tokeniserState2);
                }
                tokeniser.emit(consume);
                return;
            default:
                characterReader.unconsume();
                tokeniser.transition(tokeniserState2);
                return;
        }
    }
}

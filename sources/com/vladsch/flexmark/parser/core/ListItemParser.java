package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedListItem;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.misc.Utils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListItemParser.class */
public class ListItemParser extends AbstractBlockParser {
    private final ListItem myBlock;
    private final ListOptions myOptions;
    private final ListBlockParser.ListData myListData;
    private final Parsing myParsing;
    private boolean myHadBlankLine = false;
    private boolean myIsEmpty = false;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ListItemParser.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListItemParser(ListOptions listOptions, Parsing parsing, ListBlockParser.ListData listData) {
        this.myOptions = listOptions;
        this.myListData = listData;
        this.myParsing = parsing;
        this.myBlock = this.myListData.isNumberedList ? new OrderedListItem() : new BulletListItem();
        this.myBlock.setOpeningMarker(this.myListData.listMarker);
        this.myBlock.setMarkerSuffix(this.myListData.markerSuffix);
    }

    int getContentColumn() {
        return this.myListData.markerColumn + this.myListData.listMarker.length() + (this.myOptions.isItemContentAfterSuffix() ? this.myListData.contentOffset : this.myListData.markerSuffixOffset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getContentIndent() {
        return this.myListData.markerIndent + this.myListData.listMarker.length() + (this.myOptions.isItemContentAfterSuffix() ? this.myListData.contentOffset : this.myListData.markerSuffixOffset);
    }

    int getMarkerContentIndent() {
        return this.myListData.markerIndent + this.myListData.listMarker.length() + 1;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return ((block instanceof FencedCodeBlock) && Parser.PARSER_EMULATION_PROFILE.get(parserState.getProperties()) == ParserEmulationProfile.GITHUB_DOC && this.myListData.markerIndent >= ((FencedCodeBlockParser) blockParser).getFenceMarkerIndent()) ? false : true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isPropagatingLastBlankLine(BlockParser blockParser) {
        return this.myBlock.getFirstChild() != null || this == blockParser;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.myBlock;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.myBlock.setCharsFromContent();
    }

    private BlockContinue continueAtColumn(int i) {
        if (this.myHadBlankLine) {
            this.myBlock.setContainsBlankLine(true);
        }
        this.myIsEmpty = false;
        return BlockContinue.atColumn(i);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if (parserState.isBlank()) {
            Node firstChild = this.myBlock.getFirstChild();
            this.myIsEmpty = firstChild == null;
            if (this.myIsEmpty || firstChild.getNext() == null) {
                this.myBlock.setHadBlankAfterItemParagraph(true);
            }
            this.myHadBlankLine = true;
            return BlockContinue.atIndex(parserState.getNextNonSpaceIndex());
        }
        if (!$assertionsDisabled && !(this.myBlock.getParent() instanceof ListBlock)) {
            throw new AssertionError();
        }
        ListBlockParser listBlockParser = (ListBlockParser) parserState.getActiveBlockParser(this.myBlock.getParent());
        ParserEmulationProfile parserEmulationProfile = this.myOptions.getParserEmulationProfile();
        ParserEmulationProfile parserEmulationProfile2 = parserEmulationProfile.family;
        int contentIndent = getContentIndent();
        if (parserEmulationProfile2 == ParserEmulationProfile.COMMONMARK) {
            int indent = parserState.getIndent();
            int column = parserState.getColumn() + contentIndent;
            if (indent >= contentIndent + this.myOptions.getCodeIndent()) {
                listBlockParser.setItemHandledLine(parserState.getLine());
                return continueAtColumn(column);
            }
            ListBlockParser.ListData parseListMarker = ListBlockParser.parseListMarker(this.myOptions, this.myOptions.getCodeIndent(), parserState);
            if (indent >= contentIndent) {
                if (parseListMarker != null) {
                    BlockParser activeBlockParser = parserState.getActiveBlockParser();
                    if ((activeBlockParser.isParagraphParser() && (activeBlockParser.getBlock().getParent() instanceof ListItem) && activeBlockParser.getBlock() == activeBlockParser.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker.listBlock, parseListMarker.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker.listBlock, parseListMarker.isEmpty))) {
                        listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                        return continueAtColumn(column);
                    }
                    listBlockParser.setItemHandledNewListLine(parserState.getLine());
                    return continueAtColumn(column);
                }
                if (this.myIsEmpty) {
                    listBlockParser.setItemHandledLine(parserState.getLine());
                    return BlockContinue.none();
                }
                listBlockParser.setItemHandledLine(parserState.getLine());
                return continueAtColumn(column);
            }
            if (parseListMarker != null) {
                if (!this.myHadBlankLine && !this.myOptions.canInterrupt(parseListMarker.listBlock, parseListMarker.isEmpty, true)) {
                    listBlockParser.setItemHandledLine(parserState.getLine());
                    return continueAtColumn(parserState.getColumn() + indent);
                }
                if (!(this.myOptions.isItemTypeMismatchToNewList() && this.myOptions.isItemTypeMismatchToSubList() && this.myHadBlankLine) && this.myOptions.startSubList(listBlockParser.getBlock(), parseListMarker.listBlock)) {
                    listBlockParser.setItemHandledNewListLine(parserState.getLine());
                    return continueAtColumn(parserState.getColumn() + indent);
                }
                if (this.myOptions.startNewList(listBlockParser.getBlock(), parseListMarker.listBlock)) {
                    listBlockParser.setItemHandledNewListLine(parserState.getLine());
                    return BlockContinue.none();
                }
                listBlockParser.setItemHandledNewItemLine(parserState.getLine());
                return BlockContinue.none();
            }
        } else {
            int itemIndent = this.myOptions.getItemIndent();
            if (parserEmulationProfile2 == ParserEmulationProfile.FIXED_INDENT) {
                int indent2 = parserState.getIndent();
                int column2 = parserState.getColumn() + itemIndent;
                if (indent2 >= this.myOptions.getCodeIndent()) {
                    if (this.myBlock.getFirstChild() != null && this.myBlock.getFirstChild() == this.myBlock.getLastChild()) {
                        BlockParser activeBlockParser2 = parserState.getActiveBlockParser();
                        if (activeBlockParser2.isParagraphParser() && activeBlockParser2.getBlock() == this.myBlock.getFirstChild()) {
                            listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                            return continueAtColumn(column2);
                        }
                    }
                    listBlockParser.setItemHandledLine(parserState.getLine());
                    return continueAtColumn(column2);
                }
                ListBlockParser.ListData parseListMarker2 = ListBlockParser.parseListMarker(this.myOptions, -1, parserState);
                if (indent2 >= itemIndent) {
                    if (parseListMarker2 != null) {
                        BlockParser activeBlockParser3 = parserState.getActiveBlockParser();
                        if ((activeBlockParser3.isParagraphParser() && (activeBlockParser3.getBlock().getParent() instanceof ListItem) && activeBlockParser3.getBlock() == activeBlockParser3.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker2.listBlock, parseListMarker2.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker2.listBlock, parseListMarker2.isEmpty))) {
                            listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + indent2);
                        }
                        listBlockParser.setItemHandledNewListLine(parserState.getLine());
                        return continueAtColumn(column2);
                    }
                    if (this.myIsEmpty) {
                        listBlockParser.setItemHandledLine(parserState.getLine());
                        return BlockContinue.none();
                    }
                    listBlockParser.setItemHandledLine(parserState.getLine());
                    return continueAtColumn(column2);
                }
                if (parseListMarker2 != null) {
                    if (!this.myHadBlankLine && !this.myOptions.canInterrupt(parseListMarker2.listBlock, parseListMarker2.isEmpty, true)) {
                        listBlockParser.setItemHandledLine(parserState.getLine());
                        return continueAtColumn(parserState.getColumn() + indent2);
                    }
                    if (!(this.myOptions.isItemTypeMismatchToNewList() && this.myOptions.isItemTypeMismatchToSubList() && this.myHadBlankLine) && this.myOptions.startSubList(listBlockParser.getBlock(), parseListMarker2.listBlock)) {
                        listBlockParser.setItemHandledNewListLine(parserState.getLine());
                        return continueAtColumn(parserState.getColumn() + indent2);
                    }
                    if (this.myOptions.startNewList(listBlockParser.getBlock(), parseListMarker2.listBlock)) {
                        listBlockParser.setItemHandledNewListLine(parserState.getLine());
                        return BlockContinue.none();
                    }
                    listBlockParser.setItemHandledNewItemLine(parserState.getLine());
                    return BlockContinue.none();
                }
            } else {
                int i = listBlockParser.getListData().markerIndent;
                if (parserEmulationProfile2 != ParserEmulationProfile.KRAMDOWN) {
                    if (parserEmulationProfile == ParserEmulationProfile.GITHUB_DOC) {
                        int indent3 = parserState.getIndent();
                        parserState.getIndex();
                        int maxLimit = Utils.maxLimit(indent3, contentIndent, i + 4);
                        if (indent3 >= this.myOptions.getCodeIndent()) {
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + Utils.maxLimit(contentIndent, itemIndent));
                        }
                        ListBlockParser.ListData parseListMarker3 = ListBlockParser.parseListMarker(this.myOptions, -1, parserState);
                        if (indent3 > itemIndent) {
                            if (parseListMarker3 != null) {
                                BlockParser activeBlockParser4 = parserState.getActiveBlockParser();
                                if ((activeBlockParser4.isParagraphParser() && (activeBlockParser4.getBlock().getParent() instanceof ListItem) && activeBlockParser4.getBlock() == activeBlockParser4.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker3.listBlock, parseListMarker3.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker3.listBlock, parseListMarker3.isEmpty))) {
                                    listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                    return continueAtColumn(parserState.getColumn() + indent3);
                                }
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + maxLimit);
                            }
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + itemIndent);
                        }
                        if (indent3 > i) {
                            if (parseListMarker3 != null) {
                                BlockParser activeBlockParser5 = parserState.getActiveBlockParser();
                                if ((activeBlockParser5.isParagraphParser() && (activeBlockParser5.getBlock().getParent() instanceof ListItem) && activeBlockParser5.getBlock() == activeBlockParser5.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker3.listBlock, parseListMarker3.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker3.listBlock, parseListMarker3.isEmpty))) {
                                    listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                    return continueAtColumn(parserState.getColumn() + indent3);
                                }
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + maxLimit);
                            }
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + maxLimit);
                        }
                        if (parseListMarker3 != null) {
                            if (!(this.myOptions.isItemTypeMismatchToNewList() && this.myOptions.isItemTypeMismatchToSubList() && this.myHadBlankLine) && this.myOptions.startSubList(listBlockParser.getBlock(), parseListMarker3.listBlock)) {
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + maxLimit);
                            }
                            if (this.myOptions.startNewList(listBlockParser.getBlock(), parseListMarker3.listBlock)) {
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return BlockContinue.none();
                            }
                            BlockParser activeBlockParser6 = parserState.getActiveBlockParser();
                            if ((activeBlockParser6.isParagraphParser() && (activeBlockParser6.getBlock().getParent() instanceof ListItem) && activeBlockParser6.getBlock() == activeBlockParser6.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker3.listBlock, parseListMarker3.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker3.listBlock, parseListMarker3.isEmpty))) {
                                listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + indent3);
                            }
                            listBlockParser.setItemHandledNewItemLine(parserState.getLine());
                            return BlockContinue.none();
                        }
                        if (!this.myHadBlankLine || (parserState.getActiveBlockParser() instanceof FencedCodeBlockParser)) {
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + indent3);
                        }
                    } else if (parserEmulationProfile2 == ParserEmulationProfile.MARKDOWN) {
                        int indent4 = parserState.getIndent();
                        if (indent4 >= this.myOptions.getCodeIndent()) {
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + itemIndent);
                        }
                        ListBlockParser.ListData parseListMarker4 = ListBlockParser.parseListMarker(this.myOptions, -1, parserState);
                        if (indent4 > itemIndent) {
                            if (parseListMarker4 != null) {
                                BlockParser activeBlockParser7 = parserState.getActiveBlockParser();
                                if ((activeBlockParser7.isParagraphParser() && (activeBlockParser7.getBlock().getParent() instanceof ListItem) && activeBlockParser7.getBlock() == activeBlockParser7.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker4.listBlock, parseListMarker4.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker4.listBlock, parseListMarker4.isEmpty))) {
                                    listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                    return continueAtColumn(parserState.getColumn() + indent4);
                                }
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + itemIndent);
                            }
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + itemIndent);
                        }
                        if (indent4 > i) {
                            if (parseListMarker4 != null) {
                                BlockParser activeBlockParser8 = parserState.getActiveBlockParser();
                                if ((activeBlockParser8.isParagraphParser() && (activeBlockParser8.getBlock().getParent() instanceof ListItem) && activeBlockParser8.getBlock() == activeBlockParser8.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker4.listBlock, parseListMarker4.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker4.listBlock, parseListMarker4.isEmpty))) {
                                    listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                    return continueAtColumn(parserState.getColumn() + indent4);
                                }
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + indent4);
                            }
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + indent4);
                        }
                        if (parseListMarker4 != null) {
                            if (!(this.myOptions.isItemTypeMismatchToNewList() && this.myOptions.isItemTypeMismatchToSubList() && this.myHadBlankLine) && this.myOptions.startSubList(listBlockParser.getBlock(), parseListMarker4.listBlock)) {
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + indent4);
                            }
                            if (this.myOptions.startNewList(listBlockParser.getBlock(), parseListMarker4.listBlock)) {
                                listBlockParser.setItemHandledNewListLine(parserState.getLine());
                                return BlockContinue.none();
                            }
                            BlockParser activeBlockParser9 = parserState.getActiveBlockParser();
                            if ((activeBlockParser9.isParagraphParser() && (activeBlockParser9.getBlock().getParent() instanceof ListItem) && activeBlockParser9.getBlock() == activeBlockParser9.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker4.listBlock, parseListMarker4.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker4.listBlock, parseListMarker4.isEmpty))) {
                                listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                return continueAtColumn(parserState.getColumn() + indent4);
                            }
                            listBlockParser.setItemHandledNewItemLine(parserState.getLine());
                            return BlockContinue.none();
                        }
                    }
                } else {
                    int indent5 = parserState.getIndent();
                    int column3 = parserState.getColumn() + contentIndent;
                    ListBlockParser.ListData parseListMarker5 = ListBlockParser.parseListMarker(this.myOptions, -1, parserState);
                    if (indent5 >= contentIndent) {
                        if (parseListMarker5 != null) {
                            BlockParser activeBlockParser10 = parserState.getActiveBlockParser();
                            if ((activeBlockParser10.isParagraphParser() && (activeBlockParser10.getBlock().getParent() instanceof ListItem) && activeBlockParser10.getBlock() == activeBlockParser10.getBlock().getParent().getFirstChild()) && (!this.myOptions.canInterrupt(parseListMarker5.listBlock, parseListMarker5.isEmpty, true) || !this.myOptions.canStartSubList(parseListMarker5.listBlock, parseListMarker5.isEmpty))) {
                                listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                                return continueAtColumn(column3);
                            }
                            listBlockParser.setItemHandledNewListLine(parserState.getLine());
                            return continueAtColumn(column3);
                        }
                        if (this.myIsEmpty) {
                            listBlockParser.setItemHandledLine(parserState.getLine());
                            return BlockContinue.none();
                        }
                        listBlockParser.setItemHandledLine(parserState.getLine());
                        return continueAtColumn(column3);
                    }
                    if (indent5 >= i + itemIndent) {
                        if (this.myHadBlankLine) {
                            if (this.myBlock.isHadBlankAfterItemParagraph()) {
                                this.myBlock.setLoose(true);
                            }
                            listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                            return BlockContinue.none();
                        }
                        listBlockParser.setItemHandledLineSkipActive(parserState.getLine());
                        return continueAtColumn(parserState.getColumn() + indent5);
                    }
                    if (parseListMarker5 != null && indent5 >= i) {
                        if (!(this.myOptions.isItemTypeMismatchToNewList() && this.myOptions.isItemTypeMismatchToSubList() && this.myHadBlankLine) && this.myOptions.startSubList(listBlockParser.getBlock(), parseListMarker5.listBlock)) {
                            listBlockParser.setItemHandledNewListLine(parserState.getLine());
                            return continueAtColumn(parserState.getColumn() + indent5);
                        }
                        if (this.myOptions.startNewList(listBlockParser.getBlock(), parseListMarker5.listBlock)) {
                            listBlockParser.setItemHandledNewListLine(parserState.getLine());
                            return BlockContinue.none();
                        }
                        listBlockParser.setItemHandledNewItemLine(parserState.getLine());
                        return BlockContinue.none();
                    }
                }
            }
        }
        return BlockContinue.none();
    }
}

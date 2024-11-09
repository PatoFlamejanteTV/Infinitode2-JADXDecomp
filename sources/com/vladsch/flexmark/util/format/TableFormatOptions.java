package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.format.options.DiscretionaryText;
import com.vladsch.flexmark.util.format.options.TableCaptionHandling;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/TableFormatOptions.class */
public class TableFormatOptions implements MutableDataSetter {
    public static final char INTELLIJ_DUMMY_IDENTIFIER_CHAR = 31;
    public static final String INTELLIJ_DUMMY_IDENTIFIER = SequenceUtils.US_CHARS;
    public static final CharPredicate INTELLIJ_DUMMY_IDENTIFIER_SET = i -> {
        return i == 31;
    };
    public static final DataKey<Boolean> FORMAT_TABLE_LEAD_TRAIL_PIPES = new DataKey<>("FORMAT_TABLE_LEAD_TRAIL_PIPES", Boolean.TRUE);
    public static final DataKey<Boolean> FORMAT_TABLE_SPACE_AROUND_PIPES = new DataKey<>("FORMAT_TABLE_SPACE_AROUND_PIPES", Boolean.TRUE);
    public static final DataKey<Boolean> FORMAT_TABLE_ADJUST_COLUMN_WIDTH = new DataKey<>("FORMAT_TABLE_ADJUST_COLUMN_WIDTH", Boolean.TRUE);
    public static final DataKey<Boolean> FORMAT_TABLE_APPLY_COLUMN_ALIGNMENT = new DataKey<>("FORMAT_TABLE_APPLY_COLUMN_ALIGNMENT", Boolean.TRUE);
    public static final DataKey<Boolean> FORMAT_TABLE_FILL_MISSING_COLUMNS = new DataKey<>("FORMAT_TABLE_FILL_MISSING_COLUMNS", Boolean.FALSE);
    public static final NullableDataKey<Integer> FORMAT_TABLE_FILL_MISSING_MIN_COLUMN = new NullableDataKey<>("FORMAT_TABLE_FILL_MISSING_MIN_COLUMN", (Integer) null);
    public static final DataKey<DiscretionaryText> FORMAT_TABLE_LEFT_ALIGN_MARKER = new DataKey<>("FORMAT_TABLE_LEFT_ALIGN_MARKER", DiscretionaryText.AS_IS);
    public static final DataKey<Integer> FORMAT_TABLE_MIN_SEPARATOR_COLUMN_WIDTH = new DataKey<>("FORMAT_TABLE_MIN_SEPARATOR_COLUMN_WIDTH", 3);
    public static final DataKey<Integer> FORMAT_TABLE_MIN_SEPARATOR_DASHES = new DataKey<>("FORMAT_TABLE_MIN_SEPARATOR_DASHES", 1);
    public static final DataKey<Boolean> FORMAT_TABLE_TRIM_CELL_WHITESPACE = new DataKey<>("FORMAT_TABLE_TRIM_CELL_WHITESPACE", Boolean.TRUE);
    public static final DataKey<TableCaptionHandling> FORMAT_TABLE_CAPTION = new DataKey<>("FORMAT_TABLE_CAPTION", TableCaptionHandling.AS_IS);
    public static final DataKey<DiscretionaryText> FORMAT_TABLE_CAPTION_SPACES = new DataKey<>("FORMAT_TABLE_CAPTION_SPACES", DiscretionaryText.AS_IS);
    public static final DataKey<String> FORMAT_TABLE_INDENT_PREFIX = new DataKey<>("FORMAT_TABLE_INDENT_PREFIX", "");
    public static final DataKey<TableManipulator> FORMAT_TABLE_MANIPULATOR = new DataKey<>("FORMAT_TABLE_MANIPULATOR", TableManipulator.NULL);
    public static final DataKey<CharWidthProvider> FORMAT_CHAR_WIDTH_PROVIDER = new DataKey<>("FORMAT_CHAR_WIDTH_PROVIDER", CharWidthProvider.NULL);
    public static final DataKey<Boolean> FORMAT_TABLE_DUMP_TRACKING_OFFSETS = new DataKey<>("FORMAT_TABLE_DUMP_TRACKING_OFFSETS", Boolean.FALSE);
    public final boolean leadTrailPipes;
    public final boolean spaceAroundPipes;
    public final boolean adjustColumnWidth;
    public final boolean applyColumnAlignment;
    public final boolean fillMissingColumns;
    public final Integer formatTableFillMissingMinColumn;
    public final boolean trimCellWhitespace;
    public final boolean dumpIntellijOffsets;
    public final DiscretionaryText leftAlignMarker;
    public final TableCaptionHandling formatTableCaption;
    public final DiscretionaryText formatTableCaptionSpaces;
    public final int minSeparatorColumnWidth;
    public final int minSeparatorDashes;
    public final CharWidthProvider charWidthProvider;
    public final String formatTableIndentPrefix;
    public final TableManipulator tableManipulator;
    public final int spaceWidth;
    public final int spacePad;
    public final int pipeWidth;
    public final int colonWidth;
    public final int dashWidth;

    public TableFormatOptions() {
        this(null);
    }

    public TableFormatOptions(DataHolder dataHolder) {
        this.leadTrailPipes = FORMAT_TABLE_LEAD_TRAIL_PIPES.get(dataHolder).booleanValue();
        this.spaceAroundPipes = FORMAT_TABLE_SPACE_AROUND_PIPES.get(dataHolder).booleanValue();
        this.adjustColumnWidth = FORMAT_TABLE_ADJUST_COLUMN_WIDTH.get(dataHolder).booleanValue();
        this.applyColumnAlignment = FORMAT_TABLE_APPLY_COLUMN_ALIGNMENT.get(dataHolder).booleanValue();
        this.fillMissingColumns = FORMAT_TABLE_FILL_MISSING_COLUMNS.get(dataHolder).booleanValue();
        this.formatTableFillMissingMinColumn = FORMAT_TABLE_FILL_MISSING_MIN_COLUMN.get(dataHolder);
        this.leftAlignMarker = FORMAT_TABLE_LEFT_ALIGN_MARKER.get(dataHolder);
        this.minSeparatorColumnWidth = FORMAT_TABLE_MIN_SEPARATOR_COLUMN_WIDTH.get(dataHolder).intValue();
        this.minSeparatorDashes = FORMAT_TABLE_MIN_SEPARATOR_DASHES.get(dataHolder).intValue();
        this.charWidthProvider = FORMAT_CHAR_WIDTH_PROVIDER.get(dataHolder);
        this.formatTableCaption = FORMAT_TABLE_CAPTION.get(dataHolder);
        this.formatTableCaptionSpaces = FORMAT_TABLE_CAPTION_SPACES.get(dataHolder);
        this.formatTableIndentPrefix = FORMAT_TABLE_INDENT_PREFIX.get(dataHolder);
        this.trimCellWhitespace = FORMAT_TABLE_TRIM_CELL_WHITESPACE.get(dataHolder).booleanValue();
        this.tableManipulator = FORMAT_TABLE_MANIPULATOR.get(dataHolder);
        this.dumpIntellijOffsets = FORMAT_TABLE_DUMP_TRACKING_OFFSETS.get(dataHolder).booleanValue();
        this.spaceWidth = this.charWidthProvider.getSpaceWidth();
        this.spacePad = this.spaceAroundPipes ? 2 * this.spaceWidth : 0;
        this.pipeWidth = this.charWidthProvider.getCharWidth('|');
        this.colonWidth = this.charWidthProvider.getCharWidth(':');
        this.dashWidth = this.charWidthProvider.getCharWidth('-');
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_LEAD_TRAIL_PIPES, (DataKey<Boolean>) Boolean.valueOf(this.leadTrailPipes));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_SPACE_AROUND_PIPES, (DataKey<Boolean>) Boolean.valueOf(this.spaceAroundPipes));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_ADJUST_COLUMN_WIDTH, (DataKey<Boolean>) Boolean.valueOf(this.adjustColumnWidth));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_APPLY_COLUMN_ALIGNMENT, (DataKey<Boolean>) Boolean.valueOf(this.applyColumnAlignment));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_FILL_MISSING_COLUMNS, (DataKey<Boolean>) Boolean.valueOf(this.fillMissingColumns));
        mutableDataHolder.set((NullableDataKey<NullableDataKey<Integer>>) FORMAT_TABLE_FILL_MISSING_MIN_COLUMN, (NullableDataKey<Integer>) this.formatTableFillMissingMinColumn);
        mutableDataHolder.set((DataKey<DataKey<DiscretionaryText>>) FORMAT_TABLE_LEFT_ALIGN_MARKER, (DataKey<DiscretionaryText>) this.leftAlignMarker);
        mutableDataHolder.set((DataKey<DataKey<Integer>>) FORMAT_TABLE_MIN_SEPARATOR_COLUMN_WIDTH, (DataKey<Integer>) Integer.valueOf(this.minSeparatorColumnWidth));
        mutableDataHolder.set((DataKey<DataKey<Integer>>) FORMAT_TABLE_MIN_SEPARATOR_DASHES, (DataKey<Integer>) Integer.valueOf(this.minSeparatorDashes));
        mutableDataHolder.set((DataKey<DataKey<CharWidthProvider>>) FORMAT_CHAR_WIDTH_PROVIDER, (DataKey<CharWidthProvider>) this.charWidthProvider);
        mutableDataHolder.set((DataKey<DataKey<TableCaptionHandling>>) FORMAT_TABLE_CAPTION, (DataKey<TableCaptionHandling>) this.formatTableCaption);
        mutableDataHolder.set((DataKey<DataKey<DiscretionaryText>>) FORMAT_TABLE_CAPTION_SPACES, (DataKey<DiscretionaryText>) this.formatTableCaptionSpaces);
        mutableDataHolder.set((DataKey<DataKey<String>>) FORMAT_TABLE_INDENT_PREFIX, (DataKey<String>) this.formatTableIndentPrefix);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_TRIM_CELL_WHITESPACE, (DataKey<Boolean>) Boolean.valueOf(this.trimCellWhitespace));
        mutableDataHolder.set((DataKey<DataKey<TableManipulator>>) FORMAT_TABLE_MANIPULATOR, (DataKey<TableManipulator>) this.tableManipulator);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) FORMAT_TABLE_DUMP_TRACKING_OFFSETS, (DataKey<Boolean>) Boolean.valueOf(this.dumpIntellijOffsets));
        return mutableDataHolder;
    }
}

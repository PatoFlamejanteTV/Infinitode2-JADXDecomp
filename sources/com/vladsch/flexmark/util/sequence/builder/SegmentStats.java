package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.DelimitedBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentStats.class */
public class SegmentStats {
    public static final int NULL_REPEATED_CHAR = -1;
    public static final int NOT_REPEATED_CHAR = -2;
    protected int textLength = 0;
    protected int textSegments = 0;
    protected int textSegmentLength = 0;
    protected int textSpaceLength = 0;
    protected int textSpaceSegments = 0;
    protected int textSpaceSegmentLength = 0;
    protected int textFirst256Length = 0;
    protected int textFirst256Segments = 0;
    protected int textFirst256SegmentLength = 0;
    protected int repeatedChar = -1;
    protected final boolean trackFirst256;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SegmentStats.class.desiredAssertionStatus();
    }

    public SegmentStats(boolean z) {
        this.trackFirst256 = z;
    }

    public int getTextLength() {
        return this.textLength;
    }

    public int getTextSpaceLength() {
        return this.textSpaceLength;
    }

    public int getTextFirst256Length() {
        return this.textFirst256Length;
    }

    public boolean isTrackTextFirst256() {
        return this.trackFirst256;
    }

    public int getTextSegments() {
        return this.textSegments;
    }

    public int getTextSpaceSegments() {
        return this.textSpaceSegments;
    }

    public int getTextFirst256Segments() {
        return this.textFirst256Segments;
    }

    public boolean isEmpty() {
        if (this.textLength != 0 || this.textSegments != 0 || this.textSegmentLength != 0) {
            return false;
        }
        if (this.trackFirst256) {
            return this.textSpaceLength == 0 && this.textSpaceSegments == 0 && this.textSpaceSegmentLength == 0 && this.textFirst256Length == 0 && this.textFirst256Segments == 0 && this.textFirst256SegmentLength == 0;
        }
        return true;
    }

    public boolean isValid() {
        if (this.textLength < this.textSegments) {
            return false;
        }
        if (this.trackFirst256) {
            return this.textLength >= this.textFirst256Length && this.textSegments >= this.textFirst256Segments && this.textFirst256Length >= this.textFirst256Segments && this.textFirst256Length >= this.textSpaceLength && this.textFirst256Segments >= this.textSpaceSegments && this.textSpaceLength >= this.textSpaceSegments;
        }
        return true;
    }

    public SegmentStats committedCopy() {
        SegmentStats segmentStats = new SegmentStats(this.trackFirst256);
        segmentStats.textLength = this.textLength;
        segmentStats.textSegments = this.textSegments;
        segmentStats.textSegmentLength = this.textSegmentLength;
        if (this.trackFirst256) {
            segmentStats.textSpaceLength = this.textSpaceLength;
            segmentStats.textSpaceSegments = this.textSpaceSegments;
            segmentStats.textSpaceSegmentLength = this.textSpaceSegmentLength;
            segmentStats.textFirst256Length = this.textFirst256Length;
            segmentStats.textFirst256Segments = this.textFirst256Segments;
            segmentStats.textFirst256SegmentLength = this.textFirst256SegmentLength;
        }
        segmentStats.commitText();
        return segmentStats;
    }

    public void clear() {
        this.textLength = 0;
        this.textSegments = 0;
        this.textSegmentLength = 0;
        this.repeatedChar = -1;
        if (this.trackFirst256) {
            this.textSpaceLength = 0;
            this.textSpaceSegments = 0;
            this.textSpaceSegmentLength = 0;
            this.textFirst256Length = 0;
            this.textFirst256Segments = 0;
            this.textFirst256SegmentLength = 0;
        }
    }

    public void add(SegmentStats segmentStats) {
        this.textLength += segmentStats.textLength;
        this.textSegments += segmentStats.textSegments;
        if (this.trackFirst256 && segmentStats.trackFirst256) {
            this.textSpaceLength += segmentStats.textSpaceLength;
            this.textSpaceSegments += segmentStats.textSpaceSegments;
            this.textFirst256Length += segmentStats.textFirst256Length;
            this.textFirst256Segments += segmentStats.textFirst256Segments;
        }
    }

    public void remove(SegmentStats segmentStats) {
        if (!$assertionsDisabled && this.textLength < segmentStats.textLength) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && this.textSegments < segmentStats.textSegments) {
            throw new AssertionError();
        }
        this.textLength -= segmentStats.textLength;
        this.textSegments -= segmentStats.textSegments;
        this.textSegmentLength = this.textLength;
        if (this.trackFirst256 && segmentStats.trackFirst256) {
            if (!$assertionsDisabled && this.textSpaceLength < segmentStats.textSpaceLength) {
                throw new AssertionError();
            }
            if (!$assertionsDisabled && this.textSpaceSegments < segmentStats.textSpaceSegments) {
                throw new AssertionError();
            }
            if (!$assertionsDisabled && this.textFirst256Length < segmentStats.textFirst256Length) {
                throw new AssertionError();
            }
            if (!$assertionsDisabled && this.textFirst256Segments < segmentStats.textFirst256Segments) {
                throw new AssertionError();
            }
            this.textSpaceLength -= segmentStats.textSpaceLength;
            this.textSpaceSegments -= segmentStats.textSpaceSegments;
            this.textFirst256Length -= segmentStats.textFirst256Length;
            this.textFirst256Segments -= segmentStats.textFirst256Segments;
            this.textSpaceSegmentLength = this.textSpaceLength;
            this.textFirst256SegmentLength = this.textFirst256Length;
        }
    }

    public boolean isTextFirst256() {
        return this.textFirst256Length - this.textFirst256SegmentLength == this.textLength - this.textSegmentLength;
    }

    public boolean isTextRepeatedSpace() {
        return this.textSpaceLength - this.textSpaceSegmentLength == this.textLength - this.textSegmentLength;
    }

    public boolean isRepeatedText() {
        return this.repeatedChar >= 0;
    }

    public void commitText() {
        if (this.textLength > this.textSegmentLength) {
            this.textSegments++;
            this.repeatedChar = -1;
            if (this.trackFirst256) {
                int i = this.textLength - this.textSegmentLength;
                if (this.textSpaceLength - this.textSpaceSegmentLength == i) {
                    this.textSpaceSegments++;
                }
                if (this.textFirst256Length - this.textFirst256SegmentLength == i) {
                    this.textFirst256Segments++;
                }
            }
            this.textSegmentLength = this.textLength;
            if (this.trackFirst256) {
                this.textSpaceSegmentLength = this.textSpaceLength;
                this.textFirst256SegmentLength = this.textFirst256Length;
            }
        }
    }

    public void addText(CharSequence charSequence) {
        this.textLength += charSequence.length();
        if (this.trackFirst256) {
            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                char charAt = charSequence.charAt(i);
                if (this.repeatedChar == -1) {
                    this.repeatedChar = charAt;
                } else if (this.repeatedChar != charAt) {
                    this.repeatedChar = -2;
                }
                if (charAt < 256) {
                    if (charAt == ' ') {
                        this.textSpaceLength++;
                    }
                    this.textFirst256Length++;
                }
            }
        }
    }

    public void addText(char c) {
        this.textLength++;
        if (this.trackFirst256) {
            if (this.repeatedChar == -1) {
                this.repeatedChar = c;
            } else if (this.repeatedChar != c) {
                this.repeatedChar = -2;
            }
            if (c < 256) {
                if (c == ' ') {
                    this.textSpaceLength++;
                }
                this.textFirst256Length++;
            }
        }
    }

    public void addText(char c, int i) {
        if (!$assertionsDisabled && i <= 0) {
            throw new AssertionError();
        }
        this.textLength += i;
        if (this.trackFirst256) {
            if (this.repeatedChar == -1) {
                this.repeatedChar = c;
            } else if (this.repeatedChar != c) {
                this.repeatedChar = -2;
            }
            if (c < 256) {
                if (c == ' ') {
                    this.textSpaceLength += i;
                }
                this.textFirst256Length += i;
            }
        }
    }

    public void removeText(CharSequence charSequence) {
        this.textLength -= charSequence.length();
        if (this.trackFirst256) {
            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                char charAt = charSequence.charAt(i);
                if (this.repeatedChar == -1) {
                    this.repeatedChar = charAt;
                } else if (this.repeatedChar != charAt) {
                    this.repeatedChar = -2;
                }
                if (charAt < 256) {
                    if (charAt == ' ') {
                        if (!$assertionsDisabled && this.textSpaceLength <= 0) {
                            throw new AssertionError();
                        }
                        this.textSpaceLength--;
                    }
                    if (!$assertionsDisabled && this.textFirst256Length <= 0) {
                        throw new AssertionError();
                    }
                    this.textFirst256Length--;
                }
            }
        }
        if (this.textLength == this.textSegmentLength) {
            this.repeatedChar = -1;
        }
    }

    public String toString() {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(", ");
        delimitedBuilder.append("s=").append(this.textSpaceSegments).append(":").append(this.textSpaceLength).mark().append("u=").append(this.textFirst256Segments).append(":").append(this.textFirst256Length).mark().append("t=").append(this.textSegments).append(":").append(this.textLength);
        return delimitedBuilder.toString();
    }
}

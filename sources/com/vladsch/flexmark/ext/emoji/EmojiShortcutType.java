package com.vladsch.flexmark.ext.emoji;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/EmojiShortcutType.class */
public enum EmojiShortcutType {
    EMOJI_CHEAT_SHEET(true, false),
    GITHUB(false, true),
    ANY_EMOJI_CHEAT_SHEET_PREFERRED(true, true),
    ANY_GITHUB_PREFERRED(true, true);

    public final boolean isEmojiCheatSheet;
    public final boolean isGitHub;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !EmojiShortcutType.class.desiredAssertionStatus();
    }

    EmojiShortcutType(boolean z, boolean z2) {
        this.isEmojiCheatSheet = z;
        this.isGitHub = z2;
    }

    public final String getPreferred(String str, String str2) {
        switch (this) {
            case EMOJI_CHEAT_SHEET:
                return str;
            case GITHUB:
                return str2;
            case ANY_EMOJI_CHEAT_SHEET_PREFERRED:
                return str != null ? str : str2;
            case ANY_GITHUB_PREFERRED:
                return str2 != null ? str2 : str;
            default:
                if ($assertionsDisabled) {
                    return null;
                }
                throw new AssertionError("Missing Case Statement");
        }
    }
}

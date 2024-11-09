package com.vladsch.flexmark.ext.emoji.internal;

import com.vladsch.flexmark.ext.emoji.Emoji;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.ext.emoji.internal.EmojiReference;
import com.vladsch.flexmark.html2md.converter.internal.HtmlConverterCoreNodeRenderer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiResolvedShortcut.class */
public class EmojiResolvedShortcut {
    public final EmojiReference.Emoji emoji;
    public final String emojiText;
    public final boolean isUnicode;
    public final String alt;

    public EmojiResolvedShortcut(EmojiReference.Emoji emoji, String str, boolean z, String str2) {
        this.emoji = emoji;
        this.emojiText = str;
        this.isUnicode = z;
        this.alt = str2;
    }

    public static EmojiResolvedShortcut getEmojiText(Emoji emoji, EmojiShortcutType emojiShortcutType, EmojiImageType emojiImageType, String str) {
        return getEmojiText(emoji.getText().toString(), emojiShortcutType, emojiImageType, str);
    }

    public static EmojiResolvedShortcut getEmojiText(String str, EmojiShortcutType emojiShortcutType, EmojiImageType emojiImageType, String str2) {
        EmojiReference.Emoji emojiFromShortcut = EmojiShortcuts.getEmojiFromShortcut(str);
        String str3 = null;
        boolean z = false;
        String str4 = null;
        if (emojiFromShortcut != null) {
            String str5 = null;
            String str6 = null;
            if (emojiImageType.isUnicode && emojiFromShortcut.unicodeChars != null) {
                str5 = EmojiShortcuts.getUnicodeChars(emojiFromShortcut);
            }
            if (emojiImageType.isImage) {
                String str7 = null;
                String str8 = null;
                if (emojiShortcutType.isGitHub && emojiFromShortcut.githubFile != null) {
                    str7 = "https://github.githubassets.com/images/icons/emoji/" + emojiFromShortcut.githubFile;
                }
                if (emojiShortcutType.isEmojiCheatSheet && emojiFromShortcut.emojiCheatSheetFile != null) {
                    str8 = str2 + emojiFromShortcut.emojiCheatSheetFile;
                }
                str6 = emojiShortcutType.getPreferred(str8, str7);
            }
            if (str6 != null || str5 != null) {
                if (str5 != null) {
                    str3 = str5;
                    z = true;
                } else {
                    str3 = str6;
                }
                str4 = HtmlConverterCoreNodeRenderer.EMOJI_ALT_PREFIX + emojiFromShortcut.category + ":" + emojiFromShortcut.shortcut;
            }
        }
        return new EmojiResolvedShortcut(emojiFromShortcut, str3, z, str4);
    }
}

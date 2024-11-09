package com.vladsch.flexmark.ext.emoji.internal;

import com.vladsch.flexmark.ext.emoji.internal.EmojiReference;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.File;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiShortcuts.class */
public class EmojiShortcuts {
    public static final String gitHubUrlPrefix = "https://github.githubassets.com/images/icons/emoji/";
    private static final HashMap<String, EmojiReference.Emoji> emojiShortcuts = new HashMap<>();
    private static final HashMap<String, EmojiReference.Emoji> emojiURIs = new HashMap<>();
    private static final HashMap<EmojiReference.Emoji, String> emojiUnicodeChars = new HashMap<>();

    public static synchronized String getUnicodeChars(EmojiReference.Emoji emoji) {
        if (emoji == null || emoji.unicodeChars == null) {
            return null;
        }
        String str = emojiUnicodeChars.get(emoji);
        String str2 = str;
        if (str == null) {
            String[] split = emoji.unicodeChars.replace("U+", "").split(SequenceUtils.SPACE);
            StringBuilder sb = new StringBuilder(16);
            for (String str3 : split) {
                sb.appendCodePoint(Integer.parseInt(str3, 16));
            }
            str2 = sb.toString();
            emojiUnicodeChars.put(emoji, str2);
        }
        return str2;
    }

    public static String extractFileName(String str) {
        String name = new File(str).getName();
        String str2 = name;
        int indexOf = name.indexOf(".png");
        if (indexOf >= 0) {
            str2 = str2.substring(0, indexOf);
        }
        return str2;
    }

    public static HashMap<String, EmojiReference.Emoji> getEmojiShortcuts() {
        updateEmojiShortcuts();
        return emojiShortcuts;
    }

    public static HashMap<String, EmojiReference.Emoji> getEmojiURIs() {
        updateEmojiShortcuts();
        return emojiURIs;
    }

    public static EmojiReference.Emoji getEmojiFromShortcut(String str) {
        updateEmojiShortcuts();
        return emojiShortcuts.get(str);
    }

    public static EmojiReference.Emoji getEmojiFromURI(String str) {
        updateEmojiURIs();
        return emojiURIs.get(extractFileName(str));
    }

    private static synchronized void updateEmojiShortcuts() {
        if (emojiShortcuts.isEmpty()) {
            for (EmojiReference.Emoji emoji : EmojiReference.getEmojiList()) {
                if (emoji.shortcut != null) {
                    emojiShortcuts.put(emoji.shortcut, emoji);
                }
            }
        }
    }

    private static synchronized void updateEmojiURIs() {
        if (emojiURIs.isEmpty()) {
            for (EmojiReference.Emoji emoji : EmojiReference.getEmojiList()) {
                if (emoji.emojiCheatSheetFile != null) {
                    emojiURIs.put(extractFileName(emoji.emojiCheatSheetFile), emoji);
                }
                if (emoji.githubFile != null) {
                    emojiURIs.put(extractFileName(emoji.githubFile), emoji);
                }
                if (emoji.unicodeSampleFile != null) {
                    emojiURIs.put(extractFileName(emoji.unicodeSampleFile), emoji);
                }
            }
        }
    }
}

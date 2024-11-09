package com.vladsch.flexmark.ext.emoji.internal;

import com.prineside.tdi2.events.EventListeners;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiReference.class */
public class EmojiReference {
    public static final String githubUrl = "https://github.githubassets.com/images/icons/emoji/";
    private static ArrayList<Emoji> emojiList = null;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiReference$Emoji.class */
    public static class Emoji {
        public final String shortcut;
        public final String category;
        public final String emojiCheatSheetFile;
        public final String githubFile;
        public final String unicodeChars;
        public final String unicodeSampleFile;
        public final String unicodeCldr;

        public Emoji(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.shortcut = str;
            this.category = str2;
            this.emojiCheatSheetFile = str3;
            this.githubFile = str4;
            this.unicodeChars = str5;
            this.unicodeSampleFile = str6;
            this.unicodeCldr = str7;
        }
    }

    public static List<Emoji> getEmojiList() {
        if (emojiList == null) {
            emojiList = new ArrayList<>(EventListeners.PRIORITY_HIGHEST);
            InputStream resourceAsStream = EmojiReference.class.getResourceAsStream("/EmojiReference.txt");
            if (resourceAsStream == null) {
                throw new IllegalStateException("Could not load /EmojiReference.txt classpath resource");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8));
            try {
                bufferedReader.readLine();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String[] split = readLine.split("\t");
                    try {
                        emojiList.add(new Emoji(split[0].charAt(0) == ' ' ? null : split[0], split[1].charAt(0) == ' ' ? null : split[1], split[2].charAt(0) == ' ' ? null : split[2], split[3].charAt(0) == ' ' ? null : split[3], split[4].charAt(0) == ' ' ? null : split[4], split[5].charAt(0) == ' ' ? null : split[5], split[6].charAt(0) == ' ' ? null : split[6]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new IllegalStateException("Error processing EmojiReference.txt", e);
                    }
                }
            } catch (IOException e2) {
                throw new IllegalStateException("Error processing EmojiReference.txt", e2);
            }
        }
        return emojiList;
    }
}

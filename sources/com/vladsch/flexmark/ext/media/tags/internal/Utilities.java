package com.vladsch.flexmark.ext.media.tags.internal;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/Utilities.class */
final class Utilities {
    private Utilities() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String resolveAudioType(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        boolean z = -1;
        switch (substring.hashCode()) {
            case 108272:
                if (substring.equals("mp3")) {
                    z = 4;
                    break;
                }
                break;
            case 109967:
                if (substring.equals("ogg")) {
                    z = 3;
                    break;
                }
                break;
            case 117484:
                if (substring.equals("wav")) {
                    z = 5;
                    break;
                }
                break;
            case 3145576:
                if (substring.equals("flac")) {
                    z = 6;
                    break;
                }
                break;
            case 3418175:
                if (substring.equals("opus")) {
                    z = false;
                    break;
                }
                break;
            case 3645325:
                if (substring.equals("weba")) {
                    z = true;
                    break;
                }
                break;
            case 3645337:
                if (substring.equals("webm")) {
                    z = 2;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return "audio/ogg; codecs=opus";
            case true:
                return "audio/webm";
            case true:
                return "audio/webm; codecs=opus";
            case true:
                return "audio/ogg";
            case true:
                return "audio/mpeg";
            case true:
                return "audio/wav";
            case true:
                return "audio/flac";
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String resolveVideoType(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        boolean z = -1;
        switch (substring.hashCode()) {
            case 52316:
                if (substring.equals("3gp")) {
                    z = 3;
                    break;
                }
                break;
            case 108273:
                if (substring.equals("mp4")) {
                    z = false;
                    break;
                }
                break;
            case 109982:
                if (substring.equals("ogv")) {
                    z = 2;
                    break;
                }
                break;
            case 3645337:
                if (substring.equals("webm")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return "video/mp4";
            case true:
                return "video/webm";
            case true:
                return "video/ogg";
            case true:
                return "video/3gp";
            default:
                return null;
        }
    }
}

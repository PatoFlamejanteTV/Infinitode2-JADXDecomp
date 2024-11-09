package org.jsoup;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;

/* loaded from: infinitode-2.jar:org/jsoup/Jsoup.class */
public class Jsoup {
    private Jsoup() {
    }

    public static Document parse(String str, String str2) {
        return Parser.parse(str, str2);
    }

    public static Document parse(String str, String str2, Parser parser) {
        return parser.parseInput(str, str2);
    }

    public static Document parse(String str, Parser parser) {
        return parser.parseInput(str, "");
    }

    public static Document parse(String str) {
        return Parser.parse(str, "");
    }

    public static Connection connect(String str) {
        return HttpConnection.connect(str);
    }

    public static Connection newSession() {
        return new HttpConnection();
    }

    public static Document parse(File file, String str, String str2) {
        return DataUtil.load(file, str, str2);
    }

    public static Document parse(File file, String str) {
        return DataUtil.load(file, str, file.getAbsolutePath());
    }

    public static Document parse(File file) {
        return DataUtil.load(file, (String) null, file.getAbsolutePath());
    }

    public static Document parse(File file, String str, String str2, Parser parser) {
        return DataUtil.load(file, str, str2, parser);
    }

    public static Document parse(InputStream inputStream, String str, String str2) {
        return DataUtil.load(inputStream, str, str2);
    }

    public static Document parse(InputStream inputStream, String str, String str2, Parser parser) {
        return DataUtil.load(inputStream, str, str2, parser);
    }

    public static Document parseBodyFragment(String str, String str2) {
        return Parser.parseBodyFragment(str, str2);
    }

    public static Document parseBodyFragment(String str) {
        return Parser.parseBodyFragment(str, "");
    }

    public static Document parse(URL url, int i) {
        Connection connect = HttpConnection.connect(url);
        connect.timeout(i);
        return connect.get();
    }

    public static String clean(String str, String str2, Safelist safelist) {
        return new Cleaner(safelist).clean(parseBodyFragment(str, str2)).body().html();
    }

    public static String clean(String str, Safelist safelist) {
        return clean(str, "", safelist);
    }

    public static String clean(String str, String str2, Safelist safelist, Document.OutputSettings outputSettings) {
        Document clean = new Cleaner(safelist).clean(parseBodyFragment(str, str2));
        clean.outputSettings(outputSettings);
        return clean.body().html();
    }

    public static boolean isValid(String str, Safelist safelist) {
        return new Cleaner(safelist).isValidBodyHtml(str);
    }
}

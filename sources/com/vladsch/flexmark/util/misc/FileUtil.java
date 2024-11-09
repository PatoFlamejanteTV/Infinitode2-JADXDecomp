package com.vladsch.flexmark.util.misc;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/FileUtil.class */
public class FileUtil {
    public static boolean isChildOf(File file, File file2) {
        return Utils.suffixWith(file.getPath(), File.separator).startsWith(Utils.suffixWith(file2.getPath(), File.separator));
    }

    public static String getNameOnly(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return (lastIndexOf <= 0 || lastIndexOf <= name.lastIndexOf(File.separatorChar)) ? name : name.substring(0, lastIndexOf);
    }

    public static String getDotExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return (lastIndexOf <= 0 || lastIndexOf <= name.lastIndexOf(File.separatorChar)) ? "" : name.substring(lastIndexOf);
    }

    public static String pathSlash(File file) {
        String path = file.getPath();
        int lastIndexOf = path.lastIndexOf(File.separatorChar);
        return lastIndexOf != -1 ? path.substring(0, lastIndexOf + 1) : "";
    }

    public static File plus(File file, String str) {
        return new File(file, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.String] */
    public static String getFileContent(File file) {
        ?? sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append(SequenceUtils.EOL);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    sb = sb.toString();
                    return sb;
                }
            }
        } catch (IOException e) {
            sb.printStackTrace();
            return null;
        }
    }

    public static String getFileContentWithExceptions(File file) {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
                sb.append(SequenceUtils.EOL);
            } else {
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                return sb.toString();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.IOException, byte[]] */
    public static byte[] getFileContentBytes(File file) {
        ?? readAllBytes;
        try {
            readAllBytes = Files.readAllBytes(file.toPath());
            return readAllBytes;
        } catch (IOException e) {
            readAllBytes.printStackTrace();
            return null;
        }
    }

    public static byte[] getFileContentBytesWithExceptions(File file) {
        return Files.readAllBytes(file.toPath());
    }
}

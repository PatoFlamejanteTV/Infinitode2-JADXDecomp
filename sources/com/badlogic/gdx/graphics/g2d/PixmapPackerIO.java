package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.Writer;
import java.util.regex.Matcher;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPackerIO.class */
public class PixmapPackerIO {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPackerIO$SaveParameters.class */
    public static class SaveParameters {
        public ImageFormat format = ImageFormat.PNG;
        public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
        public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
        public boolean useIndexes;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/PixmapPackerIO$ImageFormat.class */
    public enum ImageFormat {
        CIM(".cim"),
        PNG(".png");

        private final String extension;

        public final String getExtension() {
            return this.extension;
        }

        ImageFormat(String str) {
            this.extension = str;
        }
    }

    public void save(FileHandle fileHandle, PixmapPacker pixmapPacker) {
        save(fileHandle, pixmapPacker, new SaveParameters());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x006c. Please report as an issue. */
    public void save(FileHandle fileHandle, PixmapPacker pixmapPacker, SaveParameters saveParameters) {
        Writer writer = fileHandle.writer(false);
        int i = 0;
        Array.ArrayIterator<PixmapPacker.Page> it = pixmapPacker.pages.iterator();
        while (it.hasNext()) {
            PixmapPacker.Page next = it.next();
            if (next.rects.size > 0) {
                i++;
                FileHandle sibling = fileHandle.sibling(fileHandle.nameWithoutExtension() + JavaConstant.Dynamic.DEFAULT_NAME + i + saveParameters.format.getExtension());
                switch (saveParameters.format) {
                    case CIM:
                        PixmapIO.writeCIM(sibling, next.image);
                        break;
                    case PNG:
                        PixmapIO.writePNG(sibling, next.image);
                        break;
                }
                writer.write(SequenceUtils.EOL);
                writer.write(sibling.name() + SequenceUtils.EOL);
                writer.write("size: " + next.image.getWidth() + "," + next.image.getHeight() + SequenceUtils.EOL);
                writer.write("format: " + pixmapPacker.pageFormat.name() + SequenceUtils.EOL);
                writer.write("filter: " + saveParameters.minFilter.name() + "," + saveParameters.magFilter.name() + SequenceUtils.EOL);
                writer.write("repeat: none\n");
                ObjectMap.Keys<String> it2 = next.rects.keys().iterator();
                while (it2.hasNext()) {
                    String next2 = it2.next();
                    int i2 = -1;
                    String str = next2;
                    if (saveParameters.useIndexes) {
                        Matcher matcher = PixmapPacker.indexPattern.matcher(str);
                        if (matcher.matches()) {
                            str = matcher.group(1);
                            i2 = Integer.parseInt(matcher.group(2));
                        }
                    }
                    writer.write(str + SequenceUtils.EOL);
                    PixmapPacker.PixmapPackerRectangle pixmapPackerRectangle = next.rects.get(next2);
                    writer.write("  rotate: false\n");
                    writer.write("  xy: " + ((int) pixmapPackerRectangle.x) + "," + ((int) pixmapPackerRectangle.y) + SequenceUtils.EOL);
                    writer.write("  size: " + ((int) pixmapPackerRectangle.width) + "," + ((int) pixmapPackerRectangle.height) + SequenceUtils.EOL);
                    if (pixmapPackerRectangle.splits != null) {
                        writer.write("  split: " + pixmapPackerRectangle.splits[0] + ", " + pixmapPackerRectangle.splits[1] + ", " + pixmapPackerRectangle.splits[2] + ", " + pixmapPackerRectangle.splits[3] + SequenceUtils.EOL);
                        if (pixmapPackerRectangle.pads != null) {
                            writer.write("  pad: " + pixmapPackerRectangle.pads[0] + ", " + pixmapPackerRectangle.pads[1] + ", " + pixmapPackerRectangle.pads[2] + ", " + pixmapPackerRectangle.pads[3] + SequenceUtils.EOL);
                        }
                    }
                    writer.write("  orig: " + pixmapPackerRectangle.originalWidth + ", " + pixmapPackerRectangle.originalHeight + SequenceUtils.EOL);
                    writer.write("  offset: " + pixmapPackerRectangle.offsetX + ", " + ((int) ((pixmapPackerRectangle.originalHeight - pixmapPackerRectangle.height) - pixmapPackerRectangle.offsetY)) + SequenceUtils.EOL);
                    writer.write("  index: " + i2 + SequenceUtils.EOL);
                }
            }
        }
        writer.close();
    }
}

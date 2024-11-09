package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/DistanceFieldFont.class */
public class DistanceFieldFont extends BitmapFont {
    private float distanceFieldSmoothing;

    public DistanceFieldFont(BitmapFont.BitmapFontData bitmapFontData, Array<TextureRegion> array, boolean z) {
        super(bitmapFontData, array, z);
    }

    public DistanceFieldFont(BitmapFont.BitmapFontData bitmapFontData, TextureRegion textureRegion, boolean z) {
        super(bitmapFontData, textureRegion, z);
    }

    public DistanceFieldFont(FileHandle fileHandle, boolean z) {
        super(fileHandle, z);
    }

    public DistanceFieldFont(FileHandle fileHandle, FileHandle fileHandle2, boolean z, boolean z2) {
        super(fileHandle, fileHandle2, z, z2);
    }

    public DistanceFieldFont(FileHandle fileHandle, FileHandle fileHandle2, boolean z) {
        super(fileHandle, fileHandle2, z);
    }

    public DistanceFieldFont(FileHandle fileHandle, TextureRegion textureRegion, boolean z) {
        super(fileHandle, textureRegion, z);
    }

    public DistanceFieldFont(FileHandle fileHandle, TextureRegion textureRegion) {
        super(fileHandle, textureRegion);
    }

    public DistanceFieldFont(FileHandle fileHandle) {
        super(fileHandle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.graphics.g2d.BitmapFont
    public void load(BitmapFont.BitmapFontData bitmapFontData) {
        super.load(bitmapFontData);
        Array.ArrayIterator<TextureRegion> it = getRegions().iterator();
        while (it.hasNext()) {
            Texture texture = it.next().getTexture();
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.BitmapFont
    public BitmapFontCache newFontCache() {
        return new DistanceFieldFontCache(this, this.integer);
    }

    public float getDistanceFieldSmoothing() {
        return this.distanceFieldSmoothing;
    }

    public void setDistanceFieldSmoothing(float f) {
        this.distanceFieldSmoothing = f;
    }

    public static ShaderProgram createDistanceFieldShader() {
        ShaderProgram shaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tv_color = a_color;\n\tv_color.a = v_color.a * (255.0/254.0);\n\tv_texCoords = a_texCoord0;\n\tgl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n\tprecision mediump float;\n\tprecision mediump int;\n#endif\n\nuniform sampler2D u_texture;\nuniform float u_smoothing;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tif (u_smoothing > 0.0) {\n\t\tfloat smoothing = 0.25 / u_smoothing;\n\t\tfloat distance = texture2D(u_texture, v_texCoords).a;\n\t\tfloat alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);\n\t\tgl_FragColor = vec4(v_color.rgb, alpha * v_color.a);\n\t} else {\n\t\tgl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n\t}\n}\n");
        if (!shaderProgram.isCompiled()) {
            throw new IllegalArgumentException("Error compiling distance field shader: " + shaderProgram.getLog());
        }
        return shaderProgram;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/DistanceFieldFont$DistanceFieldFontCache.class */
    private static class DistanceFieldFontCache extends BitmapFontCache {
        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont) {
            super(distanceFieldFont, distanceFieldFont.usesIntegerPositions());
        }

        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont, boolean z) {
            super(distanceFieldFont, z);
        }

        private float getSmoothingFactor() {
            DistanceFieldFont distanceFieldFont = (DistanceFieldFont) super.getFont();
            return distanceFieldFont.getDistanceFieldSmoothing() * distanceFieldFont.getScaleX();
        }

        private void setSmoothingUniform(Batch batch, float f) {
            batch.flush();
            batch.getShader().setUniformf("u_smoothing", f);
        }

        @Override // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch) {
            setSmoothingUniform(batch, getSmoothingFactor());
            super.draw(batch);
            setSmoothingUniform(batch, 0.0f);
        }

        @Override // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch, int i, int i2) {
            setSmoothingUniform(batch, getSmoothingFactor());
            super.draw(batch, i, i2);
            setSmoothingUniform(batch, 0.0f);
        }
    }
}

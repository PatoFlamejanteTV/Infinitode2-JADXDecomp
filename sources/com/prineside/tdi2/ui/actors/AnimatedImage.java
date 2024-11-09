package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AnimatedImage.class */
public class AnimatedImage extends Image {
    private Animation<? extends TextureRegion> j;
    private float k;

    public AnimatedImage(Animation<? extends TextureRegion> animation) {
        super(animation.getKeyFrame(0.0f));
        this.k = 0.0f;
        this.j = animation;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        if (isVisible()) {
            if (f < 0.0f || Float.isNaN(f)) {
                f = 0.0f;
            }
            TextureRegionDrawable textureRegionDrawable = (TextureRegionDrawable) getDrawable();
            Animation<? extends TextureRegion> animation = this.j;
            float f2 = this.k + f;
            this.k = f2;
            textureRegionDrawable.setRegion(animation.getKeyFrame(f2, this.j.getPlayMode() != Animation.PlayMode.NORMAL));
        }
        super.act(f);
    }
}

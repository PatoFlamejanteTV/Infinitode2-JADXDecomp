package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/AnimationController.class */
public class AnimationController extends BaseAnimationController {
    protected final Pool<AnimationDesc> animationPool;
    public AnimationDesc current;
    public AnimationDesc queued;
    public float queuedTransitionTime;
    public AnimationDesc previous;
    public float transitionCurrentTime;
    public float transitionTargetTime;
    public boolean inAction;
    public boolean paused;
    public boolean allowSameAnimation;
    private boolean justChangedAnimation;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/AnimationController$AnimationListener.class */
    public interface AnimationListener {
        void onEnd(AnimationDesc animationDesc);

        void onLoop(AnimationDesc animationDesc);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/AnimationController$AnimationDesc.class */
    public static class AnimationDesc {
        public AnimationListener listener;
        public Animation animation;
        public float speed;
        public float time;
        public float offset;
        public float duration;
        public int loopCount;

        protected AnimationDesc() {
        }

        protected float update(float f) {
            int i;
            if (this.loopCount != 0 && this.animation != null) {
                float f2 = this.speed * f;
                if (MathUtils.isZero(this.duration)) {
                    i = 1;
                } else {
                    this.time += f2;
                    if (this.speed < 0.0f) {
                        float f3 = this.duration - this.time;
                        i = (int) Math.abs(f3 / this.duration);
                        this.time = this.duration - Math.abs(f3 % this.duration);
                    } else {
                        i = (int) Math.abs(this.time / this.duration);
                        this.time = Math.abs(this.time % this.duration);
                    }
                }
                for (int i2 = 0; i2 < i; i2++) {
                    if (this.loopCount > 0) {
                        this.loopCount--;
                    }
                    if (this.loopCount != 0 && this.listener != null) {
                        this.listener.onLoop(this);
                    }
                    if (this.loopCount == 0) {
                        float f4 = (((i - 1) - i2) * this.duration) + (f2 < 0.0f ? this.duration - this.time : this.time);
                        this.time = f2 < 0.0f ? 0.0f : this.duration;
                        if (this.listener != null) {
                            this.listener.onEnd(this);
                        }
                        return f4;
                    }
                }
                return -1.0f;
            }
            return f;
        }
    }

    public AnimationController(ModelInstance modelInstance) {
        super(modelInstance);
        this.animationPool = new Pool<AnimationDesc>() { // from class: com.badlogic.gdx.graphics.g3d.utils.AnimationController.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.badlogic.gdx.utils.Pool
            public AnimationDesc newObject() {
                return new AnimationDesc();
            }
        };
        this.justChangedAnimation = false;
    }

    private AnimationDesc obtain(Animation animation, float f, float f2, int i, float f3, AnimationListener animationListener) {
        if (animation == null) {
            return null;
        }
        AnimationDesc obtain = this.animationPool.obtain();
        obtain.animation = animation;
        obtain.listener = animationListener;
        obtain.loopCount = i;
        obtain.speed = f3;
        obtain.offset = f;
        obtain.duration = f2 < 0.0f ? animation.duration - f : f2;
        obtain.time = f3 < 0.0f ? obtain.duration : 0.0f;
        return obtain;
    }

    private AnimationDesc obtain(String str, float f, float f2, int i, float f3, AnimationListener animationListener) {
        if (str == null) {
            return null;
        }
        Animation animation = this.target.getAnimation(str);
        if (animation == null) {
            throw new GdxRuntimeException("Unknown animation: " + str);
        }
        return obtain(animation, f, f2, i, f3, animationListener);
    }

    private AnimationDesc obtain(AnimationDesc animationDesc) {
        return obtain(animationDesc.animation, animationDesc.offset, animationDesc.duration, animationDesc.loopCount, animationDesc.speed, animationDesc.listener);
    }

    public void update(float f) {
        if (this.paused) {
            return;
        }
        if (this.previous != null) {
            float f2 = this.transitionCurrentTime + f;
            this.transitionCurrentTime = f2;
            if (f2 >= this.transitionTargetTime) {
                removeAnimation(this.previous.animation);
                this.justChangedAnimation = true;
                this.animationPool.free(this.previous);
                this.previous = null;
            }
        }
        if (this.justChangedAnimation) {
            this.target.calculateTransforms();
            this.justChangedAnimation = false;
        }
        if (this.current == null || this.current.loopCount == 0 || this.current.animation == null) {
            return;
        }
        float update = this.current.update(f);
        if (update >= 0.0f && this.queued != null) {
            this.inAction = false;
            animate(this.queued, this.queuedTransitionTime);
            this.queued = null;
            if (update > 0.0f) {
                update(update);
                return;
            }
            return;
        }
        if (this.previous != null) {
            applyAnimations(this.previous.animation, this.previous.offset + this.previous.time, this.current.animation, this.current.offset + this.current.time, this.transitionCurrentTime / this.transitionTargetTime);
        } else {
            applyAnimation(this.current.animation, this.current.offset + this.current.time);
        }
    }

    public AnimationDesc setAnimation(String str) {
        return setAnimation(str, 1, 1.0f, null);
    }

    public AnimationDesc setAnimation(String str, int i) {
        return setAnimation(str, i, 1.0f, null);
    }

    public AnimationDesc setAnimation(String str, AnimationListener animationListener) {
        return setAnimation(str, 1, 1.0f, animationListener);
    }

    public AnimationDesc setAnimation(String str, int i, AnimationListener animationListener) {
        return setAnimation(str, i, 1.0f, animationListener);
    }

    public AnimationDesc setAnimation(String str, int i, float f, AnimationListener animationListener) {
        return setAnimation(str, 0.0f, -1.0f, i, f, animationListener);
    }

    public AnimationDesc setAnimation(String str, float f, float f2, int i, float f3, AnimationListener animationListener) {
        return setAnimation(obtain(str, f, f2, i, f3, animationListener));
    }

    protected AnimationDesc setAnimation(Animation animation, float f, float f2, int i, float f3, AnimationListener animationListener) {
        return setAnimation(obtain(animation, f, f2, i, f3, animationListener));
    }

    protected AnimationDesc setAnimation(AnimationDesc animationDesc) {
        if (this.current == null) {
            this.current = animationDesc;
        } else {
            if (!this.allowSameAnimation && animationDesc != null && this.current.animation == animationDesc.animation) {
                animationDesc.time = this.current.time;
            } else {
                removeAnimation(this.current.animation);
            }
            this.animationPool.free(this.current);
            this.current = animationDesc;
        }
        this.justChangedAnimation = true;
        return animationDesc;
    }

    public AnimationDesc animate(String str, float f) {
        return animate(str, 1, 1.0f, null, f);
    }

    public AnimationDesc animate(String str, AnimationListener animationListener, float f) {
        return animate(str, 1, 1.0f, animationListener, f);
    }

    public AnimationDesc animate(String str, int i, AnimationListener animationListener, float f) {
        return animate(str, i, 1.0f, animationListener, f);
    }

    public AnimationDesc animate(String str, int i, float f, AnimationListener animationListener, float f2) {
        return animate(str, 0.0f, -1.0f, i, f, animationListener, f2);
    }

    public AnimationDesc animate(String str, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return animate(obtain(str, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc animate(Animation animation, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return animate(obtain(animation, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc animate(AnimationDesc animationDesc, float f) {
        if (this.current == null || this.current.loopCount == 0) {
            this.current = animationDesc;
        } else if (this.inAction) {
            queue(animationDesc, f);
        } else if (!this.allowSameAnimation && animationDesc != null && this.current.animation == animationDesc.animation) {
            animationDesc.time = this.current.time;
            this.animationPool.free(this.current);
            this.current = animationDesc;
        } else {
            if (this.previous != null) {
                removeAnimation(this.previous.animation);
                this.animationPool.free(this.previous);
            }
            this.previous = this.current;
            this.current = animationDesc;
            this.transitionCurrentTime = 0.0f;
            this.transitionTargetTime = f;
        }
        return animationDesc;
    }

    public AnimationDesc queue(String str, int i, float f, AnimationListener animationListener, float f2) {
        return queue(str, 0.0f, -1.0f, i, f, animationListener, f2);
    }

    public AnimationDesc queue(String str, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return queue(obtain(str, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc queue(Animation animation, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return queue(obtain(animation, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc queue(AnimationDesc animationDesc, float f) {
        if (this.current == null || this.current.loopCount == 0) {
            animate(animationDesc, f);
        } else {
            if (this.queued != null) {
                this.animationPool.free(this.queued);
            }
            this.queued = animationDesc;
            this.queuedTransitionTime = f;
            if (this.current.loopCount < 0) {
                this.current.loopCount = 1;
            }
        }
        return animationDesc;
    }

    public AnimationDesc action(String str, int i, float f, AnimationListener animationListener, float f2) {
        return action(str, 0.0f, -1.0f, i, f, animationListener, f2);
    }

    public AnimationDesc action(String str, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return action(obtain(str, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc action(Animation animation, float f, float f2, int i, float f3, AnimationListener animationListener, float f4) {
        return action(obtain(animation, f, f2, i, f3, animationListener), f4);
    }

    protected AnimationDesc action(AnimationDesc animationDesc, float f) {
        if (animationDesc.loopCount < 0) {
            throw new GdxRuntimeException("An action cannot be continuous");
        }
        if (this.current == null || this.current.loopCount == 0) {
            animate(animationDesc, f);
        } else {
            AnimationDesc obtain = this.inAction ? null : obtain(this.current);
            this.inAction = false;
            animate(animationDesc, f);
            this.inAction = true;
            if (obtain != null) {
                queue(obtain, f);
            }
        }
        return animationDesc;
    }
}

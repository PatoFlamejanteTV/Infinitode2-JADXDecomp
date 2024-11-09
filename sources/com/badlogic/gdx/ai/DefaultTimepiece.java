package com.badlogic.gdx.ai;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/DefaultTimepiece.class */
public class DefaultTimepiece implements Timepiece {
    private float time;
    private float deltaTime;
    private float maxDeltaTime;

    public DefaultTimepiece() {
        this(Float.POSITIVE_INFINITY);
    }

    public DefaultTimepiece(float f) {
        this.time = 0.0f;
        this.deltaTime = 0.0f;
        this.maxDeltaTime = f;
    }

    @Override // com.badlogic.gdx.ai.Timepiece
    public float getTime() {
        return this.time;
    }

    @Override // com.badlogic.gdx.ai.Timepiece
    public float getDeltaTime() {
        return this.deltaTime;
    }

    @Override // com.badlogic.gdx.ai.Timepiece
    public void update(float f) {
        this.deltaTime = f > this.maxDeltaTime ? this.maxDeltaTime : f;
        this.time += this.deltaTime;
    }
}

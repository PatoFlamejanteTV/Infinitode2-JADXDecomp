package com.prineside.tdi2.scene2d.utils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/Layout.class */
public interface Layout {
    void layout();

    void invalidate();

    void invalidateHierarchy();

    void validate();

    void pack();

    void setFillParent(boolean z);

    void setLayoutEnabled(boolean z);

    float getMinWidth();

    float getMinHeight();

    float getPrefWidth();

    float getPrefHeight();

    float getMaxWidth();

    float getMaxHeight();
}

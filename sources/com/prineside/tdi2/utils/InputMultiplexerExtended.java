package com.prineside.tdi2.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/InputMultiplexerExtended.class */
public class InputMultiplexerExtended implements InputProcessor {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3847a = TLog.forClass(InputMultiplexerExtended.class);

    /* renamed from: b, reason: collision with root package name */
    private final SnapshotArray<InputProcessor> f3848b = new SnapshotArray<>(true, 4);
    private boolean c;

    public InputMultiplexerExtended() {
    }

    public InputMultiplexerExtended(InputProcessor... inputProcessorArr) {
        this.f3848b.addAll(inputProcessorArr);
    }

    public void setLogging(boolean z) {
        this.c = z;
    }

    public boolean isLogging() {
        return this.c;
    }

    public void addProcessorAtIndex(int i, InputProcessor inputProcessor) {
        if (this.c) {
            f3847a.i("addProcessor " + i + SequenceUtils.SPACE + inputProcessor, new Object[0]);
        }
        Preconditions.checkNotNull(inputProcessor, "InputProcessor can not be null");
        this.f3848b.insert(i, inputProcessor);
    }

    public void removeProcessorAtIndex(int i) {
        if (this.c) {
            f3847a.i("removeProcessor " + i, new Object[0]);
        }
        this.f3848b.removeIndex(i);
    }

    public void addProcessor(InputProcessor inputProcessor) {
        if (this.c) {
            f3847a.i("addProcessor " + inputProcessor, new Object[0]);
        }
        Preconditions.checkNotNull(inputProcessor, "InputProcessor can not be null");
        this.f3848b.add(inputProcessor);
    }

    public void removeProcessor(InputProcessor inputProcessor) {
        if (this.c) {
            f3847a.i("removeProcessor " + inputProcessor, new Object[0]);
        }
        this.f3848b.removeValue(inputProcessor, true);
    }

    public int size() {
        return this.f3848b.size;
    }

    public void clear() {
        this.f3848b.clear();
    }

    public void setProcessors(InputProcessor... inputProcessorArr) {
        if (this.c) {
            f3847a.i("setProcessors " + Arrays.toString(inputProcessorArr), new Object[0]);
        }
        this.f3848b.clear();
        this.f3848b.addAll(inputProcessorArr);
    }

    public void setProcessorsArray(Array<InputProcessor> array) {
        if (this.c) {
            f3847a.i("setProcessors " + array.toString(), new Object[0]);
        }
        this.f3848b.clear();
        this.f3848b.addAll(array);
    }

    public SnapshotArray<InputProcessor> getProcessors() {
        return this.f3848b;
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int i) {
        if (this.c) {
            f3847a.i("keyDown " + i + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i2 = this.f3848b.size;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.c) {
                    f3847a.i("- (keyDown) " + i3 + ": " + begin[i3], new Object[0]);
                }
                if (begin[i3].keyDown(i)) {
                    if (this.c) {
                        f3847a.i("- (keyDown) " + begin[i3] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int i) {
        if (this.c) {
            f3847a.i("keyUp " + i + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i2 = this.f3848b.size;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.c) {
                    f3847a.i("- (keyUp) " + i3 + ": " + begin[i3], new Object[0]);
                }
                if (begin[i3].keyUp(i)) {
                    if (this.c) {
                        f3847a.i("- (keyUp) " + begin[i3] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        if (this.c) {
            f3847a.i("keyTyped " + c + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i = this.f3848b.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.c) {
                    f3847a.i("- (keyTyped) " + i2 + ": " + begin[i2], new Object[0]);
                }
                if (begin[i2].keyTyped(c)) {
                    if (this.c) {
                        f3847a.i("- (keyTyped) " + begin[i2] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int i, int i2, int i3, int i4) {
        if (this.c) {
            f3847a.i("touchDown " + i + ", " + i2 + ", " + i3 + ", " + i4 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i5 = this.f3848b.size;
            for (int i6 = 0; i6 < i5; i6++) {
                if (this.c) {
                    f3847a.i("- (touchDown) " + i6 + ": " + begin[i6], new Object[0]);
                }
                if (begin[i6].touchDown(i, i2, i3, i4)) {
                    if (this.c) {
                        f3847a.i("- (touchDown) " + begin[i6] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int i, int i2, int i3, int i4) {
        if (this.c) {
            f3847a.i("touchUp " + i + ", " + i2 + ", " + i3 + ", " + i4 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i5 = this.f3848b.size;
            for (int i6 = 0; i6 < i5; i6++) {
                if (this.c) {
                    f3847a.i("- (touchUp) " + i6 + ": " + begin[i6], new Object[0]);
                }
                if (begin[i6].touchUp(i, i2, i3, i4)) {
                    if (this.c) {
                        f3847a.i("- (touchUp) " + begin[i6] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchCancelled(int i, int i2, int i3, int i4) {
        if (this.c) {
            f3847a.i("touchCancelled " + i + ", " + i2 + ", " + i3 + ", " + i4 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i5 = this.f3848b.size;
            for (int i6 = 0; i6 < i5; i6++) {
                if (this.c) {
                    f3847a.i("- (touchCancelled) " + i6 + ": " + begin[i6], new Object[0]);
                }
                if (begin[i6].touchCancelled(i, i2, i3, i4)) {
                    if (this.c) {
                        f3847a.i("- (touchCancelled) " + begin[i6] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        if (this.c) {
            f3847a.i("touchDragged " + i + ", " + i2 + ", " + i3 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i4 = this.f3848b.size;
            for (int i5 = 0; i5 < i4; i5++) {
                if (this.c) {
                    f3847a.i("- (touchDragged) " + i5 + ": " + begin[i5], new Object[0]);
                }
                if (begin[i5].touchDragged(i, i2, i3)) {
                    if (this.c) {
                        f3847a.i("- (touchDragged) " + begin[i5] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int i, int i2) {
        if (this.c) {
            f3847a.i("mouseMoved " + i + ", " + i2 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i3 = this.f3848b.size;
            for (int i4 = 0; i4 < i3; i4++) {
                if (this.c) {
                    f3847a.i("- (mouseMoved) " + i4 + ": " + begin[i4], new Object[0]);
                }
                if (begin[i4].mouseMoved(i, i2)) {
                    if (this.c) {
                        f3847a.i("- (mouseMoved) " + begin[i4] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }

    @Override // com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f2) {
        if (this.c) {
            f3847a.i("scrolled " + f + ", " + f2 + " (" + this.f3848b.size + " processors)", new Object[0]);
        }
        InputProcessor[] begin = this.f3848b.begin();
        try {
            int i = this.f3848b.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (this.c) {
                    f3847a.i("- (scrolled) " + i2 + ": " + begin[i2], new Object[0]);
                }
                if (begin[i2].scrolled(f, f2)) {
                    if (this.c) {
                        f3847a.i("- (scrolled) " + begin[i2] + " cancels event", new Object[0]);
                    }
                    this.f3848b.end();
                    return true;
                }
            }
            return false;
        } finally {
            this.f3848b.end();
        }
    }
}

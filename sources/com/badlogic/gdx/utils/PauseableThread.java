package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PauseableThread.class */
public class PauseableThread extends Thread {
    final Runnable runnable;
    boolean paused = false;
    boolean exit = false;

    public PauseableThread(Runnable runnable) {
        this.runnable = runnable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            PauseableThread pauseableThread = this;
            InterruptedException interruptedException = pauseableThread;
            synchronized (pauseableThread) {
                while (true) {
                    try {
                        interruptedException = this.paused;
                        if (interruptedException == 0) {
                            break;
                        }
                        PauseableThread pauseableThread2 = this;
                        pauseableThread2.wait();
                        interruptedException = pauseableThread2;
                    } catch (InterruptedException e) {
                        interruptedException.printStackTrace();
                    }
                }
            }
            if (this.exit) {
                return;
            } else {
                this.runnable.run();
            }
        }
    }

    public void onPause() {
        this.paused = true;
    }

    public void onResume() {
        synchronized (this) {
            this.paused = false;
            notifyAll();
        }
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void stopThread() {
        this.exit = true;
        if (this.paused) {
            onResume();
        }
    }
}

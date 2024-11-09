package com.prineside.tdi2.ibxm;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Envelope.class */
public class Envelope {
    public boolean enabled;
    public boolean sustain;
    public boolean looped;
    public int sustainTick;
    public int loopStartTick;
    public int loopEndTick;
    public int numPoints;
    public int[] pointsTick;
    public int[] pointsAmpl;

    public Envelope() {
        this.enabled = false;
        this.sustain = false;
        this.looped = false;
        this.sustainTick = 0;
        this.loopStartTick = 0;
        this.loopEndTick = 0;
        this.numPoints = 1;
        this.pointsTick = new int[1];
        this.pointsAmpl = new int[1];
    }

    public Envelope(Envelope envelope) {
        this.enabled = false;
        this.sustain = false;
        this.looped = false;
        this.sustainTick = 0;
        this.loopStartTick = 0;
        this.loopEndTick = 0;
        this.numPoints = 1;
        this.pointsTick = new int[1];
        this.pointsAmpl = new int[1];
        this.enabled = envelope.enabled;
        this.sustain = envelope.sustain;
        this.looped = envelope.looped;
        this.sustainTick = envelope.sustainTick;
        this.loopStartTick = envelope.loopStartTick;
        this.loopEndTick = envelope.loopEndTick;
        this.numPoints = envelope.numPoints;
        this.pointsTick = new int[envelope.pointsTick.length];
        System.arraycopy(envelope.pointsTick, 0, this.pointsTick, 0, this.pointsTick.length);
        this.pointsAmpl = new int[envelope.pointsAmpl.length];
        System.arraycopy(envelope.pointsAmpl, 0, this.pointsAmpl, 0, this.pointsAmpl.length);
    }

    public int nextTick(int i, boolean z) {
        int i2 = i + 1;
        if (this.looped && i2 >= this.loopEndTick) {
            i2 = this.loopStartTick;
        }
        if (this.sustain && z && i2 >= this.sustainTick) {
            i2 = this.sustainTick;
        }
        return i2;
    }

    public int calculateAmpl(int i) {
        int i2 = this.pointsAmpl[this.numPoints - 1];
        if (i < this.pointsTick[this.numPoints - 1]) {
            int i3 = 0;
            for (int i4 = 1; i4 < this.numPoints; i4++) {
                if (this.pointsTick[i4] <= i) {
                    i3 = i4;
                }
            }
            i2 = this.pointsAmpl[i3] + (((((this.pointsAmpl[i3 + 1] - this.pointsAmpl[i3]) << 24) / (this.pointsTick[i3 + 1] - this.pointsTick[i3])) * (i - this.pointsTick[i3])) >> 24);
        }
        return i2;
    }

    public void toStringBuffer(StringBuffer stringBuffer) {
        stringBuffer.append("Enabled: " + this.enabled + '\n');
        stringBuffer.append("Sustain: " + this.sustain + '\n');
        stringBuffer.append("Looped: " + this.looped + '\n');
        stringBuffer.append("Sustain Tick: " + this.sustainTick + '\n');
        stringBuffer.append("Loop Start Tick: " + this.loopStartTick + '\n');
        stringBuffer.append("Loop End Tick: " + this.loopEndTick + '\n');
        stringBuffer.append("Num Points: " + this.numPoints + '\n');
        stringBuffer.append("Points: ");
        for (int i = 0; i < this.numPoints; i++) {
            stringBuffer.append("(" + this.pointsTick[i] + ", " + this.pointsAmpl[i] + "), ");
        }
        stringBuffer.append('\n');
    }
}

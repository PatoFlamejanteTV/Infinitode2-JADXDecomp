package org.lwjgl.system;

/* loaded from: infinitode-2.jar:org/lwjgl/system/JNI.class */
public final class JNI {
    public static native byte invokePB(long j, long j2);

    public static native short invokeC(long j);

    public static native short invokeC(int i, long j);

    public static native short invokePC(long j, long j2);

    public static native short invokeCC(int i, short s, long j);

    public static native short invokeCC(short s, boolean z, long j);

    public static native short invokeJC(int i, int i2, long j, long j2);

    public static native short invokeCUC(short s, byte b2, long j);

    public static native short invokePCC(long j, short s, long j2);

    public static native short invokeCCC(short s, short s2, boolean z, long j);

    public static native short invokePCC(int i, long j, short s, long j2);

    public static native short invokePCC(long j, int i, short s, long j2);

    public static native short invokeUPC(byte b2, long j, boolean z, long j2);

    public static native short invokeCJC(int i, boolean z, short s, int i2, long j, long j2);

    public static native short invokeCPCC(short s, long j, short s2, long j2);

    public static native short invokePPCC(long j, long j2, short s, long j3);

    public static native short invokeCCJC(short s, short s2, int i, long j, long j2);

    public static native short invokePCCC(long j, short s, short s2, int i, int i2, long j2);

    public static native short invokeCCCCC(short s, short s2, short s3, short s4, long j);

    public static native short invokePJUPC(long j, long j2, byte b2, long j3, long j4);

    public static native short invokeCCJPC(short s, boolean z, short s2, int i, long j, long j2, long j3);

    public static native short invokePCCCCC(long j, short s, short s2, short s3, short s4, long j2);

    public static native short invokeCCCJPC(short s, short s2, short s3, boolean z, int i, long j, long j2, long j3);

    public static native short invokeCCCJPC(short s, short s2, boolean z, short s3, int i, long j, long j2, long j3);

    public static native double invokeD(long j);

    public static native double invokeD(int i, long j);

    public static native double invokePD(long j, long j2);

    public static native double invokePD(long j, int i, long j2);

    public static native double invokePD(long j, int i, int i2, long j2);

    public static native double invokePPD(long j, long j2, long j3);

    public static native float invokeF(int i, long j);

    public static native float invokePF(long j, long j2);

    public static native float invokePF(float f, long j, long j2);

    public static native float invokePF(long j, int i, long j2);

    public static native float invokePF(float f, float f2, long j, long j2);

    public static native float invokePF(long j, float f, float f2, long j2);

    public static native float invokePF(long j, int i, int i2, long j2);

    public static native float invokePPF(long j, long j2, long j3);

    public static native float invokePPF(long j, int i, long j2, long j3);

    public static native float invokePPF(long j, float f, long j2, int i, long j3);

    public static native int invokeI(long j);

    public static native int invokeI(int i, long j);

    public static native int invokeI(boolean z, long j);

    public static native int invokeI(int i, int i2, long j);

    public static native int invokeI(int i, boolean z, long j);

    public static native int invokeI(int i, int i2, int i3, long j);

    public static native int invokePI(long j, long j2);

    public static native int invokeCI(int i, short s, long j);

    public static native int invokePI(int i, long j, long j2);

    public static native int invokePI(long j, int i, long j2);

    public static native int invokePI(long j, int i, int i2, long j2);

    public static native int invokePI(long j, int i, boolean z, long j2);

    public static native int invokePI(long j, int i, int i2, int i3, long j2);

    public static native int invokeCPI(short s, long j, long j2);

    public static native int invokePCI(long j, short s, long j2);

    public static native int invokePJI(long j, long j2, long j3);

    public static native int invokePNI(long j, long j2, long j3);

    public static native int invokePPI(long j, long j2, long j3);

    public static native int invokePJI(long j, long j2, int i, long j3);

    public static native int invokePNI(long j, int i, long j2, long j3);

    public static native int invokePNI(long j, long j2, int i, long j3);

    public static native int invokePPI(int i, long j, long j2, long j3);

    public static native int invokePPI(long j, int i, long j2, long j3);

    public static native int invokePPI(long j, long j2, float f, long j3);

    public static native int invokePPI(long j, long j2, int i, long j3);

    public static native int invokePPI(long j, long j2, boolean z, long j3);

    public static native int invokePPI(long j, boolean z, long j2, long j3);

    public static native int invokePPI(long j, int i, int i2, long j2, long j3);

    public static native int invokePPI(long j, int i, long j2, int i2, long j3);

    public static native int invokePPI(long j, int i, long j2, boolean z, long j3);

    public static native int invokePPI(long j, int i, boolean z, long j2, long j3);

    public static native int invokePPI(long j, long j2, int i, int i2, long j3);

    public static native int invokePPI(long j, long j2, boolean z, boolean z2, long j3);

    public static native int invokePPI(long j, int i, int i2, int i3, long j2, long j3);

    public static native int invokePPI(long j, int i, int i2, long j2, int i3, long j3);

    public static native int invokePPI(long j, int i, long j2, int i2, int i3, long j3);

    public static native int invokePPI(int i, long j, int i2, long j2, int i3, boolean z, long j3);

    public static native int invokePPI(long j, int i, int i2, int i3, int i4, long j2, long j3);

    public static native int invokePPI(long j, int i, int i2, int i3, int i4, long j2, int i5, long j3);

    public static native int invokeCPUI(short s, long j, byte b2, long j2);

    public static native int invokePCPI(long j, short s, long j2, long j3);

    public static native int invokePNNI(long j, long j2, long j3, long j4);

    public static native int invokePNPI(long j, long j2, long j3, long j4);

    public static native int invokePPCI(long j, long j2, short s, long j3);

    public static native int invokePPJI(long j, long j2, long j3, long j4);

    public static native int invokePPNI(long j, long j2, long j3, long j4);

    public static native int invokePPPI(long j, long j2, long j3, long j4);

    public static native int invokePNPI(long j, long j2, int i, long j3, long j4);

    public static native int invokePNPI(long j, long j2, long j3, int i, long j4);

    public static native int invokePPNI(long j, int i, long j2, long j3, long j4);

    public static native int invokePPPI(long j, int i, long j2, long j3, long j4);

    public static native int invokePPPI(long j, long j2, int i, long j3, long j4);

    public static native int invokePPPI(long j, long j2, long j3, int i, long j4);

    public static native int invokePNNI(long j, long j2, long j3, int i, int i2, long j4);

    public static native int invokePPPI(long j, int i, int i2, long j2, long j3, long j4);

    public static native int invokePPPI(long j, int i, long j2, int i2, long j3, long j4);

    public static native int invokePPPI(long j, int i, long j2, long j3, int i2, long j4);

    public static native int invokePPPI(long j, long j2, int i, int i2, long j3, long j4);

    public static native int invokePPPI(long j, long j2, int i, long j3, int i2, long j4);

    public static native int invokePPPI(long j, long j2, long j3, int i, int i2, long j4);

    public static native int invokePPPI(long j, int i, int i2, int i3, long j2, long j3, long j4);

    public static native int invokePPPI(long j, int i, long j2, int i2, long j3, int i3, long j4);

    public static native int invokePPPI(long j, long j2, int i, long j3, int i2, int i3, long j4);

    public static native int invokePPPI(long j, long j2, long j3, int i, boolean z, float f, long j4);

    public static native int invokePPPI(long j, int i, int i2, int i3, int i4, long j2, long j3, long j4);

    public static native int invokePPPI(long j, int i, int i2, int i3, long j2, long j3, int i4, long j4);

    public static native int invokePPPI(long j, int i, int i2, long j2, int i3, long j3, int i4, int i5, long j4);

    public static native int invokePNPPI(long j, long j2, long j3, long j4, long j5);

    public static native int invokePPNNI(long j, long j2, long j3, long j4, long j5);

    public static native int invokePPNPI(long j, long j2, long j3, long j4, long j5);

    public static native int invokePPPNI(long j, long j2, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, long j4, long j5);

    public static native int invokePNNPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int invokePPPNI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, int i, long j2, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, int i, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, long j4, int i, long j5);

    public static native int invokePPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, int i, int i2, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, int i, long j3, long j4, int i2, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, int i, int i2, long j4, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, long j4, int i, int i2, long j5);

    public static native int invokePPPPI(long j, int i, int i2, int i3, long j2, long j3, long j4, long j5);

    public static native int invokePPPPI(long j, int i, int i2, long j2, long j3, long j4, int i3, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, int i, int i2, int i3, long j4, int i4, long j5);

    public static native int invokePPPPI(long j, long j2, long j3, long j4, int i, int i2, int i3, int i4, long j5);

    public static native int invokePPPPI(long j, int i, int i2, long j2, int i3, long j3, long j4, int i4, int i5, long j5);

    public static native int invokePNNPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPNNPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPNNI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPNI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePJPPNI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPNPPI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPNPPI(long j, long j2, long j3, int i, long j4, long j5, long j6);

    public static native int invokePPPNJI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPNNI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPNPI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPNI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPNI(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, long j3, int i, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, long j3, long j4, long j5, int i, long j6);

    public static native int invokePNPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, int i, int i2, long j3, long j4, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, int i, long j3, int i2, long j4, int i3, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, long j3, int i, long j4, int i2, int i3, long j5, long j6);

    public static native int invokePPPPPI(long j, long j2, long j3, int i, int i2, int i3, float f, long j4, long j5, long j6);

    public static native int invokePPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int invokePJJJJPI(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7);

    public static native int invokePPNPPPI(long j, int i, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int invokePPPPPPI(long j, int i, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int invokePPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, int i, long j7);

    public static native int invokePNNPPPI(long j, long j2, long j3, int i, int i2, long j4, long j5, long j6, long j7);

    public static native int invokePPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int invokePPPPPPPI(long j, long j2, long j3, int i, long j4, float f, float f2, long j5, long j6, long j7, long j8);

    public static native int invokePPPPPPPI(long j, long j2, long j3, int i, int i2, long j4, long j5, int i3, long j6, int i4, long j7, int i5, long j8);

    public static native int invokePPPPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long invokeJ(long j);

    public static native long invokePJ(long j, long j2);

    public static native long invokePJ(long j, int i, long j2);

    public static native long invokePJ(long j, int i, int i2, long j2);

    public static native long invokePJJ(long j, long j2, long j3);

    public static native long invokePPJ(long j, long j2, long j3);

    public static native long invokePPJ(long j, long j2, int i, long j3);

    public static native long invokeNN(long j, long j2);

    public static native long invokePN(long j, long j2);

    public static native long invokePN(long j, int i, long j2);

    public static native long invokeNNN(long j, long j2, long j3);

    public static native long invokePPN(long j, long j2, long j3);

    public static native long invokeNNNN(long j, long j2, long j3, long j4);

    public static native long invokePNPN(long j, long j2, long j3, long j4);

    public static native long invokePNPN(long j, long j2, long j3, int i, long j4);

    public static native long invokePPNN(long j, int i, int i2, long j2, long j3, long j4);

    public static native long invokePNPNN(long j, long j2, long j3, long j4, long j5);

    public static native long invokePNPNPN(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j3, long j4, long j5, long j6);

    public static native long invokeP(long j);

    public static native long invokeP(int i, long j);

    public static native long invokeP(boolean z, long j);

    public static native long invokeP(int i, int i2, long j);

    public static native long invokeCP(short s, long j);

    public static native long invokePP(long j, long j2);

    public static native long invokePP(int i, long j, long j2);

    public static native long invokePP(long j, double d, long j2);

    public static native long invokePP(long j, int i, long j2);

    public static native long invokePP(int i, int i2, long j, long j2);

    public static native long invokePP(long j, int i, int i2, long j2);

    public static native long invokePP(int i, int i2, int i3, long j, long j2);

    public static native long invokePP(long j, int i, int i2, int i3, long j2);

    public static native long invokePP(long j, float f, int i, float f2, int i2, long j2);

    public static native long invokePP(long j, int i, int i2, int i3, int i4, int i5, long j2);

    public static native long invokeCPP(short s, long j, long j2);

    public static native long invokePJP(long j, long j2, long j3);

    public static native long invokePNP(long j, long j2, long j3);

    public static native long invokePPP(long j, long j2, long j3);

    public static native long invokePUP(long j, byte b2, long j2);

    public static native long invokeCPP(int i, short s, long j, long j2);

    public static native long invokePCP(long j, short s, boolean z, long j2);

    public static native long invokePJP(long j, int i, long j2, long j3);

    public static native long invokePJP(long j, long j2, int i, long j3);

    public static native long invokePPP(int i, long j, long j2, long j3);

    public static native long invokePPP(long j, int i, long j2, long j3);

    public static native long invokePPP(long j, long j2, int i, long j3);

    public static native long invokePPP(long j, int i, int i2, long j2, long j3);

    public static native long invokePPP(long j, int i, long j2, int i2, long j3);

    public static native long invokePPP(long j, long j2, int i, int i2, long j3);

    public static native long invokePPP(long j, long j2, boolean z, boolean z2, long j3);

    public static native long invokePPP(long j, boolean z, boolean z2, long j2, long j3);

    public static native long invokePPP(long j, int i, int i2, int i3, long j2, long j3);

    public static native long invokePPP(int i, int i2, int i3, int i4, long j, long j2, long j3);

    public static native long invokePUP(long j, int i, byte b2, int i2, boolean z, boolean z2, long j2);

    public static native long invokePPP(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3);

    public static native long invokePJJP(long j, long j2, long j3, long j4);

    public static native long invokePPJP(long j, long j2, long j3, long j4);

    public static native long invokePPPP(long j, long j2, long j3, long j4);

    public static native long invokePPUP(long j, long j2, byte b2, long j3);

    public static native long invokePPPP(int i, long j, long j2, long j3, long j4);

    public static native long invokePPPP(long j, int i, long j2, long j3, long j4);

    public static native long invokePPPP(long j, long j2, int i, long j3, long j4);

    public static native long invokePPPP(long j, long j2, long j3, int i, long j4);

    public static native long invokePPUP(long j, long j2, int i, byte b2, long j3);

    public static native long invokePPPP(int i, int i2, long j, long j2, long j3, long j4);

    public static native long invokePPPP(long j, int i, int i2, long j2, long j3, long j4);

    public static native long invokePPPP(long j, long j2, int i, int i2, long j3, long j4);

    public static native long invokePPPP(long j, long j2, int i, long j3, int i2, long j4);

    public static native long invokePPPP(long j, long j2, long j3, int i, int i2, long j4);

    public static native long invokeJPPP(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native long invokePPPP(long j, int i, long j2, long j3, int i2, int i3, long j4);

    public static native long invokePPPP(long j, long j2, int i, int i2, int i3, long j3, long j4);

    public static native long invokePPPP(long j, long j2, int i, int i2, long j3, int i3, long j4);

    public static native long invokePPPP(int i, int i2, int i3, long j, long j2, int i4, long j3, long j4);

    public static native long invokePPPP(long j, long j2, int i, int i2, long j3, int i3, int i4, long j4);

    public static native long invokePBPPP(long j, byte b2, long j2, long j3, long j4);

    public static native long invokePNNPP(long j, long j2, long j3, long j4, long j5);

    public static native long invokePPJPP(long j, long j2, long j3, long j4, long j5);

    public static native long invokePPNNP(long j, long j2, long j3, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, long j4, long j5);

    public static native long invokePPPJP(int i, long j, long j2, long j3, long j4, long j5);

    public static native long invokePPPJP(long j, long j2, long j3, long j4, int i, long j5);

    public static native long invokePPPPP(long j, int i, long j2, long j3, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, int i, long j3, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, int i, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, long j4, int i, long j5);

    public static native long invokePJPPP(long j, int i, int i2, long j2, long j3, long j4, long j5);

    public static native long invokePJPPP(long j, long j2, int i, long j3, long j4, int i2, long j5);

    public static native long invokePPPJP(long j, long j2, long j3, long j4, int i, int i2, long j5);

    public static native long invokePPPPP(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, int i, long j4, int i2, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, int i, int i2, int i3, long j4, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, int i, long j4, int i2, int i3, long j5);

    public static native long invokePPPPP(long j, long j2, long j3, long j4, int i, int i2, int i3, long j5);

    public static native long invokePPPPP(int i, int i2, int i3, long j, long j2, long j3, int i4, long j4, long j5);

    public static native long invokePJPJPP(long j, long j2, long j3, long j4, long j5, long j6);

    public static native long invokePNNNPP(long j, long j2, long j3, long j4, long j5, long j6);

    public static native long invokePPBPPP(long j, long j2, byte b2, long j3, long j4, long j5);

    public static native long invokePPPPPP(long j, long j2, long j3, long j4, long j5, long j6);

    public static native long invokeCCCUJP(short s, short s2, short s3, byte b2, int i, long j, long j2);

    public static native long invokePPPPNP(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, int i, long j4, long j5, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6);

    public static native long invokePPJPPP(long j, long j2, long j3, int i, int i2, long j4, long j5, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, int i, long j4, int i2, long j5, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, long j4, int i, long j5, int i2, long j6);

    public static native long invokePPPPPP(long j, long j2, long j3, long j4, long j5, int i, int i2, int i3, int i4, long j6);

    public static native long invokePPJJPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokePSSCCPP(long j, short s, short s2, short s3, short s4, long j2, long j3);

    public static native long invokePPPPPPP(long j, long j2, long j3, int i, long j4, long j5, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, int i, long j7);

    public static native long invokePPPPPPP(long j, long j2, int i, int i2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, int i, long j4, int i2, long j5, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, int i, long j5, int i2, long j6, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, int i, long j5, int i2, long j6, int i3, int i4, long j7);

    public static native long invokePPPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, int i3, int i4, long j7);

    public static native long invokePPPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, long j8);

    public static native long invokePPPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, int i2, long j8);

    public static native long invokePPPPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, long j7, int i3, long j8);

    public static native long invokePPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long invokePPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8, long j9);

    public static native long invokePPPPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, int i2, long j8, long j9);

    public static native long invokePPPPJJPPP(long j, long j2, long j3, long j4, int i, long j5, int i2, long j6, int i3, long j7, long j8, long j9);

    public static native long invokePPPPPJJPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, long j7, int i3, long j8, long j9);

    public static native long invokePPPPPJPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, long j7, int i3, long j8, long j9);

    public static native long invokePPPPPJPPP(long j, int i, long j2, long j3, long j4, long j5, int i2, int i3, long j6, int i4, long j7, long j8, long j9);

    public static native long invokePPPPPJPPP(long j, int i, long j2, long j3, long j4, long j5, int i2, int i3, long j6, int i4, int i5, long j7, long j8, long j9);

    public static native long invokePPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8, int i2, int i3, int i4, int i5, int i6, long j9);

    public static native long invokePPPPPJJJPP(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7, long j8, int i2, long j9, long j10);

    public static native long invokePPPPPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, long j8, int i2, long j9, long j10);

    public static native long invokePPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8, int i2, long j9, int i3, long j10);

    public static native long invokePPPPPJPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, int i3, long j7, int i4, int i5, long j8, long j9, long j10);

    public static native long invokePPPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    public static native long invokePPPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8, int i2, long j9, long j10, int i3, long j11);

    public static native long invokePPPPPJPPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, int i3, long j7, long j8, int i4, int i5, long j9, long j10, long j11, long j12);

    public static native long invokePPPPPPPPPPPPP(long j, int i, long j2, long j3, long j4, int i2, long j5, long j6, int i3, long j7, long j8, int i4, int i5, int i6, int i7, long j9, long j10, long j11, long j12, long j13);

    public static native long invokePPPPPJJPPPPPPP(long j, long j2, long j3, long j4, long j5, int i, long j6, int i2, long j7, int i3, long j8, long j9, int i4, long j10, long j11, long j12, long j13, long j14);

    public static native byte invokeU(int i, long j);

    public static native byte invokeUPU(byte b2, long j, long j2);

    public static native void invokeV(long j);

    public static native void invokeV(double d, long j);

    public static native void invokeV(float f, long j);

    public static native void invokeV(int i, long j);

    public static native void invokeV(int i, float f, long j);

    public static native void invokeV(int i, int i2, long j);

    public static native void invokeV(int i, int i2, double d, long j);

    public static native void invokeV(int i, int i2, float f, long j);

    public static native void invokeV(int i, int i2, int i3, long j);

    public static native void invokeV(int i, float f, float f2, float f3, long j);

    public static native void invokeV(int i, int i2, int i3, int i4, long j);

    public static native void invokeV(int i, int i2, double d, double d2, double d3, long j);

    public static native void invokeV(int i, int i2, float f, float f2, float f3, long j);

    public static native void invokeV(int i, int i2, int i3, int i4, int i5, long j);

    public static native void invokeCV(short s, long j);

    public static native void invokePV(long j, long j2);

    public static native void invokeUV(byte b2, long j);

    public static native void invokeCV(int i, short s, long j);

    public static native void invokeCV(short s, int i, long j);

    public static native void invokeCV(short s, boolean z, long j);

    public static native void invokeJV(int i, long j, long j2);

    public static native void invokeJV(long j, int i, long j2);

    public static native void invokePV(int i, long j, long j2);

    public static native void invokePV(long j, float f, long j2);

    public static native void invokePV(long j, int i, long j2);

    public static native void invokePV(long j, boolean z, long j2);

    public static native void invokeUV(byte b2, int i, long j);

    public static native void invokeUV(byte b2, boolean z, long j);

    public static native void invokeCV(short s, int i, int i2, long j);

    public static native void invokeJV(int i, int i2, long j, long j2);

    public static native void invokePV(int i, int i2, long j, long j2);

    public static native void invokePV(int i, long j, boolean z, long j2);

    public static native void invokePV(long j, double d, double d2, long j2);

    public static native void invokePV(long j, float f, float f2, long j2);

    public static native void invokePV(long j, int i, double d, long j2);

    public static native void invokePV(long j, int i, float f, long j2);

    public static native void invokePV(long j, int i, int i2, long j2);

    public static native void invokePV(long j, int i, boolean z, long j2);

    public static native void invokePV(long j, float f, float f2, float f3, long j2);

    public static native void invokePV(long j, float f, float f2, int i, long j2);

    public static native void invokePV(long j, int i, int i2, double d, long j2);

    public static native void invokePV(long j, int i, int i2, float f, long j2);

    public static native void invokePV(long j, int i, int i2, int i3, long j2);

    public static native void invokePV(int i, int i2, long j, int i3, int i4, long j2);

    public static native void invokePV(long j, int i, int i2, int i3, int i4, long j2);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, long j, long j2);

    public static native void invokePV(long j, int i, int i2, int i3, int i4, boolean z, long j2);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int i6, long j, long j2);

    public static native void invokeCCV(short s, short s2, long j);

    public static native void invokeCPV(short s, long j, long j2);

    public static native void invokePCV(long j, short s, long j2);

    public static native void invokePJV(long j, long j2, long j3);

    public static native void invokePNV(long j, long j2, long j3);

    public static native void invokePPV(long j, long j2, long j3);

    public static native void invokePUV(long j, byte b2, long j2);

    public static native void invokeUPV(byte b2, long j, long j2);

    public static native void invokeCPV(short s, int i, long j, long j2);

    public static native void invokeCPV(short s, long j, int i, long j2);

    public static native void invokePCV(long j, int i, short s, long j2);

    public static native void invokePCV(long j, short s, boolean z, long j2);

    public static native void invokePJV(int i, long j, long j2, long j3);

    public static native void invokePJV(long j, int i, long j2, long j3);

    public static native void invokePJV(long j, long j2, int i, long j3);

    public static native void invokePPV(int i, long j, long j2, long j3);

    public static native void invokePPV(long j, int i, long j2, long j3);

    public static native void invokePPV(long j, long j2, float f, long j3);

    public static native void invokePPV(long j, long j2, int i, long j3);

    public static native void invokePPV(long j, long j2, boolean z, long j3);

    public static native void invokeUCV(byte b2, short s, int i, long j);

    public static native void invokePBV(long j, int i, int i2, byte b2, long j2);

    public static native void invokePCV(long j, int i, int i2, short s, long j2);

    public static native void invokePCV(long j, short s, int i, int i2, long j2);

    public static native void invokePJV(long j, int i, int i2, long j2, long j3);

    public static native void invokePPV(int i, int i2, long j, long j2, long j3);

    public static native void invokePPV(long j, int i, int i2, long j2, long j3);

    public static native void invokePPV(long j, int i, long j2, int i2, long j3);

    public static native void invokePPV(long j, long j2, int i, int i2, long j3);

    public static native void invokePSV(long j, int i, int i2, short s, long j2);

    public static native void invokePUV(long j, int i, int i2, byte b2, long j2);

    public static native void invokeUCV(byte b2, short s, int i, int i2, long j);

    public static native void invokeUPV(byte b2, long j, int i, int i2, long j2);

    public static native void invokePCV(long j, short s, int i, int i2, int i3, long j2);

    public static native void invokePPV(int i, int i2, int i3, long j, long j2, long j3);

    public static native void invokePPV(long j, float f, long j2, int i, int i2, long j3);

    public static native void invokePPV(long j, int i, int i2, int i3, long j2, long j3);

    public static native void invokePPV(long j, long j2, int i, int i2, int i3, long j3);

    public static native void invokePPV(int i, int i2, int i3, int i4, long j, long j2, long j3);

    public static native void invokePPV(long j, int i, int i2, int i3, int i4, long j2, long j3);

    public static native void invokePPV(long j, long j2, float f, float f2, float f3, float f4, long j3);

    public static native void invokePPV(long j, long j2, int i, int i2, int i3, int i4, int i5, long j3);

    public static native void invokePPV(long j, long j2, float f, float f2, float f3, float f4, float f5, float f6, long j3);

    public static native void invokeCCPV(short s, short s2, long j, long j2);

    public static native void invokeCPCV(short s, long j, short s2, long j2);

    public static native void invokeCPPV(short s, long j, long j2, long j3);

    public static native void invokePNNV(long j, long j2, long j3, long j4);

    public static native void invokePNPV(long j, long j2, long j3, long j4);

    public static native void invokePPNV(long j, long j2, long j3, long j4);

    public static native void invokePPPV(long j, long j2, long j3, long j4);

    public static native void invokeCCCV(short s, short s2, short s3, int i, long j);

    public static native void invokeCCUV(short s, short s2, int i, byte b2, long j);

    public static native void invokePJPV(long j, long j2, long j3, int i, long j4);

    public static native void invokePPPV(int i, long j, long j2, long j3, long j4);

    public static native void invokePPPV(long j, int i, long j2, long j3, long j4);

    public static native void invokePPPV(long j, long j2, int i, long j3, long j4);

    public static native void invokePPPV(long j, long j2, long j3, float f, long j4);

    public static native void invokePPPV(long j, long j2, long j3, int i, long j4);

    public static native void invokePUCV(long j, byte b2, short s, int i, long j2);

    public static native void invokeUCCV(byte b2, short s, short s2, int i, long j);

    public static native void invokeCCUV(short s, short s2, int i, float f, byte b2, long j);

    public static native void invokeJJJV(int i, int i2, long j, long j2, long j3, long j4);

    public static native void invokePNNV(long j, long j2, int i, int i2, long j3, long j4);

    public static native void invokePPPV(int i, int i2, long j, long j2, long j3, long j4);

    public static native void invokePPPV(long j, int i, int i2, long j2, long j3, long j4);

    public static native void invokePPPV(long j, int i, long j2, long j3, int i2, long j4);

    public static native void invokePPPV(long j, long j2, int i, int i2, long j3, long j4);

    public static native void invokePPPV(long j, long j2, int i, long j3, int i2, long j4);

    public static native void invokePPPV(long j, long j2, long j3, float f, float f2, long j4);

    public static native void invokePPPV(long j, long j2, long j3, boolean z, boolean z2, long j4);

    public static native void invokePUCV(long j, byte b2, short s, int i, int i2, long j2);

    public static native void invokePUPV(long j, byte b2, long j2, int i, int i2, long j3);

    public static native void invokeUCCV(byte b2, short s, int i, int i2, short s2, long j);

    public static native void invokeUCUV(byte b2, short s, byte b3, int i, int i2, long j);

    public static native void invokeUPCV(byte b2, long j, int i, int i2, short s, long j2);

    public static native void invokeCCUV(short s, short s2, int i, int i2, int i3, byte b2, long j);

    public static native void invokePPPV(long j, int i, int i2, int i3, long j2, long j3, long j4);

    public static native void invokePPPV(long j, int i, long j2, int i2, long j3, int i3, long j4);

    public static native void invokePPPV(long j, int i, long j2, long j3, int i2, int i3, long j4);

    public static native void invokePPPV(long j, long j2, int i, int i2, int i3, long j3, long j4);

    public static native void invokePPPV(long j, long j2, long j3, int i, boolean z, boolean z2, long j4);

    public static native void invokePPPV(long j, boolean z, int i, long j2, long j3, int i2, long j4);

    public static native void invokePPPV(long j, int i, int i2, long j2, int i3, long j3, int i4, long j4);

    public static native void invokePPPV(long j, int i, long j2, int i2, long j3, int i3, int i4, long j4);

    public static native void invokePPPV(long j, long j2, float f, float f2, float f3, float f4, long j3, long j4);

    public static native void invokePPPV(long j, long j2, long j3, float f, float f2, float f3, float f4, long j4);

    public static native void invokePPPV(long j, long j2, int i, int i2, int i3, long j3, int i4, boolean z, long j4);

    public static native void invokePPPV(long j, long j2, float f, float f2, float f3, float f4, float f5, float f6, long j3, long j4);

    public static native void invokePPPV(long j, long j2, long j3, float f, float f2, float f3, float f4, float f5, float f6, long j4);

    public static native void invokeCCUPV(short s, short s2, byte b2, long j, long j2);

    public static native void invokePCPCV(long j, short s, long j2, short s2, long j3);

    public static native void invokePNPPV(long j, long j2, long j3, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, long j3, long j4, long j5);

    public static native void invokeCCCUV(short s, short s2, short s3, int i, byte b2, long j);

    public static native void invokePCCUV(long j, short s, short s2, int i, byte b2, long j2);

    public static native void invokePJJPV(long j, int i, long j2, long j3, long j4, long j5);

    public static native void invokePPCPV(long j, long j2, short s, int i, long j3, long j4);

    public static native void invokePPPCV(long j, long j2, int i, long j3, short s, long j4);

    public static native void invokePPPPV(long j, int i, long j2, long j3, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, int i, long j3, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, long j3, int i, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, long j3, long j4, int i, long j5);

    public static native void invokePUCCV(long j, byte b2, short s, short s2, int i, long j2);

    public static native void invokeCCCUV(short s, short s2, short s3, int i, int i2, byte b2, long j);

    public static native void invokePPPPV(long j, long j2, long j3, float f, float f2, long j4, long j5);

    public static native void invokePUCCV(long j, byte b2, short s, int i, int i2, short s2, long j2);

    public static native void invokePUCUV(long j, byte b2, short s, byte b3, int i, int i2, long j2);

    public static native void invokePUPCV(long j, byte b2, long j2, int i, int i2, short s, long j3);

    public static native void invokeCCCUV(short s, short s2, short s3, int i, int i2, int i3, byte b2, long j);

    public static native void invokePCCUV(long j, short s, short s2, int i, int i2, int i3, byte b2, long j2);

    public static native void invokePPPPV(long j, long j2, long j3, float f, float f2, float f3, float f4, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, long j3, int i, int i2, int i3, float f, long j4, long j5);

    public static native void invokePPPPV(long j, long j2, long j3, float f, float f2, float f3, float f4, float f5, float f6, long j4, long j5);

    public static native void invokeCCCCCV(short s, short s2, short s3, short s4, short s5, long j);

    public static native void invokeCCUPPV(short s, short s2, byte b2, long j, long j2, long j3);

    public static native void invokePPCPPV(long j, long j2, short s, long j3, long j4, long j5);

    public static native void invokePPPPPV(long j, long j2, long j3, long j4, long j5, long j6);

    public static native void invokePCCCUV(long j, short s, short s2, short s3, int i, byte b2, long j2);

    public static native void invokePPPPPV(int i, long j, long j2, long j3, long j4, long j5, long j6);

    public static native void invokePPPPPV(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native void invokePPPPPV(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native void invokePCCCUV(long j, short s, short s2, short s3, int i, int i2, byte b2, long j2);

    public static native void invokePPPPPV(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6);

    public static native void invokePCCCCV(long j, short s, short s2, short s3, boolean z, boolean z2, short s4, int i, long j2);

    public static native void invokePCCCUV(long j, short s, short s2, short s3, int i, int i2, int i3, byte b2, long j2);

    public static native void invokePPPPPV(long j, long j2, int i, long j3, int i2, long j4, int i3, long j5, long j6);

    public static native void invokePPPPPV(long j, long j2, int i, long j3, long j4, int i2, int i3, long j5, long j6);

    public static native void invokeCCCCUV(short s, short s2, short s3, int i, short s4, int i2, int i3, int i4, byte b2, long j);

    public static native void invokePPPPPV(int i, long j, int i2, long j2, long j3, long j4, int i3, long j5, int i4, boolean z, long j6);

    public static native void invokeCCCCPCV(short s, short s2, short s3, short s4, long j, short s5, long j2);

    public static native void invokePPPPPPV(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native void invokePCCCCUV(long j, short s, int i, short s2, short s3, short s4, byte b2, long j2);

    public static native void invokePCCCCUV(long j, short s, short s2, short s3, int i, short s4, int i2, int i3, int i4, byte b2, long j2);

    public static native void invokePPPPPPPV(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native void invokePPPPPPPV(long j, int i, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native void invokeCCUCCCCPCV(short s, short s2, byte b2, short s3, short s4, short s5, short s6, long j, short s7, long j2);

    public static native void invokeCUCCCCCCPV(short s, byte b2, short s2, short s3, short s4, short s5, short s6, short s7, long j, long j2);

    public static native void invokeCCUUCCCCPCV(short s, short s2, byte b2, byte b3, short s3, short s4, short s5, short s6, long j, short s7, long j2);

    public static native void invokeCCUUUUUUUUUV(short s, short s2, float f, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, long j);

    public static native void invokeCCUCCCCUCCCCCCV(short s, short s2, byte b2, short s3, short s4, short s5, short s6, byte b3, short s7, short s8, short s9, short s10, short s11, short s12, long j);

    public static native void invokePCCUCCCCUCCCCCCV(long j, short s, short s2, byte b2, short s3, short s4, short s5, short s6, byte b3, short s7, short s8, short s9, short s10, short s11, short s12, long j2);

    public static native boolean invokeZ(long j);

    public static native boolean invokeZ(int i, long j);

    public static native boolean invokePZ(long j, long j2);

    public static native boolean invokePZ(long j, int i, long j2);

    public static native boolean invokePZ(long j, boolean z, long j2);

    public static native boolean invokePZ(long j, int i, int i2, long j2);

    public static native boolean invokePZ(long j, int i, int i2, int i3, long j2);

    public static native boolean invokePPZ(long j, long j2, long j3);

    public static native boolean invokeUPZ(byte b2, long j, long j2);

    public static native boolean invokePPZ(long j, int i, long j2, long j3);

    public static native boolean invokePPZ(long j, int i, int i2, long j2, long j3);

    public static native boolean invokePPPZ(long j, long j2, long j3, long j4);

    public static native boolean invokePJPZ(long j, long j2, long j3, int i, long j4);

    public static native boolean invokePPPZ(long j, long j2, long j3, int i, long j4);

    public static native boolean invokeCCJZ(short s, boolean z, short s2, int i, long j, long j2);

    public static native boolean invokePPPZ(long j, long j2, long j3, boolean z, int i, long j4);

    public static native boolean invokePPPZ(long j, long j2, int i, long j3, int i2, boolean z, long j4);

    public static native boolean invokePPPPZ(long j, long j2, long j3, long j4, long j5);

    public static native boolean invokePPPPZ(long j, int i, long j2, long j3, long j4, long j5);

    public static native boolean invokePPPUPZ(long j, long j2, long j3, byte b2, long j4, long j5);

    public static native boolean invokePPPPPZ(long j, long j2, long j3, long j4, long j5, int i, long j6);

    public static native boolean invokePPPPPZ(long j, long j2, long j3, long j4, long j5, boolean z, int i, long j6);

    public static native short callC(int i, long j);

    public static native float callF(long j);

    public static native float callF(int i, int i2, int i3, long j);

    public static native float callPF(long j, float f, long j2);

    public static native float callPF(int i, int i2, long j, long j2);

    public static native float callPPPF(long j, long j2, long j3, long j4);

    public static native int callI(long j);

    public static native int callI(int i, long j);

    public static native int callI(int i, float f, long j);

    public static native int callI(int i, int i2, long j);

    public static native int callI(int i, int i2, int i3, long j);

    public static native int callJI(long j, long j2);

    public static native int callPI(long j, long j2);

    public static native int callJI(long j, float f, long j2);

    public static native int callJI(long j, int i, long j2);

    public static native int callPI(int i, long j, long j2);

    public static native int callPI(long j, float f, long j2);

    public static native int callPI(long j, int i, long j2);

    public static native int callPI(long j, boolean z, long j2);

    public static native int callJI(long j, int i, boolean z, long j2);

    public static native int callPI(int i, int i2, long j, long j2);

    public static native int callPI(int i, long j, int i2, long j2);

    public static native int callPI(long j, float f, float f2, long j2);

    public static native int callPI(long j, float f, int i, long j2);

    public static native int callPI(long j, int i, float f, long j2);

    public static native int callPI(long j, int i, int i2, long j2);

    public static native int callJI(int i, long j, int i2, int i3, long j2);

    public static native int callJI(long j, float f, float f2, float f3, long j2);

    public static native int callPI(int i, int i2, int i3, long j, long j2);

    public static native int callPI(int i, int i2, long j, int i3, long j2);

    public static native int callPI(int i, long j, int i2, int i3, long j2);

    public static native int callPI(long j, float f, float f2, float f3, long j2);

    public static native int callPI(long j, int i, float f, float f2, long j2);

    public static native int callPI(long j, int i, int i2, int i3, long j2);

    public static native int callPI(long j, int i, int i2, boolean z, long j2);

    public static native int callPI(int i, int i2, int i3, int i4, long j, long j2);

    public static native int callPI(long j, int i, float f, float f2, int i2, long j2);

    public static native int callPI(long j, int i, int i2, int i3, int i4, long j2);

    public static native int callPI(int i, int i2, int i3, float f, float f2, long j, long j2);

    public static native int callPI(int i, int i2, int i3, int i4, int i5, long j, long j2);

    public static native int callPI(int i, long j, int i2, int i3, float f, int i4, long j2);

    public static native int callPI(long j, int i, int i2, int i3, int i4, int i5, long j2);

    public static native int callPI(long j, int i, int i2, int i3, int i4, int i5, int i6, long j2);

    public static native int callPI(int i, int i2, long j, int i3, int i4, int i5, int i6, float f, long j2);

    public static native int callPI(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, long j2);

    public static native int callJJI(long j, long j2, long j3);

    public static native int callJPI(long j, long j2, long j3);

    public static native int callPJI(long j, long j2, long j3);

    public static native int callPPI(long j, long j2, long j3);

    public static native int callJPI(long j, int i, long j2, long j3);

    public static native int callJPI(long j, long j2, int i, long j3);

    public static native int callPJI(long j, int i, long j2, long j3);

    public static native int callPJI(long j, long j2, float f, long j3);

    public static native int callPJI(long j, long j2, int i, long j3);

    public static native int callPPI(int i, long j, long j2, long j3);

    public static native int callPPI(long j, float f, long j2, long j3);

    public static native int callPPI(long j, int i, long j2, long j3);

    public static native int callPPI(long j, long j2, float f, long j3);

    public static native int callPPI(long j, long j2, int i, long j3);

    public static native int callJPI(long j, int i, long j2, int i2, long j3);

    public static native int callJPI(long j, long j2, int i, int i2, long j3);

    public static native int callPJI(long j, int i, int i2, long j2, long j3);

    public static native int callPPI(int i, int i2, long j, long j2, long j3);

    public static native int callPPI(int i, long j, long j2, int i2, long j3);

    public static native int callPPI(long j, int i, float f, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, long j2, long j3);

    public static native int callPPI(long j, int i, long j2, int i2, long j3);

    public static native int callPPI(long j, long j2, float f, float f2, long j3);

    public static native int callPPI(long j, long j2, float f, int i, long j3);

    public static native int callPPI(long j, long j2, int i, int i2, long j3);

    public static native int callJPI(long j, int i, int i2, long j2, int i3, long j3);

    public static native int callJPI(long j, long j2, int i, int i2, int i3, long j3);

    public static native int callPPI(int i, int i2, int i3, long j, long j2, long j3);

    public static native int callPPI(int i, int i2, long j, int i3, long j2, long j3);

    public static native int callPPI(int i, long j, int i2, long j2, int i3, long j3);

    public static native int callPPI(long j, int i, float f, float f2, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, int i3, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, long j2, int i3, long j3);

    public static native int callPPI(long j, long j2, int i, int i2, int i3, long j3);

    public static native int callPPI(long j, long j2, int i, int i2, boolean z, long j3);

    public static native int callJJI(long j, float f, float f2, float f3, float f4, long j2, long j3);

    public static native int callPPI(int i, int i2, int i3, long j, int i4, long j2, long j3);

    public static native int callPPI(int i, long j, int i2, int i3, float f, long j2, long j3);

    public static native int callPPI(long j, int i, float f, float f2, float f3, long j2, long j3);

    public static native int callPPI(long j, long j2, int i, int i2, int i3, int i4, long j3);

    public static native int callPPI(long j, int i, float f, float f2, float f3, int i2, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, int i3, int i4, int i5, long j2, long j3);

    public static native int callPPI(long j, int i, float f, float f2, float f3, float f4, int i2, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, int i3, int i4, int i5, int i6, long j2, long j3);

    public static native int callPPI(int i, int i2, long j, long j2, int i3, int i4, int i5, int i6, float f, long j3);

    public static native int callPPI(long j, int i, float f, float f2, float f3, float f4, float f5, int i2, long j2, long j3);

    public static native int callPPI(long j, int i, int i2, float f, float f2, float f3, float f4, float f5, int i3, long j2, int i4, long j3);

    public static native int callPPI(long j, int i, int i2, int i3, int i4, int i5, int i6, long j2, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j3);

    public static native int callJPPI(long j, long j2, long j3, long j4);

    public static native int callPCPI(long j, short s, long j2, long j3);

    public static native int callPJJI(long j, long j2, long j3, long j4);

    public static native int callPJPI(long j, long j2, long j3, long j4);

    public static native int callPPJI(long j, long j2, long j3, long j4);

    public static native int callPPPI(long j, long j2, long j3, long j4);

    public static native int callPUPI(long j, byte b2, long j2, long j3);

    public static native int callJJPI(long j, long j2, long j3, int i, long j4);

    public static native int callJPJI(long j, long j2, int i, long j3, long j4);

    public static native int callJPPI(long j, int i, long j2, long j3, long j4);

    public static native int callJPPI(long j, long j2, int i, long j3, long j4);

    public static native int callJPPI(long j, long j2, long j3, int i, long j4);

    public static native int callPJJI(long j, long j2, int i, long j3, long j4);

    public static native int callPJJI(long j, long j2, long j3, float f, long j4);

    public static native int callPJJI(long j, long j2, long j3, int i, long j4);

    public static native int callPJJI(long j, long j2, long j3, boolean z, long j4);

    public static native int callPJPI(long j, int i, long j2, long j3, long j4);

    public static native int callPJPI(long j, long j2, int i, long j3, long j4);

    public static native int callPPJI(long j, int i, long j2, long j3, long j4);

    public static native int callPPJI(long j, long j2, int i, long j3, long j4);

    public static native int callPPJI(long j, long j2, long j3, int i, long j4);

    public static native int callPPNI(long j, int i, long j2, long j3, long j4);

    public static native int callPPPI(int i, long j, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, long j2, long j3, long j4);

    public static native int callPPPI(long j, long j2, int i, long j3, long j4);

    public static native int callPPPI(long j, long j2, long j3, int i, long j4);

    public static native int callJPJI(long j, int i, long j2, int i2, long j3, long j4);

    public static native int callJPPI(long j, int i, long j2, int i2, long j3, long j4);

    public static native int callJPPI(long j, int i, long j2, long j3, int i2, long j4);

    public static native int callJPPI(long j, long j2, int i, int i2, long j3, long j4);

    public static native int callJPPI(long j, long j2, long j3, int i, int i2, long j4);

    public static native int callPJJI(long j, long j2, int i, long j3, int i2, long j4);

    public static native int callPJPI(long j, int i, long j2, long j3, int i2, long j4);

    public static native int callPPJI(long j, int i, long j2, int i2, long j3, long j4);

    public static native int callPPPI(int i, int i2, long j, long j2, long j3, long j4);

    public static native int callPPPI(int i, long j, int i2, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, int i2, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, long j2, int i2, long j3, long j4);

    public static native int callPPPI(long j, int i, long j2, long j3, int i2, long j4);

    public static native int callPPPI(long j, long j2, int i, long j3, int i2, long j4);

    public static native int callPPPI(long j, long j2, long j3, int i, int i2, long j4);

    public static native int callJPJI(long j, int i, float f, long j2, int i2, long j3, long j4);

    public static native int callJPPI(long j, int i, long j2, int i2, long j3, int i3, long j4);

    public static native int callPPPI(long j, int i, int i2, int i3, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, int i2, long j2, int i3, long j3, long j4);

    public static native int callPPPI(long j, int i, long j2, long j3, int i2, int i3, long j4);

    public static native int callPPPI(long j, long j2, int i, int i2, int i3, long j3, long j4);

    public static native int callPPPI(long j, long j2, long j3, int i, int i2, int i3, long j4);

    public static native int callPPPI(long j, float f, float f2, int i, int i2, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, int i2, int i3, int i4, long j2, long j3, long j4);

    public static native int callPPPI(long j, int i, long j2, long j3, int i2, int i3, int i4, long j4);

    public static native int callPPPI(long j, long j2, int i, int i2, int i3, int i4, long j3, long j4);

    public static native int callJPPI(int i, int i2, int i3, int i4, int i5, long j, long j2, long j3, long j4);

    public static native int callPPJI(int i, int i2, int i3, long j, int i4, long j2, boolean z, long j3, long j4);

    public static native int callPPPI(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j2, long j3, long j4);

    public static native int callJJPPI(long j, long j2, long j3, long j4, long j5);

    public static native int callJPPPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPCPPI(long j, short s, long j2, long j3, long j4);

    public static native int callPJJJI(long j, long j2, long j3, long j4, long j5);

    public static native int callPJJPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPJPPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPPJPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPPNPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPPPJI(long j, long j2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, long j4, long j5);

    public static native int callPUPPI(long j, byte b2, long j2, long j3, long j4);

    public static native int callJPPPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int callPJJJI(long j, int i, long j2, long j3, long j4, long j5);

    public static native int callPJPPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int callPPJPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int callPPPJI(long j, long j2, long j3, int i, long j4, long j5);

    public static native int callPPPPI(long j, int i, long j2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, long j2, int i, long j3, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, int i, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, long j4, int i, long j5);

    public static native int callJPPPI(long j, int i, long j2, long j3, long j4, int i2, long j5);

    public static native int callPJPPI(long j, long j2, int i, int i2, long j3, long j4, long j5);

    public static native int callPPPPI(int i, long j, long j2, int i2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, int i, long j2, int i2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, int i, long j2, long j3, int i2, long j4, long j5);

    public static native int callPPPPI(long j, int i, long j2, long j3, long j4, int i2, long j5);

    public static native int callPPPPI(long j, long j2, int i, int i2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, int i, int i2, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, int i, long j4, int i2, long j5);

    public static native int callPPPPI(long j, long j2, long j3, long j4, int i, int i2, long j5);

    public static native int callPJPPI(long j, long j2, int i, int i2, int i3, long j3, long j4, long j5);

    public static native int callPPPPI(long j, int i, int i2, int i3, long j2, long j3, long j4, long j5);

    public static native int callPPPPI(long j, int i, long j2, int i2, int i3, long j3, long j4, long j5);

    public static native int callPPPPI(long j, long j2, long j3, int i, int i2, int i3, long j4, long j5);

    public static native int callJPPJI(long j, int i, int i2, int i3, long j2, int i4, long j3, boolean z, long j4, long j5);

    public static native int callPPPPI(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j2, long j3, long j4, long j5);

    public static native int callPPPPI(int i, long j, long j2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, long j3, long j4, long j5);

    public static native int callJPPPJI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPJJPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPJPPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPCPPI(long j, long j2, short s, long j3, long j4, long j5);

    public static native int callPPJPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPJJI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPPJI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPUPPI(long j, long j2, byte b2, long j3, long j4, long j5);

    public static native int callPJJJPI(long j, long j2, long j3, long j4, int i, long j5, long j6);

    public static native int callPJPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, long j3, int i, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, long j3, long j4, int i, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, long j3, long j4, long j5, int i, long j6);

    public static native int callJJPPPI(long j, long j2, int i, long j3, int i2, long j4, long j5, long j6);

    public static native int callPPJPPI(long j, int i, long j2, long j3, int i2, long j4, long j5, long j6);

    public static native int callPPJPPI(long j, long j2, int i, long j3, int i2, long j4, long j5, long j6);

    public static native int callPPPPPI(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, int i, long j2, int i2, long j3, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, int i, long j2, long j3, int i2, long j4, long j5, long j6);

    public static native int callPPPPPI(long j, long j2, long j3, long j4, long j5, int i, int i2, long j6);

    public static native int callJPPPPI(int i, int i2, long j, long j2, int i3, long j3, long j4, long j5, long j6);

    public static native int callPJPPJI(long j, long j2, int i, int i2, long j3, long j4, long j5, int i3, long j6);

    public static native int callPPPPPI(long j, int i, long j2, int i2, long j3, int i3, long j4, long j5, long j6);

    public static native int callJPJPPJI(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPJJJJPI(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPCPPPI(long j, long j2, short s, long j3, long j4, long j5, long j6);

    public static native int callPPPPJPI(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPUPPPI(long j, long j2, byte b2, long j3, long j4, long j5, long j6);

    public static native int callPJJPPPI(long j, long j2, long j3, int i, long j4, long j5, long j6, long j7);

    public static native int callPJPPPPI(long j, int i, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPPJPPI(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7);

    public static native int callPJPPPPI(long j, int i, long j2, long j3, long j4, int i2, long j5, long j6, long j7);

    public static native int callPPPJPPI(long j, int i, long j2, long j3, long j4, int i2, long j5, long j6, long j7);

    public static native int callPPPPPPI(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPPPPPI(long j, int i, long j2, int i2, long j3, long j4, long j5, long j6, long j7);

    public static native int callPPPPPPI(long j, int i, long j2, long j3, long j4, int i2, long j5, long j6, long j7);

    public static native int callPPPPPPI(long j, long j2, long j3, long j4, int i, long j5, long j6, int i2, long j7);

    public static native int callPPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6, int i3, int i4, int i5, int i6, long j7);

    public static native int callPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8);

    public static native int callPPPPPPPI(long j, int i, long j2, int i2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, int i, long j2, long j3, int i2, long j4, long j5, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, int i2, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, long j2, long j3, int i, long j4, long j5, int i2, long j6, long j7, long j8);

    public static native int callPPPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6, int i3, int i4, long j7, int i5, int i6, int i7, int i8, long j8);

    public static native int callPPPPPJPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native int callPPJPPPPPI(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7, long j8, long j9);

    public static native int callPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, int i, long j7, long j8, long j9);

    public static native int callPPPPPPPPI(long j, long j2, long j3, long j4, int i, long j5, long j6, int i2, long j7, long j8, long j9);

    public static native int callPPPPPPPPI(long j, int i, int i2, long j2, long j3, long j4, long j5, int i3, long j6, long j7, long j8, long j9);

    public static native int callJPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10);

    public static native int callPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, int i, long j7, long j8, long j9, long j10);

    public static native int callPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, int i2, long j8, long j9, long j10);

    public static native int callPPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, long j8, long j9, long j10, long j11);

    public static native int callPPPPPPPPPPI(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, int i2, long j8, long j9, long j10, long j11);

    public static native int callPPPPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i, long j11, long j12, long j13);

    public static native int callPPPPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, int i2, long j11, long j12, long j13);

    public static native int callPPPPPPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, long j12, long j13, long j14, long j15);

    public static native long callJ(long j);

    public static native long callJ(int i, long j);

    public static native long callJ(int i, int i2, long j);

    public static native long callJ(int i, int i2, boolean z, int i3, int i4, long j);

    public static native long callJJ(long j, long j2);

    public static native long callPJ(long j, long j2);

    public static native long callPJ(int i, int i2, long j, long j2);

    public static native long callPPJ(long j, long j2, long j3);

    public static native long callPPJ(long j, int i, long j2, long j3);

    public static native long callPJJ(long j, long j2, int i, int i2, long j3);

    public static native long callPN(long j, long j2);

    public static native long callP(long j);

    public static native long callP(int i, long j);

    public static native long callP(int i, int i2, long j);

    public static native long callP(int i, float f, float f2, float f3, long j);

    public static native long callJP(long j, long j2);

    public static native long callPP(long j, long j2);

    public static native long callPP(int i, long j, long j2);

    public static native long callPP(long j, int i, long j2);

    public static native long callPP(int i, int i2, long j, long j2);

    public static native long callPP(int i, long j, int i2, long j2);

    public static native long callPP(long j, int i, int i2, long j2);

    public static native long callPP(int i, int i2, long j, int i3, long j2);

    public static native long callJJP(long j, long j2, long j3);

    public static native long callPNP(long j, long j2, long j3);

    public static native long callPPP(long j, long j2, long j3);

    public static native long callPPP(int i, long j, long j2, long j3);

    public static native long callPPP(long j, int i, long j2, long j3);

    public static native long callPPP(long j, long j2, int i, long j3);

    public static native long callPPP(int i, long j, long j2, int i2, long j3);

    public static native long callPPP(long j, int i, int i2, long j2, long j3);

    public static native long callPPP(int i, int i2, int i3, long j, long j2, long j3);

    public static native long callPPP(long j, int i, int i2, int i3, long j2, long j3);

    public static native long callPPP(long j, long j2, int i, int i2, int i3, long j3);

    public static native long callPPNP(long j, long j2, long j3, long j4);

    public static native long callPPPP(long j, long j2, long j3, long j4);

    public static native long callPJPP(long j, long j2, int i, long j3, long j4);

    public static native long callPJPP(long j, long j2, long j3, int i, long j4);

    public static native long callPPPP(int i, long j, long j2, long j3, long j4);

    public static native long callPPPP(long j, int i, long j2, long j3, long j4);

    public static native long callPPPP(long j, long j2, int i, long j3, long j4);

    public static native long callPPPP(long j, long j2, long j3, int i, long j4);

    public static native long callPPPP(long j, long j2, int i, int i2, long j3, long j4);

    public static native long callPPPP(long j, long j2, int i, long j3, int i2, long j4);

    public static native long callPJPP(long j, long j2, int i, int i2, int i3, long j3, long j4);

    public static native long callJJPPP(long j, long j2, long j3, long j4, long j5);

    public static native long callPPJPP(long j, long j2, long j3, long j4, long j5);

    public static native long callPPNPP(long j, long j2, long j3, long j4, long j5);

    public static native long callPPPPP(long j, long j2, long j3, long j4, long j5);

    public static native long callPJPPP(long j, long j2, int i, long j3, long j4, long j5);

    public static native long callPJPPP(long j, long j2, long j3, int i, long j4, long j5);

    public static native long callPPPPP(long j, int i, long j2, long j3, long j4, long j5);

    public static native long callPPPPP(long j, long j2, int i, long j3, long j4, long j5);

    public static native long callPPPPP(long j, long j2, long j3, int i, long j4, long j5);

    public static native long callPPPPP(long j, long j2, long j3, long j4, int i, long j5);

    public static native long callPJPPP(long j, long j2, int i, int i2, long j3, long j4, long j5);

    public static native long callPJPPPP(long j, long j2, long j3, long j4, long j5, long j6);

    public static native long callPPPJPP(long j, long j2, long j3, long j4, long j5, long j6);

    public static native long callPPPPPP(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native long callPPPPPP(long j, long j2, long j3, long j4, int i, long j5, long j6);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long callPJPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long callPPJPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long callPPPJPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long callPPPPPPP(long j, int i, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long callPPJPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native long callPPPPJPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native long callPPPPPPPP(long j, int i, long j2, long j3, int i2, long j4, long j5, long j6, long j7, long j8);

    public static native long callPPPPPPPP(int i, long j, long j2, int i2, int i3, int i4, int i5, long j3, long j4, long j5, int i6, long j6, long j7, long j8);

    public static native long callPJPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long callPPJPPPPPP(long j, long j2, int i, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9);

    public static native long callPJPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11);

    public static native long callPPJPPPPPPPP(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, int i2, long j8, long j9, long j10, long j11);

    public static native short callS(int i, long j);

    public static native short callPS(long j, long j2);

    public static native short callPCS(long j, short s, long j2);

    public static native short callPPS(long j, long j2, long j3);

    public static native short callPSS(long j, short s, long j2);

    public static native short callSPS(short s, long j, long j2);

    public static native short callPPS(long j, long j2, int i, long j3);

    public static native short callPPS(long j, int i, long j2, int i2, long j3);

    public static native short callPCPS(long j, short s, long j2, long j3);

    public static native short callPPCS(long j, long j2, short s, long j3);

    public static native short callPPPS(long j, long j2, long j3, long j4);

    public static native short callPPSS(long j, long j2, short s, long j3);

    public static native short callPSPS(long j, short s, long j2, long j3);

    public static native short callSPPS(short s, long j, long j2, long j3);

    public static native short callSPSS(short s, long j, short s2, long j2);

    public static native short callPPPS(long j, int i, long j2, int i2, long j3, long j4);

    public static native short callPJCCS(long j, long j2, short s, short s2, long j3);

    public static native short callPPSPS(long j, long j2, short s, long j3, long j4);

    public static native short callPSSPS(long j, short s, short s2, long j2, int i, long j3);

    public static native short callPPPPS(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native short callPCPPPS(long j, short s, long j2, long j3, long j4, long j5);

    public static native short callPCPSPS(long j, short s, long j2, short s2, long j3, long j4);

    public static native short callPSSPPS(long j, short s, short s2, long j2, int i, long j3, long j4);

    public static native short callPCPPPPS(long j, short s, long j2, long j3, long j4, long j5, long j6);

    public static native short callPCSPPPS(long j, short s, short s2, long j2, long j3, long j4, long j5);

    public static native short callPPSPSPS(long j, long j2, short s, long j3, short s2, long j4, long j5);

    public static native short callPCCPSPPS(long j, short s, short s2, long j2, short s3, long j3, long j4, long j5);

    public static native short callPPSPSPSS(long j, long j2, short s, long j3, short s2, long j4, short s3, long j5);

    public static native short callSPSSPSPS(short s, long j, short s2, short s3, long j2, short s4, long j3, long j4);

    public static native short callPCPSPPSPS(long j, short s, long j2, short s2, long j3, long j4, short s3, long j5, long j6);

    public static native short callPPPSPSPCS(long j, long j2, long j3, short s, long j4, short s2, long j5, short s3, long j6);

    public static native short callSPSPPPSPS(short s, long j, short s2, long j2, long j3, long j4, short s3, long j5, long j6);

    public static native short callPCPSPPPPPS(long j, short s, long j2, short s2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native short callPPSPSPSCCS(long j, long j2, short s, long j3, short s2, long j4, short s3, short s4, short s5, long j5);

    public static native short callPPSPSPSPSS(long j, long j2, short s, long j3, short s2, long j4, short s3, long j5, short s4, long j6);

    public static native short callPCPSPSPSCCS(long j, short s, long j2, short s2, long j3, short s3, long j4, short s4, short s5, short s6, long j5);

    public static native short callPCSSSPSPPPS(long j, short s, short s2, short s3, short s4, long j2, short s5, long j3, long j4, long j5, long j6);

    public static native short callPSSSPSSPPPS(long j, short s, short s2, short s3, long j2, short s4, short s5, long j3, long j4, long j5, long j6);

    public static native short callPSPSPPPPPPPS(long j, short s, long j2, short s2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10);

    public static native short callPPSPSPSPSPSPSS(long j, long j2, short s, long j3, short s2, long j4, short s3, long j5, short s4, long j6, short s5, long j7, short s6, long j8);

    public static native void callV(long j);

    public static native void callV(double d, long j);

    public static native void callV(float f, long j);

    public static native void callV(int i, long j);

    public static native void callV(boolean z, long j);

    public static native void callV(double d, double d2, long j);

    public static native void callV(float f, float f2, long j);

    public static native void callV(float f, boolean z, long j);

    public static native void callV(int i, double d, long j);

    public static native void callV(int i, float f, long j);

    public static native void callV(int i, int i2, long j);

    public static native void callV(int i, boolean z, long j);

    public static native void callV(double d, double d2, double d3, long j);

    public static native void callV(float f, float f2, float f3, long j);

    public static native void callV(int i, double d, double d2, long j);

    public static native void callV(int i, float f, float f2, long j);

    public static native void callV(int i, int i2, double d, long j);

    public static native void callV(int i, int i2, float f, long j);

    public static native void callV(int i, int i2, int i3, long j);

    public static native void callV(int i, int i2, boolean z, long j);

    public static native void callV(double d, double d2, double d3, double d4, long j);

    public static native void callV(float f, float f2, float f3, float f4, long j);

    public static native void callV(int i, double d, double d2, double d3, long j);

    public static native void callV(int i, float f, float f2, float f3, long j);

    public static native void callV(int i, int i2, double d, double d2, long j);

    public static native void callV(int i, int i2, float f, float f2, long j);

    public static native void callV(int i, int i2, float f, int i3, long j);

    public static native void callV(int i, int i2, int i3, double d, long j);

    public static native void callV(int i, int i2, int i3, float f, long j);

    public static native void callV(int i, int i2, int i3, int i4, long j);

    public static native void callV(int i, int i2, int i3, boolean z, long j);

    public static native void callV(int i, int i2, boolean z, int i3, long j);

    public static native void callV(boolean z, boolean z2, boolean z3, boolean z4, long j);

    public static native void callV(int i, double d, double d2, double d3, double d4, long j);

    public static native void callV(int i, float f, float f2, float f3, float f4, long j);

    public static native void callV(int i, int i2, double d, double d2, double d3, long j);

    public static native void callV(int i, int i2, float f, float f2, float f3, long j);

    public static native void callV(int i, int i2, int i3, float f, int i4, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, long j);

    public static native void callV(int i, int i2, int i3, boolean z, int i4, long j);

    public static native void callV(int i, boolean z, boolean z2, boolean z3, boolean z4, long j);

    public static native void callV(double d, double d2, double d3, double d4, double d5, double d6, long j);

    public static native void callV(float f, float f2, float f3, float f4, float f5, boolean z, long j);

    public static native void callV(int i, double d, double d2, int i2, double d3, double d4, long j);

    public static native void callV(int i, float f, float f2, int i2, float f3, float f4, long j);

    public static native void callV(int i, int i2, double d, double d2, double d3, double d4, long j);

    public static native void callV(int i, int i2, float f, float f2, float f3, float f4, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, boolean z, long j);

    public static native void callV(int i, int i2, int i3, int i4, boolean z, int i5, long j);

    public static native void callV(int i, double d, double d2, double d3, double d4, double d5, double d6, long j);

    public static native void callV(int i, int i2, int i3, double d, double d2, double d3, double d4, long j);

    public static native void callV(int i, int i2, int i3, float f, float f2, float f3, float f4, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, boolean z, long j);

    public static native void callV(int i, int i2, int i3, boolean z, int i4, int i5, int i6, long j);

    public static native void callV(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, long j);

    public static native void callV(int i, int i2, int i3, float f, float f2, float f3, float f4, float f5, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void callV(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j);

    public static native void callV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, long j);

    public static native void callJV(long j, long j2);

    public static native void callPV(long j, long j2);

    public static native void callSV(short s, long j);

    public static native void callUV(byte b2, long j);

    public static native void callCV(int i, short s, long j);

    public static native void callJV(int i, long j, long j2);

    public static native void callJV(long j, int i, long j2);

    public static native void callPV(int i, long j, long j2);

    public static native void callPV(long j, float f, long j2);

    public static native void callPV(long j, int i, long j2);

    public static native void callSV(int i, short s, long j);

    public static native void callCV(int i, int i2, short s, long j);

    public static native void callJV(int i, int i2, long j, long j2);

    public static native void callPV(int i, int i2, long j, long j2);

    public static native void callPV(int i, long j, int i2, long j2);

    public static native void callPV(long j, float f, float f2, long j2);

    public static native void callPV(long j, int i, int i2, long j2);

    public static native void callJV(int i, long j, int i2, int i3, long j2);

    public static native void callNV(long j, int i, int i2, int i3, long j2);

    public static native void callPV(int i, float f, long j, int i2, long j2);

    public static native void callPV(int i, int i2, int i3, long j, long j2);

    public static native void callPV(int i, int i2, long j, int i3, long j2);

    public static native void callPV(int i, int i2, boolean z, long j, long j2);

    public static native void callPV(int i, long j, int i2, int i3, long j2);

    public static native void callPV(long j, float f, float f2, float f3, long j2);

    public static native void callPV(long j, int i, int i2, int i3, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, long j, long j2);

    public static native void callPV(int i, int i2, int i3, long j, int i4, long j2);

    public static native void callPV(int i, int i2, int i3, long j, boolean z, long j2);

    public static native void callPV(int i, int i2, int i3, boolean z, long j, long j2);

    public static native void callPV(int i, int i2, long j, int i3, int i4, long j2);

    public static native void callPV(int i, long j, int i2, int i3, int i4, long j2);

    public static native void callPV(int i, boolean z, int i2, int i3, long j, long j2);

    public static native void callPV(long j, int i, int i2, int i3, int i4, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, long j, long j2);

    public static native void callPV(int i, double d, double d2, int i2, int i3, long j, long j2);

    public static native void callPV(int i, float f, float f2, int i2, int i3, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, long j, boolean z, long j2);

    public static native void callPV(int i, int i2, int i3, long j, int i4, int i5, long j2);

    public static native void callPV(int i, int i2, int i3, boolean z, int i4, long j, long j2);

    public static native void callPV(int i, int i2, long j, int i3, int i4, int i5, long j2);

    public static native void callPV(int i, boolean z, int i2, int i3, int i4, long j, long j2);

    public static native void callPV(long j, int i, int i2, int i3, int i4, int i5, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, int i6, long j, long j2);

    public static native void callPV(int i, int i2, float f, float f2, float f3, float f4, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, long j, int i6, long j2);

    public static native void callPV(int i, int i2, int i3, long j, int i4, int i5, int i6, long j2);

    public static native void callPV(int i, int i2, long j, int i3, int i4, int i5, int i6, long j2);

    public static native void callPV(long j, int i, int i2, int i3, int i4, int i5, int i6, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, boolean z, int i6, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, boolean z, int i6, long j, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j, long j2);

    public static native void callPV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, float f, long j2);

    public static native void callPV(int i, double d, double d2, int i2, int i3, double d3, double d4, int i4, int i5, long j, long j2);

    public static native void callPV(int i, float f, float f2, int i2, int i3, float f3, float f4, int i4, int i5, long j, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j, long j2);

    public static native void callJV(long j, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j, long j2);

    public static native void callPV(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j2);

    public static native void callJV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j, boolean z, long j2);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j, long j2);

    public static native void callJPV(long j, long j2, long j3);

    public static native void callPJV(long j, long j2, long j3);

    public static native void callPPV(long j, long j2, long j3);

    public static native void callSSV(short s, short s2, long j);

    public static native void callJJV(int i, long j, long j2, long j3);

    public static native void callPCV(long j, int i, short s, long j2);

    public static native void callPJV(long j, int i, long j2, long j3);

    public static native void callPJV(long j, long j2, float f, long j3);

    public static native void callPJV(long j, long j2, int i, long j3);

    public static native void callPPV(int i, long j, long j2, long j3);

    public static native void callPPV(long j, int i, long j2, long j3);

    public static native void callPPV(long j, long j2, int i, long j3);

    public static native void callSSV(int i, short s, short s2, long j);

    public static native void callJJV(int i, int i2, long j, long j2, long j3);

    public static native void callJPV(int i, int i2, long j, long j2, long j3);

    public static native void callJPV(int i, long j, int i2, long j2, long j3);

    public static native void callPJV(int i, long j, int i2, long j2, long j3);

    public static native void callPJV(long j, int i, long j2, int i2, long j3);

    public static native void callPJV(long j, long j2, int i, int i2, long j3);

    public static native void callPPV(int i, int i2, long j, long j2, long j3);

    public static native void callPPV(int i, long j, int i2, long j2, long j3);

    public static native void callPPV(int i, long j, long j2, int i2, long j3);

    public static native void callPPV(int i, long j, long j2, boolean z, long j3);

    public static native void callPPV(long j, int i, float f, long j2, long j3);

    public static native void callPPV(long j, int i, int i2, long j2, long j3);

    public static native void callPPV(long j, long j2, int i, int i2, long j3);

    public static native void callPJV(long j, long j2, int i, int i2, int i3, long j3);

    public static native void callPPV(int i, int i2, int i3, long j, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, long j2, int i3, long j3);

    public static native void callPPV(int i, long j, int i2, long j2, int i3, long j3);

    public static native void callPPV(int i, long j, long j2, int i2, int i3, long j3);

    public static native void callPPV(long j, int i, int i2, int i3, long j2, long j3);

    public static native void callPPV(int i, int i2, int i3, int i4, long j, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, long j2, int i3, int i4, long j3);

    public static native void callPPV(int i, long j, long j2, int i2, int i3, int i4, long j3);

    public static native void callPPV(long j, int i, long j2, int i2, int i3, int i4, long j3);

    public static native void callPPV(int i, int i2, int i3, int i4, int i5, long j, long j2, long j3);

    public static native void callPPV(int i, int i2, int i3, long j, int i4, int i5, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, long j2, int i3, int i4, int i5, long j3);

    public static native void callPPV(int i, int i2, int i3, int i4, int i5, int i6, long j, long j2, long j3);

    public static native void callPPV(int i, int i2, int i3, int i4, long j, int i5, int i6, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, int i6, long j2, long j3);

    public static native void callPPV(int i, int i2, int i3, long j, int i4, float f, float f2, int i5, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, long j2, long j3);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, long j2, int i6, int i7, float f, long j3);

    public static native void callBBBV(byte b2, byte b3, byte b4, long j);

    public static native void callCCCV(short s, short s2, short s3, long j);

    public static native void callPJJV(long j, long j2, long j3, long j4);

    public static native void callPJPV(long j, long j2, long j3, long j4);

    public static native void callPPNV(long j, long j2, long j3, long j4);

    public static native void callPPPV(long j, long j2, long j3, long j4);

    public static native void callSSSV(short s, short s2, short s3, long j);

    public static native void callUUUV(byte b2, byte b3, byte b4, long j);

    public static native void callJJJV(int i, long j, long j2, long j3, long j4);

    public static native void callPJJV(long j, long j2, long j3, int i, long j4);

    public static native void callPJPV(long j, long j2, int i, long j3, long j4);

    public static native void callPPPV(int i, long j, long j2, long j3, long j4);

    public static native void callPPPV(long j, int i, long j2, long j3, long j4);

    public static native void callPPPV(long j, long j2, float f, long j3, long j4);

    public static native void callPPPV(long j, long j2, int i, long j3, long j4);

    public static native void callPPPV(long j, long j2, long j3, int i, long j4);

    public static native void callPPPV(long j, long j2, boolean z, long j3, long j4);

    public static native void callSSSV(int i, short s, short s2, short s3, long j);

    public static native void callJJJV(int i, int i2, long j, long j2, long j3, long j4);

    public static native void callPJJV(long j, int i, long j2, long j3, int i2, long j4);

    public static native void callPJJV(long j, long j2, long j3, int i, int i2, long j4);

    public static native void callPPPV(int i, int i2, long j, long j2, long j3, long j4);

    public static native void callPPPV(int i, long j, int i2, long j2, long j3, long j4);

    public static native void callPPPV(int i, long j, long j2, int i2, long j3, long j4);

    public static native void callPPPV(int i, long j, long j2, long j3, int i2, long j4);

    public static native void callPPPV(long j, int i, int i2, long j2, long j3, long j4);

    public static native void callPPPV(long j, int i, long j2, int i2, long j3, long j4);

    public static native void callPJPV(long j, int i, long j2, int i2, int i3, long j3, long j4);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, long j3, long j4);

    public static native void callPPJV(int i, long j, long j2, int i2, long j3, boolean z, long j4);

    public static native void callPPJV(long j, int i, long j2, int i2, long j3, int i3, long j4);

    public static native void callPPPV(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native void callPPPV(int i, int i2, long j, int i3, long j2, long j3, long j4);

    public static native void callPPPV(int i, long j, int i2, long j2, int i3, long j3, long j4);

    public static native void callPJJV(long j, int i, int i2, long j2, long j3, int i3, int i4, long j4);

    public static native void callPPPV(int i, int i2, int i3, int i4, long j, long j2, long j3, long j4);

    public static native void callPPPV(int i, int i2, long j, long j2, int i3, int i4, long j3, long j4);

    public static native void callPPPV(long j, int i, long j2, int i2, int i3, int i4, long j3, long j4);

    public static native void callPPPV(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3, long j4);

    public static native void callPPPV(long j, int i, int i2, int i3, int i4, int i5, long j2, long j3, long j4);

    public static native void callPPPV(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, long j3, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, long j4);

    public static native void callBBBBV(byte b2, byte b3, byte b4, byte b5, long j);

    public static native void callCCCCV(short s, short s2, short s3, short s4, long j);

    public static native void callPJJPV(long j, long j2, long j3, long j4, long j5);

    public static native void callPJPPV(long j, long j2, long j3, long j4, long j5);

    public static native void callPPPNV(long j, long j2, long j3, long j4, long j5);

    public static native void callPPPPV(long j, long j2, long j3, long j4, long j5);

    public static native void callSSSSV(short s, short s2, short s3, short s4, long j);

    public static native void callUUUUV(byte b2, byte b3, byte b4, byte b5, long j);

    public static native void callJJJJV(int i, long j, long j2, long j3, long j4, long j5);

    public static native void callPJJJV(long j, long j2, long j3, long j4, int i, long j5);

    public static native void callPJJPV(long j, int i, long j2, long j3, long j4, long j5);

    public static native void callPJJPV(long j, long j2, long j3, int i, long j4, long j5);

    public static native void callPPPPV(int i, long j, long j2, long j3, long j4, long j5);

    public static native void callPPPPV(long j, int i, long j2, long j3, long j4, long j5);

    public static native void callPPPPV(long j, long j2, long j3, int i, long j4, long j5);

    public static native void callPPPPV(long j, long j2, long j3, long j4, int i, long j5);

    public static native void callSSSSV(int i, short s, short s2, short s3, short s4, long j);

    public static native void callUUUUV(int i, byte b2, byte b3, byte b4, byte b5, long j);

    public static native void callJJJJV(int i, int i2, long j, long j2, long j3, long j4, long j5);

    public static native void callPJJPV(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native void callPJJPV(long j, long j2, long j3, int i, int i2, long j4, long j5);

    public static native void callPJPPV(long j, long j2, int i, long j3, int i2, long j4, long j5);

    public static native void callPPPPV(int i, long j, int i2, long j2, long j3, long j4, long j5);

    public static native void callPPPPV(int i, long j, long j2, long j3, long j4, int i2, long j5);

    public static native void callPPPPV(long j, int i, int i2, long j2, long j3, long j4, long j5);

    public static native void callPJJPV(long j, long j2, int i, int i2, long j3, int i3, long j4, long j5);

    public static native void callPJJPV(long j, long j2, int i, long j3, int i2, int i3, long j4, long j5);

    public static native void callPJPPV(long j, int i, long j2, int i2, int i3, long j3, long j4, long j5);

    public static native void callPPPPV(int i, int i2, int i3, long j, long j2, long j3, long j4, long j5);

    public static native void callPPPPV(int i, int i2, long j, long j2, long j3, long j4, int i3, long j5);

    public static native void callPJJPV(long j, long j2, int i, long j3, int i2, int i3, long j4, int i4, long j5);

    public static native void callPJPPV(long j, int i, long j2, int i2, int i3, long j3, int i4, long j4, long j5);

    public static native void callPPPPV(long j, int i, int i2, int i3, int i4, long j2, int i5, long j3, int i6, long j4, long j5);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, long j5, long j6);

    public static native void callPPPPPV(long j, int i, long j2, long j3, long j4, long j5, long j6);

    public static native void callPJJJJV(long j, long j2, long j3, long j4, long j5, int i, int i2, long j6);

    public static native void callPPPPPV(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6);

    public static native void callPPPPPV(long j, int i, int i2, long j2, long j3, long j4, long j5, long j6);

    public static native void callPJJJJV(long j, long j2, int i, int i2, long j3, long j4, long j5, int i3, long j6);

    public static native void callPJPPPV(long j, int i, int i2, long j2, long j3, int i3, long j4, long j5, long j6);

    public static native void callPPPPPV(long j, long j2, long j3, long j4, long j5, int i, int i2, int i3, long j6);

    public static native void callPPPPPV(long j, int i, long j2, int i2, int i3, int i4, long j3, int i5, long j4, int i6, long j5, long j6);

    public static native void callPPPPPJV(long j, long j2, long j3, long j4, long j5, long j6, long j7);

    public static native void callPPPPPPV(long j, long j2, long j3, int i, int i2, long j4, long j5, long j6, long j7);

    public static native void callPPPPPPPV(int i, int i2, int i3, long j, int i4, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native void callPPJJJJJJV(long j, long j2, long j3, long j4, int i, long j5, long j6, long j7, long j8, long j9);

    public static native void callPJJJJJJJJJJJV(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, int i, int i2, int i3, long j13);

    public static native boolean callZ(long j);

    public static native boolean callZ(int i, long j);

    public static native boolean callZ(boolean z, long j);

    public static native boolean callZ(int i, int i2, long j);

    public static native boolean callZ(int i, float f, float f2, long j);

    public static native boolean callZ(int i, int i2, float f, float f2, long j);

    public static native boolean callJZ(long j, long j2);

    public static native boolean callPZ(long j, long j2);

    public static native boolean callJZ(int i, long j, long j2);

    public static native boolean callPZ(int i, long j, long j2);

    public static native boolean callPZ(long j, int i, long j2);

    public static native boolean callJZ(int i, long j, int i2, long j2);

    public static native boolean callPZ(int i, int i2, long j, long j2);

    public static native boolean callPZ(int i, long j, int i2, long j2);

    public static native boolean callPZ(int i, float f, float f2, long j, long j2);

    public static native boolean callPPZ(long j, long j2, long j3);

    public static native boolean callJPZ(long j, long j2, int i, long j3);

    public static native boolean callPPZ(int i, long j, long j2, long j3);

    public static native boolean callPPZ(long j, int i, long j2, long j3);

    public static native boolean callPPZ(long j, long j2, int i, long j3);

    public static native boolean callPPZ(int i, long j, int i2, long j2, long j3);

    public static native boolean callPPZ(int i, int i2, long j, int i3, long j2, long j3);

    public static native boolean callJPPZ(long j, long j2, long j3, long j4);

    public static native boolean callPPPZ(long j, long j2, long j3, long j4);

    public static native boolean callPPPPZ(int i, int i2, int i3, float f, long j, long j2, long j3, long j4, long j5);

    public static native boolean callPPJPPZ(long j, long j2, long j3, long j4, long j5, long j6);

    public static native boolean callPPPPPZ(long j, long j2, long j3, long j4, long j5, long j6);

    public static native short invokeUPC(byte b2, short[] sArr, boolean z, long j);

    public static native short invokeCPCC(short s, short[] sArr, short s2, long j);

    public static native int invokeCPI(short s, int[] iArr, long j);

    public static native int invokePCI(float[] fArr, short s, long j);

    public static native int invokePPI(int i, long j, int[] iArr, long j2);

    public static native int invokePPI(int i, int[] iArr, int i2, int[] iArr2, int i3, boolean z, long j);

    public static native int invokePPI(int i, short[] sArr, int i2, short[] sArr2, int i3, boolean z, long j);

    public static native int invokeCPUI(short s, float[] fArr, byte b2, long j);

    public static native int invokeCPUI(short s, int[] iArr, byte b2, long j);

    public static native int invokeCPUI(short s, short[] sArr, byte b2, long j);

    public static native int invokePPCI(long j, float[] fArr, short s, long j2);

    public static native int invokePPPI(int[] iArr, long j, long j2, int i, boolean z, float f, long j3);

    public static native int invokePPPI(short[] sArr, long j, long j2, int i, boolean z, float f, long j3);

    public static native int invokePPPPI(long j, long j2, long j3, long[] jArr, long j4);

    public static native int invokePPPPI(long j, long j2, int i, int i2, float[] fArr, int[] iArr, long j3);

    public static native int invokePPPPI(long j, long j2, int i, int i2, int[] iArr, int[] iArr2, long j3);

    public static native int invokePPPPPI(long j, int[] iArr, int[] iArr2, int[] iArr3, long j2, long j3);

    public static native int invokePNNPPPI(long j, long j2, long j3, int i, int i2, int[] iArr, int[] iArr2, long j4, long j5);

    public static native int invokePPPPPPPPI(long j, int i, int i2, long j2, int[] iArr, int[] iArr2, float[] fArr, int[] iArr3, int[] iArr4, int[] iArr5, long j3);

    public static native long invokePP(double[] dArr, int i, long j);

    public static native long invokePP(float[] fArr, int i, long j);

    public static native long invokePP(int[] iArr, int i, long j);

    public static native long invokePP(long[] jArr, int i, long j);

    public static native long invokePP(short[] sArr, int i, long j);

    public static native long invokePPP(long j, int[] iArr, long j2);

    public static native byte invokeUPU(byte b2, int[] iArr, long j);

    public static native void invokePV(int i, double[] dArr, long j);

    public static native void invokePV(int i, float[] fArr, long j);

    public static native void invokePV(int i, int[] iArr, long j);

    public static native void invokePV(int i, int i2, double[] dArr, long j);

    public static native void invokePV(int i, int i2, float[] fArr, long j);

    public static native void invokePV(int i, int i2, int[] iArr, long j);

    public static native void invokePV(int i, int i2, long[] jArr, long j);

    public static native void invokePV(int i, int[] iArr, boolean z, long j);

    public static native void invokePV(int i, int i2, float[] fArr, int i3, int i4, long j);

    public static native void invokePV(int i, int i2, int[] iArr, int i3, int i4, long j);

    public static native void invokePV(int i, int i2, short[] sArr, int i3, int i4, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, double[] dArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, float[] fArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int[] iArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, short[] sArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int i6, double[] dArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int i6, float[] fArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int i6, int[] iArr, long j);

    public static native void invokePV(int i, int i2, int i3, int i4, int i5, int i6, short[] sArr, long j);

    public static native void invokeUPV(byte b2, float[] fArr, long j);

    public static native void invokePJV(int i, int[] iArr, long j, long j2);

    public static native void invokePPV(long j, float[] fArr, int i, long j2);

    public static native void invokePPV(long j, int[] iArr, int i, long j2);

    public static native void invokePPV(long j, short[] sArr, int i, long j2);

    public static native void invokePPV(long j, int i, int i2, int[] iArr, long j2);

    public static native void invokePPV(long j, int i, int i2, long[] jArr, long j2);

    public static native void invokeCCPV(short s, short s2, short[] sArr, long j);

    public static native void invokeCPCV(short s, double[] dArr, short s2, long j);

    public static native void invokeCPCV(short s, float[] fArr, short s2, long j);

    public static native void invokeCPCV(short s, int[] iArr, short s2, long j);

    public static native void invokeCPCV(short s, long[] jArr, short s2, long j);

    public static native void invokeCPCV(short s, short[] sArr, short s2, long j);

    public static native void invokeCPPV(short s, float[] fArr, float[] fArr2, long j);

    public static native void invokePNPV(long j, long j2, short[] sArr, long j3);

    public static native void invokePPPV(long j, double[] dArr, double[] dArr2, long j2);

    public static native void invokePPPV(long j, float[] fArr, float[] fArr2, long j2);

    public static native void invokePPPV(long j, int[] iArr, int[] iArr2, long j2);

    public static native void invokePPPV(int[] iArr, int[] iArr2, int[] iArr3, long j);

    public static native void invokePPPV(int i, float[] fArr, float[] fArr2, float[] fArr3, long j);

    public static native void invokePPPV(int i, int i2, double[] dArr, double[] dArr2, double[] dArr3, long j);

    public static native void invokePPPV(int i, int i2, float[] fArr, float[] fArr2, float[] fArr3, long j);

    public static native void invokePPPV(int i, int i2, long[] jArr, long[] jArr2, long[] jArr3, long j);

    public static native void invokePPPV(float[] fArr, int i, long j, long j2, int i2, long j3);

    public static native void invokePPPV(float[] fArr, boolean z, int i, long j, long j2, int i2, long j3);

    public static native void invokePCPCV(long j, short s, double[] dArr, short s2, long j2);

    public static native void invokePCPCV(long j, short s, float[] fArr, short s2, long j2);

    public static native void invokePCPCV(long j, short s, int[] iArr, short s2, long j2);

    public static native void invokePCPCV(long j, short s, long[] jArr, short s2, long j2);

    public static native void invokePCPCV(long j, short s, short[] sArr, short s2, long j2);

    public static native void invokePNPPV(long j, long j2, long j3, short[] sArr, long j4);

    public static native void invokePPPPPV(long j, long j2, long j3, float[] fArr, long j4, long j5);

    public static native void invokePPPPPV(long j, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long j2);

    public static native void invokePPPPPV(long j, int i, long j2, int[] iArr, long j3, long j4, long j5);

    public static native void invokePPPPPV(int i, long j, int i2, float[] fArr, float[] fArr2, long j2, int i3, long j3, int i4, boolean z, long j4);

    public static native void invokePPPPPV(int i, int[] iArr, int i2, float[] fArr, float[] fArr2, long j, int i3, int[] iArr2, int i4, boolean z, long j2);

    public static native void invokePPPPPV(int i, short[] sArr, int i2, float[] fArr, float[] fArr2, long j, int i3, short[] sArr2, int i4, boolean z, long j2);

    public static native boolean invokePPZ(long j, int[] iArr, long j2);

    public static native boolean invokePPPZ(long j, long j2, int[] iArr, long j3);

    public static native int callPI(int[] iArr, long j);

    public static native int callPI(int i, int[] iArr, long j);

    public static native int callPI(int[] iArr, int i, long j);

    public static native int callPI(int i, int i2, int[] iArr, long j);

    public static native int callPI(int i, int[] iArr, int i2, long j);

    public static native int callPI(int i, int i2, int i3, int[] iArr, long j);

    public static native int callPI(int i, int i2, int i3, int i4, float[] fArr, long j);

    public static native int callPI(int i, int i2, int i3, int i4, int[] iArr, long j);

    public static native int callPPI(long j, int[] iArr, long j2);

    public static native int callPPI(long j, long[] jArr, long j2);

    public static native int callPPI(int[] iArr, long j, long j2);

    public static native int callPPI(int i, long j, int[] iArr, long j2);

    public static native int callPPI(long j, int i, double[] dArr, long j2);

    public static native int callPPI(long j, int i, float[] fArr, long j2);

    public static native int callPPI(long j, int i, int[] iArr, long j2);

    public static native int callPPI(long j, int i, long[] jArr, long j2);

    public static native int callPPI(long j, int i, short[] sArr, long j2);

    public static native int callPPI(long j, int i, int i2, int[] iArr, long j2);

    public static native int callPPI(long j, int i, int i2, long[] jArr, long j2);

    public static native int callPPI(int i, long j, int i2, int i3, float f, int[] iArr, long j2);

    public static native int callPJPI(long j, long j2, int[] iArr, long j3);

    public static native int callPJPI(long j, long j2, long[] jArr, long j3);

    public static native int callPPPI(long j, long j2, int[] iArr, long j3);

    public static native int callPPPI(long j, long j2, long[] jArr, long j3);

    public static native int callPPPI(long j, int[] iArr, long j2, long j3);

    public static native int callPPPI(long j, int[] iArr, int[] iArr2, long j2);

    public static native int callPPPI(long j, long[] jArr, long[] jArr2, long j2);

    public static native int callPPPI(int[] iArr, long j, int[] iArr2, long j2);

    public static native int callPJPI(long j, int i, long j2, int[] iArr, long j3);

    public static native int callPJPI(long j, long j2, int i, long[] jArr, long j3);

    public static native int callPPPI(long j, int i, long j2, double[] dArr, long j3);

    public static native int callPPPI(long j, int i, long j2, float[] fArr, long j3);

    public static native int callPPPI(long j, int i, long j2, int[] iArr, long j3);

    public static native int callPPPI(long j, int i, long j2, long[] jArr, long j3);

    public static native int callPPPI(long j, int i, long j2, short[] sArr, long j3);

    public static native int callPPPI(long j, int i, int[] iArr, int[] iArr2, long j2);

    public static native int callPPPI(long j, int i, int[] iArr, long[] jArr, long j2);

    public static native int callPPPI(long j, long j2, int i, int[] iArr, long j3);

    public static native int callPPPI(long j, long j2, int i, long[] jArr, long j3);

    public static native int callPPPI(long j, long j2, int[] iArr, int i, long j3);

    public static native int callPPJI(long j, int i, long[] jArr, int i2, long j2, long j3);

    public static native int callPPPI(long j, int i, int i2, long j2, int[] iArr, long j3);

    public static native int callPPPI(long j, int i, int i2, int i3, int[] iArr, float[] fArr, long j2);

    public static native int callPPPI(long j, int i, int i2, int i3, int[] iArr, int[] iArr2, long j2);

    public static native int callPJPPI(long j, long j2, long j3, int[] iArr, long j4);

    public static native int callPJPPI(long j, long j2, long j3, long[] jArr, long j4);

    public static native int callPJPPI(long j, long j2, int[] iArr, long j3, long j4);

    public static native int callPJPPI(long j, long j2, int[] iArr, int[] iArr2, long j3);

    public static native int callPJPPI(long j, long j2, int[] iArr, long[] jArr, long j3);

    public static native int callPPNPI(long j, long j2, long j3, long[] jArr, long j4);

    public static native int callPPPPI(long j, long j2, long j3, long[] jArr, long j4);

    public static native int callPPPPI(long j, long j2, int[] iArr, long j3, long j4);

    public static native int callPPPPI(long j, long j2, int[] iArr, int[] iArr2, long j3);

    public static native int callPJPPI(long j, long j2, int i, long j3, int[] iArr, long j4);

    public static native int callPPPPI(long j, int i, long j2, long j3, long[] jArr, long j4);

    public static native int callPPPPI(long j, int i, long j2, int[] iArr, long j3, long j4);

    public static native int callPPPPI(long j, int i, long j2, long[] jArr, long j3, long j4);

    public static native int callPPPPI(long j, int i, long j2, long[] jArr, long[] jArr2, long j3);

    public static native int callPPPPI(long j, int i, int[] iArr, long j2, long j3, long j4);

    public static native int callPPPPI(long j, long j2, int i, long j3, int[] iArr, long j4);

    public static native int callPPPPI(long j, long j2, int i, long[] jArr, long j3, long j4);

    public static native int callPPPPI(long j, long j2, long j3, int i, int[] iArr, long j4);

    public static native int callPPPPI(long j, int[] iArr, long j2, int i, int[] iArr2, long j3);

    public static native int callPPPPI(long j, long[] jArr, int i, long j2, int[] iArr, long j3);

    public static native int callPJPPI(long j, long j2, int i, int i2, long j3, int[] iArr, long j4);

    public static native int callPJPPI(long j, long j2, int i, int i2, int[] iArr, int[] iArr2, long j3);

    public static native int callPPPPI(long j, int i, int i2, long j2, int[] iArr, long j3, long j4);

    public static native int callPPPPI(long j, int i, int i2, long j2, long[] jArr, long j3, long j4);

    public static native int callPPPPI(long j, int i, int i2, long[] jArr, int[] iArr, int[] iArr2, long j2);

    public static native int callPJPPI(long j, long j2, int i, int i2, int i3, long j3, int[] iArr, long j4);

    public static native int callPPPPI(long j, int i, long j2, int i2, int i3, long j3, int[] iArr, long j4);

    public static native int callPPPPI(int i, int[] iArr, long[] jArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int[] iArr2, long[] jArr2, long j);

    public static native int callPJPPPI(long j, long j2, long j3, long j4, long[] jArr, long j5);

    public static native int callPPJPPI(long j, long j2, long j3, int[] iArr, long j4, long j5);

    public static native int callPPPPPI(long j, long j2, int[] iArr, int[] iArr2, int[] iArr3, long j3);

    public static native int callPPPPPI(long j, long j2, int[] iArr, int[] iArr2, long[] jArr, long j3);

    public static native int callPPPPPI(long j, int[] iArr, int[] iArr2, int[] iArr3, long j2, long j3);

    public static native int callPJPPPI(long j, long j2, int i, long j3, long j4, long[] jArr, long j5);

    public static native int callPPPPPI(long j, long j2, int i, long j3, int[] iArr, long j4, long j5);

    public static native int callPPPPPI(long j, long j2, int i, long j3, long[] jArr, long j4, long j5);

    public static native int callPPPPPI(long j, long j2, int i, int[] iArr, int[] iArr2, long j3, long j4);

    public static native int callPPPPPI(long j, long j2, long j3, int[] iArr, int i, int[] iArr2, long j4);

    public static native int callPPPPPI(long j, int[] iArr, float[] fArr, int i, int[] iArr2, int[] iArr3, long j2);

    public static native int callPPPPPI(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long j, long j2);

    public static native int callPPPPPI(long j, int i, long j2, long[] jArr, int i2, long j3, long j4, long j5);

    public static native int callPPPPPI(long j, int i, long[] jArr, int i2, long j2, long j3, long j4, long j5);

    public static native int callPJPPJI(long j, long j2, int i, int i2, long j3, int[] iArr, long j4, int i3, long j5);

    public static native int callPJPPJI(long j, long j2, int i, int i2, long j3, long[] jArr, long j4, int i3, long j5);

    public static native int callPJJJJPI(long j, long j2, long j3, long j4, long j5, int[] iArr, long j6);

    public static native int callPPPPPPI(long j, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, long j2);

    public static native int callPJJPPPI(long j, long j2, long j3, int i, long j4, long j5, long[] jArr, long j6);

    public static native int callPPPPPPI(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, long j, long j2);

    public static native int callPPPPPPPI(long j, long j2, long j3, long j4, int i, long j5, int[] iArr, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, float[] fArr, long j3, long j4, int i, long j5, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, int[] iArr, long j3, long j4, int i, long j5, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, double[] dArr, int i2, long j5, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, float[] fArr, int i2, long j5, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, int[] iArr, int i2, long j5, long j6, long j7);

    public static native int callPPPPPPPI(long j, long j2, int i, long j3, long j4, short[] sArr, int i2, long j5, long j6, long j7);

    public static native int callPPJPPPPPI(long j, long[] jArr, long j2, long j3, long j4, int i, long j5, long j6, long j7, long j8);

    public static native int callPPJPPPPPI(long j, long[] jArr, long j2, long j3, long j4, int i, long j5, int[] iArr, long j6, long j7);

    public static native int callPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, int i, int[] iArr, int[] iArr2, long j7, long j8);

    public static native int callPPPPPPPPPI(long j, long j2, long j3, float[] fArr, long j4, long j5, int i, int[] iArr, int[] iArr2, long j6, long j7);

    public static native int callPPPPPPPPPI(long j, long j2, long j3, int[] iArr, long j4, long j5, int i, int[] iArr2, int[] iArr3, long j6, long j7);

    public static native int callPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, double[] dArr, int i2, long j7, long j8, long j9);

    public static native int callPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, float[] fArr, int i2, long j7, long j8, long j9);

    public static native int callPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, int[] iArr, int i2, long j7, long j8, long j9);

    public static native int callPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, short[] sArr, int i2, long j7, long j8, long j9);

    public static native int callPPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, int i, int[] iArr, int[] iArr2, long j8, long j9);

    public static native int callPPPPPPPPPPI(long j, long j2, long[] jArr, long j3, int i, long j4, long j5, long j6, int i2, int[] iArr, int[] iArr2, long j7, long j8);

    public static native int callPPPPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, double[] dArr, int i2, long j10, long j11, long j12);

    public static native int callPPPPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, float[] fArr, int i2, long j10, long j11, long j12);

    public static native int callPPPPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int[] iArr, int i2, long j10, long j11, long j12);

    public static native int callPPPPPPPPPPPPI(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, long j8, long j9, short[] sArr, int i2, long j10, long j11, long j12);

    public static native int callPPPPPPPPPPPPPPI(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, int i, int[] iArr, int[] iArr2, long j12, long j13);

    public static native long callPP(int[] iArr, long j);

    public static native long callPPP(long j, int[] iArr, long j2);

    public static native long callPPP(int i, long j, int[] iArr, long j2);

    public static native long callPPP(long j, int i, int[] iArr, long j2);

    public static native long callPPP(int i, int i2, int i3, int[] iArr, int[] iArr2, long j);

    public static native long callPPP(long j, int i, int i2, int i3, int[] iArr, long j2);

    public static native long callPPPP(long j, long j2, int[] iArr, long j3);

    public static native long callPPPP(long j, long[] jArr, int[] iArr, long j2);

    public static native long callPJPP(long j, long j2, int i, int[] iArr, long j3);

    public static native long callPPPP(int i, long j, long[] jArr, int[] iArr, long j2);

    public static native long callPPPP(long j, int i, int[] iArr, long j2, long j3);

    public static native long callPPPP(long j, long j2, int i, int[] iArr, long j3);

    public static native long callPPPP(long j, long j2, int i, int i2, int[] iArr, long j3);

    public static native long callPJPP(long j, long j2, int i, int i2, int i3, int[] iArr, long j3);

    public static native long callPPJPP(long j, long j2, long j3, int[] iArr, long j4);

    public static native long callPPNPP(long j, long j2, long j3, int[] iArr, long j4);

    public static native long callPPPPP(long j, long j2, long j3, int[] iArr, long j4);

    public static native long callPPPPP(long j, long j2, long[] jArr, int[] iArr, long j3);

    public static native long callPJPPP(long j, long j2, int i, long j3, int[] iArr, long j4);

    public static native long callPJPPP(long j, long j2, int[] iArr, int i, int[] iArr2, long j3);

    public static native long callPPPPP(long j, int i, long j2, long j3, int[] iArr, long j4);

    public static native long callPPPPP(long j, long j2, int i, long j3, int[] iArr, long j4);

    public static native long callPPPPP(long j, long j2, long j3, int i, int[] iArr, long j4);

    public static native long callPPPPP(long j, long[] jArr, long j2, int i, int[] iArr, long j3);

    public static native long callPJPPP(long j, long j2, int i, int i2, long j3, int[] iArr, long j4);

    public static native long callPJPPPP(long j, long j2, long j3, long j4, int[] iArr, long j5);

    public static native long callPJPPPP(long j, long j2, long j3, double[] dArr, int[] iArr, long j4);

    public static native long callPJPPPP(long j, long j2, long j3, float[] fArr, int[] iArr, long j4);

    public static native long callPJPPPP(long j, long j2, long j3, int[] iArr, int[] iArr2, long j4);

    public static native long callPJPPPP(long j, long j2, long j3, short[] sArr, int[] iArr, long j4);

    public static native long callPPPPPP(long j, int i, long j2, long j3, long j4, int[] iArr, long j5);

    public static native long callPPPPPP(long j, long j2, long[] jArr, long j3, int i, int[] iArr, long j4);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, long j5, int[] iArr, long j6);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, double[] dArr, int[] iArr, long j5);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, float[] fArr, int[] iArr, long j5);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, int[] iArr, int[] iArr2, long j5);

    public static native long callPJJPPPP(long j, long j2, long j3, long j4, short[] sArr, int[] iArr, long j5);

    public static native long callPJPPPPP(long j, long j2, long j3, long j4, long j5, int[] iArr, long j6);

    public static native long callPJPPPPP(long j, long j2, long j3, long j4, float[] fArr, int[] iArr, long j5);

    public static native long callPJPPPPP(long j, long j2, long j3, long j4, int[] iArr, int[] iArr2, long j5);

    public static native long callPJPPPPP(long j, long j2, long j3, long j4, short[] sArr, int[] iArr, long j5);

    public static native long callPPJPPPP(long j, long[] jArr, long j2, long j3, long j4, int[] iArr, long j5);

    public static native long callPPJPPPP(long j, long[] jArr, long j2, long j3, double[] dArr, int[] iArr, long j4);

    public static native long callPPJPPPP(long j, long[] jArr, long j2, long j3, float[] fArr, int[] iArr, long j4);

    public static native long callPPJPPPP(long j, long[] jArr, long j2, long j3, int[] iArr, int[] iArr2, long j4);

    public static native long callPPJPPPP(long j, long[] jArr, long j2, long j3, short[] sArr, int[] iArr, long j4);

    public static native long callPPPJPPP(long j, long j2, long j3, long j4, long j5, int[] iArr, long j6);

    public static native long callPPPPPPP(long j, int i, long j2, long j3, long j4, int[] iArr, int[] iArr2, long j5);

    public static native long callPPJPPPPP(long j, long[] jArr, long j2, long j3, long j4, long j5, int[] iArr, long j6);

    public static native long callPPJPPPPP(long j, long[] jArr, long j2, long j3, long j4, float[] fArr, int[] iArr, long j5);

    public static native long callPPJPPPPP(long j, long[] jArr, long j2, long j3, long j4, int[] iArr, int[] iArr2, long j5);

    public static native long callPPJPPPPP(long j, long[] jArr, long j2, long j3, long j4, short[] sArr, int[] iArr, long j5);

    public static native long callPPPPPPPP(long j, int i, long j2, long j3, int i2, long j4, long j5, long j6, int[] iArr, long j7);

    public static native long callPPPPPPPP(int i, int[] iArr, long[] jArr, int i2, int i3, int i4, int i5, long j, long j2, long j3, int i6, int[] iArr2, long[] jArr2, long j4);

    public static native long callPJPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, int[] iArr, long j8);

    public static native long callPJPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, float[] fArr, int[] iArr, long j7);

    public static native long callPJPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, int[] iArr, int[] iArr2, long j7);

    public static native long callPJPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, short[] sArr, int[] iArr, long j7);

    public static native long callPPJPPPPPP(long j, long j2, int i, long j3, long j4, long j5, int i2, long j6, long j7, int[] iArr, long j8);

    public static native long callPJPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, int[] iArr, long j10);

    public static native long callPJPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, float[] fArr, int[] iArr, long j9);

    public static native long callPJPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int[] iArr, int[] iArr2, long j9);

    public static native long callPJPPPPPPPPP(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, short[] sArr, int[] iArr, long j9);

    public static native long callPPJPPPPPPPP(long j, long j2, int i, long j3, long j4, long j5, long j6, long j7, int i2, long j8, long j9, int[] iArr, long j10);

    public static native void callPV(double[] dArr, long j);

    public static native void callPV(float[] fArr, long j);

    public static native void callPV(int[] iArr, long j);

    public static native void callPV(short[] sArr, long j);

    public static native void callPV(int i, double[] dArr, long j);

    public static native void callPV(int i, float[] fArr, long j);

    public static native void callPV(int i, int[] iArr, long j);

    public static native void callPV(int i, long[] jArr, long j);

    public static native void callPV(int i, short[] sArr, long j);

    public static native void callPV(int i, int i2, double[] dArr, long j);

    public static native void callPV(int i, int i2, float[] fArr, long j);

    public static native void callPV(int i, int i2, int[] iArr, long j);

    public static native void callPV(int i, int i2, long[] jArr, long j);

    public static native void callPV(int i, int i2, short[] sArr, long j);

    public static native void callPV(int i, int[] iArr, int i2, long j);

    public static native void callPV(int i, int i2, int i3, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, long[] jArr, long j);

    public static native void callPV(int i, int i2, int i3, short[] sArr, long j);

    public static native void callPV(int i, int i2, boolean z, double[] dArr, long j);

    public static native void callPV(int i, int i2, boolean z, float[] fArr, long j);

    public static native void callPV(int i, int i2, boolean z, int[] iArr, long j);

    public static native void callPV(int i, int i2, int[] iArr, int i3, long j);

    public static native void callPV(int i, int[] iArr, int i2, int i3, long j);

    public static native void callPV(int i, int i2, int i3, int i4, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, long[] jArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, boolean z, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, boolean z, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int[] iArr, boolean z, long j);

    public static native void callPV(int i, int i2, int[] iArr, int i3, int i4, long j);

    public static native void callPV(int i, boolean z, int i2, int i3, int[] iArr, long j);

    public static native void callPV(int i, double d, double d2, int i2, int i3, double[] dArr, long j);

    public static native void callPV(int i, float f, float f2, int i2, int i3, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int[] iArr, boolean z, long j);

    public static native void callPV(int i, int i2, int i3, boolean z, int i4, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, boolean z, int i4, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, boolean z, int i4, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, short[] sArr, long j);

    public static native void callPV(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, short[] sArr, long j);

    public static native void callPV(int i, double d, double d2, int i2, int i3, double d3, double d4, int i4, int i5, double[] dArr, long j);

    public static native void callPV(int i, float f, float f2, int i2, int i3, float f3, float f4, int i4, int i5, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, short[] sArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, double[] dArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, float[] fArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr, long j);

    public static native void callPV(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, short[] sArr, long j);

    public static native void callPPV(long j, float[] fArr, long j2);

    public static native void callPPV(long j, int[] iArr, long j2);

    public static native void callPPV(double[] dArr, double[] dArr2, long j);

    public static native void callPPV(float[] fArr, float[] fArr2, long j);

    public static native void callPPV(int[] iArr, int[] iArr2, long j);

    public static native void callPPV(short[] sArr, short[] sArr2, long j);

    public static native void callPPV(int i, long j, int[] iArr, long j2);

    public static native void callPPV(int i, int[] iArr, float[] fArr, long j);

    public static native void callPPV(int i, int[] iArr, int[] iArr2, long j);

    public static native void callPPV(int i, int[] iArr, long[] jArr, long j);

    public static native void callPPV(long j, int i, float[] fArr, long j2);

    public static native void callPPV(long j, int i, int[] iArr, long j2);

    public static native void callPPV(int[] iArr, int i, int[] iArr2, long j);

    public static native void callPPV(int i, int i2, long j, int[] iArr, long j2);

    public static native void callPPV(int i, int i2, int[] iArr, long j, long j2);

    public static native void callPPV(int i, int i2, int[] iArr, float[] fArr, long j);

    public static native void callPPV(int i, int i2, int[] iArr, int[] iArr2, long j);

    public static native void callPPV(int i, int i2, int[] iArr, long[] jArr, long j);

    public static native void callPPV(int i, long j, int i2, int[] iArr, long j2);

    public static native void callPPV(int i, long j, double[] dArr, int i2, long j2);

    public static native void callPPV(int i, long j, float[] fArr, int i2, long j2);

    public static native void callPPV(int i, long j, int[] iArr, int i2, long j2);

    public static native void callPPV(int i, long j, long[] jArr, int i2, long j2);

    public static native void callPPV(int i, long j, short[] sArr, int i2, long j2);

    public static native void callPPV(int i, int[] iArr, int[] iArr2, int i2, long j);

    public static native void callPPV(int i, long[] jArr, int[] iArr, int i2, long j);

    public static native void callPPV(long j, int i, int i2, int[] iArr, long j2);

    public static native void callPPV(int i, int i2, int i3, long j, int[] iArr, long j2);

    public static native void callPPV(int i, int i2, int i3, int[] iArr, long j, long j2);

    public static native void callPPV(int i, int i2, int i3, int[] iArr, int[] iArr2, long j);

    public static native void callPPV(int i, int i2, long j, int[] iArr, int i3, long j2);

    public static native void callPPV(int i, int i2, int[] iArr, int i3, int[] iArr2, long j);

    public static native void callPPV(int i, int[] iArr, int i2, long j, int i3, long j2);

    public static native void callPPV(int i, int[] iArr, int i2, int[] iArr2, int i3, long j);

    public static native void callPPV(int i, int[] iArr, long j, int i2, int i3, long j2);

    public static native void callPPV(long j, int i, int i2, int i3, int[] iArr, long j2);

    public static native void callPPV(int i, int i2, int i3, int i4, int[] iArr, long j, long j2);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, short[] sArr, long j2);

    public static native void callPPV(int i, int i2, int[] iArr, long j, int i3, int i4, long j2);

    public static native void callPPV(int i, int i2, int i3, long j, int i4, int i5, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, int i3, int i4, long j, int i5, int i6, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, int i3, int i4, long j, int i5, int i6, short[] sArr, long j2);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, int i6, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, int i3, long j, int i4, float f, float f2, int i5, float[] fArr, long j2);

    public static native void callPPV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, float[] fArr, long j2);

    public static native void callPJPV(long j, long j2, long[] jArr, long j3);

    public static native void callPPPV(long j, long j2, int[] iArr, long j3);

    public static native void callPPPV(long j, int[] iArr, long j2, long j3);

    public static native void callPJPV(long j, long j2, int i, long[] jArr, long j3);

    public static native void callPPPV(int i, long j, long j2, double[] dArr, long j3);

    public static native void callPPPV(int i, long j, long j2, float[] fArr, long j3);

    public static native void callPPPV(int i, long j, long j2, int[] iArr, long j3);

    public static native void callPPPV(int i, long j, long j2, long[] jArr, long j3);

    public static native void callPPPV(int i, long j, long j2, short[] sArr, long j3);

    public static native void callPPPV(long j, int i, int[] iArr, long j2, long j3);

    public static native void callPPPV(long j, int i, int[] iArr, long[] jArr, long j2);

    public static native void callPPPV(long j, int i, long[] jArr, long j2, long j3);

    public static native void callPPPV(long j, long j2, int i, int[] iArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, double[] dArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, float[] fArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, int[] iArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, short[] sArr, long j3);

    public static native void callPPPV(int i, int i2, int[] iArr, long j, int[] iArr2, long j2);

    public static native void callPPPV(int i, int i2, int[] iArr, int[] iArr2, long j, long j2);

    public static native void callPPPV(int i, long j, int i2, int[] iArr, long j2, long j3);

    public static native void callPPPV(int i, long j, int i2, int[] iArr, int[] iArr2, long j2);

    public static native void callPPPV(int i, int[] iArr, int[] iArr2, int i2, int[] iArr3, long j);

    public static native void callPPPV(long j, int i, int i2, int[] iArr, int[] iArr2, long j2);

    public static native void callPPPV(long j, int i, int i2, long[] jArr, long[] jArr2, long j2);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, double[] dArr, long j3);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, float[] fArr, long j3);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, int[] iArr, long j3);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, long[] jArr, long j3);

    public static native void callPJPV(long j, long j2, int i, int i2, int i3, short[] sArr, long j3);

    public static native void callPPJV(long j, int i, long[] jArr, int i2, long j2, int i3, long j3);

    public static native void callPPPV(int i, int i2, int i3, int[] iArr, long j, long j2, long j3);

    public static native void callPPPV(int i, int i2, int i3, int[] iArr, long j, int[] iArr2, long j2);

    public static native void callPPPV(int i, int i2, int[] iArr, int i3, int[] iArr2, int[] iArr3, long j);

    public static native void callPPPV(int i, int[] iArr, int i2, long j, int i3, int[] iArr2, long j2);

    public static native void callPPPV(int i, int i2, long j, long j2, int i3, int i4, float[] fArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, int i3, int i4, int[] iArr, long j3);

    public static native void callPPPV(int i, int i2, long j, long j2, int i3, int i4, short[] sArr, long j3);

    public static native void callPPPV(long j, int i, long j2, int i2, int i3, int i4, int[] iArr, long j3);

    public static native void callPPPV(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2, float[] fArr, long j);

    public static native void callPPPV(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2, int[] iArr3, long j);

    public static native void callPPPV(long j, int i, int i2, int i3, int i4, int i5, int[] iArr, long j2, long j3);

    public static native void callPJPPV(long j, long j2, int[] iArr, long j3, long j4);

    public static native void callPPPPV(long j, long j2, int[] iArr, long j3, long j4);

    public static native void callPJJPV(long j, int i, long j2, long j3, long[] jArr, long j4);

    public static native void callPPPPV(long j, int i, long j2, int[] iArr, long j3, long j4);

    public static native void callPPPPV(long[] jArr, int[] iArr, int[] iArr2, int[] iArr3, int i, long j);

    public static native void callPPPPV(int i, long j, int[] iArr, int[] iArr2, int[] iArr3, int i2, long j2);

    public static native void callPPPPV(long j, int i, int i2, long[] jArr, long[] jArr2, long[] jArr3, long j2);

    public static native void callPJPPV(long j, int i, long j2, int i2, int i3, int[] iArr, long[] jArr, long j3);

    public static native void callPPPPV(int i, int i2, int i3, int[] iArr, int[] iArr2, int[] iArr3, long j, long j2);

    public static native void callPPPPV(int i, int i2, long j, int[] iArr, int[] iArr2, int[] iArr3, int i3, long j2);

    public static native void callPJPPV(long j, int i, long j2, int i2, int i3, long[] jArr, int i4, int[] iArr, long j3);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, double[] dArr, long j5);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, float[] fArr, long j5);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, int[] iArr, long j5);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, long[] jArr, long j5);

    public static native void callPJJJPV(long j, long j2, long j3, long j4, short[] sArr, long j5);

    public static native void callPPPPPV(long j, int i, long j2, long[] jArr, int[] iArr, long j3, long j4);

    public static native void callPPPPPV(int i, int i2, long j, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long j2);

    public static native void callPPPPPV(long j, int i, int i2, long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long j2);

    public static native void callPPPPPV(long j, int i, long[] jArr, int i2, int i3, int i4, long j2, int i5, long j3, int i6, long j4, long j5);

    public static native void callPPPPPPPV(int i, int i2, int i3, long j, int i4, long j2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, long[] jArr, long j3);

    public static native boolean callPPZ(int i, int[] iArr, long j, long j2);

    public static native boolean callPPPPZ(int i, int i2, int i3, float f, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, long j);

    static {
        Library.initialize();
    }

    private JNI() {
    }
}

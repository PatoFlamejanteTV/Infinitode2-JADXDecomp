package b.a.a;

import com.badlogic.gdx.net.HttpStatus;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.linux.FCNTL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:b/a/a/n.class */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    private float[] f33a;

    /* renamed from: b, reason: collision with root package name */
    private float[] f34b;
    private float[] c;
    private int d;
    private float[] e;
    private int f;
    private float g;
    private float[] h = new float[32];
    private static final float i = (float) (1.0d / (2.0d * Math.cos(0.04908738521234052d)));
    private static final float j = (float) (1.0d / (2.0d * Math.cos(0.14726215563702155d)));
    private static final float k = (float) (1.0d / (2.0d * Math.cos(0.2454369260617026d)));
    private static final float l = (float) (1.0d / (2.0d * Math.cos(0.3436116964863836d)));
    private static final float m = (float) (1.0d / (2.0d * Math.cos(0.44178646691106466d)));
    private static final float n = (float) (1.0d / (2.0d * Math.cos(0.5399612373357456d)));
    private static final float o = (float) (1.0d / (2.0d * Math.cos(0.6381360077604268d)));
    private static final float p = (float) (1.0d / (2.0d * Math.cos(0.7363107781851077d)));
    private static final float q = (float) (1.0d / (2.0d * Math.cos(0.8344855486097889d)));
    private static final float r = (float) (1.0d / (2.0d * Math.cos(0.9326603190344698d)));
    private static final float s = (float) (1.0d / (2.0d * Math.cos(1.030835089459151d)));
    private static final float t = (float) (1.0d / (2.0d * Math.cos(1.1290098598838318d)));
    private static final float u = (float) (1.0d / (2.0d * Math.cos(1.227184630308513d)));
    private static final float v = (float) (1.0d / (2.0d * Math.cos(1.325359400733194d)));
    private static final float w = (float) (1.0d / (2.0d * Math.cos(1.423534171157875d)));
    private static final float x = (float) (1.0d / (2.0d * Math.cos(1.521708941582556d)));
    private static final float y = (float) (1.0d / (2.0d * Math.cos(0.09817477042468103d)));
    private static final float z = (float) (1.0d / (2.0d * Math.cos(0.2945243112740431d)));
    private static final float A = (float) (1.0d / (2.0d * Math.cos(0.4908738521234052d)));
    private static final float B = (float) (1.0d / (2.0d * Math.cos(0.6872233929727672d)));
    private static final float C = (float) (1.0d / (2.0d * Math.cos(0.8835729338221293d)));
    private static final float D = (float) (1.0d / (2.0d * Math.cos(1.0799224746714913d)));
    private static final float E = (float) (1.0d / (2.0d * Math.cos(1.2762720155208536d)));
    private static final float F = (float) (1.0d / (2.0d * Math.cos(1.4726215563702154d)));
    private static final float G = (float) (1.0d / (2.0d * Math.cos(0.19634954084936207d)));
    private static final float H = (float) (1.0d / (2.0d * Math.cos(0.5890486225480862d)));
    private static final float I = (float) (1.0d / (2.0d * Math.cos(0.9817477042468103d)));
    private static final float J = (float) (1.0d / (2.0d * Math.cos(1.3744467859455345d)));
    private static final float K = (float) (1.0d / (2.0d * Math.cos(0.39269908169872414d)));
    private static final float L = (float) (1.0d / (2.0d * Math.cos(1.1780972450961724d)));
    private static final float M = (float) (1.0d / (2.0d * Math.cos(0.7853981633974483d)));
    private static float[] N = null;
    private static float[][] O = (float[][]) null;

    public n(int i2, float f) {
        if (N == null) {
            float[] s2 = s();
            N = s2;
            O = a(s2, 16);
        }
        this.f33a = new float[512];
        this.f34b = new float[512];
        this.e = new float[32];
        this.f = i2;
        this.g = 32700.0f;
        a();
    }

    private void a() {
        for (int i2 = 0; i2 < 512; i2++) {
            this.f34b[i2] = 0.0f;
            this.f33a[i2] = 0.0f;
        }
        for (int i3 = 0; i3 < 32; i3++) {
            this.e[i3] = 0.0f;
        }
        this.c = this.f33a;
        this.d = 15;
    }

    public final void a(float f, int i2) {
        this.e[i2] = f;
    }

    public final void a(float[] fArr) {
        for (int i2 = 31; i2 >= 0; i2--) {
            this.e[i2] = fArr[i2];
        }
    }

    private void b() {
        float[] fArr = this.e;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[4];
        float f6 = fArr[5];
        float f7 = fArr[6];
        float f8 = fArr[7];
        float f9 = fArr[8];
        float f10 = fArr[9];
        float f11 = fArr[10];
        float f12 = fArr[11];
        float f13 = fArr[12];
        float f14 = fArr[13];
        float f15 = fArr[14];
        float f16 = fArr[15];
        float f17 = fArr[16];
        float f18 = fArr[17];
        float f19 = fArr[18];
        float f20 = fArr[19];
        float f21 = fArr[20];
        float f22 = fArr[21];
        float f23 = fArr[22];
        float f24 = fArr[23];
        float f25 = fArr[24];
        float f26 = fArr[25];
        float f27 = fArr[26];
        float f28 = fArr[27];
        float f29 = fArr[28];
        float f30 = fArr[29];
        float f31 = fArr[30];
        float f32 = fArr[31];
        float f33 = f + f32;
        float f34 = f2 + f31;
        float f35 = f3 + f30;
        float f36 = f4 + f29;
        float f37 = f5 + f28;
        float f38 = f6 + f27;
        float f39 = f7 + f26;
        float f40 = f8 + f25;
        float f41 = f9 + f24;
        float f42 = f10 + f23;
        float f43 = f11 + f22;
        float f44 = f12 + f21;
        float f45 = f13 + f20;
        float f46 = f14 + f19;
        float f47 = f15 + f18;
        float f48 = f16 + f17;
        float f49 = f33 + f48;
        float f50 = f34 + f47;
        float f51 = f35 + f46;
        float f52 = f36 + f45;
        float f53 = f37 + f44;
        float f54 = f38 + f43;
        float f55 = f39 + f42;
        float f56 = f40 + f41;
        float f57 = (f33 - f48) * y;
        float f58 = (f34 - f47) * z;
        float f59 = (f35 - f46) * A;
        float f60 = (f36 - f45) * B;
        float f61 = (f37 - f44) * C;
        float f62 = (f38 - f43) * D;
        float f63 = (f39 - f42) * E;
        float f64 = (f40 - f41) * F;
        float f65 = f49 + f56;
        float f66 = f50 + f55;
        float f67 = f51 + f54;
        float f68 = f52 + f53;
        float f69 = (f49 - f56) * G;
        float f70 = (f50 - f55) * H;
        float f71 = (f51 - f54) * I;
        float f72 = (f52 - f53) * J;
        float f73 = f57 + f64;
        float f74 = f58 + f63;
        float f75 = f59 + f62;
        float f76 = f60 + f61;
        float f77 = (f57 - f64) * G;
        float f78 = (f58 - f63) * H;
        float f79 = (f59 - f62) * I;
        float f80 = (f60 - f61) * J;
        float f81 = f65 + f68;
        float f82 = f66 + f67;
        float f83 = (f65 - f68) * K;
        float f84 = (f66 - f67) * L;
        float f85 = f69 + f72;
        float f86 = f70 + f71;
        float f87 = (f69 - f72) * K;
        float f88 = (f70 - f71) * L;
        float f89 = f73 + f76;
        float f90 = f74 + f75;
        float f91 = (f73 - f76) * K;
        float f92 = (f74 - f75) * L;
        float f93 = f77 + f80;
        float f94 = f78 + f79;
        float f95 = (f77 - f80) * K;
        float f96 = (f78 - f79) * L;
        float f97 = f81 + f82;
        float f98 = (f81 - f82) * M;
        float f99 = f83 + f84;
        float f100 = (f83 - f84) * M;
        float f101 = f85 + f86;
        float f102 = (f85 - f86) * M;
        float f103 = f87 + f88;
        float f104 = (f87 - f88) * M;
        float f105 = f89 + f90;
        float f106 = (f89 - f90) * M;
        float f107 = f91 + f92;
        float f108 = (f91 - f92) * M;
        float f109 = f93 + f94;
        float f110 = (f93 - f94) * M;
        float f111 = f95 + f96;
        float f112 = (f95 - f96) * M;
        float f113 = f104 + f102;
        float f114 = (-f113) - f103;
        float f115 = ((-f103) - f104) - f101;
        float f116 = f112 + f108;
        float f117 = f116 + f110;
        float f118 = f112 + f110 + f106;
        float f119 = (-f118) - f111;
        float f120 = (((-f111) - f112) - f107) - f108;
        float f121 = f120 - f110;
        float f122 = (((-f111) - f112) - f109) - f105;
        float f123 = f120 - f109;
        float f124 = -f97;
        float f125 = (-f100) - f99;
        float f126 = (f - f32) * i;
        float f127 = (f2 - f31) * j;
        float f128 = (f3 - f30) * k;
        float f129 = (f4 - f29) * l;
        float f130 = (f5 - f28) * m;
        float f131 = (f6 - f27) * n;
        float f132 = (f7 - f26) * o;
        float f133 = (f8 - f25) * p;
        float f134 = (f9 - f24) * q;
        float f135 = (f10 - f23) * r;
        float f136 = (f11 - f22) * s;
        float f137 = (f12 - f21) * t;
        float f138 = (f13 - f20) * u;
        float f139 = (f14 - f19) * v;
        float f140 = (f15 - f18) * w;
        float f141 = (f16 - f17) * x;
        float f142 = f126 + f141;
        float f143 = f127 + f140;
        float f144 = f128 + f139;
        float f145 = f129 + f138;
        float f146 = f130 + f137;
        float f147 = f131 + f136;
        float f148 = f132 + f135;
        float f149 = f133 + f134;
        float f150 = (f126 - f141) * y;
        float f151 = (f127 - f140) * z;
        float f152 = (f128 - f139) * A;
        float f153 = (f129 - f138) * B;
        float f154 = (f130 - f137) * C;
        float f155 = (f131 - f136) * D;
        float f156 = (f132 - f135) * E;
        float f157 = (f133 - f134) * F;
        float f158 = f142 + f149;
        float f159 = f143 + f148;
        float f160 = f144 + f147;
        float f161 = f145 + f146;
        float f162 = (f142 - f149) * G;
        float f163 = (f143 - f148) * H;
        float f164 = (f144 - f147) * I;
        float f165 = (f145 - f146) * J;
        float f166 = f150 + f157;
        float f167 = f151 + f156;
        float f168 = f152 + f155;
        float f169 = f153 + f154;
        float f170 = (f150 - f157) * G;
        float f171 = (f151 - f156) * H;
        float f172 = (f152 - f155) * I;
        float f173 = (f153 - f154) * J;
        float f174 = f158 + f161;
        float f175 = f159 + f160;
        float f176 = (f158 - f161) * K;
        float f177 = (f159 - f160) * L;
        float f178 = f162 + f165;
        float f179 = f163 + f164;
        float f180 = (f162 - f165) * K;
        float f181 = (f163 - f164) * L;
        float f182 = f166 + f169;
        float f183 = f167 + f168;
        float f184 = (f166 - f169) * K;
        float f185 = (f167 - f168) * L;
        float f186 = f170 + f173;
        float f187 = f171 + f172;
        float f188 = (f170 - f173) * K;
        float f189 = (f171 - f172) * L;
        float f190 = f174 + f175;
        float f191 = (f174 - f175) * M;
        float f192 = f176 + f177;
        float f193 = (f176 - f177) * M;
        float f194 = f178 + f179;
        float f195 = (f178 - f179) * M;
        float f196 = f180 + f181;
        float f197 = (f180 - f181) * M;
        float f198 = f182 + f183;
        float f199 = (f182 - f183) * M;
        float f200 = f184 + f185;
        float f201 = (f184 - f185) * M;
        float f202 = f186 + f187;
        float f203 = (f186 - f187) * M;
        float f204 = f188 + f189;
        float f205 = (f188 - f189) * M;
        float f206 = f205 + f197;
        float f207 = f206 + f201;
        float f208 = f207 + f195 + f203;
        float f209 = f205 + f201 + f193;
        float f210 = f209 + f203;
        float f211 = f203 + f205 + f199;
        float f212 = f211 + f191;
        float f213 = (-f212) - f204;
        float f214 = f211 + f195 + f197;
        float f215 = ((-f214) - f196) - f204;
        float f216 = (((-f200) - f201) - f204) - f205;
        float f217 = ((f216 - f203) - f192) - f193;
        float f218 = (((f216 - f203) - f195) - f196) - f197;
        float f219 = ((f216 - f202) - f192) - f193;
        float f220 = f194 + f196 + f197;
        float f221 = (f216 - f202) - f220;
        float f222 = (((-f198) - f202) - f204) - f205;
        float f223 = f222 - f190;
        float f224 = f222 - f220;
        float[] fArr2 = this.c;
        int i2 = this.d;
        fArr2[i2 + 0] = f98;
        fArr2[i2 + 16] = f212;
        fArr2[i2 + 32] = f118;
        fArr2[i2 + 48] = f214;
        fArr2[i2 + 64] = f113;
        fArr2[i2 + 80] = f208;
        fArr2[i2 + 96] = f117;
        fArr2[i2 + 112] = f210;
        fArr2[i2 + 128] = f100;
        fArr2[i2 + 144] = f209;
        fArr2[i2 + 160] = f116;
        fArr2[i2 + 176] = f207;
        fArr2[i2 + 192] = f104;
        fArr2[i2 + 208] = f206;
        fArr2[i2 + CGL.kCGLCPDispatchTableSize] = f112;
        fArr2[i2 + User32.VK_OEM_ATTN] = f205;
        fArr2[i2 + 256] = 0.0f;
        fArr2[i2 + 272] = -f205;
        fArr2[i2 + User32.WM_MENUCHAR] = -f112;
        fArr2[i2 + 304] = -f206;
        fArr2[i2 + GLFW.GLFW_KEY_KP_0] = -f104;
        fArr2[i2 + GLFW.GLFW_KEY_KP_EQUAL] = -f207;
        fArr2[i2 + MapEditorItemInfoMenu.MENU_CONTENT_WIDTH] = -f116;
        fArr2[i2 + 368] = -f209;
        fArr2[i2 + 384] = -f100;
        fArr2[i2 + 400] = -f210;
        fArr2[i2 + HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE] = -f117;
        fArr2[i2 + 432] = -f208;
        fArr2[i2 + FCNTL.S_IRWXU] = -f113;
        fArr2[i2 + 464] = -f214;
        fArr2[i2 + 480] = -f118;
        fArr2[i2 + 496] = -f212;
        float[] fArr3 = this.c == this.f33a ? this.f34b : this.f33a;
        float[] fArr4 = fArr3;
        fArr3[i2 + 0] = -f98;
        fArr4[i2 + 16] = f213;
        fArr4[i2 + 32] = f119;
        fArr4[i2 + 48] = f215;
        fArr4[i2 + 64] = f114;
        fArr4[i2 + 80] = f218;
        fArr4[i2 + 96] = f121;
        fArr4[i2 + 112] = f217;
        fArr4[i2 + 128] = f125;
        fArr4[i2 + 144] = f219;
        fArr4[i2 + 160] = f123;
        fArr4[i2 + 176] = f221;
        fArr4[i2 + 192] = f115;
        fArr4[i2 + 208] = f224;
        fArr4[i2 + CGL.kCGLCPDispatchTableSize] = f122;
        fArr4[i2 + User32.VK_OEM_ATTN] = f223;
        fArr4[i2 + 256] = f124;
        fArr4[i2 + 272] = f223;
        fArr4[i2 + User32.WM_MENUCHAR] = f122;
        fArr4[i2 + 304] = f224;
        fArr4[i2 + GLFW.GLFW_KEY_KP_0] = f115;
        fArr4[i2 + GLFW.GLFW_KEY_KP_EQUAL] = f221;
        fArr4[i2 + MapEditorItemInfoMenu.MENU_CONTENT_WIDTH] = f123;
        fArr4[i2 + 368] = f219;
        fArr4[i2 + 384] = f125;
        fArr4[i2 + 400] = f217;
        fArr4[i2 + HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE] = f121;
        fArr4[i2 + 432] = f218;
        fArr4[i2 + FCNTL.S_IRWXU] = f114;
        fArr4[i2 + 464] = f215;
        fArr4[i2 + 480] = f119;
        fArr4[i2 + 496] = f213;
    }

    private void c() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 0] * fArr3[0]) + (fArr[i2 + 15] * fArr3[1]) + (fArr[i2 + 14] * fArr3[2]) + (fArr[i2 + 13] * fArr3[3]) + (fArr[i2 + 12] * fArr3[4]) + (fArr[i2 + 11] * fArr3[5]) + (fArr[i2 + 10] * fArr3[6]) + (fArr[i2 + 9] * fArr3[7]) + (fArr[i2 + 8] * fArr3[8]) + (fArr[i2 + 7] * fArr3[9]) + (fArr[i2 + 6] * fArr3[10]) + (fArr[i2 + 5] * fArr3[11]) + (fArr[i2 + 4] * fArr3[12]) + (fArr[i2 + 3] * fArr3[13]) + (fArr[i2 + 2] * fArr3[14]) + (fArr[i2 + 1] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void d() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 1] * fArr3[0]) + (fArr[i2 + 0] * fArr3[1]) + (fArr[i2 + 15] * fArr3[2]) + (fArr[i2 + 14] * fArr3[3]) + (fArr[i2 + 13] * fArr3[4]) + (fArr[i2 + 12] * fArr3[5]) + (fArr[i2 + 11] * fArr3[6]) + (fArr[i2 + 10] * fArr3[7]) + (fArr[i2 + 9] * fArr3[8]) + (fArr[i2 + 8] * fArr3[9]) + (fArr[i2 + 7] * fArr3[10]) + (fArr[i2 + 6] * fArr3[11]) + (fArr[i2 + 5] * fArr3[12]) + (fArr[i2 + 4] * fArr3[13]) + (fArr[i2 + 3] * fArr3[14]) + (fArr[i2 + 2] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void e() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 2] * fArr3[0]) + (fArr[i2 + 1] * fArr3[1]) + (fArr[i2 + 0] * fArr3[2]) + (fArr[i2 + 15] * fArr3[3]) + (fArr[i2 + 14] * fArr3[4]) + (fArr[i2 + 13] * fArr3[5]) + (fArr[i2 + 12] * fArr3[6]) + (fArr[i2 + 11] * fArr3[7]) + (fArr[i2 + 10] * fArr3[8]) + (fArr[i2 + 9] * fArr3[9]) + (fArr[i2 + 8] * fArr3[10]) + (fArr[i2 + 7] * fArr3[11]) + (fArr[i2 + 6] * fArr3[12]) + (fArr[i2 + 5] * fArr3[13]) + (fArr[i2 + 4] * fArr3[14]) + (fArr[i2 + 3] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void f() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 3] * fArr3[0]) + (fArr[i2 + 2] * fArr3[1]) + (fArr[i2 + 1] * fArr3[2]) + (fArr[i2 + 0] * fArr3[3]) + (fArr[i2 + 15] * fArr3[4]) + (fArr[i2 + 14] * fArr3[5]) + (fArr[i2 + 13] * fArr3[6]) + (fArr[i2 + 12] * fArr3[7]) + (fArr[i2 + 11] * fArr3[8]) + (fArr[i2 + 10] * fArr3[9]) + (fArr[i2 + 9] * fArr3[10]) + (fArr[i2 + 8] * fArr3[11]) + (fArr[i2 + 7] * fArr3[12]) + (fArr[i2 + 6] * fArr3[13]) + (fArr[i2 + 5] * fArr3[14]) + (fArr[i2 + 4] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void g() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 4] * fArr3[0]) + (fArr[i2 + 3] * fArr3[1]) + (fArr[i2 + 2] * fArr3[2]) + (fArr[i2 + 1] * fArr3[3]) + (fArr[i2 + 0] * fArr3[4]) + (fArr[i2 + 15] * fArr3[5]) + (fArr[i2 + 14] * fArr3[6]) + (fArr[i2 + 13] * fArr3[7]) + (fArr[i2 + 12] * fArr3[8]) + (fArr[i2 + 11] * fArr3[9]) + (fArr[i2 + 10] * fArr3[10]) + (fArr[i2 + 9] * fArr3[11]) + (fArr[i2 + 8] * fArr3[12]) + (fArr[i2 + 7] * fArr3[13]) + (fArr[i2 + 6] * fArr3[14]) + (fArr[i2 + 5] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void h() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 5] * fArr3[0]) + (fArr[i2 + 4] * fArr3[1]) + (fArr[i2 + 3] * fArr3[2]) + (fArr[i2 + 2] * fArr3[3]) + (fArr[i2 + 1] * fArr3[4]) + (fArr[i2 + 0] * fArr3[5]) + (fArr[i2 + 15] * fArr3[6]) + (fArr[i2 + 14] * fArr3[7]) + (fArr[i2 + 13] * fArr3[8]) + (fArr[i2 + 12] * fArr3[9]) + (fArr[i2 + 11] * fArr3[10]) + (fArr[i2 + 10] * fArr3[11]) + (fArr[i2 + 9] * fArr3[12]) + (fArr[i2 + 8] * fArr3[13]) + (fArr[i2 + 7] * fArr3[14]) + (fArr[i2 + 6] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void i() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 6] * fArr3[0]) + (fArr[i2 + 5] * fArr3[1]) + (fArr[i2 + 4] * fArr3[2]) + (fArr[i2 + 3] * fArr3[3]) + (fArr[i2 + 2] * fArr3[4]) + (fArr[i2 + 1] * fArr3[5]) + (fArr[i2 + 0] * fArr3[6]) + (fArr[i2 + 15] * fArr3[7]) + (fArr[i2 + 14] * fArr3[8]) + (fArr[i2 + 13] * fArr3[9]) + (fArr[i2 + 12] * fArr3[10]) + (fArr[i2 + 11] * fArr3[11]) + (fArr[i2 + 10] * fArr3[12]) + (fArr[i2 + 9] * fArr3[13]) + (fArr[i2 + 8] * fArr3[14]) + (fArr[i2 + 7] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void j() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 7] * fArr3[0]) + (fArr[i2 + 6] * fArr3[1]) + (fArr[i2 + 5] * fArr3[2]) + (fArr[i2 + 4] * fArr3[3]) + (fArr[i2 + 3] * fArr3[4]) + (fArr[i2 + 2] * fArr3[5]) + (fArr[i2 + 1] * fArr3[6]) + (fArr[i2 + 0] * fArr3[7]) + (fArr[i2 + 15] * fArr3[8]) + (fArr[i2 + 14] * fArr3[9]) + (fArr[i2 + 13] * fArr3[10]) + (fArr[i2 + 12] * fArr3[11]) + (fArr[i2 + 11] * fArr3[12]) + (fArr[i2 + 10] * fArr3[13]) + (fArr[i2 + 9] * fArr3[14]) + (fArr[i2 + 8] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void k() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 8] * fArr3[0]) + (fArr[i2 + 7] * fArr3[1]) + (fArr[i2 + 6] * fArr3[2]) + (fArr[i2 + 5] * fArr3[3]) + (fArr[i2 + 4] * fArr3[4]) + (fArr[i2 + 3] * fArr3[5]) + (fArr[i2 + 2] * fArr3[6]) + (fArr[i2 + 1] * fArr3[7]) + (fArr[i2 + 0] * fArr3[8]) + (fArr[i2 + 15] * fArr3[9]) + (fArr[i2 + 14] * fArr3[10]) + (fArr[i2 + 13] * fArr3[11]) + (fArr[i2 + 12] * fArr3[12]) + (fArr[i2 + 11] * fArr3[13]) + (fArr[i2 + 10] * fArr3[14]) + (fArr[i2 + 9] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void l() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 9] * fArr3[0]) + (fArr[i2 + 8] * fArr3[1]) + (fArr[i2 + 7] * fArr3[2]) + (fArr[i2 + 6] * fArr3[3]) + (fArr[i2 + 5] * fArr3[4]) + (fArr[i2 + 4] * fArr3[5]) + (fArr[i2 + 3] * fArr3[6]) + (fArr[i2 + 2] * fArr3[7]) + (fArr[i2 + 1] * fArr3[8]) + (fArr[i2 + 0] * fArr3[9]) + (fArr[i2 + 15] * fArr3[10]) + (fArr[i2 + 14] * fArr3[11]) + (fArr[i2 + 13] * fArr3[12]) + (fArr[i2 + 12] * fArr3[13]) + (fArr[i2 + 11] * fArr3[14]) + (fArr[i2 + 10] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void m() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 10] * fArr3[0]) + (fArr[i2 + 9] * fArr3[1]) + (fArr[i2 + 8] * fArr3[2]) + (fArr[i2 + 7] * fArr3[3]) + (fArr[i2 + 6] * fArr3[4]) + (fArr[i2 + 5] * fArr3[5]) + (fArr[i2 + 4] * fArr3[6]) + (fArr[i2 + 3] * fArr3[7]) + (fArr[i2 + 2] * fArr3[8]) + (fArr[i2 + 1] * fArr3[9]) + (fArr[i2 + 0] * fArr3[10]) + (fArr[i2 + 15] * fArr3[11]) + (fArr[i2 + 14] * fArr3[12]) + (fArr[i2 + 13] * fArr3[13]) + (fArr[i2 + 12] * fArr3[14]) + (fArr[i2 + 11] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void n() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 11] * fArr3[0]) + (fArr[i2 + 10] * fArr3[1]) + (fArr[i2 + 9] * fArr3[2]) + (fArr[i2 + 8] * fArr3[3]) + (fArr[i2 + 7] * fArr3[4]) + (fArr[i2 + 6] * fArr3[5]) + (fArr[i2 + 5] * fArr3[6]) + (fArr[i2 + 4] * fArr3[7]) + (fArr[i2 + 3] * fArr3[8]) + (fArr[i2 + 2] * fArr3[9]) + (fArr[i2 + 1] * fArr3[10]) + (fArr[i2 + 0] * fArr3[11]) + (fArr[i2 + 15] * fArr3[12]) + (fArr[i2 + 14] * fArr3[13]) + (fArr[i2 + 13] * fArr3[14]) + (fArr[i2 + 12] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void o() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 12] * fArr3[0]) + (fArr[i2 + 11] * fArr3[1]) + (fArr[i2 + 10] * fArr3[2]) + (fArr[i2 + 9] * fArr3[3]) + (fArr[i2 + 8] * fArr3[4]) + (fArr[i2 + 7] * fArr3[5]) + (fArr[i2 + 6] * fArr3[6]) + (fArr[i2 + 5] * fArr3[7]) + (fArr[i2 + 4] * fArr3[8]) + (fArr[i2 + 3] * fArr3[9]) + (fArr[i2 + 2] * fArr3[10]) + (fArr[i2 + 1] * fArr3[11]) + (fArr[i2 + 0] * fArr3[12]) + (fArr[i2 + 15] * fArr3[13]) + (fArr[i2 + 14] * fArr3[14]) + (fArr[i2 + 13] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void p() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 13] * fArr3[0]) + (fArr[i2 + 12] * fArr3[1]) + (fArr[i2 + 11] * fArr3[2]) + (fArr[i2 + 10] * fArr3[3]) + (fArr[i2 + 9] * fArr3[4]) + (fArr[i2 + 8] * fArr3[5]) + (fArr[i2 + 7] * fArr3[6]) + (fArr[i2 + 6] * fArr3[7]) + (fArr[i2 + 5] * fArr3[8]) + (fArr[i2 + 4] * fArr3[9]) + (fArr[i2 + 3] * fArr3[10]) + (fArr[i2 + 2] * fArr3[11]) + (fArr[i2 + 1] * fArr3[12]) + (fArr[i2 + 0] * fArr3[13]) + (fArr[i2 + 15] * fArr3[14]) + (fArr[i2 + 14] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void q() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 14] * fArr3[0]) + (fArr[i2 + 13] * fArr3[1]) + (fArr[i2 + 12] * fArr3[2]) + (fArr[i2 + 11] * fArr3[3]) + (fArr[i2 + 10] * fArr3[4]) + (fArr[i2 + 9] * fArr3[5]) + (fArr[i2 + 8] * fArr3[6]) + (fArr[i2 + 7] * fArr3[7]) + (fArr[i2 + 6] * fArr3[8]) + (fArr[i2 + 5] * fArr3[9]) + (fArr[i2 + 4] * fArr3[10]) + (fArr[i2 + 3] * fArr3[11]) + (fArr[i2 + 2] * fArr3[12]) + (fArr[i2 + 1] * fArr3[13]) + (fArr[i2 + 0] * fArr3[14]) + (fArr[i2 + 15] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void r() {
        float[] fArr = this.c;
        float[] fArr2 = this.h;
        int i2 = 0;
        for (int i3 = 0; i3 < 32; i3++) {
            float[] fArr3 = O[i3];
            fArr2[i3] = ((fArr[i2 + 15] * fArr3[0]) + (fArr[i2 + 14] * fArr3[1]) + (fArr[i2 + 13] * fArr3[2]) + (fArr[i2 + 12] * fArr3[3]) + (fArr[i2 + 11] * fArr3[4]) + (fArr[i2 + 10] * fArr3[5]) + (fArr[i2 + 9] * fArr3[6]) + (fArr[i2 + 8] * fArr3[7]) + (fArr[i2 + 7] * fArr3[8]) + (fArr[i2 + 6] * fArr3[9]) + (fArr[i2 + 5] * fArr3[10]) + (fArr[i2 + 4] * fArr3[11]) + (fArr[i2 + 3] * fArr3[12]) + (fArr[i2 + 2] * fArr3[13]) + (fArr[i2 + 1] * fArr3[14]) + (fArr[i2 + 0] * fArr3[15])) * this.g;
            i2 += 16;
        }
    }

    private void b(m mVar) {
        switch (this.d) {
            case 0:
                c();
                break;
            case 1:
                d();
                break;
            case 2:
                e();
                break;
            case 3:
                f();
                break;
            case 4:
                g();
                break;
            case 5:
                h();
                break;
            case 6:
                i();
                break;
            case 7:
                j();
                break;
            case 8:
                k();
                break;
            case 9:
                l();
                break;
            case 10:
                m();
                break;
            case 11:
                n();
                break;
            case 12:
                o();
                break;
            case 13:
                p();
                break;
            case 14:
                q();
                break;
            case 15:
                r();
                break;
        }
        if (mVar != null) {
            mVar.a(this.f, this.h);
        }
    }

    public final void a(m mVar) {
        b();
        b(mVar);
        this.d = (this.d + 1) & 15;
        this.c = this.c == this.f33a ? this.f34b : this.f33a;
        for (int i2 = 0; i2 < 32; i2++) {
            this.e[i2] = 0.0f;
        }
    }

    private static float[] s() {
        try {
            return (float[]) a(n.class.getResourceAsStream("/sfd.ser"), Float.TYPE, 512);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Object a(InputStream inputStream, Class cls, int i2) {
        if (cls == null) {
            throw new NullPointerException("elemType");
        }
        Object a2 = a(inputStream);
        Class<?> cls2 = a2.getClass();
        if (!cls2.isArray()) {
            throw new InvalidObjectException("object is not an array");
        }
        if (cls2.getComponentType() != cls) {
            throw new InvalidObjectException("unexpected array component type");
        }
        if (Array.getLength(a2) != 512) {
            throw new InvalidObjectException("array length mismatch");
        }
        return a2;
    }

    private static Object a(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        try {
            return new ObjectInputStream(inputStream).readObject();
        } catch (ClassNotFoundException e) {
            throw new InvalidClassException(e.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [float[], float[][]] */
    private static float[][] a(float[] fArr, int i2) {
        int length = fArr.length / 16;
        ?? r0 = new float[length];
        for (int i3 = 0; i3 < length; i3++) {
            r0[i3] = a(fArr, i3 << 4, 16);
        }
        return r0;
    }

    private static float[] a(float[] fArr, int i2, int i3) {
        if (i2 + i3 > fArr.length) {
            i3 = fArr.length - i2;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        float[] fArr2 = new float[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            fArr2[i4] = fArr[i2 + i4];
        }
        return fArr2;
    }
}

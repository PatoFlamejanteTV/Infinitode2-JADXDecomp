package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Item.class */
final class Item {

    /* renamed from: a, reason: collision with root package name */
    int f1452a;

    /* renamed from: b, reason: collision with root package name */
    int f1453b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item(int i) {
        this.f1452a = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item(int i, Item item) {
        this.f1452a = i;
        this.f1453b = item.f1453b;
        this.c = item.c;
        this.d = item.d;
        this.g = item.g;
        this.h = item.h;
        this.i = item.i;
        this.j = item.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.f1453b = 3;
        this.c = i;
        this.j = Integer.MAX_VALUE & (this.f1453b + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(long j) {
        this.f1453b = 5;
        this.d = j;
        this.j = Integer.MAX_VALUE & (this.f1453b + ((int) j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(float f) {
        this.f1453b = 4;
        this.c = Float.floatToRawIntBits(f);
        this.j = Integer.MAX_VALUE & (this.f1453b + ((int) f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(double d) {
        this.f1453b = 6;
        this.d = Double.doubleToRawLongBits(d);
        this.j = Integer.MAX_VALUE & (this.f1453b + ((int) d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0016. Please report as an issue. */
    public final void a(int i, String str, String str2, String str3) {
        this.f1453b = i;
        this.g = str;
        this.h = str2;
        this.i = str3;
        switch (i) {
            case 7:
                this.c = 0;
            case 1:
            case 8:
            case 16:
            case 30:
                this.j = Integer.MAX_VALUE & (i + str.hashCode());
                return;
            case 12:
                this.j = Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode()));
                return;
            default:
                this.j = Integer.MAX_VALUE & (i + (str.hashCode() * str2.hashCode() * str3.hashCode()));
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, String str2, int i) {
        this.f1453b = 18;
        this.d = i;
        this.g = str;
        this.h = str2;
        this.j = Integer.MAX_VALUE & (18 + (i * this.g.hashCode() * this.h.hashCode()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2) {
        this.f1453b = 33;
        this.c = i;
        this.j = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(Item item) {
        switch (this.f1453b) {
            case 1:
            case 7:
            case 8:
            case 16:
            case 30:
                return item.g.equals(this.g);
            case 2:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 17:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            default:
                return item.g.equals(this.g) && item.h.equals(this.h) && item.i.equals(this.i);
            case 3:
            case 4:
                return item.c == this.c;
            case 5:
            case 6:
            case 32:
                return item.d == this.d;
            case 12:
                return item.g.equals(this.g) && item.h.equals(this.h);
            case 18:
                return item.d == this.d && item.g.equals(this.g) && item.h.equals(this.h);
            case 31:
                return item.c == this.c && item.g.equals(this.g);
        }
    }
}

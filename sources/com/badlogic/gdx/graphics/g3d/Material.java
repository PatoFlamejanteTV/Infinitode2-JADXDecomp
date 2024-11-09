package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Material.class */
public class Material extends Attributes {
    private static int counter = 0;
    public String id;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Material() {
        /*
            r5 = this;
            r0 = r5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = r1
            java.lang.String r3 = "mtl"
            r2.<init>(r3)
            int r2 = com.badlogic.gdx.graphics.g3d.Material.counter
            r3 = 1
            int r2 = r2 + r3
            r3 = r2
            com.badlogic.gdx.graphics.g3d.Material.counter = r3
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.g3d.Material.<init>():void");
    }

    public Material(String str) {
        this.id = str;
    }

    public Material(Attribute... attributeArr) {
        this();
        set(attributeArr);
    }

    public Material(String str, Attribute... attributeArr) {
        this(str);
        set(attributeArr);
    }

    public Material(Array<Attribute> array) {
        this();
        set(array);
    }

    public Material(String str, Array<Attribute> array) {
        this(str);
        set(array);
    }

    public Material(Material material) {
        this(material.id, material);
    }

    public Material(String str, Material material) {
        this(str);
        Iterator<Attribute> it = material.iterator();
        while (it.hasNext()) {
            set(it.next().copy());
        }
    }

    public Material copy() {
        return new Material(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attributes
    public int hashCode() {
        return super.hashCode() + (3 * this.id.hashCode());
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attributes, java.util.Comparator
    public boolean equals(Object obj) {
        if (!(obj instanceof Material)) {
            return false;
        }
        if (obj != this) {
            return ((Material) obj).id.equals(this.id) && super.equals(obj);
        }
        return true;
    }
}

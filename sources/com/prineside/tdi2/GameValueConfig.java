package com.prineside.tdi2;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/GameValueConfig.class */
public final class GameValueConfig implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private GameValueType f1712a;

    /* renamed from: b, reason: collision with root package name */
    private double f1713b;
    private boolean c;
    private boolean d;
    private boolean e;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f1712a);
        output.writeDouble(this.f1713b);
        output.writeBoolean(this.c);
        output.writeBoolean(this.d);
        output.writeBoolean(this.e);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1712a = (GameValueType) kryo.readObject(input, GameValueType.class);
        this.f1713b = input.readDouble();
        this.c = input.readBoolean();
        this.d = input.readBoolean();
        this.e = input.readBoolean();
    }

    private GameValueConfig() {
    }

    public GameValueConfig(GameValueType gameValueType, double d, boolean z, boolean z2) {
        setType(gameValueType);
        this.f1713b = d;
        this.c = z;
        this.d = z2;
    }

    public final GameValueType getType() {
        return this.f1712a;
    }

    public final void setType(GameValueType gameValueType) {
        Preconditions.checkNotNull(gameValueType, "type can not be null");
        this.f1712a = gameValueType;
    }

    public final double getValue() {
        return this.f1713b;
    }

    public final void setValue(double d) {
        this.f1713b = d;
    }

    public final boolean isOverwrite() {
        return this.c;
    }

    public final void setOverwrite(boolean z) {
        this.c = z;
    }

    public final boolean isAllowBonuses() {
        return this.d;
    }

    public final void setAllowBonuses(boolean z) {
        this.d = z;
    }

    public final boolean isFinalGlobalMultiplier() {
        return this.e;
    }

    public final void setFinalGlobalMultiplier(boolean z) {
        this.e = z;
    }

    public final void toJson(Json json) {
        json.writeValue("t", this.f1712a.name());
        json.writeValue("v", Double.valueOf(this.f1713b));
        json.writeValue("o", Boolean.valueOf(this.c));
        json.writeValue(FlexmarkHtmlConverter.B_NODE, Boolean.valueOf(this.d));
        if (this.e) {
            json.writeValue("fgm", Boolean.TRUE);
        }
    }

    public static GameValueConfig fromJson(JsonValue jsonValue) {
        try {
            GameValueConfig gameValueConfig = new GameValueConfig();
            gameValueConfig.f1712a = GameValueType.valueOf(jsonValue.getString("t"));
            gameValueConfig.f1713b = jsonValue.getDouble("v");
            gameValueConfig.c = jsonValue.getBoolean("o", false);
            gameValueConfig.d = jsonValue.getBoolean(FlexmarkHtmlConverter.B_NODE, true);
            gameValueConfig.e = jsonValue.getBoolean("fgm", false);
            return gameValueConfig;
        } catch (Exception e) {
            throw new IllegalArgumentException("failed to load from json", e);
        }
    }

    public final boolean sameAs(GameValueConfig gameValueConfig) {
        return gameValueConfig.f1712a == this.f1712a && gameValueConfig.f1713b == this.f1713b && gameValueConfig.c == this.c && gameValueConfig.d == this.d && gameValueConfig.e == this.e;
    }

    public final GameValueConfig cpy() {
        GameValueConfig gameValueConfig = new GameValueConfig(this.f1712a, this.f1713b, this.c, this.d);
        gameValueConfig.e = this.e;
        return gameValueConfig;
    }

    public final String toString() {
        return super.toString() + " {type: " + this.f1712a + ", value: " + this.f1713b + ", ow: " + this.c + ", ab: " + this.d + ", fgm: " + this.e + "}";
    }
}

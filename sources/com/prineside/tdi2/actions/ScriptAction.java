package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/ScriptAction.class */
public class ScriptAction extends Action {
    public String script;

    private ScriptAction() {
    }

    public ScriptAction(String str) {
        this.script = str;
    }

    public ScriptAction(JsonValue jsonValue) {
        this(jsonValue.getString("s"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.S;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("s", this.script);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeString(this.script);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.script = input.readString();
    }

    public String toString() {
        return "Script " + this.script;
    }
}

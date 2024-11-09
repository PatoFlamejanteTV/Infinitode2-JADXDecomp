package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;

@Deprecated
/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/GameSystemProviderSerializer.class */
public class GameSystemProviderSerializer extends Serializer<GameSystemProvider> {
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, GameSystemProvider gameSystemProvider) {
        int i = 0;
        Array<GameSystem> systemsOrdered = gameSystemProvider.getSystemsOrdered();
        for (int i2 = 0; i2 < systemsOrdered.size; i2++) {
            if (systemsOrdered.items[i2].affectsGameState()) {
                i++;
            }
        }
        output.writeByte(i);
        for (int i3 = 0; i3 < systemsOrdered.size; i3++) {
            GameSystem gameSystem = systemsOrdered.items[i3];
            if (gameSystem.affectsGameState()) {
                kryo.writeClassAndObject(output, gameSystem);
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public GameSystemProvider read2(Kryo kryo, Input input, Class<? extends GameSystemProvider> cls) {
        GameSystemProvider gameSystemProvider = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true));
        int readByte = input.readByte();
        for (int i = 0; i < readByte; i++) {
            gameSystemProvider.addSystem((GameSystem) kryo.readClassAndObject(input));
        }
        return gameSystemProvider;
    }
}

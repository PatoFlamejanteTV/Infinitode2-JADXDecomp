package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.miners.InfiarMiner;
import com.prineside.tdi2.miners.MatrixMiner;
import com.prineside.tdi2.miners.ScalarMiner;
import com.prineside.tdi2.miners.TensorMiner;
import com.prineside.tdi2.miners.VectorMiner;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MinerManager.class */
public class MinerManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final Miner.Factory[] f2380a = new Miner.Factory[MinerType.values.length];
    public final Factories F = new Factories();
    public final ObjectMap<MinerType, String> SHORT_MINER_ALIASES = new ObjectMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final StatisticsType[] f2381b;
    private final StatisticsType[] c;
    private final StatisticsType[] d;
    private final GameValueType[] e;
    private final String[] f;
    public ParticleEffectPool[] highlightParticles;
    public ParticleEffectPool minedResourceParticleEffectPool;
    public ParticleEffectPool doubleSpeedParticleEffectPool;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MinerManager$Factories.class */
    public static class Factories {
        public ScalarMiner.ScalarMinerFactory SCALAR;
        public VectorMiner.VectorMinerFactory VECTOR;
        public MatrixMiner.MatrixMinerFactory MATRIX;
        public TensorMiner.TensorMinerFactory TENSOR;
        public InfiarMiner.InfiarMinerFactory INFIAR;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MinerManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<MinerManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public MinerManager read() {
            return Game.i.minerManager;
        }
    }

    public MinerManager() {
        this.SHORT_MINER_ALIASES.put(MinerType.SCALAR, "S");
        this.SHORT_MINER_ALIASES.put(MinerType.VECTOR, "V");
        this.SHORT_MINER_ALIASES.put(MinerType.MATRIX, "M");
        this.SHORT_MINER_ALIASES.put(MinerType.TENSOR, "T");
        this.SHORT_MINER_ALIASES.put(MinerType.INFIAR, "I");
        this.f2381b = new StatisticsType[MinerType.values.length];
        this.c = new StatisticsType[MinerType.values.length];
        this.d = new StatisticsType[MinerType.values.length];
        this.e = new GameValueType[MinerType.values.length];
        this.f = new String[MinerType.values.length];
        this.highlightParticles = new ParticleEffectPool[TowerType.values.length];
        for (MinerType minerType : MinerType.values) {
            String str = this.SHORT_MINER_ALIASES.get(minerType);
            this.f2381b[minerType.ordinal()] = StatisticsType.valueOf("MMS_" + str);
            this.c[minerType.ordinal()] = StatisticsType.valueOf("MB_" + str);
            this.d[minerType.ordinal()] = StatisticsType.valueOf("MU_" + str);
        }
        Miner.Factory[] factoryArr = this.f2380a;
        int ordinal = MinerType.SCALAR.ordinal();
        Factories factories = this.F;
        ScalarMiner.ScalarMinerFactory scalarMinerFactory = new ScalarMiner.ScalarMinerFactory();
        factories.SCALAR = scalarMinerFactory;
        factoryArr[ordinal] = scalarMinerFactory;
        Miner.Factory[] factoryArr2 = this.f2380a;
        int ordinal2 = MinerType.VECTOR.ordinal();
        Factories factories2 = this.F;
        VectorMiner.VectorMinerFactory vectorMinerFactory = new VectorMiner.VectorMinerFactory();
        factories2.VECTOR = vectorMinerFactory;
        factoryArr2[ordinal2] = vectorMinerFactory;
        Miner.Factory[] factoryArr3 = this.f2380a;
        int ordinal3 = MinerType.MATRIX.ordinal();
        Factories factories3 = this.F;
        MatrixMiner.MatrixMinerFactory matrixMinerFactory = new MatrixMiner.MatrixMinerFactory();
        factories3.MATRIX = matrixMinerFactory;
        factoryArr3[ordinal3] = matrixMinerFactory;
        Miner.Factory[] factoryArr4 = this.f2380a;
        int ordinal4 = MinerType.TENSOR.ordinal();
        Factories factories4 = this.F;
        TensorMiner.TensorMinerFactory tensorMinerFactory = new TensorMiner.TensorMinerFactory();
        factories4.TENSOR = tensorMinerFactory;
        factoryArr4[ordinal4] = tensorMinerFactory;
        Miner.Factory[] factoryArr5 = this.f2380a;
        int ordinal5 = MinerType.INFIAR.ordinal();
        Factories factories5 = this.F;
        InfiarMiner.InfiarMinerFactory infiarMinerFactory = new InfiarMiner.InfiarMinerFactory();
        factories5.INFIAR = infiarMinerFactory;
        factoryArr5[ordinal5] = infiarMinerFactory;
        for (MinerType minerType2 : MinerType.values) {
            if (this.f2380a[minerType2.ordinal()] == null) {
                throw new RuntimeException("Not all miner factories were created");
            }
        }
        for (MinerType minerType3 : MinerType.values) {
            this.f[minerType3.ordinal()] = "miner_name_" + minerType3.name();
            this.e[minerType3.ordinal()] = GameValueType.valueOf("MINER_" + minerType3.name() + "_INSTALL_DURATION");
        }
    }

    public boolean isMinerOpened(MinerType minerType, GameValueProvider gameValueProvider) {
        switch (minerType) {
            case SCALAR:
                return gameValueProvider.getIntValue(GameValueType.MINER_COUNT_SCALAR) > 0;
            case VECTOR:
                return gameValueProvider.getIntValue(GameValueType.MINER_COUNT_VECTOR) > 0;
            case MATRIX:
                return gameValueProvider.getIntValue(GameValueType.MINER_COUNT_MATRIX) > 0;
            case TENSOR:
                return gameValueProvider.getIntValue(GameValueType.MINER_COUNT_TENSOR) > 0;
            case INFIAR:
                return gameValueProvider.getIntValue(GameValueType.MINER_COUNT_INFIAR) > 0;
            default:
                return false;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        for (Miner.Factory factory : this.f2380a) {
            factory.setup();
        }
        if (Game.i.assetManager != null) {
            this.minedResourceParticleEffectPool = Game.i.assetManager.getParticleEffectPool("mined-resource.prt");
            this.doubleSpeedParticleEffectPool = Game.i.assetManager.getParticleEffectPool("miner-double-speed.prt");
            for (MinerType minerType : MinerType.values) {
                ParticleEffect particleEffect = new ParticleEffect();
                particleEffect.load(Gdx.files.internal("particles/building-highlight.prt"), Game.i.assetManager.getTextureRegion("tower-basic-base").getAtlas());
                particleEffect.setEmittersCleanUpBlendFunction(false);
                particleEffect.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.minerManager.getFactory(minerType).getTexture())}));
                this.highlightParticles[minerType.ordinal()] = Game.i.assetManager.getParticleEffectPoolWithTemplate("building-highlight.prt@minerType:" + minerType.name(), particleEffect);
            }
        }
    }

    public Miner.Factory<? extends Miner> getFactory(MinerType minerType) {
        return this.f2380a[minerType.ordinal()];
    }

    public String getTitle(MinerType minerType) {
        return Game.i.localeManager.i18n.get(this.f[minerType.ordinal()]);
    }

    public boolean minersAndEnergyAvailable() {
        return Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.MINER_COUNT_SCALAR) > 0;
    }

    public GameValueType getInstallDurationGameValueType(MinerType minerType) {
        return this.e[minerType.ordinal()];
    }

    public StatisticsType getBuiltStatisticType(MinerType minerType) {
        return this.c[minerType.ordinal()];
    }

    public StatisticsType getMoneySpentStatisticType(MinerType minerType) {
        return this.f2381b[minerType.ordinal()];
    }

    public StatisticsType getUpgradedStatisticType(MinerType minerType) {
        return this.d[minerType.ordinal()];
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
    }

    public Miner fromJson(JsonValue jsonValue) {
        Miner create = getFactory(MinerType.valueOf(jsonValue.getString("type"))).create();
        create.loadFromJson(jsonValue);
        return create;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}

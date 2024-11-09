package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.items.GameValueGlobalItem;
import com.prineside.tdi2.items.GameValueLevelItem;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.ResearchManager;
import com.prineside.tdi2.managers.TrophyManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.QuadRegion;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager.class */
public class GameValueManager extends Manager.ManagerAdapter {

    /* renamed from: b, reason: collision with root package name */
    private boolean f2336b;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2335a = TLog.forClass(GameValueManager.class);
    public static final Color ICON_BACKGROUND_COLOR = new Color(252645375);
    private static final StringBuilder g = new StringBuilder();
    private static final StringBuilder h = new StringBuilder();
    private boolean d = true;
    private final GameValuesSnapshot e = new GameValuesSnapshot();
    private final GameValuesSnapshot f = new GameValuesSnapshot();
    private final DelayedRemovalArray<GameValueManagerListener> i = new DelayedRemovalArray<>(false, 1);
    private final _ResearchManagerListener j = new _ResearchManagerListener(this, 0);
    private final _ProgressManagerListener k = new _ProgressManagerListener(this, 0);
    private final GameValueStockConfig[] c = new GameValueStockConfig[GameValueType.values.length];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GameValueManagerListener.class */
    public interface GameValueManagerListener {
        void gameValuesRecalculated();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$ValueUnits.class */
    public enum ValueUnits {
        UNITS,
        SECONDS,
        UNITS_PER_SECOND,
        PERCENTS_PER_SECOND,
        PERCENTS,
        BOOLEAN
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<GameValueManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public GameValueManager read() {
            return Game.i.gameValueManager;
        }
    }

    public GameValueManager() {
        for (GameValueType gameValueType : GameValueType.values) {
            this.c[gameValueType.ordinal()] = new GameValueStockConfig();
            this.c[gameValueType.ordinal()].type = gameValueType;
        }
    }

    private void b() {
        if (this.d) {
            c();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (!Config.isHeadless()) {
            Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.GameValueManager.1
                @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
                public void reloaded() {
                    GameValueManager.this.requireRecalculation();
                }
            });
            Game.i.researchManager.addListener(this.j);
            Game.i.progressManager.addListener(this.k);
        }
        for (GameValueStockConfig gameValueStockConfig : this.c) {
            gameValueStockConfig.stockValue = 0.0d;
        }
        Iterator<JsonValue> iterator2 = new JsonReader().parse(Gdx.files.internal("res/game-values.json")).iterator2();
        while (iterator2.hasNext()) {
            JsonValue next = iterator2.next();
            try {
                GameValueType valueOf = GameValueType.valueOf(next.name);
                GameValueStockConfig gameValueStockConfig2 = this.c[valueOf.ordinal()];
                gameValueStockConfig2.stockValue = next.get("default").asDouble();
                gameValueStockConfig2.units = ValueUnits.valueOf(next.get("units").asString());
                gameValueStockConfig2.titleAlias = "gv_title_" + valueOf.name();
                if (gameValueStockConfig2.units == ValueUnits.BOOLEAN) {
                    gameValueStockConfig2.disabledTitleAlias = "gv_title_disabled_" + valueOf.name();
                }
                next.getString("flags", "");
            } catch (Exception e) {
                f2335a.e("failed to load game value config '" + next.name + "'", e);
            }
        }
        requireRecalculation();
        this.f2336b = true;
    }

    public void addListener(GameValueManagerListener gameValueManagerListener) {
        if (gameValueManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.i.contains(gameValueManagerListener, true)) {
            this.i.add(gameValueManagerListener);
        }
    }

    public void removeListener(GameValueManagerListener gameValueManagerListener) {
        if (gameValueManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.i.removeValue(gameValueManagerListener, true);
    }

    public String getCheatingReason(Array<GvSnapDiff> array) {
        for (int i = 0; i < array.size; i++) {
            GvSnapDiff gvSnapDiff = array.items[i];
            if (gvSnapDiff.type == GvSnapDiff.Type.GV_NOT_IN_ORIG && gvSnapDiff.effect.source == GameValueEffect.Source.STOCK) {
                GameValueStockConfig gameValueStockConfig = this.c[gvSnapDiff.effect.type.ordinal()];
                return gameValueStockConfig.type.name() + SequenceUtils.SPACE + gvSnapDiff.effect.delta + " != stock " + gameValueStockConfig.stockValue;
            }
        }
        return null;
    }

    public void requireRecalculation() {
        this.d = true;
    }

    private void c() {
        if (!this.f2336b) {
            throw new IllegalStateException("Manager is not set up yet");
        }
        ProgressManager.ProgressSnapshotForState createProgressSnapshotForState = Game.i.progressManager.createProgressSnapshotForState();
        createSnapshot(this.e, Game.i.progressManager.getDifficultyMode(), false, null, false, false, createProgressSnapshotForState);
        if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
            this.f.from(this.e);
        } else {
            createSnapshot(this.f, DifficultyMode.ENDLESS_I, false, null, false, false, createProgressSnapshotForState);
        }
        this.d = false;
        this.i.begin();
        int i = this.i.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.i.get(i2).gameValuesRecalculated();
        }
        this.i.end();
    }

    public Array<GameValueEffect> getCurrentEffects() {
        if (this.f2336b) {
            return createSnapshot(null, Game.i.progressManager.getDifficultyMode(), true, null, false, false, Game.i.progressManager.createProgressSnapshotForState()).effects;
        }
        throw new IllegalStateException("Manager is not set up yet");
    }

    public StringBuilder formatEffectValue(double d, ValueUnits valueUnits) {
        g.setLength(0);
        if (valueUnits == ValueUnits.BOOLEAN) {
            return g.append(d <= 0.0d ? "false" : "true");
        }
        if (d > 0.0d) {
            g.append("+");
        }
        if (d % 1.0d == 0.0d) {
            g.append((int) d);
        } else {
            g.append(StringFormatter.compactNumber(d, true));
        }
        switch (valueUnits) {
            case SECONDS:
                g.append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
            case PERCENTS:
                g.append('%');
                break;
            case UNITS_PER_SECOND:
                g.append("/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
            case PERCENTS_PER_SECOND:
                g.append("%/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
        }
        return g;
    }

    public StringBuilder formatEffectTitleValue(double d, GameValueType gameValueType) {
        g.setLength(0);
        if (Game.i.gameValueManager.getStockValueConfig(gameValueType).units == ValueUnits.BOOLEAN) {
            if (d <= 0.0d) {
                g.append(Game.i.gameValueManager.getDisabledTitle(gameValueType));
            } else {
                g.append(Game.i.gameValueManager.getTitle(gameValueType));
            }
            return g;
        }
        g.append(Game.i.gameValueManager.getTitle(gameValueType)).append(' ');
        if (d > 0.0d) {
            g.append("+");
        }
        if (d % 1.0d == 0.0d) {
            g.append((int) d);
        } else {
            g.append(StringFormatter.compactNumber(d, true));
        }
        switch (r0.units) {
            case SECONDS:
                g.append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
            case PERCENTS:
                g.append('%');
                break;
            case UNITS_PER_SECOND:
                g.append("/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
            case PERCENTS_PER_SECOND:
                g.append("%/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                break;
        }
        return g;
    }

    public GameValueStockConfig getStockValueConfig(GameValueType gameValueType) {
        return this.c[gameValueType.ordinal()];
    }

    public StringBuilder getTitle(GameValueType gameValueType) {
        h.setLength(0);
        h.append(Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.get(getStockValueConfig(gameValueType).titleAlias)));
        return h;
    }

    public StringBuilder getDisabledTitle(GameValueType gameValueType) {
        GameValueStockConfig stockValueConfig = getStockValueConfig(gameValueType);
        if (stockValueConfig.units != ValueUnits.BOOLEAN) {
            f2335a.e("Game value " + gameValueType + " is not BOOLEAN, can't return disabled title - falling back to the default", new Object[0]);
            return getTitle(gameValueType);
        }
        h.setLength(0);
        h.append(Game.i.localeManager.i18n.get(stockValueConfig.disabledTitleAlias));
        return h;
    }

    public ValueUnits getUnits(GameValueType gameValueType) {
        return getStockValueConfig(gameValueType).units;
    }

    public GameValuesSnapshot getSnapshot() {
        b();
        return this.e;
    }

    public GameValuesSnapshot getEndlessSnapshot() {
        b();
        return this.f;
    }

    public static GameValuesSnapshot createSnapshot(@Null GameValuesSnapshot gameValuesSnapshot, DifficultyMode difficultyMode, boolean z, BasicLevel basicLevel, boolean z2, boolean z3, ProgressManager.ProgressSnapshotForState progressSnapshotForState) {
        if (!Game.isLoaded()) {
            throw new IllegalStateException("Game is not loaded yet");
        }
        if (gameValuesSnapshot == null) {
            gameValuesSnapshot = new GameValuesSnapshot();
        } else {
            gameValuesSnapshot.effects.clear();
        }
        if (basicLevel != null && basicLevel.forcedDifficulty != null) {
            difficultyMode = basicLevel.forcedDifficulty;
        }
        boolean isEndless = DifficultyMode.isEndless(difficultyMode);
        for (GameValueStockConfig gameValueStockConfig : Game.i.gameValueManager.c) {
            gameValuesSnapshot.values[gameValueStockConfig.type.ordinal()] = gameValueStockConfig.stockValue;
            if (z) {
                GameValueEffect gameValueEffect = new GameValueEffect();
                gameValueEffect.delta = gameValueStockConfig.stockValue;
                gameValueEffect.type = gameValueStockConfig.type;
                gameValueEffect.source = GameValueEffect.Source.STOCK;
                gameValuesSnapshot.effects.add(gameValueEffect);
            }
        }
        Array<Research> instances = Game.i.researchManager.getInstances();
        if (z2) {
            if (z3) {
                for (int i = 0; i < instances.size; i++) {
                    Research research = instances.items[i];
                    if (isEndless || !research.endlessOnly) {
                        int researchInstalledLevel = progressSnapshotForState.getResearchInstalledLevel(research.type);
                        int i2 = researchInstalledLevel;
                        if (researchInstalledLevel != 0 && research.cantBeIgnoredOnUserMaps) {
                            if (!isEndless && i2 > research.levels.length) {
                                i2 = research.levels.length;
                            }
                            Array<GameValueEffect> effects = research.getEffects(i2);
                            for (int i3 = 0; i3 < effects.size; i3++) {
                                GameValueEffect gameValueEffect2 = effects.items[i3];
                                double[] dArr = gameValuesSnapshot.values;
                                int ordinal = gameValueEffect2.type.ordinal();
                                dArr[ordinal] = dArr[ordinal] + gameValueEffect2.delta;
                                if (z) {
                                    GameValueEffect gameValueEffect3 = new GameValueEffect();
                                    gameValueEffect3.setup(gameValueEffect2.type, gameValueEffect2.delta, GameValueEffect.Source.RESEARCH);
                                    gameValueEffect3.researchType = research.type;
                                    gameValuesSnapshot.effects.add(gameValueEffect3);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int i4 = 0; i4 < instances.size; i4++) {
                Research research2 = instances.items[i4];
                if (isEndless || !research2.endlessOnly) {
                    int researchInstalledLevel2 = progressSnapshotForState.getResearchInstalledLevel(research2.type);
                    int i5 = researchInstalledLevel2;
                    if (researchInstalledLevel2 != 0) {
                        if (!isEndless && i5 > research2.levels.length) {
                            i5 = research2.levels.length;
                        }
                        Array<GameValueEffect> effects2 = research2.getEffects(i5);
                        for (int i6 = 0; i6 < effects2.size; i6++) {
                            GameValueEffect gameValueEffect4 = effects2.items[i6];
                            double[] dArr2 = gameValuesSnapshot.values;
                            int ordinal2 = gameValueEffect4.type.ordinal();
                            dArr2[ordinal2] = dArr2[ordinal2] + gameValueEffect4.delta;
                            if (z) {
                                GameValueEffect gameValueEffect5 = new GameValueEffect();
                                gameValueEffect5.setup(gameValueEffect4.type, gameValueEffect4.delta, GameValueEffect.Source.RESEARCH);
                                gameValueEffect5.researchType = research2.type;
                                gameValuesSnapshot.effects.add(gameValueEffect5);
                            }
                        }
                    }
                }
            }
            for (TrophyManager.TrophyConfig trophyConfig : Game.i.trophyManager.getConfigs()) {
                if (progressSnapshotForState.isTrophyReceived(trophyConfig.type)) {
                    for (int i7 = 0; i7 < trophyConfig.gameValues.size; i7++) {
                        GameValueEffect gameValueEffect6 = trophyConfig.gameValues.items[i7];
                        double[] dArr3 = gameValuesSnapshot.values;
                        int ordinal3 = gameValueEffect6.type.ordinal();
                        dArr3[ordinal3] = dArr3[ordinal3] + gameValueEffect6.delta;
                        if (z) {
                            GameValueEffect gameValueEffect7 = new GameValueEffect();
                            gameValueEffect7.setup(gameValueEffect6.type, gameValueEffect6.delta, GameValueEffect.Source.TROPHY);
                            gameValueEffect7.trophyType = trophyConfig.type;
                            gameValuesSnapshot.effects.add(gameValueEffect7);
                        }
                    }
                }
            }
            for (int i8 = 0; i8 < Game.i.basicLevelManager.levelsOrdered.size; i8++) {
                BasicLevel basicLevel2 = Game.i.basicLevelManager.levelsOrdered.items[i8];
                for (int i9 = 0; i9 < basicLevel2.quests.size; i9++) {
                    BasicLevelQuestConfig basicLevelQuestConfig = basicLevel2.quests.items[i9];
                    if (progressSnapshotForState.isQuestEverCompleted(basicLevelQuestConfig.id)) {
                        for (int i10 = 0; i10 < basicLevelQuestConfig.prizes.size; i10++) {
                            ItemStack itemStack = basicLevelQuestConfig.prizes.items[i10];
                            if (itemStack.getItem().getType() == ItemType.GAME_VALUE_GLOBAL) {
                                GameValueGlobalItem gameValueGlobalItem = (GameValueGlobalItem) itemStack.getItem();
                                double[] dArr4 = gameValuesSnapshot.values;
                                int ordinal4 = gameValueGlobalItem.gameValueType.ordinal();
                                dArr4[ordinal4] = dArr4[ordinal4] + gameValueGlobalItem.delta;
                                if (z) {
                                    GameValueEffect gameValueEffect8 = new GameValueEffect();
                                    gameValueEffect8.setup(gameValueGlobalItem.gameValueType, gameValueGlobalItem.delta, GameValueEffect.Source.LEVEL_QUEST);
                                    gameValueEffect8.questId = basicLevelQuestConfig.id;
                                    gameValuesSnapshot.effects.add(gameValueEffect8);
                                }
                            }
                        }
                    }
                }
                for (int i11 = 0; i11 < basicLevel2.waveQuests.size; i11++) {
                    BasicLevel.WaveQuest waveQuest = basicLevel2.waveQuests.get(i11);
                    if (progressSnapshotForState.isQuestEverCompleted(waveQuest.id)) {
                        for (int i12 = 0; i12 < waveQuest.prizes.size; i12++) {
                            ItemStack itemStack2 = waveQuest.prizes.items[i12];
                            if (itemStack2.getItem().getType() == ItemType.GAME_VALUE_GLOBAL) {
                                GameValueGlobalItem gameValueGlobalItem2 = (GameValueGlobalItem) itemStack2.getItem();
                                double[] dArr5 = gameValuesSnapshot.values;
                                int ordinal5 = gameValueGlobalItem2.gameValueType.ordinal();
                                dArr5[ordinal5] = dArr5[ordinal5] + gameValueGlobalItem2.delta;
                                if (z) {
                                    GameValueEffect gameValueEffect9 = new GameValueEffect();
                                    gameValueEffect9.setup(gameValueGlobalItem2.gameValueType, gameValueGlobalItem2.delta, GameValueEffect.Source.LEVEL_WAVE_QUEST);
                                    gameValueEffect9.questId = waveQuest.id;
                                    gameValuesSnapshot.effects.add(gameValueEffect9);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (basicLevel != null) {
            for (int i13 = 0; i13 < basicLevel.quests.size; i13++) {
                BasicLevelQuestConfig basicLevelQuestConfig2 = basicLevel.quests.items[i13];
                if (progressSnapshotForState.isQuestEverCompleted(basicLevelQuestConfig2.id)) {
                    for (int i14 = 0; i14 < basicLevelQuestConfig2.prizes.size; i14++) {
                        ItemStack itemStack3 = basicLevelQuestConfig2.prizes.items[i14];
                        if (itemStack3.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                            GameValueLevelItem gameValueLevelItem = (GameValueLevelItem) itemStack3.getItem();
                            double[] dArr6 = gameValuesSnapshot.values;
                            int ordinal6 = gameValueLevelItem.gameValueType.ordinal();
                            dArr6[ordinal6] = dArr6[ordinal6] + gameValueLevelItem.delta;
                            if (z) {
                                GameValueEffect gameValueEffect10 = new GameValueEffect();
                                gameValueEffect10.setup(gameValueLevelItem.gameValueType, gameValueLevelItem.delta, GameValueEffect.Source.LEVEL_QUEST);
                                gameValueEffect10.questId = basicLevelQuestConfig2.id;
                                gameValuesSnapshot.effects.add(gameValueEffect10);
                            }
                        }
                    }
                }
            }
            for (int i15 = 0; i15 < basicLevel.waveQuests.size; i15++) {
                BasicLevel.WaveQuest waveQuest2 = basicLevel.waveQuests.items[i15];
                if (progressSnapshotForState.isQuestEverCompleted(waveQuest2.id)) {
                    for (int i16 = 0; i16 < waveQuest2.prizes.size; i16++) {
                        ItemStack itemStack4 = waveQuest2.prizes.items[i16];
                        if (itemStack4.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                            GameValueLevelItem gameValueLevelItem2 = (GameValueLevelItem) itemStack4.getItem();
                            double[] dArr7 = gameValuesSnapshot.values;
                            int ordinal7 = gameValueLevelItem2.gameValueType.ordinal();
                            dArr7[ordinal7] = dArr7[ordinal7] + gameValueLevelItem2.delta;
                            if (z) {
                                GameValueEffect gameValueEffect11 = new GameValueEffect();
                                gameValueEffect11.setup(gameValueLevelItem2.gameValueType, gameValueLevelItem2.delta, GameValueEffect.Source.LEVEL_WAVE_QUEST);
                                gameValueEffect11.questId = waveQuest2.id;
                                gameValuesSnapshot.effects.add(gameValueEffect11);
                            }
                        }
                    }
                }
            }
        }
        int i17 = 1;
        for (GameValueType gameValueType : GameValueType.values) {
            i17 = (i17 * 31) + ((int) (gameValuesSnapshot.values[gameValueType.ordinal()] * 1000.0d));
        }
        gameValuesSnapshot.hash = i17;
        return gameValuesSnapshot;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (GameValueType gameValueType : GameValueType.values) {
            getStockValueConfig(gameValueType);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GameValueStockConfig.class */
    public static class GameValueStockConfig {
        public GameValueType type;
        public String titleAlias;

        @Null
        public String disabledTitleAlias;
        public double stockValue;
        public ValueUnits units;

        /* renamed from: a, reason: collision with root package name */
        private Quad f2340a;

        public Quad getIcon() {
            if (this.f2340a == null) {
                this.f2340a = Game.i.assetManager.getQuad("gv." + this.type.name());
            }
            return this.f2340a;
        }

        public Quad createIconForBackground(Color color) {
            Quad quad = new Quad(getIcon(), true);
            for (int i = 0; i < quad.getRegions().size; i++) {
                QuadRegion quadRegion = quad.getRegions().get(i);
                if ("shadow".equals(quadRegion.getQuadName())) {
                    quadRegion.setSameCornerColors(color);
                    quadRegion.setColorMode((byte) 2);
                }
            }
            return quad;
        }

        public Quad createIconForBackgroundWithColor(Color color, Color color2) {
            Quad quad = new Quad(getIcon(), true);
            for (int i = 0; i < quad.getRegions().size; i++) {
                QuadRegion quadRegion = quad.getRegions().get(i);
                if ("shadow".equals(quadRegion.getQuadName())) {
                    quadRegion.setSameCornerColors(color);
                    quadRegion.setColorMode((byte) 2);
                } else {
                    quadRegion.setSameCornerColors(color2);
                    quadRegion.setColorMode((byte) 2);
                }
            }
            return quad;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GameValueEffect.class */
    public static class GameValueEffect implements KryoSerializable {
        public GameValueType type;
        public double delta;
        public Source source;

        @NAGS
        public ResearchType researchType;

        @NAGS
        public TrophyType trophyType;

        @NAGS
        public String questId;

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GameValueEffect$Source.class */
        public enum Source {
            STOCK,
            LEVEL_STOCK,
            RESEARCH,
            TROPHY,
            LEVEL_QUEST,
            LEVEL_WAVE_QUEST,
            BASE_TILE,
            CORE_TILE,
            BOSS_TILE,
            GV_TILE,
            CUSTOM;

            public static final Source[] values = values();
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.type, GameValueType.class);
            output.writeDouble(this.delta);
            kryo.writeObjectOrNull(output, this.source, Source.class);
            kryo.writeObjectOrNull(output, this.researchType, ResearchType.class);
            kryo.writeObjectOrNull(output, this.trophyType, TrophyType.class);
            kryo.writeObjectOrNull(output, this.questId, String.class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.type = (GameValueType) kryo.readObjectOrNull(input, GameValueType.class);
            this.delta = input.readDouble();
            this.source = (Source) kryo.readObjectOrNull(input, Source.class);
            this.researchType = (ResearchType) kryo.readObjectOrNull(input, ResearchType.class);
            this.trophyType = (TrophyType) kryo.readObjectOrNull(input, TrophyType.class);
            this.questId = (String) kryo.readObjectOrNull(input, String.class);
        }

        public GameValueEffect() {
        }

        public GameValueEffect(GameValueType gameValueType, double d) {
            this.type = gameValueType;
            this.delta = d;
        }

        public GameValueEffect(GameValueEffect gameValueEffect) {
            from(gameValueEffect);
        }

        public void from(GameValueEffect gameValueEffect) {
            this.type = gameValueEffect.type;
            this.delta = gameValueEffect.delta;
            this.source = gameValueEffect.source;
            this.researchType = gameValueEffect.researchType;
            this.trophyType = gameValueEffect.trophyType;
            this.questId = gameValueEffect.questId;
        }

        public boolean sameAs(GameValueEffect gameValueEffect) {
            if (gameValueEffect.type == this.type && gameValueEffect.delta == this.delta && gameValueEffect.source == this.source && gameValueEffect.researchType == this.researchType && gameValueEffect.trophyType == this.trophyType) {
                return gameValueEffect.questId == null || gameValueEffect.questId.equals(this.questId);
            }
            return false;
        }

        public void setup(GameValueType gameValueType, double d, Source source) {
            this.type = gameValueType;
            this.delta = d;
            this.source = source;
        }

        public String toString() {
            String str = this.type.name() + SequenceUtils.SPACE + (this.delta > 0.0d ? "+" : "") + this.delta;
            if (this.source != null) {
                str = str + " (source: " + this.source.name() + ")";
            }
            if (this.researchType != null) {
                str = str + " research: " + this.researchType.name();
            }
            if (this.trophyType != null) {
                str = str + " trophy: " + this.trophyType.name();
            }
            if (this.questId != null) {
                str = str + " quest: " + this.questId;
            }
            return str;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GameValuesSnapshot.class */
    public static final class GameValuesSnapshot implements KryoSerializable, GameValueProvider {
        public double[] values;
        public int hash;
        public DelayedRemovalArray<GameValueEffect> effects;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeInt(this.hash);
            kryo.writeObject(output, this.values);
            kryo.writeObject(output, this.effects);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.hash = input.readInt();
            this.values = (double[]) kryo.readObject(input, double[].class);
            this.effects = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        }

        public GameValuesSnapshot() {
            this.values = new double[GameValueType.values.length];
            this.effects = new DelayedRemovalArray<>(GameValueEffect.class);
        }

        public GameValuesSnapshot(GameValuesSnapshot gameValuesSnapshot) {
            this.values = new double[GameValueType.values.length];
            this.effects = new DelayedRemovalArray<>(GameValueEffect.class);
            System.arraycopy(gameValuesSnapshot.values, 0, this.values, 0, this.values.length);
            this.hash = gameValuesSnapshot.hash;
            this.effects.addAll(gameValuesSnapshot.effects);
        }

        public final void from(GameValuesSnapshot gameValuesSnapshot) {
            System.arraycopy(gameValuesSnapshot.values, 0, this.values, 0, this.values.length);
            this.hash = gameValuesSnapshot.hash;
            this.effects.clear();
            this.effects.addAll(gameValuesSnapshot.effects);
        }

        public final String toJson() {
            StringWriter stringWriter = new StringWriter();
            Json json = new Json(JsonWriter.OutputType.json);
            json.setWriter(stringWriter);
            json.writeObjectStart();
            json.writeValue("hash", Integer.valueOf(this.hash));
            json.writeObjectStart("values");
            for (int i = 0; i < this.values.length; i++) {
                double d = this.values[i];
                if (d != 0.0d) {
                    json.writeValue(GameValueType.values[i].name(), Double.valueOf(d));
                }
            }
            json.writeObjectEnd();
            json.writeObjectEnd();
            return stringWriter.toString();
        }

        public static GameValuesSnapshot fromJson(String str) {
            JsonValue parse = new JsonReader().parse(str);
            GameValuesSnapshot gameValuesSnapshot = new GameValuesSnapshot();
            gameValuesSnapshot.hash = parse.getInt("hash");
            Iterator<JsonValue> iterator2 = parse.get("values").iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                try {
                    gameValuesSnapshot.values[GameValueType.valueOf(next.name).ordinal()] = next.asDouble();
                } catch (Exception unused) {
                    GameValueManager.f2335a.e("failed to parse " + next.toString(), new Object[0]);
                }
            }
            return gameValuesSnapshot;
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final double getValue(GameValueType gameValueType) {
            return this.values[gameValueType.ordinal()];
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final boolean getBooleanValue(GameValueType gameValueType) {
            return this.values[gameValueType.ordinal()] != 0.0d;
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final int getIntValue(GameValueType gameValueType) {
            return (int) getValue(gameValueType);
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final int getIntValueSum(GameValueType gameValueType, GameValueType gameValueType2) {
            return (int) (this.values[gameValueType.ordinal()] + this.values[gameValueType2.ordinal()]);
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final float getFloatValue(GameValueType gameValueType) {
            return (float) this.values[gameValueType.ordinal()];
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final float getFloatValueSum(GameValueType gameValueType, GameValueType gameValueType2) {
            return (float) (this.values[gameValueType.ordinal()] + this.values[gameValueType2.ordinal()]);
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final double getPercentValueAsMultiplier(GameValueType gameValueType) {
            return this.values[gameValueType.ordinal()] * 0.01d;
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final double getPercentValueAsMultiplierSum(GameValueType gameValueType, GameValueType gameValueType2) {
            return (this.values[gameValueType.ordinal()] + this.values[gameValueType2.ordinal()]) * 0.01d;
        }

        @Override // com.prineside.tdi2.GameValueProvider
        public final double getPercentValueAsMultiplierSumAll(GameValueType[] gameValueTypeArr) {
            double d = 0.0d;
            for (GameValueType gameValueType : gameValueTypeArr) {
                d += this.values[gameValueType.ordinal()];
            }
            return d * 0.01d;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GameValuesSnapshot: {\n");
            for (GameValueType gameValueType : GameValueType.values) {
                stringBuilder.append("  ").append(gameValueType.name()).append(" = ").append(this.values[gameValueType.ordinal()]).append(SequenceUtils.EOL);
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Array<GvSnapDiff> getDifferences(GameValuesSnapshot gameValuesSnapshot) {
            Array<GvSnapDiff> array = new Array<>(GvSnapDiff.class);
            if (gameValuesSnapshot.hash != this.hash) {
                array.add(new GvSnapDiff(GvSnapDiff.Type.GLOBAL_HASH, this.hash, gameValuesSnapshot.hash));
            }
            for (int i = 0; i < this.values.length; i++) {
                if (this.values[i] != gameValuesSnapshot.values[i]) {
                    array.add(new GvSnapDiff(GvSnapDiff.Type.GV_RESULT, this.values[i], gameValuesSnapshot.values[i]));
                }
            }
            if (gameValuesSnapshot.effects.size != this.effects.size) {
                array.add(new GvSnapDiff(GvSnapDiff.Type.GV_COUNT, this.effects.size, gameValuesSnapshot.effects.size));
            }
            Array array2 = new Array(false, this.effects.size, GameValueEffect.class);
            for (int i2 = 0; i2 < this.effects.size; i2++) {
                array2.add(new GameValueEffect(this.effects.items[i2]));
            }
            for (int i3 = 0; i3 < gameValuesSnapshot.effects.size; i3++) {
                GameValueEffect gameValueEffect = gameValuesSnapshot.effects.items[i3];
                boolean z = false;
                int i4 = 0;
                while (true) {
                    if (i4 >= array2.size) {
                        break;
                    }
                    if (!((GameValueEffect[]) array2.items)[i4].sameAs(gameValueEffect)) {
                        i4++;
                    } else {
                        array2.removeIndex(i4);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    array.add(new GvSnapDiff(GvSnapDiff.Type.GV_NOT_IN_ORIG, gameValueEffect));
                }
            }
            for (int i5 = 0; i5 < array2.size; i5++) {
                array.add(new GvSnapDiff(GvSnapDiff.Type.GV_NOT_IN_TO, ((GameValueEffect[]) array2.items)[i5]));
            }
            return array;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void printDifferences(String str, String str2, GameValuesSnapshot gameValuesSnapshot, StringBuilder stringBuilder) {
            if (gameValuesSnapshot.hash != this.hash) {
                stringBuilder.append("hash (").append(str2).append(") ").append(String.valueOf(gameValuesSnapshot.hash)).append(" != (").append(str).append(") ").append(String.valueOf(this.hash)).append(SequenceUtils.EOL);
            }
            for (int i = 0; i < this.values.length; i++) {
                if (this.values[i] != gameValuesSnapshot.values[i]) {
                    stringBuilder.append("value ").append(GameValueType.values[i].name()).append(SequenceUtils.SPACE).append(String.valueOf(gameValuesSnapshot.values[i])).append(" != ").append(String.valueOf(this.values[i])).append(SequenceUtils.EOL);
                }
            }
            if (gameValuesSnapshot.effects.size != this.effects.size) {
                stringBuilder.append("effects count ").append(String.valueOf(gameValuesSnapshot.effects.size)).append(" != ").append(String.valueOf(this.effects.size)).append(SequenceUtils.EOL);
            }
            Array array = new Array(false, this.effects.size, GameValueEffect.class);
            for (int i2 = 0; i2 < this.effects.size; i2++) {
                array.add(new GameValueEffect(this.effects.items[i2]));
            }
            for (int i3 = 0; i3 < gameValuesSnapshot.effects.size; i3++) {
                GameValueEffect gameValueEffect = gameValuesSnapshot.effects.items[i3];
                boolean z = false;
                int i4 = 0;
                while (true) {
                    if (i4 >= array.size) {
                        break;
                    }
                    if (!((GameValueEffect[]) array.items)[i4].sameAs(gameValueEffect)) {
                        i4++;
                    } else {
                        array.removeIndex(i4);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    stringBuilder.append("not found effect in ").append(str).append(": ").append(gameValueEffect.toString()).append(SequenceUtils.EOL);
                }
            }
            for (int i5 = 0; i5 < array.size; i5++) {
                stringBuilder.append("not found effect in ").append(str2).append(": ").append(((GameValueEffect[]) array.items)[i5].toString()).append(SequenceUtils.EOL);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$_ResearchManagerListener.class */
    private class _ResearchManagerListener extends ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter {
        private _ResearchManagerListener() {
        }

        /* synthetic */ _ResearchManagerListener(GameValueManager gameValueManager, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener.ResearchManagerListenerAdapter, com.prineside.tdi2.managers.ResearchManager.ResearchManagerListener
        public void researchesUpdated() {
            GameValueManager.this.requireRecalculation();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$_ProgressManagerListener.class */
    private class _ProgressManagerListener extends ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter {
        private _ProgressManagerListener() {
        }

        /* synthetic */ _ProgressManagerListener(GameValueManager gameValueManager, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
        public void itemsChanged(Item item, int i, int i2) {
            if (item.getType() == ItemType.TROPHY) {
                GameValueManager.this.requireRecalculation();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GvSnapDiff.class */
    public static class GvSnapDiff {
        public Type type;
        public double vOrig;
        public double vTo;
        public GameValueEffect effect;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/GameValueManager$GvSnapDiff$Type.class */
        public enum Type {
            GLOBAL_HASH,
            GV_COUNT,
            GV_RESULT,
            GV_NOT_IN_ORIG,
            GV_NOT_IN_TO
        }

        public GvSnapDiff(Type type, double d, double d2) {
            this.type = type;
            this.vOrig = d;
            this.vTo = d2;
        }

        public GvSnapDiff(Type type, GameValueEffect gameValueEffect) {
            this.type = type;
            this.effect = gameValueEffect;
        }
    }
}

package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.ui.components.QuestList;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem.class */
public final class QuestSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3038a = TLog.forClass(QuestSystem.class);
    private boolean d;
    private BasicLevelWaveQuest f;
    private int g;
    private int h;

    /* renamed from: b, reason: collision with root package name */
    private final DelayedRemovalArray<QuestEntry> f3039b = new DelayedRemovalArray<>(true, 1, QuestEntry.class);
    private final DelayedRemovalArray<DelayedQuestRemoveEntry> c = new DelayedRemovalArray<>(false, 1, DelayedQuestRemoveEntry.class);
    private int e = 0;
    private ObjectSet<String> i = new ObjectSet<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$Quest.class */
    public interface Quest {
        String getTitle();

        void update();

        boolean isCompleted();

        void onCompletion();
    }

    static /* synthetic */ int a(QuestSystem questSystem, int i) {
        int i2 = questSystem.g + i;
        questSystem.g = i2;
        return i2;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (this.S.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            this.g = Game.i.basicLevelManager.getGainedStarsOnLevel(Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName));
        }
        a();
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.QUEST_DRAW, false, (batch, f, f2, f3) -> {
            draw(f2, f);
        }).setName("Quest-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        Quest createIngameQuest;
        if (GameStateSystem.GameMode.isBasicLevel(this.S.gameState.gameMode)) {
            int i = 0;
            int intValue = this.S.gameValue.getIntValue(GameValueType.REGULAR_QUESTS_SLOTS);
            BasicLevel level = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName);
            boolean z = true;
            int i2 = 0;
            while (true) {
                if (i2 >= level.waveQuests.size) {
                    break;
                }
                if (level.waveQuests.items[i2].isCompleted()) {
                    i2++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                f3038a.i("all WQ are completed, +1 quest slot", new Object[0]);
                intValue++;
            }
            for (int i3 = 0; i3 < level.quests.size; i3++) {
                BasicLevelQuestConfig basicLevelQuestConfig = level.quests.get(i3);
                if (!basicLevelQuestConfig.isCompleted() && (createIngameQuest = basicLevelQuestConfig.createIngameQuest(this.S)) != null) {
                    this.S._quest.addQuest(createIngameQuest);
                    i++;
                    if (i >= intValue) {
                        break;
                    }
                }
            }
        }
        update(0.0f);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.S._gameUi == null) {
            return;
        }
        if (this.S.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            this.S._gameUi.mainUi.setLevelStarsIcon(this.g);
        } else {
            this.S._gameUi.mainUi.setLevelStarsIcon(1);
        }
    }

    public final QuestEntry addQuest(Quest quest) {
        if (quest == null) {
            throw new IllegalArgumentException("Quest is null");
        }
        if (quest instanceof RegularQuest) {
            RegularQuest regularQuest = (RegularQuest) quest;
            if (!this.i.contains(regularQuest.f3046b)) {
                this.i.add(regularQuest.f3046b);
            } else {
                f3038a.e("warning: quest " + regularQuest.f3046b + " has been added to this game already", new Object[0]);
            }
        }
        QuestEntry questEntry = new QuestEntry();
        questEntry.f3044a = quest;
        if (this.S._gameUi != null) {
            QuestList.QuestListItem addQuestListItem = this.S._gameUi.questList.addQuestListItem();
            if (quest instanceof BasicLevelQuest) {
                BasicLevelQuest basicLevelQuest = (BasicLevelQuest) quest;
                if (Game.i.dailyQuestManager.getDailyLootCurrentQuest().equals(basicLevelQuest.f3040a.id)) {
                    addQuestListItem.setTitlePrefix(Game.i.assetManager.replaceRegionAliasesWithChars("[#03A9F4]<@icon-calendar>[] "));
                } else {
                    int i = 0;
                    while (true) {
                        if (i >= basicLevelQuest.f3040a.prizes.size) {
                            break;
                        }
                        if (!(basicLevelQuest.f3040a.prizes.items[i].getItem() instanceof StarItem)) {
                            i++;
                        } else {
                            addQuestListItem.setTitlePrefix(Game.i.assetManager.replaceRegionAliasesWithChars("[#FFC107]<@icon-star>[] "));
                            break;
                        }
                    }
                }
            } else if (quest instanceof BasicLevelWaveQuest) {
                BasicLevelWaveQuest basicLevelWaveQuest = (BasicLevelWaveQuest) quest;
                int i2 = 0;
                while (true) {
                    if (i2 >= basicLevelWaveQuest.f3041a.prizes.size) {
                        break;
                    }
                    if (!(basicLevelWaveQuest.f3041a.prizes.items[i2].getItem() instanceof StarItem)) {
                        i2++;
                    } else {
                        addQuestListItem.setTitlePrefix(Game.i.assetManager.replaceRegionAliasesWithChars("[#FFC107]<@icon-star>[] "));
                        break;
                    }
                }
            }
            addQuestListItem.setText(quest.getTitle());
            questEntry.f3045b = addQuestListItem;
        }
        this.f3039b.add(questEntry);
        return questEntry;
    }

    public final void removeQuest(Quest quest) {
        this.f3039b.begin();
        for (int i = 0; i < this.f3039b.size; i++) {
            if (this.f3039b.items[i].f3044a == quest) {
                if (this.S._gameUi != null) {
                    this.S._gameUi.questList.removeQuestListItem(this.f3039b.items[i].f3045b);
                }
                this.f3039b.removeIndex(i);
            }
        }
        this.f3039b.end();
    }

    public final void removeQuestWithDelay(Quest quest, float f) {
        DelayedQuestRemoveEntry delayedQuestRemoveEntry = new DelayedQuestRemoveEntry((byte) 0);
        delayedQuestRemoveEntry.f3042a = quest;
        delayedQuestRemoveEntry.f3043b = f;
        this.c.add(delayedQuestRemoveEntry);
    }

    @Null
    public final QuestList.QuestListItem getListItem(Quest quest) {
        if (quest == null) {
            throw new IllegalArgumentException("Quest is null");
        }
        for (int i = 0; i < this.f3039b.size; i++) {
            if (this.f3039b.items[i].f3044a == quest) {
                return this.f3039b.items[i].f3045b;
            }
        }
        return null;
    }

    public final int getBasicLevelStars() {
        return this.g;
    }

    public final void draw(float f, float f2) {
        if (this.S == null || this.S.gameState == null || !this.S.gameState.isGameOver()) {
            float f3 = f2 * 1.25f;
            this.c.begin();
            for (int i = 0; i < this.c.size; i++) {
                DelayedQuestRemoveEntry delayedQuestRemoveEntry = this.c.get(i);
                DelayedQuestRemoveEntry.b(delayedQuestRemoveEntry, f3);
                if (delayedQuestRemoveEntry.f3043b <= 0.0f) {
                    removeQuest(delayedQuestRemoveEntry.f3042a);
                    this.c.removeIndex(i);
                }
            }
            this.c.end();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        Quest createIngameQuest;
        this.f3039b.begin();
        for (int i = 0; i < this.f3039b.size; i++) {
            QuestEntry questEntry = this.f3039b.items[i];
            questEntry.f3044a.update();
            if (!questEntry.c && questEntry.f3044a.isCompleted()) {
                QuestList.QuestListItem listItem = getListItem(questEntry.f3044a);
                if (listItem != null) {
                    listItem.setCompleted(true);
                }
                questEntry.f3044a.onCompletion();
                QuestEntry.a(questEntry, true);
            }
        }
        this.f3039b.end();
        if (this.S.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            if (this.f == null || this.f.isCompleted()) {
                BasicLevel.WaveQuest waveQuest = null;
                BasicLevel level = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName);
                int i2 = 0;
                while (true) {
                    if (i2 >= level.waveQuests.size) {
                        break;
                    }
                    BasicLevel.WaveQuest waveQuest2 = level.waveQuests.get(i2);
                    if (waveQuest2.waves <= this.e || waveQuest2.isCompleted()) {
                        i2++;
                    } else {
                        waveQuest = waveQuest2;
                        break;
                    }
                }
                if (waveQuest != null) {
                    if (this.f != null) {
                        removeQuestWithDelay(this.f, 2.0f);
                    }
                    BasicLevelWaveQuest createIngameQuest2 = waveQuest.createIngameQuest(this.S);
                    addQuest(createIngameQuest2);
                    this.f = createIngameQuest2;
                    this.e = waveQuest.waves;
                    createIngameQuest2.update();
                }
            }
            int intValue = this.S.gameValue.getIntValue(GameValueType.REGULAR_QUESTS_REPLACES);
            if (this.h < intValue) {
                boolean z = false;
                boolean z2 = false;
                this.f3039b.begin();
                int i3 = 0;
                loop1: while (true) {
                    if (i3 >= this.f3039b.size) {
                        break;
                    }
                    QuestEntry questEntry2 = this.f3039b.items[i3];
                    if ((questEntry2.f3044a instanceof BasicLevelQuest) && questEntry2.f3044a.isCompleted()) {
                        z = true;
                        BasicLevel level2 = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName);
                        for (int i4 = 0; i4 < level2.quests.size; i4++) {
                            BasicLevelQuestConfig basicLevelQuestConfig = level2.quests.get(i4);
                            if (!basicLevelQuestConfig.isCompleted() && !this.i.contains(basicLevelQuestConfig.id) && (createIngameQuest = basicLevelQuestConfig.createIngameQuest(this.S)) != null) {
                                addQuest(createIngameQuest);
                                removeQuestWithDelay(questEntry2.f3044a, 2.0f);
                                createIngameQuest.update();
                                this.h++;
                                z2 = true;
                                break loop1;
                            }
                        }
                    }
                    i3++;
                }
                this.f3039b.end();
                if (z && !z2) {
                    this.h = Math.max(this.h, intValue);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Quest";
    }

    public final void saveBasicLevelQuestValues() {
        if (this.d) {
            f3038a.e("failed to save quests progress", new RuntimeException("saveBasicLevelQuestValues() already called"));
            return;
        }
        this.d = true;
        ProgressPrefs.i();
        for (int i = 0; i < this.f3039b.size; i++) {
            QuestEntry questEntry = this.f3039b.items[i];
            if (questEntry.f3044a instanceof BasicLevelQuest) {
                BasicLevelQuest basicLevelQuest = (BasicLevelQuest) questEntry.f3044a;
                if (!basicLevelQuest.f3040a.isDuringGame()) {
                    basicLevelQuest.f3040a.setSavedValue((long) basicLevelQuest.getValue());
                } else {
                    basicLevelQuest.f3040a.setSavedValue((long) StrictMath.max(basicLevelQuest.getValue(), basicLevelQuest.f3040a.getSavedValue()));
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.f3039b.clear();
        this.c.clear();
        this.f = null;
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$DelayedQuestRemoveEntry.class */
    public static final class DelayedQuestRemoveEntry {

        /* renamed from: a, reason: collision with root package name */
        private Quest f3042a;

        /* renamed from: b, reason: collision with root package name */
        private float f3043b;

        private DelayedQuestRemoveEntry() {
        }

        /* synthetic */ DelayedQuestRemoveEntry(byte b2) {
            this();
        }

        static /* synthetic */ float b(DelayedQuestRemoveEntry delayedQuestRemoveEntry, float f) {
            float f2 = delayedQuestRemoveEntry.f3043b - f;
            delayedQuestRemoveEntry.f3043b = f2;
            return f2;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$QuestEntry.class */
    public static final class QuestEntry {

        /* renamed from: a, reason: collision with root package name */
        private Quest f3044a;

        /* renamed from: b, reason: collision with root package name */
        private QuestList.QuestListItem f3045b;
        private boolean c;

        static /* synthetic */ boolean a(QuestEntry questEntry, boolean z) {
            questEntry.c = true;
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$RegularQuest.class */
    public static abstract class RegularQuest implements Quest {

        /* renamed from: b, reason: collision with root package name */
        protected final String f3046b;
        protected final String c;
        protected final double d;
        protected final Array<ItemStack> e;

        /* renamed from: a, reason: collision with root package name */
        private double f3047a = -1.0d;
        private GameSystemProvider g;
        protected static final StringBuilder f = new StringBuilder();

        public abstract double getValue();

        public RegularQuest(String str, CharSequence charSequence, double d, Array<ItemStack> array, GameSystemProvider gameSystemProvider) {
            this.f3046b = str;
            this.c = charSequence.toString();
            this.e = array;
            this.d = d;
            this.g = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.Quest
        public String getTitle() {
            return this.c;
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.Quest
        public void update() {
            double value = getValue();
            if (this.f3047a != value) {
                QuestList.QuestListItem listItem = this.g._quest.getListItem(this);
                if (listItem != null) {
                    f.setLength(0);
                    f.append(this.c);
                    if (this.d > 1.0d) {
                        f.append(' ').append((long) StrictMath.min(value, this.d)).append('/').append((long) this.d);
                    }
                    listItem.setText(f);
                }
                this.f3047a = value;
            }
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.Quest
        public boolean isCompleted() {
            return getValue() >= this.d;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$BasicLevelQuest.class */
    public static class BasicLevelQuest extends RegularQuest {
        private BasicLevel g;

        /* renamed from: a, reason: collision with root package name */
        protected final BasicLevelQuestConfig f3040a;
        private GameSystemProvider h;
        private double i;

        public BasicLevelQuest(BasicLevel basicLevel, BasicLevelQuestConfig basicLevelQuestConfig, GameSystemProvider gameSystemProvider) {
            super(basicLevelQuestConfig.getId(), basicLevelQuestConfig.getTitle(false, true), basicLevelQuestConfig.getRequiredValue(gameSystemProvider.gameValue.getSnapshot()), basicLevelQuestConfig.getPrizes(gameSystemProvider.gameValue.getSnapshot()), gameSystemProvider);
            this.i = -1.0d;
            this.g = basicLevel;
            this.f3040a = basicLevelQuestConfig;
            this.h = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.RegularQuest
        public double getValue() {
            if (this.f3040a.isDuringGame()) {
                return this.h.statistics.getCurrentGameStatistic(this.f3040a.statisticsType);
            }
            return this.f3040a.getSavedValue() + this.h.statistics.getCurrentGameStatistic(this.f3040a.statisticsType);
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.RegularQuest, com.prineside.tdi2.systems.QuestSystem.Quest
        public void update() {
            double value = getValue();
            if (this.i != value) {
                QuestList.QuestListItem listItem = this.h._quest.getListItem(this);
                if (listItem != null) {
                    f.setLength(0);
                    f.append(this.c);
                    if (this.d > 1.0d) {
                        f.append(' ').append(this.f3040a.formatValueForUiWithRequiredValue((long) StrictMath.min(value, this.d), this.d, true));
                    }
                    listItem.setText(f);
                }
                this.i = value;
            }
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.Quest
        public void onCompletion() {
            this.h._quest.getListItem(this);
            this.h.gameState.addCompletedQuest(this.f3046b);
            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.QUEST, Game.getTimestampSeconds());
            issuedItems.items.addAll(this.e);
            int extraDustInEndless = this.f3040a.getExtraDustInEndless(this.h.gameValue);
            if (extraDustInEndless > 0 && DifficultyMode.isEndless(this.h.gameState.difficultyMode)) {
                issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, extraDustInEndless));
            }
            issuedItems.questBasicLevel = this.g.name;
            issuedItems.questId = this.f3046b;
            this.h.gameState.addCompletedQuestIssuedPrizes(issuedItems, 512.0f, Game.i.settingsManager.getScaledViewportHeight() - 240.0f, 16);
            for (int i = 0; i < this.e.size; i++) {
                if (this.e.get(i).getItem() instanceof StarItem) {
                    QuestSystem.a(this.h._quest, this.e.get(i).getCount());
                    this.h._quest.a();
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/QuestSystem$BasicLevelWaveQuest.class */
    public static class BasicLevelWaveQuest extends RegularQuest {

        /* renamed from: a, reason: collision with root package name */
        private final BasicLevel.WaveQuest f3041a;
        private final BasicLevel g;
        private final GameSystemProvider h;
        private double i;

        public BasicLevelWaveQuest(BasicLevel basicLevel, BasicLevel.WaveQuest waveQuest, GameSystemProvider gameSystemProvider) {
            super(waveQuest.id, Game.i.localeManager.i18n.get("defeat_waves"), waveQuest.waves, waveQuest.prizes, gameSystemProvider);
            this.i = -1.0d;
            this.g = basicLevel;
            this.f3041a = waveQuest;
            this.h = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.RegularQuest
        public double getValue() {
            return this.h.wave.getCompletedWavesCount();
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.RegularQuest, com.prineside.tdi2.systems.QuestSystem.Quest
        public void update() {
            double value = getValue();
            if (this.i != value) {
                QuestList.QuestListItem listItem = this.h._quest.getListItem(this);
                if (listItem != null) {
                    f.setLength(0);
                    f.append(this.c);
                    f.append(" [#90A4AE]").append((int) StrictMath.min(value, this.d)).append("[] / [#FFFFFF]").append((int) this.d).append("[]");
                    listItem.setText(f);
                }
                this.i = value;
            }
        }

        @Override // com.prineside.tdi2.systems.QuestSystem.Quest
        public void onCompletion() {
            this.h._quest.getListItem(this);
            this.h.gameState.addCompletedQuest(this.f3041a.id);
            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.WAVE_QUEST, Game.getTimestampSeconds());
            issuedItems.items.addAll(this.f3041a.prizes);
            issuedItems.waveQuestBasicLevel = this.g.name;
            issuedItems.waveQuestId = this.f3041a.id;
            this.h.gameState.addCompletedQuestIssuedPrizes(issuedItems, 512.0f, Game.i.settingsManager.getScaledViewportHeight() - 240.0f, 16);
            for (int i = 0; i < this.f3041a.prizes.size; i++) {
                if (this.f3041a.prizes.get(i).getItem() instanceof StarItem) {
                    QuestSystem.a(this.h._quest, this.f3041a.prizes.get(i).getCount());
                    this.h._quest.a();
                }
            }
        }
    }
}

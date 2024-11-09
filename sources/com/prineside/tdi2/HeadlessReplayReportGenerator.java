package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.game.EnemyLootAdd;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    */
/* loaded from: infinitode-2.jar:com/prineside/tdi2/HeadlessReplayReportGenerator.class */
public class HeadlessReplayReportGenerator {
    private static int d;
    private static int e;

    /* renamed from: a */
    private static final TLog f1720a = TLog.forClass(HeadlessReplayReportGenerator.class);

    /* renamed from: b */
    private static Array<Frame> f1721b = new Array<>(Frame.class);
    private static Array<PassedEnemy> c = new Array<>(PassedEnemy.class);
    private static final int[] f = new int[RarityType.values.length];

    public HeadlessReplayReportGenerator() {
    }

    static {
    }

    public static void start(GameSystemProvider gameSystemProvider) {
        f1720a.i("start", new Object[0]);
        f1721b.clear();
        c.clear();
        gameSystemProvider.events.getListeners(EnemyReachTarget.class).add(enemyReachTarget -> {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                PassedEnemy passedEnemy = new PassedEnemy((byte) 0);
                passedEnemy.enemyType = enemyReachTarget.getEnemy().type;
                passedEnemy.healthCoeff = enemyReachTarget.getEnemy().getHealth() / enemyReachTarget.getEnemy().maxHealth;
                passedEnemy.time = (float) gameSystemProvider.statistics.getCurrentGameStatistic(StatisticsType.PT);
                c.add(passedEnemy);
            }
        });
        gameSystemProvider.events.getListeners(EnemyLootAdd.class).add(enemyLootAdd -> {
            Item item = enemyLootAdd.getItem();
            int count = enemyLootAdd.getCount();
            if (item == Item.D.GREEN_PAPER) {
                e += count;
            } else if (item == Item.D.BIT_DUST) {
                d += count;
            }
            int[] iArr = f;
            int ordinal = item.getRarity().ordinal();
            iArr[ordinal] = iArr[ordinal] + 1;
        });
    }

    private static Frame a(GameSystemProvider gameSystemProvider) {
        Frame frame = new Frame((byte) 0);
        frame.h = (int) gameSystemProvider.damage.getTowersMaxDps();
        Frame.a(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.SG_EK));
        Frame.b(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.SG_RM));
        Frame.c(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.SG_WCA));
        Frame.d(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.SG_WCL));
        frame.e = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.CG_B);
        frame.f = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.CG_EK);
        frame.g = (int) gameSystemProvider.statistics.getStatistic(StatisticsType.CG_WC);
        Frame.e(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.XPG_EK));
        Frame.f(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.XPG_EM));
        Frame.g(frame, (long) gameSystemProvider.statistics.getStatistic(StatisticsType.XPG_TG));
        Frame.h(frame, (int) gameSystemProvider.statistics.getStatistic(StatisticsType.CG_U));
        frame.m = d;
        frame.n = e;
        System.arraycopy(f, 0, frame.o, 0, frame.o.length);
        return frame;
    }

    public static void interval(GameSystemProvider gameSystemProvider) {
        f1721b.add(a(gameSystemProvider));
    }

    public static String stop(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider == null) {
            f1720a.e("S is null", new Object[0]);
            return null;
        }
        f1720a.i("stop", new Object[0]);
        StringWriter stringWriter = new StringWriter();
        Json json = new Json(JsonWriter.OutputType.json);
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue("frameInterval", Integer.valueOf(HeadlessConfig.REPORT_INTERVAL));
        json.writeValue("researches", Integer.valueOf(gameSystemProvider.gameState.gameStartProgressSnapshot.getResearchLevelsCount()));
        json.writeValue("sumProgressTime", Integer.valueOf(gameSystemProvider.gameState.gameStartProgressSnapshot.statsPlayRealTime));
        json.writeArrayStart("frames");
        for (int i = 0; i < f1721b.size; i++) {
            Frame frame = f1721b.items[i];
            json.writeArrayStart();
            frame.a(json);
            json.writeArrayEnd();
        }
        json.writeArrayEnd();
        json.writeArrayStart("passedEnemies");
        for (int i2 = 0; i2 < c.size; i2++) {
            PassedEnemy passedEnemy = c.items[i2];
            json.writeObjectStart();
            json.writeValue("type", passedEnemy.enemyType.name());
            json.writeValue("health", Float.valueOf(passedEnemy.healthCoeff));
            json.writeValue("time", Float.valueOf(passedEnemy.time));
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeArrayStart("lastFrame");
        a(gameSystemProvider).a(json);
        json.writeArrayEnd();
        json.writeArrayStart("towers");
        for (int i3 = 0; i3 < gameSystemProvider.tower.towers.size; i3++) {
            Tower tower = gameSystemProvider.tower.towers.items[i3];
            json.writeObjectStart();
            json.writeValue("type", tower.type.name());
            json.writeValue("x", Integer.valueOf(tower.getTile().getX()));
            json.writeValue("y", Integer.valueOf(tower.getTile().getY()));
            json.writeValue("upgradeLevel", Byte.valueOf(tower.getUpgradeLevel()));
            json.writeValue("xpLevel", Short.valueOf(tower.getLevel()));
            json.writeValue("mdps", Float.valueOf(tower.mdps));
            json.writeValue("kills", Integer.valueOf(tower.enemiesKilled));
            json.writeValue("damage", Float.valueOf(tower.damageGiven));
            json.writeValue("moneySpent", Integer.valueOf(tower.moneySpentOn));
            json.writeValue("aimStrategy", tower.aimStrategy.name());
            json.writeArrayStart("abilities");
            for (int i4 = 0; i4 < 6; i4++) {
                if (tower.isAbilityInstalled(i4)) {
                    json.writeValue(Integer.valueOf(i4));
                }
            }
            json.writeArrayEnd();
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeArrayStart("modifiers");
        for (int i5 = 0; i5 < gameSystemProvider.modifier.modifiers.size; i5++) {
            Modifier modifier = gameSystemProvider.modifier.modifiers.items[i5];
            json.writeObjectStart();
            json.writeValue("type", modifier.type.name());
            json.writeValue("x", Integer.valueOf(modifier.getTile().getX()));
            json.writeValue("y", Integer.valueOf(modifier.getTile().getY()));
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        json.writeObjectStart("statistics");
        for (StatisticsType statisticsType : StatisticsType.values) {
            double statistic = gameSystemProvider.statistics.getStatistic(statisticsType);
            if (statistic != 0.0d) {
                json.writeValue(statisticsType.name(), Double.valueOf(statistic));
            }
        }
        json.writeObjectEnd();
        json.writeObjectStart("abilitiesUsed");
        for (int i6 = 0; i6 < gameSystemProvider.ability.abilitiesConfiguration.slots.length; i6++) {
            AbilityType abilityType = gameSystemProvider.ability.abilitiesConfiguration.slots[i6];
            if (abilityType != null && gameSystemProvider.ability.abilitiesUsed[i6] > 0) {
                json.writeValue(abilityType.name(), Integer.valueOf(gameSystemProvider.ability.abilitiesUsed[i6]));
            }
        }
        json.writeObjectEnd();
        json.writeObjectEnd();
        return stringWriter.toString();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/HeadlessReplayReportGenerator$Frame.class */
    public static class Frame {

        /* renamed from: a */
        private long f1722a;

        /* renamed from: b */
        private long f1723b;
        private long c;
        private long d;
        private int e;
        private int f;
        private int g;
        private int h;
        private long i;
        private long j;
        private long k;
        private long l;
        private int m;
        private int n;
        private final int[] o;

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.a(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long a(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.f1722a = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.a(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.b(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long b(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.f1723b = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.b(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.c(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long c(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.c = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.c(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.d(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long d(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.d = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.d(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.e(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long e(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.j = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.e(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.f(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long f(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.k = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.f(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.g(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long g(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.i = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.g(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI, method: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.h(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long
            java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
            	at java.base/java.lang.System.arraycopy(Native Method)
            	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
            	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
            	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
            	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
            	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:449)
            	at jadx.core.ProcessClass.process(ProcessClass.java:70)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
            */
        static /* synthetic */ long h(com.prineside.tdi2.HeadlessReplayReportGenerator.Frame r6, long r7) {
            /*
                r0 = r6
                r1 = r7
                // decode failed: arraycopy: source index -1 out of bounds for object array[6]
                r0.l = r1
                return r-1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.HeadlessReplayReportGenerator.Frame.h(com.prineside.tdi2.HeadlessReplayReportGenerator$Frame, long):long");
        }

        private Frame() {
            this.o = new int[RarityType.values.length];
        }

        /* synthetic */ Frame(byte b2) {
            this();
        }

        public void a(Json json) {
            json.writeValue(Long.valueOf(this.f1722a));
            json.writeValue(Long.valueOf(this.f1723b));
            json.writeValue(Long.valueOf(this.c));
            json.writeValue(Long.valueOf(this.d));
            json.writeValue(Integer.valueOf(this.e));
            json.writeValue(Integer.valueOf(this.f));
            json.writeValue(Integer.valueOf(this.g));
            json.writeValue(Integer.valueOf(this.h));
            json.writeValue(Long.valueOf(this.i));
            json.writeValue(Long.valueOf(this.j));
            json.writeValue(Long.valueOf(this.k));
            json.writeValue(Long.valueOf(this.l));
            json.writeValue(Integer.valueOf(this.m));
            json.writeValue(Integer.valueOf(this.n));
            json.writeValue(this.o);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/HeadlessReplayReportGenerator$PassedEnemy.class */
    public static class PassedEnemy {
        public EnemyType enemyType;
        public float healthCoeff;
        public float time;

        private PassedEnemy() {
        }

        /* synthetic */ PassedEnemy(byte b2) {
            this();
        }
    }
}

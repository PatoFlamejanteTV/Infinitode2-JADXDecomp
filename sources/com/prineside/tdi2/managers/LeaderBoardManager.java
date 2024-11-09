package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.HashMap;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager.class */
public class LeaderBoardManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2354a = TLog.forClass(LeaderBoardManager.class);

    /* renamed from: b, reason: collision with root package name */
    private SkillPointsLeaderboardsResult f2355b;
    private final Array<LeaderboardsResult> c = new Array<>(false, 1, LeaderboardsResult.class);
    private final Array<LeaderboardsRankResult> d = new Array<>(false, 1, LeaderboardsRankResult.class);
    private BasicLevelsTopLeaderboards e = new BasicLevelsTopLeaderboards(false);
    private boolean f = true;
    private DelayedRemovalArray<ObjectConsumer<BasicLevelsTopLeaderboards>> g = new DelayedRemovalArray<>(ObjectConsumer.class);
    private boolean h;

    static /* synthetic */ boolean a(LeaderBoardManager leaderBoardManager, boolean z) {
        leaderBoardManager.h = false;
        return false;
    }

    static /* synthetic */ void a(LeaderBoardManager leaderBoardManager) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<LeaderBoardManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public LeaderBoardManager read() {
            return Game.i.leaderBoardManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        Game.i.authManager.getNews(newsResponse -> {
            if (newsResponse != null && 208 < newsResponse.networkRequiredVersion) {
                this.f = false;
                f2354a.i("submit disabled - too low game version", new Object[0]);
            }
        });
    }

    public void removeBasicLevelsTopLeaderboardsRetriever(ObjectConsumer<BasicLevelsTopLeaderboards> objectConsumer) {
        this.g.removeValue(objectConsumer, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BasicLevelsTopLeaderboards basicLevelsTopLeaderboards) {
        this.g.begin();
        for (int i = 0; i < this.g.size; i++) {
            this.g.items[i].accept(basicLevelsTopLeaderboards);
        }
        this.g.end();
        this.g.clear();
    }

    public void getBasicLevelsTopLeaderboards(ObjectConsumer<BasicLevelsTopLeaderboards> objectConsumer) {
        final DifficultyMode difficultyMode = Game.i.progressManager.getDifficultyMode();
        if (this.e.success && this.e.difficultyMode == difficultyMode && Game.getTimestampSeconds() - this.e.requestTimestamp < 120) {
            objectConsumer.accept(this.e);
            return;
        }
        if (!this.g.contains(objectConsumer, true)) {
            this.g.add(objectConsumer);
        }
        if (this.h) {
            return;
        }
        this.h = true;
        final BasicLevelsTopLeaderboards basicLevelsTopLeaderboards = this.e;
        try {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GET_BASIC_LEVELS_TOP_LEADERBOARDS_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("mode", ReplayManager.LeaderboardsMode.score.name());
            hashMap.put("difficulty", difficultyMode.name());
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.LeaderBoardManager.1
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    LeaderBoardManager.a(LeaderBoardManager.this, false);
                    try {
                        String resultAsString = httpResponse.getResultAsString();
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!parse.getString("status").equals("success")) {
                            LeaderBoardManager.f2354a.e("can't retrieve leaderboards: " + resultAsString, new Object[0]);
                            Threads i = Threads.i();
                            BasicLevelsTopLeaderboards basicLevelsTopLeaderboards2 = basicLevelsTopLeaderboards;
                            i.runOnMainThread(() -> {
                                LeaderBoardManager.this.a(basicLevelsTopLeaderboards2);
                            });
                            return;
                        }
                        BasicLevelsTopLeaderboards basicLevelsTopLeaderboards3 = new BasicLevelsTopLeaderboards(true);
                        basicLevelsTopLeaderboards3.difficultyMode = difficultyMode;
                        Iterator<JsonValue> iterator2 = parse.get("levels").iterator2();
                        while (iterator2.hasNext()) {
                            JsonValue next = iterator2.next();
                            String string = next.getString("level");
                            JsonValue jsonValue = next.get("leaderboards");
                            Array<LeaderboardsEntry> array = new Array<>();
                            int i2 = 1;
                            Iterator<JsonValue> iterator22 = jsonValue.iterator2();
                            while (iterator22.hasNext()) {
                                JsonValue next2 = iterator22.next();
                                int i3 = i2;
                                i2++;
                                array.add(new LeaderboardsEntry(next2.getString("playerid"), next2.getString("nickname"), next2.getLong("score"), i3, false, 1));
                            }
                            basicLevelsTopLeaderboards3.leaderboards.put(string, array);
                        }
                        Threads.i().runOnMainThread(() -> {
                            LeaderBoardManager.this.e = basicLevelsTopLeaderboards3;
                            LeaderBoardManager.a(LeaderBoardManager.this);
                            LeaderBoardManager.this.a(basicLevelsTopLeaderboards3);
                        });
                    } catch (Exception e) {
                        LeaderBoardManager.f2354a.e("Failed to parse response", e);
                        Threads i4 = Threads.i();
                        BasicLevelsTopLeaderboards basicLevelsTopLeaderboards4 = basicLevelsTopLeaderboards;
                        i4.runOnMainThread(() -> {
                            LeaderBoardManager.this.a(basicLevelsTopLeaderboards4);
                        });
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    LeaderBoardManager.a(LeaderBoardManager.this, false);
                    LeaderBoardManager.f2354a.e("Error while getting leaderboards", th);
                    Threads i = Threads.i();
                    BasicLevelsTopLeaderboards basicLevelsTopLeaderboards2 = basicLevelsTopLeaderboards;
                    i.runOnMainThread(() -> {
                        LeaderBoardManager.this.a(basicLevelsTopLeaderboards2);
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    LeaderBoardManager.a(LeaderBoardManager.this, false);
                    LeaderBoardManager.f2354a.e("Timeout while getting leaderboards", new Object[0]);
                    Threads i = Threads.i();
                    BasicLevelsTopLeaderboards basicLevelsTopLeaderboards2 = basicLevelsTopLeaderboards;
                    i.runOnMainThread(() -> {
                        LeaderBoardManager.this.a(basicLevelsTopLeaderboards2);
                    });
                }
            });
        } catch (Exception e) {
            this.h = false;
            f2354a.e("Failed to get leaderboards", e);
            a(basicLevelsTopLeaderboards);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0122 A[Catch: Exception -> 0x0166, TryCatch #0 {Exception -> 0x0166, blocks: (B:25:0x00c3, B:27:0x0122, B:28:0x0133), top: B:24:0x00c3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getLeaderboards(final com.prineside.tdi2.systems.GameStateSystem.GameMode r14, final com.prineside.tdi2.enums.DifficultyMode r15, final java.lang.String r16, final com.prineside.tdi2.managers.ReplayManager.LeaderboardsMode r17, final com.prineside.tdi2.utils.ObjectConsumer<com.prineside.tdi2.managers.LeaderBoardManager.LeaderboardsResult> r18) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.LeaderBoardManager.getLeaderboards(com.prineside.tdi2.systems.GameStateSystem$GameMode, com.prineside.tdi2.enums.DifficultyMode, java.lang.String, com.prineside.tdi2.managers.ReplayManager$LeaderboardsMode, com.prineside.tdi2.utils.ObjectConsumer):void");
    }

    public void getSkillPointLeaderboards(final ObjectConsumer<SkillPointsLeaderboardsResult> objectConsumer) {
        SkillPointsLeaderboardsResult skillPointsLeaderboardsResult = new SkillPointsLeaderboardsResult(Game.i.authManager.getPlayerId(), false, (byte) 0);
        if (this.f2355b != null && ((!Game.i.authManager.isSignedIn() && this.f2355b.playerId == null) || (Game.i.authManager.isSignedIn() && this.f2355b.playerId != null && this.f2355b.playerId.equals(Game.i.authManager.getPlayerId())))) {
            if (Game.getTimestampSeconds() - this.f2355b.requestTimestamp < 30) {
                objectConsumer.accept(this.f2355b);
                return;
            }
            skillPointsLeaderboardsResult = this.f2355b;
        }
        final SkillPointsLeaderboardsResult skillPointsLeaderboardsResult2 = skillPointsLeaderboardsResult;
        try {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GET_SKILL_POINT_LEADER_BOARD_URL);
            if (Game.i.authManager.isSignedIn()) {
                HashMap hashMap = new HashMap();
                hashMap.put("playerid", Game.i.authManager.getPlayerId());
                httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            }
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.LeaderBoardManager.3
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    try {
                        String resultAsString = httpResponse.getResultAsString();
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!parse.getString("status").equals("success")) {
                            LeaderBoardManager.f2354a.e("can't retrieve skill point leaderboards: " + resultAsString, new Object[0]);
                            Threads i = Threads.i();
                            ObjectConsumer objectConsumer2 = objectConsumer;
                            SkillPointsLeaderboardsResult skillPointsLeaderboardsResult3 = skillPointsLeaderboardsResult2;
                            i.runOnMainThread(() -> {
                                objectConsumer2.accept(skillPointsLeaderboardsResult3);
                            });
                            return;
                        }
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer3 = objectConsumer;
                        i2.runOnMainThread(() -> {
                            SkillPointsLeaderboardsResult skillPointsLeaderboardsResult4 = new SkillPointsLeaderboardsResult(Game.i.authManager.getPlayerId(), true, (byte) 0);
                            if (parse.has("player")) {
                                JsonValue jsonValue = parse.get("player");
                                skillPointsLeaderboardsResult4.player = new SkillPointsLeaderboardsRank(true, Game.i.authManager.getPlayerId(), (byte) 0);
                                skillPointsLeaderboardsResult4.player.score = jsonValue.getInt("score");
                            }
                            JsonValue jsonValue2 = parse.get("leaderboards");
                            int i3 = 1;
                            Iterator<JsonValue> iterator2 = jsonValue2.iterator2();
                            while (iterator2.hasNext()) {
                                JsonValue next = iterator2.next();
                                int i4 = i3;
                                i3++;
                                skillPointsLeaderboardsResult4.entries.add(new LeaderboardsEntry(next.getString("playerid"), next.getString("nickname"), next.getInt("score"), i4, next.getBoolean("hasPfp", false), next.getInt("level", 1)));
                            }
                            LeaderBoardManager.this.f2355b = skillPointsLeaderboardsResult4;
                            LeaderBoardManager.a(LeaderBoardManager.this);
                            objectConsumer3.accept(skillPointsLeaderboardsResult4);
                        });
                    } catch (Exception e) {
                        LeaderBoardManager.f2354a.e("Failed to parse response", e);
                        Threads i3 = Threads.i();
                        ObjectConsumer objectConsumer4 = objectConsumer;
                        SkillPointsLeaderboardsResult skillPointsLeaderboardsResult4 = skillPointsLeaderboardsResult2;
                        i3.runOnMainThread(() -> {
                            objectConsumer4.accept(skillPointsLeaderboardsResult4);
                        });
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    LeaderBoardManager.f2354a.e("Error while getting skill point leaderboards", th);
                    Threads i = Threads.i();
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    SkillPointsLeaderboardsResult skillPointsLeaderboardsResult3 = skillPointsLeaderboardsResult2;
                    i.runOnMainThread(() -> {
                        objectConsumer2.accept(skillPointsLeaderboardsResult3);
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    LeaderBoardManager.f2354a.e("Timeout while getting skill point leaderboards", new Object[0]);
                    Threads i = Threads.i();
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    SkillPointsLeaderboardsResult skillPointsLeaderboardsResult3 = skillPointsLeaderboardsResult2;
                    i.runOnMainThread(() -> {
                        objectConsumer2.accept(skillPointsLeaderboardsResult3);
                    });
                }
            });
        } catch (Exception e) {
            f2354a.e("Failed to get skill point leaderboards", e);
            objectConsumer.accept(skillPointsLeaderboardsResult2);
        }
    }

    public void getLeaderboardsRank(final GameStateSystem.GameMode gameMode, final DifficultyMode difficultyMode, final String str, final ReplayManager.LeaderboardsMode leaderboardsMode, final ObjectConsumer<LeaderboardsRankResult> objectConsumer) {
        final LeaderboardsRankResult leaderboardsRankResult;
        LeaderboardsRankResult leaderboardsRankResult2 = new LeaderboardsRankResult(false, difficultyMode, leaderboardsMode, false, gameMode, str, Game.i.authManager.getPlayerId(), (byte) 0);
        for (int i = 0; i < this.d.size; i++) {
            LeaderboardsRankResult leaderboardsRankResult3 = this.d.items[i];
            if (leaderboardsRankResult3.gameMode == gameMode && leaderboardsRankResult3.mapName.equals(str) && leaderboardsMode == leaderboardsRankResult3.mode && ((!Game.i.authManager.isSignedIn() && leaderboardsRankResult3.playerId == null) || (Game.i.authManager.isSignedIn() && leaderboardsRankResult3.playerId != null && leaderboardsRankResult3.playerId.equals(Game.i.authManager.getPlayerId())))) {
                if (Game.getTimestampSeconds() - leaderboardsRankResult3.requestTimestamp < 30) {
                    objectConsumer.accept(leaderboardsRankResult3);
                    return;
                }
                leaderboardsRankResult2 = leaderboardsRankResult3;
                leaderboardsRankResult = leaderboardsRankResult2;
                if (!Game.i.authManager.isSignedIn() && Game.i.authManager.getPlayerId() != null) {
                    try {
                        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                        httpRequest.setUrl(Config.GET_LEADERBOARDS_RANK_URL);
                        HashMap hashMap = new HashMap();
                        hashMap.put("gamemode", gameMode.name());
                        hashMap.put("difficulty", difficultyMode.name());
                        hashMap.put("mapname", String.valueOf(str));
                        hashMap.put("mode", leaderboardsMode.name());
                        hashMap.put("playerid", Game.i.authManager.getPlayerId());
                        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.LeaderBoardManager.4
                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                                try {
                                    String resultAsString = httpResponse.getResultAsString();
                                    JsonValue parse = new JsonReader().parse(resultAsString);
                                    if (!parse.getString("status").equals("success")) {
                                        LeaderBoardManager.f2354a.e("can't retrieve leaderboards rank: " + resultAsString, new Object[0]);
                                        Threads i2 = Threads.i();
                                        ObjectConsumer objectConsumer2 = objectConsumer;
                                        LeaderboardsRankResult leaderboardsRankResult4 = leaderboardsRankResult;
                                        i2.runOnMainThread(() -> {
                                            objectConsumer2.accept(leaderboardsRankResult4);
                                        });
                                        return;
                                    }
                                    Threads i3 = Threads.i();
                                    DifficultyMode difficultyMode2 = difficultyMode;
                                    ReplayManager.LeaderboardsMode leaderboardsMode2 = leaderboardsMode;
                                    GameStateSystem.GameMode gameMode2 = gameMode;
                                    String str2 = str;
                                    ObjectConsumer objectConsumer3 = objectConsumer;
                                    i3.runOnMainThread(() -> {
                                        JsonValue jsonValue = parse.get("player");
                                        LeaderboardsRankResult leaderboardsRankResult5 = new LeaderboardsRankResult(true, difficultyMode2, leaderboardsMode2, false, gameMode2, str2, Game.i.authManager.getPlayerId(), (byte) 0);
                                        leaderboardsRankResult5.rank = jsonValue.getInt("rank");
                                        leaderboardsRankResult5.score = jsonValue.getLong("score");
                                        leaderboardsRankResult5.total = jsonValue.getInt("total");
                                        LeaderBoardManager.this.d.add(leaderboardsRankResult5);
                                        LeaderBoardManager.a(LeaderBoardManager.this);
                                        objectConsumer3.accept(leaderboardsRankResult5);
                                    });
                                } catch (Exception e) {
                                    LeaderBoardManager.f2354a.e("Failed to parse response", e);
                                    Threads i4 = Threads.i();
                                    ObjectConsumer objectConsumer4 = objectConsumer;
                                    LeaderboardsRankResult leaderboardsRankResult5 = leaderboardsRankResult;
                                    i4.runOnMainThread(() -> {
                                        objectConsumer4.accept(leaderboardsRankResult5);
                                    });
                                }
                            }

                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                            public void failed(Throwable th) {
                                LeaderBoardManager.f2354a.e("Error while getting leaderboards rank", th);
                                Threads i2 = Threads.i();
                                ObjectConsumer objectConsumer2 = objectConsumer;
                                LeaderboardsRankResult leaderboardsRankResult4 = leaderboardsRankResult;
                                i2.runOnMainThread(() -> {
                                    objectConsumer2.accept(leaderboardsRankResult4);
                                });
                            }

                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                            public void cancelled() {
                                LeaderBoardManager.f2354a.e("Timeout while getting leaderboards rank", new Object[0]);
                                Threads i2 = Threads.i();
                                ObjectConsumer objectConsumer2 = objectConsumer;
                                LeaderboardsRankResult leaderboardsRankResult4 = leaderboardsRankResult;
                                i2.runOnMainThread(() -> {
                                    objectConsumer2.accept(leaderboardsRankResult4);
                                });
                            }
                        });
                        return;
                    } catch (Exception e) {
                        f2354a.e("Failed to get leaderboards rank", e);
                        objectConsumer.accept(leaderboardsRankResult);
                        return;
                    }
                }
                f2354a.e("not signed in, can't get rank", new Object[0]);
                objectConsumer.accept(leaderboardsRankResult);
            }
        }
        leaderboardsRankResult = leaderboardsRankResult2;
        if (!Game.i.authManager.isSignedIn()) {
        }
        f2354a.e("not signed in, can't get rank", new Object[0]);
        objectConsumer.accept(leaderboardsRankResult);
    }

    public void getLeaderboardsAdvanceRank(final GameStateSystem.GameMode gameMode, final DifficultyMode difficultyMode, final String str, final ReplayManager.LeaderboardsMode leaderboardsMode, long j, final ObjectConsumer<LeaderboardsRankResult> objectConsumer) {
        if (Game.i.authManager.isSignedIn()) {
            if (!this.f) {
                f2354a.e("submit disabled, can't get advance rank", new Object[0]);
                objectConsumer.accept(new LeaderboardsRankResult(false, difficultyMode, leaderboardsMode, true, gameMode, str, Game.i.authManager.getPlayerId(), (byte) 0));
                return;
            }
            try {
                Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                httpRequest.setUrl(Config.GET_LEADERBOARDS_ADVANCE_RANK_URL);
                HashMap hashMap = new HashMap();
                hashMap.put("gamemode", gameMode.name());
                hashMap.put("difficulty", Game.i.progressManager.getDifficultyMode().name());
                hashMap.put("mapname", String.valueOf(str));
                hashMap.put("mode", leaderboardsMode.name());
                hashMap.put("playerid", Game.i.authManager.getPlayerId());
                hashMap.put("score", String.valueOf(j));
                httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.LeaderBoardManager.5
                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        try {
                            String resultAsString = httpResponse.getResultAsString();
                            JsonValue parse = new JsonReader().parse(resultAsString);
                            if (!parse.getString("status").equals("success")) {
                                LeaderBoardManager.f2354a.e("can't retrieve advance leaderboards rank: " + resultAsString, new Object[0]);
                                Threads i = Threads.i();
                                ObjectConsumer objectConsumer2 = objectConsumer;
                                DifficultyMode difficultyMode2 = difficultyMode;
                                ReplayManager.LeaderboardsMode leaderboardsMode2 = leaderboardsMode;
                                GameStateSystem.GameMode gameMode2 = gameMode;
                                String str2 = str;
                                i.runOnMainThread(() -> {
                                    objectConsumer2.accept(new LeaderboardsRankResult(false, difficultyMode2, leaderboardsMode2, true, gameMode2, str2, Game.i.authManager.getPlayerId(), (byte) 0));
                                });
                                return;
                            }
                            Threads i2 = Threads.i();
                            DifficultyMode difficultyMode3 = difficultyMode;
                            ReplayManager.LeaderboardsMode leaderboardsMode3 = leaderboardsMode;
                            GameStateSystem.GameMode gameMode3 = gameMode;
                            String str3 = str;
                            ObjectConsumer objectConsumer3 = objectConsumer;
                            i2.runOnMainThread(() -> {
                                JsonValue jsonValue = parse.get("player");
                                LeaderboardsRankResult leaderboardsRankResult = new LeaderboardsRankResult(true, difficultyMode3, leaderboardsMode3, true, gameMode3, str3, Game.i.authManager.getPlayerId(), (byte) 0);
                                leaderboardsRankResult.rank = jsonValue.getInt("rank");
                                leaderboardsRankResult.total = jsonValue.getInt("total");
                                objectConsumer3.accept(leaderboardsRankResult);
                            });
                        } catch (Exception e) {
                            LeaderBoardManager.f2354a.e("Failed to parse response", e);
                            Threads i3 = Threads.i();
                            ObjectConsumer objectConsumer4 = objectConsumer;
                            DifficultyMode difficultyMode4 = difficultyMode;
                            ReplayManager.LeaderboardsMode leaderboardsMode4 = leaderboardsMode;
                            GameStateSystem.GameMode gameMode4 = gameMode;
                            String str4 = str;
                            i3.runOnMainThread(() -> {
                                objectConsumer4.accept(new LeaderboardsRankResult(false, difficultyMode4, leaderboardsMode4, true, gameMode4, str4, Game.i.authManager.getPlayerId(), (byte) 0));
                            });
                        }
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void failed(Throwable th) {
                        LeaderBoardManager.f2354a.e("Error while getting advance leaderboards rank", th);
                        Threads i = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        DifficultyMode difficultyMode2 = difficultyMode;
                        ReplayManager.LeaderboardsMode leaderboardsMode2 = leaderboardsMode;
                        GameStateSystem.GameMode gameMode2 = gameMode;
                        String str2 = str;
                        i.runOnMainThread(() -> {
                            objectConsumer2.accept(new LeaderboardsRankResult(false, difficultyMode2, leaderboardsMode2, true, gameMode2, str2, Game.i.authManager.getPlayerId(), (byte) 0));
                        });
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void cancelled() {
                        LeaderBoardManager.f2354a.e("Timeout while getting advance leaderboards rank", new Object[0]);
                        Threads i = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        DifficultyMode difficultyMode2 = difficultyMode;
                        ReplayManager.LeaderboardsMode leaderboardsMode2 = leaderboardsMode;
                        GameStateSystem.GameMode gameMode2 = gameMode;
                        String str2 = str;
                        i.runOnMainThread(() -> {
                            objectConsumer2.accept(new LeaderboardsRankResult(false, difficultyMode2, leaderboardsMode2, true, gameMode2, str2, Game.i.authManager.getPlayerId(), (byte) 0));
                        });
                    }
                });
                return;
            } catch (Exception e) {
                f2354a.e("Failed to get advance leaderboards rank", e);
                objectConsumer.accept(new LeaderboardsRankResult(false, difficultyMode, leaderboardsMode, true, gameMode, str, Game.i.authManager.getPlayerId(), (byte) 0));
                return;
            }
        }
        f2354a.e("not signed in, can't get advance rank", new Object[0]);
        objectConsumer.accept(new LeaderboardsRankResult(false, difficultyMode, leaderboardsMode, true, gameMode, str, Game.i.authManager.getPlayerId(), (byte) 0));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$BasicLevelsTopLeaderboards.class */
    public static class BasicLevelsTopLeaderboards {
        public boolean success;
        public DifficultyMode difficultyMode;
        public ObjectMap<String, Array<LeaderboardsEntry>> leaderboards = new ObjectMap<>();
        public int requestTimestamp = Game.getTimestampSeconds();

        public BasicLevelsTopLeaderboards(boolean z) {
            this.success = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void toJson(Json json) {
            json.writeValue("rt", Integer.valueOf(this.requestTimestamp));
            json.writeValue("dm", this.difficultyMode.name());
            json.writeArrayStart("l");
            ObjectMap.Entries<String, Array<LeaderboardsEntry>> it = this.leaderboards.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry next = it.next();
                json.writeArrayStart((String) next.key);
                Array array = (Array) next.value;
                for (int i = 0; i < array.size; i++) {
                    json.writeObjectStart();
                    ((LeaderboardsEntry) array.get(i)).toJson(json);
                    json.writeObjectEnd();
                }
                json.writeArrayEnd();
            }
            json.writeArrayEnd();
        }

        public static BasicLevelsTopLeaderboards fromJson(JsonValue jsonValue) {
            BasicLevelsTopLeaderboards basicLevelsTopLeaderboards = new BasicLevelsTopLeaderboards(true);
            basicLevelsTopLeaderboards.requestTimestamp = jsonValue.getInt("rt");
            basicLevelsTopLeaderboards.difficultyMode = DifficultyMode.valueOf(jsonValue.getString("dm"));
            Iterator<JsonValue> iterator2 = jsonValue.get("l").iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                String str = next.name;
                Array<LeaderboardsEntry> array = new Array<>();
                Iterator<JsonValue> iterator22 = next.iterator2();
                while (iterator22.hasNext()) {
                    array.add(LeaderboardsEntry.fromJson(iterator22.next()));
                }
                basicLevelsTopLeaderboards.leaderboards.put(str, array);
            }
            return basicLevelsTopLeaderboards;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$SkillPointsLeaderboardsResult.class */
    public static class SkillPointsLeaderboardsResult {
        public boolean success;
        public int requestTimestamp;
        public String playerId;
        public SkillPointsLeaderboardsRank player;
        public Array<LeaderboardsEntry> entries;

        /* synthetic */ SkillPointsLeaderboardsResult(String str, boolean z, byte b2) {
            this(str, z);
        }

        private SkillPointsLeaderboardsResult(String str, boolean z) {
            this.player = null;
            this.entries = new Array<>();
            this.playerId = str;
            this.success = z;
            this.requestTimestamp = Game.getTimestampSeconds();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$LeaderboardsResult.class */
    public static class LeaderboardsResult {
        public boolean success;
        public int requestTimestamp;
        public GameStateSystem.GameMode gameMode;
        public DifficultyMode difficultyMode;
        public String mapName;
        public String playerId;
        public ReplayManager.LeaderboardsMode mode;
        public LeaderboardsRankResult player;
        public Array<LeaderboardsEntry> entries;

        /* synthetic */ LeaderboardsResult(GameStateSystem.GameMode gameMode, DifficultyMode difficultyMode, String str, String str2, boolean z, ReplayManager.LeaderboardsMode leaderboardsMode, byte b2) {
            this(gameMode, difficultyMode, str, str2, z, leaderboardsMode);
        }

        private LeaderboardsResult(GameStateSystem.GameMode gameMode, DifficultyMode difficultyMode, String str, String str2, boolean z, ReplayManager.LeaderboardsMode leaderboardsMode) {
            this.player = null;
            this.entries = new Array<>();
            this.gameMode = gameMode;
            this.difficultyMode = difficultyMode;
            this.mapName = str;
            this.playerId = str2;
            this.success = z;
            this.mode = leaderboardsMode;
            this.requestTimestamp = Game.getTimestampSeconds();
        }

        public void toJson(Json json) {
            if (!this.success) {
                throw new IllegalStateException("This result is failed, can't save to json");
            }
            json.writeValue("rt", Integer.valueOf(this.requestTimestamp));
            json.writeValue("gm", this.gameMode.name());
            json.writeValue("dm", this.difficultyMode.name());
            json.writeValue("mn", this.mapName);
            if (this.playerId != null) {
                json.writeValue("pi", this.playerId);
            }
            json.writeValue("m", this.mode.name());
            if (this.player != null) {
                json.writeObjectStart(FlexmarkHtmlConverter.P_NODE);
                this.player.toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayStart("e");
            for (int i = 0; i < this.entries.size; i++) {
                json.writeObjectStart();
                this.entries.get(i).toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public static LeaderboardsResult fromJson(JsonValue jsonValue) {
            GameStateSystem.GameMode valueOf = GameStateSystem.GameMode.valueOf(jsonValue.getString("gm"));
            String string = jsonValue.getString("mn");
            String string2 = jsonValue.getString("pi", null);
            ReplayManager.LeaderboardsMode valueOf2 = ReplayManager.LeaderboardsMode.valueOf(jsonValue.getString("m"));
            DifficultyMode difficultyMode = DifficultyMode.NORMAL;
            try {
                difficultyMode = DifficultyMode.valueOf(jsonValue.getString("dm"));
            } catch (Exception unused) {
            }
            LeaderboardsResult leaderboardsResult = new LeaderboardsResult(valueOf, difficultyMode, string, string2, true, valueOf2);
            if (jsonValue.has(FlexmarkHtmlConverter.P_NODE)) {
                leaderboardsResult.player = LeaderboardsRankResult.fromJson(jsonValue.get(FlexmarkHtmlConverter.P_NODE));
            }
            Iterator<JsonValue> iterator2 = jsonValue.get("e").iterator2();
            while (iterator2.hasNext()) {
                leaderboardsResult.entries.add(LeaderboardsEntry.fromJson(iterator2.next()));
            }
            leaderboardsResult.requestTimestamp = jsonValue.getInt("rt");
            return leaderboardsResult;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$LeaderboardsEntry.class */
    public static class LeaderboardsEntry {
        public String playerId;
        public String nickname;
        public boolean hasProfilePicture;
        public int profileLevel;
        public long score;
        public int rank;
        public String badgeIconTexture;
        public Color badgeIconColor;
        public String badgeOverlayTexture;
        public Color badgeOverlayColor;

        public LeaderboardsEntry(String str, String str2, long j, int i, boolean z, int i2) {
            this.playerId = str;
            this.nickname = str2;
            this.score = j;
            this.rank = i;
            this.hasProfilePicture = z;
            this.profileLevel = i2;
            if (this.profileLevel <= 0) {
                this.profileLevel = 1;
            }
        }

        public void toJson(Json json) {
            json.writeValue(FlexmarkHtmlConverter.P_NODE, this.playerId);
            json.writeValue("n", this.nickname);
            json.writeValue("s", Long.valueOf(this.score));
            json.writeValue("r", Integer.valueOf(this.rank));
            json.writeValue("hpp", Boolean.valueOf(this.hasProfilePicture));
            json.writeValue("pl", Integer.valueOf(this.profileLevel));
            if (this.badgeIconTexture != null) {
                json.writeValue("bit", this.badgeIconTexture);
            }
            if (this.badgeIconColor != null) {
                json.writeValue("bic", this.badgeIconColor.toString());
            }
            if (this.badgeOverlayTexture != null) {
                json.writeValue("bot", this.badgeOverlayTexture);
            }
            if (this.badgeOverlayColor != null) {
                json.writeValue("boc", this.badgeOverlayColor);
            }
        }

        public static LeaderboardsEntry fromJson(JsonValue jsonValue) {
            LeaderboardsEntry leaderboardsEntry = new LeaderboardsEntry(jsonValue.getString(FlexmarkHtmlConverter.P_NODE), jsonValue.getString("n"), jsonValue.getLong("s"), jsonValue.getInt("r"), jsonValue.getBoolean("hpp"), jsonValue.getInt("pl"));
            leaderboardsEntry.badgeIconTexture = jsonValue.getString("bit", null);
            leaderboardsEntry.badgeIconColor = Color.valueOf(jsonValue.getString("bic", "FFFFFFFF"));
            leaderboardsEntry.badgeOverlayTexture = jsonValue.getString("bot", null);
            leaderboardsEntry.badgeOverlayColor = Color.valueOf(jsonValue.getString("boc", "FFFFFFFF"));
            return leaderboardsEntry;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$SkillPointsLeaderboardsRank.class */
    public static class SkillPointsLeaderboardsRank {
        public boolean success;
        public int requestTimestamp;
        public String playerId;
        public int score;

        /* synthetic */ SkillPointsLeaderboardsRank(boolean z, String str, byte b2) {
            this(true, str);
        }

        private SkillPointsLeaderboardsRank(boolean z, String str) {
            this.playerId = str;
            this.success = z;
            this.requestTimestamp = Game.getTimestampSeconds();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LeaderBoardManager$LeaderboardsRankResult.class */
    public static class LeaderboardsRankResult {
        public boolean success;
        public int requestTimestamp;
        public ReplayManager.LeaderboardsMode mode;
        public boolean isAdvance;
        public GameStateSystem.GameMode gameMode;
        public DifficultyMode difficultyMode;
        public String mapName;
        public String playerId;
        public int rank;
        public long score;
        public int total;

        /* synthetic */ LeaderboardsRankResult(boolean z, DifficultyMode difficultyMode, ReplayManager.LeaderboardsMode leaderboardsMode, boolean z2, GameStateSystem.GameMode gameMode, String str, String str2, byte b2) {
            this(z, difficultyMode, leaderboardsMode, z2, gameMode, str, str2);
        }

        private LeaderboardsRankResult(boolean z, DifficultyMode difficultyMode, ReplayManager.LeaderboardsMode leaderboardsMode, boolean z2, GameStateSystem.GameMode gameMode, String str, String str2) {
            this.isAdvance = z2;
            this.difficultyMode = difficultyMode;
            this.gameMode = gameMode;
            this.mapName = str;
            this.playerId = str2;
            this.success = z;
            this.mode = leaderboardsMode;
            this.requestTimestamp = Game.getTimestampSeconds();
        }

        public void toJson(Json json) {
            if (!this.success) {
                throw new IllegalStateException("This result is failed, can't save to json");
            }
            if (this.isAdvance) {
                throw new IllegalStateException("This result is advance, can't save to json");
            }
            json.writeValue("rt", Integer.valueOf(this.requestTimestamp));
            json.writeValue("m", this.mode.name());
            json.writeValue(FlexmarkHtmlConverter.A_NODE, Boolean.FALSE);
            json.writeValue("gm", this.gameMode.name());
            json.writeValue("dm", this.difficultyMode.name());
            json.writeValue("mn", this.mapName);
            json.writeValue("pi", this.playerId);
            json.writeValue("r", Integer.valueOf(this.rank));
            json.writeValue("s", Long.valueOf(this.score));
            json.writeValue("t", Integer.valueOf(this.total));
        }

        public static LeaderboardsRankResult fromJson(JsonValue jsonValue) {
            ReplayManager.LeaderboardsMode valueOf = ReplayManager.LeaderboardsMode.valueOf(jsonValue.getString("m"));
            GameStateSystem.GameMode valueOf2 = GameStateSystem.GameMode.valueOf(jsonValue.getString("gm"));
            DifficultyMode difficultyMode = DifficultyMode.NORMAL;
            try {
                difficultyMode = DifficultyMode.valueOf(jsonValue.getString("dm"));
            } catch (Exception unused) {
            }
            DifficultyMode difficultyMode2 = difficultyMode;
            LeaderboardsRankResult leaderboardsRankResult = new LeaderboardsRankResult(true, difficultyMode2, valueOf, jsonValue.getBoolean(FlexmarkHtmlConverter.A_NODE), valueOf2, jsonValue.getString("mn"), jsonValue.getString("pi"));
            leaderboardsRankResult.score = jsonValue.getLong("s");
            leaderboardsRankResult.rank = jsonValue.getInt("r");
            leaderboardsRankResult.total = jsonValue.getInt("t");
            leaderboardsRankResult.requestTimestamp = jsonValue.getInt("rt");
            return leaderboardsRankResult;
        }
    }
}

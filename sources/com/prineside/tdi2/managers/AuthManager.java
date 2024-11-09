package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Timer;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Auth;
import com.prineside.tdi2.screens.account.AccountScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.TextInputOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.TextureRegionConfig;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager.class */
public class AuthManager extends Manager.ManagerWithListeners<AuthManagerListener> {

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f2284b = TLog.forClass(AuthManager.class);
    public int lastStateUpdateTimestamp;
    private NewsResponse e;
    private boolean f;
    private float g;
    private SignInStatus c = SignInStatus.NOT_SIGNED_IN;
    public Array<String> localXpPlayedLevels = new Array<>(String.class);
    private Array<ObjectConsumer<NewsResponse>> d = new Array<>();
    public boolean gameUpdateNotificationShown = false;
    private HttpQueuedRequest h = null;
    private Array<HttpQueuedRequest> i = new Array<>(true, 1, HttpQueuedRequest.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$ConfirmEmailResult.class */
    public enum ConfirmEmailResult {
        SUCCESS,
        TOO_MANY_ATTEMPTS,
        ALREADY_CONFIRMED,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$GoogleSignInResult.class */
    public enum GoogleSignInResult {
        SUCCESS,
        OTP_REQUIRED,
        SIGN_UP_REQUIRED,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$NewsResponse.class */
    public static class NewsResponse {
        public int id;
        public String title;
        public String body;
        public int networkRequiredVersion;
        public int lastVersion;
        public int seasonPosition;
        public int seasonPlayerCount;
        public int cachedAt;
        public int seasonNumber = 1;
        public Array<IssuedItems> itemsFromServer = new Array<>(IssuedItems.class);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$PasswordResetResult.class */
    public enum PasswordResetResult {
        USER_NOT_FOUND,
        TOO_MANY_ATTEMPTS,
        SUCCESS,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$ProfileStatus.class */
    public static class ProfileStatus {
        public String id;
        public int receivedAt;
        public int expiresAt;
        public String reason;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$SaveGameResult.class */
    public enum SaveGameResult {
        MAX_SLOTS_USED,
        INVALID_SLOT_ID,
        SUCCESS,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$SignInResult.class */
    public enum SignInResult {
        USER_NOT_FOUND,
        WRONG_PASSWORD,
        SUCCESS,
        PASSWORD_NOT_SET,
        OTP_REQUIRED,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$SignInStatus.class */
    public enum SignInStatus {
        NOT_SIGNED_IN,
        SIGNED_IN,
        SIGNED_IN_OFFLINE
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$SignUpResult.class */
    public enum SignUpResult {
        INVALID_LOGIN,
        INVALID_PASSWORD,
        INVALID_EMAIL,
        TOO_MANY_ATTEMPTS,
        NICKNAME_ALREADY_EXISTS,
        EMAIL_ALREADY_EXISTS,
        SUCCESS,
        OTHER_ERROR
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$XpStatus.class */
    public enum XpStatus {
        BONUS,
        NORMAL,
        REDUCED;

        public static final XpStatus[] values = values();
    }

    static /* synthetic */ TLog a() {
        return f2284b;
    }

    static /* synthetic */ HttpQueuedRequest a(AuthManager authManager, HttpQueuedRequest httpQueuedRequest) {
        authManager.h = httpQueuedRequest;
        return httpQueuedRequest;
    }

    static /* synthetic */ void b(AuthManager authManager) {
        authManager.d();
    }

    static /* synthetic */ boolean d(AuthManager authManager) {
        return b();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<AuthManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AuthManager read() {
            return Game.i.authManager;
        }
    }

    public AuthManager() {
        fallBackToOfflineCache();
    }

    public SP_Auth.SessionData getSessionData() {
        return SettingsPrefs.i().auth.sessionData;
    }

    public boolean isPasswordSet() {
        return getSessionData().passwordSet;
    }

    public String getEmailHint() {
        return getSessionData().emailHint;
    }

    @Null
    public String getSteamAccountId() {
        return getSessionData().steamAccountId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b() {
        return Game.isLoaded() && Game.i.settingsManager.isInDebugMode() && Game.i.settingsManager.isInDebugDetailedMode();
    }

    @Override // com.prineside.tdi2.Manager.ManagerWithListeners, com.prineside.tdi2.Manager
    public void setup() {
        loadStateFromServer(null, null);
        addListener(new AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.AuthManager.1
            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void signInStatusUpdated() {
                Runnable runnable = () -> {
                    AuthManager.this.e = null;
                    AuthManager.this.getNews(newsResponse -> {
                        AuthManager.f2284b.i("news reloaded due to auth status change", new Object[0]);
                    });
                };
                if (AuthManager.this.f) {
                    AuthManager.this.getNews(newsResponse -> {
                        runnable.run();
                    });
                } else {
                    runnable.run();
                }
            }
        });
    }

    public boolean hasUnsavedProgressForCloud() {
        return SettingsPrefs.i().auth.isHasUnsavedProgressForCloud();
    }

    public void notifyNeedCloudSave(boolean z) {
        if (SettingsPrefs.i().auth.isHasUnsavedProgressForCloud() != z) {
            SettingsPrefs.i().auth.setHasUnsavedProgressForCloud(z);
            SettingsPrefs.i().requireSave();
        }
    }

    public Array<TextureRegionConfig> getProfileLevelTextures(int i) {
        String valueOf;
        if (i <= 0) {
            i = 1;
        }
        Array<TextureRegionConfig> array = new Array<>(true, 1, TextureRegionConfig.class);
        if (i >= 150) {
            valueOf = "max";
        } else {
            valueOf = String.valueOf((i / 5) + 1);
        }
        array.add(new TextureRegionConfig(Game.i.assetManager.getTextureRegion("player-level-" + valueOf)));
        String valueOf2 = String.valueOf(i);
        float length = ((64 - (valueOf2.length() * 12)) * 0.5f) - 2.0f;
        for (int i2 = 0; i2 < valueOf2.length(); i2++) {
            array.add(new TextureRegionConfig(Game.i.assetManager.getTextureRegion("player-level-digit-" + valueOf2.charAt(i2)), (length + (i2 * 12.0f)) * 0.015625f, 0.296875f, 0.125f, 0.171875f, 0.25f, 0.34375f));
        }
        return array;
    }

    public boolean anyRequestRunning() {
        Game.i.assertInMainThread();
        return (this.h == null && this.i.size == 0) ? false : true;
    }

    public void queueRequest(HttpQueuedRequest httpQueuedRequest) {
        Game.i.assertInMainThread();
        if (b()) {
            f2284b.i("Net: queueRequest: " + httpQueuedRequest.f2298a, new Object[0]);
        }
        if (!anyRequestRunning()) {
            if (b()) {
                f2284b.i("     queueRequest: starting request", new Object[0]);
            }
            this.i.add(httpQueuedRequest);
            d();
            return;
        }
        this.i.add(httpQueuedRequest);
        if (b()) {
            f2284b.i("     queueRequest: other request in progress, waiting", new Object[0]);
        }
    }

    private void c() {
        Game.i.assertInMainThread();
        try {
            if (this.h != null) {
                HttpQueuedRequest httpQueuedRequest = this.h;
                this.h = null;
                Gdx.f881net.cancelHttpRequest(httpQueuedRequest.f2299b);
            }
            this.i.clear();
        } catch (Exception e) {
            f2284b.e("failed to cancel all requests", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Game.i.assertInMainThread();
        if (this.i.size > 0) {
            if (this.h != null) {
                Gdx.f881net.cancelHttpRequest(this.h.f2299b);
            }
            this.h = this.i.removeIndex(0);
            if (b()) {
                f2284b.i("Net: starting request: " + this.h.f2298a, new Object[0]);
            }
            final HttpQueuedRequest httpQueuedRequest = this.h;
            Gdx.f881net.sendHttpRequest(this.h.f2299b, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.AuthManager.2
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    String resultAsString = httpResponse.getResultAsString();
                    Threads i = Threads.i();
                    HttpQueuedRequest httpQueuedRequest2 = httpQueuedRequest;
                    i.runOnMainThread(() -> {
                        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.managers.AuthManager.d(com.prineside.tdi2.managers.AuthManager):boolean
                            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
                            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
                            Caused by: java.lang.IndexOutOfBoundsException: Index: 0
                            	at java.base/java.util.Collections$EmptyList.get(Unknown Source)
                            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:103)
                            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:117)
                            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
                            	... 1 more
                            */
                        /*  JADX ERROR: Method code generation error
                            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
                            	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                            	at jadx.core.codegen.InsnGen.makeInlinedLambdaMethod(InsnGen.java:1048)
                            	at jadx.core.codegen.InsnGen.makeInvokeLambda(InsnGen.java:936)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:827)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1117)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:884)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                            	at java.base/java.util.ArrayList.forEach(Unknown Source)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                            */
                        /*
                            this = this;
                            r0 = r7
                            com.prineside.tdi2.managers.AuthManager r0 = com.prineside.tdi2.managers.AuthManager.this
                            boolean r0 = com.prineside.tdi2.managers.AuthManager.d(r0)
                            if (r0 == 0) goto L30
                            com.prineside.tdi2.utils.logging.TLog r0 = com.prineside.tdi2.managers.AuthManager.a()
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            r2 = r1
                            java.lang.String r3 = "Net: success: "
                            r2.<init>(r3)
                            r2 = r8
                            java.lang.String r2 = com.prineside.tdi2.managers.AuthManager.HttpQueuedRequest.a(r2)
                            java.lang.StringBuilder r1 = r1.append(r2)
                            java.lang.String r2 = ", "
                            java.lang.StringBuilder r1 = r1.append(r2)
                            r2 = r9
                            java.lang.StringBuilder r1 = r1.append(r2)
                            java.lang.String r1 = r1.toString()
                            r2 = 0
                            java.lang.Object[] r2 = new java.lang.Object[r2]
                            r0.i(r1, r2)
                        L30:
                            java.lang.String r0 = "bye"
                            r1 = r9
                            boolean r0 = r0.equals(r1)
                            if (r0 == 0) goto L49
                            com.prineside.tdi2.utils.logging.TLog r0 = com.prineside.tdi2.managers.AuthManager.a()
                            java.lang.String r1 = "Bye"
                            r2 = 0
                            java.lang.Object[] r2 = new java.lang.Object[r2]
                            r0.d(r1, r2)
                            com.prineside.tdi2.Game.exit()
                            return
                        L49:
                            long r0 = com.prineside.tdi2.Game.getRealTickCount()
                            r10 = r0
                            r0 = r8
                            com.prineside.tdi2.managers.AuthManager$HttpQueuedRequest$Listener r0 = com.prineside.tdi2.managers.AuthManager.HttpQueuedRequest.c(r0)
                            r1 = 1
                            r2 = r9
                            r0.finished(r1, r2)
                            r0 = r7
                            com.prineside.tdi2.managers.AuthManager r0 = com.prineside.tdi2.managers.AuthManager.this
                            r1 = 0
                            com.prineside.tdi2.managers.AuthManager$HttpQueuedRequest r0 = com.prineside.tdi2.managers.AuthManager.a(r0, r1)
                            r0 = r7
                            com.prineside.tdi2.managers.AuthManager r0 = com.prineside.tdi2.managers.AuthManager.this
                            com.prineside.tdi2.managers.AuthManager.b(r0)
                            com.prineside.tdi2.Game r0 = com.prineside.tdi2.Game.i
                            com.prineside.tdi2.managers.DebugManager r0 = r0.debugManager
                            if (r0 == 0) goto L92
                            com.prineside.tdi2.Game r0 = com.prineside.tdi2.Game.i
                            com.prineside.tdi2.managers.DebugManager r0 = r0.debugManager
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            r2 = r1
                            java.lang.String r3 = "AuthManager-handleRequest-"
                            r2.<init>(r3)
                            r2 = r8
                            java.lang.String r2 = com.prineside.tdi2.managers.AuthManager.HttpQueuedRequest.a(r2)
                            java.lang.StringBuilder r1 = r1.append(r2)
                            java.lang.String r1 = r1.toString()
                            long r2 = com.prineside.tdi2.Game.getRealTickCount()
                            r3 = r10
                            long r2 = r2 - r3
                            r0.registerFrameJob(r1, r2)
                        L92:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.AuthManager.AnonymousClass2.a(com.prineside.tdi2.managers.AuthManager$HttpQueuedRequest, java.lang.String):void");
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    Threads i = Threads.i();
                    HttpQueuedRequest httpQueuedRequest2 = httpQueuedRequest;
                    i.postRunnable(() -> {
                        AuthManager.f2284b.e("Net: failed: " + AuthManager.this.h.f2298a, th);
                        httpQueuedRequest2.c.finished(false, null);
                        AuthManager.this.h = null;
                        AuthManager.this.d();
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    Threads i = Threads.i();
                    HttpQueuedRequest httpQueuedRequest2 = httpQueuedRequest;
                    i.postRunnable(() -> {
                        AuthManager.f2284b.e("Net: canceled: " + httpQueuedRequest2.f2298a, new Object[0]);
                        httpQueuedRequest2.c.finished(false, null);
                        AuthManager.this.h = null;
                        AuthManager.this.d();
                    });
                }
            });
        }
    }

    public void reloadPlayerId() {
        getSessionData().playerId = null;
        SettingsPrefs.i().requireSave();
    }

    @Null
    public String getInviteCode() {
        return getSessionData().inviteCode;
    }

    @Null
    public String getInvitedById() {
        return getSessionData().invitedBy;
    }

    public String getPlayerIdCached() {
        if (getSessionData().playerId != null) {
            return getSessionData().playerId;
        }
        if (SettingsPrefs.i().auth.getOfflinePlayerId() != null) {
            return SettingsPrefs.i().auth.getOfflinePlayerId();
        }
        return "G-0000-0000-000000";
    }

    public String getPlayerId() {
        Game.i.assertInMainThread();
        if (isSignedIn()) {
            return getSessionData().playerId;
        }
        SP_Auth sP_Auth = SettingsPrefs.i().auth;
        if (sP_Auth.getOfflinePlayerId() == null) {
            sP_Auth.setOfflinePlayerId("G-" + FastRandom.generateUniqueDistinguishableId());
            SettingsPrefs.i().requireSave();
            f2284b.i("generated new offline playerId: " + sP_Auth.getOfflinePlayerId(), new Object[0]);
        }
        return sP_Auth.getOfflinePlayerId();
    }

    public boolean isSignedIn() {
        if (getSessionId() != null) {
            return this.c == SignInStatus.SIGNED_IN || this.c == SignInStatus.SIGNED_IN_OFFLINE;
        }
        return false;
    }

    @Null
    public String getSessionId() {
        return getSessionData().sessionId;
    }

    public int getCloudSaveSlotId() {
        return SettingsPrefs.i().auth.cloudSaveSlotId;
    }

    public int getCloudSaveSlotTimestamp() {
        if (SettingsPrefs.i().auth.cloudSaveSlotId == -1) {
            return -1;
        }
        return SettingsPrefs.i().auth.cloudSaveSlotTimestamp;
    }

    private static void a(int i, int i2) {
        Game.i.assertInMainThread();
        SettingsPrefs.i().auth.cloudSaveSlotId = i;
        SettingsPrefs.i().auth.cloudSaveSlotTimestamp = i2;
        SettingsPrefs.i().requireSave();
    }

    private void e() {
        Game.i.assertInMainThread();
        if (!Game.i.progressManager.existsAnyProgress()) {
            getCloudSavedGamesList(jsonValue -> {
                if (jsonValue == null) {
                    return;
                }
                int i = 0;
                int i2 = -1;
                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    int i3 = next.getInt("slotId");
                    int i4 = next.getInt("slotTimestamp");
                    if (i4 > i) {
                        i2 = i3;
                        i = i4;
                    }
                }
                if (i2 != -1) {
                    int i5 = i2;
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("load_from_cloud_confirm"), () -> {
                        loadSavedGameFromServer(i5);
                    });
                }
            });
        }
    }

    @Null
    public String getProgressOwnerPlayerId() {
        return ProgressPrefs.i().auth.getProgressOwnerId();
    }

    @Null
    public String getProgressOwnerPlayerNickname() {
        return ProgressPrefs.i().auth.getProgressOwnerNickname();
    }

    private static void a(String str, Runnable runnable) {
        String progressOwnerId = ProgressPrefs.i().auth.getProgressOwnerId();
        if (progressOwnerId == null || progressOwnerId.equals(str)) {
            f2284b.i("preferences owner confirmed: " + progressOwnerId, new Object[0]);
            runnable.run();
            return;
        }
        f2284b.e("owner id: " + progressOwnerId, new Object[0]);
        String progressOwnerNickname = ProgressPrefs.i().auth.getProgressOwnerNickname();
        String str2 = progressOwnerNickname;
        if (progressOwnerNickname == null) {
            str2 = "unknown";
        }
        Dialog.i().showAlert(Game.i.localeManager.i18n.format("current_progress_owner_mismatch_alert", str2));
    }

    public void signInWithSteam(ObjectConsumer<SignInResponse> objectConsumer) {
        Game.i.actionResolver.requestSteamAuthTicket(str -> {
            if (str == null) {
                if (objectConsumer != null) {
                    objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage("Steam auth ticket is null"));
                    return;
                }
                return;
            }
            Game.i.httpManager.post(Config.AUTH_SIGN_IN_STEAM_URL).param("ticket", str).listener((z, httpResponse, z2, th) -> {
                if (z) {
                    String resultAsString = httpResponse.getResultAsString();
                    Threads.i().runOnMainThread(() -> {
                        try {
                            if (b()) {
                                f2284b.i("Response: " + resultAsString, new Object[0]);
                            }
                            JsonValue parse = new JsonReader().parse(resultAsString);
                            if (parse.getString("status").equals("success")) {
                                if (parse.getString("signInResult").equals("OTP_REQUIRED")) {
                                    signInShowOtpForm(parse.getString("otpRequestId"), parse.getString("nickname"), objectConsumer);
                                    return;
                                } else {
                                    Game.i.analyticsManager.logSignedIn("steam");
                                    loadStateFromServer(parse.getString("sessionid"), () -> {
                                        if (objectConsumer != null) {
                                            objectConsumer.accept(new SignInResponse(SignInResult.SUCCESS));
                                        }
                                        e();
                                    });
                                    return;
                                }
                            }
                            f2284b.e("failed to sign in: " + resultAsString, new Object[0]);
                            signOut();
                            if (objectConsumer != null) {
                                try {
                                    objectConsumer.accept(new SignInResponse(SignInResult.valueOf(parse.getString("signInResult"))));
                                } catch (Exception e) {
                                    objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage(e.getMessage()));
                                }
                            }
                        } catch (Exception e2) {
                            f2284b.e("Failed to parse response", e2);
                            signOut();
                            if (objectConsumer != null) {
                                objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage(e2.getMessage()));
                            }
                        }
                    });
                } else {
                    f2284b.e("Error while signing in with Steam", th);
                    Threads.i().runOnMainThread(() -> {
                        signOut();
                        if (objectConsumer != null) {
                            objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage(th.getMessage()));
                        }
                    });
                }
            }).send();
        });
    }

    public void signInWithGoogle(String str, ObjectConsumer<GoogleSignInResult> objectConsumer) {
        Game.i.assertInMainThread();
        for (int i = 0; i < this.i.size; i++) {
            if (this.i.items[i].f2298a.equals("signInWithGoogle")) {
                f2284b.e("skipped signInWithGoogle - request already queued", new Object[0]);
                return;
            }
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_SIGN_IN_GOOGLE_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("token", str);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("signInWithGoogle", httpRequest, (z, str2) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str2, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str2);
                    if (parse.getString("status").equals("success")) {
                        GoogleSignInResult valueOf = GoogleSignInResult.valueOf(parse.getString("signInResult"));
                        switch (valueOf) {
                            case OTP_REQUIRED:
                                signInShowOtpForm(parse.getString("otpRequestId"), parse.getString("nickname"), signInResponse -> {
                                    if (objectConsumer != null) {
                                        Threads.i().runOnMainThread(() -> {
                                            objectConsumer.accept(signInResponse.result == SignInResult.SUCCESS ? GoogleSignInResult.SUCCESS : GoogleSignInResult.OTHER_ERROR);
                                        });
                                    }
                                });
                                return;
                            case SUCCESS:
                                Game.i.analyticsManager.logSignedIn("google");
                                loadStateFromServer(parse.getString("sessionid"), () -> {
                                    if (objectConsumer != null) {
                                        Threads.i().runOnMainThread(() -> {
                                            objectConsumer.accept(GoogleSignInResult.SUCCESS);
                                            e();
                                        });
                                    }
                                });
                                return;
                            default:
                                if (objectConsumer != null) {
                                    Threads.i().runOnMainThread(() -> {
                                        objectConsumer.accept(valueOf);
                                    });
                                }
                                return;
                        }
                    }
                    f2284b.e("failed to sign in: " + str2, new Object[0]);
                    Threads.i().runOnMainThread(() -> {
                        signOut();
                        if (objectConsumer != null) {
                            try {
                                objectConsumer.accept(GoogleSignInResult.valueOf(parse.getString("signInResult")));
                            } catch (Exception unused) {
                                objectConsumer.accept(GoogleSignInResult.OTHER_ERROR);
                            }
                        }
                    });
                    return;
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    Threads.i().runOnMainThread(() -> {
                        signOut();
                        if (objectConsumer != null) {
                            objectConsumer.accept(GoogleSignInResult.OTHER_ERROR);
                        }
                    });
                    return;
                }
            }
            f2284b.e("Error while signing in", new Object[0]);
            Threads.i().runOnMainThread(() -> {
                signOut();
                if (objectConsumer != null) {
                    objectConsumer.accept(GoogleSignInResult.OTHER_ERROR);
                }
            });
        }));
    }

    @Override // com.prineside.tdi2.Manager.ManagerWithListeners, com.prineside.tdi2.Manager
    public void postRender(float f) {
        String sessionId = getSessionId();
        this.g += f;
        if (((getSignInStatus() == SignInStatus.SIGNED_IN && sessionId != null) || (getSignInStatus() == SignInStatus.NOT_SIGNED_IN && this.g > 1.0f)) && this.d.size != 0 && !this.f) {
            this.f = true;
            if (b()) {
                f2284b.i("requesting latest news...", new Object[0]);
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GET_LATEST_NEWS_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("locale", Game.i.localeManager.getLocale());
            if (sessionId != null) {
                hashMap.put("sessionid", sessionId);
            }
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            queueRequest(new HttpQueuedRequest("getLatestNews", httpRequest, (z, str) -> {
                if (z) {
                    try {
                        if (b()) {
                            f2284b.i(str, new Object[0]);
                        }
                        JsonValue parse = new JsonReader().parse(str);
                        if (parse.getString("status").equals("success")) {
                            try {
                                NewsResponse newsResponse = new NewsResponse();
                                newsResponse.cachedAt = Game.getTimestampSeconds();
                                newsResponse.body = parse.get("news").getString("body");
                                newsResponse.title = parse.get("news").getString(Attribute.TITLE_ATTR);
                                newsResponse.id = parse.get("news").getInt(Attribute.ID_ATTR, 0);
                                newsResponse.networkRequiredVersion = parse.getInt("network_min_required_version");
                                newsResponse.lastVersion = parse.getInt("last_version");
                                newsResponse.seasonNumber = parse.getInt("season_number", 1);
                                newsResponse.seasonPosition = parse.getInt("season_position", 0);
                                newsResponse.seasonPlayerCount = parse.getInt("season_player_count", 0);
                                JsonValue jsonValue = parse.get("issuedItems");
                                if (jsonValue != null) {
                                    Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                                    while (iterator2.hasNext()) {
                                        newsResponse.itemsFromServer.add(IssuedItems.fromJson(iterator2.next()));
                                    }
                                }
                                this.e = newsResponse;
                                for (int i = 0; i < this.d.size; i++) {
                                    this.d.get(i).accept(newsResponse);
                                }
                            } catch (Exception e) {
                                f2284b.e("failed to parse news", e);
                            }
                            this.f = false;
                            this.d.clear();
                            return;
                        }
                        f2284b.e("can't retrieve news: " + str, new Object[0]);
                        this.f = false;
                        this.d.clear();
                        return;
                    } catch (Exception e2) {
                        f2284b.e("Failed to parse response", e2);
                        this.f = false;
                        this.d.clear();
                        return;
                    }
                }
                f2284b.e("can't retrieve news", new Object[0]);
                this.f = false;
                this.d.clear();
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (getCloudSaveSlotId() != -1) {
            getCloudSavedGamesList(jsonValue -> {
                if (jsonValue == null) {
                    f2284b.e("failed to load saved games list in checkIfCloudSaveGameDiffers", new Object[0]);
                    return;
                }
                boolean z = false;
                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                while (true) {
                    if (!iterator2.hasNext()) {
                        break;
                    }
                    JsonValue next = iterator2.next();
                    int i = next.getInt("slotId");
                    if (getCloudSaveSlotId() == i) {
                        z = true;
                        if (next.getInt("slotTimestamp") > getCloudSaveSlotTimestamp()) {
                            Dialog.i().showConfirmWithCallbacks(Game.i.localeManager.i18n.get("newer_cloud_save_load_confirm"), () -> {
                                loadSavedGameFromServer(i);
                            }, () -> {
                                a(-1, 0);
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("auto_saves_disabled_select_slot"));
                            });
                        }
                    }
                }
                if (!z) {
                    a(-1, 0);
                    f2284b.e("locally cached saved game slot ID not found", new Object[0]);
                }
            });
        }
    }

    public void loadStateFromServer(@Null String str, @Null Runnable runnable) {
        if (Game.i.actionResolver.isAppModified() || Config.isHeadless() || Config.isModdingMode()) {
            f2284b.i("loadStateFromServer - app is modified or headless", new Object[0]);
            return;
        }
        Game.i.assertInMainThread();
        if (str == null) {
            str = SettingsPrefs.i().auth.sessionData.sessionId;
        }
        if (str != null) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_GET_SESSION_INFO_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", str);
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            if (b()) {
                f2284b.i("getting status of session " + str, new Object[0]);
            }
            queueRequest(new HttpQueuedRequest("loadStateFromServer", httpRequest, (z, str2) -> {
                if (z) {
                    try {
                        if (b()) {
                            f2284b.i(str2, new Object[0]);
                        }
                        JsonValue parse = new JsonReader().parse(str2);
                        if (parse.getString("status").equals("success")) {
                            JsonValue jsonValue = parse.get("sessionData");
                            a(jsonValue.getString("playerid"), () -> {
                                try {
                                    getSessionData().fromServerResponseJson(jsonValue);
                                    if (getSessionData().tempXp == 0) {
                                        this.localXpPlayedLevels.clear();
                                    }
                                    SettingsPrefs.i().requireSave();
                                    this.lastStateUpdateTimestamp = Game.getTimestampSeconds();
                                    a(SignInStatus.SIGNED_IN);
                                    if (runnable != null) {
                                        runnable.run();
                                    }
                                    Timer.schedule(new Timer.Task() { // from class: com.prineside.tdi2.managers.AuthManager.3
                                        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                                        public void run() {
                                            AuthManager.this.f();
                                        }
                                    }, 2.0f);
                                    this.f1735a.begin();
                                    for (int i = 0; i < this.f1735a.size; i++) {
                                        ((AuthManagerListener) this.f1735a.get(i)).stateUpdated();
                                    }
                                    this.f1735a.end();
                                } catch (Exception e) {
                                    f2284b.e("loadStateFromServer failed, json: " + jsonValue.toJson(JsonWriter.OutputType.json), e);
                                }
                            });
                            return;
                        }
                        f2284b.e("can't load session: " + str2, new Object[0]);
                        a(SignInStatus.NOT_SIGNED_IN);
                        if (runnable != null) {
                            runnable.run();
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        f2284b.e("Exception: " + e.getMessage(), e);
                        fallBackToOfflineCache();
                        if (runnable != null) {
                            runnable.run();
                            return;
                        }
                        return;
                    }
                }
                f2284b.e("Error continuing session", new Object[0]);
                fallBackToOfflineCache();
                if (runnable != null) {
                    runnable.run();
                }
            }));
            return;
        }
        a(SignInStatus.NOT_SIGNED_IN);
        if (runnable != null) {
            runnable.run();
        }
    }

    public long getLastLoadFromCloudTimestamp() {
        return getSessionData().lastLoadFromCloudTimestamp;
    }

    private void g() {
        String progressOwnerPlayerId = getProgressOwnerPlayerId();
        if (progressOwnerPlayerId == null || progressOwnerPlayerId.length() != getPlayerId().length()) {
            ProgressPrefs i = ProgressPrefs.i();
            i.auth.setProgressOwnerId(getPlayerId());
            i.auth.setProgressOwnerNickname(getNickname());
            i.requireSave();
            f2284b.i("owner id saved in preferences: " + getPlayerId(), new Object[0]);
            return;
        }
        f2284b.i("owner id not changed: " + getPlayerId(), new Object[0]);
    }

    public void onSteamAuthFinished() {
    }

    private void a(SignInStatus signInStatus) {
        String str;
        Game.i.assertInMainThread();
        SignInStatus signInStatus2 = this.c;
        this.c = signInStatus;
        if (signInStatus == SignInStatus.SIGNED_IN) {
            g();
        }
        if (signInStatus2 == signInStatus) {
            return;
        }
        if (b()) {
            f2284b.i("signIn status: " + String.valueOf(signInStatus), new Object[0]);
        }
        this.f1735a.begin();
        for (int i = 0; i < this.f1735a.size; i++) {
            ((AuthManagerListener) this.f1735a.get(i)).signInStatusUpdated();
        }
        this.f1735a.end();
        if (Game.isLoaded()) {
            switch (signInStatus) {
                case SIGNED_IN:
                    str = Game.i.localeManager.i18n.get("signed_in_online_as") + SequenceUtils.SPACE + getNickname();
                    break;
                case SIGNED_IN_OFFLINE:
                    str = Game.i.localeManager.i18n.get("signed_in_offline_as") + SequenceUtils.SPACE + getNickname();
                    break;
                default:
                    str = Game.i.localeManager.i18n.get("not_signed_in");
                    break;
            }
            Notifications.i().add(str, Game.i.assetManager.getDrawable("icon-user"), null, StaticSoundType.NOTIFICATION);
        }
    }

    public void signOut() {
        if (Config.isHeadless()) {
            return;
        }
        Game.i.assertInMainThread();
        if (b()) {
            f2284b.i("cancel requests (signOut)", new Object[0]);
        }
        c();
        if (isSignedIn()) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_SIGN_OUT_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", getSessionId());
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.AuthManager.4
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    AuthManager.f2284b.i("signOut response: " + httpResponse.getResultAsString(), new Object[0]);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    AuthManager.f2284b.e("signOut failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    AuthManager.f2284b.e("signOut cancelled", new Object[0]);
                }
            });
        }
        clearLocalSessionData();
    }

    public void clearLocalSessionData() {
        SettingsPrefs.i().auth.sessionData = new SP_Auth.SessionData();
        SettingsPrefs.i().requireSave();
        a(SignInStatus.NOT_SIGNED_IN);
        Game.i.actionResolver.signOutGoogle();
    }

    public void fallBackToOfflineCache() {
        Game.i.assertInMainThread();
        if (getSessionId() == null) {
            if (b()) {
                f2284b.i("fallBackToOfflineCache - no offline cache, signing out", new Object[0]);
            }
            signOut();
            return;
        }
        a(SignInStatus.SIGNED_IN_OFFLINE);
    }

    public void addProfileStatusLocal(String str, String str2, int i) {
        Preconditions.checkNotNull(str, "reason can not be null");
        Preconditions.checkNotNull(str2, "statusId can not be null");
        f2284b.i("addProfileStatusLocal " + str2 + SequenceUtils.SPACE + i, new Object[0]);
        boolean z = false;
        Array<ProfileStatus> array = SettingsPrefs.i().auth.sessionData.profileStatuses;
        int i2 = 0;
        while (true) {
            if (i2 >= array.size) {
                break;
            }
            ProfileStatus profileStatus = array.get(i2);
            if (!str2.equals(profileStatus.id)) {
                i2++;
            } else {
                z = true;
                f2284b.i("- found existing: " + profileStatus.expiresAt, new Object[0]);
                if (profileStatus.expiresAt == -1 || i == -1) {
                    f2284b.i("- enabling permanently", new Object[0]);
                    profileStatus.expiresAt = -1;
                } else {
                    f2284b.i("- adding duration", new Object[0]);
                    profileStatus.expiresAt += i;
                }
            }
        }
        if (!z) {
            f2284b.i("- new status", new Object[0]);
            ProfileStatus profileStatus2 = new ProfileStatus();
            profileStatus2.id = str2;
            profileStatus2.expiresAt = i == -1 ? -1 : Game.getTimestampSeconds() + i;
            profileStatus2.receivedAt = Game.getTimestampSeconds();
            profileStatus2.reason = str;
            array.add(profileStatus2);
        }
        SettingsPrefs.i().requireSave();
    }

    public static boolean checkIncorrectSessionIdApiResponse(JsonValue jsonValue) {
        try {
            if (jsonValue.getString("status", "success").equals("error") && jsonValue.getBoolean("invalid_sessionid", false)) {
                f2284b.e("server told us that session ID is invalid", new Object[0]);
                if (jsonValue.getString("sessionid", "").equals(Game.i.authManager.getSessionId())) {
                    f2284b.e("currently set session ID is invalid, resetting", new Object[0]);
                    Game.i.authManager.clearLocalSessionData();
                    return false;
                }
                f2284b.e("some other session ID is invalid, skipping", new Object[0]);
                return true;
            }
            return true;
        } catch (Exception e) {
            f2284b.e("checkIncorrectSessionIdApiResponse failed", e);
            return true;
        }
    }

    public boolean isProfileStatusActive(String str) {
        Array<ProfileStatus> array = SettingsPrefs.i().auth.sessionData.profileStatuses;
        for (int i = 0; i < array.size; i++) {
            ProfileStatus profileStatus = array.get(i);
            if (str.equals(profileStatus.id) && (profileStatus.expiresAt == -1 || profileStatus.expiresAt > Game.getTimestampSeconds())) {
                return true;
            }
        }
        return false;
    }

    public int getMaxCloudSaveSlots() {
        int i = 3;
        if (isProfileStatusActive(Config.PROFILE_STATUS_PREMIUM)) {
            i = 7;
        }
        return i;
    }

    public void linkSteamAccount(ObjectConsumer<Boolean> objectConsumer) {
        Preconditions.checkNotNull(objectConsumer);
        if (!isSignedIn()) {
            objectConsumer.accept(Boolean.FALSE);
        } else {
            Game.i.actionResolver.requestSteamAuthTicket(str -> {
                if (str != null) {
                    f2284b.i("steam ticket: " + str, new Object[0]);
                    Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                    httpRequest.setUrl(Config.AUTH_LINK_STEAM_URL);
                    HashMap hashMap = new HashMap();
                    hashMap.put("sessionid", Game.i.authManager.getSessionId());
                    hashMap.put("locale", Game.i.localeManager.getLocale());
                    hashMap.put("ticket", str);
                    httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.AuthManager.5
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            String resultAsString = httpResponse.getResultAsString();
                            AuthManager.f2284b.i("linkSteamAccount server: " + resultAsString, new Object[0]);
                            Threads i = Threads.i();
                            ObjectConsumer objectConsumer2 = objectConsumer;
                            i.runOnMainThread(() -> {
                                try {
                                    JsonValue parse = new JsonReader().parse(resultAsString);
                                    if (!parse.getString("status").equals("success")) {
                                        Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_link_steam") + SequenceUtils.EOL + parse.getString("message"), null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                                        objectConsumer2.accept(Boolean.FALSE);
                                        return;
                                    }
                                    Notifications.i().add(Game.i.localeManager.i18n.get("success_to_link_steam"), null, MaterialColor.GREEN.P800, StaticSoundType.SUCCESS);
                                    String string = parse.getString("double_gain_status_error", null);
                                    if (string != null) {
                                        Notifications.i().add(string, null, null, null);
                                    }
                                    AuthManager.this.loadStateFromServer(null, () -> {
                                        objectConsumer2.accept(Boolean.TRUE);
                                    });
                                } catch (Exception e) {
                                    AuthManager.f2284b.e("failed to parse response from server: " + resultAsString, e);
                                    Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_link_steam") + "\nServer error - invalid response", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                                }
                            });
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            AuthManager.f2284b.e("failed to get account settings", th);
                            Threads.i().runOnMainThread(() -> {
                                Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_link_steam") + "\nRequest failed, check your Internet connection", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                            });
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                            Threads.i().runOnMainThread(() -> {
                                Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_link_steam") + "\nRequest cancelled", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                            });
                        }
                    });
                    return;
                }
                f2284b.i("requestSteamAuthTicket returned null", new Object[0]);
                Notifications.i().add(Game.i.localeManager.i18n.get("failed_to_link_steam") + "\nClient::requestSteamAuthTicket failed", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                objectConsumer.accept(Boolean.FALSE);
            });
        }
    }

    public SignInStatus getSignInStatus() {
        return this.c;
    }

    public String getNickname() {
        if (!isSignedIn()) {
            return "Guest";
        }
        return getSessionData().nickname;
    }

    public TextureRegion getAvatar(int i) {
        if (i != 32 && i != 64 && i != 128) {
            i = 64;
        }
        SP_Auth.SessionData sessionData = getSessionData();
        if ((this.c == SignInStatus.SIGNED_IN || this.c == SignInStatus.SIGNED_IN_OFFLINE) && sessionData.hasAvatar && sessionData.playerId != null) {
            return Game.i.assetManager.loadWebTexture(Config.AVATAR_WEB_TEXTURES_URL + sessionData.playerId.toLowerCase(Locale.US) + "-" + i + ".png", true);
        }
        return Game.i.assetManager.getTextureRegion("icon-user");
    }

    public String getAvatarWebUrl(String str, int i) {
        return Config.AVATAR_WEB_TEXTURES_URL + str.toLowerCase(Locale.US) + "-" + i + ".png";
    }

    public void linkAccountStatus(String str, final ObjectConsumer<String> objectConsumer) {
        Preconditions.checkNotNull(str, "dataJson is null");
        Preconditions.checkNotNull(objectConsumer, "cb is null");
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_LINK_ACCOUNT_STATUS_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        hashMap.put("locale", Game.i.localeManager.getLocale());
        hashMap.put("data", str);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.AuthManager.6
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                AuthManager.f2284b.i("linkAccountStatus server: " + resultAsString, new Object[0]);
                Threads i = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i.runOnMainThread(() -> {
                    try {
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (parse.getString("status").equals("success")) {
                            objectConsumer2.accept(null);
                        } else {
                            objectConsumer2.accept(parse.getString("message"));
                        }
                    } catch (Exception e) {
                        AuthManager.f2284b.e("failed to parse response from server: " + resultAsString, e);
                        objectConsumer2.accept("Server error - invalid response");
                    }
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                AuthManager.f2284b.e("failed to get account settings", th);
                Threads i = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i.runOnMainThread(() -> {
                    objectConsumer2.accept("Request failed, check your Internet connection");
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                Threads i = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i.runOnMainThread(() -> {
                    objectConsumer2.accept("Request cancelled");
                });
            }
        });
    }

    public void backupProgressToServer() {
        try {
            Game.i.assertInMainThread();
            if (this.c != SignInStatus.SIGNED_IN) {
                f2284b.i("progress backup: canceled, not signed in", new Object[0]);
            }
            if (Game.i.progressManager.isDeveloperModeEnabled()) {
                f2284b.i("progress backup: canceled, dev mode", new Object[0]);
                return;
            }
            if (Config.isHeadless()) {
                return;
            }
            if (!Game.i.progressManager.hasAnyItem()) {
                f2284b.i("progress backup: canceled, no items", new Object[0]);
                return;
            }
            if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
                f2284b.i("progress backup: canceled, modified game", new Object[0]);
                return;
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_BACKUP_PROGRESS_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", String.valueOf(getSessionId()));
            hashMap.put("gameStartHash", Game.i.progressManager.getGameStartHash());
            hashMap.put("progress", Game.i.preferencesManager.progressPrefsToBase64());
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            queueRequest(new HttpQueuedRequest("backupProgressToServer", httpRequest, (z, str) -> {
                if (z) {
                    f2284b.i("progress backup: response: " + str, new Object[0]);
                } else {
                    f2284b.e("progress backup: failed", new Object[0]);
                }
            }));
        } catch (Exception e) {
            f2284b.e("backupProgressToServer failed", e);
        }
    }

    public void saveGameToServer(int i, ObjectConsumer<SaveGameResult> objectConsumer) {
        Game.i.assertInMainThread();
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            Notifications.i().add("Could not save game to cloud while being in Developer mode, please reset your progress first", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        if (Config.isHeadless()) {
            return;
        }
        if (!Game.i.progressManager.hasAnyItem()) {
            f2284b.e("empty items, save to cloud disabled", new Object[0]);
            return;
        }
        if (this.c == SignInStatus.SIGNED_IN) {
            if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
                Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                return;
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_SAVE_GAME_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", getSessionId());
            hashMap.put("gameStartHash", Game.i.progressManager.getGameStartHash());
            hashMap.put("timeInGame", String.valueOf(Game.i.statisticsManager.getTimeSpentInGameSinceStart()));
            if (i != -1) {
                hashMap.put("slotId", String.valueOf(i));
            }
            hashMap.put("data", Game.i.preferencesManager.progressPrefsToBase64());
            String convertHttpParameters = HttpParametersUtils.convertHttpParameters(hashMap);
            f2284b.i(convertHttpParameters, new Object[0]);
            httpRequest.setContent(convertHttpParameters);
            queueRequest(new HttpQueuedRequest("saveGameToServer", httpRequest, (z, str) -> {
                if (z) {
                    try {
                        f2284b.i("save response: " + str, new Object[0]);
                        JsonValue parse = new JsonReader().parse(str);
                        if (parse.getString("status").equals("success")) {
                            a(parse.getInt("slotId"), parse.getInt("slotTimestamp"));
                            loadStateFromServer(null, null);
                            if (objectConsumer != null) {
                                objectConsumer.accept(SaveGameResult.SUCCESS);
                            }
                            Notifications.i().add(Game.i.localeManager.i18n.get("game_saved_to_cloud"), Game.i.assetManager.getDrawable("icon-info"), MaterialColor.GREEN.P800, StaticSoundType.NOTIFICATION);
                            notifyNeedCloudSave(false);
                            return;
                        }
                        f2284b.e("can't save game: " + str, new Object[0]);
                        if (objectConsumer != null) {
                            SaveGameResult saveGameResult = SaveGameResult.OTHER_ERROR;
                            try {
                                saveGameResult = SaveGameResult.valueOf(parse.getString("saveGameResult"));
                            } catch (Exception unused) {
                            }
                            objectConsumer.accept(saveGameResult);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        f2284b.e("Failed to parse response", e);
                        if (objectConsumer != null) {
                            objectConsumer.accept(SaveGameResult.OTHER_ERROR);
                            return;
                        }
                        return;
                    }
                }
                f2284b.e("Error while saving game", new Object[0]);
                if (objectConsumer != null) {
                    objectConsumer.accept(SaveGameResult.OTHER_ERROR);
                }
            }));
            return;
        }
        f2284b.e("can't save game to cloud, not signed in", new Object[0]);
        if (objectConsumer != null) {
            objectConsumer.accept(SaveGameResult.OTHER_ERROR);
        }
    }

    public void deleteGameFromServer(int i, ObjectConsumer<Boolean> objectConsumer) {
        if (Config.isHeadless()) {
            return;
        }
        Preconditions.checkNotNull(objectConsumer);
        if (this.c == SignInStatus.SIGNED_IN) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_DELETE_GAME_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", getSessionId());
            hashMap.put("slotId", String.valueOf(i));
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            queueRequest(new HttpQueuedRequest("deleteGameFromServer", httpRequest, (z, str) -> {
                if (z) {
                    try {
                        if (b()) {
                            f2284b.i(str, new Object[0]);
                        }
                        if (new JsonReader().parse(str).getString("status").equals("success")) {
                            objectConsumer.accept(Boolean.TRUE);
                            return;
                        } else {
                            f2284b.e("can't delete game: " + str, new Object[0]);
                            objectConsumer.accept(Boolean.FALSE);
                            return;
                        }
                    } catch (Exception e) {
                        f2284b.e("Failed to parse response", e);
                        objectConsumer.accept(Boolean.FALSE);
                        return;
                    }
                }
                f2284b.e("Error while deleting game", new Object[0]);
                objectConsumer.accept(Boolean.FALSE);
            }));
            return;
        }
        f2284b.e("not signed in, can't delete saved game", new Object[0]);
        objectConsumer.accept(Boolean.FALSE);
    }

    public void loadSavedGameFromServer(int i) {
        if (Config.isHeadless()) {
            return;
        }
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            Notifications.i().add("Could not load game while being in Developer mode, please reset your progress first", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        if (this.c == SignInStatus.SIGNED_IN) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.AUTH_LOAD_GAME_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("sessionid", getSessionId());
            hashMap.put("slotId", String.valueOf(i));
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            queueRequest(new HttpQueuedRequest("loadSavedGameFromServer", httpRequest, (z, str) -> {
                if (z) {
                    try {
                        if (b()) {
                            f2284b.i(str, new Object[0]);
                        }
                        JsonValue parse = new JsonReader().parse(str);
                        if (parse.getString("status").equals("success")) {
                            if (parse.get("savedGame").getInt("gameBuild") > 208) {
                                Notifications.i().add(Game.i.localeManager.i18n.get("cant_load_from_cloud_need_update"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                                return;
                            }
                            getSessionData().lastLoadFromCloudTimestamp = Game.getTimestampMillis();
                            SettingsPrefs.i().requireSave();
                            String sessionId = Game.i.authManager.getSessionId();
                            if (b()) {
                                f2284b.i("current session: " + sessionId, new Object[0]);
                            }
                            JsonValue jsonValue = parse.get("savedGame").get("data");
                            if (b()) {
                                f2284b.i("loading saved game from compact base64 " + jsonValue.asString(), new Object[0]);
                            }
                            Game.i.preferencesManager.fromBase64(jsonValue.asString());
                            a(parse.get("savedGame").getInt("slotId"), parse.get("savedGame").getInt("slotTimestamp"));
                            Game.i.preferencesManager.reapplyAllPreferences();
                            Game.i.authManager.loadStateFromServer(sessionId, null);
                            AccountScreen.goToScreen();
                            if (SettingsPrefs.i().auth.isHasUnsavedProgressForCloud()) {
                                SettingsPrefs.i().auth.setHasUnsavedProgressForCloud(false);
                                SettingsPrefs.i().requireSave();
                            }
                            if (b()) {
                                f2284b.i("new session: " + Game.i.authManager.getSessionId(), new Object[0]);
                            }
                            Notifications.i().add(Game.i.localeManager.i18n.get("game_loaded_from_cloud"), Game.i.assetManager.getDrawable("icon-info"), null, StaticSoundType.NOTIFICATION);
                            return;
                        }
                        f2284b.e("can't load game: " + str, new Object[0]);
                        return;
                    } catch (Exception e) {
                        f2284b.e("Failed to parse response", e);
                        return;
                    }
                }
                f2284b.e("Failed to load saved game from server", new Object[0]);
            }));
            return;
        }
        f2284b.e("not signed in, can't load saved game", new Object[0]);
    }

    public void handleAutoSave() {
        if (isAutoSavesEnabled()) {
            saveGameToServer(getCloudSaveSlotId(), saveGameResult -> {
                if (saveGameResult == SaveGameResult.MAX_SLOTS_USED || saveGameResult == SaveGameResult.INVALID_SLOT_ID) {
                    setAutoSavesEnabled(false);
                }
            });
        }
        backupProgressToServer();
    }

    public void requestNicknameChange(String str, ObjectConsumer<Boolean> objectConsumer) {
        if (getSessionId() == null) {
            if (objectConsumer != null) {
                objectConsumer.accept(Boolean.FALSE);
                return;
            }
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_NICKNAME_CHANGE_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("nickname", str);
        if (getSessionId() != null) {
            hashMap.put("sessionid", getSessionId());
        }
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("changeNickname", httpRequest, (z, str2) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str2, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str2);
                    if (parse.getString("status").equals("success")) {
                        loadStateFromServer(null, objectConsumer != null ? () -> {
                            objectConsumer.accept(Boolean.TRUE);
                        } : null);
                        return;
                    }
                    f2284b.e("failed to change nickname: " + str2, new Object[0]);
                    Notifications.i().add(parse.getString("message", Game.i.localeManager.i18n.get("unknown_error")), null, MaterialColor.RED.P800, StaticSoundType.FAIL);
                    if (objectConsumer != null) {
                        objectConsumer.accept(Boolean.FALSE);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    Notifications.i().add(Game.i.localeManager.i18n.get("unknown_error"), null, MaterialColor.RED.P800, StaticSoundType.FAIL);
                    if (objectConsumer != null) {
                        objectConsumer.accept(Boolean.FALSE);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to get cloud saves", new Object[0]);
            Notifications.i().add(Game.i.localeManager.i18n.get("unknown_error"), null, MaterialColor.RED.P800, StaticSoundType.FAIL);
            if (objectConsumer != null) {
                objectConsumer.accept(Boolean.FALSE);
            }
        }));
    }

    public void createPasteBin(String str, String str2, ObjectConsumer<PasteBinResponse> objectConsumer) {
        if (!isSignedIn() || getPlayerId() == null) {
            objectConsumer.accept(new PasteBinResponse(false, "Not signed in", null));
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_PASTEBIN_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", getSessionId());
        hashMap.put("description", str);
        hashMap.put("contents", str2);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("createPasteBin", httpRequest, (z, str3) -> {
            if (z) {
                try {
                    JsonValue parse = new JsonReader().parse(str3);
                    if ("success".equals(parse.getString("status"))) {
                        Threads.i().runOnMainThread(() -> {
                            objectConsumer.accept(new PasteBinResponse(true, null, parse.getString("link")));
                        });
                        return;
                    } else {
                        f2284b.e("paste bin failed: " + str3, new Object[0]);
                        Threads.i().runOnMainThread(() -> {
                            objectConsumer.accept(new PasteBinResponse(false, parse.getString("error"), null));
                        });
                        return;
                    }
                } catch (Exception e) {
                    Threads.i().runOnMainThread(() -> {
                        f2284b.e("failed to parse paste bin response", e);
                        objectConsumer.accept(new PasteBinResponse(false, "Failed to parse response from the server", null));
                    });
                    return;
                }
            }
            Threads.i().runOnMainThread(() -> {
                objectConsumer.accept(new PasteBinResponse(false, "Failed to make a request", null));
            });
        }));
    }

    public void resetPassword(String str, ObjectConsumer<PasswordResetResult> objectConsumer) {
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_PASSWORD_RESET_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("emailOrNickname", str);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("resetPassword", httpRequest, (z, str2) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str2, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str2);
                    if (parse.getString("status").equals("success")) {
                        if (objectConsumer == null) {
                            return;
                        }
                        objectConsumer.accept(PasswordResetResult.SUCCESS);
                        return;
                    } else {
                        f2284b.e("failed to reset password: " + str2, new Object[0]);
                        if (objectConsumer != null) {
                            try {
                                objectConsumer.accept(PasswordResetResult.valueOf(parse.getString("passwordResetResult")));
                                return;
                            } catch (Exception unused) {
                                objectConsumer.accept(PasswordResetResult.OTHER_ERROR);
                                return;
                            }
                        }
                        return;
                    }
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    if (objectConsumer != null) {
                        objectConsumer.accept(PasswordResetResult.OTHER_ERROR);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to reset password", new Object[0]);
            if (objectConsumer != null) {
                objectConsumer.accept(PasswordResetResult.OTHER_ERROR);
            }
        }));
    }

    public void setPassword(String str, ObjectConsumer<Boolean> objectConsumer) {
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        if (!isSignedIn() || getSessionId() == null) {
            f2284b.e("setPassword cancelled - player not signed in", new Object[0]);
            objectConsumer.accept(Boolean.FALSE);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_PASSWORD_SET_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("password", str);
        hashMap.put("sessionid", getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("setPassword", httpRequest, (z, str2) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str2, new Object[0]);
                    }
                    if (new JsonReader().parse(str2).getString("status").equals("success")) {
                        try {
                            Game.i.preferencesManager.getSettingsPrefs().auth.sessionData.passwordSet = true;
                        } catch (Exception unused) {
                        }
                        if (objectConsumer == null) {
                            return;
                        }
                        objectConsumer.accept(Boolean.TRUE);
                        return;
                    }
                    f2284b.e("failed to set password: " + str2, new Object[0]);
                    if (objectConsumer != null) {
                        objectConsumer.accept(Boolean.FALSE);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    if (objectConsumer != null) {
                        objectConsumer.accept(Boolean.FALSE);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to set password", new Object[0]);
            if (objectConsumer != null) {
                objectConsumer.accept(Boolean.FALSE);
            }
        }));
    }

    public void confirmEmail(ObjectConsumer<ConfirmEmailResult> objectConsumer) {
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        String sessionId = getSessionId();
        if (sessionId == null) {
            f2284b.e("can't confirmEmail - no session id", new Object[0]);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_CONFIRM_EMAIL_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", sessionId);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("confirmEmail", httpRequest, (z, str) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str);
                    if (parse.getString("status").equals("success")) {
                        if (objectConsumer == null) {
                            return;
                        }
                        objectConsumer.accept(ConfirmEmailResult.SUCCESS);
                        return;
                    } else {
                        f2284b.e("failed to reset password: " + str, new Object[0]);
                        if (objectConsumer != null) {
                            try {
                                objectConsumer.accept(ConfirmEmailResult.valueOf(parse.getString("statusCode")));
                                return;
                            } catch (Exception unused) {
                                objectConsumer.accept(ConfirmEmailResult.OTHER_ERROR);
                                return;
                            }
                        }
                        return;
                    }
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    if (objectConsumer != null) {
                        objectConsumer.accept(ConfirmEmailResult.OTHER_ERROR);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to confirm email", new Object[0]);
            if (objectConsumer != null) {
                objectConsumer.accept(ConfirmEmailResult.OTHER_ERROR);
            }
        }));
    }

    public void signUpWithSteam(String str, String str2, String str3, ObjectConsumer<SignUpResult> objectConsumer) {
        Preconditions.checkNotNull(str, "nickname can not be null");
        Preconditions.checkNotNull(str2, "email can not be null");
        Preconditions.checkNotNull(objectConsumer, "callback can not be null");
        if (Config.isHeadless()) {
            return;
        }
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
        } else {
            Game.i.actionResolver.requestSteamAuthTicket(str4 -> {
                if (str4 == null) {
                    f2284b.e("failed to get Steam auth ticket", new Object[0]);
                    objectConsumer.accept(SignUpResult.OTHER_ERROR);
                } else {
                    Game.i.httpManager.post(Config.AUTH_SIGN_UP_STEAM_URL).param("ticket", str4).param("nickname", str).param("email", str2).param("inviteCode", str3).listener((z, httpResponse, z2, th) -> {
                        if (z) {
                            try {
                                String resultAsString = httpResponse.getResultAsString();
                                JsonValue parse = new JsonReader().parse(resultAsString);
                                if (parse.getString("status").equals("success")) {
                                    Game.i.analyticsManager.logSignedUp("steam");
                                    Threads.i().runOnMainThread(() -> {
                                        loadStateFromServer(parse.getString("sessionid"), () -> {
                                            setAutoSavesEnabled(true);
                                            Threads.i().runOnMainThread(() -> {
                                                objectConsumer.accept(SignUpResult.SUCCESS);
                                            });
                                        });
                                    });
                                    return;
                                } else {
                                    f2284b.e("failed to sign up: " + resultAsString, new Object[0]);
                                    Threads.i().runOnMainThread(() -> {
                                        signOut();
                                        try {
                                            objectConsumer.accept(SignUpResult.valueOf(parse.getString("signUpResult")));
                                        } catch (Exception e) {
                                            f2284b.e("failed to parse SignUpResult", e);
                                            objectConsumer.accept(SignUpResult.OTHER_ERROR);
                                        }
                                    });
                                    return;
                                }
                            } catch (Exception e) {
                                f2284b.e("failed to parse Steam Sign up request", e);
                                Threads.i().runOnMainThread(() -> {
                                    objectConsumer.accept(SignUpResult.OTHER_ERROR);
                                });
                                return;
                            }
                        }
                        Threads.i().runOnMainThread(() -> {
                            objectConsumer.accept(SignUpResult.OTHER_ERROR);
                        });
                    }).send();
                }
            });
        }
    }

    public void signInEnterOTP(String str, String str2, ObjectConsumer<ObjectPair<Boolean, String>> objectConsumer) {
        Preconditions.checkNotNull(objectConsumer, "callback");
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_SIGN_IN_CONFIRM_OTP_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("otpRequestId", str2);
        hashMap.put(FlexmarkHtmlConverter.CODE_NODE, str);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("signInEnterOTP", httpRequest, (z, str3) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str3, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str3);
                    if (parse.getString("status").equals("success")) {
                        Game.i.analyticsManager.logSignedIn("otp");
                        loadStateFromServer(parse.getString("sessionid"), () -> {
                            objectConsumer.accept(new ObjectPair(Boolean.TRUE, null));
                            e();
                        });
                        return;
                    } else {
                        f2284b.e("OTP validation failed: " + str3, new Object[0]);
                        objectConsumer.accept(new ObjectPair(Boolean.FALSE, parse.getString("message")));
                        return;
                    }
                } catch (Exception e) {
                    f2284b.e("failed to handle OTP response", e);
                    objectConsumer.accept(new ObjectPair(Boolean.FALSE, e.getMessage()));
                    return;
                }
            }
            f2284b.e("request failed", new Object[0]);
            objectConsumer.accept(new ObjectPair(Boolean.FALSE, "Request failed"));
        }));
    }

    public void signInShowOtpForm(final String str, final String str2, final ObjectConsumer<SignInResponse> objectConsumer) {
        TextInputOverlay.i().show(new Input.TextInputListener() { // from class: com.prineside.tdi2.managers.AuthManager.7
            @Override // com.badlogic.gdx.Input.TextInputListener
            public void input(String str3) {
                if (str3.length() != 6 || !str3.matches("[0-9]+")) {
                    Notifications.i().add("Incorrect code - must be 6 digits long", null, null, StaticSoundType.FAIL);
                    AuthManager.this.signInShowOtpForm(str, str2, objectConsumer);
                    return;
                }
                AuthManager authManager = AuthManager.this;
                String str4 = str;
                ObjectConsumer objectConsumer2 = objectConsumer;
                String str5 = str;
                String str6 = str2;
                authManager.signInEnterOTP(str3, str4, objectPair -> {
                    if (((Boolean) objectPair.first).booleanValue()) {
                        if (objectConsumer2 != null) {
                            objectConsumer2.accept(new SignInResponse(SignInResult.SUCCESS));
                        }
                    } else {
                        if (objectPair.second != 0) {
                            Notifications.i().add((CharSequence) objectPair.second, null, null, StaticSoundType.FAIL);
                        }
                        AuthManager.this.signInShowOtpForm(str5, str6, objectConsumer2);
                    }
                });
            }

            @Override // com.badlogic.gdx.Input.TextInputListener
            public void canceled() {
                if (objectConsumer != null) {
                    objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR));
                }
            }
        }, Game.i.localeManager.i18n.format("otp_code_prompt_title", str2), "", "Enter code from your Authenticator app");
    }

    public void signIn(String str, String str2, ObjectConsumer<SignInResponse> objectConsumer) {
        if (Config.isHeadless()) {
            return;
        }
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_SIGN_IN_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("login", str);
        hashMap.put("password", str2);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("signIn", httpRequest, (z, str3) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str3, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str3);
                    if (parse.getString("status").equals("success")) {
                        if (parse.getString("signInResult").equals("OTP_REQUIRED")) {
                            signInShowOtpForm(parse.getString("otpRequestId"), parse.getString("nickname"), objectConsumer);
                            return;
                        } else {
                            Game.i.analyticsManager.logSignedIn("password");
                            loadStateFromServer(parse.getString("sessionid"), () -> {
                                if (objectConsumer != null) {
                                    objectConsumer.accept(new SignInResponse(SignInResult.SUCCESS));
                                }
                                e();
                            });
                            return;
                        }
                    }
                    f2284b.e("failed to sign in: " + str3, new Object[0]);
                    signOut();
                    if (objectConsumer != null) {
                        try {
                            objectConsumer.accept(new SignInResponse(SignInResult.valueOf(parse.getString("signInResult"))));
                            return;
                        } catch (Exception e) {
                            objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage(e.getMessage()));
                            return;
                        }
                    }
                    return;
                } catch (Exception e2) {
                    f2284b.e("Failed to parse response", e2);
                    signOut();
                    if (objectConsumer != null) {
                        objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage(e2.getMessage()));
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to sign in", new Object[0]);
            signOut();
            if (objectConsumer != null) {
                objectConsumer.accept(new SignInResponse(SignInResult.OTHER_ERROR).setErrorMessage("Request failed"));
            }
        }));
    }

    public void getCloudSavedGamesList(ObjectConsumer<JsonValue> objectConsumer) {
        if (Config.isHeadless()) {
            return;
        }
        if (this.c != SignInStatus.SIGNED_IN) {
            f2284b.e("unable to load list of cloud saved games while not signed in", new Object[0]);
            if (objectConsumer != null) {
                objectConsumer.accept(null);
                return;
            }
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.GET_SAVED_GAMES_LIST_URL);
        HashMap hashMap = new HashMap();
        if (getSessionId() == null) {
            throw new RuntimeException("sessionId is null");
        }
        hashMap.put("sessionid", getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("getCloudSavedGamesList", httpRequest, (z, str) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str);
                    if (parse.getString("status").equals("success")) {
                        if (objectConsumer != null) {
                            objectConsumer.accept(parse.get("savedGames"));
                            return;
                        }
                        return;
                    } else {
                        f2284b.e("failed to load saved games: " + str, new Object[0]);
                        if (objectConsumer != null) {
                            objectConsumer.accept(null);
                            return;
                        }
                        return;
                    }
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    if (objectConsumer != null) {
                        objectConsumer.accept(null);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to get cloud saves", new Object[0]);
            if (objectConsumer != null) {
                objectConsumer.accept(null);
            }
        }));
    }

    public void setAutoSavesEnabled(boolean z) {
        if (SettingsPrefs.i().auth.autoSavesEnabled != z) {
            SettingsPrefs.i().auth.autoSavesEnabled = z;
            SettingsPrefs.i().requireSave();
            this.f1735a.begin();
            for (int i = 0; i < this.f1735a.size; i++) {
                ((AuthManagerListener) this.f1735a.get(i)).autoSaveModeChanged();
            }
            this.f1735a.end();
            if (z) {
                Notifications.i().add(Game.i.localeManager.i18n.get("auto_saves_enabled"), Game.i.assetManager.getDrawable("icon-info"), MaterialColor.GREEN.P800, StaticSoundType.NOTIFICATION);
            } else {
                Notifications.i().add(Game.i.localeManager.i18n.get("auto_saves_disabled"), Game.i.assetManager.getDrawable("icon-info"), MaterialColor.AMBER.P800, StaticSoundType.NOTIFICATION);
            }
        }
    }

    public boolean isAutoSavesEnabled() {
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            return false;
        }
        return SettingsPrefs.i().auth.autoSavesEnabled;
    }

    public void signUp(String str, String str2, String str3, String str4, ObjectConsumer<SignUpResult> objectConsumer) {
        if (Config.isHeadless()) {
            return;
        }
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.AUTH_SIGN_UP_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("login", str);
        hashMap.put("password", str2);
        hashMap.put("email", str3);
        hashMap.put("inviteCode", str4);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        queueRequest(new HttpQueuedRequest("signUp", httpRequest, (z, str5) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i("Response: " + str5, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str5);
                    if (parse.getString("status").equals("success")) {
                        Game.i.analyticsManager.logSignedUp("password");
                        loadStateFromServer(parse.getString("sessionid"), () -> {
                            setAutoSavesEnabled(true);
                            if (objectConsumer != null) {
                                objectConsumer.accept(SignUpResult.SUCCESS);
                            }
                        });
                        return;
                    }
                    f2284b.e("failed to sign up: " + str5, new Object[0]);
                    signOut();
                    if (objectConsumer != null) {
                        try {
                            objectConsumer.accept(SignUpResult.valueOf(parse.getString("signUpResult")));
                            return;
                        } catch (Exception unused) {
                            objectConsumer.accept(SignUpResult.OTHER_ERROR);
                            return;
                        }
                    }
                    return;
                } catch (Exception e) {
                    f2284b.e("Failed to parse response", e);
                    signOut();
                    if (objectConsumer != null) {
                        objectConsumer.accept(SignUpResult.OTHER_ERROR);
                        return;
                    }
                    return;
                }
            }
            f2284b.e("Failed to sign up", new Object[0]);
            signOut();
            if (objectConsumer != null) {
                objectConsumer.accept(SignUpResult.OTHER_ERROR);
            }
        }));
    }

    public void signUpWithGoogle(String str, String str2, ObjectConsumer<SignUpResult> objectConsumer) {
        Preconditions.checkNotNull(str, "nickname can not be null");
        Preconditions.checkNotNull(objectConsumer, "callback can not be null");
        if (Config.isHeadless()) {
            return;
        }
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            Notifications.i().add(Game.i.localeManager.i18n.get("app_is_modified"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
        } else {
            Game.i.actionResolver.requestGoogleAuth(str3 -> {
                if (str3 == null) {
                    f2284b.e("failed to get Google auth ticket", new Object[0]);
                    objectConsumer.accept(SignUpResult.OTHER_ERROR);
                } else {
                    Game.i.httpManager.post(Config.AUTH_SIGN_UP_GOOGLE_URL).param("ticket", str3).param("nickname", str).param("inviteCode", str2).listener((z, httpResponse, z2, th) -> {
                        if (z) {
                            try {
                                String resultAsString = httpResponse.getResultAsString();
                                JsonValue parse = new JsonReader().parse(resultAsString);
                                if (parse.getString("status").equals("success")) {
                                    Game.i.analyticsManager.logSignedUp("google");
                                    Threads.i().runOnMainThread(() -> {
                                        loadStateFromServer(parse.getString("sessionid"), () -> {
                                            setAutoSavesEnabled(true);
                                            Threads.i().runOnMainThread(() -> {
                                                objectConsumer.accept(SignUpResult.SUCCESS);
                                            });
                                        });
                                    });
                                    return;
                                } else {
                                    f2284b.e("failed to sign up: " + resultAsString, new Object[0]);
                                    Threads.i().runOnMainThread(() -> {
                                        signOut();
                                        try {
                                            objectConsumer.accept(SignUpResult.valueOf(parse.getString("signUpResult")));
                                        } catch (Exception e) {
                                            f2284b.e("failed to parse SignUpResult", e);
                                            objectConsumer.accept(SignUpResult.OTHER_ERROR);
                                        }
                                    });
                                    return;
                                }
                            } catch (Exception e) {
                                f2284b.e("failed to parse Google Sign up request", e);
                                Threads.i().runOnMainThread(() -> {
                                    objectConsumer.accept(SignUpResult.OTHER_ERROR);
                                });
                                return;
                            }
                        }
                        Threads.i().runOnMainThread(() -> {
                            objectConsumer.accept(SignUpResult.OTHER_ERROR);
                        });
                    }).send();
                }
            });
        }
    }

    public void getNews(ObjectConsumer<NewsResponse> objectConsumer) {
        if (Config.isHeadless()) {
            return;
        }
        Game.i.assertInMainThread();
        if (this.e != null) {
            if (Game.getTimestampSeconds() - this.e.cachedAt < 600) {
                objectConsumer.accept(this.e);
                return;
            }
            this.e = null;
        }
        this.d.add(objectConsumer);
    }

    public void receiveIssuedItemsFromServer(ObjectConsumer<ReceivedIssuedItemsResponse> objectConsumer) {
        if (Config.isHeadless() || getSessionId() == null) {
            return;
        }
        Game.i.assertInMainThread();
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.RECEIVE_ISSUED_ITEMS_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("sessionid", getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        if (this.e != null) {
            this.e.itemsFromServer.clear();
        }
        queueRequest(new HttpQueuedRequest("receiveIssuedItemsFromServer", httpRequest, (z, str) -> {
            if (z) {
                try {
                    if (b()) {
                        f2284b.i(str, new Object[0]);
                    }
                    JsonValue parse = new JsonReader().parse(str);
                    if (parse.getString("status").equals("success")) {
                        try {
                            ReceivedIssuedItemsResponse receivedIssuedItemsResponse = new ReceivedIssuedItemsResponse(this);
                            JsonValue jsonValue = parse.get("issuedItems");
                            if (jsonValue != null) {
                                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                                while (iterator2.hasNext()) {
                                    receivedIssuedItemsResponse.items.add(IssuedItems.fromJson(iterator2.next()));
                                }
                            }
                            objectConsumer.accept(receivedIssuedItemsResponse);
                            return;
                        } catch (Exception e) {
                            f2284b.e("failed to parse receiveIssuedItemsFromServer", e);
                            return;
                        }
                    }
                    f2284b.e("can't retrieve receiveIssuedItemsFromServer: " + str, new Object[0]);
                    return;
                } catch (Exception e2) {
                    f2284b.e("Failed to parse response", e2);
                    return;
                }
            }
            f2284b.e("can't retrieve receiveIssuedItemsFromServer", new Object[0]);
        }));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$AuthManagerListener.class */
    public interface AuthManagerListener {
        void signInStatusUpdated();

        void autoSaveModeChanged();

        void stateUpdated();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$AuthManagerListener$AuthManagerListenerAdapter.class */
        public static abstract class AuthManagerListenerAdapter implements AuthManagerListener {
            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void signInStatusUpdated() {
            }

            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void autoSaveModeChanged() {
            }

            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void stateUpdated() {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$ReceivedIssuedItemsResponse.class */
    public class ReceivedIssuedItemsResponse {
        public Array<IssuedItems> items = new Array<>(IssuedItems.class);

        public ReceivedIssuedItemsResponse(AuthManager authManager) {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$HttpQueuedRequest.class */
    public static class HttpQueuedRequest {

        /* renamed from: a, reason: collision with root package name */
        private String f2298a;

        /* renamed from: b, reason: collision with root package name */
        private Net.HttpRequest f2299b;
        private Listener c;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$HttpQueuedRequest$Listener.class */
        public interface Listener {
            void finished(boolean z, String str);
        }

        static /* synthetic */ String a(HttpQueuedRequest httpQueuedRequest) {
            return httpQueuedRequest.f2298a;
        }

        static /* synthetic */ Listener c(HttpQueuedRequest httpQueuedRequest) {
            return httpQueuedRequest.c;
        }

        public HttpQueuedRequest(String str, Net.HttpRequest httpRequest, Listener listener) {
            this.f2298a = str;
            this.f2299b = httpRequest;
            this.c = listener;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$PasteBinResponse.class */
    public static class PasteBinResponse {
        public boolean success;
        public String errorDescription;
        public String link;

        public PasteBinResponse() {
        }

        public PasteBinResponse(boolean z, String str, String str2) {
            this.success = z;
            this.errorDescription = str;
            this.link = str2;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AuthManager$SignInResponse.class */
    public static class SignInResponse {
        public SignInResult result;
        public String otpRequestId;

        @Null
        public String errorMessage;

        public SignInResponse() {
        }

        public SignInResponse(SignInResult signInResult) {
            this.result = signInResult;
        }

        public SignInResponse(SignInResult signInResult, String str) {
            this.result = signInResult;
            this.otpRequestId = str;
        }

        @Null
        public String getErrorMessage() {
            return this.errorMessage;
        }

        public SignInResponse setErrorMessage(String str) {
            this.errorMessage = str;
            return this;
        }
    }
}

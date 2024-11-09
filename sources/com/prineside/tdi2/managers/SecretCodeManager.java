package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SecretCodeManager.class */
public class SecretCodeManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2450a = TLog.forClass(SecretCodeManager.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SecretCodeManager$CodeValidationResult.class */
    public static class CodeValidationResult {
        public String message;
        public Array<ItemStack> contents;
        public CodeValidationResultCode code;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SecretCodeManager$CodeValidationResultCode.class */
    public enum CodeValidationResultCode {
        NOT_EXISTS,
        APPLIED,
        ALREADY_USED,
        PROCESSING,
        ERROR,
        GAME_BUILD_MISMATCH
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SecretCodeManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<SecretCodeManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public SecretCodeManager read() {
            return Game.i.secretCodeManager;
        }
    }

    public boolean isBuildCompatible(int i) {
        if (208 < i || i < 18) {
            return false;
        }
        return true;
    }

    public void applyCode(String str) {
        if (Config.isHeadless()) {
            return;
        }
        if (str.equals("unlocklocales")) {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UNLOCK_ALL_LOCALES, 1.0d);
            Dialog.i().showAlert("All locales unlocked");
            return;
        }
        if (!str.matches("[A-Za-z0-9]+")) {
            Dialog.i().showAlert(Game.i.localeManager.i18n.get("code_not_exists"));
            return;
        }
        final String lowerCase = str.toLowerCase(Locale.ENGLISH);
        try {
            if (a(lowerCase)) {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("code_already_used"));
                return;
            }
            try {
                Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                httpRequest.setUrl(Config.SECRET_CODE_APPLICATION_URL);
                HashMap hashMap = new HashMap();
                hashMap.put(FlexmarkHtmlConverter.CODE_NODE, lowerCase);
                hashMap.put("playerid", Game.i.authManager.getPlayerId());
                if (Game.i.authManager.getSessionId() != null) {
                    hashMap.put("sessionid", Game.i.authManager.getSessionId());
                }
                hashMap.put("locale", Game.i.localeManager.getLocale());
                httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.SecretCodeManager.1
                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        try {
                            String resultAsString = httpResponse.getResultAsString();
                            SecretCodeManager.f2450a.i(resultAsString, new Object[0]);
                            Threads i = Threads.i();
                            String str2 = lowerCase;
                            i.runOnMainThread(() -> {
                                try {
                                    JsonValue parse = new JsonReader().parse(resultAsString);
                                    if (!parse.getString("status").equals("success")) {
                                        Threads.i().runOnMainThread(() -> {
                                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("error") + ": " + parse.getString("message", "Unknown error"));
                                        });
                                        return;
                                    }
                                    if (SecretCodeManager.this.isBuildCompatible(parse.getInt("build"))) {
                                        SecretCodeManager.this.b(str2);
                                        JsonValue jsonValue = parse.get("contents");
                                        Array array = new Array();
                                        Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                                        while (iterator2.hasNext()) {
                                            ItemStack fromJson = ItemStack.fromJson(iterator2.next());
                                            Game.i.progressManager.addItemStack(fromJson, "secret_code");
                                            array.add(fromJson);
                                        }
                                        Array<? extends ItemStack> array2 = new Array<>();
                                        for (int i2 = 0; i2 < array.size; i2++) {
                                            ItemStack itemStack = (ItemStack) array.get(i2);
                                            boolean z = false;
                                            int i3 = 0;
                                            while (true) {
                                                if (i3 >= array2.size) {
                                                    break;
                                                }
                                                if (!array2.get(i3).getItem().sameAs(itemStack.getItem())) {
                                                    i3++;
                                                } else {
                                                    array2.get(i3).setCount(PMath.addWithoutOverflow(array2.get(i3).getCount(), itemStack.getCount()));
                                                    z = true;
                                                    break;
                                                }
                                            }
                                            if (!z) {
                                                array2.add(new ItemStack(itemStack));
                                            }
                                        }
                                        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.SECRET_CODE, Game.getTimestampSeconds());
                                        issuedItems.items.addAll(array2);
                                        issuedItems.secretCode = str2;
                                        issuedItems.secretCodeDescription = parse.getString("message", "Success!");
                                        Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                                        Game.i.progressManager.showNewlyIssuedPrizesPopup();
                                        Game.i.analyticsManager.logCustomEvent("secret_code", new String[0], new String[]{FlexmarkHtmlConverter.CODE_NODE, str2});
                                        return;
                                    }
                                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("code_is_deprecated"));
                                } catch (Throwable th) {
                                    SecretCodeManager.f2450a.e("Exception: " + th.getMessage(), th);
                                    Threads.i().runOnMainThread(() -> {
                                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("error") + ": something went wrong, please try again");
                                    });
                                }
                            });
                        } catch (Exception e) {
                            SecretCodeManager.f2450a.e("Exception: " + e.getMessage(), e);
                        }
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void failed(Throwable th) {
                        SecretCodeManager.f2450a.i("Error redeeming secret code: " + th.getMessage(), new Object[0]);
                        Threads.i().runOnMainThread(() -> {
                            Dialog.i().showAlert("Error, please try again later");
                        });
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void cancelled() {
                        SecretCodeManager.f2450a.i("Timeout while redeeming secret code", new Object[0]);
                        Threads.i().runOnMainThread(() -> {
                            Dialog.i().showAlert("Error, please try again later");
                        });
                    }
                });
            } catch (Exception e) {
                f2450a.e("Failed", e);
                Dialog.i().showAlert("Error, please try again later");
            }
        } catch (Exception unused) {
            Dialog.i().showAlert(Game.i.localeManager.i18n.get("code_not_exists"));
        }
    }

    private static boolean a(String str) {
        return ProgressPrefs.i().secretCode.isCodeApplied(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (a(str)) {
            return;
        }
        ProgressPrefs.i().secretCode.setCodeApplied(str);
        ProgressPrefs.i().requireSave();
    }
}

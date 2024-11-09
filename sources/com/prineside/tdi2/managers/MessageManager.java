package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.items.DoubleGainShardItem;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.WebView;
import com.prineside.tdi2.ui.shared.MessagesOverlay;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MessageManager.class */
public final class MessageManager extends Manager.ManagerWithListeners<MessageManagerListener> {

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f2377b = TLog.forClass(MessageManager.class);
    private boolean c;
    private final Array<Message> d = new Array<>(true, 1, Message.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MessageManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<MessageManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public MessageManager read() {
            return Game.i.messageManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerWithListeners, com.prineside.tdi2.Manager
    public final void setup() {
        Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
            processLocalMessages();
            requestMessagesFromServer();
        });
        Game.i.authManager.addListener(new AuthManager.AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.MessageManager.1
            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void signInStatusUpdated() {
                MessageManager.this.processLocalMessages();
                MessageManager.this.requestMessagesFromServer();
            }

            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void autoSaveModeChanged() {
                MessageManager.this.processLocalMessages();
            }
        });
        Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.MessageManager.2
            @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
            public void reloaded() {
                MessageManager.this.processLocalMessages();
                MessageManager.this.requestMessagesFromServer();
            }
        });
    }

    public final void processLocalMessages() {
        Game.i.authManager.getNews(newsResponse -> {
            Message message = getMessage("issued_items");
            if (newsResponse != null && newsResponse.itemsFromServer.size != 0) {
                if (message != null && ((Integer) message.userData.get("count")).intValue() == newsResponse.itemsFromServer.size) {
                    return;
                }
                if (message != null) {
                    removeMessage(message);
                }
                Message message2 = new Message();
                message2.id = "issued_items";
                message2.date = Game.getTimestampSeconds();
                message2.title = Game.i.localeManager.i18n.format("new_items_from_server_count", Integer.valueOf(newsResponse.itemsFromServer.size));
                message2.local = true;
                message2.notDeletable = true;
                message2.notReadable = true;
                message2.customIcon = Game.i.assetManager.getDrawable("icon-new-item");
                message2.userData.put("count", Integer.valueOf(newsResponse.itemsFromServer.size));
                Table table = new Table();
                message2.contents = table;
                table.add(new FancyButton("regularButton.a", () -> {
                    removeMessage(message2);
                    Game.i.authManager.receiveIssuedItemsFromServer(receivedIssuedItemsResponse -> {
                        for (int i = 0; i < receivedIssuedItemsResponse.items.size; i++) {
                            IssuedItems issuedItems = receivedIssuedItemsResponse.items.get(i);
                            for (int i2 = 0; i2 < issuedItems.items.size; i2++) {
                                Game.i.progressManager.addItemStack(issuedItems.items.get(i2), "server_issued");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                        }
                        Game.i.progressManager.showNewlyIssuedPrizesPopup();
                        MessagesOverlay.i().show();
                    });
                }).withLabel(24, Game.i.localeManager.i18n.get("receive_server_items_button"))).size(400.0f, 64.0f);
                addMessage(message2);
                return;
            }
            if (message != null) {
                removeMessage(message);
            }
        });
        if (!ProgressPrefs.i().progress.isMidGameDgRewardGiven() && !isMessageItemsReceived("stage_4_unlock_reward") && Game.i.basicLevelManager.isOpened(Game.i.basicLevelManager.getLevel("4.1")) && getMessage("stage_4_unlock_reward") == null) {
            Message message = new Message();
            message.id = "stage_4_unlock_reward";
            message.date = Game.getTimestampSeconds();
            message.title = Game.i.localeManager.i18n.format("reward_for_stage_4_unlock", new Object[0]);
            message.local = true;
            message.notDeletable = true;
            message.notReadable = true;
            message.customIcon = Game.i.assetManager.getDrawable("icon-new-item");
            Table table = new Table();
            Label label = new Label(Game.i.localeManager.i18n.get("reward_for_stage_4_unlock_description"), Game.i.assetManager.getLabelStyle(24));
            label.setWrap(true);
            table.add((Table) label).growX().row();
            message.contents = table;
            message.items = new Array<>(true, 1, ItemStack.class);
            message.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.CYAN, false), 1));
            if (Game.i.progressManager.hasPermanentDoubleGain()) {
                message.items.add(new ItemStack(Item.D.ACCELERATOR, 400));
            } else {
                DoubleGainShardItem create = Item.D.F_DOUBLE_GAIN_SHARD.create();
                create.duration = 172800;
                message.items.add(new ItemStack(create, 1));
                table.add((Table) new Label(Game.i.localeManager.i18n.get("reward_for_stage_4_unlock_dg_shard_hint"), Game.i.assetManager.getLabelStyle(24))).growX().padTop(15.0f).row();
            }
            message.onItemsReceive = () -> {
                ProgressPrefs.i().progress.setMidGameDgRewardGiven(true);
                ProgressPrefs.i().requireSave();
                removeMessage(message);
            };
            addMessage(message);
        }
    }

    public final Array<Message> getMessages() {
        this.d.sort((message, message2) -> {
            return Integer.compare(message.date, message2.date);
        });
        return this.d;
    }

    public final boolean isMessageRead(String str) {
        return ProgressPrefs.i().message.isMessageRead(str);
    }

    public final void markMessageRead(Message message) {
        if (isMessageRead(message.id) || message.notReadable) {
            return;
        }
        ProgressPrefs.i().message.setMessageRead(message.id);
        ProgressPrefs.i().requireSave();
        if (!message.local && Game.i.authManager.getSessionId() != null) {
            Game.i.httpManager.post(Config.MARK_MESSAGE_URL).param("sessionid", Game.i.authManager.getSessionId()).param("message", message.id).param("status", "read").listener((z, httpResponse, z2, th) -> {
                if (z) {
                    f2377b.i("markMessageRead server: " + httpResponse.getResultAsString(), new Object[0]);
                } else {
                    f2377b.e("failed to mark message read on the server", th);
                }
            }).send();
        }
        a();
    }

    public final boolean isMessageEverDeleted(String str) {
        return ProgressPrefs.i().message.isMessageDeleted(str);
    }

    public final boolean isMessageItemsReceived(String str) {
        return ProgressPrefs.i().message.isMessageItemsReceived(str);
    }

    public final void deleteMessage(Message message) {
        if (!message.notDeletable && this.d.removeValue(message, true)) {
            if (!ProgressPrefs.i().message.isMessageDeleted(message.id)) {
                ProgressPrefs.i().message.setMessageDeleted(message.id);
                ProgressPrefs.i().requireSave();
            }
            if (!message.local && Game.i.authManager.getSessionId() != null) {
                Game.i.httpManager.post(Config.MARK_MESSAGE_URL).param("sessionid", Game.i.authManager.getSessionId()).param("message", message.id).param("status", "deleted").listener((z, httpResponse, z2, th) -> {
                    if (z) {
                        f2377b.i("deleteMessage server: " + httpResponse.getResultAsString(), new Object[0]);
                    } else {
                        f2377b.e("failed to mark message deleted on the server", th);
                    }
                }).send();
            }
            ProgressPrefs.i().message.removeMessageReadRecord(message.id);
            ProgressPrefs.i().requireSave();
            a();
        }
    }

    public final void requestMessagesFromServer() {
        if (Game.i.authManager.getSessionId() == null || this.c) {
            return;
        }
        this.c = true;
        this.f1735a.begin();
        int i = this.f1735a.size;
        for (int i2 = 0; i2 < i; i2++) {
            ((MessageManagerListener) this.f1735a.get(i2)).serverRequestStarted();
        }
        this.f1735a.end();
        Game.i.httpManager.post(Config.GET_MESSAGES_URL).param("locale", Game.i.localeManager.getLocale()).param("sessionid", Game.i.authManager.getSessionId()).listener((z, httpResponse, z2, th) -> {
            if (z) {
                String resultAsString = httpResponse.getResultAsString();
                Threads.i().runOnMainThread(() -> {
                    try {
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (AuthManager.checkIncorrectSessionIdApiResponse(parse)) {
                            Iterator<JsonValue> iterator2 = parse.iterator2();
                            while (iterator2.hasNext()) {
                                JsonValue next = iterator2.next();
                                try {
                                    Message message = new Message();
                                    message.id = next.getString(Attribute.ID_ATTR);
                                    message.title = next.getString(Attribute.TITLE_ATTR);
                                    WebView webView = new WebView();
                                    webView.loadPage(next.getString("body"));
                                    message.contents = webView;
                                    message.notDeletable = !next.getBoolean("is_deleteable", true);
                                    if (next.get("items") != null) {
                                        message.items = new Array<>(true, 1, ItemStack.class);
                                        Iterator<JsonValue> iterator22 = next.get("items").iterator2();
                                        while (iterator22.hasNext()) {
                                            message.items.add(ItemStack.fromJson(iterator22.next()));
                                        }
                                    }
                                    message.date = next.getInt("created_at");
                                    String string = next.getString("custom_icon", null);
                                    if (string != null) {
                                        try {
                                            if (string.startsWith("@")) {
                                                message.customIcon = Game.i.assetManager.getDrawable(string.substring(1));
                                            } else {
                                                message.customIcon = Quad.fromString(string);
                                            }
                                        } catch (Exception e) {
                                            f2377b.e("failed to parse custom icon for the message: " + string, e);
                                        }
                                    }
                                    addMessage(message);
                                    if (next.getBoolean("viewed")) {
                                        markMessageRead(message);
                                    }
                                } catch (Exception e2) {
                                    f2377b.e("failed to parse message: " + next.toJson(JsonWriter.OutputType.json), e2);
                                }
                            }
                        }
                    } catch (Exception e3) {
                        f2377b.e("failed to parse messages from the server: " + resultAsString, e3);
                    }
                    this.c = false;
                    b();
                });
            } else {
                f2377b.e("failed to request server for messages", th);
                Threads.i().runOnMainThread(() -> {
                    this.c = false;
                    b();
                });
            }
        }).send();
    }

    public final void removeMessage(Message message) {
        if (this.d.removeValue(message, true)) {
            a();
        }
    }

    public final void addMessage(Message message) {
        Message message2 = getMessage(message.id);
        if (message2 != null) {
            removeMessage(message2);
        }
        this.d.add(message);
        a();
    }

    @Null
    public final Message getMessage(String str) {
        Preconditions.checkNotNull(str, "id can not be null");
        for (int i = 0; i < this.d.size; i++) {
            if (this.d.get(i).id.equals(str)) {
                return this.d.get(i);
            }
        }
        return null;
    }

    public final int getUnreadMessageCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.d.size; i2++) {
            if (!isMessageRead(this.d.items[i2].id)) {
                i++;
            }
        }
        return i;
    }

    public final int getTotalMessageCount() {
        return this.d.size;
    }

    public final void receiveMessageItems(Message message) {
        if (isMessageItemsReceived(message.id)) {
            return;
        }
        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.REGULAR_REWARD, Game.getTimestampSeconds());
        Array<? extends ItemStack> array = new Array<>(ItemStack.class);
        array.addAll(message.items);
        issuedItems.items.addAll(array);
        Game.i.progressManager.addIssuedPrizes(issuedItems, true);
        Game.i.progressManager.addItemArray(issuedItems.items, "message_items");
        Game.i.progressManager.showNewlyIssuedPrizesPopup();
        ProgressPrefs.i().message.setMessageItemsReceived(message.id);
        ProgressPrefs.i().requireSave();
        if (message.onItemsReceive != null) {
            message.onItemsReceive.run();
        }
        a();
    }

    private void a() {
        this.f1735a.begin();
        int i = this.f1735a.size;
        for (int i2 = 0; i2 < i; i2++) {
            ((MessageManagerListener) this.f1735a.get(i2)).messagesUpdated();
        }
        this.f1735a.end();
    }

    private void b() {
        this.f1735a.begin();
        int i = this.f1735a.size;
        for (int i2 = 0; i2 < i; i2++) {
            ((MessageManagerListener) this.f1735a.get(i2)).serverRequestFinished();
        }
        this.f1735a.end();
    }

    public final boolean isRequestingServer() {
        return this.c;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MessageManager$Message.class */
    public static final class Message {
        public String id;
        public String title;
        public Actor contents;
        public Drawable customIcon;
        public int date;
        public boolean local;
        public boolean notDeletable;
        public boolean notReadable;

        @Null
        public Runnable onItemsReceive;

        @Null
        public Array<ItemStack> items = null;
        public ObjectMap<String, Object> userData = new ObjectMap<>();

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(super.toString()).append(" {");
            stringBuilder.append("id: ").append(this.id).append(", ");
            stringBuilder.append("title: ").append(this.title).append(", ");
            stringBuilder.append("contents: ").append(this.contents).append(", ");
            stringBuilder.append("customIcon: ").append(this.customIcon).append(", ");
            stringBuilder.append("date: ").append(this.date).append(", ");
            stringBuilder.append("local: ").append(this.local).append(", ");
            stringBuilder.append("notDeletable: ").append(this.notDeletable).append(", ");
            stringBuilder.append("notReadable: ").append(this.notReadable).append(", ");
            stringBuilder.append("items: ").append(this.items).append(", ");
            stringBuilder.append("userData: ").append(this.userData);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MessageManager$MessageManagerListener.class */
    public interface MessageManagerListener {
        void messagesUpdated();

        void serverRequestStarted();

        void serverRequestFinished();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MessageManager$MessageManagerListener$Adapter.class */
        public static abstract class Adapter implements MessageManagerListener {
            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void messagesUpdated() {
            }

            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void serverRequestStarted() {
            }

            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void serverRequestFinished() {
            }
        }
    }
}

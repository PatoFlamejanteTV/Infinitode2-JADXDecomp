package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.TriggeredActionType;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TriggeredActionManager.class */
public class TriggeredActionManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2484a = TLog.forClass(TriggeredActionManager.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TriggeredActionManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<TriggeredActionManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TriggeredActionManager read() {
            return Game.i.triggeredActionManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
    }

    public void perform(GameSystemProvider gameSystemProvider, TriggeredActionType triggeredActionType, float f) {
        switch (triggeredActionType) {
            case GIVE_COINS:
                gameSystemProvider.gameState.addMoney(f, false);
                return;
            default:
                f2484a.e("action not implemented: " + triggeredActionType, new Object[0]);
                return;
        }
    }

    public String getTitleAlias(TriggeredActionType triggeredActionType) {
        return "triggered_action_" + triggeredActionType.name();
    }

    public Group generateIcon(TriggeredActionType triggeredActionType, float f, Color color) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = null;
        switch (triggeredActionType) {
            case GIVE_COINS:
                image = new Image(Game.i.assetManager.getDrawable("icon-coins"));
                break;
        }
        if (image != null) {
            image.setColor(color);
            image.setSize(f, f);
            group.addActor(image);
        }
        return group;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void postRender(float f) {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}

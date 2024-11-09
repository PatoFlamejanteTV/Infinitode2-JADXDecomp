package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/NewEnemyOverlay.class */
public class NewEnemyOverlay implements Disposable, Listener<EnemySpawn> {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3371a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 120, "NewEnemyOverlay");

    /* renamed from: b, reason: collision with root package name */
    private boolean f3372b;
    private Image c;
    private Label d;
    private Label e;
    private long f;
    private float g;
    private final GameSystemProvider h;

    static {
        new Vector2();
    }

    public NewEnemyOverlay(GameSystemProvider gameSystemProvider) {
        this.h = gameSystemProvider;
        this.f3371a.getTable().setBackground(Game.i.assetManager.getOverlayBackground());
        this.f3371a.getTable().setTouchable(Touchable.enabled);
        this.f3371a.getTable().addListener(new InputVoid());
        this.f3371a.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.NewEnemyOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Game.getTimestampMillis() - NewEnemyOverlay.this.f > 400) {
                    NewEnemyOverlay.this.hide();
                }
            }
        });
        this.c = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.f3371a.getTable().add((Table) this.c).size(96.0f).padBottom(32.0f).row();
        this.d = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.f3371a.getTable().add((Table) this.d).row();
        this.e = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.e.setWrap(true);
        this.e.setAlignment(1);
        this.f3371a.getTable().add((Table) this.e).width(1000.0f).padTop(32.0f).row();
        Label label = new Label(Game.i.localeManager.i18n.get("tap_screen_to_continue"), Game.i.assetManager.getLabelStyle(24));
        label.setColor(MaterialColor.GREY.P500);
        label.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.5f), Actions.alpha(0.5f, 0.5f))));
        this.f3371a.getTable().add((Table) label).padTop(32.0f);
        this.f3371a.getTable().addAction(Actions.alpha(0.0f));
        this.f3371a.getTable().setVisible(false);
        gameSystemProvider.events.getListeners(EnemySpawn.class).add(this).setDescription("Shows an overlay if new enemy type has spawned");
    }

    public void show(EnemyType enemyType) {
        String title = Game.i.enemyManager.getFactory(enemyType).getTitle();
        if (title.equals("-")) {
            return;
        }
        showCustom(title, Game.i.enemyManager.getFactory(enemyType).getDescription(), new TextureRegionDrawable(Game.i.enemyManager.getFactory(enemyType).getTexture()));
    }

    public void showCustom(String str, String str2, Drawable drawable) {
        if (this.f3372b) {
            hide();
        }
        this.f3372b = true;
        this.g = this.h.gameState.getNonAnimatedGameSpeed();
        this.h.gameState.setGameSpeed(0.0f);
        this.c.setDrawable(drawable);
        this.d.setText(str);
        this.e.setText(str2);
        this.f3371a.getTable().setVisible(true);
        this.f3371a.getTable().clearActions();
        this.f3371a.getTable().addAction(Actions.alpha(1.0f, 0.3f));
        this.f = Game.getTimestampMillis();
    }

    public void hide() {
        if (this.f3372b) {
            this.h.gameState.setGameSpeed(this.g);
            this.f3372b = false;
            this.f3371a.getTable().clearActions();
            this.f3371a.getTable().addAction(Actions.sequence(Actions.alpha(0.0f, 0.5f), Actions.hide()));
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3371a);
        this.h.events.getListeners(EnemySpawn.class).remove(this);
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        if (this.h.gameState.isFastForwarding()) {
            return;
        }
        Enemy enemy = enemySpawn.getEnemy();
        if (enemy.type != EnemyType.GENERIC) {
            if (Game.i.enemyManager.isEnemyTypeNewForPlayer(enemy.type, true)) {
                show(enemy.type);
            }
            Game.i.enemyManager.markEnemyTypeAsNotNewForPlayer(enemy.type);
        }
    }
}

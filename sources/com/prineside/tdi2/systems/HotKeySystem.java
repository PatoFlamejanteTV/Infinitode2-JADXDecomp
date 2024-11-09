package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/HotKeySystem.class */
public final class HotKeySystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2960a = TLog.forClass(HotKeySystem.class);

    /* renamed from: b, reason: collision with root package name */
    private boolean f2961b;
    private boolean c = false;
    private float d;
    private int e;
    private int f;

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.e = this.S.map.getMap().getWidth() / 2;
        this.f = this.S.map.getMap().getHeight() / 2;
        this.S.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
            if (selectedTile != null) {
                this.e = selectedTile.getX();
                this.f = selectedTile.getY();
            }
        }).setDescription("HotKeySystem - moves cursor to the selected tile");
        this.S.events.getListeners(Render.class).addWithPriority(render -> {
            handleHotKeys();
        }, EventListeners.PRIORITY_HIGHEST).setDescription("HotKeySystem - handles hot keys");
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.HOT_KEY_DRAW_CURSOR, false, (batch, f, f2, f3) -> {
            a(batch, f);
        }).setName("HotKey-drawCursor"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "HotKey";
    }

    public final void setHotKeysEnabled(boolean z) {
        this.f2961b = !z;
    }

    private void a(Batch batch, float f) {
        if (this.d != 0.0f) {
            batch.setColor(1.0f, 1.0f, 1.0f, this.d);
            batch.draw(AssetManager.TextureRegions.i().tileOutlineHover, (this.e << 7) - 12.0f, (this.f << 7) - 12.0f, 152.0f, 152.0f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            this.d -= f;
            if (this.d < 0.0f) {
                this.d = 0.0f;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x048d, code lost:            r5.S.miner.buildMinerActionForSelectedTile(r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x06f5, code lost:            r0 = r5.S.ability.abilitiesConfiguration.slots[r9 ? 1 : 0];     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0708, code lost:            if (r0 == null) goto L298;     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0717, code lost:            if (r5.S.ability.getUiCurrentlyUsingAbility() != r0) goto L153;     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x071a, code lost:            r5.S.ability.cancelUsingAbility();     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0733, code lost:            com.prineside.tdi2.Game.i.soundManager.playStatic(com.prineside.tdi2.enums.StaticSoundType.BUTTON);     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0727, code lost:            r5.S.ability.startUsingAbility(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x0b51, code lost:            r5.S.tower.selectTowerAbilityAction((com.prineside.tdi2.Tower) r0, r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0bf1, code lost:            r5.S.tower.selectGlobalTowerAbilityAction((com.prineside.tdi2.Tower) r0, r10);     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x040d, code lost:            r5.S.modifier.buildModifierActionAtSelectedTile(r9);     */
    /* JADX WARN: Failed to find 'out' block for switch in B:46:0x0299. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleHotKeys() {
        /*
            Method dump skipped, instructions count: 3081
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.systems.HotKeySystem.handleHotKeys():void");
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.c = true;
    }
}

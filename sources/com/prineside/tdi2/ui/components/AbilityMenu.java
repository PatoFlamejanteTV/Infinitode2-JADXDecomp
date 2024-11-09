package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.ui.actors.AbilitySlotButton;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/AbilityMenu.class */
public class AbilityMenu implements Disposable {
    private final Label c;
    private final Group d;
    private final Label e;
    private final Label f;
    private final GameSystemProvider h;
    private boolean i;
    private static final StringBuilder l = new StringBuilder();

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3255a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "AbilityMenu");

    /* renamed from: b, reason: collision with root package name */
    private final AbilitySlotButton[] f3256b = new AbilitySlotButton[6];
    private final Image[] g = new Image[10];
    private int j = -1;
    private int k = -1;
    private final Runnable m = this::update;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x03d9, code lost:            r9.f3256b[r12].addActor(new com.prineside.tdi2.ui.actors.HotKeyHintLabel(com.prineside.tdi2.Game.i.settingsManager.getHotKey(r13), 12.0f, 91.0f));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AbilityMenu(final com.prineside.tdi2.GameSystemProvider r10) {
        /*
            Method dump skipped, instructions count: 1072
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.AbilityMenu.<init>(com.prineside.tdi2.GameSystemProvider):void");
    }

    private void a() {
        int energy = this.h.ability.getEnergy();
        if (this.j != energy) {
            l.setLength(0);
            l.append('x').append(energy);
            this.e.setText(l);
            this.j = energy;
            int maxEnergy = this.h.ability.getMaxEnergy();
            for (int i = 0; i < this.g.length; i++) {
                if (i + 1 <= energy) {
                    this.g[i].setVisible(true);
                    this.g[i].setColor(Color.WHITE);
                } else if (i + 1 > maxEnergy) {
                    this.g[i].setColor(0.0f, 0.0f, 0.0f, 0.28f);
                    this.g[i].setVisible(true);
                } else {
                    this.g[i].setVisible(false);
                }
            }
        }
        if (energy < this.h.ability.getMaxEnergy()) {
            int ceil = MathUtils.ceil(this.h.ability.getEnergyRegenerationTime() - this.h.ability.getNextEnergyGenerationTime());
            if (this.k != ceil) {
                l.setLength(0);
                l.append(ceil).append('s');
                this.f.setText(l);
                this.f.setVisible(true);
                this.k = ceil;
            }
        } else {
            this.f.setVisible(false);
        }
        float nextEnergyGenerationTime = energy + (this.h.ability.getNextEnergyGenerationTime() / this.h.ability.getEnergyRegenerationTime());
        for (AbilitySlotButton abilitySlotButton : this.f3256b) {
            if (abilitySlotButton.getGameEnergy() != nextEnergyGenerationTime) {
                abilitySlotButton.setGameEnergy(nextEnergyGenerationTime);
            }
        }
    }

    public void update() {
        int slot;
        if (this.h.ability.abilitiesConfiguration == null) {
            return;
        }
        boolean z = false;
        for (int i = 0; i < this.f3256b.length; i++) {
            AbilitySlotButton abilitySlotButton = this.f3256b[i];
            AbilityType abilityType = this.h.ability.abilitiesConfiguration.slots[i];
            int availableAbilities = this.h.ability.getAvailableAbilities(i);
            int energyCost = abilityType == null ? 0 : this.h.ability.getEnergyCost(abilityType);
            if (abilityType != null) {
                z = true;
            }
            if (abilitySlotButton.getAbility() != abilityType) {
                abilitySlotButton.setAbility(abilityType);
            }
            if (abilitySlotButton.getCount() != availableAbilities) {
                abilitySlotButton.setCount(availableAbilities);
            }
            if (abilityType == null || availableAbilities <= 0) {
                abilitySlotButton.setTouchable(Touchable.disabled);
            } else {
                abilitySlotButton.setTouchable(Touchable.enabled);
            }
            if (abilitySlotButton.getEnergyCost() != energyCost) {
                abilitySlotButton.update();
            }
        }
        this.d.setVisible(z);
        for (AbilitySlotButton abilitySlotButton2 : this.f3256b) {
            abilitySlotButton2.setSelected(false);
        }
        boolean z2 = false;
        if (this.h.ability.getUiCurrentlyUsingAbility() != null && (slot = this.h.ability.abilitiesConfiguration.getSlot(this.h.ability.getUiCurrentlyUsingAbility())) != -1) {
            this.f3256b[slot].setSelected(true);
            z2 = true;
        }
        if (z2) {
            if (Game.i.abilityManager.getFactory(this.h.ability.getUiCurrentlyUsingAbility()).requiresMapPointing()) {
                this.c.setText(Game.i.localeManager.i18n.get("ability_menu_select_point_to_apply"));
            } else {
                this.c.setText(Game.i.localeManager.i18n.get("ability_menu_tap_map_to_apply"));
            }
            this.c.clearActions();
            this.c.setVisible(true);
            this.c.addAction(Actions.alpha(1.0f, 0.3f));
        } else {
            this.c.clearActions();
            this.c.addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.hide()));
        }
        a();
        this.i = true;
    }

    public void draw(float f) {
        if (!this.i) {
            update();
        }
        a();
    }

    public void finalFadeOut() {
        this.f3255a.getTable().setTouchable(Touchable.disabled);
        this.f3255a.getTable().clearActions();
        this.f3255a.getTable().addAction(Actions.alpha(0.0f, 1.0f));
    }

    public void setVisible(boolean z) {
        this.f3255a.getTable().setVisible(z);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3255a);
    }
}

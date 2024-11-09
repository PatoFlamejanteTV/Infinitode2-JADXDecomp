package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/GameplayBonusSummary.class */
public class GameplayBonusSummary extends Group {
    public GameSystemProvider S;
    public Image iconShadow;
    public Image iconGlow;
    public Image icon;
    public Image bar;
    public ParticlesCanvas barParticles;
    public Label objectiveLabel;
    public Label stageNumberLabel;

    public GameplayBonusSummary(final GameSystemProvider gameSystemProvider) {
        this.S = gameSystemProvider;
        setTransform(false);
        setSize(290.0f, 64.0f);
        Image image = new Image(Game.i.assetManager.getQuad("ui.gameplayBonusSummary.progressBarBg"));
        image.setPosition(55.0f, 5.0f);
        image.setSize(240.0f, 16.0f);
        addActor(image);
        this.bar = new Image(Game.i.assetManager.getQuad("ui.gameplayBonusSummary.progressBar"));
        this.bar.setPosition(55.0f, 5.0f);
        this.bar.setSize(240.0f, 16.0f);
        addActor(this.bar);
        this.barParticles = new ParticlesCanvas();
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particles/gameplay-bonus-bar-ready.prt"), Game.i.assetManager.getTextureRegion("particle-default").getAtlas());
        particleEffect.setEmittersCleanUpBlendFunction(false);
        this.barParticles.addParticle(particleEffect, 0.0f, 0.0f);
        this.barParticles.setPosition(55.0f, 5.0f);
        this.barParticles.setSize(240.0f, 16.0f);
        addActor(this.barParticles);
        this.iconShadow = new Image(Game.i.assetManager.getDrawable("icon-turbo"));
        this.iconShadow.setSize(64.0f, 64.0f);
        this.iconShadow.setPosition(3.0f, -3.0f);
        this.iconShadow.setColor(Config.BACKGROUND_COLOR.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
        addActor(this.iconShadow);
        this.iconGlow = new Image(Game.i.assetManager.getDrawable("icon-turbo"));
        this.iconGlow.setSize(64.0f, 64.0f);
        this.iconGlow.setVisible(false);
        this.iconGlow.setOrigin(32.0f, 32.0f);
        addActor(this.iconGlow);
        this.iconGlow.clearActions();
        this.iconGlow.addAction(Actions.forever(Actions.parallel(Actions.sequence(Actions.alpha(0.78f), Actions.alpha(0.0f, 1.0f)), Actions.sequence(Actions.scaleTo(1.0f, 1.0f), Actions.scaleTo(1.3f, 1.3f, 1.0f)))));
        this.icon = new Image(Game.i.assetManager.getDrawable("icon-turbo"));
        this.icon.setSize(64.0f, 64.0f);
        this.icon.setOrigin(32.0f, 32.0f);
        addActor(this.icon);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-skull"));
        image2.setSize(24.0f, 24.0f);
        image2.setPosition(70.0f, 26.0f);
        addActor(image2);
        this.objectiveLabel = new Label("120 / 150", Game.i.assetManager.getLabelStyle(21));
        this.objectiveLabel.setSize(100.0f, 24.0f);
        this.objectiveLabel.setPosition(100.0f, 26.0f);
        addActor(this.objectiveLabel);
        this.stageNumberLabel = new Label("1 / 1", Game.i.assetManager.getLabelStyle(18));
        this.stageNumberLabel.setSize(295.0f, 24.0f);
        this.stageNumberLabel.setPosition(0.0f, 26.0f);
        this.stageNumberLabel.setColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        this.stageNumberLabel.setAlignment(16);
        addActor(this.stageNumberLabel);
        addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.actors.GameplayBonusSummary.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                gameSystemProvider._gameUi.gameplayBonusesOverlay.show();
            }
        });
        update();
    }

    public void update() {
        if (this.S.bonus.isEnabled()) {
            int currentVisualProgressPoints = this.S.bonus.getCurrentVisualProgressPoints();
            if (currentVisualProgressPoints < this.S.bonus.getNextStagePointsRequirement()) {
                this.objectiveLabel.setText(currentVisualProgressPoints + " / " + this.S.bonus.getNextStagePointsRequirement());
            } else if (this.S.bonus.bonusSelectionAvailable()) {
                this.objectiveLabel.setText("Boost available!");
            } else {
                this.objectiveLabel.setText("MAX");
            }
            if (this.S.bonus.getMaxBonusStages() > 0) {
                this.stageNumberLabel.setText(this.S.bonus.getCurrentVisualProgressStageNumber() + " / " + this.S.bonus.getMaxBonusStages());
            } else {
                this.stageNumberLabel.setTextFromInt(this.S.bonus.getCurrentVisualProgressStageNumber());
            }
            float nextStagePointsRequirement = currentVisualProgressPoints / this.S.bonus.getNextStagePointsRequirement();
            if (currentVisualProgressPoints > 0) {
                this.bar.setWidth(nextStagePointsRequirement * 240.0f);
                this.bar.setVisible(true);
            } else {
                this.bar.setVisible(false);
            }
            if (this.S.bonus.bonusSelectionAvailable()) {
                this.icon.setColor(Color.WHITE);
                this.iconGlow.setVisible(true);
                this.barParticles.setVisible(true);
            } else {
                this.icon.setColor(MaterialColor.GREY.P500);
                this.iconGlow.setVisible(false);
                this.barParticles.setVisible(false);
            }
        }
    }
}

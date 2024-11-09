package com.prineside.tdi2.modifiers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import java.util.Locale;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/ExperienceModifier.class */
public final class ExperienceModifier extends Modifier {

    /* renamed from: a, reason: collision with root package name */
    private float f2595a;
    public boolean[] minerActive;

    /* renamed from: b, reason: collision with root package name */
    private OnMinerResourceChange f2596b;
    private static final StringBuilder c = new StringBuilder();

    /* synthetic */ ExperienceModifier(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f2595a);
        kryo.writeObject(output, this.minerActive);
        kryo.writeObjectOrNull(output, this.f2596b, OnMinerResourceChange.class);
    }

    @Override // com.prineside.tdi2.Modifier, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2595a = input.readFloat();
        this.minerActive = (boolean[]) kryo.readObject(input, boolean[].class);
        this.f2596b = (OnMinerResourceChange) kryo.readObjectOrNull(input, OnMinerResourceChange.class);
    }

    private ExperienceModifier() {
        super(ModifierType.EXPERIENCE);
        this.minerActive = new boolean[8];
        this.f2596b = new OnMinerResourceChange(this, (byte) 0);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.S.events.getListeners(MinerResourceChange.class).addStateAffecting(this.f2596b).setDescription("Notifies nearby modifiers about the mined resource");
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.S.events.getListeners(MinerResourceChange.class).remove(this.f2596b);
        super.setUnregistered();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        if (this.minerActive[i]) {
            Array<Tower> towerArray = this.S.TSH.getTowerArray();
            this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                if (tile instanceof PlatformTile) {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building instanceof Tower) {
                        towerArray.add((Tower) platformTile.building);
                        return true;
                    }
                    return true;
                }
                return true;
            });
            if (towerArray.size != 0 && i2 > 0) {
                float intValue = (this.S.gameValue.getIntValue(GameValueType.MODIFIER_EXPERIENCE_VALUE) / towerArray.size) * i2;
                for (int i3 = 0; i3 < towerArray.size; i3++) {
                    Tower tower = towerArray.items[i3];
                    if (tower.isRegistered()) {
                        this.S.experience.addExperienceRaw(tower, intValue);
                        this.S.statistics.addStatistic(StatisticsType.XPG_EM, intValue);
                        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate()) {
                            this.S._particle.addXpOrbParticle(intValue, getTile().getX(), getTile().getY(), tower.getTile().getX(), tower.getTile().getY());
                        }
                    }
                }
            }
            this.S.TSH.freeTowerArray(towerArray);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Modifier
    public final void fillModifierMenu(Group group, ObjectMap<String, Object> objectMap) {
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Array array = new Array(true, 1, Tower.class);
        Array array2 = new Array(true, 1, Miner.class);
        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
            if (tile instanceof PlatformTile) {
                PlatformTile platformTile = (PlatformTile) tile;
                if (platformTile.building instanceof Tower) {
                    array.add((Tower) platformTile.building);
                    return true;
                }
                return true;
            }
            if (tile instanceof SourceTile) {
                SourceTile sourceTile = (SourceTile) tile;
                if (sourceTile.miner != null) {
                    array2.add(sourceTile.miner);
                    return true;
                }
                return true;
            }
            return true;
        });
        int i = 1;
        for (int i2 = 0; i2 < array2.size; i2++) {
            i = (i * 31) + ((Miner[]) array2.items)[i2].id;
        }
        for (int i3 = 0; i3 < array.size; i3++) {
            i = (i * 31) + ((Tower[]) array.items)[i3].id;
        }
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(Integer.valueOf(i))) {
            group.clear();
            Group group2 = new Group();
            group2.setTransform(false);
            group2.setSize(288.0f, 288.0f);
            group2.setPosition(156.0f, scaledViewportHeight + 150);
            group.addActor(group2);
            Group group3 = new Group();
            group3.setTransform(false);
            group3.setPosition(96.0f, 96.0f);
            group3.setSize(96.0f, 96.0f);
            group2.addActor(group3);
            Image image = new Image(Game.i.assetManager.getDrawable("tile-type-platform"));
            image.setSize(96.0f, 96.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group3.addActor(image);
            for (Modifier.ConnectionSide connectionSide : Modifier.ConnectionSide.values) {
                if (this.visuallyConnectedSides[connectionSide.ordinal()]) {
                    float[] fArr = WIRES_TEXTURES_CONFIG[connectionSide.ordinal()];
                    Image image2 = new Image(new TextureRegionDrawable(Game.i.modifierManager.getFactory(this.type).wires[connectionSide.ordinal()]));
                    image2.setPosition(48.0f + (fArr[0] * 0.75f), 48.0f + (fArr[1] * 0.75f));
                    image2.setSize(fArr[2] * 0.75f, fArr[3] * 0.75f);
                    group3.addActor(image2);
                }
            }
            Image image3 = new Image(Game.i.assetManager.getDrawable("modifier-base-experience"));
            image3.setSize(96.0f, 96.0f);
            group3.addActor(image3);
            Label label = new Label("", Game.i.assetManager.getLabelStyle(24));
            label.setPosition(0.0f, 32.0f);
            label.setSize(96.0f, 32.0f);
            label.setAlignment(1);
            group3.addActor(label);
            Image[] imageArr = new Image[8];
            Label[] labelArr = new Label[8];
            int x = getTile().getX();
            int y = getTile().getY();
            for (int i4 = 0; i4 < array2.size; i4++) {
                Miner miner = ((Miner[]) array2.items)[i4];
                int x2 = x - miner.getTile().getX();
                int y2 = y - miner.getTile().getY();
                Group group4 = new Group();
                group4.setTransform(false);
                group4.setSize(96.0f, 96.0f);
                group4.setPosition(96.0f - (x2 * 96.0f), 96.0f - (y2 * 96.0f));
                group2.addActor(group4);
                Image image4 = new Image(Game.i.assetManager.getDrawable("tile-type-platform"));
                image4.setSize(96.0f, 96.0f);
                image4.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                group4.addActor(image4);
                Image image5 = new Image(Game.i.assetManager.getDrawable("icon-miner-top"));
                image5.setSize(48.0f, 48.0f);
                image5.setPosition(24.0f, 34.0f);
                group4.addActor(image5);
                imageArr[i4] = image5;
                Label label2 = new Label("", Game.i.assetManager.getLabelStyle(21));
                label2.setPosition(0.0f, 12.0f);
                label2.setSize(96.0f, 18.0f);
                label2.setAlignment(1);
                group4.addActor(label2);
                labelArr[i4] = label2;
            }
            for (int i5 = 0; i5 < array.size; i5++) {
                Tower tower = ((Tower[]) array.items)[i5];
                int x3 = x - tower.getTile().getX();
                int y3 = y - tower.getTile().getY();
                Group group5 = new Group();
                group5.setTransform(false);
                group5.setSize(96.0f, 96.0f);
                group5.setPosition(96.0f - (x3 * 96.0f), 96.0f - (y3 * 96.0f));
                group2.addActor(group5);
                Image image6 = new Image(Game.i.assetManager.getDrawable("tile-type-platform"));
                image6.setSize(96.0f, 96.0f);
                image6.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                group5.addActor(image6);
                Image image7 = new Image(Game.i.assetManager.getDrawable("icon-tower-top"));
                image7.setSize(48.0f, 48.0f);
                image7.setPosition(24.0f, 24.0f);
                image7.setColor(MaterialColor.LIGHT_BLUE.P500);
                group5.addActor(image7);
            }
            objectMap.put("state", Integer.valueOf(i));
            objectMap.put("minerIcons", imageArr);
            objectMap.put("minerLabels", labelArr);
            objectMap.put("modifierCooldown", label);
        }
        Image[] imageArr2 = (Image[]) objectMap.get("minerIcons");
        Label[] labelArr2 = (Label[]) objectMap.get("minerLabels");
        Label label3 = (Label) objectMap.get("modifierCooldown");
        int i6 = array.size;
        for (int i7 = 0; i7 < array2.size; i7++) {
            Miner miner2 = ((Miner[]) array2.items)[i7];
            if (this.minerActive[i7]) {
                imageArr2[i7].setColor(MaterialColor.LIGHT_GREEN.P500);
            } else {
                imageArr2[i7].setColor(MaterialColor.ORANGE.P500);
            }
            c.setLength(0);
            int i8 = 0;
            for (ResourceType resourceType : ResourceType.values) {
                i8 += miner2.minedResources[resourceType.ordinal()];
            }
            if (i8 >= i6) {
                c.append("[#8BC34A]");
            } else {
                c.append("[#FF9800]");
            }
            c.append(StringFormatter.compactNumber(i8, false));
            c.append("[] / [#808080]").append(i6).append("[]");
            labelArr2[i7].setText(c);
        }
        label3.setText(StringFormatter.compactNumberWithPrecision(this.f2595a, 1));
    }

    @Override // com.prineside.tdi2.Modifier
    public final void update(float f) {
        super.update(f);
        if (this.S.gameState.isGameRealTimePasses()) {
            this.f2595a -= f;
            if (this.f2595a <= 0.0f) {
                this.f2595a += 10.0f;
                Array<Tower> towerArray = this.S.TSH.getTowerArray();
                Array<Miner> minerArray = this.S.TSH.getMinerArray();
                this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                    if (tile instanceof PlatformTile) {
                        PlatformTile platformTile = (PlatformTile) tile;
                        if (platformTile.building instanceof Tower) {
                            towerArray.add((Tower) platformTile.building);
                            return true;
                        }
                        return true;
                    }
                    if (tile instanceof SourceTile) {
                        SourceTile sourceTile = (SourceTile) tile;
                        if (sourceTile.miner != null) {
                            minerArray.add(sourceTile.miner);
                            return true;
                        }
                        return true;
                    }
                    return true;
                });
                int i = towerArray.size;
                if (i > 0) {
                    for (int i2 = 0; i2 < minerArray.size; i2++) {
                        Miner miner = minerArray.items[i2];
                        int i3 = 0;
                        for (ResourceType resourceType : ResourceType.values) {
                            i3 += miner.minedResources[resourceType.ordinal()];
                        }
                        if (i3 < i) {
                            this.minerActive[i2] = false;
                        } else {
                            this.minerActive[i2] = true;
                            int i4 = i;
                            for (ResourceType resourceType2 : ResourceType.values) {
                                int i5 = miner.minedResources[resourceType2.ordinal()];
                                if (i5 > 0) {
                                    int min = StrictMath.min(i5, i4);
                                    this.S.miner.removeResources(miner, resourceType2, min);
                                    if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                                        for (int i6 = 0; i6 < min; i6++) {
                                            this.S._particle.addOrbParticle(Game.i.modifierManager.F.EXPERIENCE.d[resourceType2.ordinal()], 18.0f, miner.getTile().getX(), miner.getTile().getY(), getTile().getX(), getTile().getY());
                                        }
                                    }
                                    int i7 = i4 - min;
                                    i4 = i7;
                                    if (i7 != 0) {
                                    }
                                }
                            }
                        }
                    }
                }
                this.S.TSH.freeTowerArray(towerArray);
                this.S.TSH.freeMinerArray(minerArray);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/ExperienceModifier$OnMinerResourceChange.class */
    public static class OnMinerResourceChange implements KryoSerializable, Listener<MinerResourceChange> {

        /* renamed from: a, reason: collision with root package name */
        private ExperienceModifier f2597a;

        /* synthetic */ OnMinerResourceChange(ExperienceModifier experienceModifier, byte b2) {
            this(experienceModifier);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.f2597a, ExperienceModifier.class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f2597a = (ExperienceModifier) kryo.readObjectOrNull(input, ExperienceModifier.class);
        }

        @Deprecated
        private OnMinerResourceChange() {
        }

        private OnMinerResourceChange(ExperienceModifier experienceModifier) {
            this.f2597a = experienceModifier;
        }

        @Override // com.prineside.tdi2.events.Listener
        public void handleEvent(MinerResourceChange minerResourceChange) {
            if (minerResourceChange.isMined()) {
                Miner miner = minerResourceChange.getMiner();
                minerResourceChange.getResourceType();
                int delta = minerResourceChange.getDelta();
                Array<Miner> minerArray = this.f2597a.S.TSH.getMinerArray();
                this.f2597a.S.map.traverseNeighborTilesAroundTile(this.f2597a.getTile(), tile -> {
                    if (tile instanceof SourceTile) {
                        SourceTile sourceTile = (SourceTile) tile;
                        if (sourceTile.miner != null) {
                            minerArray.add(sourceTile.miner);
                            return true;
                        }
                        return true;
                    }
                    return true;
                });
                for (int i = 0; i < minerArray.size; i++) {
                    if (minerArray.items[i] == miner) {
                        this.f2597a.a(i, delta);
                        return;
                    }
                }
                this.f2597a.S.TSH.freeMinerArray(minerArray);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/modifiers/ExperienceModifier$ExperienceModifierFactory.class */
    public static class ExperienceModifierFactory extends Modifier.Factory<ExperienceModifier> {
        private TextureRegion c;
        private TextureRegion[] d;

        public ExperienceModifierFactory() {
            super(ModifierType.EXPERIENCE, MaterialColor.CYAN.P500, "icon-experience-generation-lite");
            this.d = new TextureRegion[ResourceType.values.length];
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public CharSequence getDescription(GameValueProvider gameValueProvider) {
            return Game.i.localeManager.i18n.format("modifier_description_EXPERIENCE", Integer.valueOf(gameValueProvider.getIntValue(GameValueType.MODIFIER_EXPERIENCE_VALUE)), 10);
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public int getBuildPrice(GameSystemProvider gameSystemProvider, int i) {
            return a((int) (100.0f * ((float) StrictMath.pow(1.5d, i))));
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("modifier-base-experience");
            Game.i.assetManager.getTextureRegion("xp-orb");
            for (ResourceType resourceType : ResourceType.values) {
                this.d[resourceType.ordinal()] = Game.i.assetManager.getTextureRegion("resource-orb-" + resourceType.name().toLowerCase(Locale.ENGLISH));
            }
        }

        @Override // com.prineside.tdi2.Modifier.Factory
        public TextureRegion getBaseTexture() {
            return this.c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Modifier.Factory
        public ExperienceModifier create() {
            return new ExperienceModifier((byte) 0);
        }
    }
}

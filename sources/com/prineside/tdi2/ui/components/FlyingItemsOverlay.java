package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.IssuedItemsAdd;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.PooledCustomEffect;
import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/FlyingItemsOverlay.class */
public class FlyingItemsOverlay implements Disposable {
    private Group c;
    private Group d;
    private final GameSystemProvider f;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3286a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 90, "FlyingItemsOverlay papers", true);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3287b = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 91, "FlyingItemsOverlay elements", true);
    private int e = 0;
    private final Pool<Paper> g = new Pool<Paper>(this, 1, 512) { // from class: com.prineside.tdi2.ui.components.FlyingItemsOverlay.1
        {
            super(1, 512);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.badlogic.gdx.utils.Pool
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Paper newObject() {
            Paper paper = new Paper();
            paper.pool = this;
            paper.start();
            return paper;
        }
    };
    private final Listener<ScreenResize> h = screenResize -> {
        this.c.setSize(Game.i.uiManager.viewport.getWorldWidth(), Game.i.uiManager.viewport.getWorldHeight());
        this.d.setSize(Game.i.uiManager.viewport.getWorldWidth(), Game.i.uiManager.viewport.getWorldHeight());
    };

    static {
        new Vector2();
    }

    public FlyingItemsOverlay(GameSystemProvider gameSystemProvider) {
        this.f = gameSystemProvider;
        this.f3286a.getTable().setTouchable(Touchable.disabled);
        this.f3287b.getTable().setTouchable(Touchable.disabled);
        this.d = new Group();
        this.d.setTransform(false);
        this.d.setTouchable(Touchable.disabled);
        this.f3286a.getTable().add((Table) this.d).expand().fill();
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setTouchable(Touchable.disabled);
        this.f3287b.getTable().add((Table) this.c).expand().fill();
        Game.EVENTS.getListeners(ScreenResize.class).add(this.h);
        gameSystemProvider.events.getListeners(IssuedItemsAdd.class).add(this::a).setDescription("FlyingItemsOverlay - draws issued items and plays a sound");
    }

    private void a(IssuedItemsAdd issuedItemsAdd) {
        if (this.f.gameState.isFastForwarding() || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED) == 0.0d || issuedItemsAdd.getFlyAlign() < 0) {
            return;
        }
        ItemStack itemStack = issuedItemsAdd.getItemStack();
        float stageX = issuedItemsAdd.getStageX();
        float stageY = issuedItemsAdd.getStageY();
        int flyAlign = issuedItemsAdd.getFlyAlign();
        if (itemStack.getItem().getType() == ItemType.GREEN_PAPER) {
            addPapers(stageX, stageY, itemStack.getCount());
            return;
        }
        ItemCell itemCell = new ItemCell();
        itemCell.setSize(96.0f, 105.0f);
        itemCell.setItemStack(itemStack);
        int i = this.e;
        this.e = i + 1;
        itemCell.setColRow(i, 0);
        itemCell.setCovered(itemStack.isCovered());
        itemCell.shine(true, true);
        this.f._sound.playStaticParameterized(Game.i.soundManager.getRarity(itemStack.getItem().getRarity()), 0.75f, 1.0f, 0.0f);
        add(stageX, stageY, itemCell, itemStack.getCount(), flyAlign, 0.8f, 1.0f + (itemStack.getItem().getRarity().ordinal() * itemStack.getItem().getRarity().ordinal() * 0.05f));
    }

    public void addPapers(float f, float f2, int i) {
        int i2;
        int i3 = 0;
        IntArray intArray = new IntArray();
        while (i != 0) {
            if (i >= 10000) {
                i2 = CGL.kCGLBadAttribute;
            } else if (i >= 5000) {
                i2 = 5000;
            } else if (i >= 2000) {
                i2 = 2000;
            } else if (i >= 1000) {
                i2 = 1000;
            } else if (i >= 500) {
                i2 = 500;
            } else if (i >= 200) {
                i2 = 200;
            } else if (i >= 100) {
                i2 = 100;
            } else if (i >= 20) {
                i2 = 20;
            } else if (i >= 5) {
                i2 = 5;
            } else {
                i2 = 1;
            }
            int i4 = i2;
            i -= i4;
            intArray.add(i4);
        }
        intArray.reverse();
        for (int i5 = 0; i5 < intArray.size; i5++) {
            int i6 = intArray.items[i5];
            Paper obtain = this.g.obtain();
            obtain.setup(i6);
            this.f._gameUi.mainUi.particlesCanvas.addParticle(obtain, f - Game.i.uiManager.getScreenSafeMargin(), f2 + i3);
            i3 += 3;
            if (i5 == 50) {
                return;
            }
        }
    }

    public void add(float f, float f2, Actor actor, int i, int i2, float f3, float f4) {
        if (f > 0.0f && f < this.c.getWidth()) {
            actor.setPosition(f - (actor.getWidth() * 0.5f), f2 - (actor.getHeight() * 0.5f));
            actor.setOrigin(actor.getWidth() * 0.5f, actor.getHeight() * 0.5f);
            this.c.addActor(actor);
            if (actor instanceof Group) {
                ((Group) actor).setTransform(true);
            }
            float f5 = 0.0f;
            float f6 = 0.0f;
            if (Align.isLeft(i2)) {
                f5 = -1.0f;
            } else if (Align.isRight(i2)) {
                f5 = 1.0f;
            }
            if (Align.isBottom(i2)) {
                f6 = -1.0f;
            } else if (Align.isTop(i2)) {
                f6 = 1.0f;
            }
            actor.addAction(Actions.sequence(Actions.scaleTo(0.5f * f3, 0.5f * f3), Actions.parallel(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.1f), Actions.scaleBy(f3 * 0.5f, 0.0f, 0.3f, Interpolation.swingOut)), Actions.scaleBy(0.0f, f3 * 0.5f, 0.3f, Interpolation.swingOut)), Actions.delay(f4 * 1.0f), Actions.parallel(Actions.alpha(0.0f, 0.5f, Interpolation.exp5In), Actions.moveBy(0.0f, 384.0f, 0.6f, Interpolation.exp5In), Actions.sequence(Actions.delay(0.1f), Actions.parallel(Actions.scaleBy(-f3, 0.0f, 0.5f, Interpolation.exp5Out), Actions.sequence(Actions.delay(0.1f), Actions.scaleBy(0.0f, f3, 0.5f, Interpolation.exp5Out))))), Actions.removeActor()), Actions.moveBy(128.0f * f5, 128.0f * f6, 1.5f, Interpolation.circleOut))));
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.EVENTS.getListeners(ScreenResize.class).remove(this.h);
        Game.i.uiManager.removeLayer(this.f3287b);
        Game.i.uiManager.removeLayer(this.f3286a);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/FlyingItemsOverlay$Paper.class */
    public static class Paper extends ParticleEffect implements PooledCustomEffect {
        public Pool<Paper> pool;
        private float c;
        private float d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float l;
        private float m;

        /* renamed from: a, reason: collision with root package name */
        private Vector2 f3288a = new Vector2();

        /* renamed from: b, reason: collision with root package name */
        private Vector2 f3289b = new Vector2();
        private int i = -1;
        private TextureRegion j = AssetManager.TextureRegions.i().flyingPaper1_1;
        private TextureRegion[] k = new TextureRegion[4];

        public void setup(int i) {
            this.l = 2.0f + (FastRandom.getFloat() * 0.8f);
            AssetManager.TextureRegions i2 = AssetManager.TextureRegions.i();
            if (i != this.i) {
                switch (i) {
                    case 5:
                        this.k[0] = i2.flyingPaper5_1;
                        this.l *= 0.6f;
                        break;
                    case 20:
                        this.k[0] = i2.flyingPaper20_1;
                        this.l *= 0.85f;
                        break;
                    case 100:
                        this.k[0] = i2.flyingPaper100_1;
                        break;
                    case 200:
                        this.k[0] = i2.flyingPaper200_1;
                        this.l *= 1.1f;
                        break;
                    case 500:
                        this.k[0] = i2.flyingPaper500_1;
                        this.l *= 1.2f;
                        break;
                    case 1000:
                        this.k[0] = i2.flyingPaper1000_1;
                        this.l *= 1.3f;
                        break;
                    case 5000:
                        this.k[0] = i2.flyingPaper5000_1;
                        this.l *= 1.5f;
                        break;
                    case CGL.kCGLBadAttribute /* 10000 */:
                        this.k[0] = i2.flyingPaper10000_1;
                        this.l *= 1.6f;
                        break;
                    default:
                        this.k[0] = i2.flyingPaper1_1;
                        this.l *= 0.5f;
                        break;
                }
                this.k[1] = i2.flyingPaper1_2;
                this.k[2] = i2.flyingPaper1_3;
                this.k[3] = i2.flyingPaper1_4;
                this.i = i;
            }
            this.m = 0.0f;
            this.d = (FastRandom.getFloat() * 2.0f) - 1.0f;
            this.e = 110.0f + (FastRandom.getFloat() * 150.0f);
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public void start() {
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public void reset() {
            this.f3288a.setZero();
            this.f3289b.setZero();
            this.c = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
            this.g = 0.0f;
            this.h = 0.0f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public void update(float f) {
            float f2 = this.m * 2.0f;
            float f3 = this.m * 3.0f;
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            if (f3 > 1.0f) {
                f3 = 1.0f;
            }
            this.f = Interpolation.swingOut.apply(f2);
            this.g = Interpolation.swingOut.apply(f3);
            this.e -= 180.0f * f;
            if (this.e < -180.0f) {
                this.e = -180.0f;
            }
            float sin = (float) Math.sin((this.l * 2.0f) + (this.d * 3.1415927f));
            float f4 = 1.0f + this.m;
            float f5 = f4;
            if (f4 > 1.5f) {
                f5 = 1.5f;
            }
            float f6 = f5 + (this.d * 0.1f);
            this.c = sin * 60.0f * f6;
            this.f3289b.x += this.c * f;
            this.f3289b.x += this.d * f * 50.0f;
            this.f3289b.y += this.e * f;
            this.h = sin * 12.0f * f6;
            if (this.l > 0.15f) {
                this.j = this.k[0];
            } else if (this.l > 0.1f) {
                this.j = this.k[1];
            } else if (this.l > 0.05f) {
                this.j = this.k[2];
            } else {
                this.j = this.k[3];
            }
            this.l -= f;
            this.m += f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        @IgnoreMethodOverloadLuaDefWarning
        public void draw(Batch batch) {
            batch.draw(this.j, (this.f3288a.x - 32.0f) + this.f3289b.x, (this.f3288a.y - 16.0f) + this.f3289b.y, 32.0f, 16.0f, 64.0f, 32.0f, this.f, this.g, this.h);
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        @IgnoreMethodOverloadLuaDefWarning
        public void draw(Batch batch, float f) {
            update(f);
            draw(batch);
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public void allowCompletion() {
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public boolean isComplete() {
            return this.l <= 0.0f;
        }

        @Override // com.badlogic.gdx.graphics.g2d.ParticleEffect
        public void setPosition(float f, float f2) {
            this.f3288a.set(f, f2);
        }

        @Override // com.prineside.tdi2.utils.PooledCustomEffect
        public void free() {
            this.pool.free(this);
        }
    }
}

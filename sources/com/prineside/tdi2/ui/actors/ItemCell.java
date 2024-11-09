package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.utils.StringFormatter;
import java.lang.ref.WeakReference;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ItemCell.class */
public class ItemCell extends Group {
    public static final float WIDTH = 128.0f;
    public static final float HEIGHT = 140.0f;
    public static final float COMPACT_SIZE_COEFF = 0.75f;
    private AttentionRaysUnderlay k;
    private ParticlesCanvas l;
    private Image m;
    private Group n;
    private Image o;
    private Group p;
    private Image q;
    private Image r;
    private Label s;
    private Image t;
    private Label u;
    private Label v;
    private Item y;
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    public Group overlay;
    private long M;
    private WeakReference<GcListener> N;
    private static float[] O = new float[3];
    private static Color P = new Color();
    private boolean w = false;
    private boolean x = false;
    private int z = 0;
    private String J = null;
    private Actor K = null;
    private float L = 1.0f;

    static /* synthetic */ boolean a(ItemCell itemCell, boolean z) {
        itemCell.x = true;
        return true;
    }

    public ItemCell() {
        setTransform(false);
        setSize(128.0f, 140.0f);
        this.n = new Group();
        this.n.setTransform(false);
        this.n.setSize(128.0f, 140.0f);
        this.n.setOrigin(64.0f, 70.0f);
        addActor(this.n);
        this.overlay = new Group();
        this.overlay.setTransform(false);
        addActor(this.overlay);
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        if (this.x) {
            f();
        }
    }

    private void d() {
        if (this.k != null) {
            this.k.setZIndex(0);
        }
        if (this.l != null) {
            this.l.setZIndex(1);
        }
        if (this.m != null) {
            this.m.setZIndex(2);
        }
        if (this.n != null) {
            this.n.setZIndex(3);
        }
        if (this.o != null) {
            this.o.setZIndex(4);
        }
        if (this.p != null) {
            this.p.setZIndex(5);
        }
        if (this.q != null) {
            this.q.setZIndex(6);
        }
        if (this.s != null) {
            this.s.setZIndex(7);
        }
        if (this.t != null) {
            this.t.setZIndex(8);
        }
        if (this.u != null) {
            this.u.setZIndex(9);
        }
        if (this.v != null) {
            this.v.setZIndex(10);
        }
        if (this.r != null) {
            this.r.setZIndex(11);
        }
        this.overlay.setZIndex(50);
    }

    public void markStarred(boolean z) {
        this.I = z;
        if (this.w) {
            if (!z || this.y == null) {
                if (this.r != null) {
                    this.r.setVisible(false);
                    return;
                }
                return;
            }
            if (this.r == null) {
                this.r = new Image();
                this.n.addActor(this.r);
                d();
            }
            float f = this.D ? 0.75f : 1.0f;
            this.r.setSize(128.0f * f, 140.0f * f);
            if ((this.A + this.B) % 2 == 0) {
                this.r.setDrawable(Game.i.assetManager.getQuad("ui.itemCellStarA"));
            } else {
                this.r.setDrawable(Game.i.assetManager.getQuad("ui.itemCellStarB"));
            }
            this.r.setVisible(true);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (!this.w) {
            e();
        }
        this.M = Game.getTimestampMillis();
        super.draw(batch, f);
        if ((this.N == null ? null : this.N.get()) == null) {
            this.N = new WeakReference<>(new GcListener(this, (byte) 0));
        }
    }

    private void e() {
        this.w = true;
        if (this.y != null) {
            Item item = this.y;
            this.y = null;
            setItem(item, this.z);
        } else {
            setIconAndCount(this.K, this.L, this.z);
        }
        if (this.J != null) {
            setCornerText(this.J);
        }
        setNotificationBubbleEnabled(this.H);
        setSelected(this.F);
        showRays(this.G);
        setCovered(this.E);
        markStarred(this.I);
    }

    private void f() {
        this.x = false;
        if (this.w) {
            this.w = false;
            if (getStage() != null) {
                if (this.k != null) {
                    this.k.remove();
                }
                if (this.l != null) {
                    this.l.remove();
                }
                if (this.m != null) {
                    this.m.remove();
                }
                if (this.o != null) {
                    this.o.remove();
                }
                if (this.p != null) {
                    this.p.remove();
                }
                if (this.q != null) {
                    this.q.remove();
                }
                if (this.r != null) {
                    this.r.remove();
                }
                if (this.s != null) {
                    this.s.remove();
                }
                if (this.t != null) {
                    this.t.remove();
                }
                if (this.u != null) {
                    this.u.remove();
                }
                if (this.v != null) {
                    this.v.remove();
                }
            }
            this.k = null;
            this.l = null;
            this.m = null;
            this.o = null;
            this.p = null;
            this.r = null;
            this.q = null;
            this.s = null;
            this.t = null;
            this.u = null;
            this.v = null;
            if (this.y != null) {
                this.K = null;
            }
        }
    }

    public void showRays(boolean z) {
        this.G = z;
        if (this.w) {
            if (!z) {
                if (this.k != null) {
                    this.k.setVisible(false);
                    return;
                }
                return;
            }
            if (this.k == null) {
                this.k = new AttentionRaysUnderlay(192.0f, Color.WHITE);
                addActor(this.k);
                d();
            }
            float f = this.D ? 0.75f : 1.0f;
            this.k.size = 192.0f * f;
            this.k.setPosition((-64.0f) * f * 0.5f, (-52.0f) * f * 0.5f);
            this.k.restart();
            if (this.y != null) {
                this.k.setColor(Game.i.progressManager.getRarityBrightColor(this.y.getRarity()));
            }
            this.k.setVisible(true);
        }
    }

    public void setCornerText(CharSequence charSequence) {
        this.J = charSequence.toString();
        if (this.w) {
            if (this.t == null) {
                this.t = new Image(Game.i.assetManager.getDrawable("item-cell-number-bg"));
                this.t.setSize(43.0f, 36.0f);
                this.t.setPosition(7.0f, 97.0f);
                this.n.addActor(this.t);
                this.u = new Label(charSequence, Game.i.assetManager.getLabelStyle(21));
                this.u.setPosition(11.0f, 105.0f);
                this.u.setSize(38.0f, 18.0f);
                this.u.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                this.u.setAlignment(1);
                this.n.addActor(this.u);
                this.v = new Label(charSequence, Game.i.assetManager.getLabelStyle(21));
                this.v.setPosition(9.0f, 107.0f);
                this.v.setSize(38.0f, 18.0f);
                this.v.setAlignment(1);
                this.n.addActor(this.v);
                d();
                return;
            }
            this.v.setText(charSequence);
            this.u.setText(charSequence);
        }
    }

    public void setNotificationBubbleEnabled(boolean z) {
        this.H = z;
        if (this.w) {
            if (z) {
                if (this.q == null) {
                    this.q = new Image(Game.i.assetManager.getDrawable("count-bubble"));
                    this.q.setSize(32.25f, 36.75f);
                    this.q.setPosition(98.0f, 108.0f);
                    this.q.setVisible(false);
                    this.n.addActor(this.q);
                    d();
                }
                this.q.setVisible(true);
                return;
            }
            if (this.q != null) {
                this.q.setVisible(false);
            }
        }
    }

    public void setNoRarityBackground(boolean z) {
        this.C = z;
    }

    public void setCompact() {
        setSize(96.0f, 105.0f);
        this.n.setSize(96.0f, 105.0f);
        if (this.w && !this.D) {
            this.D = true;
            f();
            e();
            return;
        }
        this.D = true;
    }

    public boolean isSelected() {
        return this.F;
    }

    public void setSelected(boolean z) {
        this.F = z;
        if (this.w) {
            if (z) {
                if (this.m == null) {
                    this.m = new Image();
                    addActor(this.m);
                    d();
                }
                float f = this.D ? 0.75f : 1.0f;
                this.m.setSize(138.24f * f, 151.20001f * f);
                this.m.setPosition((-5.12f) * f, (-5.6f) * f);
                if ((this.A + this.B) % 2 == 0) {
                    this.m.setDrawable(Game.i.assetManager.getDrawable("item-cell-a-shape"));
                } else {
                    this.m.setDrawable(Game.i.assetManager.getDrawable("item-cell-b-shape"));
                }
                this.m.setVisible(true);
                return;
            }
            if (this.m != null) {
                this.m.setVisible(false);
            }
        }
    }

    private void g() {
        if (!this.w) {
            throw new IllegalStateException("Actor not set up yet");
        }
        if (this.o == null) {
            this.o = new Image();
            this.n.addActor(this.o);
            d();
        }
        float f = this.D ? 0.75f : 1.0f;
        this.o.setSize(128.0f * f, 140.0f * f);
        if (this.y == null || this.C) {
            this.o.setDrawable(Game.i.uiManager.itemCellShapes[(this.A + this.B) % 2]);
            this.o.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        } else {
            RarityType rarity = this.y.getRarity();
            if (this.E) {
                this.o.setDrawable(Game.i.uiManager.getItemCellRarityCoat(rarity, (this.A + this.B) % 2));
                this.o.setColor(Color.WHITE);
            } else {
                if ((this.A + this.B) % 2 == 0) {
                    this.o.setDrawable(Game.i.assetManager.getDrawable("item-cell-a"));
                } else {
                    this.o.setDrawable(Game.i.assetManager.getDrawable("item-cell-b"));
                }
                this.o.setColor(Game.i.progressManager.getRarityColor(rarity));
                if (this.t != null) {
                    this.t.setColor(Game.i.progressManager.getRarityColor(rarity));
                }
            }
        }
        markStarred(this.I);
        this.overlay.setZIndex(500);
    }

    public void setColRow(int i, int i2) {
        this.A = i;
        this.B = i2;
        if (this.w) {
            g();
        }
    }

    public void setCovered(boolean z) {
        this.E = z;
        if (this.w) {
            g();
            setIconAndCount(this.K, this.L, this.z);
        }
    }

    public void reveal() {
        if (!this.w) {
            e();
        }
        if (!this.E) {
            setCovered(true);
        }
        this.n.setTransform(true);
        this.n.addAction(Actions.sequence(Actions.delay(0.1f), Actions.scaleTo(0.0f, 1.0f, 0.25f, Interpolation.sine), Actions.parallel(Actions.scaleTo(1.1f, 1.1f, 0.25f, Interpolation.sine), Actions.run(() -> {
            setCovered(false);
            Game.i.soundManager.playRarity(this.y.getRarity());
            shine(true, true);
        })), Actions.scaleTo(1.0f, 1.0f, 0.1f, Interpolation.exp5In), Actions.run(() -> {
            this.n.setTransform(false);
        })));
    }

    public void shine(boolean z, boolean z2) {
        Image image;
        if (this.y == null) {
            return;
        }
        if (!this.w) {
            e();
        }
        RarityType rarity = this.y.getRarity();
        float f = this.D ? 0.75f : 1.0f;
        Color rarityBrightColor = Game.i.progressManager.getRarityBrightColor(rarity);
        if (z) {
            if (this.l == null) {
                float f2 = this.D ? 0.75f : 1.0f;
                this.l = new ParticlesCanvas();
                this.l.setSize(128.0f * f2, 140.0f * f2);
                this.l.setPosition(0.0f, 0.0f);
                addActor(this.l);
                d();
            }
            this.l.clearParticles();
            ParticleEffectPool.PooledEffect obtain = Game.i.uiManager.itemCellFlashParticles.obtain();
            O[0] = rarityBrightColor.r;
            O[1] = rarityBrightColor.g;
            O[2] = rarityBrightColor.f888b;
            Array.ArrayIterator<ParticleEmitter> it = obtain.getEmitters().iterator();
            while (it.hasNext()) {
                ParticleEmitter next = it.next();
                next.getTint().setColors(O);
                next.setMinParticleCount(8 + (8 * rarity.ordinal()));
                next.setMaxParticleCount(8 + (8 * rarity.ordinal()));
                next.getLife().setHigh(1200.0f + (rarity.ordinal() * 500.0f));
                next.getVelocity().setHigh(100.0f + (rarity.ordinal() * 10.0f), 400.0f + (rarity.ordinal() * 40.0f));
            }
            this.l.addParticle(obtain, 64.0f * f, 70.0f * f);
        }
        if (z2) {
            if ((this.A + this.B) % 2 == 0) {
                image = new Image(Game.i.assetManager.getDrawable("item-cell-a-shape"));
            } else {
                image = new Image(Game.i.assetManager.getDrawable("item-cell-b-shape"));
            }
            image.setColor(rarityBrightColor);
            image.setSize(128.0f * f, 140.0f * f);
            image.addAction(Actions.sequence(Actions.delay(0.15f + (rarity.ordinal() * 0.02f)), Actions.parallel(Actions.run(() -> {
                AnimatedImage animatedImage = new AnimatedImage(new Animation(0.01665f, Game.i.assetManager.getTextureRegions("item-cell-glow")));
                P.set(rarityBrightColor).lerp(Color.WHITE, 0.5f);
                animatedImage.setColor(P);
                animatedImage.setSize(128.0f * f, 140.0f * f);
                addActor(animatedImage);
                animatedImage.addAction(Actions.sequence(Actions.delay(1.0f), Actions.removeActor()));
            }), Actions.sequence(Actions.fadeOut(0.4f + (rarity.ordinal() * 0.05f)), Actions.removeActor()))));
            this.n.addActor(image);
            return;
        }
        AnimatedImage animatedImage = new AnimatedImage(new Animation(0.01665f, Game.i.assetManager.getTextureRegions("item-cell-glow")));
        P.set(rarityBrightColor).lerp(Color.WHITE, 0.5f);
        animatedImage.setColor(P);
        animatedImage.setSize(128.0f * f, 140.0f * f);
        addActor(animatedImage);
        animatedImage.addAction(Actions.sequence(Actions.delay(1.0f), Actions.removeActor()));
    }

    public void setIconAndCount(Actor actor, float f, int i) {
        this.K = actor;
        this.L = f;
        if (this.w) {
            if (this.p == null) {
                this.p = new Group();
                this.p.setTransform(false);
                this.n.addActor(this.p);
                d();
            }
            float f2 = this.D ? 0.75f : 1.0f;
            this.p.setSize(80.0f * f2, 80.0f * f2);
            this.p.setPosition(24.0f * f2, 46.0f * f2);
            float width = (-(f - 1.0f)) * this.p.getWidth() * 0.5f;
            this.p.clearChildren();
            if (actor != null) {
                actor.setSize(this.p.getWidth() * f, this.p.getHeight() * f);
                this.p.addActor(actor);
            }
            if (this.D) {
                if (i > 0) {
                    this.p.setPosition(width + 18.0f, width + 35.0f);
                } else {
                    this.p.setPosition(width + 18.0f, width + 23.0f);
                }
            } else if (i > 0) {
                this.p.setPosition(width + 24.0f, width + 46.0f);
            } else {
                this.p.setPosition(width + 24.0f, width + 30.0f);
            }
            this.p.setVisible(!this.E);
            if (this.t != null) {
                this.t.setVisible(!this.E);
                this.v.setVisible(!this.E);
                this.u.setVisible(!this.E);
            }
        }
        setCount(i);
    }

    public void setItemStack(ItemStack itemStack) {
        setItem(itemStack.getItem(), itemStack.getCount());
    }

    public void setItem(Item item, int i) {
        if (this.w) {
            if (this.y != item) {
                if (item == null) {
                    setIconAndCount(null, 1.0f, 0);
                } else {
                    Actor generateIcon = item.generateIcon(80.0f * (this.D ? 0.75f : 1.0f), false);
                    if (!item.isCountable()) {
                        i = 0;
                    }
                    setIconAndCount(generateIcon, 1.0f, i);
                }
                this.y = item;
                g();
                return;
            }
            return;
        }
        this.y = item;
        setCount(i);
    }

    public Item getItem() {
        return this.y;
    }

    public void setCount(int i) {
        this.z = i;
        if (this.w) {
            if (this.s == null) {
                this.s = new Label("", Game.i.assetManager.getLabelStyle(24));
                this.s.setAlignment(1);
                this.n.addActor(this.s);
                d();
            }
            float f = this.D ? 0.75f : 1.0f;
            this.s.setPosition(0.0f, 16.0f * f);
            this.s.setSize(128.0f * f, 18.0f * f);
            if (i > 0) {
                this.s.setVisible(!this.E);
                if (i < 10000000) {
                    this.s.setText(StringFormatter.commaSeparatedNumber(i));
                    return;
                } else {
                    this.s.setText(StringFormatter.compactNumber(i, false));
                    return;
                }
            }
            this.s.setVisible(false);
        }
    }

    public int getCount() {
        return this.z;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ItemCell$GcListener.class */
    private class GcListener {
        private GcListener() {
        }

        /* synthetic */ GcListener(ItemCell itemCell, byte b2) {
            this();
        }

        public void finalize() {
            if (ItemCell.this.w) {
                if (Game.getTimestampMillis() - ItemCell.this.M >= 700) {
                    ItemCell.a(ItemCell.this, true);
                }
                ItemCell.this.N = null;
            }
            super.finalize();
        }
    }
}

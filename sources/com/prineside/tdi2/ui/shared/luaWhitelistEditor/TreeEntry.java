package com.prineside.tdi2.ui.shared.luaWhitelistEditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.shared.LuajavaWhitelistEditor;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.luaWhitelistEditor.events.EntryStateChange;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/luaWhitelistEditor/TreeEntry.class */
public abstract class TreeEntry {
    public static final int STATE_NONE = 0;
    public static final int STATE_WHITELISTED = 1;
    public static final int STATE_BLACKLISTED = 2;
    public static final int STATE_ALL_CHILDREN_HANDLED = 3;
    public static final int STATE_ALL_CHILDREN_WHITELISTED = 4;
    public static final int STATE_ALL_CHILDREN_BLACKLISTED = 5;
    public static final Color BLACKLISTED_BG_COLOR = MaterialColor.RED.P800;
    public static final Color BLACKLISTED_CHILDREN_BG_COLOR = MaterialColor.RED.P800.cpy().lerp(new Color(858993663), 0.56f);
    public static final Color WHITELISTED_BG_COLOR = MaterialColor.GREEN.P800;
    public static final Color WHITELISTED_CHILDREN_BG_COLOR = MaterialColor.GREEN.P800.cpy().lerp(new Color(858993663), 0.56f);
    public static final Color ALL_CHILDREN_HANDLED_BG_COLOR = MaterialColor.CYAN.P800;

    @Null
    public final TreeEntry parent;

    /* renamed from: a, reason: collision with root package name */
    protected final String f3777a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private Table f3778b;

    @Null
    private Image c;
    public boolean hovered;
    public Array<TreeEntry> children = new Array<>(true, 1, TreeEntry.class);
    public boolean collapsed = true;
    public int state = 0;

    public abstract int getSortCategory();

    public abstract void applyStateFromWhitelist(Whitelist whitelist);

    public abstract void gatherSaveData(Array<String> array);

    public abstract Drawable getEntryIcon();

    /* JADX INFO: Access modifiers changed from: protected */
    public TreeEntry(TreeEntry treeEntry, String str) {
        this.parent = treeEntry;
        this.f3777a = str;
    }

    public String getName() {
        return this.f3777a;
    }

    public String getSortName() {
        return this.f3777a;
    }

    public void addChild(TreeEntry treeEntry) {
        this.children.add(treeEntry);
    }

    public Array<TreeEntry> getChildren() {
        return this.children;
    }

    public void walkRecursively(ObjectConsumer<TreeEntry> objectConsumer) {
        objectConsumer.accept(this);
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).walkRecursively(objectConsumer);
        }
    }

    public Table getListEntry() {
        if (this.f3778b == null) {
            this.f3778b = new Table();
            this.f3778b.setTouchable(Touchable.enabled);
            this.f3778b.setUserObject(this);
            c();
        }
        return this.f3778b;
    }

    public int getState() {
        return this.state;
    }

    public void updateParentsBackground() {
        TreeEntry treeEntry = this.parent;
        while (true) {
            TreeEntry treeEntry2 = treeEntry;
            if (treeEntry2 != null) {
                treeEntry2.updateBackground();
                treeEntry = treeEntry2.parent;
            } else {
                return;
            }
        }
    }

    public void updateBackground() {
        if (this.c == null) {
            return;
        }
        if (this.hovered) {
            this.c.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        } else {
            this.c.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        }
        updateBackgroundColorToState(getState(), getListEntry());
    }

    public static void updateBackgroundColorToState(int i, Table table) {
        switch (i) {
            case 0:
                table.setBackground((Drawable) null);
                return;
            case 1:
                table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(WHITELISTED_BG_COLOR));
                return;
            case 2:
                table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(BLACKLISTED_BG_COLOR));
                return;
            case 3:
                table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(ALL_CHILDREN_HANDLED_BG_COLOR));
                return;
            case 4:
                table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(WHITELISTED_CHILDREN_BG_COLOR));
                return;
            case 5:
                table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(BLACKLISTED_CHILDREN_BG_COLOR));
                return;
            default:
                return;
        }
    }

    private boolean b() {
        return this.children.size != 0;
    }

    protected boolean a() {
        return false;
    }

    public void setChildrenCollapsedRecursively() {
        for (int i = 0; i < this.children.size; i++) {
            TreeEntry treeEntry = this.children.get(i);
            treeEntry.collapsed = true;
            treeEntry.setChildrenCollapsedRecursively();
        }
    }

    public void setWhiteListed() {
        TreeEntry treeEntry = this.parent;
        boolean z = false;
        while (true) {
            if (treeEntry == null) {
                break;
            }
            if (treeEntry.getState() == 2) {
                z = true;
                break;
            }
            treeEntry = treeEntry.parent;
        }
        if (z) {
            Notifications.i().addFailure("This entry has blacklisted parent, can not whitelist");
        } else {
            this.state = 1;
        }
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).setWhiteListed();
        }
        updateBackground();
        Game.EVENTS.getListeners(EntryStateChange.class).trigger(new EntryStateChange(this));
    }

    public void unsetWhiteListed() {
        if (getState() == 1) {
            this.state = 0;
        }
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).unsetWhiteListed();
        }
        updateBackground();
        Game.EVENTS.getListeners(EntryStateChange.class).trigger(new EntryStateChange(this));
    }

    public void setBlackListed() {
        this.state = 2;
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).setBlackListed();
        }
        updateBackground();
        Game.EVENTS.getListeners(EntryStateChange.class).trigger(new EntryStateChange(this));
    }

    public void unsetBlackListed() {
        if (getState() == 2) {
            this.state = 0;
        }
        for (int i = 0; i < this.children.size; i++) {
            this.children.get(i).unsetBlackListed();
        }
        updateBackground();
        Game.EVENTS.getListeners(EntryStateChange.class).trigger(new EntryStateChange(this));
    }

    public void setCollapsed(boolean z) {
        this.collapsed = z;
        if (!this.collapsed) {
            setChildrenCollapsedRecursively();
            for (int i = 0; i < this.children.size; i++) {
                this.children.get(i).c();
            }
        }
        c();
    }

    private void c() {
        Table listEntry = getListEntry();
        listEntry.clearChildren();
        this.c = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.c.setFillParent(true);
        this.c.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        listEntry.addActor(this.c);
        Table table = new Table();
        listEntry.add(table).growX().row();
        if (b()) {
            TextureRegionDrawable drawable = Game.i.assetManager.getDrawable(this.collapsed ? "icon-triangle-right-hollow" : "icon-triangle-bottom-hollow");
            Runnable runnable = () -> {
                setCollapsed(!this.collapsed);
            };
            Color color = new Color(1.0f, 1.0f, 1.0f, 0.56f);
            Color color2 = Color.WHITE;
            PaddedImageButton paddedImageButton = new PaddedImageButton(drawable, runnable, color, color2, color2);
            paddedImageButton.setIconSize(16.0f, 16.0f);
            paddedImageButton.setIconPosition(4.0f, 4.0f);
            table.add((Table) paddedImageButton).width(24.0f).height(24.0f);
        } else {
            table.add().height(1.0f).width(24.0f);
        }
        table.add((Table) new Image(getEntryIcon())).size(16.0f).padRight(8.0f);
        Label label = new Label(getName(), Game.i.assetManager.getLabelStyle(21));
        label.setWrap(true);
        table.add((Table) label).minWidth(400.0f).growX();
        table.add().height(1.0f).growX();
        if (this instanceof EClass) {
            LabelButton labelButton = new LabelButton(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-magnifying-glass>"), Game.i.assetManager.getLabelStyle(21), () -> {
                LuajavaWhitelistEditor.i().showClassUsagesWindow(((EClass) this).getForClass());
            });
            labelButton.setColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300);
            table.add((Table) labelButton).width(40.0f).height(24.0f);
            labelButton.setAlignment(1);
        }
        LabelButton labelButton2 = new LabelButton("W", Game.i.assetManager.getLabelStyle(21), () -> {
            int state = getState();
            if (state == 1 || state == 4) {
                unsetWhiteListed();
            } else {
                setWhiteListed();
            }
            updateBackground();
            updateParentsBackground();
        });
        labelButton2.setColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P300);
        table.add((Table) labelButton2).width(40.0f).height(24.0f);
        labelButton2.setAlignment(1);
        LabelButton labelButton3 = new LabelButton("B", Game.i.assetManager.getLabelStyle(21), () -> {
            if (getState() == 2) {
                unsetBlackListed();
            } else {
                setBlackListed();
            }
            updateBackground();
            updateParentsBackground();
        });
        labelButton3.setColors(MaterialColor.RED.P500, MaterialColor.RED.P300);
        labelButton3.setAlignment(1);
        table.add((Table) labelButton3).width(40.0f).height(24.0f);
        if (!this.collapsed) {
            Table table2 = new Table();
            table2.add().width(12.0f).growY();
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            table2.add((Table) image).width(1.0f).growY();
            Table table3 = new Table();
            table3.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(858993612)));
            Array.ArrayIterator<TreeEntry> it = getChildren().iterator();
            while (it.hasNext()) {
                TreeEntry next = it.next();
                Table listEntry2 = next.getListEntry();
                Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                if (next.a()) {
                    table3.add((Table) image2).height(1.0f).width(6.0f);
                } else {
                    table3.add((Table) image2).height(1.0f).top().padTop(12.0f).width(6.0f);
                }
                table3.add(listEntry2).padLeft(5.0f).padTop(1.0f).growX().row();
            }
            table2.add(table3).growX().growY().row();
            listEntry.add(table2).growX();
        }
        updateBackground();
    }
}

package com.prineside.tdi2.configs;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/configs/GameRenderingOrder.class */
public final class GameRenderingOrder {

    /* renamed from: a, reason: collision with root package name */
    private static int f1829a;
    public static final int AFTER_PERCENT = 1;
    public static final int BEFORE_PERCENT = -1;
    public static final int STEP = 100;
    public static final int MAP_DRAW_TILES;
    public static final int MAP_DRAW_STAINS;
    public static final int MAP_DRAW_TILE_EXTRAS;
    public static final int MAP_DRAW_BATCH;
    public static final int MAP_DRAW_BUILDINGS_CACHE;
    public static final int TOWER_APPLY_INTERPOLATION;
    public static final int TOWER_DRAW_WEAPONS;
    public static final int TOWER_DRAW_BATCH;
    public static final int MINER_DRAW_BATCH;
    public static final int MODIFIER_DRAW_BATCH;
    public static final int UNIT_DRAW_TILE_LAYER;
    public static final int PATH_RENDERING_DRAW;
    public static final int OVERLOAD_IMPULSE_UPDATE_GRAPHICS;
    public static final int PROJECTILE_TRAIL_UPDATE_DRAW;
    public static final int PROJECTILE_TRAIL_DRAW_OPAQUE;
    public static final int OVERLOAD_IMPULSE_DRAW;
    public static final int UNIT_DRAW_GROUNDED;
    public static final int ENEMY_DRAW;
    public static final int WAVE_DRAW;
    public static final int UNIT_DRAW_FLYING;
    public static final int TOWER_DRAW_BATCH_ADDITIVE;
    public static final int MODIFIER_DRAW_BATCH_ADDITIVE;
    public static final int ABILITY_DRAW_BATCH_ADDITIVE;
    public static final int PARTICLE_UPDATE_DRAW;
    public static final int EFFECTS_FBO_START;
    public static final int PARTICLE_DRAW;
    public static final int PROJECTILE_TRAIL_DRAW;
    public static final int PROJECTILE_DRAW;
    public static final int EFFECTS_FBO_END;
    public static final int MAP_DRAW;
    public static final int TOWER_DRAW_RANGES;
    public static final int MAP_RENDERING_POST_DRAW;
    public static final int MAP_RENDERING_GAME_SELECTION;
    public static final int MAP_RENDERING_MAP_EDITOR_SELECTION;
    public static final int HOT_KEY_DRAW_CURSOR;
    public static final int ABILITY_DRAW_BATCH;
    public static final int ENEMY_DRAW_HEALTH;
    public static final int PARTICLE_DRAW_DAMAGE;
    public static final int INPUT_DRAW;
    public static final int QUEST_DRAW;
    public static final int RANDOM_ENCOUNTER_DRAW;
    public static final int GAME_UI_DRAW;
    public static final int SOUND_DRAW;
    public static final int DEBUG_DPS_CHART_DRAW;

    static {
        f1829a = 0;
        int i = f1829a;
        f1829a = i + 1;
        MAP_DRAW_TILES = 100 * i;
        int i2 = f1829a;
        f1829a = i2 + 1;
        MAP_DRAW_STAINS = 100 * i2;
        int i3 = f1829a;
        f1829a = i3 + 1;
        MAP_DRAW_TILE_EXTRAS = 100 * i3;
        int i4 = f1829a;
        f1829a = i4 + 1;
        MAP_DRAW_BATCH = 100 * i4;
        int i5 = f1829a;
        f1829a = i5 + 1;
        MAP_DRAW_BUILDINGS_CACHE = 100 * i5;
        int i6 = f1829a;
        f1829a = i6 + 1;
        TOWER_APPLY_INTERPOLATION = 100 * i6;
        int i7 = f1829a;
        f1829a = i7 + 1;
        TOWER_DRAW_WEAPONS = 100 * i7;
        int i8 = f1829a;
        f1829a = i8 + 1;
        TOWER_DRAW_BATCH = 100 * i8;
        int i9 = f1829a;
        f1829a = i9 + 1;
        MINER_DRAW_BATCH = 100 * i9;
        int i10 = f1829a;
        f1829a = i10 + 1;
        MODIFIER_DRAW_BATCH = 100 * i10;
        int i11 = f1829a;
        f1829a = i11 + 1;
        UNIT_DRAW_TILE_LAYER = 100 * i11;
        int i12 = f1829a;
        f1829a = i12 + 1;
        PATH_RENDERING_DRAW = 100 * i12;
        int i13 = f1829a;
        f1829a = i13 + 1;
        OVERLOAD_IMPULSE_UPDATE_GRAPHICS = 100 * i13;
        int i14 = f1829a;
        f1829a = i14 + 1;
        PROJECTILE_TRAIL_UPDATE_DRAW = 100 * i14;
        int i15 = f1829a;
        f1829a = i15 + 1;
        PROJECTILE_TRAIL_DRAW_OPAQUE = 100 * i15;
        int i16 = f1829a;
        f1829a = i16 + 1;
        OVERLOAD_IMPULSE_DRAW = 100 * i16;
        int i17 = f1829a;
        f1829a = i17 + 1;
        UNIT_DRAW_GROUNDED = 100 * i17;
        int i18 = f1829a;
        f1829a = i18 + 1;
        ENEMY_DRAW = 100 * i18;
        int i19 = f1829a;
        f1829a = i19 + 1;
        WAVE_DRAW = 100 * i19;
        int i20 = f1829a;
        f1829a = i20 + 1;
        UNIT_DRAW_FLYING = 100 * i20;
        int i21 = f1829a;
        f1829a = i21 + 1;
        TOWER_DRAW_BATCH_ADDITIVE = 100 * i21;
        int i22 = f1829a;
        f1829a = i22 + 1;
        MODIFIER_DRAW_BATCH_ADDITIVE = 100 * i22;
        int i23 = f1829a;
        f1829a = i23 + 1;
        ABILITY_DRAW_BATCH_ADDITIVE = 100 * i23;
        int i24 = f1829a;
        f1829a = i24 + 1;
        PARTICLE_UPDATE_DRAW = 100 * i24;
        int i25 = f1829a;
        f1829a = i25 + 1;
        EFFECTS_FBO_START = 100 * i25;
        int i26 = f1829a;
        f1829a = i26 + 1;
        PARTICLE_DRAW = 100 * i26;
        int i27 = f1829a;
        f1829a = i27 + 1;
        PROJECTILE_TRAIL_DRAW = 100 * i27;
        int i28 = f1829a;
        f1829a = i28 + 1;
        PROJECTILE_DRAW = 100 * i28;
        int i29 = f1829a;
        f1829a = i29 + 1;
        EFFECTS_FBO_END = 100 * i29;
        int i30 = f1829a;
        f1829a = i30 + 1;
        MAP_DRAW = 100 * i30;
        int i31 = f1829a;
        f1829a = i31 + 1;
        TOWER_DRAW_RANGES = 100 * i31;
        int i32 = f1829a;
        f1829a = i32 + 1;
        MAP_RENDERING_POST_DRAW = 100 * i32;
        int i33 = f1829a;
        f1829a = i33 + 1;
        MAP_RENDERING_GAME_SELECTION = 100 * i33;
        int i34 = f1829a;
        f1829a = i34 + 1;
        MAP_RENDERING_MAP_EDITOR_SELECTION = 100 * i34;
        int i35 = f1829a;
        f1829a = i35 + 1;
        HOT_KEY_DRAW_CURSOR = 100 * i35;
        int i36 = f1829a;
        f1829a = i36 + 1;
        ABILITY_DRAW_BATCH = 100 * i36;
        int i37 = f1829a;
        f1829a = i37 + 1;
        ENEMY_DRAW_HEALTH = 100 * i37;
        int i38 = f1829a;
        f1829a = i38 + 1;
        PARTICLE_DRAW_DAMAGE = 100 * i38;
        int i39 = f1829a;
        f1829a = i39 + 1;
        INPUT_DRAW = 100 * i39;
        int i40 = f1829a;
        f1829a = i40 + 1;
        QUEST_DRAW = 100 * i40;
        int i41 = f1829a;
        f1829a = i41 + 1;
        RANDOM_ENCOUNTER_DRAW = 100 * i41;
        int i42 = f1829a;
        f1829a = i42 + 1;
        GAME_UI_DRAW = 100 * i42;
        int i43 = f1829a;
        f1829a = i43 + 1;
        SOUND_DRAW = 100 * i43;
        int i44 = f1829a;
        f1829a = i44 + 1;
        DEBUG_DPS_CHART_DRAW = 100 * i44;
    }

    private GameRenderingOrder() {
    }
}
package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.opengl.CGL;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/MapPrestigeConfig.class */
public class MapPrestigeConfig implements KryoSerializable {
    public static final int BASE_BONUS = 50;
    public static final int MAX_DIFFICULTY_BONUS = 50;
    public static final int NO_ABILITIES_BONUS = 10;
    public static final int WALKABLE_PLATFORMS_BONUS = 10;
    public static final int NO_BOUNTY_MOD_BONUS = 20;
    public static final int NO_MINERS_BONUS = 20;
    public static final int NO_RESEARCH_BONUS = 40;
    public String userMapId;
    public double mapPrice;
    public int averageDifficulty;
    public boolean noAbilities;
    public boolean noResearch;
    public boolean walkablePlatforms;
    public boolean noBounty;
    public boolean noMiners;
    public long score;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.userMapId, String.class);
        output.writeDouble(this.mapPrice);
        output.writeVarInt(this.averageDifficulty, true);
        output.writeBoolean(this.noAbilities);
        output.writeBoolean(this.noResearch);
        output.writeBoolean(this.walkablePlatforms);
        output.writeBoolean(this.noBounty);
        output.writeBoolean(this.noMiners);
        output.writeVarLong(this.score, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.userMapId = (String) kryo.readObjectOrNull(input, String.class);
        this.mapPrice = input.readDouble();
        this.averageDifficulty = input.readVarInt(true);
        this.noAbilities = input.readBoolean();
        this.noResearch = input.readBoolean();
        this.walkablePlatforms = input.readBoolean();
        this.noBounty = input.readBoolean();
        this.noMiners = input.readBoolean();
        this.score = input.readVarLong(true);
    }

    private MapPrestigeConfig() {
    }

    public MapPrestigeConfig(String str, double d, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, long j) {
        this.userMapId = str;
        this.mapPrice = d;
        this.averageDifficulty = i;
        this.noAbilities = z;
        this.noResearch = z2;
        this.walkablePlatforms = z3;
        this.noBounty = z4;
        this.noMiners = z5;
        this.score = j;
    }

    public int getCrownsCount() {
        int totalBonus = getTotalBonus();
        if (totalBonus < 40) {
            return 1;
        }
        if (totalBonus < 80) {
            return 2;
        }
        if (totalBonus < 120) {
            return 3;
        }
        return totalBonus < 160 ? 4 : 5;
    }

    public double getScoreMultiplier() {
        double maxPrestigeScore = this.score / getMaxPrestigeScore();
        double d = maxPrestigeScore;
        if (maxPrestigeScore > 1.0d) {
            d = 1.0d;
        }
        return d;
    }

    public int getDifficultyBonus() {
        if (this.averageDifficulty <= 100) {
            return 0;
        }
        if (this.averageDifficulty < 170) {
            return 10;
        }
        if (this.averageDifficulty < 250) {
            return 20;
        }
        if (this.averageDifficulty < 350) {
            return 30;
        }
        if (this.averageDifficulty < 450) {
            return 40;
        }
        return 50;
    }

    public int getTotalBonus() {
        return MathUtils.floor((50 + getDifficultyBonus() + (this.noResearch ? 40 : 0) + (this.noAbilities ? 10 : 0) + (this.walkablePlatforms ? 10 : 0) + (this.noBounty ? 20 : 0) + (this.noMiners ? 20 : 0)) * ((float) getScoreMultiplier()));
    }

    public int getFinalPrestigeTokens() {
        return MathUtils.floor(((float) (this.mapPrice * getTotalBonus() * 0.01d)) + ((float) this.mapPrice));
    }

    public int getMaxPrestigeScore() {
        return getMaxPrestigeScore(this.averageDifficulty, this.noResearch);
    }

    public static int getMaxPrestigeScore(int i, boolean z) {
        int i2;
        int round;
        if (z) {
            int pow = (int) (((31800.0d / StrictMath.pow(i, 1.1d)) - 30.15d) * 1000.0d);
            i2 = pow;
            if (pow < 4000) {
                i2 = 4000;
            }
            if (i2 > 400000) {
                i2 = 400000;
            }
        } else {
            int pow2 = (int) (((12000.0d / StrictMath.pow(i - 11, 0.4d)) - 807.0d) * 1000.0d);
            i2 = pow2;
            if (pow2 < 150000) {
                i2 = 150000;
            }
            if (i2 > 3000000) {
                i2 = 3000000;
            }
        }
        if (i2 > 1000000) {
            round = ((int) StrictMath.round(i2 / 100000.0d)) * 100000;
        } else if (i2 > 100000) {
            round = ((int) StrictMath.round(i2 / 10000.0d)) * CGL.kCGLBadAttribute;
        } else if (i2 > 10000) {
            round = ((int) StrictMath.round(i2 / 1000.0d)) * 1000;
        } else {
            round = ((int) StrictMath.round(i2 / 100.0d)) * 100;
        }
        return round;
    }

    public String describe() {
        return "  Crowns: " + getCrownsCount() + "\n  Score multiplier: " + getScoreMultiplier() + " (" + ((Object) StringFormatter.commaSeparatedNumber(this.score)) + " / " + ((Object) StringFormatter.commaSeparatedNumber(getMaxPrestigeScore())) + ")\n  Difficulty bonus: " + getDifficultyBonus() + " (" + this.averageDifficulty + "%)\n  Map price: " + this.mapPrice + "P, no abilities: " + this.noAbilities + ", no research: " + this.noResearch + ", walkable platforms: " + this.walkablePlatforms + ", no bounty: " + this.noBounty + ", no miners: " + this.noMiners + "\n  Final bonus: " + getTotalBonus() + "%\n  Final Prestige tokens price: " + getFinalPrestigeTokens() + SequenceUtils.EOL;
    }

    public void toJson(Json json) {
        json.writeValue("s", Long.valueOf(this.score));
        json.writeValue("umi", this.userMapId);
        json.writeValue("mp", Double.valueOf(this.mapPrice));
        json.writeValue("ad", Integer.valueOf(this.averageDifficulty));
        json.writeValue("na", Boolean.valueOf(this.noAbilities));
        json.writeValue("nr", Boolean.valueOf(this.noResearch));
        json.writeValue("wp", Boolean.valueOf(this.walkablePlatforms));
        json.writeValue("nb", Boolean.valueOf(this.noBounty));
        json.writeValue("nm", Boolean.valueOf(this.noMiners));
    }

    public static MapPrestigeConfig fromJson(JsonValue jsonValue) {
        return new MapPrestigeConfig(jsonValue.getString("umi"), jsonValue.getInt("mp"), jsonValue.getInt("ad"), jsonValue.getBoolean("na"), jsonValue.getBoolean("nr"), jsonValue.getBoolean("wp"), jsonValue.getBoolean("nb"), jsonValue.getBoolean("nm"), jsonValue.getInt("s"));
    }
}

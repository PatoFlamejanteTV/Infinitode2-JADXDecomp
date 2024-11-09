package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/FreeTrialPeriod.class */
public final class FreeTrialPeriod {
    private final int numberOfUnits;
    private final PeriodUnit unit;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/FreeTrialPeriod$PeriodUnit.class */
    public enum PeriodUnit {
        DAY,
        MONTH,
        WEEK,
        YEAR;

        public static PeriodUnit parse(char c) {
            switch (c) {
                case 'D':
                    return DAY;
                case 'M':
                    return MONTH;
                case 'W':
                    return WEEK;
                case 'Y':
                    return YEAR;
                default:
                    throw new IllegalArgumentException("Character not mapped to PeriodUnit: " + c);
            }
        }
    }

    public FreeTrialPeriod(int i, PeriodUnit periodUnit) {
        this.numberOfUnits = i;
        this.unit = periodUnit;
    }

    public final int getNumberOfUnits() {
        return this.numberOfUnits;
    }

    public final PeriodUnit getUnit() {
        return this.unit;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FreeTrialPeriod freeTrialPeriod = (FreeTrialPeriod) obj;
        return this.numberOfUnits == freeTrialPeriod.numberOfUnits && this.unit == freeTrialPeriod.unit;
    }

    public final int hashCode() {
        return (this.numberOfUnits * 31) + this.unit.hashCode();
    }
}

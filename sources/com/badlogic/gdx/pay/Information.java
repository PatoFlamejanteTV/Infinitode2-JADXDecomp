package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/Information.class */
public final class Information {
    public static final Information UNAVAILABLE = new Information(null, null, null);
    private final String localName;
    private final String localDescription;
    private final String localPricing;

    @Deprecated
    private Integer priceInCents;
    private Double priceAsDouble;
    private String priceCurrencyCode;
    private FreeTrialPeriod freeTrialPeriod;

    public Information(String str, String str2, String str3) {
        this.localName = str;
        this.localDescription = str2;
        this.localPricing = str3;
    }

    private Information(Builder builder) {
        this.localName = builder.localName;
        this.localDescription = builder.localDescription;
        this.localPricing = builder.localPricing;
        this.priceInCents = builder.priceInCents;
        this.priceAsDouble = builder.priceAsDouble;
        this.priceCurrencyCode = builder.priceCurrencyCode;
        this.freeTrialPeriod = builder.freeTrialPeriod;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Deprecated
    public final Integer getPriceInCents() {
        return this.priceInCents;
    }

    public final FreeTrialPeriod getFreeTrialPeriod() {
        return this.freeTrialPeriod;
    }

    public final Double getPriceAsDouble() {
        return this.priceAsDouble;
    }

    public final String getPriceCurrencyCode() {
        return this.priceCurrencyCode;
    }

    public final String getLocalName() {
        return this.localName;
    }

    public final String getLocalDescription() {
        return this.localDescription;
    }

    public final String getLocalPricing() {
        return this.localPricing;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Information information = (Information) obj;
        if (this.localName != null) {
            if (!this.localName.equals(information.localName)) {
                return false;
            }
        } else if (information.localName != null) {
            return false;
        }
        if (this.localDescription != null) {
            if (!this.localDescription.equals(information.localDescription)) {
                return false;
            }
        } else if (information.localDescription != null) {
            return false;
        }
        return this.localPricing != null ? this.localPricing.equals(information.localPricing) : information.localPricing == null;
    }

    public final int hashCode() {
        return ((((this.localName != null ? this.localName.hashCode() : 0) * 31) + (this.localDescription != null ? this.localDescription.hashCode() : 0)) * 31) + (this.localPricing != null ? this.localPricing.hashCode() : 0);
    }

    public final String toString() {
        return "Information{localName='" + this.localName + "', localDescription='" + this.localDescription + "', localPricing='" + this.localPricing + "'}";
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/Information$Builder.class */
    public static final class Builder {
        private String localName;
        private String localDescription;
        private String localPricing;

        @Deprecated
        private Integer priceInCents;
        private Double priceAsDouble;
        private String priceCurrencyCode;
        private FreeTrialPeriod freeTrialPeriod;

        private Builder() {
        }

        public final Builder localName(String str) {
            this.localName = str;
            return this;
        }

        public final Builder localDescription(String str) {
            this.localDescription = str;
            return this;
        }

        public final Builder freeTrialPeriod(FreeTrialPeriod freeTrialPeriod) {
            this.freeTrialPeriod = freeTrialPeriod;
            return this;
        }

        public final Builder localPricing(String str) {
            this.localPricing = str;
            return this;
        }

        @Deprecated
        public final Builder priceInCents(Integer num) {
            this.priceInCents = num;
            return this;
        }

        public final Builder priceAsDouble(Double d) {
            this.priceAsDouble = d;
            return this;
        }

        public final Builder priceCurrencyCode(String str) {
            this.priceCurrencyCode = str;
            return this;
        }

        public final Information build() {
            return new Information(this);
        }
    }
}

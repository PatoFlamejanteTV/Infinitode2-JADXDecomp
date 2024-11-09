package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers.class */
public final class TimeSerializers {
    public static void addDefaultSerializers(Kryo kryo) {
        if (Util.isClassAvailable("java.time.Duration")) {
            kryo.addDefaultSerializer(Duration.class, DurationSerializer.class);
        }
        if (Util.isClassAvailable("java.time.Instant")) {
            kryo.addDefaultSerializer(Instant.class, InstantSerializer.class);
        }
        if (Util.isClassAvailable("java.time.LocalDate")) {
            kryo.addDefaultSerializer(LocalDate.class, LocalDateSerializer.class);
        }
        if (Util.isClassAvailable("java.time.LocalTime")) {
            kryo.addDefaultSerializer(LocalTime.class, LocalTimeSerializer.class);
        }
        if (Util.isClassAvailable("java.time.LocalDateTime")) {
            kryo.addDefaultSerializer(LocalDateTime.class, LocalDateTimeSerializer.class);
        }
        if (Util.isClassAvailable("java.time.ZoneOffset")) {
            kryo.addDefaultSerializer(ZoneOffset.class, ZoneOffsetSerializer.class);
        }
        if (Util.isClassAvailable("java.time.ZoneId")) {
            kryo.addDefaultSerializer(ZoneId.class, ZoneIdSerializer.class);
        }
        if (Util.isClassAvailable("java.time.OffsetTime")) {
            kryo.addDefaultSerializer(OffsetTime.class, OffsetTimeSerializer.class);
        }
        if (Util.isClassAvailable("java.time.OffsetDateTime")) {
            kryo.addDefaultSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.class);
        }
        if (Util.isClassAvailable("java.time.ZonedDateTime")) {
            kryo.addDefaultSerializer(ZonedDateTime.class, ZonedDateTimeSerializer.class);
        }
        if (Util.isClassAvailable("java.time.Year")) {
            kryo.addDefaultSerializer(Year.class, YearSerializer.class);
        }
        if (Util.isClassAvailable("java.time.YearMonth")) {
            kryo.addDefaultSerializer(YearMonth.class, YearMonthSerializer.class);
        }
        if (Util.isClassAvailable("java.time.MonthDay")) {
            kryo.addDefaultSerializer(MonthDay.class, MonthDaySerializer.class);
        }
        if (Util.isClassAvailable("java.time.Period")) {
            kryo.addDefaultSerializer(Period.class, PeriodSerializer.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$DurationSerializer.class */
    public static class DurationSerializer extends ImmutableSerializer<Duration> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Duration duration) {
            output.writeLong(duration.getSeconds());
            output.writeInt(duration.getNano(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Duration read2(Kryo kryo, Input input, Class cls) {
            return Duration.ofSeconds(input.readLong(), input.readInt(true));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$InstantSerializer.class */
    public static class InstantSerializer extends ImmutableSerializer<Instant> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Instant instant) {
            output.writeVarLong(instant.getEpochSecond(), true);
            output.writeInt(instant.getNano(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Instant read2(Kryo kryo, Input input, Class cls) {
            return Instant.ofEpochSecond(input.readVarLong(true), input.readInt(true));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$LocalDateSerializer.class */
    public static class LocalDateSerializer extends ImmutableSerializer<LocalDate> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LocalDate localDate) {
            write(output, localDate);
        }

        static void write(Output output, LocalDate localDate) {
            output.writeInt(localDate.getYear(), true);
            output.writeByte(localDate.getMonthValue());
            output.writeByte(localDate.getDayOfMonth());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public LocalDate read2(Kryo kryo, Input input, Class cls) {
            return read(input);
        }

        static LocalDate read(Input input) {
            return LocalDate.of(input.readInt(true), input.readByte(), input.readByte());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$LocalDateTimeSerializer.class */
    public static class LocalDateTimeSerializer extends ImmutableSerializer<LocalDateTime> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LocalDateTime localDateTime) {
            LocalDateSerializer.write(output, localDateTime.toLocalDate());
            LocalTimeSerializer.write(output, localDateTime.toLocalTime());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public LocalDateTime read2(Kryo kryo, Input input, Class cls) {
            return LocalDateTime.of(LocalDateSerializer.read(input), LocalTimeSerializer.read(input));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$LocalTimeSerializer.class */
    public static class LocalTimeSerializer extends ImmutableSerializer<LocalTime> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LocalTime localTime) {
            write(output, localTime);
        }

        static void write(Output output, LocalTime localTime) {
            if (localTime.getNano() == 0) {
                if (localTime.getSecond() == 0) {
                    if (localTime.getMinute() == 0) {
                        output.writeByte(localTime.getHour() ^ (-1));
                        return;
                    } else {
                        output.writeByte(localTime.getHour());
                        output.writeByte(localTime.getMinute() ^ (-1));
                        return;
                    }
                }
                output.writeByte(localTime.getHour());
                output.writeByte(localTime.getMinute());
                output.writeByte(localTime.getSecond() ^ (-1));
                return;
            }
            output.writeByte(localTime.getHour());
            output.writeByte(localTime.getMinute());
            output.writeByte(localTime.getSecond());
            output.writeInt(localTime.getNano(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public LocalTime read2(Kryo kryo, Input input, Class cls) {
            return read(input);
        }

        static LocalTime read(Input input) {
            int readByte = input.readByte();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            if (readByte < 0) {
                readByte = (readByte ^ (-1)) == true ? 1 : 0;
            } else {
                int readByte2 = input.readByte();
                i = readByte2;
                if (readByte2 < 0) {
                    i = (i ^ (-1)) == true ? 1 : 0;
                } else {
                    int readByte3 = input.readByte();
                    i2 = readByte3;
                    if (readByte3 < 0) {
                        i2 = (i2 ^ (-1)) == true ? 1 : 0;
                    } else {
                        i3 = input.readInt(true);
                    }
                }
            }
            return LocalTime.of(readByte, i, i2, i3);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$ZoneOffsetSerializer.class */
    public static class ZoneOffsetSerializer extends ImmutableSerializer<ZoneOffset> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, ZoneOffset zoneOffset) {
            write(output, zoneOffset);
        }

        static void write(Output output, ZoneOffset zoneOffset) {
            int totalSeconds = zoneOffset.getTotalSeconds();
            int i = totalSeconds % 900 == 0 ? totalSeconds / 900 : 127;
            output.writeByte(i);
            if (i == 127) {
                output.writeInt(totalSeconds);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public ZoneOffset read2(Kryo kryo, Input input, Class cls) {
            return read(input);
        }

        static ZoneOffset read(Input input) {
            byte readByte = input.readByte();
            return readByte == Byte.MAX_VALUE ? ZoneOffset.ofTotalSeconds(input.readInt()) : ZoneOffset.ofTotalSeconds(readByte * 900);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$ZoneIdSerializer.class */
    public static class ZoneIdSerializer extends ImmutableSerializer<ZoneId> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, ZoneId zoneId) {
            write(output, zoneId);
        }

        static void write(Output output, ZoneId zoneId) {
            output.writeString(zoneId.getId());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public ZoneId read2(Kryo kryo, Input input, Class cls) {
            return read(input);
        }

        static ZoneId read(Input input) {
            return ZoneId.of(input.readString());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$OffsetTimeSerializer.class */
    public static class OffsetTimeSerializer extends ImmutableSerializer<OffsetTime> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OffsetTime offsetTime) {
            LocalTimeSerializer.write(output, offsetTime.toLocalTime());
            ZoneOffsetSerializer.write(output, offsetTime.getOffset());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OffsetTime read2(Kryo kryo, Input input, Class cls) {
            return OffsetTime.of(LocalTimeSerializer.read(input), ZoneOffsetSerializer.read(input));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$OffsetDateTimeSerializer.class */
    public static class OffsetDateTimeSerializer extends ImmutableSerializer<OffsetDateTime> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OffsetDateTime offsetDateTime) {
            LocalDateSerializer.write(output, offsetDateTime.toLocalDate());
            LocalTimeSerializer.write(output, offsetDateTime.toLocalTime());
            ZoneOffsetSerializer.write(output, offsetDateTime.getOffset());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OffsetDateTime read2(Kryo kryo, Input input, Class cls) {
            return OffsetDateTime.of(LocalDateSerializer.read(input), LocalTimeSerializer.read(input), ZoneOffsetSerializer.read(input));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$ZonedDateTimeSerializer.class */
    public static class ZonedDateTimeSerializer extends ImmutableSerializer<ZonedDateTime> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, ZonedDateTime zonedDateTime) {
            LocalDateSerializer.write(output, zonedDateTime.toLocalDate());
            LocalTimeSerializer.write(output, zonedDateTime.toLocalTime());
            ZoneIdSerializer.write(output, zonedDateTime.getZone());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public ZonedDateTime read2(Kryo kryo, Input input, Class cls) {
            return ZonedDateTime.of(LocalDateSerializer.read(input), LocalTimeSerializer.read(input), ZoneIdSerializer.read(input));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$YearSerializer.class */
    public static class YearSerializer extends ImmutableSerializer<Year> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Year year) {
            output.writeVarInt(year.getValue(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Year read2(Kryo kryo, Input input, Class cls) {
            return Year.of(input.readInt(true));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$YearMonthSerializer.class */
    public static class YearMonthSerializer extends ImmutableSerializer<YearMonth> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, YearMonth yearMonth) {
            output.writeVarInt(yearMonth.getYear(), true);
            output.writeByte(yearMonth.getMonthValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public YearMonth read2(Kryo kryo, Input input, Class cls) {
            return YearMonth.of(input.readInt(true), input.readByte());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$MonthDaySerializer.class */
    public static class MonthDaySerializer extends ImmutableSerializer<MonthDay> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, MonthDay monthDay) {
            output.writeByte(monthDay.getMonthValue());
            output.writeByte(monthDay.getDayOfMonth());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public MonthDay read2(Kryo kryo, Input input, Class cls) {
            return MonthDay.of(input.readByte(), input.readByte());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TimeSerializers$PeriodSerializer.class */
    public static class PeriodSerializer extends ImmutableSerializer<Period> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Period period) {
            output.writeVarInt(period.getYears(), true);
            output.writeVarInt(period.getMonths(), true);
            output.writeVarInt(period.getDays(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Period read2(Kryo kryo, Input input, Class cls) {
            return Period.of(input.readInt(true), input.readInt(true), input.readInt(true));
        }
    }
}

package main.java.com.keldorn;

import main.java.com.keldorn.util.Separator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        mathRandomProject();
        bigDecimal();
        dateTimeProject();
        moreTime();
    }

    private static void mathRandomProject() {
        Separator.separator();
        System.out.println(Math.abs(-50));
        System.out.println(Math.abs(Integer.MIN_VALUE));
//        System.out.println(Math.absExact(Integer.MIN_VALUE));
        System.out.println(Math.abs((long) Integer.MIN_VALUE));

        System.out.println("Max = " + Math.max(10, -10));
        System.out.println("Min = " + Math.max(10.0000002, -10.001));

        System.out.println("Round Down = " + Math.round(10.2));
        System.out.println("Round Up = " + Math.round(10.8));
        System.out.println("Round ? = " + Math.round(10.5));

        System.out.println("Floor = " + Math.floor(10.8));
        System.out.println("Ceil = " + Math.ceil(10.2));

        System.out.println("Square root of 100 = " + Math.sqrt(100));
        System.out.println("2 to the third power (2*2*2) = " + Math.pow(2, 3));

        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26) + 65);
        }

        Separator.separator();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt(65, 91));
        }

        Separator.separator();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d = %1$c%n", r.nextInt('A', 'Z' + 1));
        }

        Separator.separator();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%1$d%n", r.nextInt(-10, 11));
        }

        Separator.separator();
        r.ints()
                .limit(10)
                .forEach(System.out::println);

        Separator.separator();
        r.ints(0, 10)
                .limit(10)
                .forEach(System.out::println);

        Separator.separator();
        r.ints(10, 0, 10)
                .forEach(System.out::println);

        Separator.separator();
        r.ints(10)
                .forEach(System.out::println);

        long nanoTime = System.nanoTime();
        Random pseudoRandom = new Random(nanoTime);

        Separator.separator();
        pseudoRandom.ints(10, 0, 10)
                .forEach(i -> System.out.print(i + " "));

        Random notReallyRandom = new Random(nanoTime);

        Separator.separator(true);
        notReallyRandom.ints(10, 0, 10)
                .forEach(i -> System.out.print(i + " "));

    }

    private static void bigDecimal() {
        Separator.separator(true);
        double policyAmount = 100_000_000;
        int beneficiaries = 3;
        float percentageFloat = 1.0f / beneficiaries;
        double percentage = 1.0 / beneficiaries;

        System.out.printf("Payout = %,.2f%n", policyAmount * percentageFloat);
        System.out.printf("Payout = %,.2f%n", policyAmount * percentage);

        double totalUsingFloat = policyAmount -
                ((policyAmount * percentageFloat) * beneficiaries);
        System.out.printf("totalUsingFloat: %,.2f%n", totalUsingFloat);

        double total = policyAmount -
                ((policyAmount * percentage) * beneficiaries);
        System.out.printf("totalUsingFloat: %,.2f%n", total);

        String[] tests = {"15.456", "8", "10000.000001", ".123"};
        BigDecimal[] bds = new BigDecimal[tests.length];
        Arrays.setAll(bds, i -> new BigDecimal(tests[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for (var bd : bds) {
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        double[] doubles = {15.456, 8, 10000.000001, .123};
        Arrays.setAll(bds, i -> BigDecimal.valueOf(doubles[i]));
        Separator.separator();
        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for (var bd : bds) {
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-14s %-15d %-8d %d%n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        BigDecimal policyPayout = new BigDecimal("100000000.00"); // 100e6 gives the same output without the decimals
        policyPayout = policyPayout.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("%-14s %-15d %-8d %d%n", policyPayout,
                policyPayout.unscaledValue(), policyPayout.scale(), policyPayout.precision());

        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),
                new MathContext(60, RoundingMode.UP));
        System.out.println(percent);

        BigDecimal checkAmount = policyPayout.multiply(percent);
        System.out.printf("%.2f%n", checkAmount);
        checkAmount = checkAmount.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("%-14s %-15d %-8d %d%n", checkAmount,
                checkAmount.unscaledValue(), checkAmount.scale(), checkAmount.precision());

        BigDecimal totalChecksAmount = checkAmount.multiply(
                BigDecimal.valueOf(beneficiaries)
        );
        System.out.printf("Combined: %.2f%n", totalChecksAmount);
        System.out.println("Remaining = " + policyPayout.subtract(totalChecksAmount));

        System.out.printf("%-14s %-15d %-8d %d%n", totalChecksAmount,
                totalChecksAmount.unscaledValue(), totalChecksAmount.scale(), totalChecksAmount.precision());
    }

    private static void dateTimeProject() {
        Separator.separator();
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate Five5 = LocalDate.of(2022, 5, 5);
        System.out.println(Five5);

        LocalDate May5th = LocalDate.of(2022, Month.MAY, 5);
        System.out.println(May5th);

        LocalDate Day125 = LocalDate.ofYearDay(2022, 125);
        System.out.println(Day125);

        LocalDate May5 = LocalDate.parse("2022-05-05");
        System.out.println(May5);

        System.out.println(May5.getYear());
        System.out.println(May5.getMonth());

        System.out.println(May5.getMonthValue());

        System.out.println(May5.getDayOfMonth());
        System.out.println(May5.getDayOfWeek());
        System.out.println(May5.getDayOfYear());

        System.out.println(May5.get(ChronoField.YEAR));
        System.out.println(May5.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(May5.get(ChronoField.DAY_OF_MONTH));
        System.out.println(May5.get(ChronoField.DAY_OF_YEAR));

        System.out.println(May5.withYear(2000));
        System.out.println(May5.withMonth(3));
        System.out.println(May5.withDayOfMonth(4));
        System.out.println(May5.withDayOfYear(126));
        System.out.println(May5);
        System.out.println(May5.with(ChronoField.DAY_OF_YEAR, 126));

        System.out.println(May5.plusYears(1));
        System.out.println(May5.plusMonths(12));
        System.out.println(May5.plusDays(365));
        System.out.println(May5.plusWeeks(52));
        System.out.println(May5.plus(365, ChronoUnit.DAYS));

        System.out.println("May5 > today? " + May5.isAfter(today));
        System.out.println("today > May5? " + May5.isBefore(today));
        System.out.println("May5 > today? " + May5.compareTo(today));
        System.out.println("today > May5? " + today.compareTo(May5));

        System.out.println("Today = now? " + today.compareTo(LocalDate.now()));
        System.out.println("Today = now? " + today.equals(LocalDate.now()));

        System.out.println(today.isLeapYear());
        System.out.println(May5.minusYears(2).isLeapYear());

        Separator.separator();
        May5.datesUntil(May5.plusDays(7))
                .forEach(System.out::println);

        Separator.separator();
        May5.datesUntil(May5.plusYears(1), Period.ofDays(7))
                .forEach(System.out::println);

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime sevenAM = LocalTime.of(7, 0);
        System.out.println(sevenAM);

        LocalTime sevenThirty = LocalTime.of(7, 30, 15);
        System.out.println(sevenThirty);

        LocalTime sevenPM = LocalTime.parse("19:00");
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:15.1000");
        System.out.println(sevenPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));

        System.out.println(sevenThirtyPM.getHour());
        System.out.println(sevenThirtyPM.get(ChronoField.HOUR_OF_DAY));

        System.out.println(sevenThirtyPM.plus(24, ChronoUnit.HOURS));

        System.out.println(sevenPM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_DAY));

        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println(todayAndNow);

        LocalDateTime May5Noon = LocalDateTime.of(2022, 5, 5, 12, 0);
        System.out.printf("%tD %tr %n", May5Noon, May5Noon);
        System.out.printf("%1$tF %1$tT %n", May5Noon);

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(May5Noon.format(dtf));

        System.out.println(May5Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        LocalDateTime May6Noon = May5Noon.plusHours(24);
        System.out.println(May6Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

    }

    private static void moreTime() {
        Separator.separator();
        System.setProperty("user.timezone", "America/Los_Angeles");
        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of TZs = " + ZoneId.getAvailableZoneIds().size());
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(s -> s.startsWith("Europe"))
                .sorted()
                .map(ZoneId::of)
                .forEach(z -> System.out.println(z.getId() + ": " + z.getRules()));

        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
        Set<String> oldWay = new HashSet<>(Set.of(alternate));

        oldWay.removeAll(jdk8Zones);
        System.out.println(oldWay);
        ZoneId bet = ZoneId.of("BET", ZoneId.SHORT_IDS);
        System.out.println(bet);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        Instant instantNow = Instant.now();
        System.out.println(instantNow);

        for (ZoneId z : List.of(
                ZoneId.of("Australia/Sydney"),
                ZoneId.of("Europe/Paris"),
                ZoneId.of("America/New_York")
        )) {
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + instantNow.atZone(z).format(zoneFormat));
            System.out.println("\t" + z.getRules().getDaylightSavings(instantNow));
            System.out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }

        Instant dobInstant = Instant.parse("2020-01-01T08:01:00Z");
        LocalDateTime dob =
                LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, LA time = " + dob.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));

        ZonedDateTime dobSydney = ZonedDateTime.ofInstant(dobInstant,
                ZoneId.of("Australia/Sydney"));
        System.out.println("Your kid's birthdate, Sydney time = " + dobSydney.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));

        ZonedDateTime dobHere = dobSydney.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("Your kid's birthdate, Here time = " + dobHere.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));

        ZonedDateTime firstOfMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.printf("First of next Month = %tD%n", firstOfMonth);

        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        System.out.println(timePast);

        Duration timeSince =
                Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince);

        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4).plusDays(4)
                .plusHours(7).plusMinutes(14).plusSeconds(37);

        System.out.println("Your 2nd kid's birthdate, Here time = " + dob2.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        ));

        Period timePast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        System.out.println(timePast2);

        Duration timeSince2 =
                Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince2);

        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(LocalDate.EPOCH)) {
                long val = u.between(LocalDate.EPOCH, dob2.toLocalDate());
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }

        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);

        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(ldt)) {
                long val = u.between(ldt, dob2);
                System.out.println(u + " past = " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }
    }
}

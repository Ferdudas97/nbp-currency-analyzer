package pl.parser.nbp;

import lombok.val;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class NbpUtills {
    private static final List<String> daysOff = new LinkedList<>();

    static {
        daysOff.add("1-1");
        daysOff.add("1-6");
        daysOff.add("5-1");
        daysOff.add("5-3");
        daysOff.add("8-15");
        daysOff.add("11-1");
        daysOff.add("11-11");
        daysOff.add("12-25");
        daysOff.add("12-26");


    }

    public static boolean checkIfIsWorkingDay(final LocalDate date) {
        return date.getDayOfWeek().getValue() <= 5 && !daysOff.contains(getMonthAndDay(date));
    }

    public static String nnnFormat(final String count) {
        if (count.length() > 2) return count;
        if (count.length() > 1) return "0" + count;
        return "00" + count;
    }

    public static String getMonthAndDay(final LocalDate date) {
        return date.getMonthValue() + "-" + date.getDayOfMonth();
    }

    public static Double parseDoubleWithComma(final String number) {
        if (number.contains(",")) return Double.parseDouble(number.replaceAll(",", "."));
        else return Double.parseDouble(number);
    }

    public static Long getNumberOfWorkingDay(final LocalDate endDate) {
        val firstDayInYear = getFirstDayInYear(endDate);
        if (endDate.equals(firstDayInYear)) return 0L;
        return Stream
                .iterate(firstDayInYear, i -> i.plusDays(1))
                .takeWhile(date -> !date.equals(endDate))
                .filter(NbpUtills::checkIfIsWorkingDay)
                .count();

    }

    public static LocalDate getFirstDayInYear(final LocalDate date) {
        return LocalDate.parse(date.getYear() + "-01-01");
    }

    public static double computeStandardDeviation(final List<Double> numbers) {
        val average = numbers.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics()
                .getAverage();
        val count = numbers.size();
        val sum = numbers.stream()
                .mapToDouble(Double::doubleValue)
                .map(rate -> Math.pow(rate - average, 2)).sum();
        return Math.sqrt(sum / (count));

    }
}

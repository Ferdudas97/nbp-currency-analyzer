package pl.parser.nbp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.val;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NbpAnalyzer {
    private static final String URL = "http://www.nbp.pl/kursy/xml/";
    private static final XmlMapper mapper = new XmlMapper().setDefaultUseWrapper(false);


    //last date is excluded
    public static void computeStatistics(final LocalDate startDate, final LocalDate endDate, final String code) {
        val positions =getPostionsByDateAndCode(startDate, endDate, code);
        val avarageOfBidRate = positions.stream()
                .map(Position::getBid_rate)
                .mapToDouble(NbpUtills::parseDoubleWithComma)
                .summaryStatistics()
                .getAverage();

        val saleRates = positions.stream()
                .map(Position::getSale_rate)
                .mapToDouble(NbpUtills::parseDoubleWithComma)
                .boxed()
                .collect(Collectors.toList());

        val deviation = NbpUtills.computeStandardDeviation(saleRates);
        Stream.of(avarageOfBidRate, deviation)
                .forEach(value -> System.out.printf("%.4f \n", value));

    }


    private static List<Position> getPostionsByDateAndCode(final LocalDate startDate, final LocalDate endDate, final String code) {
        Long number = NbpUtills.getNumberOfWorkingDay(startDate);
        AtomicInteger ordinal = new AtomicInteger(number.intValue());

        return Stream.iterate(startDate, i -> i.plusDays(1))
                .takeWhile(date -> !date.equals(endDate))
                .peek(date -> {
                    if (date.equals(NbpUtills.getFirstDayInYear(date))) ordinal.set(0);
                })
                .filter(NbpUtills::checkIfIsWorkingDay)
                .map(date -> getTable(date, ordinal))
                .filter(Objects::nonNull)
                .map(Table::getPositions)
                .flatMap(Collection::stream)
                .filter(position -> position.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public static Table getTable(final LocalDate date, final AtomicInteger tableNumber) {


        tableNumber.getAndIncrement();

        val string = URL + "c" + NbpUtills.nnnFormat(tableNumber.toString()) + "z" + String.join("", date.toString().split("-")).substring(2) + ".xml";
        try {
            return mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT).readValue(new URL(string), Table.class);


        } catch (IOException e) {
            tableNumber.getAndDecrement();
            return getTableOneTimeMore(date, tableNumber.get());
        }

    }


    private static Table getTableOneTimeMore(final LocalDate date, Integer tableNumber) {


        val string = URL + "c" + NbpUtills.nnnFormat(tableNumber.toString()) + "z" + String.join("", date.toString().split("-")).substring(2) + ".xml";
        try {
            return mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT).readValue(new URL(string), Table.class);


        } catch (IOException e) {
        }
        return null;

    }
}

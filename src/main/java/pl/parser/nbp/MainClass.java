package pl.parser.nbp;

import lombok.val;

import java.io.IOException;
import java.time.LocalDate;

public class MainClass {


    public static void main(String[] args) throws IOException {
        String startDateString;
        String endDateString;
        String code;
        if (args.length<3) {
             startDateString = "2013-01-28";
             endDateString = "2013-01-31";
             code = "EUR";
        }
        else {
            code = args[0];
            startDateString = args[1];
            endDateString = args[2];
        }
        val startDate = LocalDate.parse(startDateString);
        val endDate = LocalDate.parse(endDateString);
        System.out.println("Wait a sec...");
        NbpAnalyzer.computeStatistics(startDate,endDate.plusDays(1),code);
    }



}

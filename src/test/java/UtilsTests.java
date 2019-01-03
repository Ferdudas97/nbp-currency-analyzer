import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.parser.nbp.NbpUtills;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.parser.nbp.NbpUtills.*;

public class UtilsTests {

    @Test
    public void converterTest() {
        val actual1 = parseDoubleWithComma("3,256");
        assertEquals(3.256, actual1.doubleValue());
        val actual2 = parseDoubleWithComma("5,1");
        assertEquals(5.1, actual2.doubleValue());
        val actual3 = parseDoubleWithComma("321");
        assertEquals(321, actual3.doubleValue());
        val actual4 = parseDoubleWithComma("-12,3");
        assertEquals(-12.3, actual4.doubleValue());

    }

    @Test
    public void nnnFormatterTest() {
        assertEquals("001", nnnFormat("1"));
        assertEquals("012", nnnFormat("12"));
        assertEquals("421", nnnFormat("421"));
    }

    @Test
    public void  isDayOffTest() {
        assertTrue(checkIfIsWorkingDay(LocalDate.parse("2019-01-03")));
        assertFalse(checkIfIsWorkingDay(LocalDate.parse("2014-11-01")));
        assertFalse(checkIfIsWorkingDay(LocalDate.parse("2019-01-06")));

    }

    @Test
    public void  getWorkingDaysTest() {
        assertEquals(0,getNumberOfWorkingDay(LocalDate.parse("2019-01-01")).intValue());
        assertEquals(140,getNumberOfWorkingDay(LocalDate.parse("2015-07-21")).intValue());
        assertEquals(255,getNumberOfWorkingDay(LocalDate.parse("2015-12-31")).intValue());
    }

    @Test
    public void  computeStandardDeviationTest() {

        List<Double> list = List.of(5.,-8.,10.,30.,12.);
        assertEquals(12.27029,computeStandardDeviation(list),0.1);
        List<Double> list2 = List.of(1.3,1.6,0.,123.,0.,1.5,19.,6.);
        assertEquals(39.73235,computeStandardDeviation(list2),0.1);

    }
}

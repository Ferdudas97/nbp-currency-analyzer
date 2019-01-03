import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.parser.nbp.NbpUtills;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static pl.parser.nbp.NbpAnalyzer.*;
public class NbpAnalyzerTest {

    @Test
    public void getTableTest() {
        val date1 = LocalDate.parse("2013-01-06");
        assertNull(getTable(date1, new AtomicInteger(NbpUtills.getNumberOfWorkingDay(date1).intValue())));
        val date2 = LocalDate.parse("2013-01-08");
        assertNotNull(getTable(date2, new AtomicInteger(NbpUtills.getNumberOfWorkingDay(date2).intValue())));
        val date3 = LocalDate.parse("2015-05-11");
        assertNotNull(getTable(date3, new AtomicInteger(NbpUtills.getNumberOfWorkingDay(date3).intValue())));
    }

}

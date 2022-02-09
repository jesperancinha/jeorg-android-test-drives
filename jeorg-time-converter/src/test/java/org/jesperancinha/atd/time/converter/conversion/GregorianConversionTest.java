package org.jesperancinha.atd.time.converter.conversion;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jesperancinha on 12-5-16.
 */
public class GregorianConversionTest {

    @Test
    public void testConvertGregorianCalendar() {
        final GregorianConversion gregorianConversion = new GregorianConversion();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("y M d m s H");
        GregorianCalendar result = gregorianConversion.convertGregorianCalendar("2016 05 1 01 01 01", formatter);
        assertThat(result.get(Calendar.YEAR)).isEqualTo(2016);
        assertThat(result.get(Calendar.MONTH)).isEqualTo(4);
        assertThat(result.get(Calendar.HOUR)).isEqualTo(1);
    }

}
package org.jesperancinha.atd.time.converter.conversion;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import java.util.GregorianCalendar;

/**
 * Created by jesperancinha on 12-5-16.
 */
public class GregorianConversion {
    public GregorianCalendar convertGregorianCalendar(String dateToParse, DateTimeFormatter formatter) {
final GregorianCalendar calDate = new GregorianCalendar();
calDate.setTime(DateTime.parse(dateToParse, formatter).toDate());
        return calDate;
    }
}

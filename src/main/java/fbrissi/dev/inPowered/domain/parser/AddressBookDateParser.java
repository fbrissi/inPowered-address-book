package fbrissi.dev.inPowered.domain.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

final public class AddressBookDateParser {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/")
            .appendValueReduced(java.time.temporal.ChronoField.YEAR, 2, 2, 1970)
            .toFormatter();

    private AddressBookDateParser() {
    }

    public static LocalDate parse(String value) {
        return LocalDate.parse(value, FORMATTER);
    }
}

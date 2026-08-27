package fbrissi.dev.inPowered.domain.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class AddressBookDateParserTests {

    @Test
    void parsesDateWithTwoDigitYear() {
        assertEquals(LocalDate.of(1977, 3, 16), AddressBookDateParser.parse("16/03/77"));
    }

    @Test
    void parsesYearAtStartOfCurrentCentury() {
        assertEquals(LocalDate.of(2000, 1, 1), AddressBookDateParser.parse("01/01/00"));
    }

    @Test
    void rejectsDateWithInvalidDay() {
        assertThrows(DateTimeParseException.class, () -> AddressBookDateParser.parse("32/01/77"));
    }

    @Test
    void rejectsDateWithInvalidFormat() {
        assertThrows(DateTimeParseException.class, () -> AddressBookDateParser.parse("1977-03-16"));
    }
}

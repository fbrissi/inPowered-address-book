package fbrissi.dev.inPowered.domain.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import fbrissi.dev.inPowered.domain.processor.AddressBookEntry;
import fbrissi.dev.inPowered.domain.processor.Gender;

import org.junit.jupiter.api.Test;

class AddressBookLineParserTests {

    @Test
    void parsesMaleEntry() {
        AddressBookEntry entry = AddressBookLineParser.parse("Bill McKnight, Male, 16/03/77");

        assertEquals(new AddressBookEntry("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16)), entry);
    }

    @Test
    void parsesFemaleEntryWithWhitespaceAroundSeparators() {
        AddressBookEntry entry = AddressBookLineParser.parse("  Gemma Lane  ,   Female   ,  20/11/91  ");

        assertEquals(new AddressBookEntry("Gemma Lane", Gender.FEMALE, LocalDate.of(1991, 11, 20)), entry);
    }

    @Test
    void rejectsLinesWithMissingColumns() {
        assertThrows(IllegalArgumentException.class,
                () -> AddressBookLineParser.parse("Bill McKnight, Male"));
    }

    @Test
    void rejectsLinesWithUnknownGender() {
        assertThrows(IllegalArgumentException.class,
                () -> AddressBookLineParser.parse("Bill McKnight, Unknown, 16/03/77"));
    }

    @Test
    void propagatesInvalidDateException() {
        assertThrows(DateTimeParseException.class,
                () -> AddressBookLineParser.parse("Bill McKnight, Male, 32/01/77"));
    }
}

package fbrissi.dev.inPowered.domain.parser;

import fbrissi.dev.inPowered.domain.processor.AddressBookEntry;
import fbrissi.dev.inPowered.domain.processor.Gender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AddressBookLineParser {

    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^\\s*(?<name>[^,]+?)\\s*,\\s*(?<gender>(Male|Female)+?)\\s*,\\s*"
                    + "(?<birthDate>\\d{2}/\\d{2}/\\d{2})\\s*$",
            Pattern.CASE_INSENSITIVE);

    private AddressBookLineParser() {
    }

    public static AddressBookEntry parse(String line) {
        Matcher matcher = LINE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid address book line: " + line);
        }

        return new AddressBookEntry(
                matcher.group("name").trim(),
                Gender.from(matcher.group("gender")),
                AddressBookDateParser.parse(matcher.group("birthDate")));
    }
}

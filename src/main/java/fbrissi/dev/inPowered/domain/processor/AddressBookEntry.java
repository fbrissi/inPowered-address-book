package fbrissi.dev.inPowered.domain.processor;

import java.time.LocalDate;

public record AddressBookEntry(String name, Gender gender, LocalDate birthDate) {
}

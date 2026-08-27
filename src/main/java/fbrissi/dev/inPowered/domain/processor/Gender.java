package fbrissi.dev.inPowered.domain.processor;

import java.util.Locale;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender from(String value) {
        return valueOf(value.trim().toUpperCase(Locale.ROOT));
    }
}

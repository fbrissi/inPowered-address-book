package fbrissi.dev.inPowered.domain.processor.chain;

import fbrissi.dev.inPowered.domain.parser.AddressBookLineParser;
import fbrissi.dev.inPowered.domain.processor.AbstractProcessorData;
import fbrissi.dev.inPowered.domain.processor.ProcessorData;

import java.time.LocalDate;

public class OldestDays extends AbstractProcessorData {

    private final String oldestName;

    private final String thenName;

    private LocalDate oldestDate;

    private LocalDate thenDate;

    public OldestDays(String oldestName, String thenName) {
        this(null, oldestName, thenName);
    }

    public OldestDays(ProcessorData next, String oldestName, String thenName) {
        super(next);
        this.oldestName = oldestName;
        this.thenName = thenName;
    }

    @Override
    protected void processCurrent(String data) {
        var entry = AddressBookLineParser.parse(data);
        String name = entry.name();
        LocalDate date = entry.birthDate();
        if (name.equalsIgnoreCase(oldestName)) {
            oldestDate = date;
        }
        if (name.equalsIgnoreCase(thenName)) {
            thenDate = date;
        }
    }

    @Override
    public String getAnswer() {
        if (oldestDate == null || thenDate == null) {
            return "";
        }
        return String.valueOf(java.time.temporal.ChronoUnit.DAYS.between(oldestDate, thenDate));
    }
}

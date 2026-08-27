package fbrissi.dev.inPowered.domain.processor.chain;

import fbrissi.dev.inPowered.domain.parser.AddressBookLineParser;
import fbrissi.dev.inPowered.domain.processor.AbstractProcessorData;
import fbrissi.dev.inPowered.domain.processor.ProcessorData;

import java.time.LocalDate;

public class OldestPerson extends AbstractProcessorData {

    private String name;

    private LocalDate birthDate;

    public OldestPerson(ProcessorData next) {
        super(next);
    }

    @Override
    protected void processCurrent(String data) {
        var entry = AddressBookLineParser.parse(data);
        LocalDate candidateDate = entry.birthDate();
        if (birthDate == null || candidateDate.isBefore(birthDate)) {
            name = entry.name();
            birthDate = candidateDate;
        }
    }

    @Override
    public String getAnswer() {
        return name == null ? "" : name;
    }
}

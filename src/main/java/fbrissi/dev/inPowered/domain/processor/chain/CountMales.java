package fbrissi.dev.inPowered.domain.processor.chain;

import fbrissi.dev.inPowered.domain.parser.AddressBookLineParser;
import fbrissi.dev.inPowered.domain.processor.AbstractProcessorData;
import fbrissi.dev.inPowered.domain.processor.Gender;
import fbrissi.dev.inPowered.domain.processor.ProcessorData;

public class CountMales extends AbstractProcessorData {

    private int count;

    public CountMales(ProcessorData next) {
        super(next);
    }

    @Override
    protected void processCurrent(String data) {
        if (AddressBookLineParser.parse(data).gender() == Gender.MALE) {
            count++;
        }
    }

    @Override
    public String getAnswer() {
        return String.valueOf(count);
    }
}

package fbrissi.dev.inPowered.domain.processor;

import fbrissi.dev.inPowered.domain.processor.chain.CountMales;
import fbrissi.dev.inPowered.domain.processor.chain.OldestDays;
import fbrissi.dev.inPowered.domain.processor.chain.OldestPerson;

final public class ProcessorDataFactory {

    private ProcessorDataFactory() {
    }

    public static ProcessorDataChain create(String oldestName, String thenName) {
        OldestDays oldestDays = new OldestDays(oldestName, thenName);
        OldestPerson oldestPerson = new OldestPerson(oldestDays);
        CountMales countMales = new CountMales(oldestPerson);

        return new ProcessorDataChain(countMales, countMales, oldestPerson, oldestDays);
    }
}

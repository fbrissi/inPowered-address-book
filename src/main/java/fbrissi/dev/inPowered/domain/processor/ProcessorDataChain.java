package fbrissi.dev.inPowered.domain.processor;

import fbrissi.dev.inPowered.domain.processor.chain.CountMales;
import fbrissi.dev.inPowered.domain.processor.chain.OldestDays;
import fbrissi.dev.inPowered.domain.processor.chain.OldestPerson;

public record ProcessorDataChain(
        ProcessorData first,
        CountMales countMales,
        OldestPerson oldestPerson,
        OldestDays oldestDays) {
}

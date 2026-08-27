package fbrissi.dev.inPowered.domain.processor;

public abstract class AbstractProcessorData implements ProcessorData {

    protected final ProcessorData next;

    protected AbstractProcessorData(ProcessorData next) {
        this.next = next;
    }

    @Override
    public final void process(String data) {
        processCurrent(data);
        if (next != null) {
            next.process(data);
        }
    }

    protected abstract void processCurrent(String data);
}

package fbrissi.dev.inPowered.domain.reader;

import fbrissi.dev.inPowered.domain.processor.ProcessorData;

import java.io.IOException;
import java.nio.file.Path;

public interface Reader {

    void read(Path path, ProcessorData chain) throws IOException;
}

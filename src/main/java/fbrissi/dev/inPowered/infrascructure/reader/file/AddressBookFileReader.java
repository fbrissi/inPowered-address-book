package fbrissi.dev.inPowered.infrascructure.reader.file;

import fbrissi.dev.inPowered.domain.reader.Reader;
import fbrissi.dev.inPowered.domain.processor.ProcessorData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class AddressBookFileReader implements Reader {

    @Override
    public void read(Path path, ProcessorData chain) throws IOException {
        try (var lines = Files.lines(path)) {
            lines.filter(line -> !line.isBlank()).forEach(chain::process);
        }
    }
}

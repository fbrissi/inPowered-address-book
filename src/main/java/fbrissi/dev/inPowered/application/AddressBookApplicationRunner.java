package fbrissi.dev.inPowered.application;

import fbrissi.dev.inPowered.domain.processor.ProcessorDataFactory;

import java.nio.file.Path;
import java.util.List;

import fbrissi.dev.inPowered.domain.reader.Reader;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "address-book.process", havingValue = "true", matchIfMissing = true)
public class AddressBookApplicationRunner implements ApplicationRunner {

    private static final String DEFAULT_FILE_PATH = "AddressBook.txt";

    private static final String DEFAULT_OLDEST_NAME = "Bill McKnight";

    private static final String DEFAULT_THEN_NAME = "Paul Robinson";

    private final Reader fileReader;

    public AddressBookApplicationRunner(Reader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void run(@NonNull ApplicationArguments arguments) throws Exception {
        Path filePath = Path.of(optionOrDefault(arguments, "file-path", DEFAULT_FILE_PATH));
        String oldestName = optionOrDefault(arguments, "oldest", DEFAULT_OLDEST_NAME);
        String thenName = optionOrDefault(arguments, "then", DEFAULT_THEN_NAME);

        var chain = ProcessorDataFactory.create(oldestName, thenName);

        fileReader.read(filePath, chain.first());

        System.out.println();
        System.out.println("Males: " + chain.countMales().getAnswer());
        System.out.println("Oldest person: " + chain.oldestPerson().getAnswer());
        System.out.println("Days older: " + chain.oldestDays().getAnswer());
    }

    private String optionOrDefault(ApplicationArguments arguments, String name, String defaultValue) {
        List<String> values = arguments.getOptionValues(name);
        if (values == null || values.isEmpty() || values.getFirst().isBlank()) {
            return defaultValue;
        }
        return values.getFirst();
    }
}

package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import fbrissi.dev.inPowered.application.AddressBookApplicationRunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(properties = "address-book.process=true")
class AddressBookApplicationOptionsTests {

    @Autowired
    private AddressBookApplicationRunner runner;

    @Test
    void processesCommandLineOptions(CapturedOutput output, @TempDir Path tempDirectory)
            throws Exception {
        Path file = tempDirectory.resolve("custom-address-book.txt");
        Files.writeString(file, "Alice Smith, Female, 01/01/80\nBob Smith, Male, 01/01/90\n");

        runner.run(new DefaultApplicationArguments(
                "--file-path=" + file,
                "--oldest=Alice Smith",
                "--then=Bob Smith"));

        assertTrue(output.getOut().contains("Males: 1"));
        assertTrue(output.getOut().contains("Oldest person: Alice Smith"));
        assertTrue(output.getOut().contains("Days older: 3653"));
    }
}

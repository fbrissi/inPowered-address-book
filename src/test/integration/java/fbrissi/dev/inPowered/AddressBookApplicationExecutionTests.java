package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(properties = "address-book.process=true")
class AddressBookApplicationExecutionTests {

    @Test
    void processesDefaultAddressBook(CapturedOutput output) {
        assertTrue(output.getOut().contains("Males: 3"));
        assertTrue(output.getOut().contains("Oldest person: Wes Jackson"));
        assertTrue(output.getOut().contains("Days older: 2862"));
    }
}

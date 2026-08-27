package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import fbrissi.dev.inPowered.application.AddressBookApplicationRunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "address-book.process=true")
class AddressBookApplicationFailureTests {

    @Autowired
    private AddressBookApplicationRunner runner;

    @Test
    void propagatesMissingFileFailure() {
        assertThrows(IOException.class, () -> runner.run(new DefaultApplicationArguments(
                "--file-path=file-that-does-not-exist.txt")));
    }
}

package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import fbrissi.dev.inPowered.domain.reader.Reader;
import fbrissi.dev.inPowered.infrascructure.reader.file.AddressBookFileReader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "address-book.process=false")
class AddressBookApplicationDependencyInjectionTests {

    @Autowired
    private Reader reader;

    @Test
    void injectsFileReader() {
        assertInstanceOf(AddressBookFileReader.class, reader);
    }
}

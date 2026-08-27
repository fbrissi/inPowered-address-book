package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import fbrissi.dev.inPowered.application.AddressBookApplicationRunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "address-book.process=true")
class AddressBookApplicationProcessEnabledTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void registersRunner() {
        assertNotNull(context.getBean(AddressBookApplicationRunner.class));
    }
}

package fbrissi.dev.inPowered;

import static org.junit.jupiter.api.Assertions.assertTrue;

import fbrissi.dev.inPowered.application.AddressBookApplicationRunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "address-book.process=false")
class AddressBookApplicationProcessDisabledTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void doesNotRegisterRunner() {
        assertTrue(context.getBeansOfType(AddressBookApplicationRunner.class).isEmpty());
    }
}

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MacOsTests {
    @Test
    void testName() throws Exception{
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Mac"));
        assertTrue(true);
    }
}

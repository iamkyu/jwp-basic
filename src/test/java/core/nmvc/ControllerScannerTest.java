package core.nmvc;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Kj Nam
 * @since 2016-11-21
 */
public class ControllerScannerTest {
    private static final Logger logger =
            LoggerFactory.getLogger(ControllerScannerTest.class);

    private ControllerScanner cf;

    @Before
    public void setUp() {
        cf = new ControllerScanner("core.nmvc");
    }

    @Test
    public void getControllers() {
        Map<Class<?>, Object> controllers = cf.getControllers();
        for (Class<?> controller : controllers.keySet()) {
            logger.debug("controllers: {}", controller);
        }
    }
}

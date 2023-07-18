import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class TestMain {
    @Test
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    @Disabled
    void mainTimeDuration() throws Exception {
        Main.main(null);
    }
}

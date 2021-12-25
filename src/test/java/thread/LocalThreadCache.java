package thread;

import net.minestom.server.thread.MinestomThread;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalThreadCache {

    @Test
    public void testLocalThreadCache() throws InterruptedException {
        AtomicBoolean result = new AtomicBoolean(false);
        var thread = new MinestomThread("name") {
            @Override
            public void run() {
                final int dummy = -1;

                var value = localCache(0, () -> 5);
                assertEquals(5, value);

                value = localCache(0, () -> dummy);
                assertEquals(5, value);

                value = localCache(1, () -> 7);
                assertEquals(7, value);
                assertEquals(5, localCache(0, () -> dummy));

                value = localCache(2, () -> 5);
                assertEquals(5, value);
                assertEquals(7, localCache(1, () -> dummy));
                assertEquals(5, localCache(0, () -> dummy));

                result.set(true);
            }
        };
        thread.start();
        thread.join(1500);
        assertTrue(result.get(), "Thread didn't start");
    }
}
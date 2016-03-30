import java.util.Queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Test;

public class SampleTest {

    Queue<byte[]> l = new CircularFifoQueue<>(150);
    static int mb = 1024 * 1024;
    static int GRAPH_FACTOR=3;

    @Test
    public void test() throws Exception {
        int i = 0;
        while (true) {
            byte[] b = new byte[1024 * 1024];
            l.add(b);
            i++;

            // Getting the runtime reference from system
            Runtime runtime = Runtime.getRuntime();

            long used = (runtime.totalMemory() - runtime.freeMemory()) / mb;

            // Print used memory / free memory / total available memory / Maximum available memory
            System.out.print(String.format("%03d used/free/total/max: %03d/%03d/%03d/%03d ", i, used,
                    runtime.freeMemory() / mb, runtime.totalMemory() / mb, runtime.maxMemory() / mb));

            for (int j = 0; j < used / GRAPH_FACTOR; j+=GRAPH_FACTOR) {
                System.out.print("#");
            }

            System.out.println();

            Thread.sleep(10);
        }
    }
}

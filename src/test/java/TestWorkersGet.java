import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestWorkersGet {
    @Test
    public  void TestGetWorkers() {
        Worker executeWorker  = new Worker(1,1,1,18,"Valera","Junior Developer");
        Worker testWorker = Worker.getWorker(1);
        assertTrue(testWorker.equals(executeWorker));
    }
}

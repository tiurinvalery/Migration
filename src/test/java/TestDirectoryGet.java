import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestDirectoryGet {
    @Test
    public void testDirectoryGet() {
        Directory executeDir = new Directory(1, 1, 1, "keepers");
        Directory testDir = Directory.getDirectory(1);
        assertTrue(executeDir.equals(testDir));
    }

}

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestOfficeGet {
    @Test
    public void testOfficeGet() {
        Office executeOffice = new Office(1,1,1);
        Office testOffice = Office.getOffice(1);
        assertTrue(executeOffice.equals(testOffice));
    }
}

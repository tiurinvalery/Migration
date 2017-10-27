import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TestTypeOfCompanyGet {
    @Test
    public void testTypeOfCompanyGet() {
        TypeOfCompany tofExecute = new TypeOfCompany(1,"outcource");
        TypeOfCompany tofTest = TypeOfCompany.getType(1);
        assertTrue(tofExecute.equals(tofTest));
    }
}

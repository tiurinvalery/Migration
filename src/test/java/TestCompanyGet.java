import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCompanyGet {
    @Test
    public void testGetCompany() {
        Company executeCompany = new Company(1,"epam",1);
        Company textCompany = Company.getCompany(1);
        assertTrue(executeCompany.equals(textCompany));
    }
}

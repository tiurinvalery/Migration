import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



public class TestCompanyInfoGet {
     final ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Before
    public void SetUpStream() {
        System.setOut(new PrintStream(out));
    }
    @Test
    public void testCompanyInfoGet() {
        String str = "Try connect to DB"+"\n"+"Successfully connection"+"\n"+"Connected"+"\n"+"Connection close"+"\n"+"companyId: "+"1"+" | companyName: "+"epam"+" | typeOfcompany: " + "1";
        Company.getInfoAboutCompany(1);
        Assert.assertEquals(str.trim(),out.toString().trim());
    }
    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

}

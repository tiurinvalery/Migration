import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCountryGet {
    @Test
    public void testCountryGet(){
        Country executeCountry = new Country(1, "Ukraine");
        Country testCountry = Country.getCountry(1);
        assertTrue(executeCountry.equals(testCountry));
    }
}

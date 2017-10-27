import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;

public class Country {
    private int countryId;
    private String countryName;
    private static List<Country> countryList = new ArrayList<>();

    public Country() {
        countryId = -1;
        countryName = "Unknown";
    }
    public Country(int Id, String name) {
        countryId = Id;
        countryName = name;
    }
    private static void countryAdd(Country c) {
        countryList.add(c);
    }
    private static void getCountiesFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM countries");
            while(rs.next()) {
                Country nCountry = new Country();
                nCountry.countryId  = rs.getInt(1);
                nCountry.countryName = rs.getString(2);
                if(!countryList.contains(nCountry)) {
                    countryAdd(nCountry);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting countries");
        } finally {
            Connect.closeConnection(con);
        }

    }
    public static Country getCountry(int ID) {
        getCountiesFromDB();
        for(Country c : countryList) {
            if(c.countryId == ID) {
                return c;
            }
            return null;
        }
        return null;
    }
    public static void getInfoAboutCountries(int ID) {
        getCountiesFromDB();
        for(Country c: countryList) {
            if(c.countryId == ID) {
                System.out.println("countryId: "+c.countryId+" | countryName: "+c.countryName);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (countryId != country.countryId) return false;
        return countryName != null ? countryName.equals(country.countryName) : country.countryName == null;
    }

    @Override
    public int hashCode() {
        int result = countryId;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }
}

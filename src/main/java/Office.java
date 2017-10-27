import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;
public class Office {
    private int companyId;
    private int officeId;
    private int countryId;
    private static List<Office> offices = new ArrayList<>();

    public Office() {
        companyId = -1;
        officeId = -1;
        countryId = -1;
    }
    public Office(int cId, int oId, int couId) {
        companyId = cId;
        officeId = oId;
        countryId = couId;
    }
    private static void addOffice(Office o) {
        offices.add(o);
    }
    private static void getOfficesFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM offices");
            while(rs.next()) {
                Office nOfiice = new Office();
                nOfiice.officeId = rs.getInt(2);
                nOfiice.countryId=rs.getInt(3);
                nOfiice.companyId=rs.getInt(1);
                if(!offices.contains(nOfiice)) {
                    addOffice(nOfiice);
                }

            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting offices");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static Office getOffice(int ID) {
        getOfficesFromDB();
        for(Office o : offices) {
            if(o.officeId == ID) {
                return o;
            }
        }
        return null;
    }
    public static void getInfoAboutOffice(int ID) {
        getOfficesFromDB();
        for(Office o: offices) {
            if(o.officeId == ID) {
                System.out.println("officeID: "+o.officeId+" | countryID: "+o.countryId+" | companyId: "+o.companyId);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Office office = (Office) o;

        if (companyId != office.companyId) return false;
        if (officeId != office.officeId) return false;
        return countryId == office.countryId;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + officeId;
        result = 31 * result + countryId;
        return result;
    }
}

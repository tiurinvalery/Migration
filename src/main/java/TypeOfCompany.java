import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;

public class TypeOfCompany {
    private int typeOfCompanyId;
    private String typeOfCompany;
    private static List<TypeOfCompany> typeOfCompanies = new ArrayList<>();

    public TypeOfCompany() {
        typeOfCompanyId = -1;
        typeOfCompany = "Unknown";
    }
    public TypeOfCompany(int id, String type) {
        typeOfCompanyId = id;
        typeOfCompany = type;
    }
    private static void addTypeOfCompany(TypeOfCompany toc) {
        typeOfCompanies.add(toc);
    }
    private static void getTypeOfCompaniesFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM typeofcompanies");
            while(rs.next()) {
                TypeOfCompany nType = new TypeOfCompany();
                nType.typeOfCompanyId = rs.getInt(1);
                nType.typeOfCompany = rs.getString(2);
                if(!typeOfCompanies.contains(nType)){
                    addTypeOfCompany(nType);
                }
            }
            rs.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting typeOfCompanies");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static TypeOfCompany getType(int ID) {
        getTypeOfCompaniesFromDB();
        for(TypeOfCompany toc : typeOfCompanies) {
            if(toc.typeOfCompanyId == ID) {
                return toc;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeOfCompany that = (TypeOfCompany) o;

        if (typeOfCompanyId != that.typeOfCompanyId) return false;
        return typeOfCompany != null ? typeOfCompany.equals(that.typeOfCompany) : that.typeOfCompany == null;
    }

    @Override
    public int hashCode() {
        int result = typeOfCompanyId;
        result = 31 * result + (typeOfCompany != null ? typeOfCompany.hashCode() : 0);
        return result;
    }
}

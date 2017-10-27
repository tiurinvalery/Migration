import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private int companyId;
    private String companyName;
    private int typeOfCompany;
    private static List<Company> companyList = new ArrayList<Company>();

    public Company() {
        companyId = -1;
        companyName = "Unknown";
        typeOfCompany = -1;
    }

    public Company(int id,String name, int type) {
        companyId = id;
        companyName = name;
        typeOfCompany = type;
    }

    private static void getCompaniesFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM company");
            while(rs.next()) {
                Company nCompany = new Company();
                nCompany.companyId=rs.getInt(1);
                nCompany.companyName=rs.getString(2);
                nCompany.typeOfCompany=rs.getInt(3);
                if(!companyList.contains(nCompany)){
                    addCompany(nCompany);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with company getting");
        } finally {
            Connect.closeConnection(con);
        }
    }
    private static void addCompany(Company c) {
        companyList.add(c);
    }

    public static Company getCompany(int ID) {
        getCompaniesFromDB();
        for(Company c : companyList) {
            if(c.companyId == ID) {
                return c;
            }
            return null;
        }
        return null;
    }

    public static void getInfoAboutCompany(int ID) {
        getCompaniesFromDB();
        for(Company c : companyList) {
            if(c.companyId == ID) {
                System.out.println("companyId: "+c.companyId+" | companyName: "+c.companyName+" | typeOfcompany: " + c.typeOfCompany);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyId != company.companyId) return false;
        if (typeOfCompany != company.typeOfCompany) return false;
        return companyName != null ? companyName.equals(company.companyName) : company.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + typeOfCompany;
        return result;
    }
}

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private int companyId;
    private int directoryId;
    private int officeId;
    private String directoryName;
    private static List<Directory> directoryList = new ArrayList<Directory>();

    public Directory() {
        companyId = -1;
        directoryId = -1;
        officeId = -1;
        directoryName = "Unknown";
    }
    public Directory( int cId, int dId, int oId, String dName) {
        companyId = cId;
        directoryId = dId;
        officeId = oId;
        directoryName = dName;
    }
    private static void addDirectory(Directory d) {
        directoryList.add(d);
    }
    private static void getDirectoriesFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM directories");
            while(rs.next()) {
                Directory nDir = new Directory();
                nDir.directoryId = rs.getInt(2);
                nDir.officeId = rs.getInt(4);
                nDir.companyId=rs.getInt(1);
                nDir.directoryName=rs.getString(3);
                if(!directoryList.contains(nDir)) {
                    addDirectory(nDir);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting directories");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static Directory getDirectory(int ID) {
        getDirectoriesFromDB();
        for(Directory d: directoryList) {
            if(d.directoryId == ID) {
                return  d;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Directory directory = (Directory) o;

        if (companyId != directory.companyId) return false;
        if (directoryId != directory.directoryId) return false;
        if (officeId != directory.officeId) return false;
        return directoryName != null ? directoryName.equals(directory.directoryName) : directory.directoryName == null;
    }

}

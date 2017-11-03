import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Worker {
    private int workerId;
    private int directoryId;
    private int companyId;
    private int workerAge;
    private String workerName;
    private String workerPosition;
    public static List<Worker> workers = new ArrayList<Worker>();

    public Worker() {
        workerId = -1;
        directoryId = -1;
        companyId = -1;
        workerAge = 0;
        workerName = "NoName";
        workerPosition = "Nobody";

    }

    public Worker(int wId, int dId, int cId, int wAge, String wName, String wPos) {
        workerId = wId;
        directoryId = dId;
        companyId = cId;
        workerAge = wAge;
        workerName = wName;
        workerPosition = wPos;
    }

    private static void getWorkersFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM workers ");
            while (rs.next()) {
                Worker nWorker = new Worker();
                nWorker.companyId = rs.getInt(1);
                nWorker.directoryId=rs.getInt(2);
                nWorker.workerId=rs.getInt(3);
                nWorker.workerAge=rs.getInt(4);
                nWorker.workerName=rs.getString(5);
                nWorker.workerPosition=rs.getString(6);
                if(!workers.contains(nWorker)) {
                    workerAddToList(nWorker);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Problem with getting");
            e.printStackTrace();
        } finally {
            Connect.closeConnection(con);
        }
    }

    private static void workerAddToList(Worker w) {
        workers.add(w);
    }

    public static Worker getWorker(int ID) {
        getWorkersFromDB();
        for (Worker w : workers) {
            if (w.workerId == ID) {
                return w;
            } else {
                return null;
            }

        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (workerId != worker.workerId) return false;
        if (directoryId != worker.directoryId) return false;
        if (companyId != worker.companyId) return false;
        if (workerAge != worker.workerAge) return false;
        if (workerName != null ? !workerName.equals(worker.workerName) : worker.workerName != null) return false;
        return workerPosition != null ? workerPosition.equals(worker.workerPosition) : worker.workerPosition == null;
    }
    
}

import org.junit.Test;

public class TestStaticInfo {
    @Test
    public void TestStInfo() {
        String realUrl=Connect.db_url;
        String realUserName=Connect.user;
        String realPass=Connect.password;

        assert("jdbc:postgresql://127.0.0.1:5432/lab3".equals(realUrl)&&"lab".equals(realUserName)&&"4444".equals(realPass));
    }
}

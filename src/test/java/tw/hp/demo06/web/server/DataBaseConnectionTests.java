package tw.hp.demo06.web.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;


//測試類必須：
// 1.在組件掃描的包下(默認的包及其子孫包)
// 2.只有添加了@SpringBootTest註解才會加載整個項目的運行環境（包括Spring、讀取配置等）
@SpringBootTest
public class DataBaseConnectionTests {

    // Spring Boot自動讀取application.properties的配置，並創建了數據源對象
    @Autowired
    DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        // 獲取MySQL的鏈接，此方法的調用會實質連接數據庫
        dataSource.getConnection();
        System.out.println("執行到此，表示application.properties連接數據庫參數正確");
    }

}

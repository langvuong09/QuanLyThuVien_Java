package QuanLyThuVien.DAO;

import MyCustom.MyDialog;
import com.mysql.jdbc.Driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnect {

    public static Connection conn = null;
    private String severName;
    private String dbName;
    private String userName;
    private String password;

    public MyConnect() {
        docFileText();

        String strConnect = "jdbc:mysql://" + severName + "/" + dbName;
        Properties pro = new Properties();
        pro.put("user", userName);
        pro.put("password", password);
        try {
            com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
            conn = driver.connect(strConnect, pro);
        } catch (SQLException ex) {
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }

    }

    private void docFileText() {
        // Xử lý đọc file để lấy ra 4 tham số
        severName = "localhost";
        dbName = "quanlythuvien";
        userName = "root";
        password = "";

        try {
            FileInputStream fis = new FileInputStream("MySQL.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            severName = br.readLine();
            dbName = br.readLine();
            userName = br.readLine();
            password = br.readLine();

            if (password == null) {
                password = "";
            }

        } catch (Exception e) {
        }
    }
}

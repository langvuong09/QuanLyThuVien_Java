package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.NhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhanVienDAO {

    public ArrayList<NhanVien> getListNhanVien(){
        try {
            String sql = "SELECT * FROM nhanvien";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<NhanVien> dsnv = new ArrayList<>();
            while (rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setChucVu(rs.getString(6));
                nv.setGmail(rs.getString(7));
                dsnv.add(nv);
            }
            return dsnv;
        }catch (SQLException e){
        }
        return null;
    }

    public NhanVien getNhanVien(int ma){
        NhanVien nv = null;
        try{
            String sql = "SELECT * FROM nhanvien WHERE MaNhanVien="+ma;
            Statement st =MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setChucVu(rs.getString(6));
                nv.setGmail(rs.getString(7));
            }
        }catch (SQLException e){
            return null;
        }
        return nv;
    }

    public boolean themNhanVien(NhanVien nv){
        try{
            String sqlCheck = "SET FOREIGN_KEY_CHECKS=0";
            Statement stCheck = MyConnect.conn.createStatement();
            stCheck.execute(sqlCheck);

            String sql = "INSERT INTO nhanvien VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,nv.getMaNhanVien());
            pre.setString(2,nv.getHo());
            pre.setString(3,nv.getTen());
            pre.setString(4,nv.getGioiTinh());
            pre.setString(5,nv.getSDT());
            pre.setString(6,nv.getChucVu());
            pre.setString(7,nv.getGmail());
            int rowsAffected = pre.executeUpdate();

            String sqlChecks = "SET FOREIGN_KEY_CHECKS=1";
            Statement stChecks = MyConnect.conn.createStatement();
            stChecks.execute(sqlChecks);
            return rowsAffected > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaNhanVien(int ma){
        try{
            String sql = "DELETE FROM nhanvien WHERE MaNhanVien="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaNhanVien(int ma, NhanVien nv){
        try{
            String sql = "UPDATE nhanvien SET Ho=?, Ten=?, GioiTinh=?, SDT=?, ChucVu=?, Gmail=? WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,nv.getHo());
            pre.setString(2,nv.getTen());
            pre.setString(3,nv.getGioiTinh());
            pre.setString(4,nv.getSDT());
            pre.setString(5,nv.getChucVu());
            pre.setString(6,nv.getGmail());
            pre.setInt(7,ma);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean nhapNhanVienTuExcel(NhanVien nv) {
        try {
            String sql = "DELETE * FROM nhanvien; " +
                    "INSERT INTO nhanvien(MaNhanVien, Ho, Ten, GioiTinh, SDT, ChucVu, Gmail) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nv.getMaNhanVien());
            pre.setString(2, nv.getHo());
            pre.setString(3, nv.getTen());
            pre.setString(4, nv.getGioiTinh());
            pre.setString(5, nv.getSDT());
            pre.setString(6,nv.getChucVu());
            pre.setString(7, nv.getGmail());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}

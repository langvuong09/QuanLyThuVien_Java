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
            String sql = "SELECT * FROM nhanvien WHERE Quyen=1";
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
                nv.setGmail(rs.getString(6));
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
            String sql = "SELECT * FROM nhanvien WHERE Quyen=1 AND MaNhanVien="+ma;
            Statement st =MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioiTinh(rs.getString(4));
                nv.setSDT(rs.getString(5));
                nv.setGmail(rs.getString(6));
            }
        }catch (SQLException e){
            return null;
        }
        return nv;
    }

    public boolean themNhanVien(NhanVien nv){
        try{
            String sql = "INSERT INTO nhanvien VALUES(?,?,?,?,?,?,1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,nv.getMaNhanVien());
            pre.setString(2,nv.getHo());
            pre.setString(3,nv.getTen());
            pre.setString(4,nv.getGioiTinh());
            pre.setString(5,nv.getSDT());
            pre.setString(6,nv.getGmail());
            return pre.executeUpdate() > 0;
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
            String sql = "UPDATE nhanvien SET Ho=?, Ten=?, GioiTinh=?, SDT=?, Gmail=? WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,nv.getHo());
            pre.setString(2,nv.getTen());
            pre.setString(3,nv.getGioiTinh());
            pre.setString(4,nv.getSDT());
            pre.setString(5,nv.getGmail());
            pre.setInt(6,ma);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean nhapNhanVienTuExcel(NhanVien nv) {
        try {
            String sql = "DELETE * FROM nhanvien; " +
                    "INSERT INTO nhanvien(MaNhanVien, Ho, Ten, GioiTinh, SDT, Gmail) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
//            // Chuyển java.util.Date thành java.sql.Date
//            java.sql.Date ngayMuon = new java.sql.Date(pm.getNgayMuon().getTime());
//            java.sql.Date ngayTra = new java.sql.Date(pm.getNgayTra().getTime());

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, nv.getMaNhanVien());
            pre.setString(2, nv.getHo());
            pre.setString(3, nv.getTen());
            pre.setString(4, nv.getGioiTinh());
            pre.setString(5, nv.getGmail());
            pre.setString(6, nv.getSDT());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
}

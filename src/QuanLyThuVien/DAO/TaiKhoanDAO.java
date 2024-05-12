package QuanLyThuVien.DAO;

import QuanLyThuVien.BUS.DangNhapBUS;
import QuanLyThuVien.DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

public class TaiKhoanDAO {
    public ArrayList<TaiKhoan> getListTaiKhoan(){
        ArrayList<TaiKhoan> dstk = new ArrayList<>();
        try {
            String sql = "SELECT * FROM taikhoan";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaNhanVien(rs.getInt(1));
                tk.setTenDangNhap(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setQuyen(rs.getString(4));
                tk.setTrangThai(rs.getInt(5));
                dstk.add(tk);
            }
        }catch (SQLException e){
        }
        return dstk;
    }

    public TaiKhoan getTaiKhoan(String username, String password){
        TaiKhoan tk = null;
        try{
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap="+username+" AND MatKhau="+password;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                tk = new TaiKhoan();
                tk.setMaNhanVien(rs.getInt(1));
                tk.setTenDangNhap(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setQuyen(rs.getString(4));
            }
        }catch (Exception e){
            return null;
        }
        return tk;
    }

    public TaiKhoan getTaiKhoan(int maNV){
        TaiKhoan tk = null;
        try{
            String sql = "SELECT * FROM taikhoan WHERE MaNhanVien="+maNV;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                tk = new TaiKhoan();
                tk.setMaNhanVien(rs.getInt(1));
                tk.setTenDangNhap(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setQuyen(rs.getString(4));
            }
        }catch (Exception e){
            return null;
        }
        return tk;
    }

    public boolean themTaiKhoan(int maNhanVien,String tenDangNhap, String quyen){
        try{
            String sql = "INSERT INTO taikhoan(MaNhanVien, TenDangNhap, MatKhau, Quyen, TrangThai)" + "VALUES (?,?,?,?,1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maNhanVien);
            pre.setString(2,tenDangNhap);
            pre.setString(3,tenDangNhap);
            pre.setString(4,quyen);
            return pre.executeUpdate() > 0;
        }catch (Exception e){
        }
        return false;
    }

    public String getTenDangNhapTheoMa(int maNhanVien){
        try {
            String sql = "SELECT TenDangNhap FROM taikhoan WHERE MaNhanVien=" + maNhanVien;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch(Exception e){
        }
        return "";
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap){
        try{
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap='"+tenDangNhap+"'";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        }catch (Exception e){
        }
        return false;
    }

    public boolean datLaiMatKhau(int maNhanVien, String tenDangNhap){
        try{
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,tenDangNhap);
            pre.setInt(2,maNhanVien);
            return pre.executeUpdate() > 0;
        }catch (Exception e){
        }
        return false;
    }

    public boolean datLaiQuyen(String quyen,int maNhanVien){
        try{
            String sql = "UPDATE TaiKhoan SET Quyen=? WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,quyen);
            pre.setInt(2,maNhanVien);
            return pre.executeUpdate() > 0;
        }catch (Exception e){
        }
        return false;
    }

    public String getQuyenTheoMa(int maNhanVien){
        try{
            String sql = "SELECT Quyen FROM TaiKhoan WHERE MaNhanVien="+maNhanVien;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (Exception e){
        }
        return "";
    }

    public boolean khoaTaiKhoan(int maNhanVien){
        try{
            String sql = "UPDATE TaiKhoan SET TrangThai=0 WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maNhanVien);
            return pre.executeUpdate() > 0;
        }catch (Exception e){
        }
        return false;
    }

    public boolean moKhoaTaiKhoan(int maNhanVien){
        try{
            String sql = "UPDATE TaiKhoan SET TrangThai=1 WHERE MaNhanVien=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maNhanVien);
            return pre.executeUpdate()>0;
        }catch (Exception e){
        }
        return false;
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi){
        try{
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE MaNhanVien=? AND MatKhau=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,matKhauMoi);
            pre.setInt(2,DangNhapBUS.taiKhoanLogin.getMaNhanVien());
            pre.setString(3,matKhauCu);
            return pre.executeUpdate()>0;
        }catch (Exception e){
        }
        return false;
    }

    public int getTrangThai(int maNhanVien){
        try{
            String sql = "SELECT Trangthai FROM TaiKhoan WHERE MaNhanVien="+maNhanVien;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
        }
        return -1;
    }
}

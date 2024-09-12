package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.PhieuPhat;
import QuanLyThuVien.DTO.PhieuTra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuPhatDAO {
    public ArrayList<PhieuPhat> getListPhieuPhat(){
        try{
            String sql = "SELECT * FROM phieuphat";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhieuPhat> dspp = new ArrayList<>();
            while (rs.next()){
                PhieuPhat pp = new PhieuPhat();
                pp.setMaPhieuPhat(rs.getInt(1));
                pp.setMaPhieuTra(rs.getInt(2));
                pp.setMaSach(rs.getInt(3));
                pp.setMaPhanSach(rs.getInt(4));
                pp.setMaDocGia(rs.getInt(5));
                pp.setMaNhanVien(rs.getInt(6));
                pp.setLyDo(rs.getString(7));
                pp.setThanhTien(rs.getLong(8));
                dspp.add(pp);
            }
            return dspp;
        }catch (SQLException e){
        }
        return null;
    }

    public PhieuPhat getPhieuPhat(int ma){
        PhieuPhat pp = null;
        try{
            String sql = "SELECT * FROM phieuphat WHERE MaPhieuPhat="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                pp = new PhieuPhat();
                pp.setMaPhieuTra(rs.getInt(1));
                pp.setMaSach(rs.getInt(2));
                pp.setMaSach(rs.getInt(3));
                pp.setMaDocGia(rs.getInt(4));
                pp.setMaNhanVien(rs.getInt(5));
                pp.setLyDo(rs.getString(6));
                pp.setThanhTien(rs.getLong(7));
            }
            return pp;
        }catch (SQLException e){
        }
        return null;
    }

    public int getMaPhieuPhatMoiNhat(){
        try{
            String sql = "SELECT MAX(MaPhieuPhat) FROM phieuphat";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
        }
        return -1;
    }

    public boolean themPhieuPhat(PhieuPhat pp){
        try {
            String sql = "INSERT INTO phieuphat VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, pp.getMaPhieuPhat());
            pre.setInt(2, pp.getMaPhieuTra());
            pre.setInt(3, pp.getMaSach());
            pre.setInt(4, pp.getMaSach());
            pre.setInt(5, pp.getMaDocGia());
            pre.setInt(6, pp.getMaNhanVien());
            pre.setString(7, pp.getLyDo());
            pre.setLong(8, pp.getThanhTien());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaPhieuPhat(int ma){
        try{
            String sqlCheck = "SET FOREIGN_KEY_CHECKS=0";
            Statement stCheck = MyConnect.conn.createStatement();
            stCheck.execute(sqlCheck);

            String sql = "DELETE FROM phieuphat WHERE MaPhieuPhat="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);

            String sqlChecks = "SET FOREIGN_KEY_CHECKS=1";
            Statement stChecks =  MyConnect.conn.createStatement();
            stChecks.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaPhieuPhat(PhieuPhat pp){
        try{
            String sql = "UPDATE phieuphat SET MaPhieuTra=?, MaSach=?, MaPhanSach=?, MaDocGia=?, MaNhanVien=?, LyDo=?, ThanhTien=? WHERE MaPhieuPhat=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,pp.getMaPhieuTra());
            pre.setInt(2,pp.getMaSach());
            pre.setInt(3,pp.getMaSach());
            pre.setInt(4,pp.getMaDocGia());
            pre.setInt(5,pp.getMaNhanVien());
            pre.setString(6,pp.getLyDo());
            pre.setLong(7,pp.getThanhTien());
            pre.setInt(8,pp.getMaPhieuPhat());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean nhapPhieuPhatTuExcel(PhieuPhat pp){
        try{
            String sql = "DELETE * FROM phieuphat; "+
                    "INSERT INTO phieuphat(MaPhieuPhat, MaPhieuTra, MaSach, MaPhanSach, MaDocGia, MaNhanVien, LyDo, ThanhTien)"+
                    "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,pp.getMaPhieuPhat());
            pre.setInt(2,pp.getMaPhieuTra());
            pre.setInt(3,pp.getMaSach());
            pre.setInt(4,pp.getMaSach());
            pre.setInt(5,pp.getMaDocGia());
            pre.setInt(6,pp.getMaNhanVien());
            pre.setString(7,pp.getLyDo());
            pre.setLong(8,pp.getThanhTien());

            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}

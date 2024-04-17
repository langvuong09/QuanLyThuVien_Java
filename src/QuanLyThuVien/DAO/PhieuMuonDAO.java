package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.PhanQuyen;
import QuanLyThuVien.DTO.PhieuMuon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class PhieuMuonDAO {
    public ArrayList<PhieuMuon> getListPhieuMuon(){
        try{
            String sql = "SELECT * FROM phieumuon";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhieuMuon> dspm = new ArrayList<>();
            while (rs.next()){
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieuMuon(rs.getInt(1));
                pm.setMaDocGia(rs.getInt(2));
                pm.setMaNhanVien(rs.getInt(3));
                pm.setNgayMuon(rs.getDate(4));
                pm.setNgayTra((rs.getDate(5)));
                pm.setTongTien(rs.getLong(6));

                dspm.add(pm);
            }
            return dspm;
        }catch (SQLException e){
        }
        return null;
    }

    public int getMaPhieuMuonMoiNhat() {
        try {
            String sql = "SELECT MAX(MaPhieuMuon) FROM phieumuon";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public PhieuMuon getPhieuMuon(int ma){
        try{
            String sql ="SELECT * FROM phieumuon WHERE MaPhieuMuon=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ma);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                PhieuMuon pm = new PhieuMuon();
                pm.setMaPhieuMuon(rs.getInt(1));
                pm.setMaDocGia(rs.getInt(2));
                pm.setMaNhanVien(rs.getInt(3));
                pm.setNgayMuon(rs.getDate(4));
                pm.setNgayTra(rs.getDate(5));
                pm.setTongTien(rs.getLong(6));

                return pm;
            }
        }catch (SQLException e){
        }
        return null;
    }

    public boolean nhapPhieuMuonTuExcel(PhieuMuon pm) {
        try {
            String sql = "DELETE * FROM phieumuon; " +
                    "INSERT INTO phieumuon(MaDocGia, MaNhanVien, NgayMuon, NgayTra, TongTien) "
                    + "VALUES (?, ?, ?, ?, ?)";
            // Chuyển java.util.Date thành java.sql.Date
            java.sql.Date ngayMuon = new java.sql.Date(pm.getNgayMuon().getTime());
            java.sql.Date ngayTra = new java.sql.Date(pm.getNgayTra().getTime());

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, pm.getMaDocGia());
            pre.setInt(2, pm.getMaNhanVien());
            pre.setDate(3, ngayMuon);
            pre.setDate(4, ngayTra);
            pre.setLong(5, pm.getTongTien());

            pre.execute();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean themPhieuMuon(PhieuMuon pm){
        try{
            String sql = "INSERT INTO phieumuon(MaPhieuMuon,MaDocGia,MaNhanVien,NgayMuon,NgayTra,TongTienMuon)" +
                    "VALUES (?,?,?,?,?,?)";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Chuyển java.util.Date thành java.sql.Date
            java.sql.Date ngayMuon = new java.sql.Date(pm.getNgayMuon().getTime());
            java.sql.Date ngayTra = new java.sql.Date(pm.getNgayTra().getTime());

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,pm.getMaPhieuMuon());
            pre.setInt(2,pm.getMaDocGia());
            pre.setInt(3,pm.getMaNhanVien());
            pre.setDate(4,ngayMuon);
            pre.setDate(5,ngayTra);
            pre.setLong(6,pm.getTongTien());

            pre.execute();
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaPhieuMuon(int maPhieuMuon){
        try{
            // Tắt tính năng kiểm tra khóa ngoại tạm thời
            String sqlDisableFKCheck = "SET FOREIGN_KEY_CHECKS=0";
            Statement stDisableFKCheck = MyConnect.conn.createStatement();
            stDisableFKCheck.execute(sqlDisableFKCheck);

            String sql = "DELETE FROM phieumuon WHERE MaPhieuMuon="+maPhieuMuon;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);

            // Bật lại tính năng kiểm tra khóa ngoại
            String sqlEnableFKCheck = "SET FOREIGN_KEY_CHECKS=1";
            Statement stEnableFKCheck = MyConnect.conn.createStatement();
            stEnableFKCheck.execute(sqlEnableFKCheck);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaPhieuMuon(PhieuMuon pm){
        try{
            String sql = "UPDATE phieumuon SET MaDocGia=?, MaNhanVien=?, NgayMuon=?, NgayTra=?, TongTienMuon=?" +
                    "WHERE MaPhieuMuon=?";
            // Chuyển java.util.Date thành java.sql.Date
            java.sql.Date ngayMuon = new java.sql.Date(pm.getNgayMuon().getTime());
            java.sql.Date ngayTra = new java.sql.Date(pm.getNgayTra().getTime());

            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,pm.getMaDocGia());
            pre.setInt(2,pm.getMaNhanVien());
            pre.setDate(3,ngayMuon);
            pre.setDate(4,ngayTra);
            pre.setLong(5,pm.getTongTien());
            pre.setInt(6,pm.getMaPhieuMuon());

            pre.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

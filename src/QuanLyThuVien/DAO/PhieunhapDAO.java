package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.PhieuNhap;

import java.sql.*;
import java.util.ArrayList;

public class PhieunhapDAO {
    public ArrayList<PhieuNhap> getListPhieuNhap(){
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try{
            String sql = "SELECT * FROM phieunhap";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPhieuNhap(rs.getInt(1));
                pn.setMaNXB(rs.getInt(2));
                pn.setMaNhanVien(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getLong(5));
                dspn.add(pn);
            }
            return dspn;
        }catch (SQLException e){
        }
        return null;
    }

    public PhieuNhap getPhieuNhap(int maPN){
        try{
            String sql = "SELECT * FROM phieunhap WHERE MaPhieuNhap="+maPN;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            PhieuNhap pn = new PhieuNhap();
            while (rs.next()){
                pn.setMaPhieuNhap(rs.getInt(1));
                pn.setMaNXB(rs.getInt(2));
                pn.setMaNhanVien(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getLong(5));
                return pn;
            }
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themPhieuNhap(PhieuNhap pn){
        try{
            String sql = "INSERT INTO phieunhap(MaPhieuNhap,MaNXB,MaNhanVien,NgayLap,TongTien) VALUES(?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);

            pre.setInt(1,pn.getMaPhieuNhap());
            pre.setInt(2,pn.getMaNXB());
            pre.setInt(3,pn.getMaNhanVien());
            pre.setTimestamp(4,new java.sql.Timestamp(new java.util.Date().getTime()));
            pre.setLong(5,pn.getTongTien());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaPhieuNhap(int maPN){
        try {
            String sql = "DELETE FROM phieunhap WHERE MaPhieuNhap=" + maPN;
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaPhieuNhap(int maPN,PhieuNhap pn){
        try{
            String sql = "UPDATE phieunhap SET MaNXB=?, MaNhanVien=?, SoLuong=?, TongTien=? WHERE MaPhieuNhap=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);

            java.sql.Date ngayLap = new java.sql.Date(pn.getNgayLap().getTime());

            pre.setInt(1,pn.getMaNXB());
            pre.setInt(2,pn.getMaNhanVien());
            pre.setDate(3,ngayLap);
            pre.setLong(4,pn.getTongTien());
            pre.setInt(5,maPN);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public int getMaPhieuNhapLonNhat() {
        try {
            String sql = "SELECT MAX(MaPhieuNhap) FROM phieunhap";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

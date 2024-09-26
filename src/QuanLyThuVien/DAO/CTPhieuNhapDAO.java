package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.CTPhieuNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CTPhieuNhapDAO {
    public ArrayList<CTPhieuNhap> getListPhieuNhap() {
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieunhap";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPhieuNhap(rs.getInt(1));
                ctpn.setMaSach(rs.getInt(2));
                ctpn.setMaMin(rs.getInt(3));
                ctpn.setMaMax(rs.getInt(4));
                ctpn.setGia(rs.getLong(5));
                ctpn.setSoLuong(rs.getInt(6));
                ctpn.setThanhTien(rs.getLong(7));
                dsctpn.add(ctpn);
            }
            return dsctpn;
        }catch (SQLException e){
        }
        return null;
    }

    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMa(int maPN){
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieunhap WHERE MaPhieuNhap="+maPN;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuNhap ctpn = new CTPhieuNhap();
                ctpn.setMaPhieuNhap(rs.getInt(1));
                ctpn.setMaSach(rs.getInt(2));
                ctpn.setMaMin(rs.getInt(3));
                ctpn.setMaMax(rs.getInt(4));
                ctpn.setGia(rs.getLong(5));
                ctpn.setSoLuong(rs.getInt(6));
                ctpn.setThanhTien(rs.getLong(7));
                dsctpn.add(ctpn);
            }
            return dsctpn;
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themCTPhieuNhap(CTPhieuNhap ctpn){
        try{
            String sqlSach = "UPDATE sach SET SoLuong = SoLuong + ? WHERE MaSach=?";
            PreparedStatement preS = MyConnect.conn.prepareStatement(sqlSach);
            preS.setInt(1,ctpn.getSoLuong());
            preS.setInt(2,ctpn.getMaSach());
            preS.executeUpdate();

            String sql = "INSERT INTO ctphieunhap VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpn.getMaPhieuNhap());
            pre.setInt(2,ctpn.getMaSach());
            pre.setInt(3,ctpn.getMaSach());
            pre.setInt(4,ctpn.getMaSach());
            pre.setLong(5,ctpn.getGia());
            pre.setInt(6,ctpn.getSoLuong());
            pre.setLong(7,ctpn.getThanhTien());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaCTPhieuNhap(int maPN) {
        try {
            String sql = "DELETE FROM ctphieunhap WHERE MaPhieuNhap=" + maPN;
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
        }
        return false;
    }

    public boolean suaCTPhieuNhap(int maPN,CTPhieuNhap ctpn){
        try{
            String sql = "UPDATE ctphieunhap SET MaSach=?,MaMin=?, MaMax=?, SoLuong=?, Gia=?, ThanhTien=? WHERE MaPhieuNhap=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpn.getMaSach());
            pre.setInt(2,ctpn.getMaMin());
            pre.setInt(3,ctpn.getMaMax());
            pre.setLong(4,ctpn.getGia());
            pre.setInt(5,ctpn.getSoLuong());
            pre.setLong(6,ctpn.getThanhTien());
            pre.setInt(7,ctpn.getMaPhieuNhap());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}

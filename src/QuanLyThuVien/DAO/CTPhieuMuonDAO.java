package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DTO.PhieuMuon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CTPhieuMuonDAO {

    public ArrayList<CTPhieuMuon> getListCTPhieuMuon(){
        ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieumuon";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setMaPhieuMMuon(rs.getInt(1));
                ctpm.setMaSach(rs.getInt(2));
                ctpm.setGiaTien(rs.getLong(3));
                dsctpm.add(ctpm);
            }
        }catch (Exception e){
        }
        return  dsctpm;
    }

    public ArrayList<CTPhieuMuon> getListCTPhieuMuonTheoMaPM(int maPhieuMuon){
        ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
        try{
            String sql = "SELECT * FFROM ctphieumuon WHERE MaPhieuMuon="+maPhieuMuon;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setMaPhieuMMuon(rs.getInt(1));
                ctpm.setMaSach(rs.getInt(1));
                ctpm.setGiaTien(rs.getLong(3));
                dsctpm.add(ctpm);
            }
        }catch(SQLException e){
        }
        return  dsctpm;
    }

    public ArrayList<CTPhieuMuon> getListCTPhieuMuonTheomaSach(int maSach){
        ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieumuon WHERE MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setMaPhieuMMuon(rs.getInt(1));
                ctpm.setMaSach(rs.getInt(2));
                ctpm.setGiaTien(rs.getLong(3));
                dsctpm.add(ctpm);
            }
        }catch (SQLException e){
        }
        return dsctpm;
    }

    public boolean themCTPhieuMuon(CTPhieuMuon ctpm){
        boolean result = false;
        try{
            String sql = "INSERT INTO ctphieumuon VALUES(?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpm.getMaPhieuMMuon());
            pre.setInt(2,ctpm.getMaSach());
            pre.setLong(3,ctpm.getGiaTien());
            result = pre.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return  result;
    }

    public boolean xoaCTPhieuMuon(int maPhieuMuon, int maSach){
        boolean result = false;
        try{
            String sql = "DELETE FROM tphieumuon WHERE MaPhieuMuon="+maPhieuMuon+" AND MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            result = st.executeUpdate(sql) > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean xoaCTPhieuMuon(int maPhieuMuon){
        boolean result = false;
        try{
            String sql = "DELETE FROM tphieumuon WHERE MaPhieuMuon="+maPhieuMuon;
            Statement st = MyConnect.conn.createStatement();
            result = st.executeUpdate(sql) > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return result;
    }

    public boolean suaCTPhieuMuon(int maPhieuMuon, int maSach, CTPhieuMuon ctpm){
        boolean result = false;
        try{
            String sql = "UPDATE ctphieumuon SET MaPhieuMuon=?, MaSach=?, ThanhTien=? " +
                    "WHERE MaPhieuMuon="+maPhieuMuon+" MaSach="+maSach;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpm.getMaPhieuMMuon());
            pre.setInt(2,ctpm.getMaSach());
            pre.setLong(3,ctpm.getGiaTien());
            result = pre.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return result;
    }
}

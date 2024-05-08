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
                ctpm.setMaPhieuMuon(rs.getInt(1));
                ctpm.setMaSach(rs.getInt(2));
                ctpm.setGiaTien(rs.getLong(3));
                dsctpm.add(ctpm);
            }
            return dsctpm;
        }catch (Exception e){
        }
        return  null;
    }

    public ArrayList<CTPhieuMuon> getListCTPhieuMuonTheoTrangThai(){
        ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieumuon WHERE MaSach IN (SELECT MaSach FROM sach WHERE TrangThai=0)";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuMuon ctpm = new CTPhieuMuon();
                ctpm.setMaPhieuMuon(rs.getInt(1));
                ctpm.setMaSach(rs.getInt(2));
                ctpm.setGiaTien(rs.getInt(3));
                dsctpm.add(ctpm);
            }
            return dsctpm;
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themCTPhieuMuon(CTPhieuMuon ctpm){
        try{
            String sql = "INSERT INTO ctphieumuon VALUES(?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpm.getMaPhieuMuon());
            pre.setInt(2,ctpm.getMaSach());
            pre.setLong(3,ctpm.getGiaTien());
            return pre.executeUpdate() > 0;
        }catch (Exception e){
        }
        return  false;
    }

    public boolean xoaCTPhieuMuon(int maPhieuMuon){
        try{
            String sql = "DELETE FROM ctphieumuon WHERE MaPhieuMuon="+maPhieuMuon;
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        }catch (Exception e){
        }
        return false;
    }
}

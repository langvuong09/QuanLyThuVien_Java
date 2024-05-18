package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.CTPhieuTra;
import QuanLyThuVien.DTO.PhieuTra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CTPhieuTraDAO {
    public ArrayList<CTPhieuTra> getListCTPhieuTra(){
        ArrayList<CTPhieuTra> dsctpt = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieutra";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CTPhieuTra ctpt = new CTPhieuTra();
                ctpt.setMaPhieuTra(rs.getInt(1));
                ctpt.setMaSach(rs.getInt(2));
                dsctpt.add(ctpt);
            }
            return dsctpt;
        }catch (SQLException e){
        }
        return null;
    }

    public ArrayList<CTPhieuTra> getListCTPhieuTraTheoMaPM(int maPM){
        ArrayList<CTPhieuTra> dsctpt = new ArrayList<>();
        try{
            String sql = "SELECT ctphieutra.MaPhieuTra, ctphieutra.MaSach FROM ctphieutra,phieutra,phieumuon,ctphieumuon" +
                    " WHERE ctphieutra.MaPhieuTra=phieutra.MaPhieuTra AND phieutra.MaPhieuMuon="+maPM+
                    " AND ctphieutra.MaSach=ctphieumuon.MaSach";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CTPhieuTra ctpt = new CTPhieuTra();
                ctpt.setMaPhieuTra(rs.getInt(1));
                ctpt.setMaSach(rs.getInt(2));
                dsctpt.add(ctpt);
            }
            return dsctpt;
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themCTPhieuTra(CTPhieuTra ctpt){
        try{
            String sql = "INSERT INTO ctphieutra VALUES(?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpt.getMaPhieuTra());
            pre.setInt(2,ctpt.getMaSach());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaCTPhieuTra(int maPhieuTra){
        try{
            String sql = "DELETE FROM ctphieutra WHERE MaPhieuTra="+maPhieuTra;
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xacDinhCTPhieuTra(int maSach, int maPM){
        try{
            String sql = "SELECT * FROM ctphieutra,phieutra WHERE ctphieutra.MaSach=? AND phieutra.MaPhieuMuon=? " +
                    "AND ctphieutra.MaPhieuTra=phieutra.MaPhieuTra";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maSach);
            pre.setInt(2,maPM);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public int locDocGia(int maDocGia){
        try{
            String sql = "SELECT COUNT(MaSach) FROM ctphieutra,phieutra WHERE ctphieutra.MaPhieuTra=phieutra.MaPhieuTra " +
                    "AND phieutra.MaDocGia="+maDocGia;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
        }
        return 0;
    }
}

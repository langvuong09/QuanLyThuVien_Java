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
                ctpt.setMaPhanSach(rs.getInt(3));
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
            String sql = "SELECT DISTINCT ctphieutra.MaPhieuTra, ctphieutra.MaSach, ctphieutra.MaPhanSach \n" +
                    "FROM ctphieutra\n" +
                    "JOIN phieutra ON ctphieutra.MaPhieuTra = phieutra.MaPhieuTra\n" +
                    "JOIN phieumuon ON phieutra.MaPhieuMuon = phieumuon.MaPhieuMuon\n" +
                    "JOIN ctphieumuon ON ctphieutra.MaSach = ctphieumuon.MaSach\n" +
                    "WHERE phieutra.MaPhieuMuon = "+ maPM;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CTPhieuTra ctpt = new CTPhieuTra();
                ctpt.setMaPhieuTra(rs.getInt(1));
                ctpt.setMaSach(rs.getInt(2));
                ctpt.setMaPhanSach(rs.getInt(3));
                dsctpt.add(ctpt);
            }
            return dsctpt;
        }catch (SQLException e){
        }
        return null;
    }

    public boolean themCTPhieuTra(CTPhieuTra ctpt){
        try{
            String sql = "INSERT INTO ctphieutra VALUES(?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpt.getMaPhieuTra());
            pre.setInt(2,ctpt.getMaSach());
            pre.setInt(3,ctpt.getMaPhanSach());
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

    public boolean xacDinhCTPhieuTra(int maSach, int maPS, int maPM){
        try{
            String sql = "SELECT * FROM ctphieutra,phieutra WHERE ctphieutra.MaSach=? AND ctphieutra.MaPhanSach=?" +
                    " AND phieutra.MaPhieuMuon=? AND ctphieutra.MaPhieuTra=phieutra.MaPhieuTra";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maSach);
            pre.setInt(2,maPS);
            pre.setInt(3,maPM);
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

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
}

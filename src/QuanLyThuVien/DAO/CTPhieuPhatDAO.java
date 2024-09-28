package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.CTPhieuPhat;
import QuanLyThuVien.DTO.PhieuPhat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CTPhieuPhatDAO {
    public ArrayList<CTPhieuPhat> getListCTPhieuPhap(){
        ArrayList<CTPhieuPhat> dsctpp = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieuphat";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuPhat ctpp = new CTPhieuPhat();
                ctpp.setMaPhieuPhat(rs.getInt(1));
                ctpp.setMaSach(rs.getInt(2));
                ctpp.setMaPhanSach(rs.getInt(3));
                ctpp.setLyDo(rs.getString(4));
                ctpp.setTienPhat(rs.getLong(5));
                dsctpp.add(ctpp);
            }
            return dsctpp;
        }catch (SQLException e){
        }
        return null;
    }

    public ArrayList<CTPhieuPhat> getListCTPhieuPhatTheoMa(int maPP){
        ArrayList<CTPhieuPhat> dsctpp = new ArrayList<>();
        try{
            String sql = "SELECT * FROM ctphieuphat WHERE MaPhieuPhat="+maPP;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CTPhieuPhat ctpp = new CTPhieuPhat();
                ctpp.setMaPhieuPhat(rs.getInt(1));
                ctpp.setMaSach(rs.getInt(2));
                ctpp.setMaPhanSach(rs.getInt(3));
                ctpp.setLyDo(rs.getString(4));
                ctpp.setTienPhat(rs.getLong(5));
                dsctpp.add(ctpp);
            }
            return dsctpp;
        }catch (SQLException e){
            return null;
        }
    }

    public boolean themCTPhieuPhat(CTPhieuPhat ctpp){
        try {
            String sql = "INSERT INTO ctphieuphat VALUES(?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpp.getMaPhieuPhat());
            pre.setInt(2,ctpp.getMaSach());
            pre.setInt(3,ctpp.getMaPhanSach());
            pre.setString(4,ctpp.getLyDo());
            pre.setLong(5,ctpp.getTienPhat());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean xoaCTPhieuPhat(int maPP){
        try{
            String sql = "DELETE FROM ctphieuphat WHERE MaPhieuPhat="+maPP;
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean nhapPhieuPhatTuExcel(CTPhieuPhat ctpp){
        try{
            String sql = "DELETE * FROM ctphieuphat; "+
                    "INSERT INTO phieuphat(MaPhieuPhat, MaSach, MaPhanSach, LyDo, TienPhat)"+
                    "VALUES(?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ctpp.getMaPhieuPhat());
            pre.setInt(2,ctpp.getMaSach());
            pre.setInt(3,ctpp.getMaPhieuPhat());
            pre.setString(4,ctpp.getLyDo());
            pre.setLong(5,ctpp.getTienPhat());

            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}

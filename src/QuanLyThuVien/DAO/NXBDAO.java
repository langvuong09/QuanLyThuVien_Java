package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.NXB;
import QuanLyThuVien.DTO.PhieuMuon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NXBDAO {
    public ArrayList<NXB> getListNXB(){
        try {
            String sql = "SELECT * FROM nxb";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<NXB> dsnxb = new ArrayList<>();
            while (rs.next()){
                NXB nxb = new NXB();
                nxb.setMaNXB(rs.getInt(1));
                nxb.setTenNXB(rs.getString(2));
                dsnxb.add(nxb);
            }
            return dsnxb;
        }catch (SQLException e){
        }
        return null;
    }

    public NXB getNXB(int ma){
        NXB nxb = null;
        try {
            String sql = "SELECT * FROM nxb WHERE MaNXB="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                nxb = new NXB();
                nxb.setMaNXB(rs.getInt(1));
                nxb.setTenNXB(rs.getString(2));
            }
        }catch (SQLException e){
            return null;
        }
        return nxb;
    }

    public boolean themNXB(NXB nxb){
        boolean result = false;
        try{
            String sql = "INSERT INTO nxb VALUES(?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,nxb.getMaNXB());
            pre.setString(2,nxb.getTenNXB());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean xoaNXB(int ma){
        try{
            String sql = "DELETE FROM nxb WHERE MaNXB="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaNXB(int ma){
        try{
            String sql = "UPDATE nxb SET TenNXB=? WHERE MaNXB=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            NXB nxb = new NXB();
            pre.setString(1,nxb.getTenNXB());
            pre.setInt(2,nxb.getMaNXB());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}

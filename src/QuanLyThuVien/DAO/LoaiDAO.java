package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.Loai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoaiDAO {

    public ArrayList<Loai> getListLoai(){
        try{
            String sql = "SELECT * FROM loaisach";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Loai> dsl = new ArrayList<>();
            while (rs.next()){
                Loai l = new Loai();
                l.setMaLoai(rs.getInt(1));
                l.setTenLoai(rs.getString(2));
                dsl.add(l);
            }
            return dsl;
        }catch (SQLException e){
        }
        return null;
    }

    public Loai getLoai(int ma){
        Loai l = null;
        try{
            String sql = "SELECT * FROM loaisach WHERE MaLoai="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                l = new Loai();
                l.setMaLoai(rs.getInt(1));
                l.setTenLoai(rs.getString(2));
            }
        }catch (SQLException e){
            return null;
        }
        return l;
    }

    public boolean themLoai(Loai l){
        boolean result = false;
        try{
            String sql = "INSERT INTO loaisach VALUES(?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,l.getMaLoai());
            pre.setString(2,l.getTenLoai());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean xoaLoai(int ma){
        try{
            String sql = "DELETE FROM loai WHERE MaLoai="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaLoai(int ma, String ten){
        boolean result =false;
        try{
            String sql = "UPDATE loaisach SET TenLoai=? WHERE MaLoai=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,ten);
            pre.setInt(2,ma);
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }
}

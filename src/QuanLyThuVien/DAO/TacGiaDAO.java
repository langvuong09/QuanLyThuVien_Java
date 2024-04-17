package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.TacGia;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TacGiaDAO {
    public ArrayList<TacGia> getListTacGia(){
        try {
            String sql = "SELECT * FROM tacgia";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<TacGia> dstg = new ArrayList<>();
            while (rs.next()) {
                TacGia tg = new TacGia();
                tg.setMaTacGia(rs.getInt(1));
                tg.setTenTacGia(rs.getString(2));
                dstg.add(tg);
            }
            return dstg;
        }catch (SQLException e){
        }
        return null;
    }

    public TacGia getTacGia(int ma){
        TacGia tg = null;
        try{
            String sql = "SELECT * FROM tacgia WHERE ma="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                tg = new TacGia();
                tg.setMaTacGia(rs.getInt(1));
                tg.setTenTacGia(rs.getString(2));
            }
        }catch (SQLException e){
            return null;
        }
        return tg;
    }

    public boolean themTacGia(TacGia tg){
        try{
            String sql = "INSERT INTO tacgia VALUES(?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,tg.getMaTacGia());
            pre.setString(2,tg.getTenTacGia());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean xoaTacGia(int ma){
        try{
            String sql = "DELETE FROM tacgia WHERE MaTacGia="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaTacGia(int ma, String ten){
        try{
            String sql = "UPDATE tacgia SET TenTacGia=? WHERE MaTacGia=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,ten);
            pre.setInt(2,ma);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
        }
        return false;
    }
}

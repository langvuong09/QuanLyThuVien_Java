package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.DocGia;

import javax.print.Doc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DocGiaDAO {

    public ArrayList<DocGia> getListDocGia(){
        try{
            String sql = "SELECT * FROM docgia WHERE Quyen=1";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<DocGia> dsdg = new ArrayList<>();
            while (rs.next()){
                DocGia dg = new DocGia();
                dg.setMaDocGia(rs.getInt(1));
                dg.setHo(rs.getString(2));
                dg.setTen(rs.getString(3));
                dg.setGioiTinh(rs.getString(4));
                dg.setSDT(rs.getString(5));
                dg.setGmail(rs.getString(6));
                dsdg.add(dg);
            }
            return  dsdg;
        }catch (Exception e){
        }
        return null;
    }

    public DocGia getDocGia(int maDG){
        DocGia dg = null;
        try{
            String sql = "SELECT * FROM WHERE Quyen=1 AND MaDocGia="+maDG;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                dg = new DocGia();
                dg.setMaDocGia(rs.getInt(1));
                dg.setHo(rs.getString(2));
                dg.setTen(rs.getString(3));
                dg.setGioiTinh(rs.getString(4));
                dg.setSDT(rs.getString(5));
                dg.setGmail(rs.getString(6));
            }
        }catch (Exception e){
            return null;
        }
        return dg;
    }

    public boolean themDocGia(DocGia dg){
        boolean result =false;
        try{
            String sql ="INSERT INTO docgia VALUES(?,?,?,?,?,?,1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,dg.getMaDocGia());
            pre.setString(2, dg.getHo());
            pre.setString(3, dg.getTen());
            pre.setString(4,dg.getGioiTinh());
            pre.setString(5,dg.getSDT());
            pre.setString(6,dg.getGmail());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean xoaDocGia(int maDG){
        try{
            String sql = "DELETE FROM docgia WHERE MaDocGia="+maDG;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return false;
    }

    public boolean suaDocGia(int maDG, DocGia dg){
        boolean result = false;
        try{
            String sql = "UPDATE docgia SET Ho=?, Ten=?, GioiTinh=?, SDT=?, Gmail=? WHERE MaDocGia=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,dg.getHo());
            pre.setString(2,dg.getTen());
            pre.setString(3,dg.getGioiTinh());
            pre.setString(4,dg.getSDT());
            pre.setString(5,dg.getGmail());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }
}

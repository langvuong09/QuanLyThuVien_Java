package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.PhanQuyen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanQuyenDAO {
    public ArrayList<PhanQuyen> getListQuyen(){
        try {
            String sql = "SELECT * FROM phanquyen";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<PhanQuyen> dspq = new ArrayList<>();
            while (rs.next()) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(rs.getString(1));
                phanQuyen.setQlSach(rs.getInt(2));
                phanQuyen.setQlNhanVien(rs.getInt(3));
                phanQuyen.setQlDocGia(rs.getInt(4));
                phanQuyen.setThongKe(rs.getInt(5));
                dspq.add(phanQuyen);
            }
            return dspq;
        }
        catch (Exception e) {
        }
        return null;
    }

    public PhanQuyen getQuyen(String quyen){
        try{
            String sql = "SELECT * FROM phanquyen  WHERE  Quyen='"+ quyen +"'";
            Statement st =MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setQuyen(quyen);
                phanQuyen.setQlSach(rs.getInt(2));
                phanQuyen.setQlNhanVien(rs.getInt(3));
                phanQuyen.setQlDocGia(rs.getInt(4));
                phanQuyen.setThongKe(rs.getInt(5));
                return phanQuyen;
            }
        }
        catch (Exception e) {
        }
        return null;
    }

    public boolean suaQuyen(PhanQuyen phanQuyen){
        try{
            String sql = "UPDATE phanquyen SET QLSach=?, QLNhanVien=?, QLDocGia=?, ThongKe=? WHERE Quyen=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,phanQuyen.getQlSach());
            pre.setInt(2,phanQuyen.getQlNhanVien());
            pre.setInt(3,phanQuyen.getQlDocGia());
            pre.setInt(4,phanQuyen.getThongKe());
            pre.setString(5,phanQuyen.getQuyen());
            return pre.executeUpdate() >0;
        }catch (Exception e){
        }
        return false;
    }

    public boolean themQuyen(PhanQuyen phanQuyen){
        try {
            String sql = "INSERT INTO phanquyen VALUES (?,?,?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,phanQuyen.getQuyen());
            pre.setInt(2,phanQuyen.getQlSach());
            pre.setInt(3,phanQuyen.getQlNhanVien());
            pre.setInt(4,phanQuyen.getQlDocGia());
            pre.setInt(5,phanQuyen.getThongKe());
            return pre.executeUpdate() > 0;
        }
        catch (Exception e){
        }
        return false;
    }

    public boolean xoaQuyen(String phanQuyen) {
        try {
            String sql1 = "UPDATE taikhoan SET Quyen='Default' WHERE Quyen='" + phanQuyen + "'";
            Statement st1 = MyConnect.conn.createStatement();
            st1.executeUpdate(sql1);
            String sql = "DELETE FROM phanquyen WHERE Quyen='" + phanQuyen + "'";
            Statement st = MyConnect.conn.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

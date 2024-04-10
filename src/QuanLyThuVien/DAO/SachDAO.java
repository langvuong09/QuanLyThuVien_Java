package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.Sach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SachDAO {

    public ArrayList<Sach> getListSach(){
        try{
            String sql = "SELECT * FROM  sach WHERE TinhTrang=1";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            ArrayList<Sach> dss = new ArrayList<>();
            while (rs.next()){
                Sach s = new Sach();
                s.setMaSach(rs.getInt(1));
                s.setMaLoaiSach(rs.getInt(2));
                s.setMaNXB(rs.getInt(3));
                s.setMaTacGia(rs.getInt(4));
                s.setTenSach(rs.getString(5));
                s.setGiaSach(rs.getLong(6));
                dss.add(s);
            }
            return dss;
        }catch (SQLException e){
        }
        return null;
    }

    public Sach getSach(int ma){
        Sach s = null;
        try{
            String sql = "SELECT * FROM sach WHERE MaSach="+ma+" AND TinhTrang=1";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                s = new Sach();
                s.setMaSach(rs.getInt(1));
                s.setMaLoaiSach(rs.getInt(2));
                s.setMaNXB(rs.getInt(3));
                s.setMaTacGia(rs.getInt(4));
                s.setTenSach(rs.getString(5));
                s.setGiaSach(rs.getLong(6));
                s.setGhiChu(rs.getString(7));
            }
        }catch (SQLException e){
            return null;
        }
        return s;
    }

    public boolean themSach(Sach s){
        boolean result = false;
        try{
            String sql = "INSERT INTO sach VALUES(?,?,?,?,?,?,?,1)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,s.getMaSach());
            pre.setInt(2,s.getMaLoaiSach());
            pre.setInt(3,s.getMaNXB());
            pre.setInt(4,s.getMaTacGia());
            pre.setString(5,s.getTenSach());
            pre.setLong(6,s.getGiaSach());
            pre.setString(7,s.getGhiChu());
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public boolean xoaSach(int maSach){
        try{
            String sql = "DELETE  FROM sach WHERE MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
        }
        return  false;
    }

    public boolean suaSach(int maSach){
        boolean result = false;
        try{
            String sql = "UPDATE sach SET MaLoai=?, MaNXB=?, MaTacGia=?, TenSach=?, GiaSach=?, GhiChu=? WHERE MaSach=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            Sach s = new Sach();
            pre.setInt(1,s.getMaLoaiSach());
            pre.setInt(2,s.getMaNXB());
            pre.setInt(3,s.getMaTacGia());
            pre.setString(4,s.getTenSach());
            pre.setLong(5,s.getGiaSach());
            pre.setString(6,s.getGhiChu());
            pre.setInt(7,maSach);
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }
}

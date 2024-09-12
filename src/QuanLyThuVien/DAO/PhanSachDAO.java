package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.KhuVuc;
import QuanLyThuVien.DTO.PhanSach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanSachDAO {

    public ArrayList<PhanSach> getListPhanSach(){
        try {
            String sql = "SELECT * FROM phansach";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhanSach> dsps = new ArrayList<>();
            while (rs.next()) {
                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(rs.getInt(1));
                ps.setMaSach(rs.getInt(2));
                ps.setTrangThai(rs.getString(3));

                dsps.add(ps);
            }
            return dsps;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<PhanSach> getListPhanSachTheoMaSach(int ma){
        try {
            String sql = "SELECT * FROM phansach WHERE maSach="+ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhanSach> dsps = new ArrayList<>();
            while (rs.next()) {
                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(rs.getInt(1));
                ps.setMaSach(rs.getInt(2));
                ps.setTrangThai(rs.getString(3));

                dsps.add(ps);
            }
            return dsps;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<PhanSach> getListPhanSachCoTrongKeSach(){
        try {
            String sql = "SELECT phansach.MaPhanSach, phansach.MaSach, phansach.TrangThai\n" +
                    "FROM phansach\n" +
                    "JOIN sachquanly ON phansach.MaSach = sachquanly.MaSach;\n";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhanSach> dsps = new ArrayList<>();
            while (rs.next()) {
                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(rs.getInt(1));
                ps.setMaSach(rs.getInt(2));
                ps.setTrangThai(rs.getString(3));

                dsps.add(ps);
            }
            return dsps;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<PhanSach> getListPhanSachTheoMaCaHai(int maPS, int maS){
        try {
            String sql;
            if(maPS != 0 && maS == 0){
                sql = "SELECT * FROM phansach WHERE MaPhanSach="+maPS;
            }else {
                if(maPS == 0 && maS != 0){
                    sql = "SELECT * FROM phansach WHERE MaSach="+maS;
                }else {
                    sql = "SELECT * FROM phansach WHERE MaPhanSach="+maPS+" AND MaSach="+maS;
                }
            }
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<PhanSach> dsps = new ArrayList<>();
            while (rs.next()) {
                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(rs.getInt(1));
                ps.setMaSach(rs.getInt(2));
                ps.setTrangThai(rs.getString(3));

                dsps.add(ps);
            }
            return dsps;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean themPhanSach(PhanSach ps){
        try{
            String sql = "INSERT INTO phansach VALUES (?,?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ps.getMaPhanSach());
            pre.setInt(2,ps.getMaSach());
            pre.setString(3,ps.getTrangThai());

            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean suaPhanSach(PhanSach ps){
        try{
            String sql = "UPDATE phansach SET TrangThai=? WHERE MaPhanSach=? AND MaSach=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1,ps.getTrangThai());
            pre.setInt(2,ps.getMaPhanSach());
            pre.setInt(3,ps.getMaSach());

            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean xoaPhanSach(int maPhanSach, int maSach){
        try{
            String sql = "DELETE FROM phansach WHERE MaPhanSach="+maPhanSach+" AND MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean suaPhanSach(int maPhanSach, int maSach, PhanSach ps){
        boolean result = false;
        try {
            String sql = "UPDATE phansach SET TrangThai=? WHERE MaPhanSach=? AND MaSach=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, ps.getTrangThai());
            pre.setInt(2, maPhanSach);
            pre.setInt(3, maSach);
            result = pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
        return result;
    }

    public String trangThaiSach(int maPhanSach, int maSach){
        try {
            String sql = "SELECT TrangThai FROM phansach WHERE MaPhanSach="+maPhanSach+" AND MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            return null;
        }
        return null;
    }

    public int maPhanSachLonNhat(int maSach){
        try{
            String sql = "SELECT MAX(MAPhanSach) FROM phansach WHERE MaSach="+maSach;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return -1;
    }
}

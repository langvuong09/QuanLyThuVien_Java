package QuanLyThuVien.DAO;

import QuanLyThuVien.DTO.CTKeSach;
import QuanLyThuVien.DTO.KeSach;
import QuanLyThuVien.DTO.KhuVuc;
import QuanLyThuVien.DTO.Sach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class KhuVucDAO {

    public ArrayList<KhuVuc> getListKhuVuc() {
        try {
            String sql = "SELECT * FROM khuvuc";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhuVuc> dskv = new ArrayList<>();
            while (rs.next()) {
                KhuVuc kv = new KhuVuc();
                kv.setMaKhu(rs.getInt(1));

                dskv.add(kv);
            }
            return dskv;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<KhuVuc> getListKhuVucTheoMa(int ma) {
        try {
            String sql = "SELECT * FROM khuvuc WHERE Khu=0"+ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KhuVuc> dskv = new ArrayList<>();
            while (rs.next()) {
                KhuVuc kv = new KhuVuc();
                kv.setMaKhu(rs.getInt(1));

                dskv.add(kv);
            }
            return dskv;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<KeSach> getListKeSach() {
        try {
            String sql = "SELECT * FROM kesach";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KeSach> dsks = new ArrayList<>();
            while (rs.next()) {
                KeSach ks = new KeSach();
                ks.setMaKe(rs.getInt(1));
                ks.setMaKhu(rs.getInt(2));

                dsks.add(ks);
            }
            return dsks;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<KeSach> getListKeSachTheoMa(int ma) {
        try {
            String sql = "SELECT * FROM kesach WHERE Khu="+ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KeSach> dsks = new ArrayList<>();
            while (rs.next()) {
                KeSach ks = new KeSach();
                ks.setMaKe(rs.getInt(1));
                ks.setMaKhu(rs.getInt(2));

                dsks.add(ks);
            }
            return dsks;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<KeSach> getListKeSachTheoMaSach(int ma) {
        try {
            String sql = "SELECT * FROM kesach WHERE MaKe="+ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<KeSach> dsks = new ArrayList<>();
            while (rs.next()) {
                KeSach ks = new KeSach();
                ks.setMaKe(rs.getInt(1));
                ks.setMaKhu(rs.getInt(2));

                dsks.add(ks);
            }
            return dsks;
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<CTKeSach> getListCTKeSach() {
        try {
            String sql = "SELECT * FROM sachquanly";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<CTKeSach> dsctks = new ArrayList<>();
            while (rs.next()) {
                CTKeSach ctks = new CTKeSach();
                ctks.setMaSach(rs.getInt(1));
                ctks.setMaKe(rs.getInt(2));

                dsctks.add(ctks);
            }
            return dsctks;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<CTKeSach> getListCTKeSachTheoMa(int ma) {
        try {
            String sql = "SELECT * FROM sachquanly WHERE MaKe="+ma;
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            ArrayList<CTKeSach> dsctks = new ArrayList<>();
            while (rs.next()) {
                CTKeSach ctks = new CTKeSach();
                ctks.setMaSach(rs.getInt(1));
                ctks.setMaKe(rs.getInt(2));

                dsctks.add(ctks);
            }
            return dsctks;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean themKhuVuc(KhuVuc kv){
        boolean result = false;
        try{
            String sql ="INSERT INTO khuvuc VALUES (?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,kv.getMaKhu());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean xoaKhuVuc(int ma){
        try{
            String sql = "DELETE FROM khuvuc WHERE Khu="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean themKe(KeSach ks){
        boolean result = false;
        try{
            String sql ="INSERT INTO kesach VALUES (?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,ks.getMaKe());
            pre.setInt(2,ks.getMaKhu());
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean xoaKe(int ma){
        try{
            String sql = "DELETE FROM kesach WHERE MaKe="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean themSachVaoKe(int maSach, int ke){
        boolean result = false;
        try{
            String sql ="INSERT INTO sachquanly(MaSach,MaKe) VALUES (?,?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1,maSach);
            pre.setInt(2,ke);
            return pre.executeUpdate() > 0;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean xoaSachTrongKe(int ma){
        try{
            String sql = "DELETE FROM sachquanly WHERE MaSach="+ma;
            Statement st = MyConnect.conn.createStatement();
            st.execute(sql);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public int getTongKe(int maKhu){
        try{
            String sql = "SELECT COUNT(*) FROM kesach WHERE Khu="+maKhu;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    public int getTongSach(int maKe){
        try{
            String sql = "SELECT COUNT(*) FROM sachquanly WHERE MaKe="+maKe;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    public int getKhu(int ma){
        try{
            String sql = "SELECT Khu FROM kesach WHERE MaKe="+ma;
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    public int getKe(int ma) {
        try {
            String sql = "SELECT MaKe FROM sachquanly WHERE MaSach = ?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, ma);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

}

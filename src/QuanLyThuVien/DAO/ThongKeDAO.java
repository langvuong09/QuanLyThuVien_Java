package QuanLyThuVien.DAO;

import MyCustom.MyDialog;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DTO.ThongKe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author User
 */

public class ThongKeDAO {

    private int getTongSoLuongSach(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM sach");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private int getTongSoLuongNhanVien(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM nhanvien");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private int getTongSoLuongDocGia(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM docgia");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private int getTongSoLuongPM(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM phieumuon");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private int getTongSoLuongPT(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM phieutra");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private int getTongSoLuongPP(){
        try{
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM phieuphat");
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    private String[] getDateString(int nam, int quy) {
        int namBatDau = nam;
        int namKetThuc = nam;
        String thangBatDau = "01"; //kiểu String do có số 0 ở phía trước
        String thangKetThuc = "04"; //kiểu String do có số 0 ở phía trước
        String[] kq = new String[2];
        switch (quy) {
            case 1:
                thangBatDau = "01";
                thangKetThuc = "04";
                break;
            case 2:
                thangBatDau = "03";
                thangKetThuc = "07";
                break;
            case 3:
                thangBatDau = "06";
                thangKetThuc = "10";
                break;
            case 4:
                thangBatDau = "09";
                thangKetThuc = "01";
                namKetThuc++;
        }
        String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
        String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
        kq[0] = strBatDau;
        kq[1] = strKetThuc;
        return kq;
    }

    public double getDoanhThuThang(int thang, int nam) {
        try {
            String thangBD = nam + "-" + thang + "-01";
            String thangKT = nam + "-" + (thang + 1) + "-01";
            String sql = "SELECT SUM(total_tien) AS total FROM ( " +
                    "    SELECT TongTienMuon AS total_tien FROM phieumuon WHERE NgayMuon BETWEEN ? AND ? " +
                    "    UNION ALL " +
                    "    SELECT ThanhTien FROM phieuphat INNER JOIN phieutra ON phieuphat.MaPhieuTra = phieutra.MaPhieuTra WHERE NgayTraThuc BETWEEN ? AND ? " +
                    ") AS total_table";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, thangBD);
            pre.setString(2, thangKT);
            pre.setString(3, thangBD);
            pre.setString(4, thangKT);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                return Double.parseDouble(rs.getInt(1) + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nam;
    }

    public ThongKe getThongKe(int nam) {
        ThongKe thongKe = new ThongKe();
        int[] tongThuQuy = new int[4];
        thongKe.setSoLuongSach(getTongSoLuongSach());
        thongKe.setSoLuongDG(getTongSoLuongDocGia());
        thongKe.setSoLuongNV(getTongSoLuongNhanVien());
        thongKe.setSoLuongPM(getTongSoLuongPM());
        thongKe.setSoLuongPT(getTongSoLuongPT());
        thongKe.setSoLuongPP(getTongSoLuongPP());
        tongThuQuy[0] = getTongThuQuy(nam, 1);
        tongThuQuy[1] = getTongThuQuy(nam, 2);
        tongThuQuy[2] = getTongThuQuy(nam, 3);
        tongThuQuy[3] = getTongThuQuy(nam, 4);
        thongKe.setTongThuQuy(tongThuQuy);
        return thongKe;
    }

    private int getTongThuQuy(int nam, int quy) {
        String[] dateString = getDateString(nam, quy);
        try {
            PreparedStatement prep = MyConnect.conn.prepareStatement("SELECT SUM(total_tien) AS total FROM ( " +
                    "    SELECT TongTienMuon AS total_tien FROM phieumuon WHERE NgayMuon BETWEEN ? AND ? " +
                    "    UNION ALL " +
                    "    SELECT ThanhTien FROM phieuphat INNER JOIN phieutra ON phieuphat.MaPhieuTra = phieutra.MaPhieuTra WHERE NgayTraThuc BETWEEN ? AND ? " +
                    ") AS total_table");
            prep.setString(1, dateString[0]);
            prep.setString(2, dateString[1]);
            prep.setString(3, dateString[0]);
            prep.setString(4, dateString[1]);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }





}

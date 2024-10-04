package QuanLyThuVien.BUS;

import MyCustom.MyDialog;
import QuanLyThuVien.DTO.PhanSach;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.PhanSachDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhanSachBUS {
    public ArrayList<PhanSach> listPhanSach = null;
    public ArrayList<PhanSach> listPhanSachTrongKe = null;
    private PhanSachDAO phanSachDAO = new PhanSachDAO();
    private SachBUS sachBUS = new SachBUS();

    public void docDanhSach(){
        listPhanSach = phanSachDAO.getListPhanSach();
    }
    public void docDanhSachSachTrenKe(){
        listPhanSachTrongKe = phanSachDAO.getListPhanSachCoTrongKeSach();
    }

    public ArrayList<PhanSach> getListPhanSach(){
        if(listPhanSach == null){
            docDanhSach();
        }
        return listPhanSach;
    }

    public ArrayList<PhanSach> getListTheoMa(int maS){
        return phanSachDAO.getListPhanSachTheoMaSach(maS);
    }

    public ArrayList<PhanSach> getListPhanSachCoTrongKeSach(){
        if(listPhanSachTrongKe == null){
            docDanhSachSachTrenKe();
        }
        return listPhanSachTrongKe;
    }

    public boolean themPhanSach(String maPS, String maS, String trangThai){
        try{
            if (!trangThai.equals("Trả muộn")) {
                int maPhanSach = Integer.parseInt(maPS);
                int maSach = Integer.parseInt(maS);

                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(maPhanSach);
                ps.setMaSach(maSach);
                ps.setTrangThai(trangThai);

                if (phanSachDAO.themPhanSach(ps)) {
                    new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                    return true;
                } else {
                    new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                    return false;
                }
            }
        }catch (Exception e){
            new MyDialog("Lỗi hệ thống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public boolean xoaPhanSach(String maPS, String maS){
        int maPhanSach = Integer.parseInt(maPS);
        int maSach = Integer.parseInt(maS);
        if(phanSachDAO.xoaPhanSach(maPhanSach,maSach)){
            new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaPhanSach(String maPS, String maS, String trangThai){
        try {
            if (!trangThai.contains("Trả muộn")) {
                int maPhanSach = Integer.parseInt(maPS);
                int maSach = Integer.parseInt(maS);

                PhanSach ps = new PhanSach();
                ps.setMaPhanSach(maPhanSach);
                ps.setMaSach(maSach);
                ps.setTrangThai(trangThai);

                if (phanSachDAO.suaPhanSach(ps)) {
                    return true;
                } else {
                    return false;
                }
            }
        }catch (Exception e){
            new MyDialog("Lỗi hệ thống!", MyDialog.ERROR_DIALOG);
            return false;
        }
        return false;
    }

    public  ArrayList<PhanSach> timKiemPhanSach(String maPhanSach, String maSach, String tenSach){
        try {
            int maPS = 0, maS = 0;
            if(!maPhanSach.equals("")) {
                maPS = Integer.parseInt(maPhanSach);
            }
            if(!maSach.equals("")) {
                maS = Integer.parseInt(maSach);
            }
            if (!tenSach.equals("")) {
                maS = sachBUS.getMaSach(tenSach);
            }
            if(maPS == 0 && maS == 0){
                new MyDialog("chưa nhập thông tin tìm kiếm!!!", MyDialog.ERROR_DIALOG);
                return listPhanSach;
            }
            ArrayList<PhanSach> dsps = phanSachDAO.getListPhanSachTheoMaCaHai(maPS, maS);
            return dsps;
        }catch (Exception e){
            new MyDialog("Sai định dạng mã hoặc tên phân sách!!!", MyDialog.ERROR_DIALOG);
            return null;
        }
    }

    public String trangThaiSach(String maS, String maPS){
        int maSach = Integer.parseInt(maS);
        int maPhanSach = Integer.parseInt(maPS);
        return phanSachDAO.trangThaiSach(maPhanSach,maSach);
    }

    public int maPhanSachLonNhat(int maSach){
        return phanSachDAO.maPhanSachLonNhat(maSach);
    }
}

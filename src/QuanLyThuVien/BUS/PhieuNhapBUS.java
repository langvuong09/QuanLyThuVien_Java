package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.PhieuNhap;
import QuanLyThuVien.DAO.PhieunhapDAO;
import QuanLyThuVien.DTO.PhieuTra;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.SachDAO;
import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.BUS.DangNhapBUS;
import MyCustom.MyDialog;
import QuanLyThuVien.GUI.DangNhapGUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuNhapBUS {
    private PhieunhapDAO phieunhapDAO = new PhieunhapDAO();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private NXBBUS nxbBUS = new NXBBUS();
    private ArrayList<PhieuNhap> listPhieuNhap = null;
    public PhieuNhapBUS(){
        docDanhSachPhieuNhap();
    }
    public void docDanhSachPhieuNhap(){
        this.listPhieuNhap = phieunhapDAO.getListPhieuNhap();
    }

    public ArrayList<PhieuNhap> getListPhieuNhap(){
        if(listPhieuNhap == null){
            docDanhSachPhieuNhap();
        }
        return listPhieuNhap;
    }

    public boolean themPhieuNhap(String nxb, String nhanVien, long tongTien){
        try {
            int maNXB = nxbBUS.getMaNXB(nxb);

            int maNhanVien = Integer.parseInt(nhanVien);

            PhieuNhap pn = new PhieuNhap();
            pn.setMaNXB(maNXB);
            pn.setMaNhanVien(maNhanVien);
            pn.setTongTien(tongTien);
            if(phieunhapDAO.themPhieuNhap(pn)){
            }else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
        }
        return false;
    }

    public int getMaPhieuNhapMoiNhat(){
        return phieunhapDAO.getMaPhieuNhapLonNhat();
    }

    public PhieuNhap getPhieuNhap(String maPN){
        int maPhieuNhap = Integer.parseInt(maPN);
        for(PhieuNhap pn : listPhieuNhap){
            if(pn.getMaPhieuNhap() == maPhieuNhap){
                return pn;
            }
        }
        return null;
    }

    public ArrayList<PhieuNhap> getListPhieuNhapTheoNgay(Date tuNgay, Date denNgay) {
        ArrayList<PhieuNhap> dspn = new ArrayList<>();
        try {
            for (PhieuNhap pn : listPhieuNhap) {
                Date ngayLap = pn.getNgayLap();
                if (ngayLap != null && ngayLap.after(tuNgay) && ngayLap.before(denNgay)) {
                    dspn.add(pn);
                }
            }
            return dspn;
        } catch (Exception e) {
            new MyDialog("Hãy nhập ngày hợp lệ (dd/MM/yyy)!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }
}

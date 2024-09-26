package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.DAO.PhieuPhatDAO;
import QuanLyThuVien.DTO.PhieuPhat;
import QuanLyThuVien.DTO.PhieuTra;
import QuanLyThuVien.DAO.PhieuTraDAO;
import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.BUS.DangNhapBUS;
import MyCustom.MyDialog;
import QuanLyThuVien.GUI.DangNhapGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuPhatBUS {
    public ArrayList<PhieuPhat> listPhieuPhat = null;
    private PhieuPhatDAO ppDAO = new PhieuPhatDAO();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    public PhieuPhatBUS(){
        docListPhieuPhat();
    }
    public void docListPhieuPhat(){
        listPhieuPhat = ppDAO.getListPhieuPhat();
    }

    public ArrayList<PhieuPhat> getListPhieuPhat(){
        if(listPhieuPhat == null){
            docListPhieuPhat();
        }
        return listPhieuPhat;
    }

    public PhieuPhat getPhieuPhat(String ma){
        if(!ma.trim().equals("")){
            try{
                int maPP = Integer.parseInt(ma);
                for(PhieuPhat pp : listPhieuPhat){
                    if(pp.getMaPhieuPhat() == maPP){
                        return pp;
                    }
                }
            }catch (Exception e){
            }
        }
        return null;
    }

    public ArrayList<PhieuPhat> getListPhieuPhatTheoDocGia(String docGia){
        ArrayList<PhieuPhat> dspp = new ArrayList<>();
        for(PhieuPhat pp : listPhieuPhat){
            if(docGiaBUS.getTenDocGia(pp.getMaDocGia()).contains(docGia.trim())){
                dspp.add(pp);
            }
        }
        return dspp;
    }

//    public int getMaPhieuPhatMoiNhat(){
//        return ppDAO.getMaPhieuPhatMoiNhat();
//    }
//
//    public boolean nhapPhieuPhatExcel(String maPT,String sach, String phanSach, String docGia, String nhanVien, String lyDo, String thanhTien){
//        try{
//            int maPhieuTra = Integer.parseInt(maPT);
//            int maS = Integer.parseInt(sach);
//            int maPS = Integer.parseInt(phanSach);
//            int maDG = docGiaBUS.getMaDocGia(docGia);
//            int maNV = nhanVienBUS.getMaNhanVien(nhanVien);
//            long tien = Long.parseLong(thanhTien);
//
//            PhieuPhat pp = new PhieuPhat();
//            pp.setMaPhieuTra(maPhieuTra);
//            pp.setMaSach(maS);
//            pp.setMaPhanSach(maPS);
//            pp.setMaDocGia(maDG);
//            pp.setMaNhanVien(maNV);
//            pp.setLyDo(lyDo);
//            pp.setThanhTien(tien);
//
//            ppDAO.nhapPhieuPhatTuExcel(pp);
//            return true;
//        }catch (Exception e){
//        }
//        return false;
//    }
//
//    public boolean themPhieuPhat(String maPP, String maPT, String maSach, String maPhanSach, String docGia, String nhanVien, String lyDo, String thanhTien){
//        if(maPT.trim().equals("")){
//            new MyDialog("Không được để trống mã phiếu trả!!!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        if(maSach.trim().equals("")){
//            new MyDialog("Chưa chọn sách vi phạm!!!",MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        try{
//            int maPhieuPhat = Integer.parseInt(maPP);
//            int maPhieuTra = Integer.parseInt(maPT);
//            int maS = Integer.parseInt(maSach);
//            int maPS = Integer.parseInt(maPhanSach);
//            long tien = Long.parseLong(thanhTien);
//            int maNhanVien = Integer.parseInt(nhanVien);
//
//            PhieuPhat pp = new PhieuPhat();
//            pp.setMaPhieuPhat(maPhieuPhat);
//            pp.setMaPhieuTra(maPhieuTra);
//            pp.setMaSach(maS);
//            pp.setMaPhanSach(maPS);
//            pp.setMaDocGia(docGiaBUS.getMaDocGia(docGia));
//            pp.setMaNhanVien(maNhanVien);
//            pp.setLyDo(lyDo);
//            pp.setThanhTien(tien);
//
//            if(ppDAO.themPhieuPhat(pp)){
//                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
//                return true;
//            }else {
//                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
//                return false;
//            }
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    public boolean xoaPhieuPhat(String ma){
//        if(ma.trim().equals("")){
//            new MyDialog("Chưa chọn phiếu phạt để xóa!!!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        int maPP = Integer.parseInt(ma);
//        if(ppDAO.xoaPhieuPhat(maPP)){
//            new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
//            return true;
//        }else {
//            new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//    }
//
//    public boolean suaPhieuPhat(String maPP, String maPT, String maSach, String maPhanSach, String docGia, String nhanVien, String lyDo, String thanhTien){
//        if(maPP.trim().equals("")){
//            new MyDialog("Chưa chọn phiếu phạt để sửa!!!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        if(maPT.trim().equals("")){
//            new MyDialog("Không được để trống mã phiếu trả!!!", MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        if(maSach.trim().equals("")){
//            new MyDialog("Chưa chọn sách vi phạm!!!",MyDialog.ERROR_DIALOG);
//            return false;
//        }
//        try{
//            int maPhieuPhat = Integer.parseInt(maPP);
//            int maPhieuTra = Integer.parseInt(maPT);
//            int maS = Integer.parseInt(maSach);
//            int maPS = Integer.parseInt(maPhanSach);
//            long tien = Long.parseLong(thanhTien);
//            int maNhanVien = Integer.parseInt(nhanVien);
//
//            PhieuPhat pp = new PhieuPhat();
//            pp.setMaPhieuPhat(maPhieuPhat);
//            pp.setMaPhieuTra(maPhieuTra);
//            pp.setMaSach(maS);
//            pp.setMaPhanSach(maPS);
//            pp.setMaDocGia(docGiaBUS.getMaDocGia(docGia));
//            pp.setMaNhanVien(maNhanVien);
//            pp.setLyDo(lyDo);
//            pp.setThanhTien(tien);
//
//            if(ppDAO.suaPhieuPhat(pp)){
//                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
//                return true;
//            }else {
//                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
//                return false;
//            }
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    public ArrayList<PhieuPhat> timKiemTheoKhoang(String txtMin, String txtMax){
//        if(txtMin.trim().equals("Lựa chọn") && txtMax.trim().equals("Lựa chọn")){
//            return listPhieuPhat;
//        }
//        try{
//            ArrayList<PhieuPhat> dspp = new ArrayList<>();
//            for(PhieuPhat pp : listPhieuPhat){
//                if (pp.getLyDo().contains(txtMin) || pp.getLyDo().contains(txtMax)){
//                    dspp.add(pp);
//                }
//            }
//            return dspp;
//        }catch (Exception e){
//        }
//        return null;
//    }

    public PhieuPhat getPhieuPhatTheoMa(int ma){
        return ppDAO.getPhieuPhat(ma);
    }
}

package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.*;
import QuanLyThuVien.DAO.KhuVucDAO;
import MyCustom.MyDialog;

import java.sql.SQLException;
import java.util.ArrayList;

public class KhuVucBUS {
    public ArrayList<KhuVuc> listKhuVuc = null;
    public ArrayList<KeSach> listKeSach = null;
    public ArrayList<KeSach> listKeSach1 = null;
    public ArrayList<CTKeSach> listCTKeSach = null;
    private KhuVucDAO khuVucDAO = new KhuVucDAO();
    private SachBUS sachBUS = new SachBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();

    public KhuVucBUS(){
        docDanhSach();
    }
    public void docDanhSach(){
        listKhuVuc = khuVucDAO.getListKhuVuc();
        listKeSach = khuVucDAO.getListKeSach();
        listCTKeSach = khuVucDAO.getListCTKeSach();
        listKeSach1 = khuVucDAO.getListKeSach();
    }

    public ArrayList<KhuVuc> getListKhuVuc(){
        if(listKhuVuc == null){
            docDanhSach();
        }
        return listKhuVuc;
    }

    public ArrayList<KeSach> getListKeSach(){
        if(listKeSach == null){
            docDanhSach();
        }
        return listKeSach;
    }

    public ArrayList<CTKeSach> getListCTKeSach(){
        if(listCTKeSach == null){
            docDanhSach();
        }
        return listCTKeSach;
    }

    public void docDanhSachKhu(int ma){
        listKhuVuc = khuVucDAO.getListKhuVucTheoMa(ma);
    }

    public void docDanhSachKe(int ma){
        listKeSach = khuVucDAO.getListKeSachTheoMa(ma);
    }

    public void docDanhSachKeTheoMaSach(int ma){
        listKeSach = khuVucDAO.getListKeSachTheoMaSach(ma);
    }

    public void docDanhSachSach(int ma){
        listCTKeSach = khuVucDAO.getListCTKeSachTheoMa(ma);
    }

    public ArrayList<KhuVuc> getListKhuVucTheoMa(int ma){
        docDanhSachKhu(ma);
        return listKhuVuc;
    }

    public ArrayList<KeSach> getListKeSachTheoMa(int ma){
        docDanhSachKe(ma);
        return listKeSach;
    }

    public ArrayList<KeSach> getListKeSachTheoMaSach(int ma){
        docDanhSachKeTheoMaSach(ma);
        return listKeSach;
    }

    public ArrayList<CTKeSach> getListCTKeSachTheoMa(int ma){
        docDanhSachSach(ma);
        return listCTKeSach;
    }

    public ArrayList<CTKeSach> getListSachTheoTenSach(String tenSach){
        if(tenSach.trim().equals("")){
            return listCTKeSach;
        }
        int maSach = sachBUS.getMaSach(tenSach);
        ArrayList<CTKeSach> dsctks = new ArrayList<>();
        for(CTKeSach ctks : listCTKeSach){
            if(ctks.getMaSach() == maSach){
                dsctks.add(ctks);
            }
        }

        return dsctks;
    }

    public boolean themKhu(){
        try {
            int ma = listKhuVuc.size();
            KhuVuc kv = new KhuVuc();
            kv.setMaKhu(ma+1);
            if (khuVucDAO.themKhuVuc(kv)) {
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean xoaKhu(int maKhu){
        if(maKhu == 0){
            new MyDialog("Chưa chọn khu vực để xóa!!!", MyDialog.ERROR_DIALOG);
            return false;
        }else {
            if (khuVucDAO.xoaKhuVuc(maKhu)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean themKeSach(int khu){
        try {
            int count = listKeSach1.size();
            KeSach ks = new KeSach();
            ks.setMaKe(count+1);
            ks.setMaKhu(khu);
            if (khuVucDAO.themKe(ks)) {
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean xoaKe(int maKe){
        if(maKe == 0){
            new MyDialog("Chưa chọn kệ để xóa!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(khuVucDAO.xoaKe(maKe)){
            return true;
        }else {
            return false;
        }
    }

    public boolean themSachVaoKe(int maSach, int ke){
        try {
            if (khuVucDAO.themSachVaoKe(maSach,ke)) {
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean xoaSachTrenKe(int maSach){
        if(maSach == 0){
            new MyDialog("Chưa chọn sách để xóa!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(khuVucDAO.xoaSachTrongKe(maSach)){
            return true;
        }else {
            return false;
        }
    }

    public int tongKeMoiKhu(int maKhu){
        return khuVucDAO.getTongKe(maKhu);
    }

    public int tongSachMoiKe(int maKe){
        return khuVucDAO.getTongSach(maKe);
    }

    public int getKhu(int ma){
        return khuVucDAO.getKhu(ma);
    }

    public int getKe(int ma){
        return khuVucDAO.getKe(ma);
    }
    public ArrayList<PhanSach> timListPhanSach(String ten){
        int ma = sachBUS.getMaSach(ten);
        int count = 0;
        ArrayList<CTKeSach> dsctks = new ArrayList<>();
        ArrayList<PhanSach> dsps = phanSachBUS.getListPhanSach();
        ArrayList<PhanSach> dsps1 = new ArrayList<>();
        for(CTKeSach ctks : listCTKeSach){
            if (ctks.getMaSach() == ma) {
                count = ma;
                break;
            }
        }
        for(PhanSach ps : dsps){
            if(ps.getMaSach() == count){
                dsps1.add(ps);
            }
        }
        return dsps1;
    }
}

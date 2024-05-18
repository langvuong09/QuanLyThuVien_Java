package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.PhanQuyen;
import QuanLyThuVien.DAO.PhanQuyenDAO;
import MyCustom.MyDialog;

import java.awt.*;
import java.util.ArrayList;

public class PhanQuyenBUS {
    public  static PhanQuyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    private ArrayList<PhanQuyen> listPhanQuyen = null;
    public void docDanhSachQuyen(){
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String quyen) {
        quyenTK = phanQuyenDAO.getQuyen(quyen);
    }

    public ArrayList<PhanQuyen> getListQuyen(){
        if (listPhanQuyen == null) docDanhSachQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(String tenQuyen, int sach, int nhanVien, int docGia,int nhapSach, int thongKe){
        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen,sach,nhanVien,docGia,nhapSach,thongKe);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if(flag){
            new MyDialog("Sửa thành công", MyDialog.SUCCESS_DIALOG);
        }
        else{
            new MyDialog("Sửa thất bại",MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean themQuyen(String tenQuyen){
        if(tenQuyen == null || tenQuyen.trim().equals("")){
            return false;
        }
        if (kiemTonTaiQuyen(tenQuyen)){
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
            return false;
        }

        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen,0,0,0,0,0);
        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
        if(flag){
            new MyDialog("Thêm thành công! Hãy hiệu chỉnh quyền", MyDialog.SUCCESS_DIALOG);
        }else {
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean kiemTonTaiQuyen(String tenQuyen){
        docDanhSachQuyen();
        for(PhanQuyen pq : listPhanQuyen){
            if(pq.getQuyen().equalsIgnoreCase(tenQuyen))
            return true;
        }
        return false;
    }

    public boolean xoaQuyen(String tenQuyen){
        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
        if(flag){
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else{
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}

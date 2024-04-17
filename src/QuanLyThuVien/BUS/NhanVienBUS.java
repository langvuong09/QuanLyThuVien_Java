package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DTO.NXB;
import QuanLyThuVien.DTO.NhanVien;
import QuanLyThuVien.DAO.NhanVienDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class NhanVienBUS {
    private ArrayList<NhanVien> listNhanVien = null;
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();

    public NhanVienBUS(){docDanhSach();}

    public void docDanhSach(){
        this.listNhanVien = nhanVienDAO.getListNhanVien();
    }

    public ArrayList<NhanVien> getListNhanVien(){
        if(listNhanVien == null){
            docDanhSach();
        }
        return listNhanVien;
    }

    public String getTenNhanVien(int ma){
        for(NhanVien nv : listNhanVien){
            if(nv.getMaNhanVien() == ma){
                return nv.getHo() +" "+ nv.getTen();
            }
        }
        return "";
    }

    public int getMaNhanVien(String ten){
        for(NhanVien nv : listNhanVien){
            String nhap = nv.getHo()+" "+nv.getTen();
            if(nhap.equals(ten)){
                return nv.getMaNhanVien();
            }
        }
        return 0;
    }

    public boolean themNhanVien(int ma,String ho, String ten, String gioiTinh, String SDT, String gmail){
        if(ten.trim().equals("") || ho.trim().equals("")){
            new MyDialog("Không được để trống họ tên!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gioiTinh.trim().equals("")){
            new MyDialog("Chưa chọn giới tính!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(SDT.trim().equals("")){
            new MyDialog("Không được để trống số điện thoại!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gmail.trim().equals("")){
            new MyDialog("Không được để trống gmail!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        ma += 1;
        NhanVien nv =new NhanVien(ma,ho,ten,gioiTinh,SDT,gmail,1);
        if(nhanVienDAO.themNhanVien(nv)){
            new MyDialog("Thêm thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaNhanVien(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn nhân viên để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maNV = Integer.parseInt(ma);
        if(nhanVienDAO.xoaNhanVien(maNV)){
            new MyDialog("Xóa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaNhanVien(String ma, NhanVien nv){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn nhân viên để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }

        if(nv.getHo().trim().equals("") || nv.getTen().trim().equals("")){
            new MyDialog("Không được để trống họ tên!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(nv.getGioiTinh().trim().equals("")){
            new MyDialog("Chưa chọn giới tính!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(nv.getSDT().trim().equals("")){
            new MyDialog("Không được để trống số điện thoại!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(nv.getGmail().trim().equals("")){
            new MyDialog("Không được để trống gmail!!!",MyDialog.ERROR_DIALOG);
            return false;
        }

        int maNV = Integer.parseInt(ma);
        if (nhanVienDAO.suaNhanVien(maNV,nv)) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}

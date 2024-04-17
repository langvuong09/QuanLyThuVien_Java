package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.LoaiDAO;
import QuanLyThuVien.DTO.Loai;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class LoaiBUS {
    private ArrayList<Loai> listLoai = null;
    private LoaiDAO loaiDAO = new LoaiDAO();

    public LoaiBUS(){docDanhSach();}

    public void docDanhSach(){
        this.listLoai = loaiDAO.getListLoai();
    }

    public ArrayList<Loai> getListLoai(){
        if(listLoai == null){
            docDanhSach();;
        }
        return listLoai;
    }

    public String getTenLoai(int ma){
        for(Loai l :  listLoai){
            if(l.getMaLoai() == ma){
                return l.getTenLoai();
            }
        }
        return "";
    }

    public int getMaLoai(String ten){
        for(Loai l : listLoai){
            if(l.getTenLoai() == ten){
                return l.getMaLoai();
            }
        }
        return 0;
    }

    public boolean themLoai(int ma, String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên loại!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        ma += 1;
        Loai l = new Loai(ma,ten);
        if(loaiDAO.themLoai(l)){
            new MyDialog("Thêm thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaLoai(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn loại để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if(loaiDAO.xoaLoai(maLoai)){
            new MyDialog("Xóa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại! Loại có chứa sách",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaLoai(String ma, String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên loại!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if(loaiDAO.suaLoai(maLoai,ten)){
            new MyDialog("Sửa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Sửa thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}

package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.TacGiaDAO;
import QuanLyThuVien.DTO.TacGia;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class TacGiaBUS {
    private ArrayList<TacGia> listTacGia = null;
    private TacGiaDAO tacGiaDAO = new TacGiaDAO();

    public TacGiaBUS(){docDanhSach();}

    public void docDanhSach(){
        this.listTacGia = tacGiaDAO.getListTacGia();
    }

    public ArrayList<TacGia> getListTacGia(){
        if(listTacGia == null){
            docDanhSach();
        }
        return this.listTacGia;
    }

    public String getTenTacGia(int ma){
        for(TacGia tg : listTacGia){
            if(tg.getMaTacGia() == ma){
                return tg.getTenTacGia();
            }
        }
        return "";
    }

    public int getMaTacGia(String ten){
        for(TacGia tg : listTacGia){
            if(tg.getTenTacGia() == ten){
                return tg.getMaTacGia();
            }
        }
        return 0;
    }

    public boolean themTacGia(int ma, String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên tác giả!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        ma += 1;
        TacGia tg = new TacGia(ma,ten);
        if(tacGiaDAO.themTacGia(tg)){
            new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaTacGia(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn tác giả để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maTG = Integer.parseInt(ma);
        if(tacGiaDAO.xoaTacGia(maTG)){
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Xoá thất bại! Tác giả có sách", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaTacGia(String ma,String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên tác giả!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maTG = Integer.parseInt(ma);
        if(tacGiaDAO.suaTacGia(maTG,ten)){
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}

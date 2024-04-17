package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.MyConnect;
import QuanLyThuVien.DAO.NXBDAO;
import QuanLyThuVien.DTO.NXB;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class NXBBUS {
    private NXBDAO nxbDAO = new NXBDAO();
    private ArrayList<NXB> listNXB = null;

    public NXBBUS() {docDanhSach();}

    public void docDanhSach() {
        this.listNXB = nxbDAO.getListNXB();
    }

    public ArrayList<NXB> getListNXB(){
        if(listNXB == null){
            docDanhSach();
        }
        return this.listNXB;
    }

    public String getTenNXB(int ma){
        for(NXB nxb : listNXB){
            if(nxb.getMaNXB() == ma){
                return nxb.getTenNXB();
            }
        }
        return "";
    }

    public int getMaNXB(String ten){
        for(NXB nxb : listNXB){
            if(nxb.getTenNXB() == ten){
                return nxb.getMaNXB();
            }
        }
        return 0;
    }

    public boolean themNXB(int ma, String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên nhà xuất bản!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        NXB nxb = new NXB(ma,ten);
        if(nxbDAO.themNXB(nxb)){
            new MyDialog("Thêm thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaNXB(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn nxb để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maNXB = Integer.parseInt(ma);
        if(nxbDAO.xoaNXB(maNXB)){
            new MyDialog("Xoá thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xoá thất bại! nxb có chứa sách",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaNXB(String ma, String ten){
        if(ten.trim().equals("")){
            new MyDialog("Không được để trống tên nhà xuất bản!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maNXB = Integer.parseInt(ma);
        if(nxbDAO.suaNXB(maNXB,ten)){
            new MyDialog("Sửa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Sửa thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}

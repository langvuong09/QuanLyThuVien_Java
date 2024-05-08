package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DAO.DocGiaDAO;
import MyCustom.MyDialog;
import QuanLyThuVien.DTO.NhanVien;

import java.util.ArrayList;

public class DocGiaBUS {
    private ArrayList<DocGia> listDocGia = null;
    private DocGiaDAO docGiaDAO = new DocGiaDAO();

    public DocGiaBUS(){docDanhSach();}
    public void docDanhSach() {this.listDocGia = docGiaDAO.getListDocGia();}

    public ArrayList<DocGia> getListDocGia(){
        if(listDocGia == null)
            docDanhSach();
        return listDocGia;
    }

    public String getTenDocGia(int ma){
        for(DocGia dg : listDocGia){
            if(dg.getMaDocGia() == ma){
                return dg.getHo() +" "+ dg.getTen();
            }
        }
        return "";
    }

    public int getMaDocGia(String ten){
        for(DocGia dg : listDocGia){
            String nhap = dg.getHo()+" "+dg.getTen();
            if(nhap.equals(ten)){
                return dg.getMaDocGia();
            }
        }
        return 0;
    }

    public ArrayList<DocGia> timKiemDocGia(String tuKhoa){
        if(tuKhoa.equals("")){
            return listDocGia;
        }
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<DocGia> dsdg = new ArrayList<>();
        for(DocGia dg : listDocGia){
            String ho = dg.getHo().toLowerCase();
            String ten = dg.getTen().toLowerCase();
            String SDT = dg.getSDT().toLowerCase();
            String gmail = dg.getGmail().toLowerCase();
            if(ho.contains(tuKhoa) || ten.contains(tuKhoa) || SDT.contains(tuKhoa) || gmail.contains(tuKhoa)){
                dsdg.add(dg);
            }
        }
        return dsdg;
    }

    public boolean themDocGia(int ma,String ho, String ten, String gioiTinh, String SDT, String gmail){
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
        DocGia dg = new DocGia(ma,ho,ten,gioiTinh,SDT,gmail,1);
        if(docGiaDAO.themDocGia(dg)){
            new MyDialog("Thêm thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaDocGia(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn đọc giả để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maDG = Integer.parseInt(ma);
        if(docGiaDAO.xoaDocGia(maDG)){
            new MyDialog("Xóa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaDocGia(String ma, DocGia dg){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn nhân viên để xóa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }

        if(dg.getHo().trim().equals("") || dg.getTen().trim().equals("")){
            new MyDialog("Không được để trống họ tên!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(dg.getGioiTinh().trim().equals("")){
            new MyDialog("Chưa chọn giới tính!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(dg.getSDT().trim().equals("")){
            new MyDialog("Không được để trống số điện thoại!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(dg.getGmail().trim().equals("")){
            new MyDialog("Không được để trống gmail!!!",MyDialog.ERROR_DIALOG);
            return false;
        }

        int maDG = Integer.parseInt(ma);
        if (docGiaDAO.suaDocGia(maDG,dg)) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }
}

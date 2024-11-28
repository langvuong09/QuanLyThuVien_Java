package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.CTPhieuMuonDAO;
import QuanLyThuVien.DAO.CTPhieuTraDAO;
import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DAO.DocGiaDAO;
import MyCustom.MyDialog;
import QuanLyThuVien.DTO.NhanVien;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.regex.*;

public class DocGiaBUS {
    private ArrayList<DocGia>   listDocGia = null;
    private DocGiaDAO docGiaDAO = new DocGiaDAO();
    private CTPhieuMuonDAO ctpmDAO = new CTPhieuMuonDAO();
    private CTPhieuTraDAO ctptDAO = new CTPhieuTraDAO();

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

    public boolean themDocGia(String ho, String ten, String gioiTinh, String SDT, String gmail){
        if(ten.trim().equals("") || ho.trim().equals("")){
            new MyDialog("Không được để trống họ tên!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isName(ho)){
            new MyDialog("Họ không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isName(ten)){
            new MyDialog("Tên không hợp lệ!!!",MyDialog.ERROR_DIALOG);
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
        if(!isPhoneNumber(SDT)){
            new MyDialog("Số điện thoại không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gmail.trim().equals("")){
            new MyDialog("Không được để trống gmail!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isEmail(gmail)){
            new MyDialog("Gmail không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }

        DocGia dg = new DocGia();
        dg.setHo(ho);
        dg.setTen(ten);
        dg.setSDT(SDT);
        dg.setGioiTinh(gioiTinh);
        dg.setGmail(gmail);

        if(docGiaDAO.themDocGia(dg)){
            new MyDialog("Thêm thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Thêm thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public static boolean isPhoneNumber(String sdt) {
        sdt = sdt.trim();
        if (sdt.matches("\\d{10,11}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isName(String ten){
        if(ten.trim().isEmpty()){
            return false;
        }
        String regex = "^[\\p{L}\\s]+$";
        if (!Pattern.matches(regex, ten)) {
            return false;
        }

        return true;
    }

    public static boolean isEmail(String email) {
        // Biểu thức chính quy để kiểm tra địa chỉ email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Kiểm tra địa chỉ email có phù hợp với biểu thức chính quy không
        return Pattern.matches(regex, email);
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

    public boolean suaDocGia(String ma,String ho, String ten, String gioiTinh, String SDT, String gmail){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn đọc giả để sửa!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(ten.trim().equals("") || ho.trim().equals("")){
            new MyDialog("Không được để trống họ tên!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isName(ho)){
            new MyDialog("Họ không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isName(ten)){
            new MyDialog("Tên không hợp lệ!!!",MyDialog.ERROR_DIALOG);
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
        if(!isPhoneNumber(SDT)){
            new MyDialog("Số điện thoại không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gmail.trim().equals("")){
            new MyDialog("Không được để trống gmail!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        if(!isEmail(gmail)){
            new MyDialog("Gmail không hợp lệ!!!",MyDialog.ERROR_DIALOG);
            return false;
        }
        int maDG = Integer.parseInt(ma);
        DocGia dg = new DocGia();
        dg.setHo(ho);
        dg.setTen(ten);
        dg.setSDT(SDT);
        dg.setGioiTinh(gioiTinh);
        dg.setGmail(gmail);

        if(docGiaDAO.suaDocGia(maDG,dg)){
            new MyDialog("Sửa thành công!",MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Sửa thất bại!",MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public ArrayList<DocGia> timDocGia(String ten){
        ArrayList<DocGia> dsdg = new ArrayList<>();
        for(DocGia dg : listDocGia){
            if(dg.getHo().toLowerCase().contains(ten.trim().toLowerCase()) ||
            dg.getTen().toLowerCase().contains(ten.trim().toLowerCase())){
                dsdg.add(dg);
            }
        }
        return dsdg;
    }

    public int locDocGia(String maDocGia){
        int maDG = Integer.parseInt(maDocGia);
        int slSM = ctpmDAO.locDocGia(maDG);
        int slST = ctptDAO.locDocGia(maDG);
        return slSM - slST;
    }
}

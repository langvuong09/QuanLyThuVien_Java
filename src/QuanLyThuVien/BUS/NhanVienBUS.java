package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DTO.NXB;
import QuanLyThuVien.DTO.NhanVien;
import QuanLyThuVien.DAO.NhanVienDAO;
import MyCustom.MyDialog;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.regex.*;

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

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String SDT, String chucVu, String gmail){
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
        if(chucVu.trim().equals("")){
            chucVu="Default";
        }
        NhanVien nv =new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setSDT(SDT);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        nv.setGmail(gmail);

        if(nhanVienDAO.themNhanVien(nv)){
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

    public boolean suaNhanVien(String ma,String ho, String ten, String gioiTinh, String SDT, String chucVu, String gmail){
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
        if(chucVu.trim().equals("")){
            chucVu="Default";
        }

        int maNV = Integer.parseInt(ma);
        NhanVien nv =new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setSDT(SDT);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        nv.setGmail(gmail);
        if (nhanVienDAO.suaNhanVien(maNV,nv)) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean nhapNhanVienTuExcel(String manv, String tenNV, String hoNV, String gioitinh, String sdt, String chucVu, String gmail) {

        try {
            NhanVien nv = new NhanVien();
            int maNV = Integer.parseInt(manv);
            nv.setMaNhanVien(maNV);
            nv.setHo(hoNV);
            nv.setTen(tenNV);
            nv.setGioiTinh(gioitinh);
            nv.setSDT(sdt);
            nv.setChucVu(chucVu);
            nv.setGmail(gmail);

            nhanVienDAO.nhapNhanVienTuExcel(nv);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<NhanVien> timDocGia(String ten){
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for(NhanVien nv : listNhanVien){
            if(nv.getHo().toLowerCase().contains(ten.trim().toLowerCase()) ||
                    nv.getTen().toLowerCase().contains(ten.trim().toLowerCase())){
                dsnv.add(nv);
            }
        }
        return dsnv;
    }
}

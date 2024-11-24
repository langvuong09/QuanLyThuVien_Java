package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.TaiKhoanDAO;
import QuanLyThuVien.DTO.TaiKhoan;
import MyCustom.MyDialog;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TaiKhoanBUS {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private ArrayList<TaiKhoan> listTaiKhoan;

    public TaiKhoanBUS(){docListTaiKhoan();}

    public void docListTaiKhoan(){this.listTaiKhoan = taiKhoanDAO.getListTaiKhoan();}
    public ArrayList<TaiKhoan> getListTaiKhoan(){
        if (listTaiKhoan == null){
            docListTaiKhoan();
        }
        return this.listTaiKhoan;
    }

    public String getTenDangNhapTheoMa(String ma) {
        int maNhanVien = Integer.parseInt(ma);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNhanVien);
    }

    public String getQuyenTheoMa(String ma) {
        int maNhanVien = Integer.parseInt(ma);
        return taiKhoanDAO.getQuyenTheoMa(maNhanVien);
    }

    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNhanVien = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNhanVien, tenDangNhap);
        if (flag) {
            new MyDialog("Đặt lại thành công! Mật khẩu mới là: " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public void datLaiQuyen(String ma, String quyen) {
        int maNhanVien = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiQuyen(quyen,maNhanVien);
        if (flag) {
            new MyDialog("Đặt lại thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {
        int maNhanVien = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            new MyDialog("Không được để trống Tên đăng nhập!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            MyDialog dlg = new MyDialog("Tên đăng nhập bị trùng! Có thể tài khoản bị khoá, thực hiện mở khoá?", MyDialog.WARNING_DIALOG);
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        boolean flag = taiKhoanDAO.themTaiKhoan(maNhanVien, tenDangNhap, quyen);
        if (flag) {
            new MyDialog("Cấp tài khoản thành công! Mật khẩu là " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public void khoaTaiKhoan(String ma) {
        try {
            int maNhanVien = Integer.parseInt(ma);
            boolean flag = taiKhoanDAO.khoaTaiKhoan(maNhanVien);
            if (flag) {
                new MyDialog("Khoá tài khoản thành công!", MyDialog.SUCCESS_DIALOG);
                return;
            } else {
                new MyDialog("Khoá tài khoản thất bại!", MyDialog.ERROR_DIALOG);
                return;
            }
        }catch (Exception e){
            new MyDialog("Chưa chọn nhân viên để khóa tài khoản!", MyDialog.ERROR_DIALOG);
            return;
        }
    }

    public void moKhoaTaiKhoan(String ma) {
        int maNhanVien = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.moKhoaTaiKhoan(maNhanVien);
        if (flag) {
            new MyDialog("Mở khoá tài khoản thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mở khoá tài khoản thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if(!matKhauMoi.equals(nhapLaiMatKhau)) {
            new MyDialog("Mật khẩu mới không khớp!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            new MyDialog("Đổi thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mật khẩu cũ nhập sai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public int getTrangThai(String maNhanVien) {
        int ma = Integer.parseInt(maNhanVien);
        return taiKhoanDAO.getTrangThai(ma);
    }

    public TaiKhoan getTaiKhoan(int maNV){
        try{
            for(TaiKhoan tk : listTaiKhoan){
                if(tk.getMaNhanVien() == maNV){
                    return tk;
                }
            }
        }catch (Exception e){
        }
        return null;
    }

}


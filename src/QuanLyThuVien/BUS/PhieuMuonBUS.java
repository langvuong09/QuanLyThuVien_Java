package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DAO.PhieuMuonDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuMuonBUS {
    public ArrayList<PhieuMuon> listPhieuMuon = null;
    private PhieuMuonDAO pmDAO = new PhieuMuonDAO();
    public PhieuMuonBUS(){docListPhieuMuon();}
    public void docListPhieuMuon(){
        listPhieuMuon = pmDAO.getListPhieuMuon();
    }

    public ArrayList<PhieuMuon> getListPhieuMuon(){
        if(listPhieuMuon == null){
            docListPhieuMuon();
        }
        return listPhieuMuon;
    }

    public PhieuMuon getPhieuMuon(String ma){
        if (!ma.trim().equals("")){
            try{
                int maPM = Integer.parseInt(ma);
                for (PhieuMuon pm : listPhieuMuon){
                    if(pm.getMaPhieuMuon() == maPM){
                        return pm;
                    }
                }
            }catch (Exception e){
            }
        }
        return null;
    }

    public ArrayList<PhieuMuon> getPhieuMuonTheoMaDocGia(int ma){
        ArrayList<PhieuMuon> dspm = new ArrayList<>();
        for(PhieuMuon pm : listPhieuMuon){
            int maDocGia = pm.getMaDocGia();
            if(maDocGia == ma){
                dspm.add(pm);
            }
        }
        return dspm;
    }

    public int getMaPhieumuonnMoiNhat() {
        return pmDAO.getMaPhieuMuonMoiNhat();
    }

    public boolean nhapPhieuMuonTuExcel(String maPhieuMuon, String maDocGia, String maNhanVien, String ngayMuon, String ngayTra, String tongTien) {

        try {
            int maPM = Integer.parseInt(maPhieuMuon);
            int maDG = Integer.parseInt(maDocGia);
            int maNV = Integer.parseInt(maNhanVien);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateMuon = sdf.parse(ngayMuon);
            Date dateTra = sdf.parse(ngayTra);
            tongTien = tongTien.replace(",", "");
            long sumMoney = Long.parseLong(tongTien);

            PhieuMuon pm = new PhieuMuon();
            pm.setMaPhieuMuon(maPM);
            pm.setMaDocGia(maDG);
            pm.setMaNhanVien(maNV);
            pm.setNgayMuon(dateMuon);
            pm.setNgayTra(dateTra);
            pm.setTongTien(sumMoney);

            pmDAO.nhapPhieuMuonTuExcel(pm);
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themPhieuMuon(String DocGia, String ngayMuon, String ngayTra, String tongTien) {
        // Kiểm tra nhập liệu
        if (DocGia.trim().equals("")) {
            new MyDialog("Mã đọc giả không được để rỗng!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (ngayMuon.trim().equals("")) {
            new MyDialog("Ngày mượn không được để rỗng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayTra.trim().equals("")) {
            new MyDialog("Ngày trả không được để rỗng!", MyDialog.ERROR_DIALOG);
            return false;
        }

        // Kiểm tra định dạng ngày tháng năm
        if (!kiemTraNhapNgayThangNam(ngayMuon)) {
            new MyDialog("Sai định dạng ngày mượn! (VD: 20/01/2004)", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (!kiemTraNhapNgayThangNam(ngayTra)) {
            new MyDialog("Sai định dạng ngày trả! (VD: 20/01/2004)", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            // Chuyển đổi ngày mượn và ngày trả từ định dạng chuỗi sang Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateMuon = sdf.parse(ngayMuon);
            Date dateTra = sdf.parse(ngayTra);

            // Kiểm tra ngày trả không được trước ngày mượn
            if (dateTra.before(dateMuon)) {
                new MyDialog("Ngày trả không thể trước ngày mượn!", MyDialog.ERROR_DIALOG);
                return false;
            }

            // Chuyển đổi ngày mượn và ngày trả từ java.util.Date sang java.sql.Date
            java.sql.Date sqlDateMuon = new java.sql.Date(dateMuon.getTime());
            java.sql.Date sqlDateTra = new java.sql.Date(dateTra.getTime());

            // Chuyển đổi tổng tiền từ chuỗi sang long
            tongTien = tongTien.replace(",", "");
            long tongTienPM = Long.parseLong(tongTien);

            // Tạo đối tượng PhieuMuon mới và gọi phương thức themPhieuMuon từ DAO
            PhieuMuon pm = new PhieuMuon();
            pm.setMaDocGia(Integer.parseInt(DocGia));
            pm.setNgayMuon(sqlDateMuon);
            pm.setNgayTra(sqlDateTra);
            pm.setTongTien(tongTienPM);

            if (pmDAO.themPhieuMuon(pm)) {
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho tổng tiền!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean kiemTraNhapNgayThangNam(String input) {
        // Biểu thức chính quy mới cho định dạng "dd/MM/yyyy"
        String regex = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}$";

        if (input.matches(regex)) {
            String[] parts = input.split("/");
            int ngay = Integer.parseInt(parts[0]);
            int thang = Integer.parseInt(parts[1]);
            int nam = Integer.parseInt(parts[2]);

            int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
            int thangHienTai = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int ngayHienTai = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

            return true;
        }
        return false;
    }


//    public boolean nhapPhieuMuonTuExcel(String maDocGia, String maNhanVien, String ngayMuon, String ngayTra,String tongTien){
//    }
    // Còn tiếp

    public boolean xoaPhieuMuon(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn phiếu để xoá!", MyDialog.ERROR_DIALOG);
            return false;
        }

        int maPM = Integer.parseInt(ma);
        if(pmDAO.xoaPhieuMuon(maPM)){
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }

        new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        return false;
    }

    public boolean suaPhieuMuon(String maPhieuMuon, String maDocGia, String ngayMuon, String ngayTra, String tongTien){
        try {
            if (maPhieuMuon.trim().equals("")) {
                new MyDialog("Chưa chọn phiếu mượn để sửa!", MyDialog.ERROR_DIALOG);
                return false;
            }

            tongTien = tongTien.replace(",", "");
            int maPM = Integer.parseInt(maPhieuMuon);
            int maDG = Integer.parseInt(maDocGia);

            if (maDocGia.trim().equals("")) {
                new MyDialog("Mã đọc giả không được để rỗng!", MyDialog.ERROR_DIALOG);
                return false;
            }
            if (ngayMuon.trim().equals("")) {
                new MyDialog("Ngày mượn không được để rỗng!", MyDialog.ERROR_DIALOG);
                return false;
            }
            if (ngayTra.trim().equals("")) {
                new MyDialog("Ngày trả không được để rỗng!", MyDialog.ERROR_DIALOG);
                return false;
            }

            // Kiểm tra định dạng ngày tháng năm
            if (!kiemTraNhapNgayThangNam(ngayMuon)) {
                new MyDialog("Sai định dạng ngày mượn! (VD: 20/01/2004)", MyDialog.ERROR_DIALOG);
                return false;
            }
            if (!kiemTraNhapNgayThangNam(ngayTra)) {
                new MyDialog("Sai định dạng ngày trả! (VD: 20/01/2004)", MyDialog.ERROR_DIALOG);
                return false;
            }

            // Chuyển đổi ngày mượn và ngày trả từ định dạng chuỗi sang Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateMuon = sdf.parse(ngayMuon);
            Date dateTra = sdf.parse(ngayTra);

            // Kiểm tra ngày trả không được trước ngày mượn
            if (dateTra.before(dateMuon)) {
                new MyDialog("Ngày trả không thể trước ngày mượn!", MyDialog.ERROR_DIALOG);
                return false;
            }

            // Chuyển đổi ngày mượn và ngày trả từ java.util.Date sang java.sql.Date
            java.sql.Date sqlDateMuon = new java.sql.Date(dateMuon.getTime());
            java.sql.Date sqlDateTra = new java.sql.Date(dateTra.getTime());

            // Chuyển đổi tổng tiền từ chuỗi sang long
            tongTien = tongTien.replace(",", "");
            long tongTienPM = Long.parseLong(tongTien);

            // Tạo đối tượng PhieuMuon mới và gọi phương thức themPhieuMuon từ DAO
            PhieuMuon pm = new PhieuMuon();
            pm.setMaDocGia(Integer.parseInt(maDocGia));
            pm.setNgayMuon(sqlDateMuon);
            pm.setNgayTra(sqlDateTra);
            pm.setTongTien(tongTienPM);

            if (pmDAO.suaPhieuMuon(pm)) {
                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e) {
            new MyDialog("Nhập số hợp lệ cho tổng tiền!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

}

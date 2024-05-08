package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DAO.PhieuMuonDAO;
import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.BUS.DangNhapBUS;
import MyCustom.MyDialog;
import QuanLyThuVien.GUI.DangNhapGUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuMuonBUS {
    public ArrayList<PhieuMuon> listPhieuMuon = null;
    private PhieuMuonDAO pmDAO = new PhieuMuonDAO();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
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
            if(pm.getMaDocGia() == ma){
                dspm.add(pm);
            }
        }
        return dspm;
    }

    public int getMaPhieumuonMoiNhat() {
        return pmDAO.getMaPhieuMuonMoiNhat();
    }

    public boolean nhapPhieuMuonTuExcel(String docGia, String nhanVien, String ngayMuon, String ngayTra, String tongTien) {

        try {
            int maDG = docGiaBUS.getMaDocGia(docGia);
            int maNV = nhanVienBUS.getMaNhanVien(nhanVien);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateMuon = sdf.parse(ngayMuon);
            Date dateTra = sdf.parse(ngayTra);
            tongTien = tongTien.replace(",", "");
            long sumMoney = Long.parseLong(tongTien);

            PhieuMuon pm = new PhieuMuon();
            pm.setMaDocGia(maDG);
            pm.setMaNhanVien(maNV);
            pm.setNgayMuon(dateMuon);
            pm.setNgayTra(dateTra);
            pm.setTongTien(sumMoney);

            pmDAO.nhapPhieuMuonTuExcel(pm);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themPhieuMuon(String maPM, String DocGia, String ngayMuon, String ngayTra, String tongTien) {
        // Kiểm tra nhập liệu
        if (DocGia.trim().equals("")) {
            new MyDialog("Đọc giả không được để rỗng!", MyDialog.ERROR_DIALOG);
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
            int ma = Integer.parseInt(maPM);

            // Tạo đối tượng PhieuMuon mới và gọi phương thức themPhieuMuon từ DAO
            PhieuMuon pm = new PhieuMuon();
            pm.setMaPhieuMuon(ma);
            pm.setMaDocGia(docGiaBUS.getMaDocGia(DocGia));
            pm.setMaNhanVien(dangNhapGUI.maTaiKhoan());
            pm.setNgayMuon(sqlDateMuon);
            pm.setNgayTra(sqlDateTra);
            pm.setTongTien(tongTienPM);
//            System.out.println();
            if (pmDAO.themPhieuMuon(pm)) {
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Date timeHienTai() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, cộng thêm 1 để có giá trị đúng
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // Tạo đối tượng Date từ ngày tháng năm hiện tại
        cal.set(year, month - 1, day); // Tháng bắt đầu từ 0, trừ đi 1 để có giá trị đúng
        Date currentDate = cal.getTime();

        return currentDate;
    }

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

    public ArrayList<PhieuMuon> timKiemPhieuMuon(String tuKhoa){
        if(tuKhoa.equals("")){
            return listPhieuMuon;
        }
        tuKhoa = tuKhoa.toLowerCase();
        int khoa = Integer.parseInt(tuKhoa);
        ArrayList<PhieuMuon> dspm = new ArrayList<>();
        for(PhieuMuon pm : listPhieuMuon){
            if(pm.getMaPhieuMuon() == khoa){
                dspm.add(pm);
            }
        }
        return dspm;
    }
}

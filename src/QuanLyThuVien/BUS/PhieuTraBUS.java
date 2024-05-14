package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DTO.PhieuTra;
import QuanLyThuVien.DAO.PhieuTraDAO;
import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.BUS.DangNhapBUS;
import MyCustom.MyDialog;
import QuanLyThuVien.GUI.DangNhapGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PhieuTraBUS {
    public ArrayList<PhieuTra> listPhieuTra = null;
    private PhieuTraDAO ptDAO = new PhieuTraDAO();
    private PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    public PhieuTraBUS(){docListPhieuTra();}
    public void docListPhieuTra(){
        listPhieuTra = ptDAO.getListPhieuTra();
    }

    public ArrayList<PhieuTra> getListPhieuTra(){
        if(listPhieuTra == null){
            docListPhieuTra();
        }
        return listPhieuTra;
    }

    public PhieuTra getPhieuTra(String ma){
        if(!ma.trim().equals("")){
            try{
                int maPT = Integer.parseInt(ma);
                for(PhieuTra pt : listPhieuTra){
                    if(pt.getMaPhieuTra() == maPT){
                        return pt;
                    }
                }
            }catch (Exception e){
            }
        }
        return null;
    }

    public ArrayList<PhieuTra> getListPhieuTraTheoMaDocGia(String ma){
        int maDG = Integer.parseInt(ma);
        ArrayList<PhieuTra> dspt = new ArrayList<>();
        for (PhieuTra pt : listPhieuTra) {
            if (pt.getMaDocGia() == maDG) {
                dspt.add(pt);
            }
        }
        return dspt;
    }

    public int getMaPhieuTraMoiNhat(){
        return ptDAO.getMaPhieuTraMoiNhat();
    }

    public boolean nhapPhieuTraExcel(String maPM, String docGia, String nhanVien, String ngayTraThuc){
        try{
            int maPhieuMuon = Integer.parseInt(maPM);
            int maDG = docGiaBUS.getMaDocGia(docGia);
            int maNV = nhanVienBUS.getMaNhanVien(nhanVien);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date traThuc = sdf.parse(ngayTraThuc);

            PhieuTra pt = new PhieuTra();
            pt.setMaPhieuMuon(maPhieuMuon);
            pt.setMaDocGia(maDG);
            pt.setMaNhanVien(maNV);
            pt.setNgayTraThuc(traThuc);

            ptDAO.nhapPhieuTraTuExcel(pt);
            return true;
        }catch (Exception e){
        }
        return false;
    }

    public boolean themPhieuTra(String maPT, String maPM, String docGia, String ngayTraThuc){
        if(maPM.trim().equals("")){
            new MyDialog("Không được để trống mã phiếu mượn!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        try{
            int maT = Integer.parseInt(maPT);
            int maM = Integer.parseInt(maPM);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngay = sdf.parse(ngayTraThuc);
            java.sql.Date sqlNgay = new java.sql.Date(ngay.getTime());

            PhieuTra pt = new PhieuTra();
            pt.setMaPhieuTra(maT);
            pt.setMaPhieuMuon(maM);
            pt.setMaDocGia(docGiaBUS.getMaDocGia(docGia));
            pt.setMaNhanVien(dangNhapGUI.maTaiKhoan());
            pt.setNgayTraThuc(sqlNgay);

            if(ptDAO.themPhieuTra(pt)){
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                java.util.Date ngayMuonUtil = pmBUS.getPhieuMuon(maPM).getNgayMuon();
                java.sql.Date ngayMuon = new java.sql.Date(ngayMuonUtil.getTime());
                long sqlSoNgayMuon = sqlNgay.getTime() - ngayMuon.getTime();
                long soNgayMuon = sqlSoNgayMuon / (1000 * 60 * 60 * 24);
                if(soNgayMuon > 20){
                    new MyDialog("Cảnh báo số ngày mượn vượt quá hạn trả "+(soNgayMuon-20)+"ngày!!!", MyDialog.WARNING_DIALOG);
                }
                return true;
            }else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public boolean xoaPhieuTra(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn phiếu trả để xóa!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        int maPT = Integer.parseInt(ma);
        if(ptDAO.xoaPhieuTra(maPT)){
            new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public Date timeHienTai(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        cal.set(year, month - 1, day);
        Date cDate = cal.getTime();
        return cDate;
    }

    public ArrayList<PhieuTra> timKiemPhieuTra(String tuKhoa){
        if(tuKhoa.equals("")){
            return listPhieuTra;
        }
        tuKhoa = tuKhoa.toLowerCase();
        int khoa = Integer.parseInt(tuKhoa);
        ArrayList<PhieuTra> dspt = new ArrayList<>();
        for(PhieuTra pt : listPhieuTra){
            if(pt.getMaPhieuTra() == khoa){
                dspt.add(pt);
            }
        }
        return dspt;
    }

    public ArrayList<PhieuTra> timKiemTrongKhoang(Date min, Date max){
        ArrayList<PhieuTra> dspt = new ArrayList<>();
        try {
            for (PhieuTra pt : listPhieuTra) {
                Date ngayMuon = pmBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon();
                if (ngayMuon != null && ngayMuon.after(min) && ngayMuon.before(max)) {
                    dspt.add(pt);
                }
            }
            return dspt;
        } catch (Exception e){
        }
        return null;
    }
}

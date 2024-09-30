package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.SachDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class SachBUS {
    private ArrayList<Sach> listSach = null;
    private SachDAO sachDAO = new SachDAO();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private NXBBUS nxbBUS = new NXBBUS();
    private TacGiaBUS tacGiaBUS = new TacGiaBUS();

    public SachBUS() {
        docDanhSach();
    }

    public void docDanhSach(){
        this.listSach = sachDAO.getListSach();
    }


    public ArrayList<Sach> getListSach(){
        if(listSach == null){
            docDanhSach();
        }
        return listSach;
    }

    public ArrayList<Sach> getListSachTheoTenSach(String tenSach){
        if(tenSach.trim().equals("")){
            return listSach;
        }
        ArrayList<Sach> dss = new ArrayList<>();
        for(Sach s : listSach){
            if(s.getTenSach().toLowerCase().contains(tenSach.trim().toLowerCase())){
                dss.add(s);
            }
        }

        return dss;
    }

    public ArrayList<Sach> timKiemSach(String tuKhoa){
        if(tuKhoa.equals("")){
            return listSach;
        }
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<Sach> dss = new ArrayList<>();
        for (Sach s : listSach){
            String tenSach = s.getTenSach().toLowerCase();
            String maSach = s.getMaSach()+"";
            if(tenSach.contains(tuKhoa) || maSach.contains(tuKhoa)){
                dss.add(s);
            }
        }
        return dss;
    }

    public String getTenSach(int ma){
        for(Sach s : listSach){
            if(s.getMaSach() == ma){
                return s.getTenSach();
            }
        }
        return "";
    }

    public int getMaSach(String ten){
        for(Sach s : listSach){
            if(s.getTenSach().toLowerCase().contains(ten.trim().toLowerCase())){
                return s.getMaSach();
            }
        }
        return 0;
    }

    public Sach getSach(String ma){
        try {
            int maSach = Integer.parseInt(ma);
            for (Sach s : listSach) {
                if (s.getMaSach() == maSach)
                    return s;
            }
        }catch (Exception e){
        }
        return null;
    }

    public String getAnh(String ma){
        int maSach = Integer.parseInt(ma);
        return sachDAO.getAnh(maSach);
    }

    public void capNhatTrangThaiSach(String ma){
        sachDAO.capNhatTrangThaiSach(ma);
    }

    public void chonSach(String ma){
        sachDAO.chonSach(ma);
    }

    public boolean nhapSachExcel(String loai, String tacGia, String tenSach, String gia, String hinhAnh, String soLuong){
        try{
            String[] mLoai = loai.split(" ");
            int maLoai = Integer.parseInt(mLoai[0].trim());
//            String[] maTG = tacGia.split(" ");
//            int maTacGia = Integer.parseInt(maTG[0].trim());
            long giaSach = Long.parseLong(gia.replace(",",""));
            int sl = Integer.parseInt(soLuong);

            Sach sach = new Sach();
            sach.setMaLoaiSach(maLoai);
            sach.setTacGia(tacGia);
            sach.setTenSach(tenSach);
            sach.setGiaSach(giaSach);
            sach.setHinhAnh(hinhAnh);
            sach.setSoLuong(sl);

            sachDAO.nhapSachTuExcel(sach);
            return true;
        }catch (Exception e){
        }
        return false;
    }

    public boolean themSach(String loai, String tacGia, String tenSach, String gia, String hinhAnh){
        if(tenSach.trim().equals("")){
            new MyDialog("Không được để trống tên sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(tacGia.trim().equals("")){
            new MyDialog("Chưa nhập tác giả!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gia.trim().equals("")){
            new MyDialog("Không được để trống giá sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(loai.equals("0 - Chọn loại")){
            new MyDialog("Chưa chọn loại sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        String[] maLoai = loai.split(" ");
//        String[] maTG = tacGia.split(" ");
        try{
            int mLoai = Integer.parseInt(maLoai[0]);
//            int maTacGia = Integer.parseInt(maTG[0]);
            long giaSach = Long.parseLong(gia.replace(",",""));
            int sl = 0;

            Sach sach = new Sach();
            sach.setMaLoaiSach(mLoai);
            sach.setTacGia(tacGia);
            sach.setTenSach(tenSach);
            sach.setGiaSach(giaSach);
            sach.setHinhAnh(hinhAnh);
            sach.setSoLuong(sl);

            if(sachDAO.themSach(sach)){
                new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            }else {
                new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            new MyDialog("Nhập số hợp lệ cho giá sách!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean xoaSach(String ma){
        if(ma.trim().equals("")){
            new MyDialog("Chưa chọn sách để xóa!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        int maSach = Integer.parseInt(ma);
        if(sachDAO.xoaSach(maSach)){
            new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
            return true;
        }else {
            new MyDialog("Xóa thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaSach(String ma,String loai, String tacGia, String tenSach, String gia, String hinhAnh, String soLuong){
        if(tenSach.trim().equals("")){
            new MyDialog("Không được để trống tên sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(tacGia.trim().equals("")){
            new MyDialog("Chưa nhập tác giả!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(gia.trim().equals("")){
            new MyDialog("Không được để trống giá sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if(loai.equals("0 - Chọn loại")){
            new MyDialog("Chưa chọn loại sách!!!", MyDialog.ERROR_DIALOG);
            return false;
        }
        String[] maLoai = loai.split(" ");
        String[] maTG = tacGia.split(" ");
        try{
            int maSach = Integer.parseInt(ma);
            int mLoai = Integer.parseInt(maLoai[0]);
//            int maTacGia = Integer.parseInt(maTG[0]);
            long giaSach = Long.parseLong(gia.replace(",",""));
            int sl = Integer.parseInt(soLuong);

            Sach sach = new Sach();
            sach.setMaSach(maSach);
            sach.setMaLoaiSach(mLoai);
            sach.setTacGia(tacGia);
            sach.setTenSach(tenSach);
            sach.setGiaSach(giaSach);
            sach.setHinhAnh(hinhAnh);
            sach.setSoLuong(sl);

            if(sachDAO.suaSach(sach)){
                new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
                return true;
            }else {
                new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }catch (Exception e){
            new MyDialog("Nhập số hợp lệ cho giá sách!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }
}

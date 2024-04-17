package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.Loai;
import QuanLyThuVien.DAO.LoaiDAO;
import QuanLyThuVien.DTO.NXB;
import QuanLyThuVien.DAO.NXBDAO;
import QuanLyThuVien.DTO.TacGia;
import QuanLyThuVien.DAO.TacGiaDAO;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.SachDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class SachBUS {
    private ArrayList<Sach> listSach = null;
    private ArrayList<Sach> listSachMuon = null;
    private SachDAO sachDAO = new SachDAO();

    public SachBUS() {
        docDanhSach();
        docDanhSachMuon();
    }

    public void docDanhSach(){
        this.listSach = sachDAO.getListSach();
    }

    public void docDanhSachMuon(){
        this.listSachMuon = sachDAO.getListSachMuon();
    }

    public ArrayList<Sach> getListSach(){
        if(listSach == null){
            docDanhSach();
        }
        return listSach;
    }

    public ArrayList<Sach> getListSachMuon(){
        if(listSachMuon == null){
            docDanhSachMuon();
        }
        return listSachMuon;
    }

    public ArrayList<Sach> timKiemSach(String tuKhoa){
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
            if(s.getTenSach() == ten){
                return s.getMaSach();
            }
        }
        return 0;
    }

    public String getTenSachMuon(int ma){
        ArrayList<Sach> listTongHop = new ArrayList<>(listSachMuon); // Khởi tạo danh sách kết hợp từ listSachMuon

        // Thêm tất cả các phần tử từ listSach vào combinedList
        listTongHop.addAll(listSach);

        for(Sach s : listTongHop){
            if(s.getMaSach() == ma){
                return s.getTenSach();
            }
        }
        return "";
    }

}

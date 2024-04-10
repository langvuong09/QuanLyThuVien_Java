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

    private ArrayList<Loai> listLoai = null;
    private SachDAO sachDAO = new SachDAO();

    public void docDanhSach(){
        this.listSach = sachDAO.getListSach();
    }

    public ArrayList<Sach> getListSach(){
        if(listSach == null){
            docDanhSach();
        }
        return listSach;
    }

    public ArrayList<Sach> timKiemSach(String tuKhoa){
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<Sach> dss = new ArrayList<>();
        for (Sach s : listSach){
            String ten = s.getTenSach().toLowerCase();
            if(ten.contains(tuKhoa)){
                dss.add(s);
            }
        }
        return dss;
    }
}

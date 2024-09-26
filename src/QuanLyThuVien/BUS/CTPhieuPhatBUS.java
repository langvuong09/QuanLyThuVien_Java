package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.CTPhieuPhat;
import QuanLyThuVien.DAO.CTPhieuPhatDAO;
import QuanLyThuVien.DTO.PhieuPhat;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.SachDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class CTPhieuPhatBUS {
    private ArrayList<CTPhieuPhat> listCTPhieuPhat = null;
    private CTPhieuPhatDAO ctPhieuPhatDAO = new CTPhieuPhatDAO();
    public CTPhieuPhatBUS(){
        docDanhSach();
    }

    public void docDanhSach() {
        this.listCTPhieuPhat = ctPhieuPhatDAO.getListCTPhieuPhap();
    }

    public ArrayList<CTPhieuPhat> getListCTPhieuPhat(){
        if(listCTPhieuPhat == null){
            docDanhSach();
        }
        return listCTPhieuPhat;
    }

    public boolean luuCTPhieuPhat(CTPhieuPhat ctpp){
        return ctPhieuPhatDAO.themCTPhieuPhat(ctpp);
    }
}

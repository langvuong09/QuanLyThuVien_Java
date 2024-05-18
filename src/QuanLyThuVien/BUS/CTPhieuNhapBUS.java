package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.CTPhieuNhap;
import QuanLyThuVien.DAO.CTPhieuNhapDAO;
import QuanLyThuVien.DTO.PhieuNhap;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DAO.SachDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class CTPhieuNhapBUS {
    private ArrayList<CTPhieuNhap> listCTPhieuNhap = null;
    private CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAO();
    public CTPhieuNhapBUS(){
        docDanhSach();
    }

    public void docDanhSach(){
        this.listCTPhieuNhap = ctPhieuNhapDAO.getListPhieuNhap();
    }

    public ArrayList<CTPhieuNhap> getListCTPhieuNhap(){
        if(listCTPhieuNhap == null){
            docDanhSach();
        }
        return listCTPhieuNhap;
    }

    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMa(String maPN){
        int ma = Integer.parseInt(maPN);
        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
        for(CTPhieuNhap ctpn : listCTPhieuNhap){
            if(ctpn.getMaPhieuNhap() == ma){
                dsctpn.add(ctpn);
            }
        }
        return dsctpn;
    }

    public boolean luuCTPhieuNhap(CTPhieuNhap ctpn){
        return ctPhieuNhapDAO.themCTPhieuNhap(ctpn);
    }
}

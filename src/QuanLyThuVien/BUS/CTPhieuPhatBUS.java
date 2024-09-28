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

    public ArrayList<CTPhieuPhat> getListPhieuPhatTheoMa(int maPP){
        listCTPhieuPhat = ctPhieuPhatDAO.getListCTPhieuPhatTheoMa(maPP);
        return listCTPhieuPhat;
    }

    public boolean luuCTPhieuPhat(CTPhieuPhat ctpp){
        return ctPhieuPhatDAO.themCTPhieuPhat(ctpp);
    }

    public boolean nhapCTPhieuPhatExcel(String maPP, String maS, String maPS, String lyDo, String tienPhat){
        try{
            int maPhieuPhat = Integer.parseInt(maPP);
            int maSach = Integer.parseInt(maS);
            int maPhanSach = Integer.parseInt(maPS);
            long tienP = Long.parseLong(tienPhat);

            CTPhieuPhat ctpp = new CTPhieuPhat();
            ctpp.setMaPhieuPhat(maPhieuPhat);
            ctpp.setMaSach(maSach);
            ctpp.setMaPhanSach(maPhanSach);
            ctpp.setTienPhat(tienP);

            ctPhieuPhatDAO.nhapPhieuPhatTuExcel(ctpp);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean themCTPhieuPhat(int maPP, int maS, int maPS, String lyDo, long tienPhat){
        try {
            CTPhieuPhat ctpp = new CTPhieuPhat();
            ctpp.setMaPhieuPhat(maPP);
            ctpp.setMaSach(maS);
            ctpp.setMaPhanSach(maPS);
            ctpp.setLyDo(lyDo);
            ctpp.setTienPhat(tienPhat);
            return ctPhieuPhatDAO.themCTPhieuPhat(ctpp);
        }catch (Exception e){
            return false;
        }
    }
}

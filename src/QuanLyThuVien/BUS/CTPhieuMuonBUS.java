package QuanLyThuVien.BUS;

import MyCustom.MyDialog;
import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DAO.CTPhieuMuonDAO;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DAO.SachDAO;

import java.util.ArrayList;

public class CTPhieuMuonBUS {

    private ArrayList<CTPhieuMuon> listCTPhieuMuon;
    private CTPhieuMuonDAO ctPhieuMuonDAO = new CTPhieuMuonDAO();
    private SachDAO sachDAO = new SachDAO();
    public CTPhieuMuonBUS() {docListCTPhieuMuon();}
    public void docListCTPhieuMuon(){this.listCTPhieuMuon = ctPhieuMuonDAO.getListCTPhieuMuon();}
    public ArrayList<CTPhieuMuon> getListCTPhieuMuon(){return  listCTPhieuMuon;}

    public ArrayList<CTPhieuMuon> getListCTPhieuMuonTheoMaPM(String maPhieuMuon){
        int ma = Integer.parseInt(maPhieuMuon);
        ArrayList<CTPhieuMuon> dsctpm = new ArrayList<>();
        for(CTPhieuMuon ctpm : listCTPhieuMuon){
            if(ctpm.getMaPhieuMMuon() == ma){
                dsctpm.add(ctpm);
            }
        }
        return dsctpm;
    }

    public boolean themCTPhieuMuon(String maPhieuMuon, String maSach, String thanhTien){
        try {
            int maPM = Integer.parseInt(maPhieuMuon);
            int maS = Integer.parseInt(maSach);
            thanhTien = thanhTien.replace(",", "");

            CTPhieuMuon ctpm = new CTPhieuMuon();
            ctpm.setMaPhieuMMuon(maPM);
            ctpm.setMaSach(maS);
            long tienMuon = Long.parseLong(thanhTien);
            ctpm.setGiaTien(tienMuon);

            return ctPhieuMuonDAO.themCTPhieuMuon(ctpm);
        }catch (Exception e){
        }
        return false;
    }

    public boolean xoaCTPhieuMuon(String maPhieuMuon){
        try {
            int maPM = Integer.parseInt(maPhieuMuon);

            return ctPhieuMuonDAO.xoaCTPhieuMuon(maPM);
        }catch (Exception e){
        }
        return false;
    }

    public void chonSachMuon(String ma){
        boolean flag = sachDAO.chonSach(ma);
    }
}

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
    private PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    private SachBUS sachBUS = new SachBUS();
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
        int maPM = Integer.parseInt(maPhieuMuon);
        int maS = Integer.parseInt(maSach);
        thanhTien = thanhTien.replace(",","");

        CTPhieuMuon ctpm = new CTPhieuMuon();
        ctpm.setMaPhieuMMuon(maPM);
        ctpm.setMaSach(maS);
        long tienMuon = Long.parseLong(thanhTien);
        ctpm.setGiaTien(tienMuon);

        ctPhieuMuonDAO.themCTPhieuMuon(ctpm);
        return true;
    }

    public boolean xoaCTPhieuMuon(String maPhieuMuon){
        int maPM = Integer.parseInt(maPhieuMuon);
        CTPhieuMuon ctpm = new CTPhieuMuon();
        ctpm.setMaPhieuMMuon(maPM);

        ctPhieuMuonDAO.xoaCTPhieuMuon(maPM);

        return true;
    }

    public boolean xoaCTPhieuMuonTheoMa(String maPhieuMuon){
        int maPM = Integer.parseInt(maPhieuMuon);
        ctPhieuMuonDAO.xoaCTPhieuMuon(maPM);

        return true;
    }

    public void chonSachMuon(String ma){
        int maSach = Integer.parseInt(ma);
        boolean flag = sachDAO.chonSach(maSach);
    }
}

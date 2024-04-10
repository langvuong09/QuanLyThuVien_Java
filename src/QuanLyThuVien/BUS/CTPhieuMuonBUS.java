package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DAO.CTPhieuMuonDAO;

import java.util.ArrayList;

public class CTPhieuMuonBUS {

    private ArrayList<CTPhieuMuon> listCTPhieuMuon;
    private CTPhieuMuonDAO ctPhieuMuonDAO = new CTPhieuMuonDAO();
    private PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
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

    public void themCTPhieuMuon(String maPhieuMuon, String maSach, String thanhTien){
        int ma = phieuMuonBUS.getMaPhieumuonnMoiNhat();

        thanhTien = thanhTien.replace(",","");

        CTPhieuMuon ctpm = new CTPhieuMuon();
        ctpm.setMaPhieuMMuon(ma);
        ctpm.setMaSach(Integer.parseInt(maSach));
        ctpm.setGiaTien(Long.parseLong(thanhTien));

        ctPhieuMuonDAO.themCTPhieuMuon(ctpm);
    }
}

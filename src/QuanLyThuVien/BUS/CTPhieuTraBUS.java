package QuanLyThuVien.BUS;

import MyCustom.MyDialog;
import QuanLyThuVien.DTO.CTPhieuTra;
import QuanLyThuVien.DAO.CTPhieuTraDAO;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DAO.SachDAO;

import java.util.ArrayList;

public class CTPhieuTraBUS {
    private ArrayList<CTPhieuTra> listCTPhieuTra;
    private CTPhieuTraDAO ctPhieuTraDAO = new CTPhieuTraDAO();
    private SachDAO sachDAO = new SachDAO();
    public CTPhieuTraBUS() {
        docListCTPhieuTra();
    }
    public void docListCTPhieuTra(){
        this.listCTPhieuTra = ctPhieuTraDAO.getListCTPhieuTra();
    }
    public ArrayList<CTPhieuTra> getListCTPhieuTra(){
        return listCTPhieuTra;
    }

    public ArrayList<CTPhieuTra> getListCTPhieuTraTheoMaPT(String maPT){
        int maT = Integer.parseInt(maPT);
        ArrayList<CTPhieuTra> dsctpt = new ArrayList<>();
        for(CTPhieuTra ctpt : listCTPhieuTra){
            if(ctpt.getMaPhieuTra() == maT){
                dsctpt.add(ctpt);
            }
        }
        return dsctpt;
    }

    public boolean themCTPhieuTra(String maPhieuTra, String maSach){
        try {
            int maPT = Integer.parseInt(maPhieuTra);
            int maS = Integer.parseInt(maSach);

            CTPhieuTra ctpt = new CTPhieuTra();
            ctpt.setMaPhieuTra(maPT);
            ctpt.setMaSach(maS);

            return ctPhieuTraDAO.themCTPhieuTra(ctpt);
        }catch (Exception e){
        }
        return false;
    }
    public boolean xoaCTPhieuTra(String maPhieuTra){
        try {
            int maPT = Integer.parseInt(maPhieuTra);
            return ctPhieuTraDAO.xoaCTPhieuTra(maPT);
        }catch (Exception e){
        }
        return false;
    }
}

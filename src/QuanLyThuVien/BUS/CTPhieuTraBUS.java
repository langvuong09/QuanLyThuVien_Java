package QuanLyThuVien.BUS;

import MyCustom.MyDialog;
import QuanLyThuVien.DTO.CTPhieuMuon;
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
        if(maPT.equals("")){
            return listCTPhieuTra;
        }
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

    public void chonSachTra(String ma){
        sachDAO.capNhatTrangThaiSach(ma);
    }

    public ArrayList<CTPhieuTra> timKiemCTPhieuTra(String tuKhoa, String maPT){
        if(tuKhoa.equals("")){
            return getListCTPhieuTraTheoMaPT(maPT);
        }
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<CTPhieuTra> dsctpt = new ArrayList<>();
        for(CTPhieuTra ctpt : getListCTPhieuTraTheoMaPT(maPT)){
            String maSach = String.valueOf(ctpt.getMaSach());
            if(maSach.contains(tuKhoa)){
                dsctpt.add(ctpt);
            }
        }
        return dsctpt;
    }

    public ArrayList<CTPhieuTra> luaChon(String maPM) {
        if(maPM.equals("")){
            return listCTPhieuTra;
        }
        int ma = Integer.parseInt(maPM);
        return ctPhieuTraDAO.getListCTPhieuTraTheoMaPM(ma);
    }

    public boolean xacDinhCTPT(String maSach, String maPM){
        int maS = Integer.parseInt(maSach);
        int maM = Integer.parseInt(maPM);
        if(ctPhieuTraDAO.xacDinhCTPhieuTra(maS,maM)){
            return true;
        }else {
            return false;
        }
    }
}

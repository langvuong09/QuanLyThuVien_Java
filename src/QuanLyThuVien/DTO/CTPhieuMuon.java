package QuanLyThuVien.DTO;

public class CTPhieuMuon {

    private int maPhieuMMuon;
    private int maSach;
    private long giaTien;

    public CTPhieuMuon(){
    }

    public CTPhieuMuon(int maPhieuMMuon,int maSach,long giaTien){
        this.maPhieuMMuon = maPhieuMMuon;
        this.maSach = maSach;
        this.giaTien = giaTien;
    }

    public int getMaPhieuMMuon() {
        return maPhieuMMuon;
    }

    public void setMaPhieuMMuon(int maPhieuMMuon) {
        this.maPhieuMMuon = maPhieuMMuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }
}

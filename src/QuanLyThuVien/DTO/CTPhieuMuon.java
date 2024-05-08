package QuanLyThuVien.DTO;

public class CTPhieuMuon {

    private int maPhieuMuon;
    private int maSach;
    private long giaTien;

    public CTPhieuMuon(){
    }

    public CTPhieuMuon(int maPhieuMuon,int maSach,long giaTien){
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.giaTien = giaTien;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
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

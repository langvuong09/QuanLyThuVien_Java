package QuanLyThuVien.DTO;

public class CTPhieuMuon {

    private int maPhieuMuon;
    private int maSach;
    private int maPhanSach;
    private long giaTien;

    public CTPhieuMuon(){
    }

    public CTPhieuMuon(int maPhieuMuon,int maSach, int maPhanSach,long giaTien){
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.maPhanSach = maPhanSach;
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

    public int getMaPhanSach() {
        return maPhanSach;
    }

    public void setMaPhanSach(int maPhanSach) {
        this.maPhanSach = maPhanSach;
    }
}

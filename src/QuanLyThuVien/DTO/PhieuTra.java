package QuanLyThuVien.DTO;

import java.util.Date;

public class PhieuTra {

    private int maPhieuTra;
    private int maPhieuMuon;
    private int maDocGia;
    private int maNhanVien;
    private Date ngayTraThuc;

    public PhieuTra(){
    }

    public PhieuTra(int maPhieuTra,int maPhieuMuon,int maDocGia,int maNhanVien,Date ngayTraThuc){
        this.maPhieuTra = maPhieuTra;
        this.maDocGia = maDocGia;
        this.maNhanVien = maNhanVien;
        this.maPhieuMuon = maPhieuMuon;
        this.ngayTraThuc = ngayTraThuc;
    }

    public int getMaPhieuTra() {
        return maPhieuTra;
    }

    public void setMaPhieuTra(int maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public Date getNgayTraThuc() {
        return ngayTraThuc;
    }

    public void setNgayTraThuc(Date ngayTraThuc) {
        this.ngayTraThuc = ngayTraThuc;
    }
}

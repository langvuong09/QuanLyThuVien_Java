package QuanLyThuVien.DTO;

import java.util.Date;

public class PhieuPhat {
    private int maPhieuPhat;
    private int maPhieuTra;
    private int maDocGia;
    private int maNhanVien;
    private long thanhTien;

    public PhieuPhat(){
    }

    public PhieuPhat(int maPhieuPhat, int maPhieuTra, int maDocGia, int maNhanVien, long thanhTien){
        this.maPhieuPhat = maPhieuPhat;
        this.maPhieuTra = maPhieuTra;
        this.maDocGia = maDocGia;
        this.maNhanVien = maNhanVien;
        this.thanhTien = thanhTien;
    }

    public int getMaPhieuPhat() {
        return maPhieuPhat;
    }

    public void setMaPhieuPhat(int maPhieuPhat) {
        this.maPhieuPhat = maPhieuPhat;
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

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}

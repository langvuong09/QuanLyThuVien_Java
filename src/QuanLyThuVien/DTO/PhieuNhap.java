package QuanLyThuVien.DTO;

import java.util.Date;

public class PhieuNhap {
    private int maPhieuNhap;
    private int maNXB;
    private int maNhanVien;
    private Date ngayLap;
    private long tongTien;

    public PhieuNhap(){
    }

    public PhieuNhap(int maPhieuNhap, int maNXB, int maNhanVien, Date ngayLap, long tongTien){
        this.maPhieuNhap = maPhieuNhap;
        this.maNXB = maNXB;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }
}

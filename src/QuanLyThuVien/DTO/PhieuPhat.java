package QuanLyThuVien.DTO;

import java.util.Date;

public class PhieuPhat {
    private int maPhieuPhat;
    private int maPhieuTra;
    private int maSach;
    private int maPhanSach;
    private int maDocGia;
    private int maNhanVien;
    private String lyDo;
    private long thanhTien;

    public PhieuPhat(){
    }

    public PhieuPhat(int maPhieuPhat, int maPhieuTra, int maSach, int maPhanSach, int maDocGia, int maNhanVien, String lyDo, long thanhTien){
        this.maPhieuPhat = maPhieuPhat;
        this.maPhieuTra = maPhieuTra;
        this.maSach = maSach;
        this.maPhanSach = maPhanSach;
        this.maDocGia = maDocGia;
        this.maNhanVien = maNhanVien;
        this.lyDo = lyDo;
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

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaPhanSach() {
        return maPhanSach;
    }

    public void setMaPhanSach(int maPhanSach) {
        this.maPhanSach = maPhanSach;
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

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}

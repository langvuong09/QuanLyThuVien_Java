package QuanLyThuVien.DTO;

import java.util.Date;

public class PhieuMuon {

    private int maPhieuMuon;
    private int maDocGia;
    private int maNhanVien;
    private Date ngayMuon;
    private Date ngayTra;
    private long tongTien;

    public PhieuMuon(){
    }

    public PhieuMuon(int maPhieuMuon,int maDocGia,int maNhanVien,Date ngayMuon,Date ngayTra,long tongTien){
        this.maPhieuMuon = maPhieuMuon;
        this.maDocGia = maDocGia;
        this.maNhanVien = maNhanVien;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.tongTien = tongTien;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
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

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }
}

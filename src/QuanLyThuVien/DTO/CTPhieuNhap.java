package QuanLyThuVien.DTO;

public class CTPhieuNhap {
    private int maPhieuNhap;
    private int maSach;
    private int soLuong;
    private long gia;
    private long thanhTien;

    public CTPhieuNhap(){
    }

    public CTPhieuNhap(int maPhieuNhap, int maSach, long gia, int soLuong, long thanhTien){
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = thanhTien;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public long getThanhTien() {
        return soLuong*gia;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}

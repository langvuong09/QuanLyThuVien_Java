package QuanLyThuVien.DTO;

public class CTPhieuNhap {
    private int maPhieuNhap;
    private int maSach;
    private int maMin;
    private int maMax;
    private int soLuong;
    private long gia;
    private long thanhTien;

    public CTPhieuNhap(){
    }

    public CTPhieuNhap(int maPhieuNhap, int maSach, int maMin, int maMax, long gia, int soLuong, long thanhTien){
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.maMin = maMin;
        this.maMax = maMax;
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

    public int getMaMin() {
        return maMin;
    }

    public void setMaMin(int maMin) {
        this.maMin = maMin;
    }

    public int getMaMax() {
        return maMax;
    }

    public void setMaMax(int maMax) {
        this.maMax = maMax;
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

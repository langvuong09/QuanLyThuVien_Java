package QuanLyThuVien.DTO;

public class    PhanQuyen {

    private String quyen;
    private int qlSach;
    private int qlNhanVien;
    private int qlDocGia;
    private int thongKe;

    public PhanQuyen() {
    }

    public PhanQuyen(String quyen, int qlSach, int qlNhanVien, int qlDocGia, int thongKe) {
        this.quyen = quyen;
        this.qlSach = qlSach;
        this.qlNhanVien = qlNhanVien;
        this.qlDocGia = qlDocGia;
        this.thongKe = thongKe;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public int getQlSach() {
        return qlSach;
    }

    public void setQlSach(int qlSach) {
        this.qlSach = qlSach;
    }

    public int getQlNhanVien() {
        return qlNhanVien;
    }

    public void setQlNhanVien(int qlNhanVien) {
        this.qlNhanVien = qlNhanVien;
    }

    public int getQlDocGia() {
        return qlDocGia;
    }

    public void setQlDocGia(int qlDocGia) {
        this.qlDocGia = qlDocGia;
    }

    public int getThongKe() {
        return thongKe;
    }

    public void setThongKe(int thongKe) {
        this.thongKe = thongKe;
    }

}

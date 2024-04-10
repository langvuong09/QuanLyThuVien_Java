package QuanLyThuVien.DTO;

public class Sach {

    private int maSach;
    private int maLoaiSach;
    private int maNXB;
    private int maTacGia;
    private String tenSach;
    private long giaSach;
    private String ghiChu;
    private int trangThai;

    public Sach(){
    }

    public Sach(int maSach,int maLoaiSach,int maNXB,int maTacGia,String tenSach,long giaSach,String ghiChu,int trangThai){
        this.maSach = maSach;
        this.maLoaiSach = maLoaiSach;
        this.maNXB = maNXB;
        this.maTacGia = maTacGia;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(int maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public long getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(long giaSach) {
        this.giaSach = giaSach;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}

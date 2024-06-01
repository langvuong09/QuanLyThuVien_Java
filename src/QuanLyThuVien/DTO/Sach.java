package QuanLyThuVien.DTO;

public class Sach {

    private int maSach;
    private int maLoaiSach;
    private int maTacGia;
    private String tenSach;
    private long giaSach;
    private String hinhAnh;
    private int soLuong;

    public Sach(){
    }

    public Sach(int maSach,int maLoaiSach,int maTacGia,String tenSach,long giaSach,String hinhAnh,int soLuong){
        this.maSach = maSach;
        this.maLoaiSach = maLoaiSach;
        this.maTacGia = maTacGia;
        this.tenSach = tenSach;
        this.giaSach = giaSach;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

package QuanLyThuVien.DTO;

public class PhanSach {
    private int maPhanSach;
    private int maSach;
    private String trangThai;

    public PhanSach(){
    }

    public PhanSach(int maPhanSach, int maSach, String trangThai){
        this.maPhanSach = maPhanSach;
        this.maSach = maSach;
        this.trangThai = trangThai;
    }

    public int getMaPhanSach() {
        return maPhanSach;
    }

    public void setMaPhanSach(int maPhanSach) {
        this.maPhanSach = maPhanSach;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

package QuanLyThuVien.DTO;

public class CTKeSach {
    private int maKe;
    private int maSach;

    public CTKeSach(){
    }

    public CTKeSach(int maSach, int maKe){
        this.maSach = maSach;
        this.maKe = maKe;
    }

    public int getMaKe() {
        return maKe;
    }

    public void setMaKe(int maKhu) {
        this.maKe = maKe;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }
}

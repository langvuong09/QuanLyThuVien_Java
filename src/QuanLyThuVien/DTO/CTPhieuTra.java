package QuanLyThuVien.DTO;

public class CTPhieuTra {
    private int maPhieuTra;
    private int maSach;
    private int maPhanSach;

    public CTPhieuTra(){
    }

    public CTPhieuTra(int maPhieuTra,int maSach, int maPhanSach){
        this.maPhieuTra = maPhieuTra;
        this.maSach = maSach;
        this.maPhanSach = maPhanSach;
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
}

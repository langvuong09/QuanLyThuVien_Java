package QuanLyThuVien.DTO;

public class CTPhieuTra {
    private int maPhieuTra;
    private int maSach;

    public CTPhieuTra(){
    }

    public CTPhieuTra(int maPhieuTra,int maSach){
        this.maPhieuTra = maPhieuTra;
        this.maSach = maSach;
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
}

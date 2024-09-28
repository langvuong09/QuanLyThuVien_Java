package QuanLyThuVien.DTO;

public class CTPhieuPhat {
    private int maPhieuPhat;
    private int maSach;
    private int maPhanSach;
    private String lyDo;
    private long tienPhat;

    public CTPhieuPhat(){
    }

    public CTPhieuPhat(int maPhieuPhat, int maSach, int maPhanSach, String lyDo, long tienPhat){
        this.maPhieuPhat = maPhieuPhat;
        this.maSach = maSach;
        this.maPhanSach = maPhanSach;
        this.lyDo = lyDo;
        this.tienPhat = tienPhat;
    }

    public int getMaPhieuPhat() {
        return maPhieuPhat;
    }

    public void setMaPhieuPhat(int maPhieuPhat) {
        this.maPhieuPhat = maPhieuPhat;
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

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public long getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(long tienPhat) {
        this.tienPhat = tienPhat;
    }
}

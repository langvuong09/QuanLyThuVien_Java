package QuanLyThuVien.DTO;

public class KeSach {
    private int maKe;
    private int maKhu;

    public KeSach(){
    }

    public KeSach(int maKe, int maKhu){
        this.maKe = maKe;
        this.maKhu = maKhu;
    }

    public int getMaKe() {
        return maKe;
    }

    public void setMaKe(int maKe) {
        this.maKe = maKe;
    }

    public int getMaKhu() {
        return maKhu;
    }

    public void setMaKhu(int maKhu) {
        this.maKhu = maKhu;
    }
}

package QuanLyThuVien.DTO;

public class DocGia {
    private int maDocGia;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String SDT;
    private String gmail;

    public DocGia(){
    }

    public DocGia(int maDocGia, String ho, String ten, String gioiTinh, String SDT, String gmail){
        this.maDocGia = maDocGia;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.SDT = SDT;
        this.gmail = gmail;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}

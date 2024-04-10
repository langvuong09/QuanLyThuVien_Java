package QuanLyThuVien.BUS;

import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DAO.DocGiaDAO;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class DocGiaBUS {
    private ArrayList<DocGia> listDocGia = null;
    private DocGiaDAO docGiaDAO = new DocGiaDAO();
    public void docDanhSach() {this.listDocGia = docGiaDAO.getListDocGia();}

    public ArrayList<DocGia> getListDocGia(){
        if(listDocGia == null)
            docDanhSach();
        return listDocGia;
    }

    public ArrayList<DocGia> timKiemDocGia(String tuKhoa){
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<DocGia> dsdg = new ArrayList<>();
        for(DocGia dg : listDocGia){
            String ho = dg.getHo().toLowerCase();
            String ten = dg.getTen().toLowerCase();
            String SDT = dg.getSDT().toLowerCase();
            String gmail = dg.getGmail().toLowerCase();
            if(ho.contains(tuKhoa) || ten.contains(tuKhoa) || SDT.contains(tuKhoa) || gmail.contains(tuKhoa)){
                dsdg.add(dg);
            }
        }
        return dsdg;
    }
}

package QuanLyThuVien.DTO;

import java.util.ArrayList;

public class ThongKe {
    public int soLuongSach;
    public int soLuongDG;
    public int soLuongNV;
    public int soLuongPM;
    public int soLuongPT;
    public int soLuongPP;
    public int[] tongThuQuy;
    public int[] tongSachDuocMuon;

    public ThongKe(){
    }

    public ThongKe(int soLuongSach, int soLuongDG, int soLuongNV, int soLuongPM, int soLuongPT, int soLuongPP, int[] tongThuQuy, int[] tongSachDuocMuon){
        this.soLuongSach=soLuongSach;
        this.soLuongDG=soLuongDG;
        this.soLuongNV=soLuongNV;
        this.soLuongPM=soLuongPM;
        this.soLuongPT=soLuongPT;
        this.soLuongPP=soLuongPP;
        this.tongThuQuy=tongThuQuy;
        this.tongSachDuocMuon=tongSachDuocMuon;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public int getSoLuongDG() {
        return soLuongDG;
    }

    public void setSoLuongDG(int soLuongDG) {
        this.soLuongDG = soLuongDG;
    }

    public int getSoLuongNV() {
        return soLuongNV;
    }

    public void setSoLuongNV(int soLuongNV) {
        this.soLuongNV = soLuongNV;
    }

    public int getSoLuongPM() {
        return soLuongPM;
    }

    public void setSoLuongPM(int soLuongPM) {
        this.soLuongPM = soLuongPM;
    }

    public int getSoLuongPT() {
        return soLuongPT;
    }

    public void setSoLuongPT(int soLuongPT) {
        this.soLuongPT = soLuongPT;
    }

    public int getSoLuongPP() {
        return soLuongPP;
    }

    public void setSoLuongPP(int soLuongPP) {
        this.soLuongPP = soLuongPP;
    }

    public int getTongThuQuy(int quy) {
        return tongThuQuy[quy - 1];
    }

    public void setTongThuQuy(int[] tongThuQuy) {
        this.tongThuQuy = tongThuQuy;
    }

    public int[] getTongSachDuocMuon() {
        return tongSachDuocMuon;
    }

    public void setTongSachDuocMuon(int[] tongSachDuocMuon) {
        this.tongSachDuocMuon = tongSachDuocMuon;
    }

    public long getTongDoanhThu(){
        long tong=0;
        for(int i=0;i < tongThuQuy.length; i++){
            tong += tongThuQuy[i];
        }
        return tong;
    }
}

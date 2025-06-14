package QuanLyThuVien.BUS;

import QuanLyThuVien.DAO.ThongKeDAO;
import QuanLyThuVien.DTO.ThongKe;

import java.util.ArrayList;

public class ThongKeBUS {
    public ThongKeDAO thongKeDAO = new ThongKeDAO();

    public ThongKe thongKe(int nam) {
        return thongKeDAO.getThongKe(nam);
    }

    public int getDoanhThuThang(int thang, int nam) {
        return thongKeDAO.getDoanhThuThang(thang, nam);
    }






}

package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DAO.MyConnect;
import QuanLyThuVien.DTO.*;
import MyCustom.MyTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

public class DlgThemSachVaoKhuVuc extends  JDialog {
    private SachBUS sachBUS = new SachBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private TacGiaBUS tacGiaBUS = new TacGiaBUS();
    private KhuVucBUS khuVucBUS = new KhuVucBUS();
    public static Sach sachThem= null;

    public DlgThemSachVaoKhuVuc() {
        addControls();
        addEvents();
        loadDataLenTable();
        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblSach;
    protected DefaultTableModel dtmSach;
    private JButton btnChon;

    private void addControls(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Nhập mã hoặc tên sách để tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã");
        dtmSach.addColumn("Loại");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Giá");
        dtmSach.addColumn("Hình ảnh");
        dtmSach.addColumn("SL");
        tblSach = new MyTable(dtmSach);
        JScrollPane scrSach = new JScrollPane(tblSach);
        pnTable.add(scrSach, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        pnButton.add(btnChon);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonSach();
            }
        });
    }

    private void xuLyChonSach() {
        int row = tblSach.getSelectedRow();
        if (row > -1) {
            int ma = Integer.parseInt(tblSach.getValueAt(row, 0) + "");
            String loai = String.valueOf(tblSach.getValueAt(row, 1));
            int maLoai = loaiBUS.getMaLoai(loai);
            String tacGia = String.valueOf(tblSach.getValueAt(row, 2));
            int maTacGia = tacGiaBUS.getMaTacGia(tacGia);
            String ten = tblSach.getValueAt(row, 3) + "";
            String giaMuon = tblSach.getValueAt(row, 4).toString().replace(",", "");
            long gia = Long.parseLong(giaMuon);
            String hinhAnh = tblSach.getValueAt(row,5) + "";
            String soLuong = tblSach.getValueAt(row,6)+"";
            int sl = Integer.parseInt(soLuong);

            sachThem = new Sach(ma, maLoai, maTacGia, ten, gia, hinhAnh,sl);

        }
        this.dispose();
    }

    public boolean kiemTraSachTrenKe(int ma){
        for(CTKeSach ctks : khuVucBUS.listCTKeSach){
            if(ma == ctks.getMaSach()){
                return false;
            }
        }
        return true;
    }

    public void loadDataLenTable() {
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.getListSach();
        if (dss != null) {
            for (Sach s : dss) {
                if(kiemTraSachTrenKe(s.getMaSach())) {
                    Vector vec = new Vector();
                    vec.add(s.getMaSach());
                    String tenLoai = loaiBUS.getTenLoai(s.getMaLoaiSach());
                    vec.add(tenLoai);
                    String tenTacGia = tacGiaBUS.getTenTacGia(s.getMaTacGia());
                    vec.add(tenTacGia);
                    vec.add(s.getTenSach());
                    DecimalFormat formatter = new DecimalFormat("###,###");
                    String giaSach = formatter.format(s.getGiaSach());
                    vec.add(giaSach);
                    vec.add(s.getHinhAnh());
                    vec.add(s.getSoLuong());
                    dtmSach.addRow(vec);
                }
            }
        }
    }

    public void isKiemTraKhuVuc(int ma){

    }

    public void loadDataLenTable(String ma) {
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.getListSach();
        if (dss != null) {
            for (Sach s : dss) {
                Vector vec = new Vector();
                vec.add(s.getMaSach());
                String tenLoai = loaiBUS.getTenLoai(s.getMaLoaiSach());
                vec.add(tenLoai);
                String tenTacGia = tacGiaBUS.getTenTacGia(s.getMaTacGia());
                vec.add(tenTacGia);
                vec.add(s.getTenSach());
                DecimalFormat formatter = new DecimalFormat("###,###");
                String giaSach = formatter.format(s.getGiaSach());
                vec.add(giaSach);
                vec.add(s.getGiaSach());
                vec.add(s.getHinhAnh());
                dtmSach.addRow(vec);
            }
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }
}

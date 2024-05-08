package QuanLyThuVien.GUI;
import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import QuanLyThuVien.BUS.*;
import MyCustom.MyTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import static Main.Main.changLNF;

import MyCustom.TransparentPanel;
import QuanLyThuVien.DTO.NhanVien;
import QuanLyThuVien.DTO.PhieuMuon;

public class PnQuanLyNhanVienGUI extends JPanel{
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    public PnQuanLyNhanVienGUI() {
        changLNF("Windows");
        addConTrolsNhanVien();
        addEventsNhanVien();

    }
    NhanVienBUS nvBUS = new NhanVienBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblNhanVien;
    CardLayout cardNhanVienGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    DefaultTableModel dtmNhanVien;
    JTextField txtIDNhanVien, txtTenNhanVien,txtHoNhanVien, txtSDT, txtGmail, txtGioiTinh, txtTimKiem;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim;
    JLabel lblTabbedNhanVien;
    JRadioButton jRBmuon,jRBchuamuon;
    JComboBox jComboBox1;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");

    private void addConTrolsNhanVien(){
        Font font = new Font("Tahoma", Font.PLAIN,16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL Nhân Viên
        =========================================================================
         */

        JPanel pnTableNhanVien = new TransparentPanel();
        pnTableNhanVien.setLayout(new BorderLayout());

        JPanel pnTitleNhanVien = new TransparentPanel();
        JLabel lblTitleNhanVien = new JLabel("Quản lý nhân viên");
        lblTitleNhanVien.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleNhanVien.add(lblTitleNhanVien);
        pnTitleNhanVien.add(btnReset);
        pnTableNhanVien.add(pnTitleNhanVien, BorderLayout.NORTH);
        //=================PANEL INPUT===========
//        int x =15, y=15;
        txtIDNhanVien = new JTextField();
        txtHoNhanVien = new JTextField();
        txtTenNhanVien = new JTextField();
        txtGmail = new JTextField();
        txtGioiTinh = new JTextField();
        txtSDT = new JTextField();
        txtTimKiem = new JTextField();
        txtIDNhanVien.setText("");
        txtHoNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtGmail.setText("");
        txtGioiTinh.setText("");
        txtSDT.setText("");

        //=================Thông tin nhân viên==============

        JPanel pnThongTinNhanVien = new TransparentPanel();
        pnThongTinNhanVien.setLayout(null);

        JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
        lblMaNhanVien.setFont(font);
        txtIDNhanVien.setFont(font);
        txtIDNhanVien.setEditable(false);
        lblMaNhanVien.setBounds(20,40,150,25);
        txtIDNhanVien.setBounds(200,40,320,25);

        JLabel lblHoNhanVien = new JLabel("Họ nhân viên:");
        lblHoNhanVien.setFont(font);
        txtHoNhanVien.setFont(font);
        txtHoNhanVien.setEditable(true);
        lblHoNhanVien.setBounds(20,80,150,25);
        txtHoNhanVien.setBounds(200,80,320,25);

        JLabel lblTenNhanVien = new JLabel("Tên nhân viên:");
        lblTenNhanVien.setFont(font);
        txtTenNhanVien.setFont(font);
        txtTenNhanVien.setEditable(true);
        lblTenNhanVien.setBounds(20,120,150,25);
        txtTenNhanVien.setBounds(200,120,320,25);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(font);
        txtGioiTinh.setFont(font);
        txtGioiTinh.setEditable(true);
        lblGioiTinh.setBounds(20,200,150,25);
        txtGioiTinh.setBounds(200,200,320,25);

        JLabel lblGmail = new JLabel("Gmail:");
        lblGmail.setFont(font);
        txtGmail.setFont(font);
        txtGmail.setEditable(true);
        lblGmail.setBounds(20,240,150,25);
        txtGmail.setBounds(200,240,320,25);

        JLabel lblSDT = new JLabel("SDT:");
        lblSDT.setFont(font);
        txtSDT.setFont(font);
        txtSDT.setEditable(true);
        lblSDT.setBounds(20,160,150,25);
        txtSDT.setBounds(200,160,320,25);

        JLabel lblTimKiem = new JLabel("Tên nhân viên cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20,300,300,25);
        txtTimKiem.setBounds(210,300,350,25);

        pnThongTinNhanVien.add(lblMaNhanVien);
        pnThongTinNhanVien.add(txtIDNhanVien);
        pnThongTinNhanVien.add(lblHoNhanVien);
        pnThongTinNhanVien.add(txtHoNhanVien);
        pnThongTinNhanVien.add(lblTenNhanVien);
        pnThongTinNhanVien.add(txtTenNhanVien);
        pnThongTinNhanVien.add(lblGioiTinh);
        pnThongTinNhanVien.add(txtGioiTinh);
        pnThongTinNhanVien.add(lblGmail);
        pnThongTinNhanVien.add(txtGmail);
        pnThongTinNhanVien.add(lblSDT);
        pnThongTinNhanVien.add(txtSDT);
        pnThongTinNhanVien.add(lblTimKiem);
        pnThongTinNhanVien.add(txtTimKiem);

        //=================BUTTON===============

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnSua = new JButton("Sửa");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnSua.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnSua.setBounds(55,350,110,40);
        btnThem.setBounds(170,350,110,40);
        btnXoa.setBounds(285,350,110,40);
        btnTim.setBounds(400,350,110,40);
        btnXuatExcel.setBounds(515,350,110,40);
        btnNhapExcel.setBounds(630,350,110,40);

        pnThongTinNhanVien.add(btnSua);
        pnThongTinNhanVien.add(btnThem);
        pnThongTinNhanVien.add(btnXoa);
        pnThongTinNhanVien.add(btnTim);
        pnThongTinNhanVien.add(btnXuatExcel);
        pnThongTinNhanVien.add(btnNhapExcel);

        pnTableNhanVien.add(pnThongTinNhanVien);

        //====================Bảng nhân viên====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã");
        dtmNhanVien.addColumn("Họ");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("SDT");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Gmail");
        tblNhanVien = new MyTable(dtmNhanVien);

        tblNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuMuon = tblNhanVien.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(150);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(120);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(100);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(230);

        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        scrTblNhanVien.setPreferredSize(new Dimension(900,150));

        scrTblNhanVien.setBounds(0,410,820,155);
        //</editor-fold>
        pnThongTinNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);

//        loadDataLenBangNhanVien();

        pnTableNhanVien.add(pnThongTinNhanVien);
        //=======================================================
        this.add(pnTableNhanVien);
    }

    private void addEventsNhanVien(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                loadDataLenBangNhanVien();
                txtIDNhanVien.setText("");
                txtHoNhanVien.setText("");
                txtTenNhanVien.setText("");
                txtGioiTinh.setText("");
                txtGmail.setText("");
                txtSDT.setText("");
            }
        });
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblNhanVien();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xulySuaNhanVien();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyThemNhanVien();
                txtIDNhanVien.setText("");
                txtHoNhanVien.setText("");
                txtTenNhanVien.setText("");
                txtGioiTinh.setText("");
                txtGmail.setText("");
                txtSDT.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyXoaNhanVien();
                txtIDNhanVien.setText("");
                txtHoNhanVien.setText("");
                txtTenNhanVien.setText("");
                txtGioiTinh.setText("");
                txtGmail.setText("");
                txtSDT.setText("");
            }
        });
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTimKiem();
            }
        });
        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTimKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapFileExcel();
            }
        });
//        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                search();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                search();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                search();
//            }
//        });
    }

    private void xuLyNhapFileExcel(){
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblNhanVien);

        int row = tblNhanVien.getRowCount();
        for (int i = 0; i < row; i++) {
            String Ma = tblNhanVien.getValueAt(i, 1) + "";
            String Ho = tblNhanVien.getValueAt(i, 2) + "";
            String Ten = tblNhanVien.getValueAt(i, 3) + "";
            String Sdt = tblNhanVien.getValueAt(i, 4) + "";
            String Gtinh = tblNhanVien.getValueAt(i, 5) + "";
            String Gmail = tblNhanVien.getValueAt(i, 6) + "";


            nvBUS.nhapNhanVienTuExcel(Ma, Ho, Ten, Sdt, Gtinh, Gmail);
        }
    }

    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblNhanVien);
    }

    private void xuLyClickTblNhanVien(){
        int row = tblNhanVien.getSelectedRow();
        if(row > -1){
            String maNhanVien =tblNhanVien.getValueAt(row,0)+"";
            String hoNhanVien =tblNhanVien.getValueAt(row,1)+"";
            String tenNhanVien =tblNhanVien.getValueAt(row,2)+"";
            String sdt =tblNhanVien.getValueAt(row,3)+"";
            String gioiTinh =tblNhanVien.getValueAt(row,4)+"";
            String gmail = tblNhanVien.getValueAt(row, 5)+"";

            txtIDNhanVien.setText(maNhanVien);
            txtHoNhanVien.setText(hoNhanVien);
            txtTenNhanVien.setText(tenNhanVien);
            txtGioiTinh.setText(gioiTinh);
            txtGmail.setText(gmail);
            txtSDT.setText(sdt);
//            txtGmail.setText(tongTien.replace(",",""));
//            loadDataLenBangCTPhieuMuon(txtMaPhieuMuon.getText());
        }
    }

//    private void loadDataLenBangNhanVien(){
//        nvBUS.docListNhanVien();
//        dtmNhanVien.setRowCount(0);
//
//        ArrayList<NhanVien> dsnv = nvBUS.getListNhanVien();
//
//
//        for (NhanVien nv : dsnv) {
//            Vector vec = new Vector();
//            vec.add(nv.getMaNhanVien());
//            String hoNV = nhanVienBUS.getHoNhanVien(nv.getMaNhanVien());
//            vec.add(hoNV);
//            String tenNV = nhanVienBUS.getTenNhanVien(nv.getMaNhanVien());
//            vec.add(tenNV);
//            String gtinhNV = nhanVienBUS.getGioiTinhNhanVien(nv.getMaNhanVien());
//            vec.add(gtinhNV);
//            String gmailNV = nhanVienBUS.getGmailNhanVien(nv.getMaNhanVien());
//            vec.add(gmailNV);
//            String sdtNV = nhanVienBUS.getSdtNhanVien(nv.getMaNhanVien());
//            vec.add(sdtNV);
//            dtmNhanVien.addRow(vec);
//        }
//    }
//
//    private void xuLyThemNhanVien(){
//        boolean flag = nhanVienBUS.themNhanVien(
//                txtHoNhanVien.getText(),
//                txtTenNhanVien.getText(),
//                txtSDT.getText(),
//                txtGioiTinh.getText(),
//                txtGmail.getText());
//
//        nhanVienBUS.docListNhanVien();
//        if(flag) {
//            loadDataLenBangNhanVien();
//        }
//    }
//
//    private void xulySuaNhanVien(){
//        if (txtIDNhanVien.getText()=="") {
//            NhanVien nv = new NhanVien(Integer.valueOf(txtIDNhanVien.getText()),
//                    txtHoNhanVien.getText(),
//                    txtTenNhanVien.getText(),
//                    txtSDT.getText(),
//                    txtGioiTinh.getText(),
//                    txtGmail.getText(),
//                    1);
//            boolean flag = nhanVienBUS.suaNhanVien(txtIDNhanVien.getText(), nv
//            );
//
//            nhanVienBUS.docListNhanVien();
//            if (flag) {
//                loadDataLenBangNhanVien();
//            }
//        }
//    }
//
//    private void xuLyXoaNhanVien(){
//        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
//        if (dlg.OK_OPTION == dlg.getAction()) {
//            boolean flag = nvBUS.xoaNhanVien(txtIDNhanVien.getText());
//            loadDataLenBangNhanVien();
////            timSachGUI.loadDataLenTable();
//        }
//    }
//
//    private void xuLyTimKiem(){
////        String nhanVien = txtTimKiem.getText();
////        dtmNhanVien.setRowCount(0);
////        ArrayList<NhanVien> dsnv = nhanVienBUS.timKiemNhanVien(nhanVien);
////        for (NhanVien nv : dsnv) {
////            Vector vec = new Vector();
////            vec.add(nv.getMaNhanVien());
////            String hoNV = nhanVienBUS.getHoNhanVien(nv.getMaNhanVien());
////            vec.add(hoNV);
////            String tenNV = nhanVienBUS.getTenNhanVien(nv.getMaNhanVien());
////            vec.add(tenNV);
////            String gtinhNV = nhanVienBUS.getGioiTinhNhanVien(nv.getMaNhanVien());
////            vec.add(gtinhNV);
////            String gmailNV = nhanVienBUS.getGmailNhanVien(nv.getMaNhanVien());
////            vec.add(gmailNV);
////            String sdtNV = nhanVienBUS.getSdtNhanVien(nv.getMaNhanVien());
////            vec.add(sdtNV);
////            dtmNhanVien.addRow(vec);
////        }
////        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dsnv.size(), MyDialog.INFO_DIALOG);
//    }
//
//    private void xuLyTimNhanVien(){
//        timNhanVienGUI.setVisible(true);
//        if (timNhanVienGUI.nhanVienTimDuoc != null) {
//            txtTenNhanVien.setText(timNhanVienGUI.nhanVienTimDuoc.getHo() + " " + timNhanVienGUI.nhanVienTimDuoc.getTen());
//        }
//    }
//
//    private boolean kiemTraNhanVien(DefaultTableModel dtmNhanVien, String ma){
//        int rowCount = dtmNhanVien.getRowCount();
//        for (int i = 0; i < rowCount; i++) {
//            int maNhanvien = (int) dtmNhanVien.getValueAt(i, 0);
//            String maPM = String.valueOf(maNhanvien);
//            String ho = (String) dtmNhanVien.getValueAt(i, 1);
//            String ten = (String) dtmNhanVien.getValueAt(i, 2);
//            String gioiTinh = (String) dtmNhanVien.getValueAt(i, 3);
//            String gmail = (String) dtmNhanVien.getValueAt(i,4);
//            String sdt = (String) dtmNhanVien.getValueAt(i,5);
//            if(ma.trim().equals(maPM)){
//                new MyDialog("Nhân viên đã tồn tại!!!",MyDialog.ERROR_DIALOG);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void search() {
//        String tuKhoa = txtTimKiem.getText().trim();
//        loadDataLenTable(tuKhoa); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
//    }
//
//    private void loadDataLenTable(String tuKhoa) {
//        dtmNhanVien.setRowCount(0);
//        ArrayList<NhanVien> dsnv = nhanVienBUS.timKiemNhanVien(tuKhoa);
//        for (NhanVien nv : dsnv) {
//            Vector vec = new Vector();
//            vec.add(nv.getMaNhanVien());
//            vec.add(nv.getHo());
//            vec.add(nv.getTen());
//            vec.add(nv.getGioiTinh());
//            vec.add(nv.getGmail());
//            vec.add(nv.getSDT());
//            dtmNhanVien.addRow(vec);
//        }
//    }

//    public void xuLyXuatNhanVien(){
//        ArrayList<Vector> dsNhanVien = new ArrayList<>();
//        int row = tblNhanVien.getRowCount();
//        int count = 0;
//        if(row == 0) return;
//        if(txtIDNhanVien.getText().equals("")){
//            new MyDialog("Chưa chọn nhân viên để in!!!",MyDialog.ERROR_DIALOG);
//            return;
//        }
////        for(int i=0;i<row_pm;i++){
////            if(txtMaPhieuMuon.getText().equals(tblPhieuMuon.getValueAt(i,0))){
////                count += 1;
////            }
////        }
////        if(count != 0){
////            new MyDialog("Phiếu mượn chưa được tạo!!!",MyDialog.ERROR_DIALOG);
////            return;
////        }
//        for(int i=0;i<row;i++){
//            Vector vec = new Vector();
//            vec.add(tblNhanVien.getValueAt(i,0));
//            vec.add(tblNhanVien.getValueAt(i,1));
//            vec.add(tblNhanVien.getValueAt(i,2));
//            vec.add(tblNhanVien.getValueAt(i,3));
//            vec.add(tblNhanVien.getValueAt(i,4));
//            dsNhanVien.add(vec);
//        }
//        int maNV = Integer.parseInt(txtIDNhanVien.getText());
//        XuatNhanVienGUI nhanVienGUI = new XuatNhanVienGUI(dsNhanVien, maNV,
//                txtHoNhanVien.getText(),txtTenNhanVien.getText(),txtGioiTinh.getText(),txtGmail.getText(),txtSDT.getText());
//        nhanVienGUI.setVisible(true);
//    }
}

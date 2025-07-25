package QuanLyThuVien.GUI;


import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import QuanLyThuVien.BUS.*;
import MyCustom.MyTable;
import QuanLyThuVien.DTO.TaiKhoan;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import static Main.Main.changLNF;

import MyCustom.TransparentPanel;
import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DTO.NhanVien;
import QuanLyThuVien.BUS.NhanVienBUS;
import QuanLyThuVien.DTO.PhanQuyen;

public class PnQuanLyNhanVienGUI extends JPanel{
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
    public PnQuanLyNhanVienGUI() {
        changLNF("Windows");
        addConTrolsNhanVien();
        addEventsNhanVien();
        addEventsPhanQuyen();

    }
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblNhanVien;
    CardLayout cardNhanVienGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    DefaultTableModel dtmNhanVien;
    JTextField txtIDNhanVien, txtTenNhanVien,txtHoNhanVien, txtSDT, txtChucVu, txtTimKiem;
    JTextArea txtGmail;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnCapTK, btnMKQuyen, btnKhoa;
    JLabel lblTabbedNhanVien, lblTabbedQuyen;
    JRadioButton rdbNam, rdbNu;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/Manager-GUI/tabbed-btn.png");

    private ImageIcon scaleIcon(String iconPath, int width, int height) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void addConTrolsNhanVien(){
        Font font = new Font("Tahoma", Font.PLAIN,20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1290;
        int h = 740;

        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(0xA5FDEC));

        //<editor-fold defaultstate="collapsed" desc="Panel Tab Nhân viên & Quyền">
        Font fonts = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));


        lblTabbedNhanVien = new JLabel("Nhân viên");
        lblTabbedNhanVien.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhanVien.setIcon(tabbedSelected);
        lblTabbedNhanVien.setBounds(2, 0, 140, 41);
        lblTabbedNhanVien.setFont(fonts);
        lblTabbedNhanVien.setForeground(Color.black);
        lblTabbedNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuyen = new JLabel("Quyền");
        lblTabbedQuyen.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuyen.setIcon(tabbedDefault);
        lblTabbedQuyen.setBounds(143, 0, 140, 41);
        lblTabbedQuyen.setFont(fonts);
        lblTabbedQuyen.setForeground(Color.black);
        lblTabbedQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedNhanVien);
        pnTop.add(lblTabbedQuyen);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

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
        ImageIcon originalIcon = new ImageIcon("image/img_qltv/icon_reset.png");
        Image img = originalIcon.getImage(); // Lấy Image từ ImageIcon
        Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
        ImageIcon scaledIcon = new ImageIcon(scaledImg); // Tạo ImageIcon mới với kích thước mới

        btnReset = new JButton(scaledIcon); // Gán ImageIcon mới cho JButton
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleNhanVien.add(lblTitleNhanVien);
        pnTitleNhanVien.add(btnReset);
        pnTableNhanVien.add(pnTitleNhanVien, BorderLayout.NORTH);
        //=================PANEL INPUT===========
        int x =20;
        txtIDNhanVien = new JTextField(x);
        txtHoNhanVien = new JTextField(x);
        txtTenNhanVien = new JTextField(x);
        txtGmail = new JTextArea();
        rdbNam = new JRadioButton("Nam");
        rdbNu = new JRadioButton("Nữ");
        txtSDT = new JTextField(x);
        txtChucVu = new JTextField(x);
        txtTimKiem = new JTextField(x);

        //=================Thông tin nhân viên==============

        JPanel pnThongTinNhanVien = new TransparentPanel();
        pnThongTinNhanVien.setLayout(null);

        JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
        lblMaNhanVien.setFont(font);
        txtIDNhanVien.setFont(font);
        txtIDNhanVien.setEditable(false);
        lblMaNhanVien.setBounds(20,20,150,25);
        txtIDNhanVien.setBounds(170,20,300,40);

        JLabel lblHoNhanVien = new JLabel("Họ nhân viên:");
        lblHoNhanVien.setFont(font);
        txtHoNhanVien.setFont(font);
        lblHoNhanVien.setBounds(20,70,150,25);
        txtHoNhanVien.setBounds(170,70,300,40);

        JLabel lblTenNhanVien = new JLabel("Tên nhân viên:");
        lblTenNhanVien.setFont(font);
        txtTenNhanVien.setFont(font);
        lblTenNhanVien.setBounds(20,120,150,25);
        txtTenNhanVien.setBounds(170,120,300,40);

        JLabel lblChucVu = new JLabel("Chức vụ:");
        lblChucVu.setFont(font);
        txtChucVu.setFont(font);
        lblChucVu.setBounds(20,170,150,25);
        txtChucVu.setBounds(170,170,300,40);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(font);
        rdbNam.setFont(font);
        rdbNu.setFont(font);
        ButtonGroup group = new ButtonGroup();
        group.add(rdbNam);
        group.add(rdbNu);
        lblGioiTinh.setBounds(700,20,150,25);
        rdbNam.setBounds(800,20,70,25);
        rdbNu.setBounds(880,20,70,25);

        JLabel lblSDT = new JLabel("SDT:");
        lblSDT.setFont(font);
        txtSDT.setFont(font);
        lblSDT.setBounds(700,70,150,25);
        txtSDT.setBounds(770,70,300,40);

        JLabel lblGmail = new JLabel("Gmail:");
        lblGmail.setFont(font);
        txtGmail.setFont(font);
        txtGmail.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtGmail);
        lblGmail.setBounds(700,120,150,25);
        scrollPane.setBounds(770,120,300,40);

        JLabel lblTimKiem = new JLabel("Tên nhân viên cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(100,225,300,25);
        txtTimKiem.setBounds(350,220,800,45);

        pnThongTinNhanVien.add(lblMaNhanVien);
        pnThongTinNhanVien.add(txtIDNhanVien);
        pnThongTinNhanVien.add(lblHoNhanVien);
        pnThongTinNhanVien.add(txtHoNhanVien);
        pnThongTinNhanVien.add(lblTenNhanVien);
        pnThongTinNhanVien.add(txtTenNhanVien);
        pnThongTinNhanVien.add(lblGioiTinh);
        pnThongTinNhanVien.add(rdbNam);
        pnThongTinNhanVien.add(rdbNu);
        pnThongTinNhanVien.add(lblGmail);
        pnThongTinNhanVien.add(scrollPane);
        pnThongTinNhanVien.add(lblSDT);
        pnThongTinNhanVien.add(txtSDT);
        pnThongTinNhanVien.add(lblChucVu);
        pnThongTinNhanVien.add(txtChucVu);
        pnThongTinNhanVien.add(lblTimKiem);
        pnThongTinNhanVien.add(txtTimKiem);

        //=================BUTTON===============

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnSua = new JButton("Sửa");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnCapTK = new JButton("Cấp tài khoản");
        btnMKQuyen = new JButton("Mật khẩu/Quyền");
        btnKhoa = new JButton("Khóa tài khoản");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnCapTK.setFont(fontButton);
        btnMKQuyen.setFont(fontButton);
        btnKhoa.setFont(font);





        btnThem.setIcon(scaleIcon("image/img_qltv/icon_them.png", 30, 30));
        btnXoa.setIcon(scaleIcon("image/img_qltv/icon_xoa.png", 30, 30));
        btnTim.setIcon(scaleIcon("image/img_qltv/icon_search.png", 30, 30));
        btnSua.setIcon(scaleIcon("image/img_qltv/icon_sua.png", 30, 30));
        btnXuatExcel.setIcon(scaleIcon("image/img_qltv/icon_excel.png", 30, 30));
        btnNhapExcel.setIcon(scaleIcon("image/img_qltv/icon_excel.png", 30, 30));
        btnCapTK.setIcon(scaleIcon("image/Pencil-icon.png", 30, 30));
        

//

        // Assuming panel width is 960px
        int startingX = 50; // Starting point for the first button
        int buttonWidth = 110; // Width of each button
        int spaceBetweenButtons = 50; // Space between each button

// Set the buttons' positions
        btnSua.setBounds(startingX, 270, buttonWidth, 40);
        btnThem.setBounds(startingX + buttonWidth + spaceBetweenButtons, 270, buttonWidth, 50);
        btnXoa.setBounds(startingX + 2 * (buttonWidth + spaceBetweenButtons), 270, buttonWidth, 50);
        btnTim.setBounds(startingX + 3 * (buttonWidth + spaceBetweenButtons), 270, buttonWidth, 50);
        btnXuatExcel.setBounds(startingX + 4 * (buttonWidth + spaceBetweenButtons), 270, buttonWidth, 50);
        btnNhapExcel.setBounds(startingX + 5 * (buttonWidth + spaceBetweenButtons), 270, buttonWidth, 50);
        btnCapTK.setBounds(startingX + 6 * (buttonWidth + spaceBetweenButtons), 270, 200, 50);


        pnThongTinNhanVien.add(btnSua);
        pnThongTinNhanVien.add(btnThem);
        pnThongTinNhanVien.add(btnXoa);
        pnThongTinNhanVien.add(btnTim);
        pnThongTinNhanVien.add(btnXuatExcel);
        pnThongTinNhanVien.add(btnNhapExcel);
        pnThongTinNhanVien.add(btnCapTK);
        pnThongTinNhanVien.add(btnMKQuyen);
        pnThongTinNhanVien.add(btnKhoa);

        pnTableNhanVien.add(pnThongTinNhanVien);

        //====================Bảng nhân viên====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmNhanVien = new DefaultTableModel();
        dtmNhanVien.addColumn("Mã");
        dtmNhanVien.addColumn("Họ");
        dtmNhanVien.addColumn("Tên");
        dtmNhanVien.addColumn("SDT");
        dtmNhanVien.addColumn("Giới tính");
        dtmNhanVien.addColumn("Chức vụ");
        dtmNhanVien.addColumn("Gmail");
        dtmNhanVien.addColumn("Tài khoản");
        tblNhanVien = new MyTable(dtmNhanVien);

        tblNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        // Tăng kích thước font của header
        JTableHeader tableHeader = tblNhanVien.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 20)); // Thay đổi font và kích thước
        tableHeader.setPreferredSize(new Dimension(1240, 30)); // Tăng chiều cao của header
        TableColumnModel columnModelPhieuMuon = tblNhanVien.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(50);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(100);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(70);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(90);
        columnModelPhieuMuon.getColumn(6).setPreferredWidth(190);
        columnModelPhieuMuon.getColumn(7).setPreferredWidth(90);

        JScrollPane scrTblNhanVien = new JScrollPane(tblNhanVien);
        scrTblNhanVien.setPreferredSize(new Dimension(1240,250));

        scrTblNhanVien.setBounds(20,350,1240,250);
        //</editor-fold>
        pnThongTinNhanVien.add(scrTblNhanVien, BorderLayout.CENTER);

        loadDataLenTableNhanVien();

        pnTableNhanVien.add(pnThongTinNhanVien);

        /*
        =========================================================================
                                    PANEL QUYỀN
        =========================================================================
         */

        JPanel pnTablePhanQuyen = new TransparentPanel();
        pnTablePhanQuyen.setLayout(new BorderLayout());

        JPanel pnTitlePhanQuyen = new TransparentPanel();
        JLabel lblTitlePhanQuyen = new JLabel("Quản lý phân quyền");
        lblTitlePhanQuyen.setFont(new Font("Arial", Font.BOLD,28));
        pnTitlePhanQuyen.add(lblTitlePhanQuyen);
        pnTablePhanQuyen.add(pnTitlePhanQuyen,BorderLayout.NORTH);

        JPanel pnPhanQuyen = new TransparentPanel();
        pnPhanQuyen.setLayout(null);

        JLabel lblCmbQuyen = new JLabel("Nhóm quyền:");
        lblCmbQuyen.setFont(font);
        cmbPhanQuyen = new JComboBox<String>();
        cmbPhanQuyen.setFont(font);
        lblCmbQuyen.setBounds(400,50,150,40);
        cmbPhanQuyen.setBounds(550,50,400,40);

        ckbQLSach = new JCheckBox("Quản lý sách.");
        ckbQLSach.setFont(font);
        ckbQLSach.setBounds(400,100,200,30);

        ckbQLDocGia = new JCheckBox("Quản lý đọc giả.");
        ckbQLDocGia.setFont(font);
        ckbQLDocGia.setBounds(400,150,200,30);

        ckbQLNhanVien = new JCheckBox("Quản lý nhân viên.");
        ckbQLNhanVien.setFont(font);
        ckbQLNhanVien.setBounds(400,200,200,30);

        ckbQLNhapSach = new JCheckBox("Quản lý nhập sách.");
        ckbQLNhapSach.setFont(font);
        ckbQLNhapSach.setBounds(400,250,200,30);

        ckbQLThongKe = new JCheckBox("Quản lý thống kê");
        ckbQLThongKe.setFont(font);
        ckbQLThongKe.setBounds(400,300,200,30);

        Dimension ckbSize = ckbQLSach.getPreferredSize();
        cmbPhanQuyen.setPreferredSize(ckbSize);
        ckbQLSach.setPreferredSize(ckbSize);
        ckbQLDocGia.setPreferredSize(ckbSize);
        ckbQLNhanVien.setPreferredSize(ckbSize);
        ckbQLNhapSach.setPreferredSize(ckbSize);
        ckbQLThongKe.setPreferredSize(ckbSize);

        btnThemQuyen = new JButton("Thêm quyền");
        btnSuaQuyen = new JButton("Sửa quyền");
        btnXoaQuyen = new JButton("Xoá quyền");
        btnThemQuyen.setFont(font);
        btnSuaQuyen.setFont(font);
        btnXoaQuyen.setFont(font);
        btnThemQuyen.setIcon(new ImageIcon("image/add-icon.png"));
        btnSuaQuyen.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoaQuyen.setIcon(new ImageIcon("image/delete-icon.png"));
        btnSuaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnXoaQuyen.setPreferredSize(btnThemQuyen.getPreferredSize());
        btnThemQuyen.setBounds(350,350,180,40);
        btnXoaQuyen.setBounds(550,350,180,40);
        btnSuaQuyen.setBounds(750,350,180,40);

        pnPhanQuyen.add(lblCmbQuyen);
        pnPhanQuyen.add(cmbPhanQuyen);
        pnPhanQuyen.add(ckbQLSach);
        pnPhanQuyen.add(ckbQLDocGia);
        pnPhanQuyen.add(ckbQLNhanVien);
        pnPhanQuyen.add(ckbQLNhapSach);
        pnPhanQuyen.add(ckbQLThongKe);
        pnPhanQuyen.add(btnThemQuyen);
        pnPhanQuyen.add(btnXoaQuyen);
        pnPhanQuyen.add(btnSuaQuyen);

        pnTablePhanQuyen.add(pnPhanQuyen);

        //=======================================================

        pnCardTabNhanVien = new JPanel(cardNhanVienGroup);
        pnCardTabNhanVien.add(pnTableNhanVien,"1");
        pnCardTabNhanVien.add(pnTablePhanQuyen,"2");

        this.add(pnCardTabNhanVien);
        loadDataCmbQuyen();
    }

    JComboBox<String> cmbPhanQuyen;
    JCheckBox ckbQLSach, ckbQLNhanVien, ckbQLDocGia, ckbQLNhapSach, ckbQLThongKe;
    JButton btnThemQuyen, btnXoaQuyen, btnSuaQuyen;

    private void addEventsNhanVien(){
        lblTabbedNhanVien.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhanVien.setIcon(tabbedSelected);
                lblTabbedQuyen.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "1");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        lblTabbedQuyen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedQuyen.setIcon(tabbedSelected);
                lblTabbedNhanVien.setIcon(tabbedDefault);
                cardNhanVienGroup.show(pnCardTabNhanVien, "2");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIDNhanVien.setText("");
                txtHoNhanVien.setText("");
                txtTenNhanVien.setText("");
                txtGmail.setText("");
                txtSDT.setText("");
                ButtonGroup group = new ButtonGroup();
                group.add(rdbNam);
                group.add(rdbNu);
                group.clearSelection();
                loadDataLenTableNhanVien();
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
                xuLySuaNhanVien();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNhanVien();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaNhanVien();
            }
        });
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
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
        btnCapTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyCapTaiKhoan();
            }
        });
        btnMKQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyResetMatKhau();
            }
        });
        btnKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyKhoaTaiKhoan();
            }
        });
    }

    private void addEventsPhanQuyen() {
        cmbPhanQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyHienThiChiTietQuyen();
            }
        });
        btnThemQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemQuyen();
            }
        });
        btnSuaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaQuyen();
            }
        });
        btnXoaQuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaQuyen();
            }
        });
    }

    private void loadDataLenTableNhanVien(){
        dtmNhanVien.setRowCount(0);
        nhanVienBUS.docDanhSach();
        ArrayList<NhanVien> dsnv = nhanVienBUS.getListNhanVien();
        for(NhanVien nv : dsnv){
            Vector vec = new Vector<>();
            vec.add(nv.getMaNhanVien());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getSDT());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());
            vec.add(nv.getGmail());
            int trangThai = taiKhoanBUS.getTrangThai(nv.getMaNhanVien() + "");
            if (trangThai == 0) {
                vec.add("Khoá");
            }
            else if(trangThai == 1) {
                vec.add("Hiệu lực");
            }
            else {
                vec.add("Chưa có");
            }
            dtmNhanVien.addRow(vec);
        }
    }

    private void xuLyClickTblNhanVien(){
        int row = tblNhanVien.getSelectedRow();
        if(row > -1){
            String maNV = tblNhanVien.getValueAt(row,0)+"";
            String gioi = tblNhanVien.getValueAt(row,4)+"";
            txtIDNhanVien.setText(maNV);
            txtHoNhanVien.setText(tblNhanVien.getValueAt(row,1)+"");
            txtTenNhanVien.setText(tblNhanVien.getValueAt(row,2)+"");
            txtSDT.setText(tblNhanVien.getValueAt(row,3)+"");
            txtChucVu.setText(tblNhanVien.getValueAt(row,5)+"");
            txtGmail.setText(tblNhanVien.getValueAt(row,6)+"");

            if(gioi.equals("Nam")){
                rdbNam.setSelected(true);
            }else {
                rdbNu.setSelected(true);
            }
        }
    }

    private void xuLyThemNhanVien(){
        for(int i =0;i<= tblNhanVien.getSelectedRow();i++) {
            if (txtIDNhanVien.getText().equals(dtmNhanVien.getValueAt(i, 0).toString())) {
                new MyDialog("Nhân viên đã tồn tại!!!", MyDialog.ERROR_DIALOG);
                return;
            }
        }
        String gioi = "";
        if(rdbNam.isSelected()){
            gioi = "Nam";
        }else {
            gioi = "Nữ";
        }
        boolean flag = nhanVienBUS.themNhanVien(txtHoNhanVien.getText(),txtTenNhanVien.getText(),gioi,
                txtSDT.getText(),txtChucVu.getText(),txtGmail.getText());
        if(flag){
            txtIDNhanVien.setText("");
            txtHoNhanVien.setText("");
            txtTenNhanVien.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            txtChucVu.setText("");
            ButtonGroup group = new ButtonGroup();
            group.add(rdbNam);
            group.add(rdbNu);
            group.clearSelection();
        }
        loadDataLenTableNhanVien();
    }

    private void xuLyXoaNhanVien(){
        boolean flag = nhanVienBUS.xoaNhanVien(txtIDNhanVien.getText());
        if(flag){
            txtIDNhanVien.setText("");
            txtHoNhanVien.setText("");
            txtTenNhanVien.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            txtChucVu.setText("");
            ButtonGroup group = new ButtonGroup();
            group.add(rdbNam);
            group.add(rdbNu);
            group.clearSelection();
        }
        loadDataLenTableNhanVien();
    }

    private void xuLySuaNhanVien(){
        String gioi = "";
        if(rdbNam.isSelected()){
            gioi = "Nam";
        }else {
            gioi = "Nữ";
        }
        boolean flag = nhanVienBUS.suaNhanVien(txtIDNhanVien.getText(),txtHoNhanVien.getText(),txtTenNhanVien.getText(),gioi,
                txtSDT.getText(),txtChucVu.getText(),txtGmail.getText());
        if(flag){
            txtIDNhanVien.setText("");
            txtHoNhanVien.setText("");
            txtTenNhanVien.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            txtChucVu.setText("");
            ButtonGroup group = new ButtonGroup();
            group.add(rdbNam);
            group.add(rdbNu);
            group.clearSelection();
        }
        loadDataLenTableNhanVien();
    }

    private void xuLyTimKiem(){
        dtmNhanVien.setRowCount(0);
        ArrayList<NhanVien> dsnv = null;
        dsnv = nhanVienBUS.timDocGia(txtTimKiem.getText());
        for(NhanVien nv : dsnv){
            Vector vec = new Vector<>();
            vec.add(nv.getMaNhanVien());
            vec.add(nv.getHo());
            vec.add(nv.getTen());
            vec.add(nv.getSDT());
            vec.add(nv.getGioiTinh());
            vec.add(nv.getChucVu());
            vec.add(nv.getGmail());
            String q = "";
            if(taiKhoanBUS.getTaiKhoan(nv.getMaNhanVien()).getTrangThai() == 1){
                q = "Hiệu lực";
            }else {
                q = "Khóa";
            }
            vec.add(q);
            dtmNhanVien.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dsnv.size(), MyDialog.INFO_DIALOG);
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
            String chucVu = tblNhanVien.getValueAt(i,6)+"";
            String Gmail = tblNhanVien.getValueAt(i, 7) + "";

            nhanVienBUS.nhapNhanVienTuExcel(Ma, Ho, Ten, Sdt, Gtinh, chucVu, Gmail);
        }
    }

    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblNhanVien);
    }

    private void xuLyKhoaTaiKhoan(){
        TaiKhoanBUS taiKhoanBUS1 =new TaiKhoanBUS();
        taiKhoanBUS1.khoaTaiKhoan(txtIDNhanVien.getText());
        loadDataLenTableNhanVien();
    }

    private void xuLyCapTaiKhoan(){
        if(txtIDNhanVien.getText().trim().equals("")){
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgCapTaiKhoan capTaiKhoanGUI = new DlgCapTaiKhoan(txtIDNhanVien.getText());
        capTaiKhoanGUI.setVisible(true);
        loadDataLenTableNhanVien();
    }

    private void xuLyResetMatKhau(){
        String maNV = txtIDNhanVien.getText();
        if (maNV.trim().equals("")) {
            new MyDialog("Hãy chọn nhân viên!", MyDialog.ERROR_DIALOG);
            return;
        }
        DlgQuyen_MatKhau quyenMatKhauGUI = new DlgQuyen_MatKhau(maNV);
        quyenMatKhauGUI.setVisible(true);
    }

    private void loadDataCmbQuyen() {
        phanQuyenBUS.docDanhSachQuyen();
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        cmbPhanQuyen.removeAllItems();
        cmbPhanQuyen.addItem("Chọn quyền");
        for (PhanQuyen pq : dsq) {
            cmbPhanQuyen.addItem(pq.getQuyen());
        }
    }

    private void xuLyHienThiChiTietQuyen(){
        ArrayList<PhanQuyen> dsq = phanQuyenBUS.getListQuyen();
        PhanQuyen phanQuyen = new PhanQuyen();
        for(PhanQuyen pq : dsq){
            if(pq.getQuyen().equals(cmbPhanQuyen.getSelectedItem())){
                phanQuyen.setQuyen(pq.getQuyen());
                phanQuyen.setQlSach(pq.getQlSach());
                phanQuyen.setQlDocGia(pq.getQlDocGia());
                phanQuyen.setQlNhanVien(pq.getQlNhanVien());
                phanQuyen.setQlNhapSach(pq.getQlNhapSach());
                phanQuyen.setThongKe(pq.getThongKe());
                break;
            }
        }
        ckbQLSach.setSelected(false);
        ckbQLDocGia.setSelected(false);
        ckbQLNhanVien.setSelected(false);
        ckbQLNhapSach.setSelected(false);
        ckbQLThongKe.setSelected(false);
        if(phanQuyen.getQlSach() == 1){
            ckbQLSach.setSelected(true);
        }
        if(phanQuyen.getQlDocGia() == 1){
            ckbQLDocGia.setSelected(true);
        }
        if(phanQuyen.getQlNhanVien() == 1){
            ckbQLNhanVien.setSelected(true);
        }
        if(phanQuyen.getQlNhapSach() == 1){
            ckbQLNhapSach.setSelected(true);
        }
        if(phanQuyen.getThongKe() == 1){
            ckbQLThongKe.setSelected(true);
        }
    }

    private void xuLyThemQuyen() {
        String tenQuyen = JOptionPane.showInputDialog("Nhập tên quyền");

        boolean flag = phanQuyenBUS.themQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLyXoaQuyen() {
        if (cmbPhanQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để xoá!", MyDialog.ERROR_DIALOG);
            return;
        }
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() == MyDialog.CANCEL_OPTION) {
            return;
        }
        String tenQuyen = cmbPhanQuyen.getSelectedItem() + "";
        boolean flag = phanQuyenBUS.xoaQuyen(tenQuyen);
        if (flag) {
            loadDataCmbQuyen();
        }
    }

    private void xuLySuaQuyen() {
        if (cmbPhanQuyen.getSelectedIndex() < 1) {
            new MyDialog("Chưa chọn nhóm quyền để sửa!", MyDialog.ERROR_DIALOG);
            return;
        }
        String tenQuyen = cmbPhanQuyen.getSelectedItem() + "";
        int sach = ckbQLSach.isSelected() ? 1 : 0;
        int nhanVien = ckbQLNhanVien.isSelected() ? 1 : 0;
        int docGia = ckbQLDocGia.isSelected() ? 1 : 0;
        int nhapSach = ckbQLDocGia.isSelected() ? 1 : 0;
        int thongKe = ckbQLThongKe.isSelected() ? 1 : 0;

        boolean flag = phanQuyenBUS.suaQuyen(tenQuyen, sach, nhanVien, docGia, nhapSach, thongKe);
        if (flag) {
            loadDataCmbQuyen();
        }
    }
}

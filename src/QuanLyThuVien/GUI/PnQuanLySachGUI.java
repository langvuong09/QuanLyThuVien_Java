package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.*;

import static Main.Main.changLNF;
import MyCustom.MyTable;
import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import MyCustom.TransparentPanel;
import QuanLyThuVien.BUS.SachBUS;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class PnQuanLySachGUI extends JPanel {

    public PnQuanLySachGUI() {
        changLNF("Windows");
        addConTrolsSach();
        addEventsSach();

    }

    LoaiBUS loaiBUS = new LoaiBUS();
    NXBBUS nxbBUS = new NXBBUS();
    TacGiaBUS tacGiaBUS = new TacGiaBUS();
    SachBUS sachBUS = new SachBUS();
    PhanSachBUS phanSachBUS = new PhanSachBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSach,tblPhanSach;
    CardLayout cardSachGroup = new CardLayout();
    JPanel pnCardTabNhanVien;
    DefaultTableModel dtmSach,dtmPhanSach;
    JTextField txtIDSach, txtTenSach, txtTacGia, txtGia, txtTimKiem, txtSoLuong, txtMaPhanSach, txtMaSach, txtTenPhanSach;
    JLabel lblHinhAnh,lblTabbedSach,lblTabbedPhanSach;
    File fileAnhSach;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnDocGia, btnChonAnh, btnTimPhanSach;
    JComboBox<String> cmbLoai, cmbNXB;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/Manager-GUI/tabbed-btn.png");

    private void addConTrolsSach() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Sách & Phân sách">
        Font fonts = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedSach = new JLabel("Sách");
        lblTabbedSach.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedSach.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedSach.setIcon(tabbedSelected);
        lblTabbedSach.setBounds(2, 2, 140, 37);
        lblTabbedSach.setFont(fonts);
        lblTabbedSach.setForeground(Color.white);
        lblTabbedSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedPhanSach = new JLabel("Phân sách");
        lblTabbedPhanSach.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedPhanSach.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedPhanSach.setIcon(tabbedDefault);
        lblTabbedPhanSach.setBounds(143, 2, 140, 37);
        lblTabbedPhanSach.setFont(fonts);
        lblTabbedPhanSach.setForeground(Color.white);
        lblTabbedPhanSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedSach);
        pnTop.add(lblTabbedPhanSach);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnTop, BorderLayout.NORTH);

        /*

        /*
        =========================================================================
                                    PANEL Sách
        =========================================================================
         */
        JPanel pnTableSach = new TransparentPanel();
        pnTableSach.setLayout(new BorderLayout());

        JPanel pnTitleSach = new TransparentPanel();
        JLabel lblTitleSach = new JLabel("Quản lý Sách");
        lblTitleSach.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleSach.add(lblTitleSach);
        pnTitleSach.add(btnReset);
        pnTableSach.add(pnTitleSach, BorderLayout.NORTH);

        //=================PANEL INPUT===========
        int x = 15, y = 15;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        txtIDSach = new JTextField(x);
        cmbLoai = new JComboBox<String>();
        cmbNXB = new JComboBox<String>();
        txtTenSach = new JTextField(x);
        txtTacGia = new JTextField();
        txtGia = new JTextField(y);
        txtSoLuong = new JTextField(x);
        lblHinhAnh = new JLabel();
        txtTimKiem = new JTextField(x);
        txtMaPhanSach = new JTextField(x);
        txtMaSach = new JTextField(x);
        txtTenPhanSach = new JTextField(x);

        //=================Thông tin sách==============

        JPanel pnThongTinSach = new TransparentPanel();
        pnThongTinSach.setLayout(null);

        JLabel lblIDSach = new JLabel("Mã sách:");
        lblIDSach.setFont(font);
        txtIDSach.setFont(font);
        txtIDSach.setEditable(false);
        lblIDSach.setBounds(20, 0, 140, 25);
        txtIDSach.setBounds(120, 0, 200, 25);

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        lblTenSach.setBounds(20, 50, 140, 25);
        txtTenSach.setBounds(120, 50, 200, 25);

        JLabel lblLoaiSach = new JLabel("Loại sách:");
        lblLoaiSach.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtIDSach.getPreferredSize());
        lblLoaiSach.setBounds(20, 100, 140, 25);
        cmbLoai.setBounds(120,100,200,25);

        JLabel lblNXB = new JLabel("Nhà xuất bản:");
        lblNXB.setFont(font);
        cmbNXB.setFont(font);
        cmbNXB.setPreferredSize(txtIDSach.getPreferredSize());
        lblNXB.setBounds(15, 150, 140, 25);
        cmbNXB.setBounds(120,150,200,25);

        JLabel lblTacGia = new JLabel("Tác giả:");
        lblTacGia.setFont(font);
        txtTacGia.setFont(font);
        lblTacGia.setBounds(20, 200, 140, 25);
        txtTacGia.setBounds(120, 200, 200, 25);

        JLabel lblGia = new JLabel("Giá:");
        lblGia.setFont(font);
        txtGia.setFont(font);
        lblGia.setBounds (20, 250, 140, 25);
        txtGia.setBounds (120, 250, 200, 25);

        pnThongTinSach.add(lblIDSach);
        pnThongTinSach.add(txtIDSach);
        pnThongTinSach.add(lblTenSach);
        pnThongTinSach.add(txtTenSach);
        pnThongTinSach.add(lblLoaiSach);
        pnThongTinSach.add(cmbLoai);
        pnThongTinSach.add(lblNXB);
        pnThongTinSach.add(cmbNXB);
        pnThongTinSach.add(lblTacGia);
        pnThongTinSach.add(txtTacGia);
        pnThongTinSach.add(lblGia);
        pnThongTinSach.add(txtGia);

        lblHinhAnh.setPreferredSize(new Dimension(180,180));
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblHinhAnh.setIcon(getAnhSach(""));
        lblHinhAnh.setBounds(540, 0, 180, 180);

        JLabel lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setFont(font);
        txtSoLuong.setFont(font);
        txtSoLuong.setEditable(false);
        lblSoLuong.setBounds(400,200,140,25);
        txtSoLuong.setBounds(520,200,220,25);

        JLabel lblTimKiem = new JLabel("Tên sách cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(350, 250, 300, 25);
        txtTimKiem.setBounds(520, 250, 250, 25);

        pnThongTinSach.add(lblHinhAnh);
        pnThongTinSach.add(lblSoLuong);
        pnThongTinSach.add(txtSoLuong);
        pnThongTinSach.add(lblTimKiem);
        pnThongTinSach.add(txtTimKiem);

        pnTableSach.add(pnThongTinSach);

        //=================Chi tiết sách==========


        //=================BUTTON===============


        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnChonAnh = new JButton("Chọn ảnh");
        btnDocGia = new JButton("...");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnDocGia.setFont(fontButton);
        btnChonAnh.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnThem.setBounds(65, 300, 110, 40);
        btnSua.setBounds(180, 300, 110, 40);
        btnXoa.setBounds(295, 300, 110, 40);
        btnTim.setBounds(410, 300, 110, 40);
        btnXuatExcel.setBounds(525, 300, 105, 40);
        btnNhapExcel.setBounds(635, 300, 105, 40);
        btnChonAnh.setBounds(400, 150, 120, 30);

        pnThongTinSach.add(btnThem);
        pnThongTinSach.add(btnSua);
        pnThongTinSach.add(btnXoa);
        pnThongTinSach.add(btnTim);
        pnThongTinSach.add(btnXuatExcel);
        pnThongTinSach.add(btnNhapExcel);
         pnThongTinSach.add(btnChonAnh);

        pnTableSach.add(pnThongTinSach);

        //====================Bảng sách====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã");
        dtmSach.addColumn("Loại sách");
        dtmSach.addColumn("NXB");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Giá");
        dtmSach.addColumn("Hình ảnh");
        dtmSach.addColumn("SL");

        tblSach = new MyTable(dtmSach);


        tblSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSach = tblSach.getColumnModel();
        columnModelSach.getColumn(0).setPreferredWidth(60);
        columnModelSach.getColumn(1).setPreferredWidth(120);
        columnModelSach.getColumn(2).setPreferredWidth(160);
        columnModelSach.getColumn(3).setPreferredWidth(130);
        columnModelSach.getColumn(4).setPreferredWidth(240);
        columnModelSach.getColumn(5).setPreferredWidth(120);
        columnModelSach.getColumn(6).setPreferredWidth(100);
        columnModelSach.getColumn(7).setPreferredWidth(50);


        JScrollPane scrTblSach = new JScrollPane(tblSach);
        scrTblSach.setPreferredSize(new Dimension(900, 200));

        scrTblSach.setBounds(0, 360, 820, 205);
        //</editor-fold>
        pnThongTinSach.add(scrTblSach, BorderLayout.CENTER);

        pnTableSach.add(pnThongTinSach);

        /*
        =========================================================================
                                    PANEL PHÂN SÁCH
        =========================================================================
         */

        JPanel pnTablePhanSach = new TransparentPanel();
        pnTablePhanSach.setLayout(new BorderLayout());

        JPanel pnTitlePhanSach = new TransparentPanel();
        JLabel lblTitlePhanSach = new JLabel("Quản lý phân sách");
        lblTitlePhanSach.setFont(new Font("Arial", Font.BOLD,28));
        pnTitlePhanSach.add(lblTitlePhanSach);
        pnTablePhanSach.add(pnTitlePhanSach,BorderLayout.NORTH);

        JPanel pnPhanSach = new TransparentPanel();
        pnPhanSach.setLayout(null);

        JLabel lblTimPhanSach = new JLabel("Tìm phân sách:");
        lblTimPhanSach.setFont(font);
        lblTimPhanSach.setBounds(20,35,130,26);
        JLabel lblMaSach = new JLabel("ID sách:");
        lblMaSach.setFont(font);
        lblMaSach.setBounds(145,15,130,26);
        JLabel lblMaPhanSach = new JLabel("ID Phân sách:");
        lblMaPhanSach.setFont(font);
        lblMaPhanSach.setBounds(145,50,130,26);
        JLabel lblTenPhanSach = new JLabel("Tên sách:");
        lblTenPhanSach.setFont(font);
        lblTenPhanSach.setBounds(340,35,130,26);

        txtMaPhanSach.setFont(font);
        txtMaPhanSach.setBounds(260,50,60,26);
        txtMaSach.setFont(font);
        txtMaSach.setBounds(260,15,60,26);
        txtTenPhanSach.setFont(font);
        txtTenPhanSach.setBounds(420,35,200,26);

        btnTimPhanSach = new JButton("Tìm kiếm");
        btnTimPhanSach.setFont(fontButton);
        btnTimPhanSach.setIcon(new ImageIcon("image/Search-icon.png"));
        btnTimPhanSach.setBounds(640,28,140,40);

        pnPhanSach.add(lblTimPhanSach);
        pnPhanSach.add(lblMaPhanSach);
        pnPhanSach.add(lblMaSach);
        pnPhanSach.add(lblTenPhanSach);
        pnPhanSach.add(txtMaPhanSach);
        pnPhanSach.add(txtMaSach);
        pnPhanSach.add(txtTenPhanSach);
        pnPhanSach.add(btnTimPhanSach);

        //====================Bảng phân sách====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phân sách">
        dtmPhanSach = new DefaultTableModel();
        dtmPhanSach.addColumn("Mã phân sách");
        dtmPhanSach.addColumn("Mã sách");
        dtmPhanSach.addColumn("Tên sách");
        dtmPhanSach.addColumn("Trạng thái");
        tblPhanSach = new MyTable(dtmPhanSach);

        tblPhanSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhanSach.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhanSach.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhanSach = tblPhanSach.getColumnModel();
        columnModelPhanSach.getColumn(0).setPreferredWidth(110);
        columnModelPhanSach.getColumn(1).setPreferredWidth(80);
        columnModelPhanSach.getColumn(2).setPreferredWidth(240);
        columnModelPhanSach.getColumn(3).setPreferredWidth(120);

        JScrollPane scrTblPhanSach = new JScrollPane(tblPhanSach);
        scrTblPhanSach.setPreferredSize(new Dimension(900,500));
        scrTblPhanSach.setBounds(0,115,818,500);
        //</editor-fold>
        pnPhanSach.add(scrTblPhanSach, BorderLayout.CENTER);

        pnTablePhanSach.add(pnPhanSach);

        //=======================================================

        pnCardTabNhanVien = new JPanel(cardSachGroup);
        pnCardTabNhanVien.add(pnTableSach,"1");
        pnCardTabNhanVien.add(pnTablePhanSach,"2");

        this.add(pnCardTabNhanVien);
        loadDataLoai();
        loadDataNXB();
//        loadDataTG();
        loadDataLenTableSach();
        loadDataLenTablePhanSach();
    }

    private void addEventsSach() {
        lblTabbedSach.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedSach.setIcon(tabbedSelected);
                lblTabbedPhanSach.setIcon(tabbedDefault);
                cardSachGroup.show(pnCardTabNhanVien,"1");
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
        lblTabbedPhanSach.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedPhanSach.setIcon(tabbedSelected);
                lblTabbedSach.setIcon(tabbedDefault);
                cardSachGroup.show(pnCardTabNhanVien,"2");
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
                txtIDSach.setText("");
                txtTenSach.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNXB.setSelectedIndex(0);
                txtTacGia.setText("");
                lblHinhAnh.setIcon(null);
                txtGia.setText("");
                txtSoLuong.setText("");
                txtTimKiem.setText("");
                loadDataLenTableSach();

            }
        });
        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSach();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSach();
                txtIDSach.setText("");
                txtTenSach.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNXB.setSelectedIndex(0);
                txtTacGia.setText("");
                lblHinhAnh.setIcon(null);
                txtGia.setText("");
                txtSoLuong.setText("");
                txtTimKiem.setText("");
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaSach();
                txtIDSach.setText("");
                txtTenSach.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNXB.setSelectedIndex(0);
                txtTacGia.setText("");
                lblHinhAnh.setIcon(null);
                txtGia.setText("");
                txtTimKiem.setText("");
                txtSoLuong.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSach();
                txtIDSach.setText("");
                txtTenSach.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbNXB.setSelectedIndex(0);
                txtTacGia.setText("");
                lblHinhAnh.setIcon(null);
                txtGia.setText("");
                txtTimKiem.setText("");
                txtSoLuong.setText("");
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
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonAnh();
            }
        });
        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });
        cmbNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemNXB();
            }
        });
//        cmbTacGia.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                xuLyThemTG();
//            }
//        });
        btnTimPhanSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemPhanSach();
            }
        });
    }

    private void xuLyThemLoai(){
        int x = cmbLoai.getSelectedIndex();
        if(x == cmbLoai.getItemCount() - 1){
            DlgLoai loaiGUI = new DlgLoai();
            loaiGUI.setVisible(true);
            loadDataLoai();
        }
    }

    private void xuLyThemNXB(){
        int x = cmbNXB.getSelectedIndex();
        if(x == cmbNXB.getItemCount() - 1){
            DlgNXB nxbGUI = new DlgNXB();
            nxbGUI.setVisible(true);
            loadDataNXB();
        }
    }

    private void loadDataLoai(){
        cmbLoai.removeAllItems();

        ArrayList<Loai> dsl = loaiBUS.getListLoai();
        cmbLoai.addItem("0 - Chọn loại");
        for (Loai l : dsl){
            cmbLoai.addItem(l.getMaLoai() +" - "+l.getTenLoai());
        }
        cmbLoai.addItem("Khác...");
    }

    private void loadDataNXB(){
        cmbNXB.removeAllItems();

        ArrayList<NXB> dsnxb = nxbBUS.getListNXB();
        cmbNXB.addItem("Chọn nxb");
        for (NXB nxb : dsnxb){
            cmbNXB.addItem(nxb.getTenNXB());
        }
        cmbNXB.addItem("Khác...");
    }

//    private void xuLyThemTG(){
//        int x = cmbTacGia.getSelectedIndex();
//        if(x == cmbTacGia.getItemCount() -1){
//            DlgTacGia tgGUI = new DlgTacGia();
//            tgGUI.setVisible(true);
//            loadDataTG();
//        }
//    }

//    private void loadDataTG(){
//        cmbTacGia.removeAllItems();
//
//        ArrayList<TacGia> dstg = tacGiaBUS.getListTacGia();
//        cmbTacGia.addItem("0 - Chọn tác giả");
//        for(TacGia tg : dstg){
//            cmbTacGia.addItem(tg.getMaTacGia() +" - "+tg.getTenTacGia());
//        }
//        cmbTacGia.addItem("Khác...");
//    }

    private void loadDataLenTableSach(){
        sachBUS.docDanhSach();
        dtmSach.setRowCount(0);

        ArrayList<Sach> dss = sachBUS.getListSach();

        for(Sach s : dss){
            Vector vec = new Vector<>();
            vec.add(s.getMaSach());
            vec.add(loaiBUS.getTenLoai(s.getMaLoaiSach()));
            vec.add(nxbBUS.getTenNXB(s.getMaNXB()));
            vec.add(s.getTacGia());
            vec.add(s.getTenSach());
            vec.add(s.getGiaSach());
            vec.add(s.getHinhAnh());
           vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
    }

    private void loadDataLenTablePhanSach(){
        phanSachBUS.docDanhSach();
        dtmPhanSach.setRowCount(0);

        ArrayList<PhanSach> dsps = phanSachBUS.getListPhanSach();

        for(PhanSach ps : dsps){
            Vector vec = new Vector<>();
            vec.add(ps.getMaPhanSach());
            vec.add(ps.getMaSach());
            vec.add(sachBUS.getTenSach(ps.getMaSach()));
            vec.add(ps.getTrangThai());
            dtmPhanSach.addRow(vec);
        }
    }

    private void loadAnh(String hinhAnh){lblHinhAnh.setIcon(getAnhSach(hinhAnh));}

    private void luuFileAnh(){
        BufferedImage bImage = null;
        try{
            File fImage = new File(fileAnhSach.getPath());
            bImage = ImageIO.read(fImage);

            ImageIO.write(bImage,"png", new File("image/Sach/"+fileAnhSach.getName()));
        }catch (IOException e){
            System.out.println("Exception occured :" + e.getMessage());
        }
    }

    private void xuLyChonAnh(){
        JFileChooser fileChooser = new JFileChooser("image/Sach/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh","jpg","png","jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION){
            fileAnhSach = fileChooser.getSelectedFile();
            lblHinhAnh.setIcon(getAnhSach(fileAnhSach.getPath()));
        }
    }

    private ImageIcon getAnhSach(String src){
        src = src.trim().equals("") ? "default.png" : src;
        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("image/Sach/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSach = new File(src);
        } catch (IOException e) {
            fileAnhSach = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

    private void xuLyClickTblSach(){
        int row = tblSach.getSelectedRow();
        if(row > -1){
            String maSach = tblSach.getValueAt(row,0)+"";
            String giaSach = tblSach.getValueAt(row,5)+"";
            String loai = tblSach.getValueAt(row,1)+"";
            String nxb = tblSach.getValueAt(row,2)+"";
            String tacGia = tblSach.getValueAt(row,3)+"";
            String anh = tblSach.getValueAt(row,6)+"";
            txtIDSach.setText(maSach);
            txtTacGia.setText(tacGia);
            txtTenSach.setText(tblSach.getValueAt(row,4)+"");
            txtGia.setText(giaSach);
            txtSoLuong.setText(tblSach.getValueAt(row,7)+"");

            int l=-1,mnxb=-1;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    l = i;
                    break;
                }
            }
            for (int i = 0; i < cmbNXB.getItemCount(); i++) {
                if (cmbNXB.getItemAt(i).contains(nxb)) {
                    mnxb = i;
                    break;
                }
            }
//            for(int i=0;i<cmbTacGia.getItemCount();i++){
//                if(cmbTacGia.getItemAt(i).contains(tacGia)){
//                    tg=i;
//                    break;
//                }
//            }

            cmbLoai.setSelectedIndex(l);
            cmbNXB.setSelectedIndex(mnxb);
//            cmbTacGia.setSelectedIndex(tg);
            loadAnh("image/Sach/"+ anh);
        }
    }

    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSach);
    }

    private void xuLyNhapFileExcel(){
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if(dlg.getAction() != MyDialog.OK_OPTION){
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblSach);

        int row = tblSach.getRowCount();
        for(int i=0;i<row;i++){
            String loai = tblSach.getValueAt(i,1)+"";
            String nxb = tblSach.getValueAt(i,2)+"";
            String tacGia = tblSach.getValueAt(i,3)+"";
            String tenSach = tblSach.getValueAt(i,4)+"";
            String gia = tblSach.getValueAt(i,5)+"";
            String ghiChu = tblSach.getValueAt(i,6)+"";
            String soLuong = tblSach.getValueAt(i,7)+"";

            sachBUS.nhapSachExcel(loai,nxb,tacGia,tenSach,gia,ghiChu,soLuong);
        }
    }

    private void xuLyThemSach(){
        String anh =fileAnhSach.getName();
        String loaiSach = (String) cmbLoai.getSelectedItem();
        String nxb = (String) cmbNXB.getSelectedItem();
        boolean flag = sachBUS.themSach(loaiSach,nxb,txtTacGia.getText(),txtTenSach.getText(),txtGia.getText(),anh);
        loadDataLenTableSach();
        luuFileAnh();
    }

    private void xuLyXoaSach(){
        boolean flag = sachBUS.xoaSach(txtIDSach.getText());
        loadDataLenTableSach();
    }

    private void xuLySuaSach(){
        String anh = fileAnhSach.getName();
        String loaiSach = (String) cmbLoai.getSelectedItem();
        String nxb = (String) cmbNXB.getSelectedItem();
        String maSach = txtIDSach.getText();
        boolean flag = sachBUS.suaSach(maSach,loaiSach,nxb, txtTacGia.getText(),txtTenSach.getText(),txtGia.getText(),anh,txtSoLuong.getText());
        loadDataLenTableSach();
        luuFileAnh();
    }

    private void xuLyTimKiem(){
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = null;
        dss = sachBUS.getListSachTheoTenSach(txtTimKiem.getText());
        for(Sach s : dss){
            Vector vec = new Vector<>();
            vec.add(s.getMaSach());
            vec.add(loaiBUS.getTenLoai(s.getMaLoaiSach()));
            vec.add(nxbBUS.getTenNXB(s.getMaNXB()));
            vec.add(s.getTenSach());
            vec.add(s.getGiaSach());
            vec.add(s.getHinhAnh());
            vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dss.size(), MyDialog.INFO_DIALOG);
    }

    private void xuLyTimKiemPhanSach(){
        dtmPhanSach.setRowCount(0);
        ArrayList<PhanSach> dsps = null;
        dsps = phanSachBUS.timKiemPhanSach(txtMaPhanSach.getText(),txtMaSach.getText(),txtTenPhanSach.getText());
        for(PhanSach ps : dsps){
            Vector vec = new Vector<>();
            vec.add(ps.getMaPhanSach());
            vec.add(ps.getMaSach());
            vec.add(sachBUS.getTenSach(ps.getMaSach()));
            vec.add(ps.getTrangThai());
            dtmPhanSach.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dsps.size(), MyDialog.INFO_DIALOG);
    }
}

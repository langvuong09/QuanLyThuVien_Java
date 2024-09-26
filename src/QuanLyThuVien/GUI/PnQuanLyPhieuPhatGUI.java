package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.PhieuPhat;

import static Main.Main.changLNF;

import MyCustom.XuLyFileExcel;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLyPhieuPhatGUI extends JPanel{
    private DlgTimPhieuTra timPhieuTraGUI = new DlgTimPhieuTra();
    private DlgTimSachPhat timSachPhatGUI = new DlgTimSachPhat();
    private PhieuTraBUS ptBUS = new PhieuTraBUS();
    private CTPhieuTraBUS ctPhieuTraBUS = new CTPhieuTraBUS();
    private SachBUS sachBUS = new SachBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();


    public PnQuanLyPhieuPhatGUI(){
        changLNF("Windows");
        addConTrolsPhieuPhat();
        addEventsPhieuPhat();
    }

    PhieuPhatBUS ppBUS = new PhieuPhatBUS();
    final Color colorPanel = new Color(247, 247, 247);

    MyTable tblCTPhieuPhat, tblPhieuPhat, tblXemCTPhieuPhat;
    DefaultTableModel dtmCTPhieuPhat, dtmPhieuPhat, dtmXemCTPhieuPhat;
    CardLayout cardPhieuPhatGroup = new CardLayout();
    JPanel pnCardTabPhieuPhat;
    JLabel lblTabbedPhieuPhat, lblTabbedQuanLyPhieu;
    JTextField txtMaPhieuPhat, txtMaPhieuTra, txtMaSach, txtMaPhanSach, txtDocGia, txtThanhTien, txtNgayTraMuon, txtTimKiem;
    JComboBox<String> cmbLyDo;
    JButton btnThem, btnXoa, btnInThe, btnReset, btnXuatExcel, btnNhapExcel, btnTimKiem, btnChon, btnPhieuTra, btnSachPhat;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/Manager-GUI/tabbed-btn.png");

    private void addConTrolsPhieuPhat(){
        Font font = new Font("Tahoma", Font.PLAIN,16);
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

        lblTabbedPhieuPhat = new JLabel("Phiếu phạt");
        lblTabbedPhieuPhat.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedPhieuPhat.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedPhieuPhat.setIcon(tabbedSelected);
        lblTabbedPhieuPhat.setBounds(2, 2, 140, 37);
        lblTabbedPhieuPhat.setFont(fonts);
        lblTabbedPhieuPhat.setForeground(Color.white);
        lblTabbedPhieuPhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedQuanLyPhieu = new JLabel("Danh sách");
        lblTabbedQuanLyPhieu.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedQuanLyPhieu.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedQuanLyPhieu.setIcon(tabbedDefault);
        lblTabbedQuanLyPhieu.setBounds(143, 2, 140, 37);
        lblTabbedQuanLyPhieu.setFont(fonts);
        lblTabbedQuanLyPhieu.setForeground(Color.white);
        lblTabbedQuanLyPhieu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedPhieuPhat);
        pnTop.add(lblTabbedQuanLyPhieu);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);
        this.add(pnTop, BorderLayout.NORTH);

        /*

        /*
        =========================================================================
                                    PANEL Phiếu phạt
        =========================================================================
         */
        JPanel pnTablePhieuPhat = new TransparentPanel();
        pnTablePhieuPhat.setLayout(new BorderLayout());

        JPanel pnTitlePhieuPhat = new TransparentPanel();
        JLabel lblTitlePhieuPhat = new JLabel("Quản lý phiếu phạt");
        lblTitlePhieuPhat.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitlePhieuPhat.add(lblTitlePhieuPhat);
        pnTitlePhieuPhat.add(btnReset);
        pnTablePhieuPhat.add(pnTitlePhieuPhat, BorderLayout.NORTH);

        //=================PANEL INPUT===========
        int x = 15;
        txtMaPhieuPhat = new JTextField(x);
        txtMaPhieuTra = new JTextField(x);
        txtMaSach = new JTextField(x);
        txtDocGia = new JTextField(x);
        txtMaPhanSach = new JTextField(x);
        cmbLyDo = new JComboBox<String>();
        txtThanhTien = new JTextField(x);
        txtNgayTraMuon = new JTextField(x);
        txtTimKiem = new JTextField(x);

        //=================Thông tin phiếu phạt==============

        JPanel pnThongTinPhieuPhat = new TransparentPanel();
        pnThongTinPhieuPhat.setLayout(null);

        JLabel lblMaPhieuPhat = new JLabel("Mã phiếu phạt:");
        lblMaPhieuPhat.setFont(font);
        txtMaPhieuPhat.setFont(font);
        txtMaPhieuPhat.setEditable(false);
        lblMaPhieuPhat.setBounds(20,10,150,25);
        txtMaPhieuPhat.setBounds(160,10,220,25);

        JLabel lblMaPhieuTra = new JLabel("Mã phiếu trả:");
        lblMaPhieuTra.setFont(font);
        txtMaPhieuTra.setFont(font);
        txtMaPhieuTra.setEditable(false);
        lblMaPhieuTra.setBounds(20,55,150,25);
        txtMaPhieuTra.setBounds(160,55,220,25);

        JLabel lblMaSach = new JLabel("Mã sách:");
        lblMaSach.setFont(font);
        txtMaSach.setFont(font);
        txtMaSach.setEditable(false);
        lblMaSach.setBounds(20,100,150,25);
        txtMaSach.setBounds(160,100,220,25);

        JLabel lblMaPhanSach = new JLabel("Mã phân sách:");
        lblMaPhanSach.setFont(font);
        txtMaPhanSach.setFont(font);
        txtMaPhanSach.setEditable(false);
        lblMaPhanSach.setBounds(20,145,150,25);
        txtMaPhanSach.setBounds(160,145,220,25);

        JLabel lblLyDo = new JLabel("Lý do:");
        lblLyDo.setFont(font);
        String[] luaChon = {"Trả muộn", "Rách sách", "Ướt sách", "Mất sách", "Mất trang"};
        cmbLyDo = new JComboBox<>(luaChon);
        cmbLyDo.setFont(font);
        lblLyDo.setBounds(20,220,150,25);
        cmbLyDo.setBounds(160,220,220,25);

        JLabel lblThanhTien = new JLabel("Thành tiền:");
        lblThanhTien.setFont(font);
        txtThanhTien.setFont(font);
        txtThanhTien.setEditable(false);
        lblThanhTien.setBounds(20,265,150,25);
        txtThanhTien.setBounds(160,265,220,25);

        JLabel lblNgayTraMuon = new JLabel("Số ngày trả muộn: ");
        lblNgayTraMuon.setFont(font);
        txtNgayTraMuon.setFont(font);
        lblNgayTraMuon.setBounds(450,10,200,25);
        txtNgayTraMuon.setBounds(600,10,150,25);


        JLabel lblDocGia = new JLabel("Đọc giả:");
        lblDocGia.setFont(font);
        txtDocGia.setFont(font);
        txtDocGia.setEditable(false);
        lblDocGia.setBounds(450,55,200,25);
        txtDocGia.setBounds(600,55,150,25);

        JLabel lblNoiDungPhat = new JLabel("<html><pre>      HÌNH THỨC PHẠT<br></pre>Trả muộn: 10% giá sách/10 ngày<br>Rách sách: 25% giá sách<br>Ướt, mất sách: 100% giá sách<br>Mất trang: 50% giá sách</html>");
        lblNoiDungPhat.setFont(font);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        lblNoiDungPhat.setBorder(border);
        lblNoiDungPhat.setBounds(500,100,250,120);

        JLabel lblTimKiem = new JLabel("Tìm đọc giả:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(420,245,200,25);
        txtTimKiem.setBounds(530,245,200,25);

        dtmCTPhieuPhat = new DefaultTableModel();
        dtmCTPhieuPhat.addColumn("Mã sách");
        dtmCTPhieuPhat.addColumn("Mã phân sách");
        dtmCTPhieuPhat.addColumn("Tên sách");
        dtmCTPhieuPhat.addColumn("Lý do");
        dtmCTPhieuPhat.addColumn("Tiền phạt");
        tblCTPhieuPhat = new MyTable(dtmCTPhieuPhat);

        tblCTPhieuPhat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCTPhieuPhat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuPhat = tblCTPhieuPhat.getColumnModel();
        columnModelPhieuPhat.getColumn(0).setPreferredWidth(100);
        columnModelPhieuPhat.getColumn(1).setPreferredWidth(120);
        columnModelPhieuPhat.getColumn(2).setPreferredWidth(200);
        columnModelPhieuPhat.getColumn(3).setPreferredWidth(120);
        columnModelPhieuPhat.getColumn(4).setPreferredWidth(120);
        JScrollPane scrTblPhieuPhat = new JScrollPane(tblCTPhieuPhat);
        scrTblPhieuPhat.setPreferredSize(new Dimension(900,150));
        scrTblPhieuPhat.setBounds(0,365, 820,250);
        pnThongTinPhieuPhat.add(scrTblPhieuPhat, BorderLayout.CENTER);

        pnThongTinPhieuPhat.add(lblMaPhieuPhat);
        pnThongTinPhieuPhat.add(txtMaPhieuPhat);
        pnThongTinPhieuPhat.add(lblMaPhieuTra);
        pnThongTinPhieuPhat.add(txtMaPhieuTra);
        pnThongTinPhieuPhat.add(lblMaSach);
        pnThongTinPhieuPhat.add(txtMaSach);
        pnThongTinPhieuPhat.add(lblMaPhanSach);
        pnThongTinPhieuPhat.add(txtMaPhanSach);
        pnThongTinPhieuPhat.add(lblLyDo);
        pnThongTinPhieuPhat.add(cmbLyDo);
        pnThongTinPhieuPhat.add(lblThanhTien);
        pnThongTinPhieuPhat.add(txtThanhTien);
        pnThongTinPhieuPhat.add(lblNgayTraMuon);
        pnThongTinPhieuPhat.add(txtNgayTraMuon);
        pnThongTinPhieuPhat.add(lblDocGia);
        pnThongTinPhieuPhat.add(txtDocGia);
        pnThongTinPhieuPhat.add(lblNoiDungPhat);
        pnThongTinPhieuPhat.add(lblTimKiem);
        pnThongTinPhieuPhat.add(txtTimKiem);

        pnTablePhieuPhat.add(pnThongTinPhieuPhat);

        //=================BUTTON===============


        btnThem = new JButton("Thêm phiếu");
        btnXoa = new JButton("Xoá CT sách");
        btnTimKiem = new JButton();
        btnInThe = new JButton("In thẻ");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnPhieuTra = new JButton("...");
        btnSachPhat = new JButton("...");
        btnChon = new JButton("Chọn");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTimKiem.setFont(fontButton);
        btnInThe.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnPhieuTra.setFont(fontButton);
        btnSachPhat.setFont(fontButton);
        btnChon.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTimKiem.setIcon(new ImageIcon("image/Search-icon.png"));
        btnInThe.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnChon.setIcon(new ImageIcon());

        btnInThe.setBounds(55,310,110,40);
        btnThem.setBounds(170,310,170,40);
        btnXoa.setBounds(345,310,165,40);
        btnXuatExcel.setBounds(515,310,110,40);
        btnNhapExcel.setBounds(630,310,110,40);
        btnTimKiem.setBounds(740,245,50,30);
        btnPhieuTra.setBounds(390,55,30,25);
        btnSachPhat.setBounds(390,100,30,25);
        btnChon.setBounds(200,180,120,30);

        pnThongTinPhieuPhat.add(btnInThe);
        pnThongTinPhieuPhat.add(btnThem);
        pnThongTinPhieuPhat.add(btnXoa);
        pnThongTinPhieuPhat.add(btnTimKiem);
        pnThongTinPhieuPhat.add(btnXuatExcel);
        pnThongTinPhieuPhat.add(btnNhapExcel);
        pnThongTinPhieuPhat.add(btnPhieuTra);
        pnThongTinPhieuPhat.add(btnSachPhat);
        pnThongTinPhieuPhat.add(btnChon);

//        loadDataLenBangPhieuPhat();

        pnTablePhieuPhat.add(pnThongTinPhieuPhat);

        /*
        =========================================================================
                                    PANEL Danh sách
        =========================================================================
         */

        JPanel pnTableQuanLy = new TransparentPanel();
        pnTableQuanLy.setLayout(new BorderLayout());

        JPanel pnTitleQuanLy = new TransparentPanel();
        JLabel lblTitleQuanLy = new JLabel("Danh sách phiếu phạt");
        lblTitleQuanLy.setFont(new Font("Arial", Font.BOLD,28));
        pnTitleQuanLy.add(lblTitleQuanLy);
        pnTableQuanLy.add(pnTitleQuanLy,BorderLayout.NORTH);

        JPanel pnQuanLy = new TransparentPanel();
        pnQuanLy.setLayout(null);

        //====================Bảng phiếu phạt===================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu phạt">
        dtmPhieuPhat = new DefaultTableModel();
        dtmPhieuPhat.addColumn("Mã PP");
        dtmPhieuPhat.addColumn("Mã PT");
        dtmPhieuPhat.addColumn("Đọc giả");
        dtmPhieuPhat.addColumn("Thành tiền");
        tblPhieuPhat = new MyTable(dtmPhieuPhat);

        tblPhieuPhat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuPhat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TableColumnModel columnModelQuanLy = tblPhieuPhat.getColumnModel();
        columnModelQuanLy.getColumn(0).setPreferredWidth(50);
        columnModelQuanLy.getColumn(1).setPreferredWidth(50);
        columnModelQuanLy.getColumn(2).setPreferredWidth(50);
        columnModelQuanLy.getColumn(3).setPreferredWidth(50);

        JScrollPane scrTblQuanLy = new JScrollPane(tblPhieuPhat);
        scrTblQuanLy.setPreferredSize(new Dimension(900,500));
        scrTblQuanLy.setBounds(0,0,818,200);
        //</editor-fold>
        pnQuanLy.add(scrTblQuanLy, BorderLayout.CENTER);

        //====================Bảng Xem CT phiếu phạt===================
        //<editor-fold defaultstate="collapsed" desc="Bảng Xem CT phiếu phạt">
        dtmXemCTPhieuPhat = new DefaultTableModel();
        dtmXemCTPhieuPhat.addColumn("Mã PP");
        dtmXemCTPhieuPhat.addColumn("Mã sách");
        dtmXemCTPhieuPhat.addColumn("Mã phân sách");
        dtmXemCTPhieuPhat.addColumn("Tên sách");
        dtmXemCTPhieuPhat.addColumn("Lý do");
        dtmXemCTPhieuPhat.addColumn("tiền phạt");
        tblXemCTPhieuPhat = new MyTable(dtmXemCTPhieuPhat);

        tblXemCTPhieuPhat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblXemCTPhieuPhat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblXemCTPhieuPhat.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        TableColumnModel columnModelXemCT = tblXemCTPhieuPhat.getColumnModel();
        columnModelXemCT.getColumn(0).setPreferredWidth(50);
        columnModelXemCT.getColumn(1).setPreferredWidth(50);
        columnModelXemCT.getColumn(2).setPreferredWidth(50);
        columnModelXemCT.getColumn(3).setPreferredWidth(50);
        columnModelXemCT.getColumn(4).setPreferredWidth(50);
        columnModelXemCT.getColumn(5).setPreferredWidth(50);

        JScrollPane scrTblXemCT = new JScrollPane(tblXemCTPhieuPhat);
        scrTblXemCT.setPreferredSize(new Dimension(900,500));
        scrTblXemCT.setBounds(0,300,818,200);
        //</editor-fold>
        pnQuanLy.add(scrTblXemCT, BorderLayout.CENTER);

        pnTableQuanLy.add(pnQuanLy);

        //=======================================================

        pnCardTabPhieuPhat = new JPanel(cardPhieuPhatGroup);
        pnCardTabPhieuPhat.add(pnTablePhieuPhat,"1");
        pnCardTabPhieuPhat.add(pnTableQuanLy,"2");

        this.add(pnCardTabPhieuPhat);
    }

    private void addEventsPhieuPhat(){
        lblTabbedPhieuPhat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedPhieuPhat.setIcon(tabbedSelected);
                lblTabbedQuanLyPhieu.setIcon(tabbedDefault);
                cardPhieuPhatGroup.show(pnCardTabPhieuPhat,"1");
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
        lblTabbedQuanLyPhieu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedQuanLyPhieu.setIcon(tabbedSelected);
                lblTabbedPhieuPhat.setIcon(tabbedDefault);
                cardPhieuPhatGroup.show(pnCardTabPhieuPhat,"2");
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
//                loadDataLenBangPhieuPhat();
                txtMaPhieuPhat.setText("");
                txtMaPhieuTra.setText("");
                txtMaSach.setText("");
                txtMaPhanSach.setText("");
                cmbLyDo.setSelectedIndex(0);
                txtThanhTien.setText("");
                txtNgayTraMuon.setText("");
                txtDocGia.setText("");
                txtTimKiem.setText("");
            }
        });
        tblPhieuPhat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                xuLyClickTblPhieuPhat();
            }
        });
        btnInThe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyXuatPhieuPhat();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyThemPhieuPhat();
                txtMaPhieuPhat.setText("");
                txtMaPhieuTra.setText("");
                txtMaSach.setText("");
                txtMaPhanSach.setText("");
                cmbLyDo.setSelectedIndex(0);
                txtThanhTien.setText("");
                txtNgayTraMuon.setText("");
                txtDocGia.setText("");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyXoaPhieuPhat();
                txtMaPhieuPhat.setText("");
                txtMaPhieuTra.setText("");
                txtMaSach.setText("");
                txtMaPhanSach.setText("");
                cmbLyDo.setSelectedIndex(0);
                txtThanhTien.setText("");
                txtNgayTraMuon.setText("");
                txtDocGia.setText("");
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTImKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyNhapFileExcel();
            }
        });
        btnPhieuTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTimPhieuTra();
//                xuLyThemMa();
            }
        });
        btnSachPhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                timSachPhatGUI = new DlgTimSachPhat();
//                if(!txtMaPhieuTra.getText().equals(""))
//                    timSachPhatGUI.loadDataLenTable(txtMaPhieuTra.getText());
//                xuLyTimSachPhat();
//                xuLyTinhToan();
            }
        });
        cmbLyDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTinhToan();
            }
        });
        txtNgayTraMuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                xuLyTinhToan();
            }
        });

    }

//    public void loadDataLenBangPhieuPhat(){
//        ppBUS.docListPhieuPhat();
//        dtmPhieuPhat.setRowCount(0);
//
//        ArrayList<PhieuPhat> dspp = ppBUS.getListPhieuPhat();
//
//        DecimalFormat dcf = new DecimalFormat("###,###");
//
//        for(PhieuPhat pp : dspp){
//            Vector vec = new Vector();
//            vec.add(pp.getMaPhieuPhat());
//            vec.add(pp.getMaPhieuTra());
//            vec.add(sachBUS.getTenSach(pp.getMaSach()));
//            vec.add(docGiaBUS.getTenDocGia(pp.getMaDocGia()));
//            vec.add(nhanVienBUS.getTenNhanVien(pp.getMaNhanVien()));
//            vec.add(pp.getLyDo());
//            vec.add(dcf.format(pp.getThanhTien()));
//            dtmPhieuPhat.addRow(vec);
//        }
//    }
//
//    public void loadDataLenBangPhieuPhat(ArrayList<PhieuPhat> dspp){
//        ppBUS.docListPhieuPhat();
//
//        DecimalFormat dcf = new DecimalFormat("###,###");
//
//        for(PhieuPhat pp : dspp){
//            Vector vec = new Vector<>();
//            vec.add(pp.getMaPhieuPhat());
//            vec.add(pp.getMaPhieuTra());
//            vec.add(sachBUS.getTenSach(pp.getMaSach()));
//            vec.add(docGiaBUS.getTenDocGia(pp.getMaDocGia()));
//            vec.add(nhanVienBUS.getTenNhanVien(pp.getMaNhanVien()));
//            vec.add(pp.getLyDo());
//            vec.add(dcf.format(pp.getThanhTien()));
//            dtmPhieuPhat.addRow(vec);
//        }
//    }
//
//    public void xuLyClickTblPhieuPhat(){
//        int row = tblPhieuPhat.getSelectedRow();
//        if(row > -1){
//            String maPhieuPhat = tblPhieuPhat.getValueAt(row,0)+"";
//            String maPhieuTra = tblPhieuPhat.getValueAt(row,1)+"";
//            String tenSach = tblPhieuPhat.getValueAt(row,2)+"";
//            String docGia = tblPhieuPhat.getValueAt(row,3)+"";
//            String nhanVien = tblPhieuPhat.getValueAt(row,4)+"";
//            String lyDo = tblPhieuPhat.getValueAt(row,5)+"";
//            String thanhTien = tblPhieuPhat.getValueAt(row,6)+"";
//
//            String[] tachChuoi1 = lyDo.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
//            String lyDoDau = tachChuoi1[0].trim();
//            if (tachChuoi1.length >= 2) {
//                String soNgay = tachChuoi1[1].trim();
//                txtNgayTraMuon.setText(soNgay);
//            }
//            String[] tachChuoi = lyDoDau.split("\\+");
//            String lyDoDau1 = tachChuoi[0].trim();
//            int index = -1;
//            for(int i=0;i<cmbLyDo.getItemCount();i++){
//                if(cmbLyDo.getItemAt(i).equals(lyDoDau1)){
//                    index = i;
//                    break;
//                }
//            }
//            String maPS = String.valueOf(ppBUS.getPhieuPhatTheoMa(Integer.parseInt(maPhieuPhat)).getMaPhanSach());
//
//            txtMaPhieuPhat.setText(maPhieuPhat);
//            txtMaPhieuTra.setText(maPhieuTra);
//            txtMaSach.setText(String.valueOf(sachBUS.getMaSach(tenSach)));
//            txtMaPhanSach.setText(maPS);
//            txtDocGia.setText(docGia);
//            cmbLyDo.setSelectedIndex(index);
//            txtThanhTien.setText(thanhTien.replace(",",""));
//        }
//    }
//
//    private void xuLyThemPhieuPhat(){
//        String lyDo="",lyDo1="",lyDoTong;
//        if(!cmbLyDo.getSelectedItem().equals("Trả muộn")){
//            lyDo = String.valueOf(cmbLyDo.getSelectedItem());
//            if (txtNgayTraMuon.getText().trim().equals("") || Integer.parseInt(txtNgayTraMuon.getText()) == 0) {
//                lyDo1 = " ";
//            } else {
//                lyDo1 = " + " + "Trả muộn" + " " + txtNgayTraMuon.getText() + " ngày";
//            }
//        }else {
//            lyDo = String.valueOf(cmbLyDo.getSelectedItem())+ " " + txtNgayTraMuon.getText() + " ngày";
//        }
//        lyDoTong = lyDo + lyDo1;
//        boolean flag = ppBUS.themPhieuPhat(txtMaPhieuPhat.getText(),
//                            txtMaPhieuTra.getText(),txtMaSach.getText(),txtMaPhanSach.getText(),
//                            txtDocGia.getText(), String.valueOf(dangNhapGUI.maTaiKhoan()),lyDoTong,
//                            txtThanhTien.getText());
//        ppBUS.docListPhieuPhat();
//        boolean flag1 = phanSachBUS.themPhanSach(txtMaPhanSach.getText(),txtMaSach.getText(),cmbLyDo.getSelectedItem()+"");
//        loadDataLenBangPhieuPhat();
//    }
//
//    private void xuLyXoaPhieuPhat(){
//        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
//        if(dlg.OK_OPTION == dlg.getAction()) {
//            boolean flag = ppBUS.xoaPhieuPhat(txtMaPhieuPhat.getText());
//            boolean flag1 = phanSachBUS.xoaPhanSach(txtMaPhanSach.getText(),txtMaSach.getText());
//            loadDataLenBangPhieuPhat();
//        }
//    }
//
//    private void xuLySuaPhieuPhat(){
//        String lyDo="",lyDo1="",lyDoTong;
//        if(!cmbLyDo.getSelectedItem().equals("Trả muộn")){
//            lyDo = String.valueOf(cmbLyDo.getSelectedItem());
//            if (!txtNgayTraMuon.getText().trim().equals("") || !txtNgayTraMuon.getText().trim().equals("0")) {
//                lyDo1 = " + " + "Trả muộn" + " " + txtNgayTraMuon.getText() + " ngày";
//            } else {
//                lyDo1 = "";
//            }
//        }else {
//            lyDo = String.valueOf(cmbLyDo.getSelectedItem())+ " " + txtNgayTraMuon.getText() + " ngày";
//        }
//        lyDoTong = lyDo + lyDo1;
//        boolean flag = ppBUS.suaPhieuPhat(txtMaPhieuPhat.getText(),
//                txtMaPhieuTra.getText(),txtMaSach.getText(),txtMaPhanSach.getText(),
//                txtDocGia.getText(), String.valueOf(dangNhapGUI.maTaiKhoan()),lyDoTong,
//                txtThanhTien.getText());
//        boolean flag1 = phanSachBUS.suaPhanSach(txtMaPhanSach.getText(),txtMaSach.getText(),cmbLyDo.getSelectedItem()+"");
//        ppBUS.docListPhieuPhat();
//        loadDataLenBangPhieuPhat();
//    }
//
//    private void xuLyXuatFileExcel(){
//        XuLyFileExcel xuatFile = new XuLyFileExcel();
//        xuatFile.xuatExcel(tblPhieuPhat);
//    }
//
//    private void xuLyNhapFileExcel(){
//        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
//        if(dlg.getAction() != MyDialog.OK_OPTION){
//            return;
//        }
//
//        XuLyFileExcel nhapFile = new XuLyFileExcel();
//        nhapFile.nhapExcel(tblPhieuPhat);
//
//        int row = tblPhieuPhat.getRowCount();
//        for(int i=0;i<row;i++){
//            String maPT = String.valueOf(tblPhieuPhat.getValueAt(i,1));
//            String tenSach = String.valueOf(tblPhieuPhat.getValueAt(i,2));
//            String docGia = String.valueOf(tblPhieuPhat.getValueAt(i,3));
//            String nhanVien = String.valueOf(tblPhieuPhat.getValueAt(i,4));
//            String lyDo = String.valueOf(tblPhieuPhat.getValueAt(i,5));
//            String thanhTien = String.valueOf(tblPhieuPhat.getValueAt(i,6));
//            String maPS = String.valueOf(ppBUS.getPhieuPhatTheoMa(Integer.parseInt(maPT)).getMaPhanSach());
//
//            ppBUS.nhapPhieuPhatExcel(maPT,tenSach,maPS,docGia,nhanVien,lyDo,thanhTien);
//        }
//    }
//
//    private void xuLyXuatPhieuPhat(){
//        ArrayList<Vector> dspp = new ArrayList<>();
//        int row = tblPhieuPhat.getSelectedRow();
//        int count = 0;
//        if(txtMaPhieuPhat.getText().equals("")){
//            new MyDialog("Chưa chọn phiếu phạt để in phiếu!!!", MyDialog.ERROR_DIALOG);
//            return;
//        }
//        for (int i=0;i<row;i++){
//            if(txtMaPhieuPhat.getText().equals(tblPhieuPhat.getValueAt(i,0))){
//                count = 1;
//                break;
//            }
//        }
//        if(count != 0){
//            new MyDialog("Phiếu phạt chưa được tạo!!!",MyDialog.ERROR_DIALOG);
//            return;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Vector vec = new Vector();
//        vec.add(tblPhieuPhat.getValueAt(row, 0));
//        vec.add(tblPhieuPhat.getValueAt(row, 1));
//        vec.add(sachBUS.getMaSach(String.valueOf(tblPhieuPhat.getValueAt(row, 2))));
//        vec.add(String.valueOf(ppBUS.getPhieuPhatTheoMa(Integer.parseInt(tblPhieuPhat.getValueAt(row, 0)+"")).getMaPhanSach()));
//        vec.add(tblPhieuPhat.getValueAt(row, 2));
//        vec.add(tblPhieuPhat.getValueAt(row, 3));
//        vec.add(tblPhieuPhat.getValueAt(row, 4));
//        vec.add(tblPhieuPhat.getValueAt(row, 5));
//        vec.add(tblPhieuPhat.getValueAt(row, 6));
//        dspp.add(vec);
//        int maPP = Integer.parseInt(txtMaPhieuPhat.getText());
//        long tien = Long.parseLong(txtThanhTien.getText());
//        XuatPhieuPhatGUI phieuPhatGUI = new XuatPhieuPhatGUI(dspp,maPP,txtDocGia.getText(),
//                nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()),
//                String.valueOf(sdf.format(ptBUS.getPhieuTra(txtMaPhieuTra.getText()).getNgayTraThuc())),
//                Long.parseLong(txtThanhTien.getText()));
//        phieuPhatGUI.setVisible(true);
//    }
//
//    private void xuLyTImKiem(){
//        String docGia = txtTimKiem.getText();
//        dtmPhieuPhat.setRowCount(0);
//        ArrayList<PhieuPhat> dspp = null;
//        dspp = ppBUS.getListPhieuPhatTheoDocGia(docGia);
//
//        DecimalFormat dcf = new DecimalFormat("###,###");
//        for(PhieuPhat pp : dspp){
//            Vector vec = new Vector<>();
//            vec.add(pp.getMaPhieuPhat());
//            vec.add(pp.getMaPhieuTra());
//            vec.add(sachBUS.getTenSach(pp.getMaSach()));
//            vec.add(String.valueOf(ppBUS.getPhieuPhatTheoMa(pp.getMaPhieuPhat()).getMaPhanSach()));
//            vec.add(docGiaBUS.getTenDocGia(pp.getMaDocGia()));
//            vec.add(nhanVienBUS.getTenNhanVien(pp.getMaNhanVien()));
//            vec.add(pp.getLyDo());
//            vec.add(dcf.format(pp.getThanhTien()));
//            dtmPhieuPhat.addRow(vec);
//        }
//        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspp.size(), MyDialog.INFO_DIALOG);
//    }
//
//    private void xuLyTimKiemKhoang(){
//        dtmPhieuPhat.setRowCount(0);
//        String min = String.valueOf(txtMin.getSelectedItem());
//        String max = String.valueOf(txtMax.getSelectedItem());
//        ArrayList<PhieuPhat> dspp = ppBUS.timKiemTheoKhoang(min,max);
//        if(dspp == null){
//            return;
//        }
//        loadDataLenBangPhieuPhat(dspp);
//        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspp.size(), MyDialog.INFO_DIALOG);
//    }
//
//    private void xuLyTimPhieuTra(){
//        timPhieuTraGUI = new DlgTimPhieuTra();
//        timPhieuTraGUI.setVisible(true);
//        if(timPhieuTraGUI.phieuTraTimDuoc != null){
//            txtMaPhieuTra.setText(String.valueOf(timPhieuTraGUI.phieuTraTimDuoc.getMaPhieuTra()));
//            txtDocGia.setText(docGiaBUS.getTenDocGia(timPhieuTraGUI.phieuTraTimDuoc.getMaDocGia()));
//        }
//    }
//
//    private void xuLyTimSachPhat(){
//        if(txtMaPhieuTra.getText().equals("")){
//            new MyDialog("Chưa chọn mã phiếu trả!!!", MyDialog.ERROR_DIALOG);
//            return;
//        }
//        timSachPhatGUI.xuLyChonCTPhieuTra();
//        timSachPhatGUI.setMaPt(txtMaPhieuTra.getText());
//        timSachPhatGUI.setVisible(true);
//        if(timSachPhatGUI.ctPhieuTraTimDuoc != null){
//            txtMaSach.setText(String.valueOf(timSachPhatGUI.ctPhieuTraTimDuoc.getMaSach()));
//            txtMaPhanSach.setText(String.valueOf(timSachPhatGUI.ctPhieuTraTimDuoc.getMaPhanSach()));
//        }
//    }
//
//    private void xuLyTinhToan(){
//        long traMuon = 0;
//        long giaSach = 0;
//        if(sachBUS.getSach(String.valueOf(txtMaSach.getText())) != null){
//            giaSach = sachBUS.getSach(String.valueOf(txtMaSach.getText())).getGiaSach();
//        }
//        long phat = 0;
//        long tongTienPhat = 0;
//        if(txtNgayTraMuon.getText().trim().equals("") || txtNgayTraMuon.getText().trim().equals("0")) {
//            traMuon = 0;
//        }else {
//            try{
//                if(Long.parseLong(txtNgayTraMuon.getText()) % 10 != 0) {
//                    traMuon = Long.parseLong(txtNgayTraMuon.getText()) / 10 + 1;
//                }else {
//                    traMuon = Long.parseLong(txtNgayTraMuon.getText()) / 10;
//                }
//            }catch (Exception e){
//                new MyDialog("Hãy nhập giá trị nguyên phù hợp!!!", MyDialog.ERROR_DIALOG);
//                return;
//            }
//        }
////        "Trả muộn", "Rách sách", "Ướt sách", "Mất sách", "Mất trang"
////        Trả muộn: 10% giá sách/10 ngày<br>Rách sách: 25% giá sách<br>Ướt, mất sách: 100% giá sách<br>Mất trang: 50% giá sách
//        if(cmbLyDo.getSelectedItem().equals("Rách sách")){
//            phat = (giaSach * 25)/100;
//        }else if(cmbLyDo.getSelectedItem().equals("Ướt sách") || cmbLyDo.getSelectedItem().equals("Mất sách")){
//            phat = giaSach;
//        }else if (cmbLyDo.getSelectedItem().equals("Mất trang")){
//            phat = (giaSach * 50)/100;
//        }else {
//            phat = 0;
//        }
//
//        tongTienPhat = ((giaSach * traMuon)/10) + phat;
//        txtThanhTien.setText(String.valueOf(tongTienPhat));
//    }
//
//    private void xuLyThemMa(){
//        if(txtMaPhieuTra.getText() != ""){
//            int maPP = ppBUS.getMaPhieuPhatMoiNhat()+1;
//            txtMaPhieuPhat.setText(String.valueOf(maPP));
//        }
//    }
}

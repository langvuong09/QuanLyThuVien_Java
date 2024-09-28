package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.PhieuPhat;
import QuanLyThuVien.DTO.CTPhieuPhat;

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
    private PhieuPhatBUS ppBUS = new PhieuPhatBUS();
    private CTPhieuPhatBUS ctPhieuPhatBUS = new CTPhieuPhatBUS();

    public PnQuanLyPhieuPhatGUI(){
        changLNF("Windows");
        addConTrolsPhieuPhat();
        addEventsPhieuPhat();
    }

    final Color colorPanel = new Color(247, 247, 247);

    MyTable tblCTPhieuPhat, tblPhieuPhat, tblXemCTPhieuPhat;
    DefaultTableModel dtmCTPhieuPhat, dtmPhieuPhat, dtmXemCTPhieuPhat;
    CardLayout cardPhieuPhatGroup = new CardLayout();
    JPanel pnCardTabPhieuPhat;
    JLabel lblTabbedPhieuPhat, lblTabbedQuanLyPhieu;
    JTextField txtMaPhieuPhat, txtMaPhieuTra, txtMaSach, txtMaPhanSach, txtDocGia, txtThanhTien, txtNgayTraMuon, txtTimKiemDG;
    JComboBox<String> cmbLyDo;
    JButton btnThem, btnXoa, btnInThe, btnReset, btnXuatExcel, btnNhapExcel, btnTimKiem, btnChon, btnPhieuTra, btnSachPhat, btnTimDG;
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
        txtTimKiemDG = new JTextField(x);

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

        pnTablePhieuPhat.add(pnThongTinPhieuPhat);

        //=================BUTTON===============


        btnThem = new JButton("Thêm phiếu");
        btnXoa = new JButton("Xoá CT sách");
        btnPhieuTra = new JButton("...");
        btnSachPhat = new JButton("...");
        btnChon = new JButton("Chọn");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnPhieuTra.setFont(fontButton);
        btnSachPhat.setFont(fontButton);
        btnChon.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnChon.setIcon(new ImageIcon());

        btnThem.setBounds(170,310,170,40);
        btnXoa.setBounds(345,310,165,40);
        btnPhieuTra.setBounds(390,55,30,25);
        btnSachPhat.setBounds(390,100,30,25);
        btnChon.setBounds(200,180,120,30);

        pnThongTinPhieuPhat.add(btnThem);
        pnThongTinPhieuPhat.add(btnXoa);
        pnThongTinPhieuPhat.add(btnPhieuTra);
        pnThongTinPhieuPhat.add(btnSachPhat);
        pnThongTinPhieuPhat.add(btnChon);

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
        pnTitleQuanLy.add(btnReset);
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
        dtmPhieuPhat.addColumn("Nhân viên");
        dtmPhieuPhat.addColumn("Thành tiền");
        tblPhieuPhat = new MyTable(dtmPhieuPhat);

        tblPhieuPhat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuPhat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TableColumnModel columnModelQuanLy = tblPhieuPhat.getColumnModel();
        columnModelQuanLy.getColumn(0).setPreferredWidth(50);
        columnModelQuanLy.getColumn(1).setPreferredWidth(50);
        columnModelQuanLy.getColumn(2).setPreferredWidth(150);
        columnModelQuanLy.getColumn(3).setPreferredWidth(150);
        columnModelQuanLy.getColumn(4).setPreferredWidth(50);

        JScrollPane scrTblQuanLy = new JScrollPane(tblPhieuPhat);
        scrTblQuanLy.setPreferredSize(new Dimension(900,500));
        scrTblQuanLy.setBounds(0,95,800,220);
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
        columnModelXemCT.getColumn(2).setPreferredWidth(100);
        columnModelXemCT.getColumn(3).setPreferredWidth(150);
        columnModelXemCT.getColumn(4).setPreferredWidth(150);
        columnModelXemCT.getColumn(5).setPreferredWidth(50);

        JScrollPane scrTblXemCT = new JScrollPane(tblXemCTPhieuPhat);
        scrTblXemCT.setPreferredSize(new Dimension(900,500));
        scrTblXemCT.setBounds(0,370,800,200);
        //</editor-fold>
        pnQuanLy.add(scrTblXemCT, BorderLayout.CENTER);

        //=======================================================

        JLabel lblPhieuPhat = new JLabel("Phiếu phạt");
        JLabel lblCTPhieuPhat = new JLabel("Chi tiết phiếu phạt");
        lblPhieuPhat.setFont(new Font("Arial", Font.BOLD, 22));
        lblCTPhieuPhat.setFont(new Font("Arial", Font.BOLD, 22));
        lblPhieuPhat.setBounds(335,65,200,22);
        lblCTPhieuPhat.setBounds(290,340,200,22);

        JLabel lblTimKiemDG = new JLabel("Tìm đọc giả theo tên:");
        txtTimKiemDG.setFont(font);
        lblTimKiemDG.setFont(font);
        lblTimKiemDG.setBounds(5,20,200,25);
        txtTimKiemDG.setBounds(165,20,180,26);

        btnTimDG = new JButton("Tìm");
        btnInThe = new JButton("In thẻ");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnTimDG.setFont(fontButton);
        btnInThe.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnTimDG.setIcon(new ImageIcon("image/Search-icon.png"));
        btnInThe.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnTimDG.setBounds(350,15,100,40);
        btnInThe.setBounds(455,15,110,40);
        btnXuatExcel.setBounds(570,15,110,40);
        btnNhapExcel.setBounds(685,15,110,40);

        pnQuanLy.add(lblPhieuPhat);
        pnQuanLy.add(lblCTPhieuPhat);
        pnQuanLy.add(lblTimKiemDG);
        pnQuanLy.add(txtTimKiemDG);
        pnQuanLy.add(btnTimDG);
        pnQuanLy.add(btnInThe);
        pnQuanLy.add(btnXuatExcel);
        pnQuanLy.add(btnNhapExcel);

        pnTableQuanLy.add(pnQuanLy);

        //=======================================================

        pnCardTabPhieuPhat = new JPanel(cardPhieuPhatGroup);
        pnCardTabPhieuPhat.add(pnTablePhieuPhat,"1");
        pnCardTabPhieuPhat.add(pnTableQuanLy,"2");

        loadDataLenBangPhieuPhat();
        loadDataLenBangCTPhieuPhat();

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
                loadDataLenBangPhieuPhat();
                loadDataLenBangCTPhieuPhat();
                txtMaPhieuPhat.setText("");
                txtMaPhieuTra.setText("");
                txtMaSach.setText("");
                txtMaPhanSach.setText("");
                cmbLyDo.setSelectedIndex(0);
                txtThanhTien.setText("");
                txtNgayTraMuon.setText("");
                txtDocGia.setText("");
                dtmCTPhieuPhat.setRowCount(0);
            }
        });
        tblPhieuPhat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuPhatTheoMa();
            }
        });
        btnInThe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatPhieuPhat();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemPhieuPhat();
                txtMaPhieuPhat.setText("");
                txtMaPhieuTra.setText("");
                txtMaSach.setText("");
                txtMaPhanSach.setText("");
                cmbLyDo.setSelectedIndex(0);
                txtThanhTien.setText("");
                txtNgayTraMuon.setText("");
                txtDocGia.setText("");
                dtmCTPhieuPhat.setRowCount(0);
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaCTPhieuPhat();
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
        btnPhieuTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimPhieuTra();
                xuLyThemMa();
            }
        });
        btnSachPhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timSachPhatGUI = new DlgTimSachPhat();
                if(!txtMaPhieuTra.getText().equals(""))
                    timSachPhatGUI.loadDataLenTable(txtMaPhieuTra.getText());
                xuLyTimSachPhat();
                xuLyTinhToan();
            }
        });
        cmbLyDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTinhToan();
            }
        });
        txtNgayTraMuon.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyTinhToan();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyTinhToan();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyTinhToan();
            }
        });
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemVaoTable();
            }
        });
        btnTimDG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
    }

    public void loadDataLenBangPhieuPhat(){
        ppBUS.docListPhieuPhat();
        dtmPhieuPhat.setRowCount(0);

        ArrayList<PhieuPhat> dspp = ppBUS.getListPhieuPhat();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for(PhieuPhat pp : dspp){
            Vector vec = new Vector();
            vec.add(pp.getMaPhieuPhat());
            vec.add(pp.getMaPhieuTra());
            vec.add(docGiaBUS.getTenDocGia(pp.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pp.getMaNhanVien()));
            vec.add(dcf.format(pp.getThanhTien()));
            dtmPhieuPhat.addRow(vec);
        }
    }

    public void loadDataLenBangCTPhieuPhat(){
        ctPhieuPhatBUS.docDanhSach();
        dtmXemCTPhieuPhat.setRowCount(0);

        ArrayList<CTPhieuPhat> dsctpp = ctPhieuPhatBUS.getListCTPhieuPhat();

        DecimalFormat dcf = new DecimalFormat("###,###");

        for(CTPhieuPhat ctpp : dsctpp){
            Vector vec = new Vector();
            vec.add(ctpp.getMaPhieuPhat());
            vec.add(ctpp.getMaSach());
            vec.add(ctpp.getMaPhanSach());
            vec.add(sachBUS.getTenSach(ctpp.getMaSach()));
            vec.add(ctpp.getLyDo());
            vec.add(dcf.format(ctpp.getTienPhat()));
            dtmXemCTPhieuPhat.addRow(vec);
        }
    }

    private void xuLyThemVaoTable(){
        if(txtMaSach.getText().equals("")){
            new MyDialog("Chưa chọn sách để phạt!", MyDialog.ERROR_DIALOG);
            return;
        }
        if(txtMaPhieuTra.getText().equals("")){
            new MyDialog("Chưa chọn phiếu trả!", MyDialog.ERROR_DIALOG);
            return;
        }

        int maS = Integer.parseInt(txtMaSach.getText());
        int maPS = Integer.parseInt(txtMaPhanSach.getText());
        long tienPhat = Long.parseLong(txtThanhTien.getText());
        String lyDo="", lyDo1="", lyDoTong;

        if(!cmbLyDo.getSelectedItem().equals("Trả muộn")){
            lyDo = String.valueOf(cmbLyDo.getSelectedItem());
            if (txtNgayTraMuon.getText().trim().equals("") || Integer.parseInt(txtNgayTraMuon.getText()) == 0) {
                lyDo1 = " ";
            } else {
                lyDo1 = " + " + "Trả muộn" + " " + txtNgayTraMuon.getText() + " ngày";
            }
        } else {
            lyDo = String.valueOf(cmbLyDo.getSelectedItem()) + " " + txtNgayTraMuon.getText() + " ngày";
        }
        lyDoTong = lyDo + lyDo1;
        String tenS = sachBUS.getTenSach(maS);
        Vector vec = new Vector<>();
        vec.add(maS);
        vec.add(maPS);
        vec.add(tenS);
        vec.add(lyDoTong);
        vec.add(tienPhat);
        dtmCTPhieuPhat.addRow(vec);
    }


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
    public void xuLyClickTblPhieuPhatTheoMa(){
        int row = tblPhieuPhat.getSelectedRow();
        dtmXemCTPhieuPhat.setRowCount(0);
        if(row > -1){
            String maPhieuPhat = tblPhieuPhat.getValueAt(row,0)+"";
            int maPP = Integer.parseInt(maPhieuPhat);
            ArrayList<CTPhieuPhat> dsctpp = ctPhieuPhatBUS.getListPhieuPhatTheoMa(maPP);
            for(CTPhieuPhat ctpp : dsctpp){
                Vector vec = new Vector<>();
                vec.add(ctpp.getMaPhieuPhat());
                vec.add(ctpp.getMaSach());
                vec.add(ctpp.getMaPhanSach());
                vec.add(sachBUS.getTenSach(ctpp.getMaSach()));
                vec.add(ctpp.getLyDo());
                vec.add(ctpp.getTienPhat());
                dtmXemCTPhieuPhat.addRow(vec);
            }
        }
    }

    private void xuLyThemPhieuPhat(){

        boolean flag = ppBUS.themPhieuPhat(txtMaPhieuPhat.getText(), txtMaPhieuTra.getText(),
                            txtDocGia.getText(),String.valueOf(dangNhapGUI.maTaiKhoan()), txtThanhTien.getText());
        int maPP = Integer.parseInt(txtMaPhieuPhat.getText());
        if(flag) {
            int count = tblCTPhieuPhat.getRowCount();
            for (int i = 0; i < count; i++) {
                int maS = Integer.parseInt(tblCTPhieuPhat.getValueAt(i,0)+"");
                int maPS = Integer.parseInt(tblCTPhieuPhat.getValueAt(i,1)+"");
                String lyDo = String.valueOf(tblCTPhieuPhat.getValueAt(i,3)+"");
                long tienPhat = Long.parseLong(tblCTPhieuPhat.getValueAt(i,4)+"");
                boolean flag1 = ctPhieuPhatBUS.themCTPhieuPhat(maPP,maS,maPS,lyDo,tienPhat);
                if(lyDo.contains("+")) {
                    String[] lyDoTong = lyDo.split("//+");
                    String lyDo1 = lyDoTong[0];
                    boolean flag2 = phanSachBUS.suaPhanSach(String.valueOf(maPS), String.valueOf(maS), lyDo1);
                }
            }
        }
        ppBUS.docListPhieuPhat();
        loadDataLenBangPhieuPhat();
        loadDataLenBangCTPhieuPhat();
    }

    private void xuLyXoaCTPhieuPhat(){
        int row = tblCTPhieuPhat.getSelectedRow();
        if(row > -1){
            dtmCTPhieuPhat.removeRow(row);
        }else {
            new MyDialog("Hãy chọn sách để xóa!!!", MyDialog.ERROR_DIALOG);
        }
    }

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
    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblPhieuPhat);
    }

    private void xuLyNhapFileExcel(){
        loadDataLenBangCTPhieuPhat();
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if(dlg.getAction() != MyDialog.OK_OPTION){
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblPhieuPhat);

        int row = tblPhieuPhat.getRowCount();
        for(int i=0;i<row;i++){
            String maPT = String.valueOf(tblPhieuPhat.getValueAt(i,1));
            String docGia = String.valueOf(tblPhieuPhat.getValueAt(i,3));
            String nhanVien = String.valueOf(tblPhieuPhat.getValueAt(i,4));
            String thanhTien = String.valueOf(tblPhieuPhat.getValueAt(i,6));

            ppBUS.nhapPhieuPhatExcel(maPT,docGia,nhanVien,thanhTien);
        }
    }

    private void xuLyXuatPhieuPhat(){
        ArrayList<Vector> dsctpp = new ArrayList<>();
        int row = tblPhieuPhat.getSelectedRow();
        int count = 0;
        if(row <= -1){
            new MyDialog("Chưa chọn phiếu phạt để in phiếu!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int maPP = Integer.parseInt(tblPhieuPhat.getValueAt(row,0)+"");
        String maPT = tblPhieuPhat.getValueAt(row,1)+"";
        long tongTien = Long.parseLong((tblPhieuPhat.getValueAt(row,4)+"").replace(",",""));
        String tenDG = tblPhieuPhat.getValueAt(row,2)+"";
        int rowCT = tblXemCTPhieuPhat.getRowCount();
        for(int i=0;i<rowCT;i++){
            Vector vec = new Vector();
            vec.add(tblXemCTPhieuPhat.getValueAt(i,1));
            vec.add(tblXemCTPhieuPhat.getValueAt(i,2));
            vec.add(tblXemCTPhieuPhat.getValueAt(i,3));
            vec.add(tblXemCTPhieuPhat.getValueAt(i,4));
            vec.add(tblXemCTPhieuPhat.getValueAt(i,5));
            dsctpp.add(vec);
        }

        XuatPhieuPhatGUI phieuPhatGUI = new XuatPhieuPhatGUI(dsctpp,maPP,tenDG,
                nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()),
                String.valueOf(sdf.format(ptBUS.getPhieuTra(maPT).getNgayTraThuc())),
                tongTien);
        phieuPhatGUI.setVisible(true);
    }

    private void xuLyTimKiem(){
        String docGia = txtTimKiemDG.getText();
        dtmPhieuPhat.setRowCount(0);
        ArrayList<PhieuPhat> dspp = null;
        dspp = ppBUS.getListPhieuPhatTheoDocGia(docGia);

        DecimalFormat dcf = new DecimalFormat("###,###");
        for(PhieuPhat pp : dspp){
            Vector vec = new Vector<>();
            vec.add(pp.getMaPhieuPhat());
            vec.add(pp.getMaPhieuTra());
            vec.add(docGiaBUS.getTenDocGia(pp.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pp.getMaNhanVien()));
            vec.add(dcf.format(pp.getThanhTien()));
            dtmPhieuPhat.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspp.size(), MyDialog.INFO_DIALOG);
    }


    private void xuLyTimPhieuTra(){
        timPhieuTraGUI = new DlgTimPhieuTra();
        timPhieuTraGUI.setVisible(true);
        if(timPhieuTraGUI.phieuTraTimDuoc != null){
            txtMaPhieuTra.setText(String.valueOf(timPhieuTraGUI.phieuTraTimDuoc.getMaPhieuTra()));
            txtDocGia.setText(docGiaBUS.getTenDocGia(timPhieuTraGUI.phieuTraTimDuoc.getMaDocGia()));
        }
    }

    private void xuLyTimSachPhat(){
        if(txtMaPhieuTra.getText().equals("")){
            new MyDialog("Chưa chọn mã phiếu trả!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        timSachPhatGUI.xuLyChonCTPhieuTra();
        timSachPhatGUI.setMaPt(txtMaPhieuTra.getText());
        timSachPhatGUI.setVisible(true);
        if(timSachPhatGUI.ctPhieuTraTimDuoc != null){
            txtMaSach.setText(String.valueOf(timSachPhatGUI.ctPhieuTraTimDuoc.getMaSach()));
            txtMaPhanSach.setText(String.valueOf(timSachPhatGUI.ctPhieuTraTimDuoc.getMaPhanSach()));
        }
    }

    private void xuLyTinhToan(){
        long traMuon = 0;
        long giaSach = 0;
        if(sachBUS.getSach(String.valueOf(txtMaSach.getText())) != null){
            giaSach = sachBUS.getSach(String.valueOf(txtMaSach.getText())).getGiaSach();
        }
        long phat = 0;
        long tongTienPhat = 0;
        if(txtNgayTraMuon.getText().trim().equals("") || txtNgayTraMuon.getText().trim().equals("0")) {
            traMuon = 0;
        }else {
            try{
                if(Long.parseLong(txtNgayTraMuon.getText()) % 10 != 0) {
                    traMuon = Long.parseLong(txtNgayTraMuon.getText()) / 10 + 1;
                }else {
                    traMuon = Long.parseLong(txtNgayTraMuon.getText()) / 10;
                }
            }catch (Exception e){
                new MyDialog("Hãy nhập giá trị nguyên phù hợp!!!", MyDialog.ERROR_DIALOG);
                return;
            }
        }
//        "Trả muộn", "Rách sách", "Ướt sách", "Mất sách", "Mất trang"
//        Trả muộn: 10% giá sách/10 ngày<br>Rách sách: 25% giá sách<br>Ướt, mất sách: 100% giá sách<br>Mất trang: 50% giá sách
        if(cmbLyDo.getSelectedItem().equals("Rách sách")){
            phat = (giaSach * 25)/100;
        }else if(cmbLyDo.getSelectedItem().equals("Ướt sách") || cmbLyDo.getSelectedItem().equals("Mất sách")){
            phat = giaSach;
        }else if (cmbLyDo.getSelectedItem().equals("Mất trang")){
            phat = (giaSach * 50)/100;
        }else {
            phat = 0;
        }

        tongTienPhat = ((giaSach * traMuon)/10) + phat;
        txtThanhTien.setText(String.valueOf(tongTienPhat));
    }

    private void xuLyThemMa(){
        if(txtMaPhieuTra.getText() != ""){
            int maPP = ppBUS.getMaPhieuPhatMoiNhat()+1;
            txtMaPhieuPhat.setText(String.valueOf(maPP));
        }
    }
}

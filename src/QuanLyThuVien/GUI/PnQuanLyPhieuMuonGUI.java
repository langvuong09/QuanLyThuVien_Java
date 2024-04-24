package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.CTPhieuMuonBUS;
import QuanLyThuVien.BUS.PhieuMuonBUS;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DAO.SachDAO;
import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.BUS.NhanVienBUS;
import QuanLyThuVien.DTO.NhanVien;
import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.DTO.DocGia;

import static Main.Main.changLNF;

import MyCustom.XuLyFileExcel;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.awt.*;
import java.awt.event.*;import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLyPhieuMuonGUI extends JPanel{

    private DlgTimDocGia timDocGiaGUI = new DlgTimDocGia();
    private DlgTimSach timSachGUI = new DlgTimSach();
    private SachBUS sachBUS = new SachBUS();
    private SachDAO sachDAO = new SachDAO();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();


    public PnQuanLyPhieuMuonGUI(){
        changLNF("Windows");
        addConTrolsPhieuMuon();
        addEventsPhieuMuon();
    }

    PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblPhieuMuon, tblSachMuon;
    DefaultTableModel dtmPhieuMuon, dtmSachMuon;
    JTextField txtMaPhieuMuon,txtDocGia, txtNgayMuon, txtNgayTra, txtTongTien, txtTimKiem, txtMaSach, txtTenSach, txtThanhTien;
    JButton btnThem, btnXoa, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnDocGia, btnSach;

    private void addConTrolsPhieuMuon(){
        Font font = new Font("Tahoma", Font.PLAIN,16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL Phiếu mượn
        =========================================================================
         */
        JPanel pnTablePhieuMuon = new TransparentPanel();
        pnTablePhieuMuon.setLayout(new BorderLayout());

        JPanel pnTitlePhieuMuon = new TransparentPanel();
        JLabel lblTitlePhieuMuon = new JLabel("Quản lý phiếu mượn");
        lblTitlePhieuMuon.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitlePhieuMuon.add(lblTitlePhieuMuon);
        pnTitlePhieuMuon.add(btnReset);
        pnTablePhieuMuon.add(pnTitlePhieuMuon, BorderLayout.NORTH);

        //=================PANEL INPUT===========
        int x =15,y=15;
        txtMaPhieuMuon = new JTextField(x);
        txtDocGia = new JTextField(x);
        txtNgayMuon = new JTextField(x);
        txtNgayTra = new JTextField(x);
        txtTongTien = new JTextField(x);
        txtMaSach = new JTextField(y);
        txtTenSach = new JTextField(y);
        txtThanhTien = new JTextField(y);
        txtTimKiem = new JTextField(y);


        //=================Thông tin phiếu mượn==============

        JPanel pnThongTinPhieuMuon = new TransparentPanel();
        pnThongTinPhieuMuon.setLayout(null);

        JLabel lblMaPhieuMuon = new JLabel("Mã phiếu mượn:");
        lblMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setEditable(false);
        lblMaPhieuMuon.setBounds(20,50,150,25);
        txtMaPhieuMuon.setBounds(200,50,220,25);

        JLabel lblDocGia = new JLabel("Đọc giả:");
        lblDocGia.setFont(font);
        txtDocGia.setFont(font);
        txtDocGia.setEditable(false);
        lblDocGia.setBounds(20,100,150,25);
        txtDocGia.setBounds(200,100,220,25);

        JLabel lblNgayMuon = new JLabel("Ngày mượn:");
        lblNgayMuon.setFont(font);
        txtNgayMuon.setFont(font);
        txtNgayMuon.setEditable(false);
        lblNgayMuon.setBounds(20,150,150,25);
        txtNgayMuon.setBounds(200,150,220,25);

        JLabel lblNgayTra = new JLabel("Hạn trả:");
        lblNgayTra.setFont(font);
        txtNgayTra.setFont(font);
        txtNgayTra.setEditable(false);
        lblNgayTra.setBounds(20,200,150,25);
        txtNgayTra.setBounds(200,200,220,25);

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setFont(font);
        txtTongTien.setFont(font);
        txtTongTien.setEditable(false);
        lblTongTien.setBounds(20,250,150,25);
        txtTongTien.setBounds(200,250,220,25);

        JLabel lblTimKiem = new JLabel("Tên đọc giả cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20,300,300,25);
        txtTimKiem.setBounds(210,300,250,25);

        pnThongTinPhieuMuon.add(lblMaPhieuMuon);
        pnThongTinPhieuMuon.add(txtMaPhieuMuon);
        pnThongTinPhieuMuon.add(lblDocGia);
        pnThongTinPhieuMuon.add(txtDocGia);
        pnThongTinPhieuMuon.add(lblNgayMuon);
        pnThongTinPhieuMuon.add(txtNgayMuon);
        pnThongTinPhieuMuon.add(lblNgayTra);
        pnThongTinPhieuMuon.add(txtNgayTra);
        pnThongTinPhieuMuon.add(lblTongTien);
        pnThongTinPhieuMuon.add(txtTongTien);
        pnThongTinPhieuMuon.add(lblTimKiem);
        pnThongTinPhieuMuon.add(txtTimKiem);

        //=================Chi tiết phiếu mượn==========

        JLabel lblTitleCTPhieuMuon = new JLabel("Chi tiết phiếu mượn");
        lblTitleCTPhieuMuon.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitleCTPhieuMuon.setBounds(550,0,300,25);

        JLabel lblMaSach = new JLabel("Mã sách:");
        lblMaSach.setFont(font);
        txtMaSach.setFont(font);
        txtMaSach.setEditable(false);
        lblMaSach.setBounds(510,50,120,25);
        txtMaSach.setBounds(600,50,150,25);

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        txtTenSach.setEditable(false);
        lblTenSach.setBounds(510,100,120,25);
        txtTenSach.setBounds(600,100,190,25);

        JLabel lblThanhTien = new JLabel("Tiền mượn:");
        lblThanhTien.setFont(font);
        txtThanhTien.setFont(font);
        txtThanhTien.setEditable(false);
        lblThanhTien.setBounds(510,150,120,25);
        txtThanhTien.setBounds(600,150,190,25);

        Font fontB = new Font("Tahoma", Font.PLAIN, 16);
        btnThemSach = new JButton("Thêm");
        btnThemSach.setFont(fontB);
        btnThemSach.setBounds(540,190,100,30);

        btnXoaSach = new JButton("Xóa");
        btnXoaSach.setFont(fontB);
        btnXoaSach.setBounds(660,190,100,30);

        dtmSachMuon = new DefaultTableModel();
        dtmSachMuon.addColumn("Mã");
        dtmSachMuon.addColumn("Tên sách");
        dtmSachMuon.addColumn("Giá mượn");
        tblSachMuon = new MyTable(dtmSachMuon);

        tblSachMuon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSachMuon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSachMuon = tblSachMuon.getColumnModel();
        columnModelSachMuon.getColumn(0).setPreferredWidth(20);
        columnModelSachMuon.getColumn(1).setPreferredWidth(135);
        columnModelSachMuon.getColumn(2).setPreferredWidth(65);

        JScrollPane srclblSachMuon = new JScrollPane(tblSachMuon);
        srclblSachMuon.setPreferredSize(new Dimension(200,75));
        srclblSachMuon.setBounds(510,230,290,105);
        srclblSachMuon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pnThongTinPhieuMuon.add(lblTitleCTPhieuMuon);
        pnThongTinPhieuMuon.add(lblMaSach);
        pnThongTinPhieuMuon.add(txtMaSach);
        pnThongTinPhieuMuon.add(lblTenSach);
        pnThongTinPhieuMuon.add(txtTenSach);
        pnThongTinPhieuMuon.add(lblThanhTien);
        pnThongTinPhieuMuon.add(txtThanhTien);
        pnThongTinPhieuMuon.add(btnThemSach);
        pnThongTinPhieuMuon.add(btnXoaSach);
        pnThongTinPhieuMuon.add(srclblSachMuon,BorderLayout.CENTER);

        pnTablePhieuMuon.add(pnThongTinPhieuMuon);

        //=================BUTTON===============


        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnInthe = new JButton("In thẻ");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnDocGia = new JButton("...");
        btnSach = new JButton("...");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnDocGia.setFont(fontButton);
        btnSach.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnInthe.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnInthe.setBounds(55,350,110,40);
        btnThem.setBounds(170,350,110,40);
        btnXoa.setBounds(285,350,110,40);
        btnTim.setBounds(400,350,110,40);
        btnXuatExcel.setBounds(515,350,110,40);
        btnNhapExcel.setBounds(630,350,110,40);
        btnDocGia.setBounds(430,100,30,25);
        btnSach.setBounds(760,50,30,25);

        pnThongTinPhieuMuon.add(btnInthe);
        pnThongTinPhieuMuon.add(btnThem);
        pnThongTinPhieuMuon.add(btnXoa);
        pnThongTinPhieuMuon.add(btnTim);
        pnThongTinPhieuMuon.add(btnXuatExcel);
        pnThongTinPhieuMuon.add(btnNhapExcel);
        pnThongTinPhieuMuon.add(btnDocGia);
        pnThongTinPhieuMuon.add(btnSach);

        pnTablePhieuMuon.add(pnThongTinPhieuMuon);

        //====================Bảng phiếu mượn====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("Mã");
        dtmPhieuMuon.addColumn("Đọc giả");
        dtmPhieuMuon.addColumn("Nhân viên");
        dtmPhieuMuon.addColumn("Ngày mượn");
        dtmPhieuMuon.addColumn("Hạn trả");
        dtmPhieuMuon.addColumn("Tổng tiền");
        tblPhieuMuon = new MyTable(dtmPhieuMuon);

        tblPhieuMuon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuMuon = tblPhieuMuon.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(70);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(150);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(150);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(130);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(130);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(130);

        JScrollPane scrTblPhieuMuon = new JScrollPane(tblPhieuMuon);
        scrTblPhieuMuon.setPreferredSize(new Dimension(900,150));

        scrTblPhieuMuon.setBounds(0,410,820,195);
        //</editor-fold>
        pnThongTinPhieuMuon.add(scrTblPhieuMuon, BorderLayout.CENTER);

        loadDataLenBangPhieuMuon();

        pnTablePhieuMuon.add(pnThongTinPhieuMuon);

        //=======================================================
        this.add(pnTablePhieuMuon);
    }

    private void addEventsPhieuMuon(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangPhieuMuon();
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                loadDataLenBangCTPhieuMuon("0");
            }
        });
        tblPhieuMuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuMuon();
                loadDataLenBangCTPhieuMuon(txtMaPhieuMuon.getText());
            }
        });
        tblSachMuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblCTPhieuMuon();
            }
        });
        btnInthe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatPhieuMuon();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemPhieuMuon();
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                loadDataLenBangCTPhieuMuon("0");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaPhieuMuon();
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                loadDataLenBangCTPhieuMuon("0");
            }
        });
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
        txtTimKiem.addActionListener(new ActionListener() {
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
        btnDocGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimDocGia();
                xuLyThemNgayThang();
            }
        });
        btnSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimSach();
            }
        });
        btnThemSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraPhieuMuon(txtMaPhieuMuon.getText())) {
                    xuLyThemCTPhieuMuon();
                }

            }
        });
        btnXoaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraPhieuMuon(txtMaPhieuMuon.getText())) {
                    xuLyXoaCTPhieuMuon();
                }
            }
        });
    }

    private void xuLyNhapFileExcel() {
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblPhieuMuon);

        int row = tblPhieuMuon.getRowCount();
        for (int i = 0; i < row; i++) {
            String docGia = tblPhieuMuon.getValueAt(i, 1) + "";
            String nhanVien = tblPhieuMuon.getValueAt(i, 2) + "";
            String ngayMuon = tblPhieuMuon.getValueAt(i, 3) + "";
            String ngayTra = tblPhieuMuon.getValueAt(i, 4) + "";
            String tongTien = tblPhieuMuon.getValueAt(i, 5) + "";

            pmBUS.nhapPhieuMuonTuExcel(docGia,nhanVien,ngayMuon,ngayTra,tongTien);
        }
    }

    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblPhieuMuon);
    }

    private void xuLyClickTblPhieuMuon(){
        int row = tblPhieuMuon.getSelectedRow();
        if(row > -1){
            String maPhieuMuon =tblPhieuMuon.getValueAt(row,0)+"";
            String docGia =tblPhieuMuon.getValueAt(row,1)+"";
            String nhanVien =tblPhieuMuon.getValueAt(row,2)+"";
            String ngayMuon =tblPhieuMuon.getValueAt(row,3)+"";
            String ngayTra =tblPhieuMuon.getValueAt(row,4)+"";
            String tongTien =tblPhieuMuon.getValueAt(row,5)+"";

            txtMaPhieuMuon.setText(maPhieuMuon);
            txtDocGia.setText(docGia);
            txtNgayMuon.setText(ngayMuon);
            txtNgayTra.setText(ngayTra);
            txtTongTien.setText(tongTien.replace(",",""));
            loadDataLenBangCTPhieuMuon(txtMaPhieuMuon.getText());
        }
    }

    private void loadDataLenBangPhieuMuon(){
        pmBUS.docListPhieuMuon();
        dtmPhieuMuon.setRowCount(0);

        ArrayList<PhieuMuon> dspm = pmBUS.getListPhieuMuon();

        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (PhieuMuon pm : dspm) {
            Vector vec = new Vector();
            vec.add(pm.getMaPhieuMuon());
            String tenDG = docGiaBUS.getTenDocGia(pm.getMaDocGia());
            vec.add(tenDG);
            String tenNV = nhanVienBUS.getTenNhanVien(pm.getMaNhanVien());
            vec.add(tenNV);
            vec.add(sdf.format(pm.getNgayMuon()));
            vec.add(sdf.format(pm.getNgayTra()));
            vec.add(dcf.format(pm.getTongTien()));
            dtmPhieuMuon.addRow(vec);
        }
    }

    private void xuLyClickTblCTPhieuMuon(){
        int row = tblSachMuon.getSelectedRow();
        if(row > -1){
            String maSach =tblSachMuon.getValueAt(row,0)+"";
            String tenSach =tblSachMuon.getValueAt(row,1)+"";
            String tongTien =tblSachMuon.getValueAt(row,2)+"";

            txtMaSach.setText(maSach);
            txtTenSach.setText(tenSach);
            txtThanhTien.setText(tongTien.replace(",",""));
        }
    }

    private void loadDataLenBangCTPhieuMuon(String ma){
        ArrayList<CTPhieuMuon> listCTPhieuMuon = ctPhieuMuonBUS.getListCTPhieuMuonTheoMaPM(ma);
        ctPhieuMuonBUS.docListCTPhieuMuon();
        dtmSachMuon.setRowCount(0);

        DecimalFormat dcf = new DecimalFormat("###,###");

        for (CTPhieuMuon ctpm : listCTPhieuMuon) {
            Vector vec = new Vector();
            vec.add(ctpm.getMaSach());
            String tenSach = sachBUS.getTenSachMuon(ctpm.getMaSach());
            vec.add(tenSach);
            vec.add(dcf.format(ctpm.getGiaTien()));
            dtmSachMuon.addRow(vec);
        }
    }

    private void xuLyThemPhieuMuon(){
        boolean flag = pmBUS.themPhieuMuon(txtMaPhieuMuon.getText(),
                txtDocGia.getText(),
                txtNgayMuon.getText(),
                txtNgayTra.getText(),
                txtTongTien.getText());
        pmBUS.docListPhieuMuon();
        if(flag) {
            xuLyThemCTPhieuMuon(txtMaPhieuMuon.getText());
            loadDataLenBangPhieuMuon();
            loadDataLenBangCTPhieuMuon("0");
        }
    }

    private void xuLyXoaPhieuMuon(){
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flags = xuLyXoaCTPhieuMuon(txtMaPhieuMuon.getText());
            boolean flag = pmBUS.xoaPhieuMuon(txtMaPhieuMuon.getText());
            loadDataLenBangPhieuMuon();
            timSachGUI.loadDataLenTable();
        }
    }

    private void xuLyTimKiem(){
        String docGia = txtTimKiem.getText();
        int maDocGia = docGiaBUS.getMaDocGia(docGia);
        dtmPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = null;
        dspm = pmBUS.getPhieuMuonTheoMaDocGia(maDocGia);
        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (PhieuMuon pm : dspm) {
            Vector vec = new Vector();
            vec.add(pm.getMaPhieuMuon());
            vec.add(docGiaBUS.getTenDocGia(maDocGia));
            vec.add(nhanVienBUS.getTenNhanVien(pm.getMaNhanVien()));
            vec.add(sdf.format(pm.getNgayMuon()));
            vec.add(sdf.format(pm.getNgayTra()));
            vec.add(dcf.format(pm.getTongTien()));
            dtmPhieuMuon.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspm.size(), MyDialog.INFO_DIALOG);
    }

    private void xuLyTimDocGia(){
        timDocGiaGUI.setVisible(true);
        if (timDocGiaGUI.docGiaTimDuoc != null) {
            txtDocGia.setText(timDocGiaGUI.docGiaTimDuoc.getHo() + " " + timDocGiaGUI.docGiaTimDuoc.getTen());
        }
    }

    private void xuLyTimSach(){
        timSachGUI.setVisible(true);
        if(timSachGUI.sachTimDuoc != null){
            txtMaSach.setText(timSachGUI.sachTimDuoc.getMaSach()+"");
            txtTenSach.setText(timSachGUI.sachTimDuoc.getTenSach());
            long tien = (timSachGUI.sachTimDuoc.getGiaSach() * 10)/100;
            DecimalFormat formatter = new DecimalFormat("###,###");
            String tienMuon = formatter.format(tien);
            txtThanhTien.setText(tienMuon);
        }
    }

    private void xuLyThemNgayThang(){
        if (txtDocGia.getText() != "") {
            int ma = pmBUS.getMaPhieumuonMoiNhat() + 1;
            txtMaPhieuMuon.setText(String.valueOf(ma));
            // Lấy ngày hiện tại
            Date ngayHienTai = pmBUS.timeHienTai();

            // Hiển thị ngày mượn
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtNgayMuon.setText(sdf.format(ngayHienTai));

            // Tính toán và hiển thị ngày trả (ngày hiện tại + 20 ngày)
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngayHienTai);
            cal.add(Calendar.DATE, 20);
            Date ngayTra = cal.getTime();
            txtNgayTra.setText(sdf.format(ngayTra));
        }
    }

    private void btnThemSachAction(String maSach, String tenSach, String thanhTien) {
        Vector<Object> rowData = new Vector<>();
        rowData.add(maSach);
        rowData.add(tenSach);
        rowData.add(thanhTien);
        dtmSachMuon.addRow(rowData);
    }

    private void xuLyThemCTPhieuMuon() {
        if (txtDocGia.getText().equals("")){
            new MyDialog("Chưa chọn đọc giả mượn sách!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtMaSach.getText().equals("")){
            new MyDialog("Chưa chọn sách mượn!!!",MyDialog.ERROR_DIALOG);
            return;
        }else {
            int rowCount = dtmSachMuon.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSach = String.valueOf(dtmSachMuon.getValueAt(i, 0));
                String tenSach = String.valueOf(dtmSachMuon.getValueAt(i, 1));
                String thanhTien = String.valueOf(dtmSachMuon.getValueAt(i, 2));
                timSachGUI.dtmSach.removeRow(timSachGUI.hang);
                if(maSach.trim().equals(txtMaSach.getText())){
                    new MyDialog("Sách đã được thêm vào phiếu mượn!!!",MyDialog.ERROR_DIALOG);
                    return;
                }
            }

            String maSach = txtMaSach.getText();
            String tenSach = txtTenSach.getText();
            String thanhTien = txtThanhTien.getText();

            btnThemSachAction(maSach, tenSach, thanhTien);

            ctPhieuMuonBUS.chonSachMuon(maSach);

            // Xóa dữ liệu trong các trường nhập liệu
            txtMaSach.setText("");
            txtTenSach.setText("");
            txtThanhTien.setText("");

            tinhTien(dtmSachMuon);
        }
    }

    private void xuLyThemCTPhieuMuon(String ma) {
        int rowCount = dtmSachMuon.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String maSach = (String) dtmSachMuon.getValueAt(i, 0);
            String tenSach = (String) dtmSachMuon.getValueAt(i, 1);
            String thanhTien = (String) dtmSachMuon.getValueAt(i, 2);

            // Gọi phương thức để xử lý chi tiết phiếu mượn với từng hàng dữ liệu từ bảng
            boolean flag = ctPhieuMuonBUS.themCTPhieuMuon(ma, maSach, thanhTien);
        }
    }

    private void xuLyXoaCTPhieuMuon() {
        if (txtMaSach.getText().equals("")) {
            new MyDialog("Chưa chọn sách mượn để xóa!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        else {
            int selectedRow = tblSachMuon.getSelectedRow();
            if (selectedRow >= 0) {
                String maSach = (String) dtmSachMuon.getValueAt(selectedRow, 0);

                dtmSachMuon.removeRow(selectedRow);
                sachDAO.capNhatTrangThaiSach(txtMaSach.getText());
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
            }
        }
        timSachGUI.loadDataLenTable();
    }

    private boolean kiemTraPhieuMuon(String ma){
        int rowCount = dtmPhieuMuon.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int maPhieuMuon = (int) dtmPhieuMuon.getValueAt(i, 0);
            String maPM = String.valueOf(maPhieuMuon);
            String docGia = (String) dtmPhieuMuon.getValueAt(i, 1);
            String ngayMuon = (String) dtmPhieuMuon.getValueAt(i, 2);
            String ngayTra = (String) dtmPhieuMuon.getValueAt(i,3);
            String tongTien = (String) dtmPhieuMuon.getValueAt(i,4);
            if(ma.trim().equals(maPM)){
                new MyDialog("Phiếu mượn đã tồn tại!!!",MyDialog.ERROR_DIALOG);
                return false;
            }
        }
        return true;
    }

    private boolean xuLyXoaCTPhieuMuon(String ma) {
        // Gọi phương thức để xử lý chi tiết phiếu mượn với từng hàng dữ liệu từ bảng
        boolean flag = ctPhieuMuonBUS.xoaCTPhieuMuon(ma);
        int rowCount = dtmSachMuon.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int maSach = (int) dtmSachMuon.getValueAt(i, 0);
            String maS = String.valueOf(maSach);
            sachDAO.capNhatTrangThaiSach(maS);
        }
        return flag;
    }

    private void tinhTien(DefaultTableModel dtmSachMuon){
        int rowCount = dtmSachMuon.getRowCount();
        long tien = 0;
        for (int i = 0; i < rowCount; i++) {
            String maSach = (String) dtmSachMuon.getValueAt(i, 0);
            String tenSach = (String) dtmSachMuon.getValueAt(i, 1);
            String thanhTien = (String) dtmSachMuon.getValueAt(i, 2);
            tien = tien + Long.parseLong(thanhTien.replace(",",""));
        }
        txtTongTien.setText(String.valueOf(tien));
    }


    public void xuLyXuatPhieuMuon(){
        ArrayList<Vector> dsPhieuMuon = new ArrayList<>();
        int row = tblSachMuon.getRowCount();
        int row_pm = tblPhieuMuon.getRowCount();
        int count = 0;
        if(row == 0) return;
        if(txtMaPhieuMuon.getText().equals("")){
            new MyDialog("Chưa chọn phiếu mượn để in phiếu!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        for(int i=0;i<row_pm;i++){
            if(txtMaPhieuMuon.getText().equals(tblPhieuMuon.getValueAt(i,0))){
                count += 1;
            }
        }
        if(count != 0){
            new MyDialog("Phiếu mượn chưa được tạo!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        for(int i=0;i<row;i++){
            Vector vec = new Vector();
            vec.add(tblSachMuon.getValueAt(i,0));
            vec.add(tblSachMuon.getValueAt(i,1));
            vec.add(tblSachMuon.getValueAt(i,2));
            dsPhieuMuon.add(vec);
        }
        int maPM = Integer.parseInt(txtMaPhieuMuon.getText());
        long tien = Long.parseLong(txtTongTien.getText());
        XuatPhieuMuonGUI phieuMuonGUI = new XuatPhieuMuonGUI(dsPhieuMuon,maPM,
                txtDocGia.getText(),nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()),txtNgayMuon.getText(),txtNgayTra.getText(),tien);
        phieuMuonGUI.setVisible(true);
    }

    public void xuLyThoat(){
        int rowCount = tblSachMuon.getRowCount();
        int row = tblPhieuMuon.getRowCount();
        int count = 0;
        for(int j =0; j<row;j++){
            String maPM = String.valueOf(dtmPhieuMuon.getValueAt(j, 0));
            if(txtMaPhieuMuon.getText().equals(maPM)){
                count = 1;
            }
        }
        if(count != 1) {
            for (int i = 0; i < rowCount; i++) {
                String maSach = (String) dtmSachMuon.getValueAt(i, 0);
                sachDAO.capNhatTrangThaiSach(maSach);
            }
        }else return;
    }
}

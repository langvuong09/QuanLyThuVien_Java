package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.CTPhieuMuonBUS;
import QuanLyThuVien.BUS.PhieuMuonBUS;
//import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DTO.Sach;

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

    public PnQuanLyPhieuMuonGUI(){
        changLNF("Windows");
        addConTrolsPhieuMuon();
        addEventsPhieuMuon();
    }

    PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblPhieuMuon, tblSachMuon;
    CardLayout cardPhieuMuonGroup =new CardLayout();
    JPanel pnCardTabPhieuMuon;
    DefaultTableModel dtmPhieuMuon, dtmSachMuon;
    JTextField txtMaPhieuMuon,txtDocGia, txtNgayMuon, txtNgayTra, txtTongTien, txtTimKiem, txtMaSach, txtTenSach, txtThanhTien;
    JButton btnThem, btnXoa, btnSua, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnDocGia, btnSach;
    JLabel lblTabbedPhieuMuon, lblTabbedLichSu;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/Manager-GUI/tabbed-btn.png");

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
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new TransparentPanel();
        Font fontSize = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedPhieuMuon = new JLabel("Phiếu mượn");
        lblTabbedPhieuMuon.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedPhieuMuon.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedPhieuMuon.setIcon(tabbedSelected);
        lblTabbedPhieuMuon.setBounds(2, 2, 140, 37);
        lblTabbedPhieuMuon.setFont(font);
        lblTabbedPhieuMuon.setForeground(Color.white);
        lblTabbedPhieuMuon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedLichSu = new JLabel("Lịch sử");
        lblTabbedLichSu.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedLichSu.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedLichSu.setIcon(tabbedDefault);
        lblTabbedLichSu.setBounds(143, 2, 140, 37);
        lblTabbedLichSu.setFont(font);
        lblTabbedLichSu.setForeground(Color.white);
        lblTabbedLichSu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedPhieuMuon);
        pnTop.add(lblTabbedLichSu);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

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

        JLabel lblNgayTra = new JLabel("Ngày trả:");
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
        txtMaSach.setBounds(620,50,130,25);

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        txtTenSach.setEditable(false);
        lblTenSach.setBounds(510,100,120,25);
        txtTenSach.setBounds(620,100,170,25);

        JLabel lblThanhTien = new JLabel("Tiền mượn:");
        lblThanhTien.setFont(font);
        txtThanhTien.setFont(font);
        txtThanhTien.setEditable(false);
        lblThanhTien.setBounds(510,150,120,25);
        txtThanhTien.setBounds(620,150,170,25);

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
        columnModelSachMuon.getColumn(0).setPreferredWidth(30);
        columnModelSachMuon.getColumn(1).setPreferredWidth(110);
        columnModelSachMuon.getColumn(2).setPreferredWidth(80);

        JScrollPane srclblSachMuon = new JScrollPane(tblSachMuon);
        srclblSachMuon.setPreferredSize(new Dimension(200,70));
        srclblSachMuon.setBounds(510,230,290,100);

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
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnInthe = new JButton("In thẻ");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnDocGia = new JButton("...");
        btnSach = new JButton("...");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnDocGia.setFont(fontButton);
        btnSach.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnInthe.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnInthe.setBounds(5,350,110,40);
        btnThem.setBounds(120,350,110,40);
        btnSua.setBounds(235,350,110,40);
        btnXoa.setBounds(350,350,110,40);
        btnTim.setBounds(465,350,110,40);
        btnXuatExcel.setBounds(580,350,105,40);
        btnNhapExcel.setBounds(690,350,105,40);
        btnDocGia.setBounds(430,100,30,25);
        btnSach.setBounds(760,50,30,25);

        pnThongTinPhieuMuon.add(btnInthe);
        pnThongTinPhieuMuon.add(btnThem);
        pnThongTinPhieuMuon.add(btnSua);
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
        dtmPhieuMuon.addColumn("Ngày trả");
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

        scrTblPhieuMuon.setBounds(0,410,820,155);
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
            }
        });
        tblPhieuMuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuMuon();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemPhieuMuon();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaPhieuMuon();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaPhieuMuon();
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
            String maPM = tblPhieuMuon.getValueAt(i, 1) + "";
            String maDG = tblPhieuMuon.getValueAt(i, 2) + "";
            String maNV = tblPhieuMuon.getValueAt(i, 3) + "";
            String ngayMuon = tblPhieuMuon.getValueAt(i, 4) + "";
            String ngayTra = tblPhieuMuon.getValueAt(i, 5) + "";
            String tongTien = tblPhieuMuon.getValueAt(i, 6) + "";

            pmBUS.nhapPhieuMuonTuExcel(maPM,maDG,maNV,ngayMuon,ngayTra,tongTien);
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
            String maDocGia =tblPhieuMuon.getValueAt(row,1)+"";
            String maNhanVien =tblPhieuMuon.getValueAt(row,2)+"";
            String ngayMuon =tblPhieuMuon.getValueAt(row,3)+"";
            String ngayTra =tblPhieuMuon.getValueAt(row,4)+"";
            String tongTien =tblPhieuMuon.getValueAt(row,5)+"";

            txtMaPhieuMuon.setText(maPhieuMuon);
            txtDocGia.setText(maDocGia);
            txtNgayMuon.setText(ngayMuon);
            txtNgayTra.setText(ngayTra);
            txtTongTien.setText(tongTien.replace(",",""));
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
            vec.add(pm.getMaDocGia());
            vec.add(pm.getMaNhanVien());
            vec.add(sdf.format(pm.getNgayMuon()));
            vec.add(sdf.format(pm.getNgayTra()));
            vec.add(dcf.format(pm.getTongTien()));
            dtmPhieuMuon.addRow(vec);
        }
    }

    private void xuLyThemPhieuMuon(){
        boolean flag = pmBUS.themPhieuMuon(txtDocGia.getText(),
                txtNgayMuon.getText(),
                txtNgayTra.getText(),
                txtTongTien.getText());
        pmBUS.docListPhieuMuon();
        loadDataLenBangPhieuMuon();
    }

    private void xuLySuaPhieuMuon(){
        boolean flag = pmBUS.suaPhieuMuon(txtMaPhieuMuon.getText(),
                txtDocGia.getText(),
                txtNgayMuon.getText(),
                txtNgayTra.getText(),
                txtTongTien.getText());
        pmBUS.docListPhieuMuon();
        loadDataLenBangPhieuMuon();
    }

    private void xuLyXoaPhieuMuon(){
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            boolean flag = pmBUS.xoaPhieuMuon(txtMaPhieuMuon.getText());
            if (flag) {
                loadDataLenBangPhieuMuon();
            }
        }
    }

    private void xuLyTimKiem(){
        String maDG = txtTimKiem.getText().toLowerCase();
        int maDocGia = Integer.parseInt(maDG);
        dtmPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = null;
        dspm = pmBUS.getPhieuMuonTheoMaDocGia(maDocGia);
        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (PhieuMuon pm : dspm) {
            Vector vec = new Vector();
            vec.add(pm.getMaPhieuMuon());
            vec.add(pm.getMaDocGia());
            vec.add(pm.getMaNhanVien());
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
            txtDocGia.setText(timDocGiaGUI.docGiaTimDuoc.getMaDocGia() + " - " + timDocGiaGUI.docGiaTimDuoc.getHo() + " " + timDocGiaGUI.docGiaTimDuoc.getTen());
        }
    }
}

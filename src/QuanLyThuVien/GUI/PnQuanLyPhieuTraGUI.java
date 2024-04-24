package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DAO.SachDAO;
import QuanLyThuVien.DTO.*;

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
import java.util.*;

import java.awt.*;
import java.awt.event.*;import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLyPhieuTraGUI extends JPanel{

    private DlgTimDocGia timDocGiaGUI = new DlgTimDocGia();
    private SachBUS sachBUS = new SachBUS();
    private SachDAO sachDAO = new SachDAO();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private PhieuMuonBUS pmBUS = new PhieuMuonBUS();

    public PnQuanLyPhieuTraGUI(){
        changLNF("Windows");
        addConTrolsPhieuTra();
//        addEventsPhieuTra();
    }

    PhieuTraBUS ptBUS = new PhieuTraBUS();
    CTPhieuTraBUS ctPhieuTraBUS = new CTPhieuTraBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblPhieuTra, tblSachTra;
    DefaultTableModel dtmPhieuTra, dtmSachTra;
    JTextField txtMaPhieuTra,txtMaPhieuMuon ,txtDocGia, txtNgayMuon, txtNgayTraThuc, txtTimKiem, txtMaSach, txtTenSach;
    JButton btnThem, btnXoa, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnDocGia, btnSach;

    private void addConTrolsPhieuTra() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL Phiếu Trả
        =========================================================================
         */
        JPanel pnTablePhieuTra = new TransparentPanel();
        pnTablePhieuTra.setLayout(new BorderLayout());

        JPanel pnTitlePhieuTra = new TransparentPanel();
        JLabel lblTitlePhieuTra = new JLabel("Quản lý phiếu trả");
        lblTitlePhieuTra.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitlePhieuTra.add(lblTitlePhieuTra);
        pnTitlePhieuTra.add(btnReset);
        pnTablePhieuTra.add(pnTitlePhieuTra, BorderLayout.NORTH);

        //=================PANEL INPUT===========
        int x = 15, y = 15;
        txtMaPhieuTra = new JTextField(x);
        txtMaPhieuMuon = new JTextField(x);
        txtDocGia = new JTextField(x);
        txtNgayMuon = new JTextField(x);
        txtNgayTraThuc = new JTextField(x);
        txtMaSach = new JTextField(y);
        txtTenSach = new JTextField(y);
        txtTimKiem = new JTextField(y);


        //=================Thông tin phiếu trả==============

        JPanel pnThongTinPhieuTra = new TransparentPanel();
        pnThongTinPhieuTra.setLayout(null);

        JLabel lblMaPhieuTra = new JLabel("Mã phiếu trả:");
        lblMaPhieuTra.setFont(font);
        txtMaPhieuTra.setFont(font);
        txtMaPhieuTra.setEditable(false);
        lblMaPhieuTra.setBounds(20, 50, 150, 25);
        txtMaPhieuTra.setBounds(200, 50, 220, 25);

        JLabel lblMaPhieuMuon = new JLabel("Mã phiếu mượn:");
        lblMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setEditable(false);
        lblMaPhieuMuon.setBounds(20, 100, 150, 25);
        txtMaPhieuMuon.setBounds(200, 100, 220, 25);

        JLabel lblDocGia = new JLabel("Đọc giả:");
        lblDocGia.setFont(font);
        txtDocGia.setFont(font);
        txtDocGia.setEditable(false);
        lblDocGia.setBounds(20, 150, 150, 25);
        txtDocGia.setBounds(200, 150, 220, 25);

        JLabel lblNgayMuon = new JLabel("Ngày mượn:");
        lblNgayMuon.setFont(font);
        txtNgayMuon.setFont(font);
        txtNgayMuon.setEditable(false);
        lblNgayMuon.setBounds(20, 200, 150, 25);
        txtNgayMuon.setBounds(200, 200, 220, 25);

        JLabel lblNgayTraThuc = new JLabel("Ngày trả:");
        lblNgayTraThuc.setFont(font);
        txtNgayTraThuc.setFont(font);
        txtNgayTraThuc.setEditable(false);
        lblNgayTraThuc.setBounds(20, 250, 150, 25);
        txtNgayTraThuc.setBounds(200, 250, 220, 25);

        JLabel lblTimKiem = new JLabel("Tên đọc giả cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20, 300, 300, 25);
        txtTimKiem.setBounds(210, 300, 250, 25);

        pnThongTinPhieuTra.add(lblMaPhieuTra);
        pnThongTinPhieuTra.add(txtMaPhieuTra);
        pnThongTinPhieuTra.add(lblMaPhieuMuon);
        pnThongTinPhieuTra.add(txtMaPhieuMuon);
        pnThongTinPhieuTra.add(lblDocGia);
        pnThongTinPhieuTra.add(txtDocGia);
        pnThongTinPhieuTra.add(lblNgayMuon);
        pnThongTinPhieuTra.add(txtNgayMuon);
        pnThongTinPhieuTra.add(lblNgayTraThuc);
        pnThongTinPhieuTra.add(txtNgayTraThuc);
        pnThongTinPhieuTra.add(lblTimKiem);
        pnThongTinPhieuTra.add(txtTimKiem);

        //=================Chi tiết phiếu mượn==========

        JLabel lblTitleCTPhieuTra = new JLabel("Chi tiết phiếu trả");
        lblTitleCTPhieuTra.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitleCTPhieuTra.setBounds(550, 0, 300, 25);

        JLabel lblMaSach = new JLabel("Mã sách:");
        lblMaSach.setFont(font);
        txtMaSach.setFont(font);
        txtMaSach.setEditable(false);
        lblMaSach.setBounds(510, 50, 120, 25);
        txtMaSach.setBounds(600, 50, 150, 25);

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        txtTenSach.setEditable(false);
        lblTenSach.setBounds(510, 100, 120, 25);
        txtTenSach.setBounds(600, 100, 190, 25);

        Font fontB = new Font("Tahoma", Font.PLAIN, 16);
        btnThemSach = new JButton("Thêm");
        btnThemSach.setFont(fontB);
        btnThemSach.setBounds(540, 150, 100, 30);

        btnXoaSach = new JButton("Xóa");
        btnXoaSach.setFont(fontB);
        btnXoaSach.setBounds(660, 150, 100, 30);

        dtmSachTra = new DefaultTableModel();
        dtmSachTra.addColumn("Mã");
        dtmSachTra.addColumn("Tên sách");
        tblSachTra = new MyTable(dtmSachTra);

        tblSachTra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSachTra = tblSachTra.getColumnModel();
        columnModelSachTra.getColumn(0).setPreferredWidth(30);
        columnModelSachTra.getColumn(1).setPreferredWidth(190);

        JScrollPane srclblSachTra = new JScrollPane(tblSachTra);
        srclblSachTra.setPreferredSize(new Dimension(200, 105));
        srclblSachTra.setBounds(510, 200, 290, 135);
        srclblSachTra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pnThongTinPhieuTra.add(lblTitleCTPhieuTra);
        pnThongTinPhieuTra.add(lblMaSach);
        pnThongTinPhieuTra.add(txtMaSach);
        pnThongTinPhieuTra.add(lblTenSach);
        pnThongTinPhieuTra.add(txtTenSach);
        pnThongTinPhieuTra.add(btnThemSach);
        pnThongTinPhieuTra.add(btnXoaSach);
        pnThongTinPhieuTra.add(srclblSachTra, BorderLayout.CENTER);

        pnTablePhieuTra.add(pnThongTinPhieuTra);

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

        btnInthe.setBounds(55, 350, 110, 40);
        btnThem.setBounds(170, 350, 110, 40);
        btnXoa.setBounds(285, 350, 110, 40);
        btnTim.setBounds(400, 350, 110, 40);
        btnXuatExcel.setBounds(515, 350, 110, 40);
        btnNhapExcel.setBounds(630, 350, 110, 40);
        btnDocGia.setBounds(430, 100, 30, 25);
        btnSach.setBounds(760, 50, 30, 25);

        pnThongTinPhieuTra.add(btnInthe);
        pnThongTinPhieuTra.add(btnThem);
        pnThongTinPhieuTra.add(btnXoa);
        pnThongTinPhieuTra.add(btnTim);
        pnThongTinPhieuTra.add(btnXuatExcel);
        pnThongTinPhieuTra.add(btnNhapExcel);
        pnThongTinPhieuTra.add(btnDocGia);
        pnThongTinPhieuTra.add(btnSach);

        pnTablePhieuTra.add(pnThongTinPhieuTra);

        //====================Bảng phiếu mượn====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmPhieuTra = new DefaultTableModel();
        dtmPhieuTra.addColumn("Mã PT");
        dtmPhieuTra.addColumn("Mã PM");
        dtmPhieuTra.addColumn("Đọc giả");
        dtmPhieuTra.addColumn("Nhân viên");
        dtmPhieuTra.addColumn("Ngày mượn");
        dtmPhieuTra.addColumn("Ngày trả");

        tblPhieuTra = new MyTable(dtmPhieuTra);

        tblPhieuTra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuTra = tblPhieuTra.getColumnModel();
        columnModelPhieuTra.getColumn(0).setPreferredWidth(70);
        columnModelPhieuTra.getColumn(1).setPreferredWidth(70);
        columnModelPhieuTra.getColumn(2).setPreferredWidth(170);
        columnModelPhieuTra.getColumn(3).setPreferredWidth(170);
        columnModelPhieuTra.getColumn(4).setPreferredWidth(140);
        columnModelPhieuTra.getColumn(5).setPreferredWidth(140);

        JScrollPane scrTblPhieuTra = new JScrollPane(tblPhieuTra);
        scrTblPhieuTra.setPreferredSize(new Dimension(900, 150));

        scrTblPhieuTra.setBounds(0, 410, 820, 195);
        //</editor-fold>
        pnThongTinPhieuTra.add(scrTblPhieuTra, BorderLayout.CENTER);

        loadDataLenBangPhieuTra();

        pnTablePhieuTra.add(pnThongTinPhieuTra);

        //=======================================================
        this.add(pnTablePhieuTra);
    }

    public void addEventsPhieuTra(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangPhieuTra();
                txtMaPhieuTra.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTraThuc.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                loadDataLenBangCTPhieuTra("0");
            }
        });
        tblPhieuTra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuTra();
                loadDataLenBangCTPhieuTra(txtMaPhieuTra.getText());
            }
        });
        tblSachTra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblCTPhieuTra();
            }
        });
        btnInthe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatPhieuTra();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemPhieuTra();
                txtMaPhieuTra.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTraThuc.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                loadDataLenBangCTPhieuTra("0");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaPhieuTra();
                txtMaPhieuTra.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTraThuc.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                loadDataLenBangCTPhieuTra("0");
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
                xuLyThemNgayThang();
            }
        });
        btnSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThemSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraPhieuTra(txtMaPhieuTra.getText())) {
                    xuLyThemCTPhieuTra();
                }

            }
        });
        btnXoaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraPhieuTra(txtMaPhieuTra.getText())) {
                    xuLyXoaCTPhieuTra();
                }
            }
        });
    }

    private void loadDataLenBangPhieuTra() {
        ptBUS.docListPhieuTra();
        dtmPhieuTra.setRowCount(0);

        ArrayList<PhieuTra> dspt = ptBUS.getListPhieuTra();

        DecimalFormat dcf = new DecimalFormat("###,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (PhieuTra pt : dspt) {
            Vector vec = new Vector();
            vec.add(pt.getMaPhieuTra());
            vec.add(pt.getMaPhieuMuon());
            String tenDG = docGiaBUS.getTenDocGia(pt.getMaDocGia());
            vec.add(tenDG);
            String tenNV = nhanVienBUS.getTenNhanVien(pt.getMaNhanVien());
            vec.add(tenNV);
            String maPM = String.valueOf(pt.getMaPhieuMuon());
            vec.add(sdf.format(pmBUS.getPhieuMuon(maPM).getNgayMuon()));
            vec.add(sdf.format(pt.getNgayTraThuc()));
            dtmPhieuTra.addRow(vec);
        }
    }

    private void loadDataLenBangCTPhieuTra(String maPT){
        ArrayList<CTPhieuTra> listCTPhieuTra = ctPhieuTraBUS.getListCTPhieuTraTheoMaPT(maPT);
        ctPhieuTraBUS.docListCTPhieuTra();
        dtmSachTra.setColumnCount(0);

        for(CTPhieuTra ctpt : listCTPhieuTra){
            Vector vec = new Vector<>();
            vec.add(ctpt.getMaSach());
            vec.add(sachBUS.getTenSach(ctpt.getMaSach()));
            dtmSachTra.addRow(vec);
        }
    }

    private void xuLyClickTblPhieuTra(){
        int row = tblPhieuTra.getSelectedRow();
        if(row > -1){
            String maPhieuTra = tblPhieuTra.getValueAt(row,0)+"";
            String maPhieuMuon = tblPhieuTra.getValueAt(row,1)+"";
            String docGia = tblPhieuTra.getValueAt(row,2)+"";
            String nhanVien = tblPhieuTra.getValueAt(row,3)+"";
            String ngayMuon = tblPhieuTra.getValueAt(row,4)+"";
            String ngayTraThuc = tblPhieuTra.getValueAt(row, 5)+"";

            txtMaPhieuTra.setText(maPhieuTra);
            txtMaPhieuMuon.setText(maPhieuMuon);
            txtDocGia.setText(docGia);
            txtNgayMuon.setText(ngayMuon);
            txtNgayTraThuc.setText(ngayTraThuc);
            loadDataLenBangCTPhieuTra(txtMaPhieuTra.getText());
        }
    }

    private void xuLyClickTblCTPhieuTra(){
        int row = tblSachTra.getSelectedRow();
        if(row > -1){
            String maSach = tblSachTra.getValueAt(row, 0)+"";
            String tenSach = tblSachTra.getValueAt(row,1)+"";

            txtMaSach.setText(maSach);
            txtTenSach.setText(tenSach);
        }
    }

    private void xuLyThemPhieuTra(){
        boolean flag = ptBUS.themPhieuTra(txtMaPhieuTra.getText(),
                txtMaPhieuMuon.getText(),
                txtNgayMuon.getText(),
                txtNgayTraThuc.getText());
        ptBUS.docListPhieuTra();
        if(flag) {
            xuLyThemCTPhieuTra(txtMaPhieuTra.getText());
            loadDataLenBangPhieuTra();
            loadDataLenBangCTPhieuTra("0");
        }
    }

    private void xuLyThemCTPhieuTra(){
        if(txtMaSach.getText().equals("")){
            new MyDialog("Chưa chọn sách trả!!!", MyDialog.ERROR_DIALOG);
            return;
        }else {
            int row = dtmPhieuTra.getRowCount();
            for(int i=0;i < row; i++){
                String maSach = String.valueOf(dtmSachTra.getValueAt(i,0));
                String tenSach = String.valueOf(dtmSachTra.getValueAt(i,1));

                if(maSach.trim().equals("")){
                    new MyDialog("Sách đã được thêm vào phiếu trả!!!", MyDialog.ERROR_DIALOG);
                    return;
                }
            }

            String maSach = txtMaSach.getText();
            String tenSach = txtTenSach.getText();

            btnThemSachAction(maSach, tenSach);


            txtMaSach.setText("");
            txtTenSach.setText("");
        }
    }

    private void btnThemSachAction(String ma, String ten){
        Vector<Object> rowData = new Vector<>();
        rowData.add(ma);
        rowData.add(ten);
        dtmSachTra.addRow(rowData);
    }

    private void xuLyThemCTPhieuTra(String maPT){
        int row = dtmSachTra.getRowCount();
        for(int i=0;i<row;i++){
            String maSach = String.valueOf(dtmSachTra.getValueAt(i,0));
            String tenSach = String.valueOf(dtmSachTra.getValueAt(i,1));

            boolean flag = ctPhieuTraBUS.themCTPhieuTra(maPT, maSach);
        }
    }

    private void xuLyXoaPhieuTra(){
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if(dlg.OK_OPTION == dlg.getAction()){
            boolean flag = xuLyXoaCTPhieuTra(txtMaPhieuTra.getText());
            boolean flags = ptBUS.xoaPhieuTra(txtMaPhieuTra.getText());
            loadDataLenBangPhieuTra();
        }
    }

    private void xuLyXoaCTPhieuTra(){
        if(txtMaSach.getText().equals("")){
            new MyDialog("Chưa chọn sách trả để xóa!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        else {
            int selectedRow = tblSachTra.getSelectedRow();
            if(selectedRow >= 0){
                String maSach = String.valueOf(dtmSachTra.getValueAt(selectedRow,0));

                dtmSachTra.removeRow(selectedRow);
                txtMaSach.setText("");
                txtTenSach.setText("");
            }
        }
    }

    private boolean xuLyXoaCTPhieuTra(String ma){
        boolean flag = ctPhieuTraBUS.xoaCTPhieuTra(ma);
        int row = dtmSachTra.getRowCount();
        for(int i=0;i<row;i++){
            String maSach = String.valueOf(dtmSachTra.getValueAt(i,0));
            sachDAO.chonSach(maSach);
        }
        return flag;
    }

    private boolean kiemTraPhieuTra(String ma){
        int row = dtmPhieuTra.getRowCount();
        for(int i=0;i<row;i++){
            String maPhieuTra = String.valueOf(dtmPhieuTra.getValueAt(i,0));
            if(ma.trim().equals(maPhieuTra)){
                new MyDialog("Phiếu trả đã tồn tại!!!", MyDialog.ERROR_DIALOG);
                return false;
            }
        }
        return true;
    }

    private void xuLyThemNgayThang(){
        if(txtMaPhieuMuon.getText() != ""){
            int maPT = ptBUS.getMaPhieuTraMoiNhat()+1;
            txtMaPhieuTra.setText(String.valueOf(maPT));

            Date ngayHienTai = ptBUS.timeHienTai();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtNgayTraThuc.setText(sdf.format(ngayHienTai));
        }
    }

    private void xuLyTimKiem(){
        String docGia = txtDocGia.getText();
        String maDocGia = String.valueOf(docGiaBUS.getMaDocGia(docGia));
        ArrayList<PhieuTra> dspt  = null;
        dspt = ptBUS.getListPhieuTraTheoMaDocGia(maDocGia);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuTra pt : dspt){
            Vector vec = new Vector();
            vec.add(pt.getMaPhieuTra());
            vec.add(pt.getMaPhieuMuon());
            vec.add(docGiaBUS.getTenDocGia(pt.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pt.getMaNhanVien()));
            vec.add(pmBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon());
            vec.add(pt.getNgayTraThuc());
            dtmPhieuTra.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspt.size(), MyDialog.INFO_DIALOG);
    }

    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblPhieuTra);
    }

    private void xuLyNhapFileExcel(){
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if(dlg.getAction() != MyDialog.OK_OPTION){
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblPhieuTra);

        int row = tblPhieuTra.getRowCount();
        for(int i=0;i<row;i++){
            String maPhieuMuon = String.valueOf(tblPhieuTra.getValueAt(i,1));
            String docGia = String.valueOf(tblPhieuTra.getValueAt(i,2));
            String nhanVien = String.valueOf(tblPhieuTra.getValueAt(i,3));
            String ngayMuon = String.valueOf(tblPhieuTra.getValueAt(i,4));
            String ngayTraThuc = String.valueOf(tblPhieuTra.getValueAt(i,5));

            ptBUS.nhapPhieuTraExcel(maPhieuMuon,docGia,nhanVien,ngayTraThuc);
        }
    }

    private void xuLyXuatPhieuTra(){
        ArrayList<Vector> dspt = new ArrayList<>();
        int row = tblSachTra.getRowCount();
        int row_pt = tblPhieuTra.getRowCount();
        int count = 0;
        if(row == 0) return;
        if(txtMaPhieuTra.getText().equals("")){
            new MyDialog("Chưa chọn phiếu trả để in phiếu!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        for(int i=0;i<row_pt;i++){
            if(txtMaPhieuTra.getText().equals(tblPhieuTra.getValueAt(i,0))){
                count += 1;
            }
        }
        if(count != 0){
            new MyDialog("Phiếu trả chưa được tạo!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        for(int i=0;i<row;i++){
            Vector vec = new Vector();
            vec.add(tblSachTra.getValueAt(i,0));
            vec.add(tblSachTra.getValueAt(i,1));
            dspt.add(vec);
        }
        int maPT = Integer.parseInt(txtMaPhieuTra.getText());
    }
}
package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.*;

import static Main.Main.changLNF;

import MyCustom.XuLyFileExcel;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import com.toedter.calendar.JDateChooser;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnQuanLyPhieuTraGUI extends JPanel{

    private DlgTimPhieuMuon timPhieuMuonGUI = new DlgTimPhieuMuon();
    private DlgTimSachMuon timSachMuonGUI = new DlgTimSachMuon();
    private SachBUS sachBUS = new SachBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    private CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();

    public PnQuanLyPhieuTraGUI(){
        changLNF("Windows");
        addConTrolsPhieuTra();
        addEventsPhieuTra();
    }

    PhieuTraBUS ptBUS = new PhieuTraBUS();
    CTPhieuTraBUS ctPhieuTraBUS = new CTPhieuTraBUS();
    final Color colorPanel = new Color(247, 247, 247);
    ArrayList<CTPhieuTra> danhSachctpt = new ArrayList<>();
    MyTable tblPhieuTra, tblSachTra;
    DefaultTableModel dtmPhieuTra, dtmSachTra;
    JTextField txtMaPhieuTra,txtMaPhieuMuon ,txtDocGia, txtNgayMuon, txtNgayTraThuc, txtTimKiem;
    JDateChooser dateBD, dateKT;
    JButton btnThem, btnXoa, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnPhieuMuon, btnTimTrongKhoang;

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
        txtTimKiem = new JTextField(y);
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");


        //=================Thông tin phiếu trả==============

        JPanel pnThongTinPhieuTra = new TransparentPanel();
        pnThongTinPhieuTra.setLayout(null);

        JLabel lblMaPhieuTra = new JLabel("Mã phiếu trả:");
        lblMaPhieuTra.setFont(font);
        txtMaPhieuTra.setFont(font);
        txtMaPhieuTra.setEditable(false);
        lblMaPhieuTra.setBounds(20, 20, 150, 25);
        txtMaPhieuTra.setBounds(200, 20, 220, 25);

        JLabel lblMaPhieuMuon = new JLabel("Mã phiếu mượn:");
        lblMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setFont(font);
        txtMaPhieuMuon.setEditable(false);
        lblMaPhieuMuon.setBounds(20, 70, 150, 25);
        txtMaPhieuMuon.setBounds(200, 70, 220, 25);

        JLabel lblDocGia = new JLabel("Đọc giả:");
        lblDocGia.setFont(font);
        txtDocGia.setFont(font);
        txtDocGia.setEditable(false);
        lblDocGia.setBounds(20, 120, 150, 25);
        txtDocGia.setBounds(200, 120, 220, 25);

        JLabel lblNgayMuon = new JLabel("Ngày mượn:");
        lblNgayMuon.setFont(font);
        txtNgayMuon.setFont(font);
        txtNgayMuon.setEditable(false);
        lblNgayMuon.setBounds(20, 170, 150, 25);
        txtNgayMuon.setBounds(200, 170, 220, 25);

        JLabel lblNgayTraThuc = new JLabel("Ngày trả:");
        lblNgayTraThuc.setFont(font);
        txtNgayTraThuc.setFont(font);
        txtNgayTraThuc.setEditable(false);
        lblNgayTraThuc.setBounds(20, 220, 150, 25);
        txtNgayTraThuc.setBounds(200, 220, 220, 25);

        JLabel lblTimKiem = new JLabel("Đọc giả cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20, 270, 300, 25);
        txtTimKiem.setBounds(180, 270, 240, 25);

        JLabel lblTimKiemKhoang = new JLabel("Tìm kiếm ngày mượn: từ");
        JLabel lblKhoang = new JLabel("đến");
        lblTimKiemKhoang.setFont(font);
        lblKhoang.setFont(font);
        lblTimKiemKhoang.setBounds(20, 330, 300, 25);
        lblKhoang.setBounds(400, 330, 300, 25);

        dateBD.getCalendarButton().setIcon(new ImageIcon("image/icons8_calendar_25_20px.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());
        dateBD.setFont(font);
        dateKT.setFont(font);
        dateBD.setBounds(220,325,150,30);
        dateKT.setBounds(450,325,150,30);

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
        pnThongTinPhieuTra.add(lblTimKiemKhoang);
        pnThongTinPhieuTra.add(lblKhoang);
        pnThongTinPhieuTra.add(dateBD);
        pnThongTinPhieuTra.add(dateKT);

        //=================Chi tiết phiếu mượn==========

        JLabel lblTitleCTPhieuTra = new JLabel("Chi tiết phiếu trả");
        lblTitleCTPhieuTra.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitleCTPhieuTra.setBounds(550, 0, 300, 25);

        Font fontB = new Font("Tahoma", Font.PLAIN, 16);
        btnThemSach = new JButton("Trả");
        btnThemSach.setFont(fontB);
        btnThemSach.setBounds(540, 260, 100, 35);

        btnXoaSach = new JButton("Bỏ");
        btnXoaSach.setFont(fontB);
        btnXoaSach.setBounds(660, 260, 100, 35);

        dtmSachTra = new DefaultTableModel();
        dtmSachTra.addColumn("Mã");
        dtmSachTra.addColumn("Tên sách");
        dtmSachTra.addColumn("TT");
        tblSachTra = new MyTable(dtmSachTra);

        tblSachTra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSachTra = tblSachTra.getColumnModel();
        columnModelSachTra.getColumn(0).setPreferredWidth(30);
        columnModelSachTra.getColumn(1).setPreferredWidth(140);
        columnModelSachTra.getColumn(2).setPreferredWidth(50);

        JScrollPane srclblSachTra = new JScrollPane(tblSachTra);
        srclblSachTra.setPreferredSize(new Dimension(200, 200));
        srclblSachTra.setBounds(510, 50, 290, 195);
        srclblSachTra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        pnThongTinPhieuTra.add(lblTitleCTPhieuTra);
        pnThongTinPhieuTra.add(btnThemSach);
        pnThongTinPhieuTra.add(btnXoaSach);
        pnThongTinPhieuTra.add(srclblSachTra, BorderLayout.CENTER);

        pnTablePhieuTra.add(pnThongTinPhieuTra);

        //=================BUTTON===============


        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton();
        btnTimTrongKhoang = new JButton();
        btnInthe = new JButton("In thẻ");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnPhieuMuon = new JButton("...");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnTimTrongKhoang.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnInthe.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnPhieuMuon.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnTimTrongKhoang.setIcon(new ImageIcon("image/Search-icon.png"));
        btnInthe.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnInthe.setBounds(55, 380, 120, 40);
        btnThem.setBounds(200, 380, 120, 40);
        btnXoa.setBounds(345, 380, 120, 40);
        btnTim.setBounds(430, 265, 50, 30);
        btnTimTrongKhoang.setBounds(630,325,50,30);
        btnXuatExcel.setBounds(485, 380, 120, 40);
        btnNhapExcel.setBounds(630, 380, 120, 40);
        btnPhieuMuon.setBounds(430, 70, 30, 25);

        pnThongTinPhieuTra.add(btnInthe);
        pnThongTinPhieuTra.add(btnThem);
        pnThongTinPhieuTra.add(btnXoa);
        pnThongTinPhieuTra.add(btnTim);
        pnThongTinPhieuTra.add(btnTimTrongKhoang);
        pnThongTinPhieuTra.add(btnXuatExcel);
        pnThongTinPhieuTra.add(btnNhapExcel);
        pnThongTinPhieuTra.add(btnPhieuMuon);

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
        scrTblPhieuTra.setPreferredSize(new Dimension(900, 120));

        scrTblPhieuTra.setBounds(0, 440, 820, 165);
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
                txtTimKiem.setText("");
                dateBD.setDate(null);
                dateKT.setDate(null);
                dtmSachTra.setRowCount(0);
                danhSachctpt.clear();
            }
        });
        tblPhieuTra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuTra();
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
                dtmSachTra.setRowCount(0);
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
                dtmSachTra.setRowCount(0);
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
        btnPhieuMuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timPhieuMuonGUI = new DlgTimPhieuMuon();
                timPhieuMuonGUI.setVisible(true);
                xuLyTimPhieuMuon();
                xuLyThemNgayThang();
                danhSachctpt.clear();
            }
        });
        txtMaPhieuMuon.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadDataLenBangCTPhieuTraKhiChon();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loadDataLenBangCTPhieuTraKhiChon();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadDataLenBangCTPhieuTraKhiChon();
            }
        });
        btnThemSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraSachTra(txtMaPhieuTra.getText())) {
                    xuLyThemCTPhieuTra();
                }

            }
        });
        btnXoaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kiemTraSachTra(txtMaPhieuTra.getText())){
                    xuLyXoaCTPhieuTra();
                }
            }
        });
        btnTimTrongKhoang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTrongKhoang();
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

    private void loadDataLenBangCTPhieuTra(){
        ArrayList<CTPhieuTra> listCTPhieuTra = ctPhieuTraBUS.getListCTPhieuTraTheoMaPT(txtMaPhieuTra.getText());
        ctPhieuTraBUS.docListCTPhieuTra();
        dtmSachTra.setRowCount(0);
        for(CTPhieuTra ctpt : listCTPhieuTra){
            Vector vec = new Vector<>();
            vec.add(ctpt.getMaSach());
            vec.add(sachBUS.getTenSach(ctpt.getMaSach()));
            vec.add("Đã trả");
            dtmSachTra.addRow(vec);
        }
    }

    private void loadDataLenBangCTPhieuTraKhiChon(){
        ArrayList<CTPhieuMuon> listCTPhieuTra = ctPhieuMuonBUS.getListCTPhieuMuonTheoMaPM(txtMaPhieuMuon.getText());
        ctPhieuMuonBUS.docListCTPhieuMuon();
        dtmSachTra.setRowCount(0);
        for(CTPhieuMuon ctpm : listCTPhieuTra){
            int count = 0;
            Vector vec = new Vector<>();
            vec.add(ctpm.getMaSach());
            vec.add(sachBUS.getTenSach(ctpm.getMaSach()));
            for(CTPhieuTra ctpt : ctPhieuTraBUS.luaChon(txtMaPhieuMuon.getText())){
                if(ctpt.getMaSach() == ctpm.getMaSach()){
                    vec.add("Đã trả");
                    count = 1;
                }
            }
            if(count == 0){
                vec.add("Mượn");
            }
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
            loadDataLenBangCTPhieuTra();
            danhSachctpt.clear();
        }
    }

    private void xuLyThemPhieuTra(){
        if(danhSachctpt.size() == 0){
            new MyDialog("Chưa chọn sách trả!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        boolean flag = ptBUS.themPhieuTra(txtMaPhieuTra.getText(),
                txtMaPhieuMuon.getText(),
                txtDocGia.getText(), String.valueOf(dangNhapGUI.maTaiKhoan()),
                txtNgayTraThuc.getText());
        ptBUS.docListPhieuTra();
        if(flag) {
            xuLyThemCTPhieuTraTuArray();
            loadDataLenBangPhieuTra();
            dtmSachTra.setRowCount(0);
        }
        danhSachctpt.clear();
    }

    private void xuLyThemCTPhieuTra(){
        int row = tblSachTra.getSelectedRow();
        if(row < 0){
            new MyDialog("Hãy chọn sách trả!!!", MyDialog.ERROR_DIALOG);
            return;
        }else {
            String tt = dtmSachTra.getValueAt(row,2)+"";
            if(tt.equals("Đã trả")){
                new MyDialog("Sách đã được trả!!!", MyDialog.ERROR_DIALOG);
                return;
            }else {
                dtmSachTra.setValueAt("Đã trả",row,2);
                CTPhieuTra ctpt = new CTPhieuTra();
                ctpt.setMaPhieuTra(Integer.parseInt(txtMaPhieuTra.getText()));
                ctpt.setMaSach(Integer.parseInt(dtmSachTra.getValueAt(row,0)+""));
                danhSachctpt.add(ctpt);
            }
        }
    }

    private void xuLyThemCTPhieuTraTuArray(){
        for(CTPhieuTra ctpt : danhSachctpt){
            String maPT = String.valueOf(ctpt.getMaPhieuTra());
            String maSach = String.valueOf(ctpt.getMaSach());
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
        int row = tblSachTra.getSelectedRow();
        if(row < 0){
            new MyDialog("Chưa chọn sách trả để bỏ chọn!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        else {
            if(!ctPhieuTraBUS.xacDinhCTPT(dtmSachTra.getValueAt(row, 0) + "", txtMaPhieuMuon.getText())) {
                if ((dtmSachTra.getValueAt(row, 2) + "").equals("Mượn")) {
                    new MyDialog("Sách chưa được trả!!!", MyDialog.ERROR_DIALOG);
                    return;
                } else {
                    dtmSachTra.setValueAt("Mượn", row, 2);

                    int maPT = Integer.parseInt(txtMaPhieuTra.getText());
                    int maS = Integer.parseInt(dtmSachTra.getValueAt(row, 0) + "");

                    danhSachctpt.removeIf(ctpt -> ctpt.getMaSach() == maS);
                }
            }else {
                new MyDialog("Sách đã được trả trước rồi!!!", MyDialog.ERROR_DIALOG);
                return;
            }
        }
    }

    private boolean xuLyXoaCTPhieuTra(String ma){
        boolean flag = ctPhieuTraBUS.xoaCTPhieuTra(ma);
        int row = dtmSachTra.getRowCount();
        for(int i=0;i<row;i++){
            String maSach = String.valueOf(dtmSachTra.getValueAt(i,0));
            sachBUS.chonSach(maSach);
        }
        return flag;
    }

    private boolean kiemTraSachTra(String ma){
        int row = dtmPhieuTra.getRowCount();
        for(int i=0;i<row;i++){
            String maPT = String.valueOf(dtmPhieuTra.getValueAt(i,0));
            if(ma.trim().equals(maPT)){
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

    private void xuLyTimPhieuMuon(){

        if(timPhieuMuonGUI.phieuMuonTimDuoc != null){
            txtMaPhieuMuon.setText(String.valueOf(timPhieuMuonGUI.phieuMuonTimDuoc.getMaPhieuMuon()));
            txtDocGia.setText(docGiaBUS.getTenDocGia(timPhieuMuonGUI.phieuMuonTimDuoc.getMaDocGia()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtNgayMuon.setText(String.valueOf(sdf.format(timPhieuMuonGUI.phieuMuonTimDuoc.getNgayMuon())));
        }
    }

    private void xuLyTimKiem(){
        String docGia = txtTimKiem.getText();
        String maDocGia = String.valueOf(docGiaBUS.getMaDocGia(docGia));
        dtmPhieuTra.setRowCount(0);
        ArrayList<PhieuTra> dspt  = null;
        dspt = ptBUS.getListPhieuTraTheoMaDocGia(maDocGia);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuTra pt : dspt){
            Vector vec = new Vector();
            vec.add(pt.getMaPhieuTra());
            vec.add(pt.getMaPhieuMuon());
            vec.add(docGiaBUS.getTenDocGia(pt.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pt.getMaNhanVien()));
            vec.add(sdf.format(pmBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon()));
            vec.add(sdf.format(pt.getNgayTraThuc()));
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
        ArrayList<Vector> dsctpm = new ArrayList<>();
        int row = tblSachTra.getRowCount();
        int rowPT = tblPhieuTra.getRowCount();
        if(row == 0) return;
        int count = 0;
        for(int i=0;i<rowPT;i++) {
            String ma = dtmPhieuTra.getValueAt(i,0)+"";
            if (ma.equals(txtMaPhieuTra.getText())) {
                count = 1;
            }
        }
        if(count == 0){
            new MyDialog("Phiếu trả chưa được tạo!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        for(int i=0;i<row;i++){
            Vector vec = new Vector();
            vec.add(tblSachTra.getValueAt(i,0));
            vec.add(tblSachTra.getValueAt(i,1));
            dspt.add(vec);
        }
        for(CTPhieuMuon ctPhieuMuon : ctPhieuMuonBUS.getListCTPhieuMuonTheoMaPM(txtMaPhieuMuon.getText())){
            Vector vect = new Vector<>();
            vect.add(ctPhieuMuon.getMaPhieuMuon());
            vect.add(ctPhieuMuon.getMaSach());
            dsctpm.add(vect);
        }

        int maPT = Integer.parseInt(txtMaPhieuTra.getText());
        XuatPhieuTraGUI phieuTraGUI = new XuatPhieuTraGUI(dspt,dsctpm,maPT,txtDocGia.getText(),
                nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()),txtNgayMuon.getText(),txtNgayTraThuc.getText());
        phieuTraGUI.setVisible(true);
    }

    private void xuLyTimKiemTrongKhoang(){
        if(dateBD.equals("") || dateKT.equals("")){
            new MyDialog("Hãy nhập đủ thông tin!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        Date ngay1 = dateBD.getDate();
        Date ngay2 = dateKT.getDate();
        if(ngay1.compareTo(ngay2) > 0){
            new MyDialog("Ngày bắt đầu không được sau ngày kết thúc!!!",MyDialog.ERROR_DIALOG);
            return;
        }
        dtmPhieuTra.setRowCount(0);
        ArrayList<PhieuTra> dspt  = null;
        dspt = ptBUS.timKiemTrongKhoang(ngay1,ngay2);;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuTra pt : dspt){
            Vector vec = new Vector();
            vec.add(pt.getMaPhieuTra());
            vec.add(pt.getMaPhieuMuon());
            vec.add(docGiaBUS.getTenDocGia(pt.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pt.getMaNhanVien()));
            vec.add(sdf.format(pmBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon()));
            vec.add(sdf.format(pt.getNgayTraThuc()));
            dtmPhieuTra.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dspt.size(), MyDialog.INFO_DIALOG);
    }

    public void xuLyThoatPhieuTra(){
        int rowCount = tblSachTra.getRowCount();
        int row = tblPhieuTra.getRowCount();
        int count = 0;
        for(int j=0; j<row;j++){
            String maPT = String.valueOf(dtmPhieuTra.getValueAt(j,0));
            if(txtMaPhieuTra.getText().equals(maPT)){
                count = 1;
            }
        }
        if(count != 1) {
            for(int i=0;i<rowCount; i++){
                String maSach = String.valueOf(dtmSachTra.getValueAt(i,0));
                sachBUS.chonSach(maSach);
            }
        }else return;
    }
}
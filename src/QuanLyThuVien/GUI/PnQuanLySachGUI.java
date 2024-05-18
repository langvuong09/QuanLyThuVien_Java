package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.Sach;
import QuanLyThuVien.DTO.Loai;
import QuanLyThuVien.DTO.NXB;

import static Main.Main.changLNF;
import MyCustom.MyTable;
import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import MyCustom.TransparentPanel;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DTO.TacGia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSach;
    DefaultTableModel dtmSach;
    JTextField txtIDSach, txtTenSach, txtGia, txtTimKiem, txtSoLuong;
    JTextArea txtGhiChu;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnDocGia, btnSach;
    JComboBox<String> cmbLoai, cmbTacGia;
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
                                    PANEL Phiếu mượn
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
        txtIDSach = new JTextField(x);
        cmbLoai = new JComboBox<String>();
        txtTenSach = new JTextField(x);
        cmbTacGia = new JComboBox<String>();
        txtGia = new JTextField(y);
        txtGhiChu = new JTextArea();
        txtSoLuong = new JTextField(x);
        txtTimKiem = new JTextField(x);


        //=================Thông tin phiếu mượn==============

        JPanel pnThongTinSach = new TransparentPanel();
        pnThongTinSach.setLayout(null);

        JLabel lblIDSach = new JLabel("Mã sách:");
        lblIDSach.setFont(font);
        txtIDSach.setFont(font);
        txtIDSach.setEditable(false);
        lblIDSach.setBounds(20, 50, 140, 25);
        txtIDSach.setBounds(120, 50, 200, 25);

        JLabel lblTenSach = new JLabel("Tên sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        lblTenSach.setBounds(20, 100, 140, 25);
        txtTenSach.setBounds(120, 100, 200, 25);

        JLabel lblLoaiSach = new JLabel("Loại sách:");
        lblLoaiSach.setFont(font);
        cmbLoai.setFont(font);
        cmbLoai.setPreferredSize(txtIDSach.getPreferredSize());
        lblLoaiSach.setBounds(20, 150, 140, 25);
        cmbLoai.setBounds(120,150,200,25);

        JLabel lblTacGia = new JLabel("Tác giả:");
        lblTacGia.setFont(font);
        cmbTacGia.setFont(font);
        lblTacGia.setBounds(20, 200, 140, 25);
        cmbTacGia.setBounds(120, 200, 200, 25);

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
        pnThongTinSach.add(lblTacGia);
        pnThongTinSach.add(cmbTacGia);
        pnThongTinSach.add(lblGia);
        pnThongTinSach.add(txtGia);

        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setFont(font);
        txtGhiChu.setFont(font);
        txtGhiChu.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtGhiChu);
        lblGhiChu.setBounds(400, 50, 140, 25);
        scrollPane.setBounds(520, 50, 220, 125);

        JLabel lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setFont(font);
        txtSoLuong.setFont(font);
        lblSoLuong.setBounds(400,200,140,25);
        txtSoLuong.setBounds(520,200,220,25);

        JLabel lblTimKiem = new JLabel("Tên sách cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(350, 250, 300, 25);
        txtTimKiem.setBounds(520, 250, 250, 25);

        pnThongTinSach.add(lblGhiChu);
        pnThongTinSach.add(scrollPane);
        pnThongTinSach.add(lblSoLuong);
        pnThongTinSach.add(txtSoLuong);
        pnThongTinSach.add(lblTimKiem);
        pnThongTinSach.add(txtTimKiem);

        pnTableSach.add(pnThongTinSach);

        //=================Chi tiết phiếu mượn==========


        //=================BUTTON===============


        btnThem = new JButton("Thêm");
        btnSua = new JButton("Lưu");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");
        btnDocGia = new JButton("...");
        btnSach = new JButton("...");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);
        btnDocGia.setFont(fontButton);
        btnSach.setFont(fontButton);

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
        //btnSach.setBounds(705, 50, 30, 25);

        pnThongTinSach.add(btnThem);
        pnThongTinSach.add(btnSua);
        pnThongTinSach.add(btnXoa);
        pnThongTinSach.add(btnTim);
        pnThongTinSach.add(btnXuatExcel);
        pnThongTinSach.add(btnNhapExcel);
        // pnThongTinSach.add(btnSach);

        pnTableSach.add(pnThongTinSach);

        //====================Bảng phiếu mượn====================
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã");
        dtmSach.addColumn("Loại sách");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Giá");
        dtmSach.addColumn("Ghi chú");
        dtmSach.addColumn("SL");

        tblSach = new MyTable(dtmSach);


        tblSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSach = tblSach.getColumnModel();
        columnModelSach.getColumn(0).setPreferredWidth(50);
        columnModelSach.getColumn(1).setPreferredWidth(150);
        columnModelSach.getColumn(2).setPreferredWidth(130);
        columnModelSach.getColumn(3).setPreferredWidth(230);
        columnModelSach.getColumn(4).setPreferredWidth(130);
        columnModelSach.getColumn(5).setPreferredWidth(170);
        columnModelSach.getColumn(6).setPreferredWidth(80);


        JScrollPane scrTblSach = new JScrollPane(tblSach);
        scrTblSach.setPreferredSize(new Dimension(900, 230));

        scrTblSach.setBounds(0, 370, 820, 235);
        //</editor-fold>
        pnThongTinSach.add(scrTblSach, BorderLayout.CENTER);

        //loadDataLenBangSach();

        pnTableSach.add(pnThongTinSach);

        //=======================================================

        this.add(pnTableSach);
        loadDataLoai();
        loadDataTG();
        loadDataLenTableSach();
    }

    private void addEventsSach() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIDSach.setText("");
                txtTenSach.setText("");
                cmbLoai.setSelectedIndex(0);
                cmbTacGia.setSelectedIndex(0);
                txtGhiChu.setText("");
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
                cmbTacGia.setSelectedIndex(0);
                txtGhiChu.setText("");
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
                cmbTacGia.setSelectedIndex(0);
                txtGhiChu.setText("");
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
                cmbTacGia.setSelectedIndex(0);
                txtGhiChu.setText("");
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
        cmbLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemLoai();
            }
        });
        cmbTacGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemTG();
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

    private void loadDataLoai(){
        cmbLoai.removeAllItems();

        ArrayList<Loai> dsl = loaiBUS.getListLoai();
        cmbLoai.addItem("0 - Chọn loại");
        for (Loai l : dsl){
            cmbLoai.addItem(l.getMaLoai() +" - "+l.getTenLoai());
        }
        cmbLoai.addItem("Khác...");
    }

    private void xuLyThemTG(){
        int x = cmbTacGia.getSelectedIndex();
        if(x == cmbTacGia.getItemCount() -1){
            DlgTacGia tgGUI = new DlgTacGia();
            tgGUI.setVisible(true);
            loadDataTG();
        }
    }

    private void loadDataTG(){
        cmbTacGia.removeAllItems();

        ArrayList<TacGia> dstg = tacGiaBUS.getListTacGia();
        cmbTacGia.addItem("0 - Chọn tác giả");
        for(TacGia tg : dstg){
            cmbTacGia.addItem(tg.getMaTacGia() +" - "+tg.getTenTacGia());
        }
        cmbTacGia.addItem("Khác...");
    }

    private void loadDataLenTableSach(){
        sachBUS.docDanhSach();
        dtmSach.setRowCount(0);

        ArrayList<Sach> dss = sachBUS.getListSach();

        for(Sach s : dss){
            Vector vec = new Vector<>();
            vec.add(s.getMaSach());
            vec.add(loaiBUS.getTenLoai(s.getMaLoaiSach()));
            vec.add(tacGiaBUS.getTenTacGia(s.getMaTacGia()));
            vec.add(s.getTenSach());
            vec.add(s.getGiaSach());
            vec.add(s.getGhiChu());
           vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
    }

    private void xuLyClickTblSach(){
        int row = tblSach.getSelectedRow();
        if(row > -1){
            String maSach = tblSach.getValueAt(row,0)+"";
            String giaSach = tblSach.getValueAt(row,4)+"";
            String loai = tblSach.getValueAt(row,1)+"";
            String tacGia = tblSach.getValueAt(row,2)+"";
            txtIDSach.setText(maSach);
            txtTenSach.setText(tblSach.getValueAt(row,3)+"");
            txtGia.setText(giaSach);
            txtGhiChu.setText(tblSach.getValueAt(row,5)+"");
            txtSoLuong.setText(tblSach.getValueAt(row,6)+"");

            int l=-1,nxb=-1,tg=-1;
            for (int i = 0; i < cmbLoai.getItemCount(); i++) {
                if (cmbLoai.getItemAt(i).contains(loai)) {
                    l = i;
                    break;
                }
            }
            for(int i=0;i<cmbTacGia.getItemCount();i++){
                if(cmbTacGia.getItemAt(i).contains(tacGia)){
                    tg=i;
                    break;
                }
            }

            cmbLoai.setSelectedIndex(l);
            cmbTacGia.setSelectedIndex(tg);
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
            String tacGia = tblSach.getValueAt(i,2)+"";
            String tenSach = tblSach.getValueAt(i,3)+"";
            String gia = tblSach.getValueAt(i,4)+"";
            String ghiChu = tblSach.getValueAt(i,5)+"";
            String soLuong = tblSach.getValueAt(i,6)+"";

            sachBUS.nhapSachExcel(loai,tacGia,tenSach,gia,ghiChu,soLuong);
        }
    }

    private void xuLyThemSach(){
        String loaiSach = (String) cmbLoai.getSelectedItem();
        String tacGia = (String) cmbTacGia.getSelectedItem();
        boolean flag = sachBUS.themSach(loaiSach,tacGia,txtTenSach.getText(),txtGia.getText(),txtGhiChu.getText(),txtSoLuong.getText());
        loadDataLenTableSach();
    }

    private void xuLyXoaSach(){
        boolean flag = sachBUS.xoaSach(txtIDSach.getText());
        loadDataLenTableSach();
    }

    private void xuLySuaSach(){
        String loaiSach = (String) cmbLoai.getSelectedItem();
        String tacGia = (String) cmbTacGia.getSelectedItem();
        String maSach = txtIDSach.getText();
        boolean flag = sachBUS.suaSach(maSach,loaiSach,tacGia,txtTenSach.getText(),txtGia.getText(),txtGhiChu.getText(),txtSoLuong.getText());
        loadDataLenTableSach();
    }

    private void xuLyTimKiem(){
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = null;
        dss = sachBUS.getListSachTheoTenSach(txtTimKiem.getText());
        for(Sach s : dss){
            Vector vec = new Vector<>();
            vec.add(s.getMaSach());
            vec.add(loaiBUS.getTenLoai(s.getMaLoaiSach()));
            vec.add(tacGiaBUS.getTenTacGia(s.getMaTacGia()));
            vec.add(s.getTenSach());
            vec.add(s.getGiaSach());
            vec.add(s.getGhiChu());
            vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dss.size(), MyDialog.INFO_DIALOG);
    }
}

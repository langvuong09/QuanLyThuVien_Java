package QuanLyThuVien.GUI;

import QuanLyThuVien.DTO.Sach;
import static Main.Main.changLNF;
import MyCustom.MyTable;
import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import MyCustom.TransparentPanel;
import QuanLyThuVien.BUS.SachBUS;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnQuanLySachGUI extends JPanel {

    public PnQuanLySachGUI() {
        changLNF("Windows");
        addConTrolsSach();
        addEventsSach();

    }


    SachBUS sachBUS = new SachBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSach;
    DefaultTableModel dtmSach;
    JTextField txtIDSach, txtTenSach,txtLoaiSach, txtTacGia, txtGia, txtNXB, txtGhiChu, txtTrangThai, txtTimKiem;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnDocGia, btnSach;
    JComboBox jComboBox1, jComboBox2;
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

        txtLoaiSach = new JTextField(x);
        txtTenSach = new JTextField(x);
        txtTacGia = new JTextField(x);
        txtGia = new JTextField(y);
        txtNXB = new JTextField(y);
        txtGhiChu = new JTextField(y);
        txtTimKiem = new JTextField(x);


        //=================Thông tin phiếu mượn==============

        JPanel pnThongTinSach = new TransparentPanel();
        pnThongTinSach.setLayout(null);

        JLabel lblIDSach = new JLabel("Mã Sách:");
        lblIDSach.setFont(font);
        txtIDSach.setFont(font);
        txtIDSach.setEditable(false);
        lblIDSach.setBounds(20, 50, 140, 25);
        txtIDSach.setBounds(120, 50, 200, 25);

        JLabel lblTenSach = new JLabel("Ten Sách:");
        lblTenSach.setFont(font);
        txtTenSach.setFont(font);
        lblTenSach.setBounds(20, 100, 140, 25);
        txtTenSach.setBounds(120, 100, 200, 25);

        JLabel lblLoaiSach = new JLabel("Loai sach");
        lblLoaiSach.setFont(font);
        txtLoaiSach.setFont(font);
        lblLoaiSach.setBounds(20, 150, 140, 25);
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBounds(120, 150, 200, 25);
        jComboBox1.setSelectedIndex(-1);
        jComboBox1.setFont(font);

        JLabel lblTacGia = new JLabel("tac gia");

        lblTacGia.setFont(font);
        txtTacGia.setFont(font);
        lblTacGia.setBounds(20, 200, 140, 25);
        txtTacGia.setBounds(120, 200, 200, 25);

        JLabel lblGia = new JLabel("Gia");
        lblGia.setFont(font);
        txtGia.setFont(font);
        txtGia.setEditable(true);
        lblGia.setBounds (20, 250, 140, 25);
        txtGia.setBounds (120, 250, 200, 25);

        JLabel lblTimKiem = new JLabel("Tên Sách cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(350, 225, 300, 25);
        txtTimKiem.setBounds(500, 225, 250, 25);

        pnThongTinSach.add(lblIDSach);
        pnThongTinSach.add(txtIDSach);
        pnThongTinSach.add(lblTenSach);
        pnThongTinSach.add(txtTenSach);
        pnThongTinSach.add(lblLoaiSach);
        pnThongTinSach.add(jComboBox1);
        pnThongTinSach.add(lblTacGia);
        pnThongTinSach.add(txtTacGia);
        pnThongTinSach.add(lblGia);
        pnThongTinSach.add(txtGia);





        JLabel lblNXB = new JLabel("NXB");
        lblNXB.setFont(font);
        txtNXB.setFont(font);
        //txtNXB.setEditable(false);
        lblNXB.setBounds(400, 50, 140, 25);
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBounds(500, 50, 220, 25);
        jComboBox2.setSelectedIndex(-1);
        jComboBox2.setFont(font);

        //txtNXB.setBounds(500, 50, 220, 25);

        JLabel lblGhiChu = new JLabel("Ghi chu");
        lblGhiChu.setFont(font);
        txtGhiChu.setFont(font);
        lblGhiChu.setBounds(400, 100, 140, 25);
        txtGhiChu.setBounds(500, 100, 220, 100);


        pnThongTinSach.add(lblNXB);
        pnThongTinSach.add(jComboBox2);
        pnThongTinSach.add(lblGhiChu);
        pnThongTinSach.add(txtGhiChu);
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
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("ten sach");
        dtmSach.addColumn("Loại sách");
        dtmSach.addColumn("Tac gia");
        dtmSach.addColumn("Gia");
        dtmSach.addColumn("NXB");
        dtmSach.addColumn("Ghi chú");
        dtmSach.addColumn("Trạng thái");

        tblSach = new MyTable(dtmSach);


        tblSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSach = tblSach.getColumnModel();
        columnModelSach.getColumn(0).setPreferredWidth(70);
        columnModelSach.getColumn(1).setPreferredWidth(150);
        columnModelSach.getColumn(2).setPreferredWidth(150);
        columnModelSach.getColumn(3).setPreferredWidth(130);
        columnModelSach.getColumn(4).setPreferredWidth(130);
        columnModelSach.getColumn(5).setPreferredWidth(130);
        columnModelSach.getColumn(6).setPreferredWidth(130);
        columnModelSach.getColumn(7).setPreferredWidth(130);


        JScrollPane scrTblSach = new JScrollPane(tblSach);
        scrTblSach.setPreferredSize(new Dimension(900, 150));

        scrTblSach.setBounds(0, 370, 820, 155);
        //</editor-fold>
        pnThongTinSach.add(scrTblSach, BorderLayout.CENTER);

        //loadDataLenBangSach();

        pnTableSach.add(pnThongTinSach);

        //=======================================================
        this.add(pnTableSach);
    }

    private void addEventsSach() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadDataLenSach();
                txtIDSach.setText("");
                txtTenSach.setText("");
                txtLoaiSach.setText("");
                txtTacGia.setText("");
                txtGhiChu.setText("");
                txtNXB.setText("");
                txtGia.setText("");
                txtTrangThai.setText("");
                txtTimKiem.setText("");

            }
        });
        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //xuLyClickTblSach();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSach();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xuLySuaSach();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xuLyXoaSach();
            }
        });
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xuLyTimKiem();
            }
        });
        txtTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xuLyTimKiem();
            }
        });
        btnXuatExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //xuLyXuatFileExcel();
            }
        });
        btnNhapExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyNhapFileExcel();
            }
        });
    }

    private void xuLyNhapFileExcel() {
        MyDialog dlg = new MyDialog("Dữ liệu cũ sẽ bị xoá, tiếp tục?", MyDialog.WARNING_DIALOG);
        if (dlg.getAction() != MyDialog.OK_OPTION) {
            return;
        }

        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblSach);

        int row = tblSach.getRowCount();
        for (int i = 0; i < row; i++) {
            String maSach = tblSach.getValueAt(i, 1) + "";
            String TenSach = tblSach.getValueAt(i, 2) + "";
            String LoaiSach = tblSach.getValueAt(i, 3) + "";
            String TacGia = tblSach.getValueAt(i, 4) + "";
            String Gia = tblSach.getValueAt(i, 5) + "";
            String NXB = tblSach.getValueAt(i, 6) + "";
            String GhiChu = tblSach.getValueAt(i, 7) + "";
            String TrangThai = tblSach.getValueAt(i, 8) + "";

//            SachBUS.nhapSachTuExcel(maSach,TenSach,LoaiSach,TacGia,Gia,NXB,GhiChu,TrangThai);
        }
    }
    private void xuLyXuatFileExcel(){
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblSach);
    }

    private void xuLyClickTblPhieuMuon(){
        int row = tblSach.getSelectedRow();
        if(row > -1){
            String maSach =tblSach.getValueAt(row,0)+"";
            String TenSach =tblSach.getValueAt(row,1)+"";
            String loaiSach =tblSach.getValueAt(row,2)+"";
            String tacgia =tblSach.getValueAt(row,3)+"";
            String gia =tblSach.getValueAt(row,4)+"";
            String NXB =tblSach.getValueAt(row,5)+"";
            String GhiChu =tblSach.getValueAt(row,6)+"";
            String trangThai =tblSach.getValueAt(row,7)+"";

            txtIDSach.setText(maSach);
            txtTenSach.setText(TenSach);
            txtLoaiSach.setText(loaiSach);
            txtTacGia.setText(tacgia);
            txtGia.setText(gia);
            txtNXB.setText(NXB);
            txtGhiChu.setText(GhiChu);
            txtTrangThai.setText(trangThai);
        }
    }

    private void xuLyThemSach(){
//        boolean flag = sachBUS.ThemSach(txtIDSach.getText(),
//                txtTenSach.getText(),txtLoaiSach.getText(),txtTacGia.getText(),txtGia.getText(),txtNXB.getText(), txtGhiChu.getText());
//        sachBUS.docDanhSach();
//        if(flag) {
//            //loadDataLenBangPhieuMuon();
//            //loadDataLenBangCTPhieuMuon("0");
//        }
    }
}

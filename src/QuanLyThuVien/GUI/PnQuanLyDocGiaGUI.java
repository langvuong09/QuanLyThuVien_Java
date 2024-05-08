package QuanLyThuVien.GUI;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import QuanLyThuVien.BUS.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

import static Main.Main.changLNF;

public class PnQuanLyDocGiaGUI extends JPanel{
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    public PnQuanLyDocGiaGUI() {
        changLNF("Windows");
        addConTrolsDocGia();
        addEventsDocGia();
    }
    DocGiaBUS dgBUS = new DocGiaBUS();
    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblDocGia;
    CardLayout cardDocGiaGroup = new CardLayout();
    JPanel pnCardTabDocGia;
    DefaultTableModel dtmDocGia;
    JTextField txtIDDocGia, txtTenDocGia,txtHoDocGia, txtSDT, txtGmail, txtTimKiem;
    JButton btnThem, btnXoa, btnSua, btnReset, btnXuatExcel, btnNhapExcel, btnTim;
    JLabel lblTabbedDocGia;
    JRadioButton jRBmuon,jRBchuamuon;
    JComboBox<String> comboBoxGioiTinh;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");

    private void addConTrolsDocGia(){
        Font font = new Font("Tahoma", Font.PLAIN,16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL Nhân Viên
        =========================================================================
         */

        JPanel pnTableDocGia = new TransparentPanel();
        pnTableDocGia.setLayout(new BorderLayout());

        JPanel pnTitleDocGia = new TransparentPanel();
        JLabel lblTitleDocGia = new JLabel("Quản lý đọc giả");
        lblTitleDocGia.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleDocGia.add(lblTitleDocGia);
        pnTitleDocGia.add(btnReset);
        pnTableDocGia.add(pnTitleDocGia, BorderLayout.NORTH);
        //=================PANEL INPUT===========
//        int x =15, y=15;
        txtIDDocGia = new JTextField();
        txtHoDocGia = new JTextField();
        txtTenDocGia = new JTextField();
        txtGmail = new JTextField();
        txtSDT = new JTextField();
        txtTimKiem = new JTextField();
        txtIDDocGia.setText("");
        txtHoDocGia.setText("");
        txtTenDocGia.setText("");
        txtGmail.setText("");
        txtSDT.setText("");
        String[] gioiTinhValues = {"Nam", "Nữ", "Không xác định"};
        comboBoxGioiTinh = new JComboBox<>(gioiTinhValues);

        //=================Thông tin nhân viên==============

        JPanel pnThongTinDocGia = new TransparentPanel();
        pnThongTinDocGia.setLayout(null);

        JLabel lblMaDocGia = new JLabel("Mã đọc giả:");
        lblMaDocGia.setFont(font);
        txtIDDocGia.setFont(font);
        txtIDDocGia.setEditable(false);
        lblMaDocGia.setBounds(20,40,150,25);
        txtIDDocGia.setBounds(200,40,320,25);

        JLabel lblHoDocGia = new JLabel("Họ đọc giả:");
        lblHoDocGia.setFont(font);
        txtHoDocGia.setFont(font);
        txtHoDocGia.setEditable(true);
        lblHoDocGia.setBounds(20,80,150,25);
        txtHoDocGia.setBounds(200,80,320,25);

        JLabel lblTenDocGia = new JLabel("Tên đọc giả:");
        lblTenDocGia.setFont(font);
        txtTenDocGia.setFont(font);
        txtTenDocGia.setEditable(true);
        lblTenDocGia.setBounds(20,120,150,25);
        txtTenDocGia.setBounds(200,120,320,25);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(font);
        comboBoxGioiTinh.setFont(font);
        lblGioiTinh.setBounds(20,200,150,25);
        comboBoxGioiTinh.setBounds(200,200,320,25);

        JLabel lblGmail = new JLabel("Gmail:");
        lblGmail.setFont(font);
        txtGmail.setFont(font);
        txtGmail.setEditable(true);
        lblGmail.setBounds(20,240,150,25);
        txtGmail.setBounds(200,240,320,25);

        JLabel lblSDT = new JLabel("SDT:");
        lblSDT.setFont(font);
        txtSDT.setFont(font);
        txtSDT.setEditable(true);
        lblSDT.setBounds(20,160,150,25);
        txtSDT.setBounds(200,160,320,25);

        JLabel lblTimKiem = new JLabel("Tên đọc giả cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20,300,300,25);
        txtTimKiem.setBounds(210,300,350,25);

        pnThongTinDocGia.add(lblMaDocGia);
        pnThongTinDocGia.add(txtIDDocGia);
        pnThongTinDocGia.add(lblHoDocGia);
        pnThongTinDocGia.add(txtHoDocGia);
        pnThongTinDocGia.add(lblTenDocGia);
        pnThongTinDocGia.add(txtTenDocGia);
        pnThongTinDocGia.add(lblGioiTinh);
        pnThongTinDocGia.add(comboBoxGioiTinh);
        pnThongTinDocGia.add(lblGmail);
        pnThongTinDocGia.add(txtGmail);
        pnThongTinDocGia.add(lblSDT);
        pnThongTinDocGia.add(txtSDT);
        pnThongTinDocGia.add(lblTimKiem);
        pnThongTinDocGia.add(txtTimKiem);
        //=================BUTTON===============

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm kiếm");
        btnSua = new JButton("Sửa");
        btnXuatExcel = new JButton("Xuất");
        btnNhapExcel = new JButton("Nhập");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnXuatExcel.setFont(fontButton);
        btnNhapExcel.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnSua.setIcon(new ImageIcon("image/card-icon.png"));
        btnXuatExcel.setIcon(new ImageIcon("image/excel-icon.png"));
        btnNhapExcel.setIcon(new ImageIcon("image/excel-icon.png"));

        btnSua.setBounds(55,350,110,40);
        btnThem.setBounds(170,350,110,40);
        btnXoa.setBounds(285,350,110,40);
        btnTim.setBounds(400,350,110,40);
        btnXuatExcel.setBounds(515,350,110,40);
        btnNhapExcel.setBounds(630,350,110,40);

        pnThongTinDocGia.add(btnSua);
        pnThongTinDocGia.add(btnThem);
        pnThongTinDocGia.add(btnXoa);
        pnThongTinDocGia.add(btnTim);
        pnThongTinDocGia.add(btnXuatExcel);
        pnThongTinDocGia.add(btnNhapExcel);

        pnTableDocGia.add(pnThongTinDocGia);

        //====================Bảng nhân viên====================
        dtmDocGia = new DefaultTableModel();
        dtmDocGia.addColumn("Mã");
        dtmDocGia.addColumn("Họ");
        dtmDocGia.addColumn("Tên");
        dtmDocGia.addColumn("SDT");
        dtmDocGia.addColumn("Giới tính");
        dtmDocGia.addColumn("Gmail");
        tblDocGia = new MyTable(dtmDocGia);

        tblDocGia.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuMuon = tblDocGia.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(150);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(80);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(120);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(100);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(230);

        JScrollPane scrTblDocGia = new JScrollPane(tblDocGia);
        scrTblDocGia.setPreferredSize(new Dimension(900,150));

        scrTblDocGia.setBounds(0,410,820,155);
        //</editor-fold>
        pnThongTinDocGia.add(scrTblDocGia, BorderLayout.CENTER);

//        loadDataLenBangDocGia();

        pnTableDocGia.add(pnThongTinDocGia);
        //=======================================================
        this.add(pnTableDocGia);
    }

    private void addEventsDocGia(){

    }
}



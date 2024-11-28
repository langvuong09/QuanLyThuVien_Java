package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.XuLyFileExcel;
import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DTO.Sach;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class PnQuanLyPhieuMuonGUI extends JPanel {
    private DlgTimDocGia timDocGiaGUI = new DlgTimDocGia();
    private DlgTimSach timSachGUI;
    private SachBUS sachBUS = new SachBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private String tenNhanVien=nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan());
    private String maNhanVien=dangNhapGUI.maTaiKhoan()+"";

    PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();
    public PnQuanLyPhieuMuonGUI (){
        addControlsPhieuMuon();
        addEventsPhieuMuon();
    }

    private JLabel CreateJLabelItem (String name){
        JLabel label=new JLabel();
        label.setText(name);
        label.setFont(new Font("Times New Roman",Font.BOLD,20));
        return label;
    }

    private JTextField CreateJTextField (){
        JTextField txt=new JTextField();
        txt.setPreferredSize(new Dimension(300,30));
        txt.setFont(new Font("Times New Roman",Font.BOLD,15));
        txt.setBackground(Color.WHITE);
        return txt;
    }

    private JButton createItemButton(String linkicon, String namebutton){
        // TẠO LABEL CHỨA ICON
        ImageIcon original_icon=new ImageIcon(linkicon);
        Image scaledImage_icon=resizeImage(original_icon.getImage(), 30, 30);
        ImageIcon scaledIcon_icon = new ImageIcon(scaledImage_icon);
        JLabel icon=new JLabel(scaledIcon_icon);
        //TẠO LABEL CHỨA TÊN
        JLabel name =new JLabel(namebutton);
        name .setFont(new Font("Times New Roman", Font.BOLD, 20));
        name .setForeground(Color.BLACK);

        JButton button=new JButton();
        button.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        button.setBackground(new Color(0xB6F2E6));
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusPainted(false);
        button.add(icon);
        button.add(name);

        return button;
    }
    // Phương thức để thay đổi kích thước icon
    private static Image resizeImage(Image originalImage, int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return bufferedImage;
    }

    final Color colorPanel = new Color(0xFBF1F1);
    JTable tblPhieuMuon, tblSachMuon;
    DefaultTableModel dtmPhieuMuon, dtmSachMuon;

    JTextField txtMaPhieuMuon,txtDocGia, txtNgayMuon, txtNgayTra, txtTenDocGia,txtSDTDocGia,txtTenNhanVien,txtMaNhanVien ,txtTimKiem, txtMaSach, txtTenSach, txtThanhTien,txtTongTien;

    JButton btnThem, btnXoa, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnDocGia, btnSach,btnClose;
    public void addControlsPhieuMuon(){
        this.setPreferredSize(new Dimension(1290,740));
        this.setLayout(new BorderLayout());
        //=================================JPANEL CHỨA THÔNG TIN ĐỌC GIẢ=================================//
        JPanel first_pn=new JPanel();
        first_pn.setLayout(null);
        first_pn.setBackground(colorPanel);
        //Title
        JLabel lb_title=new JLabel("PHIẾU MƯỢN");
        lb_title.setFont(new Font("Times New Roman",Font.BOLD,25));
        lb_title.setBounds(200,0,200,50);
        ImageIcon imgiconclose=new ImageIcon("image/img_qltv/close_btn.png");
        Image closescaledImage = imgiconclose.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Kích thước 50x50
        ImageIcon closescaledIcon = new ImageIcon(closescaledImage);
        btnClose=new JButton(closescaledIcon);
        btnClose.setBounds(1210,5,40,40);
        btnClose.setBackground(colorPanel);
        btnClose.setBorderPainted(false); // Tắt border được vẽ
        btnClose.setBorder(null); // Set border là null (không viền)

        //TXT VÀ JTEXTFIELD
        JLabel lb_MaPhieuMuon, lb_DocGia, lb_NgayMuon, lbNgayTra, lbTenDocGia,lbSDTDocGia, lbTenNhanVien,lbMaNhanVien,lbMaSach, lbTenSach, lbThanhTien, lbTitleChiTietPMuon,lblTongTien;
        lb_MaPhieuMuon=CreateJLabelItem("ID: ");
        lb_MaPhieuMuon.setBounds(20,50,100,30);
        txtMaPhieuMuon=CreateJTextField();
        txtMaPhieuMuon.setBounds(150,50,300,30);
        txtMaPhieuMuon.setEnabled(false);

        lb_NgayMuon =CreateJLabelItem("Ngày Mượn: ");
        lb_NgayMuon.setBounds(20,90,150,30);
        txtNgayMuon=CreateJTextField();
        txtNgayMuon.setBounds(150,90,300,30);
        txtNgayMuon.setEnabled(false);

        lbNgayTra=CreateJLabelItem("Ngày Trả: ");
        lbNgayTra.setBounds(20,130,100,30);
        txtNgayTra=CreateJTextField();
        txtNgayTra.setBounds(150,130,300,30);
        txtNgayTra.setEnabled(false);

        lb_DocGia=CreateJLabelItem("Thẻ: ");
        lb_DocGia.setBounds(20,170,100,30);
        txtDocGia=CreateJTextField();
        txtDocGia.setEnabled(false);
        txtDocGia.setBounds(150,170,300,30);
        btnDocGia=new JButton("...");
        btnDocGia.setPreferredSize(new Dimension(30,30));
        btnDocGia.setBounds(460,170,30,30);

        lbTenDocGia=CreateJLabelItem("Tên: ");
        lbTenDocGia.setBounds(20,210,100,30);
        txtTenDocGia=CreateJTextField();
        txtTenDocGia.setBounds(150,210,300,30);
        txtTenDocGia.setEnabled(false);

        lblTongTien=CreateJLabelItem("Tổng Tiền: ");
        lblTongTien.setBounds(20,250,100,30);
        txtTongTien=CreateJTextField();
        txtTongTien.setBounds(150,250,300,30);
        txtTongTien.setEnabled(false);

        lbTenNhanVien=CreateJLabelItem("Tên NV: ");
        lbTenNhanVien.setBounds(600,50,100,30);
        txtTenNhanVien=CreateJTextField();
        txtTenNhanVien.setText(tenNhanVien);
        txtTenNhanVien.setBounds(700,50,300,30);
        txtTenNhanVien.setEnabled(false);

        lbMaNhanVien=CreateJLabelItem("Mã NV: ");
        lbMaNhanVien.setBounds(600,90,100,30);
        txtMaNhanVien=CreateJTextField();
        txtMaNhanVien.setText(maNhanVien);
        txtMaNhanVien.setBounds(700,90,300,30);
        txtMaNhanVien.setEnabled(false);
        //=========JPANEL CHI TIẾT PHIẾU MƯỢN=======//
        lbTitleChiTietPMuon=new JLabel("CHI TIẾT PHIẾU MƯỢN");
        lbTitleChiTietPMuon.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbTitleChiTietPMuon.setBounds(150,0,300,30);

        lbMaSach=CreateJLabelItem("ID Sách: ");
        lbMaSach.setBounds(4,30,100,30);
        txtMaSach=CreateJTextField();
        txtMaSach.setBounds(150,30,300,30);
        txtMaSach.setEnabled(false);
        btnSach=new JButton("...");
        btnSach.setPreferredSize(new Dimension(30,30));
        btnSach.setBounds(460,29,30,30);

        lbTenSach=CreateJLabelItem("Tên Sách: ");
        lbTenSach.setBounds(4,70,100,30);
        txtTenSach=CreateJTextField();
        txtTenSach.setBounds(150,70,300,30);
        txtTenSach.setEnabled(false);

        lbThanhTien=CreateJLabelItem("Thành Tiền: ");
        lbThanhTien.setBounds(4,110,130,30);
        txtThanhTien=CreateJTextField();
        txtThanhTien.setBounds(150,110,300,30);
        txtThanhTien.setEnabled(false);
        //BUTTON
        btnThemSach=new JButton("Thêm");
        btnThemSach.setFont(new Font("Arial",Font.BOLD,20));
        btnThemSach.setPreferredSize(new Dimension(100,40));
        btnThemSach.setBackground(new Color(0xE06576));
        btnThemSach.setBounds(500,50,100,40);
        btnXoaSach=new JButton("Xóa");
        btnXoaSach.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnXoaSach.setPreferredSize(new Dimension(100,40));
        btnXoaSach.setBackground(new Color(0xE06576));
        btnXoaSach.setBounds(500,100,100,40);

        JPanel pn_CTietPMuon=new JPanel();
        // Thêm border cho JPanel
        Border border = BorderFactory.createLineBorder(Color.black, 2); // Đường viền màu xanh dương, độ dày 3px
        pn_CTietPMuon.setBorder(border);
        pn_CTietPMuon.setPreferredSize(new Dimension(670,152));
        pn_CTietPMuon.setBackground(colorPanel);
        pn_CTietPMuon.setLayout(null);
        pn_CTietPMuon.setBounds(600,130,670,152);
        pn_CTietPMuon.add(lbTitleChiTietPMuon);
        pn_CTietPMuon.add(lbMaSach);
        pn_CTietPMuon.add(lbTenSach);
        pn_CTietPMuon.add(lbThanhTien);
        pn_CTietPMuon.add(txtMaSach);
        pn_CTietPMuon.add(txtTenSach);
        pn_CTietPMuon.add(txtThanhTien);
        pn_CTietPMuon.add(btnSach);
        pn_CTietPMuon.add(btnThemSach);
        pn_CTietPMuon.add(btnXoaSach);

        //=============TABLE CHI TIẾT PHIẾU MƯỢN VÀ KHU VỰC CÁC NÚT VÀ KHU VỰC TÌM KIẾM==========
        tblSachMuon=new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dtmSachMuon = new DefaultTableModel();
        dtmSachMuon.addColumn("Mã");
        dtmSachMuon.addColumn("Mã PS");
        dtmSachMuon.addColumn("Tên");
        dtmSachMuon.addColumn("Tiền Mượn");

        tblSachMuon = new JTable( dtmSachMuon);
        tblSachMuon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSachMuon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSachMuon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblSachMuon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSachMuon = tblSachMuon.getColumnModel();
        columnModelSachMuon.getColumn(0).setPreferredWidth(50);
        columnModelSachMuon.getColumn(1).setPreferredWidth(80);
        columnModelSachMuon.getColumn(2).setPreferredWidth(320);
        columnModelSachMuon.getColumn(3).setPreferredWidth(150);

        JScrollPane scrTblSachMuon = new JScrollPane(tblSachMuon);
        scrTblSachMuon.setPreferredSize(new Dimension(600,150));
        tblSachMuon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tblSachMuon.setRowHeight(30);
        JTableHeader header = tblSachMuon.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        tblSachMuon.setShowGrid(true);
        tblSachMuon.setGridColor(Color.BLACK);
        tblSachMuon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrTblSachMuon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblSachMuon.setDefaultEditor(Object.class, null);
        tblSachMuon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrTblSachMuon.setBounds(20,300,600,150);

        //KHU VỰC TÌM KIẾM
        JLabel lbTimKiem =new JLabel("Tìm Kiếm: ");
        lbTimKiem.setFont(new Font("Times New Roman",Font.BOLD,25));
        lbTimKiem.setBounds(630,300,200,30);
        txtTimKiem=new JTextField();
        txtTimKiem.setBackground(Color.WHITE);
        txtTimKiem.setFont(new Font("Times New Roman",Font.BOLD,15));
        txtTimKiem.setBounds(750,290,400,40);
        btnTim=new JButton("Tìm");
        btnTim.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnTim.setBackground(new Color(0xD4F7F0));
        btnTim.setBounds(1170,290,100,40);

        //BUTTON
        btnThem=createItemButton("image/img_qltv/icon_them.png","Thêm");
        btnThem.setBounds(630,340,140,40);
        btnReset=createItemButton("image/img_qltv/icon_reload.png","");
        btnReset.setBounds(800,340,50,40);
        btnReset.setBackground(new Color(0xE06576));

        btnXoa=createItemButton("image/img_qltv/icon_reload.png","Xóa");
        btnXoa.setBounds(630,400,140,40);
        btnInthe=createItemButton("image/img_qltv/icon_print.png","In Thẻ");
        btnInthe.setBounds(795,400,140,40);
        btnNhapExcel=createItemButton("image/img_qltv/icon_excel.png","Nhập");
        btnNhapExcel.setBounds(960,400,140,40);
        btnXuatExcel=createItemButton("image/img_qltv/icon_excel.png","Xuất");
        btnXuatExcel.setBounds(1125,400,140,40);

        //==========================JTABLE PHIẾU MƯỢN=================================
        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("ID");
        dtmPhieuMuon.addColumn("Đọc Giả");
        dtmPhieuMuon.addColumn("Nhân Viên");
        dtmPhieuMuon.addColumn("Ngày Mượn");
        dtmPhieuMuon.addColumn("Hạn Trả");
        dtmPhieuMuon.addColumn("Tổng Tiền");
        tblPhieuMuon = new JTable(dtmPhieuMuon);

        tblPhieuMuon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblPhieuMuon.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);


        TableColumnModel columnModelPhieuMuon = tblPhieuMuon.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(50);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(200);


        JScrollPane scrTblPhieuMuon = new JScrollPane(tblPhieuMuon);
        scrTblPhieuMuon.setPreferredSize(new Dimension(1250,200));
        scrTblPhieuMuon.setBounds(20,460,1250,200);
        tblPhieuMuon.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tblPhieuMuon.setRowHeight(30);
        JTableHeader headerpm = tblPhieuMuon.getTableHeader();
        headerpm.setFont(new Font("Times New Roman", Font.BOLD, 20));
        headerpm.setBackground(new Color(0xB6F2E6));
        headerpm.setForeground(Color.BLACK);
        tblPhieuMuon.setShowGrid(true);
        tblPhieuMuon.setGridColor(Color.BLACK);
        tblPhieuMuon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrTblPhieuMuon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblPhieuMuon.setDefaultEditor(Object.class, null);
        tblPhieuMuon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        first_pn.add(lb_title);
        first_pn.add(btnClose);
        first_pn.add(lb_MaPhieuMuon);
        first_pn.add(txtMaPhieuMuon);
        first_pn.add(lb_NgayMuon);
        first_pn.add(txtNgayMuon);
        first_pn.add(lbNgayTra);
        first_pn.add(txtNgayTra);
        first_pn.add(lb_DocGia);
        first_pn.add(txtDocGia);
        first_pn.add(lbTenDocGia);
        first_pn.add(btnDocGia);
        first_pn.add(txtTenDocGia);
        first_pn.add(lblTongTien);
        first_pn.add(txtTongTien);
        first_pn.add(lbTenNhanVien);
        first_pn.add(txtTenNhanVien);
        first_pn.add(lbMaNhanVien);
        first_pn.add(txtMaNhanVien);
        first_pn.add(pn_CTietPMuon);
        first_pn.add(scrTblSachMuon);
        first_pn.add(lbTimKiem);
        first_pn.add(txtTimKiem);
        first_pn.add(btnTim);
        first_pn.add(btnThem);
        first_pn.add(btnReset);
        first_pn.add(btnXoa);
        first_pn.add(btnInthe);
        first_pn.add(btnNhapExcel);
        first_pn.add(btnXuatExcel);
        first_pn.add(scrTblPhieuMuon);

        //====================Bảng phiếu mượn====================
        loadDataLenBangPhieuMuon();
        this.add(first_pn,BorderLayout.CENTER);


    }
    public JButton getCloseButtonPhieuMuon() {
        return btnClose;
    }

    private void addEventsPhieuMuon(){

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangPhieuMuon();
                txtTenDocGia.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                txtTimKiem.setText("");
                txtTenNhanVien.setText(tenNhanVien);
                txtMaNhanVien.setText(maNhanVien);
                loadDataLenBangCTPhieuMuon("0");
            }
        });
        tblPhieuMuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblPhieuMuon();
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
                loadDataLenBangPhieuMuon();
                txtTenNhanVien.setText(tenNhanVien);
                txtMaNhanVien.setText(maNhanVien);
                txtTenDocGia.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                txtTimKiem.setText("");
                loadDataLenBangCTPhieuMuon("0");
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaPhieuMuon();
                loadDataLenBangPhieuMuon();
                txtTenNhanVien.setText(tenNhanVien);
                txtMaNhanVien.setText(maNhanVien);
                txtTenDocGia.setText("");
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
                loadDataLenBangCTPhieuMuon("0");
                txtTenNhanVien.setText(tenNhanVien);
                txtMaNhanVien.setText(maNhanVien);
                txtTenDocGia.setText("");
                txtMaPhieuMuon.setText("");
                txtDocGia.setText("");
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                txtTongTien.setText("");
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
                txtTimKiem.setText("");
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
            PhieuMuon phieuMuon=pmBUS.getPhieuMuon(maPhieuMuon.trim());
            txtDocGia.setText(phieuMuon.getMaDocGia()+"");
            txtMaNhanVien.setText(phieuMuon.getMaNhanVien()+"");
            txtMaPhieuMuon.setText(maPhieuMuon);
            txtTenNhanVien.setText(nhanVien);
            txtTenDocGia.setText(docGia);
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
            String maPhanSach =""+tblSachMuon.getValueAt(row,1);
            String tenSach=tblSachMuon.getValueAt(row,2)+"";
            String tongTien =tblSachMuon.getValueAt(row,3)+"";

            txtMaSach.setText(maSach+"|"+maPhanSach);
            txtTenSach.setText(tenSach);
            txtThanhTien.setText(tongTien);
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
            vec.add(ctpm.getMaPhanSach());
            String tenSach = sachBUS.getTenSach(ctpm.getMaSach());
            vec.add(tenSach);
            vec.add(dcf.format(ctpm.getGiaTien()));
            dtmSachMuon.addRow(vec);
        }
    }

    private void xuLyThemPhieuMuon(){
        boolean flag = pmBUS.themPhieuMuon(txtMaPhieuMuon.getText(),
                txtTenDocGia.getText(), String.valueOf(dangNhapGUI.maTaiKhoan()),
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
        }
    }

    private void xuLyTimKiem(){
        String docGia = txtTimKiem.getText();
        int maDocGia = docGiaBUS.getMaDocGia(docGia.trim());
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
            txtTenDocGia.setText(timDocGiaGUI.docGiaTimDuoc.getHo() + " " + timDocGiaGUI.docGiaTimDuoc.getTen());
            txtDocGia.setText(timDocGiaGUI.docGiaTimDuoc.getMaDocGia()+"");
        }
    }

    private void xuLyTimSach(){
        timSachGUI = new DlgTimSach();
        timSachGUI.setVisible(true);
        if(timSachGUI.sachTimDuoc != null){
            txtMaSach.setText(timSachGUI.sachTimDuoc.getMaSach()+" | "+timSachGUI.sachTimDuoc.getMaPhanSach());
            txtTenSach.setText(sachBUS.getTenSach(timSachGUI.sachTimDuoc.getMaSach()));
            Sach sach = sachBUS.getSach(String.valueOf(timSachGUI.sachTimDuoc.getMaSach()));
            long tien = (sach.getGiaSach() * 10)/100 ;
            txtThanhTien.setText(tien+"");
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

    private void btnThemSachAction(String maSach,String maPhanSach, String tenSach, String thanhTien) {
        Vector<Object> rowData = new Vector<>();
        rowData.add(maSach);
        rowData.add(maPhanSach);
        rowData.add(tenSach);
        rowData.add(thanhTien);
        dtmSachMuon.addRow(rowData);
    }

    private void xuLyThemCTPhieuMuon() {
        if (txtDocGia.getText().equals("")) {
            new MyDialog("Chưa chọn đọc giả mượn sách!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        if (txtMaSach.getText().equals("")) {
            new MyDialog("Chưa chọn sách mượn!!!", MyDialog.ERROR_DIALOG);
            return;
        } else {
            int rowCount = dtmSachMuon.getRowCount();
            if (rowCount == 4) {
                new MyDialog("Số lượng sách mượn đã đạt tối đa!!!", MyDialog.ERROR_DIALOG);
                return;
            }
            for (int i = 0; i < rowCount; i++) {
                String maSach = String.valueOf(dtmSachMuon.getValueAt(i, 0));
                String maPhanSach = String.valueOf(dtmSachMuon.getValueAt(i, 1));
                String tenSach = String.valueOf(dtmSachMuon.getValueAt(i, 2));
                String thanhTien = String.valueOf(dtmSachMuon.getValueAt(i, 3));
            }
            String[] parts = txtMaSach.getText().split("\\|");
            String part1 = parts[0].trim();
            String part2 = parts[1].trim();
            String maSach = part1;
            String maPhanSach = part2;
            String tenSach = txtTenSach.getText();
            String thanhTien = txtThanhTien.getText();

            btnThemSachAction(maSach,maPhanSach, tenSach, thanhTien);

            ctPhieuMuonBUS.chonSachMuon(maSach);
            timSachGUI = new DlgTimSach();
            timSachGUI.loadDataLenTable(); // Load lại dữ liệu vào bảng

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
            String maPhanSach = (String) dtmSachMuon.getValueAt(i, 1);
            String tenSach = (String) dtmSachMuon.getValueAt(i, 2);
            String thanhTien = (String) dtmSachMuon.getValueAt(i, 3);

            // Gọi phương thức để xử lý chi tiết phiếu mượn với từng hàng dữ liệu từ bảng
            boolean flag = ctPhieuMuonBUS.themCTPhieuMuon(ma, maSach, maPhanSach, thanhTien);
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
                sachBUS.capNhatTrangThaiSach(txtMaSach.getText());
                txtMaSach.setText("");
                txtTenSach.setText("");
                txtThanhTien.setText("");
            }
        }
        timSachGUI = new DlgTimSach();
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
            sachBUS.capNhatTrangThaiSach(maS);
        }
        return flag;
    }

    private void tinhTien(DefaultTableModel dtmSachMuon){
        int rowCount = dtmSachMuon.getRowCount();
        long tien = 0;
        for (int i = 0; i < rowCount; i++) {
            String thanhTien = (String) dtmSachMuon.getValueAt(i, 3);
            tien = tien + Long.parseLong(thanhTien.trim());
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
            vec.add(tblSachMuon.getValueAt(i,3));
            dsPhieuMuon.add(vec);
        }
        int maPM = Integer.parseInt(txtMaPhieuMuon.getText());
        long tien = Long.parseLong(txtTongTien.getText());
        XuatPhieuMuonGUI phieuMuonGUI = new XuatPhieuMuonGUI(dsPhieuMuon,maPM,
                txtDocGia.getText(),nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()),txtNgayMuon.getText(),txtNgayTra.getText(),tien);
        phieuMuonGUI.setVisible(true);
    }


}

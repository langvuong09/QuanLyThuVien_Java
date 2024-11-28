package QuanLyThuVien.GUI;


import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.CTPhieuMuon;
import QuanLyThuVien.DTO.CTPhieuTra;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.DTO.PhieuTra;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class PnQuanLyPhieuTraGUI extends JPanel {

    private DlgTimPhieuMuon timPhieuMuonGUI = new DlgTimPhieuMuon();
    private DlgTimSachMuon timSachMuonGUI = new DlgTimSachMuon();
    private SachBUS sachBUS = new SachBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    private CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();

    PhieuTraBUS ptBUS = new PhieuTraBUS();
    CTPhieuTraBUS ctPhieuTraBUS = new CTPhieuTraBUS();

    ArrayList<CTPhieuTra> danhSachctpt = new ArrayList<>();
    public PnQuanLyPhieuTraGUI(){
        addConTrolsPhieuTra();
        addEventsPhieuTra();
    }


    final Color colorPanel = new Color(247, 247, 247);

    JTable tblPhieuTra, tblSachTra;
    DefaultTableModel dtmPhieuTra, dtmSachTra;

    JTextField txtMaPhieuTra,txtMaPhieuMuon ,txtDocGia, txtNgayMuon, txtNgayTraThuc, txtTimKiem;
    JDateChooser dateBD, dateKT;
    JButton btnThem, btnXoa, btnInthe, btnReset, btnXuatExcel, btnNhapExcel, btnTim, btnThemSach, btnXoaSach, btnPhieuMuon, btnTimTrongKhoang,btnClose;
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
        button.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
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
    private void addConTrolsPhieuTra() {
        this.setPreferredSize(new Dimension(1290,740));
        this.setLayout(new BorderLayout());
        //=================================JPANEL CHỨA THÔNG TIN ĐỌC GIẢ=================================//
        JPanel first_pn=new JPanel();
        first_pn.setLayout(null);
        first_pn.setBackground(colorPanel);

        //Title
        JLabel lb_title=new JLabel("PHIẾU TRẢ");
        lb_title.setFont(new Font("Arial",Font.BOLD,25));
        lb_title.setBounds(200,0,200,50);
        ImageIcon imgiconclose=new ImageIcon("image/img_qltv/close_btn.png");
        Image closescaledImage = imgiconclose.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Kích thước 50x50
        ImageIcon closescaledIcon = new ImageIcon(closescaledImage);
        btnClose=new JButton(closescaledIcon);
        btnClose.setBounds(1210,5,40,40);
        btnClose.setBackground(colorPanel);
        btnClose.setBorderPainted(false);
        btnClose.setBorder(null);

        //TXT VÀ JTEXTFIELD
        JLabel lb_MaPhieuTra,lb_MaPhieuMuon,lb_DocGia,lb_NgayMuon,lb_NgayTra;
        lb_MaPhieuTra=CreateJLabelItem("Mã Phiếu Trả: ");
        lb_MaPhieuTra.setBounds(20,50,150,30);
        txtMaPhieuTra=CreateJTextField();
        txtMaPhieuTra.setBounds(180,50,300,30);
        txtMaPhieuTra.setEnabled(false);

        lb_MaPhieuMuon =CreateJLabelItem("Mã Phiếu Mượn: ");
        lb_MaPhieuMuon.setBounds(20,90,200,30);
        txtMaPhieuMuon=CreateJTextField();
        txtMaPhieuMuon.setBounds(180,90,300,30);
        txtMaPhieuMuon.setEnabled(false);

        btnPhieuMuon=new JButton("...");
        btnPhieuMuon.setPreferredSize(new Dimension(30,30));
        btnPhieuMuon.setBounds(480,90,30,30);
        btnPhieuMuon.setFont(new Font("Times New Roman",Font.BOLD,20));

        lb_DocGia=CreateJLabelItem("Đọc Gỉa: ");
        lb_DocGia.setBounds(20,130,100,30);
        txtDocGia=CreateJTextField();
        txtDocGia.setBounds(180,130,300,30);
        txtDocGia.setEnabled(false);

        lb_NgayMuon=CreateJLabelItem("Ngày Mượn: ");
        lb_NgayMuon.setBounds(20,170,150,30);
        txtNgayMuon=CreateJTextField();
        txtNgayMuon.setBounds(180,170,300,30);
        txtNgayMuon.setEnabled(false);

        lb_NgayTra=CreateJLabelItem("Ngày Trả: ");
        lb_NgayTra.setBounds(20,210,100,30);
        txtNgayTraThuc=CreateJTextField();
        txtNgayTraThuc.setBounds(180,210,300,30);
        txtNgayTraThuc.setEnabled(false);

        //JTABLE CHI TIẾT PHIẾU TRẢ
        tblSachTra=new JTable();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dtmSachTra = new DefaultTableModel();
        dtmSachTra.addColumn("Mã");
        dtmSachTra.addColumn("Mã PS");
        dtmSachTra.addColumn("Tên");
        dtmSachTra.addColumn("Status");

        tblSachTra = new JTable( dtmSachTra);
        tblSachTra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSachTra.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSachTra.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblSachTra.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSachMuon = tblSachTra.getColumnModel();
        columnModelSachMuon.getColumn(0).setPreferredWidth(80);
        columnModelSachMuon.getColumn(1).setPreferredWidth(100);
        columnModelSachMuon.getColumn(2).setPreferredWidth(400);
        columnModelSachMuon.getColumn(3).setPreferredWidth(180);

        JScrollPane scrTblSachTra = new JScrollPane(tblSachTra);
        scrTblSachTra.setPreferredSize(new Dimension(760,150));
        tblSachTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tblSachTra.setRowHeight(30);
        JTableHeader header = tblSachTra.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        tblSachTra.setShowGrid(true);
        tblSachTra.setGridColor(Color.BLACK);
        tblSachTra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrTblSachTra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblSachTra.setDefaultEditor(Object.class, null);
        tblSachTra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrTblSachTra.setBounds(520,50,740,150);

        //JBUTTON XÓA SÁCH VÀ BỎ SÁCH
        btnThemSach=new JButton("Thêm Sách");
        btnThemSach.setBackground(new Color(0XE06567));
        btnThemSach.setBounds(720,210,150,40);
        btnThemSach.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnXoaSach=new JButton("Xóa Sách");
        btnXoaSach.setBackground(new Color(0XE06567));
        btnXoaSach.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnXoaSach.setBounds(920,210,150,40);

        //KHU VỰC TÌM KIẾM
        txtTimKiem=CreateJTextField();
        txtTimKiem.setBounds(180,265,720,40);
        btnTim=createItemButton("image/img_qltv/icon_search.png","Tìm Kiếm");
        btnTim.setBounds(920,265,200,40);

        //KHU VỰC LỌC
        JLabel lb_NgayBD=new JLabel("Ngày Bắt Đầu: ");
        lb_NgayBD.setFont(new Font("Times New Roman",Font.BOLD,20));
        lb_NgayBD.setBounds(180,310,200,40);
        JLabel lb_NgayKT=new JLabel("Ngày Kết Thúc: ");
        lb_NgayKT.setBounds(550,310,200,40);
        lb_NgayKT.setFont(new Font("Times New Roman",Font.BOLD,20));
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");
        dateBD.getCalendarButton().setIcon(new ImageIcon("image/img_qltv/icon_lich.png"));
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());
        dateBD.setFont(new Font("Times New Roman",Font.BOLD,20));
        dateKT.setFont(new Font("Times New Roman",Font.BOLD,20));
        dateBD.setBounds(320,310,200,40);
        dateKT.setBounds(700,310,200,40);
        btnTimTrongKhoang=createItemButton("image/img_qltv/icon_dgiachuatrasach.png","Submit");
        btnTimTrongKhoang.setBounds(920,310,200,40);
        //JBUTTON
        JPanel pn_button=new JPanel(new FlowLayout(FlowLayout.CENTER,25,5));
        pn_button.setBackground(colorPanel);
        pn_button.setBounds(20,370,1250,50);
        btnThem=createItemButton("image/img_qltv/icon_them.png","Thêm");
        btnXoa=createItemButton("image/img_qltv/icon_xoa.png","Xóa");
        btnInthe=createItemButton("image/img_qltv/icon_print.png","In Thẻ");
        btnNhapExcel=createItemButton("image/img_qltv/icon_excel.png","Nhập");
        btnXuatExcel=createItemButton("image/img_qltv/icon_excel.png","Xuất");
        btnReset=createItemButton("image/img_qltv/icon_reload.png","");
        btnReset.setBackground(new Color(0xE06567));
        btnReset.setPreferredSize(new Dimension(68,40));
        pn_button.add(btnThem);
        pn_button.add(btnXoa);
        pn_button.add(btnInthe);
        pn_button.add(btnNhapExcel);
        pn_button.add(btnXuatExcel);
        pn_button.add(btnReset);

        //JTABLE PHIẾU TRẢ
        dtmPhieuTra = new DefaultTableModel();
        dtmPhieuTra.addColumn("Mã PT");
        dtmPhieuTra.addColumn("Mã PM");
        dtmPhieuTra.addColumn("Đọc Gỉa");
        dtmPhieuTra.addColumn("Nhân Viên");
        dtmPhieuTra.addColumn("Ngày Mượn");
        dtmPhieuTra.addColumn("Ngày Trả");
        tblPhieuTra = new JTable(dtmPhieuTra);

        tblPhieuTra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblPhieuTra.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);


        TableColumnModel columnModelPhieuMuon = tblPhieuTra.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(50);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(50);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(200);


        JScrollPane scrTblPhieuTra = new JScrollPane(tblPhieuTra);
        scrTblPhieuTra.setPreferredSize(new Dimension(1250,200));
        scrTblPhieuTra.setBounds(20,450,1230,200);
        tblPhieuTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tblPhieuTra.setRowHeight(30);
        JTableHeader headerpm = tblPhieuTra.getTableHeader();
        headerpm.setFont(new Font("Times New Roman", Font.BOLD, 20));
        headerpm.setBackground(new Color(0x00D4A9));
        headerpm.setForeground(Color.BLACK);
        tblPhieuTra.setShowGrid(true);
        tblPhieuTra.setGridColor(Color.BLACK);
        tblPhieuTra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrTblPhieuTra.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblPhieuTra.setDefaultEditor(Object.class, null);
        tblPhieuTra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        first_pn.add(lb_title);
        first_pn.add(btnClose);
        first_pn.add(lb_MaPhieuTra);
        first_pn.add(txtMaPhieuTra);
        first_pn.add(lb_MaPhieuMuon);
        first_pn.add(txtMaPhieuMuon);
        first_pn.add(lb_DocGia);
        first_pn.add(txtDocGia);
        first_pn.add(lb_NgayMuon);
        first_pn.add(txtNgayMuon);
        first_pn.add(txtNgayTraThuc);
        first_pn.add(lb_NgayTra);
        first_pn.add(btnPhieuMuon);
        first_pn.add(scrTblSachTra);
        first_pn.add(btnThemSach);
        first_pn.add(btnXoaSach);
        first_pn.add(txtTimKiem);
        first_pn.add(btnTim);
        first_pn.add(dateBD);
        first_pn.add(dateKT);
        first_pn.add(lb_NgayBD);
        first_pn.add(lb_NgayKT);
        first_pn.add(btnTimTrongKhoang);
        first_pn.add(pn_button);
        first_pn.add(scrTblPhieuTra);
        loadDataLenBangPhieuTra();
        this.add(first_pn,BorderLayout.CENTER);


    }
    public JButton getCloseButtonPhieuTra() {
        return btnClose;
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
    if (pmBUS == null) {

        return; // Tránh gọi phương thức khi pmBUS là null
    }

    ptBUS.docListPhieuTra();
    ctPhieuTraBUS.docListCTPhieuTra();
    dtmPhieuTra.setRowCount(0);
    ArrayList<PhieuTra> dspt = ptBUS.getListPhieuTra();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    for (PhieuTra pt : dspt) {
        Vector vec = new Vector();
        vec.add(pt.getMaPhieuTra());
        vec.add(pt.getMaPhieuMuon());
        String tenDG = docGiaBUS.getTenDocGia(pt.getMaDocGia());
        vec.add(tenDG);
        String tenNV = nhanVienBUS.getTenNhanVien(pt.getMaNhanVien());
        vec.add(tenNV);

        String maPM = pt.getMaPhieuMuon() + "";

        // Kiểm tra pmBUS có null không và xử lý nếu cần
        PhieuMuon phieuMuon = pmBUS.getPhieuMuon(maPM.trim());
        if (phieuMuon != null) {
            vec.add(sdf.format(phieuMuon.getNgayMuon()));
        } else {
            vec.add("Ngày mượn không có");
        }

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
            vec.add(ctpt.getMaPhanSach());
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
            vec.add(ctpm.getMaPhanSach());
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
            String tt = dtmSachTra.getValueAt(row,3)+"";
            if(tt.equals("Đã trả")){
                new MyDialog("Sách đã được trả!!!", MyDialog.ERROR_DIALOG);
                return;
            }else {
                dtmSachTra.setValueAt("Đã trả",row,3);
                CTPhieuTra ctpt = new CTPhieuTra();
                ctpt.setMaPhieuTra(Integer.parseInt(txtMaPhieuTra.getText()));
                ctpt.setMaSach(Integer.parseInt(dtmSachTra.getValueAt(row,0)+""));
                ctpt.setMaPhanSach(Integer.parseInt(dtmSachTra.getValueAt(row,1)+""));
                danhSachctpt.add(ctpt);
            }
        }
    }

    private void xuLyThemCTPhieuTraTuArray(){
        for(CTPhieuTra ctpt : danhSachctpt){
            String maPT = String.valueOf(ctpt.getMaPhieuTra());
            String maSach = String.valueOf(ctpt.getMaSach());
            String maPhanSach = String.valueOf(ctpt.getMaPhanSach());
            boolean flag = ctPhieuTraBUS.themCTPhieuTra(maPT, maSach, maPhanSach);
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
            if(!ctPhieuTraBUS.xacDinhCTPT(dtmSachTra.getValueAt(row, 0) + "", dtmSachTra.getValueAt(row, 1) + "", txtMaPhieuMuon.getText())) {
                if ((dtmSachTra.getValueAt(row, 2) + "").equals("Mượn")) {
                    new MyDialog("Sách chưa được trả!!!", MyDialog.ERROR_DIALOG);
                    return;
                } else {
                    dtmSachTra.setValueAt("Mượn", row, 3);

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
            PhieuMuon pm=pmBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon()));
            vec.add(sdf.format(pm.getNgayMuon()));
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
            vec.add(tblSachTra.getValueAt(i,2));
            dspt.add(vec);
        }
        for(CTPhieuMuon ctPhieuMuon : ctPhieuMuonBUS.getListCTPhieuMuonTheoMaPM(txtMaPhieuMuon.getText())){
            Vector vect = new Vector<>();
            vect.add(ctPhieuMuon.getMaPhieuMuon());
            vect.add(ctPhieuMuon.getMaSach());
            vect.add(ctPhieuMuon.getMaPhanSach());
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

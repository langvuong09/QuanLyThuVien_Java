package QuanLyThuVien.GUI;


import MyCustom.MyDialog;
import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.DTO.DocGia;

import javax.swing.*;
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
import java.util.ArrayList;
import java.util.Vector;

import static Main.Main.changLNF;


public class PnQuanLyDocGiaGUI extends JPanel {
    public PnQuanLyDocGiaGUI() {
        addConTrolsDocGia();
        addEventsDocGia();
    }


    DocGiaBUS dgBUS = new DocGiaBUS();
    JTable tblDocGia;
    final Color colorPanel = new Color(0xFBF1F1);

    CardLayout cardDocGiaGroup = new CardLayout();

    JPanel pnCardTabDocGia;

    DefaultTableModel dtmDocGia;

    JTextField txtIDDocGia, txtTenDocGia,txtHoDocGia, txtSDT, txtTimKiem,txtGmail;


    JButton btnThem, btnXoa, btnSua, btnReset, btnTim, btnLoc;

    JLabel lblTabbedDocGia;

    JRadioButton rdbNam, rdbNu;

    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    //Phương thức tạo Button
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
    //Hàm add giao diện vào jpanel
    private void addConTrolsDocGia(){
        this.setPreferredSize(new Dimension(1290,740));
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        JLabel lblTitleDocGia=new JLabel("QUẢN LÍ ĐỌC GIẢ");
        lblTitleDocGia.setFont(new Font("Arial", Font.BOLD,30));
        lblTitleDocGia.setForeground(Color.BLACK);
        btnReset = new JButton(new ImageIcon(new ImageIcon("image/img_qltv/icon_reset.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        JPanel pnTitleDocGia = new JPanel();
        pnTitleDocGia.setBackground( colorPanel);
        pnTitleDocGia.add(lblTitleDocGia);
        pnTitleDocGia.add(btnReset);


        JPanel pn_body=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn_body.setPreferredSize(new Dimension(1290,720));
        pn_body.setBackground(colorPanel);
        //=============================PANEL FROM THÔNG TIN ĐỌC GIẢ===========================//

        JPanel pn_bodyinfo = new JPanel(new GridLayout(1, 2, 10, 0));
        pn_bodyinfo.setPreferredSize(new Dimension(1280,150));
        pn_bodyinfo.setBackground(colorPanel);

        // Left panel for reader information
        JPanel pnleft_infor_docgia = new JPanel(new GridBagLayout());
        pnleft_infor_docgia.setBackground(colorPanel);
        pnleft_infor_docgia.setPreferredSize(new Dimension(640, 150));
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(10, 5, 10, 0);
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMaDocGia = new JLabel("Mã Độc Giả:");
        lblMaDocGia.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblMaDocGia.setForeground(Color.BLACK);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        pnleft_infor_docgia.add(lblMaDocGia, gbcLeft);

        txtIDDocGia = new JTextField();
        txtIDDocGia.setEnabled(false);
        txtIDDocGia.setPreferredSize(new Dimension(20,30));
        gbcLeft.gridx = 1;
        gbcLeft.gridy = 0;
        pnleft_infor_docgia.add(txtIDDocGia, gbcLeft);

        JLabel lblHoDocGia = new JLabel("Họ Độc Giả:");
        lblHoDocGia.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblHoDocGia.setForeground(Color.BLACK);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        pnleft_infor_docgia.add(lblHoDocGia, gbcLeft);

        txtHoDocGia = new JTextField(20);
        txtHoDocGia.setPreferredSize(new Dimension(20,30));
        gbcLeft.gridx = 1;
        gbcLeft.gridy = 1;
        pnleft_infor_docgia.add(txtHoDocGia, gbcLeft);

        JLabel lblTenDocGia = new JLabel("Tên Độc Giả:");
        lblTenDocGia.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblTenDocGia.setForeground(Color.BLACK);
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        pnleft_infor_docgia.add(lblTenDocGia, gbcLeft);

        txtTenDocGia = new JTextField(20);
        txtTenDocGia.setPreferredSize(new Dimension(20,30));
        gbcLeft.gridx = 1;
        gbcLeft.gridy = 2;
        pnleft_infor_docgia.add(txtTenDocGia, gbcLeft);

        // Right panel for reader information
        JPanel pnright_infor_docgia = new JPanel(new GridBagLayout());
        pnright_infor_docgia.setPreferredSize(new Dimension(640, 150));
        GridBagConstraints gbcRight = new GridBagConstraints();
        pnright_infor_docgia.setBackground(colorPanel);
        gbcRight.insets = new Insets(10, 0, 10, 0);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        lblGioiTinh.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblGioiTinh.setForeground(Color.BLACK);
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        pnright_infor_docgia.add(lblGioiTinh, gbcRight);

        rdbNam = new JRadioButton("Nam");
        rdbNam.setFont(new Font("Times New Roman", Font.BOLD,20));
        rdbNam.setForeground(Color.BLACK);
        rdbNam.setBackground(colorPanel);
        rdbNu = new JRadioButton("Nữ");
        rdbNu.setFont(new Font("Times New Roman", Font.BOLD,20));
        rdbNu.setForeground(Color.BLACK);
        rdbNu.setBackground(colorPanel);
        ButtonGroup bgGioiTinh = new ButtonGroup();
        bgGioiTinh.add(rdbNam);
        bgGioiTinh.add(rdbNu);

        JPanel genderPanel = new JPanel();
        genderPanel.setPreferredSize(new Dimension(20,30));
        genderPanel.add(rdbNam);
        genderPanel.add(rdbNu);
        genderPanel.setBackground(colorPanel);

        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        pnright_infor_docgia.add(genderPanel, gbcRight);

        JLabel lblSDT = new JLabel("SĐT:");
        lblSDT.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblSDT.setForeground(Color.BLACK);
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        pnright_infor_docgia.add(lblSDT, gbcRight);

        txtSDT = new JTextField(20);
        txtSDT.setPreferredSize(new Dimension(20,30));
        gbcRight.gridx = 1;
        gbcRight.gridy = 1;
        pnright_infor_docgia.add(txtSDT, gbcRight);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD,20));
        lblEmail.setForeground(Color.BLACK);
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        pnright_infor_docgia.add(lblEmail, gbcRight);

        txtGmail = new JTextField(20);
        txtGmail.setPreferredSize(new Dimension(20,30));
        gbcRight.gridx = 1;
        gbcRight.gridy = 2;
        pnright_infor_docgia.add(txtGmail, gbcRight);

        pn_bodyinfo.add(pnleft_infor_docgia);
        pn_bodyinfo.add(pnright_infor_docgia);

        //========================JPANEL THANH TÌM KIẾM ===================//
        JPanel pn_search=new JPanel(new FlowLayout());
        pn_search.setPreferredSize(new Dimension(1280,60));
        pn_search.setBackground(colorPanel);

        JPanel pn_thanhtimkiem=new JPanel(new FlowLayout());
        pn_thanhtimkiem.setPreferredSize(new Dimension(800,45));
        pn_thanhtimkiem.setBackground(colorPanel);

        ImageIcon original_icon_search=new ImageIcon("image/img_qltv/icon_search.png");
        Image scaledImage_icon_search=resizeImage(original_icon_search.getImage(), 40, 40);
        ImageIcon scaledIcon_icon_search = new ImageIcon(scaledImage_icon_search);

        JLabel lbl_icon_search=new JLabel(scaledIcon_icon_search);
        txtTimKiem=new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(750,40));
        Border roundedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true), // Tạo đường viền đậm và tròn
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding cho văn bản cách xa đường viền
        );
        txtTimKiem.setBorder(roundedBorder);
        txtTimKiem.setBackground(Color.WHITE);
        pn_thanhtimkiem.add(lbl_icon_search);
        pn_thanhtimkiem.add(txtTimKiem);


        //Button tìm kiếm
        btnTim=new JButton("Tìm Kiếm");
        btnTim.setPreferredSize(new Dimension(150,50));
        btnTim.setBackground(new Color(0xB6F2E6));
        btnTim.setFont(new Font("Times New Roman",Font.BOLD,20));

        pn_search.add(pn_thanhtimkiem);
        pn_search.add(btnTim);

        //===================PANEL CÁC BUTTON==============================//
        JPanel pn_button=new JPanel(new FlowLayout());
        pn_button.setPreferredSize(new Dimension(1280,60));
        pn_button.setBackground(colorPanel);
        btnLoc = createItemButton("image/img_qltv/icon_dgiachuatrasach.png","Đọc Gỉa Chưa Trả Sách");
        btnSua =createItemButton("image/img_qltv/icon_sua.png","Sửa");
        btnXoa =createItemButton("image/img_qltv/icon_xoa.png","Xóa");
        btnThem =createItemButton("image/img_qltv/icon_them.png","Thêm");
        pn_button.add(btnThem);
        pn_button.add(btnXoa);
        pn_button.add(btnSua);
        pn_button.add(btnLoc);

        //==========================TABLE=============================//
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        dtmDocGia = new DefaultTableModel();
        dtmDocGia.addColumn("Mã");
        dtmDocGia.addColumn("Họ");
        dtmDocGia.addColumn("Tên");
        dtmDocGia.addColumn("SDT");
        dtmDocGia.addColumn("Giới tính");
        dtmDocGia.addColumn("Gmail");
        dtmDocGia.addColumn("SL sách mượn");
        tblDocGia = new JTable(dtmDocGia);

        tblDocGia.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TableColumnModel columnModelDocGia = tblDocGia.getColumnModel();
        columnModelDocGia.getColumn(0).setPreferredWidth(60);
        columnModelDocGia.getColumn(1).setPreferredWidth(110);
        columnModelDocGia.getColumn(2).setPreferredWidth(70);
        columnModelDocGia.getColumn(3).setPreferredWidth(100);
        columnModelDocGia.getColumn(4).setPreferredWidth(90);
        columnModelDocGia.getColumn(5).setPreferredWidth(180);
        columnModelDocGia.getColumn(6).setPreferredWidth(140);

        JScrollPane scrTblDocGia = new JScrollPane(tblDocGia);
        scrTblDocGia.setPreferredSize(new Dimension(1250,300));


        tblDocGia.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tblDocGia.setRowHeight(30);
        JTableHeader header = tblDocGia.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));
        header.setBackground(new Color(0xB6F2E6));
        header.setForeground(Color.BLACK);
        tblDocGia.setShowGrid(true);
        tblDocGia.setGridColor(Color.BLACK);
        tblDocGia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrTblDocGia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tblDocGia.setDefaultEditor(Object.class, null);
        tblDocGia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pn_body.add(pn_bodyinfo);
        pn_body.add(pn_search);
        pn_body.add(pn_button);
        pn_body.add(scrTblDocGia);
        loadDataLenTableDocGia();


        //JPANEL CHÍNH
        this.add(pnTitleDocGia,BorderLayout.NORTH);
        this.add(pn_body, BorderLayout.CENTER);

    }


    private void addEventsDocGia(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIDDocGia.setText("");
                txtHoDocGia.setText("");
                txtTenDocGia.setText("");
                txtGmail.setText("");
                txtSDT.setText("");
                ButtonGroup group = new ButtonGroup();
                group.add(rdbNam);
                group.add(rdbNu);
                group.clearSelection();
                loadDataLenTableDocGia();
            }
        });
        tblDocGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblDocGia();
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaDocGia();
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemDocGia();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaDocGia();
            }
        });
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiem();
            }
        });
        btnLoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyLocDocGiaConMuonSach();
            }
        });
    }

    private void loadDataLenTableDocGia(){
        dtmDocGia.setRowCount(0);
        dgBUS.docDanhSach();
        ArrayList<DocGia> dsdg = dgBUS.getListDocGia();
        for(DocGia dg : dsdg){
            Vector vec = new Vector<>();
            vec.add(dg.getMaDocGia());
            vec.add(dg.getHo());
            vec.add(dg.getTen());
            vec.add(dg.getSDT());
            vec.add(dg.getGioiTinh());
            vec.add(dg.getGmail());
            dtmDocGia.addRow(vec);
        }
    }

    private void xuLyClickTblDocGia(){
        int row = tblDocGia.getSelectedRow();
        if(row > -1){
            String maDG = tblDocGia.getValueAt(row,0)+"";
            String gioi = tblDocGia.getValueAt(row,4)+"";
            txtIDDocGia.setText(maDG);
            txtHoDocGia.setText(tblDocGia.getValueAt(row,1)+"");
            txtTenDocGia.setText(tblDocGia.getValueAt(row,2)+"");
            txtSDT.setText(tblDocGia.getValueAt(row,3)+"");
            txtGmail.setText(tblDocGia.getValueAt(row,5)+"");

            if(gioi.equals("Nam")){
                rdbNam.setSelected(true);
            }else {
                rdbNu.setSelected(true);
            }
        }
    }

    private void xuLyThemDocGia(){
        for(int i =0;i<= tblDocGia.getSelectedRow();i++) {
            if (txtIDDocGia.getText().equals(dtmDocGia.getValueAt(i, 0).toString())) {
                new MyDialog("Đọc giả đã tồn tại!!!", MyDialog.ERROR_DIALOG);
                return;
            }
        }
        String gioi = "";
        if(rdbNam.isSelected()){
            gioi = "Nam";
        }else if(rdbNu.isSelected()){
            gioi = "Nữ";
        }else {
            gioi = "";
        }
        boolean flag = dgBUS.themDocGia(txtHoDocGia.getText(),txtTenDocGia.getText(),gioi,
                txtSDT.getText(),txtGmail.getText());
        if(flag){
            txtIDDocGia.setText("");
            txtHoDocGia.setText("");
            txtTenDocGia.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            ButtonGroup group = new ButtonGroup();
            group.add(rdbNam);
            group.add(rdbNu);
            group.clearSelection();
        }
        loadDataLenTableDocGia();
    }

    private void xuLyXoaDocGia(){
        boolean flag = dgBUS.xoaDocGia(txtIDDocGia.getText());
        loadDataLenTableDocGia();
    }

    private void xuLySuaDocGia(){
        String gioi = "";
        if(rdbNam.isSelected()){
            gioi = "Nam";
        }else {
            gioi = "Nữ";
        }
        boolean flag = dgBUS.suaDocGia(txtIDDocGia.getText(),txtHoDocGia.getText(),txtTenDocGia.getText(),gioi,
                txtSDT.getText(),txtGmail.getText());
        if(flag){
            txtIDDocGia.setText("");
            txtHoDocGia.setText("");
            txtTenDocGia.setText("");
            txtGmail.setText("");
            txtSDT.setText("");
            ButtonGroup group = new ButtonGroup();
            group.add(rdbNam);
            group.add(rdbNu);
            group.clearSelection();
        }
        loadDataLenTableDocGia();
    }

    private void xuLyTimKiem(){
        dtmDocGia.setRowCount(0);
        ArrayList<DocGia> dsdg = null;
        dsdg = dgBUS.timDocGia(txtTimKiem.getText());
        for(DocGia dg : dsdg){
            Vector vec = new Vector<>();
            vec.add(dg.getMaDocGia());
            vec.add(dg.getHo());
            vec.add(dg.getTen());
            vec.add(dg.getSDT());
            vec.add(dg.getGioiTinh());
            vec.add(dg.getGmail());
            dtmDocGia.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dsdg.size(), MyDialog.INFO_DIALOG);
    }

    public void xuLyLocDocGiaConMuonSach(){
        dtmDocGia.setRowCount(0);
        dgBUS.docDanhSach();
        ArrayList<DocGia> dsdg = dgBUS.getListDocGia();
        for(DocGia dg : dsdg){
            Vector vec = new Vector<>();
            vec.add(dg.getMaDocGia());
            vec.add(dg.getHo());
            vec.add(dg.getTen());
            vec.add(dg.getSDT());
            vec.add(dg.getGioiTinh());
            vec.add(dg.getGmail());
            vec.add(dgBUS.locDocGia(String.valueOf(dg.getMaDocGia())));
            if(dgBUS.locDocGia(String.valueOf(dg.getMaDocGia())) != 0)
                dtmDocGia.addRow(vec);
        }
    }



}

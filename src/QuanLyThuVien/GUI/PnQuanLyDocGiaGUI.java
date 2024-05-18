package QuanLyThuVien.GUI;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;
import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DTO.Sach;

import javax.swing.*;
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
    JTextField txtIDDocGia, txtTenDocGia,txtHoDocGia, txtSDT, txtTimKiem;
    JTextArea txtGmail;
    JButton btnThem, btnXoa, btnSua, btnReset, btnTim, btnLoc;
    JLabel lblTabbedDocGia;
    JRadioButton rdbNam, rdbNu;
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
                                    PANEL ĐỌC GIẢ
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
        txtGmail = new JTextArea();
        txtSDT = new JTextField();
        txtTimKiem = new JTextField();
        rdbNam = new JRadioButton("Nam");
        rdbNu = new JRadioButton("Nữ");
        txtIDDocGia.setText("");
        txtHoDocGia.setText("");
        txtTenDocGia.setText("");
        txtGmail.setText("");
        txtSDT.setText("");

        //=================Thông tin đọc giả==============

        JPanel pnThongTinDocGia = new TransparentPanel();
        pnThongTinDocGia.setLayout(null);

        JLabel lblMaDocGia = new JLabel("Mã đọc giả:");
        lblMaDocGia.setFont(font);
        txtIDDocGia.setFont(font);
        txtIDDocGia.setEditable(false);
        lblMaDocGia.setBounds(20,40,150,25);
        txtIDDocGia.setBounds(170,40,200,25);

        JLabel lblHoDocGia = new JLabel("Họ đọc giả:");
        lblHoDocGia.setFont(font);
        txtHoDocGia.setFont(font);
        lblHoDocGia.setBounds(20,90,150,25);
        txtHoDocGia.setBounds(170,90,200,25);

        JLabel lblTenDocGia = new JLabel("Tên đọc giả:");
        lblTenDocGia.setFont(font);
        txtTenDocGia.setFont(font);
        lblTenDocGia.setBounds(20,140,150,25);
        txtTenDocGia.setBounds(170,140,200,25);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(font);
        rdbNam.setFont(font);
        rdbNu.setFont(font);
        ButtonGroup group = new ButtonGroup();
        group.add(rdbNam);
        group.add(rdbNu);
        rdbNam.setBackground(colorPanel);
        rdbNu.setBackground(colorPanel);
        lblGioiTinh.setBounds(440,40,150,25);
        rdbNam.setBounds(540,40,70,25);
        rdbNu.setBounds(630,40,70,25);

        JLabel lblSDT = new JLabel("SDT:");
        lblSDT.setFont(font);
        txtSDT.setFont(font);
        lblSDT.setBounds(440,90,150,25);
        txtSDT.setBounds(520,90,200,25);

        JLabel lblGmail = new JLabel("Gmail:");
        lblGmail.setFont(font);
        txtGmail.setFont(font);
        txtGmail.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtGmail);
        lblGmail.setBounds(440,140,150,25);
        scrollPane.setBounds(520,140,200,50);

        JLabel lblTimKiem = new JLabel("Tên đọc giả cần tìm:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(120,230,300,25);
        txtTimKiem.setBounds(320,230,300,25);

        pnThongTinDocGia.add(lblMaDocGia);
        pnThongTinDocGia.add(txtIDDocGia);
        pnThongTinDocGia.add(lblHoDocGia);
        pnThongTinDocGia.add(txtHoDocGia);
        pnThongTinDocGia.add(lblTenDocGia);
        pnThongTinDocGia.add(txtTenDocGia);
        pnThongTinDocGia.add(lblGioiTinh);
        pnThongTinDocGia.add(rdbNam);
        pnThongTinDocGia.add(rdbNu);
        pnThongTinDocGia.add(lblGmail);
        pnThongTinDocGia.add(scrollPane);
        pnThongTinDocGia.add(lblSDT);
        pnThongTinDocGia.add(txtSDT);
        pnThongTinDocGia.add(lblTimKiem);
        pnThongTinDocGia.add(txtTimKiem);
        //=================BUTTON===============

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xoá");
        btnTim = new JButton("Tìm");
        btnSua = new JButton("Sửa");
        btnLoc = new JButton("Đọc giả chưa trả sách");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThem.setFont(fontButton);
        btnXoa.setFont(fontButton);
        btnTim.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnSua.setFont(fontButton);
        btnLoc.setFont(fontButton);

        btnThem.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoa.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTim.setIcon(new ImageIcon("image/Search-icon.png"));
        btnSua.setIcon(new ImageIcon("image/Pencil-icon.png"));
        btnLoc.setIcon(new ImageIcon("image/pheu-icon.png"));

        btnTim.setBounds(395,300,120,40);
        btnSua.setBounds(270,300,120,40);
        btnThem.setBounds(20,300,120,40);
        btnXoa.setBounds(145,300,120,40);
        btnLoc.setBounds(520,300,260,40);

        pnThongTinDocGia.add(btnSua);
        pnThongTinDocGia.add(btnThem);
        pnThongTinDocGia.add(btnXoa);
        pnThongTinDocGia.add(btnTim);
        pnThongTinDocGia.add(btnLoc);

        pnTableDocGia.add(pnThongTinDocGia);

        //====================Bảng đọc giả====================
        dtmDocGia = new DefaultTableModel();
        dtmDocGia.addColumn("Mã");
        dtmDocGia.addColumn("Họ");
        dtmDocGia.addColumn("Tên");
        dtmDocGia.addColumn("SDT");
        dtmDocGia.addColumn("Giới tính");
        dtmDocGia.addColumn("Gmail");
        dtmDocGia.addColumn("SL sách mượn");
        tblDocGia = new MyTable(dtmDocGia);

        tblDocGia.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblDocGia.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuMuon = tblDocGia.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(60);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(110);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(70);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(100);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(90);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(180);
        columnModelPhieuMuon.getColumn(6).setPreferredWidth(140);

        JScrollPane scrTblDocGia = new JScrollPane(tblDocGia);
        scrTblDocGia.setPreferredSize(new Dimension(800,230));

        scrTblDocGia.setBounds(0,370,800,235);
        //</editor-fold>
        pnThongTinDocGia.add(scrTblDocGia, BorderLayout.CENTER);

        pnTableDocGia.add(pnThongTinDocGia);
        //=======================================================
        this.add(pnTableDocGia);
        loadDataLenTableDocGia();
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
        docGiaBUS.docDanhSach();
        ArrayList<DocGia> dsdg = docGiaBUS.getListDocGia();
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
        String gioi = "";
        if(rdbNam.isSelected()){
            gioi = "Nam";
        }else {
            gioi = "Nữ";
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
        docGiaBUS.docDanhSach();
        ArrayList<DocGia> dsdg = docGiaBUS.getListDocGia();
        for(DocGia dg : dsdg){
            Vector vec = new Vector<>();
            vec.add(dg.getMaDocGia());
            vec.add(dg.getHo());
            vec.add(dg.getTen());
            vec.add(dg.getSDT());
            vec.add(dg.getGioiTinh());
            vec.add(dg.getGmail());
            vec.add(docGiaBUS.locDocGia(String.valueOf(dg.getMaDocGia())));
            if(docGiaBUS.locDocGia(String.valueOf(dg.getMaDocGia())) != 0)
                dtmDocGia.addRow(vec);
        }
    }

}



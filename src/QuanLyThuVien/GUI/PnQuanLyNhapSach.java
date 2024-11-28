package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.CTPhieuNhap;
import QuanLyThuVien.DTO.NXB;
import QuanLyThuVien.DTO.PhieuNhap;
import QuanLyThuVien.DTO.Sach;
import com.toedter.calendar.JDateChooser;

public class PnQuanLyNhapSach extends JPanel {
    private PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    private CTPhieuNhapBUS ctPhieuNhapBUS = new CTPhieuNhapBUS();
    private DangNhapGUI dangNhapGUI = new DangNhapGUI();
    private SachBUS sachBUS = new SachBUS();
    private NXBBUS nxbBUS = new NXBBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();
    public PnQuanLyNhapSach() {
        addConTrolsNhapSach();
        addEventsNhapSach();

    }



    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblSach, tblNhapSach, tblPhieuNhap, tblCTPhieuNhap;
    CardLayout cardNhapSachGroup = new CardLayout();
    JPanel pnCardTabNhapSach;
    DefaultTableModel dtmSach, dtmNhapSach, dtmPhieuNhap, dtmCTPhieuNhap;
    JTextField txtSoLuong, txtTimKiem, txtIDMin;
    JDateChooser dateBD;
    JDateChooser dateKT;
    JComboBox<String> cmbNXB;
    JButton btnChon, btnXoa, btnXacNhan, btnTimKiem, btnReset, btnReset1;
    JLabel lblTabbedNhapSach, lblTabbedLichSu;
    final ImageIcon tabbedSelected = new ImageIcon("image/Manager-GUI/tabbed-btn--selected.png");
    final ImageIcon tabbedDefault = new ImageIcon("image/Manager-GUI/tabbed-btn.png");

    private void addConTrolsNhapSach() {
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1290;
        int h = 740;

        /*
        =========================================================================
                                    PANEL TABBED
        =========================================================================
         */
        JPanel pnTop = new JPanel();
        pnTop.setBackground(new Color(101, 224, 199));
        //<editor-fold defaultstate="collapsed" desc="Panel Tab Nhân viên & Quyền">
        Font fonts = new Font("", Font.PLAIN, 20);
        pnTop.setPreferredSize(new Dimension(w, 41));
        pnTop.setLayout(null);
        pnTop.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));

        lblTabbedNhapSach = new JLabel("Nhập sách");
        lblTabbedNhapSach.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedNhapSach.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedNhapSach.setIcon(tabbedSelected);
        lblTabbedNhapSach.setBounds(2, 2, 140, 37);
        lblTabbedNhapSach.setFont(fonts);
        lblTabbedNhapSach.setForeground(Color.WHITE);
        lblTabbedNhapSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        lblTabbedLichSu = new JLabel("Danh sách");
        lblTabbedLichSu.setHorizontalTextPosition(JLabel.CENTER);
        lblTabbedLichSu.setVerticalTextPosition(JLabel.CENTER);
        lblTabbedLichSu.setIcon(tabbedDefault);
        lblTabbedLichSu.setBounds(143, 2, 140, 37);
        lblTabbedLichSu.setFont(fonts);
        lblTabbedLichSu.setForeground(Color.WHITE);
        lblTabbedLichSu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTop.add(lblTabbedNhapSach);
        pnTop.add(lblTabbedLichSu);
        //</editor-fold>
        this.add(pnTop, BorderLayout.NORTH);

        /*
        =========================================================================
                                    PANEL phiếu nhập
        =========================================================================
         */

        JPanel pnTableNhapSach = new JPanel();
        pnTableNhapSach.setLayout(new BorderLayout());

        JPanel pnTitleNhapSach = new JPanel();
        JLabel lblTitleNhapSach = new JLabel("Quản lý nhập sách");
        lblTitleNhapSach.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/img_qltv/icon_reset.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(30, 30));
        pnTitleNhapSach.add(lblTitleNhapSach);
        pnTitleNhapSach.add(btnReset);
        pnTableNhapSach.add(pnTitleNhapSach, BorderLayout.NORTH);
        //=================PANEL INPUT===========
        int x = 15;
        txtSoLuong = new JTextField(x);
        txtTimKiem = new JTextField(x);
        txtIDMin = new JTextField(x);
        cmbNXB = new JComboBox<String>();

        //=================Table sách==============
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        JPanel pnSach = new JPanel();


        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("NXB");
        dtmSach.addColumn("Giá");
        dtmSach.addColumn("SL còn");
        tblSach = new MyTable(dtmSach);
        tblSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSach = tblSach.getColumnModel();
        columnModelSach.getColumn(0).setPreferredWidth(50);
        columnModelSach.getColumn(1).setPreferredWidth(220);
        columnModelSach.getColumn(2).setPreferredWidth(180);
        columnModelSach.getColumn(3).setPreferredWidth(120);
        columnModelSach.getColumn(4).setPreferredWidth(80);

        JScrollPane srcTblSach = new JScrollPane(tblSach);
        srcTblSach.setPreferredSize(new Dimension(840, 280));

        srcTblSach.setBounds(0, 100, 580, 300);
        pnSach.add(srcTblSach);
        pnSach.setPreferredSize(new Dimension(840, 300));
        pnTableNhapSach.add(pnSach, BorderLayout.WEST);


        //=================Table sách cần nhập==============
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        JPanel pnSachNhap = new JPanel(new BorderLayout());

        JPanel pnTieuDe = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTenBang = new JLabel("Sách chờ nhập");
        lblTenBang.setFont(new Font("Arial", Font.BOLD, 20));
        pnTieuDe.add(lblTenBang);

        dtmNhapSach = new DefaultTableModel();
        dtmNhapSach.addColumn("Mã");
        dtmNhapSach.addColumn("ID min");
        dtmNhapSach.addColumn("Tên sách");
        dtmNhapSach.addColumn("Giá");
        dtmNhapSach.addColumn("SL");
        dtmNhapSach.addColumn("Thành tiền");
        tblNhapSach = new MyTable(dtmNhapSach);

        tblNhapSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblNhapSach.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblNhapSach.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TableColumnModel columnModelNhapSach = tblNhapSach.getColumnModel();
        columnModelNhapSach.getColumn(0).setPreferredWidth(60);
        columnModelNhapSach.getColumn(1).setPreferredWidth(60);
        columnModelNhapSach.getColumn(2).setPreferredWidth(250);
        columnModelNhapSach.getColumn(3).setPreferredWidth(150);
        columnModelNhapSach.getColumn(4).setPreferredWidth(80);
        columnModelNhapSach.getColumn(5).setPreferredWidth(150);

        JScrollPane srcTblNhapSach = new JScrollPane(tblNhapSach);
        srcTblNhapSach.setPreferredSize(new Dimension(800, 200));
        srcTblNhapSach.setBounds(0, 860, 760, 185);
        pnSachNhap.add(pnTieuDe, BorderLayout.NORTH); // Add title to the top
        pnSachNhap.add(srcTblNhapSach, BorderLayout.CENTER); // Add table in the center

        pnSachNhap.setPreferredSize(new Dimension(800, 250));
        //</editor-fold>
        pnTableNhapSach.add(pnSachNhap, BorderLayout.SOUTH);


        //=================Thông tin nhập sách==============

        JPanel pnThongTinNhapSach = new JPanel();
        pnThongTinNhapSach.setLayout(null);
        pnThongTinNhapSach.setPreferredSize(new Dimension(400, 180));
        pnThongTinNhapSach.setBounds(200, 0, 800, 400);


        JLabel lblTimKiem = new JLabel("Tìm tên sách:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(0, 10, 150, 25);
        txtTimKiem.setBounds(150, 10, 200, 25);

        JLabel lblSoLuong = new JLabel("Số lượng nhập:");
        lblSoLuong.setFont(font);
        txtSoLuong.setFont(font);
        lblSoLuong.setBounds(0, 50, 150, 25);
        txtSoLuong.setBounds(150, 50, 200, 25);

        JLabel lblMaPhanSach = new JLabel("Mã phân sách min:");
        lblMaPhanSach.setFont(font);
        txtIDMin.setFont(font);
        lblMaPhanSach.setBounds(0, 100, 170, 25);
        txtIDMin.setBounds(150, 100, 50, 25);

        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        btnChon.setBounds(20, 250, 110, 40);
        btnChon.setBackground(new Color(200, 255, 200));

        pnThongTinNhapSach.add(lblTimKiem);
        pnThongTinNhapSach.add(txtTimKiem);
        pnThongTinNhapSach.add(lblSoLuong);
        pnThongTinNhapSach.add(txtSoLuong);
        pnThongTinNhapSach.add(lblMaPhanSach);
        pnThongTinNhapSach.add(txtIDMin);
        pnThongTinNhapSach.add(btnChon);

        //=================Thông tin phiếu nhập==============

        JLabel lblNXB = new JLabel("Nhà xuất bản:");
        lblNXB.setFont(font);
        cmbNXB.setFont(font);
        lblNXB.setBounds(0, 150, 150, 25);
        cmbNXB.setBounds(150, 150, 200, 25);

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setFont(font);
        btnXacNhan.setBackground(new Color(200, 220, 255));
        btnXacNhan.setBounds(280, 250, 110, 40);

        btnXoa = new JButton("Xóa");
        btnXoa.setFont(font);
        btnXoa.setBackground(new Color(255, 200, 200));
        btnXoa.setBounds(150, 250, 110, 40);

        pnThongTinNhapSach.add(lblNXB);
        pnThongTinNhapSach.add(cmbNXB);
        pnThongTinNhapSach.add(btnXacNhan);
        pnThongTinNhapSach.add(btnXoa);

        pnTableNhapSach.add(pnThongTinNhapSach, BorderLayout.EAST);
        
    

        /*
        =========================================================================
                                    PANEL lịch sử
        =========================================================================
         */
        JPanel pnTableLichSu = new TransparentPanel();
        pnTableLichSu.setLayout(new BoxLayout(pnTableLichSu, BoxLayout.Y_AXIS));

        JPanel pnTitleLichSu = new TransparentPanel();
        JLabel lblTitleLichSu = new JLabel("Quản lý phiếu nhập");
        lblTitleLichSu.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset1 = new JButton(new ImageIcon("image/img_qltv/icon_reset.png"));
        btnReset1.setFocusPainted(false);
        btnReset1.setPreferredSize(new Dimension(40, 40));
        pnTitleLichSu.add(lblTitleLichSu);
        pnTitleLichSu.add(btnReset1);
        pnTableLichSu.add(pnTitleLichSu, BorderLayout.NORTH);
        //=================PANEL INPUT===========
        dateBD = new JDateChooser();
        dateBD.setDateFormatString("dd/MM/yyyy");
        dateKT = new JDateChooser();
        dateKT.setDateFormatString("dd/MM/yyyy");
        //=================Table phiếu nhập==============
        //<editor-fold defaultstate="collapsed" desc="Bảng phiếu mượn">
        JPanel pnPhieuNhap = new JPanel(new BorderLayout());

        dtmPhieuNhap = new DefaultTableModel();
        dtmPhieuNhap.addColumn("Mã");
        dtmPhieuNhap.addColumn("Nhà xuất bản");
        dtmPhieuNhap.addColumn("Nhân viên");
        dtmPhieuNhap.addColumn("Ngày lập");
        dtmPhieuNhap.addColumn("Tổng tiền");
        tblPhieuNhap = new MyTable(dtmPhieuNhap);
        tblPhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblPhieuNhap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelPhieuNhap = tblPhieuNhap.getColumnModel();
        columnModelPhieuNhap.getColumn(0).setPreferredWidth(50);
        columnModelPhieuNhap.getColumn(1).setPreferredWidth(240);
        columnModelPhieuNhap.getColumn(2).setPreferredWidth(180);
        columnModelPhieuNhap.getColumn(3).setPreferredWidth(130);
        columnModelPhieuNhap.getColumn(3).setPreferredWidth(160);

        JScrollPane srcTblPhieuNhap = new JScrollPane(tblPhieuNhap);

        pnPhieuNhap.add(srcTblPhieuNhap);
        //</editor-fold>
        pnTableLichSu.add(pnPhieuNhap);
        //=================Thông tin tìm kiếm==============

        JPanel pnTimKiem = new TransparentPanel();
        pnTimKiem.setLayout(new GridBagLayout());

        JLabel lblTimKiem1 = new JLabel("Ngày lập phiếu:    từ     ");
        JLabel lblTimKiem2 = new JLabel("     đến     ");
        JLabel lblKhoangTrang = new JLabel("     ");
        lblTimKiem1.setFont(font);
        lblTimKiem2.setFont(font);

        ImageIcon iconDate = new ImageIcon("image/img_qltv/icon_date.png");
        Image imgDate = iconDate.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIconDate = new ImageIcon(imgDate);

        dateBD.getCalendarButton().setIcon(resizedIconDate);
        dateKT.getCalendarButton().setPreferredSize(dateBD.getCalendarButton().getPreferredSize());
        dateKT.getCalendarButton().setIcon(dateBD.getCalendarButton().getIcon());
        dateBD.setFont(font);
        dateKT.setFont(font);

        btnTimKiem = new JButton();
        btnTimKiem.setFont(font);
        ImageIcon iconSearch = new ImageIcon("image/img_qltv/icon_search.png");
        Image imgSearch = iconSearch.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIconSearch = new ImageIcon(imgSearch);

        btnTimKiem.setIcon(resizedIconSearch);

        dateBD.setPreferredSize(new Dimension(150, 25));
        dateKT.setPreferredSize(new Dimension(150, 25));
        btnTimKiem.setPreferredSize(new Dimension(80, 40));

        pnTimKiem.add(lblTimKiem1);
        pnTimKiem.add((Component) dateBD);
        pnTimKiem.add(lblTimKiem2);
        pnTimKiem.add((Component) dateKT);
        pnTimKiem.add(lblKhoangTrang);
        pnTimKiem.add(btnTimKiem);

        pnTableLichSu.add(pnTimKiem);
        //=================Table chi tiết phiếu nhập==============
        //<editor-fold defaultstate="collapsed" desc="Bảng chi tiết phiếu nhập">
        JPanel pnCTPhieuNhap = new JPanel();

        JPanel pnTieuDePN = new JPanel();
        JLabel lblTenBangPN = new JLabel("Chi tiết phiếu nhập");
        lblTenBangPN.setFont(new Font("Arial", Font.BOLD, 20));
        pnTieuDePN.add(lblTenBangPN);

        dtmCTPhieuNhap = new DefaultTableModel();
        dtmCTPhieuNhap.addColumn("Mã");
        dtmCTPhieuNhap.addColumn("Tên sách");
        dtmCTPhieuNhap.addColumn("Giá");
        dtmCTPhieuNhap.addColumn("SL");
        dtmCTPhieuNhap.addColumn("Thành tiền");
        tblCTPhieuNhap = new MyTable(dtmCTPhieuNhap);

        tblCTPhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCTPhieuNhap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelCTPN = tblCTPhieuNhap.getColumnModel();
        columnModelCTPN.getColumn(0).setPreferredWidth(50);
        columnModelCTPN.getColumn(1).setPreferredWidth(300);
        columnModelCTPN.getColumn(2).setPreferredWidth(150);
        columnModelCTPN.getColumn(3).setPreferredWidth(120);
        columnModelCTPN.getColumn(4).setPreferredWidth(150);

        JScrollPane srcTblCTPN = new JScrollPane(tblCTPhieuNhap);
        srcTblCTPN.setPreferredSize(new Dimension(800, 180));

        pnCTPhieuNhap.add(pnTieuDePN);
        pnCTPhieuNhap.add(srcTblCTPN);

        pnPhieuNhap.setPreferredSize(new Dimension(700, 350));
        pnTimKiem.setPreferredSize(new Dimension(760, 100));
        pnCTPhieuNhap.setPreferredSize(new Dimension(800, 300));
        //</editor-fold>

        pnTableLichSu.add(pnCTPhieuNhap);
        //=======================================================

        pnCardTabNhapSach = new JPanel(cardNhapSachGroup);
        pnCardTabNhapSach.add(pnTableNhapSach, "1");
        pnCardTabNhapSach.add(pnTableLichSu, "2");

        // Tăng kích thước header cho tblSach
        JTableHeader headerSach = tblSach.getTableHeader();
        headerSach.setFont(new Font("Arial", Font.BOLD, 18)); // Thay đổi font
        headerSach.setPreferredSize(new Dimension(headerSach.getPreferredSize().width, 40)); // Tăng chiều cao

// Tăng kích thước header cho tblNhapSach
        JTableHeader headerNhapSach = tblNhapSach.getTableHeader();
        headerNhapSach.setFont(new Font("Arial", Font.BOLD, 18));
        headerNhapSach.setPreferredSize(new Dimension(headerNhapSach.getPreferredSize().width, 40));

// Tăng kích thước header cho tblPhieuNhap
        JTableHeader headerPhieuNhap = tblPhieuNhap.getTableHeader();
        headerPhieuNhap.setFont(new Font("Arial", Font.BOLD, 18));
        headerPhieuNhap.setPreferredSize(new Dimension(headerPhieuNhap.getPreferredSize().width, 40));

// Tăng kích thước header cho tblCTPhieuNhap
        JTableHeader headerCTPhieuNhap = tblCTPhieuNhap.getTableHeader();
        headerCTPhieuNhap.setFont(new Font("Arial", Font.BOLD, 18));
        headerCTPhieuNhap.setPreferredSize(new Dimension(headerCTPhieuNhap.getPreferredSize().width, 40));


        this.add(pnCardTabNhapSach);
        loadDataNXB();
        loadDataLenTableSach();
        loadDataTablePhieuNhap();
        loadDataTableCTPhieuNhap();

    }

    private void addEventsNhapSach() {
        lblTabbedNhapSach.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblTabbedNhapSach.setIcon(tabbedSelected);
                lblTabbedLichSu.setIcon(tabbedDefault);
                cardNhapSachGroup.show(pnCardTabNhapSach, "1");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        lblTabbedLichSu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataTablePhieuNhap();
                loadDataTableCTPhieuNhap();
                lblTabbedLichSu.setIcon(tabbedSelected);
                lblTabbedNhapSach.setIcon(tabbedDefault);
                cardNhapSachGroup.show(pnCardTabNhapSach, "2");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        cmbNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonNXB();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyReset();
            }
        });
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemVaoGio();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhoiGio();
            }
        });
        btnXacNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXacNhan();
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemNangCao();
            }
        });
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyTimKiem();
            }
        });
        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtSoLuong.setText("1");
            }
        });
        tblPhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblPhieuNhap.getSelectedRow();;
                loadDataTableCTPhieuNhap(tblPhieuNhap.getValueAt(row,0)+"");
            }
        });
        btnReset1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTablePhieuNhap();
                loadDataTableCTPhieuNhap();
            }
        });
    }

    private void xuLyChonNXB(){
        int x = cmbNXB.getSelectedIndex();
        if(dtmNhapSach.getRowCount()==0) {
            if (x != 0) {
                String ten = cmbNXB.getItemAt(x);
                loadDataLenTableSachTheoMaNXB(ten);
            } else {
                loadDataLenTableSach();
            }
        }else {
            int MaNXB = sachBUS.getSach(tblNhapSach.getValueAt(0,0)+"").getMaNXB();
            String nxb = nxbBUS.getTenNXB(MaNXB);
            if(!cmbNXB.getItemAt(x).contains(nxb)) {
                new MyDialog("Bạn không thể nhập mặt hàng của nxb khác!!!", MyDialog.ERROR_DIALOG);
                String ten = dtmSach.getValueAt(0, 2) + "";
                for (int i = 0; i < cmbNXB.getItemCount(); i++) {
                    if (cmbNXB.getItemAt(i).contains(ten)) {
                        cmbNXB.setSelectedIndex(i);
                        return;
                    }
                }
            }
        }
    }

    private void loadDataLenTableSachTheoMaNXB(String ten){
        int maNXB = nxbBUS.getMaNXB(ten);
        sachBUS.docDanhSach();
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.getListSach();
        for (Sach s : dss){
            if(maNXB == s.getMaNXB()){
                Vector vec = new Vector<>();
                vec.add(s.getMaSach());
                vec.add(sachBUS.getTenSach(s.getMaSach()));
                vec.add(nxbBUS.getTenNXB(s.getMaNXB()));
                vec.add(s.getGiaSach());
                vec.add(s.getSoLuong());
                dtmSach.addRow(vec);
            }
        }
    }

    private void loadDataNXB(){
        cmbNXB.removeAllItems();

        ArrayList<NXB> dsnxb = nxbBUS.getListNXB();
        cmbNXB.addItem("Chọn nxb");
        for(NXB nxb : dsnxb){
            cmbNXB.addItem(nxb.getTenNXB());
        }
    }

    public void loadDataLenTableSach(){
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.getListSach();
        for(Sach s : dss){
            Vector vec = new Vector();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(nxbBUS.getTenNXB(s.getMaNXB()));
            vec.add(s.getGiaSach());
            vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
    }

    public void loadDataLenTableSachTheoTuKhoa(String tuKhoa){
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.timKiemSach(tuKhoa);
        for(Sach s : dss){
            Vector vec = new Vector();
            vec.add(s.getMaSach());
            vec.add(s.getTenSach());
            vec.add(nxbBUS.getTenNXB(s.getMaNXB()));
            vec.add(s.getGiaSach());
            vec.add(s.getSoLuong());
            dtmSach.addRow(vec);
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
    DecimalFormat dcf = new DecimalFormat("###,###");

    private void loadDataTablePhieuNhap(){
        phieuNhapBUS.docDanhSachPhieuNhap();
        ArrayList<PhieuNhap> dspn = phieuNhapBUS.getListPhieuNhap();
        duaDataVaoTablePhieuNhap(dspn);
    }

    private void duaDataVaoTablePhieuNhap(ArrayList<PhieuNhap> dspn) {
        if (dspn != null) {
            dtmPhieuNhap.setRowCount(0);
            for (PhieuNhap pn : dspn) {
                Vector vec = new Vector();
                vec.add(pn.getMaPhieuNhap());
                vec.add(nxbBUS.getTenNXB(pn.getMaNXB()));
                vec.add(nhanVienBUS.getTenNhanVien(pn.getMaNhanVien()));
                vec.add(sdf.format(pn.getNgayLap()));
                vec.add(dcf.format(pn.getTongTien()));
                dtmPhieuNhap.addRow(vec);
            }
        }
    }


    private void loadDataTableCTPhieuNhap() {
        ctPhieuNhapBUS.docDanhSach();
        dtmCTPhieuNhap.setRowCount(0);
        ArrayList<CTPhieuNhap> dsct = ctPhieuNhapBUS.getListCTPhieuNhap();
        if (dsct != null) {
            for (CTPhieuNhap ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaSach());
                vec.add(sachBUS.getTenSach(ct.getMaSach()));
                vec.add(dcf.format(ct.getGia()));
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmCTPhieuNhap.addRow(vec);
            }
        }
    }

    private void loadDataTableCTPhieuNhap(String ma) {
        dtmCTPhieuNhap.setRowCount(0);
        ArrayList<CTPhieuNhap> dsct = ctPhieuNhapBUS.getListCTPhieuNhapTheoMa(ma);
        if (dsct != null) {
            for (CTPhieuNhap ct : dsct) {
                Vector vec = new Vector();
                vec.add(ct.getMaSach());
                vec.add(sachBUS.getTenSach(ct.getMaSach()));
                vec.add(dcf.format(ct.getGia()));
                vec.add(dcf.format(ct.getSoLuong()));
                vec.add(dcf.format(ct.getThanhTien()));
                dtmCTPhieuNhap.addRow(vec);
            }
        }
    }

    private void xuLyThemVaoGio(){
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText());

        } catch (Exception e) {
            new MyDialog("Phải có số lượng!", MyDialog.ERROR_DIALOG);
            return;
        }
        if(txtIDMin.getText().equals("")){
            new MyDialog("Chưa nhập mã phân sách tối thiểu!", MyDialog.ERROR_DIALOG);
            return;
        }
        int idMin = Integer.parseInt(txtIDMin.getText());

        int row = tblSach.getSelectedRow();
        if (row > -1) {
            String maSach= tblSach.getValueAt(row, 0) + "";
            if(idMin <= phanSachBUS.maPhanSachLonNhat(Integer.parseInt(tblSach.getValueAt(row, 0)+""))){
                new MyDialog("Mã lớn nhất hiện tại là "+ phanSachBUS.maPhanSachLonNhat(Integer.parseInt(tblSach.getValueAt(row, 0)+"")) +"!", MyDialog.ERROR_DIALOG);
                return;
            }
            String NXB = tblSach.getValueAt(row,2)+"";
            for(int i=0;i<cmbNXB.getItemCount();i++){
                if(cmbNXB.getItemAt(i).contains(NXB)){
                    cmbNXB.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < tblNhapSach.getRowCount(); i++) {
                if (maSach.equals(tblNhapSach.getValueAt(i, 0))) {
                    long gia = Long.parseLong(tblNhapSach.getValueAt(i, 3) + "");
                    int soLuongCu = Integer.parseInt(tblNhapSach.getValueAt(i, 4) + "");
                    soLuong += soLuongCu;
                    long thanhTien = soLuong * gia;
                    tblNhapSach.setValueAt(idMin,i,2);
                    tblNhapSach.setValueAt(soLuong, i, 4);
                    tblNhapSach.setValueAt(gia, i, 3);
                    tblNhapSach.setValueAt(thanhTien, i, 5);
                    return;
                }
            }
            String tenSach = tblSach.getValueAt(row, 1) + "";
            long gia = Long.parseLong(tblSach.getValueAt(row, 3)+"");
            long thanhTien = soLuong * gia;
            Vector vec = new Vector();
            vec.add(maSach);
            vec.add(idMin);
            vec.add(tenSach);
            vec.add(gia);
            vec.add(soLuong);
            vec.add(thanhTien);
            dtmNhapSach.addRow(vec);
        } else {
            new MyDialog("Chưa chọn sách để nhập!!!", MyDialog.ERROR_DIALOG);
        }
    }

    private void xuLyXoaKhoiGio(){
        int row = tblNhapSach.getSelectedRow();
        if(row > -1){
            dtmNhapSach.removeRow(row);
        }else {
            new MyDialog("Hãy chọn sách để xóa!!!", MyDialog.ERROR_DIALOG);
        }
    }

    private void xuLyTimKiem(){
        loadDataLenTableSachTheoTuKhoa(txtTimKiem.getText());
    }

    private void xuLyXacNhan(){
        int row = tblNhapSach.getRowCount();
        if (row < 1) {
            new MyDialog("Chưa có gì để nhập!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        String nxb = (String) cmbNXB.getSelectedItem();
        if (nxb.trim().equals("Chọn nxb")){
            new MyDialog("Hãy chọn nhà xuất bản!!!", MyDialog.ERROR_DIALOG);
            return;
        }

        ArrayList<CTPhieuNhap> dsct = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            int maSach = Integer.parseInt(tblNhapSach.getValueAt(i, 0) + "");
            int gia = Integer.parseInt(tblNhapSach.getValueAt(i, 3) + "");
            int soLuong = Integer.parseInt(tblNhapSach.getValueAt(i, 4) + "");
            int thanhTien = Integer.parseInt(tblNhapSach.getValueAt(i, 5) + "");
            CTPhieuNhap ctpn = new CTPhieuNhap(0, maSach, gia, soLuong, thanhTien);
            dsct.add(ctpn);
        }
        XuatPhieuNhapGUI xuatPhieuNhap = new XuatPhieuNhapGUI(nxb, nhanVienBUS.getTenNhanVien(dangNhapGUI.maTaiKhoan()), dsct);
        xuatPhieuNhap.setVisible(true);
        if (xuatPhieuNhap.getCheckNhap()) {
            for (int i = 0; i<row;i++){
                int maSach = Integer.parseInt(tblNhapSach.getValueAt(i, 0) + "");
                int soLuong = Integer.parseInt(tblNhapSach.getValueAt(i, 4) + "");
                int iDMin = Integer.parseInt(tblNhapSach.getValueAt(i,1)+"");
                int iDMax = iDMin+soLuong-1;
                for (int maPS=iDMin;maPS <= iDMax;maPS++){
                    String maS = String.valueOf(maSach);
                    String maPhanSach = String.valueOf(maPS);
                    boolean flag = phanSachBUS.themPhanSach(maPhanSach,maS,"tốt");
                }
            }
            dtmNhapSach.setRowCount(0);
            sachBUS.docDanhSach();
            loadDataLenTableSach();
        }
    }

    private void xuLyTimKiemNangCao(){
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
        dtmPhieuNhap.setRowCount(0);
        ArrayList<PhieuNhap> dspn  = null;
        dspn = phieuNhapBUS.getListPhieuNhapTheoNgay(ngay1,ngay2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(PhieuNhap pn : dspn){
            Vector vec = new Vector();
            vec.add(pn.getMaPhieuNhap());
            vec.add(nxbBUS.getTenNXB(pn.getMaNXB()));
            vec.add(nhanVienBUS.getTenNhanVien(pn.getMaNhanVien()));
            vec.add(sdf.format(pn.getNgayLap()));
            vec.add(dcf.format(pn.getTongTien()));
            dtmPhieuNhap.addRow(vec);
        }
    }

    private void xuLyReset(){
        sachBUS.docDanhSach();
        loadDataLenTableSach();
        dtmNhapSach.setRowCount(0);
        txtIDMin.setText("");
        txtSoLuong.setText("");
        cmbNXB.setSelectedIndex(0);
    }



    }



    
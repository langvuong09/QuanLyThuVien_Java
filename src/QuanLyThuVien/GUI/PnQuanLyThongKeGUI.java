
package QuanLyThuVien.GUI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DAO.PhieuPhatDAO;
import QuanLyThuVien.DTO.DocGia;
import QuanLyThuVien.DTO.PhieuMuon;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.TitledBorder;


public class PnQuanLyThongKeGUI extends JPanel {
    public PnQuanLyThongKeGUI(){
        addControls();
    }
    DocGiaBUS dgBUS = new DocGiaBUS();
    PhieuMuonBUS pmBUS = new PhieuMuonBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private LoaiBUS loaiBUS=new LoaiBUS();
    private PhieuTraBUS ptBUS=new PhieuTraBUS();
    private PhieuPhatDAO ppBUS=new PhieuPhatDAO();
    DefaultTableModel tableModel,modelChuaTra,modelDaTra;

    public void addControls(){
        this.setSize(1290, 740);
        this.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel lblTitle = new JLabel("THỐNG KÊ - BÁO CÁO", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(175, 238, 238));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setPreferredSize(new Dimension(this.getWidth(), 50));
        this.add(lblTitle, BorderLayout.NORTH);

        // Sử dụng CardLayout để chuyển đổi giữa các trang
        JPanel cardPanel = new JPanel(new CardLayout());

        // Trang thống kê (chính)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel cho các thông số
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 6, 10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statsPanel.setPreferredSize(new Dimension(this.getWidth(), 180));

        String[] labels = {"THỂ LOẠI", "ĐỌC GIẢ", "PHIẾU MƯỢN", "PHIẾU TRẢ", "PHIẾU PHẠT"};
        Color[] colors = {new Color(102, 204, 255), new Color(153, 255, 153), new Color(255, 204, 102),
                new Color(255, 102, 102), new Color(102, 153, 255), new Color(255, 153, 102)};

        String theloai=loaiBUS.getListLoai().size()+"";
        String docgia=docGiaBUS.getListDocGia().size()+"";
        String pmuon=pmBUS.getListPhieuMuon().size()+"";
        String ptra=ptBUS.getListPhieuTra().size()+"";
        String pphat=ppBUS.getListPhieuPhat().size()+"";
        String[] values={theloai.trim(),docgia.trim(),pmuon.trim(),ptra.trim(),pphat.trim()};

        for (int i = 0; i < labels.length; i++) {
            JPanel statPanel = new JPanel(new BorderLayout());
            statPanel.setBackground(colors[i]);
            statPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            statPanel.setPreferredSize(new Dimension(150, 150));

            JLabel lblValue = new JLabel(values[i], JLabel.CENTER);
            lblValue.setFont(new Font("Arial", Font.BOLD, 36));
            lblValue.setForeground(Color.WHITE);

            JLabel lblLabel = new JLabel(labels[i], JLabel.CENTER);
            lblLabel.setFont(new Font("Arial", Font.BOLD, 18));
            lblLabel.setForeground(Color.WHITE);

            statPanel.add(lblValue, BorderLayout.CENTER);
            statPanel.add(lblLabel, BorderLayout.SOUTH);

            statsPanel.add(statPanel);
        }

        mainPanel.add(statsPanel, BorderLayout.NORTH);

        // Bảng thông tin mượn sách
        String[] columnNames = {"Mã", "Đọc Gỉa", "Nhân Viên", "Ngày Mượn", "Hạn Trả", "Tổng tiền"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        JTableHeader tableHeader = table.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModelPhieuMuon = table.getColumnModel();
        columnModelPhieuMuon.getColumn(0).setPreferredWidth(50);
        columnModelPhieuMuon.getColumn(1).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(2).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(3).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(4).setPreferredWidth(200);
        columnModelPhieuMuon.getColumn(5).setPreferredWidth(200);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 18));
        tableHeader.setForeground(Color.BLACK);
        tableHeader.setBackground(new Color(101, 224, 199));
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        cardPanel.add(mainPanel, "Main");

        // Tạo JPanel cho DS chưa trả
        JPanel dsChuaTraPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Danh sách chưa trả", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Khoảng cách xung quanh
        dsChuaTraPanel.add(titleLabel, BorderLayout.NORTH);

        // Dữ liệu bảng
        String[] column1 = {"Mã", "Họ", "Tên", "SĐT", "Giới tính", "Gmail"};
        Object[][] dataChuaTra = {};

        modelChuaTra = new DefaultTableModel(dataChuaTra, column1);
        JTable tableChuaTra = new JTable(modelChuaTra);
        TableColumnModel columnModelDocGia1 = tableChuaTra.getColumnModel();
        columnModelDocGia1.getColumn(0).setPreferredWidth(60);
        columnModelDocGia1.getColumn(1).setPreferredWidth(110);
        columnModelDocGia1.getColumn(2).setPreferredWidth(70);
        columnModelDocGia1.getColumn(3).setPreferredWidth(100);
        columnModelDocGia1.getColumn(4).setPreferredWidth(90);
        columnModelDocGia1.getColumn(5).setPreferredWidth(180);
        // Định dạng header cho bảng chưa trả
        JTableHeader headerChuaTra = tableChuaTra.getTableHeader();
        headerChuaTra.setFont(new Font("Arial", Font.BOLD, 18));
        headerChuaTra.setForeground(Color.BLACK);
        // Định dạng cho nội dung bảng chưa trả
        tableChuaTra.setFont(new Font("Arial", Font.PLAIN, 14));
        tableChuaTra.setRowHeight(25);
        JScrollPane scrollChuaTra = new JScrollPane(tableChuaTra);
        // Điều chỉnh kích thước cho cả hai bảng
        tableChuaTra.setPreferredScrollableViewportSize(new Dimension(1050, 600));
        scrollChuaTra.setPreferredSize(new Dimension(1000, 600));
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        tablePanel.add(scrollChuaTra, gbc);
        dsChuaTraPanel.add(tablePanel, BorderLayout.CENTER);


        JButton btnBackChuaTra = new JButton("Back");
        btnBackChuaTra.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBackChuaTra.setBackground(new Color(215, 62, 62));
        btnBackChuaTra.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Main");
        });
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(btnBackChuaTra);
        dsChuaTraPanel.add(backPanel, BorderLayout.SOUTH);
        cardPanel.add(dsChuaTraPanel, "DSChuaTra");

        // Panel "Danh sách đã trả"
        JPanel dsDaTraPanel = new JPanel(new BorderLayout());

        // Tiêu đề
        JLabel titleLabelDaTra = new JLabel("Danh sách đã trả", SwingConstants.CENTER);
        titleLabelDaTra.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabelDaTra.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        dsDaTraPanel.add(titleLabelDaTra, BorderLayout.NORTH);

        // Dữ liệu bảng
        String[] column2 = {"Mã", "Họ", "Tên", "SĐT", "Giới tính", "Gmail"};
        Object[][] dataDaTra = {};

        // Tạo bảng
        modelDaTra = new DefaultTableModel(dataDaTra, column2);
        JTable tableDaTra = new JTable(modelDaTra);

        // Định dạng header cho bảng đã trả
        JTableHeader headerDaTra = tableDaTra.getTableHeader();
        headerDaTra.setFont(new Font("Arial", Font.BOLD, 18));
        headerDaTra.setForeground(Color.BLACK);
        TableColumnModel columnModelDocGia2 = tableDaTra.getColumnModel();
        columnModelDocGia2.getColumn(0).setPreferredWidth(60);
        columnModelDocGia2.getColumn(1).setPreferredWidth(110);
        columnModelDocGia2.getColumn(2).setPreferredWidth(70);
        columnModelDocGia2.getColumn(3).setPreferredWidth(100);
        columnModelDocGia2.getColumn(4).setPreferredWidth(90);
        columnModelDocGia2.getColumn(5).setPreferredWidth(180);

        // Định dạng cho nội dung bảng đã trả
        tableDaTra.setFont(new Font("Arial", Font.PLAIN, 14));
        tableDaTra.setRowHeight(25);
        JScrollPane scrollDaTra = new JScrollPane(tableDaTra);

        // Điều chỉnh kích thước cho cả hai bảng
        tableDaTra.setPreferredScrollableViewportSize(new Dimension(1050, 600));
        scrollDaTra.setPreferredSize(new Dimension(1000, 600));

        // Panel chứa bảng
        JPanel tablePanelDaTra = new JPanel(new GridBagLayout());
        tablePanelDaTra.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbcDaTra = new GridBagConstraints();
        gbcDaTra.gridx = 0;
        gbcDaTra.gridy = 0;
        gbcDaTra.weightx = 1.0;
        gbcDaTra.weighty = 1.0;
        gbcDaTra.fill = GridBagConstraints.BOTH;
        tablePanelDaTra.add(scrollDaTra, gbcDaTra);

        dsDaTraPanel.add(tablePanelDaTra, BorderLayout.CENTER);

        // Nút Back
        JButton btnBackDaTra = new JButton("Back");
        btnBackDaTra.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBackDaTra.setBackground(new Color(215, 62, 62));
        btnBackDaTra.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Main");
        });

        JPanel backPanelDaTra = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanelDaTra.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        backPanelDaTra.add(btnBackDaTra);

        dsDaTraPanel.add(backPanelDaTra, BorderLayout.SOUTH);

        cardPanel.add(dsDaTraPanel, "DSDaTra");

        // ================ PANEL THỐNG KÊ =================================================
        JPanel thongKePanel = new JPanel(new BorderLayout(10, 10));
        thongKePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Thống kê
        JPanel statisticsPanel = new JPanel(new BorderLayout(10, 10));
        statisticsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Thống kê",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));

        cardPanel.add(thongKePanel, "ThongKe");

        this.add(cardPanel, BorderLayout.CENTER);

        // Panel nút bấm
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel1.setBackground(Color.cyan);
        String[] buttonLabels = {"DS chưa trả", "DS đã trả"};
        String[] panelKeys = {"DSChuaTra", "DSDaTra"};

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setPreferredSize(new Dimension(250, 40));
            button.setForeground(Color.black);
            final String panelKey = panelKeys[i];
            button.addActionListener(e -> {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, panelKey);
            });

            buttonPanel1.add(button);
        }
        xuLyLocDocGiaDaTraHetSach();
        xuLyLocDocGiaConMuonSach();
        loadDataLenBangPhieuMuon();
        this.add(buttonPanel1, BorderLayout.SOUTH);


    }

    public void xuLyLocDocGiaConMuonSach(){
        modelChuaTra.setRowCount(0);
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
                modelChuaTra.addRow(vec);
        }
    }

    public void xuLyLocDocGiaDaTraHetSach(){
        modelDaTra.setRowCount(0);
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
            if(dgBUS.locDocGia(String.valueOf(dg.getMaDocGia())) == 0)
                modelDaTra.addRow(vec);
        }
    }

    private void loadDataLenBangPhieuMuon(){
        pmBUS.docListPhieuMuon();
        tableModel.setRowCount(0);

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
            tableModel.addRow(vec);
        }
    }

}

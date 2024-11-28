package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.MyTable;
import QuanLyThuVien.BUS.KhuVucBUS;
import QuanLyThuVien.BUS.PhanSachBUS;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DTO.CTKeSach;
import QuanLyThuVien.DTO.KeSach;
import QuanLyThuVien.DTO.KhuVuc;
import QuanLyThuVien.DTO.PhanSach;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class PnQuanLyKhuVucGUI extends JPanel {
    private KhuVucBUS khuVucBUS = new KhuVucBUS();
    private SachBUS sachBUS = new SachBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();
    private DlgThemSachVaoKhuVuc themSachVaoKe = new DlgThemSachVaoKhuVuc();
    final Color colorPanel = new Color(251, 241, 241);
    final Color colorHeader = new Color(122, 213, 172);
    final Color colorButtonAdd = new Color(230, 134, 134);
    final Color colorButtonDelete = new Color(230, 134, 134);
    final Color colorButtonSearch = new Color(122, 213, 172);
    MyTable tblKhuVuc, tblKe, tblSach;
    DefaultTableModel dtmSach, dtmKhu, dtmKe;
    JTextField txtTimKiem;
    JButton btnTimKiem, btnThemSach, btnXoaSach, btnThemKhu, btnXoaKhu, btnThemKe, btnXoaKe, btnReset;

    public PnQuanLyKhuVucGUI(){
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        addConTrolsKhuVuc();
        addEventsKhuVuc();
    }

    private void addConTrolsKhuVuc(){
        Font font = new Font("Arial", Font.BOLD,14);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        JPanel pnTableKhuVuc = new JPanel();
        pnTableKhuVuc.setLayout(new BorderLayout());

        // Panel tiêu đề "Quản lý khu vực" với màu nền
        JPanel pnTitleKhuVuc = new JPanel();
        pnTitleKhuVuc.setBackground(new Color(0x65E0C7));
        JLabel lblTitleKhuVuc = new JLabel("QUẢN LÝ KHU VỰC");
        lblTitleKhuVuc.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitleKhuVuc.setForeground(Color.WHITE);
        
        btnReset = new JButton(new ImageIcon(new ImageIcon("image/img_qltv/icon_reset.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        btnReset.setPreferredSize(new Dimension(40, 40));
        btnReset.setBackground(Color.WHITE);
        btnReset.setBorder(BorderFactory.createEmptyBorder());

        pnTitleKhuVuc.add(lblTitleKhuVuc);
        pnTitleKhuVuc.add(btnReset);
        pnTableKhuVuc.add(pnTitleKhuVuc, BorderLayout.NORTH);

        JPanel pnKhuVuc = new JPanel();
        pnKhuVuc.setLayout(null);
        pnKhuVuc.setBackground(colorPanel);

        // Text field tìm kiếm
        txtTimKiem = new JTextField(20);
        JLabel lblTimKiem = new JLabel("Tìm tên sách:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(100,240,150,28);
        txtTimKiem.setBounds(200,240,500,32);
        pnKhuVuc.add(lblTimKiem);
        pnKhuVuc.add(txtTimKiem);

        // Các nút chức năng
        btnThemKhu = createButton("Thêm", colorButtonAdd);
        btnXoaKhu = createButton("Xóa", colorButtonDelete);
        btnThemKe = createButton("Thêm", colorButtonAdd);
        btnXoaKe = createButton("Xóa", colorButtonDelete);
        btnThemSach = createButton("Thêm", colorButtonAdd);
        btnXoaSach = createButton("Xóa", colorButtonDelete);
        btnTimKiem = createButton("Tìm", colorButtonSearch);

        btnThemKhu.setBounds(400,400,110,40);
        btnXoaKhu.setBounds(400,450,110,40);
        btnThemKe.setBounds(1060,400,110,40);
        btnXoaKe.setBounds(1060,450,110,40);
        btnThemSach.setBounds(900,240,110,40);
        btnXoaSach.setBounds(1050,240,110,40);
        btnTimKiem.setBounds(750,240,110,40);

        pnKhuVuc.add(btnThemKhu);
        pnKhuVuc.add(btnXoaKhu);
        pnKhuVuc.add(btnThemKe);
        pnKhuVuc.add(btnXoaKe);
        pnKhuVuc.add(btnThemSach);
        pnKhuVuc.add(btnXoaSach);
        pnKhuVuc.add(btnTimKiem);

        // Bảng khu vực
        dtmKhu = new DefaultTableModel();
        dtmKhu.addColumn("Mã khu vực");
        dtmKhu.addColumn("Số lượng kệ");
        tblKhuVuc = new MyTable(dtmKhu);
        tblKhuVuc.getTableHeader().setBackground(colorHeader);
        JScrollPane scrTblKhuVuc = new JScrollPane(tblKhuVuc);
        scrTblKhuVuc.setBounds(80,350,280,200);
        pnKhuVuc.add(scrTblKhuVuc);

        // Bảng kệ
        dtmKe = new DefaultTableModel();
        dtmKe.addColumn("Mã kệ");
        dtmKe.addColumn("Số lượng sách");
        tblKe = new MyTable(dtmKe);
        tblKe.getTableHeader().setBackground(colorHeader);
        JScrollPane scrTblKe = new JScrollPane(tblKe);
        scrTblKe.setBounds(750,350,280,200);
        pnKhuVuc.add(scrTblKe);

        // Bảng sách
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Mã phân sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Trạng thái");
        tblSach = new MyTable(dtmSach);
        tblSach.getTableHeader().setBackground(colorHeader);
        JScrollPane scrTblSach = new JScrollPane(tblSach);
        scrTblSach.setBounds(80,20,1100,200);
        pnKhuVuc.add(scrTblSach);

        loadDataLenBangKhuVuc();
        loadDataLenBangKe();
        loadDataLenBangSach();

        pnTableKhuVuc.add(pnKhuVuc);
        // Tăng kích thước font và chiều cao của header bảng
        tblKhuVuc.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblKhuVuc.getTableHeader().setPreferredSize(new Dimension(tblKhuVuc.getTableHeader().getPreferredSize().width, 30));

        tblKe.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblKe.getTableHeader().setPreferredSize(new Dimension(tblKe.getTableHeader().getPreferredSize().width, 30));

        tblSach.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblSach.getTableHeader().setPreferredSize(new Dimension(tblSach.getTableHeader().getPreferredSize().width, 30));

        this.add(pnTableKhuVuc);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(80, 40));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }



    private void addEventsKhuVuc(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangKhuVuc();
                loadDataLenBangKe();
                loadDataLenBangSach();
                txtTimKiem.setText("");
            }
        });
        tblKhuVuc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKhuVuc();
            }
        });
        tblKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblKe();
            }
        });
        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClickTblSach();
            }
        });
        btnThemKhu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhu();
            }
        });
        btnXoaKhu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhu();
            }
        });
        btnThemKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKe();
            }
        });
        btnXoaKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKe();
            }
        });
        btnThemSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSach();
            }
        });
        btnXoaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaSach();
            }
        });
        btnTimKiem.addActionListener(new ActionListener() {
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
    }

    private void loadDataLenBangKhuVuc(){
        khuVucBUS.docDanhSach();
        dtmKhu.setRowCount(0);

        ArrayList<KhuVuc> dskv = khuVucBUS.getListKhuVuc();

        for(KhuVuc kv : dskv){
            Vector vec = new Vector();
            vec.add(kv.getMaKhu());
            vec.add(khuVucBUS.tongKeMoiKhu(kv.getMaKhu()));
            dtmKhu.addRow(vec);
        }
    }

    private void loadDataLenBangKe(){
        khuVucBUS.docDanhSach();
        dtmKe.setRowCount(0);

        ArrayList<KeSach> dsks = khuVucBUS.getListKeSach();

        for(KeSach ks : dsks){
            Vector vec = new Vector();
            vec.add(ks.getMaKe());
            vec.add(khuVucBUS.tongSachMoiKe(ks.getMaKe()));
            dtmKe.addRow(vec);
        }
    }

    private void loadDataLenBangSach(){
        khuVucBUS.docDanhSach();
        phanSachBUS.docDanhSach();
        phanSachBUS.docDanhSachSachTrenKe();
        dtmSach.setRowCount(0);

        ArrayList<PhanSach> dsps = phanSachBUS.getListPhanSachCoTrongKeSach();

        for(PhanSach ps : dsps) {
            Vector vec = new Vector();
            vec.add(ps.getMaSach());
            vec.add(ps.getMaPhanSach());
            vec.add(sachBUS.getTenSach(ps.getMaSach()));
            vec.add(ps.getTrangThai());
            dtmSach.addRow(vec);
        }
    }

    private void loadDataLenBangKhuVucTheoMa(int ma){
        khuVucBUS.docDanhSach();
        dtmKhu.setRowCount(0);

        ArrayList<KhuVuc> dskv = khuVucBUS.getListKhuVucTheoMa(ma);

        for(KhuVuc kv : dskv){
            Vector vec = new Vector();
            vec.add(kv.getMaKhu());
            vec.add(khuVucBUS.tongKeMoiKhu(kv.getMaKhu()));
            dtmKhu.addRow(vec);
        }
    }

    private void loadDataLenBangKeTheoMa(int ma){
        khuVucBUS.docDanhSach();
        dtmKe.setRowCount(0);
        ArrayList<KeSach> dsks = khuVucBUS.getListKeSachTheoMa(ma);

        for(KeSach ks : dsks){
            Vector vec = new Vector();
            vec.add(ks.getMaKe());
            vec.add(khuVucBUS.tongSachMoiKe(ks.getMaKe()));
            dtmKe.addRow(vec);
        }
    }

    private void loadDataLenBangKeTheoMaSach(int ma){
        khuVucBUS.docDanhSach();
        dtmKe.setRowCount(0);
        ArrayList<KeSach> dsks = khuVucBUS.getListKeSachTheoMaSach(ma);

        for(KeSach ks : dsks){
            Vector vec = new Vector();
            vec.add(ks.getMaKe());
            vec.add(khuVucBUS.tongSachMoiKe(ks.getMaKe()));
            dtmKe.addRow(vec);
        }
    }

    private void loadDataLenBangSachTheoMa(int ma){
        khuVucBUS.docDanhSach();
        phanSachBUS.docDanhSach();

        ArrayList<CTKeSach> dsctks = khuVucBUS.getListCTKeSachTheoMa(ma);

        for(CTKeSach ctks : dsctks){
            for(PhanSach ps : phanSachBUS.getListTheoMa(ctks.getMaSach())) {
                Vector vec = new Vector();
                vec.add(ps.getMaSach());
                vec.add(ps.getMaPhanSach());
                vec.add(sachBUS.getTenSach(ps.getMaSach()));
                vec.add(ps.getTrangThai());
                dtmSach.addRow(vec);
            }
        }
    }

    private void xuLyClickTblKhuVuc() {
        int row = tblKhuVuc.getSelectedRow();
        dtmSach.setRowCount(0);
        if (row > -1) {
            String maKhu = tblKhuVuc.getValueAt(row, 0) + "";
            int khu = Integer.parseInt(maKhu);  // Sửa từ getInteger thành parseInt
            loadDataLenBangKeTheoMa(khu);
        }

        int rowCount = tblKe.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String maKe = tblKe.getValueAt(i, 0) + "";  // Sửa lấy từ bảng tblKe thay vì tblKhuVuc
            int ke = Integer.parseInt(maKe);  // Sửa từ getInteger thành parseInt
            loadDataLenBangSachTheoMa(ke);
        }
    }

    private void xuLyClickTblKe() {
        int row = tblKe.getSelectedRow();
        dtmSach.setRowCount(0);
        if (row > -1) {
            String maKe = tblKe.getValueAt(row, 0) + "";
            int ke = Integer.parseInt(maKe);  // Sửa từ getInteger thành parseInt
            loadDataLenBangSachTheoMa(ke);
            int maKhu = khuVucBUS.getKhu(ke);
            loadDataLenBangKhuVucTheoMa(maKhu);
        }
    }

    private void xuLyClickTblSach() {
        int row = tblSach.getSelectedRow();
        if (row > -1) {
            String maSach = tblSach.getValueAt(row, 0) + "";
            int sach = Integer.parseInt(maSach);
            int maKe = khuVucBUS.getKe(sach);
            loadDataLenBangKeTheoMaSach(maKe);
            int maKhu = khuVucBUS.getKhu(maKe);
            loadDataLenBangKhuVucTheoMa(maKhu);
        }
    }

    private void xuLyThemKhu(){
        boolean flag = khuVucBUS.themKhu();
        loadDataLenBangKhuVuc();
    }

    private void xuLyXoaKhu(){
        int row = tblKhuVuc.getSelectedRow();
        if (row > -1) {
            String maKhu = tblKhuVuc.getValueAt(row, 0) + "";
            String soLuong = tblKhuVuc.getValueAt(row, 1) + "";
            int khu = Integer.parseInt(maKhu);
            int sl = Integer.parseInt(soLuong);
            if(sl != 0) {
                MyDialog dlg = new MyDialog("Khu có chứa kệ!!!", MyDialog.ERROR_DIALOG);
            } else {
                // Thực hiện việc xóa khu
                boolean flag = khuVucBUS.xoaKhu(khu);
                if(flag) {
                    loadDataLenBangKhuVuc();
                }else {
                    new MyDialog("Xóa kệ thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
        }
    }


    private void xuLyThemKe(){
        int row = tblKhuVuc.getSelectedRow();
        dtmKe.setRowCount(0);
        if (row > -1) {
            String maKhu = tblKhuVuc.getValueAt(row, 0) + "";
            int khu = Integer.parseInt(maKhu);
            boolean flag = khuVucBUS.themKeSach(khu);// Sửa từ getInteger thành parseInt
            loadDataLenBangKeTheoMa(khu);
            loadDataLenBangKhuVuc();
        }else {
            new MyDialog("Chưa chọn khu để thêm kệ!", MyDialog.ERROR_DIALOG);
        }
    }

    private void xuLyXoaKe() {
        int row = tblKe.getSelectedRow();
        if (row > -1) {
            String maKe = tblKe.getValueAt(row, 0) + "";
            String soLuong = tblKe.getValueAt(row, 1) + "";
            int ke = Integer.parseInt(maKe);
            int maKhu = khuVucBUS.getKhu(ke);
            int sl = Integer.parseInt(soLuong);

            if (sl != 0) {
                new MyDialog("Kệ có chứa sách!!!", MyDialog.ERROR_DIALOG);
            } else {
                boolean flag = khuVucBUS.xoaKe(ke);

                if (flag) {
                    loadDataLenBangKeTheoMa(maKhu);
                    loadDataLenBangKhuVuc();
                } else {
                    new MyDialog("Xóa kệ thất bại!", MyDialog.ERROR_DIALOG);
                }
            }
        } else {
            new MyDialog("Chưa chọn hàng trong bảng Kệ hoặc Khu Vực!", MyDialog.ERROR_DIALOG);
        }
    }


    private void xuLyThemSach(){
        int row = tblKe.getSelectedRow();
        if(row <= -1){
            new MyDialog("Chưa chọn kệ để thêm sách!", MyDialog.ERROR_DIALOG);
            return;
        }else
            themSachVaoKe.loadDataLenTable();
        themSachVaoKe.setVisible(true);
        dtmSach.setRowCount(0);
        if (row > -1) {
            String maKe = tblKe.getValueAt(row, 0) + "";
            int ke = Integer.parseInt(maKe);
            if (themSachVaoKe.sachThem==null){
//                new MyDialog("Thêm sách thất bại!", MyDialog.ERROR_DIALOG);
                return;
            }
            int maSach = themSachVaoKe.sachThem.getMaSach();
            boolean flag = khuVucBUS.themSachVaoKe(maSach,ke);
            if (flag) {
                new MyDialog("Thêm sách thành công!", MyDialog.SUCCESS_DIALOG);
                loadDataLenBangKe();
                loadDataLenBangSach();
                loadDataLenBangKhuVuc();
            } else {
                new MyDialog("Thêm sách thất bại!", MyDialog.ERROR_DIALOG);
            }
        }
    }

    private void xuLyXoaSach() {
        int row = tblSach.getSelectedRow();
        if (row > -1) {
            String maSach = tblSach.getValueAt(row, 0) + "";
            int sach = Integer.parseInt(maSach);
            int maKe = khuVucBUS.getKe(sach);
            int maKhu = khuVucBUS.getKhu(maKe);

            boolean flag = khuVucBUS.xoaSachTrenKe(sach);
            if (flag) {
                new MyDialog("Xóa sách thành công!", MyDialog.SUCCESS_DIALOG);
                loadDataLenBangKe();
                loadDataLenBangSach();
                loadDataLenBangKhuVuc();
            } else {
                new MyDialog("Xóa sách thất bại!", MyDialog.ERROR_DIALOG);
            }
        } else {
            new MyDialog("Chưa chọn hàng trong bảng Sách!", MyDialog.ERROR_DIALOG);
        }
    }


    private void xuLyTimKiem(){
        dtmSach.setRowCount(0);
        ArrayList<PhanSach> dsps = null;
        dsps = khuVucBUS.timListPhanSach(txtTimKiem.getText());
        for(PhanSach ps : dsps){
            Vector vec = new Vector();
            vec.add(ps.getMaSach());
            vec.add(ps.getMaPhanSach());
            vec.add(sachBUS.getTenSach(ps.getMaSach()));
            vec.add(ps.getTrangThai());
            dtmSach.addRow(vec);
        }
        MyDialog dlg = new MyDialog("Số kết quả tìm được: " + dsps.size(), MyDialog.INFO_DIALOG);
    }




}

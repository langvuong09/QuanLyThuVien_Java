package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.XuLyFileExcel;
import QuanLyThuVien.BUS.*;
import MyCustom.MyTable;
import QuanLyThuVien.DTO.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.*;

import static Main.Main.changLNF;
import MyCustom.TransparentPanel;
import com.toedter.calendar.JDateChooser;

public class PnQuanLyKhuVucGUI extends JPanel {
    private KhuVucBUS khuVucBUS = new KhuVucBUS();
    private SachBUS sachBUS = new SachBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();
    private DlgThemSachVaoKhuVuc themSachVaoKe = new DlgThemSachVaoKhuVuc();

    public PnQuanLyKhuVucGUI(){
        changLNF("Windows");
        addConTrolsKhuVuc();
        addEventsKhuVuc();
    }

    final Color colorPanel = new Color(247, 247, 247);
    MyTable tblKhuVuc, tblKe, tblSach;
    DefaultTableModel dtmSach, dtmKhu, dtmKe;
    JTextField txtTimKiem;
    JButton btnTimKiem, btnThemSach, btnXoaSach, btnThemKhu, btnXoaKhu, btnThemKe, btnXoaKe, btnReset;

    private void addConTrolsKhuVuc(){
        Font font = new Font("Tahoma", Font.PLAIN,16);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        int w = 1050;
        int h = 700;

        /*
        =========================================================================
                                    PANEL Khu vực
        =========================================================================
         */
        JPanel pnTableKhuVuc = new TransparentPanel();
        pnTableKhuVuc.setLayout(new BorderLayout());

        JPanel pnTitleKhuVuc = new TransparentPanel();
        JLabel lblTitleKhuVuc = new JLabel("Quản lý khu vực");
        lblTitleKhuVuc.setFont(new Font("Arial", Font.BOLD, 28));
        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(40, 40));
        pnTitleKhuVuc.add(lblTitleKhuVuc);
        pnTitleKhuVuc.add(btnReset);
        pnTableKhuVuc.add(pnTitleKhuVuc, BorderLayout.NORTH);

        //=================Quản l khu vực=====================//

        JPanel pnKhuVuc = new TransparentPanel();
        pnKhuVuc.setLayout(null);

        //=================PANEL INPUT===========
        int x =15,y=15;
        txtTimKiem = new JTextField(y);

        //=================Thông tin ==============
        JLabel lblTimKiem = new JLabel("Tìm tên sách:");
        lblTimKiem.setFont(font);
        txtTimKiem.setFont(font);
        lblTimKiem.setBounds(20,450,150,28);
        txtTimKiem.setBounds(140,450,200,28);

        pnKhuVuc.add(lblTimKiem);
        pnKhuVuc.add(txtTimKiem);

        //=================BUTTON===============

        btnThemKhu = new JButton("Thêm");
        btnXoaKhu = new JButton("Xoá");
        btnThemKe = new JButton("Thêm");
        btnXoaKe = new JButton("Xoá");
        btnThemSach = new JButton("Thêm");
        btnXoaSach = new JButton("Xoá");
        btnTimKiem = new JButton("Tìm");

        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnThemKhu.setFont(fontButton);
        btnXoaKhu.setFont(fontButton);
        btnThemKe.setFont(fontButton);
        btnXoaKe.setFont(fontButton);
        btnThemSach.setFont(fontButton);
        btnXoaSach.setFont(fontButton);
        btnTimKiem.setFont(fontButton);

        btnThemKhu.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoaKhu.setIcon(new ImageIcon("image/delete-icon.png"));
        btnThemKe.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoaKe.setIcon(new ImageIcon("image/delete-icon.png"));
        btnThemSach.setIcon(new ImageIcon("image/add-icon.png"));
        btnXoaSach.setIcon(new ImageIcon("image/delete-icon.png"));
        btnTimKiem.setIcon(new ImageIcon("image/Search-icon.png"));

        btnThemKhu.setBounds(520,240,110,40);
        btnXoaKhu.setBounds(670,240,110,40);
        btnThemKe.setBounds(520,530,110,40);
        btnXoaKe.setBounds(670,530,110,40);
        btnThemSach.setBounds(110,500,110,40);
        btnXoaSach.setBounds(260,500,110,40);
        btnTimKiem.setBounds(360,440,110,40);

        pnKhuVuc.add(btnThemKhu);
        pnKhuVuc.add(btnXoaKhu);
        pnKhuVuc.add(btnThemKe);
        pnKhuVuc.add(btnXoaKe);
        pnKhuVuc.add(btnThemSach);
        pnKhuVuc.add(btnXoaSach);
        pnKhuVuc.add(btnTimKiem);

        //=================TABLE=====================//

        //=================Bảng Khu vực==============

        dtmKhu = new DefaultTableModel();
        dtmKhu.addColumn("Mã khu vực");
        dtmKhu.addColumn("Số lượng kệ");
        tblKhuVuc = new MyTable(dtmKhu);

        tblKhuVuc.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKhuVuc.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TableColumnModel columnModelKhuVuc = tblKhuVuc.getColumnModel();
        columnModelKhuVuc.getColumn(0).setPreferredWidth(70);
        columnModelKhuVuc.getColumn(1).setPreferredWidth(70);
        JScrollPane scrTblKhuVuc = new JScrollPane(tblKhuVuc);
        scrTblKhuVuc.setPreferredSize(new Dimension(140,120));
        scrTblKhuVuc.setBounds(520,20,260,200);

        pnKhuVuc.add(scrTblKhuVuc);
        pnTableKhuVuc.add(pnKhuVuc);

        //=================Bảng kệ==========

        dtmKe = new DefaultTableModel();
        dtmKe.addColumn("Mã kệ");
        dtmKe.addColumn("Số lượng sách");
        tblKe = new MyTable(dtmKe);

        tblKe.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblKe.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TableColumnModel columnModelKe = tblKe.getColumnModel();
        columnModelKe.getColumn(0).setPreferredWidth(70);
        columnModelKe.getColumn(1).setPreferredWidth(70);
        JScrollPane scrTblKe = new JScrollPane(tblKe);
        scrTblKe.setPreferredSize(new Dimension(140,120));
        scrTblKe.setBounds(520,310,260,200);

        pnKhuVuc.add(scrTblKe);
        pnTableKhuVuc.add(pnKhuVuc);

        //====================Bảng sách====================

        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Mã phân sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Trạng thái");
        tblSach = new MyTable(dtmSach);

        tblSach.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblSach.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModelSach = tblSach.getColumnModel();
        columnModelSach.getColumn(0).setPreferredWidth(60);
        columnModelSach.getColumn(1).setPreferredWidth(100);
        columnModelSach.getColumn(2).setPreferredWidth(160);
        columnModelSach.getColumn(3).setPreferredWidth(80);
        JScrollPane scrTblSach = new JScrollPane(tblSach);
        scrTblSach.setPreferredSize(new Dimension(140,120));
        scrTblSach.setBounds(0,20,500,400);

        pnKhuVuc.add(scrTblSach);
        pnTableKhuVuc.add(pnKhuVuc);
        //====================================================
        loadDataLenBangKhuVuc();
        loadDataLenBangKe();
        loadDataLenBangSach();
        //=======================================================
        this.add(pnTableKhuVuc);
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
        themSachVaoKe.loadDataLenTable();
        themSachVaoKe.setVisible(true);
        int row = tblKe.getSelectedRow();
        dtmSach.setRowCount(0);
        if (row > -1) {
            String maKe = tblKe.getValueAt(row, 0) + "";
            int ke = Integer.parseInt(maKe);
            if (themSachVaoKe.sachThem==null){
                new MyDialog("Thêm sách thất bại!", MyDialog.ERROR_DIALOG);
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
        }else {
            new MyDialog("Chưa chọn kệ để thêm sách!", MyDialog.ERROR_DIALOG);
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


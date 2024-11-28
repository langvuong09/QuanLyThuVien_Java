package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.MyTable;
import QuanLyThuVien.BUS.LoaiBUS;
import QuanLyThuVien.BUS.PhanSachBUS;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.BUS.TacGiaBUS;
import QuanLyThuVien.DTO.PhanSach;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class DlgTimSach extends  JDialog {
    private SachBUS sachBUS = new SachBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private TacGiaBUS tacGiaBUS = new TacGiaBUS();
    private PhanSachBUS phanSachBUS = new PhanSachBUS();
    public static PhanSach sachTimDuoc = null;

    public DlgTimSach() {
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblSach;
    protected DefaultTableModel dtmSach;
    private JButton btnChon;

    private void addControls(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Nhập mã hoặc tên sách để tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã sách");
        dtmSach.addColumn("Mã phân sách");
        dtmSach.addColumn("Tên sách");
        dtmSach.addColumn("Trạng thái");
        tblSach = new MyTable(dtmSach);
        JScrollPane scrSach = new JScrollPane(tblSach);
        pnTable.add(scrSach, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        pnButton.add(btnChon);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonSach();
            }
        });
    }

    private void xuLyChonSach() {
        int row = tblSach.getSelectedRow();
        if (row > -1) {
            int maS = Integer.parseInt(tblSach.getValueAt(row, 0) + "");
            int maPS = Integer.parseInt(tblSach.getValueAt(row,1)+"");
            String tenSach = tblSach.getValueAt(row,2)+"";
            String trangThai = tblSach.getValueAt(row,3) + "";
            sachTimDuoc = new PhanSach(maPS,maS,trangThai);
        }
        this.dispose();
    }

    public void loadDataLenTable() {
        dtmSach.setRowCount(0);
        ArrayList<PhanSach> dsps = phanSachBUS.getListPhanSach();
        if (dsps != null) {
            for (PhanSach ps : dsps) {
                Vector vec = new Vector();
                vec.add(ps.getMaSach());
                vec.add(ps.getMaPhanSach());
                vec.add(sachBUS.getTenSach(ps.getMaSach()));
                vec.add(ps.getTrangThai());
                dtmSach.addRow(vec);
            }
        }
    }

    public void loadDataLenTable(String tuKhoa) {
        dtmSach.setRowCount(0);
        if(tuKhoa.equals("")){
            new MyDialog("chưa nhập thông tin tìm kiếm!!!", MyDialog.ERROR_DIALOG);
            return;
        }
        int maS = sachBUS.getMaSach(tuKhoa);
        ArrayList<PhanSach> dsps = phanSachBUS.getListTheoMa(maS);
        if (dsps != null) {
            for (PhanSach ps : dsps) {
                Vector vec = new Vector();
                vec.add(ps.getMaSach());
                vec.add(ps.getMaPhanSach());
                vec.add(sachBUS.getTenSach(ps.getMaSach()));
                vec.add(ps.getTrangThai());
                dtmSach.addRow(vec);
            }
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim().toLowerCase();
        loadDataLenTable(tuKhoa); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }
}

package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DAO.MyConnect;
import QuanLyThuVien.DTO.Sach;
import MyCustom.MyTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class DlgTimSach extends  JDialog {
    private SachBUS sachBUS = new SachBUS();
    public static Sach sachTimDuoc = null;

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
    private DefaultTableModel dtmSach;
    private JButton btnChon;

    private void addControls(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmSach = new DefaultTableModel();
        dtmSach.addColumn("Mã");
        dtmSach.addColumn("Loại");
        dtmSach.addColumn("NXB");
        dtmSach.addColumn("Tác giả");
        dtmSach.addColumn("Tên");
        dtmSach.addColumn("Giá");
        dtmSach.addColumn("Ghi chú");
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
            int ma = Integer.parseInt(tblSach.getValueAt(row, 0) + "");
            int loai = Integer.parseInt(tblSach.getValueAt(row, 1) + "");
            int nxb = Integer.parseInt(tblSach.getValueAt(row, 2) + "");
            int tacGia = Integer.parseInt(tblSach.getValueAt(row, 3) + "");
            String ten = tblSach.getValueAt(row, 4) + "";
            long gia = Long.parseLong(tblSach.getValueAt(row, 5) + "");
            String ghiChu = tblSach.getValueAt(row,6) + "";
            int trangThai = Integer.parseInt(tblSach.getValueAt(row, 7) + "");

            sachTimDuoc = new Sach(ma, loai, nxb, tacGia, ten, gia, ghiChu, trangThai);
        }
        this.dispose();
    }

    private void loadDataLenTable() {
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.getListSach();
        if (dss != null) {
            for (Sach s : dss) {
                Vector vec = new Vector();
                vec.add(s.getMaSach());
                vec.add(s.getMaLoaiSach());
                vec.add(s.getMaNXB());
                vec.add(s.getMaTacGia());
                vec.add(s.getTenSach());
                vec.add(s.getGiaSach());
                vec.add(s.getGhiChu());
                dtmSach.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmSach.setRowCount(0);
        ArrayList<Sach> dss = sachBUS.timKiemSach(tuKhoa);
        for (Sach s : dss) {
            Vector vec = new Vector();
            vec.add(s.getMaSach());
            vec.add(s.getMaLoaiSach());
            vec.add(s.getMaNXB());
            vec.add(s.getMaTacGia());
            vec.add(s.getTenSach());
            vec.add(s.getGiaSach());
            vec.add(s.getGhiChu());
            dtmSach.addRow(vec);
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }
}

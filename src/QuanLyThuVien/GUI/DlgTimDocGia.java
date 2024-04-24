package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.DTO.DocGia;
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

public class DlgTimDocGia extends  JDialog{
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    public static DocGia docGiaTimDuoc = null;

    public DlgTimDocGia() {
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblDocGia;
    private DefaultTableModel dtmDocGia;
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
        dtmDocGia = new DefaultTableModel();
        dtmDocGia.addColumn("Mã ĐG");
        dtmDocGia.addColumn("Họ");
        dtmDocGia.addColumn("Tên");
        dtmDocGia.addColumn("Giới tính");
        dtmDocGia.addColumn("SĐT");
        dtmDocGia.addColumn("Gmail");
        tblDocGia = new MyTable(dtmDocGia);
        JScrollPane scrDocGia = new JScrollPane(tblDocGia);
        pnTable.add(scrDocGia, BorderLayout.CENTER);
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
                xuLyChonDocGia();
            }
        });
    }

    private void xuLyChonDocGia() {
        int row = tblDocGia.getSelectedRow();
        if (row > -1) {
            int ma = Integer.parseInt(tblDocGia.getValueAt(row, 0) + "");
            String ho = tblDocGia.getValueAt(row, 1) + "";
            String ten = tblDocGia.getValueAt(row, 2) + "";
            String gioiTinh = tblDocGia.getValueAt(row, 3) + "";
            String SDT = tblDocGia.getValueAt(row, 4) + "";
            String gmail = tblDocGia.getValueAt(row, 5) + "";

            docGiaTimDuoc = new DocGia(ma, ho, ten, gioiTinh, SDT, gmail,1);
        }
        this.dispose();
    }

    private void loadDataLenTable() {
        dtmDocGia.setRowCount(0);
        ArrayList<DocGia> dsdg = docGiaBUS.getListDocGia();
        if (dsdg != null) {
            for (DocGia dg : dsdg) {
                Vector vec = new Vector();
                vec.add(dg.getMaDocGia());
                vec.add(dg.getHo());
                vec.add(dg.getTen());
                vec.add(dg.getGioiTinh());
                vec.add(dg.getSDT());
                vec.add(dg.getGmail());
                dtmDocGia.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmDocGia.setRowCount(0);
        ArrayList<DocGia> dsdg = docGiaBUS.timKiemDocGia(tuKhoa);
        for (DocGia dg : dsdg) {
            Vector vec = new Vector();
            vec.add(dg.getMaDocGia());
            vec.add(dg.getHo());
            vec.add(dg.getTen());
            vec.add(dg.getGioiTinh());
            vec.add(dg.getSDT());
            vec.add(dg.getGmail());
            dtmDocGia.addRow(vec);
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }
}

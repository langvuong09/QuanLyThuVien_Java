package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.BUS.NhanVienBUS;
import QuanLyThuVien.DTO.PhieuMuon;
import QuanLyThuVien.BUS.PhieuMuonBUS;
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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DlgTimPhieuMuon extends JDialog{
    private PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    public static PhieuMuon phieuMuonTimDuoc = null;

    public DlgTimPhieuMuon() {
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblPhieuMuon;
    private DefaultTableModel dtmPhieuMuon;
    private JButton btnChon;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khóa tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmPhieuMuon = new DefaultTableModel();
        dtmPhieuMuon.addColumn("Mã PM");
        dtmPhieuMuon.addColumn("Đọc giả");
        dtmPhieuMuon.addColumn("Nhân viên");
        dtmPhieuMuon.addColumn("Ngày mượn");
        dtmPhieuMuon.addColumn("Hạn trả");
        dtmPhieuMuon.addColumn("Tổng tiền");
        tblPhieuMuon = new MyTable(dtmPhieuMuon);
        JScrollPane srcPhieuMuon = new JScrollPane(tblPhieuMuon);
        pnTable.add(srcPhieuMuon, BorderLayout.CENTER);
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
                xuLyChonPhieuMuon();
            }
        });
    }

    private void xuLyChonPhieuMuon() {
        int row = tblPhieuMuon.getSelectedRow();
        if (row > -1) {
            try {
                int ma = Integer.parseInt(tblPhieuMuon.getValueAt(row, 0) + "");
                String docGia = tblPhieuMuon.getValueAt(row, 1) + "";
                int maDocGia = docGiaBUS.getMaDocGia(docGia);
                String nhanVien = tblPhieuMuon.getValueAt(row, 2) + "";
                int maNhanVien = nhanVienBUS.getMaNhanVien(nhanVien);
                String ngayMuon = tblPhieuMuon.getValueAt(row, 3) + "";
                String hanTra = tblPhieuMuon.getValueAt(row, 5) + "";
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayM = null;
                Date hanT = null;
                if (ngayMuon != null && !ngayMuon.isEmpty() && hanTra != null && !hanTra.isEmpty()) {
                    ngayM = sdf.parse(ngayMuon);
                    hanT = sdf.parse(hanTra);
                }
                long tongTien = Long.parseLong(tblPhieuMuon.getValueAt(row, 6) + "");

                phieuMuonTimDuoc = new PhieuMuon(ma, maDocGia, maNhanVien, ngayM, hanT, tongTien);

                this.dispose();
            } catch (ParseException | NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadDataLenTable() {
        dtmPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = phieuMuonBUS.getListPhieuMuon();
        if (dspm != null) {
            for (PhieuMuon pm : dspm) {
                Vector vec = new Vector();
                vec.add(pm.getMaPhieuMuon());
                vec.add(docGiaBUS.getTenDocGia(pm.getMaDocGia()));
                vec.add(nhanVienBUS.getTenNhanVien(pm.getMaNhanVien()));
                vec.add(pm.getNgayMuon());
                vec.add(pm.getNgayTra());
                vec.add(pm.getTongTien());
                dtmPhieuMuon.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = phieuMuonBUS.timKiemPhieuMuon(tuKhoa);
        for (PhieuMuon pm : dspm) {
            Vector vec = new Vector<>();
            vec.add(pm.getMaPhieuMuon());
            vec.add(docGiaBUS.getTenDocGia(pm.getMaDocGia()));
            vec.add(nhanVienBUS.getTenNhanVien(pm.getMaNhanVien()));
            vec.add(pm.getNgayMuon());
            vec.add(pm.getNgayTra());
            vec.add(pm.getTongTien());
            dtmPhieuMuon.addRow(vec);
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa);
    }
}

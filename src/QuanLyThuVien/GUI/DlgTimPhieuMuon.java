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
    private DlgTimSachMuon sachMuonGUI = new DlgTimSachMuon();
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
    private JTable tblTimPhieuMuon;
    private DefaultTableModel dtmTimPhieuMuon;
    private JButton btnChon;

    private void addControls() {
        Container conn = getContentPane();
        conn.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Mã phiếu mượn cần tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        conn.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmTimPhieuMuon = new DefaultTableModel();
        dtmTimPhieuMuon.addColumn("Mã PM");
        dtmTimPhieuMuon.addColumn("Đọc giả");
        dtmTimPhieuMuon.addColumn("Nhân viên");
        dtmTimPhieuMuon.addColumn("Ngày mượn");
        dtmTimPhieuMuon.addColumn("Hạn trả");
        dtmTimPhieuMuon.addColumn("Tổng tiền");
        tblTimPhieuMuon = new MyTable(dtmTimPhieuMuon);
        JScrollPane srcPhieuMuon = new JScrollPane(tblTimPhieuMuon);
        pnTable.add(srcPhieuMuon, BorderLayout.CENTER);
        conn.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        pnButton.add(btnChon);
        conn.add(pnButton, BorderLayout.SOUTH);

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
        int row = tblTimPhieuMuon.getSelectedRow();
        if (row > -1) {
            try {
                int ma = Integer.parseInt(tblTimPhieuMuon.getValueAt(row, 0) + "");
                String docGia = tblTimPhieuMuon.getValueAt(row, 1) + "";
                int maDocGia = docGiaBUS.getMaDocGia(docGia);
                String nhanVien = tblTimPhieuMuon.getValueAt(row, 2) + "";
                int maNhanVien = nhanVienBUS.getMaNhanVien(nhanVien);
                String ngayMuon = tblTimPhieuMuon.getValueAt(row, 3) + "";
                String hanTra = tblTimPhieuMuon.getValueAt(row, 4) + ""; // Sửa index thành 4
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayM = null;
                Date hanT = null;
                if (!ngayMuon.isEmpty() && !hanTra.isEmpty()) { // Kiểm tra chuỗi không rỗng trước khi chuyển đổi
                    ngayM = sdf.parse(ngayMuon);
                    hanT = sdf.parse(hanTra);
                }
                long tongTien = Long.parseLong(tblTimPhieuMuon.getValueAt(row, 5) + ""); // Sửa index thành 5

                phieuMuonTimDuoc = new PhieuMuon(ma, maDocGia, maNhanVien, ngayM, hanT, tongTien);
                this.dispose();
            } catch (NumberFormatException | ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadDataLenTable() {
        phieuMuonBUS.docListPhieuMuon();
        dtmTimPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = phieuMuonBUS.getListPhieuMuon();
        if (dspm != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (PhieuMuon pm : dspm) {
                Vector vec = new Vector();
                vec.add(pm.getMaPhieuMuon());
                vec.add(docGiaBUS.getTenDocGia(pm.getMaDocGia()));
                vec.add(nhanVienBUS.getTenNhanVien(pm.getMaNhanVien()));
                vec.add(sdf.format(pm.getNgayMuon())); // Định dạng ngày mượn
                vec.add(sdf.format(pm.getNgayTra())); // Định dạng hạn trả
                vec.add(pm.getTongTien());
                dtmTimPhieuMuon.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmTimPhieuMuon.setRowCount(0);
        ArrayList<PhieuMuon> dspm = phieuMuonBUS.timKiemPhieuMuon(tuKhoa);
        if (dspm != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (PhieuMuon pm : dspm) {
                Vector vec = new Vector<>();
                vec.add(pm.getMaPhieuMuon());
                vec.add(docGiaBUS.getTenDocGia(pm.getMaDocGia()));
                vec.add(nhanVienBUS.getTenNhanVien(pm.getMaNhanVien()));
                vec.add(sdf.format(pm.getNgayMuon())); // Định dạng ngày mượn
                vec.add(sdf.format(pm.getNgayTra())); // Định dạng hạn trả
                vec.add(pm.getTongTien());
                dtmTimPhieuMuon.addRow(vec);
            }
        }
    }

    private void search(){
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa);
    }
}

package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.DocGiaBUS;
import QuanLyThuVien.BUS.NhanVienBUS;
import QuanLyThuVien.BUS.PhieuMuonBUS;
import QuanLyThuVien.DTO.PhieuTra;
import QuanLyThuVien.BUS.PhieuTraBUS;
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

public class DlgTimPhieuTra extends JDialog{
    private PhieuTraBUS phieuTraBUS = new PhieuTraBUS();
    private PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    private DocGiaBUS docGiaBUS = new DocGiaBUS();
    private NhanVienBUS nhanVienBUS = new NhanVienBUS();
    private DlgTimSachMuon sachMuonGUI = new DlgTimSachMuon();
    public static PhieuTra phieuTraTimDuoc = null;

    public DlgTimPhieuTra() {
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblTimPhieuTra;
    private DefaultTableModel dtmTimPhieuTra;
    private JButton btnChon;

    private void addControls() {
        Container conn = getContentPane();
        conn.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Mã phiếu trả cần tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        conn.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmTimPhieuTra = new DefaultTableModel();
        dtmTimPhieuTra.addColumn("Mã PT");
        dtmTimPhieuTra.addColumn("Mã PM");
        dtmTimPhieuTra.addColumn("Đọc giả");
        dtmTimPhieuTra.addColumn("Nhân viên");
        dtmTimPhieuTra.addColumn("Ngày mượn");
        dtmTimPhieuTra.addColumn("Ngày trả");
        tblTimPhieuTra = new MyTable(dtmTimPhieuTra);
        JScrollPane srcPhieuTra = new JScrollPane(tblTimPhieuTra);
        pnTable.add(srcPhieuTra, BorderLayout.CENTER);
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
                xuLyChonPhieuTra();
            }
        });
    }

    private void xuLyChonPhieuTra() {
        int row = tblTimPhieuTra.getSelectedRow();
        if (row > -1) {
            try {
                int ma = Integer.parseInt(tblTimPhieuTra.getValueAt(row, 0) + "");
                int maPM = Integer.parseInt(tblTimPhieuTra.getValueAt(row, 1) + "");
                String docGia = tblTimPhieuTra.getValueAt(row, 2) + "";
                int maDocGia = docGiaBUS.getMaDocGia(docGia);
                String nhanVien = tblTimPhieuTra.getValueAt(row, 3) + "";
                int maNhanVien = nhanVienBUS.getMaNhanVien(nhanVien);
                String ngayMuon = tblTimPhieuTra.getValueAt(row, 4) + "";
                String ngayTra = tblTimPhieuTra.getValueAt(row, 5) + ""; // Sửa index thành 4
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngayM = null;
                Date hanT = null;
                if (!ngayMuon.isEmpty() && !ngayTra.isEmpty()) { // Kiểm tra chuỗi không rỗng trước khi chuyển đổi
                    ngayM = sdf.parse(ngayMuon);
                    hanT = sdf.parse(ngayTra);
                }

                phieuTraTimDuoc = new PhieuTra(ma, maPM, maDocGia, maNhanVien, hanT);
                this.dispose();
            } catch (NumberFormatException | ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadDataLenTable() {
        dtmTimPhieuTra.setRowCount(0);
        ArrayList<PhieuTra> dspt = phieuTraBUS.getListPhieuTra();
        if (dspt != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (PhieuTra pt : dspt) {
                Vector vec = new Vector();
                vec.add(pt.getMaPhieuTra());
                vec.add(pt.getMaPhieuMuon());
                vec.add(docGiaBUS.getTenDocGia(pt.getMaDocGia()));
                vec.add(nhanVienBUS.getTenNhanVien(pt.getMaNhanVien()));
                vec.add(sdf.format(phieuMuonBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon())); // Định dạng ngày mượn
                vec.add(sdf.format(pt.getNgayTraThuc())); // Định dạng hạn trả
                dtmTimPhieuTra.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa) {
        dtmTimPhieuTra.setRowCount(0);
        ArrayList<PhieuTra> dspt = phieuTraBUS.timKiemPhieuTra(tuKhoa);
        if (dspt != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (PhieuTra pt : dspt) {
                Vector vec = new Vector();
                vec.add(pt.getMaPhieuTra());
                vec.add(pt.getMaPhieuMuon());
                vec.add(docGiaBUS.getTenDocGia(pt.getMaDocGia()));
                vec.add(nhanVienBUS.getTenNhanVien(pt.getMaNhanVien()));
                vec.add(sdf.format(phieuMuonBUS.getPhieuMuon(String.valueOf(pt.getMaPhieuMuon())).getNgayMuon())); // Định dạng ngày mượn
                vec.add(sdf.format(pt.getNgayTraThuc())); // Định dạng hạn trả
                dtmTimPhieuTra.addRow(vec);
            }
        }
    }

    private void search(){
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa);
    }
}

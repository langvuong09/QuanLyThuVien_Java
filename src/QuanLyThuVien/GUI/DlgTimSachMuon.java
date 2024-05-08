package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.CTPhieuMuon;
import MyCustom.MyTable;
import QuanLyThuVien.DTO.DocGia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class DlgTimSachMuon extends JDialog{
    private CTPhieuMuonBUS ctPhieuMuonBUS = new CTPhieuMuonBUS();
    private SachBUS sachBUS = new SachBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private NXBBUS nxbBUS = new NXBBUS();
    private TacGiaBUS tacGiaBUS = new TacGiaBUS();
    protected String maPm = "0";
    public static CTPhieuMuon ctPhieuMuonTimDuoc = null;

    public DlgTimSachMuon(){
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblTimCTPhieuMuon;
    protected DefaultTableModel dtmTimCTPhieuMuon;
    private JButton btnChon;

    private void addControls(){
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahooma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Nhập mã sách cần tìm:");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmTimCTPhieuMuon = new DefaultTableModel();
        dtmTimCTPhieuMuon.addColumn("Mã sách");
        dtmTimCTPhieuMuon.addColumn("Tên sách");
        tblTimCTPhieuMuon = new MyTable(dtmTimCTPhieuMuon);
        JScrollPane srcTimCTPhieuMuon = new JScrollPane(tblTimCTPhieuMuon);
        pnTable.add(srcTimCTPhieuMuon, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        pnButton.add(btnChon);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120,40));

        loadDataLenTable(this.maPm);
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
                xuLyChonCTPhieuMuon();
            }
        });
    }

    public void xuLyChonCTPhieuMuon() {
        int row = tblTimCTPhieuMuon.getSelectedRow();
        if (row > -1) {
            int maSach = Integer.parseInt(tblTimCTPhieuMuon.getValueAt(row, 0) + "");
            String maPhieuMuonString = this.maPm;
            int maPhieuMuon = Integer.parseInt(maPhieuMuonString);

            ctPhieuMuonTimDuoc = new CTPhieuMuon(maPhieuMuon, maSach, 0);

        }
        this.dispose();
    }


    public void loadDataLenTable(String maPM) {
        dtmTimCTPhieuMuon.setRowCount(0);
        ArrayList<CTPhieuMuon> dsctpm = ctPhieuMuonBUS.getListCTPhieuMuonTheoMaPMTheoTrangThai(maPM);
        if(dsctpm != null){
            for(CTPhieuMuon ctpm : dsctpm){
            Vector vec = new Vector();
                vec.add(ctpm.getMaSach());
                vec.add(sachBUS.getTenSachMuon(ctpm.getMaSach()));
                dtmTimCTPhieuMuon.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa, String maPM) {
        dtmTimCTPhieuMuon.setRowCount(0);
        ArrayList<CTPhieuMuon> dsctpm = ctPhieuMuonBUS.timKiemCTPhieuMuon(tuKhoa, maPM);
        for(CTPhieuMuon ctpm : dsctpm){
            Vector vec = new Vector();
            vec.add(ctpm.getMaSach());
            vec.add(sachBUS.getTenSach(ctpm.getMaSach()));
            dtmTimCTPhieuMuon.addRow(vec);
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa, this.maPm); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }

    public void setMaPm(String maPM){
        this.maPm = maPM;
    }
}

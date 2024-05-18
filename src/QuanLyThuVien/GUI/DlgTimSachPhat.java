package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.*;
import QuanLyThuVien.DTO.CTPhieuTra;
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

public class DlgTimSachPhat extends JDialog{
    private CTPhieuTraBUS ctPhieuTraBUS = new CTPhieuTraBUS();
    private SachBUS sachBUS = new SachBUS();
    private LoaiBUS loaiBUS = new LoaiBUS();
    private NXBBUS nxbBUS = new NXBBUS();
    private TacGiaBUS tacGiaBUS = new TacGiaBUS();
    protected String maPt = "0";
    public static CTPhieuTra ctPhieuTraTimDuoc = null;

    public DlgTimSachPhat(){
        addControls();
        addEvents();

        this.setSize(800, 500);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblTimCTPhieuTra;
    protected DefaultTableModel dtmTimCTPhieuTra;
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
        dtmTimCTPhieuTra = new DefaultTableModel();
        dtmTimCTPhieuTra.addColumn("Mã sách");
        dtmTimCTPhieuTra.addColumn("Tên sách");
        tblTimCTPhieuTra = new MyTable(dtmTimCTPhieuTra);
        JScrollPane srcTimCTPhieuTra = new JScrollPane(tblTimCTPhieuTra);
        pnTable.add(srcTimCTPhieuTra, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnChon.setFont(font);
        pnButton.add(btnChon);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120,40));

        loadDataLenTable(this.maPt);
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
                xuLyChonCTPhieuTra();
            }
        });
    }

    public void xuLyChonCTPhieuTra() {
        int row = tblTimCTPhieuTra.getSelectedRow();
        if (row > -1) {
            int maSach = Integer.parseInt(tblTimCTPhieuTra.getValueAt(row, 0) + "");
            String maPhieuTraString = this.maPt;
            int maPhieuMuon = Integer.parseInt(maPhieuTraString);

            ctPhieuTraTimDuoc = new CTPhieuTra(maPhieuMuon, maSach);

        }
        this.dispose();
    }


    public void loadDataLenTable(String maPT) {
        dtmTimCTPhieuTra.setRowCount(0);
        ArrayList<CTPhieuTra> dsctpt = ctPhieuTraBUS.getListCTPhieuTraTheoMaPT(maPT);
        if(dsctpt != null){
            for(CTPhieuTra ctpt : dsctpt){
                Vector vec = new Vector();
                vec.add(ctpt.getMaSach());
                vec.add(sachBUS.getTenSach(ctpt.getMaSach()));
                dtmTimCTPhieuTra.addRow(vec);
            }
        }
    }

    private void loadDataLenTable(String tuKhoa, String maPT) {
        dtmTimCTPhieuTra.setRowCount(0);
        ArrayList<CTPhieuTra> dsctpt = ctPhieuTraBUS.timKiemCTPhieuTra(tuKhoa, maPT);
        for(CTPhieuTra ctpt : dsctpt){
            Vector vec = new Vector();
            vec.add(ctpt.getMaSach());
            vec.add(sachBUS.getTenSach(ctpt.getMaSach()));
            dtmTimCTPhieuTra.addRow(vec);
        }
    }

    private void search() {
        String tuKhoa = txtTuKhoa.getText().trim();
        loadDataLenTable(tuKhoa, this.maPt); // Gọi phương thức tìm kiếm khi có sự thay đổi trong ô nhập liệu
    }

    public void setMaPt(String maPT){
        this.maPt = maPT;
    }
}

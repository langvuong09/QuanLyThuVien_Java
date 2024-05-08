package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.PhieuPhatBUS;
import MyCustom.MyDialog;
import QuanLyThuVien.BUS.SachBUS;
import QuanLyThuVien.DTO.PhieuPhat;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class XuatPhieuPhatGUI extends JDialog{
    private PhieuPhatBUS phieuPhatBUS = new PhieuPhatBUS();
    private SachBUS sachBUS = new SachBUS();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInPhieuPhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane txtPhieuPhat;

    public XuatPhieuPhatGUI() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setModal(true);
//        this.setSize(700,500);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
//        customEvents();
    }

    private ArrayList<Vector> dsPhieuPhat;
    private int maPP;
    private String nhanVien, docGia;
    private String ngayMuon, ngayTraThuc;
    private long thanhTien;

    public XuatPhieuPhatGUI(ArrayList<Vector> dsPhieuPhat,int maPP, Object docGia, Object nhanVien,String ngayTraThuc, long thanhTien) {
        this();
        this.maPP = maPP;
        this.docGia = (String) docGia;
        this.nhanVien = (String) nhanVien;
        this.ngayMuon = ngayMuon;
        this.ngayTraThuc = ngayTraThuc;
        this.dsPhieuPhat = dsPhieuPhat;
        this.thanhTien = thanhTien;
        xuLyHienThiPhieuPhat();
    }

    private void xuLyHienThiPhieuPhat() {
        txtPhieuPhat.setContentType("text/html");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");

        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-collapse: collapse;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:20px"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>PHIẾU PHẠT</h1>";
        hd += "<table style='width: 80%; margin: auto; border: none'>"; // Thiết lập chiều rộng là 80% của phần tử cha và canh giữa bảng
        hd += "<tr style='border: none'>";
        hd += "<td style='vertical-align: top;'>";
        hd += "<pre>Mã phiếu: "+maPP+"<br/></pre>";
        hd += "<pre>Nhân viên: " + nhanVien + "<br/>";
        hd += "Đọc giả: " + docGia + "</pre>";
        hd += "</td>";
        hd += "<td style='vertical-align: top;'>";
        hd += "<pre>                <br/></pre>";
        hd += "<pre>    Ngày trả: " + ngayTraThuc + "</pre>";
        hd += "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%; margin: auto;'>";
        hd += "<tr style='font-family: Tahoma; font-size: 14px;'>"
                + "<th>Mã Sách</th>"
                + "<th>Tên Sách</th>"
                + "<th>Lý do>"
                + "</tr>";
        for (Vector vec : dsPhieuPhat) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + vec.get(2) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(3) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(6) + "</td>";
            hd += "</tr>";
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng tiền phạt: </td>";
        hd += "<td style='text-align:center;'>" + dcf.format(thanhTien) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div><pre>  </pre></div>";
        hd += "<div><pre>================================================================= </pre></div>";
        hd += "<div><pre>  </pre></div>";
        txtPhieuPhat.setText(hd);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    public void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPhieuPhat = new javax.swing.JEditorPane();
        btnInPhieuPhat = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Phiếu phạt");
        jPanel1.add(jLabel1);

        btnInPhieuPhat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInPhieuPhat.setText("In hoá đơn");
        btnInPhieuPhat.setPreferredSize(new java.awt.Dimension(128, 45));
        btnInPhieuPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuPhatActionPerformed(evt);
            }
        });
        jPanel2.add(btnInPhieuPhat);

        jScrollPane1.setViewportView(txtPhieuPhat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE,  611, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup())
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0 , Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }

    private void btnInPhieuPhatActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (!txtPhieuPhat.getText().equals("")) {
                txtPhieuPhat.print();
                this.dispose();
            }
        } catch (PrinterException ex) {
        }
    }

    // End of variables declaration//GEN-END:variables
}

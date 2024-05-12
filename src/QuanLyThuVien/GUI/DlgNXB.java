package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.NXBBUS;
import QuanLyThuVien.DTO.NXB;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DlgNXB extends javax.swing.JDialog {

    DefaultTableModel dtmNXB;

    public DlgNXB() {
        initComponents();
        dtmNXB = new DefaultTableModel();
        dtmNXB.addColumn("Mã NXB");
        dtmNXB.addColumn("Tên NXB");
        tblNXB.setModel(dtmNXB);
        loadDataLenTblNXB();
        this.setLocationRelativeTo(null);
    }

    NXBBUS nxbBUS = new NXBBUS();

    private void loadDataLenTblNXB() {
        dtmNXB.setRowCount(0);
        ArrayList<NXB> dsnxb = nxbBUS.getListNXB();
        if (dsnxb != null) {
            for (NXB nxb : dsnxb) {
                Vector vec = new Vector();
                vec.add(nxb.getMaNXB());
                vec.add(nxb.getTenNXB());
                dtmNXB.addRow(vec);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNXB = new MyTable();
        jPanel3 = new javax.swing.JPanel();
        pnMaNXB = new javax.swing.JPanel();
        lblMaNXB = new javax.swing.JLabel();
        txtMaNXB = new javax.swing.JTextField();
        pnTenNXB = new javax.swing.JPanel();
        lblTenNXB = new javax.swing.JLabel();
        txtTenNXB = new javax.swing.JTextField();
        pnButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setModal(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÀ XUẤT BẢN");
        jPanel1.add(jLabel1);

        tblNXB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNXB.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã nxb", "Tên nxb"
                }
        ));
        tblNXB.getTableHeader().setReorderingAllowed(false);
        tblNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNXBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNXB);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
                pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnTableLayout.setVerticalGroup(
                pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnTableLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        lblMaNXB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMaNXB.setText("Mã nxb:");
        lblMaNXB.setPreferredSize(new java.awt.Dimension(63, 22));
        pnMaNXB.add(lblMaNXB);

        txtMaNXB.setEditable(false);
        txtMaNXB.setColumns(15);
        txtMaNXB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnMaNXB.add(txtMaNXB);

        jPanel3.add(pnMaNXB);

        lblTenNXB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenNXB.setText("Tên nxb:");
        pnTenNXB.add(lblTenNXB);

        txtTenNXB.setColumns(15);
        txtTenNXB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnTenNXB.add(txtTenNXB);

        jPanel3.add(pnTenNXB);

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setPreferredSize(new java.awt.Dimension(80, 35));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnButton.add(btnThem);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setPreferredSize(new java.awt.Dimension(80, 35));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnButton.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setPreferredSize(new java.awt.Dimension(80, 35));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnButton.add(btnXoa);

        jPanel3.add(pnButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                        .addComponent(pnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiMouseClicked
        int row = tblNXB.getSelectedRow();
        if (row > -1) {
            String maNXB = tblNXB.getValueAt(row, 0) + "";
            String tenNXB = tblNXB.getValueAt(row, 1) + "";
            txtMaNXB.setText(maNXB);
            txtTenNXB.setText(tenNXB);
        }
    }//GEN-LAST:event_tblLoaiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (nxbBUS.themNXB(dtmNXB.getRowCount(), txtTenNXB.getText())) {
            loadDataLenTblNXB();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            String ma = txtMaNXB.getText();
            if (nxbBUS.xoaNXB(ma)) {
                loadDataLenTblNXB();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String ma = txtMaNXB.getText();
        String ten = txtTenNXB.getText();
        if (nxbBUS.suaNXB(ma, ten)) {
            loadDataLenTblNXB();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaNXB;
    private javax.swing.JLabel lblTenNXB;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnMaNXB;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnTenNXB;
    private javax.swing.JTable tblNXB;
    private javax.swing.JTextField txtMaNXB;
    private javax.swing.JTextField txtTenNXB;
    // End of variables declaration//GEN-END:variables
}

package QuanLyThuVien.GUI;

import MyCustom.MyDialog;
import MyCustom.MyTable;
import QuanLyThuVien.BUS.TacGiaBUS;
import QuanLyThuVien.DTO.TacGia;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class DlgTacGia extends javax.swing.JDialog {

    DefaultTableModel dtmTacGia;

    public DlgTacGia() {
        initComponents();
        dtmTacGia = new DefaultTableModel();
        dtmTacGia.addColumn("Mã tác giả");
        dtmTacGia.addColumn("Tên tác giả");
        tblTacGia.setModel(dtmTacGia);
        loadDataLenTblTacGia();
        this.setLocationRelativeTo(null);
    }

    TacGiaBUS tacGiaBUS = new TacGiaBUS();

    private void loadDataLenTblTacGia() {
        dtmTacGia.setRowCount(0);
        ArrayList<TacGia> dstg = tacGiaBUS.getListTacGia();
        if (dstg != null) {
            for (TacGia tg : dstg) {
                Vector vec = new Vector();
                vec.add(tg.getMaTacGia());
                vec.add(tg.getTenTacGia());
                dtmTacGia.addRow(vec);
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
        tblTacGia = new MyTable();
        jPanel3 = new javax.swing.JPanel();
        pnMaTG = new javax.swing.JPanel();
        lblMaTG = new javax.swing.JLabel();
        txtMaTG = new javax.swing.JTextField();
        pnTenNXB = new javax.swing.JPanel();
        lblTenTG = new javax.swing.JLabel();
        txtTenTG = new javax.swing.JTextField();
        pnButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setModal(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ TÁC GIẢ");
        jPanel1.add(jLabel1);

        tblTacGia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTacGia.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã tác giả", "Tên tác giả"
                }
        ));
        tblTacGia.getTableHeader().setReorderingAllowed(false);
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNXBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTacGia);

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

        lblMaTG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMaTG.setText("Mã tg:");
        lblMaTG.setPreferredSize(new java.awt.Dimension(63, 22));
        pnMaTG.add(lblMaTG);

        txtMaTG.setEditable(false);
        txtMaTG.setColumns(15);
        txtMaTG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnMaTG.add(txtMaTG);

        jPanel3.add(pnMaTG);

        lblTenTG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTenTG.setText("Tên tg:");
        pnTenNXB.add(lblTenTG);

        txtTenTG.setColumns(15);
        txtTenTG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pnTenNXB.add(txtTenTG);

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
        int row = tblTacGia.getSelectedRow();
        if (row > -1) {
            String maTG = tblTacGia.getValueAt(row, 0) + "";
            String tenTG = tblTacGia.getValueAt(row, 1) + "";
            txtMaTG.setText(maTG);
            txtTenTG.setText(tenTG);
        }
    }//GEN-LAST:event_tblLoaiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (tacGiaBUS.themTacGia(dtmTacGia.getRowCount(), txtTenTG.getText())) {
            loadDataLenTblTacGia();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
        if (dlg.OK_OPTION == dlg.getAction()) {
            String ma = txtMaTG.getText();
            if (tacGiaBUS.xoaTacGia(ma)) {
                loadDataLenTblTacGia();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String ma = txtMaTG.getText();
        String ten = txtTenTG.getText();
        if (tacGiaBUS.suaTacGia(ma,ten)) {
            loadDataLenTblTacGia();
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
    private javax.swing.JLabel lblMaTG;
    private javax.swing.JLabel lblTenTG;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnMaTG;
    private javax.swing.JPanel pnTable;
    private javax.swing.JPanel pnTenNXB;
    private javax.swing.JTable tblTacGia;
    private javax.swing.JTextField txtMaTG;
    private javax.swing.JTextField txtTenTG;
    // End of variables declaration//GEN-END:variables
}

package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.ThongKeBUS;
import QuanLyThuVien.DAO.ThongKeDAO;
import QuanLyThuVien.DTO.ThongKe;
import MyCustom.TransparentPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

import java.awt.*;

import static Main.Main.changLNF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;

public class PnQuanLyThongKeGUI extends JPanel {

    public PnQuanLyThongKeGUI() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    ThongKeBUS thongKeBUS = new ThongKeBUS();
    final Color colorPanel = new Color(173, 173, 173);
    JLabel lblThongKeSach, lblThongKeDocGia, lblThongKeNhanVien, lblThongKePM, lblThongKePT, lblThongKePP, lblThongKeDoanhThu;
    JLabel lblDoanhThuQuy1, lblDoanhThuQuy2, lblDoanhThuQuy3, lblDoanhThuQuy4, lblTongDoanhThu;
    JComboBox<Integer> cmbNam;
    CardLayout cardLayoutThongKe = new CardLayout();
    JPanel pnMain;
    JLabel lblMon1, lblMon2, lblMon3, lblMon4, lblMon5, lblSoLuong1, lblSoLuong2, lblSoLuong3, lblSoLuong4, lblSoLuong5;
    private ChartPanel chartPanel;
    JPanel pnChart;

    private void addControls() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);

        //========================================
        pnMain = new TransparentPanel();
        pnMain.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pnMain.setLayout(cardLayoutThongKe);

        JPanel pnThongKeTong = new JPanel(null);
        pnThongKeTong.setBackground(colorPanel);
        JLabel lblTitleThongKeTong, lblBackgroundSach, lblBackgroundDocGia, lblBackgroundNhanVien, lblBackgroundPM, lblBackgroundPT, lblBackgroundPP;

        lblTitleThongKeTong = new JLabel("THỐNG KÊ TỔNG QUÁT", JLabel.CENTER);
        lblTitleThongKeTong.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblBackgroundSach = new JLabel(new ImageIcon("image/Manager-GUI/sl-sach.png"));
        lblBackgroundDocGia = new JLabel(new ImageIcon("image/Manager-GUI/sl-docgia.png"));
        lblBackgroundNhanVien = new JLabel(new ImageIcon("image/Manager-GUI/sl-nhanvien.png"));
        lblBackgroundPM = new JLabel(new ImageIcon("image/Manager-GUI/sl-phieumuon.png"));
        lblBackgroundPT = new JLabel(new ImageIcon("image/Manager-GUI/sl-phieutra.png"));
        lblBackgroundPP = new JLabel(new ImageIcon("image/Manager-GUI/sl-phieuphat.png"));

        lblTitleThongKeTong.setBounds(150, 10, 500, 50);
        lblBackgroundSach.setBounds(180,100,106,136);
        lblBackgroundDocGia.setBounds(340,100,106,136);
        lblBackgroundNhanVien.setBounds(500,100,106,136);
        lblBackgroundPM.setBounds(180,250,106,136);
        lblBackgroundPT.setBounds(340,250,106,136);
        lblBackgroundPP.setBounds(500,250,106,136);

        lblThongKeSach = new JLabel("20",JLabel.CENTER);
        lblThongKeDocGia = new JLabel("10",JLabel.CENTER);
        lblThongKeNhanVien = new JLabel("10",JLabel.CENTER);
        lblThongKePM = new JLabel("15",JLabel.CENTER);
        lblThongKePT = new JLabel("15",JLabel.CENTER);
        lblThongKePP = new JLabel("15",JLabel.CENTER);

        Font font = new Font("tahoma", Font.BOLD,30);
        lblThongKeSach.setFont(font);
        lblThongKeDocGia.setFont(font);
        lblThongKeNhanVien.setFont(font);
        lblThongKePM.setFont(font);
        lblThongKePT.setFont(font);
        lblThongKePP.setFont(font);

        lblTitleThongKeTong.setForeground(Color.WHITE);
        lblThongKeSach.setForeground(Color.WHITE);
        lblThongKeDocGia.setForeground(Color.WHITE);
        lblThongKeNhanVien.setForeground(Color.WHITE);
        lblThongKePM.setForeground(Color.WHITE);
        lblThongKePT.setForeground(Color.WHITE);
        lblThongKePP.setForeground(Color.WHITE);

        lblThongKeSach.setBounds(185,150,100,50);
        lblThongKeDocGia.setBounds(345,150,100,50);
        lblThongKeNhanVien.setBounds(505,150,100,50);
        lblThongKePM.setBounds(185,300,100,50);
        lblThongKePT.setBounds(345,300,100,50);
        lblThongKePP.setBounds(505,300,100,50);

        pnThongKeTong.add(lblTitleThongKeTong);
        pnThongKeTong.add(lblThongKeSach);
        pnThongKeTong.add(lblThongKeDocGia);
        pnThongKeTong.add(lblThongKeNhanVien);
        pnThongKeTong.add(lblThongKePM);
        pnThongKeTong.add(lblThongKePT);
        pnThongKeTong.add(lblThongKePP);
        pnThongKeTong.add(lblBackgroundSach);
        pnThongKeTong.add(lblBackgroundDocGia);
        pnThongKeTong.add(lblBackgroundNhanVien);
        pnThongKeTong.add(lblBackgroundPM);
        pnThongKeTong.add(lblBackgroundPT);
        pnThongKeTong.add(lblBackgroundPP);

        lblDoanhThuQuy1 = new JLabel("200.000", JLabel.CENTER);
        lblDoanhThuQuy2 = new JLabel("300.000", JLabel.CENTER);
        lblDoanhThuQuy3 = new JLabel("220.000", JLabel.CENTER);
        lblDoanhThuQuy4 = new JLabel("150.000", JLabel.CENTER);
        lblTongDoanhThu = new JLabel("870.000", JLabel.CENTER);

        Font font1 = new Font("Tahoma", Font.BOLD, 22);
        lblDoanhThuQuy1.setFont(font1);
        lblDoanhThuQuy2.setFont(font1);
        lblDoanhThuQuy3.setFont(font1);
        lblDoanhThuQuy4.setFont(font1);
        font1 = new Font("Tahoma", Font.BOLD, 28);
        lblTongDoanhThu.setFont(font1);

        lblDoanhThuQuy1.setForeground(Color.WHITE);
        lblDoanhThuQuy2.setForeground(Color.WHITE);
        lblDoanhThuQuy3.setForeground(Color.WHITE);
        lblDoanhThuQuy4.setForeground(Color.WHITE);
        lblTongDoanhThu.setForeground(Color.WHITE);
//        230 200
        int x = 135;
        int y = 533;
        lblDoanhThuQuy1.setBounds(x, y, 167, 63);
        lblDoanhThuQuy2.setBounds(x += 167, y, 167, 63);
        lblDoanhThuQuy3.setBounds(x += 167, y, 167, 63);
        lblDoanhThuQuy4.setBounds(x += 167, y, 167, 63);
        lblTongDoanhThu.setBounds(165, 595, 667, 63);

        pnThongKeTong.add(lblTongDoanhThu);
        pnThongKeTong.add(lblDoanhThuQuy1);
        pnThongKeTong.add(lblDoanhThuQuy2);
        pnThongKeTong.add(lblDoanhThuQuy3);
        pnThongKeTong.add(lblDoanhThuQuy4);
        pnThongKeTong.add(lblTongDoanhThu);

        cmbNam = new JComboBox<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= year - 10; i--)
            cmbNam.addItem(i);
        cmbNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cmbNam.setBounds(335, 410, 120, 35);
        pnThongKeTong.add(cmbNam);

//        btn_filter = new JButton("Chi tiết");
//        btn_filter.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        btn_filter.setBounds((800/ 2 - 100 / 2) + 140, 360, 100, 35);

        JLabel lblBackgroundBang = new JLabel(new ImageIcon("image/Manager-GUI/bangThongKe.png"));
        lblBackgroundBang.setBounds(0, 470, 814, 189);
        pnThongKeTong.add(lblBackgroundBang);

        pnMain.add(pnThongKeTong,"1");

        // ==============================================
        //              THỐNG KÊ CHI TIẾT
        // ==============================================
        //================================

        hienThiThongKe();
        this.add(pnMain, BorderLayout.CENTER);
    }

    private void addEvents() {
        cmbNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hienThiThongKe();
            }
        });
    }

    private void veLaiChart() {
        pnChart.removeAll();

        chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension(800, 241));

        pnChart.add(chartPanel);
    }

    private JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu năm " + Calendar.getInstance().get(Calendar.YEAR),
                "Tháng", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 12; i++) {
            double value = thongKeBUS.getDoanhThuThang(i, Calendar.getInstance().get(Calendar.YEAR));
            dataset.addValue(value, "Doanh thu", i + "");
        }
        return dataset;
    }

    private DecimalFormat dcf = new DecimalFormat("###,###");

    private void hienThiThongKe(){
        ThongKe thongKe = thongKeBUS.thongKe(Integer.parseInt(cmbNam.getSelectedItem()+""));
        lblThongKeSach.setText(dcf.format(thongKe.getSoLuongSach()));
        lblThongKeDocGia.setText(dcf.format(thongKe.getSoLuongDG()));
        lblThongKeNhanVien.setText(dcf.format(thongKe.getSoLuongNV()));
        lblThongKePM.setText(dcf.format(thongKe.getSoLuongPM()));
        lblThongKePT.setText(dcf.format(thongKe.getSoLuongPT()));
        lblThongKePP.setText(dcf.format(thongKe.getSoLuongPP()));
        lblDoanhThuQuy1.setText(dcf.format(thongKe.getTongThuQuy(1)));
        lblDoanhThuQuy2.setText(dcf.format(thongKe.getTongThuQuy(2)));
        lblDoanhThuQuy3.setText(dcf.format(thongKe.getTongThuQuy(3)));
        lblDoanhThuQuy4.setText(dcf.format(thongKe.getTongThuQuy(4)));
        lblTongDoanhThu.setText(dcf.format(thongKe.getTongDoanhThu()));
    }
}

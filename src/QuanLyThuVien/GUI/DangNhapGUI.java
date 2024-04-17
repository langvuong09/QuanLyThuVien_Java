package QuanLyThuVien.GUI;

import Main.Main;
import MyCustom.ImagePanel;
import MyCustom.MyDialog;
import QuanLyThuVien.BUS.DangNhapBUS;
import QuanLyThuVien.BUS.TaiKhoanBUS;
import QuanLyThuVien.DTO.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class DangNhapGUI extends JFrame {

    public DangNhapGUI() {
        this.setTitle("Đăng nhập");
        this.setSize(750, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        addControls();
        addEvents();
        xuLyTaiKhoanDaGhiNho();
    }

    private void xuLyTaiKhoanDaGhiNho() {
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        String line = dangNhapBUS.getTaiKhoanGhiNho();
        try {
            String[] arr = line.split(" | ");
            ckbRemember.setSelected(true);
            txtUser.setText(arr[0]);
            txtPassword.setText(arr[2]);
            txtUser.requestFocus();
        } catch (Exception e) {
            txtUser.setText("");
            txtPassword.setText("");
            txtUser.requestFocus();
        }
    }

    private JLabel btnLogin, btnExit, btnForgot;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JPanel pnMain;
    private JCheckBox ckbRemember;
    private JLabel lbUser, lbPassword, lbTitle;
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

    private void addControls() {
        Container con = getContentPane();

        pnMain = new ImagePanel("image/dangnhap/background-thuvien.png");

        pnMain.setLayout(null);

        lbTitle = new JLabel("QUẢN LÍ THƯ VIỆN");
        lbTitle.setFont(new Font("Arial",Font.BOLD,40));
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setBounds(190,85,500,50);

        btnLogin = new JLabel(new ImageIcon("image/dangnhap/btn-login.png"));
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(140, 340, 207, 55);

        btnExit = new JLabel(new ImageIcon("image/dangnhap/btn-exit.png"));
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.setBounds(397, 340, 207, 55);

        btnForgot = new JLabel(new ImageIcon("image/dangnhap/btn-forgot.png"));
        btnForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnForgot.setBounds(150, 405, 164, 30);

        lbUser = new JLabel("Mã nhân viên:");
        lbUser.setFont(new Font("Arial",Font.BOLD,20));
        lbUser.setForeground(Color.WHITE);
        lbUser.setBounds(140,180,200,20);

        lbPassword = new JLabel("Mật khẩu:");
        lbPassword.setFont(new Font("Arial",Font.BOLD,20));
        lbPassword.setForeground(Color.WHITE);
        lbPassword.setBounds(180,250,200,20);

        Font fontTXT = new Font("", Font.BOLD, 18);
        txtUser = new JTextField();
        txtUser.setBackground(new Color(0, 0, 0, 0f));
        txtUser.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtUser.setForeground(Color.WHITE);
        txtUser.setFont(fontTXT);
        txtUser.setHorizontalAlignment(JTextField.LEFT);
        txtUser.setBounds(300, 175, 305, 30);

        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('•');
        txtPassword.setBackground(new Color(0, 0, 0, 0f));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setFont(fontTXT);
        txtPassword.setHorizontalAlignment(JTextField.LEFT);
        txtPassword.setBounds(300, 245, 305, 30);

        Main.changLNF("Windows");
        ckbRemember = new JCheckBox("Ghi nhớ đăng nhập");
        ckbRemember.setFont(fontTXT);
        ckbRemember.setOpaque(false);
        ckbRemember.setForeground(Color.WHITE);
        ckbRemember.setBounds(140, 310, 290, 19);
        ckbRemember.setFocusPainted(false);
        Main.changLNF("Nimbus");

        pnMain.add(ckbRemember);
        pnMain.add(btnLogin);
        pnMain.add(btnExit);
        pnMain.add(btnForgot);
        pnMain.add(lbUser);
        pnMain.add(lbPassword);
        pnMain.add(txtUser);
        pnMain.add(txtPassword);
        pnMain.add(lbTitle);

        con.add(pnMain);
    }

    private void addEvents() {
        moveFrame();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public  void mouseClicked(MouseEvent e){
                xuLyDangNhap();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setIcon(new ImageIcon("image/dangnhap/btn-login-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setIcon(new ImageIcon("image/dangnhap/btn-login.png"));
            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnExit.setIcon(new ImageIcon("image/dangnhap/btn-exit-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setIcon(new ImageIcon("image/dangnhap/btn-exit.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyThoat();
            }
        });
        btnForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyQuenMatKhau();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnForgot.setIcon(new ImageIcon("image/dangnhap/btn-forgot-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnForgot.setIcon(new ImageIcon("image/dangnhap/btn-forgot.png"));
            }
        });
    }

    int xMouse, yMouse;

    private void moveFrame() {
        pnMain.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Move(x, y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
    }

    private void Move(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void xuLyThoat() {
        System.exit(0);
    }

    private void xuLyQuenMatKhau() {
        new MyDialog("Xin liên hệ Admin để giải quyết!", MyDialog.INFO_DIALOG);
    }

    private void xuLyDangNhap(){
        DangNhapBUS dangNhapBUS = new DangNhapBUS();
        TaiKhoan tk = dangNhapBUS.getTaiKhoanDangNhap(txtUser.getText(),txtPassword.getText(),ckbRemember.isSelected());
        maTaiKhoan();
        if (tk != null) {
            this.dispose();
            MainQuanLyGUI gui = new MainQuanLyGUI();
            this.dispose();
            gui.showWindow();
        }
    }

    public int maTaiKhoan(){
        ArrayList<TaiKhoan> dstk = taiKhoanBUS.getListTaiKhoan();
        for (TaiKhoan tk1 : dstk){
            if(txtUser.getText().equals(tk1.getTenDangNhap())){
                return tk1.getMaNhanVien();
            }
        }
        return 0;
    }

    public void showWindow() {
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BOOK1.gif.jpg");
        this.setIconImage(icon);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        DangNhapGUI dangNhapGUI = new DangNhapGUI();
        dangNhapGUI.showWindow();
    }
}

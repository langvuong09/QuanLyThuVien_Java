package QuanLyThuVien.GUI;


import MyCustom.MyDialog;
import QuanLyThuVien.BUS.DangNhapBUS;
import QuanLyThuVien.BUS.TaiKhoanBUS;
import QuanLyThuVien.DTO.TaiKhoan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.util.ArrayList;


public class DangNhapGUI extends JFrame{
    // Biến cờ để theo dõi trạng thái hiển thị mật khẩu
    private boolean isPasswordVisible = false;
    private JButton btnLogin,  btnShowPass;
    private JLabel btnForgot;
    private JTextField txtUser;
    private JPasswordField txtPassword;

    private JCheckBox ckbRemember;
    private TaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();

    public DangNhapGUI(){
        addControls();
        xuLyTaiKhoanDaGhiNho();
        addEvents();
    }
    public void addControls(){
        // Tạo frame chính
        this.setTitle("Phần mềm quản lý thư viện");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1290, 740);
        this.setLayout(null);
        this.setResizable(true); // Cho phép thay đổi kích thước frame

        // Panel bên trái để hiển thị hình ảnh thư viện
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("image/img_qltv/bìa.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Vẽ ảnh tự động điều chỉnh theo kích thước panel
            }
        };
        leftPanel.setLayout(new BorderLayout());
        this.add(leftPanel, BorderLayout.WEST);

        // Panel bên phải để hiển thị form đăng nhập
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(10, 3, 42)); // Màu xanh

        // Tiêu đề
        JLabel titleLabel = new JLabel("PHẦN MỀM QUẢN LÝ THƯ VIỆN");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Tăng kích thước font
        rightPanel.add(titleLabel);

        ImageIcon icon = new ImageIcon("image/img_qltv/avatar.png"); // Tải icon
        Image img = icon.getImage(); // Lấy đối tượng hình ảnh từ ImageIcon
        Image resizedImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Thay đổi kích thước hình ảnh
        ImageIcon resizedIcon = new ImageIcon(resizedImg); // Tạo ImageIcon mới từ hình ảnh đã thay đổi kích thước

        JLabel iconLabel = new JLabel(resizedIcon); // Đặt icon đã thay đổi kích thước vào JLabel
        iconLabel.setPreferredSize(new Dimension(200, 200)); // Đặt kích thước tùy ý
        rightPanel.add(iconLabel); // Thêm vào rightPanel

        JLabel userIcon = new JLabel(new ImageIcon("image/img_qltv/user.png"));
        rightPanel.add(userIcon);

        // Trường nhập tên đăng nhập
        txtUser = new JTextField();
        rightPanel.add(txtUser);

        // Biểu tượng mật khẩu
        JLabel passwordIcon = new JLabel(new ImageIcon("image/img_qltv/lock.png"));
        rightPanel.add(passwordIcon);

        // Trường nhập mật khẩu
        txtPassword = new JPasswordField();
        rightPanel.add(txtPassword);

        // Nút hiển thị mật khẩu
        // Nút hiển thị mật khẩu
        ImageIcon eyeIcon = new ImageIcon("image/img_qltv/Eye.png");
        Image eyeImage = eyeIcon.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
        btnShowPass = new JButton(new ImageIcon(eyeImage));
        btnShowPass.setBounds(550, 400, 40, 40); // Đặt vị trí ban đầu
        btnShowPass.setBackground(new Color(10, 3, 42)); // Màu nền
        btnShowPass.setOpaque(true); // Hiển thị rõ ràng
        btnShowPass.setBorder(null);
        rightPanel.add(btnShowPass);



        //Nút ghi nhớ mật khẩu
        ckbRemember = new JCheckBox("Ghi nhớ mật khẩu");
        ckbRemember.setBounds(160, 445, 200, 30); // Đặt tọa độ và kích thước
        ckbRemember.setForeground(Color.WHITE); // Màu chữ
        ckbRemember.setBackground(new Color(10, 3, 42)); // Màu nền khớp với panel
        ckbRemember.setSelected(true); // Checkbox được chọn mặc định
        // Loại bỏ đường viền
        ckbRemember.setBorderPainted(false);
        ckbRemember.setFocusPainted(false); // Loại bỏ hiệu ứng khi được chọn
        ckbRemember.setBorder(null);
        rightPanel.add(ckbRemember); // Thêm vào panel


        // Nút "Forgot Password?"
        btnForgot = new JLabel("Forgot password?");
        btnForgot.setForeground(Color.WHITE);
        rightPanel.add( btnForgot);

        // Nút Sign In
        btnLogin = new JButton("Sign in");
        btnLogin.setBackground(Color.PINK);
        btnLogin.setForeground(Color.BLACK);
        rightPanel.add(btnLogin);

        int frameWidth = DangNhapGUI.this.getWidth();
        int frameHeight = DangNhapGUI.this.getHeight();

        // Điều chỉnh kích thước panel
        leftPanel.setBounds(0, 0, frameWidth / 2, frameHeight);
        rightPanel.setBounds(frameWidth / 2, 0, frameWidth / 2, frameHeight);

        // Cập nhật vị trí và kích thước các thành phần trong rightPanel
        int rightWidth = rightPanel.getWidth();
        int rightHeight = rightPanel.getHeight();

        titleLabel.setBounds(rightWidth / 4, 20, rightWidth / 2, 40);
        iconLabel.setBounds(rightWidth / 2 - 75, 100, 150, 150);


        userIcon.setBounds(rightWidth / 4 - 40, 320, 40, 40);
        txtUser.setBounds(rightWidth / 4, 320, rightWidth / 2 + 60, 40);


        // Biểu tượng mật khẩu và trường nhập mật khẩu
        passwordIcon.setBounds(rightWidth / 4 - 40, 400, 40, 40);
        txtPassword.setBounds(rightWidth / 4, 400, rightWidth / 2 + 60, 40);



        btnForgot.setBounds(rightWidth / 4 + rightWidth / 2 - 40, 450, rightWidth / 2, 20);

        btnLogin.setBounds(rightWidth / 4, 550, rightWidth / 2 + 60, 40);

        // Thêm các panel vào frame
        this.add(rightPanel, BorderLayout.CENTER);


    }


    public void showWindow(){
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    //==================ADD CHỨC NĂNG CHO LOGIN PANEL
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

    private void addEvents() {

        btnShowPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPasswordVisible) {
                    // Nếu đang hiển thị, chuyển về ẩn mật khẩu
                    txtPassword.setEchoChar('*'); // Đặt lại ký tự ẩn mật khẩu
                    isPasswordVisible = false;
                } else {
                    // Nếu đang ẩn, hiển thị mật khẩu
                    txtPassword.setEchoChar((char) 0); // Loại bỏ ký tự ẩn
                    isPasswordVisible = true;
                }
            }
        });
        moveFrame();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public  void mouseClicked(MouseEvent e){
                xuLyDangNhap();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        btnForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyQuenMatKhau();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }


    int xMouse, yMouse;

    private void moveFrame() {
        this.addMouseMotionListener(new MouseAdapter() {
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




}

package QuanLyThuVien.GUI;

import QuanLyThuVien.BUS.PhanQuyenBUS;
import QuanLyThuVien.DTO.PhanQuyen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;

public class MainQuanLyGUI extends JFrame {
    public MainQuanLyGUI() {
        // Đặt nền cho frame
        Color backgroundColor = new Color(0xFBF1F1); // hoặc new Color(251, 241, 241)
        getContentPane().setBackground(backgroundColor);
        // Icon cho app
        Image icon = Toolkit.getDefaultToolkit().getImage("image/img_qltv/icon_logo_qltv.png");
        this.setIconImage(icon);
        this.setSize(1540, 820);
        addControls();
        addEvents();

    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    PnQuanLySachGUI SachPanel;
    PnQuanLyKhuVucGUI KhuVucPanel;
    PnMuonTraPhat MuonTraPhatPanel;
    PnQuanLyNhapSach NhapSachPanel;
    PnQuanLyDocGiaGUI DocGiaPanel;
    PnQuanLyNhanVienGUI NhanVienPanel;
    PnQuanLyThongKeGUI ThongKePanel;


    JButton btnDoiMatKhau, btnDangXuat;
    JPanel pnTitle, pnTrangChu, pnMenuLeft, pnCard;
    JButton lblTrangChu, lblSach, lblKhuVuc, lblDocGia, lblMuonSach, lblTraSach, lblQuaHan, lblNhanVien, lblNhapSach,
            lblThongKe;

    // Khởi tạo cái Panel

    // Màu sắc của menu khi bình thường
    final Color clLeftItem = new Color(0x0A032A);
    // Màu sắc khi menu đc hover
    final Color clLeftItemHover = new Color(0x160561);
    // Màu sắc của menu khi được chọn
    final Color clLeftItemSelected = new Color(0x65E0C7);

    // Menusidebar và Panel chính
    ArrayList<JButton> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();

    // Phương thức để thay đổi kích thước icon
    private static Image resizeImage(Image originalImage, int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return bufferedImage;
    }

    // Chỉnh các icon của các item trong MenuLeftSideBar
    private JButton createItemSidebar(String linkicon, String namebutton) {
        // TẠO LABEL CHỨA ICON
        ImageIcon original_icon = new ImageIcon(linkicon);
        Image scaledImage_icon = resizeImage(original_icon.getImage(), 50, 50);
        ImageIcon scaledIcon_icon = new ImageIcon(scaledImage_icon);
        JLabel icon = new JLabel(scaledIcon_icon);
        // TẠO LABEL CHỨA TÊN
        JLabel name = new JLabel(namebutton);
        name.setFont(new Font("Times New Roman", Font.BOLD, 25));
        name.setForeground(Color.WHITE);

        JButton button = new JButton();
        button.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        button.setBackground(clLeftItem);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusPainted(false);
        button.add(icon);
        button.add(name);

        return button;
    }

    // JPanel sẽ hiện ra khi click vào nút trang chủ
    private JPanel Panel_TrangChu() {
        JPanel trangchu = new JPanel();
        trangchu.setPreferredSize(new Dimension(1290, 740));
        ImageIcon original = new ImageIcon("image/img_qltv/img_trangchu.png");
        Image scaledImage = resizeImage(original.getImage(), 1290, 740);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel img = new JLabel(scaledIcon);
        trangchu.add(img);
        return trangchu;
    }

    // Thêm giao diện vào Jframe
    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        // =======================TITLE BAR (TIÊU ĐỀ)========================//
        pnTitle = new JPanel(new BorderLayout()); // Change to BorderLayout
        pnTitle.setPreferredSize(new Dimension(1520, 80));
        pnTitle.setBackground(new Color(0x65E0C7));

        /* PanelTitle bên trái */
        FlowLayout left_titlepanel = new FlowLayout(FlowLayout.LEFT, 20, 10);
        JPanel left_pnTitle = new JPanel(left_titlepanel);
        left_pnTitle.setPreferredSize(new Dimension(770, 80));
        left_pnTitle.setBackground(new Color(0x65E0C7));

        ImageIcon original_icon_logo = new ImageIcon("image/img_qltv/logo.png");
        Image scaledImage_icon_logo = resizeImage(original_icon_logo.getImage(), 60, 55);
        ImageIcon scaledIcon_icon_logo = new ImageIcon(scaledImage_icon_logo);
        JLabel lbl_icon_logo = new JLabel(scaledIcon_icon_logo);

        JLabel Lbl_title_qltv = new JLabel("THƯ VIỆN");
        Lbl_title_qltv.setFont(new Font("Times New Roman", Font.BOLD, 35));
        Lbl_title_qltv.setForeground(Color.WHITE);

        left_pnTitle.add(lbl_icon_logo);
        left_pnTitle.add(Lbl_title_qltv);

        /* PanelTitle bên phải */
        FlowLayout right_titlepanel = new FlowLayout(FlowLayout.RIGHT, 16, 10);
        JPanel right_pnTitle = new JPanel(right_titlepanel);
        right_pnTitle.setPreferredSize(new Dimension(770, 80));
        right_pnTitle.setBackground(new Color(0x65E0C7));

        /*
         * =======TÊN ĐĂNG NHẬP CỦA NHÂN VIÊN ====================NHỚ THÊM CODE VÔ ĐOẠN
         * NÀY NHA TRỜI ƠI
         */
        JLabel lbl_username = new JLabel("Lang Vương");
        lbl_username.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_username.setForeground(new Color(0x1B5453));

        ImageIcon original_icon_user = new ImageIcon("image/img_qltv/icon_chaomung.png");
        Image scaledImage_icon_user = resizeImage(original_icon_user.getImage(), 50, 50);
        ImageIcon scaledIcon_icon_user = new ImageIcon(scaledImage_icon_user);
        JLabel lbl_icon_user = new JLabel(scaledIcon_icon_user);

        ImageIcon original_icon_DoiMatKhau = new ImageIcon("image/img_qltv/icon_doimatkhau.png");
        Image scaledImage_icon_DoiMatKhau = resizeImage(original_icon_DoiMatKhau.getImage(), 50, 50);
        ImageIcon scaledIcon_icon_DoiMatKhau = new ImageIcon(scaledImage_icon_DoiMatKhau);
        btnDoiMatKhau = new JButton(scaledIcon_icon_DoiMatKhau);
        btnDoiMatKhau.setBackground(new Color(0x65E0C7));
        btnDoiMatKhau.setBorderPainted(false); // Tắt border được vẽ
        btnDoiMatKhau.setBorder(null); // Set border là null (không viền)
        btnDoiMatKhau.setFocusPainted(false);

        ImageIcon original_icon_DangXuat = new ImageIcon("image/img_qltv/icon_dangxuat.png");
        Image scaledImage_icon_DangXuat = resizeImage(original_icon_DangXuat.getImage(), 50, 50);
        ImageIcon scaledIcon_icon_DangXuat = new ImageIcon(scaledImage_icon_DangXuat);
        btnDangXuat = new JButton(scaledIcon_icon_DangXuat);
        btnDangXuat.setBackground(new Color(0x65E0C7));
        btnDangXuat.setBorderPainted(false); // Tắt border được vẽ
        btnDangXuat.setBorder(null); // Set border là null (không viền)
        btnDangXuat.setFocusPainted(false);

        right_pnTitle.add(lbl_username);
        right_pnTitle.add(lbl_icon_user);
        right_pnTitle.add(btnDoiMatKhau);
        right_pnTitle.add(btnDangXuat);

        pnTitle.add(left_pnTitle, BorderLayout.WEST);
        pnTitle.add(right_pnTitle, BorderLayout.EAST);

        // ================================SIDE BAR (PHÂN QUYỀN KHÚC NÀY NHA)
        // ==========================//
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, 740));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new GridLayout(0, 1, 0, 0));
        // icon chào mừng
        JLabel lbl_iconchaomung = new JLabel();
        Image scaledImage_icon_user2 = resizeImage(original_icon_user.getImage(), 50, 50);
        ImageIcon scaledIcon_icon_user2 = new ImageIcon(scaledImage_icon_user2);
        lbl_iconchaomung.setIcon(scaledIcon_icon_user2);
        JLabel lbl_chaomung = new JLabel("CHÀO MỪNG");
        lbl_chaomung.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl_chaomung.setForeground(Color.WHITE);
        JPanel pnChaoMung = new JPanel();
        pnChaoMung.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        pnChaoMung.setMaximumSize(new Dimension(250, 50));
        pnChaoMung.setBackground(clLeftItem);
        pnChaoMung.add(lbl_iconchaomung);
        pnChaoMung.add(lbl_chaomung);

        // BUTTON ITEM
        // Tạo Button Trang Chủ
        lblTrangChu = createItemSidebar("image/img_qltv/icon_trangchu.png", "Trang Chủ");
        lblSach = createItemSidebar("image/img_qltv/icon_qlsach.png", "Quản Lí Sách");
        lblKhuVuc = createItemSidebar("image/img_qltv/icon_khuvuc.png", "Khu Vực");
        lblDocGia = createItemSidebar("image/img_qltv/icon_docgia.png", "Độc giả");
        lblMuonSach = createItemSidebar("image/img_qltv/icon_muontra.png", "Mượn Trả");
        lblNhanVien = createItemSidebar("image/img_qltv/icon_nhanvien.png", "Nhân Viên");
        lblNhapSach = createItemSidebar("image/img_qltv/icon_nhapsach.png", "Nhập Sách");
        lblThongKe = createItemSidebar("image/img_qltv/icon_thongke.png", "Thống Kê");

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lblSach);
        listMenuLeft.add(lblKhuVuc);
        listMenuLeft.add(lblDocGia);
        listMenuLeft.add(lblMuonSach);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblNhapSach);
        listMenuLeft.add(lblThongKe);

        pnMenuLeft.add(pnChaoMung);
        pnMenuLeft.add(lblTrangChu);

        for (JButton lbl : listMenuLeft) {
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
            lbl.setVisible(false);
        }

        lblSach.setVisible(true);
        lblKhuVuc.setVisible(true);
        lblDocGia.setVisible(true);
        lblMuonSach.setVisible(true);

        // ===============================CÁC JPANEL ADD VÔ CARD
        // LAYOUT================================//
        pnCard = new JPanel(cardMenuLeftGroup);

        SachPanel=new PnQuanLySachGUI();
        KhuVucPanel=new PnQuanLyKhuVucGUI();
        MuonTraPhatPanel=new PnMuonTraPhat();
        DocGiaPanel=new PnQuanLyDocGiaGUI();

        pnCard.add(Panel_TrangChu(), "1");
        pnCard.add(SachPanel,"2");
        pnCard.add(KhuVucPanel,"3");
        pnCard.add(DocGiaPanel,"4");
        pnCard.add(MuonTraPhatPanel,"5");


        PhanQuyen quyen = PhanQuyenBUS.quyenTK;

        if(quyen.getQlNhanVien()==1){
            NhanVienPanel = new PnQuanLyNhanVienGUI();
            pnCard.add( NhanVienPanel,"6");
            lblNhanVien.setVisible(true);
        }

        if(quyen.getQlNhapSach()==1){
            NhapSachPanel = new PnQuanLyNhapSach();
            pnCard.add(NhapSachPanel,"7");
            lblNhapSach.setVisible(true);
        }

        if(quyen.getThongKe()==1){
            ThongKePanel = new PnQuanLyThongKeGUI();
            pnCard.add(ThongKePanel,"8");
            lblThongKe.setVisible(true);
        }



        // =========================PANEL CHÍNH==============================//
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTitle, BorderLayout.NORTH);
        pnMain.add(pnMenuLeft, BorderLayout.WEST);
        pnMain.add(pnCard, BorderLayout.CENTER);
        this.add(pnMain);

    }

    int xMouse, yMouse;
    private void addEvents() {
        for (JButton lbl : listMenuLeft) {
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JButton lblDisable : listMenuLeft) {
                        lblDisable.setBackground(clLeftItem);
                    }
                    lbl.setBackground(clLeftItemSelected);
                    // Xử lý lật trang theo menu
                    String cardName = "";
                    if (lbl == lblSach) {
                        cardName = "2";
                    } else if (lbl == lblKhuVuc) {
                        cardName = "3";
                    } else if (lbl ==lblDocGia) {
                        cardName = "4";
                    } else if (lbl == lblMuonSach) {
                        cardName = "5";
                    }else if(lbl==lblNhanVien){
                        cardName = "6";
                    }else if(lbl==lblNhapSach){
                        cardName = "7";
                    } else if (lbl==lblThongKe) {
                        cardName = "8";
                    }
                    cardMenuLeftGroup.show(pnCard, cardName);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (lbl.getBackground().equals(clLeftItem)) {
                        lbl.setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (lbl.getBackground().equals(clLeftItemHover)) {
                        lbl.setBackground(clLeftItem);
                    }
                }
            });
        }

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                new DlgDoiMatKhau().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDoiMatKhau.setOpaque(true);
                btnDoiMatKhau.setBackground(clLeftItemHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDoiMatKhau.setOpaque(false);
                btnDoiMatKhau.setBackground(new Color(0, 0, 0, 0));
            }
        });
        lblTrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cardMenuLeftGroup.show(pnCard, "1");
                for (JButton lblDisable : listMenuLeft) {
                    lblDisable.setBackground(clLeftItem);
                }
                lblTrangChu.setBackground(clLeftItemSelected);
            }
        });
        btnDangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }









}

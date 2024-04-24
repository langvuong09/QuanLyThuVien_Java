package QuanLyThuVien.GUI;


import QuanLyThuVien.BUS.PhanQuyenBUS;
import QuanLyThuVien.DAO.PhanQuyenDAO;
import QuanLyThuVien.DTO.PhanQuyen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MainQuanLyGUI extends JFrame{
    public MainQuanLyGUI() {
        this.setTitle("Phần mềm quản lý thư viện");
        Image icon = Toolkit.getDefaultToolkit().getImage("image/BOOK1.gif.jpg");
        this.setIconImage(icon);
        this.setSize(1050, 700);
        addControls();
        addEvents();
    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JLabel btnDoiMatKhau;

    JPanel pnTitle, pnMenuLeft, pnCard, pnSach, pnDocGia, pnMuonSach, pnTraSach, pnQuaHan, pnTheLoai, pnNhanVien, pnThongKe;

    JLabel btnClose, btnMinimize,lblSach,lblDocGia, lblMuonSach, lblTraSach, lblQuaHan, lblThemMoi, lblNhanVien, lblThongKe;

    PnQuanLySachGUI sachPanel;
    PnQuanLyDocGiaGUI docGiaPanel;
    PnQuanLyPhieuMuonGUI phieuMuonPanel;
    PnQuanLyPhieuTraGUI phieuTraPanel;
    PnQuanLyPhieuPhatGUI phieuPhatPanel;
    PnQuanLyLoaiGUI loaiPanel;
    PnQuanLyNhanVienGUI nhanVienPanel;
    PnQuanLyThongKeGUI thongKePanel;

    final Color clLeftItem = new Color(62, 62, 62);
    final Color clLeftItemHover = new Color(88, 88, 88);
    final Color clLeftItemSelected = new Color(128, 128, 128);

    ArrayList<JLabel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        /*
        ============================================================
                                TITLE BAR
        ============================================================
        */

        pnTitle = new JPanel(null);
        pnTitle.setPreferredSize(new Dimension(width,46));
        pnTitle.setBackground(new Color(62, 62, 62));

        btnDoiMatKhau = new JLabel(new ImageIcon("image/Manager-GUI/setting.png"));
        btnDoiMatKhau.setToolTipText("Đổi mật khẩu");
        btnDoiMatKhau.setBounds(0, 0, 46, 46);
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblTitleText = new JLabel(new ImageIcon("image/Manager-GUI/title-text.png"));
        lblTitleText.setBounds(width / 2 - 428 / 2, 3, 428, 38);

        btnMinimize = new JLabel(new ImageIcon("image/Manager-GUI/btn-minimize.png"));
        btnMinimize.setBounds(width - 85, 5, 38, 35);
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnClose = new JLabel(new ImageIcon("image/Manager-GUI/btn-close.png"));
        btnClose.setBounds(width - 40, 5, 35, 35);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnTitle.add(btnDoiMatKhau);
        pnTitle.add(lblTitleText);
        pnTitle.add(btnMinimize);
        pnTitle.add(btnClose);

        pnMain.add(pnTitle, BorderLayout.NORTH);

        /*
        ============================================================
                                SIDE BAR MENU
        ============================================================
         */
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        JLabel lblAvatar = new JLabel(new ImageIcon("image/Manager-GUI/app.jpg"));
        lblAvatar.setPreferredSize(new Dimension(250, 130));

        lblSach = new JLabel(new ImageIcon("image/Manager-GUI/lblSach.png"));
        lblDocGia = new JLabel(new ImageIcon("image/Manager-GUI/lblDocGia.png"));
        lblMuonSach = new JLabel(new ImageIcon("image/Manager-GUI/lblPhieuMuon.png"));
        lblTraSach = new JLabel(new ImageIcon("image/Manager-GUI/lblPhieuTra.png"));
        lblQuaHan = new JLabel(new ImageIcon("image/Manager-GUI/lblPhieuPhat.png"));
        lblThemMoi = new JLabel(new ImageIcon("image/Manager-GUI/lblThemMoi.png"));
        lblNhanVien = new JLabel(new ImageIcon("image/Manager-GUI/lblNhanVien.png"));
        lblThongKe = new JLabel(new ImageIcon("image/Manager-GUI/lblThongKe.png"));

        listMenuLeft = new ArrayList<>();
        pnMenuLeft.add(lblAvatar);
        listMenuLeft.add(lblSach);
        listMenuLeft.add(lblDocGia);
        listMenuLeft.add(lblMuonSach);
        listMenuLeft.add(lblTraSach);
        listMenuLeft.add(lblQuaHan);
        listMenuLeft.add(lblThemMoi);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblThongKe);

        for (JLabel lbl : listMenuLeft) {
            lbl.setVisible(false);
            lbl.setPreferredSize(new Dimension(250, 65));
            lbl.setOpaque(true);
            lbl.setBackground(clLeftItem);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
        }

        lblSach.setBackground(clLeftItemSelected);
        lblSach.setVisible(true);
        lblDocGia.setVisible(true);
        lblMuonSach.setVisible(true);
        lblTraSach.setVisible(true);
        lblQuaHan.setVisible(true);
        lblThemMoi.setVisible(true);

        pnMain.add(pnMenuLeft, BorderLayout.WEST);

        /*
        ============================================================
                                CARD PANEL
        ============================================================
         */
        pnCard = new JPanel(cardMenuLeftGroup);

        pnSach = new JPanel();
        pnDocGia = new JPanel();
        pnMuonSach = new JPanel();
        pnTraSach = new JPanel();
        pnQuaHan = new JPanel();
        pnTheLoai = new JPanel();
        pnNhanVien = new JPanel();
        pnThongKe = new JPanel();

        pnCard.add(pnSach, "1");
        pnCard.add(pnDocGia, "2");
        pnCard.add(pnMuonSach, "3");
        pnCard.add(pnTraSach, "4");
        pnCard.add(pnQuaHan, "5");
        pnCard.add(pnTheLoai, "6");
        pnCard.add(pnNhanVien,"7");
        pnCard.add(pnThongKe, "8");

        //==========ADD PANEL KHÔNG PHÂN QUYỀN==========//
        sachPanel = new PnQuanLySachGUI();
        pnSach.setLayout(new BorderLayout());
        pnSach.add(sachPanel,BorderLayout.CENTER);

        docGiaPanel = new PnQuanLyDocGiaGUI();
        pnDocGia.setLayout(new BorderLayout());
//        pnDocGia.add(docGiaPanel,BorderLayout.CENTER);

        phieuMuonPanel = new PnQuanLyPhieuMuonGUI();
        pnMuonSach.setLayout(new BorderLayout());
        pnMuonSach.add(phieuMuonPanel,BorderLayout.CENTER);

        phieuTraPanel = new PnQuanLyPhieuTraGUI();
        pnTraSach.setLayout(new BorderLayout());
        pnTraSach.add(phieuTraPanel,BorderLayout.CENTER);

        phieuPhatPanel = new PnQuanLyPhieuPhatGUI();
        pnQuaHan.setLayout(new BorderLayout());
//        pnQuaHan.add(phieuPhatPanel,BorderLayout.CENTER);

        loaiPanel = new PnQuanLyLoaiGUI();
        pnTheLoai.setLayout(new BorderLayout());
//        pnTheLoai.add(loaiPanel,BorderLayout.CENTER);

        //==========ADD PANEL PHÂN QUYỀN==========//
        PhanQuyen quyen = PhanQuyenBUS.quyenTK;

        if(quyen.getQlNhanVien()==1){
            nhanVienPanel = new PnQuanLyNhanVienGUI();
            pnNhanVien.setLayout(new BorderLayout());
            pnNhanVien.add(nhanVienPanel,BorderLayout.CENTER);
            lblNhanVien.setVisible(true);
        }

        if(quyen.getThongKe()==1){
            thongKePanel = new PnQuanLyThongKeGUI();
            pnThongKe.setLayout(new BorderLayout());
//            pnThongKe.add(thongKePanel,BorderLayout.CENTER);
            lblThongKe.setVisible(true);
        }

        pnMain.add(pnCard);
        /*
        ============================================================
                                CARD PANEL
        ============================================================
         */
        con.add(pnMain);
    }

    int xMouse, yMouse;

    private void addEvents() {
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

        btnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thuNhoFrame();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/Manager-GUI/btn-minimize-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setIcon(new ImageIcon("image/Manager-GUI/btn-minimize.png"));
            }
        });

        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thoatChuongTrinh();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/Manager-GUI/btn-close-hover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("image/Manager-GUI/btn-close.png"));
            }
        });

        for (JLabel lbl : listMenuLeft) {
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lblDisable : listMenuLeft) {
                        lblDisable.setBackground(clLeftItem);
                    }
                    lbl.setBackground(clLeftItemSelected);

                    // Xử lý lật trang theo menu
                    String cardName = "";
                    if (lbl == lblSach) {
                        cardName = "1";
                    } else if (lbl == lblDocGia) {
                        cardName = "2";
                    } else if (lbl == lblMuonSach) {
                        cardName = "3";
                    } else if (lbl == lblTraSach) {
                        cardName = "4";
                    } else if (lbl == lblQuaHan) {
                        cardName = "5";
                    } else if (lbl == lblThemMoi) {
                        cardName = "6";
                    }
                    else if (lbl == lblNhanVien) {
                        cardName = "7";
                    }else {
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
    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void thoatChuongTrinh() {
        phieuMuonPanel.xuLyThoat();
        Main.Main.changLNF("Nimbus");
        System.exit(0);
    }
}

package QuanLyThuVien.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnMuonTraPhat extends JPanel {
    PnQuanLyPhieuMuonGUI phieuMuonPanel;
    PnQuanLyPhieuTraGUI phieuTraPanel;
    PnQuanLyPhieuPhatGUI phieuPhatGUI;
    CardLayout layoutGroup=new CardLayout();

    public PnMuonTraPhat() {
        try {
            // Đặt giao diện Look and Feel để đảm bảo màu sắc hiển thị đúng
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Khởi tạo các nút
        btn_Muon = new JButton();
        btn_Tra = new JButton();
        btn_Phat = new JButton();

        addControlsMuonTraPhat();
        addEvents();
    }

    private JPanel createJPanelItem(JButton btn, String name) {
        JPanel pn = new JPanel(new BorderLayout());
        JLabel lb_img = new JLabel();
        ImageIcon img_icon = new ImageIcon("image/img_qltv/img_muontraphat.png");
        lb_img.setIcon(img_icon);
        lb_img.setPreferredSize(new Dimension(450, 250));

        btn.setPreferredSize(new Dimension(450, 50));
        btn.setBackground(new Color(0x65E0C7));
        btn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        btn.setForeground(Color.WHITE);
        btn.setText(name);
        pn.add(lb_img, BorderLayout.CENTER);
        pn.add(btn, BorderLayout.SOUTH);
        pn.setPreferredSize(new Dimension(450, 300));
        return pn;
    }

    private JPanel Pn_MuonTraPhatGUI (){
        JPanel pn=new JPanel();
        pn.setBackground(new Color(0xFBF1F1));
        pn.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));

        JPanel pn_Muon, pn_Phat, pn_Tra;
        pn_Muon = createJPanelItem(btn_Muon, "PHIẾU MƯỢN");
        pn_Phat = createJPanelItem(btn_Phat, "PHIẾU PHẠT");
        pn_Tra = createJPanelItem(btn_Tra, "PHIẾU TRẢ");

        // Thêm các panel con vào panel chính
        pn.add(pn_Muon);
        pn.add(pn_Phat);
        pn.add(pn_Tra);

        return pn;
    }

    private JButton btn_Muon, btn_Tra, btn_Phat;

    public void addControlsMuonTraPhat() {
        this.setPreferredSize(new Dimension(1290, 740));
        this.setBackground(new Color(0xFBF1F1));
        this.setLayout(layoutGroup);
        this.add(Pn_MuonTraPhatGUI (),"1");
        phieuMuonPanel=new PnQuanLyPhieuMuonGUI();
        phieuTraPanel=new PnQuanLyPhieuTraGUI();
        phieuPhatGUI=new PnQuanLyPhieuPhatGUI();
        this.add(phieuMuonPanel,"2");
        this.add(phieuTraPanel,"3");
        this.add(phieuPhatGUI,"4");

    }
    public void addEvents(){
        btn_Muon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("2");
            }
        });
        btn_Tra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("3");
            }
        });
        btn_Phat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("4");
            }
        });
        phieuMuonPanel.getCloseButtonPhieuMuon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("1");
            }
        });
        phieuTraPanel.getCloseButtonPhieuTra().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("1");
            }
        });
        //HÀM TRẢ VỀ TRANG CHÍNH
        phieuPhatGUI.getCloseButtonPhieuPhat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("1");
            }
        });
        phieuPhatGUI.getCloseButtonPhieuPhat1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ShowPanelMuonTraPhat("1");
            }
        });
    }



    public void ShowPanelMuonTraPhat (String  i){
        layoutGroup.show(this, i);
    }


}

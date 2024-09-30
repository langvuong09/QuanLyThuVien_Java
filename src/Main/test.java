import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Multi Selection Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Danh sách các mục
            String[] data = {"Option 1", "Option 2", "Option 3", "Option 4"};
            JList<String> list = new JList<>(data);

            // Cho phép chọn nhiều mục
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

            JScrollPane scrollPane = new JScrollPane(list);

            // Nút để hiển thị các mục được chọn
            JButton btnGetSelection = new JButton("Get Selected Items");
            btnGetSelection.addActionListener(e -> {
                List<String> selectedValues = list.getSelectedValuesList();
                JOptionPane.showMessageDialog(frame, "Selected items: " + selectedValues);
            });

            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(btnGetSelection, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}

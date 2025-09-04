package org.escapeone.view.player;

import javax.swing.*;
import java.awt.*;

public class PlayerHeaderPanel extends JPanel {

    private final Font titleFont = new Font("Arial", Font.BOLD, 24);
    private final Color backgroundColor = new Color(45, 45, 45);
    private final Color textColor = Color.WHITE;

    public PlayerHeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(0, 60));

        JLabel title = new JLabel("Cadastro de Player", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(textColor);

        add(title, BorderLayout.CENTER);
    }
}

package org.escapeone.view.main;

import javax.swing.*;
import java.awt.*;

public class MainHeaderPanel extends JPanel {

    private final Font titleFont = new Font("Arial", Font.BOLD, 40);
    private final Color backgroundColor = new Color(45, 45, 45); // fundo cinza escuro
    private final Color titleColor = Color.WHITE;                // texto branco
    private final Dimension headerSize = new Dimension(0, 140);
    private final JLabel title = new JLabel("Escape One", SwingConstants.CENTER);

    public MainHeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setPreferredSize(headerSize);
        title.setFont(titleFont);
        title.setForeground(titleColor);
        add(title, BorderLayout.CENTER);
    }
}

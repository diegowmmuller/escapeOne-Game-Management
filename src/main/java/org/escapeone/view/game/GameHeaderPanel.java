package org.escapeone.view.game;

import javax.swing.*;
import java.awt.*;

public class GameHeaderPanel extends JPanel {

    private final Font titleFont = new Font("Arial", Font.BOLD, 40);
    private final Color backgroundColor = new Color(45, 45, 45);
    private final Color textColor = Color.WHITE;
    private final JLabel title = new JLabel("Cadastro de Jogo", SwingConstants.CENTER);

    public GameHeaderPanel(){
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(0, 140));

        title.setFont(titleFont);
        title.setForeground(textColor);

        add(title, BorderLayout.CENTER);
    }
}

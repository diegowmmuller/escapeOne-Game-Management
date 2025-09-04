package org.escapeone.view.main;

import org.escapeone.view.player.PlayerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainContentPanel extends JPanel {

    private final Color backgroundColor = new Color(165, 42, 42); // dark red background
    private final Color buttonColor = new Color(70, 70, 70);      // medium gray buttons
    private final Color buttonTextColor = Color.WHITE;
    private final Font buttonFont = new Font("Arial", Font.BOLD, 20);

    private JButton btnRegisterGame;
    private JButton btnRegisterPlayer;
    private JButton btnSearch;
    private JButton btnReport;
    private JButton btnTutorial;
    private JButton btnAbout;

    public MainContentPanel() {
        setBackground(backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Top spacing
        add(Box.createVerticalStrut(175));

        // Creating buttons (texts in Portuguese for user)
        btnRegisterGame = createButton("Cadastrar Jogo");
        add(btnRegisterGame);
        add(Box.createVerticalStrut(15));

        btnRegisterPlayer = createButton("Cadastrar Player");
        add(btnRegisterPlayer);
        add(Box.createVerticalStrut(15));

        btnSearch = createButton("Buscar");
        add(btnSearch);
        add(Box.createVerticalStrut(15));

        btnReport = createButton("Relatório");
        add(btnReport);
        add(Box.createVerticalStrut(15));

        btnTutorial = createButton("Tutorial");
        add(btnTutorial);
        add(Box.createVerticalStrut(15));

        btnAbout = createButton("Sobre o Software");
        add(btnAbout);
        add(Box.createVerticalStrut(15));

        JButton btnExit = createButton("Sair");
        add(btnExit);

        // Final spacing
        add(Box.createVerticalGlue());

        // Button actions
        btnExit.addActionListener(exitAction());
        btnRegisterPlayer.addActionListener(openPlayerFrame());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // center horizontally
        button.setMaximumSize(new Dimension(250, 50));    // fixed size
        button.setBackground(buttonColor);
        button.setForeground(buttonTextColor);
        button.setFont(buttonFont);
        button.setFocusPainted(false); // remove focus highlight
        return button;
    }

    private ActionListener exitAction() {
        return e -> System.exit(0);
    }

    private ActionListener openPlayerFrame(){
        System.out.println("Abrindo playerFrame");
        return e -> {
            PlayerFrame playerFrame = new PlayerFrame();
            playerFrame.setVisible(true);
            playerFrame.toFront();
            playerFrame.requestFocus();
        };
    }
}

package org.escapeone.view.player;

import javax.swing.*;
import java.awt.*;

public class PlayerContentPanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private JButton saveButton;
    private JButton cancelButton;

    // 🎨 estilos iguais ao MainFrame
    private final Color buttonColor = new Color(70, 70, 70);
    private final Color buttonTextColor = Color.WHITE;
    private final Font buttonFont = new Font("Arial", Font.BOLD, 20);
    private final Color backgroundColor = new Color(165, 42, 42); // vermelho

    // Fonte para os campos de texto
    private final Font fieldFont = new Font("Arial", Font.PLAIN, 18); // maior e mais legível


    public PlayerContentPanel() {
        setBackground(backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top glue para centralizar verticalmente
        add(Box.createVerticalGlue());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(backgroundColor);

        Dimension fieldSize = new Dimension(200, 30);

        formPanel.add(new JLabel("Nome:"));
        nameField = new JTextField();
        nameField.setPreferredSize(fieldSize);
        nameField.setFont(fieldFont);
        formPanel.add(nameField);

        formPanel.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        emailField.setPreferredSize(fieldSize);
        emailField.setFont(fieldFont);
        formPanel.add(emailField);

        formPanel.add(new JLabel("Telefone:"));
        phoneField = new JTextField();
        phoneField.setPreferredSize(fieldSize);
        phoneField.setFont(fieldFont);
        formPanel.add(phoneField);

        // Centralizar o form horizontalmente
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(backgroundColor);
        formWrapper.add(formPanel);
        add(formWrapper);

        // Espaço entre form e botões
        add(Box.createVerticalStrut(20));

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        saveButton = createStyledButton("Salvar", 150, 40);
        cancelButton = createStyledButton("Cancelar", 150, 40);

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);

        // Bottom glue para centralizar verticalmente
        add(Box.createVerticalGlue());

        // Default actions
        saveButton.addActionListener(e -> savePlayer());
        cancelButton.addActionListener(e -> closeFrame());
    }

    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(buttonColor);
        button.setForeground(buttonTextColor);
        button.setFont(buttonFont);
        button.setFocusPainted(false);
        return button;
    }

    private void savePlayer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        System.out.println("Saving Player: " + name + " | " + email + " | " + phone);
        JOptionPane.showMessageDialog(this, "Player salvo com sucesso!");
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private void closeFrame() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}

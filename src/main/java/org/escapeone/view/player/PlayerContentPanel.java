package org.escapeone.view.player;

import javax.swing.*;
import java.awt.*;

public class PlayerContentPanel extends JPanel {

    // 🔹 Campos do formulário
    private JTextField txtNameField;
    private JTextField txtEmailField;
    private JTextField txtPhoneField;

    private JButton saveButton;
    private JButton cancelButton;

    // 🔹 Estilos
    private static final Color BUTTON_COLOR = new Color(70, 70, 70);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Color BACKGROUND_COLOR = new Color(165, 42, 42); // vermelho
    private static final Font TEXTFIELD_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Color LABEL_COLOR = Color.WHITE;

    public PlayerContentPanel() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(Box.createVerticalGlue());              // Espaço superior
        add(centerComponent(buildFormPanel()));     // Form centralizado
        add(Box.createVerticalStrut(20));           // Espaço entre form e botões
        add(centerComponent(buildButtonPanel()));   // Botões centralizados
        add(Box.createVerticalGlue());              // Espaço inferior
    }

    // 🔹 Monta o formulário
    private JPanel buildFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(BACKGROUND_COLOR);

        txtNameField = createTextField();
        txtEmailField = createTextField();
        txtPhoneField = createTextField();

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(LABEL_FONT);
        nameLabel.setForeground(LABEL_COLOR);
        formPanel.add(nameLabel);
        formPanel.add(txtNameField);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(LABEL_FONT);
        emailLabel.setForeground(LABEL_COLOR);
        formPanel.add(emailLabel);
        formPanel.add(txtEmailField);

        JLabel phoneLabel = new JLabel("Telefone:");
        phoneLabel.setFont(LABEL_FONT);
        phoneLabel.setForeground(LABEL_COLOR);
        formPanel.add(phoneLabel);
        formPanel.add(txtPhoneField);

        // 👉 trava o tamanho para não esticar
        formPanel.setMaximumSize(formPanel.getPreferredSize());

        return formPanel;
    }

    // 🔹 Monta os botões
    private JPanel buildButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        saveButton = createStyledButton("Salvar", 150, 40);
        cancelButton = createStyledButton("Cancelar", 150, 40);

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Ações
        saveButton.addActionListener(e -> savePlayer());
        cancelButton.addActionListener(e -> closeFrame());

        return buttonPanel;
    }

    // 🔹 Criação de JTextField padronizado
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(200, 30));
        field.setFont(TEXTFIELD_FONT);
        return field;
    }

    // 🔹 Criação de JButton padronizado
    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        return button;
    }

    // 🔹 Helper para centralizar qualquer componente no BoxLayout
    private JPanel centerComponent(JComponent comp) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(BACKGROUND_COLOR);
        wrapper.add(comp);
        return wrapper;
    }

    // 🔹 Lógica de salvar
    private void savePlayer() {
        String name = txtNameField.getText();
        String email = txtEmailField.getText();
        String phone = txtPhoneField.getText();

        System.out.println("Saving Player: " + name + " | " + email + " | " + phone);
        JOptionPane.showMessageDialog(this, "Player salvo com sucesso!");
        closeFrame();
    }

    // 🔹 Fechar a janela
    private void closeFrame() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}

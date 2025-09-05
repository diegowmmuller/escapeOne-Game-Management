package org.escapeone.view.game.form;

import org.escapeone.entity.enums.Room;

import javax.swing.*;
import java.awt.*;

public class GameFormPanel extends JPanel {

    private final Font LBL_FONT = new Font("Arial", Font.BOLD, 24);
    private final Font CMB_FONT = new Font("Arial", Font.BOLD, 24);
    private final Color BACKGROUND_COLOR = new Color(165, 42, 42); // vermelho
    private final Color FNT_COLOR = Color.WHITE;

    public GameFormPanel() {
        setBackground(BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // espaçamento
        gbc.fill = GridBagConstraints.NONE; // evita esticar horizontal
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int row = 0;

        // Sala
        JLabel roomLabel = new JLabel("Sala:");
        roomLabel.setFont(LBL_FONT);
        roomLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(roomLabel, gbc);

        JComboBox<Room> comboRoom = new JComboBox<>(Room.values());
        comboRoom.setFont(CMB_FONT);
        comboRoom.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        add(comboRoom, gbc);

        row++;

        // Quantidade de jogadores
        JLabel qtyLabel = new JLabel("Quantidade de jogadores:");
        qtyLabel.setFont(LBL_FONT);
        qtyLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(qtyLabel, gbc);

        JSpinner spinnerPlayerQty = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        spinnerPlayerQty.setFont(CMB_FONT);
        spinnerPlayerQty.setPreferredSize(new Dimension(60, 30));
        gbc.gridx = 1;
        add(spinnerPlayerQty, gbc);

        row++;

        // Data
        JLabel dateLabel = new JLabel("Data:");
        dateLabel.setFont(LBL_FONT);
        dateLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(dateLabel, gbc);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        datePanel.setBackground(BACKGROUND_COLOR);

        JComboBox<Integer> comboDay = new JComboBox<>();
        for (int d = 1; d <= 31; d++) comboDay.addItem(d);
        comboDay.setFont(CMB_FONT);
        comboDay.setPreferredSize(new Dimension(60, 30));
        datePanel.add(comboDay);

        String[] months = {
                "Janeiro","Fevereiro","Março","Abril","Maio","Junho",
                "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
        };
        JComboBox<String> comboMonth = new JComboBox<>(months);
        comboMonth.setFont(CMB_FONT);
        comboMonth.setPreferredSize(new Dimension(120, 30));
        datePanel.add(comboMonth);

        int currentYear = java.time.Year.now().getValue();
        JComboBox<Integer> comboYear = new JComboBox<>();
        for (int y = currentYear; y <= currentYear + 5; y++) comboYear.addItem(y);
        comboYear.setFont(CMB_FONT);
        comboYear.setPreferredSize(new Dimension(100, 30));
        datePanel.add(comboYear);

        gbc.gridx = 1;
        add(datePanel, gbc);

        row++;

        // Hora
        JLabel timeLabel = new JLabel("Hora:");
        timeLabel.setFont(LBL_FONT);
        timeLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(timeLabel, gbc);

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        timePanel.setBackground(BACKGROUND_COLOR);

        JComboBox<Integer> comboHour = new JComboBox<>();
        for (int h = 0; h < 24; h++) comboHour.addItem(h);
        comboHour.setFont(CMB_FONT);
        comboHour.setPreferredSize(new Dimension(60, 30));
        timePanel.add(comboHour);

        timePanel.add(new JLabel(":"));

        JComboBox<Integer> comboMinute = new JComboBox<>(new Integer[]{0, 30});
        comboMinute.setFont(CMB_FONT);
        comboMinute.setPreferredSize(new Dimension(60, 30));
        timePanel.add(comboMinute);

        gbc.gridx = 1;
        add(timePanel, gbc);

        row++;

        // Preço Total
        JLabel priceLabel = new JLabel("Preço Total:");
        priceLabel.setFont(LBL_FONT);
        priceLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(priceLabel, gbc);

        JTextField txtPrice = new JTextField();
        txtPrice.setFont(CMB_FONT);
        txtPrice.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        add(txtPrice, gbc);

        row++;

        // Observações
        JLabel notesLabel = new JLabel("Observações:");
        notesLabel.setFont(LBL_FONT);
        notesLabel.setForeground(FNT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(notesLabel, gbc);

        JTextArea txtNotes = new JTextArea(5, 20);
        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);
        txtNotes.setFont(CMB_FONT);

        JScrollPane scrollNotes = new JScrollPane(txtNotes);
        scrollNotes.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 1;
        add(scrollNotes, gbc);

        // Mantém todos os componentes ligeiramente mais para cima
        gbc.weighty = 1.0;
        gbc.gridy = row + 1;
        add(Box.createVerticalGlue(), gbc);

        System.out.println("Preferred: " + getPreferredSize());
        System.out.println("Minimum: " + getMinimumSize());
        System.out.println("Maximum: " + getMaximumSize());
        System.out.println("Actual: " + getSize());
    }
}

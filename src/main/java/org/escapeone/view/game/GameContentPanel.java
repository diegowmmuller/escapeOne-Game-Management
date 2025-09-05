package org.escapeone.view.game;

import org.escapeone.entity.enums.Room;
import org.escapeone.view.game.form.GameFormPanel;

import javax.swing.*;
import java.awt.*;

public class GameContentPanel extends JPanel {

    Dimension fieldSize = new Dimension(200, 25);

    public GameContentPanel(){
        setLayout(new GridLayout(1, 2, 10, 0));
        add(new GameFormPanel());
        add(buidPlayerForm());
    }


    public JPanel buidPlayerForm(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Cadastro de Jogador"));
        // Aqui você vai incluir o formulário de player + JTable depois
        return panel;
    }
}

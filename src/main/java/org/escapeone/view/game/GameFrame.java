package org.escapeone.view.game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        setTitle("Cadastro de Jogos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        add(new GameHeaderPanel(), BorderLayout.NORTH);
        add(new GameContentPanel(), BorderLayout.CENTER);
    }
}

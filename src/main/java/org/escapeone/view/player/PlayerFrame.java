package org.escapeone.view.player;

import javax.swing.*;
import java.awt.*;

public class PlayerFrame extends JFrame {

    public PlayerFrame(){

        setTitle("Cadastro de Player");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new PlayerHeaderPanel(), BorderLayout.NORTH);
        add(new PlayerContentPanel(), BorderLayout.CENTER);
    }
}

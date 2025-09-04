package org.escapeone.view.main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("Escape One");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());


        add(new MainHeaderPanel(), BorderLayout.NORTH);
        add(new MainContentPanel(), BorderLayout.CENTER);
    }
}

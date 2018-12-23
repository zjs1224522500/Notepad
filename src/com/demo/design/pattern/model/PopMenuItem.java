package com.demo.design.pattern.model;

import javax.swing.*;
import java.awt.*;

public class PopMenuItem extends JMenuItem {

    public static final Color GREEN = new Color(51, 154, 47);

    public PopMenuItem(String name) {
        setText(name);
        setFont(new Font("system", Font.BOLD, 12));
        setBorderPainted(false);
        setForeground(GREEN);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }
}

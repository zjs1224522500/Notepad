package com.demo.design.pattern.demo;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * 模板模式
 */
public abstract class GUIApplication extends JFrame implements ActionListener {

    public abstract void initFrame();

    public abstract void initAction() throws Exception;

    public final void init() throws Exception {
        initFrame();

        initAction();

        setVisible(true);
    }
}

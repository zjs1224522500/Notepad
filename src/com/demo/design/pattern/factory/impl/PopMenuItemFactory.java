package com.demo.design.pattern.factory.impl;

import com.demo.design.pattern.factory.AbstractMenuItemFactory;
import com.demo.design.pattern.model.PopMenuItem;

import javax.swing.*;

public class PopMenuItemFactory extends AbstractMenuItemFactory {
    @Override
    public JMenuItem createJMenuItem(String name) {
        return new PopMenuItem(name);
    }
}

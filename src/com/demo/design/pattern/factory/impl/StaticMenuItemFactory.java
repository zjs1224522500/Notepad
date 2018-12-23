package com.demo.design.pattern.factory.impl;

import com.demo.design.pattern.factory.AbstractMenuItemFactory;
import com.demo.design.pattern.model.StaticMenuItem;

import javax.swing.*;

public class StaticMenuItemFactory extends AbstractMenuItemFactory {

    @Override
    public JMenuItem createJMenuItem(String name) {
        return new StaticMenuItem(name);
    }
}

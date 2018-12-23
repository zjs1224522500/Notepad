package com.demo.design.pattern.factory;

import javax.swing.*;

public abstract class AbstractMenuItemFactory {

    public abstract JMenuItem createJMenuItem(String name);
}

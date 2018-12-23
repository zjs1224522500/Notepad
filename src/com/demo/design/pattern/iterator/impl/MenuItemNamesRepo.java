package com.demo.design.pattern.iterator.impl;

import com.demo.design.pattern.iterator.Container;
import com.demo.design.pattern.iterator.Iterator;

/**
 * 迭代器模式
 */
public class MenuItemNamesRepo implements Container {

    private String[] menuItemNames;

    public MenuItemNamesRepo(String[] menuItemNames) {
        this.menuItemNames = menuItemNames;
    }

    public String[] getMenuItemNames() {
        return menuItemNames;
    }

    public void setMenuItemNames(String[] menuItemNames) {
        this.menuItemNames = menuItemNames;
    }

    @Override
    public Iterator getIterator() {
        return new MenuItemNameIterator();
    }

    private class MenuItemNameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < getMenuItemNames().length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return getMenuItemNames()[index++];
            }
            return null;
        }
    }
}

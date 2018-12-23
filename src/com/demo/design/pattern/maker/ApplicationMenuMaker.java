package com.demo.design.pattern.maker;

import com.demo.design.pattern.constants.INotepadConstants;
import com.demo.design.pattern.factory.FactoryProducer;
import com.demo.design.pattern.iterator.Iterator;
import com.demo.design.pattern.iterator.impl.MenuItemNamesRepo;
import com.demo.design.pattern.model.AboutJMenu;
import com.demo.design.pattern.model.EditJMenu;
import com.demo.design.pattern.model.FileJMenu;
import com.demo.design.pattern.model.IApplicationMenu;

import javax.swing.*;
import java.awt.event.ActionListener;

import static com.demo.design.pattern.constants.INotepadConstants.MenuItemName.*;

public class ApplicationMenuMaker {

    private IApplicationMenu fileMenu;
    private IApplicationMenu editMenu;
    private IApplicationMenu aboutMenu;

    public ApplicationMenuMaker() {
        fileMenu = new FileJMenu();
        editMenu = new EditJMenu();
        aboutMenu = new AboutJMenu();
    }

    /**
     * 外观模式
     * @param listener
     * @return
     */
    public JMenuBar createMenuBar(ActionListener listener) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu(listener));
        menuBar.add(createEditMenu(listener));
        menuBar.add(createAboutMenu(listener));
        return menuBar;
    }

    public JPopupMenu createPopupMenu(ActionListener listener) {
        MenuItemNamesRepo repo = new MenuItemNamesRepo(clickMenuItems);
        JPopupMenu popupMenu = new JPopupMenu();
        for (Iterator it = repo.getIterator(); it.hasNext(); ) {
            JMenuItem item = FactoryProducer.getFactory(INotepadConstants.POP_MENU_ITEM_TYPE).createJMenuItem((String) it.next());
            popupMenu.add(item);
            item.addActionListener(listener);
        }
        return popupMenu;
    }


    private JMenu createFileMenu(ActionListener listener) {
        JMenu file = fileMenu.createMenu();
        return addMenuItems(fileMenuItemNames, file, listener);
    }

    private JMenu createEditMenu(ActionListener listener) {
        JMenu edit = editMenu.createMenu();
        return addMenuItems(editMenuItemNames, edit, listener);
    }


    private JMenu createAboutMenu(ActionListener listener) {
        JMenu about = aboutMenu.createMenu();
        return addMenuItems(aboutMenuItemNames , about, listener);
    }

    /**
     * 迭代器模式
     * @param menuName
     * @param jMenu
     * @param listener
     * @return
     */
    private JMenu addMenuItems(String[] menuName, JMenu jMenu, ActionListener listener) {
        MenuItemNamesRepo repo = new MenuItemNamesRepo(menuName);
        for (Iterator it = repo.getIterator(); it.hasNext(); ) {
            JMenuItem item = FactoryProducer.getFactory(INotepadConstants.STATIC_MENU_ITEM_TYPE).createJMenuItem((String) it.next());
            jMenu.add(item);
            item.addActionListener(listener);
        }
        return jMenu;
    }



}

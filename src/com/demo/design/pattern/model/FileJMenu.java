package com.demo.design.pattern.model;

import com.demo.design.pattern.constants.INotepadConstants;

import javax.swing.*;

public class FileJMenu implements IApplicationMenu {

    @Override
    public JMenu createMenu() {
        return new JMenu(INotepadConstants.MenuName.FILE);
    }
}

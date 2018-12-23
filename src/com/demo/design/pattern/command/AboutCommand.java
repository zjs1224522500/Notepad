package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class AboutCommand implements IOperationCommand {
    @Override
    public void execute() throws IOException, UnsupportedFlavorException {
        JOptionPane.showMessageDialog(null, "Notepad Demo", "软件信息", JOptionPane.INFORMATION_MESSAGE);
    }
}

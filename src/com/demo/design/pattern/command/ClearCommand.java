package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClearCommand implements IOperationCommand {

    private JTextArea textArea;

    public ClearCommand(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() throws IOException, UnsupportedFlavorException {
        int result = JOptionPane.showConfirmDialog(null, "确认清空所有文字吗？", "警告", 1);
        if (result == JOptionPane.OK_OPTION) {
            textArea.setText(null);
        }
    }
}

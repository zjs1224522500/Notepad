package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DeleteCommand implements IOperationCommand {

    private JTextArea textArea;

    public DeleteCommand(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() throws IOException, UnsupportedFlavorException {
        if (textArea.getSelectedText() == null) {
            JOptionPane.showMessageDialog(null, "你没有选中任何文字！", "记事本", JOptionPane.WARNING_MESSAGE);
        }
        textArea.replaceSelection("");
    }
}

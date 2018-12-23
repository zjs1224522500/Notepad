package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyCommand implements IOperationCommand {

    private JTextArea textArea;

    public CopyCommand(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        if (textArea.getSelectedText() == null) {
            JOptionPane.showMessageDialog(null, "你没有选中任何文字！", "记事本", JOptionPane.WARNING_MESSAGE);
        }
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(textArea.getSelectedText());
        clipBoard.setContents(stringSelection, null);
    }
}

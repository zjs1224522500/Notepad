package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CreatCommand implements IOperationCommand {

    private JTextArea textArea;

    public CreatCommand(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() throws IOException, UnsupportedFlavorException {
        textArea.setText("");
    }
}

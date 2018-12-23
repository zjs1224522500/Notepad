package com.demo.design.pattern.command;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class PasteCommand implements IOperationCommand {

    private JTextArea textArea;

    public PasteCommand(JTextArea textArea) {
        this.textArea = textArea;
    }


    @Override
    public void execute() throws IOException, UnsupportedFlavorException {
        String content_copy = "";
        // 构造系统剪切板
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪切板内容
        Transferable content = clipBoard.getContents(null);

        if (content != null) {
            // 检查是否是文本类型
            if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                content_copy = (String) content.getTransferData(DataFlavor.stringFlavor);

                // 判断文本框中有无文字选中
                if (textArea.getSelectedText() != null) {
                    textArea.replaceSelection(content_copy);
                } else {
                    textArea.insert(content_copy, textArea.getSelectionStart());
                }
            }
        }
    }
}

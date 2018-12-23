package com.demo.design.pattern.demo.impl;

import com.demo.design.pattern.command.*;
import com.demo.design.pattern.constants.INotepadConstants;
import com.demo.design.pattern.demo.GUIApplication;
import com.demo.design.pattern.maker.ApplicationMenuMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static com.demo.design.pattern.constants.INotepadConstants.MenuItemName.*;
import static com.demo.design.pattern.constants.INotepadConstants.TITLE;

public class NotepadDemo extends GUIApplication {

    private JTextArea textArea = new JTextArea();
    private String fileName; // 打开的文件名
    private String textContent = "";// 编辑框中的内容
    private JPopupMenu clickPopMenu;
    private Broker broker = new Broker();
    int start, end = 0;

    @Override
    public void initFrame() {
        ApplicationMenuMaker menuMaker = new ApplicationMenuMaker();
        // 弹出菜单
        clickPopMenu = menuMaker.createPopupMenu(this);

        // 给文本区域添加滚动条
        textArea.add(clickPopMenu);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        // 主窗口
        setTitle(TITLE);
        setSize(600, 400);
        setLocation(400, 300);
        // 添加菜单栏
        setJMenuBar(menuMaker.createMenuBar(this));
    }

    @Override
    public void initAction() throws Exception {
        // 窗口监听
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                try {
                    exit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //键盘监听
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                //ctrl+f实现查找功能
                if ((ke.getKeyCode() == KeyEvent.VK_F)
                        && (ke.isControlDown())) {
                    searchAndReplace();
                }
                //esc退出
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        exit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 鼠标监听
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int mods = e.getModifiers();
                // 鼠标右键
                if ((mods & InputEvent.BUTTON3_MASK) != 0) {
                    // 弹出菜单
                    clickPopMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case CREATE:
                    CreatCommand command = new CreatCommand(textArea);
                    broker.takeCommands(command);
                    break;
                case COPY:
                    CopyCommand copyCommand = new CopyCommand(textArea);
                    broker.takeCommands(copyCommand);
                    break;
                case PASTE:
                    PasteCommand pasteCommand = new PasteCommand(textArea);
                    broker.takeCommands(pasteCommand);
                    break;
                case CUT:
                    CopyCommand copy = new CopyCommand(textArea);
                    DeleteCommand delete = new DeleteCommand(textArea);
                    broker.takeCommands(copy);
                    broker.takeCommands(delete);
                    break;
                case DELETE:
                    DeleteCommand deleteCommand = new DeleteCommand(textArea);
                    broker.takeCommands(deleteCommand);
                    break;
                case CLEAR:
                    ClearCommand clearCommand = new ClearCommand(textArea);
                    broker.takeCommands(clearCommand);
                    break;
                case ABOUT:
                    AboutCommand aboutCommand = new AboutCommand();
                    broker.takeCommands(aboutCommand);
                    break;
                case OPEN:
                    open();
                    break;
                case SAVE_AS:
                    saveAs();
                    break;
                case SAVE:
                    save();
                    break;
                case EXIT:
                    exit();
                    break;
                case SEARCH_AND_REPLACE:
                    searchAndReplace();
                    break;
                default:
                    break;
            }

            if (null != broker && null != broker.getCommands() && !broker.getCommands().isEmpty()) {
                broker.executeCommands();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void save() throws Exception {
        if (null != fileName) {
            try {
                File file = new File(fileName);
                FileWriter file_writer = new FileWriter(file);
                //将文件输出流包装进缓冲区
                BufferedWriter bw = new BufferedWriter(file_writer);
                PrintWriter pw = new PrintWriter(bw);

                pw.print(textArea.getText());
                textContent = textArea.getText();
                pw.close();
                bw.close();
                file_writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private void saveAs() {
        FileDialog fileDialog = new FileDialog(this, "另存为", FileDialog.SAVE);
        fileDialog.setFile("UnName.txt");
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            // 写入文件
            FileWriter fw;
            try {
                fw = new FileWriter(fileDialog.getDirectory() + fileDialog.getFile());
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.print(textArea.getText());
                textContent = textArea.getText();
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void open() throws Exception {
        FileDialog fileDialog = new FileDialog(this, "打开文件", FileDialog.LOAD);
        fileDialog.setFile("*.txt");
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            fileName = fileDialog.getDirectory() + fileDialog.getFile();// 获得文件名

            // 读取文件

            FileReader fileReader = new FileReader(fileName);// 此处必须要捕获异常
            BufferedReader br = new BufferedReader(fileReader);
            String temp = "";
            while (br.ready())// 判断缓冲区是否为空，非空时返回true
            {
                int c = br.read();
                temp = temp + (char) c;
            }
            textArea.setText(temp);
            br.close();
            fileReader.close();
            textContent = textArea.getText();
            setTitle("记事本-" + fileName);
        }
    }

    private void exit() throws Exception {
        if (!textArea.getText().equals(textContent)) {
            int result = JOptionPane.showConfirmDialog(null, "文件内容已改变，确认保存退出吗？", "警告", 1);
            switch (result) {
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.YES_OPTION:
                    save();
                    System.exit(0);
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
                default:
                    break;
            }
        } else {
            System.exit(0);

        }
    }


    private void searchAndReplace() {
        // 查找对话框
        JDialog search = new JDialog(this, INotepadConstants.MenuItemName.SEARCH_AND_REPLACE);
        search.setSize(500, 100);
        search.setLocation(450, 350);
        JLabel searchLabel = new JLabel(INotepadConstants.LabelName.SEARCH);
        JLabel replaceLabel = new JLabel(INotepadConstants.LabelName.REPLACE);
        final JTextField searchTextField = new JTextField(5);
        final JTextField replaceTextField = new JTextField(5);
        JButton buttonFind = new JButton(INotepadConstants.ButtonName.SEARCH);
        JButton buttonChange = new JButton(INotepadConstants.ButtonName.REPLACE);
        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(searchLabel);
        panel.add(searchTextField);
        panel.add(buttonFind);
        panel.add(replaceLabel);
        panel.add(replaceTextField);
        panel.add(buttonChange);
        search.add(panel);
        search.setVisible(true);


        // 为查找下一个 按钮绑定监听事件
        buttonFind.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String findText = searchTextField.getText();// 查找的字符串

                String textArea = NotepadDemo.this.textArea.getText();// 当前文本框的内容
                start = textArea.indexOf(findText, end);
                end = start + findText.length();
                // 没有找到
                if (start == -1) {
                    JOptionPane.showMessageDialog(null, "“" + findText + "”" + "已经查找完毕", "记事本", JOptionPane.WARNING_MESSAGE);
                    NotepadDemo.this.textArea.select(start, end);
                } else {
                    NotepadDemo.this.textArea.select(start, end);
                }

            }
        });
        // 为替换按钮绑定监听事件
        buttonChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String changeText = replaceTextField.getText();// 替换的字符串
                textArea.select(start, end);
                textArea.replaceSelection(changeText);
                textArea.select(start, start + changeText.length());
            }
        });
    }

}

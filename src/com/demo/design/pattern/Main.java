package com.demo.design.pattern;

import com.demo.design.pattern.demo.GUIApplication;
import com.demo.design.pattern.demo.impl.NotepadDemo;

public class Main {

    public static void main(String[] args) throws Exception {
        GUIApplication application = new NotepadDemo();
        application.init();
    }
}

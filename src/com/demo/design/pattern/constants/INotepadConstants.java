package com.demo.design.pattern.constants;

public interface INotepadConstants {

    String STATIC_MENU_ITEM_TYPE = "static";
    String POP_MENU_ITEM_TYPE = "pop";
    String TITLE = "Notepad";

    interface MenuName {
        String FILE = "File";
        String EDIT = "Edit";
        String ABOUT = "About";
    }

    interface MenuItemName {
        String COPY = "Copy";
        String CUT = "Cut";
        String PASTE = "Paste";
        String DELETE = "Delete";
        String CLEAR = "Clear";
        String CREATE = "Create";
        String OPEN = "Open";
        String SAVE = "Save";
        String SAVE_AS = "Save As";
        String EXIT = "Exit";
        String SEARCH_AND_REPLACE = "Search and Replace";
        String ABOUT = "About";

        String[] clickMenuItems = {COPY, CUT, PASTE, DELETE, CLEAR};
        String[] fileMenuItemNames = {CREATE, OPEN, SAVE, SAVE_AS, EXIT};
        String[] editMenuItemNames = {COPY, CUT, PASTE, DELETE, SEARCH_AND_REPLACE};
        String[] aboutMenuItemNames = {ABOUT};
    }

    interface LabelName {
        String SEARCH = "Search for :";
        String REPLACE = "Replace with :";
    }

    interface ButtonName {
        String SEARCH = "Search";
        String REPLACE = "Replace";
    }

}

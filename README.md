#### 工厂模式
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/factory/FactoryProducer.java#L16-L33)
#### 单例模式
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/factory/FactoryProducer.java#L16-L33)
```Java
    public static AbstractMenuItemFactory getFactory(String type) {
        /**
         * 单例模式
         */
        if (INotepadConstants.STATIC_MENU_ITEM_TYPE.equals(type)) {
            if (null == staticMenuItemFactory) {
                staticMenuItemFactory = new StaticMenuItemFactory();
            }
            return staticMenuItemFactory;

        } else if (INotepadConstants.POP_MENU_ITEM_TYPE.equals(type)) {
            if (null == popMenuItemFactory) {
                popMenuItemFactory = new PopMenuItemFactory();
            }
            return popMenuItemFactory;

        }
        return null;
    }
```
#### 门面模式（外观模式）
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/maker/ApplicationMenuMaker.java#L29-L51)
```Java
    /**
     * 外观模式
     * @param listener
     * @return
     */
    public JMenuBar createMenuBar(ActionListener listener) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu(listener));
        menuBar.add(createEditMenu(listener));
        menuBar.add(createAboutMenu(listener));
        return menuBar;
    }

    public JPopupMenu createPopupMenu(ActionListener listener) {
        MenuItemNamesRepo repo = new MenuItemNamesRepo(clickMenuItems);
        JPopupMenu popupMenu = new JPopupMenu();
        for (Iterator it = repo.getIterator(); it.hasNext(); ) {
            JMenuItem item = FactoryProducer.getFactory(INotepadConstants.POP_MENU_ITEM_TYPE).createJMenuItem((String) it.next());
            popupMenu.add(item);
            item.addActionListener(listener);
        }
        return popupMenu;
    }
```
#### 迭代器模式
- Define
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/iterator/impl/MenuItemNamesRepo.java#L26-L48)
```Java
    @Override
    public Iterator getIterator() {
        return new MenuItemNameIterator();
    }

    private class MenuItemNameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < getMenuItemNames().length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return getMenuItemNames()[index++];
            }
            return null;
        }
    }
```
- Use
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/maker/ApplicationMenuMaker.java#L70-L85)
```Java
    /**
     * 迭代器模式
     * @param menuName
     * @param jMenu
     * @param listener
     * @return
     */
    private JMenu addMenuItems(String[] menuName, JMenu jMenu, ActionListener listener) {
        MenuItemNamesRepo repo = new MenuItemNamesRepo(menuName);
        for (Iterator it = repo.getIterator(); it.hasNext(); ) {
            JMenuItem item = FactoryProducer.getFactory(INotepadConstants.STATIC_MENU_ITEM_TYPE).createJMenuItem((String) it.next());
            jMenu.add(item);
            item.addActionListener(listener);
        }
        return jMenu;
    }
```
#### 模板模式
- Define
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/demo/GUIApplication.java#L6-L21)
```Java
/**
 * 模板模式
 */
public abstract class GUIApplication extends JFrame implements ActionListener {

    public abstract void initFrame();

    public abstract void initAction() throws Exception;

    public final void init() throws Exception {
        initFrame();

        initAction();

        setVisible(true);
    }
}
```
- Implementation
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/demo/impl/NotepadDemo.java#L26-L88)

#### 命令模式
- Define
[Command Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/command/IOperationCommand.java#L6-L9)
```Java
public interface IOperationCommand {
    void execute() throws IOException, UnsupportedFlavorException;
}
```
[Broker Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/command/Broker.java#L8-L28)
```Java
/**
 * 命令模式
 */
public class Broker {

    private List<IOperationCommand> commands = new ArrayList<>();

    public List<IOperationCommand> getCommands() {
        return commands;
    }

    public void takeCommands(IOperationCommand command) {
        commands.add(command);
    }

    public void executeCommands() throws IOException, UnsupportedFlavorException {
        for (IOperationCommand command : commands) {
            command.execute();
        }
        commands.clear();
    }
}
```
- Implementation e.g.
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/command/CopyCommand.java#L8-L25)
```Java
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
```

- Use
[Source Code](https://github.com/zjs1224522500/Notepad/blob/master/src/com/demo/design/pattern/demo/impl/NotepadDemo.java#L94-L122)
```Java
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
                    ...
                    .
                    .
            if (null != broker && null != broker.getCommands() && !broker.getCommands().isEmpty()) {
                broker.executeCommands();
            }
```

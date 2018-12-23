package com.demo.design.pattern.command;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

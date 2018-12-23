package com.demo.design.pattern.command;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public interface IOperationCommand {

    void execute() throws IOException, UnsupportedFlavorException;
}

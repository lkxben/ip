package cate.command;

import java.util.Stack;

public class CommandManager {
    private final Stack<Command> history = new Stack<>();

    public void addCommand(Command command) {
        if (command.canUndo()) {
            history.push(command);
        }
    }

    public Command popLastCommand() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

    public boolean hasUndoableCommand() {
        return !history.isEmpty();
    }
}


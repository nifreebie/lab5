package org.example.command_support;

import lombok.Getter;
import org.example.command_handlers.*;
import org.example.commands.*;
import org.example.utils.ResponseWriter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CommandManager {
    private final Map<Class, Handler> commands;

    public CommandManager() {
        commands = new HashMap<>();
        commands.put(AddCommand.class, command -> new AddCommandHandler().handle((AddCommand) command));
        commands.put(HelpCommand.class, command -> new HelpCommandHandler().handle((HelpCommand) command));
        commands.put(RemoveByIdCommand.class, command -> new RemoveByIdCommandHandler().handle((RemoveByIdCommand) command));
        commands.put(ShowCommand.class, command -> new ShowCommandHandler().handle((ShowCommand) command));
        commands.put(SaveCommand.class, command -> new SaveCommandHandler().handle((SaveCommand) command));
        commands.put(ExitCommand.class, command -> new ExitCommandHandler().handle((ExitCommand) command));
        commands.put(InfoCommand.class, command -> new InfoCommandHandler().handle((InfoCommand) command));
        commands.put(ClearCommand.class, command -> new ClearCommandHandler().handle((ClearCommand) command));
        commands.put(UpdateCommand.class, command -> new UpdateCommandHandler().handle((UpdateCommand) command));
        commands.put(RemoveGreaterCommand.class, command -> new RemoveGraeterCommandHandler().handle((RemoveGreaterCommand) command));
        commands.put(RemoveLowerCommand.class, command -> new RemoveLowerCommandHandler().handle((RemoveLowerCommand) command));
        commands.put(AddIfMinCommand.class, command -> new AddIfMinCommandHandler().handle((AddIfMinCommand) command));
        commands.put(PrintDescendingCommand.class, command -> new PrintDescendingCommandHandler().handle((PrintDescendingCommand) command));
        commands.put(PrintFieldDescendingPartNumberCommand.class, command -> new PrintDescendingPartNumberCommandHandler().handle((PrintFieldDescendingPartNumberCommand) command));
        commands.put(ExecuteScriptCommand.class, command -> new ExecuteScriptCommandHandler().handle((ExecuteScriptCommand) command));


    }

    public void executeCommand(Command command) {
        commands.get(command.getClass()).apply(command);
    }


}

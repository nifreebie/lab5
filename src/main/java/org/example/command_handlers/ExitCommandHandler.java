package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.ExitCommand;
import org.example.controller.ReqWriter;

public class ExitCommandHandler extends CommandHandler<ExitCommand> {
    @Override
    public String handle(ExitCommand command) {
        this.app.getResponseWriter().write("Завершение программы...");
        System.exit(0);
        return null;
    }
}

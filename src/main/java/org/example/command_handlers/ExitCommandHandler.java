package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.ExitCommand;

public class ExitCommandHandler extends CommandHandler<ExitCommand> {
    @Override
    public void handle(ExitCommand command) {
        this.app.getResponseWriter().write("Завершение программы...");
        System.exit(0);
    }
}

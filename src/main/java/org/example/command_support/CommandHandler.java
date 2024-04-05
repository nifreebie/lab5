package org.example.command_support;

import org.example.service.AppContainer;

abstract public class CommandHandler<T extends Command> {
    protected final AppContainer app;

    public CommandHandler() {
        this.app = AppContainer.getInstance();
    }

    public abstract String handle(T command);
}

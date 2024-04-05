package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.ClearCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;

public class ClearCommandHandler extends CommandHandler<ClearCommand> {
    @Override
    public String handle(ClearCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.clear();
        this.app.getResponseWriter().write("✓Коллекция очищена");
        return null;
    }
}

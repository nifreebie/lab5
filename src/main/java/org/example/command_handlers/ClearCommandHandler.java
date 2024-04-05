package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.ClearCommand;
import org.example.dao.CollectionManager;
import org.example.utils.ResponseWriter;

public class ClearCommandHandler extends CommandHandler<ClearCommand> {
    @Override
    public void handle(ClearCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        collectionManager.clear();
        responseWriter.write("✓Коллекция очищена");
    }
}

package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.RemoveByIdCommand;
import org.example.dao.CollectionManager;
import org.example.utils.ResponseWriter;

public class RemoveByIdCommandHandler extends CommandHandler<RemoveByIdCommand> {
    @Override
    public void handle(RemoveByIdCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        if (collectionManager.getSize() == 0) {
            responseWriter.write("Коллекция пустая");
        } else {
            long findId = command.id;
            if (!collectionManager.isIdExists(findId)) responseWriter.write("Такого id не существует!");
            else {
                app.getCollectionManager().removeById(findId);
                responseWriter.write("✓Продукт с id: " + findId + " был удален");
            }
        }
    }


}

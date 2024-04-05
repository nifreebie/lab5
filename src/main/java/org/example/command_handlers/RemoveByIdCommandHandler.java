package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.RemoveByIdCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;

public class RemoveByIdCommandHandler extends CommandHandler<RemoveByIdCommand> {
    @Override
    public String handle(RemoveByIdCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.getSize() == 0) {
            this.app.getResponseWriter().write("Коллекция пустая");
        } else {
            long findId = command.id;
            if (!collectionManager.isIdExists(findId)) System.out.println("Такого id не существует!");
            else {
                app.getCollectionManager().removeById(findId);
                this.app.getResponseWriter().write("✓Продукт с id: " + findId + " был удален");
            }
        }
        return null;
    }


}

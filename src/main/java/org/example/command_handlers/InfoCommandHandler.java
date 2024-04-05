package org.example.command_handlers;

import org.example.command_support.Command;
import org.example.command_support.CommandHandler;
import org.example.commands.InfoCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;
import org.example.model.Product;

public class InfoCommandHandler extends CommandHandler<InfoCommand> {
    @Override
    public String handle(InfoCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        this.app.getResponseWriter().write("Информация о коллекции:");
        this.app.getResponseWriter().write("Тип коллекции: " + collectionManager.getProducts().getClass().getSimpleName());
        this.app.getResponseWriter().write("Тип элементов коллекции: " + Product.class.getSimpleName());
        this.app.getResponseWriter().write("Количество элементов: " + collectionManager.getSize());
        return null;
    }
}

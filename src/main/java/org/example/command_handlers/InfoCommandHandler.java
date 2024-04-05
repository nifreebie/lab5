package org.example.command_handlers;


import org.example.command_support.CommandHandler;
import org.example.commands.InfoCommand;
import org.example.dao.CollectionManager;
import org.example.model.Product;
import org.example.utils.ResponseWriter;

public class InfoCommandHandler extends CommandHandler<InfoCommand> {
    @Override
    public void handle(InfoCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        responseWriter.write("Информация о коллекции:");
        responseWriter.write("Тип коллекции: " + collectionManager.getProducts().getClass().getSimpleName());
        responseWriter.write("Тип элементов коллекции: " + Product.class.getSimpleName());
        responseWriter.write("Количество элементов: " + collectionManager.getSize());
    }
}

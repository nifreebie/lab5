package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.commands.ShowCommand;
import org.example.dao.CollectionManager;
import org.example.model.Product;

public class ShowCommandHandler extends CommandHandler<ShowCommand> {
    @Override
    public void handle(ShowCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.getSize() == 0) {
            this.app.getResponseWriter().write("Коллекция пустая");
        } else {
            for (Product p : collectionManager.getProducts()) {
                this.app.getResponseWriter().write(p.toString());
            }
        }
    }


}

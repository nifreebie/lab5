package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.commands.SaveCommand;
import org.example.dao.CollectionManager;

public class SaveCommandHandler extends CommandHandler<SaveCommand> {
    @Override
    public void handle(SaveCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
        collectionManager.save();
        this.app.getResponseWriter().write("✓Коллекция сохранена");
    }


}

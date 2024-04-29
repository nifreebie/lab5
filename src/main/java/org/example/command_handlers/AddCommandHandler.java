package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.command_support.ProductCreator;
import org.example.commands.AddCommand;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;
import org.example.utils.ResponseWriter;

public class AddCommandHandler extends CommandHandler<AddCommand> {

    public void handle(AddCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        collectionManager.add(command.productDTO);
        responseWriter.write("✓Продукт добавлен в коллекцию");
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
    }
}

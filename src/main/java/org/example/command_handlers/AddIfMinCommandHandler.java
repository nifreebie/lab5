package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.command_support.ProductCreator;
import org.example.commands.AddIfMinCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;

public class AddIfMinCommandHandler extends CommandHandler<AddIfMinCommand> {
    @Override
    public String handle(AddIfMinCommand command) {
        ProductCreator productCreator = new ProductCreator();
        ProductDTO productDTO = productCreator.createNewProduct();
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.checkIfMin(productDTO)) {
            collectionManager.add(productDTO);
            this.app.getResponseWriter().write("✓Продукт добавлен в коллекцию");
            ProductComparator productComparator = new ProductComparator();
            collectionManager.sort(productComparator);
        } else {
            this.app.getResponseWriter().write("Введенный продукт больше наименьшего в коллекции!");
        }
        return null;
    }
}

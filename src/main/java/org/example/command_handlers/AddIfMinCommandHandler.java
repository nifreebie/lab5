package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.command_support.ProductCreator;
import org.example.commands.AddIfMinCommand;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;
import org.example.utils.ResponseWriter;

public class AddIfMinCommandHandler extends CommandHandler<AddIfMinCommand> {
    @Override
    public void handle(AddIfMinCommand command) {
        ProductCreator productCreator = new ProductCreator();
        ProductDTO productDTO = productCreator.createNewProduct();
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        if (collectionManager.checkIfMin(productDTO)) {
            collectionManager.add(productDTO);
            responseWriter.write("✓Продукт добавлен в коллекцию");
            ProductComparator productComparator = new ProductComparator();
            collectionManager.sort(productComparator);
        } else {
            responseWriter.write("Введенный продукт больше наименьшего в коллекции!");
        }

    }
}

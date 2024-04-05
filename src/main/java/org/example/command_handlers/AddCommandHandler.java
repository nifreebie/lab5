package org.example.command_handlers;

import org.example.command_support.Command;
import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.command_support.ProductCreator;
import org.example.commands.AddCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;

public class AddCommandHandler extends CommandHandler<AddCommand> {

    public String handle(AddCommand command) {
        ProductCreator productCreator = new ProductCreator();
        ProductDTO productDTO = productCreator.createNewProduct();
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.add(productDTO);
        this.app.getResponseWriter().write("✓Продукт добавлен в коллекцию");
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
        return "✓Продукт добавлен в коллекцию";
    }
}

package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductCreator;
import org.example.commands.RemoveLowerCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;

public class RemoveLowerCommandHandler extends CommandHandler<RemoveLowerCommand> {
    @Override
    public String handle(RemoveLowerCommand command) {
        ProductCreator productCreator = new ProductCreator();
        ProductDTO productDTO = productCreator.createNewProduct();
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.removeLower(productDTO);
        this.app.getResponseWriter().write("✓Все продукты, превышаюие введенный были удалены");
        return null;
    }
}

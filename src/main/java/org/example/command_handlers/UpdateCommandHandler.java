package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductCreator;
import org.example.commands.UpdateCommand;
import org.example.controller.ReqWriter;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;

public class UpdateCommandHandler extends CommandHandler<UpdateCommand> {
    @Override
    public String handle(UpdateCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        if (collectionManager.getSize() == 0) {
            this.app.getResponseWriter().write("Коллекция пустая");
        }else{
            long updateId = command.id;
            if (!collectionManager.isIdExists(updateId)) {
                this.app.getResponseWriter().write("Такого id не существует!");
            } else {
                ProductCreator productCreator = new ProductCreator();
                ProductDTO productDTO = productCreator.createNewProduct();
                collectionManager.updateById(updateId, productDTO);
                this.app.getResponseWriter().write("✓Продукт с id: " + updateId + " был обновлен");
            }
        }
        return null;
    }
}

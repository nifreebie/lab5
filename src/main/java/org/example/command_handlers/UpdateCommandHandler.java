package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductCreator;
import org.example.commands.UpdateCommand;
import org.example.dao.CollectionManager;
import org.example.model.ProductDTO;
import org.example.utils.ResponseWriter;

public class UpdateCommandHandler extends CommandHandler<UpdateCommand> {
    @Override
    public void handle(UpdateCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        if (collectionManager.getSize() == 0) {
            responseWriter.write("Коллекция пустая");
        }else{
            long updateId = command.id;
            if (!collectionManager.isIdExists(updateId)) {
                responseWriter.write("Такого id не существует!");
            } else {
                ProductCreator productCreator = new ProductCreator();
                ProductDTO productDTO = productCreator.createNewProduct();
                collectionManager.updateById(updateId, productDTO);
                responseWriter.write("✓Продукт с id: " + updateId + " был обновлен");
            }
        }
    }
}

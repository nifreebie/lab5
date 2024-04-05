package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.PartNumberProductComparator;
import org.example.command_support.ProductComparator;
import org.example.commands.PrintFieldDescendingPartNumberCommand;
import org.example.dao.CollectionManager;
import org.example.model.Product;

public class PrintDescendingPartNumberCommandHandler extends CommandHandler<PrintFieldDescendingPartNumberCommand> {
    @Override
    public void handle(PrintFieldDescendingPartNumberCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        PartNumberProductComparator comparator = new PartNumberProductComparator();
        collectionManager.sort(comparator);
        for (Product p : collectionManager.getProducts()) {
            this.app.getResponseWriter().write(p.getPartNumber());
        }
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
    }
}

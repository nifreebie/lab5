package org.example.command_handlers;

import org.example.command_support.CommandHandler;
import org.example.command_support.ProductComparator;
import org.example.command_support.ReverseProductComparator;
import org.example.commands.PrintDescendingCommand;
import org.example.dao.CollectionManager;
import org.example.model.Product;

public class PrintDescendingCommandHandler extends CommandHandler<PrintDescendingCommand> {
    @Override
    public void handle(PrintDescendingCommand command) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ReverseProductComparator reverseProductComparator = new ReverseProductComparator();
        collectionManager.sort(reverseProductComparator);
        for (Product p : collectionManager.getProducts()) {
            this.app.getResponseWriter().write(p.toString());
        }
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
    }
}

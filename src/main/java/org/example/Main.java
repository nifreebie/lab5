package org.example;

import org.example.command_support.CommandManager;
import org.example.command_support.ProductComparator;
import org.example.controller.ConsoleManager;
import org.example.dao.CollectionManager;
import org.example.dao.Storage;
import org.example.model.Product;
import org.example.service.AppContainer;
import org.example.utils.BufferedLineReader;
import org.example.utils.ResponseWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args)  {
        initAppContainer();
        initCollection(args);
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.run();



    }
    private static void initAppContainer() {
        AppContainer app = AppContainer.getInstance();
        BufferedLineReader reader = new BufferedLineReader(System.in);
        ResponseWriter writer = new ResponseWriter();
        app.setBufferedLineReader(reader);
        app.setResponseWriter(writer);
        CommandManager commandManager = new CommandManager();
        app.setCommandManager(commandManager);
    }

    private static void initCollection(String[] args) {
        Set<Product> productCollection;
        long newLastId = 0;
        if (args.length > 0) {
            try {
                String fileName = args[0];
                productCollection = Storage.read(fileName);
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла!");
                productCollection = new LinkedHashSet<>();

            }


        } else {
            productCollection = new LinkedHashSet<>();
        }
        CollectionManager collectionManager = new CollectionManager(productCollection);
        AppContainer.getInstance().setCollectionManager(collectionManager);
        newLastId = collectionManager.getMaxId();
        AppContainer.getInstance().getCollectionManager().setLastId(newLastId);
        ProductComparator productComparator = new ProductComparator();
        collectionManager.sort(productComparator);
    }
}
package org.example.commands;


import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.ProductCreator;
import org.example.command_support.exceptions.ExtraArgumentException;
import org.example.model.ProductDTO;

@NoArgsConstructor
public class AddCommand implements Command {

    public ProductDTO productDTO;
    public AddCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
        ProductCreator productCreator = new ProductCreator();
        productDTO = productCreator.createNewProduct();

    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}

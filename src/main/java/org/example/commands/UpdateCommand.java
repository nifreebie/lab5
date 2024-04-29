package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.CommandWithIdArgument;
import org.example.command_support.ProductCreator;
import org.example.command_support.exceptions.NoArgumentException;
import org.example.model.ProductDTO;

@NoArgsConstructor
public class UpdateCommand implements Command, CommandWithIdArgument {
    public long id;
    public ProductDTO productDTO;
    public UpdateCommand(String[] str) {
        if(str.length == 1) throw new NoArgumentException();
        if(checkArgForId(str[1])){
            if(Long.parseLong(str[1])<=0) throw new NumberFormatException();
            this.id = Long.parseLong(str[1]);
            ProductCreator productCreator = new ProductCreator();
            productDTO = productCreator.createNewProduct();
        }else{
            throw new NumberFormatException();


        }


    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }
}

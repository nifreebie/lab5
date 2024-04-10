package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.CommandWithIdArgument;
import org.example.command_support.exceptions.NoArgumentException;
@NoArgsConstructor
public class UpdateCommand implements Command, CommandWithIdArgument {
    public long id;
    public UpdateCommand(String[] str) {
        if(str.length == 1) throw new NoArgumentException();
        if(checkArgForId(str[1])){
            if(Long.parseLong(str[1])<=0) throw new NumberFormatException();
            this.id = Long.parseLong(str[1]);
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
